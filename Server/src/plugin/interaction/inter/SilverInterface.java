package plugin.interaction.inter;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.component.Component;
import org.crandor.game.component.ComponentDefinition;
import org.crandor.game.component.ComponentPlugin;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.free.crafting.SilverProduct;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.RunScript;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the silver crafting making interface.
 * @author 'Vexia
 */
@InitializablePlugin
public final class SilverInterface extends ComponentPlugin {

	/**
	 * Represents the silver bar item.
	 */
	private static final Item SILVER_BAR = new Item(2355);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(438, this);
		return this;
	}

	@Override
	public boolean handle(final Player p, Component component, int opcode, int button, int slot, int itemId) {
		final SilverProduct silver = SilverProduct.forId(button);
		if (silver == null) {
			return true;
		}
		if (!p.getInventory().contains(silver.getNeeded(), 1)) {
			p.getPacketDispatch().sendMessage("You need a " + ItemDefinition.forId(silver.getNeeded()).getName().toLowerCase() + " to make this item.");
			return true;
		}
		if (silver == SilverProduct.SILVTHRIL_ROD) {
			p.getPacketDispatch().sendMessage("You can't do that yet.");
			return true;
		}
		if (p.getSkills().getLevel(Skills.CRAFTING) < silver.getLevel()) {
			p.getPacketDispatch().sendMessage("You need a crafting level of " + silver.getLevel() + " to make this.");
		}
		int amt = -1;
		switch (opcode) {
		case 155:
			amt = 1;
			break;
		case 196:
			amt = 5;
			break;
		case 124:
			amt = p.getInventory().getAmount(new Item(2355));
			break;
		case 199:
			p.setAttribute("runscript", new RunScript() {
				@Override
				public boolean handle() {
					int ammount = (int) value;
					make(p, silver, ammount);
					return true;
				}
			});
			p.getDialogueInterpreter().sendInput(false, "Enter the amount.");
			return true;
		}
		make(p, silver, amt);
		return true;
	}

	private void make(final Player player, final SilverProduct def, final int ammount) {
		if (!player.getInventory().containsItem(SILVER_BAR)) {
			return;
		}
		player.getInterfaceManager().close();
		GameWorld.Pulser.submit(new Pulse(1, player) {
			int amt = ammount;

			@Override
			public boolean pulse() {
				if (amt < 1) {
					return true;
				}
				if (!player.getInventory().contains(def.getNeeded(), 1)) {
					return true;
				}
				if (player.getInventory().containsItem(SILVER_BAR) && player.getInventory().contains(def.getNeeded(), 1)) {
					player.animate(new Animation(899));
					if (player.getInventory().remove(SILVER_BAR)) {
						player.getInventory().add(new Item(def.getProduct(), def == SilverProduct.SILVER_BOLTS ? 10 : 1));
						player.getSkills().addExperience(Skills.CRAFTING, def.getExp(), true);
					}
				} else {
					return true;
				}
				amt--;
				return false;
			}

			@Override
			public void stop() {
				player.animate(new Animation(-1));
			}
		});
	}
}
