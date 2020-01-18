package plugin.interaction.inter;

import org.crandor.game.component.Component;
import org.crandor.game.component.ComponentDefinition;
import org.crandor.game.component.ComponentPlugin;
import org.crandor.game.content.global.tutorial.TutorialSession;
import org.crandor.game.content.global.tutorial.TutorialStage;
import org.crandor.game.node.entity.combat.equipment.WeaponInterface;
import org.crandor.game.node.entity.combat.equipment.WeaponInterface.WeaponInterfaces;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.Rights;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.RegionManager;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.InterfaceConfigContext;
import org.crandor.net.packet.out.InterfaceConfig;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the component plugin used for the game interface.
 * @author Vexia
 *
 */
@InitializablePlugin
public final class GameInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(548, this);
		ComponentDefinition.put(750, this);
		ComponentDefinition.put(751, this);
		ComponentDefinition.put(740, this);
		ComponentDefinition.put(746, this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Component component, int opcode, int button, int slot, int itemId) {
		switch (component.getId()) {
			case 740:
				switch (button){
					case 3:
						player.getInterfaceManager().closeChatbox();
						break;
				}
				return true;
			case 746:
				switch (button){
					case 110:
						configureWorldMap(player);
						break;
				}
				return true;
			case 548:
				int tut_stage = TutorialSession.getExtension(player).getStage();
				if (button >= 38 && button<= 44 || button >= 20 && button <= 26) {
					player.getInterfaceManager().setCurrentTabIndex(getTabIndex(button));
				}
				//Interface buttons that advance the Tutorial Island stages
				switch (button) {
					case 21://Friends Tab
						if (tut_stage == 63) {
							player.getConfigManager().set(1021, 0);
							TutorialStage.load(player, 64, false);
						}

						break;
					case 22://Ignore Tab
						if (tut_stage == 64) {
							player.getConfigManager().set(1021, 0);
							TutorialStage.load(player, 65, false);
						}
						break;
					case 24://Settings Tab
						if (tut_stage == 1) {
							TutorialStage.load(player, 2, false);
						}
						break;
					case 25://Emotes Tab
						if (tut_stage == 23) {
							TutorialStage.load(player, 24, false);
						}
						break;
					case 26://Music Tab
						if (tut_stage == 21) {
							TutorialStage.load(player, 22, false);
						}
						break;
					case 38://Attack Tab
						if (tut_stage == 49) {
							player.getConfigManager().set(1021, 0);
							TutorialStage.load(player, 50, false);
						} else {
							if (TutorialSession.getExtension(player).getStage() > TutorialSession.MAX_STAGE) {
								if (player.getExtension(WeaponInterface.class) == WeaponInterfaces.STAFF) {
									final Component c = new Component(WeaponInterfaces.STAFF.getInterfaceId());
									player.getInterfaceManager().openTab(0, c);
									final WeaponInterface inter = player.getExtension(WeaponInterface.class);
									inter.updateInterface();
								}
							}
						}
						break;
					case 39://Skill Tab
						if (tut_stage == 10) {
							player.getConfigManager().set(1021, 0);
							TutorialStage.load(player, 11, false);
						}
						break;
					case 40://Quest Tab
						if (tut_stage == 27) {
							TutorialStage.load(player, 28, false);
						}
				/*if (GameWorld.isEconomyWorld()) {
					player.getQuestRepository().syncronizeTab(player);
				}  else {
					player.getSavedData().getSpawnData().drawStatsTab(player);
				}*/

						player.getQuestRepository().syncronizeTab(player);
						break;
					case 41://Inventory Tab
						if (tut_stage == 5) {
							player.getConfigManager().set(1021, 0);
							TutorialStage.load(player, 6, false);
						}
						player.getInventory().refresh();
						break;
					case 42://Worn Equipment Tab
						if (tut_stage == 45) {
							player.getConfigManager().set(1021, 0);
							TutorialStage.load(player, 46, false);
						}
						break;
					case 43://Prayer Tab
						if (tut_stage == 61) {
							player.getConfigManager().set(1021, 0);
							TutorialStage.load(player, 62, false);
						}
						break;
					case 44://Magic Tab
						if (tut_stage == 68) {
							player.getConfigManager().set(1021, 0);
							TutorialStage.load(player, 69, false);
							player.getDialogueInterpreter().open(946, RegionManager.getNpc(player, 946));
						}
						break;
					case 66://World map
					case 110:
						configureWorldMap(player);
						break;
					case 69://Logout
						player.getPacketDispatch().sendString("When you have finished playing " + GameWorld.getName() + ", always use the button below to logout safely. ", 182, 0);
						break;
				}
				return true;
			case 750:
				switch (opcode) {
					case 155:
						switch (button) {
							case 1:
								player.getSettings().toggleRun();
								break;
						}
						break;
				}
				return true;
			case 751:
				switch (opcode) {
					case 155:
						switch (button) {
							case 27:
								openReport(player);
								break;
						}
						break;
				}
				return true;
		}
		return true;
	}

	/**
	 * Method used to open the report interface.
	 * @param player the player.
	 */
	public static void openReport(final Player player) {
		player.getInterfaceManager().open(new Component(553)).setCloseEvent((player1, c) -> {
			player1.getPacketDispatch().sendRunScript(80, "");
			player1.getPacketDispatch().sendRunScript(137, "");
			return true;
		});
		player.getPacketDispatch().sendRunScript(508, "");
		if (player.getDetails().getRights() != Rights.REGULAR_PLAYER) {
			for (int i = 0; i < 18; i++) {
				player.getPacketDispatch().sendInterfaceConfig(553, i, false);
			}
		}
	}

	/**
	 * Gets the tab index.
	 * @param button The button id.
	 * @return The tab index.
	 */
	private static int getTabIndex(int button) {
		int tabIndex = button - 38;
		if (button < 27) {
			tabIndex = (button - 20) + 7;
		}
		return tabIndex;
	}

	/**
	 * Configures the world map for a player.
	 * @param player The player.
	 */
	private void configureWorldMap(Player player) {
		if (player.inCombat()) {
			player.getPacketDispatch().sendMessage("It wouldn't be very wise opening the world map during combat.");
			return;
		}
//		player.getInterfaceManager().openWindowsPane(new Component(755), 1);
//		player.getPacketDispatch().sendScriptConfig(1187, 0, "ii", 0);
		int position = player.getLocation().getX() << 14 | player.getLocation().getY() | player.getLocation().getZ() << 28;

//		player.getPacketDispatch().sendScriptConfig(622, position, "ii");
//		player.getPacketDispatch().sendScriptConfig(674, position, "ii");
//		player.getPacketDispatch().sendScriptConfig(622, position);
//		player.getPacketDispatch().sendScriptConfig(674, position);
		System.out.println(position);


		/*player.getPacketDispatch().sendWindowsPane(755, 1);
		int hash = player.getLocation().getX() << 14 | player.getLocation().getY();
		player.getPacketDispatch().sendRunScript(622, "",  hash);
		player.getPacketDispatch().sendRunScript(674, "", hash);
		PacketRepository.send(InterfaceConfig.class, new InterfaceConfigContext(player, 371, 25, true));
		player.setAttribute("worldMap:viewing", true);*/
	}
}
