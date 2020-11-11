package plugin.interaction.inter;

import core.game.component.Component;
import core.game.component.ComponentDefinition;
import core.game.component.ComponentPlugin;
import core.game.node.entity.player.link.diary.DiaryType;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the plugin related to the custom fur clothing interface.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class FurClothingInterface extends ComponentPlugin {

	/**
	 * Represents the item array of clothing.
	 */
	private static final Item[] ITEMS = new Item[] { new Item(10065), new Item(10067), new Item(10053), new Item(10055), new Item(10057), new Item(10059), new Item(10061), new Item(10063), new Item(10075), new Item(10069), new Item(10045), new Item(10043), new Item(10041), new Item(10051), new Item(10049), new Item(10047), new Item(10039), new Item(10037), new Item(10035), new Item(10071) };

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(477, this);
		new FancyDressOwnerDialogue().init();
		return this;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
		final Object[] data = FurClothing.getData(slot);
		if (data == null) {
			return false;
		}
		final FurClothing clothing = ((FurClothing) data[0]);
		final int index = (int) data[1];
		switch (opcode) {
		case 133:
			switch (button) {
			case 75:
				player.getPacketDispatch().sendMessage(clothing.getValueMessage(index));
				break;
			}
			break;
		case 236:// 1
			clothing.buy(player, index, 1);
			break;
		case 157:// 5
			clothing.buy(player, index, 5);
			break;
		case 243:// 10
			clothing.buy(player, index, 10);
			break;
		}
		return true;
	}

	/**
	 * Method used to open the fur clothing interface.
	 * @param player the player.
	 */
	//public static void open(final Player player) {
	//	player.getInterfaceManager().open(new Component(477));
	//	PacketRepository.send(ContainerPacket.class, new ContainerContext(player, 477, 75, 91, new Container(21, ITEMS), true));
	//}

	/**
	 * Represents fur clothing.
	 * @author 'Vexia
	 * @version 1.0
	 */

	public enum FurClothing {
		POLAR(new Item[][] { { new Item(10117, 2), new Item(10065), new Item(995, 20) }, { new Item(10117, 2), new Item(10067), new Item(995, 20) } }, 0, 1),
		COMMON(new Item[][] { { new Item(10121, 2), new Item(10053), new Item(995, 20) }, { new Item(10121, 2), new Item(10055), new Item(995, 20) } }, 2, 3),
		FELDIP(new Item[][] { { new Item(10119, 2), new Item(10057), new Item(995, 20) }, { new Item(10119, 2), new Item(10059), new Item(995, 20) } }, 4, 5),
		DESERT(new Item[][] { { new Item(10123, 2), new Item(10061), new Item(995, 20) }, { new Item(10123, 2), new Item(10063), new Item(995, 20) } }, 6, 7),
		LARUPIA(new Item[][] { { new Item(10095), new Item(10045), new Item(995, 500) }, { new Item(10095), new Item(10043), new Item(995, 100) }, { new Item(10095), new Item(10041), new Item(995, 100) } }, 10, 11, 12),
		GRAAHK(new Item[][] { { new Item(10099), new Item(10051), new Item(995, 750) }, { new Item(10099), new Item(10049), new Item(995, 150) }, { new Item(10099), new Item(10047), new Item(995, 150) } }, 13, 14, 15),
		KYATT(new Item[][] { { new Item(10103), new Item(10039), new Item(995, 1000) }, { new Item(10103), new Item(10037), new Item(995, 250) }, { new Item(10103), new Item(10035), new Item(995, 250) } }, 16, 17, 18),
		DARK_KEBBIT(new Item[][] { { new Item(10115, 2), new Item(10075), new Item(995, 600) } }, 8),
		SPOTTED_KEBBIT(new Item[][] { { new Item(10125, 2), new Item(10069), new Item(995, 400) } }, 9),
		DASHING_KEBBIT(new Item[][] { { new Item(10127, 2), new Item(10071), new Item(995, 800) } }, 19);

		/**
		 * Represents the items of the clothing in paralled to childs.
		 */
		private final Item[][] clothing;

		/**
		 * Represents the child ids of the clothing set.
		 */
		private final int[] childs;

		/**
		 * Constructs a new {@code FancyDressOwnerDialogue} {@code Object}.
		 * @param clothing the clothing.
		 * @param childs the childs.
		 */
		FurClothing(final Item[][] clothing, final int... childs) {
			this.clothing = clothing;
			this.childs = childs;
		}

		/**
		 * Method used to buy a clothing piece.
		 * @param player the player.
		 * @param index the index.
		 * @param amount the amount.
		 * @return true if successful buy, false if not
		 */
		public boolean buy(final Player player, final int index, int amount) {
			if (!player.getInventory().containsItem(getCoins(index))) {
				player.getPacketDispatch().sendMessage("You don't have enough coins.");
				return false;
			}
			if (!isAnyFur(index) ? !player.getInventory().containsItem(getFur(index)) : (!player.getInventory().containsItem(getFur(index, true))) && !player.getInventory().containsItem(getFur(index))) {
				player.getPacketDispatch().sendMessage("You don't have the material required to make this item.");
				return false;
			}
			final Item fur = !isAnyFur(index) ? getFur(index) : !player.getInventory().containsItem(getFur(index)) ? getFur(index, true) : getFur(index);
			int inventoryAmount = player.getInventory().getAmount(fur);
			if (amount > inventoryAmount) {
				amount = inventoryAmount;
			}
			final Item coins = new Item(995, getCoins(index).getAmount() * amount);
			if (!player.getInventory().containsItem(coins)) {
				player.getPacketDispatch().sendMessage("You don't have enough coins.");
				return false;
			}
			if (player.getInventory().remove(coins, new Item(fur.getId(), fur.getAmount() * amount))) {
				player.getInventory().add(new Item(getProduct(index).getId(), amount != 1 ? amount / fur.getAmount() : amount));
				return true;
			}
			return false;
		}

		/**
		 * Gets the tile child.
		 * @return the title.
		 */
		public int getTitleChild() {
			return childs[0];
		}

		/**
		 * Gets the fur item.
		 * @param index the index.
		 * @param all if both furs.
		 * @return the fur.
		 */
		public Item getFur(int index, boolean all) {
			return all ? (new Item(clothing[index][0].getId() - 2)) : clothing[index][0];
		}

		/**
		 * Gets the fur item.
		 * @param index the index.
		 * @return the fur.
		 */
		public Item getFur(int index) {
			return getFur(index, false);
		}

		/**
		 * Gets the product item.
		 * @param index the index.
		 * @return the product.
		 */
		public Item getProduct(int index) {
			return clothing[index][1];
		}

		/**
		 * Gets the coins item.
		 * @param index the index.
		 * @return the coins.
		 */
		public Item getCoins(int index) {
			return clothing[index][2];
		}

		/**
		 * Checks if it takes any type of fur(tatty or not).
		 * @param index the index.
		 * @return {@code True} if so.
		 */
		public boolean isAnyFur(int index) {
			return index > 0 && ordinal() > 3 && ordinal() < 7;
		}

		/**
		 * Gets the value message.
		 * @param index the index.
		 * @return the value message.
		 */
		public String getValueMessage(int index) {
			return getProduct(index).getName() + ": Requires " + (isAnyFur(index) ? "any " : "") + getFur(index).getAmount() + " " + getFur(index).getName().toLowerCase() + (getFur(index).getAmount() > 1 ? "s" : "") + " and " + getCoins(index).getAmount() + " coins.";
		}

		/**
		 * Gets the clothing.
		 * @return the clothing.
		 */
		public Item[][] getClothing() {
			return clothing;
		}

		/**
		 * Gets the childs.
		 * @return the childs.
		 */
		public int[] getChilds() {
			return childs;
		}

		/**
		 * Method used to get the data by the slot.
		 * @param slot the slot.
		 * @return the clothing & the current index.
		 */
		public static Object[] getData(int slot) {
			for (FurClothing cloth : values()) {
				for (int i = 0; i < cloth.getChilds().length; i++) {
					if (cloth.getChilds()[i] == slot) {
						return new Object[] { cloth, i };
					}
				}
			}
			return null;
		}
	}

	/**
	 * Represents the fancy dress owner dialogue plugin.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class FancyDressOwnerDialogue extends DialoguePlugin {

		/**
		 * Constructs a new {@code FancyDressOwnerDialogue} {@code Object}.
		 */
		public FancyDressOwnerDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code FancyDressOwnerDialogue} {@code Object}.
		 * @param player the player.
		 */
		public FancyDressOwnerDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new FancyDressOwnerDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			npc("Now you look like someone who goes to a lot of fancy", "dress parties.");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				player("Errr... what are you saying exactly?");
				stage++;
				break;
			case 1:
				npc("I'm just saying that perhaps you would like to peruse", "my selection of garments.");
				stage++;
				break;
			case 2:
				npc("Or, if that doesn't interest you, then maybe you have", "something else to offer? I'm always on the lookout for", "interesting or unusual new materials.");
				stage++;
				break;
			case 3:
				options("Okay, let's see what you've got then.", "I think I might just leave the perusing for now thanks.", "Can you make clothing suitable for hunting?", "What sort of unusual materials did you have in mind?");
				stage++;
				break;
			case 4:

				switch (buttonId) {
				case 1:
					player("Okay, let's see what you've got then.");
					stage = 10;
					break;
				case 2:
					player("I think I might just leave the perusing for now thanks.");
					stage = 100;
					break;
				case 3:
					player("Can you make clothing suitable for hunting?");
					stage = 30;
					break;
				case 4:
					player("What sort of unusual materials did you have in mind?");
					stage = 41;
					break;
				}

				break;
			case 100:
				end();
				break;
			case 30:
				npc("Certainly. Take a look at my range of made-to-order", "items. If you can supply the furs, I'll gladly make any of", "these for you.");
				stage = 31;
				break;
			case 31:
				interpreter.sendOptions("Select a type of gear:","Polar Camouflage","Wood Camouflage","Jungle Camouflage","Desert Camouflage","More...");
				stage = 45;
				break;
				//end();
				//FurClothingInterface.open(player);
				//break;
			case 41:
				npc("Well, some more colourful feathers might be useful. For", "some surreal reason, all I normally seem to get offered", "are large quantities of rather beaten-up looking chicken", "feathers.");
				stage = 42;
				break;
			case 42:
				npc("People must have some very strange pastimes around", "these parts, that's all I can say.");
				stage = 43;
				break;
			case 43:
				player("Ok, let's see what you've got then.");
				stage = 44;
				break;
			case 44:
			case 10:
				end();
				npc.openShop(player);
				break;
			case 45:
				switch(buttonId){
					case 1:
						interpreter.sendOptions("Select one.","Polar camo top","Polar camo bottom");
						stage = 451;
						break;
					case 2:
						interpreter.sendOptions("Select one.", "Wood camo top","Wood camo bottom");
						stage = 452;
						break;
					case 3:
						interpreter.sendOptions("Select one.","Jungle camo top","Jungle camo bottom");
						stage = 453;
						break;
					case 4:
						interpreter.sendOptions("Select one.","Desert camo top","Desert camo bottom");
						stage = 454;
						break;
					case 5:
						interpreter.sendOptions("Select a type of gear:","Larupia","Graahk","Kyatt","Other");
						stage = 46;
						break;
				}
				break;
			case 46:
				switch(buttonId){
					case 1:
						interpreter.sendOptions("Select one.", "Larupia hat", "Larupia top", "Larupia legs");
						stage = 461;
						break;
					case 2:
						interpreter.sendOptions("Select one.", "Graahk headdress", "Graahk top", "Graahk legs");
						stage = 462;
						break;
					case 3:
						interpreter.sendOptions("Select one.", "Kyatt hat", "Kyatt top", "Kyatt legs");
						stage = 463;
						break;
					case 4:
						interpreter.sendOptions("Select one.", "Gloves of silence", "Spotted cape", "Spottier cape");
						stage = 464;
						break;
				}
				break;
			case 451: {
				FurClothing clothing = FurClothing.POLAR;
				switch (buttonId) {
					case 1:
						clothing.buy(player, 0, 1);
						break;
					case 2:
						clothing.buy(player, 1, 1);
						break;
				}
				end();
				break;
			}
			case 452: {
				FurClothing clothing = FurClothing.COMMON;
				switch (buttonId) {
					case 1:
						clothing.buy(player, 0,1);
						break;
					case 2:
						clothing.buy(player,1,1);
						break;
				}
				end();
				break;
			}
			case 453: {
				FurClothing clothing = FurClothing.FELDIP;
				switch (buttonId) {
					case 1:
						clothing.buy(player,0,1);
						break;
					case 2:
						clothing.buy(player,1,1);
						break;
				}
				end();
				break;
			}
				case 454: {
					FurClothing clothing = FurClothing.DESERT;
					switch (buttonId){
						case 1:
							clothing.buy(player,0,1);
							break;
						case 2:
							clothing.buy(player,1,1);
							break;
					}
					end();
					break;
				}
				case 461: {
					FurClothing clothing = FurClothing.LARUPIA;
					switch(buttonId){
						case 1:
							clothing.buy(player,0,1);
							break;
						case 2:
							clothing.buy(player,1,1);
							break;
						case 3:
							clothing.buy(player,2,1);
							break;
					}
					end();
					break;
				}
				case 462: {
					FurClothing clothing = FurClothing.GRAAHK;
					switch(buttonId){
						case 1:
							clothing.buy(player,0,1);
							break;
						case 2:
							clothing.buy(player,1,1);
							break;
						case 3:
							clothing.buy(player,2,1);
							break;
					}
					end();
					break;
				}
				case 463: {
					FurClothing clothing = FurClothing.KYATT;
					switch(buttonId){
						case 1:
							clothing.buy(player,0,1);
							break;
						case 2:
							clothing.buy(player,1,1);
							break;
						case 3:
							clothing.buy(player,2,1);
							break;
					}
					end();
					break;
				}
				case 464:{
					switch(buttonId){
						case 1:{
							FurClothing.DARK_KEBBIT.buy(player, 0, 1);
							break;
						}
						case 2:{
							FurClothing.SPOTTED_KEBBIT.buy(player,0,1);
							break;
						}
						case 3:{
							if (FurClothing.DASHING_KEBBIT.buy(player,0,1)) {
								player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 2, 2);
							}
							break;
						}
					}
					end();
					break;
				}
			}

			return true;

		}


		@Override
		public int[] getIds() {
			return new int[] { 554 };
		}

	}
}
