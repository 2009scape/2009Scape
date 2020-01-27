package plugin.holiday.halloween;

import java.util.ArrayList;
import java.util.List;

import org.crandor.ServerConstants;
import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.component.Component;
import org.crandor.game.content.dialogue.DialogueAction;
import org.crandor.game.content.dialogue.DialogueInterpreter;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.holiday.HolidayEvent;
import org.crandor.game.content.holiday.HolidayItem;
import org.crandor.game.content.holiday.HolidayType;
import org.crandor.game.content.holiday.ItemLimitation;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.agility.AgilityHandler;
import org.crandor.game.interaction.NodeUsageEvent;
import org.crandor.game.interaction.Option;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.interaction.UseWithHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.impl.ForceMovement;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.Rights;
import org.crandor.game.node.entity.player.link.emote.Emotes;
import org.crandor.game.node.entity.player.link.request.RequestType;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.LocationLogoutTask;
import org.crandor.game.system.task.LogoutTask;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.map.zone.MapZone;
import org.crandor.game.world.map.zone.ZoneBorders;
import org.crandor.game.world.map.zone.ZoneBuilder;
import org.crandor.game.world.repository.Repository;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.CameraContext;
import org.crandor.net.packet.context.CameraContext.CameraType;
import org.crandor.net.packet.out.CameraViewPacket;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles the death mansion halloween event set in 2007.
 * @author Vexia
 * @author Emperor
 * @see <b>http://runescape.wikia.com/wiki/2007_Hallowe'en_event</b>
 */
@InitializablePlugin
public class DeathMansionEvent extends HolidayEvent {

	/**
	 * The last pumpkin being rewarded for skilling.
	 */
	private static int lastPumpkinReward;

	/**
	 * The grim reaper chat messages.
	 */
	private static final String[] GRIM_CHATS = new String[] { "Let me escort you away, @name...", "@name is mine!", "Now is the time you die, @name.", "Muahahahahaha!", "There is no escape, @name...", "Beware Mortals. @name travels with me now.", "I claim @name as my own.", "Your time here is over, @name.", "I have come for you, @name!" };

	/**
	 * The current pumpkin item.
	 */
	private static Item pumpkin;

	/**
	 * The location of bad webs to choose from.
	 */
	private static final Location[][] WEB_PATHS = new Location[][] { { new Location(1709, 4837, 0), new Location(1708, 4839, 0), new Location(1705, 4841, 0), new Location(1703, 4843, 0) },// path
			// 1
			{ new Location(1706, 4836, 0), new Location(1704, 4837, 0), new Location(1702, 4842, 0) },// path
			// 2
			{ new Location(1704, 4835, 0), new Location(1704, 4837, 0), new Location(1697, 4841, 0), new Location(1695, 4846, 0), new Location(1697, 4848, 0) },// path
			// 3
			{ new Location(1700, 4835, 0), new Location(1698, 4838, 0), new Location(1697, 4841, 0), new Location(1698, 4845, 0) },// path
			// 4
			{ new Location(1691, 4835, 0), new Location(1690, 4838, 0), new Location(1691, 4840, 0), new Location(1693, 4843, 0), new Location(1694, 4845, 0), new Location(1695, 4846, 0), new Location(1697, 4848, 0) },// path
			// 5
			{ new Location(1684, 4839, 0), new Location(1687, 4840, 0), new Location(1691, 4840, 0), new Location(1693, 4843, 0), new Location(1694, 4845, 0), new Location(1695, 4846, 0), new Location(1697, 4848, 0) },// path
			// 6
			{ new Location(1704, 4835, 0), new Location(1704, 4837, 0), new Location(1694, 4840, 0), new Location(1693, 4843, 0), new Location(1694, 4845, 0), new Location(1698, 4845, 0) } // 7
	};

	/**
	 * The start location.
	 */
	public static final Location START = new Location(1697, 4814, 0);

	/**
	 * The portal location.
	 */
	public static final Location PORTAL_LOCATION = new Location(3000, 3270, 0);

	/**
	 * The servants skull item.
	 */
	public static final Item SERVANT_SKULL = new Item(11784);

	/**
	 * The diary item.
	 */
	public static final Item DIARY = new Item(11780);

	/**
	 * The hood item.
	 */
	public static final Item HOOD = new Item(11789);

	/**
	 * The grim reaper id.
	 */
	public static final int GRIM_REAPER = 6390;

	/**
	 * The muncher npc id.
	 */
	public static final int MUNCHER = 2329;

	/**
	 * The death portal id.
	 */
	public static final int DEATH_PORTAL = 27254;

	/**
	 * Constructs a new {@code DeathMansionEvent} {@code Object}
	 */
	public DeathMansionEvent() {
		super("Death Mansion", HolidayType.HALLOWEEN, 1085, 16, 9);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		if (isActive()) {
			PluginManager.definePlugin(new DarkPortalHandler(), new DeathPortalZone(), new GrimReaperDialogue(), new MuncherDialogue(), new MuncherNPC(), new GrimItemHandler());
		}
		return super.newInstance(arg);
	}

	@Override
	public boolean enter(Entity entity) {
		if (entity instanceof Player) {
			Player player = entity.asPlayer();
			player.getProperties().setSpawnLocation(START);
			if (getStage(player) == 3 && !player.hasItem(SERVANT_SKULL)) {
				setStage(player, 2);
			}
		}
		return super.enter(entity);
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		if (e instanceof Player) {
			Player player = e.asPlayer();
			if (!logout) {
				if (player.getAttribute("grimDis", 0) > GameWorld.getTicks()) {
					return super.leave(player, logout);
				}
				int stage = getStage(player);
				if (stage == 5) {
					GrimItem.resetItems(player);
					player.sendMessage("Any items you were carrying from the house have been removed.");
				}
				cleanItems(player, GrimItem.getItems());
				cleanItems(player, new Item[] { DIARY, SERVANT_SKULL });
			} else {
				List<GameObject> objs = player.getAttribute("objs", null);
				if (objs == null) {
					objs = new ArrayList<GameObject>();
				}
				for (GameObject o : objs) {
					if (RegionManager.getLocalEntitys(o.getLocation(), 1).size() != 0) {
						continue;
					}
					if (!o.getLocation().withinDistance(player.getLocation(), 1)) {
						player.getPacketDispatch().sendObjectAnimation(o, Animation.create(7286));
					}
				}
			}
			player.getProperties().setSpawnLocation(ServerConstants.HOME_LOCATION);
		}
		return super.leave(e, logout);
	}

	@Override
	public boolean teleport(Entity e, int type, Node node) {
		if (e instanceof Player) {
			if (type != -1) {
				e.asPlayer().getDialogueInterpreter().sendDialogues(GRIM_REAPER, null, "Foolish mortal! Do you have no manners? Use", "the portal near me to exit my mansion.");
				return false;
			}
		}
		return super.teleport(e, type, node);
	}

	@Override
	public boolean isComplete(Player player) {
		return super.isComplete(player) || getStage(player) < 0 || getConfig(player) == 249852;
	}

	@Override
	public boolean interact(Entity e, final Node target, Option option) {
		if (e instanceof Player) {
			final Player player = e.asPlayer();
			switch (target.getId()) {
			case DEATH_PORTAL:
				player.teleport(PORTAL_LOCATION);
				return true;
			case 27276:
				handlePassageWay(player, target.asObject());
				return true;
			case 27266:
				passSpiderWeb(player, target.asObject());
				return true;
			case 27242:
			case 27243:
				if (getStage(player) >= 6) {
					player.setAttribute("grimDis", GameWorld.getTicks() + 2);
					player.teleport(target.getId() == 27243 ? Location.create(1703, 4826, 0) : Location.create(1639, 4835, 0));
					return true;
				}
				player.getDialogueInterpreter().sendDialogues(GRIM_REAPER, null, "Don't go gallivanting around my house. Come and see", "me if you're not sure what to do.");
				return true;
			case 27257:
				if (!player.getInventory().hasSpaceFor(SERVANT_SKULL)) {
					player.sendMessage("You don't have enough inventory space.");
					return true;
				}
				int webIndex = player.getAttribute("webIndex", 0);
				int newIndex = -1;
				while (newIndex == -1) {
					newIndex = RandomFunction.random(WEB_PATHS.length);
					if (newIndex == webIndex) {
						newIndex = -1;
					}
				}
				player.setAttribute("/save:webIndex", newIndex);
				setStage(player, 3);
				player.getInventory().add(SERVANT_SKULL);
				player.getDialogueInterpreter().sendItemMessage(SERVANT_SKULL, "You found the servant's skull. Now to get out of the garden.");
				return true;
			case 2329:
				player.getDialogueInterpreter().open("muncher-dialogue", target);
				return true;
			case 27245:
				if (getStage(player) >= 6) {
					player.sendMessage("There's no need to search the table any more.");
					return true;
				}
				if (!player.hasItem(DIARY)) {
					player.getInventory().add(DIARY);
					player.getDialogueInterpreter().sendItemMessage(DIARY, "You find a diary on the table. This should give some clues as to where items within the room should go.");
				} else {
					player.sendMessage("You already have a copy of the diary, you don't need to take another.");
				}
				return true;
			case 11780:
				if (option.getName().toLowerCase().equals("read")) {
					if (!player.getAttribute("read-diary", false)) {
						player.getDialogueInterpreter().sendDialogues(player, null, "Hopefully, by reading this, I can find clues on where to", "put any items I find.");
						player.setAttribute("/save:read-diary", true);
						player.getDialogueInterpreter().addAction(new DialogueAction() {
							@Override
							public void handle(Player player, int buttonId) {
								player.getDialogueInterpreter().open("grim-diary");
							}
						});
					} else {
						player.getDialogueInterpreter().open("grim-diary");
					}
					return true;
				}
				break;
			case 27255:
			case 27247:
			case 27246:
			case 27261:
			case 27252:
			case 27250:
			case 27248:
			case 27251:
			case 27249:
			case 27253:
				final GrimItem item = GrimItem.forObjectId(target.getId());
				if (item == GrimItem.HUMAN_BONES) {
					player.getDialogueInterpreter().sendOptions("Where to search?", "Towards the left", "Towards the right");
					player.getDialogueInterpreter().addAction(new DialogueAction() {
						@Override
						public void handle(Player player, int buttonId) {
							if (buttonId == 2) {
								item.search(player, target.asObject());
							} else {
								player.animate(Animation.create(7271));
								player.getDialogueInterpreter().sendDialogues(player, FacialExpression.SUSPICIOUS, "Arrghhh! My eyes!");
							}
						}
					});
					return true;
				} else if (item == GrimItem.EYEBALL) {
					player.getDialogueInterpreter().sendOptions("Where to search?", "Under the cushions", "Under the sofa");
					player.getDialogueInterpreter().addAction(new DialogueAction() {
						@Override
						public void handle(Player player, int buttonId) {
							if (buttonId == 1) {
								item.search(player, target.asObject());
							} else {
								player.getPacketDispatch().sendObjectAnimation(target.asObject(), Animation.create(7278));
								player.animate(Animation.create(7272));
								player.getDialogueInterpreter().sendDialogues(player, FacialExpression.DISGUSTED, "That wasn't such a good idea.");
							}
						}
					});
					return true;
				}
				item.search(player, target.asObject());
				return true;
			case 27278:
				final Location end = target.getLocation().equals(new Location(1633, 4824, 0)) ? new Location(1630, 4824, 0) : target.getLocation().equals(new Location(1627, 4819, 0)) ? new Location(1627, 4819, 0) : target.getLocation().equals(new Location(1630, 4819, 0)) ? new Location(1627, 4819, 0) : target.getLocation().equals(1624, 4822, 0) ? new Location(1624, 4828, 0) : new Location(1637, 4820, 0);
				if (target.getLocation().equals(new Location(1637, 4820, 0)) || target.getLocation().equals(new Location(1630, 4824, 0)) || target.getLocation().equals(new Location(1627, 4819, 0))) {
					player.sendMessage("You can't go back that way.");
					return true;
				}
				player.lock(3);
				if (target.getLocation().equals(1624, 4822, 0)) {
					player.sendChat("Weeeeee");
					GameWorld.submit(new Pulse(1, player) {

						@Override
						public boolean pulse() {
							player.getWalkingQueue().reset();
							player.getWalkingQueue().setRunDisabled(true);
							player.addExtension(LogoutTask.class, new LocationLogoutTask(10000000, new Location(1624, 4821, 0)));
							player.getInterfaceManager().closeDefaultTabs();
							player.getAppearance().setStandAnimation(7266);
							player.getAppearance().setWalkAnimation(7267);
							player.getAppearance().setRunAnimation(7267);
							player.getAppearance().setTurn180(7267);
							player.getAppearance().setTurn90ccw(7267);
							player.getAppearance().setTurn90cw(7267);
							player.getAppearance().sync();
							return true;
						}

					});
				}
				player.getPacketDispatch().sendObjectAnimation(target.asObject(), Animation.create(7296));
				AgilityHandler.walk(player, -1, player.getLocation(), target.getLocation(), null, 0.0, null);
				GameWorld.submit(new Pulse(1, player) {

					@Override
					public boolean pulse() {
						AgilityHandler.forceWalk(player, -1, player.getLocation(), end, Animation.create(target.getLocation().equals(1624, 4822, 0) ? 7269 : 7268), target.getLocation().equals(1624, 4822, 0) ? 80 : 40, 0.0, null);
						return true;
					}

				});
				return true;
			case 27211:
				player.getInterfaceManager().restoreTabs();
				player.removeExtension(LogoutTask.class);
				player.getAppearance().setDefaultAnimations();
				player.getAppearance().sync();
				player.getWalkingQueue().setRunDisabled(false);
				AgilityHandler.forceWalk(player, -1, player.getLocation(), player.getLocation().transform(0, 3, 0), Animation.create(7273), 6, 0.0, null);
				return true;
			case 27212:
				player.sendMessage("You can't go back that way.");
				return true;
			case 27218:
				player.lock(10);
				Location strt = player.getLocation().transform(0, -1, 0);
				AgilityHandler.forceWalk(player, -1, player.getLocation(), strt, Animation.create(7274), 5, 0.0, null);
				GameWorld.submit(new Pulse(1, player) {
					int ticks;
					int x = 1636;
					int y = 4829;
					int yInc = 0;
					int xInc = 0;
					int speed = 100;
					int height = 560;

					@Override
					public boolean pulse() {
						switch (++ticks) {
						case 1:
							PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, x + xInc, y + yInc, height, 1, speed));
							PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, x + 5, y - 4, height, 1, speed));
							break;
						case 8:
							x = 1637;
							y = 4816;
							PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, x + xInc, y + yInc, height, 1, speed));
							PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, x + 1, y + 1, height, 1, speed));
							ForceMovement.run(player, player.getLocation(), player.getLocation().transform(0, -5, 0), null, null, Direction.SOUTH, 50);
							break;
						case 10:
							PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.RESET, x + 5, y - 4, height, 1, speed));
							return true;
						}
						return false;
					}
				});
				return true;
			}
		}
		return super.interact(e, target, option);
	}

	@Override
	public void locationUpdate(Entity entity, Location last) {
		if (entity instanceof Player) {
			final Player p = entity.asPlayer();
			Location loc = p.getLocation();
			List<GameObject> objs = p.getAttribute("objs", null);
			if (objs == null) {
				objs = new ArrayList<GameObject>();
			}
			for (int i = 0; i < 1; i++) {
				for (Direction direction : Direction.values()) {
					GameObject o = RegionManager.getObject(loc.transform(direction, 1));
					if (o != null && o.getId() == 27241) {
						objs.add(o);
						p.setAttribute("objs", objs);
						p.getPacketDispatch().sendObjectAnimation(o, Animation.create(7289));
					}
				}
			}
			for (GameObject o : objs) {
				if (RegionManager.getLocalEntitys(o.getLocation(), 1).size() != 0) {
					continue;
				}
				if (!o.getLocation().withinDistance(p.getLocation(), 1)) {
					p.getPacketDispatch().sendObjectAnimation(o, Animation.create(7286));
				}
			}
			if (loc.getX() >= 1630 && loc.getX() <= 1646 && loc.getY() >= 4844 && loc.getY() <= 4852) {
				final GameObject object = RegionManager.getObject(entity.getLocation());
				if (object != null && object.getId() == 27240) {
					p.getWalkingQueue().reset();
					p.getLocks().lock();
					p.sendMessage("You accidentally trigger a trap.");
					GameWorld.submit(new Pulse(1, p) {
						int ticks;

						@Override
						public boolean pulse() {
							switch (++ticks) {
							case 1:
								if (!p.getLocation().equals(object.getLocation())) {
									p.teleport(object.getLocation());
								}
								p.lock();
								p.getPacketDispatch().sendObjectAnimation(object, Animation.create(1939));
								break;
							case 2:
								p.animate(Animation.create(4366));
								break;
							case 4:
								p.getPacketDispatch().sendGlobalPositionGraphic(86, object.getLocation());
								p.teleport(new Location(1641, 4830, 0));
								break;
							case 5:
								p.unlock();
								p.getDialogueInterpreter().sendDialogues(GRIM_REAPER, null, "Look closely and you should see the joins", "of the doors on the ground.");
								return true;
							}
							return false;
						}
					});
					return;
				}
			}
		}
		super.locationUpdate(entity, last);
	}

	@Override
	public boolean canRequest(RequestType type, Player player, Player target) {
		if (player.getAppearance().getStandAnimation() == 7266 || target.getAppearance().getStandAnimation() == 7266) {
			return false;
		}
		return super.canRequest(type, player, target);
	}

	/**
	 * Handles the passage way.
	 * @param player the player.
	 * @param target the target.
	 */
	private void handlePassageWay(final Player player, final GameObject target) {
		int stage = getStage(player);
		if (stage < 1 || stage < 4 && target.getLocation().getX() == 1694) {
			electrocute(player, target);
			return;
		}
		if (stage >= 6 && target.getLocation().getX() == 1645) {
			player.lock(2);
			player.animate(Animation.create(7299));
			player.graphics(Graphics.create(281));
			setStage(player, stage + 1);
			player.teleport(stage + 1 == 8 ? Location.create(1633, 4839, 0) : new Location(1642, 4828, 0), 1);
			player.getDialogueInterpreter().open(GRIM_REAPER, true, true);
			return;
		}
		if (stage >= 4 && player.getLocation().getX() == 1701 || stage >= 6 && (target.getLocation().getX() == 1694 && player.getLocation().getX() >= target.getLocation().getX()) || stage >= 8 && target.getLocation().equals(new Location(1641, 4829, 0))) {
			player.getDialogueInterpreter().sendDialogues(GRIM_REAPER, null, "Foolish mortal! You have no business in there.");
			return;
		}
		if (stage == 1 && player.getLocation().getX() == 1701) {
			setStage(player, 2);
			passDorway(player, target.getLocation());
			player.getDialogueInterpreter().open(GRIM_REAPER, true, true);
			return;
		} else if (stage == 3 && player.getLocation().getX() == 1701 && player.getInventory().containsItem(SERVANT_SKULL)) {
			setStage(player, 4);
			passDorway(player, target.getLocation());
			player.getInventory().remove(SERVANT_SKULL);
			player.removeAttribute("webIndex");
			player.getDialogueInterpreter().open(GRIM_REAPER, true, true, true);
			return;
		} else if (getStage(player) == 4 && target.getLocation().getX() == 1694) {
			setStage(player, 5);
			passDorway(player, target.getLocation());
			player.getDialogueInterpreter().open(GRIM_REAPER, true, true);
			return;
		}
		player.getDialogueInterpreter().sendDialogues(6389, 7292, "We're watching you...");
		player.getDialogueInterpreter().addAction(new DialogueAction() {
			@Override
			public void handle(Player player, int buttonId) {
				passDorway(player, target.getLocation());
			}
		});
	}

	/**
	 * Passes through a spider web.
	 * @param player the player.
	 * @param object the object.
	 */
	private void passSpiderWeb(Player player, GameObject object) {
		int webIndex = player.getAttribute("webIndex", -1);
		if (webIndex == -1) {
			player.setAttribute("/save:webIndex", (webIndex = RandomFunction.random(WEB_PATHS.length)));
		}
		boolean goodWeb = false;
		for (Location l : WEB_PATHS[webIndex]) {
			if (l.equals(object.getLocation())) {
				goodWeb = true;
				break;
			}
		}
		if (!goodWeb) {
			player.sendMessage("You cannot pass through this particular web - try another.");
			return;
		}
		player.getPacketDispatch().sendObjectAnimation(object, Animation.create(7280));
		player.lock(3);
		AgilityHandler.forceWalk(player, -1, player.getLocation(), player.getLocation().transform(Direction.getDirection(player.getLocation(), object.getLocation()), 2), Animation.create(2240), 8, 0.0, null);
	}

	/**
	 * Passes through a doorway.
	 * @param player the player.
	 * @param location the location.
	 */
	public void passDorway(Player player, Location location) {
		Location end = player.getLocation().transform(Direction.getDirection(player.getLocation(), location), 2);
		player.addExtension(LogoutTask.class, new LocationLogoutTask(3, end));
		player.sendMessage("You safely pass through the gargoyles' judgement.");
		player.lock(3);
		AgilityHandler.walk(player, -1, player.getLocation(), end, null, 0.0, null);
	}

	/**
	 * Electrocutes the player.
	 * @param player the player.
	 * @param object the object.
	 */
	private void electrocute(final Player player, GameObject object) {
		player.lock(4);
		player.animate(Animation.create(7299));
		player.graphics(Graphics.create(281));
		int x = 0, x2 = 0;
		int y = 0, y2 = 0;
		Direction dir = object.getDirection();
		if (dir == Direction.SOUTH || dir == Direction.NORTH) {
			x = 1;
			x2 = -1;
		} else if (dir == Direction.WEST) {
			y = 1;
			y2 = -1;
		} else {
			y = -1;
			y2 = 1;
		}
		GameObject first = RegionManager.getObject(object.getLocation().transform(x, y, 0));
		GameObject second = RegionManager.getObject(object.getLocation().transform(x2, y2, 0));
		if (first != null && second != null) {
			player.getPacketDispatch().sendObjectAnimation(first, Animation.create(7285));
			player.getPacketDispatch().sendObjectAnimation(second, Animation.create(7285));
		}
		player.sendMessage("You fail to pass through the gargoyles' judgement");
		GameWorld.submit(new Pulse(1, player) {
			@Override
			public boolean pulse() {
				player.getImpactHandler().manualHit(player, player.getSkills().getLifepoints(), HitsplatType.NORMAL);
				player.getDialogueInterpreter().sendDialogues(GRIM_REAPER, null, "You may be able to wander around other people's", "houses, but I shall not allow it.");
				return true;
			}
		});
	}

	@Override
	public void load() {
		// ObjectBuilder.add(new GameObject(DEATH_PORTAL, new Location(2999,
		// 3268, 0)));
		pumpkin = new Item(1959);
		for (int id = 1053; id < 1059; id += 2) {
			if (!ItemLimitation.isRegistered(id)) {
				ItemLimitation.register(id, 15);
			}
		}
		if (!ItemLimitation.isRegistered(pumpkin.getId())) {
			ItemLimitation.register(pumpkin.getId(), 100);
		}
		if (ItemLimitation.getAmountLeft(pumpkin.getId()) > 0) {
			HolidayItem.startRandomSpawn(pumpkin, 1000, Location.create(3433, 3534, 0), Location.create(3099, 3548, 0), Location.create(3067, 10255, 0), Location.create(2913, 3746, 0), Location.create(3193, 3914, 0), Location.create(3214, 9618, 0), Location.create(3227, 3219, 2), Location.create(3300, 3312, 0), Location.create(3075, 3260, 0), Location.create(3213, 3428, 0), Location.create(3213, 3480, 0), Location.create(2710, 3460, 0), Location.create(2750, 3440, 0), Location.create(2927, 3144, 0), Location.create(2667, 3309, 0), Location.create(2600, 3104, 0), Location.create(2910, 4832, 0), Location.create(2925, 3445, 0), Location.create(2475, 3423, 1), Location.create(2817, 3441, 0), Location.create(2948, 3216, 0), Location.create(1864, 5243, 0), Location.create(2706, 3733, 0), Location.create(3186, 3446, 1), Location.create(3189, 9832, 0), Location.create(2655, 3310, 0), Location.create(3145, 9913, 0));
		}
	}

	@Override
	public void addExperience(Player player, int skill, double experience) {
		if (skill <= Skills.MAGIC && skill != Skills.PRAYER) {
			return;
		}
		if (GameWorld.getTicks() - lastPumpkinReward < 1000 && RandomFunction.random(2500) == 0 && ItemLimitation.getAmountLeft(pumpkin.getId()) > 0) {
			if (player.getInventory().add(pumpkin)) {
				player.getPacketDispatch().sendMessage("You found a pumpkin!");
				lastPumpkinReward = GameWorld.getTicks();
				ItemLimitation.decreaseAmount(pumpkin.getId());
			}
		}
	}

	@Override
	public void commenceDeath(Entity killer, Entity victim) {
		if (!(victim instanceof Player) || victim.getZoneMonitor().isInZone("Death Mansion")) {
			return;
		}
		Player player = (Player) victim;
		final NPC reaper = NPC.create(2862, player.getLocation());
		reaper.setLocation(RegionManager.getSpawnLocation(player, reaper));
		if (reaper.getLocation() == null) {
			return;
		}
		reaper.init();
		reaper.face(player);
		reaper.setWalks(false);
		reaper.setNeverWalks(true);
		reaper.setAggressive(false);
		reaper.animate(Animation.create(392));
		reaper.sendChat(RandomFunction.getRandomElement(GRIM_CHATS).replace("@name", player.getUsername()));
		GameWorld.submit(new Pulse(4, reaper) {
			@Override
			public boolean pulse() {
				reaper.clear();
				return true;
			}
		});
	}

	@Override
	public void finalizeDeath(Entity killer, Entity victim) {
		if (killer instanceof Player && victim instanceof NPC) {
			Player player = (Player) killer;
			if (victim.getProperties().getCurrentCombatLevel() > 0) {
				int chance = (5_000 / victim.getProperties().getCurrentCombatLevel()) * 100;
				if (RandomFunction.random(chance) == 0) {
					int itemId = 1053 + (RandomFunction.random(3) * 2);
					if (ItemLimitation.getAmountLeft(itemId) > 0) {
						ItemLimitation.decreaseAmount(itemId);
						Repository.sendNews(player.getUsername() + " found a halloween mask!");
						player.getPacketDispatch().sendMessage("<col=FF0000>You find a scary looking mask.");
						GroundItemManager.create(new Item(itemId), victim.getLocation(), player);
					}
				}
			}
		}
	}

	@Override
	public boolean isActive() {
		return false;
	}

	@Override
	public void configure() {
		registerRegion(6731);
		registerRegion(6475);
	}

	/**
	 * Handles the grim item handler.
	 * @author Vexia
	 */
	public static class GrimItemHandler extends UseWithHandler {

		/**
		 * The object ids.
		 */
		private static final int[] OBJECTS = new int[] { 27249, 27255, 27247, 27246, 27261, 27260, 27252, 27248, 27250, 27251, 27253 };

		/**
		 * Constructs a new {@code GrimItemHandler} {@code Object}
		 */
		public GrimItemHandler() {
			super(11782, 11781, 11785, 11787, 11786, 11788, 11783);
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			for (int id : OBJECTS) {
				addHandler(id, OBJECT_TYPE, this);
			}
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			final Player player = event.getPlayer();
			GrimItem item = GrimItem.forDestinationId(event.getUsedWith().getId());
			if (item == null || event.getUsedItem().getId() != item.getItem().getId()) {
				return false;
			}
			item.useWithEvent(player, event.getUsedWith().asObject());
			return true;
		}

	}

	/**
	 * A grim reaper item.
	 * @author Vexia
	 */
	public enum GrimItem {
		TESTAMENT(new int[] { 27255 }, new Item(11782), "You found someone's Last Will and Testament.", new String[] { "You recall the entry in the diary, '...have to sit on that for a while...'", "and place the Last Will and Testament under the sofa cushions." }, 27252), ROBE(new int[] { 27247, 27246 }, new Item(11781), "You found the Grim Reaper's robes.", new String[] { "You recall the entry in the diary, '...decided to throw them in the", "fireplace...' and place the robes in the fireplace." }, 27251), HOURGLASS(new int[] { 27261, 27260 }, new Item(11785), "You found an hourglass.", new String[] { "You recall the entry in the diary, '...hourglass today so have added", "that to the fishtank...' and place the hourglass in the fishtank." }, 27253), EYEBALL(new int[] { 27252 }, new Item(11787), "You found someone's eye.", new String[] { "You recall the entry in the diary, '...I put the eye back on the", "shelf...' and place the eye on the shelf." }, 27261, 27260), SCYTHE_SHARPENER(new int[] { 27248, 27249, 27250 }, new Item(11786), "You found a scythe sharpener.", new String[] { "You recall the entry in the diary, '...put the sharpener back in the", "cabinet...' and place the scythe sharpener in the cabinet." }, 27247, 27246), POTION(new int[] { 27251 }, new Item(11788), "You found a 'Voice of Doom' potion.", new String[] { "You recall the entry in the diary, '...I found my old 'Voice of Doom'", "potion amongst some books...' and place the potion on the bookcase." }, 27248, 27249, 27250), HUMAN_BONES(new int[] { 27253 }, new Item(11783), "You found some bones. They look decidely human.", new String[] { "You recall the entry in the diary, '...decided to lock them up in the", "chest...' and place the bones in the chest." }, 27255);

		/**
		 * The object id.
		 */
		private final int[] objectIds;

		/**
		 * The item.
		 */
		private final Item item;

		/**
		 * The found text.
		 */
		private final String foundText;

		/**
		 * The return text.
		 */
		private final String[] returnText;

		/**
		 * The destination object id.
		 */
		private final int[] destinationIds;

		/**
		 * Constructs a new {@code GrimItem} {@code Object}
		 * @param objectId the object id.
		 * @param item the item.
		 * @param foundText the found text.
		 * @param returnText the returning text.
		 */
		private GrimItem(int[] objectIds, Item item, String foundText, String[] returnText, int... destinationIds) {
			this.objectIds = objectIds;
			this.item = item;
			this.foundText = foundText;
			this.returnText = returnText;
			this.destinationIds = destinationIds;
		}

		/**
		 * Searches for the grim item.
		 * @param player the player.
		 * @param object the object.
		 */
		public void search(Player player, GameObject object) {
			if (!player.getInventory().hasSpaceFor(item)) {
				player.sendMessage("You don't have enough inventory space.");
				return;
			}
			if (player.getInventory().containsItem(item) || hasGrimItem(player, this)) {
				player.getDialogueInterpreter().sendDialogue("You have already taken the item you were looking for", "from here.");
				return;
			}
			if (player.getInventory().add(item)) {
				player.animate(Animation.create(833));
				player.getDialogueInterpreter().sendItemMessage(item, foundText);
			}
		}

		/**
		 * Called on the use with event.
		 * @param player the player.
		 * @param object the object.
		 */
		public void useWithEvent(Player player, GameObject object) {
			if (hasGrimItem(player, this)) {
				player.getDialogueInterpreter().sendDialogue("You have already returned this item to it's proper", "spot in the mansion.");
				return;
			}
			if (player.getInventory().remove(item)) {
				setGrimItem(player, this);
				player.animate(Animation.create(833));
				player.getDialogueInterpreter().sendDialogue(returnText);
				if (getGrimItems(player).size() == values().length) {
					DeathMansionEvent event = (DeathMansionEvent) player.getZoneMonitor().getZones().get(0).getZone();
					if (event != null) {
						player.removeAttribute("ready-diary");
						event.setStage(player, 6);
						player.getDialogueInterpreter().open(GRIM_REAPER, true, true);
					}
				}
			}
		}

		/**
		 * Checks if the player has the grim item.
		 * @param player the player.
		 * @param item the item.
		 * @return {@code True} if so.
		 */
		public static boolean hasGrimItem(Player player, GrimItem item) {
			return getGrimItems(player).contains(item);
		}

		/**
		 * Sets the grim item as completed.
		 * @param player the player.
		 * @param item the item.
		 */
		public static void setGrimItem(Player player, GrimItem item) {
			List<GrimItem> items = getGrimItems(player);
			items.add(item);
			player.setAttribute("grim-items", items);
		}

		/**
		 * Resets the grim items.
		 * @param player the items.
		 */
		public static void resetItems(Player player) {
			player.removeAttribute("grim-items");
		}

		/**
		 * Gets the list of set grim items.
		 * @param player the player.
		 * @return the items.
		 */
		public static List<GrimItem> getGrimItems(Player player) {
			List<GrimItem> items = player.getAttribute("grim-items", null);
			if (items == null) {
				items = new ArrayList<>();
			}
			player.setAttribute("grim-items", items);
			return items;
		}

		/**
		 * Gets the grim item by the destination id.
		 * @param objectId the object id.
		 * @return the id.
		 */
		public static GrimItem forDestinationId(int objectId) {
			for (GrimItem item : values()) {
				for (int id : item.getDestinationId()) {
					if (id == objectId) {
						return item;
					}
				}
			}
			return null;
		}

		/**
		 * Gets the object id.
		 * @param objectId the object id.
		 * @return the grim item.
		 */
		public static GrimItem forObjectId(int objectId) {
			for (GrimItem item : values()) {
				for (int id : item.getObjectIds()) {
					if (id == objectId) {
						return item;
					}
				}
			}
			return null;
		}

		/**
		 * Gets the items.
		 * @return the items.
		 */
		public static Item[] getItems() {
			Item[] items = new Item[values().length];
			for (int i = 0; i < items.length; i++) {
				items[i] = values()[i].getItem();
			}
			return items;
		}

		/**
		 * Gets the objectId.
		 * @return the objectId
		 */
		public int[] getObjectIds() {
			return objectIds;
		}

		/**
		 * Gets the item.
		 * @return the item
		 */
		public Item getItem() {
			return item;
		}

		/**
		 * Gets the foundText.
		 * @return the foundText
		 */
		public String getFoundText() {
			return foundText;
		}

		/**
		 * Gets the returnText.
		 * @return the returnText
		 */
		public String[] getReturnText() {
			return returnText;
		}

		/**
		 * Gets the destinationId.
		 * @return the destinationId
		 */
		public int[] getDestinationId() {
			return destinationIds;
		}

	}

	/**
	 * Handles the grim reaper dialogue.
	 * @author Vexia
	 */
	public class GrimReaperDialogue extends DialoguePlugin {

		/**
		 * Constructs a new {@code GrimReaperDialogue} {@code Object}
		 */
		public GrimReaperDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code GrimReaperDialogue} {@code Object}
		 * @param player the player.
		 */
		public GrimReaperDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new GrimReaperDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			switch (getStage(player)) {
			case 0:
				player("Erm... Excuse me... Could I ask...");
				break;
			case 1:
			case 2:
			case 3:
				if (args.length > 1) {
					npc("You have found my garden I see.");
					stage = 100;
					return true;
				}
				player("Erm... What was I supposed to do?");
				break;
			case 4:
				if (args.length == 0 && !player.getInventory().containsItem(SERVANT_SKULL)) {
					player("Erm... What was I supposed to do?");
					stage = 2;
					return true;
				}
				player("Woah, what'd I do? Oh no, the skull's gone.");
				break;
			case 5:
				if (args.length > 1) {
					npc("You made it.");
				} else {
					npc("Foolish mortal, get back to the task.");
					stage = -1;
				}
				break;
			case 6:
				if (args.length > 1) {
					npc("Looks like you've returned everything to its proper", "location.");
					stage = 20;
				} else {
					player("Erm... What was I supposed to do?");
				}
				break;
			case 7:
				if (args.length > 1) {
					player("Huh? What happened there?");
					stage = 20;
				} else {
					player("Erm... What was I supposed to do?");
				}
				break;
			case 8:
				if (args.length > 1) {
					npc("That is sufficient.");
					stage = 20;
				} else {
					player("You wished to speak with me?");
				}
				break;
			case 9:
			case -12290:
				end();
				break;
			}
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			if (player.getAttribute("lo", false)) {
				end();
				return true;
			}
			switch (getStage(player)) {
			case 0:
				switch (stage) {
				case 0:
					npc("Speak, mortal.");
					stage++;
					break;
				case 1:
					player("Well, sir, lord, erm, your highness...");
					stage++;
					break;
				case 2:
					npc("I need no title.");
					stage++;
					break;
				case 3:
					player("Well, of course. Sorry. I was just wondering, what is", "this place?");
					stage++;
					break;
				case 4:
					npc("My house. You have found yourself here because I", "deem it necessary.");
					stage++;
					break;
				case 5:
					player("You do?");
					stage++;
					break;
				case 6:
					npc("Long have I had a servant performing tasks as", "instructed. He left my service to pass on into death", "Such a role is to be replaced.");
					stage++;
					break;
				case 7:
					player("I see so you want me to be your servant?");
					stage++;
					break;
				case 8:
					npc("Eager assumptions shall bring you regret, human. A", "worthy soul has already been picked for the position.", "My house is open now as various things need attending", "to before the transition can take place.");
					stage++;
					break;
				case 9:
					player("So basically you want me to tidy up some things before", "your new servant arrives?");
					stage++;
					break;
				case 10:
					npc("Interpret as you will, mortal!");
					stage++;
					break;
				case 11:
					options("I'll help out no problem.", "That doesn't really appeal, I'm off.");
					stage++;
					break;
				case 12:
					if (buttonId == 1) {
						player("I'll help out no problem.");
						stage = 15;
						break;
					}
					player("That doesn't really appeal, I'm off.");
					stage++;
					break;
				case 13:
					npc("So be it, mortal!");
					stage++;
					break;
				case 14:
					end();
					break;
				case 15:
					DeathMansionEvent.this.setStage(player, 1, 16);
					npc("Proceeding as instructed is the best way to survive", "here, human. I can always see you and can speak with", "you whenever I wish as this is my domain. Go to the", "garden for further instructions.");
					stage++;
					break;
				}
				break;
			case 1:
			case 2:
			case 3:
				switch (stage) {
				case 0:
					npc("Foolish mortal! Proceed to the garden for", "further instructions.");
					stage++;
					break;
				case 1:
					end();
					break;
				case 16:
					player("Always watched, To the garden. Thank you. Much", "appreciated.");
					stage++;
					break;
				case 17:
					end();
					break;
				case 100:
					player("Woah. You made me jump. How does your voice carry", "so far?");
					stage++;
					break;
				case 101:
					npc("That is not of your concern. Whilst in my domain you", "shall never be far from my gaze.");
					stage++;
					break;
				case 102:
					player("Okay... So, what exactly do you want me to do out", "here?");
					stage++;
					break;
				case 103:
					npc("My last servant died in here. His skull still remains and", "you must bring it out to prevent my next servant from", "getting the wrong impression.");
					stage++;
					break;
				case 104:
					player("And I suppose your servant was killed by some huge", "man-eating spider?");
					stage++;
					break;
				case 105:
					npc("That is not your business, mortal. Anyway, the webs", "you see are from a nest of very small spiders. Some", "webs you can pass, some you cannot. You will only", "need to use your bare hands to break though.");
					stage++;
					break;
				case 106:
					player("Sorry I asked. I'll go find the skull and bring it back", "out of the garden.");
					stage++;
					break;
				case 107:
					npc("Yes, and do not try teleporting out of the garden with", "the skull. The skull will remain behind and you'll have to", "find it again.");
					stage++;
					break;
				case 108:
					end();
					break;
				}
				break;
			case 4:
				switch (stage) {
				case -1:
					end();
					break;
				case 0:
					npc("Do not concern yourself. I had instructed the", "gargoyles to take it from you.");
					stage++;
					break;
				case 1:
					player("You could have let me know! I almost had a heart", "attack.");
					stage++;
					break;
				case 2:
					npc("Please proceed to the westernmost room for your next", "task.");
					stage++;
					break;
				case 3:
					player("You don't waste time, do you? So, the room that looks a", "bit like your lounge? I'm on my way.");
					stage++;
					break;
				case 4:
					end();
					break;
				}
				break;
			case 5:
				switch (stage) {
				case -1:
					end();
					break;
				case 0:
					player("You sound surprised.");
					stage++;
					break;
				case 1:
					npc("You wouldn't be the first, or last, to die in this house.");
					stage++;
					break;
				case 2:
					player("I'm so pleased. So, to the task at hand. What should I", "be doing in here?");
					stage++;
					break;
				case 3:
					npc("If you search around the room you'll find various", "items. Put them back where they belong. My diary is", "on the table - read this for some clues, but tell anyone", "what you read and you die.");
					stage++;
					break;
				case 4:
					player("*Gulp* I shall have a look.");
					stage++;
					break;
				case 5:
					end();
					break;
				}
				break;
			case 6:
				switch (stage) {
				case 0:
					npc("Please proceed upstairs and enter", "the room you find there.");
					stage = 22;
					break;
				case 20:
					player("Woo hoo!");
					stage++;
					break;
				case 21:
					npc("You've not finished yet, mortal. Please proceed upstairs", "and enter the room you find there.");
					stage++;
					break;
				case 22:
					player("Upstairs it is.");
					stage++;
					break;
				case 23:
					end();
					break;
				}
				break;
			case 7:
				switch (stage) {
				case 0:
					npc("You must complete the playground.");
					stage = 23;
					break;
				case 20:
					npc("Again.");
					stage++;
					break;
				case 21:
					player("What?");
					stage++;
					break;
				case 22:
					npc("One more time around just to be sure all is in order.");
					stage++;
					break;
				case 23:
					end();
					break;
				}
				break;
			case 8:
				switch (stage) {
				case 0:
					npc("Correct. You have completed the tasks as ordered,", "hence your time here is done.");
					stage++;
					break;
				case 1:
					player("An I'm still alive. I rock!");
					stage++;
					break;
				case 2:
					player("So...any chance of a reward for my service?");
					stage++;
					break;
				case 3:
					npc("Hmmm. Mortals and their goal-driven attitude - one", "thing that shall forever elude my understanding. As you", "wish, but trouble me no more.");
					stage++;
					break;
				case 4:
					player("Thank you!");
					stage++;
					break;
				case 5:
					if (!player.getInventory().hasSpaceFor(HOOD)) {
						npc("You don't have enough inventory space,", "mortal.");
						stage = 23;
						break;
					}
					interpreter.sendItemMessage(HOOD, "The Grim Reaper has given you the " + RED + "Grim Reaper hood</col> and you've unlocked the " + RED + "Zombie Hand Emote</col> as well as the " + RED + "Scared</col> emote.");
					stage++;
					break;
				case 6:
					npc("Death will find you one day...");
					stage++;
					break;
				case 7:
					DeathMansionEvent.this.setStage(player, 9);
					player.getConfigManager().set(1085, 249852, true); // hallowe'en
					// hand
					// emote
					player.getEmoteManager().unlock(Emotes.SCARED);
					player.getEmoteManager().unlock(Emotes.ZOMBIE_HAND);
					player.addExtension(LogoutTask.class, new LocationLogoutTask(5, PORTAL_LOCATION));
					player.getInventory().add(HOOD);
					player.lock(5);
					player.getProperties().setSpawnLocation(PORTAL_LOCATION);
					player.getImpactHandler().manualHit(player, player.getSkills().getLifepoints(), HitsplatType.NORMAL);
					close();
					player.setAttribute("lo", true);
					GameWorld.submit(new Pulse(5, player) {
						@Override
						public boolean pulse() {
							player.setAttribute("lo", true);
							player("Well. That was an experience I shall never forget.");
							stage++;
							return true;
						}
					});
					break;
				case 8:
					end();
					break;
				case 20:
					player("Phew.");
					stage++;
					break;
				case 21:
					npc("Come and speak with me downstairs.");
					stage++;
					break;
				case 22:
					player("I'll be right down.");
					stage++;
					break;
				case 23:
					end();
					break;
				}
				break;
			case 9:
			case -12290:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { GRIM_REAPER };
		}

	}

	/**
	 * Represents the area around the death portal. (used to darken the screen)
	 * @author Vexia
	 */
	public class DeathPortalZone extends MapZone implements Plugin<Object> {

		/**
		 * Constructs a new {@code DeathPortalZone} {@code Object}
		 */
		public DeathPortalZone() {
			super("Death portal", true);
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			ZoneBuilder.configure(this);
			return this;
		}

		@Override
		public Object fireEvent(String identifier, Object... args) {
			return null;
		}

		@Override
		public boolean enter(Entity entity) {
			if (entity instanceof Player) {
				darken(entity.asPlayer());
			}
			return super.enter(entity);
		}

		@Override
		public boolean leave(Entity e, boolean logout) {
			if (e instanceof Player) {
				e.asPlayer().getInterfaceManager().closeOverlay();
			}
			return super.leave(e, logout);
		}

		@Override
		public void locationUpdate(Entity e, Location last) {
			if (e instanceof Player) {
				darken(e.asPlayer());
			}
			super.locationUpdate(e, last);
		}

		/**
		 * Darkens the players screen.
		 * @param player the player.
		 */
		private void darken(Player player) {
			int distance = (int) player.getLocation().getDistance(new Location(3000, 3269, 0));
			int component = 97;
			if (distance < 5) {
				component = 98;
			}
			if (player.getInterfaceManager().getOverlay() == null || player.getInterfaceManager().getOverlay().getId() != component) {
				player.getInterfaceManager().openOverlay(new Component(component));
			}
		}

		@Override
		public void configure() {
			register(new ZoneBorders(2993, 3263, 3008, 3275));
		}

	}

	/**
	 * Handles the dark portal entrance.
	 * @author Vexia
	 */
	public class DarkPortalHandler extends OptionHandler {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			PluginManager.definePlugin(new DarkPortalDialogue());
			ObjectDefinition.forId(DEATH_PORTAL).getConfigurations().put("option:enter", this);
			return this;
		}

		@Override
		public boolean handle(Player player, Node node, String option) {
			if (player.getDetails().getRights() == Rights.ADMINISTRATOR) {
				player.getPacketDispatch().sendMessage("Current pumpkin location: " + pumpkin.getLocation() + ".");
			}
			if (isComplete(player)) {
				player.sendMessage("You've no need to return to the Grim Reaper's house.");
				return true;
			}
			player.getDialogueInterpreter().open("dark-portal");
			return true;
		}

		/**
		 * Handles the dark portals dialogue.
		 * @author Vexia
		 */
		public class DarkPortalDialogue extends DialoguePlugin {

			/**
			 * Constructs a new {@code DarkPortalDialogue} {@code Object}
			 */
			public DarkPortalDialogue() {
				/**
				 * empty.
				 */
			}

			/**
			 * Constructs a new {@code DarkPortalDialogue} {@code Object}
			 * @param player the player.
			 */
			public DarkPortalDialogue(Player player) {
				super(player);
			}

			@Override
			public DialoguePlugin newInstance(Player player) {
				return new DarkPortalDialogue(player);
			}

			@Override
			public boolean open(Object... args) {
				switch (getStage(player)) {
				case 0:
					player("I can't believe I'm doing this.", "Oh, well, here goes nothing.");
					break;
				default:
					player.teleport(START);
					break;
				}
				return true;
			}

			@Override
			public boolean handle(int interfaceId, int buttonId) {
				switch (getStage(player)) {
				case 0:
					switch (stage) {
					case 0:
						player.teleport(START);
						GameWorld.submit(new Pulse(1, player) {

							@Override
							public boolean pulse() {
								player("Well, I'm still in one piece - that's a good start.");
								stage++;
								return true;
							}

						});
						close();
						break;
					case 1:
						end();
						break;
					}
					break;
				default:
					end();
					break;
				}
				return true;
			}

			@Override
			public int[] getIds() {
				return new int[] { DialogueInterpreter.getDialogueKey("dark-portal") };
			}

		}

	}

	/**
	 * Handles the muncher npc.
	 * @author Vexia
	 */
	public class MuncherNPC extends AbstractNPC {

		/**
		 * Constructs a new {@code MuncherNPC} {@code Object}
		 * @param id the id.
		 * @param location the location.
		 */
		public MuncherNPC(int id, Location location) {
			super(id, location);
		}

		/**
		 * Constructs a new {@code MuncherNPC} {@code Object}
		 */
		public MuncherNPC() {
			super(-1, null);
		}

		@Override
		public void handleTickActions() {
			super.handleTickActions();
			if (RandomFunction.random(100) < 4) {
				sendChat("Grrrrr");
			}
		}

		@Override
		public AbstractNPC construct(int id, Location location, Object... objects) {
			return new MuncherNPC(id, location);
		}

		@Override
		public int[] getIds() {
			return new int[] { 2329 };
		}

	}

	/**
	 * Handles the muncher dialogue.
	 * @author Vexia
	 */
	public class MuncherDialogue extends DialoguePlugin {

		/**
		 * Constructs a new {@code MuncherDialogue} {@code Object}
		 */
		public MuncherDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code MuncherDialogue} {@code Object}
		 * @param player the player.
		 */
		public MuncherDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new MuncherDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			interpreter.sendDialogues(player, FacialExpression.JOLLY, "Here, boy!");
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				npc.sendChat("Grrrrrrr");
				interpreter.sendDialogues(player, FacialExpression.SUSPICIOUS, "Whoaah!");
				stage++;
				break;
			case 1:
				options("Stroke him", "Try to entertain him", "Blow a raspberry at him");
				stage++;
				break;
			case 2:
				if (buttonId == 1) {
					close();
					Animation a = Animation.create(7270);
					player.animate(a);
					player.lock(a.getDuration());
					npc.faceTemporary(player, 2);
					npc.animate(Animation.create(6578));
					GameWorld.submit(new Pulse(a.getDuration(), player) {
						@Override
						public boolean pulse() {
							player("Okay, touching him seems to be a bad idea.");
							stage++;
							return true;
						}
					});
				} else if (buttonId == 2) {
					close();
					Animation anim = new Animation(866);
					player.animate(anim);
					player.lock(anim.getDuration());
					GameWorld.submit(new Pulse(anim.getDuration(), player) {
						@Override
						public boolean pulse() {
							player("If it's possible for a skeletal dog to smile, I think he", "would be now.");
							stage++;
							return true;
						}
					});
				} else {
					npc.sendChat("Grrrrrr");
					player.animate(Animation.create(2110));
					npc.faceTemporary(player, 3);
					player.lock(4);
					GameWorld.submit(new Pulse(3, player) {
						@Override
						public boolean pulse() {
							npc.animate(Animation.create(6579));
							player.getImpactHandler().manualHit(player, player.getSkills().getLifepoints(), HitsplatType.NORMAL);
							return true;
						}
					});
					end();
				}
				break;
			case 3:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { DialogueInterpreter.getDialogueKey("muncher-dialogue") };
		}

	}
}
