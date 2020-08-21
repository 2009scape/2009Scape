package plugin.quest.free.shieldofarrav;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;

/**
 * Represents the dialogue which handles the weapons master.
 * @author 'Vexia
 * @version 1.0
 */
public final class WeaponsMasterDialogue extends DialoguePlugin {

	/**
	 * Represents the quest.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code WeaponsMasterDialogue} {@code Object}.
	 */
	public WeaponsMasterDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code WeaponsMasterDialogue} {@code Object}.
	 * @param player the player.
	 */
	public WeaponsMasterDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new WeaponsMasterDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("Shield of Arrav");
		switch (quest.getStage(player)) {
		default:
			if (args.length > 1) {
				npc("Stop! Thief!");
				stage = 600;
				return true;
			}
			player("Hello.");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		default:
			switch (stage) {
			case 600:
				end();
				npc.getProperties().getCombatPulse().attack(player);
				break;
			case 0:
				if (ShieldofArrav.isPhoenix(player)) {
					npc("Hello fellow Phoenix! What are you after?");
					stage = 1;
				} else {
					npc("Hey! Who are you? I'm gonna teach you not to stick", "your nose where it don't belong!");
					stage = 50;
				}
				break;
			case 50:
				end();
				npc.getProperties().getCombatPulse().attack(player);
				break;
			case 1:
				options("I'm after a weapon or two.", "I'm looking for treasure.");
				stage = 2;
				break;
			case 2:
				switch (buttonId) {
				case 1:
					player("I'm after a weapon or two.");
					stage = 10;
					break;
				case 2:
					player("I'm looking for treasure.");
					stage = 20;
					break;
				}
				break;
			case 10:
				npc("No problem. Feel free to look around.");
				stage = 11;
				break;
			case 11:
				end();
				break;
			case 20:
				npc("Aren't we all? We've not got any up here. Go mug", "someone somewhere if you want some treasure.");
				stage = 21;
				break;
			case 21:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 643 };
	}
}