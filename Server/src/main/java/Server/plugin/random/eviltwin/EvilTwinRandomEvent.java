package plugin.random.eviltwin;

import java.nio.ByteBuffer;

import core.ServerConstants;
import core.game.component.CloseEvent;
import core.game.component.Component;
import plugin.ame.AntiMacroEvent;
import core.game.content.global.action.DoorActionHandler;
import core.game.interaction.Option;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.map.build.DynamicRegion;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.net.packet.PacketRepository;
import core.net.packet.context.CameraContext;
import core.net.packet.context.CameraContext.CameraType;
import core.net.packet.out.CameraViewPacket;
import core.plugin.PluginManager;
import core.tools.RandomFunction;

/**
 * Handles the evil twin random event.
 * @author Emperor
 */
public final class EvilTwinRandomEvent extends AntiMacroEvent {

	/**
	 * The x-offset.
	 */
	private int offsetX = 0;

	/**
	 * The y-offset.
	 */
	private int offsetY = 0;

	/**
	 * The Molly NPC.
	 */
	private NPC molly;

	/**
	 * The current crane object.
	 */
	private GameObject currentCrane;

	/**
	 * The amount of tries left.
	 */
	private int tries = 3;

	/**
	 * If the event has been completed.
	 */
	private boolean success;

	/**
	 * The crane NPC.
	 */
	private NPC craneNPC;

	/**
	 * Constructs a new {@code EvilTwinRandomEvent} {@code Object}.
	 */
	public EvilTwinRandomEvent() {
		this(null);
	}

	/**
	 * Constructs a new {@code EvilTwinRandomEvent} {@code Object}.
	 * @param player The player.
	 */
	public EvilTwinRandomEvent(Player player) {
		super("Evil twin", true, true);
		super.player = player;
	}

	@Override
	public void register() {
		PluginManager.definePlugin(new MollyDialogue());
	}

	@Override
	public boolean start(final Player player, boolean login, Object... args) {
		region = DynamicRegion.create(7504);
		region.setMusicId(612);
		registerRegion(region.getId());
		currentCrane = new GameObject(14976, region.getBaseLocation().transform(14, 12, 0), 10, 0);
		if (login) {
			int hash = player.getAttribute("ame:evil_twin", 0);
			molly = NPC.create(getMollyId(hash), region.getBaseLocation().transform(4, 15, 0));
			molly.init();
			spawnSuspects(hash);
			toggleVisibleNPCs(player.getLocation().getLocalX() < 9);
			player.getProperties().setTeleportLocation(region.getBaseLocation().transform(offsetX, offsetY, 0));
			return true;
		}
		TwinClothColor color = RandomFunction.getRandomElement(TwinClothColor.values());
		int model = RandomFunction.random(5);
		final int hash = color.ordinal() | (model << 16);
		player.setAttribute("ame:evil_twin", hash);
		int npcId = getMollyId(hash);
		molly = NPC.create(npcId, Location.getRandomLocation(player.getLocation(), 2, true));
		molly.init();
		molly.graphics(Graphics.create(86, 96));
		molly.sendChat("I need your help, " + player.getUsername() + ".");
		molly.face(player);
		player.lock(4);
		player.setAttribute("ame:location", player.getLocation());
		GameWorld.getPulser().submit(new Pulse(3) {
			@Override
			public boolean pulse() {
				teleport(player, molly, hash);
				return true;
			}
		});
		return true;
	}

	@Override
	public boolean enter(Entity entity) {
		if (entity instanceof Player) {
			((Player) entity).getInterfaceManager().hideTabs(0, 1, 2, 3, 4, 5, 6, 7, 11, 12);
		}
		return super.enter(entity);
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		if (e == player) {
			if (!logout) {
				terminate();
			}
			player.removeAttribute("ame:evil_twin:ccx");
			player.removeAttribute("ame:evil_twin:ccy");
		}
		return super.leave(e, logout);
	}

	@Override
	public void terminate() {
		super.terminate();
		craneNPC = null;
		if (player != null) {
			player.getInterfaceManager().restoreTabs();
			player.removeAttribute("ame:location");
			player.removeAttribute("ame:evil_twin_info");
			PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.RESET, 0, 0, 0, 0, 0));
		}
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		if (target instanceof GameObject) {
			GameObject object = (GameObject) target;
			switch (object.getId()) {
			case 14982:
				if (e.getLocation().getLocalX() < 9 && !e.getAttribute("ame:evil_twin_info", false)) {
					player.getDialogueInterpreter().open("ame:molly", molly, 3);
					return true;
				}
				DoorActionHandler.handleAutowalkDoor(e, object);
				return true;
			case 14978:
				if (success) {
					player.getPacketDispatch().sendMessage("You already caught the evil twin.");
					return true;
				}
				player.getInterfaceManager().openSingleTab(new Component(240).setCloseEvent(new CloseEvent() {
					@Override
					public boolean close(Player player, Component c) {
						ObjectBuilder.remove(currentCrane);
						ObjectBuilder.add(new GameObject(66, currentCrane.getLocation(), 22, 0));
						currentCrane = currentCrane.transform(currentCrane.getId(), currentCrane.getRotation(), region.getBaseLocation().transform(14, 12, 0));
						ObjectBuilder.add(new GameObject(14977, currentCrane.getLocation(), 22, 0));
						ObjectBuilder.add(currentCrane);
						PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.RESET, 0, 0, 0, 0, 0));
						return true;
					}
				}));
				player.getPacketDispatch().sendString("Tries: " + tries, 240, 27);
				updateCraneCam(14, 12);
				return true;
			}
		} else if (target instanceof NPC) {
			NPC npc = (NPC) target;
			if ((tries < 1 || success) && npc.getId() >= 3892 && npc.getId() <= 3911) {
				player.getDialogueInterpreter().open("ame:molly", npc, success ? 2 : 1);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean actionButton(final Player player, int interfaceId, int buttonId, int slot, int itemId, int opcode) {
		if (success) {
			return false;
		}
		switch (interfaceId) {
		case 240:
			switch (buttonId) {
			case 28:
				player.lock(5);
				player.getLocks().lockComponent(5);
				success = false;
				for (final NPC npc : region.getPlanes()[0].getNpcs()) {
					if (npc.getLocation().equals(currentCrane.getLocation())) {
						final boolean evilTwin = isEvilTwin(npc, player.getAttribute("ame:evil_twin", 0));
						if (evilTwin) {
							success = true;
							player.getPacketDispatch().sendMessage("You caught the Evil twin!");
						} else {
							player.getPacketDispatch().sendMessage("You caught an innocent civilian!");
						}
						npc.animate(Animation.create(4001));
						npc.lock(10);
						player.lock(10);
						player.getLocks().lockComponent(15);
						updateCraneCam(10, 4);
						GameWorld.getPulser().submit(new Pulse(5, player) {
							int cycle = 0;

							@Override
							public boolean pulse() {
								switch (cycle++) {
								case 0:
									ObjectBuilder.remove(currentCrane);
									ObjectBuilder.add(new GameObject(66, currentCrane.getLocation(), 22, 0));
									npc.transform(npc.getId() + 20);
									npc.lock(20);
									npc.getWalkingQueue().reset();
									npc.getWalkingQueue().addPath(region.getBaseLocation().getX() + 10, region.getBaseLocation().getY() + 4);
									setDelay(npc.getWalkingQueue().getQueue().size() + 1);
									craneNPC = npc;
									break;
								case 1:
									craneNPC = null;
									npc.animate(Animation.create(4003));
									setDelay(3);
									break;
								case 2:
									npc.reTransform();
									npc.faceLocation(player.getLocation());
									if (evilTwin) {
										removeSuspects();
										npc.getAnimator().forceAnimation(Animation.create(859));
									} else {
										npc.sendChat("You're putting me in prison?!");
									}
									currentCrane = currentCrane.transform(currentCrane.getId(), currentCrane.getRotation(), region.getBaseLocation().transform(14, 12, 0));
									ObjectBuilder.add(new GameObject(14977, currentCrane.getLocation(), 22, 0));
									ObjectBuilder.add(currentCrane);
									break;
								case 3:
									updateCraneCam(14, 12);
									if (evilTwin) {
										player.getInterfaceManager().closeSingleTab();
										player.getDialogueInterpreter().open("ame:molly", molly, 0);
									} else {
										decreaseTries();
									}
									return true;
								}
								return false;
							}
						});
						player.getPacketDispatch().sendObjectAnimation(currentCrane, Animation.create(4000));
						return true;
					}
				}
				decreaseTries();
				player.getPacketDispatch().sendObjectAnimation(currentCrane, Animation.create(4000));
				return true;
			case 29:
				moveCrane(Direction.SOUTH);
				return true;
			case 30:
				moveCrane(Direction.NORTH);
				return true;
			case 31:
				moveCrane(Direction.EAST);
				return true;
			case 32:
				moveCrane(Direction.WEST);
				return true;
			case 34:
				player.getInterfaceManager().closeSingleTab();
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * Decreases the tries amount.
	 */
	public void decreaseTries() {
		tries--;
		player.getPacketDispatch().sendString("Tries: " + tries, 240, 27);
		if (tries < 1) {
			player.lock(20);
			player.getInterfaceManager().closeSingleTab();
			player.getDialogueInterpreter().open("ame:molly", molly, 1);
		}
	}

	@Override
	public void locationUpdate(Entity e, Location last) {
		if (e == craneNPC && e.getWalkingQueue().getQueue().size() > 1 && player.getInterfaceManager().getSingleTab() != null) {
			Location l = e.getLocation();
			PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, l.getX() + 2, l.getY() + 3, 650, 1, 5));
			PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, l.getX() - 3, l.getY() - 3, 420, 1, 5));
		} else if (e == player) {
			if (molly.isInvisible() && e.getLocation().getLocalX() < 9) {
				toggleVisibleNPCs(true);
			} else if (!molly.isInvisible() && e.getLocation().getLocalX() > 8) {
				toggleVisibleNPCs(false);
			}
		}
		super.locationUpdate(e, last);
	}

	/**
	 * Toggles the visibility of the NPCs.
	 * @param showMolly If molly should be shown.
	 */
	public void toggleVisibleNPCs(boolean showMolly) {
		for (NPC npc : region.getPlanes()[0].getNpcs()) {
			if (npc.getId() >= 3852 && npc.getId() <= 3891) {
				npc.setInvisible(showMolly);
			}
		}
		molly.setInvisible(!showMolly);
	}

	/**
	 * Method used to handle the camera.
	 * @param x the x offset.
	 * @param y the y offset.
	 * @Param height the height.
	 */
	private void updateCraneCam(int x, int y) {
		if (player.getInterfaceManager().getSingleTab() != null) {
			Location loc = region.getBaseLocation().transform(14, 20, 0);
			PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, loc.getX(), loc.getY(), 650, 1, 100));
			loc = region.getBaseLocation().transform(x, 4 + y - (x < 14 || x > 14 ? (y / 4) : 0), 0);
			PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, loc.getX(), loc.getY(), 420, 1, 100));
		}
		player.setAttribute("ame:evil_twin:ccx", x);
		player.setAttribute("ame:evil_twin:ccy", y);
	}

	/**
	 * Removes the suspects.
	 */
	public void removeSuspects() {
		int hash = player.getAttribute("ame:evil_twin", 0);
		for (final NPC npc : region.getPlanes()[0].getNpcs()) {
			if (npc.getId() >= 3852 && npc.getId() <= 3891 && !isEvilTwin(npc, hash)) {
				Graphics.send(Graphics.create(86), npc.getLocation());
				npc.clear();
			}
		}
	}

	/**
	 * Checks if the NPC is the evil twin NPC.
	 * @param npc The NPC.
	 * @return {@code True} if so.
	 */
	private boolean isEvilTwin(NPC npc, int hash) {
		int npcId = npc.getId() - 3852;
		int type = npcId / TwinClothColor.values().length;
		int color = npcId - (type * TwinClothColor.values().length);
		return hash == (color | (type << 16));
	}

	/**
	 * Moves the crane.
	 * @param direction The current direction.
	 */
	private void moveCrane(final Direction direction) {
		GameWorld.getPulser().submit(new Pulse(1, player) {
			@Override
			public boolean pulse() {
				if (!direction.canMove(currentCrane.getLocation().transform(direction))) {
					return true;
				}
				int craneX = player.getAttribute("ame:evil_twin:ccx", 14) + direction.getStepX();
				int craneY = player.getAttribute("ame:evil_twin:ccy", 12) + direction.getStepY();
				updateCraneCam(craneX, craneY);
				ObjectBuilder.remove(currentCrane);
				ObjectBuilder.add(new GameObject(66, currentCrane.getLocation(), 22, 0));
				currentCrane = currentCrane.transform(currentCrane.getId(), currentCrane.getRotation(), region.getBaseLocation().transform(craneX, craneY, 0));
				ObjectBuilder.add(new GameObject(14977, currentCrane.getLocation(), 22, 0));
				ObjectBuilder.add(currentCrane);
				return true;
			}
		});
	}

	@Override
	public boolean teleport(Entity e, int type, Node node) {
		if (type != -1) {
			if (e instanceof Player) {
				((Player) e).getPacketDispatch().sendMessage("You can't teleport away from here.");
			}
			return false;
		}
		return true;
	}

	/**
	 * Spawns the suspects.
	 * @param hash The color & type.
	 */
	public void spawnSuspects(int hash) {
		if (region.getPlanes()[0].getNpcs().size() > 3) {
			return;
		}
		int npcId = 3852 + (hash & 0xFF);
		for (int i = 0; i < 5; i++) {
			Location location = region.getBaseLocation().transform(11 + RandomFunction.random(8), 6 + RandomFunction.random(6), 0);
			NPC suspect = NPC.create(npcId + (i * TwinClothColor.values().length), location);
			suspect.setWalks(true);
			suspect.setWalkRadius(6);
			suspect.init();
		}
	}

	/**
	 * Teleports the player and Molly to the random event region.
	 * @param player The player.
	 * @param npc The NPC.
	 */
	public void teleport(Player player, NPC npc, int hash) {
		npc.getProperties().setTeleportLocation(region.getBaseLocation().transform(4, 15, 0));
		player.getProperties().setTeleportLocation(region.getBaseLocation().transform(4, 16, 0));
		player.getDialogueInterpreter().open(npc.getId(), npc);
		spawnSuspects(hash);
		toggleVisibleNPCs(true);
	}

	/**
	 * Gets the current NPC id for Molly.
	 * @return The NPC id.
	 */
	public static int getMollyId(int hash) {
		return 3892 + (hash & 0xFF) + (((hash >> 16) & 0xFF) * TwinClothColor.values().length);
	}

	@Override
	public void save(ByteBuffer buffer) {
		int offset = player.getAttribute("ame:evil_twin", 0);
		buffer.put((byte) (offset & 0xFF));
		buffer.put((byte) ((offset >> 16) & 0xFF));
		buffer.put((byte) (player.getLocation().getX() - region.getBaseLocation().getX()));
		buffer.put((byte) (player.getLocation().getY() - region.getBaseLocation().getY()));
		buffer.put((byte) tries);
		Location loc = player.getAttribute("ame:location", ServerConstants.HOME_LOCATION);
		buffer.putShort((short) loc.getX());
		buffer.putShort((short) loc.getY());
		buffer.put((byte) loc.getZ());
		buffer.put((byte) (success ? 1 : 0));
	}

	@Override
	public void parse(ByteBuffer buffer) {
		player.setAttribute("ame:evil_twin", (buffer.get() & 0xFF) | ((buffer.get() & 0xFF) << 16));
		offsetX = buffer.get() & 0xFF;
		offsetY = buffer.get() & 0xFF;
		tries = buffer.get() & 0xFF;
		player.setAttribute("ame:location", Location.create(buffer.getShort(), buffer.getShort(), buffer.get()));
		success = (buffer.get() & 0xFF) == 1;
	}

	@Override
	public AntiMacroEvent create(Player player) {
		return new EvilTwinRandomEvent(player);
	}

	@Override
	public Location getSpawnLocation() {
		return null;
	}

	@Override
	public void configure() {
	}

}