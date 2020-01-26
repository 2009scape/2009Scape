package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.GameWorld;

/**
 * Represents the dialogue plugin for the new Researcher NPC that sells unobtainable items.
 * @author Splinter
 * @version 1.0
 */
@InitializablePlugin
public final class ResearcherDialogue extends DialoguePlugin {

    /**
     * Constructs a new {@code ResearcherDialogue} {@code Object}.
     */
    public ResearcherDialogue() {
	/**
	 * empty.
	 */
    }

    /**
     * Constructs a new {@code ResearcherDialogue} {@code Object}.
     * @param player the player.
     */
    public ResearcherDialogue(Player player) {
	super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
	return new ResearcherDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
	npc = (NPC) args[0];
	interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Hello.");
	stage = 0;
	return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
	switch (stage) {
	case 0:
	    player("Hello, are you new around these parts?");
	    stage = 1;
	    break;
	case 1:
	    npc("I am indeed. I am a traveling researcher studying the", "lands of "+GameWorld.getName()+".");
	    stage = 2;
	    break;
	case 2:
	    player("That's amazing! Surely you must have collected a few", "trinkets in your travels.");
	    stage = 3;
	    break;
	case 3:
	    npc("I have. I suppose I could let you take a look at them","if you're interested.");
	    stage = 4;
	    break;
	case 4:
	    interpreter.sendOptions("Select an Option", "Look at wares.", "Decline.");
	    stage = 5;
	    break;
	case 5:
	    switch(buttonId){
	    case 1:
		player("Alright, show me your wares.");
		stage = 6;
		break;
	    case 2:
		player("On second thought, I really must be going.");
		stage = 7;
		break;
	    }
	    break;
	case 6:
	    end();
	    npc.openShop(player);
	    break;
	case 7:
	    end();
	    break;
	}
	return true;
    }

    @Override
    public int[] getIds() {
	return new int[] { 4568 };
    }
}
