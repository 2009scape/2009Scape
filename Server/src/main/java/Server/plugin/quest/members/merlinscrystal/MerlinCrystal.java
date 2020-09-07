package plugin.quest.members.merlinscrystal;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * Represents the merlin's crystal quest.
 * @author Splinter
 */
@InitializablePlugin
public final class MerlinCrystal extends Quest {

	/**
	 * Constructs a new {@code MerlinCrystal} {@code Object}.
	 * @param player the player.
	 */
	public MerlinCrystal() {
		super("Merlin's Crystal", 87, 86, 6, 14, 0, 1, 7);
	}

	@Override
	public void drawJournal(Player player, int stage) { 
		super.drawJournal(player, stage);
		switch (stage) {
		case 0:// not started
			line(player, "<blue>I can start this quest by speaking to <red>King Arthur<blue> at<n> <red>Camelot Castle<blue>, just <red>North West of Catherby<n><blue>I must be able to defeat a <red>level 37 enemy", 11);
			break;
		case 10:// after talking to arthur
			line(player, "<str>I spoke to King Arthur and he said I would be worthy of <n><str>becoming a Knight of the Round Table if I could free Merlin<n><str>from a giant crystal that he has been trapped in.</str>" + "<br><br><br><blue> I should ask the <red>other Knights<blue> if they have any <red>advice<blue> for<n><blue>me on how I should go about doing this.", 11);
			break;
		case 20:// upon talking to kay/gawain
			line(player, "<str>I spoke to King Arthur and he said I would be worthy of <n><str>becoming a Knight of the Round Table if I could free Merlin<n><str>from a giant crystal that he has been trapped in.</str>" + "<n><blue>Gawain told me it was the work of <red>Morgan Le Faye<blue>.", 11);
			break;
		case 30:// after talking to lancelot
		case 40:// after talking to arhein
			line(player, "<str>I spoke to King Arthur and he said I would be worthy of <n><str>becoming a Knight of the Round Table if I could free Merlin<n><str>from a giant crystal that he has been trapped in.</str>" + "<n><str>Gawain told me it was the work of Morgan Le Faye.<n><str>I told Lancelot of Gawain's suspicions, and he told me that<n><str>Mordred's Fortress is not completely impenetrable." + "<n><blue>There might be a way to enter with a <red>delivery by sea...", 11);
			break;
		case 50:// defeated mordred
			line(player, "<str>I spoke to King Arthur and he said I would be worthy of <n><str>becoming a Knight of the Round Table if I could free Merlin<n><str>from a giant crystal that he has been trapped in.</str>" + "<n><str>Gawain told me it was the work of Morgan Le Faye.<n><str>I told Lancelot of Gawain's suspicions, and he told me that<n><str>Mordred's Fortress is not completely impenetrable." + "<n><str>There might be a way to enter with a delivery by sea...<n><blue>I have broken into <red>Keep Le Faye<blue> and slain <red>Sir Mordred<blue>.<n>" + "<red>Morgan Le Faye<blue> disclosed the secret of how to free <red>Merlin<blue>.<n><blue> I'll need the sword <red>Excalibur<blue> and a <red>lit black candle<blue> first." + "<n><blue>According to Morgan, the <red>Lady of the Lake<blue> has the<n><red>sword<blue> that can be used to free Merlin.", 11);
			break;
		case 60:// talking to lady of the lake
			line(player, "<str>I spoke to King Arthur and he said I would be worthy of <n><str>becoming a Knight of the Round Table if I could free Merlin<n><str>from a giant crystal that he has been trapped in.</str>" + "<n><str>Gawain told me it was the work of Morgan Le Faye.<n><str>I told Lancelot of Gawain's suspicions, and he told me that<n><str>Mordred's Fortress is not completely impenetrable." + "<n><str>There might be a way to enter with a delivery by sea...<n><str>I have broken into Keep Le Faye and slain Sir Mordred.<n>" + "<str>Morgan Le Faye disclosed the secret of how to free Merlin.<n><str>I'll need the sword Excalibur and a lit black candle.<n>" + "<red>The Lady of the Lake<blue> told me she had the <red>Excalibur<n><blue> but I'd have to meet her in the <red>jewellery store" + "<blue> in <red>Port Sarim<n><blue>before she'd give it to me.", 11);
			break;
		case 70:// after actually obtaining excalibur
			line(player, "<str>I spoke to King Arthur and he said I would be worthy of <n><str>becoming a Knight of the Round Table if I could free Merlin<n><str>from a giant crystal that he has been trapped in.</str>" + "<n><str>Gawain told me it was the work of Morgan Le Faye.<n><str>I told Lancelot of Gawain's suspicions, and he told me that<n><str>Mordred's Fortress is not completely impenetrable." + "<n><str>There might be a way to enter with a delivery by sea...<n><str>I have broken into Keep Le Faye and slain Sir Mordred.<n>" + "<str>Morgan Le Faye disclosed the secret of how to free Merlin.<n><str>I'll need the sword Excalibur and a lit black candle.<n>" + "<str>The Lady of the Lake told me she had the Excalibur<n> <str>but I'd have to visit the jewellery store" + " in Port Sarim first.</str><n>" + "<blue>I have the sword <red>Excalibur<blue> and can free <red>Merlin<blue> from the crystal.<n>" + "<blue>I must now memorize an incantation inscribed on a <red>Chaos altar<n><blue>that is located somewhere in the world in order to banish<n>" + "<blue>the spirit." + "<n><blue>I will also need to find some <red>bat bones<blue> and drop them<n><blue>on the magical symbol to the <red>North East of Camelot<blue>.<n>" + "<blue>after I have learned the incantation.", 11);
			break;
		case 80:// after reading the incantation
			line(player, "<str>I spoke to King Arthur and he said I would be worthy of <n><str>becoming a Knight of the Round Table if I could free Merlin<n><str>from a giant crystal that he has been trapped in.</str>" + "<n><str>Gawain told me it was the work of Morgan Le Faye.<n><str>I told Lancelot of Gawain's suspicions, and he told me that<n><str>Mordred's Fortress is not completely impenetrable." + "<n><str>There might be a way to enter with a delivery by sea...<n><str>I have broken into Keep Le Faye and slain Sir Mordred.<n>" + "<str>Morgan Le Faye disclosed the secret of how to free Merlin.<n><str>I'll need the sword Excalibur and a lit black candle.<n>" + "<str>The Lady of the Lake told me she had the Excalibur<n> <str>but I'd have to visit the jewellery store" + " in Port Sarim first.<n>" + "<str>I have the sword Excalibur and can free Merlin from the crystal.<n>" + "<str>I must now memorize an incantation inscribed on a Chaos altar<n><str>that is located somewhere in the world in order to banish<n>" + "<str>the spirit.<n>" + "<blue> I managed to find the <red>Chaos Altar<blue> that Morgan described.<n>" + "<blue>The incantation is 'Snarthon Candtrick Termanto'." + "<n><blue>I now need to find some <red>bat bones<blue> and drop them<n><blue>on the magical symbol to the <red>North East of Camelot<blue>.", 11);
			break;
		case 90:
			line(player, "<str>I spoke to King Arthur and he said I would be worthy of <n><str>becoming a Knight of the Round Table if I could free Merlin<n><str>from a giant crystal that he has been trapped in.</str>" + "<n><str>Gawain told me it was the work of Morgan Le Faye.<n><str>I told Lancelot of Gawain's suspicions, and he told me that<n><str>Mordred's Fortress is not completely impenetrable." + "<n><str>There might be a way to enter with a delivery by sea...<n><str>I have broken into Keep Le Faye and slain Sir Mordred.<n>" + "<str>Morgan Le Faye disclosed the secret of how to free Merlin.<n><str>I'll need the sword Excalibur and a lit black candle.<n>" + "<str>The Lady of the Lake told me she had the Excalibur<n> <str>but I'd have to visit the jewellery store" + " in Port Sarim first.<n>" + "<str>I have the sword Excalibur and can free Merlin from the crystal.<n>" + "<str>I must now memorize an incantation inscribed on a Chaos altar<n><str>that is located somewhere in the world in order to banish<n>" + "<str>the spirit.<n><blue>Now is your time to free <red>Merlin!", 11);
			break;
		case 99:
			line(player, "<str>I spoke to King Arthur and he said I would be worthy of <n><str>becoming a Knight of the Round Table if I could free Merlin<n><str>from a giant crystal that he has been trapped in.</str>" + "<n><str>Gawain told me it was the work of Morgan Le Faye.<n><str>I told Lancelot of Gawain's suspicions, and he told me that<n><str>Mordred's Fortress is not completely impenetrable." + "<n><str>There might be a way to enter with a delivery by sea...<n><str>I have broken into Keep Le Faye and slain Sir Mordred.<n>" + "<str>Morgan Le Faye disclosed the secret of how to free Merlin.<n><str>I'll need the sword Excalibur and a lit black candle.<n>" + "<str>The Lady of the Lake told me she had the Excalibur<n> <str>but I'd have to visit the jewellery store" + " in Port Sarim first.<n>" + "<str>I have the sword Excalibur and can free Merlin from the crystal.<n>" + "<str>I must now memorize an incantation inscribed on a Chaos altar<n><str>that is located somewhere in the world in order to banish<n>" + "<str>the spirit.<n><blue>Speak to <red>King Arthur<blue> for a reward.", 11);
			break;
		case 100:
			line(player, "<str>You helped King Arthur free Merlin from the crystal.<n><n><col=FF0000>QUEST COMPLETE!</col>", 11);
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getPacketDispatch().sendString("6 Quest Points", 277, 8+ 2);
		player.getPacketDispatch().sendString("Excalibur", 277, 9+ 2);
		player.getPacketDispatch().sendItemZoomOnInterface(35, 235, 277, 3+ 2);
		player.getQuestRepository().syncronizeTab(player);
	}

	@Override
	public boolean hasRequirements(Player player) {
		return true;
	}

	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugins(new CrateCutscenePlugin(), new MerlinCrystalPlugin(), new ArheinShopDialogue(), new BeggarDialogue(), new CandleMakerDialogue(), new KingArthurDialogue(), new MerlinCrystalOptionPlugin(), new SirKayDialogue(), new SirLancelotDialogue(), new SirLucan(), new SirMordredNPC(), new SirPalomedes(), new TheLadyOfTheLake(), new ThrantaxDialogue());
		return this;
	}

}
