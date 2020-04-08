/*
package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.skill.member.fletching.items.darts.Dart;
import org.crandor.game.content.skill.member.fletching.items.darts.DartPulse;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

*/
/**
 * Represents the dart creating dialogue.
 * @author 'Vexia
 * @version 1.0
 *//*

@InitializablePlugin
public final class DartCreateDialogue extends DialoguePlugin {

	*/
/**
	 * Represents the first item parameter.
	 *//*

	private Item item;

	*/
/**
	 * Represents the second item parameter.
	 *//*

	private Item second;

	*/
/**
	 * Represents the dark.
	 *//*

	private Dart dart;

	*/
/**
	 * Constructs a new {@code DartCreateDialogue} {@code Object}.
	 *//*

	public DartCreateDialogue() {
		*/
/**
		 * empty.
		 *//*

	}

	*/
/**
	 * Constructs a new {@code DartCreateDialogue} {@code Object}.
	 * @param player the player.
	 *//*

	public DartCreateDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new DartCreateDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		item = (Item) args[0];
		second = (Item) args[1];
		dart = Dart.forItem(item.getName().toLowerCase().contains("feather") ? second : item);
		player.getInterfaceManager().openChatbox(582);
		player.getPacketDispatch().sendString("<br><br><br><br>" + dart.getProduct().getName(), 582, 5);
		player.getPacketDispatch().sendItemZoomOnInterface(dart.getProduct().getId(), 160, 582, 2);
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		end();
		int amount = 0;
		switch (buttonId) {
		case 4:
			amount = 5;
			break;
		case 5:
			amount = 1;
			break;
		case 3:
			amount = 10;
			break;
		}
		player.getPulseManager().run(new DartPulse(player, item, dart, amount));
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 328933 };
	}
}
*/
