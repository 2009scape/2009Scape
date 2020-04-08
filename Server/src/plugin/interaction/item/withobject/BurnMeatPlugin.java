/*
package plugin.interaction.item.withobject;

import org.crandor.game.content.global.consumable.Consumables;
import org.crandor.game.content.global.consumable.Food;
import org.crandor.game.interaction.NodeUsageEvent;
import org.crandor.game.interaction.UseWithHandler;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.GameMessageContext;
import org.crandor.net.packet.out.GameMessage;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

*/
/**
 * Represents the plugin used to burn a fire.
 * @author 'Vexia
 * @version 1.0
 *//*

@InitializablePlugin
public final class BurnMeatPlugin extends UseWithHandler {

	*/
/**
	 * Represents the objects allowed to cook {@code Food} on.
	 *//*

	private static final int[] OBJECTS = new int[] { 21302, 13528, 13529, 13533, 13531, 13536, 13539, 13542, 2728, 2729, 2730, 2731, 2732, 2859, 3038, 3039, 3769, 3775, 4265, 4266, 5249, 5499, 5631, 5632, 5981, 9682, 10433, 11404, 11405, 11406, 12102, 12796, 13337, 13881, 14169, 14919, 15156, 20000, 20001, 21620, 21792, 22713, 22714, 23046, 24283, 24284, 25155, 25156, 25465, 25730, 27297, 29139, 30017, 32099, 33500, 34495, 34546, 36973, 37597, 37629, 37726, 114, 4172, 5275, 8750, 16893, 22154, 34410, 34565, 114, 9085, 9086, 9087, 12269, 15398, 25440, 25441, 2724, 2725, 2726, 4618, 4650, 5165, 6093, 6094, 6095, 6096, 8712, 9439, 9440, 9441, 10824, 17640, 17641, 17642, 17643, 18039, 21795, 24285, 24329, 27251, 33498, 35449, 36815, 36816, 37426, 40110 };

	*/
/**
	 * Represents the animation used when cooking over a range.
	 *//*

	private static final Animation RANGE_ANIMATION = new Animation(883);

	*/
/**
	 * Represents the animation used when cooking over a fire.
	 *//*

	private static final Animation FIRE_ANIMATION = new Animation(897);

	*/
/**
	 * Constructs a new {@code BurnMeatPlugin} {@code Object}.
	 *//*

	public BurnMeatPlugin() {
		super(2140, 1861, 3228, 7521, 3151, 325, 319, 347, 355, 333, 339, 351, 329, 3381, 361, 10136, 5003, 379, 365, 373, 2149, 7946, 385, 397, 391, 3369, 3371, 3373, 1893, 1895, 1897, 1899, 1901, 1963, 2102, 2120, 2108, 5972, 5504, 1982, 1965, 1957, 5988, 1781, 6701, 6703, 6705, 7056, 7058, 7060, 1933, 1783, 1927, 1929, 6032, 6034, 2011, 227, 1921, 1937, 7934, 7086, 4239, 7068, 7086, 2003, 7078, 7072, 4239, 7062, 7064, 7084, 1871, 7082, 2309, 1891, 1967, 1971, 2142, 9436, 2142, 2142, 2325, 2333, 2327, 2331, 7170, 2323, 2335, 7178, 7180, 7188, 7190, 7198, 7200, 7208, 7210, 7218, 7220, 2289, 2291, 2293, 2295, 2297, 2299, 2301, 2303, 315, 9988, 2878, 7568, 9980, 7223, 5982, 5984);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (int id : OBJECTS) {
			addHandler(id, OBJECT_TYPE, this);
		}
		System.out.println("Burned meat plugin successfully initialized");
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		final Food food = Consumables.forFood(event.getUsedItem());
		final GameObject object = (GameObject) event.getUsedWith();
		final Item usedItem = event.getUsedItem();
		if (food.getBurnt() == null) {
			player.getPacketDispatch().sendMessage("You can't burn this piece of food.");
			return true;
		}
		player.debug("Used Item: " + usedItem + " In inventory? " + player.getInventory().containsItem(usedItem));
		if (player.getInventory().containsItem(usedItem)) {
			player.getInventory().remove(usedItem);
			player.lock(3);
			player.animate(!object.getName().toLowerCase().equals("fire") ? RANGE_ANIMATION : FIRE_ANIMATION);
			player.getInventory().add(food.getBurnt());
			player.getPacketDispatch().sendMessage("You deliberately burn the perfectly good piece of meat.");
		}
		return true;
	}

}
*/
