package plugin.quest.tutorials.tutorialisland;

import core.plugin.InitializablePlugin;
import core.game.component.Component;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;

/**
 * Handles survival expert dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class SurvivalExpertDialogue extends DialoguePlugin {

	/**
	 * The NPC ids that use this dialogue plugin.
	 */
	private static final int[] NPC_IDS = { 943 };

	public SurvivalExpertDialogue() {
		/*
		 * empty.
		 */
	}

	public SurvivalExpertDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return NPC_IDS;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		int tut_stage = TutorialSession.getExtension(player).getStage();
		switch (tut_stage) {
		case 6:
			end();
			TutorialStage.load(player, 6, false);
			break;
		case 12:
			end();
			TutorialStage.load(player, 15, false);
			break;
		case 4:
			switch (stage) {
			case 0:
				Component.setUnclosable(player, interpreter.sendDoubleItemMessage(590, 1351, "The Surivival Guide gives you a <col=08088A>tinderbox</col> and a <col=08088A>bronze axe</col>!"));
				player.getInventory().add(new Item(590, 1));
				player.getInventory().add(new Item(1351, 1));
				stage = 1;
				break;
			case 1:
				end();// Component.setUnclosable(player,
				TutorialStage.load(player, 5, false);
				break;
			}
			break;
		case 11:
			switch (stage) {
			case 0:
				Component.setUnclosable(player, interpreter.sendItemMessage(303, "The Survival Guide gives you a <col=08088A>net</col>!"));
				player.getInventory().add(new Item(303));
				stage = 1;
				break;
			case 1:
				end();
				TutorialStage.load(player, 12, false);
				break;
			}
			break;
		case 8:
			end();
			TutorialStage.load(player, TutorialSession.getExtension(player).getStage(), false);
			break;
		case 14:
			switch (stage) {
			case 0:
				end();
				TutorialStage.load(player, TutorialSession.getExtension(player).getStage(), false);
				break;
			}
			break;
		case 15:
			end();
			TutorialStage.load(player, TutorialSession.getExtension(player).getStage(), false);
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new SurvivalExpertDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		int tut_stage = TutorialSession.getExtension(player).getStage();
		switch (tut_stage) {
		case 4:
			Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello there, newcomer. My name is Brynna. My job is", "to teach you a few suvival tips and tricks. First off", "we're going to start with the most basic skill of", "all: making a fire."));
			break;
		case 6:
			if (!player.getInventory().contains(1351, 1) && player.getInventory().contains(590, 1)) {
				Component.setUnclosable(player, interpreter.sendItemMessage(1351, "The Surivival Guide gives you a <col=08088A>bronze axe</col>."));
				player.getInventory().add(new Item(1351));
			} else if (!player.getInventory().contains(590, 1) && player.getInventory().contains(1351, 1)) {
				player.getInventory().add(new Item(590, 1));
				Component.setUnclosable(player, interpreter.sendDialogue("The Surivival Guide gives you a <col=08088A>tinderbox</col>."));
			} else if (!player.getInventory().contains(590, 1) && !player.getInventory().contains(1351, 1)) {
				Component.setUnclosable(player, interpreter.sendDoubleItemMessage(590, 1351, "The Surivival Guide gives you a <col=08088A>tinderbox</col> and a <col=08088A>bronze axe</col>!"));
				player.getInventory().add(new Item(590, 1));
				player.getInventory().add(new Item(1351, 1));
			}
			stage = 90;
			break;
		case 11:
			Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well done! Next we need to get some food in our", "bellies. We'd need something to cook. There are shrimp", "in the pond there. So let's catch and cook some."));
			break;
		case 8:
			if (!player.getInventory().contains(1351, 1) && player.getInventory().contains(590, 1)) {
				Component.setUnclosable(player, interpreter.sendItemMessage(1351, "The Surivival Guide gives you a <col=08088A>bronze axe</col>."));
				player.getInventory().add(new Item(1351));
			} else if (!player.getInventory().contains(590, 1) && player.getInventory().contains(1351, 1)) {
				player.getInventory().add(new Item(590, 1));
				Component.setUnclosable(player, interpreter.sendDialogue("The Surivival Guide gives you a <col=08088A>tinderbox</col>."));
			} else {
				end();
				TutorialStage.load(player, 8, false);
			}
			break;
		case 14:
			if (!player.getInventory().contains(1351, 1) && player.getInventory().contains(590, 1)) {
				Component.setUnclosable(player, interpreter.sendItemMessage(1351, "The Surivival Guide gives you a <col=08088A>bronze axe</col>."));
				player.getInventory().add(new Item(1351));
			} else if (!player.getInventory().contains(590, 1) && player.getInventory().contains(1351, 1)) {
				player.getInventory().add(new Item(590, 1));
				Component.setUnclosable(player, interpreter.sendDialogue("The Surivival Guide gives you a <col=08088A>tinderbox</col>."));
			} else {
				end();
				TutorialStage.load(player, 14, false);
			}
			break;
		case 12:
			if (!player.getInventory().contains(303, 1)) {
				player.getInventory().add(new Item(303, 1));
				Component.setUnclosable(player, interpreter.sendDialogue("The Surivival Guide gives you a <col=08088A>net</col>."));
			} else {
				end();
				TutorialStage.load(player, 12, false);
			}
			break;
		case 15:
			if (!player.getInventory().contains(303, 1)) {
				player.getInventory().add(new Item(303, 1));
				Component.setUnclosable(player, interpreter.sendDialogue("The Surivival Guide gives you a <col=08088A>net</col>."));
			} else {
				end();
				TutorialStage.load(player, 15, false);
			}
			break;
		}
		return true;
	}

}
