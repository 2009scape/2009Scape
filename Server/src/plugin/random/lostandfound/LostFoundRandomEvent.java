package plugin.random.lostandfound;

import java.nio.ByteBuffer;

import org.crandor.ServerConstants;
import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.content.ame.AntiMacroEvent;
import org.crandor.game.interaction.Option;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.TeleportManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.node.entity.impl.PulseManager;
import org.crandor.game.world.map.Location;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.MinimapStateContext;
import org.crandor.net.packet.out.MinimapState;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;
import org.crandor.tools.RandomFunction;

/**
 * Handles the "Lost and found" random event.
 * @author Emperor
 */
public final class LostFoundRandomEvent extends AntiMacroEvent {

	/**
	 * Constructs a new {@code LostFoundRandomEvent} {@code Object}.
	 */
	public LostFoundRandomEvent() {
		this(null);
	}

	/**
	 * Constructs a new {@code LostFoundRandomEvent} {@code Object}.
	 * @param player The player.
	 */
	public LostFoundRandomEvent(Player player) {
		super("landf", false, true, -1);
		super.player = player;
	}

	@Override
	public boolean start(Player player, boolean login, Object... args) {
		player.getProperties().setTeleportLocation(Location.create(2338, 4747, 0));
		if (player.getAttribute("l&f_dest") == null) {
			player.setAttribute("l&f_dest", args.length > 0 ? args[0] : player.getLocation());
		}
		player.sendChat("Uh? Help!");
		return true;
	}

	@Override
	public boolean enter(Entity entity) {
		if (entity instanceof Player) {
			Player player = (Player) entity;
			setRandomAppendage(player);
			PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 2));
			player.getDialogueInterpreter().sendPlainMessage(false, "There has been a fault in the teleportation matrix. Please operate the", "odd appendage out to be forwarded to your destination.");
		}
		return super.enter(entity);
	}

	@Override
	public boolean leave(Entity entity, boolean logout) {
		if (entity instanceof Player && !logout) {
			Player player = (Player) entity;
			PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
			player.removeAttribute("l&f_dest");
			player.removeAttribute("teleport:items");
			player.getLocks().unlock();
			player.getAntiMacroHandler().setEvent(null);
		}
		return super.leave(entity, logout);
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		if (!(target instanceof GameObject)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean actionButton(Player player, int interfaceId, int buttonId, int slot, int itemId, int opcode) {
		return false;
	}

	@Override
	public boolean teleport(Entity e, int type, Node node) {
		if (type != -1) {
			if (e instanceof Player) {
				((Player) e).getPacketDispatch().sendMessage("You have to operate the appendage before you can teleport again.");
			}
			return false;
		}
		return true;
	}

	@Override
	public AntiMacroEvent create(Player player) {
		return new LostFoundRandomEvent(player);
	}

	@Override
	public Location getSpawnLocation() {
		return null;
	}

	/**
	 * Sets a random odd appendage.
	 * @param player The player.
	 */
	private static void setRandomAppendage(Player player) {
		int value = 0;
		int odd = RandomFunction.random(4);
		int mod = RandomFunction.RANDOM.nextBoolean() ? 0 : 1;
		int offset = RandomFunction.random(4) * 2;
		for (int i = 0; i < 4; i++) {
			if (i == odd) {
				value |= (offset + (1 - mod)) << (i * 5);
			} else {
				value |= (offset + mod) << (i * 5);
			}
		}
		player.getConfigManager().set(531, value);
	}

	@Override
	public void configure() {
		registerRegion(9290);
		PluginManager.definePlugin(new OptionHandler() {
			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				for (int id = 8998; id < 9006; id++) {
					ObjectDefinition.forId(id).getConfigurations().put("option:operate", this);
				}
				return this;
			}

			@Override
			public boolean handle(final Player player, Node node, String option) {
				GameObject object = (GameObject) node;
				player.lock(2);
				if (isOddAppendage(player, object)) {
					GameWorld.Pulser.submit(new Pulse(4) {
						@Override
						public boolean pulse() {
							player.getDialogueInterpreter().sendPlainMessage(false, "The Abyssal Services Department apologises for the inconvenience.");
							return true;
						}
					});
					player.getInventory().add(player.getAttribute("teleport:items", new Item[0]));
					player.getTeleporter().send(player.getAttribute("l&f_dest", ServerConstants.HOME_LOCATION), TeleportManager.getType(player), -1);
				} else {
					setRandomAppendage(player);
					player.getPacketDispatch().sendMessage("That was not the correct appendage!");
				}
				return true;
			}

			/**
			 * Checks if the object was the player's odd appendage.
			 * @param player The player.
			 * @param objectId The object id.
			 * @return {@code True} if the odd appendage was operated.
			 */
			private boolean isOddAppendage(Player player, GameObject object) {
				int index = object.getWrapper().getId() - 8994;
				int current = object.getWrapper().getChild(player).getId();
				for (int i = 0; i < 4; i++) {
					if (index != i && ObjectDefinition.forId(8994 + i).getChildObject(player).getId() == current) {
						return false;
					}
				}
				return true;
			}
		});
	}

	@Override
	public void save(ByteBuffer buffer) {
		Location l = player.getAttribute("l&f_dest", player.getLocation());
		buffer.putShort((short) l.getX()).putShort((short) l.getY()).put((byte) l.getZ());
		Item[] runes = player.getAttribute("teleport:items", new Item[0]);
		buffer.put((byte) runes.length);
		for (int i = 0; i < runes.length; i++) {
			buffer.putShort((short) runes[i].getId());
			buffer.put((byte) runes[i].getAmount());
		}
	}

	@Override
	public void parse(ByteBuffer buffer) {
		player.setAttribute("l&f_dest", Location.create(buffer.getShort(), buffer.getShort(), buffer.get()));
		Item[] runes = new Item[buffer.get() & 0xFF];
		for (int i = 0; i < runes.length; i++) {
			runes[i] = new Item(buffer.getShort() & 0xFFFF, buffer.get() & 0xFF);
		}
		if (runes.length > 0) {
			player.setAttribute("teleport:items", runes);
		}
	}

}