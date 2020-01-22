package plugin.dialogue;

import java.util.List;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.global.action.DoorActionHandler;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.map.RegionManager;

/**
 * Represents the fortress guard dialogue.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public class FortressGuardDialogue extends DialoguePlugin {

	/**
	 * Represents the uniform item.
	 */
	private static final Item[] UNIFORM = new Item[] { new Item(1139), new Item(1101) };

	/**
	 * Constructs a new {@code FortressGuardDialogue} {@code Object}.
	 */
	public FortressGuardDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code FortressGuardDialogue} {@code Object}.
	 * @param player the player.
	 */
	public FortressGuardDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new FortressGuardDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (args.length == 2) {
			interpreter.sendDialogues(npc, null, "Hey! You can't come in here! This is a high security", "military installation!");
			stage = 40;
			return true;
		}
		if (args.length == 3) {
			interpreter.sendDialogues(npc, null, "I wouldn't go in there if I were you. Those Black", "Knights are in an important meeting. They said they'd", "kill anyone who went in there!");
			stage = 50;
			return true;
		}
		if (!inUniform(player)) {
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Get lost. This is private property.");
			stage = 0;
		} else {
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Hey! Get back on duty!");
			stage = 30;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Yes, but I work here!");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Well, this is the guards' entrance. I might be new here", "but I can tell you're not a guard.");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "How can you tell?");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "You're not even wearing proper guards uniform!");
			stage = 4;
			break;
		case 4:
			interpreter.sendOptions("Select an Option", "Oh pleeeaase let me in!", "So what is this uniform?");
			stage = 5;
			break;
		case 5:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Oh pleeeaaase let me in!");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "So what is this uniform?");
				stage = 20;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Go away. You're getting annoying.");
			stage = 11;
			break;
		case 11:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Well you can see me wearing it. It's an iron chainbody", "and a medium bronze helm.");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Hmmm... I wonder if I can make that or get some in", "the local towns...");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "What was that you muttered?");
			stage = 23;
			break;
		case 23:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Oh, nothing important!");
			stage = 24;
			break;
		case 24:
			end();
			break;
		case 30:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Uh...");
			stage = 31;
			break;
		case 31:
			end();
			break;
		case 40:
			interpreter.sendOptions("Select an Option", "Yes, but I work here!", "Oh, sorry.", "So who does it belong to?");
			stage = 41;
			break;
		case 41:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Yes, but I work here!");
				stage = 1;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Oh, sorry.");
				stage = 42;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "So who does it belong to?");
				stage = 44;
				break;
			}
			break;
		case 42:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Don't let it happen again.");
			stage = 43;
			break;
		case 43:
			end();
			break;
		case 44:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "This fortress belongs to the order of Black Knights", "known as the Kinshra.");
			stage = 45;
			break;
		case 45:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Oh. Okay, thanks.");
			stage = 46;
			break;
		case 46:
			end();
			break;
		case 50:
			interpreter.sendOptions("Select an Option", "Okay, I won't.", "I don't care. I'm going in anyway.");
			stage = 51;
			break;
		case 51:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "Okay, I won't.");
				stage = 52;
				break;
			case 2:
				interpreter.sendDialogues(player, null, "I don't care. I'm going in anyway.");
				stage = 54;
				break;
			}
			break;
		case 52:
			interpreter.sendDialogues(npc, null, "Wise move.");
			stage = 53;
			break;
		case 53:
			end();
			break;
		case 54:
			end();
			DoorActionHandler.handleAutowalkDoor(player, RegionManager.getObject(0, 3020, 3515));
			List<NPC> npcs = RegionManager.getLocalNpcs(player);
			for (NPC npc : npcs) {
				if (npc.getId() == 179) {
					npc.getProperties().getCombatPulse().attack(player);
					npc.sendChat("Die, intruder!");
					return true;
				}
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 609, 4603, 4604, 4605, 4606 };
	}

	/**
	 * Method used to cehck if the player is in the inventory.
	 * @param player the player.
	 * @return <code>True</code> if so.
	 */
	public static boolean inUniform(final Player player) {
		for (Item i : UNIFORM) {
			if (!player.getEquipment().containsItem(i)) {
				return false;
			}
		}
		return true;
	}
}
