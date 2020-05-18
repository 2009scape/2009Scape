package plugin.interaction.item;

import java.util.concurrent.TimeUnit;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.container.impl.EquipmentContainer;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.combat.CombatPulse;
import org.crandor.game.node.entity.combat.InteractionType;
import org.crandor.game.node.entity.combat.equipment.SwitchAttack;
import org.crandor.game.node.entity.combat.handlers.DragonfireSwingHandler;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.item.ItemPlugin;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.PluginManager;

/**
 * Handles the dragonfire shield options.
 * @author Emperor
 */
@InitializablePlugin
public final class DragonfireShieldPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.forId(11283).getConfigurations().put("option:empty", this);
		ItemDefinition.forId(11283).getConfigurations().put("option:inspect", this);
		ItemDefinition.forId(11283).getConfigurations().put("option:operate", this);
		ItemDefinition.forId(11284).getConfigurations().put("option:inspect", this);
		ItemDefinition.forId(11284).getConfigurations().put("option:operate", this);
		PluginManager.definePlugin(new DFSItemPlugin());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		Item item = (Item) node;
		switch (option) {
		case "operate":
			boolean usingAttack = !player.getAttribute("dfs_spec", false);
			if (!usingAttack) {
				if (!player.getSettings().isSpecialToggled()) {
					player.getConfigManager().set(301, 0);
				}
				player.removeAttribute("dfs_spec");
				player.getProperties().getCombatPulse().setTemporaryHandler(null);
				return true;
			}
			boolean notCharged = item.getId() == 11284 || item.getCharge() < 20;
			if (player.getSavedData().getGlobalData().getOverChargeDelay() <= System.currentTimeMillis()) {
				item.setCharge(1020);
				player.sendMessage("You use the power from the overcharge lords & charge your shield.");
				notCharged = false;
				player.getSavedData().getGlobalData().setOverChargeDelay(System.currentTimeMillis() + (GameWorld.getSettings().isDevMode() ? TimeUnit.SECONDS.toMillis(10) : TimeUnit.MINUTES.toMicros(10)));
			}
			if (notCharged) {
				player.getPacketDispatch().sendMessage("Your shield has no charges left.");
				return true;
			}
			if (player.getLocks().isLocked("dfs_recharge")) {
				player.getPacketDispatch().sendMessage("Your dragonfire shield is recharging.");
				return true;
			}
			player.getConfigManager().set(301, 1);
			player.setAttribute("dfs_spec", true);
			SwitchAttack attack = new SwitchAttack(null, Animation.create(6696), Graphics.create(1165), new Graphics(1167, 96), Projectile.create(player, null, 1166, 36, 36, 80, 70, 0, 11));
			DragonfireSwingHandler handler = new DragonfireSwingHandler(false, 26, attack, true) {
				@Override
				public int swing(Entity entity, Entity victim, BattleState state) {
					if (entity instanceof Player) {
						Player player = (Player) entity;
						if (!player.getSettings().isSpecialToggled()) {
							player.getConfigManager().set(301, 0);
						}
						player.removeAttribute("dfs_spec");
						Item shield = player.getEquipment().get(EquipmentContainer.SLOT_SHIELD);
						if (shield == null || shield.getId() != 11283) {
							return -1;
						}
						shield.setCharge(shield.getCharge() - 20);
						if (shield.getCharge() < 1) {
							player.getEquipment().replace(new Item(11284), EquipmentContainer.SLOT_SHIELD);
						}
						EquipmentContainer.updateBonuses(player);
						player.getLocks().lock("dfs_recharge",50);
					}
					return super.swing(entity, victim, state);
				}
			};
			attack.setHandler(handler);
			Entity victim = player.getProperties().getCombatPulse().getVictim();
			if (player.getProperties().getCombatPulse().isAttacking() && handler.canSwing(player, victim) == InteractionType.STILL_INTERACT) {
				CombatPulse.swing(player, victim, handler);
				return true;
			}
			player.getProperties().getCombatPulse().setTemporaryHandler(handler);
			return true;
		case "empty":
			player.getInventory().replace(new Item(11284), item.getSlot());
			player.graphics(Graphics.create(1160));
			player.getPacketDispatch().sendMessage("You release the charges.");
			return true;
		case "inspect":
			if (item.getId() == 11284) {
				player.getPacketDispatch().sendMessage("The shield has no charges.");
				return true;
			}
			player.getPacketDispatch().sendMessage("The shield has " + (item.getCharge() / 20) + " charges.");
			return true;
		}
		return false;
	}

	public class DFSItemPlugin extends ItemPlugin {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			register(11283);
			return this;
		}

		@Override
		public Item getDeathItem(Item item) {
			return new Item(11284);
		}

	}

}
