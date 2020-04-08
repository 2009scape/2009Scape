package plugin.skill.fletching;

import org.crandor.game.content.dialogue.SkillDialogueHandler;
import org.crandor.game.content.skill.member.fletching.Fletching;
import org.crandor.game.content.skill.member.fletching.items.bow.StringPulse;
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
 * Represents the plugin used to string a bow.
 * @author Ceikry
 * @version 2.0
 */
@InitializablePlugin
public final class StringbowPlugin extends UseWithHandler {
	Fletching.String string;

	/**
	 * Constructs a new {@code StringbowPlugin} {@code Object}.
	 */
	public StringbowPlugin() {
		super(1777, 9438);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (Fletching.String bw : Fletching.String.values()) {
			addHandler(bw.unfinished, ITEM_TYPE, this);
		}
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		Fletching.String string = Fletching.stringMap.get(event.getUsedItem().getId());
		if(string == null){
			string = Fletching.stringMap.get(event.getUsedWith().getId());
		}
		final Fletching.String bow = string;
		if(!(player.getInventory().containsItem(new Item(bow.string)))){
			player.getDialogueInterpreter().sendDialogue("That's not the correct type of string.");
			return true;
		}
		SkillDialogueHandler handler = new SkillDialogueHandler(player, SkillDialogueHandler.SkillDialogue.ONE_OPTION, new Item(bow.product)) {
			@Override
			public void create(final int amount, int index) {
				player.getPulseManager().run(new StringPulse(player, event.getUsedItem(), bow, amount));
			}
			@Override
			public int getAll(int index) {
				return player.getInventory().getAmount(event.getUsedItem());
			}
		};
		handler.open();
		PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 309, 2, 215, 10));
		return true;
	}
}
