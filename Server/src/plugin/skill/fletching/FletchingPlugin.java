package plugin.skill.fletching;

import org.crandor.game.content.dialogue.SkillDialogueHandler;
import org.crandor.game.content.dialogue.SkillDialogueHandler.SkillDialogue;
import org.crandor.game.content.skill.member.fletching.Fletching;
import org.crandor.game.content.skill.member.fletching.FletchingPulse;
import org.crandor.game.content.skill.member.fletching.items.bolts.BoltPulse;
import org.crandor.game.content.skill.member.fletching.items.darts.DartPulse;
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
 * Represents the plugin used to open the fletching dialogue.
 * @author Ceikry
 */
@InitializablePlugin
public class FletchingPlugin extends UseWithHandler {

	public FletchingPlugin() {
		super(819,820,821,822,823,824,11232,9375,9376,9377,9382,9378,9379,9380,9381,13279,1511,1521,1519,1517,1515,1513,6332,6333);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(946, ITEM_TYPE, this);
		addHandler(314,ITEM_TYPE,this);
		return this;
	}

	@Override
	public boolean handle(final NodeUsageEvent event) {
		final Player player = event.getPlayer();

		//handle darts
		if(Fletching.isDart(event.getUsedItem().getId())){
			final Fletching.Darts dart = Fletching.dartMap.get(event.getUsedItem().getId());
			SkillDialogueHandler handler = new SkillDialogueHandler(player, SkillDialogue.ONE_OPTION, dart.getFinished()) {
				@Override
				public void create(final int amount, int index) {
					player.getPulseManager().run(new DartPulse(player, event.getUsedItem(), dart, amount));
				}
				@Override
				public int getAll(int index) {
					return player.getInventory().getAmount(event.getUsedItem());
				}
			};
			handler.open();
			PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 309, 2, 230, 10));
			return true;
		}

		//handle bolts
		if(Fletching.isBolt(event.getUsedItem().getId())){
			final Fletching.Bolts bolt = Fletching.boltMap.get(event.getUsedItem().getId());
			SkillDialogueHandler handler = new SkillDialogueHandler(player, SkillDialogue.ONE_OPTION, bolt.getFinished()) {
				@Override
				public void create(final int amount, int index) {
					player.getPulseManager().run(new BoltPulse(player, event.getUsedItem(), bolt, amount));
				}
				@Override
				public int getAll(int index) {
					return player.getInventory().getAmount(event.getUsedItem());
				}
			};
			handler.open();
			PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 309, 2, 210, 10));
			return true;
		}

		//handle logs
		if(Fletching.isLog(event.getUsedItem().getId())) {
			final Item log = event.getUsedItem();
			Item[] items = Fletching.getItems(log.getId());
			SkillDialogue dialLength = SkillDialogue.ONE_OPTION;
			switch (items.length) {
				case 2:
					dialLength = SkillDialogue.TWO_OPTION;
					break;
				case 3:
					dialLength = SkillDialogue.THREE_OPTION;
					break;
				case 4:
					dialLength = SkillDialogue.FOUR_OPTION;
					break;
			}
			SkillDialogueHandler handler = new SkillDialogueHandler(player, dialLength, items) {

				@Override
				public void create(final int amount, int index) {
					final Fletching.FletchingItems item = Fletching.getEntries(log.getId())[index];
					player.getPulseManager().run(new FletchingPulse(player, log, amount, item));
				}

				@Override
				public int getAll(int index) {
					return player.getInventory().getAmount(log);
				}

			};
			handler.open();
			return true;
		}
		return false;
	}
}
