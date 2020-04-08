/*
package plugin.skill.cooking;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.global.consumable.Consumables;
import org.crandor.game.content.global.consumable.Food;
import org.crandor.game.content.skill.free.cooking.CookableItems;
import org.crandor.game.content.skill.free.cooking.CookingPulse;
import org.crandor.game.interaction.NodeUsageEvent;
import org.crandor.game.interaction.UseWithHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.RunScript;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.ChildPositionContext;
import org.crandor.net.packet.out.RepositionChild;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

*/
/**
 * Handles the interaction of a raw food (or if intentionally burning cooked food)
 * with a range/fire.
 * @author ceik
 *//*

@InitializablePlugin
public class CookingPlugin extends UseWithHandler {

	public static int DIALOGUE_ID = 43989;

	*/
/**
	 * Represents the objects allowed to cook {@code Food} on.
	 *//*

	public static final int[] OBJECTS = new int[] { 21302, 13528, 13529, 13533, 13531, 13536, 13539, 13542, 2728, 2729, 2730, 2731, 2732, 2859, 3038, 3039, 3769, 3775, 4265, 4266, 5249, 5499, 5631, 5632, 5981, 9682, 10433, 11404, 11405, 11406, 12102, 12796, 13337, 13881, 14169, 14919, 15156, 20000, 20001, 21620, 21792, 22713, 22714, 23046, 24283, 24284, 25155, 25156, 25465, 25730, 27297, 29139, 30017, 32099, 33500, 34495, 34546, 36973, 37597, 37629, 37726, 114, 4172, 5275, 8750, 16893, 22154, 34410, 34565, 114, 9085, 9086, 9087, 12269, 15398, 25440, 25441, 2724, 2725, 2726, 4618, 4650, 5165, 6093, 6094, 6095, 6096, 8712, 9439, 9440, 9441, 10824, 17640, 17641, 17642, 17643, 18039, 21795, 24285, 24329, 27251, 33498, 35449, 36815, 36816, 37426, 40110 };

	*/
/**
	 * Constructs a new {@code CookingPlugin} {@code Object}.
	 *//*

	public CookingPlugin() {
		super(2140, 1861, 3228, 7521, 3151, 325, 319, 347, 355, 333, 339, 351, 329, 3381, 361, 10136, 5003, 379, 365, 373, 2149, 7946, 385, 397, 391, 3369, 3371, 3373, 1893, 1895, 1897, 1899, 1901, 1963, 2102, 2120, 2108, 5972, 5504, 1982, 1965, 1957, 5988, 1781, 6701, 6703, 6705, 7056, 7058, 7060, 1933, 1783, 1927, 1929, 6032, 6034, 2011, 227, 1921, 1937, 7934, 7086, 4239, 7068, 7086, 2003, 7078, 7072, 4239, 7062, 7064, 7084, 1871, 7082, 2309, 1891, 1967, 1971, 2142, 9436, 2142, 2142, 2325, 2333, 2327, 2331, 7170, 2323, 2335, 7178, 7180, 7188, 7190, 7198, 7200, 7208, 7210, 7218, 7220, 2289, 2291, 2293, 2295, 2297, 2299, 2301, 2303, 315, 9988, 2878, 7568, 9980, 7223, 5982, 598, 4293, 10816, 2138, 2134, 3142, 2136, 1859, 3226, 7518, 3150, 327, 321, 345, 353, 335, 341, 349, 331, 3379, 359, 10138, 5001, 377, 363, 371, 2148, 7944, 383, 395, 389, 3363, 3365, 3367, 5986, 401, 1942, 4237, 2001, 7076, 1871, 7080, 2307, 1889, 2132, 2132, 2132, 2132, 2321, 2319, 7168, 2317, 7176, 7186, 7196, 7206, 7216, 2287, 317, 9986, 2876, 7566, 9984, 7224);
	 }

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		new CookingDialoguePlugin().init();
		for (int object : OBJECTS) {
			addHandler(object, OBJECT_TYPE, this);
		}
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final GameObject object = (GameObject) event.getUsedWith();
		final int food = event.getUsedItem().getId();
		//handles intentional burning (deprecates BurnMeatPlugin)
		if(CookableItems.intentionalBurn(food)){
			event.getPlayer().getPulseManager().run(new CookingPulse(event.getPlayer(),object,event.getUsedItem().getId(),true));
			return true;
		}
		event.getPlayer().getDialogueInterpreter().open(DIALOGUE_ID, food, event.getUsedWith());
		return true;
	}

	@Override
	public Location getDestination(final Player player, final Node node) {
		if (node.getName().toLowerCase().equals("fire")) {
			return player.getLocation().getY() > node.getLocation().getY() ? node.getLocation().transform(0, 1, 0) : player.getLocation().getX() < node.getLocation().getX() ? node.getLocation().transform(-1, 0, 0) : player.getLocation().getX() > node.getLocation().getX() ? node.getLocation().transform(1, 0, 0) : node.getLocation().transform(0, -1, 0);
		} else {
			Direction direction = node.getDirection();
			if (direction == Direction.NORTH) {
				return node.getLocation().transform(1, 1, 0);
			} else if (direction == Direction.SOUTH) {
				return node.getLocation().transform(-1, 0, 0);
			}
			return null;
		}
	}

	public final class CookingDialoguePlugin extends DialoguePlugin {

		*/
/**
		 * Represents the sinew item.
		 *//*

		private final Item SINEW = new Item(9436);

		*/
/**
		 * Represents the meat item.
		 *//*

		private final Item MEAT = new Item(2142);

		*/
/**
		 * Represents the food we're cooking.
		 *//*

		private int food;

		private int product;

		private boolean nonFood = false;

		*/
/**
		 * Represents the obejct we're cooking on.
		 *//*

		private GameObject object;

		*/
/**
		 * Constructs a new {@code CookingDialoguePlugin} {@code Object}.
		 *//*

		public CookingDialoguePlugin() {
			*/
/**
			 * empty.
			 *//*

		}

		*/
/**
		 * Constructs a new {@code CookingDialoguePlugin} {@code Object}.
		 * @param player the player.
		 *//*

		public CookingDialoguePlugin(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new CookingDialoguePlugin(player);
		}

		@Override
		public boolean open(Object... args) {
			food = ((int) args[0]);
			if (args.length == 3){
				nonFood = true;
				stage = 0;
				product = (int)args[1];
				display();
				return true;
			}
			if (player.getInventory().getAmount(CookableItems.forId(food).raw) == 1) {
				end();
				cook(player,object,food,1);
				return true;
			}
			display();
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
				case 0:
					end();
					int amount = getAmount(buttonId);
					if (amount == -1) {
						player.setAttribute("runscript", new RunScript() {
							@Override
							public boolean handle() {
								int amount = (int) value;
								if(nonFood){
									cookNonFood(player,food,product,amount);
									return false;
								}
								cook(player, object, food, amount);
								return false;
							}
						});
						player.getDialogueInterpreter().sendInput(false, "Enter the amount:");
					} else if (amount == 28) {
						player.setAttribute("runscript", new RunScript() {
							@Override
							public boolean handle() {
								if(nonFood){
									cookNonFood(player,food,product,amount);
								}
								cook(player, object, food, amount);
								return false;
							}
						});
					}
					if(nonFood){
						cookNonFood(player,food,product,amount);
						break;
					}
					cook(player, object, food, amount);
					break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { DIALOGUE_ID };
		}

		*/
/**
		 * Method used to display the content food.
		 *//*

		public void display() {
			player.getInterfaceManager().openChatbox(307);
			PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 307, 3, 60, 79));
			player.getPacketDispatch().sendItemZoomOnInterface(nonFood ? product : CookableItems.getCooked(food).getId(), 160, 307, 2);
			player.getPacketDispatch().sendString(nonFood ? ItemDefinition.forId(product).getName() : CookableItems.getCooked(food).getName(), 307, 3);
			stage = 0;
		}

		public void cookNonFood(Player player, int food, int product, int amount){
			player.getPulseManager().run(new CookingPulse(player,food,amount,product,false));
		}

		public void cook(Player player, GameObject object, int food, int amount){
			player.getPulseManager().run(new CookingPulse(player,object,food,amount));
		}

		*/
/**
		 * Method used to get the amount to make based off the button id.
		 * @param buttonId the button id.
		 * @return the amount to make.
		 *//*

		private final int getAmount(final int buttonId) {
			switch (buttonId) {
				case 5:
					return 1;
				case 4:
					return 5;
				case 3:
					return -1;
				case 2:
					return 28;
			}
			return -1;
		}

	}
}
*/
