package plugin.dialogue;

import plugin.skill.fletching.items.grapple.GrapplePulse;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.RunScript;
import core.game.node.item.Item;
import core.net.packet.PacketRepository;
import core.net.packet.context.ChildPositionContext;
import core.plugin.InitializablePlugin;
import core.net.packet.out.RepositionChild;

/**
 * Represents the dialogue used to create a mith grapple.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class MithGrappleDialogue extends DialoguePlugin {

	/**
	 * Represents the mith grapple tip.
	 */
	private static final Item MITH_GRAPPLE = new Item(9418);

	/**
	 * Represents the first item used.
	 */
	private Item first;

	/**
	 * Represents the second item used.
	 */
	private Item second;

	/**
	 * Constructs a new {@code MithGrappleDialogue} {@code Object}.
	 */
	public MithGrappleDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code MithGrappleDialogue} {@code Object}.
	 * @param player the player.
	 */
	public MithGrappleDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MithGrappleDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		first = (Item) args[0];
		second = (Item) args[1];
		player.getInterfaceManager().openChatbox(309);
		player.getPacketDispatch().sendString("<br><br><br><br>Mith Grapple", 309, 6);
		PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 309, 2, 215, 26));
		player.getPacketDispatch().sendItemZoomOnInterface(MITH_GRAPPLE.getId(), 175, 309, 2);
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		player.getInterfaceManager().closeChatbox();
		int amount = 0;
		switch (buttonId) {
		case 6:
			amount = 1;
			break;
		case 5:
			amount = 5;
			break;
		case 4:
			player.setAttribute("runscript", new RunScript() {
				@Override
				public boolean handle() {
					int ammount = (int) value;
					player.getPulseManager().run(new GrapplePulse(player, first, ammount));
					return false;
				}
			});
			player.getDialogueInterpreter().sendInput(false, "Enter the amount");
			return true;
		case 3:
			amount = player.getInventory().getAmount(second);
			break;
		}
		player.getPulseManager().run(new GrapplePulse(player, first, amount));
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 903213 };
	}
}
