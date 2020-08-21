package plugin.quest.free;

import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.link.quest.Quest;

/**
 * Represents the romeo and juliet quest.
 * @author 'Vexia
 */
@InitializablePlugin
public class RomeoJuliet extends Quest {

	/**
	 * Constructs a new {@code RomeoJuliet} {@code Object}.
	 * @param player The player to construct the class for.
	 */
	public RomeoJuliet() {
		super("Romeo & Juliet", 26, 25, 5, 144, 0, 1, 100);
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (getStage(player)) {
		case 0:
			line(player, BLUE + "I can start this quest by speaking to " + RED + "Romeo " + BLUE + "in " + RED + "Varrock", 4 + 7);
			line(player, BLUE + "central square by the " + RED + "fountain.", 5+ 7);
			break;
		case 10:
			line(player, "<str>I have agreed to find Juliet for Romeo and tell her how he", 4+ 7);
			line(player, "<str>feels. For some reason he can't just do this himself.", 5+ 7);
			line(player, BLUE + "All I need to do now is find " + RED + "Juliet.", 6+ 7);
			break;
		case 20:
			line(player, "<str>I have agreed to find Juliet for Romeo and tell her how he", 4+ 7);
			line(player, "<str>feels. For some reason he can't just do this himself.", 5+ 7);
			line(player, "<str>I found Juliet on the Western edge of Varrock, and told", 6+ 7);
			line(player, "<str>her about Romeo. She gave me a message + 7+ 7); take back", 7+ 7);
			line(player, BLUE + "I should take the " + RED + "message " + BLUE + "from" + RED + " Juliet " + BLUE + "to" + RED + " Romeo.", 8+ 7);
			break;
		case 30:
			line(player, "<str>I have agreed to find Juliet for Romeo and tell her how he", 4+ 7);
			line(player, "<str>feels. For some reason he can't just do this himself.", 5+ 7);
			line(player, "<str>I found Juliet on the Western edge of Varrock, and told", 6+ 7);
			line(player, "<str>her about Romeo. She gave me a message to take back", 7+ 7);
			line(player, "<str>I delivered the message to Romeo, and he was sad to hear", 8+ 7);
			line(player, "<str>that Juliet's father opposed their marriage. However, he", 9+ 7);
			line(player, "<str>said that Father Lawrence might be able to overcome this.", 10+ 7);
			line(player, BLUE + "I should find" + RED + " Father Lawrence " + BLUE + "and see how we can help.", 11+ 7);
			break;
		case 40:
			line(player, "<str>I have agreed to find Juliet for Romeo and tell her how he", 4+ 7);
			line(player, "<str>feels. For some reason he can't just do this himself.", 5+ 7);
			line(player, "<str>I found Juliet on the Western edge of Varrock, and told", 6+ 7);
			line(player, "<str>her about Romeo. She gave me a message to take back", 7+ 7);
			line(player, "<str>I delivered the message to Romeo, and he was sad to hear", 8+ 7);
			line(player, "<str>that Juliet's father opposed their marriage. However, he", 9+ 7);
			line(player, "<str>said that Father Lawrence might be able to overcome this.", 10+ 7);
			line(player, "<str>I found Father Lawrence and he suggested the use of a", 11+ 7);
			line(player, "<str>potion to fool Juliet's father that she is dead so that", 12+ 7);
			line(player, "<str>Romeo and Juliet can be together in peace.", 13+ 7);
			line(player, BLUE + "I need to find the " + RED + "Apothecary" + " " + BLUE + "to make a " + RED + "cadava potion.", 14+ 7);
			break;
		case 50:
			line(player, "<str>I have agreed to find Juliet for Romeo and tell her how he", 4+ 7);
			line(player, "<str>feels. For some reason he can't just do this himself.", 5+ 7);
			line(player, "<str>I found Juliet on the Western edge of Varrock, and told", 6+ 7);
			line(player, "<str>her about Romeo. She gave me a message to take back", 7+ 7);
			line(player, "<str>I delivered the message to Romeo, and he was sad to hear", 8+ 7);
			line(player, "<str>that Juliet's father opposed their marriage. However, he", 9+ 7);
			line(player, "<str>said that Father Lawrence might be able to overcome this.", 10+ 7);
			line(player, "<str>I found Father Lawrence and he suggested the use of a", 11+ 7);
			line(player, "<str>potion to fool Juliet's father that she is dead so that", 12+ 7);
			line(player, "<str>Romeo and Juliet can be together in peace.", 13+ 7);
			line(player, "<str>I went to the Apothecary regarding making this cadava", 14+ 7);
			line(player, "<str>potion, and he told me to bring him some cadava berries", 15+ 7);
			if (!player.getInventory().contains(753, 1)) {
				line(player, BLUE + "I will have to find some " + RED + "Cadava berries" + BLUE + " somewhere!", 16+ 7);
			} else {
				line(player, BLUE + "I should take these " + RED + "cadava berries" + BLUE + " to the " + RED + "Apothechary.", 16+ 7);
			}
			break;
		case 60:
			line(player, "<str>I have agreed to find Juliet for Romeo and tell her how he", 4+ 7);
			line(player, "<str>feels. For some reason he can't just do this himself.", 5+ 7);
			line(player, "<str>I found Juliet on the Western edge of Varrock, and told", 6+ 7);
			line(player, "<str>her about Romeo. She gave me a message to take back", 7+ 7);
			line(player, "<str>I delivered the message to Romeo, and he was sad to hear", 8+ 7);
			line(player, "<str>that Juliet's father opposed their marriage. However, he", 9+ 7);
			line(player, "<str>said that Father Lawrence might be able to overcome this.", 10+ 7);
			line(player, "<str>I found Father Lawrence and he suggested the use of a", 11+ 7);
			line(player, "<str>potion to fool Juliet's father that she is dead so that", 12+ 7);
			line(player, "<str>Romeo and Juliet can be together in peace.", 13+ 7);
			line(player, "<str>I went to the Apothecary regarding making this cadava", 14+ 7);
			line(player, "<str>potion, and he told me to bring him some cadava berries", 15+ 7);
			line(player, BLUE + "I should take this " + RED + "cadava potion " + BLUE + "to " + RED + "Juliet.", 16+ 7);
			break;
		case 70:
			line(player, "<str>I have agreed to find Juliet for Romeo and tell her how he", 4+ 7);
			line(player, "<str>feels. For some reason he can't just do this himself.", 5+ 7);
			line(player, "<str>I found Juliet on the Western edge of Varrock, and told", 6+ 7);
			line(player, "<str>her about Romeo. She gave me a message to take back", 7+ 7);
			line(player, "<str>I delivered the message to Romeo, and he was sad to hear", 8+ 7);
			line(player, "<str>that Juliet's father opposed their marriage. However, he", 9+ 7);
			line(player, "<str>said that Father Lawrence might be able to overcome this.", 10+ 7);
			line(player, "<str>I found Father Lawrence and he suggested the use of a", 11+ 7);
			line(player, "<str>potion to fool Juliet's father that she is dead so that", 12+ 7);
			line(player, "<str>Romeo and Juliet can be together in peace.", 13+ 7);
			line(player, "<str>I went to the Apothecary regarding making this cadava", 14+ 7);
			line(player, "<str>potion, and he told me to bring him some cadava berries", 15+ 7);
			line(player, "<str>After the Apothecary made me the potion, I delivered it to", 16+ 7);
			line(player, "<str>Juliet. She asked me to tell Romeo the plan.", 17+ 7);
			line(player, BLUE + "I have to find " + RED + "Romeo" + BLUE + " and tell him what's happened.", 18+ 7);
			break;
		case 100:
			line(player, "<str>Romeo and Juliet can be together in peace.", 4+ 7);
			line(player, "<str>I went to the Apothecary regarding making this cadava", 5+ 7);
			line(player, "<str>potion, and he told me to bring him some cadava berries.", 6+ 7);
			line(player, "<str>After the Apothecary made me the potion, I delivered it to", 7+ 7);
			line(player, "<str>Juliet. She asked me to tell Romeo the plan.", 8+ 7);
			line(player, "<str>I told Romeo what was going to happen, but I'm not exactly", 9+ 7);
			line(player, "<str>sure he understood what was happening. Ah well, I was", 10+ 7);
			line(player, "<str>rewarded for all of my help regardless.", 11+ 7);
			line(player, "<col=FF0000>QUEST COMPLETE!", 12+ 7);
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getPacketDispatch().sendString("5 Quest Points", 277, 8 + 2);
		player.getPacketDispatch().sendItemZoomOnInterface(756, 240, 277, 3 + 2);
		player.getQuestRepository().syncronizeTab(player);
	}

	@Override
	public Quest newInstance(Object object) {
		return this;
	}
}
