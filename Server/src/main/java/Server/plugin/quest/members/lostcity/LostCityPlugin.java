package plugin.quest.members.lostcity;

import core.cache.def.impl.ItemDefinition;
import core.cache.def.impl.ObjectDefinition;
import plugin.dialogue.FacialExpression;
import core.game.content.global.action.DoorActionHandler;
import core.game.content.global.action.EquipHandler;
import plugin.skill.gather.GatheringSkillPulse;
import plugin.skill.gather.SkillingTool;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.TeleportManager.TeleportType;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.plugin.Plugin;

/**
 * Handles the lost city quest.
 * @author Vexia
 */
public final class LostCityPlugin extends OptionHandler {
	
	/**
	 * The dramen staff item.
	 */
	private static final Item DRAMEN_STAFF = new Item(772);

	/**
	 * The shamus npc.
	 */
	public static NPC SHAMUS;

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.forId(1305).getConfigurations().put("option:wield", this);
		ItemDefinition.forId(1215).getConfigurations().put("option:wield", this);
		ItemDefinition.forId(1231).getConfigurations().put("option:wield", this);
		ItemDefinition.forId(5680).getConfigurations().put("option:wield", this);
		ObjectDefinition.forId(2409).getConfigurations().put("option:chop", this);
		ObjectDefinition.forId(2406).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(1292).getConfigurations().put("option:chop down", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final Quest quest = player.getQuestRepository().getQuest("Lost City");
		switch (node.getId()) {
		case 2409:
			handleShamusTree(player, quest);
			break;
		case 1292:
			if (SkillingTool.getHatchet(player) == null) {
				player.getPacketDispatch().sendMessage("You do not have an axe which you have the level to use.");
				return true;
			}
			if (quest.getStage(player) < 20) {
				return true;
			}
			if (quest.getStage(player) == 20) {
				if (player.getAttribute("treeSpawned", false)) {
					return true;
				}
				TreeSpiritNPC spirit = (TreeSpiritNPC) TreeSpiritNPC.create(655, Location.create(2862, 9734, 0));
				spirit.setPlayer(player);
				spirit.setRespawn(false);
				spirit.init();
				spirit.attack(player);
				player.setAttribute("treeSpawned", true);
				spirit.sendChat("You must defeat me before touching the tree!");
				return true;
			}
			player.getPulseManager().run(new GatheringSkillPulse(player, (GameObject) node));
			break;
		case 2406:
			final boolean dramenTeleport = player.getEquipment().containsItem(DRAMEN_STAFF) && quest.getStage(player) > 20 && player.getLocation().getX() <= 3201;
			DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
			if (dramenTeleport) {
				player.getPacketDispatch().sendMessage("The world starts to shimmer...");
				player.getTeleporter().send(Location.create(2452, 4473, 0), TeleportType.FAIRY_RING);
			}
			break;
		case 1305:
		case 1215:
		case 1231:
		case 5680:
			if (!player.getQuestRepository().isComplete("Lost City")) {
				player.getPacketDispatch().sendMessage("You need to have completed the Lost City quest in order to wield that weapon.");
				return true;
			}
			return EquipHandler.SINGLETON.handle(player, node, option);
		}
		return true;
	}

	/**
	 * Handles the shamus tree interaction.
	 * @param player the player.
	 * @param quest the quest.
	 */
	private void handleShamusTree(Player player, Quest quest) {
		if (SkillingTool.getHatchet(player) == null) {
			player.getPacketDispatch().sendMessage("You do not have an axe which you have the level to use.");
			return;
		}
		if (SHAMUS == null) {
			initalizeShamus();
		}
		if (SHAMUS.isInvisible()) {
			showShamus();
			return;
		}
		player.getDialogueInterpreter().sendDialogues(SHAMUS, FacialExpression.FURIOUS, "Hey! Yer big elephant! Don't go choppin' down me", "house, now!");
	}

	/**
	 * Shows the shamus npc.
	 */
	private void showShamus() {
		if (SHAMUS == null) {
			initalizeShamus();
		}
		SHAMUS.setInvisible(false);
		SHAMUS.getProperties().setTeleportLocation(SHAMUS.getProperties().getSpawnLocation());
		GameWorld.Pulser.submit(new Pulse(100, SHAMUS) {
			@Override
			public boolean pulse() {
				if (SHAMUS.getDialoguePlayer() != null) {
					return false;
				}
				SHAMUS.setInvisible(true);
				return true;
			}
		});
	}

	/**
	 * Initializes the shamus npc.
	 */
	private void initalizeShamus() {
		if (SHAMUS == null) {
			SHAMUS = NPC.create(654, Location.create(3138, 3211, 0));
		}
		SHAMUS.init();
		SHAMUS.setWalks(true);
		SHAMUS.setInvisible(true);
	}

	@Override
	public boolean isWalk(Player player, Node node) {
		return !(node instanceof Item);
	}

	@Override
	public boolean isWalk() {
		return false;
	}
}
