package plugin.quest.members.waterfallquest;

import plugin.dialogue.DialogueInterpreter;
import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.combat.ImpactHandler.HitsplatType;
import core.game.node.entity.player.Player;
import core.game.system.task.Pulse;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;

/**
 * Handles the dialogue for the tree on the edge of the Waterfall.
 * @author Splinter
 */
public class WaterfallTreeDialogue extends DialoguePlugin {

	public WaterfallTreeDialogue() {

	}

	public WaterfallTreeDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { DialogueInterpreter.getDialogueKey("waterfall_tree_dialogue") };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {

		case 100: // Generic end to the dlg
			end();
			break;

		/* Main dialogue sequence */
		case 0:
			player.getDialogueInterpreter().sendOptions("Select an Option", "Climb down anyway", "Back away");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				end();
				player.getPacketDispatch().sendMessage("You climb down the tree");
				player.animate(new Animation(828));
				player.getPulseManager().run(new Pulse(4, player) {
					@Override
					public boolean pulse() {
						player.getPacketDispatch().sendMessage("and lose your grip.");
						player.getPacketDispatch().sendMessage("You get washed away in the current.");
						player.teleport(new Location(2527, 3413));
						player.getImpactHandler().manualHit(player, 8, HitsplatType.NORMAL);
						return true;
					}
				});
				break;
			case 2:
				player.getDialogueInterpreter().sendDialogue("You leave the tree alone.");
				stage = 100;
				break;
			}
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new WaterfallTreeDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		player.getDialogueInterpreter().sendDialogue("It would be difficult to get down this tree without using a rope", "on it first.");
		stage = 0;
		return true;
	}
}