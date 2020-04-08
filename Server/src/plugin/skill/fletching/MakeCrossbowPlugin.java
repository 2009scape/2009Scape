package plugin.skill.fletching;

import org.crandor.game.content.dialogue.SkillDialogueHandler;
import org.crandor.game.content.skill.member.fletching.Fletching;
import org.crandor.game.content.skill.member.fletching.items.crossbow.LimbPulse;
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
 * Represents the plugin used to make a crossbow.
 * @author Ceikry
 */
@InitializablePlugin
public class MakeCrossbowPlugin extends UseWithHandler {
	Fletching.Limb limb;
	Item whichItem;

	/**
	 * Constructs a new {@code StringcrossbowPlugin} {@code Object}.
	 */
	public MakeCrossbowPlugin() {
		super(9420, 9423, 9422, 9425, 9427, 9429, 9431);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (Fletching.Limb l : Fletching.Limb.values()) {
			addHandler(new Item(l.stock).getId(), ITEM_TYPE, this);
		}
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		limb = Fletching.limbMap.get(event.getBaseItem().getId());
		whichItem = (Item)event.getUsedWith();
		if(limb == null){
			limb = Fletching.limbMap.get(event.getUsed().getId());
			whichItem = (Item)event.getUsed();
		}
		SkillDialogueHandler handler = new SkillDialogueHandler(player, SkillDialogueHandler.SkillDialogue.ONE_OPTION, new Item(limb.product)) {
			@Override
			public void create(final int amount, int index) {
				player.getPulseManager().run(new LimbPulse(player, whichItem, limb, amount));
			}
			@Override
			public int getAll(int index) {
				return player.getInventory().getAmount(whichItem);
			}
		};
		handler.open();
		PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 309, 2, 210, 10));
		return true;
	}
}
