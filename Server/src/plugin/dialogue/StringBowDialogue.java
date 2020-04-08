/*
package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.skill.member.fletching.items.bow.StringBow;
import org.crandor.game.content.skill.member.fletching.items.bow.StringPulse;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.RunScript;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

*/
/**
 * Handles the StringBowDialogue dialogue.
 * @author Vexia
 *//*

@InitializablePlugin
public class StringBowDialogue extends DialoguePlugin {

	*/
/**
	 * Constructs a new {@code StringBowDialogue.java} {@code Object}.
	 *//*

	public StringBowDialogue() {
	}

	*/
/**
	 * Constructs a new {@code StringBowDialogue.java} {@code Object}.
	 * @param player the player.
	 *//*

	public StringBowDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new StringBowDialogue(player);
	}

	*/
/**
	 * The item.
	 *//*

	private Item item;

	*/
/**
	 * The string bow enum.
	 *//*

	private StringBow bow;

	@Override
	public boolean open(Object... args) {
		item = (Item) args[0];
		bow = StringBow.forItem(item.getId());
		if (bow == null) {
			return true;
		}
		player.getInterfaceManager().openChatbox(309);
		player.getPacketDispatch().sendString("<br><br><br><br>" + bow.getProduct().getName(), 309, 6);
		player.getPacketDispatch().sendItemZoomOnInterface(bow.getProduct().getId(), 160, 309, 2);
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		end();
		switch (stage) {
		case 0:
			System.out.println(buttonId);
			int amt = 0;
			switch (buttonId) {
			case 5:
				amt = 1;
				break;
			case 4:
				amt = 5;
				break;
			case 3:
				player.setAttribute("runscript", new RunScript() {
					@Override
					public boolean handle() {
						int ammount = (int) value;
						player.getPulseManager().run(new StringPulse(player, item, bow, ammount));
						return false;
					}
				});
				player.getDialogueInterpreter().sendInput(false, "Enter the amount");
				return true;
			case 2:
				amt = player.getInventory().getAmount(item);
				break;
			}
			player.getPulseManager().run(new StringPulse(player, item, bow, amt));
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 9174399 };
	}

}
*/
