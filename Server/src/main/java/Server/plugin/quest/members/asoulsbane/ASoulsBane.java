package plugin.quest.members.asoulsbane;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * Author: afaroutdude
 */
@InitializablePlugin
public class ASoulsBane extends Quest {
    public static final String NAME = "A Soul's Bane";

    public ASoulsBane() {
        super(NAME, 115, 114, 1, 709, 0, 1, 1261);
    }

    // config 710 does a lot of shit
    //      launa 3638 quest start? can disappear w/ config 710
    //      launa 3639 ??
    //      launa 3640 standing upright w/out staff
    // sconfigrange 710 711
    // 2^0 Launa disappears
    // 2^13 warning signs appear around rift entrance
    // 2^11 rope appears at rift entrance

    // rage iface 315
    // rage config ???
    // fear black iface 317
    // fear ghost iface 318
    // https://www.youtube.com/watch?v=Ad5KUHV7duQ
    // https://www.youtube.com/watch?v=bKaaEqmS9KU

    // animGFX 3804 630 - Get Angry In Tolnaâ€™s Rift
    // anim 3807 - Tolna Awakens From Nightmare
    // anim 3838 - Crawl Into Tolnaâ€™s Rift

    @Override
    public Quest newInstance(Object object) {
        PluginManager.definePlugins(new SoulsBaneLaunaDialogue(), new ASoulsBanePlugin());
        return this;
    }

    protected enum JournalEntries {
        a(0, 5,"I can start this quest by talking to !!Launa?? at the !!Rift<br><br>!!entrance north west of the dig site."),
        b(5, 15, "I spoke with !!Launa?? next to the rift and she told me<br><br>about her lost !!Son.??"),
        d(15, 20, "I heard Tolna's voice say, 'Why should I, Tolna, be<br><br>trapped in such a wretched place? Feel my ANGER!'"),
        e(20, 25, "I beat the anger beasts in the rift."),
        f(25, 30, "I heard Tolna's voice say, 'I'm trapped and it's so dark. I FEAR I may never escape this place. Mum, help me!'"),
        g(30, 35, "I defeated the fear beasts in the rift."),
        h(35, 40, "I heard Tolna's voice say, 'Where am I? How long have I been here? Am I still the person I once was? I hate my CONFUSION'"),
        i(40, 45, "I conquered the beasts of confusion."),
        j(45, 50, "I heard Tolna's voice say, 'What's the point? Why do I defy such HOPELESSNESS? No-one will ever help me.'"),
        k(50, 55, "I overcame the emotion of hopelessness."),
        l(55, 60, "I've discovered the physical form of Tolna, and his father."),
        m(60, 70, "I fought Tolna and he appears to have returned to his human form."),
        n(70, 100, "Tolna has repented and returned to the entrance of the rift."),
        o(100, 110, "Tolna has set up a dungeon to help others avoid his mistakes.");

        int stageIntroduce;
        int stageStrikeout;
        String msg;

        JournalEntries(int stageIn, int stageOut, String msg) {
            this.stageIntroduce = stageIn;
            this.stageStrikeout = stageOut;
            this.msg = msg;
        }
    }

    private int drawJournalSub(Player player, int questStage, int line) {
        // todo this doesn't work right
        for (JournalEntries stageLine : JournalEntries.values()) {
            if (stageLine.stageIntroduce > questStage)
                break;

            line(player, stageLine.msg, line++, stageLine.stageStrikeout <= questStage);
        }
        return line;
    }


    @Override
    public void drawJournal(Player player, int stage) {
        int line = 11;
        super.drawJournal(player, stage);

        line = drawJournalSub(player, stage, line);
        switch (stage) {
            case 0:
                line(player, "There are no minimum requirements.", line + 2);
                break;
            case 100:
                line(player, "--- QUEST COMPLETE ---", line + 2);
            default:
                break;
        }
    }
}
