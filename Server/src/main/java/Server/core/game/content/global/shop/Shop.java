package core.game.content.global.shop;

import core.cache.def.impl.ItemDefinition;
import core.game.container.Container;
import core.game.container.ContainerType;
import core.tools.Items;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.game.system.config.ItemConfigParser;
import core.game.world.GameWorld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class representing a shop.
 *
 * @author 'Vexia
 * @author Jamix77
 */
public class Shop {

    /**
     * Represents the general store items.
     */
    public final static Item[] GENERAL_STORE_ITEMS = new Item[]{
            new Item(Items.EMPTY_POT_1931,5),
            new Item(Items.JUG_1935,5),
            new Item(Items.SHEARS_1735,2),
            new Item(Items.BUCKET_1925,3),
            new Item(Items.BOWL_1923,2),
            new Item(Items.CAKE_TIN_1887,2),
            new Item(Items.TINDERBOX_590,2),
            new Item(Items.CHISEL_1755,2),
            new Item(Items.HAMMER_2347,5),
            new Item(Items.NEWCOMER_MAP_550,5),
            new Item(Items.SECURITY_BOOK_9003,5)
    };
    /**
     * Represents the coins item.
     */
    private static final int COINS = 995;
    /**
     * Represents the tokkul item id.
     */
    private static final int TOKKUL = 6529;
    
    /**
     * Represents the archery ticket item id
     */
    private static final int ARCHERY_TICKET = 1464;
    
    /**
     * Represents the shop containers.
     */
    private final Container[] containers = new Container[]{new Container(40, ContainerType.SHOP), new Container(40, ContainerType.SHOP)};
    /**
     * Represents the list of shop viewers.
     */
    private final List<ShopViewer> viewers = new ArrayList<>();

    /**
     * Represents the title of the shop.
     */
    private final String title;

    /**
     * Represents the items in the store.
     */
    private Item[] items;

    /**
     * Represents if it's a general store.
     */
    private final boolean general;

    /**
     * Represents the currency the shop allows.
     */
    private final int currency;

    /**
     * If the shop buys for high alch.
     */
    private final boolean highAlch;

    /**
     * Sell price for all shop items, if needed.
     */
    private int sellAllFor;

    /**
     * The last restock.
     */
    private int lastRestock;

    /**
     * If the shop should restock.
     */
    private boolean restock;

    /**
     * If it's a point shop.
     */
    private boolean pointShop;

    /**
     * The npcs of the shop.
     */
    private int[] npcs;

    /**
     * Constructs a new {@code Shop} {@code Object}.
     *
     * @param title    the title.
     * @param items    the items.
     * @param general  the general.
     * @param currency the currency.
     * @param highAlch if high alch.
     */
    public Shop(String title, Item[] items, boolean general, int currency, boolean highAlch) {
        this.title = title;
        this.items = items;
        this.general = general;
        this.currency = currency;
        this.highAlch = highAlch;
        this.getContainer(0).add(items);
        this.setRestock(true);
        lastRestock = GameWorld.getTicks() + 100;
    }


    /**
     * Constructs a new {@code Shop} {@code Object}.
     *
     * @param title    the title.
     * @param items    the items.
     * @param general  the general.
     * @param currency the currency.
     * @param highAlch if high alch.
     * @param restock  if restock.
     */
    public Shop(String title, Item[] items, boolean general, int currency, boolean highAlch, boolean restock, int sellAllFor) {
        this(title, items, general, currency, highAlch);
        this.sellAllFor = sellAllFor;
        this.setRestock(restock);
    }

    /**
     * Constructs a new {@code Shop} {@code Object}
     *
     * @param title the shop title
     * @param items items the shop can handle
     * @param general is this a general store
     * @param currency what currency is used
     */
    public Shop(String title, Item[] items, int[] npcs, boolean general, int currency) {
        this(title, items, general, currency, false);
        this.setNpcs(npcs);
    }

    /**
     * Constructs a new {@code Shop} {@code Object}.
     *
     * @param title   the title.
     * @param items   the items.
     * @param general the general.
     */
    public Shop(String title, Item[] items, boolean general) {
        this(title, items, general, COINS, false);
    }

    /**
     * Constructs a new {@code Shop} {@code Object}.
     *
     * @param title   the title.
     * @param general the general.
     */
    public Shop(String title, boolean general) {
        this(title, GENERAL_STORE_ITEMS, general);
    }

    /**
     * Constructs a new {@code Shop} {@code Object}.
     *
     * @param title    the title.
     * @param general  the general.
     * @param highAlch if highAlch.
     */
    public Shop(String title, boolean general, boolean highAlch) {
        this(title, GENERAL_STORE_ITEMS, general, COINS, highAlch);
    }

    /**
     * Constructs a new {@code Shop} {@code Object}.
     *
     * @param title    the title.
     * @param general  the general.
     * @param highAlch if highAlch.
     */
    public Shop(String title, boolean general, int currency, boolean highAlch) {
        this(title, GENERAL_STORE_ITEMS, general, currency, highAlch);
    }

    /**
     * Constructs a new {@code Shop} {@code Object}.
     *
     * @param title   the title.
     * @param items   the items.
     * @param npcs    the npcs.
     * @param general the general.
     */
    public Shop(String title, Item[] items, int[] npcs, boolean general) {
        this(title, items, general, COINS, false);
        this.setNpcs(npcs);
    }

    /**
     * Constructs a new {@code Shop} {@code Object}.
     *
     * @param title   the title.
     * @param npcs    the npcs.
     * @param general the general.
     */
    public Shop(String title, int[] npcs, boolean general) {
        this(title, GENERAL_STORE_ITEMS, npcs, general);
        this.setNpcs(npcs);
    }

    /**
     * Constructs a new {@code Shop} {@code Object}.
     *
     * @param title    the title.
     * @param npcs     the npcs.
     * @param general  the general.
     * @param highAlch if highAlch.
     */
    public Shop(String title, int[] npcs, boolean general, boolean highAlch) {
        this(title, GENERAL_STORE_ITEMS, general, 995, highAlch);
        this.setNpcs(npcs);
    }

    /**
     * Method used to open the shop.
     *
     * @param player the shop.
     */
    public void open(final Player player) {
        ShopViewer.extend(player, this).open();

        // Browse the Lumbridge General Store
        if (getTitle().equalsIgnoreCase("Lumbridge General Store")) {
            player.getAchievementDiaryManager().finishTask(player, DiaryType.LUMBRIDGE, 0, 18);
        }

        // Browse through Oziach's Armour Shop
        if (getTitle().equalsIgnoreCase("Oziach's Armour")) {
            player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 1, 20);
        }
    }

    public void give(Player player, final int slot, int amount, int tabIndex) {
        final Container container = getContainer(tabIndex);
        final Item item = container.get(slot);
        if (item == null) {
            return;
        }
        final Item add = new Item(item.getId(), amount);
        if (add.getAmount() < 1 || !player.getInventory().hasSpaceFor(add)) {
            player.getPacketDispatch().sendMessage("You have no inventory space at the moment and cannot get anything.");
            return;
        }
        add.setAmount(getAmount(player, add));
        if (add.getAmount() < 1 || !player.getInventory().hasSpaceFor(add)) {
            player.getPacketDispatch().sendMessage("You have no inventory space at the moment and cannot get anything.");
            return;
        }
        player.getInventory().add(add);
        update();
    }

    /**
     * Method used to buy an item from the shop.
     *
     * @param slot   the slot.
     * @param amount the amount.
     */
    public void buy(Player player, final int slot, int amount, int tabIndex) {
        if (tabIndex == 1 && player.getIronmanManager().checkRestriction()) {
            return;
        }
        final Container container = getContainer(tabIndex);
        final Item item = container.get(slot);
        if (item == null) {
            return;
        }
        if (item.getAmount() == 0) {
            player.getPacketDispatch().sendMessage("There is no stock of that item at the moment.");
            return;
        }
        if (amount > item.getAmount() && !(item.getAmount() == -1)) {
            amount = item.getAmount();
        }
        final Item add = new Item(item.getId(), amount);
        if (player.getInventory().getMaximumAdd(add) < amount) {
            add.setAmount(player.getInventory().getMaximumAdd(add));
        }
        if (add.getAmount() < 1 || !player.getInventory().hasSpaceFor(add)) {
            player.getPacketDispatch().sendMessage("You have no inventory space at the moment and cannot buy anything.");
            return;
        }
        add.setAmount(getAmount(player, add));
        if (add.getAmount() < 1 || !player.getInventory().hasSpaceFor(add)) {
            player.getPacketDispatch().sendMessage("You have no inventory space at the moment and cannot buy anything.");
            return;
        }
        int price = add.getAmount() * getBuyPrice(item, player);
        final Item currency = new Item(getCurrency(), price);
        if (!canBuy(player, item, price, currency)) {
            return;
        }
        if (handleBuy(player, currency)) {
            if (pointShop) {
                decrementPoints(player, price);
            }
            if (tabIndex == 0) {
                if(!(container.getAmount(item) == -1))
                    container.replace(new Item(item.getId(), container.getAmount(item) - add.getAmount()), slot, true);
            } else {
                container.remove(add);
                container.shift();
            }

            // Achievement Diary Handlers
            if (add.getId() == Items.BLACK_CHAINBODY_1107 && getTitle().equalsIgnoreCase("Wayne's Chains") && !player.getAttribute("diary:falador:black-chain-bought", false)) {
                player.setAttribute("/save:diary:falador:black-chain-bought", true);
            }
            if (add.getId() == 12622 && getTitle().equalsIgnoreCase("Sarah's Farming Shop")) {
                player.getAchievementDiaryManager().finishTask(player, DiaryType.FALADOR, 0, 0);
            }
            if (add.getId() == Items.CANDLE_36 && getTitle().equalsIgnoreCase("Candle Shop")) {
                player.getAchievementDiaryManager().finishTask(player, DiaryType.SEERS_VILLAGE, 0, 9);
            }
            if (getTitle().equalsIgnoreCase("Ranging Guild Ticket Exchange")) {
                player.getAchievementDiaryManager().finishTask(player, DiaryType.SEERS_VILLAGE, 1, 8);
            }

            player.getInventory().add(add);
            update();
        }
    }

    /**
     * Method used to sell an item to the shop.
     *
     * @param slot   the slot.
     * @param amount the amount.
     */
    public void sell(Player player, final int slot, int amount, int tabIndex) {
        final Item item = player.getInventory().get(slot);
        if (item == null) {
            return;
        }
        final ItemDefinition def = ItemDefinition.forId(item.getId());
        if (!canSell(player, item, def)) {
            return;
        }
        final Container container = getContainer(item);
        if (amount > player.getInventory().getAmount(item)) {
            amount = player.getInventory().getAmount(item);
        }
        Item add = new Item(item.getId(), amount);
        if (add.getAmount() > container.getMaximumAdd(add)) {
            add.setAmount(container.getMaximumAdd(add));
        }
        player.debug("" + add.getAmount());
        if (!container.hasSpaceFor(add) || add.getAmount() < 1) {
            player.getPacketDispatch().sendMessage("The shop has ran out of space.");
            return;
        }
        final Item currency = new Item(getCurrency(), getSellingValue(add, player));
        if(item.getDefinition().isStackable()){
            if (!player.getInventory().hasSpaceFor(currency)) {
                player.getPacketDispatch().sendMessage("You don't have enough space for that many " + currency.getName().toLowerCase() + ".");
                return;
            }
        }
        player.debug("Selling item");
        if (player.getInventory().remove(add, slot, true)) {
            if (currency.getAmount() > player.getInventory().getMaximumAdd(currency)) {
                currency.setAmount(player.getInventory().getMaximumAdd(currency));
            }
            if (!add.getDefinition().isUnnoted()) {
                add = new Item(add.getNoteChange(), add.getAmount());
            }
            if (container.getAmount(add.getId()) == -1 || container.add(add)) {
                if (currency.getAmount() > 0) {
                    player.debug("Adding coins to inventory");
                    player.getInventory().add(currency);
                }
                final ShopViewer viewer = player.getExtension(ShopViewer.class);
                tabIndex = container == getContainers()[0] ? 0 : 1;
                sendStock(player, tabIndex);
                if (viewer != null) {
                    viewer.setTabIndex(tabIndex);
                }
                update();
            }
        }
    }

    /**
     * Values an item.
     *
     * @param player the player.
     * @param viewer the viewer.
     * @param item   the item.
     * @param sell   the sell.
     */
    public void value(Player player, ShopViewer viewer, Item item, boolean sell) {
        if (sell) {
            if (pointShop || item.getId() == viewer.getShop().getCurrency() || !item.getDefinition().isTradeable() || !viewer.getShop().itemAllowed(item.getId())) {
                player.getPacketDispatch().sendMessage("You can't sell this item.");
                return;
            }
            final int value = viewer.getShop().getSellingValue(new Item(item.getId(), 1), player);
            String currency = pointShop ? getPointsName() : ItemDefinition.forId(viewer.getShop().getCurrency()).getName().toLowerCase();
            if (value == 1 && currency.charAt(currency.length() - 1) == 's') {
                currency = currency.substring(0, currency.length() - 1);
            }
            player.getPacketDispatch().sendMessage(item.getName() + ": shop will buy for " + value + " " + currency + ".");
        } else {
            int value = viewer.getShop().getBuyPrice(item, player);
            String name = pointShop ? getPointsName() + "s" : ItemDefinition.forId(viewer.getShop().getCurrency()).getName().toLowerCase();
            if (value == 1 && (name.charAt(name.length() - 1) == 's')) {
                name = name.substring(0, name.length() - 1);
            }
            player.getPacketDispatch().sendMessage("" + item.getName() + ": currently costs " + value + " " + name + ".");
        }
    }

    /**
     * Method used to send a stock
     *
     * @param player
     * @param tabIndex
     */
    public void sendStock(final Player player, int tabIndex) {
        final boolean main = tabIndex == 0;
        player.getPacketDispatch().sendInterfaceConfig(620, 23, !main);
        player.getPacketDispatch().sendInterfaceConfig(620, 24, main);
        player.getPacketDispatch().sendInterfaceConfig(620, 29, !main);
        player.getPacketDispatch().sendInterfaceConfig(620, 25, main);
        player.getPacketDispatch().sendInterfaceConfig(620, 27, main);
        player.getPacketDispatch().sendInterfaceConfig(620, 26, false);
        player.getPacketDispatch().sendAccessMask(1278, main ? 23 : 24, 620, 0, 40);
    }

    /**
     * Method used to update the viewers.
     */
    public void update() {
        for (ShopViewer viewer : viewers) {
            viewer.update();
        }
    }

    /**
     * Method used to restock the shop.
     */
    public void restock() {
        for (Container container : containers) {
            for (int i = 0; i < container.toArray().length; i++) {
                final boolean main = (container == containers[0]);
                final Item item = container.toArray()[i];
                if (item == null) {
                    continue;
                }
                boolean reduce = !main;
                if (main) {
                    if (item.getAmount() < items[i].getAmount()) {
                        item.setAmount(item.getAmount() + 1);
                    }
                    reduce = item.getAmount() > items[i].getAmount();
                }
                if (reduce) {
                    int amount = item.getAmount() - 1;
                    if (amount < 1 && !main) {
                        container.remove(item);
                    } else {
                        item.setAmount(amount);
                    }
                    if (!main) {
                        container.shift();
                    }
                }
            }
        }
        update();
    }

    /**
     * Checks if the player can sell an item to the shop.
     *
     * @param player the player.
     * @param item   the item.
     * @param def    the def.
     * @return {@code True} if so.
     */
    public boolean canSell(Player player, Item item, ItemDefinition def) {
        if (pointShop || item.getDefinition().hasDestroyAction() || !def.isTradeable() || !itemAllowed(item.getId())) {
            player.getPacketDispatch().sendMessage("You can't sell this item to this shop.");
            return false;
        }
        if (item.getId() == getCurrency()) {
            player.getPacketDispatch().sendMessage("You can't sell " + item.getName().toLowerCase() + " to a shop.");
            return false;
        }
        return true;
    }

    /**
     * Gets the amount to buy/sell.
     *
     * @param player the player.
     * @param add    the added item.
     * @return the amount.
     */
    public int getAmount(Player player, Item add) {
        return add.getAmount();
    }

    /**
     * Checks if the player can buy the item.
     *
     * @param player   the player.
     * @param currency the currency.
     * @return {@code True} if so.
     */
    public boolean handleBuy(Player player, Item currency) {
        return pointShop || player.getInventory().remove(currency);
    }

    /**
     * Checks if the player can buy from the shop.
     *
     * @param player   the player.
     * @param item     the item.
     * @param price    the price.
     * @param currency the currency.
     * @return {@code True} if they can buy.
     */
    public boolean canBuy(Player player, Item item, int price, Item currency) {
        if (!pointShop && !player.getInventory().containsItem(currency)) {
            player.getPacketDispatch().sendMessage("You don't have enough " + ItemDefinition.forId(getCurrency()).getName().toLowerCase() + ".");
            return false;
        }
        if (pointShop && getPoints(player) < price) {
            player.sendMessage("You don't have enough " + getPointsName() + "s.");
            return false;
        }
        return true;
    }

    /**
     * Gets the points.
     *
     * @param player the player.
     * @return the points.
     */
    public int getPoints(Player player) {
        return 0;
    }

    /**
     * Decrements the points.
     *
     * @param player    the player.
     * @param decrement the decrementation.
     */
    public void decrementPoints(Player player, int decrement) {

    }

    /**
     * Gets the points name.
     *
     * @return the name.
     */
    public String getPointsName() {
        return "";
    }

    /**
     * Gets the value gained for selling this item to a certain shop.
     *
     * @param item   The item to sell.
     * @param player the player.
     * @return The value.
     */
    public int getSellingValue(Item item, Player player) {
        if (!item.getDefinition().isUnnoted()) {
            item = new Item(item.getNoteChange(), item.getAmount());
        }
        int amount = getContainer(1).getAmount(item);
        if (getCurrency() == TOKKUL) {
            for (Item i : items) {
                if (i.getId() == item.getId()) {
                    amount = i.getAmount();
                }
            }
        }
        if (amount < 1) {
            amount = getContainer(0).getAmount(item);
        }
        int value = getSellValue(player, amount, item);
        if (getCurrency() == TOKKUL) {
            int tokkul = item.getDefinition().getConfiguration("tokkul_price", -1);
            if (tokkul > 0) {
                value = tokkul /= 10;
            }
        }
        return value;
    }

    /**
     * Gets the selling value formula based.
     *
     * @param amount the amount.
     * @param item   the item.
     * @return the selling value.
     */
    private int getSellValue(Player player, int amount, Item item) {
        double diff = item.getDefinition().isStackable() ? 0.005 : 0.05;
        double maxMod = 1.0 - (amount * diff);
        if (maxMod < 0.25) {
            maxMod = 0.25;
        }
        double minMod = maxMod - ((item.getAmount() - 1) * diff);
        if (minMod < 0.25) {
            minMod = 0.25;
        }
        double mod = (maxMod + minMod) / 2;
        int value = (int) (item.getDefinition().getAlchemyValue(highAlch) * mod * item.getAmount());
        if(item.getId() == 12183){
            value = 25 * item.getAmount();
        }
        return value;
    }

    /**
     * Gets the buying price.
     *
     * @param item the item.
     * @return the price.
     */
    public int getBuyPrice(Item item, Player player) {
        item = new Item(item.getId(), 1);
        int price = item.getDefinition().getMaxValue();
        int sellVal = getSellingValue(item, player);
        if (price < sellVal) {
            price = getSellValue(player, 0, item) + sellVal - (sellVal - item.getDefinition().getMaxValue());
        }
        if (price < 0) {
            price = 1;
        }
        if (getCurrency() == TOKKUL) {
            int tokkul = item.getDefinition().getConfiguration("tokkul_price", -1);
            if (tokkul > 0) {
                price = tokkul;
            }
            if (player.getAchievementDiaryManager().getKaramjaGlove() != -1) {
                price *= 0.86666666667;
            }
        }
        if (getCurrency() == ARCHERY_TICKET) {
        	int tickets = item.getDefinition().getConfiguration(ItemConfigParser.ARCHERY_TICKET_PRICE,-1);
        	if (tickets > 0) {
        		price = tickets;
        	}
        }
        return (getSellAllFor() > 0 ? getSellAllFor() : price);
    }

    /**
     * Checks if the item is allowed to be sold to the shop.
     *
     * @param itemId the item id.
     * @return {@code True} if so.
     */
    public boolean itemAllowed(int itemId) {
        if (general) {
            return true;
        }
        int noteId = ItemDefinition.forId(itemId).getNoteId();
        if (!ItemDefinition.forId(itemId).isUnnoted()) {
            noteId = ItemDefinition.forId(noteId).getNoteId();
        }
        for (Item id : items) {
            if (itemId == id.getId() || (noteId > -1 && noteId == ItemDefinition.forId(id.getId()).getNoteId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the container the item should go to.
     *
     * @param item the item.
     * @return the container.
     */
    public Container getContainer(Item item) {
        int itemId = item.getId();
        int noteId = ItemDefinition.forId(itemId).getNoteId();
        if (!ItemDefinition.forId(itemId).isUnnoted()) {
            noteId = ItemDefinition.forId(noteId).getNoteId();
        }
        for (Item i : items) {
            if (i.getId() == item.getId() || (noteId > -1 && noteId == ItemDefinition.forId(i.getId()).getNoteId())) {
                return getContainer(0);
            }
        }
        return getContainer(1);
    }

    /**
     * Creates a copy of this shop.
     *
     * @return the shop.
     */
    public Shop copy() {
        return new Shop(title, items, general, currency, highAlch);
    }

    /**
     * Gets the container on the slot.
     *
     * @param tabIndex the tab index.
     * @return the container.
     */
    public Container getContainer(int tabIndex) {
        if (tabIndex > containers.length) {
            throw new IndexOutOfBoundsException("Error! Shop tab index out of bounds.");
        }
        return containers[tabIndex];
    }

    /**
     * Gets the viewers.
     *
     * @return The viewers.
     */
    public List<ShopViewer> getViewers() {
        return viewers;
    }

    /**
     * Gets the title.
     *
     * @return The title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the items.
     *
     * @return The items.
     */
    public Item[] getItems() {
        return items;
    }

    /**
     * Sets the items.
     *
     * @return true if the items were changed.
     */
    public boolean setItems(Item... item) {
        return items == item;
    }

    /**
     * Gets the general.
     *
     * @return The general.
     */
    public boolean isGeneral() {
        return general;
    }

    /**
     * Gets the currency.
     *
     * @return The currency.
     */
    public int getCurrency() {
        return currency;
    }

    /**
     * Gets the containers.
     *
     * @return The containers.
     */
    public Container[] getContainers() {
        return containers;
    }

    /**
     * Gets the bhighAlch.
     *
     * @return the highAlch
     */
    public boolean isHighAlch() {
        return highAlch;
    }

    @Override
    public String toString() {
        return "Shop [containers=" + Arrays.toString(containers) + ", viewers=" + viewers + ", title=" + title + ", items=" + Arrays.toString(items) + ", general=" + general + ", currency=" + currency + ",  highAlch=" + highAlch + "]";
    }

    /**
     * Gets the lastRestock.
     *
     * @return the lastRestock
     */
    public int getLastRestock() {
        return lastRestock;
    }

    /**
     * Sets the balastRestock.
     *
     * @param lastRestock the lastRestock to set.
     */
    public void setLastRestock(int lastRestock) {
        this.lastRestock = lastRestock;
    }

    /**
     * Gets the npcs.
     *
     * @return the npcs
     */
    public int[] getNpcs() {
        return npcs;
    }

    /**
     * Sets the banpcs.
     *
     * @param npcs the npcs to set.
     */
    public void setNpcs(int[] npcs) {
        this.npcs = npcs;
    }

    /**
     * Gets the pointShop.
     *
     * @return the pointShop
     */
    public boolean isPointShop() {
        return pointShop;
    }

    /**
     * Sets the pointShop.
     *
     * @param pointShop the pointShop to set.
     */
    public void setPointShop(boolean pointShop) {
        this.pointShop = pointShop;
    }

    /**
     * Check if shop should restock.
     *
     * @return the restock
     */
    public boolean isRestock() {
        return restock;
    }

    /**
     * Sets the restock.
     *
     * @param reStock
     */
    public void setRestock(boolean reStock) {
        restock = reStock;
    }


    /**
     * Gets the SellAllFor value.
     *
     * @return the sellAllFor
     */
    public int getSellAllFor() {
        return sellAllFor;
    }

    public void setItems(ArrayList<Item> items){
        this.items = items.toArray(new Item[0]);
    }

}