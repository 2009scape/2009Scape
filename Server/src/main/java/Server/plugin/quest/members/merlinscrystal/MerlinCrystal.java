package plugin.quest.members.merlinscrystal;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
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
     */
    public MerlinCrystal() {
        super(
            "Merlin's Crystal",
            87,
            86,
            6,
            14, 0, 1, 7
        );
    }

    @Override
    public void drawJournal(Player player, int stage) {
        super.drawJournal(player, stage);
        switch (stage) {
            case 0:
                writeJournal(player,
                        "I can start this quest by speaking to <red>King Arthur<blue> at",
                        "<red>Camelot Castle<blue>, just <red>North West of Catherby",
                        "I must be able to defeat a <red>level 37 enemy");
                break;
            case 10: // After talking to Arthur
                writeJournal(player,
                        "<str>I spoke to King Arthur and he said I would be worthy of",
                        "<str>becoming a Knight of the Round Table if I could free Merlin",
                        "<str>from a giant crystal that he has been trapped in.",
                        "",
                        "<blue>I should ask the <red>other Knights<blue> if they have any <red>advice<blue> for",
                        "<blue>me on how I should go about doing this.");
                break;
            case 20: // After talking to Gawain
                writeJournal(player,
                        "<str>I spoke to King Arthur and he said I would be worthy of ",
                        "<str>becoming a Knight of the Round Table if I could free Merlin",
                        "<str>from a giant crystal that he has been trapped in.",
                        "<blue>Gawain told me it was the work of <red>Morgan Le Faye<blue>.");
                break;
            case 30: // After talking to Lancelot
            case 40: // After talking to Arhein
                writeJournal(player,
                        "<str>I spoke to King Arthur and he said I would be worthy of ",
                        "<str>becoming a Knight of the Round Table if I could free Merlin",
                        "<str>from a giant crystal that he has been trapped in.",
                        "<str>Gawain told me it was the work of Morgan Le Faye.",
                        "<str>I told Lancelot of Gawain's suspicions, and he told me that",
                        "<str>Mordred's Fortress is not completely impenetrable.",
                        "<blue>There might be a way to enter with a <red>delivery by sea...");
                break;
            case 50: // Defeated Mordred
                writeJournal(player,
                        "<str>I spoke to King Arthur and he said I would be worthy of ",
                        "<str>becoming a Knight of the Round Table if I could free Merlin",
                        "<str>from a giant crystal that he has been trapped in.",
                        "<str>Gawain told me it was the work of Morgan Le Faye.",
                        "<str>I told Lancelot of Gawain's suspicions, and he told me that",
                        "<str>Mordred's Fortress is not completely impenetrable.",
                        "<str>There might be a way to enter with a delivery by sea...",
                        "<blue>I have broken into <red>Keep Le Faye<blue> and slain <red>Sir Mordred<blue>.",
                        "<red>Morgan Le Faye<blue> disclosed the secret of how to free <red>Merlin<blue>.",
                        "<blue> I'll need the sword <red>Excalibur<blue> and a <red>lit black candle<blue> first.",
                        "<blue>According to Morgan, the <red>Lady of the Lake<blue> has the",
                        "<red>sword<blue> that can be used to free Merlin.");
                break;
            case 60: // After talking to the Lady of the Lake
                writeJournal(player,
                        "<str>I spoke to King Arthur and he said I would be worthy of ",
                        "<str>becoming a Knight of the Round Table if I could free Merlin",
                        "<str>from a giant crystal that he has been trapped in.",
                        "<str>Gawain told me it was the work of Morgan Le Faye.",
                        "<str>I told Lancelot of Gawain's suspicions, and he told me that",
                        "<str>Mordred's Fortress is not completely impenetrable.",
                        "<str>There might be a way to enter with a delivery by sea...",
                        "<str>I have broken into Keep Le Faye and slain Sir Mordred.",
                        "<str>Morgan Le Faye disclosed the secret of how to free Merlin.",
                        "<str>I'll need the sword Excalibur and a lit black candle.",
                        "<red>The Lady of the Lake<blue> told me she had the <red>Excalibur",
                        "<blue> but I'd have to meet her in the <red>jewellery store<blue> in <red>Port Sarim",
                        "<blue>before she'd give it to me.");
                break;
            case 70: // After actually obtaining Excalibur
                writeJournal(player,
                        "<str>I spoke to King Arthur and he said I would be worthy of ",
                        "<str>becoming a Knight of the Round Table if I could free Merlin",
                        "<str>from a giant crystal that he has been trapped in.",
                        "<str>Gawain told me it was the work of Morgan Le Faye.",
                        "<str>I told Lancelot of Gawain's suspicions, and he told me that",
                        "<str>Mordred's Fortress is not completely impenetrable.",
                        "<str>There might be a way to enter with a delivery by sea...",
                        "<str>I have broken into Keep Le Faye and slain Sir Mordred.",
                        "<str>Morgan Le Faye disclosed the secret of how to free Merlin.",
                        "<str>I'll need the sword Excalibur and a lit black candle.",
                        "<str>The Lady of the Lake told me she had the Excalibur",
                        "<str>but I'd have to visit the jewellery store in Port Sarim first.",
                        "<blue>I have the sword <red>Excalibur<blue> and can free <red>Merlin<blue> from the crystal.",
                        "<blue>I must now memorize an incantation inscribed on a <red>Chaos altar",
                        "<blue>that is located somewhere in the world in order to banish",
                        "<blue>the spirit.",
                        "<blue>I will also need to find some <red>bat bones<blue> and drop them",
                        "<blue>on the magical symbol to the <red>North East of Camelot<blue>.",
                        "<blue>after I have learned the incantation.");
                break;
            case 80: // After reading the incantation
                writeJournal(player,
                        "<str>I spoke to King Arthur and he said I would be worthy of ",
                        "<str>becoming a Knight of the Round Table if I could free Merlin",
                        "<str>from a giant crystal that he has been trapped in.",
                        "<str>Gawain told me it was the work of Morgan Le Faye.",
                        "<str>I told Lancelot of Gawain's suspicions, and he told me that",
                        "<str>Mordred's Fortress is not completely impenetrable.",
                        "<str>There might be a way to enter with a delivery by sea...",
                        "<str>I have broken into Keep Le Faye and slain Sir Mordred.",
                        "<str>Morgan Le Faye disclosed the secret of how to free Merlin.",
                        "<str>I'll need the sword Excalibur and a lit black candle.",
                        "<str>The Lady of the Lake told me she had the Excalibur",
                        "<str>but I'd have to visit the jewellery store in Port Sarim first.",
                        "<str>I have the sword Excalibur and can free Merlin from the crystal.",
                        "<str>I must now memorize an incantation inscribed on a Chaos altar",
                        "<str>that is located somewhere in the world in order to banish",
                        "<str>the spirit.",
                        "<blue> I managed to find the <red>Chaos Altar<blue> that Morgan described.",
                        "<blue>The incantation is 'Snarthon Candtrick Termanto'.",
                        "<blue>I now need to find some <red>bat bones<blue> and drop them",
                        "<blue>on the magical symbol to the <red>North East of Camelot<blue>.");
                break;
            case 90:
                writeJournal(player,
                        "<str>I spoke to King Arthur and he said I would be worthy of ",
                        "<str>becoming a Knight of the Round Table if I could free Merlin",
                        "<str>from a giant crystal that he has been trapped in.",
                        "<str>Gawain told me it was the work of Morgan Le Faye.",
                        "<str>I told Lancelot of Gawain's suspicions, and he told me that",
                        "<str>Mordred's Fortress is not completely impenetrable.",
                        "<str>There might be a way to enter with a delivery by sea...",
                        "<str>I have broken into Keep Le Faye and slain Sir Mordred.",
                        "<str>Morgan Le Faye disclosed the secret of how to free Merlin.",
                        "<str>I'll need the sword Excalibur and a lit black candle.",
                        "<str>The Lady of the Lake told me she had the Excalibur",
                        "<str>but I'd have to visit the jewellery store in Port Sarim first.",
                        "<str>I have the sword Excalibur and can free Merlin from the crystal.",
                        "<str>I must now memorize an incantation inscribed on a Chaos altar",
                        "<str>that is located somewhere in the world in order to banish",
                        "<str>the spirit.",
                        "<blue>Now is your time to free <red>Merlin!");
                break;
            case 99:
                writeJournal(player,
                        "<str>I spoke to King Arthur and he said I would be worthy of ",
                        "<str>becoming a Knight of the Round Table if I could free Merlin",
                        "<str>from a giant crystal that he has been trapped in.",
                        "<str>Gawain told me it was the work of Morgan Le Faye.",
                        "<str>I told Lancelot of Gawain's suspicions, and he told me that",
                        "<str>Mordred's Fortress is not completely impenetrable.",
                        "<str>There might be a way to enter with a delivery by sea...",
                        "<str>I have broken into Keep Le Faye and slain Sir Mordred.",
                        "<str>Morgan Le Faye disclosed the secret of how to free Merlin.",
                        "<str>I'll need the sword Excalibur and a lit black candle.",
                        "<str>The Lady of the Lake told me she had the Excalibur",
                        "<str>but I'd have to visit the jewellery store in Port Sarim first.",
                        "<str>I have the sword Excalibur and can free Merlin from the crystal.",
                        "<str>I must now memorize an incantation inscribed on a Chaos altar",
                        "<str>that is located somewhere in the world in order to banish",
                        "<str>the spirit.",
                        "<blue>Speak to <red>King Arthur<blue> for a reward.");
                break;
            case 100:
                writeJournal(player,
                        "<str>You helped King Arthur free Merlin from the crystal.",
                        "",
                        "<col=FF0000>QUEST COMPLETE!</col>");
                break;
        }
    }

    @Override
    public boolean hasRequirements(Player player) {
        return true;
    }

    @Override
    public QuestRewardComponentItem getRewardComponentItem() {
        return new QuestRewardComponentItem(35, 235);
    }

    @Override
    public QuestReward[] getQuestRewards(Player player) {
        return new QuestReward[]{
            new QuestReward("Excalibur"),
        };
    }

    @Override
    public Quest newInstance(Object object) {
        PluginManager.definePlugins(new CrateCutscenePlugin(), new MerlinCrystalPlugin(), new ArheinShopDialogue(), new BeggarDialogue(), new CandleMakerDialogue(), new KingArthurDialogue(), new MerlinCrystalOptionPlugin(), new SirKayDialogue(), new SirLancelotDialogue(), new SirLucan(), new SirMordredNPC(), new SirPalomedes(), new TheLadyOfTheLake(), new ThrantaxDialogue());
        return this;
    }

}
