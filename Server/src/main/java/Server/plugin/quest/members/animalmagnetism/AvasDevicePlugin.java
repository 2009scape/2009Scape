package plugin.quest.members.animalmagnetism;

import core.cache.def.impl.ItemDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.entity.state.EntityState;
import core.game.node.item.Item;
import core.plugin.Plugin;
import core.plugin.PluginManager;

/**
 * Handles the equippage event of an ava device.
 * @author Vexia
 */
public final class AvasDevicePlugin implements Plugin<Object> {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		AnimalMagnetism.AVAS_ACCUMULATOR.getDefinition().getConfigurations().put("equipment", this);
		AnimalMagnetism.AVAS_ATTRACTOR.getDefinition().getConfigurations().put("equipment", this);
		PluginManager.definePlugin(new DisableDevicePlugin());
		return this;
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		final Player player = (Player) args[0];
		final Item item = (Item) args[1];
		switch (identifier) {
		case "equip":
			player.getStateManager().register(EntityState.AVA_DEVICE, true, item);
			break;
		case "unequip":
			if (args.length == 3) {
				Item second = (Item) args[2];
				if (second.getId() == 10498 || second.getId() == 10499) {
					player.getStateManager().register(EntityState.AVA_DEVICE, true, second);
					break;
				}
			}
			player.getStateManager().remove(EntityState.AVA_DEVICE);
			break;
		}
		return true;
	}
	
	/**
	 * Handles the disabling of Ava's devices -- they'll no longer randomly collect loot.
	 * @author Splinter
	 */
	public final class DisableDevicePlugin extends OptionHandler {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			ItemDefinition.forId(10499).getConfigurations().put("option:operate", this);
			ItemDefinition.forId(10498).getConfigurations().put("option:operate", this);
			return this;
		}

		@Override
		public boolean handle(Player player, Node node, String option) {
			player.getGlobalData().setAvasDisabled(!player.getGlobalData().isAvasDisabled());
			player.sendMessage("<col=990000>Ava's device will "+(player.getGlobalData().isAvasDisabled() ? "no longer" : "now")+" randomly collect loot for you.</col>");
			return true;
		}
	}

}
