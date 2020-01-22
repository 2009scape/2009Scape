package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the skillcape dwarfs dialogue plugin.
 * @author 'Vexia
 * @version 1.5
 */
@InitializablePlugin
public final class MiningSkillCapeDwarfDialogue extends DialoguePlugin {

	/**
	 * Represents the items used for skillcape purchasing.
	 */
	private static final Item[] ITEMS = new Item[] { new Item(9792), new Item(9793), new Item(9794) };

	/**
	 * Represents the coins required to buy a skillcape.
	 */
	private static final Item COINS = new Item(995, 99000);

	/**
	 * Constructs a new {@code MiningSkillCapeDwarfDialogue} {@code Object}.
	 */
	public MiningSkillCapeDwarfDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code MiningSkillCapeDwarfDialogue} {@code Object}.
	 * @param player the player.
	 */
	public MiningSkillCapeDwarfDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MiningSkillCapeDwarfDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Welcome to the Mining Guild.", "Can I help you with anything?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("What would you like to say?", "What have you got in the Guild?", "What do you dwarves do with the ore you mine?", "Can you tell me about your skillcape?", "No thanks, I'm fine.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What have you got in the guild?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What do you dwarves do with the ore you mine?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Can you tell me about your skillcape?");
				stage = 40;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No thanks, I'm fine.");
				stage = 30;
				break;

			}
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "What do you think? We smelt it into bars, smith the metal", "to make armour and weapons, then we exchange them for", "goods and services.");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I don't see many dwarves", "selling armour or weapons here.");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "No, this is only a mining outpost. We dwarves don't much", "like to settle in human cities. Most of the ore is carted off", "to Keldagrim, the great dwarven city. They've got a", "special blast furnace up there - it makes smelting the ore");
			stage = 12;
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Ooh, it's WONDERFUL! There are lots of coal rocks,", "and even a few mithril rocks in the guild,", "all exclusively for people with at least level 60 mining.", "There's no better mining site anywhere near here.");
			stage = 11;
			break;
		case 11:
			interpreter.sendOptions("What would you like to say?", "What have you got in the Guild?", "What do you dwarves do with the ore you mine?", "Can you tell me about your skillcape?", "No thanks, I'm fine.");
			stage = 1;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "so much easier. There are plenty of dwarven traders", "working in Keldagrim. Anyway, can I help you with anything ", "else?");
			stage = 11;
			break;
		case 30:
			end();
			break;
		case 40:
			interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Sure, this is a Skillcape of Mining. It shows my stature as", "a master miner! It has all sorts of uses , if you", "have a level of 99 mining I'll sell you one.");
			stage = 41;
			break;
		case 41:
			if (player.getSkills().getStaticLevel(Skills.MINING) < 99) {
				interpreter.sendOptions("What would you like to say?", "What have you got in the Guild?", "What do you dwarves do with the ore you mine?", "Can you tell me about your skillcape?", "No thanks, I'm fine.");
				stage = 1;
			} else {
				options("I'd like to buy a Skillcape of Mining.", "Goodbye.");
				stage = 43;
			}
			break;
		case 43:
			switch (buttonId) {
			case 1:
				player("I'd like to buy a Skillcape of Mining.");
				stage = 45;
				break;
			case 2:
				player("Goodbye.");
				stage = 44;
				break;
			}
			break;
		case 44:
			end();
			break;
		case 45:
			interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "It will cost you 99,000 gold coins, are you sure?");
			stage = 46;
			break;
		case 46:
			options("Yes.", "No.");
			stage = 47;
			break;
		case 47:
			switch (buttonId) {
			case 1:
				player("Yes.");
				stage = 48;
				break;
			case 2:
				end();
				break;
			}
			break;
		case 48:
			if (!player.getInventory().containsItem(COINS)) {
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "You need 99,000 gold coins in order to buy a Skillcape", "of mining.");
				stage = 44;
				return true;
			}
			if (player.getInventory().freeSlots() < 2) {
				player.getPacketDispatch().sendMessage("You don't have enough room in your inventory.");
				end();
				return true;
			}
			if (!player.getInventory().containsItem(COINS)) {
				end();
				return true;
			}
			if (player.getInventory().remove(COINS) && player.getInventory().add(ITEMS[player.getSkills().getMasteredSkills() > 1 ? 1 : 0], ITEMS[2])) {
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Thanks!");
				stage = 49;
			}
			break;
		case 49:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3295 };
	}
}
