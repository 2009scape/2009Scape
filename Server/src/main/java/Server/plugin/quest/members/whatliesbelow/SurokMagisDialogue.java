package plugin.quest.members.whatliesbelow;

import plugin.activity.ActivityManager;
import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.world.update.flag.context.Animation;

/**
 * Handles the surok magis dialogue.
 * @author Vexia
 */
public class SurokMagisDialogue extends DialoguePlugin {

	/**
	 * The quest instance.
	 */
	private Quest quest;

	/**
	 * The cutscene.
	 */
	private WLBelowCutscene cutscene;

	/**
	 * Constructs a new {@Code SurokMagisDialogue} {@Code Object}
	 */
	public SurokMagisDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@Code SurokMagisDialogue} {@Code Object}
	 * @param player the player.
	 */
	public SurokMagisDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new SurokMagisDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest(WhatLiesBelow.NAME);
		switch (quest.getStage(player)) {
		default:
			npc("Excuse me?");
			break;
		case 20:
			player("Hello.");
			break;
		case 30:
		case 40:
			npc("Ah! You're back. Have you found the things I need yet?");
			break;
		case 50:
			if (!player.hasItem(WhatLiesBelow.SUROKS_LETTER)) {
				player("That letter was treasonous so I destroyed it!");
				stage = 10;
				break;
			}
			player("This letter is treasonous! I'm going to report you to the", "king!");
			break;
		case 60:
			npc("Hi, " + player.getUsername() + "!");
			break;
		case 70:
			if (!player.getInventory().containsItem(WhatLiesBelow.BEACON_RING) && !player.getEquipment().containsItem(WhatLiesBelow.BEACON_RING)) {
				player("I should probably get my beacon ring first...");
				stage = -1;
				break;
			}
			if (args.length >= 2) {
				cutscene = player.getAttribute("cutscene", null);
				if (args.length == 3) {
					player.getDialogueInterpreter().sendDialogues(cutscene.getZaff(), null, "Your teleport spell has been corrupted, Surok! I have", "placed a magic block on this room. You will remain here,", "under guard, in the library from now on.");
					stage = 12;
					return true;
				}
				interpreter.sendPlainMessage(true, "The room grows dark and you sense objects moving...");
				stage = 10;
				break;
			}
			player("Surok!! Your plans have been uncovered! You are hereby", "under arrest on the authority of the Varrock Palace", "Secret Guard!");
			break;
		case 80:
		case 90:
		case 100:
			npc("You have foiled my plans, " + player.getUsername() + "... I obviously", "underestimated you.");
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		default:
			switch (stage) {
			case 0:
				player("What do you want? ...Oh, wait. I know! You're", "porbably just like all the others, aren't you? After some", "fancy spell or potion from me, I bet!");
				stage = 1;
				break;
			case 1:
				npc("No! atleast, I don't think so. What sort of spells", "do you have?");
				stage = 2;
				break;
			case 2:
				player("Hah! I knew it! I expect you want my Aphro-Dizzy-", "Yak spell! Want someone to fall madly in love with you,", "eh?");
				stage = 3;
				break;
			case 3:
				npc("That spell sounds very interesting, but I didn't mean to", "disturb you!");
				stage = 4;
				break;
			case 4:
				player("Well, I see that you do have some manners. I'm glad", "to see that you use them.");
				stage = 5;
				break;
			case 5:
				player("Now, if it's all the same, I am very bust at the", "moment. Come back another time", "please and thank you.");
				stage = 6;
				break;
			case 6:
				npc("Yes, ofcourse!");
				stage = 7;
				break;
			case 7:
				end();
				break;
			}
			break;
		case 20:
			switch (stage) {
			case 0:
				npc("Hah! Come for my Aphro-Dizzy-Yak spell! Want", "someone to fall madly in love with you eh? Not", "surprised with a face like that, to be honest!");
				stage++;
				break;
			case 1:
				player("I didn't come here to be insulted!");
				stage++;
				break;
			case 2:
				npc("Really? Well, with ears like that, you do surprise me!");
				stage++;
				break;
			case 3:
				if (!player.getInventory().containsItem(WhatLiesBelow.RATS_LETTER)) {
					player("Nevermind!");
					stage = 99;
					break;
				}
				player("No, look. I have a letter for you.");
				stage++;
				break;
			case 4:
				npc("Really? Well then, let me see it!");
				stage++;
				break;
			case 5:
				player("Here it is!");
				stage++;
				break;
			case 6:
				npc.animate(Animation.create(6096));
				npc("Of all the luck!");
				stage++;
				break;
			case 7:
				player("Why did you destroy the letter?");
				stage++;
				break;
			case 8:
				npc("None of your business! It's a secret!");
				stage++;
				break;
			case 9:
				player("Yes, there seems to be a lot of them going around at", "the moment.");
				stage++;
				break;
			case 10:
				npc("Of course. Hmmm. However, I could let you in on", "another secret, if you like?");
				stage++;
				break;
			case 11:
				player("Go on, then!");
				stage++;
				break;
			case 12:
				npc("My secret is this. I have been spending time here in", "the palace library trying to discover some ancient spells", "and magics.");
				stage++;
				break;
			case 13:
				npc("In my research, I have uncovered the most astounding", "spell that will allow me to transform simple clay into solid", "gold bars!");
				stage++;
				break;
			case 14:
				npc("Now I am ready to use the spell to create all the gold", "I...uh...the city wants. I would gladly share this gold", "with you; I simply need a few more things.");
				stage++;
				break;
			case 15:
				player("Okay, what do you need?");
				stage++;
				break;
			case 16:
				npc("I will only need a couple of items. The first is very", "simple: an ordinary bowl to use as a castign vessel.");
				stage++;
				break;
			case 17:
				npc("You should be able to find one of these at any local", "store here in Varrock. I would go myself but I", "am...uh...busy with my research.");
				stage++;
				break;
			case 18:
				npc("The other item is much harder. I need a metal wand", "infused with chaos magic.");
				stage++;
				break;
			case 19:
				player("How would I get something like that?");
				stage++;
				break;
			case 20:
				npc("Take this metal wand. You will also need 15 chaos", "runes. When you get to the Chaos Altar, use the wand", "on the altar itself. This would infuse the runes into the", "wand.");
				stage++;
				break;
			case 21:
				player("How on earth do you know about Runecrafting? I", "thought onyly a few people knew of it.");
				stage++;
				break;
			case 22:
				npc("Hah! Don't presume to think that those wizards in their", "fancy towers are the only people to have heard of", "Runecrafting! Now pay attention!");
				stage++;
				break;
			case 23:
				npc("You will need to have the 15 chaos runes in your", "inventory. Make sure you also have either a chaos", "talisman or chaos tiara to complete the infusion.");
				stage++;
				break;
			case 24:
				player("Where can I get a talisman or a tiara?");
				stage++;
				break;
			case 25:
				npc("I'm afraid I don't know. You will need to research for", "one.");
				stage++;
				break;
			case 26:
				npc("Bring the infused wand and a bowl back to me and I", "will make us both rich!");
				stage++;
				break;
			case 27:
				npc("One more thing. I have uncovered information here in", "the library which may be of use to you. It tells of a", "safe route to the Chaos Altar that avoids the Wilderness.");
				stage++;
				break;
			case 28:
				player("Great! What is it?");
				stage++;
				break;
			case 29:
				npc("It is an old tome...a history book of sorts. It's", "somewhere here in the library. I forget where I left it,", "but it should be easy enough for you to find.");
				stage++;
				break;
			case 30:
				npc("I have also given you a copy of a diary", "I...uh...acquired. It may also help you to find that which", "you seek.");
				stage++;
				break;
			case 31:
				player.getInventory().remove(WhatLiesBelow.RATS_LETTER);
				player.getInventory().add(WhatLiesBelow.WAND, player);
				player.getInventory().add(WhatLiesBelow.SIN_KETH_DIARY, player);
				quest.setStage(player, 30);
				end();
				break;
			case 99:
				end();
				break;
			}
			break;
		case 30:
		case 40:
			switch (stage) {
			case 0:
				if (player.getInventory().containsItem(WhatLiesBelow.INFUSED_WAND)) {
					if (!player.getInventory().containsItem(WhatLiesBelow.BOWL)) {
						player("No, I still need to get you a bowl.");
						stage = 1;
						break;
					}
					player("I have the things you wanted.");
					stage = 6;
					break;
				}
				if (!player.hasItem(WhatLiesBelow.WAND)) {
					player("I lost the wand!");
					stage = 2;
					break;
				}
				if (!player.hasItem(WhatLiesBelow.SIN_KETH_DIARY)) {
					player("I lost the diary!");
					stage = 4;
					break;
				}
				player("No not yet.");
				stage++;
				break;
			case 1:
				end();
				break;
			case 2:
				npc("Somehow, I knew that would happen so I have made a", "few spares for just such an occasion!");
				stage++;
				break;
			case 3:
				npc("Here you are. Try not to lose this one!");
				player.getInventory().add(WhatLiesBelow.WAND, player);
				stage = 1;
				break;
			case 4:
				player.getInventory().add(WhatLiesBelow.SIN_KETH_DIARY, player);
				npc("Here you are, Try not to lose this one!");
				stage = 5;
				break;
			case 5:
				end();
				break;
			case 6:
				npc("Excellent! Well done! I knew that you would not let", "me down.");
				stage++;
				break;
			case 7:
				player("So...about this gold that you're going to", "give me?");
				stage++;
				break;
			case 8:
				npc("All in good time. I must prepare the spell first", ", and that will take a little time. While I am", "doing that, please take this letter to Rat, the trader", "outside the city who sent you here.");
				stage++;
				break;
			case 9:
				player("Okay, but I'll be back for my gold.");
				stage++;
				break;
			case 10:
				npc("Yes, yes, yes. Now off you go!");
				stage++;
				break;
			case 11:
				quest.setStage(player, 50);
				player.getInventory().add(WhatLiesBelow.SUROKS_LETTER, player);
				end();
				break;
			}
			break;
		case 50:
			switch (stage) {
			case 0:
				npc("Hah! Have fun with that!");
				stage++;
				break;
			case 1:
				end();
				break;
			case 10:
				npc("Really? You destroyed it?");
				stage++;
				break;
			case 11:
				player("Yes!");
				stage++;
				break;
			case 12:
				npc("You want another one, don't you?");
				stage++;
				break;
			case 13:
				player("Ah..uh..yes..yes, I do.");
				stage++;
				break;
			case 14:
				player.getInventory().add(WhatLiesBelow.SUROKS_LETTER, player);
				npc("Fine. Here you are. And stop all this complaining; it's", "getting me down!");
				stage++;
				break;
			case 15:
				end();
				break;
			}
			break;
		case 60:
			switch (stage) {
			case 0:
				player("You are a bad man!");
				stage++;
				break;
			case 1:
				end();
				break;
			}
			break;
		case 70:
			switch (stage) {
			case -1:
				end();
				break;
			case 0:
				npc("So! You're with the Secret Guard, eh? I should have", "known! I knew you had ugly ears from the start...", "and your nose is too short!");
				stage++;
				break;
			case 1:
				player("Give yourself up, Surok!");
				stage++;
				break;
			case 2:
				npc("Never! I am Surok Magis, descendant of the High Elder", "Sin'keth Magis, rightful heir of the Dagon'hai Order! I will", "have my revenge on those who destroyed my people!");
				stage++;
				break;
			case 3:
				player("The place is surrounded. There is nowhere to run!");
				stage++;
				break;
			case 4:
				npc("Do you really wish to die so readily? Are you prepared", "to face your death?");
				stage++;
				break;
			case 5:
				player("Bring it on!");
				stage++;
				break;
			case 6:
				npc("I am Dagon'hai! I run from nothing. My spell has", "been completed and it is time for you to meet your", "end, " + player.getUsername() + "! The king is now under my control!");
				stage++;
				break;
			case 7:
				end();
				ActivityManager.start(player, "What Lies below", false);
				break;
			case 10:
				close();
				player.unlock();
				cutscene.getKing().unlock();
				cutscene.reset();
				player.getInterfaceManager().restoreTabs();
				cutscene.getKing().getProperties().getCombatPulse().attack(player);
				stage = 11;
				break;
			case 11:
				close();
				stage = 12;
				break;
			case 12:
				player.lock();
				npc("No! All is lost! I must escape!");
				stage++;
				break;
			case 13:
				interpreter.sendDialogues(cutscene.getZaff(), null, "You will not escape justice this time, Surok!");
				stage = 14;
				break;
			case 14:
				npc("No! My plans have been ruined! I was so close to", "success!");
				stage = 16;
				break;
			case 16:
				interpreter.sendDialogues(cutscene.getZaff(), null, "Thank you for your help, " + player.getUsername() + ". I will put the", "room back in order and then I must leave. Surok is", "defeated and will be no more trouble for us. We will", "guard him more closely from now on!");
				stage++;
				break;
			case 17:
				quest.setStage(player, 80);
				interpreter.sendPlainMessage(true, "The room grows dark and you sense objects moving...");
				cutscene.stop(true);
				break;
			}
			break;
		case 80:
		case 90:
		case 100:
			switch (stage) {
			case 0:
				player("Yes. Let this be a lesson to you.");
				stage++;
				break;
			case 1:
				npc("...");
				stage++;
				break;
			case 2:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public void end() {
		if (cutscene != null) {
			return;
		}
		super.end();
	}

	@Override
	public int[] getIds() {
		return new int[] { 5834, 5835 };
	}

	/**
	 * Gets the cutscene.
	 * @return the cutscene
	 */
	public WLBelowCutscene getCutscene() {
		return cutscene;
	}

	/**
	 * Sets the bacutscene.
	 * @param cutscene the cutscene to set.
	 */
	public void setCutscene(WLBelowCutscene cutscene) {
		this.cutscene = cutscene;
	}

}
