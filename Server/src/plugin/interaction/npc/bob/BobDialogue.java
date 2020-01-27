package plugin.interaction.npc.bob;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.global.RepairItem;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.diary.AchievementDiary;
import org.crandor.game.node.entity.player.link.diary.DiaryType;
import org.crandor.game.node.item.Item;

/**
 * Represents the dialogue plugin used for the bob npc who repairs items.
 * @author 'Vexia
 * @version 1.0
 */
public final class BobDialogue extends DialoguePlugin {

	/**
	 * Represents the item id being repaired.
	 */
	private int itemId = 0;

	/**
	 * Represents the item being repaired.
	 */
	private Item item;

	/**
	 * Represents the item repairing.
	 */
	private static RepairItem repairitem = null;
	
	/**
	 * The achievement diary.
	 */
	private AchievementDiary diary;

	/**
	 * Constructs a new {@code BobDialogue} {@code Object}.
	 */
	public BobDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code BobDialogue} {@code Object}.
	 * @param player the player.
	 */
	public BobDialogue(Player player) {
		super(player);
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 754:
			interpreter.sendOptions("Select an Option", "Yes, please.", "No, thank you.");
			stage = 755;
			break;
		case 755:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, please.");
				stage = 757;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, thank you.");
				stage = 756;
				break;
			}
			break;
		case 756:
			end();
			break;
		case 757:
			end();
			if (repairitem != null) {
				if (!player.getInventory().contains(995, repairitem.getCost())) {
					end();
					player.getPacketDispatch().sendMessage("You don't have enough to pay him.");
					break;
				}
				if (!player.getInventory().contains(itemId, 1)) {
					end();
					return true;
				}
				player.getInventory().remove(new Item(995, repairitem.getCost()));
				if (player.getInventory().remove(new Item(itemId, 1))) {
					player.getInventory().add(repairitem.getProduct());
				}
				String cost = "free";
				if (repairitem.getCost() != 0) {
					cost = repairitem.getCost() + "gp";
				}
				player.getPacketDispatch().sendMessage("Bob fixes your " + ItemDefinition.forId(itemId).getName().toLowerCase().replace("broken", "").trim() + " for " + cost + ".");
			}
			if (repairitem == null) {
				String cost = "free";
				String type = BarrowsEquipment.formatedName(itemId);
				String single = BarrowsEquipment.getSingleName(type);
				String equipment = BarrowsEquipment.getEquipmentType(type);
				String newString = type.toLowerCase().replace(single, "").trim().replace("'s", "");
				StringBuilder newewString = new StringBuilder();
				newewString.append(newString).append(" " + equipment);
				final BarrowsEquipment.BarrowsFullEquipment fullequip = BarrowsEquipment.BarrowsFullEquipment.forName(newewString.toString());
				if (BarrowsEquipment.getFormatedCost(equipment, item) != 0) {
					cost = String.valueOf(BarrowsEquipment.getFormatedCost(equipment, item) + "gp");
				}
				if (!player.getInventory().contains(995, BarrowsEquipment.getFormatedCost(equipment, item))) {
					end();
					player.getPacketDispatch().sendMessage("You don't have enough to pay him.");
					break;
				}
				if (fullequip == null || fullequip.getFull() == null) {
					player.getPacketDispatch().sendMessage("Report this to an administrator!");
					return true;
				}
				if (!player.getInventory().contains(itemId, 1)) {
					end();
					return true;
				}
				player.getInventory().remove(new Item(995, BarrowsEquipment.getFormatedCost(equipment, item)));
				if (player.getInventory().remove(new Item(itemId, 1))) {
					player.getInventory().add(fullequip.getFull());
				}
				player.getPacketDispatch().sendMessage("Bob fixes your " + equipment + " for " + cost + ".");
			}
			break;
		case 678:
			end();
			break;
		case 0:
			switch (buttonId) {
			case 1:
				player("Give me a quest!");
				stage = -5;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Have you anything to sell?");
				stage = 10;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can you repair my items for me?");
				stage = 20;
				break;
			case 4:
				player("I'd like to talk about Achievement Diaries.");
				stage = 30;
				break;
			}
			break;
		case -5:
			interpreter.sendDialogues(npc, FacialExpression.FURIOUS, "Get yer own!");
			stage = -4;
			break;
		case -4:
			end();
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yes! I buy and sell axes! Take your pick (or axe)!");
			stage = 11;
			break;
		case 11:
			end();
			npc.openShop(player);
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Of course I'll repair it, though the materials may cost", "you. Just hand me the item and I'll have a look.");
			stage = 21;
			break;
		case 21:
			end();
			break;
		case 30:
			if (diary == null) {
				diary = player.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE);
			}
			if (diary.isComplete(1) && !diary.hasReward(1)) {
				player("I've done all the medium tasks in my Lumbridge", "Achievement Diary.");
				stage = 150;
				break;
			}
			if (diary.hasReward(1) && diary.isComplete(1) && !player.hasItem(diary.getType().getRewards(1)[0])) {
				player("I've seemed to have lost my explorer's ring...");
				stage = 160;
				break;
			}
			options("What is the Achievement Diary?", "What are the rewards?", "How do I claim the rewards?", "See you later.");
			stage = 31;
			break;
		case 31:
			switch (buttonId) {
			case 1:
				player("What is the Achievement Diary?");
				stage = 110;
				break;
			case 2:
				player("What are the rewards?");
				stage = 120;
				break;
			case 3:
				player("How do I claim the rewards?");
				stage = 130;
				break;
			case 4:
				player("See you later!");
				stage = 140;
				break;
			}
			break;
		case 110:
			npc("Ah, well it's a diary that helps you keep track of", "particular achievements you've made in the world of", "Keldagrim. In Lumbridge and Draynor i can help you", "discover some very useful things indeed.");
			stage++;
			break;
		case 111:
			npc("Eventually with enough exploration you will be", "rewarded for your explorative efforts.");
			stage++;
			break;
		case 112:
			npc("You can access your Achievement Diary by going to", "the Quest Journal. When you've opened the Quest", "Journal click on the green star icon on the top right", "hand corner. This will open the diary.");
			stage = 30;
			break;
		case 120:
			npc("Ah, well there are different rewards for each", "Achievement Diary. For completing the Lumbridge and", "Draynor diary you are presented with an explorer's", "ring.");
			stage++;
			break;
		case 121:
			npc("This ring will become increasingly useful with each", "section of the diary that you complete.");
			stage = 30;
			break;
		case 130:
			npc("You need to complete the taks so that they're all ticked", "off then you can claim your reward. Most of them are", "straightforward although you might find some required", "quests to be started, if not finished.");
			stage++;
			break;
		case 131:
			npc("To claim the explorer's ring speak to Explorer Jack", " in Lumbridge, Ned in Draynor Village or myself.");
			stage = 30;
			break;
		case 140:
			end();
			break;
		case 150:
			npc("Yes I see that, you'll be wanting your", "reward then I assume?");
			stage++;
			break;
		case 151:
			player("Yes please.");
			stage++;
			break;
		case 152:
			diary.setRewarded(1);
			for (Item i : diary.getType().getRewards(1)) {
				player.getInventory().add(i, player);
			}
			npc("This ring is a representation of the adventures you", "went on to complete your tasks.");
			stage ++;
			break;
		case 153:
			player("Wow, thanks!");
			stage = 30;
			break;
		case 160:
			player.getInventory().add(diary.getType().getRewards(1)[0], player);
			npc("You better be more careful this time.");
			stage = -1;
			break;
		}

		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new BobDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		boolean repair = false;
		boolean wrong = false;
		if (npc.getId() == 3797 && args.length == 1) {
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can you repair my items for me?");
			stage = 20;
			return true;
		}
		if (args.length == 1) {
			interpreter.sendOptions("Select an Option", "Give me a quest!", "Have you anything to sell?", "Can you repair my items for me?", "Talk about Achievement Diaries");
			stage = 0;
			return true;
		}
		if (args[1] != null) {
			repair = (boolean) args[1];
		}
		if (args[2] != null) {
			wrong = (boolean) args[2];
		}
		if (args[3] != null) {
			repairitem = RepairItem.forId((int) args[3]);
			itemId = (int) args[3];
		}
		if (args[4] != null) {
			item = (Item) args[4];
		}
		if (repair && !wrong) {
			String cost = "free";
			if (repairitem != null) {
				if (repairitem.getCost() != 0) {
					cost = String.valueOf(repairitem.getCost() + "gp");
				}
			} else {
				String type = BarrowsEquipment.formatedName(itemId);
				String single = BarrowsEquipment.getSingleName(type);
				String equipment = BarrowsEquipment.getEquipmentType(type);
				String newString = type.toLowerCase().replace(single, "").trim().replace("'s", "");
				StringBuilder newewString = new StringBuilder();
				newewString.append(newString).append(" " + equipment);
				if (BarrowsEquipment.getFormatedCost(equipment, item) != 0) {
					cost = String.valueOf(BarrowsEquipment.getFormatedCost(equipment, item) + "gp");
				}
			}
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Quite badly damaged, but easy to repair. Would you", "like me to repair it for " + cost + "?");
			stage = 754;
			return true;
		}
		if (repair == true && wrong == true) {
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Sorry friend, but I can't do anything with that.");
			stage = 678;
			return true;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 519, 3797 };
	}

	/**
	 * Barrows equipment information.
	 * @author 'Vexia
	 */
	public static class BarrowsEquipment {

		/**
		 * Represents the base names.
		 */
		private String[] base = new String[] { "dharok", "verac", "ahrim", "torag", "guthan" };

		/**
		 * The weapon names.
		 */
		private static final String[] weapon_names = new String[] { "flail", "greataxe", "spear", "x-bow", "hammer", "hammers", "staff" };

		/**
		 * The weapon body names.
		 */
		private static final String[] body_names = new String[] { "top", "platebody", "body" };

		/**
		 * The helm names.
		 */
		private static final String[] helm_names = new String[] { "hood", "helm", "coif" };

		/**
		 * The leg names.
		 */
		private static final String[] leg_names = new String[] { "skirt", "legs", "plateskirt", "platelegs" };

		/**
		 * The prices.
		 */
		private static final Object[][] prices = new Object[][] { { "weapon", 100 }, { "body", 90 }, { "legs", 80 }, { "helm", 60 } };

		/**
		 * Represents the items.
		 */
		private static final Object[][] ITEMS = { { 4856, "Ahrim's hood" }, { 4857, "Ahrim's hood" }, { 4858, "Ahrim's hood" }, { 4859, "Ahrim's hood" }, { 4860, "Ahrim's hood" }, { 4862, "Ahrim's staff" }, { 4863, "Ahrim's staff" }, { 4864, "Ahrim's staff" }, { 4865, "Ahrim's staff" }, { 4866, "Ahrim's staff" }, { 4868, "Ahrim's top" }, { 4869, "Ahrim's top" }, { 4870, "Ahrim's top" }, { 4871, "Ahrim's top" }, { 4872, "Ahrim's top" }, { 4874, "Ahrim's skirt" }, { 4875, "Ahrim's skirt" }, { 4876, "Ahrim's skirt" }, { 4877, "Ahrim's skirt" }, { 4878, "Ahrim's skirt" }, { 4880, "Dharok's helm" }, { 4881, "Dharok's helm" }, { 4882, "Dharok's helm" }, { 4883, "Dharok's helm" }, { 4884, "Dharok's helm" }, { 4886, "Dharok's greataxe" }, { 4887, "Dharok's greataxe" }, { 4888, "Dharok's greataxe" }, { 4889, "Dharok's greataxe" }, { 4890, "Dharok's greataxe" }, { 4892, "Dharok's platebody" }, { 4893, "Dharok's platebody" }, { 4894, "Dharok's platebody" }, { 4895, "Dharok's platebody" }, { 4896, "Dharok's platebody" }, { 4898, "Dharok's platelegs" }, { 4899, "Dharok's platelegs" }, { 4900, "Dharok's platelegs" }, { 4901, "Dharok's platelegs" }, { 4902, "Dharok's platelegs" }, { 4904, "Guthan's helm" }, { 4905, "Guthan's helm" }, { 4906, "Guthan's helm" }, { 4907, "Guthan's helm" }, { 4908, "Guthan's helm" }, { 4910, "Guthan's spear" }, { 4911, "Guthan's spear" }, { 4912, "Guthan's spear" }, { 4913, "Guthan's spear" }, { 4914, "Guthan's spear" }, { 4916, "Guthan's body" }, { 4917, "Guthan's body" }, { 4918, "Guthan's body" }, { 4919, "Guthan's body" }, { 4920, "Guthan's body" }, { 4922, "Guthan's skirt" }, { 4923, "Guthan's skirt" }, { 4924, "Guthan's skirt" }, { 4925, "Guthan's skirt" }, { 4926, "Guthan's skirt" }, { 4928, "Karil's coif" }, { 4929, "Karil's coif" }, { 4930, "Karil's coif" }, { 4931, "Karil's coif" }, { 4932, "Karil's coif" }, { 4934, "Karil's x-bow" }, { 4935, "Karil's x-bow" }, { 4936, "Karil's x-bow" }, { 4937, "Karil's x-bow" }, { 4938, "Karil's x-bow" }, { 4940, "Karil's top" }, { 4941, "Karil's top" }, { 4942, "Karil's top" }, { 4943, "Karil's top" }, { 4944, "Karil's top" }, { 4946, "Karil's skirt" }, { 4947, "Karil's skirt" }, { 4948, "Karil's skirt" }, { 4949, "Karil's skirt" }, { 4950, "Karil's skirt" }, { 4952, "Torag's helm" }, { 4953, "Torag's helm" }, { 4954, "Torag's helm" }, { 4955, "Torag's helm" }, { 4956, "Torag's helm" }, { 4958, "Torag's hammers" }, { 4959, "Torag's hammers" }, { 4960, "Torag's hammers" }, { 4961, "Torag's hammers" }, { 4962, "Torag's hammers" }, { 4964, "Torag's body" }, { 4965, "Torag's body" }, { 4966, "Torag's body" }, { 4967, "Torag's body" }, { 4968, "Torag's body" }, { 4970, "Torag's legs" }, { 4971, "Torag's legs" }, { 4972, "Torag's legs" }, { 4973, "Torag's legs" }, { 4974, "Torag's legs" }, { 4976, "Verac's helm" }, { 4977, "Verac's helm" }, { 4978, "Verac's helm" }, { 4979, "Verac's helm" }, { 4980, "Verac's helm" }, { 4982, "Verac's flail" }, { 4983, "Verac's flail" }, { 4984, "Verac's flail" }, { 4985, "Verac's flail" }, { 4986, "Verac's flail" }, { 4988, "Verac's top" }, { 4989, "Verac's top" }, { 4990, "Verac's top" }, { 4991, "Verac's top" }, { 4992, "Verac's top" }, { 4994, "Verac's skirt" }, { 4995, "Verac's skirt" }, { 4996, "Verac's skirt" }, { 4997, "Verac's skirt" }, { 4998, "Verac's skirt" } };

		/**
		 * Gets the cost.
		 * @param name the name.
		 * @return the price.
		 */
		public static int getFormatedCost(String name, Item item) {
			int[] degrades = new int[] { 100, 75, 50, 25, 0 };
			for (int i = 0; i < prices.length; i++) {
				String check = (String) prices[i][0];
				if (check.equals(name)) {
					int degrade = 0;
					for (int d : degrades) {
						if (item.getName().contains(String.valueOf(d))) {
							degrade = d;
							break;
						}
					}
					degrade -= 25 - (25 * (item.getCharge() * 0.001));
					int max = (int) prices[i][1] * 1000;
					return (int) (max - (max * (degrade * 0.01)));
				}
			}
			return 0;
		}

		/**
		 * Gets the cost of the item type.
		 * @param name the name.
		 * @return the return type.
		 */
		public static int getCost(String name) {
			for (int i = 0; i < prices.length; i++) {
				String check = (String) prices[i][0];
				if (check.equals(name)) {
					return (int) prices[i][1];
				}
			}
			return 0;
		}

		/**
		 * Represents if an item is a barrows item.
		 * @param id the id.
		 * @return {@code True} if so.
		 */
		public static boolean isBarrowsItem(int id) {
			for (int i = 0; i < ITEMS.length; i++) {
				if ((int) ITEMS[i][0] == id) {
					return true;
				}
			}
			return false;
		}

		/**
		 * Gets the formatted name.
		 * @param id the id.
		 * @return the name.
		 */
		public static String formatedName(int id) {
			for (int i = 0; i < ITEMS.length; i++) {
				if ((int) ITEMS[i][0] == id) {
					return (String) ITEMS[i][1];
				}
			}
			return null;
		}

		/**
		 * Gets the equipment type.
		 * @param name the name.
		 * @return the type.
		 */
		public static String getEquipmentType(String name) {
			name = name.toLowerCase().replace("verac's", "").replace("karil's", "").replace("dharok's", "").replace("torag's", "").replace("guthan's", "").replace("ahrim's", "").trim();
			for (int i = 0; i < weapon_names.length; i++) {
				if (weapon_names[i].contains(name)) {
					return "weapon";
				}
			}
			for (int k = 0; k < body_names.length; k++) {
				if (body_names[k].contains(name)) {
					return "body";
				}
			}
			for (int z = 0; z < leg_names.length; z++) {
				if (leg_names[z].contains(name)) {
					return "legs";
				}
			}
			for (int q = 0; q < helm_names.length; q++) {
				if (helm_names[q].contains(name)) {
					return "helm";
				}
			}
			return null;
		}

		/**
		 * Method used t get its single name.
		 * @param name the name.
		 * @return the name.
		 */
		public static String getSingleName(String name) {
			name = name.toLowerCase().replace("verac's", "").replace("karil's", "").replace("dharok's", "").replace("torag's", "").replace("guthan's", "").replace("ahrim's", "").trim();
			for (int i = 0; i < weapon_names.length; i++) {
				if (weapon_names[i].contains(name)) {
					return weapon_names[i];
				}
			}
			for (int k = 0; k < body_names.length; k++) {
				if (body_names[k].contains(name)) {
					return body_names[k];
				}
			}
			for (int z = 0; z < leg_names.length; z++) {
				if (leg_names[z].contains(name)) {
					return leg_names[z];
				}
			}
			for (int q = 0; q < helm_names.length; q++) {
				if (helm_names[q].contains(name)) {
					return helm_names[q];
				}
			}
			return null;
		}

		/**
		 * Gets the bases.
		 * @return the base.
		 */
		public String[] getBase() {
			return base;
		}

		/**
		 * Represents the multiple full barrows equipment items.
		 * @author 'Vexia
		 * @version 1.0
		 */
		public enum BarrowsFullEquipment {
			VERAC_LEGS(new Item(4759, 1)), VERAC_TOP(new Item(4757, 1)), VERAC_WEAPON(new Item(4755, 1)), VERAC_HELM(new Item(4753, 1)), TORAG_LEGS(new Item(4751, 1)), TORAG_BODY(new Item(4749, 1)), TORAG_HELM(new Item(4745, 1)), TORAG_WEAPON(new Item(4747, 1)), KARIL_HELM(new Item(4732, 1)), KARIL_WEAPON(new Item(4734, 1)), KARIL_BODY(new Item(4736, 1)), KARIL_LEGS(new Item(4738, 1)), GUTHAN_HELM(new Item(4724, 1)), GUTHAN_BODY(new Item(4728, 1)), GUTHAN_LEGS(new Item(4730, 1)), GUTHAN_WEAPON(new Item(4726, 1)), DHAROK_HELM(new Item(4716, 1)), DHAROK_BODY(new Item(4720, 1)), DHAROK_LEGS(new Item(4722, 1)), DHAROK_WEAPON(new Item(4718, 1)), AHRIM_HELM(new Item(4708, 1)), AHRIM_BODY(new Item(4712, 1)), AHRIM_LEGS(new Item(4714, 1)), AHRIM_WEAPON(new Item(4710, 1));

			/**
			 * Constructs a new {@code BarrowsEquipment} {@code Object}.
			 * @param full the full item.
			 */
			BarrowsFullEquipment(Item full) {
				this.full = full;
			}

			/**
			 * Represents the full item.
			 */
			private final Item full;

			/**
			 * for name
			 * @param name thename.
			 * @return the equipment.
			 */
			public static BarrowsFullEquipment forName(String name) {
				if (name.equals("guthan body body")) {
					name = "guthan body";
				} else if (name.equals("torag body body")) {
					name = "torag body";
				} else if (name.equals("verac body")) {
					name = "verac top";
				}
				for (BarrowsFullEquipment barrow : BarrowsFullEquipment.values()) {
					if (barrow.name().toLowerCase().replace("_", " ").trim().equalsIgnoreCase(name)) {
						return barrow;
					}
				}
				return null;
			}

			/**
			 * Gets the full.
			 * @return The full.
			 */
			public Item getFull() {
				return full;
			}
		}

	}

}