package plugin.skill.fletching;

import org.crandor.game.content.dialogue.SkillDialogueHandler;
import org.crandor.game.content.dialogue.SkillDialogueHandler.SkillDialogue;
import org.crandor.game.content.skill.member.fletching.Fletching;
import org.crandor.game.content.skill.member.fletching.items.arrow.ArrowHeadPulse;
import org.crandor.game.content.skill.member.fletching.items.arrow.HeadlessArrowPulse;
import org.crandor.game.interaction.NodeUsageEvent;
import org.crandor.game.interaction.UseWithHandler;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.ChildPositionContext;
import org.crandor.net.packet.out.RepositionChild;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the plugin used to create arrows.
 * @author Angle
 */
@InitializablePlugin
public class ArrowCreatePlugin extends UseWithHandler {

    /**
     * Represents the headless arrow item.
     */
    private static final Item HEADLESS_ARROW = new Item(53);

	/**
	 * Constructs a new {@code ArrowCreatePlugin} {@code Object}.
	 */
	public ArrowCreatePlugin() {
		super(314, 53, 52);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		// Feathers plus colored feathers
		addHandler(314, ITEM_TYPE, this);
		addHandler(10087, ITEM_TYPE, this);
		addHandler(10088, ITEM_TYPE, this);
		addHandler(10089, ITEM_TYPE, this);
		addHandler(10090, ITEM_TYPE, this);
		addHandler(10091, ITEM_TYPE, this);

		// Headless Arrows
		addHandler(53, ITEM_TYPE, this);
		// Arrow heads
		for (Fletching.ArrowHeads head : Fletching.ArrowHeads.values()) {
			addHandler(head.unfinished, ITEM_TYPE, this);
		}
		return this;
	}

	public boolean hasFeather(int id1, int id2) {
		return (id1 == 314 || (id1 >= 10087 && id1 <= 10091)) || (id2 == 314 || (id2 >= 10087 && id2 <= 10091));
	}

	public boolean hasShaft(int id1, int id2) {
		return (id1 == 52 || id2 == 52);
	}

	@Override
	public boolean handle(final NodeUsageEvent event) {
		event.getPlayer().debug("Trying to handle: " + event.getUsedItem() + " with " + event.getUsedWith());
		final Player player = event.getPlayer();
		// If the player uses a feather on headless arrows, do headless arrow crafting
		int itemID = event.getUsedItem().getId();
		int otherID = event.getUsedWith().getId();
		boolean hasFeather = hasFeather(itemID, otherID);
		boolean hasShaft = hasShaft(itemID, otherID);
		// If the item used was feathers and the target was arrow shafts
		if (hasFeather && hasShaft) {
			// Creating headless arrows
			SkillDialogueHandler handler = new SkillDialogueHandler(player, SkillDialogue.ONE_OPTION, HEADLESS_ARROW) {
				@Override
				public void create(final int amount, int index) {
					player.getPulseManager().run(new HeadlessArrowPulse(player, event.getUsedItem(), amount));
				}
				@Override
				public int getAll(int index) {
					return player.getInventory().getAmount(HEADLESS_ARROW);
				}
			};
			handler.open();
			PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 309, 2, 210, 10));
			return true;
		}
		// Otherwise, fletch normally
		boolean firstIsHead = Fletching.isArrowHead(itemID);
		if (!firstIsHead && !Fletching.isArrowHead(otherID)) {
			return false;
		} else if (hasFeather || hasShaft) {
			// Disallow crafting feathers with arrowheads or shafts with arrowheads.
			// It will fail anyway but this makes sure
			// that the gui doesn't pop up on the client.
			return true;
		}
		final Fletching.ArrowHeads head = Fletching.arrowHeadMap.get(firstIsHead ? itemID : otherID);
		SkillDialogueHandler handler = new SkillDialogueHandler(player, SkillDialogue.ONE_OPTION, head.getFinished()) {
			@Override
			public void create(final int amount, int index) {
				player.getPulseManager().run(new ArrowHeadPulse(player, event.getUsedItem(), head, amount));
			}
			@Override
			public int getAll(int index) {
				return player.getInventory().getAmount(head.getUnfinished());
			}
		};
		handler.open();
		PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 309, 2, 210, 10));

		return true;
	}

}
