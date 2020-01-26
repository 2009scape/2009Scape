package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.map.Location;

/**
 * Handles the SorcceresDialouge dialogue.
 * @author Vexia
 */
@InitializablePlugin
public class SorcceresDialouge extends DialoguePlugin {

	/**
	 * Constructs a new {@code SorcceresDialouge} {@code Object}.
	 */
	public SorcceresDialouge() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code SorcceresDialouge} {@code Object}.
	 * @param player the player.
	 */
	public SorcceresDialouge(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new SorcceresDialouge(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		npc("What are you doing in my house?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "None of your business!", "I'm here to kill you!", "Can I have some sq'irks please?", "I'm just passing by.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.FURIOUS, "None of your business!");
				stage = 10;
				break;
			case 2:
				player("I'm here to kill you!");
				stage = 20;
				break;
			case 3:
				player("Can I have some sq'irks please?");
				stage = 30;
				break;
			case 4:
				player("I'm just passing by.");
				stage = 40;
				break;
			}
			break;
		case 10:
			player("I go where I like and do what I like.");
			stage = 11;
			break;
		case 11:
			npc("Not in my house. Be gone!");
			stage = 12;
			break;
		case 12:
			end();
			tele();
			break;
		case 20:
			npc("I think not!");
			stage = 21;
			break;
		case 21:
			end();
			tele();
			break;
		case 30:
			npc("What do you want them for?");
			stage = 31;
			break;
		case 31:
			player("Someone asked me to bring them some.");
			stage = 32;
			break;
		case 32:
			npc("Who?");
			stage = 33;
			break;
		case 33:
			player("<col=0000FF>You find yourself compelled to answer truthfully:</col>", "Osman.");
			stage = 34;
			break;
		case 34:
			npc("In that case I'm sorry, you can't. I have had a falling", "out with him recently and would rather not oblige him.");
			stage = 35;
			break;
		case 35:
			end();
			break;
		case 40:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 5531 };
	}

	/**
	 * Method used to tele the player out.
	 */
	public void tele() {
		npc.sendChat("Be gone intruder!");
		player.lock();
		GameWorld.submit(new Pulse(2, player) {
			@Override
			public boolean pulse() {
				player.unlock();
				player.getProperties().setTeleportLocation(new Location(3321, 3143, 0));
				return true;
			}
		});
	}
}
