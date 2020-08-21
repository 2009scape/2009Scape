package plugin.quest.tutorials.tutorialisland;

import core.cache.def.impl.NPCDefinition;
import core.cache.def.impl.ObjectDefinition;
import core.game.content.global.action.ClimbActionHandler;
import core.game.content.global.action.DoorActionHandler;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Handles the node options in tutorial island.
 * @author Vexia
 */
@InitializablePlugin
public class TutorialIslandPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(3015).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(3016).getConfigurations().put("option:open", this);
		NPCDefinition.forId(2796).getConfigurations().put("option:skip-tutorial", this);
		ObjectDefinition.forId(3014).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(3017).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(3018).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(3019).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(3020).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(3021).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(3022).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(3023).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(3024).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(3025).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(3026).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(3029).getConfigurations().put("option:climb-down", this);
		ObjectDefinition.forId(3030).getConfigurations().put("option:climb-up", this);
		ObjectDefinition.forId(3031).getConfigurations().put("option:climb-down", this);
		ObjectDefinition.forId(1740).getConfigurations().put("option:climb-down", this);
		ObjectDefinition.forId(3028).getConfigurations().put("option:climb-up", this);
//		PluginManager.definePlugin(new BrotherBraceDialogue(), new CombatInstructorDialogue(), new TutorialBook(), new FinancialAdvisorDialogue(), new MasterChefDialogue(), new MiningInstructorDialogue(), new QuestGuideDialogue(), new RSGuideDialogue(), new SurvivalExpertDialogue(), new TutorialCompletionDialogue());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		if (node instanceof NPC) {
			if (option.equals("skip-tutorial")) {
				player.getDialogueInterpreter().open(((NPC) node).getId(), ((NPC) node));
				return true;
			}
		} else if (node instanceof GameObject) {
			if (TutorialSession.getExtension(player).getStage() == 16) {
				int first = node.getId();
				int second = first == 3015 ? 3016 : 3015;
				if (DoorActionHandler.autowalkFence(player, (GameObject) node, first, second)) {
					TutorialStage.load(player, 17, false);
				}
				return true;
			}
			final int tut_stage = TutorialSession.getExtension(player).getStage();
			final GameObject object = (GameObject) node;
			if (object.getId() == 3028) {
				return true;
			}
			if (object.getId() == 3023 || object.getId() == 3022) {
				if (tut_stage == 54) {
					player.getDialogueInterpreter().sendDialogues(944, null, "No, don't enter the pit. Range the rats from outside", "the cage.");
					return true;
				}
				if (tut_stage > 54) {
					player.getDialogueInterpreter().sendDialogues(944, null, "Oi! Get away from there. Only enter the rat cage", "when I say so.");
					return true;
				}
				if (tut_stage >= 50) {
					if (DoorActionHandler.handleAutowalkDoor(player, object) && tut_stage == 50) {
						TutorialStage.load(player, 51, false);
					}
					return true;
				}
			}
			if (object.getId() == 3031) {
				return true;
			}
			if (object.getId() == 1740) {
				if (player.getLocation().equals(Location.create(3084, 3124, 1))) {
					ClimbActionHandler.climb(player, new Animation(827), Location.create(3084, 3124, 0));
					return true;
				}
				ClimbActionHandler.climbLadder(player, object, option);
				return true;
			}
			switch (tut_stage) {
			case 3:
				if (DoorActionHandler.handleAutowalkDoor(player, object)) {
					TutorialStage.load(player, 4, false);
				}
				break;
			case 17:
				if (DoorActionHandler.handleAutowalkDoor(player, object)) {
					TutorialStage.load(player, 18, false);
				}
				break;
			case 22:
				if (object.getId() == 3018 && DoorActionHandler.handleAutowalkDoor(player, object)) {
					TutorialStage.load(player, 23, false);
				}
				break;
			case 26:
				if (DoorActionHandler.handleAutowalkDoor(player, object)) {
					TutorialStage.load(player, 27, false);
				}
				break;
			case 29:
				if (object.getId() != 3019) {
					ClimbActionHandler.climbLadder(player, object, option);
					TutorialStage.load(player, 30, false);
				}
				break;
			case 43:
				if (object.getId() == 3021 || object.getId() == 3020) {
					if (DoorActionHandler.handleAutowalkDoor(player, object)) {
						TutorialStage.load(player, 44, false);
					}
				}
				break;
			case 55:
				if (object.getId() == 3030) {
					ClimbActionHandler.climbLadder(player, object, option);
					TutorialStage.load(player, 56, false);
				}
				break;
			case 57:
				if (object.getId() == 3024) {
					if (DoorActionHandler.handleAutowalkDoor(player, object)) {
						TutorialStage.load(player, 58, false);
					}
				}
				break;
			case 59:
				if (object.getId() == 3025) {
					if (DoorActionHandler.handleAutowalkDoor(player, object)) {
						TutorialStage.load(player, 60, false);
					}
				}
				break;
			case 66:
				if (object.getId() == 3026) {
					if (DoorActionHandler.handleAutowalkDoor(player, object)) {
						TutorialStage.load(player, 67, false);
					}
				}
				break;
			}
		}
		return true;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		if (n instanceof GameObject) {
			GameObject object = (GameObject) n;
			if (object.getDefinition().hasAction("open")) {
				return DoorActionHandler.getDestination((Entity) node, object);
			}
		}
		return null;
	}

}
