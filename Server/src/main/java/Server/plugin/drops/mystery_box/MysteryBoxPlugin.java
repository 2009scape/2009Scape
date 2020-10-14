package plugin.drops.mystery_box;

import core.cache.def.impl.ItemDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.npc.drop.DropFrequency;
import core.game.node.entity.player.Player;
import core.game.node.item.ChanceItem;
import core.game.node.item.Item;
import core.game.node.item.WeightedChanceItem;
import core.game.world.repository.Repository;
import core.plugin.Plugin;
import core.plugin.PluginManifest;
import core.tools.ItemNames;
import core.tools.RandomFunction;
import core.plugin.InitializablePlugin;
import core.tools.StringUtils;

import java.util.List;

/**
 * Handles the mystery box item.
 * @author Ceikry
 */
@InitializablePlugin
@PluginManifest(name="MysteryBox")
public final class MysteryBoxPlugin extends OptionHandler {

	/**
     * The rewards recieved from a mystery box.
     */
    private static final WeightedChanceItem[] REWARDS = new WeightedChanceItem[] {
	
    		new WeightedChanceItem(ItemNames.UNCUT_DIAMOND,1,10),
			new WeightedChanceItem(ItemNames.UNCUT_RUBY_1619,1,50),
			new WeightedChanceItem(ItemNames.UNCUT_SAPPHIRE_1623,1,60),
			new WeightedChanceItem(ItemNames.UNCUT_JADE,1,150),
			new WeightedChanceItem(ItemNames.UNCUT_DRAGONSTONE_1631,1,1),
			new WeightedChanceItem(ItemNames.MOLTEN_GLASS_NOTED_1776,25,5),
			new WeightedChanceItem(ItemNames.UNCUT_EMERALD_1621,1,60),
			new WeightedChanceItem(ItemNames.COINS,25,30),
			new WeightedChanceItem(ItemNames.COINS,250,50),
			new WeightedChanceItem(ItemNames.COINS,10,80),
			new WeightedChanceItem(0,1,100),
			new WeightedChanceItem(ItemNames.UNCUT_OPAL_1625,1,150),
			new WeightedChanceItem(ItemNames.VIAL_OF_WATER_NOTED,10,25),
			new WeightedChanceItem(ItemNames.VARROCK_TELEPORT_8007,10,5),
			new WeightedChanceItem(ItemNames.FALADOR_TELEPORT_8009,10,5)
	};

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.forId(6199).getConfigurations().put("option:open", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final Item item = RandomFunction.rollWeightedChanceTable(REWARDS);
		String name = item.getName().toLowerCase();
		if(item.getId() == 0){
			name = "nothing";
		}
		final Item box = (Item) node;
		if (player.getInventory().remove(box, box.getSlot(), true)) {
			String message;
			player.lock(1);
			if(name.equals("nothing")){
				message = "Inside the box you find nothing :(";
			} else {
				if(item.getAmount() > 1 && item.getId() != 995){
					name = name + "s";
				}
				message = "Inside the box you find " + (item.getAmount() > 1 ? "some" : (StringUtils.isPlusN(name) ? "an" : "a")) + " " + name + "!";
			}
			player.sendMessage(message);
			if(item.getId() != 0)
				player.getInventory().add(item);
		}
		return true;
	}
	

	@Override
	public boolean isWalk() {
		return false;
	}

}
