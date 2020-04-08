package plugin.skill.magic.lunar;

import org.crandor.game.content.global.consumable.Consumables;
import org.crandor.game.content.global.consumable.Food;
import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import plugin.skill.cooking.CookableItems;
import org.crandor.game.content.skill.free.magic.MagicSpell;
import org.crandor.game.content.skill.free.magic.Runes;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.equipment.SpellType;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.SpellBookManager.SpellBook;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the pie baking lunar spell.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class BakePieSpell extends MagicSpell {

	/**
	 * Represents the animation of the spell.
	 */
	private static final Animation ANIMATION = Animation.create(4413);

	/**
	 * Represents the graphics to use.
	 */
	private static final Graphics GRAPHIC = new Graphics(746, (150 << 16));

	/**
	 * Constructs a new {@code BakePieSpell} {@code Object}.
	 */
	public BakePieSpell() {
		super(SpellBook.LUNAR, 65, 60, null, null, null, new Item[] { new Item(Runes.ASTRAL_RUNE.getId(), 1), new Item(Runes.FIRE_RUNE.getId(), 5), new Item(Runes.WATER_RUNE.getId(), 4) });
	}

	@Override
	public Plugin<SpellType> newInstance(SpellType arg) throws Throwable {
		SpellBook.LUNAR.register(15, this);
		return this;
	}

	@Override
	public boolean cast(Entity entity, Node target) {
		final Player player = ((Player) entity);
		if (!super.meetsRequirements(player, true, false)) {
			return false;
		}
		Item foodItem = target.asItem();
		CookableItems food = null;
		for (Item item : player.getInventory().toArray()) {
			if (item == null) {
				continue;
			}
			if (item.getName().toLowerCase().contains("pie") && item.getName().toLowerCase().contains("uncooked") || item.getName().toLowerCase().contains("raw")) {
				food = CookableItems.forId(item.getId());
			}
		}
		if (food == null) {
			player.getPacketDispatch().sendMessage("You need a pie in order to cast this spell.");
			return false;
		}
		player.getPulseManager().run(new LunarPiePulse(player, foodItem, food));
		return true;
	}

	/**
	 * Represents the skill pulse used to cook a pie.
	 * @author 'Vexia
	 */
	public final class LunarPiePulse extends SkillPulse<Item> {

		//the pie
		private CookableItems pie;

		/**
		 * Constructs a new {@code BakePieSpell} {@code Object}.
		 * @param player the player.
		 * @param node the node.
		 */
		public LunarPiePulse(final Player player, final Item node, final CookableItems pie) {
			super(player, node);
			this.pie = pie;
		}

		@Override
		public boolean checkRequirements() {
			if (player.getSkills().getLevel(Skills.COOKING) < pie.level) {
				player.getDialogueInterpreter().sendDialogue("You need to have a Cooking level of " + pie.level + " to cook this pie.");
				return false;
			}
			if (!player.getInventory().containsItem(new Item(pie.raw))) {
				stop();
				return false;
			}
			return true;
		}

		@Override
		public void animate() {
			player.animate(ANIMATION);
			player.graphics(GRAPHIC);
		}

		@Override
		public boolean reward() {
			if (getDelay() == 1) {
				setDelay(5);
				return false;
			}
			if (player.getInventory().remove(new Item(pie.raw)) && meetsRequirements(player, true, true)) {
				player.getInventory().add(new Item(pie.cooked));
			} else {
				return true;
			}
			return nextPie() == null;
		}

		@Override
		public void stop() {
			super.stop();
			player.graphics(new Graphics(-1));
		}

		@Override
		public void message(int type) {
		}

		/**
		 * Method used to get the next pie.
		 * @return the pie.
		 */
		public CookableItems nextPie() {
			for (Item item : player.getInventory().toArray()) {
				if (item == null) {
					continue;
				}
				if (item.getName().toLowerCase().contains("pie") && item.getName().toLowerCase().contains("uncooked") || item.getName().toLowerCase().contains("raw")) {
					if(CookableItems.cookingMap.get(item.getId()) == null){
						pie = null;
					}
					pie = CookableItems.forId(item.getId());
					node = item;
				}
			}
			return pie;
		}
	}
}
