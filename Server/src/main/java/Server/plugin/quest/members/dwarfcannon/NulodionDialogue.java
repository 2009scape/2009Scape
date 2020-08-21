package plugin.quest.members.dwarfcannon;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;

/**
 * Handles captains lawgof dialogue.
 * @author Vexia
 */
public class NulodionDialogue extends DialoguePlugin {

	/**
	 * The cannon pieces.
	 */
	private static final Item[] CANNON_PIECES = new Item[] { new Item(6), new Item(8), new Item(10), new Item(12) };

	/**
	 * The quest.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@Code NulodionDialogue} {@Code Object}
	 */
	public NulodionDialogue() {
		/**
		 * empty
		 */
	}

	/**
	 * Constructs a new {@Code NulodionDialogue} {@Code Object}
	 * @param player the player.
	 */
	public NulodionDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new NulodionDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest(DwarfCannon.NAME);
		switch (quest.getStage(player)) {
		case 70:
			player("Hello there.");
			break;
		default:
			player("Hello again.");
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 70:
			switch (stage) {
			case 0:
				npc("Can I help you?");
				stage++;
				break;
			case 1:
				player("Captain Lawgof sent me. He's having trouble with his", "cannon.");
				stage++;
				break;
			case 2:
				npc("Of course, we forgot to send the ammo mould!");
				stage++;
				break;
			case 3:
				player("It fires a mould?");
				stage++;
				break;
			case 4:
				npc("Don't be silly - the ammo's made by using a mould.", "Here, take these to him. The instructions explain", "everything.");
				stage++;
				break;
			case 5:
				player("That's great, thanks.");
				stage++;
				break;
			case 6:
				npc("Thank you, adventurer. The Dwarf Black Guard will", "remember this.");
				stage++;
				break;
			case 7:
				player.getConfigManager().set(0, 10);
				quest.setStage(player, 80);
				player.sendMessage("The Cannon Engineer gives you some notes and a mould.");
				player.getInventory().add(DwarfCannon.NULODION_NOTES, player);
				player.getInventory().add(DwarfCannon.MOULD, player);
				end();
				break;
			}
			break;
		case 80:
			switch (stage) {
			case 0:
				if (!player.hasItem(DwarfCannon.NULODION_NOTES)) {
					player("I've lost the notes.");
					stage = 100;
					break;
				}
				if (!player.hasItem(DwarfCannon.MOULD)) {
					player("I've lost the cannonball mould.");
					stage = 102;
					break;
				}
				npc("So has the Captain figured out how to work the cannon", "yet?");
				stage++;
				break;
			case 1:
				player("Not yet, but I'm sure he will.");
				stage++;
				break;
			case 2:
				npc("If you can get those items to him it'll be a great help.");
				stage++;
				break;
			case 3:
				end();
				break;
			case 100:
				npc("Here, take these...");
				stage++;
				break;
			case 101:
				player.sendMessage("The Cannon Engineer gives you some more notes.");
				player.getInventory().add(DwarfCannon.NULODION_NOTES, player);
				end();
				break;
			case 102:
				npc("Deary me, you are trouble. Here, take this one.");
				stage++;
				break;
			case 103:
				player.sendMessage("The Cannon Engineer gives you another mould.");
				player.getInventory().add(DwarfCannon.MOULD, player);
				end();
				break;
			}
			break;
		default:
			switch (stage) {
			case 0:
				npc("Hello traveller, how's things?");
				stage++;
				break;
			case 1:
				player("Not bad thanks, yourself?");
				stage++;
				break;
			case 2:
				npc("I'm good, just working hard as usual...");
				stage++;
				break;
			case 3:
				options("I was hoping you might sell a cannon?", "Well, take care of yourself then.", "I want to know more about the cannon.", "I've lost my cannon.");
				stage++;
				break;
			case 4:
				switch (buttonId) {
				case 1:
					player("I was hoping you might sell me a cannon?");
					stage = 10;
					break;
				case 2:
					player("Well, take care of yourself then.");
					stage = 20;
					break;
				case 3:
					player("I want to know more about the cannon.");
					stage = 30;
					break;
				case 4:
					player("I've lost my cannon.");
					stage = 40;
					break;
				}
				break;
			case 10:
				npc("Hmmmmmmm...");
				stage++;
				break;
			case 11:
				npc("I shouldn't really, but as you helped us so much, well, I", "could sort something out. I'll warn you though, they", "don't come cheap!");
				stage++;
				break;
			case 12:
				player("How much?");
				stage++;
				break;
			case 13:
				npc("For the full setup, 750,000 coins. Or I can sell you", "the seperate parts... but it'll cost extra!");
				stage++;
				break;
			case 14:
				player("That's not cheap!");
				stage++;
				break;
			case 15:
				options("Okay, I'll take a cannon please.", "Can I look at the seperate parts please?", "Sorry, that's too much for me.", "Have you any ammo or instructions to sell?");
				stage++;
				break;
			case 16:
				switch (buttonId) {
				case 1:
					player("Okay, I'll take a cannon please.");
					stage = 110;
					break;
				case 2:
					player("Can I look at the seperate parts please?");
					stage = 120;
					break;
				case 3:
					player("Sorry, that's too much for me.");
					stage = 130;
					break;
				case 4:
					player("Have you any ammo or instructions to sell?");
					stage = 140;
					break;
				}
				break;
			case 20:
				npc("Indeed I will adventurer.");
				stage++;
				break;
			case 21:
				end();
				break;
			case 30:
				npc("There's only so much I can tell you, adventurer.", "We've been working on this little beauty for some time", "now.");
				stage++;
				break;
			case 31:
				player("Is it effective?");
				stage++;
				break;
			case 32:
				npc("In short bursts it's very effective, the most destructive", "weapon to date. The cannon automatically targets", "monsters close by. You just have to make the ammo", "and let rip.");
				stage++;
				break;
			case 33:
				end();
				break;
			case 40:
				npc("That's unfortunate... but don't worry, I can sort you", "out.");
				stage++;
				break;
			case 41:
				if (player.getSavedData().getActivityData().isLostCannon()) {
					npc("There you go, take better care next time.");
					stage = 43;
					break;
				}
				npc("Oh dear, I'm only allowed to replace cannons that were", "stolen in reward. I'm sorry, but you'll have to buy a", "new set.");
				stage++;
				break;
			case 43:
				player.getSavedData().getActivityData().setLostCannon(false);
				for (Item i : CANNON_PIECES) {
					player.getInventory().add(i, player);
				}
				end();
				break;
			case 42:
				end();
				break;
			case 110:
				npc("Okay then, but keep it quiet... This thing's top secret!");
				stage++;
				break;
			case 111:
				if (!player.getInventory().contains(995, 750000)) {
					player("Oops, I don't have enough money.");
					stage++;
					break;
				}
				if (player.getInventory().remove(new Item(995, 750000))) {
					for (Item i : CANNON_PIECES) {
						player.getInventory().add(i, player);
					}
				}
				npc("There you go adventurer, I hope you enjoy this", "fine work of craftsmanship.");
				stage = 113;
				break;
			case 112:
				npc("Sorry, I can't go any lower than that.");
				stage++;
				break;
			case 113:
				end();
				break;
			case 120:
				npc("Of course!");
				stage++;
				break;
			case 121:
				end();
				npc.openShop(player);
				break;
			case 130:
				npc("Fair enough, it's too much for most of us.");
				stage++;
				break;
			case 131:
				end();
				break;
			case 140:
				npc("Of course!");
				stage = 121;
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 209 };
	}

}
