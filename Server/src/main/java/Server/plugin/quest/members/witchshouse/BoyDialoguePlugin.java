package plugin.quest.members.witchshouse;

import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;

/**
 * Created for 2009Scape
 * User: Ethan Kyle Millard
 * Date: March 15, 2020
 * Time: 11:49 AM
 */
@InitializablePlugin
public class BoyDialoguePlugin extends DialoguePlugin {

    private static final Item BALL = new Item(2407);

    public BoyDialoguePlugin() {
    }

    public BoyDialoguePlugin(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new BoyDialoguePlugin(player);
    }

    @Override
    public boolean open(Object... args) {
        final Quest quest = player.getQuestRepository().getQuest("Witch's House");
        player.debug(quest.isStarted(player) + " " + quest.getStage(player) );
        if (!quest.isStarted(player) && quest.getStage(player) < 10) {
            player("Hello young man.");
            setStage(1);
            return true;
        }
        if (quest.isCompleted(player) || quest.getStage(player) == 100) {
            sendDialogue("The boy is too busy playing with his ball to talk.");
            finish();
            return true;
        }
        if (!player.getInventory().containsItem(BALL)) {
            npc( FacialExpression.OLD_NORMAL, "Have you gotten my ball back yet?");
        } else {
            player("Hi, I have got your ball back. It was MUCH harder", "than I thought it would be.");
        }
        setStage(11);
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        final Quest quest = player.getQuestRepository().getQuest("Witch's House");
        switch(stage) {
            case -1:
                end();
                break;
            case 1:
                sendDialogue("The boy sobs.");
                next();
                break;
            case 2:
                options("What's the matter?", "Well if you're not going to answer then I'll go.");
                next();
                break;
            case 3:
                switch(buttonId) {
                    case 1:
                        player("What's the matter?");
                        setStage(5);
                        break;
                    case 2:
                        player("Well if you're not going to answer then I'll go.");
                        next();
                        break;
                }
                break;
            case 4:
                sendDialogue("The boy sniffs slightly.");
                finish();
                break;
            case 5:
                npc(FacialExpression.OLD_NORMAL, "I've kicked my ball over that hedge, into that garden!", "The old lady who lives there is scary... She's locked the","ball in her wooden shed! Can you get my ball back for", "me please?");
                next();
                break;
            case 6:
                options("Ok, I'll see what I can do.", "Get it back yourself.");
                next();
                break;
            case 7:
                switch(buttonId) {
                    case 1:
                        player("Ok, I'll see what I can do.");
                        setStage(10);
                        break;
                    case 2:
                        player("Get it back yourself.");
                        next();
                        break;
                }
                break;
            case 8:
                npc(FacialExpression.OLD_NORMAL, "You're a meany.");
                next();
                break;
            case 9:
                sendDialogue("The boy starts crying again.");
                finish();
                break;
            case 10:
                npc(FacialExpression.OLD_NORMAL, "Thanks mister!");
                finish();
                quest.start(player);
                break;
            case 11:
                if (!player.getInventory().containsItem(BALL)) {
                    player("Not yet.");
                    next();
                } else {
                    if (player.getInventory().remove(BALL))
                        sendDialogue("You give the ball back.");
                    setStage(13);
                }
                break;
            case 12:
                npc(FacialExpression.OLD_NORMAL, "Well it's in the shed in that garden.");
                finish();
                break;
            case 13:
                npc(FacialExpression.OLD_NORMAL, "Thank you so much!");
                next();
                break;
            case 14:
                quest.setStage(player, 100);
                quest.finish(player);
                finish();
                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[] { 895 };
    }
}
