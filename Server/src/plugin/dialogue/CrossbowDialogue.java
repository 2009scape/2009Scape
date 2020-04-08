/*
package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.skill.member.fletching.items.crossbow.CrossbowPulse;
import org.crandor.game.content.skill.member.fletching.items.crossbow.StringCross;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.RunScript;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

*/
/**
 * Represents the dialogue plugin used for the crossbow.
 * @author 'Vexia
 * @version 1.0
 *//*

@InitializablePlugin
public final class CrossbowDialogue extends DialoguePlugin {

	*/
/**
	 * Represents the item used.
	 *//*

	private Item item;

	*/
/**
	 * The string cross.
	 *//*

	private StringCross cross;

	*/
/**
	 * Constructs a new {@code CrossbowDialogue} {@code Object}.
	 *//*

	public CrossbowDialogue() {
		*/
/**
		 * empty.
		 *//*

	}

	*/
/**
	 * Constructs a new {@code CrossbowDialogue.java} {@code Object}.
	 * @param player
	 *//*

	public CrossbowDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CrossbowDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		item = (Item) args[0];
		cross = StringCross.forItem(item);
		if (cross == null) {
			return true;
		}
		player.getInterfaceManager().openChatbox(309);
		player.getPacketDispatch().sendString("<br><br><br><br>" + cross.getProduct().getName(), 309, 6);
		player.getPacketDispatch().sendItemZoomOnInterface(cross.getProduct().getId(), 160, 309, 2);
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			end();
			int amt = 0;
			switch (buttonId) {
			case 6:
				amt = 1;
				break;
			case 5:
				amt = 5;
				break;
			case 4:
				player.setAttribute("runscript", new RunScript() {
					@Override
					public boolean handle() {
						int ammount = (int) value;
						player.getPulseManager().run(new CrossbowPulse(player, item, cross, ammount));
						return false;
					}
				});
				player.getDialogueInterpreter().sendInput(false, "Enter the amount");
				return true;
			case 3:
				amt = player.getInventory().getAmount(item);
				break;
			}
			player.getPulseManager().run(new CrossbowPulse(player, item, cross, amt));
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 92747 };
	}
}
*/
