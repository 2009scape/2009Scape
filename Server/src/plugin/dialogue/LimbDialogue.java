/*
package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.skill.member.fletching.items.crossbow.Limb;
import org.crandor.game.content.skill.member.fletching.items.crossbow.LimbPulse;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.RunScript;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

*/
/**
 * Represents the dialogue plugin used for making limbs.
 * @author 'Vexia
 * @version 1.0
 *//*

@InitializablePlugin
public final class LimbDialogue extends DialoguePlugin {

	*/
/**
	 * Constructs a new {@code LimbDialogue} {@code Object}.
	 *//*

	public LimbDialogue() {
		*/
/**
		 * empty.
		 *//*

	}

	*/
/**
	 * Constructs a new {@code LimbDialogue} {@code Object}.
	 * @param player the player.
	 *//*

	public LimbDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new LimbDialogue(player);
	}

	*/
/**
	 * The item.
	 *//*

	private Item item;

	*/
/**
	 * The second item.
	 *//*

	private Item second;

	*/
/**
	 * The string bow enum.
	 *//*

	private Limb bow;

	@Override
	public boolean open(Object... args) {
		item = (Item) args[0];
		second = (Item) args[1];
		bow = Limb.forItems(item, second);
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
						player.getPulseManager().run(new LimbPulse(player, (item.getName().toLowerCase().contains("stock") ? second : item), bow, ammount));
						return false;
					}
				});
				player.getDialogueInterpreter().sendInput(false, "Enter the amount");
				return true;
			case 3:
				amt = player.getInventory().getAmount(item);
				break;
			}
			player.getPulseManager().run(new LimbPulse(player, (item.getName().toLowerCase().contains("stock") ? second : item), bow, amt));
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 84923 };
	}
}
*/
