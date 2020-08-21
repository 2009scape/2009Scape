package plugin.quest.free.dragonslayer;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;

/**
 * Represents the dialogue used to handle the oziach dialogue.
 * @author Aero
 * @author 'Vexia
 */
public final class OziachDialogue extends DialoguePlugin {

	/**
	 * Represents the demon slayer quest.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code OziachDialogue} {@code Object}.
	 */
	public OziachDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code OziachDialogue} {@code Object}.
	 * @param player the player.
	 */
	public OziachDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new OziachDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("Dragon Slayer");
		player.debug("" + quest.getStage(player));
		switch (quest.getStage(player)) {
		case 100:
			npc("Aye, 'tis a fair day, my mighty dragon-slaying friend.");
			if (player.getInventory().containsItem(new Item(11286))) {// player.getEquipment().containsItem(new
				// Item(1540))//TODO:
				// Anti-drag
				// shield
				// dialogue.
				stage = 42;
			} else {
				stage = 0;
			}
			break;
		case 40:
		case 30:
		case 20:
		case 15:
			if (args.length > 1) {
				npc("I'm not selling' ye anything till you've slayed that", "dragon! Now be off wi' ye.");
				stage = -1;
				return true;
			} else {
				npc("Have ye slayed that dragon yet?");
				stage = 0;
			}
			break;
		case 10:
			if (args.length > 1) {
				player("Can you sell me a rune platebody?");
				stage = 0;
			} else {
				npc("Aye, 'tis a fair day my friend.");
				stage = -1;
			}
			break;
		default:
			npc("Aye, 'tis a fair day my friend.");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 30:
		case 20:
			if (stage == -1) {
				end();
			} else {
				end();
				break;
			}
			break;
		case 100:
			switch (stage) {
			case 0:
				options("Can I buy a rune platebody now, please?", "I'm not your friend.", "Yes, it's a very nice day.", "Can I have another key to Melzar's Maze?");
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					player("Can I buy a rune platebody now, please?");
					stage = 10;
					break;
				case 2:
					player("I'm not your friend.");
					stage = 20;
					break;
				case 3:
					player("Yes, it's a very nice day.");
					stage = 30;
					break;
				case 4:
					player("Can I have another key to Melzar's Maze?");
					stage = 40;
					break;
				case 5:
					player("Here you go.");
					stage = 44;
					break;
				}
				break;
			case 10:
				end();
				npc.openShop(player);
				break;
			case 20:
				end();
				break;
			case 30:
				end();
				break;
			case 40:
				npc("It's the Guildmaster in the Champions' Guild who hands", "those keys out now. Go talk to him. No need to bother", "me if you don't need armour.");
				stage = 41;
				break;
			case 41:
				end();
				break;
			case 42:
				npc("Ye've got... Ye've found a draconic visage! Could I look at", "it?");
				stage++;
				break;
			case 43:
				options("Can I buy a rune platebody now, please?", "I'm not your friend.", "Yes, it's a very nice day.", "Can I have another key to Melzar's Maze?", "Here you go");
				stage = 1;
				break;
			case 44:
				npc("Amazin'! Ye can almost feel it pulsin with draconic power!");
				stage++;
				break;
			case 45:
				npc("Now, if ye want me to, I could attach this to yer anti-", "dragonbreath shield and make something pretty special.");
				stage++;
				break;
			case 46:
				npc("The shield won't be easy to weild though; ye'll need level", "75 Defence.");
				stage++;
				break;
			case 47:
				npc("I'll charge 1,250,000 coins to construct it. What d'ye say?");
				stage++;
				break;
			case 48:
				options("Yes, please!", "No, thanks.", "That's a bit expensive!");
				stage++;
				break;
			case 49:
				switch (buttonId) {
				case 1:
					player("Yes please!");
					stage = 50;
					break;
				case 2:
					player("No thanks.");
					stage = 52;
					break;
				case 3:
					player("That's a bit expensive!");
					stage = 53;
					break;
				}
				break;
			case 50:
				if (player.getInventory().contains(11286, 1)) {
					if (player.getInventory().contains(995, 1250000)) {
						if (player.getInventory().contains(1540, 1)) {
							player.getInventory().remove(new Item(1540, 1));
							player.getInventory().remove(new Item(11286, 1));
							player.getInventory().remove(new Item(995, 1250000));
							player.getInventory().add(new Item(11283, 1));
							player.getDialogueInterpreter().sendItemMessage(11283, "Oziach skillfully forges the shield and visage into a new shield.");
							stage = 51;
						} else {
							npc("Ye need an anti-dragonbreath shield for me to", "attach this onto, talk to me again once you do.");
							stage = 41;
						}
					} else {
						player("I don't seem to have enough coins tho,", "I will return once I do.");
						stage = 41;
					}
				} else {
					npc("Ye need the draconic visage for me to attach.", "Talk to me again once you have it.");
					stage = 41;
				}
				break;
			case 51:
				npc("There ye go. Now, the more dragonfire your shield", "absorbs, the more powerful it'll become.");
				stage = 41;
				break;
			case 52:
				npc("Talk to me again if ye change your mind", "mighty dragon slayer.");
				stage = 41;
				break;
			case 53:
				npc("It's the price ye pay to make such a magnificent shield.");
				stage = 41;
				break;
			}
			break;
		case 40:
			switch (stage) {
			case -1:
				end();
				break;
			case 0:
				if (!player.getInventory().containsItem(DragonSlayer.ELVARG_HEAD)) {
					player("Nope, not yet.");
					stage = -1;
					return true;
				}
				player("Yes, I have!");
				stage = 2;
				break;
			case 2:
				player("I have its head here.");
				stage = 3;
				break;
			case 3:
				npc("You actually did it?");
				stage = 4;
				break;
			case 4:
				npc("I underestimated ye, adventurer. I apologize.");
				stage = 5;
				break;
			case 5:
				npc("Yer a true hero, and I'd be happy to sell ye rune", "platebodies.");
				stage = 6;
				break;
			case 6:
				end();
				if (player.getInventory().remove(DragonSlayer.ELVARG_HEAD) && !player.getQuestRepository().getQuest("Dragon Slayer").isCompleted(player)) {
					quest.finish(player);
				}
				break;
			}
			break;
		case 15:
			switch (stage) {
			case -1:
				end();
				break;
			case 0:
				player("Um... no.");
				stage = 2;
				break;
			case 1:
				player("Um... no.");
				stage = 2;
				break;
			case 2:
				npc("Be off with ye then.");
				stage = 3;
				break;
			case 3:
				end();
				break;
			}
			break;
		case 10:
			switch (stage) {
			case -1:
				player("Can you sell me a rune platebody?");
				stage = 0;
				break;
			case 0:
				npc("So, how does thee know I 'ave some?");
				stage = 2;
				break;
			case 2:
				player("The Guildmaster of the Champions' Guild told me.");
				stage = 3;
				break;
			case 3:
				npc("Yes, I suppose he would, wouldn't he? He's always", "sending you fancy-pants 'heroes' up to bother me.", "Telling me I'll give them a quest or sommat like that.");
				stage = 4;
				break;
			case 4:
				npc("Well, I'm not going to let just anyone wear my rune", "platemail. It's only for heroes. So, leave me alone.");
				stage = 5;
				break;
			case 5:
				player("I thought you were going to give me a quest.");
				stage = 6;
				break;
			case 6:
				npc("*Sigh*");
				stage = 7;
				break;
			case 7:
				npc("All right, I'll give ye a quest. I'll let ye wear my rune", "platemail if ye...");
				stage = 8;
				break;
			case 8:
				npc("Slay the dragon of Crandor!");
				stage = 9;
				break;
			case 9:
				options("A dragon, that sounds like fun.", "I may be a champion, but I don't think I'm up to dragon-killing yet.");
				stage = 10;
				break;
			case 10:
				switch (buttonId) {
				case 1:
					player("A dragon, that sounds like fun!");
					stage = 100;
					break;
				case 2:
					player("I may be a champion, but I don't think I'm up to", "dragon-killing yet.");
					stage = 200;
					break;
				}
				break;
			case 100:
				npc("Hah, yes, you are a typical reckless adventurer, aren't", "you? Now go kill the dragon and get out of my face.");
				stage = 101;
				break;
			case 101:
				player("But how can I defeat the dragon?");
				stage = 102;
				break;
			case 102:
				npc("Go talk to the Guildmaster in the Champions' Guild. He'll", "help ye out if yer so keen on doing a quest. I'm not", "going to be handholding any adventurers.");
				stage = 103;
				break;
			case 103:
				quest.setStage(player, 15);
				end();
				break;
			case 200:
				npc("Yes, I can understand that. Yer a coward.");
				stage = 201;
				break;
			case 201:
				end();
				break;
			}
			break;
		default:
			switch (stage) {
			case 0:
				options("I'm not your friend.", "Yes, it's a very nice day.");
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					player("I'm not your friend.");
					stage = 10;
					break;
				case 2:
					player("Yes, it's a very nice day.");
					stage = 20;
					break;
				}
				break;
			case 10:
				npc("I'm surprised if you're anyone's friend with those kind", "of manners.");
				stage = 11;
				break;
			case 11:
				end();
				break;
			case 20:
				npc("Aye, may the gods walk by yer side. Now leave me", "alone.");
				stage = 21;
				break;
			case 21:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 747 };
	}
}