package plugin.dialogue;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.AchievementDiary;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;

/**
 * Represents the dialogue to handle Rebeard Frank.
 *
 * @author afaroutdude
 */
@InitializablePlugin
public class ReadbeardFrankDialogue extends DialoguePlugin {
    private boolean replacementReward = false;
    private AchievementDiary diary;
    private int level = 0;

    /**
     * Represents the karamjan rum item.
     */
    private static final Item KARAMJAN_RUM = new Item(431);

    /**
     * Represents the chest key item.
     */
    private static final Item KEY = new Item(432);

    /**
     * Represents this quest instance.
     */
    private Quest quest;

    /**
     * Constructs a new {@code ReadbeardFrankDialogue} {@code Object}.
     */
    public ReadbeardFrankDialogue() {
        /**
         * empty.
         */
    }

    /**
     * Constructs a new {@code ReadbeardFrankDialogue} {@code Object}.
     *
     * @param player the player.
     */
    public ReadbeardFrankDialogue(Player player) {
        super(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        quest = player.getQuestRepository().getQuest("Pirate's Treasure");
        npc("Arr, Matey!");
        stage = 0;
        AchievementDiary diary = player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR);
        replacementReward = diary.isLevelRewarded(level)
                && diary.isComplete(level, true)
                && !player.hasItem(diary.getType().getRewards(level)[0]);
        return true;
    }

    // https://www.youtube.com/watch?v=U_sNcqQ2dtQ - achievement diary branch
    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {
            case 0:
                if (quest.getStage(player) == 20 && !player.getInventory().containsItem(KEY) && !player.getBank().containsItem(KEY)) {
                    player("I seem to have lost my chest key...");
                    stage = 700;
                    return true;
                }
                if (quest.getStage(player) == 10) {
                    npc("Have ye brought some rum for yer ol' mate Frank?");
                    stage = 20;
                    break;
                }
                if (quest.getStage(player) == 0) {
                    options("I'm in search of treasure.", "Arr!", "Do you have anything for trade?", "Tell me about the Falador Achievement Diary.");
                    stage = 11;
                    break;
                }
                options("Arr!", "Do you have anything for trade?", "Tell me about the Falador Achievement Diary.");
                stage = 10;
                break;
            case 10:
                switch (buttonId) {
                    case 1:
                        player("Arr!");
                        stage = 12;
                        break;
                    case 2:
                        player("Do you have anything for trade?");
                        stage = 13;
                        break;
                    case 3:
                        player("Tell me about the Falador Achievement Diary.");
                        stage = 80;
                        break;
                }
                break;
            case 11:
                switch (buttonId) {
                    case 1:
                        player("I'm in search of treasure.");
                        stage = 40;
                        break;
                    case 2:
                        player("Arr!");
                        stage = 12;
                        break;
                    case 3:
                        player("Do you have anything for trade?");
                        stage = 13;
                        break;
                    case 4:
                        player("Tell me about the Falador Achievement Diary.");
                        stage = 80;
                        break;
                }
                break;
            case 12:
                npc("Arr!");
                stage = 0;
                break;
            case 13:
                npc("Nothin' at the moment, but then again the Customs", "Agents are on the warpath right now.");
                stage = 999;
                break;
            case 20:
                if (!player.getInventory().containsItem(KARAMJAN_RUM)) {
                    player("No, not yet.");
                    stage = 21;
                } else {
                    player("Yes, I've got some.");
                    stage = 31;
                }
                break;
            case 21:
                npc("Not suprising, tis no easy task to get it off Karamja.");
                stage = 22;
                break;
            case 22:
                player("What do you mean?");
                stage = 23;
                break;
            case 23:
                npc("The Customs office has been clampin' down on the", "export of spirits. You seem like a resourceful young lad,", "I'm sure ye'll be able to find a way to slip the stuff past", "them.");
                stage = 24;
                break;
            case 24:
                player("Well I'll give it another shot.");
                stage = 25;
                break;
            case 999:
                end();
                break;
            case 31:
                npc("Now a deal's a deal, I'll tell ye about the treasure. I", "used to serve under a pirate captain called One-Eyed", "Hector.");
                stage = 32;
                break;
            case 32:
                npc("Hector were very successful and became very rich.", "But about a year ago we were boarded by the Customs", "and Excise Agents.");
                stage = 33;
                break;
            case 33:
                npc("Hector were killed along with many of the crew, I were", "one of the few to escape and I escaped with this.");
                stage = 34;
                break;
            case 34:
                if (player.getInventory().remove(KARAMJAN_RUM)) {
                    if (!player.getInventory().add(KEY)) {
                        GroundItemManager.create(KEY, player);
                    }
                    quest.setStage(player, 20);
                    interpreter.sendItemMessage(KEY.getId(), "Frank happily takes the rum... ... and hands you a key");
                    stage = 35;
                }
                end();
                break;
            case 35:
                npc("This be Hector's key. I believe it opens his chest in his", "old room in the Blue Moon Inn in Varrock.");
                stage = 36;
                break;
            case 36:
                npc("With any luck his treasure will be in there.");
                stage = 37;
                break;
            case 37:
                player("Ok thanks, I'll go and get it.");
                stage = 999;
                break;
            case 40:
                npc("Arr, treasure you be after eh? Well I might be able to", "tell you where to find some... For a price...");
                stage = 41;
                break;
            case 41:
                player("What sort of price?");
                stage = 42;
                break;
            case 42:
                npc("Well for example if you go and get me a bottle of rum...", "Not just any rum mind...");
                stage = 43;
                break;
            case 43:
                npc("I'd like some rum made on Karamja Island. There's no", "rum like Karamaja Rum!");
                stage = 44;
                break;
            case 44:
                options("Ok, I will bring you some rum.", "Not right now.");
                stage = 45;
                break;
            case 45:
                switch (buttonId) {
                    case 1:
                        quest.start(player);
                        player("Ok, I will bring you some rum.");
                        stage = 47;
                        break;
                    case 2:
                        player("Not right now.");
                        stage = 46;
                        break;
                }
                break;
            case 46:
                npc("Fair enough. I'll still be here and thirsty whenever you", "feel like helpin' out.");
                stage = 999;
                break;
            case 47:
                npc("Yer a saint, although it'll take a miracle to get it off", "Karamja.");
                stage = 48;
                break;
            case 48:
                player("What do you mean?");
                stage = 49;
                break;
            case 49:
                npc("The Customs office has been clampin' down on the", "export of spirits. You seem like a resourceful young lad,", "I'm sure ye'll be able to find a way to slip the stuff past", "them.");
                stage = 50;
                break;
            case 50:
                player("Well I'll give it a shot.");
                stage = 51;
                break;
            case 51:
                npc("Arr, that's the spirit!");
                stage = 999;
                break;


            case 700:
                npc("Arr, silly you. Fortunately I took the precaution to have", "another one made.");
                stage = 701;
                break;
            case 701:
                interpreter.sendItemMessage(KEY.getId(), "Frank hands you a chest key.");
                stage = 702;
                break;
            case 702:
                end();
                if (!player.getInventory().add(KEY)) {
                    GroundItemManager.create(KEY, player);
                }
                break;


            case 80:
                if (player.getAttribute("falador-diary-talk-first-time", false)) {
                    npc("So you're interested in exploring Falador and it's", "surrounding areas, eh?");
                    player.setAttribute("/save:falador-diary-talk-first-time", true);
                    stage = 100;
                } else {
                    npc("How are you getting on with the Achievement Diary?");
                    stage = 90;
                }
                break;

            case 90:
                options("I've come for my reward.", "I'm doing good.", "I have a question.");
                stage = 91;
                break;
            case 91:
                switch (buttonId) {
                    case 1:
                        player("I've come for my reward.");
                        stage = 200;
                        break;
                    case 2:
                        player("I'm doing good.");
                        stage = 220;
                        break;
                    case 3:
                        player("I have a question.");
                        stage = 105;
                        break;
                }
                break;

            case 100:
                player("Er... I guess.");
                stage = 101;
                break;
            case 101:
                npc("Arrr! That's the spirit! Soon ye'll be exploring", "underground caverns, sailin' the high seas and", "plundering booty!");
                stage = 102;
                break;
            case 102:
                interpreter.sendDialogues(player, FacialExpression.AMAZED, "Plundering booty?");
                stage = 103;
                break;
            case 103:
                npc("Arrr! Nay, that be a lie, I be getting carried away.");
                stage = 104;
                break;
            case 104:
                npc("Have you got any questions?");
                stage = 105;
                break;
            case 105:
                if (!diary.isLevelRewarded(level)) {
                    options("What is the Achievement Diary?", "What are the rewards?", "How do I claim the rewards?", "See you later.");
                    stage = 106;
                } else {
                    options("Can you remind me what my Falador shield does, please?", "What is the Achievement Diary?", "What are the rewards?", "How do I claim the rewards?", "See you later.");
                    stage = 107;
                }
                break;
            case 106:
                switch (buttonId) {
                    case 1:
                        player("What is the Achievement Diary?");
                        stage = 110;
                        break;
                    case 2:
                        player("What are the rewards?");
                        stage = 120;
                        break;
                    case 3:
                        player("How do I claim the rewards?");
                        stage = 130;
                        break;
                    case 4:
                        player("See you later.");
                        stage = 999;
                        break;
                }
                break;

            case 107:
                switch (buttonId) {
                    case 1:
                        player("Can you remind me what my Falador shield does, please?");
                        stage = 150;
                        break;
                    case 2:
                        player("What is the Achievement Diary?");
                        stage = 110;
                        break;
                    case 3:
                        player("What are the rewards?");
                        stage = 120;
                        break;
                    case 4:
                        player("How do I claim the rewards?");
                        stage = 130;
                        break;
                    case 5:
                        player("See you later.");
                        stage = 999;
                        break;
                }
                break;

            case 110:
                npc("Ah, well it's a diary that helps you keep track of", "particular achievements you've made here on", "2009Scape.");
                stage = 111;
                break;
            case 111:
                npc("If you manage to complete a particular set of tasks,", "you will be rewarded for your explorative efforts.");
                stage = 112;
                break;
            case 112:
                npc("You can access your Achievement Diary by going to", "the Quest Journal, then clicking on the green star icon", "in the top-right hand corner.");
                stage = 105;
                break;

            case 120:
                npc("Ah, well there are different rewards for each", "Achievement Diary. For completing each stage of the", "Falador diary, you are presented with a Falador shield.");
                stage = 121;
                break;
            case 121:
                npc("There are three shields available, one for each difficulty", "level.");
                stage = 122;
                break;
            case 122:
                npc("When you are presented with your rewards, you will", "be told of their uses.");
                stage = 105;
                break;

            case 130:
                npc("You need to complete all of the tasks in a particular", "difficulty, then you can claim your reward.");
                stage = 131;
                break;
            case 131:
                npc("Some of Falador's tasks are simple, some will require", "certain skill levels, and some might require quests to be", "started or completed.");
                stage = 132;
                break;
            case 132:
                npc("To claim your Falador Achievement Diary rewards,", "speak to the chemist in Rimmington, Sir Vyvin's squire", "in the White Knight's Castle, or myself.");
                stage = 105;
                break;

            case 150:
                npc("This is the first stage fo the Falador shield: a buckler. It", "grants you access to a Prayer restore ability and an", "emote.");
                stage = 151;
                break;
            case 151:
                npc("Each of these features can be triggered while wielding", "the shield by selecting the 'Operate' option. The Prayer", "restore can also be activated from your inventory.");
                stage = 152;
                break;
            case 152:
                npc("The Prayer restore ability can only be used once per", "day, and gives you back a quarter of your Prayer", "points.");
                stage = 153;
                break;
            case 153:
                npc("As well as all of these features, the shield is pretty", "handy in combat, and gives you a small Prayer boost.");
                stage = 105;
                break;

            case 200:
                if (diary.isLevelRewarded(level)) {
                    npc("But you've already gotten yours!");
                    stage = 105;
                } else if (diary.isComplete(level, true)) {
                    npc("So, you've finished. Well done! I believe congratulations", "are in order.");
                    stage = 201;
                } else {
                    npc("But you haven't finished!");
                    stage = 105;
                }
                break;
            case 201:
                player("I believe rewards are in order.");
                stage = 202;
                break;
            case 202:
                npc("Right you are.");
                stage = 203;
                break;
            case 203:
                npc("This is the first stage of the Falador shield: a buckler. It", "grants you access to a Prayer restore ability and an", "emote.");
                if (!diary.isLevelRewarded(level)) {
                    for (Item i : diary.getType().getRewards(level)) {
                        if (!player.getInventory().add(i, player)) {
                            GroundItemManager.create(i, player);
                        };
                    }
                    diary.setLevelRewarded(level);
                }
                stage = 204;
                break;
            case 204:
                npc("Each of these features can be triggered while wielding", "the shield by selecting the 'Operate' option. The Prayer", "restore can also be activated from your inventory.");
                stage = 205;
                break;
            case 205:
                npc("The Prayer restore ability can only be used once per", "day, and gives you back a quarter of your Prayer", "points.");
                stage = 206;
                break;
            case 206:
                npc("As well as all of these features, the shield is pretty", "handy in combat, and gives you a small Prayer boost.");
                stage = 207;
                break;
            case 207:
                npc("I've even thrown in a bit of booty I found on my", "travels.");
                stage = 208;
                break;
            case 208:
                interpreter.sendDialogues(player, FacialExpression.AMAZED, "Wow, thanks!");
                stage = 209;
                break;
            case 209:
                npc("If you should lose your shield, come back and see me", "for another one.");
                stage = 105;
                break;
            case 220:
                npc("Keep it up!");
                stage = 105;
                break;


        }
        return true;
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new ReadbeardFrankDialogue(player);
    }

    @Override
    public int[] getIds() {
        return new int[]{375};
    }

}
