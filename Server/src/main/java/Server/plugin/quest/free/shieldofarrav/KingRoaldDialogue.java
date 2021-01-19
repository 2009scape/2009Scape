package plugin.quest.free.shieldofarrav;

import core.tools.Items;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import plugin.skill.Skills;

/**
 * Represents the dialogue used to handle king roald NPC.
 * @author 'Vexia
 * @version 1.0
 */
public class KingRoaldDialogue extends DialoguePlugin {

	/**
	 * Represents the certificate item.
	 */
	private static final Item CERTIFICATE = new Item(769);

	/**
	 * Constructs a new {@code KingRoaldDialogue} {@code Object}.
	 */
	public KingRoaldDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code KingRoaldDialogue} {@code Object}.
	 * @param player the player.
	 */
	public KingRoaldDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new KingRoaldDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if(player.getQuestRepository().getQuest("All Fired Up").getStage(player) == 90){
			player("Greetings, your majesty.");
			stage = 600;
			return true;
		}
		if(player.getAttribute("afu-mini:ring",false) || player.getAttribute("afu-mini:gloves",false) || player.getAttribute("afu-mini:adze",false)){
			player("Your Majesty! I did it!");
			stage = 700;
			return true;
		}
		switch (player.getQuestRepository().getQuest("Priest in Peril").getStage(player)) {
		case 0:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Greetings, your majesty.");
			stage = 0;
			break;
		case 100:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Greetings, your majesty.");
			if(player.getQuestRepository().getQuest("All Fired Up").isCompleted(player)){
				stage = 200;
			} else {
				stage = 499;
			}
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		if (player.getQuestRepository().getQuest("Shield of Arrav").getStage(player) == 70 && player.getInventory().containsItem(ShieldofArrav.PHOENIX_SHIELD) || player.getInventory().containsItem(ShieldofArrav.BLACKARM_SHIELD)) {
			switch (stage) {
			case 0:
				if (player.getInventory().containsItem(ShieldofArrav.PHOENIX_SHIELD) || player.getInventory().containsItem(ShieldofArrav.BLACKARM_SHIELD)) {
					player("Your majesty, I have recovered the Shield of Arrav; I", "would like to claim the reward.");
					stage = 1;
				}
				break;
			case 1:
				npc("The Shield of Arrav, eh? Yes, I do recall my father,", "King Roald, put a reward out for that.");
				stage = 2;
				break;
			case 2:
				npc("Very well.");
				stage = 3;
				break;
			case 3:
				npc("If you get the authenticity of the shield verified by the", "curator at the museum and then return here with", "authentication, I will grant your reward.");
				stage = 4;
				break;
			case 4:
				end();
				break;
			}
			return true;
		}
		if (player.getQuestRepository().getQuest("Shield of Arrav").getStage(player) == 70 && player.getInventory().containsItem(ShieldofArrav.BLACKARM_CERTIFICATE) || player.getInventory().containsItem(ShieldofArrav.PHOENIX_CERTIFICATE)) {
			switch (stage) {
			case 0:
				player("Your majesty, I have come to claim the reward for the", "return of the Shield of Arrav.");
				stage = 1;
				break;
			case 1:
				interpreter.sendItemMessage(player.getInventory().containsItem(ShieldofArrav.BLACKARM_CERTIFICATE) ? ShieldofArrav.BLACKARM_CERTIFICATE.getId() : ShieldofArrav.PHOENIX_CERTIFICATE.getId(), "You show the certificate to the king.");
				stage = 2;
				break;
			case 2:
				npc("I'm  afraid that's only half the reward certificate. You'll", "have to get the other half and join them together if you", "want to cliam the reward.");
				stage = 3;
				break;
			case 3:
				end();
				break;
			}
			return true;
		}
		if (player.getQuestRepository().getQuest("Shield of Arrav").getStage(player) == 70 && player.getInventory().containsItem(CERTIFICATE)) {
			switch (stage) {
			case 0:
				player("Your majesty, I have come to claim the reward for the", "return of the Shield of Arrav.");
				stage = 1;
				break;
			case 1:
				interpreter.sendItemMessage(CERTIFICATE.getId(), "You show the certificate to the king.");
				stage = 2;
				break;
			case 2:
				npc("My goodness! This claim is for the reward offered by", "my father many years ago!");
				stage = 3;
				break;
			case 3:
				npc("I never thought I would live to see the day when", "someone came forward to claim this reward!");
				stage = 4;
				break;
			case 4:
				npc("I heard that you found half the shield, so I will give", "you half of the bounty. That comes to exactly 600 gp!");
				stage = 5;
				break;
			case 5:
				interpreter.sendItemMessage(CERTIFICATE.getId(), "You hand over a certificate. The king gives you 600 gp.");
				stage = 6;
				break;
			case 6:
				if (player.getInventory().remove(CERTIFICATE)) {
					if (!player.getInventory().add(new Item(995, 600))) {
						GroundItemManager.create(new Item(995, 600), player);
					}
					player.getQuestRepository().getQuest("Shield of Arrav").finish(player);
					end();
				}
				break;
			}
			return true;
		}
		Quest quest = player.getQuestRepository().getQuest("Priest in Peril");
		switch (stage) {
			case 0:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well hello there. What do you want?");
				stage = 1;
				break;
			case 1:
				if (quest.getStage(player) == 0) {
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I am looking for a quest!");
					stage = 2;
					return true;
				}
				if (quest.getStage(player) <= 11) {
					interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You have news of Drezel for me?");
					stage = 20;
				}
				if (quest.getStage(player) == 12) {
					interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You have news of Drezel for me?");
					stage = 23;
				}
				if (quest.getStage(player) == 13) {
					interpreter.sendDialogues(npc, FacialExpression.FURIOUS, "AND MORE IMPORTANTLY, WHY HAVEN'T", "YOU ENSURED THE BORDER TO", "MORYTANIA IS SECURE YET?");
					stage = 40;
				}
				if (quest.getStage(player) == 14) {
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm looking for a key to unlock drezel!");
					stage = 50;
				}
				if (quest.getStage(player) == 15 || quest.getStage(player) == 16 || quest.getStage(player) == 17) {
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm helping Drezel!");
					stage = 50;
				}
				if (quest.getStage(player) == 100) {
					interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ah, it's you again. Hello there.");
					stage = 200;
				}
				break;
			case 200:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Do you have anything of importace to say?");
				stage = 201;
				break;
			case 201:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "...Not really.");
				stage = 202;
				break;
			case 202:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You will have to excuse me, then. I am very busy as I", "have a kingdom to run!");
				stage = 203;
				break;
			case 203:
				end();
				break;
			case 2:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "A quest you say? Hmm, what an odd request to make", "of the king. It's funny you should mention it though, as", "there is something you can do for me.");
				stage = 3;
				break;
			case 3:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Are you aware of the temple easy of here? It stands on", "the river Salve and guards the entrance to the lands of", "Morytania?");
				stage = 4;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, I don't think I know it...");
				stage = 5;
				break;
			case 5:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hmm, how strange that you don't. Well anyway, it has", "been some days since last I heard from Drezel, the", "priest who lives there.");
				stage = 6;
				break;
			case 6:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Be a sport and go make sure that nothing untoward", "has happend to the silly old codger for me, would you?");
				stage = 7;
				break;
			case 7:
				interpreter.sendOptions("Select an Option", "Sure.", "No, that sounds boring.");
				stage = 8;
				break;
			case 8:
				switch (buttonId) {
					case 1:
						quest.start(player);
						player.getQuestRepository().syncronizeTab(player);
						interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Sure. I don't have anything better to do right now.");
						stage = 10;
						break;
					case 2:
						interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yes, I dare say it does. I wouldn't even have", "mentioned it had you not seemed to be looking for", "something to do anyway.");
						stage = 9;
						break;
				}
				break;
			case 9:
				end();
				break;
			case 10:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Many thanks adventurer! I would have sent one of my", "squires but they wanted payment for it!");
				stage = 11;
				break;
			case 11:
				end();
				break;
			case 20:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No not yet.");
				stage = 21;
				break;
			case 21:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I would wish you would go check on my dear friend.");
				stage = 22;
				break;
			case 22:
				end();
				break;
			case 23:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yeah, I spoke to the guys at the temple and they said", "they were being bothered by that dog in the crypt, so I", "went and killed it for them. No problem.");
				stage = 24;
				break;
			case 24:
				interpreter.sendDialogues(npc, FacialExpression.FURIOUS, "YOU DID WHAT???");
				stage = 25;
				break;
			case 25:
				interpreter.sendDialogues(npc, FacialExpression.FURIOUS, "Are you mentally deficient??? That guard dog was", "protecting the route to Morytania! Without it we could", "be in severe peril of attack!");
				stage = 26;
				break;
			case 26:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Did I make a mistake?");
				stage = 27;
				break;
			case 27:
				interpreter.sendDialogues(npc, FacialExpression.FURIOUS, "YES YOU DID!!!!! You need to get there", "and find out what is happening! Before it is too late for", "us all!");
				stage = 28;
				break;
			case 28:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "B-but Drezel TOLD me to...!");
				stage = 29;
				break;
			case 29:
				interpreter.sendDialogues(npc, FacialExpression.FURIOUS, "No, you absolute cretin! Obviously some fiend has done", "something to Drezel and tricked your feeble intellect", "into helping them kill that guard dog!");
				stage = 30;
				break;
			case 30:
				interpreter.sendDialogues(npc, FacialExpression.FURIOUS, "You get back there and do whatever is necessary to", "safeguard my kingdom from attack, or I will see you", "beheaded for high treason!");
				stage = 31;
				break;
			case 31:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Y-yes your Highness.");
				stage = 32;
				break;
			case 32:
				quest.setStage(player, 13);
				end();
				break;
			case 40:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Okay, okay, I'm going, I'm going.... There's no need to", "shout...");
				stage = 41;
				break;
			case 41:
				interpreter.sendDialogues(npc, FacialExpression.FURIOUS, "NO NEED TO SHOUT???");
				stage = 42;
				break;
			case 42:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Listen, and listen well, and see if your puny mind can", "comprehend this; if the border is not protected, then we", "are at the mercy of the evil beings");
				stage = 43;
				break;
			case 43:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "that live in Morytania. Given the most of the", "inhabitants consider humans to be nothing more than", "over talkative snack foods, I would");
				stage = 44;
				break;
			case 44:
				interpreter.sendDialogues(npc, FacialExpression.FURIOUS, "say that me shouting at you for your incompetence is", "the LEAST of your worries right now NOW GO!");
				stage = 45;
				break;
			case 45:
				end();
				break;
			case 50:
				end();
				break;
			case 499:
				npc("Ah, it's you again. Hello there.");
				stage = 500;
				break;
			case 500:
				player("Hello, Your Majesty. I am happy to report that the", "situation at the Temple of Paterdomus has been sorted.", "Misthalin's borders should once again be fully protected", "against the threats of Morytania.");
				stage++;
				break;
			case 501:
				npc("Ah, thank you! The kingdom is forever in your", "debt.");
				stage++;
				break;
			case 502:
				player("In your debt? Does that mean you're going to give me", "fabulous rewards for my efforts?");
				stage++;
				break;
			case 503:
				npc("Of course not; however, if it's rewards you're after, it", "occurs to me that you could be of even more service to", "the kingdom...and this time, there's payment in it for", "you.");
				stage++;
				break;
			case 504:
				options("Oh, tell me more!", "Sorry, not interested.");
				stage++;
				break;
			case 505:
				switch (buttonId) {
					case 1:
						player("Oh, tell me more!");
						stage++;
						break;
					case 2:
						player("Sorry, not interested.");
						stage = 50;
						break;
				}
				break;
			case 506:
				npc("Well, you see, because of the mounting threats from", "Morytania and the Wilderness, the southern kingdoms", "have banded together to take action.");
				stage++;
				break;
			case 507:
				npc("We have constructed a network of beacons that stretch", "all the way from the source of the River Salve to the", "northwestern-most edge of the Wilderness.");
				stage++;
				break;
			case 508:
				npc("Should there be any threat from these uncivilized lands,", "we'll be able to spread the word as fast as the light of", "the flames can travel.");
				stage++;
				break;
			case 509:
				player("So how could I be of help?");
				stage++;
				break;
			case 510:
				npc("The task itself should be rather straightforward: I need", "you to help us test the network of beacons to make", "sure everything is in order, in case the worst", "should occur.");
				stage++;
				break;
			case 511:
				options("I'd be happy to help.", "I'm a bit busy right now.");
				stage++;
				break;
			case 512:
				switch (buttonId) {
					case 1:
						player("I'd be happy to help.");
						stage++;
						break;
					case 2:
						player("I'm a bit busy right now.");
						stage = 50;
						break;
				}
				break;
			case 513:
				if (player.getSkills().getLevel(Skills.FIREMAKING) < 43) {
					npc("I'd love for you to help, but you need", "to get better at lighting fires first.");
					stage = 50;
				} else {
					npc("Excellent! The kingdom of Misthalin is eternally", "grateful.");
					stage++;
				}
				break;
			case 514:
				player("So what do I need to do?");
				stage++;
				break;
			case 515:
				npc("Talk to the head fire tender, Blaze Sharpeye - he'll", "explain everything. He is stationed just south of the", "Temple of Paterdomus, on the cliffs by the River Salve.");
				stage++;
				break;
			case 516:
				player("Thank you, Your Majesty. I'll seek out Blaze", "right away.");
				stage = 50;
				player.getQuestRepository().getQuest("All Fired Up").start(player);
				break;
			case 600:
				player("I'm happy to report that the beacon network seems to", "be working as expected.");
				stage++;
				break;
			case 601:
				npc("Excellent! I'm delighted to hear it.");
				stage++;
				break;
			case 602:
				player("So, about that reward you promised?");
				stage++;
				break;
			case 603:
				npc("What happened to the days when adventurers felt", "rewarded in full by the knowledge of a job well done?");
				stage++;
				break;
			case 604:
				player("Well before my time, I'm afraid.");
				stage++;
				break;
			case 605:
				npc("Hmph. Well, I suppose a king must stick to his word.", "Mind you, let me stress how grateful we are - and how", "grateful we'd be if you could continue helping us test", "the beacons.");
				stage++;
				break;
			case 606:
				npc("There is much more to be done and this is but a", "pittance compared to what I'm willing to offer for", "further assistance!");
				stage++;
				break;
			case 607:
				end();
				player.getQuestRepository().getQuest("All Fired Up").finish(player);
				break;
			case 700:
				npc("Did what?");
				stage++;
				break;
			case 701:
				if (player.getAttribute("afu-mini:adze", false)) {
					player("I lit all 14 beacons at once!");
				} else if (player.getAttribute("afu-mini:gloves", false)) {
					player("I lit 10 beacons at once!");
				} else if (player.getAttribute("afu-mini:ring", false)) {
					player("I lit 6 beacons at once!");
				}
				stage++;
				break;
			case 702:
				npc("Oh, wonderful! Here is your reward then.");
				if (player.getAttribute("afu-mini:adze", false))
					if (player.getInventory().add(new Item(Items.INFERNO_ADZE_13661)))
						player.removeAttribute("afu-mini:adze");
				if (player.getAttribute("afu-mini:gloves", false))
					if (player.getInventory().add(new Item(Items.FLAME_GLOVES_13660)))
						player.removeAttribute("afu-mini:gloves");
				if (player.getAttribute("afu-mini:ring", false))
					if (player.getInventory().add(new Item(Items.RING_OF_FIRE_13659)))
						player.removeAttribute("afu-mini:ring");
				stage++;
				break;
			case 703:
				end();
				break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 648, 2590, 5838 };
	}
}