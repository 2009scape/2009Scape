package core.game.content.activity.stronghold.playersafety;

import core.game.component.CloseEvent;
import core.game.component.Component;
import core.game.component.ComponentPlugin;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.game.world.map.Location;
import core.net.packet.PacketRepository;
import core.net.packet.context.CameraContext;
import core.net.packet.context.CameraContext.CameraType;
import core.net.packet.out.CameraViewPacket;
import core.plugin.Initializable;
import core.plugin.Plugin;
import core.plugin.PluginManager;

/**
 * @author Tyler Telis
 */
@Initializable
public class StrongHoldOfPlayerSafetyPlugin implements Plugin<Object> {

	/**
	 * when u pull back the poster and the dialogue pops up
	 * you get teleported to this map tile: [3140, 4230, 2]
	 */

	/**
	 * The jail object ids.
	 */
	public static final int JAIL_ENTRANCE_ID_ENTER = 29603, JAIL_ENTRANCE_LEAVE = 29602, JAIL_STAIRS_UP = 29589, JAIL_STAIRS_DOWN = 29592;

	/**
	 * The guard NPC id.
	 */
	public static final int GUARD_NPC_ID = 7142;

	/**
	 * The test item id.
	 */
	public static final int TEST_PAPER_ITEM_ID = 12626;

	/**
	 * The jail {@code Location}'s
	 */
	public static final Location JAIL_ENTRANCE_LOCATION_ENTER = Location.create(3082, 4229, 0), JAIL_ENTRANCE_LOCATION_LEAVE = Location.create(3074, 3456, 0), JAIL_UP_STAIRS = Location.create(3083, 3452, 0), JAIL_DOWN_STAIRS = Location.create(3086, 4247, 0);

	@Override
	public Plugin<Object> newInstance(Object object) throws Throwable {
		PluginManager.definePlugin(new PSOptionHandler());
		PluginManager.definePlugin(new GuardDialoguePlugin());
		PluginManager.definePlugin(new PlayerSafetyTest());
		PluginManager.definePlugin(new ProfessorHenryDialogue());
		return this;
	}

	@Override
	public Object fireEvent(String identifier, Object... object) {
		return null;
	}

	public enum JailPlaques {

		/**
		 * This sneaky gublinch wanted to set up a webcam conversation with a
		 * player! The player refused and sensibly used the Report Abuse button
		 * to enable us to catch him.
		 */
		PLAQUE_1(29599, 312),

		/**
		 * This naughty gublinchette gave a player her email address. The wise
		 * player hit the Report Abuse button straight away, allowing us to
		 * catch her!
		 */
		PLAQUE_2(29598, 695),

		/**
		 * This evil gublinch asked a younger player where she went to school.
		 * The player was smart enough to not tell the gublinch and report him
		 * instead.
		 */
		PLAQUE_3(29597, 711),

		/**
		 * This vile gublinchette was asking a player for his home address. The
		 * clever player didn't give her their address, but used the Report
		 * Abuse button to report her instead.
		 */
		PLAQUE_4(29596, 703),

		/**
		 * This sly gublinch wanted to meet one of his player friends in the
		 * real world. The player obviously refused and reported him straight
		 * away.
		 */
		PLAQUE_5(29600, 706),

		/**
		 * This cheeky gublinch claimed to be a Jagex Moderator! He was reported
		 * straight away and dealt with.
		 */
		PLAQUE_6(29601, 698),

		/**
		 * This wretched gublinch was asking a player for his telephone number.
		 * The player helped us catch him by refusing to give their information
		 * away and reporting the gublinch straight away!
		 */
		PLAQUE_7(29595, 701);

		/**
		 * The object id & interface id.
		 */
		private int objectId, interfaceId;

		/**
		 * Constructs a new {@code JailPlaques} instance.
		 * @param objectId The object id of the jail plague.
		 * @param interfaceId The interface id to send on the chat box.
		 */
		private JailPlaques(int objectId, int interfaceId) {
			this.objectId = objectId;
			this.interfaceId = interfaceId;
		}

		/**
		 * Gets the object id.
		 * @return objectId.
		 */
		public int getObjectId() {
			return objectId;
		}

		/**
		 * Gets the interface id.
		 * @return interfaceId.
		 */
		public int getInterfaceId() {
			return interfaceId;
		}

		/**
		 * Gets the value of the jail-plaque.
		 * @return The ordinal.
		 */
		public int value() {
			return ordinal();
		}

		/**
		 * Sends the jail-plague interface.
		 * @param player The {@code Player} instance.
		 */
		public void read(Player player, GameObject object) {
			int x = object.getLocation().getX();
			int y = object.getLocation().getY();
			int rotationX = x;
			int rotationY = y;
			Component component = new Component(interfaceId);
			PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.SET, x, y, 245, 1, 50));
			PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, rotationX, rotationY, 245, 1, 50));
			player.getInterfaceManager().openChatbox(component);
			component.setCloseEvent(new CloseEvent() {

				@Override
				public boolean close(Player player, Component c) {
					PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.RESET, 0, 0, 0, 1, 100));
					if (!player.getSavedData().getGlobalData().getReadPlaques()[value()]) {
						player.getSavedData().getGlobalData().getReadPlaques()[value()] = true;
					}
					return true;
				}

			});
			component.setPlugin(new ComponentPlugin() {

				@Override
				public Plugin<Object> newInstance(Object arg) throws Throwable {
					return this;
				}

				@Override
				public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
					switch (button) {
					case 2:
						player.getInterfaceManager().closeChatbox();
						player.getSavedData().getGlobalData().getReadPlaques()[value()] = true;
						return true;
					}
					return false;
				}

			});
		}
	}

	/**
	 * Find the {@link JailPlaques} instance for the object id.
	 * @param objectId The object id.
	 * @return The {@link JailPlaques} instance.
	 */
	public static JailPlaques forId(int objectId) {
		for (JailPlaques plaque : JailPlaques.values()) {
			if (plaque.getObjectId() == objectId) {
				return plaque;
			}
		}
		return null;
	}
}
