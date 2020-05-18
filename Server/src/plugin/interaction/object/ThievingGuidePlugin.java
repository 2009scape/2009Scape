package plugin.interaction.object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.impl.Animator.Priority;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.ChanceItem;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Represents the thieving guide plugin.
 * @author 'Vexia
 * @date 16/11/2013
 */
@InitializablePlugin
public class ThievingGuidePlugin extends OptionHandler {

	/**
	 * The coin looots.
	 */
	private static final ChanceItem[] COINS = new ChanceItem[] { new ChanceItem(995, 20, 20, 90), new ChanceItem(995, 40, 40, 80) };

	/**
	 * The gem items.
	 */
	private static final ChanceItem[] GEMS = new ChanceItem[] { new ChanceItem(1623, 1, 1, 80), new ChanceItem(1621, 1, 1, 60), new ChanceItem(1619, 1, 1, 8), new ChanceItem(1617, 1, 1, 7) };

	/**
	 * Represents the stethoscope item.
	 */
	private static final Item SETHOSCOPE = new Item(5560);

	/**
	 * Represents the required level to crack a safe.
	 */
	private static final int level = 50;

	/**
	 * Represents the experience gained.
	 */
	private static final double experience = 70;

	/**
	 * Represents the animations to use.
	 */
	private static final Animation[] animations = new Animation[] { new Animation(2247), new Animation(2248), new Animation(1113), new Animation(2244) };

	/**
	 * Represents the cracked safe.
	 */
	private static final int CRACKED_SAFE = 7238;

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(7236).getConfigurations().put("option:crack", this);// wall
		// safe.
		ObjectDefinition.forId(7227).getConfigurations().put("option:disarm", this);// trap
		ObjectDefinition.forId(7256).getConfigurations().put("option:open", this);
		return this;
	}

	@Override
	public boolean handle(final Player player, final Node node, String option) {
		switch (option) {
		case "open":
			player.getDialogueInterpreter().sendDialogues(2266, null, "And where do you think you're going? A little too eager", "I think. Come and talk to me before you go wandering", "around in there.");
			break;
		case "crack":
			if (player.getSkills().getLevel(Skills.THIEVING) < 50) {
				player.getPacketDispatch().sendMessage("You need to be level " + level + " thief to crack this safe.");
				return true;
			}
			if (player.getInventory().freeSlots() == 0) {
				player.getPacketDispatch().sendMessage("Not enough inventory space.");
				return true;
			}
			final boolean success = success(player, Skills.THIEVING);
			player.lock(4);
			player.getPacketDispatch().sendMessage("You start cracking the safe.");
			player.animate(animations[success ? 1 : 0]);
			GameWorld.Pulser.submit(new Pulse(3, player) {
				@Override
				public boolean pulse() {
					if (success) {
						handleSuccess(player, (GameObject) node);
						return true;
					}
					final boolean trapped = RandomFunction.random(3) == 1;
					if (trapped) {
						player.getPacketDispatch().sendMessage("You slip and trigger a trap!");
						player.animate(animations[2]);
						player.getImpactHandler().manualHit(player, RandomFunction.random(2, 6), HitsplatType.NORMAL);
						GameWorld.Pulser.submit(new Pulse(1) {
							@Override
							public boolean pulse() {
								player.animate(new Animation(-1, Priority.HIGH));
								return true;
							}
						});
					}
					return true;
				}
			});
			break;
		case "search":
			player.animate(animations[3]);
			player.getPacketDispatch().sendMessage("You temporarily disarm the trap!");
			break;
		}
		return true;
	}

	/**
	 * Handles the success.
	 * @param player the player.
	 * @param object the object.
	 */
	public void handleSuccess(final Player player, final GameObject object) {
		ObjectBuilder.replace(object, object.transform(CRACKED_SAFE), 1);
		player.getPacketDispatch().sendMessage("You get some loot.");
		player.getSkills().addExperience(Skills.THIEVING, experience, true);
		addItem(player);
	}

	/**
	 * Adds an item.
	 * @param player the player.
	 */
	private void addItem(Player player) {
		ChanceItem[] l = RandomFunction.random(2) == 1 ? GEMS : COINS;
		List<ChanceItem> chances = new ArrayList<>();
		for (ChanceItem c : l) {
			chances.add(c);
		}
		Collections.shuffle(chances);
		int rand = RandomFunction.random(100);
		Item item = null;
		int tries = 0;
		while (item == null) {
			ChanceItem i = chances.get(0);
			if (rand <= i.getChanceRate()) {
				item = i;
				break;
			}
			if (tries > chances.size()) {
				if (i.getId() == 1617) {
					item = COINS[0];
					break;
				}
				item = i;
				break;
			}
			tries++;
		}
	    player.getInventory().add(item);
	}

	/**
	 * Method used to determine the success of a player when thieving.
	 * @param player the player.
	 * @return <code>True</code> if succesfull, <code>False</code> if not.
	 */
	public final boolean success(final Player player, final int skill) {
		double level = player.getSkills().getLevel(skill);
		double req = 50;
		int mod = player.getInventory().containsItem(SETHOSCOPE) ? 8 : 17;
		double successChance = Math.ceil((level * 50 - req * mod) / req / 3 * 4);
		int roll = RandomFunction.random(99);
		if (successChance >= roll) {
			return true;
		}
		return false;
	}
}
