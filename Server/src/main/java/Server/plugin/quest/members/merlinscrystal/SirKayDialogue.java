package plugin.quest.members.merlinscrystal;

import core.game.node.entity.player.link.diary.AchievementDiary;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.entity.player.link.quest.Quest;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for Sir Kay.
 *
 * @author afaroutdude
 */
public final class SirKayDialogue extends DialoguePlugin {

    /**
     * Constructs a new {@code KingArthurDialogue} {@code Object}.
     */
    public SirKayDialogue() {
        /**
         * empty.
         */
    }

    /**
     * Constructs a new {@code KingArthurDialogue} {@code Object}.
     *
     * @param player the player.
     */
    public SirKayDialogue(Player player) {
        super(player);
    }

    private Quest quest;

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new SirKayDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        quest = player.getQuestRepository().getQuest("Merlin's Crystal");

        options("Hello.", "Talk about achievement diary.");
        stage = 0;

        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
		AchievementDiary diary = player.getAchievementDiaryManager().getDiary(DiaryType.SEERS_VILLAGE);
		int level = 2;

		switch (stage) {
        	case 999:
        		end();
        		break;
			case 0:
				switch(buttonId) {
					case 1:
						player("Hello.");
						stage = 1;
						break;
					case 2:
						if (!diary.isStarted(level)) {
							player("Do you have an Achievement Diary for me?");
							stage = 100;
							break;
						}
						else if (diary.isLevelRewarded(level) && !player.hasItem(diary.getType().getRewards()[level][0])) {
							player("I seem to have lost my seers' headband...");
							stage = 80;
							break;
						}
						else if (diary.isLevelRewarded(level)) {
							player("Can you remind me what my headband does?");
							stage = 90;
							break;
						}
						else if (diary.isComplete(level, true)) {
							player("Greetings, Sir Kay. I have completed all of the Hard", "tasks in my Achievement Diary. May I have a reward?");
							stage = 200;
							break;
						}
						else {
							player("Hi! Can you help me out with the Achievement Diary", "tasks?");
							stage = 101;
							break;
						}
				}
				break;
			case 1:
				npc("Good morrow " + (player.isMale() ? "sirrah" : "madam") + "!");
				stage++;
				break;
			case 2:
				switch(quest.getStage(player)) {
					case 0:
						player("Morning. Know where an adventurer has to go to", "find a quest around here?");
						stage = 3;
						break;
					case 10:
						player("Any ideas on getting Merlin out of that crystal?");
						stage = 4;
						break;
					case 20:
						player("Any ideas on getting into Mordred's fort?");
						stage = 5;
						break;
					case 50:
						player("Any ideas on finding Excalibur?");
						stage = 4;
						break;
					case 100:
						npc("Many thanks for your assistance in restoring Merlin", "to his former freedom!");
						stage = 10;
						break;
					default:
						end();
						break;
				}
				break;
			case 3:
				npc("An adventurer eh? There is no service finer than", "serving the bountiful King Arthur, and I happen to", "know there's an important quest to fulfill.");
				stage = 999;
				break;
			case 4:
				npc("Unfortunately not, " + (player.isMale() ? "sirrah" : "madam") + ".");
				stage = 999;
				break;
			case 5:
				npc("Mordred... So you think he may be involved with", "the curse upon Merlin?");
				stage++;
				break;
			case 6:
				player("Good a guess as any right?");
				stage++;
				break;
			case 7:
				npc("I think you may be on to something there.", "Unfortunately his fortress is impregnable!");
				stage++;
				break;
			case 8:
				player("... I'll figure something out.");
				stage = 999;
				break;
			case 10:
				player("Hey, no problem.");
				stage = 999;
				break;

			case 80:
				player.getInventory().add(diary.getType().getRewards(level)[0], player);
				npc("Here's your replacement. Please be more careful.");
				stage = 999;
				break;
			case 90:
				npc("Your headband will help you get experience when", "woodcutting maple trees, and an extra log or two when", "cutting normal trees. I've also told Geoff to increase");
				stage++;
			case 91:
				npc("your flax allowance in acknowledgement of your", "standing.");
				stage = 999;
				break;
			case 100:
				npc("I certainly do - we have a set of tasks spanning Seers'", "Village, Catherby, Hemenster and the Sinclair Mansion.", "Just complete the tasks listed in the Achievement Diary", "and they will be ticked off automatically.");
				stage = 999;
				break;
			case 101:
				npc("I'm afraid not. It is important that adventurers", "complete the tasks unaided. That way, only the truly", "worthy collect the spoils.");
				stage = 999;
				break;
			case 200:
				npc("Well done, young " + (player.isMale() ? "sir" : "madam") + ". You must be a mighty", "adventurer indeed to have completed the Hard tasks.");
				stage++;
				break;
			case 201:
				if (!player.hasItem(diary.getType().getRewards()[level][0])) {
					npc("I need your headband. Come back when", "you have it.");
					stage = 999;
				} else {
					diary.setLevelRewarded(level);
					player.getInventory().add(diary.getType().getRewards()[level][1]);
					sendDialogue("You hand Sir Kay your headband and he concentrates for a", "moment. Some mysterious knightly energy passes through his hands", "and he gives the headband back to you, along with an old lamp.");
					stage++;
				}
				break;
			case 202:
				npc("You will find that your headband now blesses you with", "the power to spin fabrics at extreme speed in Seers'", "Village. I will also instruct Geoff-erm-Flax to offer you", "a far larger flax allowance. Use your new powers");
				stage++;
				break;
			case 203:
				npc("wisely.");
				stage++;
				break;
			case 204:
				player("Thank you, Sir Kay, I'll try not to harm anyone with", "my spinning.");
				stage++;
				break;
			case 205:
				npc("You are most welcome. You may also find that the", "Lady of the Lake is prepared to reward you for your", "services if you wear the headband in her presence.");
				stage=999;
				break;
        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[]{241};
    }
}
