package plugin.dialogue;

import org.crandor.game.component.Component;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.global.travel.glider.Gliders;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for the captain dalbur npc.
 * @author 'Vexia
 * @note complete with gnome city stuff.
 * @version 1.0
 */
@InitializablePlugin
public final class CaptainDalburDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code CaptainDalburDialogue} {@code Object}.
	 */
	public CaptainDalburDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code CaptainDalburDialogue.java} {@code Object}.
	 * @param player the player.
	 */
	public CaptainDalburDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CaptainDalburDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "What do you want human?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "May you fly me somewhere on your glider?");
			stage = 1;
			break;
		case 1:
			npc("If you wish.");
			stage++;
			/*
			 * interpreter.sendDialogues(npc, FacialExpression.NORMAL,
			 * "I only fly friends of the gnomes!"); stage = 2;
			 */
			break;
		case 2:
			end();
			player.getInterfaceManager().open(new Component(138));
			Gliders.sendConfig(npc, player);
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3809, 3810, 3811, 3812, 3813 };
	}
}
