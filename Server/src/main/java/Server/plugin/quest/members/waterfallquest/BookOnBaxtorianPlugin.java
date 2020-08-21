package plugin.quest.members.waterfallquest;

import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.book.Book;
import plugin.dialogue.book.BookLine;
import plugin.dialogue.book.Page;
import plugin.dialogue.book.PageSet;
import core.game.node.entity.player.Player;

/**
 * Handles the book on Baxtorian that nobody will read.
 * @author Splinter
 */
public final class BookOnBaxtorianPlugin extends Book {

	/**
	 * Represents the book id
	 */
	public static int ID = 183764;

	/**
	 * Represents the array of pages for this book.
	 */
	private static final PageSet[] PAGES = new PageSet[] { 
		new PageSet(
				new Page(
						new BookLine("The missing relics", 55), new BookLine("", 56), new BookLine("    Many artefacts of", 57), new BookLine("elven history were lost", 58),
						new BookLine("after the fourth age. The", 59), new BookLine("greatest loss to our", 60), new BookLine("collections of elf history", 61), 
						new BookLine("were the hidden treasures", 62), new BookLine("of Baxtorian.", 63), new BookLine("  Some believe these", 64), new BookLine("treasures are still", 65)),
				new Page(
						new BookLine("unclaimed, but it is more", 66), new BookLine("commonly believed that", 67), new BookLine("dwarf miners recovered", 68), 
						new BookLine("the treasure at the", 69), new BookLine("beginning of the third", 70), new BookLine("age. Another great loss", 71), new BookLine("was Glarial's pebble, a key", 72),
						new BookLine("which allowed her family", 73), new BookLine("to visit her tomb.", 74), new BookLine("    The stone was taken", 75), new BookLine("by a gnome family over a", 76))),
		new PageSet(
				new Page(
						new BookLine("century ago. It is", 55), new BookLine("believed that the gnomes'", 56), new BookLine("descendent Golrie still has", 57), new BookLine("the stone hidden in the", 58),
						new BookLine("caves under the gnome", 59), new BookLine("tree village.", 60), new BookLine("", 61), new BookLine("The sonnet of Baxtorian", 62), new BookLine("", 63), 
						new BookLine("The love between", 64), new BookLine("Baxtorian and Glarial was", 65)), new Page(new BookLine("said to have lasted over a", 66), new BookLine("century. They lived a", 67),
								new BookLine("peaceful life learning and", 68), new BookLine("teaching the laws of", 69), new BookLine("nature. When Baxtorian's", 70), new BookLine("kingdom was invaded by", 71),
								new BookLine("the dark forces he left on", 72), new BookLine("a five year campaign. He", 73), new BookLine("returned to find his", 74), new BookLine("people slaughtered and his", 75),
								new BookLine("wife taken by the enemy.", 76))), 
		new PageSet(
				new Page(
						new BookLine("    After years of", 55), new BookLine("searching for his love he", 56), 
						new BookLine("finally gave up and", 57), new BookLine("returned to the home he", 58), new BookLine("made for Glarial under", 59), new BookLine("the Baxtorian Waterfall.", 60), 
						new BookLine("Once he entered he", 61), new BookLine("never returned. Only", 62), new BookLine("Glarial had the power to", 63), new BookLine("also enter the waterfall.", 64),
						new BookLine("  Since Baxtorian", 65)),
				new Page(new BookLine("entered no one but her", 66), new BookLine("can follow him in, it's as if", 67), new BookLine("the powers of nature still", 68), new BookLine("work to protect him.", 69),
						new BookLine("", 70), new BookLine("The power of nature", 71), new BookLine("", 72), new BookLine("    Glarial and Baxtorian", 73), new BookLine("were masters of nature.", 74),
						new BookLine("Trees would grow, hills", 75), new BookLine("form and rivers flood on", 76))), 
		new PageSet(
				new Page(
						new BookLine("their command. Baxtorian", 55), new BookLine("in particular had", 56), new BookLine("perfected rune lore. It", 57), new BookLine("was said that he could", 58),
						new BookLine("uses the stones to control", 59), new BookLine("water, earth, and air.", 60), new BookLine("", 61), new BookLine("Ode to eternity", 62), new BookLine("", 63),
						new BookLine("A short piece written by", 64), new BookLine("Baxtorian himself.", 65)), 
				new Page(new BookLine("", 66), new BookLine("What care I for this", 67), new BookLine("mortal coil,", 68), new BookLine("where treasures are yet", 69),
						new BookLine("so frail,", 70), new BookLine("for it is you that is my", 71), new BookLine("life blood,", 72), new BookLine("the wine to my holy grail", 73), 
						new BookLine("and if I see the", 74), new BookLine("judgement day,", 75), new BookLine("when the gods fill the air", 76))), 
		new PageSet(
				new Page(
						new BookLine("with dust,", 55), new BookLine("I'll happily choke on your", 56), new BookLine("memory,", 57), new BookLine("as my kingdom turns to", 58), new BookLine("rust", 59))),

	};

	/**
	 * Constructs a new {@code ShieldofArravBook} {@code Object}.
	 */
	public BookOnBaxtorianPlugin(final Player player) {
		super(player, "Book On Baxtorian", 260, PAGES);
	}

	/**
	 * Constructs a new {@code ShieldofArravBook} {@code Object}.
	 */
	public BookOnBaxtorianPlugin() {
		/**
		 * empty.
		 */
	}

	@Override
	public void finish() {
		if (player.getQuestRepository().getQuest(WaterFall.NAME).getStage(player) == 20) {
			player.getQuestRepository().getQuest(WaterFall.NAME).setStage(player, 30);
		}
	}

    @Override
    public void display(Page[] set) {
    	player.lock();
    	player.getInterfaceManager().open(getInterface());
    	player.getPacketDispatch().sendString("Previous", getInterface().getId(), 77);
    	player.getPacketDispatch().sendString("Next", getInterface().getId(), 78);
		if (player.getQuestRepository().getQuest(WaterFall.NAME).getStage(player) == 20) {
			player.getQuestRepository().getQuest(WaterFall.NAME).setStage(player, 30);
		}
    	for (int i = 55; i < 77; i++) {
    		player.getPacketDispatch().sendString("", getInterface().getId(), i);
    	}
    	player.getPacketDispatch().sendString(getName(), getInterface().getId(), 6);
    	for (Page page : set) {
    		for (BookLine line : page.getLines()) {
    			player.getPacketDispatch().sendString(line.getMessage(), getInterface().getId(), line.getChild());
    		}
    	}
	boolean lastPage = index == sets.length - 1;
		if (lastPage) {
	  	 finish();
		}
		player.unlock();
    }

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new BookOnBaxtorianPlugin(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { ID };
	}
}
