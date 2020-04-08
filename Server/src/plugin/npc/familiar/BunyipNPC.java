package plugin.npc.familiar;

import org.crandor.game.content.global.consumable.Consumables;
import org.crandor.game.content.global.consumable.Food;
import org.crandor.game.content.skill.Skills;
import plugin.skill.cooking.CookableItems;
import org.crandor.game.content.skill.free.fishing.Fish;
import org.crandor.game.content.skill.member.summoning.familiar.Familiar;
import org.crandor.game.content.skill.member.summoning.familiar.FamiliarSpecial;
import org.crandor.game.interaction.NodeUsageEvent;
import org.crandor.game.interaction.UseWithHandler;
import org.crandor.game.node.entity.combat.equipment.WeaponInterface;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Represents the Bunyip familiar.
 * @author Aero
 * @author Vexia
 */
@InitializablePlugin
public class BunyipNPC extends Familiar {

	/**
	 * The fish.
	 */
	private static final int[] FISH = new int[] { 317, 327, 3150, 345, 321, 353, 335, 341, 349, 3379, 331, 5004, 359, 10138, 5001, 377, 363, 371, 2148, 7944, 3142, 383, 395, 389, 401, 405, 407 };

	/**
	 * The time since the last heal.
	 */
	private int lastHeal;

	/**
	 * Constructs a new {@code BunyipNPC} {@code Object}.
	 */
	public BunyipNPC() {
		this(null, 6813);
	}

	/**
	 * Constructs a new {@code BunyipNPC} {@code Object}.
	 * @param owner The owner.
	 * @param id The id.
	 */
	public BunyipNPC(Player owner, int id) {
		super(owner, id, 4400, 12029, 3, WeaponInterface.STYLE_ACCURATE);
		setLastHeal();
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return new BunyipNPC(owner, id);
	}

	@Override
	public void tick() {
		super.tick();
		if (lastHeal < GameWorld.getTicks()) {
			setLastHeal();
			owner.graphics(Graphics.create(1507), 1);
			owner.getSkills().heal((int) ((int) owner.getSkills().getMaximumLifepoints() * 0.02));
		}
	}

	@Override
	public boolean isPoisonImmune() {
		return true;
	}

	/**
	 * Sets the last heal.
	 */
	public void setLastHeal() {
		this.lastHeal = GameWorld.getTicks() + (int) (15 / 0.6);
	}

	@Override
	protected boolean specialMove(FamiliarSpecial special) {
		Fish fish = Fish.forItem(special.getItem());
		Player player = owner;
		if (fish == null) {
			player.sendMessage("You can't use this special on an object like that.");
			return false;
		}
		Food food = Consumables.forFood(new Item(special.getItem().getId() + 2, 1));
		if (food == null) {
			player.sendMessage("Error: Report to admin.");
			return false;
		}
		if (player.getSkills().getLevel(Skills.COOKING) < CookableItems.forId(special.getItem().getId() + 2).level) {
			player.sendMessage("You need a Cooking level of at least " + CookableItems.forId(special.getItem().getId()).level + " in order to do that.");
			return false;
		}
		if (player.getInventory().remove(special.getItem())) {
			animate(Animation.create(7747));
			graphics(Graphics.create(1481));
			owner.getSkills().heal(food.getProperties().getHealing());
		}
		return true;
	}

	@Override
	public void visualizeSpecialMove() {
		owner.visualize(Animation.create(7660), Graphics.create(1316));
	}

	@Override
	protected void handleFamiliarTick() {
	}

	@Override
	protected void configureFamiliar() {
		UseWithHandler.addHandler(6813, UseWithHandler.NPC_TYPE, new UseWithHandler(FISH) {
			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				addHandler(6814, UseWithHandler.NPC_TYPE, this);
				return this;
			}

			@Override
			public boolean handle(NodeUsageEvent event) {
				Player player = event.getPlayer();
				Fish fish = Fish.forItem(event.getUsedItem());
				Item cookedFish = new Item(fish.getItem().getId() + 2, 1);
				Food food = Consumables.forFood(cookedFish);
				if (food == null) {
					return true;
				}
				player.lock(1);
				Item runes = new Item(555, RandomFunction.random(1, food.getProperties().getHealing()));
				if (player.getInventory().remove(event.getUsedItem())) {
					player.animate(Animation.create(2779));
					Projectile.create(player, event.getUsedWith().asNpc(), 1435).send();
					player.getInventory().add(runes);
					player.sendMessage("The bunyip transmutes the fish into some water runes.");
				}
				return true;
			}
		});
	}

	@Override
	public int[] getIds() {
		return new int[] { 6813, 6184 };
	}

}
