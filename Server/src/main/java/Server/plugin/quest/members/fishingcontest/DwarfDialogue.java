package plugin.quest.members.fishingcontest;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.GroundItemManager;
import core.plugin.InitializablePlugin;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;

@InitializablePlugin
public class DwarfDialogue extends DialoguePlugin {
    public DwarfDialogue(){
        //empty
    }
    public DwarfDialogue(Player player){super(player);}

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new DwarfDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        int questStage = player.getQuestRepository().getStage("Fishing Contest");
        if((questStage < 20 && questStage > 0) && !player.getInventory().containsItem(FishingContest.FISHING_PASS)){
            player("I lost my fishing pass...");
            stage = 1000;
            return true;
        }
        if(player.getInventory().containsItem(FishingContest.FISHING_TROPHY) && player.getAttribute("fishing_contest:won",false)){
            npc(FacialExpression.OLD_NORMAL,"Have you won yet?");
            stage = 2000;
            return true;
        }
        if(player.getQuestRepository().getStage("Fishing Contest") >= 10 && !player.getAttribute("fishing_contest:won",false)){
            npc(FacialExpression.OLD_NORMAL,"Have you won yet?");
            stage = 1500;
            return true;
        }
        if(player.getQuestRepository().getStage("Fishing Contest") == 100){
            npc(FacialExpression.OLD_NORMAL,"Welcome, oh great fishing champion!","Feel free to pop by and use","our tunnel any time!");
            stage = 2500;
            return true;
        }
        npc(FacialExpression.OLD_NORMAL,"Hmph! What do you want?");
        stage = 0;
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch(stage){
            case 0:
                player("I was wondering what was down that tunnel?");
                stage++;
                break;
            case 1:
                npc(FacialExpression.OLD_NORMAL,"You can't go down there!");
                stage++;
                break;
            case 2:
                options("I didn't want to anyway.","Why not?");
                stage++;
                break;
            case 3:
                switch(buttonId){
                    case 1:
                        player("I didn't want to anyway.");
                        stage = 10;
                        break;
                    case 2:
                        player("Why not?");
                        stage = 21;
                        break;
                }
                break;
            case 10:
                npc(FacialExpression.OLD_NORMAL,"Good. Because you can't.");
                stage++;
                break;
            case 11:
                player("Because I don't want to.");
                stage++;
                break;
            case 12:
                npc(FacialExpression.OLD_NORMAL,"Because you can't. So that's fine.");
                stage++;
                break;
            case 13:
                player("Yes it is.");
                stage++;
                break;
            case 14:
                npc(FacialExpression.OLD_NORMAL,"Yes. Fine.");
                stage++;
                break;
            case 15:
                player("Absolutely.");
                stage++;
                break;
            case 16:
                npc(FacialExpression.OLD_NORMAL,"Well then.");
                stage = 100;
                break;
            case 20:
                player("Why not?");
                stage++;
                break;
            case 21:
                npc(FacialExpression.OLD_NORMAL,"This is the home of the Mountain Dwarves.","How would you like it if I","wanted to take a shortcut through your home?");
                stage++;
                break;
            case 22:
                options("Ooh... is this a short cut to somewhere?","Oh, sorry, I hadn't realized it was private.","If you were my friend I wouldn't mind it.");
                stage++;
                break;
            case 23:
                switch(buttonId) {
                    case 1:
                        player("Ooh... is this a short cut to somewhere?");
                        stage = 30;
                        break;
                    case 2:
                        player("Oh, sorry, I hadn't realized it was private.");
                        stage = 40;
                        break;
                    case 3:
                        player("If you were my friend I wouldn't mind it.");
                        stage = 50;
                        break;
                }
                break;
            case 30:
                npc(FacialExpression.OLD_NORMAL,"Well, it is a lot easier to go this way", "to get past White Wolf Mountain than through", "those wolf filled passes.");
                stage = 22;
                break;
            case 40:
                npc(FacialExpression.OLD_NORMAL,"Well, it is.");
                stage = 22;
                break;
            case 50:
                npc(FacialExpression.OLD_NORMAL,"Yes, but I don't even know you.");
                stage++;
                break;
            case 51:
                player("Well, let's be friends!");
                stage++;
                break;
            case 52:
                npc(FacialExpression.OLD_NORMAL,"I don't make friends easily.","People need to earn my trust first.");
                stage++;
                break;
            case 53:
                player("And how am I to do that?");
                stage++;
                break;
            case 54:
                npc(FacialExpression.OLD_NORMAL,"My, we are the persistent one aren't we?","Well, there's a certain gold artefact we're after.","We dwarves are big fans of gold! This artefact","is the first prize at the Hemenster fishing competition.");
                stage++;
                break;
            case 55:
                npc(FacialExpression.OLD_NORMAL,"Fortunately we have acquired a pass to enter","that competition... Unfortunately Dwarves don't","make good fisherman. Okay, I entrust you with our","competition pass.");
                stage++;
                break;
            case 56:
                npc(FacialExpression.OLD_NORMAL,"Don't forget to take some gold","with you for the entrance fee.");
                stage++;
                break;
            case 57:
                player.getDialogueInterpreter().sendDialogue("You got the Fishing Contest Pass!");
                if(!player.getInventory().add(FishingContest.FISHING_PASS)){
                    GroundItemManager.create(FishingContest.FISHING_PASS,player.getLocation());
                }
                player.getQuestRepository().getQuest("Fishing Contest").start(player);
                stage++;
                break;
            case 58:
                npc(FacialExpression.OLD_NORMAL,"Go to Hemenster and do us proud!");
                stage = 100;
                break;
            case 100:
                end();
                break;
            case 1000:
                npc(FacialExpression.OLD_NORMAL,"Hmm. It's a good job they sent us spares.","There you go. Try not to lose that one.");
                player.getInventory().add(FishingContest.FISHING_PASS);
                stage++;
                break;
            case 1001:
                player("No, it takes preparation to win","fishing competitions.");
                stage++;
                break;
            case 1002:
                npc(FacialExpression.OLD_NORMAL,"Maybe that's where we are going wrong","when we try fishing?");
                stage++;
                break;
            case 1003:
                player("Probably.");
                stage++;
                break;
            case 1004:
                npc(FacialExpression.OLD_NORMAL,"Maybe we should talk to that old Jack","fella near the competition, everyone","seems to be ranting about him.");
                stage = 100;
                break;
            case 2000:
                player("Yes, I have!");
                stage++;
                break;
            case 2001:
                npc(FacialExpression.OLD_NORMAL,"Well done! That's brilliant, do you have","the trophy?");
                stage++;
                break;
            case 2002:
                player("Yep, I have it right here!");
                stage++;
                break;
            case 2003:
                npc(FacialExpression.OLD_NORMAL,"Oh, it's even more shiny and gold than","I thought possible...");
                stage++;
                break;
            case 2004:
                player.getQuestRepository().getQuest("Fishing Contest").finish(player);
                player.getInventory().remove(FishingContest.FISHING_TROPHY);
                end();
                break;
            case 1500:
                player("No, I haven't.");
                stage++;
                break;
            case 1501:
                end();
                break;
            case 2500:
                player("Thanks, I think I will stop by.");
                stage = 100;
                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[] {232,3679};
    }
}
