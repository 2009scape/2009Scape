package plugin.skill.fletching;

import core.tools.ItemNames;
import plugin.dialogue.SkillDialogueHandler;
import plugin.dialogue.SkillDialogueHandler.SkillDialogue;
import plugin.skill.fletching.items.gem.GemBoltCutPulse;
import plugin.skill.fletching.items.gem.GemBoltPulse;
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
 * Represents the gem bolt creating plugin.
 * @author Ceikry
 * @version 2.0
 */
@InitializablePlugin
public final class GemBoltPlugin extends UseWithHandler {
	Fletching.GemBolts bolt;

	/**
	 * Constructs a new {@code GemBoltPlugin} {@code Object}.
	 */
	public GemBoltPlugin() {
		super(ItemNames.OPAL_BOLT_TIPS,
				ItemNames.PEARL_BOLT_TIPS_46,
				ItemNames.JADE_BOLT_TIPS,
				ItemNames.TOPAZ_BOLT_TIPS_9188,
				ItemNames.SAPPHIRE_BOLT_TIPS_9189,
				ItemNames.EMERALD_BOLT_TIPS_9190,
				ItemNames.RUBY_BOLT_TIPS_9191,
				ItemNames.DIAMOND_BOLT_TIPS_9192,
				ItemNames.DRAGONSTONE_BOLT_TIPS_9193,
				ItemNames.ONYX_BOLT_TIPS_9194,
				ItemNames.OYSTER_PEARL,
				ItemNames.OYSTER_PEARLS,
				ItemNames.OPAL_1609,
				ItemNames.JADE_1611,
				ItemNames.RED_TOPAZ_1613,
				ItemNames.SAPPHIRE,
				ItemNames.EMERALD,
				ItemNames.RUBY,
				ItemNames.DIAMOND_1601,
				ItemNames.DRAGONSTONE_1615,
				ItemNames.ONYX_6573);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(ItemNames.CHISEL_1755, ITEM_TYPE, this);
		for(Fletching.GemBolts gem : Fletching.GemBolts.values()){
			addHandler(gem.base, ITEM_TYPE,this);
		}
		return this;
	}

	@Override
	public boolean handle(final NodeUsageEvent event) {
		final Player player = event.getPlayer();
		bolt = Fletching.tipMap.get(event.getUsedItem().getId());
		if(bolt == null){
			bolt = Fletching.tipMap.get(event.getUsedWith().getId());
		}
		if(bolt != null){
			SkillDialogueHandler handler = new SkillDialogueHandler(player, SkillDialogue.ONE_OPTION, new Item(bolt.product)) {
				@Override
				public void create(final int amount, int index) {
					player.getPulseManager().run(new GemBoltPulse(player, event.getUsedItem(), bolt, amount));
				}
				@Override
				public int getAll(int index) {
					return player.getInventory().getAmount(event.getUsedItem());
				}
			};
			handler.open();
			PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 309, 2, 210, 10));
		} else {
			final Fletching.GemBolts gem = Fletching.gemMap.get(event.getUsedItem().getId() == ItemNames.CHISEL_1755 ? event.getBaseItem().getId() : event.getUsedItem().getId());
			if(gem == null){
				return false;
			}
			new SkillDialogueHandler(player, SkillDialogue.ONE_OPTION, new Item(gem.gem)) {
				@Override
				public void create(final int amount, final int index) {
					player.getPulseManager().run(new GemBoltCutPulse(player, event.getUsedItem(), gem, amount));
				}

				@Override
				public int getAll(int index) {
					return player.getInventory().getAmount(gem.gem);
				}

			}.open();
		}
		return true;
	}
}
