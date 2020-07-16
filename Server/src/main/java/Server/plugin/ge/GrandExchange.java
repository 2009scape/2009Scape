package plugin.ge;

import core.cache.def.impl.ItemDefinition;
import core.game.component.CloseEvent;
import core.game.component.Component;
import core.game.component.InterfaceType;
import core.game.container.Container;
import core.game.container.ContainerEvent;
import core.game.container.ContainerListener;
import core.game.container.access.BitregisterAssembler;
import core.game.container.access.InterfaceContainer;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.login.SavingModule;
import core.game.node.entity.player.link.audio.Audio;
import core.game.node.item.Item;
import core.game.system.monitor.PlayerMonitor;
import core.net.packet.PacketRepository;
import core.net.packet.context.GrandExchangeContext;
import core.net.packet.out.GrandExchangePacket;

import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Handles a player's Grand Exchange.
 *
 * @author Emperor
 */
public final class GrandExchange implements SavingModule {

    /**
     * The player.
     */
    private final Player player;

    /**
     * The grand exchange offers.
     */
    private final GrandExchangeOffer[] offers = new GrandExchangeOffer[6];

    /**
     * The offer the player is currently constructing.
     */
    private GrandExchangeOffer temporaryOffer;

    /**
     * The grand exchange offer history.
     */
    private GrandExchangeOffer[] history = new GrandExchangeOffer[5];

    /**
     * The currently opened index.
     */
    private int openedIndex = -1;

    /**
     * Constructs a new {@code GrandExchange} {@code Object}.
     *
     * @param player The player.
     */
    public GrandExchange(Player player) {
        this.player = player;
    }

    /**
     * Opens the Grand Exchange menu.
     *
     * @NOTICE: The amount of GE boxes is limited at LoginWriteEvent.java (2 or 6 GE boxes, set it 0 for 2, 1 for 6)
     */
    public void open() {
        if (player.getIronmanManager().checkRestriction()) {
            return;
        }
        if (!player.getBankPinManager().isUnlocked()) {
            player.getBankPinManager().openType(4);
            return;
        }
        player.getInterfaceManager().open(new Component(105)).setCloseEvent(new CloseEvent() {
            @Override
            public boolean close(Player player, Component c) {
                temporaryOffer = null;
                player.getPacketDispatch().sendRunScript(571, "");
                player.getInterfaceManager().closeChatbox();
                player.getInterfaceManager().closeSingleTab();
                return true;
            }
        });
        player.getPacketDispatch().sendInterfaceConfig(105, 193, true);
        player.getPacketDispatch().sendAccessMask(6, 211, 105, -1, -1);
        player.getPacketDispatch().sendAccessMask(6, 209, 105, -1, -1);
        toMainInterface();
    }

    /**
     * Opens the collection box.
     */
    public void openCollectionBox() {
        if (!player.getBankPinManager().isUnlocked()) {
            player.getBankPinManager().openType(3);
            return;
        }
        player.getInterfaceManager().openComponent(109);
        player.getPacketDispatch().sendAccessMask(6, 18, 109, 0, 2);
        player.getPacketDispatch().sendAccessMask(6, 23, 109, 0, 2);
        player.getPacketDispatch().sendAccessMask(6, 28, 109, 0, 2);
        player.getPacketDispatch().sendAccessMask(6, 36, 109, 0, 2);
        player.getPacketDispatch().sendAccessMask(6, 44, 109, 0, 2);
        player.getPacketDispatch().sendAccessMask(6, 52, 109, 0, 2);
        for (GrandExchangeOffer offer : player.getGrandExchange().getOffers()) {
            if (offer != null) {
                offer.sendItems();
            }
        }
    }

    /**
     * Opens the history log.
     *
     * @param p The player to open it for.
     */
    public void openHistoryLog(Player p) {
        p.getInterfaceManager().open(new Component(643));
        for (int i = 0; i < history.length; i++) {
            GrandExchangeOffer o = history[i];
            if (o == null) {
                for (int j = 0; j < 4; j++) {
                    p.getPacketDispatch().sendString("-", 643, 25 + i + (j * 5));
                }
                continue;
            }
            p.getPacketDispatch().sendString(o.isSell() ? "You sold" : "You bought", 643, 25 + i);
            p.getPacketDispatch().sendString(NumberFormat.getNumberInstance(Locale.US).format(o.getCompletedAmount()), 643, 30 + i);
            p.getPacketDispatch().sendString(ItemDefinition.forId(o.getItemId()).getName(), 643, 35 + i);
            p.getPacketDispatch().sendString(NumberFormat.getNumberInstance(Locale.US).format(o.getTotalCoinExchange()) + " gp", 643, 40 + i);
        }
    }

    /**
     * Opens the item sets interface.
     */
    public void openItemSets() {
        player.getInventory().getListeners().add(new ContainerListener() {

            @Override
            public void update(Container c, ContainerEvent event) {
                player.setAttribute("container-key", InterfaceContainer.generateItems(player, player.getInventory().toArray(), new String[]{"Examine", "Exchange", "Components"}, 644, 0, 7, 4));
                InterfaceContainer.generateItems(player, GEItemSet.getItemArray(), new String[]{"Examine", "Exchange", "Components"}, 645, 16, 15, 10);

            }

            @Override
            public void refresh(Container c) {
                player.setAttribute("container-key", InterfaceContainer.generateItems(player, player.getInventory().toArray(), new String[]{"Examine", "Exchange", "Components"}, 644, 0, 7, 4));
                InterfaceContainer.generateItems(player, GEItemSet.getItemArray(), new String[]{"Examine", "Exchange", "Components"}, 645, 16, 15, 10);
            }

        });
        player.getInterfaceManager().open(new Component(645)).setCloseEvent(new CloseEvent() {
            @Override
            public boolean close(Player player, Component c) {
                player.getInventory().getListeners().remove(1);
                player.getInterfaceManager().closeSingleTab();
                player.removeAttribute("container-key");
                return true;
            }
        });
        player.getInterfaceManager().openSingleTab(new Component(644)).open(player);
        player.setAttribute("container-key", InterfaceContainer.generateItems(player, player.getInventory().toArray(), new String[]{"Examine", "Exchange", "Components"}, 644, 0, 7, 4));
        InterfaceContainer.generateItems(player, GEItemSet.getItemArray(), new String[]{"Examine", "Exchange", "Components"}, 645, 16, 15, 10);
    }

    /**
     * Returns to the main interface.
     */
    @SuppressWarnings("deprecation")
    public void toMainInterface() {
        player.getConfigManager().send(1112, -1);
        player.getConfigManager().send(1113, -1);
        player.getInterfaceManager().closeChatbox();
        player.getInterfaceManager().closeSingleTab();
        openedIndex = -1;
    }

    @Override
    public void save(ByteBuffer buffer) {
        for (GrandExchangeOffer offer : offers) {
            if (offer != null) {
                buffer.put((byte) offer.getIndex());
                buffer.putLong(offer.getUid());
            }
        }
        buffer.put((byte) -1);
        for (GrandExchangeOffer o : history) {
            if (o == null) {
                buffer.put((byte) -1);
                continue;
            }
            buffer.put((byte) (o.isSell() ? 1 : 0));
            buffer.putShort((short) o.getItemId());
            buffer.putInt(o.getTotalCoinExchange());
            buffer.putInt(o.getCompletedAmount());
        }
    }

    @Override
    public void parse(ByteBuffer buffer) {
        int index = -1;
        GrandExchangeOffer o;
        while ((index = buffer.get()) != -1) {
            long key = buffer.getLong();
            o = offers[index] = GEOfferDispatch.forUID(key);
            if (o == null) {
                System.out.println("Could not locate G.E offer for key " +
                        key + "!");
            } else {
                o.setIndex(index);
            }
        }
        for (int i = 0; i < history.length; i++) {
            int s = buffer.get();
            if (s == -1) {
                continue;
            }
            o = history[i] = new GrandExchangeOffer(buffer.getShort(), s == 1);
            o.setTotalCoinExchange(buffer.getInt());
            o.setCompletedAmount(buffer.getInt());
        }
    }

    /**
     * Initializes the grand exchange.
     */
    public void init() {
        boolean updated = false;
        for (GrandExchangeOffer offer : offers) {
            if (offer != null) {
                offer.setPlayer(player);
                if (!updated && (offer.getWithdraw()[0] != null || offer.getWithdraw()[1] != null)) {
                    updated = true;
                }
            }
        }
        update();
        if (updated) {
            player.getPacketDispatch().sendMessage("You have items from the Grand Exchange waiting in your collection box.");
        }
    }

    /**
     * Updates the grand exchange data.
     */
    public void update() {
        for (GrandExchangeOffer offer : offers) {
            update(offer);
        }
    }

    /**
     * Updates a grand exchange offer.
     *
     * @param offer The offer to update.
     */
    public void update(GrandExchangeOffer offer) {
        if (offer != null) {
            PacketRepository.send(GrandExchangePacket.class, new GrandExchangeContext(player, offer));
        }
    }

    /**
     * Constructs a new buy offer.
     *
     * @param itemId The item id.
     */
    public void constructBuy(int itemId) {
        if (openedIndex < 0) {
            return;
        }
        temporaryOffer = new GrandExchangeOffer(itemId, false);
        if (temporaryOffer.getEntry() == null) {
            player.getPacketDispatch().sendMessage("This item has been blacklisted from the Grand Exchange.");
            return;
        }
        temporaryOffer.setPlayer(player);
        temporaryOffer.setDefault();
        sendConfiguration(temporaryOffer, false);
    }

    /**
     * Constructs a new sale offer.
     *
     * @param item The item to sell.
     */
    public void constructSale(Item item) {
        if (openedIndex < 0 || offers[openedIndex] != null) {
            return;
        }
        if (item.getId() == 995) {
            player.getPacketDispatch().sendMessage("You can't offer money!");
            return;
        }
        int id = item.getId();
        if (!item.getDefinition().isUnnoted()) {
            id = item.getNoteChange();
        }
        if (GrandExchangeDatabase.getDatabase().get(id) == null) {
            player.getPacketDispatch().sendMessage("This item can't be sold on the Grand Exchange.");
            return;
        }
        temporaryOffer = new GrandExchangeOffer(id, true);
        temporaryOffer.setPlayer(player);
        temporaryOffer.setDefault();
        temporaryOffer.setAmount(item.getAmount());
        sendConfiguration(temporaryOffer, true);
    }

    /**
     * Gets the total amount of this item in the inventory (including noted
     * version).
     *
     * @param player The player.
     * @param itemId the item id.
     * @return The amount of items + notes in the inventory.
     */
    public static int getInventoryAmount(Player player, int itemId) {
        Item item = new Item(itemId);
        int amount = player.getInventory().getAmount(item);
        if (item.getDefinition().getNoteId() > -1) {
            amount += player.getInventory().getAmount(new Item(item.getDefinition().getNoteId()));
        }
        return amount;
    }

    /**
     * Confirms the current offer.
     */
    public void confirmOffer() {
        if (openedIndex < 0 || temporaryOffer == null) {
            return;
        }
        if (temporaryOffer.getOfferedValue() < 1) {
            player.getAudioManager().send(new Audio(4039, 1, 1));
            player.getPacketDispatch().sendMessage("You can't make an offer for 0 coins.");
            return;
        }
        if (temporaryOffer.getAmount() > (Integer.MAX_VALUE / temporaryOffer.getOfferedValue())) {
            player.getAudioManager().send(new Audio(4039, 1, 1));
            player.getPacketDispatch().sendMessage("You can't " + (temporaryOffer.isSell() ? "sell " : "buy ") + " this much!");
            return;
        }
        temporaryOffer.setIndex(openedIndex);
        if (temporaryOffer.isSell()) {
            int maxAmount = getInventoryAmount(player, temporaryOffer.getItemId());
            if (temporaryOffer.getAmount() > maxAmount) {
                player.getAudioManager().send(new Audio(4039, 1, 1));
                player.getPacketDispatch().sendMessage("You do not have enough of this item in your inventory to cover the");
                player.getPacketDispatch().sendMessage("offer.");
                return;
            }
            Item item;
            int amountLeft = temporaryOffer.getAmount() - player.getInventory().getAmount(new Item(temporaryOffer.getItemId()));
            boolean remove = player.getInventory().remove(item = new Item(temporaryOffer.getItemId(), temporaryOffer.getAmount()));
            int note;
            if (amountLeft > 0) {
                if ((note = item.getNoteChange()) > 0) {
                    player.getInventory().remove(new Item(note, amountLeft));
                } else if (remove) {
                    player.getInventory().add(new Item(temporaryOffer.getItemId(), temporaryOffer.getAmount() - amountLeft));
                    return;
                }
            }
            if (GEOfferDispatch.dispatch(player, temporaryOffer)) {
                offers[openedIndex] = temporaryOffer;
                GEOfferDispatch.updateOffer(temporaryOffer);
            }
        } else {
            int total = temporaryOffer.getAmount() * temporaryOffer.getOfferedValue();
            if (total > player.getInventory().getAmount(new Item(995))) {
                player.getAudioManager().send(new Audio(4039, 1, 1));
                player.getPacketDispatch().sendMessage("You do not have enough coins to cover the offer.");
                return;
            }
            if (GEOfferDispatch.dispatch(player, temporaryOffer) && player.getInventory().remove(new Item(995, total))) {
                offers[openedIndex] = temporaryOffer;
                GEOfferDispatch.updateOffer(temporaryOffer);
            }
        }
        player.getMonitor().log((temporaryOffer.isSell() ? "selling" : "buying") + " offer => item => " + ItemDefinition.forId(temporaryOffer.getItemId()).getName() + " => amount => " + temporaryOffer.getAmount() + " => price => " + temporaryOffer.getOfferedValue(), PlayerMonitor.GRAND_EXCHANGE_LOG);
        toMainInterface();
        player.getAudioManager().send(new Audio(4043, 1, 1));
        temporaryOffer = null;
    }

    /**
     * Aborts an offer.
     *
     * @param index The offer index.
     */
    public void abort(int index) {
        GrandExchangeOffer offer = offers[index];
        player.getPacketDispatch().sendMessage("Abort request acknowledged. Please be aware that your offer may");
        player.getPacketDispatch().sendMessage("have already been completed.");
        if (offer == null || !offer.isActive()) {
            return;
        }
        offer.setState(OfferState.ABORTED);
        if (offer.isSell()) {
            offer.addWithdraw(offer.getItemId(), offer.getAmountLeft(), true);
        } else {
            offer.addWithdraw(995, offer.getAmountLeft() * offer.getOfferedValue(), true);
        }
        player.getGrandExchange().update(offer);
        player.getMonitor().log("aborted offer => item => " + ItemDefinition.forId(offer.getItemId()).getName() + " => amount => " + offer.getAmount() + "", PlayerMonitor.GRAND_EXCHANGE_LOG);

        GEOfferDispatch.setDumpDatabase(true);
    }

    /**
     * Removes an offer.
     *
     * @param index The offer index.
     */
    public boolean remove(int index) {
        GrandExchangeOffer offer;
        if ((offer = offers[index]) == null) {
            return false;
        }
        if (offer.getCompletedAmount() > 0) {
            logHistory(offer);
            player.getMonitor().log("offer removed => item => " + ItemDefinition.forId(offer.getItemId()).getName() + " =>  amount => " + offer.getAmount() + " => amount_left => " + offer.getAmountLeft() + " => completed_amount => " + offer.getCompletedAmount() + "", PlayerMonitor.GRAND_EXCHANGE_LOG);

        }
        offer.setWithdraw(new Item[2]);
        offer.setUid(0);
        offer.setState(OfferState.REMOVED);
        offers[index] = null;
        update(offer);
        toMainInterface();
        return GEOfferDispatch.remove(offer.getUid());
    }

    /**
     * Adds the completed offer to the log.
     *
     * @param offer The completed offer.
     */
    public void logHistory(GrandExchangeOffer offer) {
        GrandExchangeOffer[] newHistory = new GrandExchangeOffer[5];
        newHistory[0] = offer;
        System.arraycopy(history, 0, newHistory, 1, 4);
        history = newHistory;
    }

    /**
     * Views a registered offer.
     *
     * @param index The index.
     */
    public void view(int index) {
        if (offers[index] == null) {
            return;
        }
        this.openedIndex = index;
        sendConfiguration(offers[index], false);
    }

    /**
     * Opens the buying screen.
     *
     * @param index The offer index.
     */
    public void openBuy(int index) {
        this.openedIndex = index;
        sendConfiguration(offers[index], false);
        openSearch();
    }

    /**
     * Opens the selling screen.
     */
    public void openSell(int index) {
        this.openedIndex = index;
        sendConfiguration(offers[index], true);
        player.getInterfaceManager().openSingleTab(new Component(107)).open(player);
        player.getPacketDispatch().sendRunScript(149, "IviiiIsssss", "", "", "", "Examine", "Offer", -1, 0, 7, 4, 93, 7012370);
        BitregisterAssembler.send(player, 107, 18, 0, 27, new BitregisterAssembler(0, 1));
    }

    /**
     * Withdraws an item.
     *
     * @param offer The offer to withdraw from.
     * @param index The item index.
     */
    public void withdraw(GrandExchangeOffer offer, int index) {
        Item item = offer.getWithdraw()[index];
        if (item == null) {
            return;
        }
        if (player.getInventory().getMaximumAdd(item) < item.getAmount()) {
            int note = item.getNoteChange();
            if (note == -1 || player.getInventory().getMaximumAdd(new Item(note)) < item.getAmount()) {
                player.getAudioManager().send(new Audio(4039, 1, 1));
                player.getPacketDispatch().sendMessage("You do not have enough room in your inventory.");
                return;
            }
            player.getInventory().add(new Item(note, item.getAmount()));
        } else {
            player.getInventory().add(item);
        }
        offer.getWithdraw()[index] = null;
        if (!offer.isActive() && offer.getWithdraw()[0] == null && offer.getWithdraw()[1] == null) {
            player.getGrandExchange().remove(offer.getIndex());
        }
        player.getAudioManager().send(new Audio(4040, 1, 1));
        offer.sendItems();
        GEOfferDispatch.setDumpDatabase(true);
    }

    /**
     * Opens the search interface.
     */
    public void openSearch() {
        Component c = new Component(389);
        c.getDefinition().setType(InterfaceType.CS_CHATBOX);
        c.setCloseEvent(new CloseEvent() {
            @Override
            public boolean close(Player player, Component c) {
                player.getPacketDispatch().sendRunScript(571, "");
                return true;
            }
        });
        player.getPacketDispatch().sendRunScript(570, "s", "Grand Exchange Item Search");
        player.getInterfaceManager().openChatbox(c);
    }

    /**
     * Sends the configuration packets for the offer.
     *
     * @param offer The grand exchange offer.
     * @param sell  If it's a selling offer.
     */
    @SuppressWarnings("deprecation")
    public void sendConfiguration(GrandExchangeOffer offer, boolean sell) {
        boolean construct = offer == null;
        GrandExchangeEntry entry = null;
        String examine = "";
        DecimalFormat formatter = new DecimalFormat("###,###,###,###");
        StringBuilder text = new StringBuilder();
        int lowestOfferValue = 0;
        int totalAmounts = 0;
        List<Integer> foundOffers = new ArrayList<>();
        List<Integer> foundAmounts = new ArrayList<>();
        if (!construct) {
            entry = offer.getEntry();
            examine = ItemDefinition.forId(offer.getItemId()).getExamine();
            int count = 0;
            if (!offer.isSell()) {
                for (GrandExchangeOffer o : GEOfferDispatch.getOfferMapping().values()) {
                    if (o != null)
                        if (o.isSell() != offer.isSell() && o.getItemId() == offer.getItemId() && o.isActive()) {
                            foundOffers.add(o.getOfferedValue());
                            foundAmounts.add(o.getAmountLeft());
                            count++;
                            System.out.println(o.toString());
                        }
                }

                for (GrandExchangeOffer o : GEAutoStock.getStock()) {
                    if (o != null)
                        if (o.isSell() != offer.isSell() && o.getItemId() == offer.getItemId() && o.isActive()) {
                            foundOffers.add(o.getOfferedValue());
                            foundAmounts.add(o.getAmountLeft());
                            count++;
                        }
                }

                if (!foundOffers.isEmpty()) {
                    lowestOfferValue = Collections.min(foundOffers);
                    totalAmounts = foundAmounts.stream().mapToInt(Integer::intValue).sum();
                }
                if (count == 0)
                    text.append("<col=8A0808>There are currently no sell offers for this item.");
                if (count >= 1)
                    text.append("Lowest price: <col=FFFFFF>").append(formatter.format(lowestOfferValue)).append(" gp").append("</col><br>Quantity: <col=FFFFFF>")
                            .append(formatter.format(totalAmounts)).append("</col><br>Users: <col=FFFFFF>").append(formatter.format(count));
            }
        }


        player.getPacketDispatch().sendString(offer != null && !offer.isSell() ? text.toString() : examine, 105, 142);
        player.getConfigManager().send(1114, entry == null ? 0 : entry.getValue());
        player.getConfigManager().send(1115, (int) (entry == null ? 0 : entry.getValue() * 0.95));
        player.getConfigManager().send(1116, (int) (entry == null ? 0 : entry.getValue() * 1.05));
        player.getConfigManager().send(1112, openedIndex);
        player.getConfigManager().send(1113, sell ? 1 : 0);
        player.getConfigManager().send(1109, construct ? -1 : offer.getItemId());
        player.getConfigManager().send(1110, construct ? 0 : offer.getAmount());
        player.getConfigManager().send(1111, construct ? 0 : offer.getOfferedValue());
        if (!construct) {
            offer.sendItems();
        }
    }

    /**
     * Formats the grand exchange.
     *
     * @return the formatted offer for the SQL database.
     */
    public String format() {
        String log = "";
        for (GrandExchangeOffer offer : offers) {
            if (offer != null) {
                log += offer.getItemId() + "," + offer.getAmount() + "," + offer.isSell() + "|";
            }
        }
        if (log.length() > 0 && log.charAt(log.length() - 1) == '|') {
            log = log.substring(0, log.length() - 1);
        }
        return log;
    }

    /**
     * Gets the currently opened offer.
     *
     * @return The grand exchange offer currently opened.
     */
    public GrandExchangeOffer getOpenedOffer() {
        if (openedIndex < 0) {
            return null;
        }
        return offers[openedIndex];
    }

    /**
     * Checks if the player has an active offer.
     *
     * @return {@code True} if so.
     */
    public boolean hasActiveOffer() {
        for (GrandExchangeOffer offer : offers) {
            if (offer != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the offers.
     *
     * @return The offers.
     */
    public GrandExchangeOffer[] getOffers() {
        return offers;
    }

    public static int getLowestValue(int[] numbers){
        int minValue = numbers[0];
        for(int i=1;i<numbers.length;i++){
            if(numbers[i] < minValue){
                minValue = numbers[i];
            }
        }
        return minValue;
    }

    /**
     * Gets the openedIndex.
     *
     * @return The openedIndex.
     */
    public int getOpenedIndex() {
        return openedIndex;
    }

    /**
     * Sets the openedIndex.
     *
     * @param openedIndex The openedIndex to set.
     */
    public void setOpenedIndex(int openedIndex) {
        this.openedIndex = openedIndex;
    }

    /**
     * Gets the temporaryOffer.
     *
     * @return The temporaryOffer.
     */
    public GrandExchangeOffer getTemporaryOffer() {
        return temporaryOffer;
    }

    /**
     * Sets the temporaryOffer.
     *
     * @param temporaryOffer The temporaryOffer to set.
     */
    public void setTemporaryOffer(GrandExchangeOffer temporaryOffer) {
        this.temporaryOffer = temporaryOffer;
    }

    /**
     * Gets the history.
     *
     * @return The history.
     */
    public GrandExchangeOffer[] getHistory() {
        return history;
    }

}