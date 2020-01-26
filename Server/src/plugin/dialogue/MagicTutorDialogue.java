package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the magic tutor dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class MagicTutorDialogue extends DialoguePlugin {

	/**
	 * Represents the mind runes item.
	 */
	private static final Item MIND_RUNE = new Item(558);

	/**
	 * Represents the air rune item.
	 */
	private static final Item AIR_RUNE = new Item(556);

	/**
	 * Constructs a new {@code MagicTutorDialogue} {@code Object}.
	 */
	public MagicTutorDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code MagicTutorDialogue} {@code Object}.
	 * @param player the player.
	 */
	public MagicTutorDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MagicTutorDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (args.length == 2) {
			if (player.getSavedData().getGlobalData().getTutorClaim() > System.currentTimeMillis()) {
				interpreter.sendDialogues(npc, null, "I work with the Ranged Combat tutor to give out", "consumable items that you may need for combat such", "as arrows and runes. However we have had some", "cheeky people try to take both!");
				stage = 200;
				return true;
			}
			stage = 99;
			for (int x = 0; x < 16; x++) {
				for (int y = 0; y < 16; y++) {
					final GroundItem ground = GroundItemManager.get(MIND_RUNE.getId(), player.getLocation().transform(x, y, 0), player);
					final GroundItem second = GroundItemManager.get(AIR_RUNE.getId(), player.getLocation().transform(x, y, 0), player);
					if (ground != null && ground.droppedBy(player) || second != null && second.getDropper() != null && second.getDropper() == player) {
						interpreter.sendDialogues(npc, null, "Someone seems to have dropped some " + (ground == null ? "air" : "mind") + " runes on", "the floor, perhaps you should pick them up to use them.");
						return true;
					}
				}
			}
			if (player.getInventory().freeSlots() < 2) {
				end();
				player.getPacketDispatch().sendMessage("Not enough inventory space.");
				return true;
			}
			if (player.getInventory().containsItem(MIND_RUNE) || player.getBank().containsItem(MIND_RUNE)) {
				interpreter.sendDialogues(npc, null, "You have some mind runes already!");
				stage = 69;
				return true;
			}
			if (player.getInventory().containsItem(AIR_RUNE) || player.getBank().containsItem(AIR_RUNE)) {
				interpreter.sendDialogues(npc, null, "You have some air runes already!");
				stage = 70;
				return true;
			}
			add(true);
			add(false);
			stage = 99;
			return true;
		}
		interpreter.sendOptions("Select an Option", "Can you teach me the basics please?", "Teach me about making runes.", "Goodbye.");
		stage = 1;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 69:
			if (player.getInventory().containsItem(AIR_RUNE) || player.getBank().containsItem(AIR_RUNE)) {
				interpreter.sendDialogues(npc, null, "You have some air runes already!");
				stage = 99;
				return true;
			}
			add(false);
			stage = 99;
			end();
			break;
		case 70:
			if (player.getInventory().containsItem(MIND_RUNE) || player.getBank().containsItem(MIND_RUNE)) {
				interpreter.sendDialogues(npc, null, "You have some mind runes already!");
				stage = 99;
				return true;
			}
			add(true);
			stage = 99;
			end();
			break;
		case 99:
			end();
			break;
		case 300:
			add(false);
			break;
		case 301:
			end();
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Can you teach me the basics please?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Teach me about making runes.");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Goodbye.");
				stage = 30;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "You can cast different spells according to what runes", "you have in your inventory. To start off with you'll", "need mind runes and air runes. These will allow you to", "cast Wind Strike like you did in the tutorial.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Use the spell book icon in the top right of the control", "panel to see what spells you can cast. If you have the", "correct runes, the spell will light up.");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Nemarti, the Ranged Combat tutor and I both give out", "items every 30 minutes, however you must choose", "wether you want runes or ranged equipment. To", "claim runes, right-click on me and choose Claim, to claim");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "ranged equipment right-click on the Ranged Combat", "tutor and select Claim.");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "When you have the sepll available, click on it once, then", "click on your target. A good target would be a monster", "that is below your combat level.");
			stage = 15;
			break;
		case 15:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Try rats in the castle or if you're feeling brave, the", "goblins to the west of here have been causing a", "nuisance of themselves.");
			stage = 16;
			break;
		case 16:
			interpreter.sendOptions("Select an Option", "Can you teach me the basics please?", "Teach me about making runes.", "Goodbye.");
			stage = 1;
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I'd talk to the Duke of Lumbridge if I were you. I", "hear he has an interesting artifact that might just have", "something to do with Runecrafting. I expect there wil", "be a quest involved too!");
			stage = 21;
			break;
		case 21:
			interpreter.sendOptions("Select an Option", "Can you teach me the basics please?", "Teach me about making runes.", "Goodbye.");
			stage = 1;
			break;
		case 30:
			end();
			break;
		case 200:
			interpreter.sendDialogues(npc, null, "So, every half an hour, you may come back and claim", "either arrows OR runes, but not both. Come back in a", "while for runes, or simply make your own.");
			stage = 201;
			break;
		case 201:
			end();
			break;
		}
		return true;
	}

	public void add(boolean mind) {
		if (mind) {
			if (player.getInventory().add(new Item(558, 30))) {
				player.getPacketDispatch().sendMessage("Mikasi gives you 30 mind runes.");
				stage = 300;
				player.getSavedData().getGlobalData().setTutorClaim(System.currentTimeMillis() + 1800000);
			}
		} else {
			if (player.getInventory().add(new Item(556, 30))) {
				player.getPacketDispatch().sendMessage("Mikasi gives you 30 air runes.");
				stage = 301;
				player.getSavedData().getGlobalData().setTutorClaim(System.currentTimeMillis() + 1800000);
			}
		}
	}

	@Override
	public int[] getIds() {
		return new int[] { 4707 };
	}
}
