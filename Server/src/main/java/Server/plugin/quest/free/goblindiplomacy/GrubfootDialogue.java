package plugin.quest.free.goblindiplomacy;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;

/**
 * Represents the grub foot dialogue.
 * @author 'Vexia
 * @date 28/12/2013
 */
public final class GrubfootDialogue extends DialoguePlugin {

	/**
	 * Represents the quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code GrubfootDialogue} {@code Object}.
	 */
	public GrubfootDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GrubfootDialogue} {@code Object}.
	 * @param player the player.
	 */
	public GrubfootDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GrubfootDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest(GoblinDiplomacy.NAME);
		switch (quest.getStage(player)) {
		case 100:
			npc("Me lonely.");
			stage = 0;
			break;
		case 30:
			npc("Me not like this blue colour.");
			stage = 0;
			break;
		case 20:
			npc("Me not like this orange armour. Make me look like that", "thing.");
			stage = 0;
			break;
		default:
			npc("Grubfoot wear red armour! Grubfoot wear green", "armour!");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 100:
			switch (stage) {
			case 0:
				player("Why?");
				stage = 1;
				break;
			case 1:
				npc("Other goblins in village follow either General Wartface", "or General Bentnoze. Me try to follow both but then", "me get left out of both groups.");
				stage = 2;
				break;
			case 2:
				interpreter.sendDialogues(4493, null, "Shut up Grubfoot!");
				stage = 3;
				break;
			case 3:
				end();
				break;
			}
			break;
		case 30:
			switch (stage) {
			case 0:
				player("Why not?");
				stage = 1;
				break;
			case 1:
				npc("Me not know. It just make me feel...");
				stage = 2;
				break;
			case 2:
				player("Makes you feel blue?");
				stage = 3;
				break;
			case 3:
				npc("Makes me feel kinda of sad.");
				stage = 4;
				break;
			case 4:
				interpreter.sendDialogues(4493, null, "Shut up Grubfoot!");
				stage = 5;
				break;
			case 5:
				end();
				break;
			}
			break;
		case 20:
			switch (stage) {
			case 0:
				player("Look like what thing?");
				stage = 1;
				break;
			case 1:
				npc("That fruit thing. The one that orange. What it called?");
				stage = 2;
				break;
			case 2:
				player("An orange?");
				stage = 3;
				break;
			case 3:
				npc("That right. This armour make me look same colour as", "orange-fruit.");
				stage = 4;
				break;
			case 4:
				interpreter.sendDialogues(4493, null, "Shut up Grubfoot!");
				stage = 5;
				break;
			case 5:
				end();
				break;
			}
			break;
		default:
			switch (stage) {
			case 0:
				npc("Why they not make up their minds?");
				stage = 1;
				break;
			case 1:
				interpreter.sendDialogues(4493, null, "Shut up Grubfoot!");
				stage = 2;
				break;
			case 2:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 4495, 4497, 4498, 4496 };
	}
}