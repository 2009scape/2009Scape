package plugin.quest.members.merlinscrystal;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * Represents the merlin's crystal quest.
 *
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

    static String[][] JOURNAL_ENTRIES = new String[][]{
        new String[]{
            "I spoke to King Arthur and he said I would be worthy of",
            "becoming a Knight of the Round Table if I could free Merlin",
            "from a giant crystal that he has been trapped in.",
        },
        new String[]{
            "Gawain told me it was the work of <red>Morgan Le Faye<blue>.",
        },
        new String[]{
            "I told Lancelot of Gawain's suspicions, and he told me that",
            "Mordred's Fortress is not completely impenetrable.",
            "There might be a way to enter with a <red>delivery by sea..."
        },
        new String[]{
            "I have broken into <red>Keep Le Faye <blue>and slain <red>Sir Mordred<blue>.",
            "<red>Morgan Le Faye <blue>disclosed the secret of how to free <red>Merlin<blue>.",
            "I'll need the sword <red>Excalibur <blue>and a <red>lit black candle<blue>."
        },
        new String[]{
            "The Lady of the Lake told me she had the Excalibur",
            "but I'd have to visit the jewellery store in Port Sarim first."
        },
        new String[]{
            "I have the sword <red>Excalibur <blue>and can free <red>Merlin <blue>from the crystal.",
            "I must now memorize an incantation inscribed on a <red>Chaos altar",
            "that is located somewhere in the world in order to banish",
            "the spirit.",
        }
    };

    @Override
    public String[][] getJournalEntries() {
        return JOURNAL_ENTRIES;
    }

    @Override
    public void drawJournal(Player player, int stage) {
        super.drawJournal(player, stage);
        int line = JOURNAL_TEXT_START;
        switch (stage) {
            case 0:
                writeJournal(player,
                    "I can start this quest by speaking to <red>King Arthur <blue>at",
                    "<red>Camelot Castle<blue>, just <red>North West of Catherby",
                    "I must be able to defeat a <red>level 37 enemy");
                break;
            case 10: // After talking to Arthur
                line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
                writeJournal(player, line,
                    "I should ask the <red>other Knights <blue>if they have any <red>advice <blue>for",
                    "me on how I should go about doing this.");
                break;
            case 20: // After talking to Gawain
                line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
                writeJournal(player, line, JOURNAL_ENTRIES[1]);
                break;
            case 30: // After talking to Lancelot
            case 40: // After talking to Arhein
                line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
                line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
                writeJournal(player, line, JOURNAL_ENTRIES[2]);
                break;
            case 50: // Defeated Mordred
                line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
                line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
                line = writeJournal(player, line, true, JOURNAL_ENTRIES[2]);
                writeJournal(player, line,
                    "I have broken into <red>Keep Le Faye <blue>and slain <red>Sir Mordred<blue>.",
                    "<red>Morgan Le Faye <blue>disclosed the secret of how to free <red>Merlin<blue>.",
                    "I'll need the sword <red>Excalibur <blue>and a <red>lit black candle <blue>first.",
                    "According to Morgan, the <red>Lady of the Lake <blue>has the",
                    "<red>sword <blue>that can be used to free Merlin.");
                break;
            case 60: // After talking to the Lady of the Lake
                line = writeJournalEntries(player, line, true, 3);
                writeJournal(player, line,
                    "<red>The Lady of the Lake <blue>told me she had the <red>Excalibur",
                    "but I'd have to meet her in the <red>jewellery store <blue>in <red>Port Sarim",
                    "before she'd give it to me.");
                break;
            case 70: // After actually obtaining Excalibur
                line = writeJournalEntries(player, line, true, 5);
                writeJournal(player, line,
                    "I will also need to find some <red>bat bones <blue>and drop them",
                    "on the magical symbol to the <red>North East of Camelot<blue>.",
                    "after I have learned the incantation.");
                break;
            case 80: // After reading the incantation
                line = writeJournalEntries(player, line, true, 5);
                writeJournal(player, line,
                    "I managed to find the <red>Chaos Altar <blue>that Morgan described.",
                    "The incantation is 'Snarthon Candtrick Termanto'.",
                    "I now need to find some <red>bat bones <blue>and drop them",
                    "on the magical symbol to the <red>North East of Camelot<blue>.");
                break;
            case 90:
                line = writeJournalEntries(player, line, true, 5);
                writeJournal(player, line,
                    "Now is your time to free <red>Merlin!");
                break;
            case 99:
                line = writeJournalEntries(player, line, true, 5);
                writeJournal(player, line,
                    "Speak to <red>King Arthur <blue>for a reward.");
                break;
            case 100:
                line = writeJournal(player, true,
                    "You helped King Arthur free Merlin from the crystal.");
                writeJournal(player, ++line,
                    "<col=FF0000>QUEST COMPLETE!</col>");
                break;
        }
    }

    @Override
    public boolean hasRequirements(Player player) {
        return true;
    }

    @Override
    public Item getRewardComponentItem() {
        return new Item(35);
    }

    @Override
    public QuestReward[] getQuestRewards() {
        return new QuestReward[]{
            new QuestReward("Excalibur"),
        };
    }

    @Override
    public Quest newInstance(Object object) {
        PluginManager.definePlugins(
            new CrateCutscenePlugin(), new MerlinCrystalPlugin(), new ArheinShopDialogue(), new BeggarDialogue(),
            new CandleMakerDialogue(), new KingArthurDialogue(), new MerlinCrystalOptionPlugin(), new SirKayDialogue(),
            new SirLancelotDialogue(), new SirLucan(), new SirMordredNPC(), new SirPalomedes(), new TheLadyOfTheLake(),
            new ThrantaxDialogue()
        );
        return this;
    }
}
