package plugin.quest.free.shieldofarrav;

import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;

/**
 * Represents the curator haig halen dialogue.
 * @author 'Vexia
 * @version 1.0
 */
public final class CuratorHaigHalenDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code CuratorHaigHalenDialogue} {@code Object}.
	 */
	public CuratorHaigHalenDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code CuratorHaigHalenDialogue} {@code Object}.
	 * @param player the player.
	 */
	public CuratorHaigHalenDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CuratorHaigHalenDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Welcome to the museum of Varrock.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		if (player.getQuestRepository().getQuest("Shield of Arrav").getStage(player) == 70 && player.getInventory().containsItem(ShieldofArrav.PHOENIX_SHIELD) || player.getInventory().containsItem(ShieldofArrav.BLACKARM_SHIELD)) {
			switch (stage) {
			case 0:
				player("I have half the shield of Arrav here. Can I get a", "reward?");
				stage = 1;
				break;
			case 1:
				npc("The Shield of Arrav! Goodness, the Museum has been", "searching for that for years! The late Kibg Roald II", "offered a reward for it years ago!");
				stage = 2;
				break;
			case 2:
				player("Well, I'm here to claim it.");
				stage = 3;
				break;
			case 3:
				npc("Let me have a look at it first.");
				stage = 4;
				break;
			case 4:
				interpreter.sendItemMessage(ShieldofArrav.getShield(player), "The curator peers at the shield.");
				stage = 5;
				break;
			case 5:
				npc("This is incredible!");
				stage = 6;
				break;
			case 6:
				npc("That shield has been missing for over twenty-five years!");
				stage = 7;
				break;
			case 7:
				npc("Leave the shield here with me and I'll write you out a", "certificate saying that you have returned the shield, so", "that you can claim your reward from the King.");
				stage = 8;
				break;
			case 8:
				player("Can I have two certificates please?");
				stage = 9;
				break;
			case 9:
				npc("Yes, certainly. Please hand over the shield.");
				stage = 10;
				break;
			case 10:
				interpreter.sendItemMessage(ShieldofArrav.getShield(player), "You hand over the shield half.");
				stage = 11;
				break;
			case 11:
				final Item shield = ShieldofArrav.getShield(player);
				final Item certificate = shield == ShieldofArrav.BLACKARM_SHIELD ? ShieldofArrav.BLACKARM_CERTIFICATE : ShieldofArrav.PHOENIX_CERTIFICATE;
				if (player.getInventory().remove(shield)) {
					player.getInventory().add(certificate);
					interpreter.sendItemMessage(certificate, "The curator writes out two half-certificates.");
					stage = 12;
				}
				break;
			}
			return true;
		} else {
			switch (stage) {
			case 12:
				npc("Of course you won't actually be able to claim the", "reward with only half the reward certificate...");
				stage = 13;
				break;
			case 13:
				player("What? I went through a lot of trouble to get that shield", "piece and now you tell me it was for nothing? That's", "not very fair!");
				stage = 14;
				break;
			case 14:
				npc("Well, if you were to get me the other half of the shield,", "I could give you the other half of the reward certificate.", "It's rumoured to be in the possesion of the infamous", "Blackarm Gang, beyond that I can't help you.");
				stage = 15;
				break;
			case 15:
				player("Okay, I'll see what I can do.");
				stage = 16;
				break;
			case 16:
				end();
				break;
			}
		}
		switch (stage) {
		case 0:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 646 };
	}
}