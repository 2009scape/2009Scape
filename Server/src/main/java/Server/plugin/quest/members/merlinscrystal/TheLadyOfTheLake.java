package plugin.quest.members.merlinscrystal;

import core.game.container.impl.EquipmentContainer;
import core.game.content.ItemNames;
import core.game.node.entity.player.link.diary.DiaryType;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;

/**
 * Handles the LadyOfTheLake dialogue.
 *
 * @author Vexia
 * @author Splinter
 */
public class TheLadyOfTheLake extends DialoguePlugin {

    /**
     * Constructs a new {@code TheLadyOfTheLake} {@code Object}
     */
    public TheLadyOfTheLake() {
        /*
         * empty.
         */
    }

    /**
     * Constructs a new {@code TheLadyOfTheLake} {@code Object}
     *
     * @param player the player.
     */
    public TheLadyOfTheLake(Player player) {
        super(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        if (!player.hasItem(new Item(35, 1))) {
            npc("Good day to you, " + (player.isMale() ? "sir" : "madam") + ".");
            stage = 0;
        } else {
            npc("Good day to you, " + (player.isMale() ? "sir" : "madam") + ".");
            stage = 699;
        }
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        final Quest quest = player.getQuestRepository().getQuest("Merlin's Crystal");
        switch (stage) {
			case 999:
				end();
				break;
            case 0:
                options("Who are you?", "I seek the sword Excalibur.", "Good day.");
                stage = 1;
                break;
            case 1:
                switch (buttonId) {
                    case 1:
                        player("Who are you?");
                        stage = 100;
                        break;
                    case 2:
                    	player("I seek the sword Excalibur.");
						if (quest.getStage(player) < 50) {
                            stage = 250;
                        } else {
                            stage = 161;
                        }
                        break;
                    case 3:
                        player("Good day.");
                        stage = 999;
                        break;
                }
                break;
            case 250:
                npc("I am afraid I do not know what you are talking about.");
                stage = 171;
                break;
            case 100:
                npc("I am the Lady of the Lake.");
                if (player.getAchievementDiaryManager().getDiary(DiaryType.SEERS_VILLAGE).isComplete(2)
						&& player.getEquipment().get(EquipmentContainer.SLOT_HAT) != null
						&& player.getEquipment().get(EquipmentContainer.SLOT_HAT).getId() == 14631
						&& (player.getInventory().containsOneItem(ItemNames.EXCALIBUR_35) || player.getEquipment().containsOneItem(ItemNames.EXCALIBUR_35))) {
                	stage = 110;
				} else {
					stage = 145;
				}
                break;
			case 110:
				player("And I'm-");
				stage++;
				break;
			case 111:
				npc("You're " + player.getName() + ". And I see from the sign you", "wear that you have earned the trust of Sir Kay.");
				stage++;
				break;
			case 112:
				player("It was nothing.. really...");
				stage++;
				break;
			case 113:
				npc("You shall be rewarded handsomely!");
				stage++;
				break;
			case 114:
				interpreter.sendItemMessage(ItemNames.EXCALIBUR_35, "The Lady of the Lake reaches out and touches the", "blade Excalibur which seems to vibrate with new power.");
				if (player.getInventory().containsOneItem(ItemNames.EXCALIBUR_35)) {
					player.getInventory().remove(new Item(ItemNames.EXCALIBUR_35));
					player.getInventory().add(new Item(14632));
				} else if (player.getEquipment().containsOneItem(ItemNames.EXCALIBUR_35)) {
					player.getEquipment().remove(new Item(ItemNames.EXCALIBUR_35));
					player.getEquipment().add(new Item(14632), true, false);
				}
				stage++;
				break;
            case 115:
                player("What does this do then?");
                stage++;
                break;
            case 116:
                npc("I made the blade more powerful, and also gave it a", "rather healthy effect when you use the special.");
                stage++;
                break;
            case 117:
                player("Thanks!");
                stage = 999;
                break;
            case 145:
                options("I seek the sword Excalibur.", "Good day.");
                stage = 150;
                break;
            case 150:
                switch (buttonId) {
                    case 1:
                        player("I seek the sword Excalibur.");
                        stage = 161;
                        break;
                    case 2:
                        player("Good day.");
                        stage = 999;
                        break;
                }
                break;
            case 161:
                if (quest.getStage(player) == 50 || quest.getStage(player) == 60) {// they haven't proven themselves yet
                    npc("Aye, I have that artefact in my possession.");
                    stage = 300;
                } else if (quest.getStage(player) >= 70) {
                    npc("... But you have already proved thyself to be worthy", "of wielding it once already. I shall return it to you", "if you can prove yourself to still be worthy.");
                    stage = 162;
                }
                break;
            case 162:
                player("... And how can I do that?");
                stage = 163;
                break;
            case 163:
                npc("Why, by proving yourself to be above material goods.");
                stage = 164;
                break;
            case 164:
                npc("500 coins ought to do it.");
                stage = 166;
                break;
            case 166:
            	if (player.getInventory().contains(995, 500)) {
            		player("Ok, here you go.");
            		stage = 168;
				} else {
            		player("I don't have that kind of money...");
            		stage = 167;
				}
                break;
            case 167:
                npc("Well, come back when you do.");
                stage = 999;
                break;
            case 168:
                if (player.getInventory().freeSlots() == 0) {
                    player("Sorry, I don't seem to have enough inventory space.");
                    stage = 999;
                } else if (player.getInventory().contains(995, 500)) {
                    player.getInventory().remove(new Item(995, 500));
                    player.getInventory().add(new Item(35, 1));
                    npc("You are still worthy to wield Excalibur! And thanks", "for the cash! I felt like getting a new haircut!");
                    stage = 170;
                } else {
					player("I don't have that kind of money...");
					stage = 167;
                }
                break;
            case 170:
                interpreter.sendDialogue("The lady of the Lake hands you Excalibur.");
                stage = 999;
                break;
            case 300:
                npc("'Tis very valuable, and not an artefact to be given", "away lightly.");
                stage = 301;
                break;
            case 301:
                npc("I would want to give it away only to one who is worthy", "and good.");
                stage = 302;
                break;
            case 302:
                player("And how am I meant to prove that?");
                stage = 303;
                break;
            case 303:
                npc("I shall set a test for you.");
                stage = 304;
                break;
            case 304:
                npc("First I need you to travel to Port Sarim. Then go to", "the upstairs room of the jeweller's shop there.");
                stage = 305;
                break;
            case 305:
                player("Ok. That seems easy enough.");
                quest.setStage(player, 60);
                stage = 171;
                break;
            case 699:
                options("Who are you?", "Good day.");
                stage = 700;
                break;
            case 700:
                switch (buttonId) {
                    case 1:
                        player("Who are you?");
                        stage = 720;
                        break;
                    case 2:
                        player("Good day.");
                        stage = 999;
                        break;
                }
                break;
            case 720:
                npc("I am the Lady of the Lake.");
                stage = 999;
                break;
        }
        return true;
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new TheLadyOfTheLake(player);
    }

    @Override
    public int[] getIds() {
        return new int[]{250};
    }
}
