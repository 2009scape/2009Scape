package plugin.npc.familiar;

import org.crandor.game.content.skill.member.summoning.familiar.Familiar;
import org.crandor.game.content.skill.member.summoning.familiar.FamiliarSpecial;
import org.crandor.game.content.skill.member.summoning.familiar.Forager;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Represents the Fruit Bat familiar.
 * @author Aero
 */
@InitializablePlugin
public class FruitBatNPC extends Forager {

	/**
	 * The random fruit to forge.
	 */
	private static final Item[] FRUIT = new Item[] { new Item(5504), new Item(5982), new Item(2114), new Item(5974), new Item(5972), new Item(2108), new Item(2114), new Item(2102), new Item(2120) };

	/**
	 * Constructs a new {@code FruitBatNPC} {@code Object}.
	 */
	public FruitBatNPC() {
		this(null, 6817);
	}

	/**
	 * Constructs a new {@code FruitBatNPC} {@code Object}.
	 * @param owner The owner.
	 * @param id The id.
	 */
	public FruitBatNPC(Player owner, int id) {
		super(owner, id, 4500, 12033, 6, FRUIT);
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return new FruitBatNPC(owner, id);
	}

	@Override
	public int[] getIds() {
		return new int[] { 6817 };
	}

	@Override
	protected boolean specialMove(FamiliarSpecial special) {
		if (owner.getAttribute("fruit-bat", 0) > GameWorld.getTicks()) {
			return false;
		}
		final boolean goodFruit = RandomFunction.random(100) == 1;
		final int fruitAmount = !goodFruit && RandomFunction.random(10) == 1 ? RandomFunction.random(0, 2) : RandomFunction.random(0, goodFruit ? 8 : 4);
		animate(new Animation(8320));
		graphics(new Graphics(1332, 200));
		animate(new Animation(8321), 3);
		graphics(new Graphics(1331), 4);
		owner.setAttribute("fruit-bat", GameWorld.getTicks() + 5);
		lock(4);
		GameWorld.Pulser.submit(new Pulse(4, this) {
			@Override
			public boolean pulse() {
				for (int i = 0; i < fruitAmount; i++) {
					GroundItemManager.create(RandomFunction.getRandomElement(FRUIT), getLocation().transform(RandomFunction.random(2), RandomFunction.random(2), 0), owner);
				}
				return true;
			}
		});
		return true;
	}

}
