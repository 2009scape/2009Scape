package core.game.container;

import core.cache.def.impl.ItemDefinition;
import core.game.node.entity.player.Player;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents a container which contains items.
 *
 * @author Emperor
 */
public class Container {

    /**
     * The item array. A crystalline
     */
    private Item[] items;

    /**
     * The capacity.
     */
    private final int capacity;

    /**
     * The current sort type.
     */
    private SortType sortType;

    /**
     * The current container type.
     */
    private final ContainerType type;

    /**
     * The current container event.
     */
    private ContainerEvent event;

    /**
     * The container listeners.
     */
    private final List<ContainerListener> listeners = new ArrayList<>();

    /**
     * Constructs a new {@code Container} {@code Object}.
     *
     * @param capacity The capacity.
     */
    public Container(int capacity) {
        this(capacity, ContainerType.DEFAULT);
    }

    /**
     * Constructs a new {@code Container.java} {@code Object}.
     *
     * @param capacity the capacity.
     * @param items    the items to add.
     */
    public Container(int capacity, Item... items) {
        this(capacity);
        add(items);
    }

    /**
     * Constructs a new {@code Container} {@code Object}.
     *
     * @param capacity The capacity.
     * @param type     The container type.
     */
    public Container(int capacity, ContainerType type) {
        this(capacity, type, SortType.ID);
    }

    /**
     * Constructs a new {@code Container} {@code Object}.
     *
     * @param capacity The capacity.
     * @param type     The container type.
     * @param sortType The sort type.
     */
    public Container(int capacity, ContainerType type, SortType sortType) {
        this.capacity = capacity;
        this.type = type;
        this.items = new Item[capacity];
        this.sortType = sortType;
        this.event = new ContainerEvent(capacity);
    }

    /**
     * Registers a container listener.
     *
     * @param listener The container listener.
     * @return This container instance, for chaining.
     */
    public Container register(ContainerListener listener) {
        listeners.add(listener);
        return this;
    }

    /**
     * Adds the items.
     *
     * @param items The items to add.
     * @return {@code True} if successfully added <b>all</b> items.
     */
    public boolean add(Item... items) {
        boolean addedAll = true;
        for (Item item : items) {
            if (item == null) {
                continue;
            }
            if (!add(item, false)) {
                addedAll = false;
                break;
            }
        }
        update();
        return addedAll;
    }

    public void addList(List<Item> items){
        items.stream().filter(Objects::nonNull).forEach(this::add);
        update();
    }

    /**
     * Inserts an item into a specific slot.
     *
     * @param fromSlot The original slot of the item.
     * @param toSlot   The slot to insert into.
     */
    public void insert(int fromSlot, int toSlot) {
        insert(fromSlot, toSlot, true);
    }

    /**
     * Inserts an item into a specific slot.
     *
     * @param fromSlot The original slot of the item.
     * @param toSlot   The slot to insert into.
     * @param update   If the container packets should be sent.
     */
    public void insert(int fromSlot, int toSlot, boolean update) {
        Item temp = items[fromSlot];
        if (toSlot > fromSlot) {
            for (int i = fromSlot; i < toSlot; i++) {
                replace(get(i + 1), i, false);
            }
        } else if (fromSlot > toSlot) {
            for (int i = fromSlot; i > toSlot; i--) {
                replace(get(i - 1), i, false);
            }
        }
        replace(temp, toSlot, update);
    }

    /**
     * Adds an item to this container. If full, it goes on the ground.
     *
     * @param item   the item.
     * @param player the player.
     * @return {@code True} if added.
     */
    public boolean add(final Item item, final Player player) {
        if (!add(item, true, -1)) {
            GroundItemManager.create(item, player);
            return false;
        }
        return true;
    }

    public boolean addIfDoesntHave(final Item item) {
        if (containsItem(item)) {
            return false;
        } else {
            return add(item);

        }
    }

    /**
     * Adds an item to this container.
     *
     * @param item The item.
     * @return {@code True} if the item got added.
     */
    public boolean add(Item item) {
        return add(item, true, -1);
    }

    /**
     * Adds an item to this container.
     *
     * @param item          The item to add.
     * @param fireListener  If we should update.
     * @param preferredSlot The slot to add the item in, when possible.
     * @return {@code True} if the item got added.
     */
    public boolean add(Item item, boolean fireListener) {
        return add(item, fireListener, -1);
    }

    /**
     * Adds an item to this container.
     *
     * @param item          The item to add.
     * @param fireListener  If we should update.
     * @param preferredSlot The slot to add the item in, when possible.
     * @return {@code True} if the item got added.
     */
    public boolean add(Item item, boolean fireListener, int preferredSlot) {
        item = item.copy();
        int maximum = getMaximumAdd(item);
        if (maximum == 0) {
            return false;
        }
//		if (preferredSlot > -1 && items[preferredSlot] != null) {
//			preferredSlot = -1;
//		}
        if (item.getAmount() > maximum) {
            item.setAmount(maximum);
        }
        if (type != ContainerType.NEVER_STACK && (item.getDefinition().isStackable() || type == ContainerType.ALWAYS_STACK || type == ContainerType.SHOP)) {
            boolean hashBased = sortType == SortType.HASH;
            for (int i = 0; i < items.length; i++) {
                if (items[i] != null) {
                    if ((hashBased && items[i].getIdHash() == item.getIdHash()) || (!hashBased && items[i].getId() == item.getId())) {
                        int totalCount = item.getAmount() + items[i].getAmount();
                        items[i] = new Item(items[i].getId(), totalCount, item.getCharge());
                        items[i].setIndex(i);
                        event.flag(i, items[i]);
                        if (fireListener) {
                            update();
                        }
                        return true;
                    }
                }
            }
            int slot = preferredSlot > -1 ? preferredSlot : freeSlot();
            if (slot == -1) {
                return false;
            }
            items[slot] = item;
            item.setIndex(slot);
            event.flag(slot, item);
            if (fireListener) {
                update();
            }
            return true;
        }
        int slots = freeSlots();
        if (slots >= item.getAmount()) {
            for (int i = 0; i < item.getAmount(); i++) {
                int slot = i == 0 && preferredSlot > -1 ? preferredSlot : freeSlot();
                items[slot] = new Item(item.getId(), 1, item.getCharge());
                items[slot].setIndex(slot);
                event.flag(slot, items[slot]);
            }
            if (fireListener) {
                update();
            }
            return true;
        }
        return false;
    }

    /**
     * Removes a set of items.
     *
     * @param items The set of items.
     * @return {@code True} if all items got successfully removed.
     */
    public boolean remove(Item... items) {
        boolean removedAll = true;
        for (Item item : items) {
            if (!remove(item, false)) {
                removedAll = false;
            }
        }
        update();
        return removedAll;
    }

    /**
     * Removes an item.
     *
     * @param item The item.
     * @return {@code True} if the item got removed, {@code false} if not.
     */
    public boolean remove(Item item) {
        return remove(item, true);
    }

    /**
     * Removes an item.
     *
     * @param item         The item to remove.
     * @param fireListener If the fire listener should be "notified".
     * @return {@code True} if the item got removed, <br> {@code false} if not.
     */
    public boolean remove(Item item, boolean fireListener) {
        int slot = getSlot(item);
        if (slot != -1) {
            return remove(item, slot, fireListener);
        }
        return false;
    }

    /**
     * Removes an item from this container.
     *
     * @param item         The item.
     * @param slot         The item slot.
     * @param fireListener If the fire listener should be "notified".
     * @return {@code True} if the item got removed, <br> {@code false} if the
     * item on the slot was null or the ids didn't match.
     */
    public boolean remove(Item item, int slot, boolean fireListener) {
        Item oldItem = items[slot];
        if (oldItem == null || oldItem.getId() != item.getId()) {
            return false;
        }
        if (item.getAmount() < 1) {
            return true;
        }
        if (oldItem.getDefinition().isStackable() || type.equals(ContainerType.ALWAYS_STACK) || type == ContainerType.SHOP) {
            if (item.getAmount() >= oldItem.getAmount()) {
                items[slot] = null;
                event.flagNull(slot);
                if (fireListener) {
                    update();
                }
                return true;
            }
            items[slot] = new Item(item.getId(), oldItem.getAmount() - item.getAmount(), item.getCharge());
            items[slot].setIndex(slot);
            event.flag(slot, items[slot]);
            if (fireListener) {
                update();
            }
            return true;
        }
        items[slot] = null;
        event.flagNull(slot);
        int removed = 1;
        for (int i = removed; i < item.getAmount(); i++) {
            slot = getSlot(item);
            if (slot != -1) {
                items[slot] = null;
                event.flagNull(slot);
            } else {
                break;
            }
        }
        if (fireListener) {
            update();
        }
        return true;
    }

    /**
     * Replaces the item on the given slot with the argued item.
     *
     * @param item The item.
     * @param slot The slot.
     * @return The old item.
     */
    public Item replace(Item item, int slot) {
        return replace(item, slot, true);
    }

    /**
     * Replaces the item on the given slot with the argued item.
     *
     * @param item         The item.
     * @param slot         The slot.
     * @param fireListener If the listener should be "notified".
     * @return The old item.
     */
    public Item replace(Item item, int slot, boolean fireListener) {
        if (item != null) {
            if (item.getAmount() < 1 && type != ContainerType.SHOP) {
                item = null;
            } else {
                item = item.copy();
            }
        }
        Item oldItem = items[slot];
        items[slot] = item;
        if (item == null) {
            event.flagNull(slot);
        } else {
            item.setIndex(slot);
            event.flag(slot, item);
        }
        if (fireListener) {
            update();
        }
        return oldItem;
    }

    /**
     * Updates the container.
     */
    public void update() {
        if (event.getChangeCount() < 1 && !event.isClear()) {
            return;
        }
        for (ContainerListener listener : listeners) {
            listener.update(this, event);
        }
        event.setClear(false);
        event = new ContainerEvent(capacity);
    }

    /**
     * Updates the container.
     */
    public void update(boolean force) {
        if (event.getChangeCount() < 1 && !force) {
            return;
        }
        for (ContainerListener listener : listeners) {
            listener.update(this, event);
        }
        event = new ContainerEvent(capacity);
    }

    /**
     * Refreshes the entire container.
     */
    public void refresh() {
        for (ContainerListener listener : listeners) {
            listener.refresh(this);
        }
        event = new ContainerEvent(capacity);
    }
    
    public void refresh(ContainerListener listener) {
    	listener.refresh(this);
    	event = new ContainerEvent(capacity);
    }

    /**
     * Gets the item on the given slot.
     *
     * @param slot The slot.
     * @return The id of the item on the slot, or 0 if the item wasn't there.
     */
    public int getAsId(int slot) {
        if (slot < 0 || slot >= items.length || items[slot] == null) {
            return 0;
        }
        return items[slot].getId();
    }


    /**
     * Gets the item on the given slot.
     *
     * @param slot The slot.
     * @return The item on the slot, or {@code null} if the item wasn't there.
     */
    public Item get(int slot) {
        if (slot < 0 || slot >= items.length) {
            return null;
        }
        return items[slot];
    }

    /**
     * Gets the item on the given slot.
     *
     * @param slot The slot.
     * @return The item on the slot, or a new constructed item with id 0 if the
     * item wasn't there.
     */
    public Item getNew(int slot) {
        Item item = items[slot];
        if (item != null) {
            return item;
        }
        return new Item(0);
    }

    /**
     * Gets the item id on the given slot.
     *
     * @param slot The slot.
     * @return The id of the item on the slot.
     */
    public int getId(int slot) {
        if (slot >= items.length) {
            return -1;
        }
        Item item = items[slot];
        if (item != null) {
            return item.getId();
        }
        return -1;
    }

    /**
     * Parses the container data from the byte buffer.
     *
     * @param buffer The byte buffer.
     * @return The total value of all items (G.E price > Store price > High
     * alchemy price).
     */
    public int parse(ByteBuffer buffer) {
        int slot;
        int total = 0;
        while ((slot = buffer.getShort()) != -1) {
            int id = buffer.getShort() & 0xFFFF;
            int amount = buffer.getInt();
            int charge = buffer.getInt();
            if (id >= ItemDefinition.getDefinitions().size() || id < 0 || slot >= items.length || slot < 0) {
                continue;
            }
            Item item = items[slot] = new Item(id, amount, charge);
            item.setIndex(slot);
            total += item.getValue();
        }
        return total;
    }

    public void parse(JSONArray itemArray){
        AtomicInteger total = new AtomicInteger(0);
        itemArray.forEach(item -> {
            JSONObject i = (JSONObject) item;
            int slot = Integer.parseInt(i.get("slot").toString());
            int id = Integer.parseInt(i.get("id").toString());
            int amount = Integer.parseInt(i.get("amount").toString());
            int charge = Integer.parseInt(i.get("charge").toString());
            if (id >= ItemDefinition.getDefinitions().size() || id < 0 || slot >= items.length) {
            } else {
                Item it = items[slot] = new Item(id,amount,charge);
                it.setIndex(slot);
                total.set(total.get() + (int)it.getValue());
            }
        });
    }

    /**
     * Saves the item data on the byte buffer.
     *
     * @param buffer The byte buffer.
     * @return The total value of all items (G.E price > Store price > High
     * alchemy price).
     */
    public long save(ByteBuffer buffer) {
        long totalValue = 0;
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            if (item == null) {
                continue;
            }
            buffer.putShort((short) i);
            buffer.putShort((short) item.getId());
            buffer.putInt(item.getAmount());
            buffer.putInt(item.getCharge());
            totalValue += item.getValue();
        }
        buffer.putShort((short) -1);
        return totalValue;
    }

    /**
     * Copies the container to this container.
     *
     * @param c The container to copy.
     */
    public void copy(Container c) {
        items = new Item[c.items.length];
        for (int i = 0; i < items.length; i++) {
            Item it = c.items[i];
            if (it == null) {
                continue;
            }
            items[i] = new Item(it.getId(), it.getAmount(), it.getCharge());
            items[i].setIndex(i);
        }
    }

    /**
     * Formats a container for the SQL database.
     *
     * @return the string.
     */
    public String format() {
        String log = "";
        Map<Integer, Integer> map = new HashMap<>();
        Integer old = null;
        for (Item item : items) {
            if (item != null) {
                old = map.get(item.getId());
                map.put(item.getId(), old == null ? item.getAmount() : old + item.getAmount());

            }
        }
        for (int i : map.keySet()) {
            log += i + "," + map.get(i) + "|";
        }
        if (log.length() > 0 && log.charAt(log.length() - 1) == '|') {
            log = log.substring(0, log.length() - 1);
        }
        return log;
    }

    /**
     * Checks if the container contains an item.
     *
     * @param item the Item
     * @return {@code True} if so.
     */
    public boolean containsItem(Item item) {
        return contains(item.getId(), item.getAmount());
    }

    /**
     * Checks if the containers contains these items.
     *
     * @param items the items.
     * @return {@code True} if so.
     */
    public boolean containsItems(Item... items) {
        for (Item i : items) {
            if (!containsItem(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the container contains an item.
     *
     * @param itemId The item id.
     * @param amount The amount.
     * @return {@code True} if so.
     */
    public boolean contains(int itemId, int amount) {
        int count = 0;
        for (Item item : items) {
            if (item != null && item.getId() == itemId) {
                if ((count += item.getAmount()) >= amount) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the containers contains ONE item.
     *
     * @param itemId
     * @return
     */
    public boolean containsOneItem(int itemId) {
        for (Item item : items) {
            if (item != null && item.getId() == itemId && item.getAmount() == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the container contains all items.
     *
     * @param the itemIds to check
     * @return {@code True} if so.
     */
    public boolean containsAll(int... itemIds) {
        for (int i : itemIds) {
            if (!containsOneItem(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds a container to this container.
     *
     * @param container The container.
     */
    public void addAll(Container container) {
        add(container.items);
    }

    /**
     * Checks the maximum amount of this item we can add.
     *
     * @param item The item.
     * @return The maximum amount we can add.
     */
    public int getMaximumAdd(Item item) {
        if (type != ContainerType.NEVER_STACK) {
            if (item.getDefinition().isStackable() || type == ContainerType.ALWAYS_STACK || type == ContainerType.SHOP) {
                if (contains(item.getId(), 1)) {
                    return Integer.MAX_VALUE - getAmount(item);
                }
                return freeSlots() > 0 ? Integer.MAX_VALUE : 0;
            }
        }
        return freeSlots();
    }

    /**
     * Checks if the container has space for the item.
     *
     * @param item The item to check.
     * @return {@code True} if so.
     */
    public boolean hasSpaceFor(Item item) {
        return item.getAmount() <= getMaximumAdd(item);
    }

    /**
     * Checks if this container has space to add the other container.
     *
     * @param c The other container.
     * @return {@code True} if so.
     */
    public boolean hasSpaceFor(Container c) {
        if (c == null) {
            return false;
        }
        Container check = new Container(capacity, type);
        check.addAll(this);
        for (Item item : c.items) {
            if (item != null) {
                if (!check.add(item, false)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Gets the item slot.
     *
     * @param item The item.
     * @return The slot of the item in this container.
     */
    public int getSlot(Item item) {
        if (item == null) {
            return -1;
        }
        int id = item.getId();
        for (int i = 0; i < items.length; i++) {
            Item it = items[i];
            if (it != null && it.getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets the item instance.
     *
     * @param item the item.
     * @return the item.
     */
    public Item getItem(Item item) {
        return get(getSlot(item));
    }

    /**
     * Gets the next free slot.
     *
     * @return The slot, or <code>-1</code> if there are no available slots.
     */
    public int freeSlot() {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets the slot of where to add the item.
     *
     * @param item The item to add.
     * @return The slot where the item will go.
     */
    public int getAddSlot(Item item) {
        if (type != ContainerType.NEVER_STACK && (item.getDefinition().isStackable() || type.equals(ContainerType.ALWAYS_STACK) || type == ContainerType.SHOP)) {
            boolean hashBased = sortType == SortType.HASH;
            for (int i = 0; i < items.length; i++) {
                if (items[i] != null) {
                    if ((hashBased && items[i].getIdHash() == item.getIdHash()) || (!hashBased && items[i].getId() == item.getId())) {
                        return i;
                    }
                }
            }
        }
        return freeSlot();
    }

    /**
     * Gets the number of free slots.
     *
     * @return The number of free slots.
     */
    public int freeSlots() {
        return capacity - itemCount();
    }

    /**
     * Gets the size of this container.
     *
     * @return The size of this container.
     */
    public int itemCount() {
        int size = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                size++;
            }
        }
        return size;
    }

    /**
     * Checks if the player has all the item ids in the inventory.
     *
     * @param itemIds The item ids.
     * @return {@code True} if so.
     */
    public boolean containItems(int... itemIds) {
        for (int i = 0; i < itemIds.length; i++) {
            if (!contains(itemIds[i], 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the amount of an item.
     *
     * @param item The item.
     * @return The amount of this item in this container.
     */
    public int getAmount(Item item) {
        if (item == null) {
            return 0;
        }
        int count = 0;
        for (Item i : items) {
            if (i != null && i.getId() == item.getId()) {
                count += i.getAmount();
            }
        }
        return count;
    }

    /**
     * Gets the amount.
     *
     * @param id the id.
     * @return the amount.
     */
    public int getAmount(int id) {
        return getAmount(new Item(id));
    }

    /**
     * Shifts the elements in the <b>Container</b> to the appropriate position.
     */
    public void shift() {
        final Item itemss[] = items;
        clear(false);
        for (Item item : itemss) {
            if (item == null) {
                continue;
            }
            add(item, false);
        }
        refresh();
    }

    /**
     * Checks if the container is empty.
     *
     * @return {@code True} if so.
     */
    public boolean isEmpty() {
        for (Item item : items) {
            if (item != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the container is full.
     *
     * @return {@code True} if so.
     */
    public boolean isFull() {
        return freeSlots() < 1;
    }

    /**
     * Clears and updates the container.
     */
    public void clear() {
        clear(true);
    }

    /**
     * Clears the container.
     *
     * @param update If the container should be updated.
     */
    public void clear(boolean update) {
        items = new Item[capacity];
        event.flagEmpty();
        if (update) {
            refresh();
        }
    }

    /**
     * Gets the wealth.
     *
     * @return the wealth.
     */
    public int getWealth() {
        int wealth = 0;
        for (Item i : items) {
            if (i == null) {
                continue;
            }
            wealth += i.getDefinition().getValue() * i.getAmount();
        }
        return wealth;
    }

    /**
     * Returns an array representing this container.
     *
     * @return The array.
     */
    public Item[] toArray() {
        return items;
    }

    /**
     * Gets the listeners.
     *
     * @return The listeners.
     */
    public List<ContainerListener> getListeners() {
        return listeners;
    }

    /**
     * Gets the capacity.
     *
     * @return The capacity of this container.
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Gets the event.
     *
     * @return the event.
     */
    public ContainerEvent getEvent() {
        return event;
    }

}