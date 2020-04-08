package plugin.interaction.item;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.content.global.consumable.Consumable;
import org.crandor.game.content.global.consumable.Consumables;
import org.crandor.game.content.global.consumable.Drink;
import org.crandor.game.content.global.consumable.Food;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

import java.util.HashMap;

/**
 * Handles items with "empty" options
 * @author ceik
 */
@InitializablePlugin
public final class EmptyOptionPlugin extends OptionHandler {
	public static final int BUCKET = 1925;
	public static final int VIAL = 229;
	public static final String BUCKET_EMPTY_MSG = "You empty the contents of the bucket onto the floor.";
	@Override
	public boolean handle(Player player, Node node, String option) {
		if(EmptyItem.emptyItemMap.get(node.getId()) != null){
			player.getInventory().remove(node.asItem());
			player.getInventory().add(EmptyItem.getEmpty(node.getId()));
			player.getPacketDispatch().sendMessage(EmptyItem.getEmptyMessage(node.getId()));
		} else {
			player.debug("Unhandled empty option! ITEM ID: " + node.getId());
		}
		return true;
	}

	@Override
	public boolean isWalk() {
		return false;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.setOptionHandler("empty", this);
		ItemDefinition.setOptionHandler("empty dish", this);
		return this;
	}
	public enum EmptyItem{
		POT_OF_FLOUR(1933, 1931, "You empty the contents of the pot onto the floor."),
		BONE_MEAL(4255, 1931, "You empty the pot of crushed bones."),
		BUCKET_OF_SAND(1783, BUCKET, BUCKET_EMPTY_MSG),
		BUCKET_OF_MILK(1927, BUCKET, BUCKET_EMPTY_MSG),
		BUCKET_OF_WATER(1929, BUCKET, BUCKET_EMPTY_MSG),
		BUCKET_OF_COMPOST(6032, BUCKET, "You empty the bucket of compost."),
		BUCKET_OF_SUPERCOMPOST(6034, BUCKET, "You empty the bucket of supercompost."),
		BUCKET_OF_SLIME(4286, BUCKET, "You empty the contents of the bucket on the floor."),
		VIAL_OF_WATER(227, VIAL, "You empty the vial."),
		BOWL_OF_SAND(1921, 1923, "You empty the contents of the bowl onto the floor."),
		JUG_OF_WATER(1937, 1935, "You empty the contents of the jug onto the floor."),
		BURNT_PIE(2329, 2313, "You empty the pie dish.");

		int fullId, emptyId;
		String emptyMessage;
		EmptyItem(int fullId, int emptyId, String emptyMessage){
			this.fullId = fullId;
			this.emptyId = emptyId;
			this.emptyMessage = emptyMessage;
		}

		public static HashMap<Integer,Integer> emptyItemMap = new HashMap<>();
		public static HashMap<Integer,String> emptyMessageMap = new HashMap<>();

		static{
			for(EmptyItem item : EmptyItem.values()){
				emptyItemMap.putIfAbsent(item.fullId,item.emptyId);
				emptyMessageMap.putIfAbsent(item.fullId,item.emptyMessage);
			}
		}

		public static Item getEmpty(int id){
			return new Item(emptyItemMap.get(id));
		}

		public static String getEmptyMessage(int id){
			return emptyMessageMap.get(id);
		}
	}
}
