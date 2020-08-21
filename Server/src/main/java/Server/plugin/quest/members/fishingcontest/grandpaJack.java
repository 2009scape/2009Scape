package plugin.quest.members.fishingcontest;

import core.game.node.entity.npc.NPC;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.Player;
import plugin.dialogue.DialoguePlugin;

/**
 * Rerpesents the dialogue plugin used for the grandpaJack npc.
 * @author Woah
 * @note finish with fishing contests.
 **/
@InitializablePlugin
public final class grandpaJack extends DialoguePlugin {



    /**
     * Constructs a new {@code grandpaJack} {@code Object}.
     * @param player
     */
    public grandpaJack(final Player player) {
        super(player);
    }

    /**
     * Constructs a new {@code grandpaJack} {@code Object}.
     */
    public grandpaJack() {
        /**
         * empty.
         */
    }


    @Override
    public DialoguePlugin newInstance(Player player) {
        return new grandpaJack(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        npc("Hello young'on!", "Come to visit old Grandpa Jack? I can tell ye stories", " for sure. I sued to be the best fisherman these parts", "have seen!");
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {

            case 0:
                interpreter.sendOptions("Choose an option:", "Tell me a story then.", "Are you entering the fishing competition?", "Sorry, I don't have time now.", "Can I buy one of your fishing rods?", "I've forgotten how to fish, can you remind me?");
                stage++;
                break;

            case 1:
                switch (buttonId) {
                    case 1:
                        player("Tell me a story then.");
                        stage = 10;
                        break;
                    case 2:
                        player("Are you entering the fishing competition?");
                        stage = 20;
                        break;
                    case 3:
                        player("Sorry, I don't have time now.");
                        stage = 35;
                        break;
                    case 4:
                        player("Can I buy one of your fishing rods?");
                        stage = 40;
                        break;
                    case 5:
                        player("I've forgotten how to fish, can you remind me?");
                        stage = 50;
                        break;
                }
                break;

            case 4:
                interpreter.sendOptions("Choose an option:", "I don't suppose you could give me any hints?", "That's less competition for me then.");
                stage++;
                break;

            case 5:
                switch (buttonId) {
                    case 1:
                        player("I don't suppose you could give me any hints?");
                        stage = 22;
                        break;
                    case 2:
                        player("That's less competition for me then.");
                        stage = 30;
                        break;
                }
                break;

            case 6:
                interpreter.sendOptions("Choose an option:", "Very fair, I'll buy that rod!", "That's too rich for me, I'll go to Catherby.");
                stage++;
                break;

            case 7:
                switch (buttonId) {
                    case 1:
                        player("Very fair, I'll buy that rod!");
                        if(player.getInventory().containsItem(new Item(995,5))){
                            stage = 42;
                        } else {
                            stage = 60;
                        }
                        break;
                    case 2:
                        player("That's too rich for me, I'll go to Catherby.");
                        stage = 44;
                        break;
                }
                break;


            //CASE 9 is break
            case 9:
                end();
                break;


            //Case 1 Option 1 - Tell me a story then.
            case 10:
                npc("Well, when I were a young man we used", "to take fishing trips over to Catherby.", "The fishing over there, now that was something!");
                stage++;
                break;
            case 11:
                npc("Anyway, we decided to do a bit of fishing with our nets,", "I wasn't having the best of days turning up", "nothing but old boots and bits of seaweed.");
                stage++;
                break;
            case 12:
                npc("Then my net suddenly got really heavy!", " I pulled it up... To my amazement", "I'd caught this little chest thing!");
                stage++;
                break;
            case 13:
                npc("Even more amazing was when I opened it", "it contained a diamond the size of a radish!", "That's the best catch I've ever had!");
                stage = 0;
                break;


            //Case 1 Option 2 - Are you entering the fishing competition?
            case 20:
                npc("Ah... the Hemenster fishing competition...");
                stage++;
                break;

            case 21:
                npc(" I know all about that... I won that four years straight!", "I'm too old for that lark now though...");
                stage = 4;
                break;

            //Case 5 Option 1 - I don't suppose you could give me any hints?
            case 22:
                npc("Well, you sometimes get these really big fish in the", "water just by the outflow pipes.");
                stage++;
                break;
            case 23:
                npc("I think they're some kind of carp...");
                stage++;
                break;
            case 24:
                npc("Try to get a spot round there. ", "The best sort of bait for them is red vine worms.");
                stage++;
                break;
            case 25:
                npc("I used to get those from McGrubor's wood, north of", "here. Just dig around in the red vines up there but be", "careful of the guard dogs.");
                stage++;
                break;
            case 26:
                player("There's this weird creepy guy who says he's not a", "vampire using that spot. He keeps winning too.");
                stage++;
                break;
            case 27:
                npc("Ahh well, I'm sure you'll find something to put him off.", "Afer all, there must be a kitchen around here with", "some garlic in it, perhaps in Seers Village or Ardougne.", "If he's pretending to be a vampire then he can pretend");
                stage++;
                break;
            case 28:
                npc("to be scared of garlic!");
                stage++;
                break;
            case 29:
                player("You're right! Thanks Jack!");
                stage = 9;//End
                break;

            //Case 5 Option 2 - That's less competition for me then.
            case 30:
                npc("Why you young whippersnapper!", "If I was twenty years younger I'd show you something that's for sure!");
                stage = 0;
                break;

            //Case 1 Option 3 - Sorry, I don't have time now.
            case 35:
                npc("Sigh... Young people - always in such a rush. ");
                stage = 9;
                break;

            //Case 1 Option 4 - Can I buy one of your fishing rods?
            case 40:
                npc("Of course you can young man. Let's see now...", "I think 5 gold is a fair price for a rod which" , "has won the Fishing contest before eh?");
                stage = 6;
                break;

            //Case 7 Option 1 - Very fair, I'll buy that rod! (player has 5 gp)
            case 42:
                npc("Excellent choice!");
                player.getInventory().remove(new Item(995, 5));
                player.getInventory().add(FishingContest.FISHING_ROD, player);//
                stage = 9;
                break;


            //Case 7 Option 2 - That's too rich for me, I'll go to Catherby.
            case 44:
                npc("If you're sure... passing up an opportunity of a lifetime you are.");
                stage = 9;
                break;

            //Case 1 Option 5 - I've forgotten how to fish, can you remind me?
            case 50:
                npc("Of course! Let me see now... You'll need a rod and bait.", "You can fish with a net too, but not in the competition.");
                stage++;
                break;
            case 51:
                player("Ok... I think I can get those in Catherby.");
                stage++;
                break;
            case 52:
                npc("Then simply find yourself a fishing spot, ", "either in the competition near here, or wherever you can.", "I recommend net fishing in Catherby.");
                stage++;
                break;
            case 53:
                npc("Net or Lure the fish in the fishing spot", "by clicking on it and then be patient...");
                stage++;
                break;
            case 54:
                player("It's that simple?");
                stage++;
                break;
            case 55:
                npc("Yep! Go get em tiger.");
                stage = 0;
                break;

            //Case 7 Option 1 - Very fair, I'll buy that rod! (not enough gp)
            case 60:
                player("I don't have enough money for that,", "I'll go get some and come back.");
                stage++;
                break;
            case 61:
                npc("Right you are. I'll be here. ");
                stage = 9;
                break;
        }
        return true;
    }


    @Override
    public int[] getIds() {
        return new int[] { 230 };
    }
}
