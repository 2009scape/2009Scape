package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the dialogue of the Joe guard NPC.
 * @author 'Vexia
 * @date 1/14/1
 */
@InitializablePlugin
public final class JoeGuardDialogue extends DialoguePlugin {

	/**
	 * Represents the beer item.
	 */
	private static final Item BEER = new Item(1917);

	/**
	 * Represents the quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code JoeGuardDialogue} {@code Object}.
	 */
	public JoeGuardDialogue() {

	}

	/**
	 * Constructs a new {@code JoeGuardDialogue} {@code Object}.
	 * @param player the player.
	 */
	public JoeGuardDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new JoeGuardDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("Prince Ali Rescue");
		switch (quest.getStage(player)) {
		case 40:
			if (player.getAttribute("guard-drunk", false)) {
				interpreter.sendDialogues(npc, null, "Halt! Who goes there?");
				stage = 23;
				return true;
			}
			if (player.getInventory().contains(1917, 3)) {
				interpreter.sendDialogues(player, null, "I have some beer here, fancy one?");
				stage = 10;
				break;
			}
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hi, I'm Joe, door guard for Lady Keli.");
			stage = 0;
			break;
		case 60:
		case 100:
			interpreter.sendDialogues(npc, null, "Halt! Who goes there? Friend or foe?");
			stage = 0;
			break;
		default:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hi, I'm Joe, door guard for Lady Keli.");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		if (quest.getStage(player) > 50) {
			switch (stage) {
			case 0:
				interpreter.sendDialogues(player, null, "Hi friend, I am just checking out things here.");
				stage = 1;
				break;
			case 1:
				interpreter.sendDialogues(npc, null, "The Prince got away, I am in trouble. I better not talk", "to you, they are not sure I was drunk.");
				stage = 2;
				break;
			case 2:
				end();
				break;
			}
			return true;
		}
		if (stage >= 10 && quest.getStage(player) == 40) {
			switch (stage) {
			case 10:
				interpreter.sendDialogues(npc, null, "Ah, that would be lovely, just one now,", "just to wet my throat.");
				stage = 11;
				break;
			case 11:
				interpreter.sendDialogues(player, null, "Of course, it must be tough being here without a drink.");
				stage = 12;
				break;
			case 12:
				interpreter.sendDialogue("You hand a beer to the guard, he drinks it in seconds.");
				stage = 13;
				break;
			case 13:
				if (!player.getInventory().containsItem(BEER)) {
					end();
					return true;
				}
				if (player.getInventory().remove(BEER)) {
					interpreter.sendDialogues(npc, null, "That was perfect, I can't thank you enough.");
					stage = 14;
				}
				break;
			case 14:
				interpreter.sendDialogues(player, null, "How are you? Still ok? Not too drunk?");
				stage = 15;
				break;
			case 15:
				interpreter.sendDialogues(player, null, "Would you care for another, my friend?");
				stage = 16;
				break;
			case 16:
				interpreter.sendDialogues(npc, null, "I better not, I don't want to be drunk on duty.");
				stage = 17;
				break;
			case 17:
				interpreter.sendDialogues(player, null, "Here, just keep these for later,", "I hate to see a thirsty guard.");
				stage = 18;
				break;
			case 18:
				if (player.getInventory().remove(BEER) && player.getInventory().remove(BEER)) {
					interpreter.sendDialogue("You hand two more beers to the guard.", "He takes a sip of one, and then he drinks the both.");
					stage = 19;
					player.setAttribute("/save:guard-drunk", true);
				}
				break;
			case 19:
				interpreter.sendDialogues(npc, null, "Franksh, that wash just what I need to shtay on guard.", "No more beersh, I don't want to get drunk.");
				stage = 20;
				break;
			case 20:
				interpreter.sendDialogue("The guard is drunk, and no longer a problem.");
				stage = 21;
				break;
			case 21:
				end();
				break;
			case 23:
				interpreter.sendDialogues(player, null, "Hello friend, I am just rescuing the prince, is that ok?");
				stage = 24;
				break;
			case 24:
				interpreter.sendDialogues(npc, null, "Thatsh a funny joke. You are lucky I am shober. Go", "in peace, friend.");
				stage = 21;
				break;
			}
			return true;
		}
		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hi, who are you guarding here?");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Can't say, all very secret. You should get out of here.", "I am not suposed to talk while I guard.");
			stage = 2;
			break;
		case 2:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 916 };
	}
}
