package plugin.quest.members;

import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;

/**
 * Represents the <b>Quest</b> priest in peril.
 * @author 'Vexia
 */
@InitializablePlugin
public class PriestInPeril extends Quest {

	/**
	 * Constructs a new {@code PriestInPeril} {@code Object}.
	 * @param player The player to construct the class for.
	 */
	public PriestInPeril() {
		super("Priest in Peril", 99, 98, 1, 302, 0, 1, 100);
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (stage) {
		case 0:
			player.getPacketDispatch().sendString("<col=08088A>I can start this quest by speaking to <col=8A0808>King Roald</col> <col=08088A>in <col=8A0808>Varrock", 275, 4+ 7);
			player.getPacketDispatch().sendString("<col=8A0808>Palace</col>", 275, 5+ 7);
			player.getPacketDispatch().sendString("<col=08088A>I must be able to defeat a <col=8A0808>level 30 enemy</col>", 275, 7+ 7);
			break;
		case 10:
			player.getPacketDispatch().sendString("<str>I spoke to King Roald who asked me to investigate why his", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>friend Priest Drezel has stopped communicating with him.", 275, 5+ 7);
			player.getPacketDispatch().sendString("<col=8A0808>Drezel</col> <col=08088A>lives in a <col=8A0808>temple</col> <col=08088A>to the east of Varrock Palace. I", 275, 7+ 7);
			player.getPacketDispatch().sendString("<col=08088A>should head there and <col=8A0808>investigate</col> <col=08088A>what's happend to him", 275, 8+ 7);
			break;
		case 11:
			player.getPacketDispatch().sendString("<str>I spoke to King Roald who asked me to investigate why his", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>friend Priest Drezel has stopped communicating with him.", 275, 5+ 7);
			player.getPacketDispatch().sendString("<str>I headed to the temple where Drezel lives, but it was all", 275, 6+ 7);
			player.getPacketDispatch().sendString("<str>locked shut. I spoke through the locked door to Drezel.", 275, 7+ 7);
			player.getPacketDispatch().sendString("<col=08088A>He told me that there was an annoying <col=8A0808>dog</col> <col=08088A>below the", 275, 8+ 7);
			player.getPacketDispatch().sendString("<col=08088A>temple, and has asked me to <col=8A0808>kill it</col> <col=08088A>for him.", 275, 9+ 7);
			break;
		case 12:
			player.getPacketDispatch().sendString("<str>I spoke to King Roald who asked me to investigate why his", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>friend Priest Drezel has stopped communicating with him.", 275, 5+ 7);
			player.getPacketDispatch().sendString("<str>I headed to the temple where Drezel lives, but it was all", 275, 6+ 7);
			player.getPacketDispatch().sendString("<str>locked shut. I spoke through the locked door to Drezel.", 275, 7+ 7);
			player.getPacketDispatch().sendString("<str>He told me that there was an annoying dog below the", 275, 8+ 7);
			player.getPacketDispatch().sendString("<str>temple, and asked me to kill it, which I did easily.", 275, 9+ 7);
			player.getPacketDispatch().sendString("<col=08088A>I should tell <col=8A0808>King Roald <col=08088A>everything's fine with <col=8A0808>Drezel <col=08088A>now I", 275, 11+ 7);
			player.getPacketDispatch().sendString("<col=08088A>have killed that <col=8A0808>dog <col=08088A>for him, and claim my <col=8A0808>reward.", 275, 12+ 7);
			break;
		case 13:
			player.getPacketDispatch().sendString("<str>I spoke to King Roald who asked me to investigate why his", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>friend Priest Drezel has stopped communicating with him.", 275, 5+ 7);
			player.getPacketDispatch().sendString("<str>I headed to the temple where Drezel lives, but it was all", 275, 6+ 7);
			player.getPacketDispatch().sendString("<str>locked shut. I spoke through the locked door to Drezel.", 275, 7+ 7);
			player.getPacketDispatch().sendString("<str>He told me that there was an annoying dog below the", 275, 8+ 7);
			player.getPacketDispatch().sendString("<str>temple, and asked me to kill it, which I did easily.", 275, 9+ 7);
			player.getPacketDispatch().sendString("<str>When I told Roald what I had done, he was furious. The", 275, 10+ 7);
			player.getPacketDispatch().sendString("<str>person who told me to kill the dog wasn't Drezel at all!", 275, 11+ 7);
			player.getPacketDispatch().sendString("<col=08088A>I must return to the <col=8A0808>temple <col=08088A>and find out what happend to", 275, 13+ 7);
			player.getPacketDispatch().sendString("<col=08088A>the real <col=8A0808>Drezel<col=08088A>, or the King will have me executed!", 275, 14+ 7);
			break;
		case 14:
			player.getPacketDispatch().sendString("<str>I spoke to King Roald who asked me to investigate why his", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>friend Priest Drezel has stopped communicating with him.", 275, 5+ 7);
			player.getPacketDispatch().sendString("<str>I headed to the temple where Drezel lives, but it was all", 275, 6+ 7);
			player.getPacketDispatch().sendString("<str>locked shut. I spoke through the locked door to Drezel.", 275, 7+ 7);
			player.getPacketDispatch().sendString("<str>He told me that there was an annoying dog below the", 275, 8+ 7);
			player.getPacketDispatch().sendString("<str>temple, and asked me to kill it, which I did easily.", 275, 9+ 7);
			player.getPacketDispatch().sendString("<str>When I told Roald what I had done, he was furious. The", 275, 10+ 7);
			player.getPacketDispatch().sendString("<str>person who told me to kill the dog wasn't Drezel at all!", 275, 11+ 7);
			player.getPacketDispatch().sendString("<str>I returned to the temple and found the real Drezel locked", 275, 12+ 7);
			player.getPacketDispatch().sendString("<str>in a makeshift cell upstairs, guarded by a vampire.", 275, 13+ 7);
			player.getPacketDispatch().sendString("<col=08088A>I need to find the <col=8A0808>key</col> <col=08088A>to his cell and free him!", 275, 15+ 7);
			break;
		case 15:
			player.getPacketDispatch().sendString("<str>I spoke to King Roald who asked me to investigate why his", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>friend Priest Drezel has stopped communicating with him.", 275, 5+ 7);
			player.getPacketDispatch().sendString("<str>I headed to the temple where Drezel lives, but it was all", 275, 6+ 7);
			player.getPacketDispatch().sendString("<str>locked shut. I spoke through the locked door to Drezel.", 275, 7+ 7);
			player.getPacketDispatch().sendString("<str>He told me that there was an annoying dog below the", 275, 8+ 7);
			player.getPacketDispatch().sendString("<str>temple, and asked me to kill it, which I did easily.", 275, 9+ 7);
			player.getPacketDispatch().sendString("<str>When I told Roald what I had done, he was furious. The", 275, 10+ 7);
			player.getPacketDispatch().sendString("<str>person who told me to kill the dog wasn't Drezel at all!", 275, 11+ 7);
			player.getPacketDispatch().sendString("<str>I returned to the temple and found the real Drezel locked", 275, 12+ 7);
			player.getPacketDispatch().sendString("<str>in a makeshift cell upstairs, guarded by a vampire.", 275, 13+ 7);
			player.getPacketDispatch().sendString("<str>I used a key from the monument to open the cell door", 275, 14+ 7);
			player.getPacketDispatch().sendString("<col=08088A>but I still have to do something about that <col=8A0808>vampire", 275, 15+ 7);
			break;
		case 16:
			player.getPacketDispatch().sendString("<str>I spoke to King Roald who asked me to investigate why his", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>friend Priest Drezel has stopped communicating with him.", 275, 5+ 7);
			player.getPacketDispatch().sendString("<str>I headed to the temple where Drezel lives, but it was all", 275, 6+ 7);
			player.getPacketDispatch().sendString("<str>locked shut. I spoke through the locked door to Drezel.", 275, 7+ 7);
			player.getPacketDispatch().sendString("<str>He told me that there was an annoying dog below the", 275, 8+ 7);
			player.getPacketDispatch().sendString("<str>temple, and asked me to kill it, which I did easily.", 275, 9+ 7);
			player.getPacketDispatch().sendString("<str>When I told Roald what I had done, he was furious. The", 275, 10+ 7);
			player.getPacketDispatch().sendString("<str>person who told me to kill the dog wasn't Drezel at all!", 275, 11+ 7);
			player.getPacketDispatch().sendString("<str>I returned to the temple and found the real Drezel locked", 275, 12+ 7);
			player.getPacketDispatch().sendString("<str>in a makeshift cell upstairs, guarded by a vampire.", 275, 13+ 7);
			player.getPacketDispatch().sendString("<str>I used a key from the monument to open the cell door and", 275, 14+ 7);
			player.getPacketDispatch().sendString("<str>used Holy Water to trap the vampire in his coffin.", 275, 15+ 7);
			player.getPacketDispatch().sendString("<col=08088A>I should speak to <col=8A0808>Drezel <col=08088A>again.", 275, 16+ 7);
			break;
		case 17:
			player.getPacketDispatch().sendString("<str>I spoke to King Roald who asked me to investigate why his", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>friend Priest Drezel has stopped communicating with him.", 275, 5+ 7);
			player.getPacketDispatch().sendString("<str>I headed to the temple where Drezel lives, but it was all", 275, 6+ 7);
			player.getPacketDispatch().sendString("<str>locked shut. I spoke through the locked door to Drezel.", 275, 7+ 7);
			player.getPacketDispatch().sendString("<str>He told me that there was an annoying dog below the", 275, 8+ 7);
			player.getPacketDispatch().sendString("<str>temple, and asked me to kill it, which I did easily.", 275, 9+ 7);
			player.getPacketDispatch().sendString("<str>When I told Roald what I had done, he was furious. The", 275, 10+ 7);
			player.getPacketDispatch().sendString("<str>person who told me to kill the dog wasn't Drezel at all!", 275, 11+ 7);
			player.getPacketDispatch().sendString("<str>I returned to the temple and found the real Drezel locked", 275, 12+ 7);
			player.getPacketDispatch().sendString("<str>in a makeshift cell upstairs, guarded by a vampire.", 275, 13+ 7);
			player.getPacketDispatch().sendString("<str>I used a key from the monument to open the cell door and", 275, 14+ 7);
			player.getPacketDispatch().sendString("<str>used Holy Water to trap the vampire in his coffin.", 275, 15+ 7);
			player.getPacketDispatch().sendString("<col=08088A>I should head downstairs to the <col=8A0808>monument <col=08088A>like <col=8A0808>Drezel", 275, 16+ 7);
			player.getPacketDispatch().sendString("<col=08088A>asked me to, and asses what <col=08088A>damage <col=08088A>has been done.", 275, 17+ 7);
			break;
		case 18:
			player.getPacketDispatch().sendString("<str>I spoke to King Roald who asked me to investigate why his", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>friend Priest Drezel has stopped communicating with him.", 275, 5+ 7);
			player.getPacketDispatch().sendString("<str>I headed to the temple where Drezel lives, but it was all", 275, 6+ 7);
			player.getPacketDispatch().sendString("<str>locked shut. I spoke through the locked door to Drezel.", 275, 7+ 7);
			player.getPacketDispatch().sendString("<str>He told me that there was an annoying dog below the", 275, 8+ 7);
			player.getPacketDispatch().sendString("<str>temple, and asked me to kill it, which I did easily.", 275, 9+ 7);
			player.getPacketDispatch().sendString("<str>When I told Roald what I had done, he was furious. The", 275, 10+ 7);
			player.getPacketDispatch().sendString("<str>person who told me to kill the dog wasn't Drezel at all!", 275, 11+ 7);
			player.getPacketDispatch().sendString("<str>I returned to the temple and found the real Drezel locked", 275, 12+ 7);
			player.getPacketDispatch().sendString("<str>in a makeshift cell upstairs, guarded by a vampire.", 275, 13+ 7);
			player.getPacketDispatch().sendString("<str>I used a key from the monument to open the cell door and", 275, 14+ 7);
			player.getPacketDispatch().sendString("<str>used Holy Water to trap the vampire in his coffin.", 275, 15+ 7);
			player.getPacketDispatch().sendString("<str>I followed Drezel downstairs only to find that the Salve", 275, 16+ 7);
			player.getPacketDispatch().sendString("<str>had been contaminated and now needed purifying", 275, 17+ 7);
			int amt = player.getGameAttributes().getAttribute("priest-in-peril:rune", 50+ 7);
			player.getPacketDispatch().sendString("<col=08088A>I need to bring <col=8A0808>" + amt + " <col=08088A>rune essence to undo the damage", 275, 19+ 7);
			player.getPacketDispatch().sendString("<col=08088A>done by the Zamorakians and <col=8A0808>purify the salve", 275, 20+ 7);
			break;
		case 100:
			player.getPacketDispatch().sendString("<str>I spoke to King Roald who asked me to investigate why his", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>friend Priest Drezel has stopped communicating with him.", 275, 5+ 7);
			player.getPacketDispatch().sendString("<str>I headed to the temple where Drezel lives, but it was all", 275, 6+ 7);
			player.getPacketDispatch().sendString("<str>locked shut. I spoke through the locked door to Drezel.", 275, 7+ 7);
			player.getPacketDispatch().sendString("<str>He told me that there was an annoying dog below the", 275, 8+ 7);
			player.getPacketDispatch().sendString("<str>temple, and asked me to kill it, which I did easily.", 275, 9+ 7);
			player.getPacketDispatch().sendString("<str>When I told Roald what I had done, he was furious. The", 275, 10+ 7);
			player.getPacketDispatch().sendString("<str>person who told me to kill the dog wasn't Drezel at all!", 275, 11+ 7);
			player.getPacketDispatch().sendString("<str>I returned to the temple and found the real Drezel locked", 275, 12+ 7);
			player.getPacketDispatch().sendString("<str>in a makeshift cell upstairs, guarded by a vampire.", 275, 13+ 7);
			player.getPacketDispatch().sendString("<str>I used a key from the monument to open the cell door and", 275, 14+ 7);
			player.getPacketDispatch().sendString("<str>used Holy Water to trap the vampire in his coffin.", 275, 15+ 7);
			player.getPacketDispatch().sendString("<str>I followed Drezel downstairs only to find that the Salve", 275, 16+ 7);
			player.getPacketDispatch().sendString("<str>had been contaminated and now needed purifying", 275, 17+ 7);
			player.getPacketDispatch().sendString("<str>I brought Drezel fifty rune essences and the", 275, 18+ 7);
			player.getPacketDispatch().sendString("<str>contaminants were dissolved from the Salve, and Drezel", 275, 19+ 7);
			player.getPacketDispatch().sendString("<str>Rewarded me for all of my help with an ancient holy weapon", 275, 20+ 7);
			player.getPacketDispatch().sendString("<str>to fight with.", 275, 21+ 7);
			player.getPacketDispatch().sendString("<col=FF0000>QUEST COMPLETE!", 275, 23+ 7);
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getGameAttributes().removeAttribute("priest-in-peril:rune");
		player.getPacketDispatch().sendString("1 Quest Point", 277, 8+ 2);
		player.getPacketDispatch().sendString("1406 Prayer XP", 277, 9+ 2);
		player.getPacketDispatch().sendString("Wolfbane dagger", 277, 10+ 2);
		player.getPacketDispatch().sendString("Route to Canifis", 277, 11+ 2);
		if (!player.getInventory().add(new Item(2952, 1))) {
			GroundItemManager.create(new Item(2952, 1), player.getLocation(), player);
		}
		player.removeAttribute("priest_in_peril:key");
		player.removeAttribute("priest-in-peril:rune");
		player.getSkills().addExperience(Skills.PRAYER, 1406);
		player.getPacketDispatch().sendItemZoomOnInterface(2952, 240, 277, 3+ 2);
	}

	@Override
	public Quest newInstance(Object object) {
		return this;
	}
}
