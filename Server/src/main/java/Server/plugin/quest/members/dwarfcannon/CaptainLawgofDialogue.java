package plugin.quest.members.dwarfcannon;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;

/**
 * Handles captains lawgof dialogue.
 * @author Vexia
 */
public class CaptainLawgofDialogue extends DialoguePlugin {

	/**
	 * The quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@Code CaptainLawgofDialogue} {@Code
	 * Object}
	 */
	public CaptainLawgofDialogue() {
		/**
		 * empty
		 */
	}

	/**
	 * Constructs a new {@Code CaptainLawgofDialogue} {@Code
	 * Object}
	 * @param player the player.
	 */
	public CaptainLawgofDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CaptainLawgofDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		quest = player.getQuestRepository().getQuest(DwarfCannon.NAME);
		switch (quest.getStage(player)) {
		case 80:
			player("Hi.");
			break;
		case 50:
			npc("How are you doing in there, trooper? We've been", "trying our best with that thing, but I just haven't got", "the patience.");
			break;
		case 40:
			player("Hello, has Lollk returned yet?");
			break;
		default:
			player("Hello.");
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 0:
			switch (stage) {
			case 0:
				npc("Guthix be praised, the cavalry has arrived! Hero, how", "would you like to be made an honorary member of the", "Black Guard?");
				stage++;
				break;
			case 1:
				player("The Black Guard, what's that?");
				stage++;
				break;
			case 2:
				npc("Hawhaw! 'What's that' " + (player.getAppearance().isMale() ? "he" : "she") + " asks, what a sense of", "humour! The Black Guard is the finest regiment in the", "dwarven army. Only the best of the best are allowed to", "join it and then they receive months of rigorous");
				stage++;
				break;
			case 3:
				npc("training. However, we are currently in need of a hero,", "so for a limited time only I'm offering you, a human, a", "chance to join this prestigious regiment. What do you", "say?");
				stage++;
				break;
			case 4:
				player("Sure, I'd be honoured to join.");
				stage++;
				break;
			case 5:
				quest.start(player);
				npc("That's the spirit! Now trooper, we have no time to waste", "- the goblins are attacking from the forests to the", "South. There are so many of them, they are", "overwhelming my men and breaking through our");
				stage = 100;
				break;
			}
			break;
		case 10:
			switch (stage) {
			case 0:
				if (player.getConfigManager().get(1) == 2016) {
					npc("Well done, trooper! The goblins seems to have stopped", "getting in. I think you've done the job!");
					stage = 105;
					break;
				}
				npc("Hello, trooper, how are you doing with those railings?");
				stage++;
				break;
			case 1:
				player("I'm getting there.");
				stage++;
				break;
			case 2:
				npc("The goblins are still getting in, so there must still be", "some broken railings.");
				stage++;
				break;
			case 3:
				if (!player.hasItem(new Item(14))) {
					player("But I'm out of railings...");
					stage = 5;
					break;
				}
				player("Don't worry, I'll find them soon enough.");
				stage++;
				break;
			case 4:
				end();
				break;
			case 5:
				npc("That's okay, we've got plenty, here you go.");
				stage++;
				break;
			case 6:
				player.getPacketDispatch().sendMessage("The Dwarf Captain gives you another railing.");
				player.getInventory().add(new Item(14), player);
				end();
				break;
			case 100:
				npc("perimeter defences; could you please try to fix the", "stockade by replacing the broken rails with these new", "ones?");
				stage++;
				break;
			case 101:
				player("Sure, sounds easy enough...");
				stage++;
				break;
			case 102:
				player.getInventory().add(new Item(14, 6), player);
				player.getDialogueInterpreter().sendDialogue("The Dwarf Captain gives you six railings.");
				stage++;
				break;
			case 103:
				npc("Report back to me once you've fixed the railings.");
				stage++;
				break;
			case 104:
				player("Yes Sir, Captain!");
				end();
				break;
			case 105:
				player("Great, I'll be getting on then.");
				stage++;
				break;
			case 106:
				npc("What? I'll have you jailed for desertion!");
				stage++;
				break;
			case 107:
				npc("Besides, I have another commision for you. Just", "before the goblins over-ran us we lost contact with our", "watch tower to the South, that's why the goblins", "managed to catch us unawares. I'd like you to perform.");
				stage++;
				break;
			case 108:
				npc("a covert operation into enemy territory, to check up on", "the guards we have stationed there.");
				stage++;
				break;
			case 109:
				npc("They should have reported in by now ...");
				stage++;
				break;
			case 110:
				player("Okay, I'll see what I can find out.");
				stage++;
				break;
			case 111:
				npc("Excellent! I have two men there, the dwarf-in-charge is", "called Gilob, find him and tell him that I'll send him a", "relief guard just as soon as we mop up these remaining", "goblins.");
				stage++;
				break;
			case 112:
				player.getConfigManager().set(0, 3, true);
				quest.setStage(player, 20);
				end();
				break;
			}
			break;
		case 20:
			switch (stage) {
			case 0:
				if (player.getInventory().containsItem(DwarfCannon.DWARF_REMAINS)) {
					npc("Have you been to the watch tower yet?");
					stage = 4;
					break;
				}
				npc("Hello, any news from the watchman?");
				stage++;
				break;
			case 1:
				player("Not yet.");
				stage++;
				break;
			case 2:
				npc("Well, as quick as you can then.");
				stage++;
				break;
			case 3:
				end();
				break;
			case 4:
				player("I have some terrible news for you Captain, the goblins", "over ran the tower, your guards fought well but were", "overwhelemd.");
				stage++;
				break;
			case 5:
				interpreter.sendItemMessage(DwarfCannon.DWARF_REMAINS, "You give the Dwarf Captain his subordinate's remains...");
				stage++;
				break;
			case 6:
				npc("I can't believe it, Gilob was the finest lieutenant I had!", "We'll give him a fitting funeral, but what of his", "command? His son, Lollk, was with him. Did you find", "his body too?");
				stage++;
				break;
			case 7:
				player("No, there was only one body there, I searched pretty", "well.");
				stage++;
				break;
			case 8:
				npc("The goblins must have taken him. Please traveller, seek", "out the goblin's hideout and return the lad to us. They", "always attack from the South-weast, so they must be", "based down there.");
				stage++;
				break;
			case 9:
				player("Okay, I'll see if I can find their hideout.");
				stage++;
				break;
			case 10:
				end();
				player.getConfigManager().set(0, 5, true);
				player.getInventory().remove(DwarfCannon.DWARF_REMAINS);
				quest.setStage(player, 30);
				break;
			}
			break;
		case 30:
			switch (stage) {
			case 0:
				npc("Trooper, have you managed to find the goblins' base?");
				stage++;
				break;
			case 1:
				player("Not yet I'm afraid, but I'll keep looking...");
				stage++;
				break;
			case 2:
				end();
				break;
			}
			break;
		case 40:
			switch (stage) {
			case 0:
				npc("He has, and I thank you from the bottom of my heart", "- without you he'd be a goblin barbecue!");
				stage++;
				break;
			case 1:
				player("Always a please to help.");
				stage++;
				break;
			case 2:
				npc("In that case could I ask one more favour of you...");
				stage++;
				break;
			case 3:
				npc("When the golins attacked us some of them managed to", "slip past my guards and sabotage our cannon. I don't", "have anybody who understands how it works, could you,", "have a look at it and see if you could get it working for");
				stage++;
				break;
			case 4:
				npc("us, please?");
				stage++;
				break;
			case 5:
				player("Okay, I'll see what I can do.");
				stage++;
				break;
			case 6:
				npc("Thank you, take this toolkit, you'll need it...");
				stage++;
				break;
			case 7:
				npc("Report back to me if you manage to fix it.");
				stage++;
				break;
			case 8:
				player.getInventory().add(DwarfCannon.TOOL_KIT, player);
				player.getConfigManager().set(0, 7);
				quest.setStage(player, 50);
				end();
				break;
			}
			break;
		case 50:
			switch (stage) {
			case 0:
				player("It's not an easy job, but I'm getting there.");
				stage++;
				break;
			case 1:
				if (!player.hasItem(DwarfCannon.TOOL_KIT)) {
					player("I'm afraid I lost the toolkit...");
					stage = 3;
					break;
				}
				npc("Good stuff, let me know if you have any luck. If we", "manage to get that thing working, those goblins will be", "no trouble at all.");
				stage++;
				break;
			case 2:
				end();
				break;
			case 3:
				npc("That was silly... never mind, here you go.");
				stage++;
				break;
			case 4:
				end();
				player.sendMessage("The Dwarf Captain gives you another toolkit.");
				player.getInventory().add(DwarfCannon.TOOL_KIT, player);
				break;
			}
			break;
		case 60:
			switch (stage) {
			case -1:
				end();
				break;
			case 0:
				npc("Hello there trooper, how's things?");
				stage++;
				break;
			case 1:
				player("Well, I think I've done it, take a look...");
				stage++;
				break;
			case 2:
				if (!player.getInventory().contains(1, 1)) {
					npc("Bring me back the toolkit please.");
					stage = -1;
					break;
				}
				npc("That's fantastic, well done!");
				stage++;
				break;
			case 3:
				npc("Well I don't believe it, it seems to be working perfectly!", "I seem to have underestimated you, trooper!");
				stage++;
				break;
			case 4:
				player("Not bad for an adventurer eh?");
				stage++;
				break;
			case 5:
				npc("Not bad at all, your effort is appreciated, my friend.", "Now, if I could figure what the thing uses as ammo...");
				stage++;
				break;
			case 6:
				npc("The Black Guard forgot to send instructions. I know I", "said that was the last favour, but...");
				stage++;
				break;
			case 7:
				player("What now?");
				stage++;
				break;
			case 8:
				npc("I can't leave this post, could you go to the Black Guard", "base and find out what this thing actually shoots?");
				stage++;
				break;
			case 9:
				player("Okay then, just for you!");
				stage++;
				break;
			case 10:
				npc("That's great, we were lucky you came along when you", "did. The base is located just South of the Ice Mountain.");
				stage++;
				break;
			case 11:
				npc("You'll need to speak to Nulodion, the Dwarf Cannon", "engineer. He's the Weapons Development Chief for the", "Black Guard, so if anyone knows how to fire this thing,", "it'll be him.");
				stage++;
				break;
			case 12:
				player("Okay, I'll see what I can do.");
				stage++;
				break;
			case 13:
				player.getInventory().remove(DwarfCannon.TOOL_KIT);
				player.sendMessage("You giv the toolkit back to Captain Lawgof.");
				player.getConfigManager().set(0, 9, true);
				quest.setStage(player, 70);
				end();
				break;
			}
			break;
		case 80:
			switch (stage) {
			case 0:
				npc("Hello trooper, any word from the Cannon Engineer?");
				stage++;
				break;
			case 1:
				player("Yes, I have spoken to him.");
				stage++;
				break;
			case 2:
				if (!player.getInventory().containsItem(DwarfCannon.MOULD) || !player.getInventory().containsItem(DwarfCannon.NULODION_NOTES)) {
					player("He gave me some items to give you... but I seem to", "have lost something.");
					stage++;
				} else {
					player("He gave me an ammo mould and some notes to give to", "you...");
					stage = 6;
				}
				break;
			case 3:
				npc("If you could go back and get another, I'd appreciate it.");
				stage++;
				break;
			case 4:
				player("Oh, okay then.");
				stage++;
				break;
			case 5:
				end();
				break;
			case 6:
				npc("Aah, of course, we make the ammo! This is great, now", "we will be able to defend ourselves. I don't know how to", "thank you...");
				stage++;
				break;
			case 7:
				player("You could give me a cannon...");
				stage++;
				break;
			case 8:
				npc("Hah! You'd be lucky, those things are worth a fortune.");
				stage++;
				break;
			case 9:
				npc("I'll tell you what, I'll write to the Cannon", "Engineer requesting him to sell you one. He controls", "production of the cannons.");
				stage++;
				break;
			case 10:
				npc("He won't be able to give you one, but for the right", "price, I'm sure he'll sell one to you.");
				stage++;
				break;
			case 11:
				player("Hmmm... sounds interesting, I might take you up on", "that.");
				stage++;
				break;
			case 12:
				quest.finish(player);
				end();
				break;
			}
			break;
		case 100:
			switch (stage) {
			case 0:
				npc("Well hello there, how you doing?");
				stage++;
				break;
			case 1:
				player("Not bad, yourself?");
				stage++;
				break;
			case 2:
				npc("I'm great now, those goblins can't get close with this", "cannon blasting at them!");
				stage++;
				break;
			case 3:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 208 };
	}

}
