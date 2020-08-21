package plugin.quest.tutorials.tutorialisland;

import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;

/**
 * Represents the mining instructor dialogue.
 * @author Vexia
 */
@InitializablePlugin
public final class MiningInstructorDialogue extends DialoguePlugin {

	/**
	 * Represents the hammer item.
	 */
	private static final Item HAMMER = new Item(2347, 1);

	/**
	 * Represents the pickaxe item.
	 */
	private static final Item PICKAXE = new Item(1265);

	/**
	 * Constructs a new {@code MiningInstructorDialogue} {@code Object}.
	 */
	public MiningInstructorDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code MiningInstructorDialogue} {@code Object}.
	 * @param player the player.
	 */
	public MiningInstructorDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MiningInstructorDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		switch (TutorialSession.getExtension(player).getStage()) {
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hi there. You must be new around here. So what do I", "call you? 'Newcomer' seems so impersonal, and if we're", "going to be working together, I'd rather call you by", "name.");
			break;
		case 34:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I prospected both types of rock! One set contains tin", "and the other has copper ore inside.");
			break;
		case 35:
			if (!player.getInventory().contains(1265, 1)) {
				if (player.getInventory().hasSpaceFor(PICKAXE)) {
					interpreter.sendItemMessage(1265, "Dezzick gives you a <col=08088A>bronze pickaxe</col>!");
					player.getInventory().add(PICKAXE);
					stage = 3;
				} else {
					end();
					TutorialStage.load(player, 35, false);
				}
			} else {
				end();
				TutorialStage.load(player, 35, false);
			}
			break;
		case 40:
			interpreter.sendDialogues(player, null, "How do I make a weapon out of this?");
			stage = 1;
			break;
		case 41:
			if (!player.getInventory().contains(2347, 1)) {
				if (player.getInventory().hasSpaceFor(HAMMER)) {
					interpreter.sendItemMessage(2347, "Dezzick gives you a <col=08088A>hammer!");
					player.getInventory().add(HAMMER);
					stage = 3;
				}
			} else {
				end();
				TutorialStage.load(player, 41, false);
			}
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (TutorialSession.getExtension(player).getStage()) {
		case 30:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "You can call me " + player.getUsername() + ".");
				stage = 1;
				break;
			case 1:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ok then, " + player.getUsername() + ". My name is Dezzick and I'm a", "miner by Trade. Let's prospect some of those rocks.");
				stage = 2;
				break;
			case 2:
				end();
				TutorialStage.load(player, 31, false);
				break;
			}
			break;
		case 35:
			end();
			TutorialStage.load(player, 35, false);
			break;
		case 40:
			switch (stage) {
			case 1:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Okay, I'll show you how to make a dagger out of it.", "You'll be needing this..");
				stage = 2;
				break;
			case 2:
				if (!player.getInventory().contains(2347, 1)) {
					if (player.getInventory().hasSpaceFor(HAMMER)) {
						interpreter.sendItemMessage(2347, "Dezzick gives you a <col=08088A>hammer!");
						player.getInventory().add(HAMMER);
						stage = 3;
					}
				} else {
					end();
					TutorialStage.load(player, 41, false);
				}
				break;
			case 3:
				end();
				TutorialStage.load(player, 41, false);
				break;
			}
			break;
		case 41:
			end();
			TutorialStage.load(player, 41, false);
			break;
		case 34:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Absolutely right, " + player.getUsername() + ". These two ore types can be", "Smelted together to make bronze.");
				stage = 1;
				break;
			case 1:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "So now you know what ore is in the rocks over there,", "why don't you have a go at mining some tin and", "copper? Here, you'll need this to start with.");
				stage = 2;
				break;
			case 2:
				if (!player.getInventory().contains(1265, 1)) {
					if (player.getInventory().hasSpaceFor(PICKAXE)) {
						interpreter.sendItemMessage(1265, "Dezzick gives you a <col=08088A>bronze pickaxe</col>!");
						player.getInventory().add(PICKAXE);
						stage = 3;
					} else {
						end();
						TutorialStage.load(player, 34, false);
					}
				} else {
					end();
					TutorialStage.load(player, 35, false);
				}
				break;
			case 3:
				end();
				TutorialStage.load(player, 35, false);
				break;
			case 99:
				end();
				TutorialStage.load(player, 34, false);
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 948 };
	}
}
