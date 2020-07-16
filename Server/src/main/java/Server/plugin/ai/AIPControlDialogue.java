package plugin.ai;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.Rights;

/**
 * Handles artificial intelligent player controls.
 * @author Emperor
 */
public final class AIPControlDialogue extends DialoguePlugin {

	/**
	 * The AIP to control.
	 */
	private AIPlayer aip;

	/**
	 * Constructs a new {@code AIPControlDialogue} {@code Object}.
	 */
	public AIPControlDialogue() {
		/*
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code AIPControlDialogue} {@code Object}.
	 * @param player The player.
	 */
	public AIPControlDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new AIPControlDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		if (player.getDetails().getRights() != Rights.ADMINISTRATOR) {
			return false;
		}
		aip = (AIPlayer) args[0];
		String select = "Select";
		if (player.getAttribute("aip_select") == aip) {
			select = "Deselect";
		}
		interpreter.sendOptions("AIP#" + aip.getUid() + " controls", select, "Settings", "Follow", "Stand-by", "Clear");
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (interfaceId) {
		case 234:
			switch (buttonId) {
			case 1:
				if (player.getAttribute("aip_select") == aip) {
					player.removeAttribute("aip_select");
					break;
				}
				player.setAttribute("aip_select", aip);
				break;
			case 2:
				interpreter.sendOptions("AIP#" + aip.getUid() + " settings", "Toggle run", "Toggle retaliate", "Toggle special attack");
				return true;
			case 3:
				aip.follow(player);
				player.removeAttribute("aip_select");
				break;
			case 4:
				aip.getPulseManager().clear();
				player.removeAttribute("aip_select");
				break;
			case 5:
				AIPlayer.deregister(aip.getUid());
				player.removeAttribute("aip_select");
				break;
			}
			close();
			return true;
		case 230:
			switch (buttonId) {
			case 1:
				aip.getSettings().toggleRun();
				break;
			case 2:
				aip.getSettings().toggleRetaliating();
				break;
			case 3:
				aip.getSettings().toggleSpecialBar();
				break;
			}
			close();
			return true;
		}
		return false;
	}

	@Override
	public int[] getIds() {
		return new int[0];
	}

}