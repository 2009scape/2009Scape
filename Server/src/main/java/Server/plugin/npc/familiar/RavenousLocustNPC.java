package plugin.npc.familiar;

import plugin.consumable.Consumable;
import plugin.consumable.Consumables;
import plugin.consumable.Food;
import plugin.skill.summoning.familiar.Familiar;
import plugin.skill.summoning.familiar.FamiliarSpecial;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.equipment.WeaponInterface;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.game.world.update.flag.context.Graphics;

/**
 * Represents the Ravenous Locust familiar.
 * @author Aero
 */
@InitializablePlugin
public class RavenousLocustNPC extends Familiar {

	/**
	 * Constructs a new {@code RavenousLocustNPC} {@code Object}.
	 */
	public RavenousLocustNPC() {
		this(null, 7372);
	}

	/**
	 * Constructs a new {@code RavenousLocustNPC} {@code Object}.
	 * @param owner The owner.
	 * @param id The id.
	 */
	public RavenousLocustNPC(Player owner, int id) {
		super(owner, id, 2400, 12820, 12, WeaponInterface.STYLE_ACCURATE);
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return new RavenousLocustNPC(owner, id);
	}

	@Override
	protected boolean specialMove(FamiliarSpecial special) {
		Entity target = special.getTarget();
		if (!canCombatSpecial(target)) {
			return false;
		}
		animate(getProperties().getAttackAnimation());
		graphics(Graphics.create(1346));
		target.graphics(Graphics.create(1347));
		if (target instanceof Player) {
			Player p = target.asPlayer();
			for (Item item : p.getInventory().toArray()) {
				if (item == null) {
					continue;
				}
				Consumable consumable = Consumables.getConsumableById(item.getId());
				if (consumable != null) {
					p.getInventory().remove(item);
					break;
				}
			}
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 7372, 7373 };
	}

}
