package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the dialogue plugin used for thurgo.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class ThuroDialogue extends DialoguePlugin {

	/**
	 * Represents the skillcape items.
	 */
	private static final Item[] ITEMS = new Item[] { new Item(9795), new Item(9796), new Item(9797) };

	/**
	 * Represents the coins item.
	 */
	private static final Item COINS = new Item(995, 99000);

	/**
	 * Represents the redberry pie item.
	 */
	private static final Item REDBERRY_PIE = new Item(2325);

	/**
	 * Represents the blurite ore item.
	 */
	private static final Item BLURITE_ORE = new Item(668);

	/**
	 * Represents the iron bars item.
	 */
	private static final Item IRON_BARS = new Item(2351, 2);

	/**
	 * Represents the blurite sword item.
	 */
	private static final Item BLURITE_SWORD = new Item(667);

	/**
	 * Represents the current quest.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code ThuroDialogu} {@code Object}.
	 */
	public ThuroDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code ThuroDialogue} {@code Object}.
	 * @param player the player.
	 */
	public ThuroDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ThuroDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("The Knight's Sword");
		player.removeAttribute("thurgo:1");
		switch (quest.getStage(player)) {
		default:
			interpreter.sendOptions("Ask a Question", "Skillcape of Smithing.", "Something else.");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		if (stage == 0 && buttonId == 1 || player.getAttribute("thurgo:1", false)) {
			switch (stage) {
			case 0:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "That's an unusual cape you're wearing, what is it?");
					stage = 10;
					player.setAttribute("thurgo:1", true);
					break;
				case 2:
					interpreter.sendDialogue("Thurgo doesn't appear to be interested in talking.");
					stage = 9000;
					break;
				}
				break;
			case 10:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "It's a Skillcape of Smithing. Shows that I am a master", "blacksmith, but of course that's only to be expected. I", "am an Imcando dwarf after all and everybody knows", "we're the best blacksmiths.");
				stage = 11;
				break;
			case 9000:
				end();
				break;
			case 11:
				if (player.getSkills().getLevel(Skills.SMITHING) == 99) {
					interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Wow! An adventurer who is as skilled as me", "would you like to purchase a Skillcape of", "smithing for 99,000 coins?");
					stage = 12;
					return true;
				}
				end();
				break;
			case 12:
				interpreter.sendOptions("Select an Option", "Yes, please.", "No, thanks.");
				stage = 13;
				break;
			case 13:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Yes, please.");
					stage = 15;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No, thanks.");
					stage = 14;
					break;
				}
				break;
			case 14:
				end();
				break;
			case 15:
				if (player.getInventory().freeSlots() < 2) {
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Sorry, I don't have enough room in my inventory.");
					stage = 14;
					return true;
				}
				if (!player.getInventory().containsItem(COINS)) {
					end();
					return true;
				}
				if (!player.getInventory().remove(COINS)) {
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Sorry, I don't seem to have enough coins.");
					stage = 14;
					return true;
				}
				player.getInventory().add(player.getSkills().getMasteredSkills() >= 1 ? ITEMS[1] : ITEMS[0]);
				player.getInventory().add(ITEMS[2]);
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "There you go! You're truley a master of Smithing.");
				stage = 16;
				break;
			case 16:
				end();
				break;
			}
			return true;
		}
		switch (quest.getStage(player)) {
		case 60:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "About that sword...");
				stage = 1;
				break;
			case 1:
				if (player.getInventory().contains(667, 1) || player.getEquipment().contains(667, 1) || player.getBank().contains(667, 1)) {
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Thanks for all your help in getting it for me!");
					stage = 9;
				} else {
					interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "How are you doing finding those sword materials?");
					stage = 2;
				}
				break;
			case 2:
				if (player.getInventory().containsItem(BLURITE_ORE) && player.getInventory().containsItem(IRON_BARS)) {
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I have them right here.");
					stage = 5;
				} else {
					if (player.getInventory().containsItem(BLURITE_ORE)) {
						interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I don't have enough iron bars...");
						stage = 3;
						return true;
					}
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I don't have any blurite ore yet...");
					stage = 3;
				}
				break;
			case 3:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Better go get some then, huh?");
				stage = 4;
				break;
			case 4:
				end();
				break;
			case 5:
				interpreter.sendDialogue("You give the blurite ore and two iron bars to Thurgo. Thurgo starts", "to make the sword. Thurgo hands you a sword.");
				stage = 6;
				break;
			case 6:
				if (player.getInventory().remove(BLURITE_ORE) && player.getInventory().remove(IRON_BARS)) {
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Thank you very much!");
					player.getInventory().add(BLURITE_SWORD);
					stage = 7;
				}
				break;
			case 7:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Just remember to call in with more pie some time!");
				stage = 8;
				break;
			case 8:
				end();
				break;
			case 9:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "No worries mate.");
				stage = 10;
				break;
			case 10:
				end();
				break;
			}
			break;
		case 50:
			switch (stage) {
			case 0:
				if (player.getInventory().contains(666, 1)) {
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I have found a picture of the sword I would like you to", "make.");
					stage = 5;
				} else {
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "About that sword...");
					stage = 1;
				}
				break;
			case 1:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Have you got a picture of the sword for me yet?");
				stage = 2;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Sorry, not yet.");
				stage = 3;
				break;
			case 3:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Well, come back when you do.");
				stage = 4;
				break;
			case 5:
				interpreter.sendDialogue("You give the portrait to Thurgo. Thurgo studies the portrait.");
				stage = 6;
				break;
			case 6:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Ok. You'll need to get me some stuff in order for me", "to make this.");
				stage = 7;
				break;
			case 7:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "I'll need two iron bars to make the sword to start with.", "I'll also need an ore called blurite. It's useless for", "making actual weapons for fighting with except", "crossbows, but I'll need some as decoration for the hilt.");
				stage = 8;
				break;
			case 8:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "It is fairly rare sort of ore... The only place I know", "where to get it is under this cliff here...");
				stage = 9;
				break;
			case 9:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "But it is guarded by a very powerful ice giant.");
				stage = 10;
				break;
			case 10:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Most of the rocks in that cliff are pretty useless, and", "don't contain much of anything, but there's", "DEFINITELY some blurite in there.");
				stage = 11;
				break;
			case 11:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "You'll need a little bit of mining experience to be able to", "find it.");
				stage = 12;
				break;
			case 12:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Ok. I'll go and find them then.");
				stage = 13;
				break;
			case 13:
				if (player.getInventory().remove(new Item(666))) {
					quest.setStage(player, 60);
					end();
				}
				break;
			}
			break;
		case 40:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "About that sword...");
				stage = 1;
				break;
			case 1:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Have you got a picture of the sword for me yet?");
				stage = 2;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Sorry, not yet.");
				stage = 3;
				break;
			case 3:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Well, come back when you do.");
				stage = 4;
				break;
			case 4:
				end();
				break;
			}
			break;
		case 30:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Can you make me a special sword?");
				stage = 1;
				break;
			case 1:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Well, after bringing me my favorite food I guess I", "should give it a go. What sort of sword is it?");
				stage = 2;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I need you to make a sword for one of Falador's", "knights. He had one which was passed down through five", "generations, but his squire has lost it. So we need an", "identical one to replace it.");
				stage = 3;
				break;
			case 3:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "A Knight's sword eh? Well I'd need to know exactly", "how it looked before I could make a new one.");
				stage = 4;
				break;
			case 4:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "All the Faladian knights used to have swords with unique", "designs according to their position. Could you bring me", "a picture or something?");
				stage = 5;
				break;
			case 5:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I'll go and ask if his squire and see if I can find one.");
				stage = 6;
				break;
			case 6:
				quest.setStage(player, 40);
				end();
				break;
			case 24:
				end();
				break;
			}
			break;
		case 20:
			switch (stage) {
			case 0:
				if (player.getInventory().containsItem(REDBERRY_PIE)) {
					interpreter.sendOptions("Select an Option", "Hello. Are you an Imcando dwarf?", "Would you like some redberry pie?");
					stage = 1;
				} else {
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Hello. Are you an Imcando dwarf?");
					stage = 10;
				}
				break;
			case 1:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Hello. Are you an Imcando dwarf?");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Would you like some redberry pie?");
					stage = 20;
					break;
				}
				break;
			case 10:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Maybe. Who wants to know?");
				stage = 11;
				break;
			case 11:
				if (player.getInventory().containsItem(REDBERRY_PIE)) {
					interpreter.sendOptions("Select an Option", "Would you like some redberry pie?", "Can you make me a special sword?");
					stage = 12;
				} else {
					end();
				}
				break;
			case 12:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Would you like some redberry pie?");
					stage = 20;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Can you make me a special sword?");
					stage = 13;
					break;
				}
				break;
			case 13:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "No, I don't do that anymore. I'm getting old.");
				stage = 14;
				break;
			case 14:
				end();
				break;
			case 20:
				interpreter.sendDialogue("You see Thurgo's eyes light up.");
				stage = 21;
				break;
			case 21:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "I'd never say no to a redberry pie! They're GREAT", "stuff!");
				stage = 22;
				break;
			case 22:
				interpreter.sendDialogue("You hand over the pie. Thurgo eats the pie. Thurgo pats his", "stomach.");
				stage = 23;
				break;
			case 23:
				if (player.getInventory().remove(REDBERRY_PIE)) {
					quest.setStage(player, 30);
					interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "By Guthix! THAT was good pie! Anyone who makes pie", "like THAT has got to be alright!");
					stage = 24;
				}
				break;
			case 24:
				end();
				break;
			}
			break;
		default:
			switch (stage) {
			case 0:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "That's an unusual cape you're wearing, what is it?");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogue("Thurgo doesn't appear to be interested in talking.");
					stage = 9000;
					break;
				}
				break;
			case 9000:
				end();
				break;
			case 10:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "It's a Skillcape of Smithing. Shows that I am a master", "blacksmith, but of course that's only to be expected. I", "am an Imcando dwarf after all and everybody knows", "we're the best blacksmiths.");
				stage = 11;
				break;
			case 11:
				if (player.getSkills().getLevel(Skills.SMITHING) == 99) {
					interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Wow! An adventurer who is as skilled as me", "would you like to purchase a Skillcape of", "smithing for 99,000 coins?");
					stage = 12;
					return true;
				}
				end();
				break;
			case 12:
				interpreter.sendOptions("Select an Option", "Yes, please.", "No, thanks.");
				stage = 13;
				break;
			case 13:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Yes, please.");
					stage = 15;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No, thanks.");
					stage = 14;
					break;
				}
				break;
			case 14:
				end();
				break;
			case 15:
				if (player.getInventory().freeSlots() < 2) {
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Sorry, I don't have enough room in my inventory.");
					stage = 14;
					return true;
				}
				if (!player.getInventory().remove(COINS)) {
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Sorry, I don't seem to have enough coins.");
					stage = 14;
					return true;
				}
				player.getInventory().add(player.getSkills().getMasteredSkills() >= 1 ? ITEMS[1] : ITEMS[0]);
				player.getInventory().add(ITEMS[2]);
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "There you go! You're truley a master of Smithing.");
				stage = 16;
				break;
			case 16:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 604 };
	}
}
