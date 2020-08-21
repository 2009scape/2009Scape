package plugin.quest.members.witchshouse;

import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.book.Book;
import plugin.dialogue.book.BookLine;
import plugin.dialogue.book.Page;
import plugin.dialogue.book.PageSet;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;

/**
 * Created for 2009Scape
 * User: Ethan Kyle Millard
 * Date: March 30, 2020
 * Time: 2:49 PM
 */
@InitializablePlugin
public class WitchsDiaryBook extends Book {

    /**
     * Represents the shield of arrav book id.
     */
    public static int ID = 4501993;

    /**
     * Represents the array of pages for this book.
     */
    private static final PageSet[] PAGES = new PageSet[]{
            new PageSet(
                new Page(new BookLine("<col=ff0000>2nd of Pentember", 55),
                    new BookLine("Experiment is growing", 56),
                    new BookLine("larger daily. Making", 57),
                    new BookLine("excellent progress now. I", 58),
                    new BookLine("am currently feeding it", 59),
                    new BookLine("on a mixture of fungus,", 60),
                    new BookLine("tar and clay.", 61),
                    new BookLine("It seems to like this", 62),
                    new BookLine("combination alot!", 63)),
                new Page(new BookLine("<col=ff0000>3rd of Pentember", 66),
                    new BookLine("Experiment still going", 67),
                    new BookLine("extremely well. Moved it", 68),
                    new BookLine("to the wooden garden", 69),
                    new BookLine("shed; it does too much", 70),
                    new BookLine("damage in the house! it", 71),
                    new BookLine("is getting very strong", 72),
                    new BookLine("now, but unfortunately is", 73),
                    new BookLine("not too intelligent yet. It", 74),
                    new BookLine("has a really mean stare", 75),
                    new BookLine("too!", 76))),
            new PageSet(
                new Page(new BookLine("<col=ff0000>4th of Pentember", 55),
                    new BookLine("Sausages for dinner", 56),
                    new BookLine("tonight! Lovely!", 57),
                    new BookLine("<col=ff0000>5th of Pentember", 59),
                    new BookLine("A guy called Professor", 60),
                    new BookLine("Oddenstein installed a", 61),
                    new BookLine("new security system for", 62),
                    new BookLine("me in the basement. He", 63),
                    new BookLine("seems to have a lot of", 64),
                    new BookLine("good security ideas.", 65)),
                new Page(new BookLine("<col=ff0000>6th of Pentember", 66),
                    new BookLine("Don't want people getting", 67),
                    new BookLine("into back garden to see", 68),
                    new BookLine("the experiment. Professor", 69),
                    new BookLine("Oddenstein is fitting me a", 70),
                    new BookLine("new security system,", 71),
                    new BookLine("after his successful", 72),
                    new BookLine("installation in the cellar.", 73))),
            new PageSet(
                new Page(new BookLine("<col=ff0000>7th of Pentember", 55),
                    new BookLine("That pesky kid keeps", 56),
                    new BookLine("kicking his ball into my", 57),
                    new BookLine("garden. I swear, if he", 58),
                    new BookLine("does it AGAIN, I'm going", 59),
                    new BookLine("to lock his ball away in", 60),
                    new BookLine("the shed.", 61),
                    new BookLine("<col=ff0000>8th of Pentember.", 63),
                    new BookLine("The security system is", 64),
                    new BookLine("done. By Zamorak! Wow,", 65)),
            new Page(new BookLine("is it contrived! Now, to", 66),
                    new BookLine("open my own back door,", 67),
                    new BookLine("I lure a mouse out of a", 68),
                    new BookLine("hole in the back porch, I", 69),
                    new BookLine("fit a magic curved piece", 70),
                    new BookLine("of metal to the harness", 71),
                    new BookLine("on its back, the mouse", 72),
                    new BookLine("goes back in the hole, and", 73),
                    new BookLine("the door unlocks! The", 74),
                    new BookLine("prof tells me that this is", 75),
                    new BookLine("cutting edge technology!", 76))),
            new PageSet(
                new Page(new BookLine("As an added precaution I", 55),
                    new BookLine("have hidden the key to", 56),
                    new BookLine("the shed in a secret", 57),
                    new BookLine("compartment of the", 58),
                    new BookLine("fountain in the garden.", 59),
                    new BookLine("No one will ever look", 60),
                    new BookLine("there.", 61),
                    new BookLine("<col=ff0000>9th of Pentember", 63),
                    new BookLine("Still can't think of a good", 64),
                    new BookLine("name of 'The", 65)),
                new Page(new BookLine("Experiment'. Leaning", 66),
                    new BookLine("Towards 'fritz'... Although", 67),
                    new BookLine("am considering Lucy as", 68),
                    new BookLine("it reminds me of my", 69),
                    new BookLine("Mother!", 70))),
    };

    /**
     * Constructs a new {@code WitchsDiaryBook} {@code Object}.
     */
    public WitchsDiaryBook(final Player player) {
        super(player, "Witches' Diary", 2408, PAGES);
    }

    /**
     * Constructs a new {@code WitchsDiaryBook} {@code Object}.
     */
    public WitchsDiaryBook() {
        /**
         * empty.
         */
    }

    @Override
    public void finish() {
//        if (player.getQuestRepository().getQuest("Shield of Arrav").getStage(player) == 10) {
//            player.getQuestRepository().getQuest("Shield of Arrav").setStage(player, 20);
//        }
    }

    @Override
    public void display(Page[] set) {
        player.lock();
        player.getInterfaceManager().open(getInterface());
        for (int i = 55; i < 77; i++) {
            player.getPacketDispatch().sendString("", getInterface().getId(), i);
        }
        player.getPacketDispatch().sendString(getName(), getInterface().getId(), 6);
        player.getPacketDispatch().sendString("", getInterface().getId(), 77);
        player.getPacketDispatch().sendString("", getInterface().getId(), 78);
        for (Page page : set) {
            for (BookLine line : page.getLines()) {
                player.getPacketDispatch().sendString(line.getMessage(), getInterface().getId(), line.getChild());
            }
        }
        player.getPacketDispatch().sendInterfaceConfig(getInterface().getId(), 51, index < 1 ? true : false);
        boolean lastPage = index == sets.length - 1;
        player.getPacketDispatch().sendInterfaceConfig(getInterface().getId(), 53, lastPage ? true : false);
        if (lastPage) {
            finish();
        }
        player.unlock();
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new WitchsDiaryBook(player);
    }

    @Override
    public int[] getIds() {
        return new int[]{ID};
    }
}