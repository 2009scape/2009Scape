package plugin.npc.familiar;

import org.crandor.game.content.skill.SkillBonus;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.summoning.familiar.Familiar;
import org.crandor.game.content.skill.member.summoning.familiar.FamiliarSpecial;
import org.crandor.game.content.skill.member.summoning.familiar.Forager;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.path.Pathfinder;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Represents the Beaver familiar.
 * @author Vexia
 * @author Aero
 */
@InitializablePlugin
public class BeaverNPC extends Forager {

	/**
	 * The items to foraged.
	 */
	private static final Item[] ITEMS = new Item[] { new Item(1511), new Item(2862), new Item(1521), new Item(1519), new Item(6333), new Item(10810), new Item(1517), new Item(6332), new Item(12581), new Item(960), new Item(8778) };

	/**
	 * The tree names the scroll can be casted on.
	 */
	private static final String[] TREE_NAMES = new String[] { "Tree", "Oak", "Hollow", "Willow", "Arctic pine", "Eucalyptus", "Maple", "Yew", "Magic", "Cursed magic" };

	/**
	 * If multi chopping.
	 */
	private boolean multiChop;

	/**
	 * Constructs a new {@code BeaverNPC} {@code Object}.
	 */
	public BeaverNPC() {
		this(null, 6808);
	}

	/**
	 * Constructs a new {@code BeaverNPC} {@code Object}.
	 * @param owner The owner.
	 * @param id The id.
	 */
	public BeaverNPC(Player owner, int id) {
		super(owner, id, 2700, 12021, 6, ITEMS);
		boosts.add(new SkillBonus(Skills.WOODCUTTING, 2));
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return new BeaverNPC(owner, id);
	}

	@Override
	protected boolean specialMove(FamiliarSpecial special) {
		final GameObject object = (GameObject) special.getNode();
		if (!isTree(object.getName())) {
			owner.getPacketDispatch().sendMessages("This scroll only works on naturally growing, oak, willow, arctic pine", "teak, mahogany, maple, yew, and magic trees.");
			return false;
		}
		if (owner.getInventory().freeSlots() == 0) {
			return false;
		}
		if (object.getLocation().getDistance(getLocation()) > 5) {
			owner.getPacketDispatch().sendMessages("The beaver is a little too far from the tree for the scroll to work - stand", "closer.");
			return false;
		}
		Direction dir = Direction.getLogicalDirection(getLocation(), object.getLocation());
		Pathfinder.find(getLocation(), object.getLocation().transform(dir)).walk(this);
		final int ticks = 2 + (int) Math.floor(owner.getLocation().getDistance(object.getLocation().transform(dir)) * 0.5);
		owner.lock(ticks);
		multiChop = true;
		getPulseManager().clear();
		GameWorld.Pulser.submit(new Pulse(ticks, owner, this) {
			@Override
			public boolean pulse() {
				lock(11);
				owner.lock(11);
				faceLocation(object.getLocation());
				animate(Animation.create(7722));
				GameWorld.Pulser.submit(new Pulse(1, owner, BeaverNPC.this) {
					int counter;
					boolean recieved = false;

					@Override
					public boolean pulse() {
						switch (++counter) {
						default:
							if (counter > 3) {
								if (RandomFunction.random(12) < 4) {
									owner.getInventory().add(ITEMS[RandomFunction.random(ITEMS.length)], owner);
									recieved = true;
								}
							}
							break;
						case 11:
							if (!recieved) {
								owner.getInventory().add(ITEMS[RandomFunction.random(ITEMS.length)], owner);
							}
							multiChop = false;
							return true;
						}
						return false;
					}
				});
				return true;
			}
		});
		return true;
	}

	@Override
	public void startFollowing() {
		if (multiChop) {
			return;
		}
		super.startFollowing();
	}

	/**
	 * Checks if its a tree.
	 * @param name the name.
	 * @return {@code True} if so.
	 */
	private boolean isTree(final String name) {
		for (String s : TREE_NAMES) {
			if (s.equals(name)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int[] getIds() {
		return new int[] { 6808 };
	}

}
