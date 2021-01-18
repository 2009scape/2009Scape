package plugin.quest.free.therestlessghost;

import core.game.component.Component;
import core.game.container.access.BitregisterAssembler;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.link.diary.DiaryType;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.Player;
import plugin.dialogue.DialoguePlugin;
import plugin.quest.free.therestlessghost.RestlessGhost;


/**
 * Represents the father aereck dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class FatherAereckDialogue extends DialoguePlugin {

    /**
     * Constructs a new {@code FatherAereckDialogue} {@code Object}.
     */
    public FatherAereckDialogue() {
        /**
         * empty.
         */
    }

    /**
     * Constructs a new {@code FatherAereckDialogue} {@code Object}.
     *
     * @param player the player.
     */
    public FatherAereckDialogue(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new FatherAereckDialogue(player);
    }

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		int questStage = player.getQuestRepository().getQuest(RestlessGhost.NAME).getStage(player);
		if (questStage == 10) {
			npc("Have you got rid of the ghost yet?");
			stage = 520;
			return true;
		}
		if (questStage == 20) {
			player("I had a talk with Father Urhney. He has given me this", "funny amulet to talk to the ghost with.");
			stage = 530;
			return true;
		}
		if (questStage == 30) {
			player("I've found out that the ghost's corpse has lost its skull.", "If I can find the skull, the ghost should leave.");
			stage = 540;
			return true;
		}
		if (questStage == 40) {
			player("I've finally found the ghost's skull!");
			stage = 550;
			return true;
		}
		npc("Welcome to the church of holy Saradomin, my", "friend! What can I do for you today?");
		stage = 0;
		return true;
	}

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {
            case 0:
                if (player.getQuestRepository().isComplete("The Restless Ghost")) {
                    interpreter.sendOptions("What would you like to say?", "Can you change my gravestone now?", "Who's Saradomin?", "Nice place you've got here.");
                    stage = 1;
                } else {
                    interpreter.sendOptions("What would you like to say?", "Can you change my gravestone now?", "Who's Saradomin?", "Nice place you've got here.", "I'm looking for a quest.");
                    stage = 500;
                }
                break;
            case 500:
                switch (buttonId) {
                    case 1:
                        npc("Certainly. All proceeds are donated to the", "Varrockian Guards' Widows & Orphans Fund.");
                        stage = 10;
                        break;
                    case 2:
                        npc("Surely you have heard of our god, Saradomin?");
                        stage = 20;
                        break;
                    case 3:
                        npc("It is, isn't it? It was built over two centuries ago.");
                        stage = 300;
                        break;
                    case 4:
                        player("I'm looking for a quest.");
                        stage = 505;
                        break;
                }
                break;
            case 505:
                npc("That's lucky, I need someone to do a quest for me.");
                stage = 506;
                break;
            case 506:
                options("Ok, let me help then.", "Sorry, I don't have time right now.");
                stage = 507;
                break;
            case 507:
                switch (buttonId) {
                    case 1:
                        player("Ok, let me help then.");
                        stage = 510;
                        break;
                    case 2:
                        player("Sorry, I don't have time right now.");
                        stage = 508;
                        break;
                }
                break;
            case 508:
                npc("Oh well. If you do have some spare time on your", "hands, come back and talk to me.");
                stage = 509;
                break;
            case 509:
                end();
                break;
            case 510:
                player.getQuestRepository().getQuest(RestlessGhost.NAME).start(player);
                player.getQuestRepository().syncronizeTab(player);
                npc("Thank you. The problem is, there is a ghost in the", "church graveyard. I would like you to get rid of it.");
                stage = 511;
                break;
            case 511:
                npc("If you need any help, my friend Father Urhney is an", "expert on ghosts.");
                stage = 512;
                break;
            case 512:
                npc("I believe he is currently living as a hermit in Lumbridge", "swamp. He has a little shack in the south-west of the", "swamps.");
                stage = 513;
                break;
            case 513:
                npc("Exit the graveyard through the south gate to reach the", "swamp. I'm sure if you told him that I sent you he'd", "be willing to help.");
                stage = 514;
                break;
            case 514:
                npc("My name is Father Aereck by the way. Pleased to", "meet you.");
                stage = 515;
                break;
            case 515:
                player("Likewise.");
                stage = 516;
                break;
            case 516:
                npc("Take care travelling through the swamps, I have heard", "they can be quite dangerous.");
                stage = 517;
                break;
            case 517:
                player("I will, thanks.");
                stage = 518;
                break;
            case 518:
                end();
                break;
            case 520:
                if (!player.getGameAttributes().getAttributes().containsKey("restless-ghost:urhney")) {
                    player("I can't find Father Urhney at the moment.");
                    stage = 521;
                    break;
                }
                break;
            case 521:
                npc("Well, you can get to the swamp he lives in by going", "south through the cemetery.");
                stage = 522;
                break;
            case 522:
                npc("You'll have to go right into the western depths of the", "swamp, near the coastline. That is where his house is.");
                stage = 523;
                break;
            case 523:
                end();
                break;
            case 530:
                npc("I always wondered what that amulet was... Well, I hope", "it's useful. Tell me when you get rid of the ghost!");
                stage = 531;
                break;
            case 531:
                end();
                break;
            case 540:
                npc("That WOULD explain it.");
                stage = 541;
                break;
            case 541:
                npc("Hmmmmm. Well, I haven't seen any skulls.");
                stage = 542;
                break;
            case 542:
                player("Yes, I think a warlock has stolen it.");
                stage = 543;
                break;
            case 543:
                npc("I hate warlocks.");
                stage = 544;
                break;
            case 544:
                npc("Ah well, good luck!");
                stage = 545;
                break;
            case 545:
                end();
                break;
            case 550:
                npc("Great! Put it in the ghost's coffin and see what", "happens!");
                stage = 545;
                break;
            case 1:
                switch (buttonId) {
                    case 1:
                        npc("Certainly. All proceeds are donated to the", "Varrockian Guards' Widows & Orphans Fund.");
                        stage = 10;
                        break;
                    case 2:
                        npc("Surely you have heard of our god, Saradomin?");
                        stage = 20;
                        break;
                    case 3:
                        npc("It is, isn't it? It was built over two centuries ago.");
                        stage = 300;
                        break;
                }

                break;
            case 10:
                end();
                player.getInterfaceManager().open(new Component(652));
                BitregisterAssembler.send(player, 652, 34, 0, 13, new BitregisterAssembler(0));
                player.getConfigManager().set(1146, player.getGraveManager().getType().ordinal() | 262112);
                player.getAchievementDiaryManager().finishTask(player, DiaryType.LUMBRIDGE, 0, 15);
                break;
            case 20:
                npc("He who created the forces of goodness and purity in this", "world? I cannot believe your ignorance!");
                stage = 21;
                break;
            case 21:
                npc("This is the god with more followers than any other ...at", "least in this part of the world.");
                stage = 22;
                break;
            case 22:
                npc("He who forged the world as we know it, along with his", "brothers Guthix and Zamorak?");
                stage = 23;
                break;
            case 23:
                options("Oh, THAT Saradomin.", "Oh, sorry, I'm not from this world.");
                stage = 24;
                break;
            case 24:
                switch (buttonId) {
                    case 1:
                        npc("There is only one Saradomin.");
                        stage = 200;
                        break;
                    case 2:
                        npc("...");
                        stage = 250;
                        break;
                }

                break;
            case 200:
                player("Yeah. I, uh, thought you said something else.");
                stage = 201;
                break;
            case 201:
                end();
                break;
            case 250:
                npc("That's...strange.");
                stage = 251;
                break;
            case 251:
                npc("I thought things not from this world were all, you know,", "slime and tentacles.");
                stage = 270;
                break;
            case 270:
                options("Not me.", "I am! Do you like my disguise?");
                stage = 271;
                break;
            case 271:

                switch (buttonId) {
                    case 1:
                        npc("Well, I can see that. Still, there's something special about", "you.");
                        stage = 253;
                        break;
                    case 2:
                        npc("Argh! Avaunt, foul creature from another dimension!", "Avaunt! Begone in the name of Saradomin!");
                        stage = 291;
                        break;
                }
                break;
            case 291:
                player("Okay, okay, I was only joking!");
                stage = 292;
                break;
            case 292:
                end();
                break;
            case 253:
                player("Thanks, I think.");
                stage = 254;
                break;
            case 254:
                end();
                break;
            case 300:
                end();
                break;
            case 570:
                interpreter.sendDialogues(player, null, "Yes, I have!");
                stage = 571;
                break;
            case 571:
                interpreter.sendDialogues(npc, null, "Thank you for getting rid of that awful ghost for me!", "May Saradomin always smile upon you!");
                stage = 572;
                break;
            case 572:
                interpreter.sendDialogues(player, null, "I'm looking for a new quest.");
                stage = 573;
                break;
            case 573:
                interpreter.sendDialogues(npc, null, "Sorry, I only had the one quest.");
                stage = 300;
                break;
        }

        return true;
    }

    @Override
    public int[] getIds() {
        return new int[]{456};
    }
}
