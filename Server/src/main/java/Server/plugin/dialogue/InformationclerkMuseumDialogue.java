package plugin.dialogue;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.game.world.GameWorld;
import core.tools.ItemNames;

/**
 * Represents the information clerk museum dialogue plugin
 *
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class InformationclerkMuseumDialogue extends DialoguePlugin {

    /**
     * Constructs a new {@code InformationclerkMuseumDialogue} {@code Object}.
     */
    public InformationclerkMuseumDialogue() {
        /**
         * empty.
         */
    }

    /**
     * Constructs a new {@code InformationclerkMuseumDialogue} {@code Object}.
     *
     * @param player the player.
     */
    public InformationclerkMuseumDialogue(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new InformationclerkMuseumDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        npc("Welcome to Varrock Museum. How can I help you today?");
        stage = 0;
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {
            case 150:
                npc("Of course. The Dig Site exhibit has several display cases", "of finds discovered on the Dig Site to the east of Varrock.");
                stage = 151;
                break;
            case 151:
                npc("As you've passed your Earth Science exams at the Dig", "Site, you can go through into the cleaning area and clean", "off some finds. This will help our Dig Site display floor to", "give a more accurate view of life back in the 3rd and fth");
                stage = 152;
                break;
            case 152:
                npc("Ages, as well as earning you Kudos. If you'd like to know", "more about cleaning finds, just ask the archaeologists.");
                stage = 153;
                break;
            case 153:
                options("Ask about something else.", "Bye");
                stage = 154;
                break;
            case 154:
                switch (buttonId) {
                    case 1:
                        player("What else can you inform me on?");
                        stage = 0;
                        break;
                    case 2:
                        player("Bye!");
                        stage = 200;
                        break;
                }
                break;
            case 200:
                end();
                break;
            case 170:
                npc("Why, yes. The Timeline exhibit has lots of display cases", "showing things from the beginning of time right up to the", "present day.");
                stage = 171;
                break;
            case 171:
                npc("I know you've helped out a bit in the Timeline exhibit", "upstairs, but I'm sure you can help more. When you're out", "on your travels being a vrave adventurer, remember that", "you can come back to the Museum after some quests to");
                stage = 172;
                break;
            case 172:
                npc("let us know important historical facts. This will help us to", "update the displays and make the Museum a more", "informative place! You'll earn yourself Kudos too.");
                stage = 173;
                break;
            case 173:
                player("Okay, thanks. One more question: why are the display", "numbers all out of sequence?");
                stage = 174;
                break;
            case 174:
                npc("Ahh, that's due to the numbering being done as we were", "constructing the cases and putting the displays in them,", "then suffling them into the right places. We thought", "rather than renumbering them all - such a boring job,");
                stage = 175;
                break;
            case 175:
                npc("writing labels - we'd leave it. They all have unique numbers", "and future displays would mess up the consecutive", "numbering anyway.");
                stage = 176;
                break;
            case 176:
                player("Ahhh, I see.");
                stage = 153;
                break;
            case 180:
                npc("Why, yes. The Natural History exhibit has displays of", "various creatures you can find around " + GameWorld.getSettings().getName() + ".");
                stage = 181;
                break;
            case 181:
                npc("I see you have already demonstrated some of your", "knowledge of the natural world in the Natural History", "exhibit, so why not pop down and so some more quizzes", "with Orlando! You can earn Kudos at the same time.");
                stage = 182;
                break;
            case 182:
                options("But what's Natural History got to do with existing animals?", "Ask about something else.", "Bye");
                stage = 183;
                break;
            case 183:
                switch (buttonId) {
                    case 1:
                        player("But what's natural history got to do with existing animals?");
                        stage = 184;
                        break;
                    case 2:
                        player("What else can you inform me on?");
                        stage = 0;
                        break;
                    case 3:
                        player("Bye!");
                        stage = 200;
                        break;
                }
                break;
            case 184:
                npc("The study of nautral history is simply the study of the", "history of the species. The species doesn't neccasrily", "need to be an extinct one.");
                stage = 153;
                break;
            case 230:
                npc("Kudos is a measure of how much you've assisted the", "Museum. The more information you give us, Dig Site", "finds that you clean and quizzes you solve, the", "higher your Kudos.");
                stage = 231;
                break;
            case 231:
                player("But what's it for?");
                stage = 232;
                break;
            case 232:
                npc("Well, recently we found a rather interesting island to the", "north of Morytania. We believe that it may be of", "archaeological significance. I suspect we'll be looking for", "qualified archaeologists once we have constructed out");
                stage = 233;
                break;
            case 233:
                npc("canal and barge. So, we're using Kudos as a measure of", "who is willing and able to help us here at the Museum, so", "they can then be invited on our dig son the new island.");
                stage = 234;
                break;
            case 234:
                player("Would I qualify, then?");
                stage = 235;
                break;
            case 235:
                npc("Unfortunately, you haven't earned enough Kudos yet, so", "you aren't qualified to help us on the dig. If you're", "interested in helping us out and getting that Kudos, simply", "help out around the museum.");
                stage = 236;
                break;
            case 236:
                player("Okay, thanks.");
                stage = 237;
                break;
            case 237:
                end();
                break;
            case 0:
                interpreter.sendOptions("What would you like to do?",
                        "Take a map of the Museum.",
                        "Find out about the Dig Site exhibit.",
                        "Find out about the Timeline exhibit.",
                        "Find out about the Natural History exhibit.",
                        "Find out about Kudos.");
                stage = 1;
                break;
            case 1:

                switch (buttonId) {
                    case 1:
                        sendDialogue("You reach and take a map of the Museum.");
                        player.getInventory().add(new Item(ItemNames.MUSEUM_MAP_11184, 1));
                        stage = 999;
                        break;
                    case 2:
                        player("Could you tell me about the Dig Site exhibit please?");
                        stage = 150;
                        break;
                    case 3:
                        player("Could you tell me about the Timeline exhibit please?");
                        stage = 170;
                        break;
                    case 4:
                        player("Could you tell me about the Natural History exhibit", "please?");
                        stage = 180;
                        break;
                    case 5:
                        npc("What is Kudos?");
                        stage = 230;
                        break;

                }

                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[]{5938};
    }
}
