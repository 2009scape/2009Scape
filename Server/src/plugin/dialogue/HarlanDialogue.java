package plugin.dialogue;

import org.crandor.game.container.Container;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.global.Skillcape;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the dialogue plugin used for the harlan npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class HarlanDialogue extends DialoguePlugin {

	/**
	 * Represents the training sword.
	 */
	private static final Item SWORD = new Item(9703);

	/**
	 * Represents the training shield.
	 */
	private static final Item SHIELD = new Item(9704);

	/**
	 * Constructs a new {@code HarlanDialogue} {@code Object}.
	 */
	public HarlanDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code HarlanDialogue} {@code Object}.
	 * @param player the player.
	 */
	public HarlanDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new HarlanDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendOptions("Select an Option", "Can you tell me about different weapon types I can use?", "Please tell me about skillcapes.", "Bye.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Can you tell me about different weapon types I can", "use?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Please tell me about skillcapes.");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Bye.");
				stage = 30;
				break;

			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Well let me see now...There are stabbing type weapons", "such as daggers, then you have swords which are", "slashing, maces that have great crushing abilities, battle", "axes which are powerful and spears which can be good");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "for Defence and many forms of Attack.");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "It depends a lot on how you want to fight. Experiment", "and find out what is best for you. Never be scared to", "try out a new weapon; you never know, you might like", "it! Why, I tried all of them for a while and settled on");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "this rather good sword!");
			stage = 14;
			break;
		case 14:
			interpreter.sendOptions("Select an Option", "I'd like a training sword and shield.", "Bye");
			stage = 15;
			break;
		case 15:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I'd like a training sword and shield.");
				stage = 16;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Bye.");
				stage = 30;
				break;
			}
			break;
		case 16:
			if (hasBoth()) {
				interpreter.sendDialogues(npc, null, "You already have them! If they're not in your", "inventory, perhaps you should check your bank.");
				stage = 99;
				return true;
			}
			if (hasItem(SHIELD)) {
				interpreter.sendDialogues(npc, null, "You already have a shield but I can give you a sword.");
				stage = 16;
				return true;
			}
			if (hasItem(SWORD)) {
				interpreter.sendDialogues(npc, null, "You already have a sword but I can give you a shield.");
				stage = 17;
				return true;
			}
			if (player.getInventory().add(SWORD)) {
				interpreter.sendItemMessage(SWORD, "Harlan gives you a training sword.");
			} else {
				end();
			}
			stage = 17;
			break;
		case 17:
			if (player.getInventory().add(SHIELD)) {
				interpreter.sendItemMessage(SHIELD, "Harlan gives you a training shield.");
			} else {
				end();
			}
			stage = 18;
			break;
		case 18:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, null, "Of course. Skillcapes are a symbol of achievement. Only", "people who have mastered a skill and reached level 99", "can get their hands on them and gain the benefits they", "carry. Is there something else I can help you with,");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(npc, null, "perhaps?");
			stage = 22;
			break;
		case 22:
			if (player.getSkills().getStaticLevel(Skills.DEFENCE) >= 99) {
				interpreter.sendOptions("Select an Option", "Can you tell me about different weapon types I can use?", "Can I purchase a defence cape?", "Bye.");
				stage = 23;
				return true;
			}
			interpreter.sendOptions("Select an Option", "Can you tell me about different weapon types I can use?", "Please tell me about skillcapes.", "Bye.");
			stage = 0;
			break;
		case 23:
			interpreter.sendDialogues(npc, null, "You will have to pay a fee of 99,000 gp.");
			stage = 24;
			break;
		case 24:
			interpreter.sendOptions("Select an Option", "Okay.", "No, thanks.");
			stage = 25;
			break;
		case 25:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "Okay.");
				stage = 27;
				break;
			case 2:
				interpreter.sendDialogues(player, null, "No, thanks.");
				stage = 26;
				break;
			}
			break;
		case 27:
			if (Skillcape.purchase(player, Skills.DEFENCE)) {
				npc("There you go! Enjoy.");
			}
			stage = 26;
			break;
		case 26:
			end();
			break;
		case 30:
			end();
			break;
		case 99:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 705 };
	}

	/**
	 * Method usd to check if the player has both.
	 * @return <code>True</code> if so.
	 */
	public boolean hasBoth() {
		final Container[] containers = new Container[] { player.getInventory(), player.getBank(), player.getEquipment() };
		int count = 0;
		for (Container c : containers) {
			if (c.containsItem(SWORD)) {
				count++;
			}
			if (c.containsItem(SHIELD)) {
				count++;
			}
		}
		return count >= 2;
	}

	/**
	 * Method used to get the item if its in.
	 * @param item the item.
	 * @return <code>True</code>
	 */
	public boolean hasItem(final Item item) {
		final Container[] containers = new Container[] { player.getInventory(), player.getBank(), player.getEquipment() };
		for (Container c : containers) {
			if (c.containsItem(item)) {
				return true;
			}
		}
		return false;
	}
}
