package plugin.quest.tutorials.learningtheropes;

import core.game.container.impl.EquipmentContainer;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import plugin.quest.tutorials.tutorialisland.TutorialSession;
import plugin.quest.tutorials.tutorialisland.TutorialStage;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;

/**
 * @author Ethan Kyle Millard <skype:pumpklins>
 * @since Mon, October 08, 2018 @ 4:19 PM
 */
@InitializablePlugin
public class SirVantDialogue extends DialoguePlugin {


    /**
     * Default Constructor
     */
    public SirVantDialogue() {
        /**
         * Leave this empty
         */
    }

    public SirVantDialogue(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new SirVantDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        int tut_stage = TutorialSession.getExtension(player).getStage();
        System.out.println("tut_stage = " + tut_stage);
        switch (tut_stage) {
            case 0:
            case 1:
            case 2:
            case 72:
                npc(FacialExpression.JOLLY, "My Word! You scared me there, friend. I have no idea", "where you came from, but you have fantastic timing.", "You see, I have come across a dragon.");
                setStage(1);
                break;
            case 3:
                player.getEquipment().replace(new Item(9703), EquipmentContainer.SLOT_WEAPON);
                player.getEquipment().replace(new Item(9704), EquipmentContainer.SLOT_SHIELD);
                npc("I think we have found a job for you, whoever you are.", "Here, take this sword and shield.");
                setStage(10);
                break;
        }
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {
            case 1:
                player("A dragon?");
                next();
                break;
            case 2:
                npc("Oh yes. It's not usually a problem. After all, I am a", "White Knight! I just haven't really had time to prepare:", "food, runes, legions of other White Knights - those sorts", "of things.");
                next();
                break;
            case 3:
                player ("You didn't prepare for facing a dragon?");
                next();
                break;
            case 4:
                npc ("It's not as of I woke with a dragon-slaying urge! I", "was on my way through Lumbridge when it started", "attacking. It's a rather larger, three-headed variant. Very", "rare.");
                next();
                break;
            case 5:
                player ("So, what can I do?");
                 next();
                 break;
            case 6:
                npc ("You can keep back - it's returning!");
                next();
                break;
            case 7:
                end();
                TutorialStage.load(player, 2, false);
                break;
            case 10:
                npc("I think the value of this property has slumped a little.");
                next();
                break;
            case 11:
                player(FacialExpression.ASKING, "A little? It's got a huge dragon on it!");
                next();
                break;
            case 12:
                npc(FacialExpression.JOLLY, "I'm sure the owners has no idea when they bought it.", "It would take and interesting individual to deliberately","build a cellar in a dragon's den.");
                next();
                break;
            case 13:
                npc("I believe this dragon has been asleep for a while - at", "least as long as it has taken Lumbridge, the town above","us, to be established. Something must have roused the","dragon - a large noise, tail cramp, the smell of damsels.");
                next();
                break;
            case 14:
                npc("Don't worry, the goblin is stunned, so it can't hurt you", "at the moment. You should know something about","basic combat before tackling it.");
                next();
                break;
            case 15:
                npc("Now, I have to keep watch for the dragon.");
                next();
                break;
            case 16:
                end();
                TutorialStage.load(player, 4, false);
                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[] {7938};
    }
}
