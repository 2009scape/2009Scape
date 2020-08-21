package plugin.quest.members.sheepherder;

import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import plugin.dialogue.DialoguePlugin;

@InitializablePlugin
public class HalgriveDialogue extends DialoguePlugin {
    public HalgriveDialogue(){
        //empty
    }

    public HalgriveDialogue(Player player){super(player);}

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new HalgriveDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        if(player.getQuestRepository().getStage("Sheep Herder") < 10) {
            player("Hello. How are you?");
            stage = 0;
            return true;
        } else {
            npc("Have you managed to find and dispose of those four","plague-bearing sheep yet?");
            stage = 200;
            return true;
        }
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch(stage){
            case 0:
                npc("I've been better.");
                stage++;
                break;
            case 1:
                options("What's wrong?","That's life for you.");
                stage++;
                break;
            case 2:
                switch(buttonId){
                    case 1:
                        player("What's wrong?");
                        stage = 10;
                        break;
                    case 2:
                        player("That's life for you.");
                        stage = 108;
                        break;
                }
                break;
            case 10:
                npc("You may or may not be aware, but a plague has","spread across Western Ardougne. Now, so far, our","efforts to contain it have been largely successful, for the","most part.");
                stage++;
                break;
            case 11:
                npc("However, four sheep recently escaped from a farm near","the city. When they were found, we noticed that they","were strangely discoloured, so we asked the mourners","to examine them.");
                stage++;
                break;
            case 12:
                npc("They found that the sheep had become infected with the","plague.");
                stage++;
                break;
            case 13:
                npc("As the councillor responsible for public health and safety","here in East Ardougne I am looking for someone to","herd these sheep into a safe enclosure, kill them quickly","and cleanly and then dispose of the remains hygienically");
                stage++;
                break;
            case 14:
                npc("in a special incinerator.");
                stage++;
                break;
            case 15:
                npc("Unfortunately nobody wants to risk catching the plague,","and I am unable to find someone willing to undertake","this mission for me.");
                stage++;
                break;
            case 16:
                options("I can do that for you.","That's not a job for me.");
                stage++;
                break;
            case 17:
                switch(buttonId){
                    case 1:
                        player("I can do that for you.");
                        stage = 100;
                        break;
                    case 2:
                        player("That's not a job for me.");
                        stage = 108;
                        break;
                }
                break;
            case 100:
                player.getQuestRepository().getQuest("Sheep Herder").start(player);
                npc("Y-you will??? That is excellent news! Head to the","enclosure we have set up on Farmer Brumty's land to","the north of the city; the four infected sheap should still","be somewhere in that vicinity. Before you will be allowed");
                stage++;
                break;
            case 101:
                npc("to enter the enclosure, however, you must ensure you","have some kind of protective clothing to prevent","contagion.");
                stage++;
                break;
            case 102:
                player("Where can I find some protective clothing then?");
                stage++;
                break;
            case 103:
                npc("Doctor Orbon wears it when conducting mercy","missions to the infected parts of the city. You should be","able to find him in the chapel just north of here. Please","also take this poisoned sheed feed; we believe poisoning");
                stage++;
                break;
            case 104:
                npc("the sheep will niminise the risk of airborne","contamination, and is of course also more humane to","the sheep.");
                stage++;
                break;
            case 105:
                player.getDialogueInterpreter().sendDialogue("The councillor gives you some poisoned sheep feed.");
                player.getInventory().add(SheepHerder.POISON);
                stage++;
                break;
            case 106:
                player("How will I know which sheep are infected?");
                stage++;
                break;
            case 107:
                npc("The poor creatures have developed strangely discoloured","wool and flesh. You should have no trouble spotting","them.");
                stage++;
                break;
            case 108:
                end();
                break;
            case 200:
                if(player.getAttribute("sheep_herder:all_dead",false)){
                    player("Yes, I have.");
                    stage = 205;
                } else {
                    player("No, I haven't.");
                    stage++;
                }
                break;
            case 201:
                npc("Well, pleae do hurry!");
                stage = 108;
                break;
            case 205:
                npc("Excellent work adventurer! Please let me reimburse","you the 100 gold it cost you to purchase your","protective clothing.");
                stage++;
                break;
            case 206:
                npc("And in recognition of your service to the public health","of Ardougne please accept this further 3000 coins as a","reward.");
                stage++;
                break;
            case 207:
                player.getQuestRepository().getQuest("Sheep Herder").finish(player);
                end();
                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[] {289} ;
    }
}
