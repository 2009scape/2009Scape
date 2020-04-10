package org.crandor.game.content.eco.ge;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.content.global.consumable.Consumables;
import org.crandor.game.content.global.consumable.Food;
import org.crandor.game.content.skill.free.cooking.recipe.Recipe;
import org.crandor.game.content.skill.free.crafting.GlassProduct;
import org.crandor.game.content.skill.free.crafting.SilverProduct;
import org.crandor.game.content.skill.free.crafting.TanningProduct;
import org.crandor.game.content.skill.free.crafting.armour.LeatherCrafting;
import org.crandor.game.content.skill.free.crafting.gem.Gems;
import org.crandor.game.content.skill.free.crafting.jewellery.JewelleryCrafting;
import org.crandor.game.content.skill.free.crafting.pottery.PotteryItem;
import org.crandor.game.content.skill.free.crafting.spinning.SpinningItem;
import org.crandor.game.content.skill.free.fishing.Fish;
import org.crandor.game.content.skill.free.gather.SkillingResource;
import org.crandor.game.content.skill.free.magic.Runes;
import org.crandor.game.content.skill.free.runecrafting.Talisman;
import org.crandor.game.content.skill.free.runecrafting.Tiara;
import org.crandor.game.content.skill.free.smithing.Bars;
import org.crandor.game.content.skill.member.farming.patch.Allotments;
import org.crandor.game.content.skill.member.fletching.Fletching;
import org.crandor.game.content.skill.member.fletching.items.crossbow.Limb;
import org.crandor.game.content.skill.member.fletching.items.darts.Dart;
import org.crandor.game.content.skill.member.fletching.items.gem.Gem;
import org.crandor.game.content.skill.member.herblore.FinishedPotion;
import org.crandor.game.content.skill.member.herblore.GrindingItem;
import org.crandor.game.content.skill.member.herblore.Herbs;
import org.crandor.game.content.skill.member.herblore.UnfinishedPotion;
import org.crandor.game.content.skill.member.summoning.SummoningPouch;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.mysql.impl.ItemConfigSQLHandler;
import org.crandor.game.world.GameWorld;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Manages several resources being "pumped" into the game by creating offers in
 * the grand exchange.
 * @author Emperor
 */
public final class ResourceManager {

	/**
	 * The resources used for "kick-starting" the eco.
	 */
	private static final int[] RESOURCES = { 3122, 4153, 6809, 10564, 10589, 1215, 4587, 1305, 1434, 7158, 3204, 1377, 1249, 11212, 11230, 6523, 6527, 6528, 6525, 6524, 6128, 6129, 6130, 6131, 6133, 6135, 6137, 6139, 6141, 6143, 6145, 6147, 6149, 6151, 6153 };

	/**
	 * The database path.
	 */
	private static final String DB_PATH = "eco/ge_resource.emp";

	/**
	 * The current stock of resources.
	 */
	private static final List<GrandExchangeOffer> STOCK = new ArrayList<>();

	/**
	 * Loads the resources stock.
	 */
	public static void init() {
		File file = new File("data/" + DB_PATH);
		if (!file.exists()) {
			return;
		}
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw"); FileChannel c = raf.getChannel()) {
			ByteBuffer b = c.map(MapMode.READ_WRITE, 0, c.size());
			int itemId = -1;
			while ((itemId = b.getShort()) != -1) {
				boolean sale = b.get() == 1;
				GrandExchangeOffer offer = new GrandExchangeOffer(itemId, sale);
				offer.setAmount(b.getInt());
				offer.setCompletedAmount(b.getInt());
				offer.setOfferedValue(b.getInt());
				int value = offer.getOfferedValue();
				int shopValue = ItemDefinition.forId(itemId).getValue();
				if (value < (shopValue * 1.05)) {
					value = (int) (shopValue * 1.05);
				}
				offer.setOfferedValue(value);
				offer.setTimeStamp(b.getLong());
				offer.setState(OfferState.values()[b.get()]);
				offer.setTotalCoinExchange(b.getInt());
				offer.setPlayerUID(-1);
				offer.setUid(STOCK.size() + 1);
				STOCK.add(offer);
			}
			raf.close();
			c.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	/**
	 * Dumps the current resources.
	 * @param directory The directory to save to.
	 */
	public static void dump(String directory) {
		File file = new File(directory + DB_PATH);
		ByteBuffer b = ByteBuffer.allocate(50_000_000);
		for (GrandExchangeOffer offer : STOCK) {
			if (offer == null || offer.getState() == OfferState.COMPLETED) {
				continue;
			}
			b.putShort((short) offer.getItemId());
			b.put((byte) (offer.isSell() ? 1 : 0));
			b.putInt(offer.getAmount());
			b.putInt(offer.getCompletedAmount());
			b.putInt(offer.getOfferedValue());
			b.putLong(offer.getTimeStamp());
			b.put((byte) offer.getState().ordinal());
			b.putInt(offer.getTotalCoinExchange());
		}
		b.putShort((short) -1);
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw"); FileChannel c = raf.getChannel()) {
			b.flip();
			c.write(b);
			raf.close();
			c.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	/**
	 * Clears the resource offer.
	 * @param itemId The item id to clear.
	 */
	public static void clearResource(int itemId) {
		for (Iterator<GrandExchangeOffer> it = STOCK.iterator(); it.hasNext();) {
			GrandExchangeOffer offer = it.next();
			if (offer.getItemId() == itemId) {
				offer.setCompletedAmount(offer.getAmount());
				offer.setState(OfferState.COMPLETED);
				it.remove();
			}
		}
	}

	/**
	 * Adds a new resource offer.
	 * @param itemId The item id.
	 * @param amount The amount.
	 * @param sell If the G.E should sell the resource.
	 */
	public static void addResource(int itemId, int amount, boolean sell) {
		GrandExchangeOffer offer = new GrandExchangeOffer(itemId, sell);
		if (offer.getEntry() == null) {
			System.out.println("No Grand Exchange entry found for item " + itemId + "!");
			return;
		}
		offer.setState(OfferState.REGISTERED);
		offer.setAmount(amount);
		offer.setOfferedValue((int) (new Item(itemId).getValue() * 1.05));
		offer.setPlayerUID(-1);
		offer.setUid(STOCK.size() + 1);
		offer.setTimeStamp(System.currentTimeMillis());
		STOCK.add(offer);
	}


	/**
	 * "Kick starts" the economy by adding resources to the Grand Exchange.
	 */
	public static void kickStartEconomy() {
		List<Integer> handledResources = new ArrayList<>();
		int id;
		for (int itemId : RESOURCES) {
			handledResources.add(itemId);
		}
		for (SkillingResource r : SkillingResource.values()) {
			if (!handledResources.contains(id = r.getReward())) {
				handledResources.add(id);
			}
		}
		for (Consumables c : Consumables.values()) {
			if (c.getConsumable() instanceof Food) {
				Food f = c.getConsumable().asFood();
				if (f.getItem() != null && !handledResources.contains(id = f.getItem().getId())) {
					handledResources.add(id);
				}
			}
			if (!handledResources.contains(id = c.getConsumable().getItem().getId())) {
				handledResources.add(id);
			}
		}
		for (Recipe r : Recipe.RECIPES) {
			for (Item item : r.getIngredients()) {
				if (!handledResources.contains(id = item.getId())) {
					handledResources.add(id);
				}
			}
			if (!handledResources.contains(id = r.getBase().getId())) {
				handledResources.add(id);
			}
		}
		for (LeatherCrafting.DragonHide d : LeatherCrafting.DragonHide.values()) {
			if (!handledResources.contains(id = d.getLeather())) {
				handledResources.add(id);
			}
			if (!handledResources.contains(id = d.getProduct())) {
				handledResources.add(id);
			}
		}
		for (LeatherCrafting.SoftLeather s : LeatherCrafting.SoftLeather.values()) {
			if (!handledResources.contains(id = s.getProduct().getId())) {
				handledResources.add(id);
			}
		}
		for (Gems g : Gems.values()) {
			if (!handledResources.contains(id = g.getGem().getId())) {
				handledResources.add(id);
			}
			if (!handledResources.contains(id = g.getUncut().getId())) {
				handledResources.add(id);
			}
		}
		for (JewelleryCrafting.JewelleryItem d : JewelleryCrafting.JewelleryItem.values()) {
			if (!handledResources.contains(id = d.getSendItem())) {
				handledResources.add(id);
			}
			for (int item : d.getItems()) {
				if (!handledResources.contains(id = item)) {
					handledResources.add(id);
				}
			}
		}
		for (PotteryItem item : PotteryItem.values()) {
			if (!handledResources.contains(id = item.getUnfinished().getId())) {
				handledResources.add(id);
			}
			if (!handledResources.contains(id = item.getProduct().getId())) {
				handledResources.add(id);
			}
		}
		for (SpinningItem s : SpinningItem.values()) {
			if (!handledResources.contains(id = s.getNeed())) {
				handledResources.add(id);
			}
			if (!handledResources.contains(id = s.getProduct())) {
				handledResources.add(id);
			}
		}
		for (GlassProduct s : GlassProduct.values()) {
			if (!handledResources.contains(id = s.getProduct())) {
				handledResources.add(id);
			}
		}
		for (SilverProduct s : SilverProduct.values()) {
			if (!handledResources.contains(id = s.getNeeded())) {
				handledResources.add(id);
			}
			if (!handledResources.contains(id = s.getProduct())) {
				handledResources.add(id);
			}
			if (!handledResources.contains(id = s.getStrung())) {
				handledResources.add(id);
			}
		}
		for (TanningProduct t : TanningProduct.values()) {
			if (!handledResources.contains(id = t.getItem())) {
				handledResources.add(id);
			}
			if (!handledResources.contains(id = t.getProduct())) {
				handledResources.add(id);
			}
		}
		for (Fish f : Fish.values()) {
			if (!handledResources.contains(id = f.getItem().getId())) {
				handledResources.add(id);
			}
		}
		for (Runes r : Runes.values()) {
			if (!handledResources.contains(id = r.getId())) {
				handledResources.add(id);
			}
		}
		for (Talisman t : Talisman.values()) {
			if (!handledResources.contains(id = t.getTalisman().getId())) {
				handledResources.add(id);
			}
		}
		for (Tiara t : Tiara.values()) {
			if (!handledResources.contains(id = t.getTiara().getId())) {
				handledResources.add(id);
			}
		}
		for (Bars b : Bars.values()) {
			if (!handledResources.contains(id = b.getProduct())) {
				handledResources.add(id);
			}
			if (!handledResources.contains(id = b.getBarType().getBarType())) {
				handledResources.add(id);
			}
		}
		for (Allotments a : Allotments.values()) {
			if (!handledResources.contains(id = a.getFarmingNode().getSeed().getId())) {
				handledResources.add(id);
			}
			if (!handledResources.contains(id = a.getFarmingNode().getProduct().getId())) {
				handledResources.add(id);
			}
		}
		for (Fletching.ArrowHeads h : Fletching.ArrowHeads.values()) {
			if (!handledResources.contains(id = h.finished)) {
				handledResources.add(id);
			}
			if (!handledResources.contains(id = h.unfinished)) {
				handledResources.add(id);
			}
		}
		for (Dart d : Dart.values()) {
			if (!handledResources.contains(id = d.getProduct().getId())) {
				handledResources.add(id);
			}
		}
		for (Fletching.GemBolts b : Fletching.GemBolts.values()) {
			if (!handledResources.contains(id = b.product)) {
				handledResources.add(id);
			}
		}
		for (Fletching.String b : Fletching.String.values()) {
			if (!handledResources.contains(id = b.unfinished)) {
				handledResources.add(id);
			}
			if (!handledResources.contains(id = b.product)) {
				handledResources.add(id);
			}
		}
		for (Limb l : Limb.values()) {
			if (!handledResources.contains(id = l.getLimb().getId())) {
				handledResources.add(id);
			}
			if (!handledResources.contains(id = l.getStock().getId())) {
				handledResources.add(id);
			}
			if (!handledResources.contains(id = l.getProduct().getId())) {
				handledResources.add(id);
			}
		}
		for (Gem g : Gem.values()) {
			if (!handledResources.contains(id = g.getBolt().getId())) {
				handledResources.add(id);
			}
		}
		for (Fletching.FletchingItems f : Fletching.FletchingItems.values()) {
			if (!handledResources.contains(id = f.getItem().getId())) {
				handledResources.add(id);
			}
		}
		for (Herbs h : Herbs.values()) {
			if (!handledResources.contains(id = h.getHerb().getId())) {
				handledResources.add(id);
			}
			if (!handledResources.contains(id = h.getProduct().getId())) {
				handledResources.add(id);
			}
		}
		for (GrindingItem g : GrindingItem.values()) {
			if (!handledResources.contains(id = g.getProduct().getId())) {
				handledResources.add(id);
			}
			for (Item i : g.getItems()) {
				if (!handledResources.contains(id = i.getId())) {
					handledResources.add(id);
				}
			}
		}
		for (FinishedPotion f : FinishedPotion.values()) {
			if (!handledResources.contains(id = f.getIngredient().getId())) {
				handledResources.add(id);
			}
			if (!handledResources.contains(id = f.getPotion().getId())) {
				handledResources.add(id);
			}
		}
		for (UnfinishedPotion f : UnfinishedPotion.values()) {
			if (!handledResources.contains(id = f.getIngredient().getId())) {
				handledResources.add(id);
			}
			if (!handledResources.contains(id = f.getPotion().getId())) {
				handledResources.add(id);
			}
			if (!handledResources.contains(id = f.getBase().getId())) {
				handledResources.add(id);
			}
		}
		for (SummoningPouch s : SummoningPouch.values()) {
			for (Item item : s.getItems()) {
				if (!handledResources.contains(id = item.getId())) {
					handledResources.add(id);
				}
			}
		}
		for (int itemId : handledResources) {
			ItemDefinition def = ItemDefinition.forId(itemId);
			if (def == null) {
				System.err.println("Roar " + itemId);
				continue;
			}
			int amount = def.getConfiguration(ItemConfigSQLHandler.GE_LIMIT, 500) * 100;
			 addResource(itemId, amount, true);
			System.out.println(amount + " x " + def.getName() + " - " + (int) (new Item(itemId).getValue() * 1.05) + "gp");
		}
		 System.out.println("Added " + handledResources.size() +
		 " resources!");
		 handledResources.clear();
	}

	public static void main(String... args) throws Throwable {
		GameWorld.prompt(false);
		kickStartEconomy();
	}


	/**
	 * Gets the stock.
	 * @return The stock.
	 */
	public static List<GrandExchangeOffer> getStock() {
		return STOCK;
	}

}