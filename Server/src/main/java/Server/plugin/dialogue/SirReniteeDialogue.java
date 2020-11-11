package plugin.dialogue;

import core.Util;
import core.game.content.ItemNames;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.tools.RandomFunction;
import plugin.skill.Skills;
import plugin.skill.construction.CrestType;

/**
 * Represents the dialogue to handle Sir Renitee
 *
 * @author afaroutdude
 */
@InitializablePlugin
public class SirReniteeDialogue extends DialoguePlugin {
    /**
     * Constructs a new {@code SirReniteeDialogue} {@code Object}.
     */
    public SirReniteeDialogue() {
    }

    public SirReniteeDialogue(Player player) {
        super(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hmm? What's that, young " + (player.getAppearance().isMale() ? "man" : "woman") + "? What can I do for", "you?");
        stage = 0;
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {
            case 0:
                interpreter.sendOptions("Select an Option", "I don't know, what can you do for me?", "Nothing, thanks");
                stage = 10;
                break;
            case 10:
                switch (buttonId) {
                    case 1:
                        interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I don't know, what can you do for me?");
                        stage = 100;
                        break;
                    case 2:
                        interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Nothing, thanks.");
                        stage = 30;
                        break;
                }
                break;
            case 30:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Mmm, well, see you some other time maybe.");
                stage = 40;
                break;
            case 40:
                end();
                break;
            case 100:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hmm, well, mmm, do you have a family crest? I keep", "track of every 2009Scape family, you know, so I might", "be able to find yours.");
                stage = 110;
                break;
            case 110:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I'm also something of an, mmm, a painter. If you've", "met any important persons or visited any nice places I", "could paint them for you.");
                stage = 120;
                break;
            case 120:
                interpreter.sendOptions("Select an Option", "Can you see if I have a family crest?", "Can I buy a painting?");
                stage = 130;
                break;
            case 130:
                switch (buttonId) {
                    case 1:
                        interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can you see if I have a family crest?");
                        stage = 200;
                        break;
                    case 2:
                        interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can I buy a painting?");
                        stage = 500;
                        break;
                }
            case 200:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "What is your name?");
                stage = 210;
                break;
            case 210:
                interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, player.getUsername() + ".");
                stage = 220;
                break;
            case 220:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Mmm, " + player.getUsername() + ", let me see...");
                stage = 230;
                break;
            case 230:
                if (player.getSkills().hasLevel(Skills.CONSTRUCTION, 16)) {
                    if (player.getAttribute("sir-renitee-assigned-crest", false)) {
                        interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "According to my records, your crest is ", player.getHouseManager().getCrest().getName() + ".");
                    } else {
                        final int c = RandomFunction.random(4);
                        final CrestType[] crests = {CrestType.VARROCK, CrestType.ASGARNIA, CrestType.KANDARIN, CrestType.MISTHALIN};
                        final CrestType crest = crests[c];
                        player.getHouseManager().setCrest(crest);
                        player.setAttribute("/save:sir-renitee-assigned-crest", true);
                        String message = "that can be your";
                        if (crest == CrestType.VARROCK) {
                            message = "you can use that city's";
                        }
                        interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, I don't think you have any noble blood,",
                                "but I see that your ancestors came from " + Util.enumToString(player.getHouseManager().getCrest().name()) + ",", " so " + message + " crest.");
                    }
                    if (!player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR).isComplete(0,4)) {
                        player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR).updateTask(player,0,4,true);
                    }
                    stage = 240;
                } else {
                    interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY,
                            "First thing's first, young " + (player.getAppearance().isMale() ? "man" : "woman") + "! There is not much point",
                            "in having a family crest if you cannot display it.");
                    stage = 235;
                }
                break;
            case 235:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You should train construction until you can build a wall", "decoration in your dining room.");
                stage = 40;
                break;
            case 240:
                interpreter.sendOptions("Select an Option", "I don't like that crest. Can I have a different one?", "Thanks!");
                stage = 250;
                break;
            case 250:
                switch (buttonId) {
                    case 1:
                        interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I don't like that crest. Can I have a different one?");
                        stage = 300;
                        break;
                    case 2:
                        interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Thanks!");
                        stage = 260;
                        break;
                }
                break;
            case 260:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You're welcome, my " + (player.getAppearance().isMale() ? "boy." : "girl."));
                stage = 40;
                break;
            case 300:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Mmm, very well. Changing your crest will cost", "5,000 coins.");
                if (player.getInventory().getAmount(ItemNames.COINS) < 5000) {
                    stage = 302;
                } else {
                    stage = 305;
                }
                break;
            case 302:
                interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'll have to go and get some money then.");
                stage = 40;
                break;
            case 305:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "There are sixteen different symbols;", "which one would you like?");
                stage = 310;
                break;
            case 310:
                interpreter.sendOptions("Select an Option", "Shield of Arrav", "Asgarnia", "Dorgeshuun Symbol", "Dragon", "More...");
                stage = 320;
                break;
            case 320:
                switch (buttonId) {
                    case 1: {
                        CrestType c = CrestType.ARRAV;
                        if (c.eligible(player) && player.getInventory().getAmount(ItemNames.COINS) > c.getCost()
                                && player.getInventory().remove(new Item(ItemNames.COINS, c.getCost()))) {
                            player.getHouseManager().setCrest(c);
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ah yes, the shield that you helped to retrieve. You have", "certainly earned the right to wear its symbol.");
                            stage = 40;
                        } else {
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "[MISSING DIALOGUE - NOT ELIGIBLE]");
                            stage = 310;
                        }
                        break;
                    }
                    case 2: {
                        CrestType c = CrestType.ASGARNIA;
                        if (c.eligible(player) && player.getInventory().getAmount(ItemNames.COINS) > c.getCost()
                                && player.getInventory().remove(new Item(ItemNames.COINS, c.getCost()))) {
                            player.getHouseManager().setCrest(c);
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ah, splendid, splendid. There is no better symbol", "than that of our fair land!");
                            stage = 40;
                        } else {
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "[MISSING DIALOGUE - NOT ELIGIBLE]");
                            stage = 310;
                        }
                        break;
                    }
                    case 3: {
                        CrestType c = CrestType.DORGESHUUN;
                        if (c.eligible(player) && player.getInventory().getAmount(ItemNames.COINS) > c.getCost()
                                && player.getInventory().remove(new Item(ItemNames.COINS, c.getCost()))) {
                            player.getHouseManager().setCrest(c);
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ah yes, our new neighbours under Lumbridge. I hear", "you were the one who made contact with them, jolly good.");
                            stage = 40;
                        } else {
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hmm, have you ever even met the Dorgeshuun? I don't", "think you should wear their symbol until you have", "made contact with that lost tribe.");
                            stage = 310;
                        }
                        break;
                    }
                    case 4: {
                        CrestType c = CrestType.DRAGON;
                        if (c.eligible(player) && player.getInventory().getAmount(ItemNames.COINS) > c.getCost()
                                && player.getInventory().remove(new Item(ItemNames.COINS, c.getCost()))) {
                            player.getHouseManager().setCrest(c);
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I see you are a mighty dragon-slayer! You have", "certainly earned the right to wear a dragon symbol.");
                            stage = 40;
                        } else {
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "[MISSING DIALOGUE - NOT ELIGIBLE]");
                            stage = 310;
                        }
                        break;
                    }
                    case 5:
                        interpreter.sendOptions("Select an option", "Fairy", "Guthix", "HAM", "Horse", "More...");
                        stage = 330;
                        break;
                }
                break;
            case 330:
                switch (buttonId) {
                    case 1: {
                        CrestType c = CrestType.FAIRY;
                        if (c.eligible(player) && player.getInventory().getAmount(ItemNames.COINS) > c.getCost()
                                && player.getInventory().remove(new Item(ItemNames.COINS, c.getCost()))) {
                            player.getHouseManager().setCrest(c);
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hmm, mmm, yes, everyone likes pretty fairies.");
                            stage = 40;
                        } else {
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "[MISSING DIALOGUE - NOT ELIGIBLE]");
                            stage = 310;
                        }
                        break;
                    }
                    case 2: {
                        CrestType c = CrestType.GUTHIX;
                        if (c.eligible(player) && player.getInventory().getAmount(ItemNames.COINS) > c.getCost()
                                && player.getInventory().remove(new Item(ItemNames.COINS, c.getCost()))) {
                            player.getHouseManager().setCrest(c);
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Guthix, god of balance! I'm a Saradominist myself,", "you know, but we all find meaning in our own way, what?");
                            stage = 40;
                        } else {
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You do not seem to be very devoted to any god.", "I will not let you have a divine symbol", "unless you have level 70 prayer.");
                            stage = 310;
                        }
                        break;
                    }
                    case 3: {
                        CrestType c = CrestType.HAM;
                        if (c.eligible(player) && player.getInventory().getAmount(ItemNames.COINS) > c.getCost()
                                && player.getInventory().remove(new Item(ItemNames.COINS, c.getCost()))) {
                            player.getHouseManager().setCrest(c);
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hmm, I'm not sure I like that HAM group, their", "beliefs are a little extreme for me.", "But if that's what you want.");
                            stage = 40;
                        } else {
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "[MISSING DIALOGUE - NOT ELIGIBLE]");
                            stage = 310;
                        }
                        break;
                    }
                    case 4: {
                        CrestType c = CrestType.HORSE;
                        if (c.eligible(player) && player.getInventory().getAmount(ItemNames.COINS) > c.getCost()
                                && player.getInventory().remove(new Item(ItemNames.COINS, c.getCost()))) {
                            player.getHouseManager().setCrest(c);
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ah, I see you've brought a toy horse for me to see. An",
                                    "interesting beast. Certainly you can use that as your",
                                    "crest if you like, although it seems a bit strange to me.");
                            stage = 40;
                        } else {
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "A horse? I know people talk about them, but I'm not at all sure",
                                    "they ever existed. I don't think I could let you use as",
                                    "your symbol unless you can fetch me some kind of model of one.");
                            stage = 310;
                        }
                        break;
                    }
                    case 5:
                        interpreter.sendOptions("Select an option", "Jogre", "Kandarin", "Misthalin", "Money", "More...");
                        stage = 340;
                        break;
                }
                break;
            case 340:
                switch (buttonId) {
                    case 1: {
                        CrestType c = CrestType.JOGRE;
                        if (c.eligible(player) && player.getInventory().getAmount(ItemNames.COINS) > c.getCost()
                                && player.getInventory().remove(new Item(ItemNames.COINS, c.getCost()))) {
                            player.getHouseManager().setCrest(c);
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "A Jungle Ogre, eh? Odd beast, very odd.");
                            stage = 40;
                        } else {
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "[MISSING DIALOGUE - NOT ELIGIBLE]");
                            stage = 310;
                        }
                        break;
                    }
                    case 2: {
                        CrestType c = CrestType.KANDARIN;
                        if (c.eligible(player) && player.getInventory().getAmount(ItemNames.COINS) > c.getCost()
                                && player.getInventory().remove(new Item(ItemNames.COINS, c.getCost()))) {
                            player.getHouseManager().setCrest(c);
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Our neighbours in the west? Very good, very good.");
                            stage = 40;
                        } else {
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "[MISSING DIALOGUE - NOT ELIGIBLE]");
                            stage = 310;
                        }
                        break;
                    }
                    case 3: {
                        CrestType c = CrestType.MISTHALIN;
                        if (c.eligible(player) && player.getInventory().getAmount(ItemNames.COINS) > c.getCost()
                                && player.getInventory().remove(new Item(ItemNames.COINS, c.getCost()))) {
                            player.getHouseManager().setCrest(c);
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ah, the fair land of Lumbridge and Varrock.");
                            stage = 40;
                        } else {
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "[MISSING DIALOGUE - NOT ELIGIBLE]");
                            stage = 310;
                        }
                        break;
                    }
                    case 4:
                        interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You wish to represent yourself by a moneybag?", "I think to make that meaningful I should increase", "the price to 500,000 coins. Do you agree?");
                        stage = 342;
                        break;
                    case 5:
                        interpreter.sendOptions("Select an option", "Saradomin", "Skull", "Varrock", "Zamorak", "More...");
                        stage = 350;
                        break;
                }
                break;
            case 341:
                interpreter.sendOptions("Select an option", "All right.", "No way!");
                stage = 342;
                break;
            case 342:
                switch (buttonId) {
                    case 1:
                        interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "All right.");
                        CrestType c = CrestType.MONEY;
                        if (c.eligible(player) && player.getInventory().getAmount(ItemNames.COINS) > c.getCost()) {
                            stage = 343;
                        } else {
                            stage = 302;
                        }
                        break;
                    case 2:
                        interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No way!");
                        stage = 344;
                        break;
                }
                break;
            case 343: {
                CrestType c = CrestType.MONEY;
                if (c.eligible(player) && player.getInventory().getAmount(ItemNames.COINS) > c.getCost()
                        && player.getInventory().remove(new Item(ItemNames.COINS, c.getCost()))) {
                    player.getHouseManager().setCrest(c);
                    interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Thank you very much! You may now use a money-bag", "as your symbol.");
                    stage = 40;
                } else {
                    interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "[MISSING DIALOGUE - NOT ELIGIBLE]");
                    stage = 310;
                }
                break;
            }
            case 344:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well we can't have just any pauper using a money-bag", "as a symbol, can we? You'll have to choose a", "different symbol.");
                stage = 310;
                break;
            case 350:
                switch (buttonId) {
                    case 1: {
                        CrestType c = CrestType.SARADOMIN;
                        if (c.eligible(player) && player.getInventory().getAmount(ItemNames.COINS) > c.getCost()
                                && player.getInventory().remove(new Item(ItemNames.COINS, c.getCost()))) {
                            player.getHouseManager().setCrest(c);
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ah, the great god Saradomin! May he smile on your house", "as you adorn it with his symbol!");
                            if (!player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR).isComplete(2,1)) {
                                player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR).updateTask(player,2,1,true);
                            }
                            stage = 40;
                        } else {
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You do not seem to be very devoted to any god.", "I will not let you have a divine symbol", "unless you have level 70 prayer.");
                            stage = 310;
                        }
                        break;
                    }
                    case 2: {
                        CrestType c = CrestType.SKULL;
                        if (c.eligible(player) && player.getInventory().getAmount(ItemNames.COINS) > c.getCost()
                                && player.getInventory().remove(new Item(ItemNames.COINS, c.getCost()))) {
                            player.getHouseManager().setCrest(c);
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Of, of course you can have a skull symbol, " + (player.getAppearance().isMale() ? "sir!" : "madam!"));
                            stage = 40;
                        } else {
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "A symbol of death? You do not seem like a killer to me;", "perhaps some other symbol would suit you better.");
                            stage = 310;
                        }
                        break;
                    }
                    case 3: {
                        CrestType c = CrestType.VARROCK;
                        if (c.eligible(player) && player.getInventory().getAmount(ItemNames.COINS) > c.getCost()
                                && player.getInventory().remove(new Item(ItemNames.COINS, c.getCost()))) {
                            player.getHouseManager().setCrest(c);
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ah, Varrock, a fine city!");
                            stage = 40;
                        } else {
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "[MISSING DIALOGUE - NOT ELIGIBLE]");
                            stage = 310;
                        }
                        break;
                    }
                    case 4: {
                        CrestType c = CrestType.ZAMORAK;
                        if (c.eligible(player) && player.getInventory().getAmount(ItemNames.COINS) > c.getCost()
                                && player.getInventory().remove(new Item(ItemNames.COINS, c.getCost()))) {
                            player.getHouseManager().setCrest(c);
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "The god of Chaos? It is a terrible thing to worship", "that evil being. But if that is what you wish...");
                            stage = 40;
                        } else {
                            interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You do not seem to be very devoted to any god.", "I will not let you have a divine symbol", "unless you have level 70 prayer.");
                            stage = 310;
                        }
                        break;
                    }
                    case 5:
                        interpreter.sendOptions("Select an Option", "Shield of Arrav", "Asgarnia", "Dorgeshuun Symbol", "Dragon", "More...");
                        stage = 320;
                        break;
                }
                break;
            case 500:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "[MISSING DIALOGUE - UNIMPLIMENTED]");
                stage = 40;
        }
        return true;
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new SirReniteeDialogue(player);
    }

    @Override
    public int[] getIds() {
        return new int[]{4249};
    }

}
