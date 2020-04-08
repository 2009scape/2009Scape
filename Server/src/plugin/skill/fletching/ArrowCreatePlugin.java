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
 * @author Vexia
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
		addHandler(52, ITEM_TYPE, this);
		for (Fletching.ArrowHeads head : Fletching.ArrowHeads.values()) {
			addHandler(head.unfinished, ITEM_TYPE, this);
		}
		return this;
	}

	@Override
	public boolean handle(final NodeUsageEvent event) {
		event.getPlayer().debug("Trying to handle: " + event.getUsedItem() + " with " + event.getUsedWith());
		final Fletching.ArrowHeads head = Fletching.arrowHeadMap.get((event.getUsedItem().getName().contains("tip") || event.getUsedItem().getName().contains("head")) ? event.getUsedItem().getId() : event.getUsedWith().asItem().getId());
		final Player player = event.getPlayer();
		SkillDialogueHandler handler = new SkillDialogueHandler(player, SkillDialogue.ONE_OPTION, head == null ? HEADLESS_ARROW : head.getFinished()) {
			@Override
			public void create(final int amount, int index) {
				if (head == null) {
					player.getPulseManager().run(new HeadlessArrowPulse(player, event.getUsedItem(), amount));
				} else{
					player.getPulseManager().run(new ArrowHeadPulse(player, event.getUsedItem(), head, amount));
				}
			}

			@Override
			public int getAll(int index) {
				return player.getInventory().getAmount(head == null ? HEADLESS_ARROW : head.getUnfinished());
			}

		};
		handler.open();
		PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 309, 2, 210, 10));
		return true;
	}

}
