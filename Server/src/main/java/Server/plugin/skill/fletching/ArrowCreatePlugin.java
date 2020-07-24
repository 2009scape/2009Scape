package plugin.skill.fletching;

import plugin.dialogue.SkillDialogueHandler;
import plugin.dialogue.SkillDialogueHandler.SkillDialogue;
import plugin.skill.fletching.items.arrow.ArrowHeadPulse;
import plugin.skill.fletching.items.arrow.HeadlessArrowPulse;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.net.packet.PacketRepository;
import core.net.packet.context.ChildPositionContext;
import core.net.packet.out.RepositionChild;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

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

	@Override
	public boolean handle(final NodeUsageEvent event) {
		event.getPlayer().debug("Trying to handle: " + event.getUsedItem() + " with " + event.getUsedWith());
		final Player player = event.getPlayer();

		// If the player uses a feather on headless arrows, do headless arrow crafting
		final int itemId = event.getUsedItem().getId();
		final int otherId = event.getUsedWith().getId();

		final boolean hasFeather = (itemId == 314 || (itemId >= 10087 && itemId <= 10091)) || (otherId == 314 || (otherId >= 10087 && otherId <= 10091));
		final boolean hasShaft = (itemId == 52 || otherId == 52);

		// If the item used was feathers and the target was arrow shafts
		if (hasFeather && hasShaft) {
			// Creating headless arrows
			final int featherId = itemId == 52 ? otherId : itemId;

			SkillDialogueHandler handler = new SkillDialogueHandler(player, SkillDialogue.ONE_OPTION, HEADLESS_ARROW) {
				@Override
				public void create(final int amount, int index) {
					player.getPulseManager().run(new HeadlessArrowPulse(player, event.getUsedItem(), new Item(featherId), amount));
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
		boolean firstIsHead = Fletching.isArrowHead(itemId);
		if (!firstIsHead && !Fletching.isArrowHead(otherId)) {
			return false;
		} else if (hasFeather || hasShaft) {
			// Disallow crafting feathers with arrowheads or shafts with arrowheads.
			// It will fail anyway but this makes sure
			// that the gui doesn't pop up on the client.
			return true;
		}
		final Fletching.ArrowHeads head = Fletching.arrowHeadMap.get(firstIsHead ? itemId : otherId);
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
