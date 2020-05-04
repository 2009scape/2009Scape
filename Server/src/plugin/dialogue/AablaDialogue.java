package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.PulseRunner;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.update.flag.context.Animation;

/**
 * Represents the npc aabla's dialogue.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class AablaDialogue extends DialoguePlugin {

	/**
	 * Represents the npc animation to use.
	 */
	private static final Animation ANIMATION = new Animation(881);

	/**
	 * Constructs a new {@code AablaDialogue} {@code Object}.
	 */
	public AablaDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code AablaDialogue} {@code Object}.
	 * @param player the player.
	 */
	public AablaDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new AablaDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		player("Hi!");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			npc("Hi. How can I help?");
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "Can you heal me?", "Do you see a lot of injured fighters?", "Do you come here often?");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				player("Can you heal me?");
				stage = 10;
				break;
			case 2:
				player("Do you see a lot of injured fighters?");
				stage = 30;
				break;
			case 3:
				player("Do you come here often?");
				stage = 20;
				break;
			}
			break;
		case 10:
			npc("Of course!");
			stage = 11;
			break;
		case 11:
			npc.faceTemporary(player, 2);
			npc.animate(ANIMATION);
			player.lock(4);
			close();
			GameWorld.Pulser.submit(new Pulse(3, player) {
				@Override
				public boolean pulse() {
					if (player.getSkills().getLifepoints() == player.getSkills().getStaticLevel(Skills.HITPOINTS)) {
						npc("You look healthy to me!");
					} else {
						player.getSkills().heal(20);
						npc("There you go!");
					}
					stage = 12;
					return true;
				}
			});
			break;
		case 12:
			end();
			break;
		case 20:
			npc("I work here, so yes!");
			stage = 21;
			break;
		case 21:
			end();
			break;
		case 30:
			npc("Yes I do. Thankfully we can cope with almost aything.", "Jaraah really is a wonderful surgeon, his methods are a", "little unorthodox but he gets the job done.");
			stage = 31;
			break;
		case 31:
			npc("I shouldn't tell you this but his nickname is 'The", "Butcher'.");
			stage = 32;
			break;
		case 32:
			player("That's reassuring.");
			stage = 33;
			break;
		case 33:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 959 };
	}
}
