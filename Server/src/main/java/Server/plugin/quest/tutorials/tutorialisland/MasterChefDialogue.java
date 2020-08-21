package plugin.quest.tutorials.tutorialisland;

import core.game.component.Component;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;

/**
 * Handles the master chef dialogue plugin.
 * @author 'Vexia
 */
@InitializablePlugin
public class MasterChefDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code MasterChefDialogue} {@code Object}
	 */
	public MasterChefDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code MasterChefDialogue} {@code Object}
	 * @param player the player.
	 */
	public MasterChefDialogue(Player player) {
		super(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		int tut_stage = TutorialSession.getExtension(player).getStage();
		switch (tut_stage) {
		case 18:
			Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ahh! Welcome, newcomer. I am the Master Chef, Lev. It", "is here I will teach you how to cook food truly fit for a", "king."));
			break;
		case 20:
			if (player.getInventory().containsAll(1933,1929)) {
				Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.NEUTRAL, "Mix together the flour and water to form a dough."));
				Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.NEUTRAL, "Mix together the flour and water to form a dough."));
				stage = 1;
			
			} else if (player.getInventory().containsItem(new Item(2307))) {
				Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.NEUTRAL, "You already have some dough, no need", "to make more."));
				stage = 1;
			
			} else if (player.getInventory().containsItem(new Item(2307))) {
				Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.NEUTRAL, "You already have some dough, no need", "to make more."));
				stage = 1;
			/* Disabled for now, not sure what is supposed to be happening here, but the else can't be here
			} else {
				Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.NEUTRAL, "I see you have lost your pot of flour and bucket of water,", "No worries i will supply you with more."));
				Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Mix together the flour and water to form a dough."));
				stage = 1;
			*/
			} else if (player.getInventory().containsItem(new Item(2307))) {
				Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You already have some dough, no need", "to make more."));
				stage = 1;
			} else {
				Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I see you have lost your pot of flour and bucket of water,", "No worries i will supply you with more."));
				Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.NEUTRAL, "I see you have lost your pot of flour and bucket of water,", "No worries i will supply you with more."));
				if (player.getInventory().freeSlots() >= 2) {
					player.getInventory().add(new Item(1933));
					player.getInventory().add(new Item(1929));
					stage = 1;
				} else {
					Component.setUnclosable(player, interpreter.sendDialogue("You don't have enough inventory space."));
					stage = 99;
				}
				break;	
			}
			
		case 19:
			if (!player.getInventory().contains(1929, 1) && !player.getInventory().containItems(1933)) {
				if (player.getInventory().hasSpaceFor(new Item(1929, 1)) && player.getInventory().hasSpaceFor(new Item(1933, 1))) {
					Component.setUnclosable(player, interpreter.sendDoubleItemMessage(1929, 1933, "The Cooking Guide gives you a <col=08088A>bucket of water<col> and a <col=08088A>pot of flour</col>."));
					player.getInventory().add(new Item(1933));
					player.getInventory().add(new Item(1929));
					stage = 4;
				}
			} else {
				end();
				TutorialStage.load(player, 19, false);
			}
			stage = 4;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		int tut_stage = TutorialSession.getExtension(player).getStage();
		switch (tut_stage) {
		case 20:
			switch (stage) {
			case 1:
				end();
				TutorialStage.load(player, 20, false);
				break;
			case 99:
				TutorialStage.load(player, 20, false);
				break;
			}
			break;
		case 19:
			switch (stage) {
			case 4:
				end();
				TutorialStage.load(player, 19, false);
				break;
			}
			break;
		case 18:
			switch (stage) {
			case 0:
				Component.setUnclosable(player, interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I already know how to cook. Brynna taught me just", "now."));
				stage = 1;
				break;
			case 1:
				Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hahahahahaha! You call THAT cooking? Some shrimp", "on an open log fire? Oh, no, no no. I am going to", "teach you the fine art of cooking bread."));
				stage = 2;
				break;
			case 2:
				Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "And no fine meal is complete without good music, so", "we'll cover that while you're here too."));
				stage = 3;
				break;
			case 3:
				if (!player.getInventory().contains(1929, 1) && !player.getInventory().containItems(1933)) {
					if (player.getInventory().hasSpaceFor(new Item(1929, 1)) && player.getInventory().hasSpaceFor(new Item(1933, 1))) {
						Component.setUnclosable(player, interpreter.sendDoubleItemMessage(1929, 1933, "The Cooking Guide gives you a <col=08088A>bucket of water<col> and a <col=08088A>pot of flour</col>."));
						player.getInventory().add(new Item(1933));
						player.getInventory().add(new Item(1929));
						stage = 4;
					}
				} else {
					end();
					TutorialStage.load(player, 19, false);
				}
				stage = 4;
				break;
			case 4:
				end();
				TutorialStage.load(player, 19, false);
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MasterChefDialogue(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 942 };
	}

}
