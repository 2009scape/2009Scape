package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.PulseRunner;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.GameWorld;

/**
 * Represents the dialogue used to handle the Pricne Ali NPC.
 * @author 'Vexia
 * @date 1/14/1/
 */
@InitializablePlugin
public class PrinceAliDialogue extends DialoguePlugin {

	/**
	 * Represents the array of disguised items.
	 */
	private static final Item[] DISGUISE = new Item[] { new Item(2424), new Item(2419), new Item(2418), new Item(1013) };

	/**
	 * Represents the quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code PrinceAliDialogue} {@code Object}.
	 */
	public PrinceAliDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code PrinceAliDialogue} {@code Object}.
	 * @param player the player.
	 */
	public PrinceAliDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new PrinceAliDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("Prince Ali Rescue");
		switch (quest.getStage(player)) {
		case 50:
			interpreter.sendDialogues(player, null, "Prince, I come to rescue you.");
			stage = 0;
			break;
		case 60:
		case 100:
			interpreter.sendDialogues(npc, null, "I owe you my life for that escape. You cannot help me", "this time, they know who you are. Go in peace, friend", "of Al-Kharid");
			stage = 100;
			break;
		default:
			end();
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 100:
			end();
			break;
		case 0:
			interpreter.sendDialogues(npc, null, "That is very kind of you, how do I get out?");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, null, "With a dusiguise. I have removed the Lady Keli. she is", "tied up, but will not stay tied up for long.");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(player, null, "Take this disguise, and this key.");
			stage = 3;
			break;
		case 3:
			for (Item i : DISGUISE) {
				if (!player.getInventory().containsItem(i)) {
					player.getPacketDispatch().sendMessage("You don't have all the parts of the disguise.");
					end();
					return true;
				}
			}
			if (player.getInventory().remove(DISGUISE)) {
				interpreter.sendDialogue("You hand the disguise and the key to the prince.");
				stage = 4;
				quest.setStage(player, 60);
				player.removeAttribute("guard-drunk");
				player.getGameAttributes().removeAttribute("guard-drunk");
			}
			break;
		case 4:
			// NPC 921 start dialogue.921
			npc.transform(921);
			PulseRunner.submit(new Pulse(50) {
				@Override
				public boolean pulse() {
					npc.transform(920);
					return true;
				}
			});
			interpreter.sendDialogues(npc, null, "Thank you my friend, I must leave you now. My", "father will pay you well for this.");
			stage = 5;
			break;
		case 5:
			interpreter.sendDialogues(player, null, "Go to Leela, she is close to here.");
			stage = 6;
			break;
		case 6:
			npc.setInvisible(true);
			PulseRunner.submit(new Pulse(20) {
				@Override
				public boolean pulse() {
					npc.transform(920);
					npc.setInvisible(false);
					return true;
				}
			});
			interpreter.sendDialogue("The prince has escaped, well done! You are now a friend of Al-", "Kharid and may pass through the Al-Kharid toll gate for free.");
			stage = 7;
			break;
		case 7:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 920 };
	}
}
