package plugin.ge;

import core.game.content.eco.EcoStatus;
import core.game.content.eco.EconomyManagement;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.PlayerDetails;
import core.game.node.entity.player.link.audio.Audio;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.system.task.TaskExecutor;
import core.game.world.GameWorld;
import core.game.world.callback.CallBack;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles the Grand Exchange offers.
 * @author Ceikry
 * @author Emperor
 */
public final class GEOfferDispatch extends Pulse implements CallBack {

	/**
	 * The update notification.
	 */
	public static final String UPDATE_NOTIFICATION = "One or more of your grand exchange offers have been updated.";

	/**
	 * The database path.
	 */
	private static final String DB_PATH = "data" + File.separator + "eco" + File.separator + "offer_dispatch.xml";

	/**
	 * The offset of the offer UIDs.
	 */
	private static long offsetUID = 1;

	/**
	 * The mapping of all current offers.
	 */
	private static final Map<Long, GrandExchangeOffer> OFFER_MAPPING = new HashMap<>();

	/**
	 * If the database should be dumped.
	 */
	private static boolean dumpDatabase;

	/**
	 * Initializes the Grand Exchange.
	 */
	public static void init() {
		File file = new File(DB_PATH);
		if(!file.exists()){
			return;
		}
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
			Document doc = builder.parse(file);

			doc.getDocumentElement().normalize();

			offsetUID = Long.parseLong(doc.getElementsByTagName("offsetUID").item(0).getTextContent());

			NodeList offers = doc.getElementsByTagName("offer");
			for(int i = 0; i < offers.getLength(); i++){
				Node offer = offers.item(i);
				if(offer.getNodeType() == Node.ELEMENT_NODE){
					Element offerElement = (Element) offer;
					long uid = Long.parseLong(offerElement.getElementsByTagName("uid").item(0).getTextContent());
					int itemId = Integer.parseInt(offerElement.getElementsByTagName("itemId").item(0).getTextContent());
					boolean sale = Boolean.parseBoolean(offerElement.getElementsByTagName("sale").item(0).getTextContent());
					int amount = Integer.parseInt(offerElement.getElementsByTagName("amount").item(0).getTextContent());
					int completedAmount = Integer.parseInt(offerElement.getElementsByTagName("completedAmount").item(0).getTextContent());
					int offeredValue = Integer.parseInt(offerElement.getElementsByTagName("offeredValue").item(0).getTextContent());
					long timeStamp = Long.parseLong(offerElement.getElementsByTagName("timeStamp").item(0).getTextContent());
					int offerState = Integer.parseInt(offerElement.getElementsByTagName("offerState").item(0).getTextContent());
					int totalCoinExchange = Integer.parseInt(offerElement.getElementsByTagName("totalCoinExchange").item(0).getTextContent());
					int playerUID = Integer.parseInt(offerElement.getElementsByTagName("playerUID").item(0).getTextContent());

					GrandExchangeOffer o = new GrandExchangeOffer(itemId,sale);
					o.setUid(uid);
					o.setAmount(amount);
					o.setCompletedAmount(completedAmount);
					o.setOfferedValue(offeredValue);
					o.setTimeStamp(timeStamp);
					o.setState(OfferState.values()[offerState]);
					o.setTotalCoinExchange(totalCoinExchange);
					o.setPlayerUID(playerUID);
					NodeList withdrawItems = offerElement.getElementsByTagName("withdrawItem");
					for(int j = 0; j < withdrawItems.getLength(); j++){
						Node item = withdrawItems.item(i);
						if(item.getNodeType() == Node.ELEMENT_NODE){
							Element itemElement = (Element) item;
							Item newItem = new Item(Integer.parseInt(itemElement.getAttribute("id")),Integer.parseInt(itemElement.getAttribute("amount")));
							o.getWithdraw()[i] = newItem;
						}
					}
					OFFER_MAPPING.put(uid,o);
				}
			}

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Dumps the grand exchange offers.
	 * @param directory The directory to save to.
	 */
	public static void dump() {
		File file = new File(DB_PATH);

		try{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbFactory.newDocumentBuilder();
			Document doc = builder.newDocument();

			Element root = doc.createElement("database");
			doc.appendChild(root);

			for(long uid : OFFER_MAPPING.keySet()){
				GrandExchangeOffer o = OFFER_MAPPING.get(uid);
				if(o == null){
					continue;
				}

				Element offer = doc.createElement("offer");
				root.appendChild(offer);

				Element uidElement = doc.createElement("uid");
				uidElement.setTextContent("" + uid);
				offer.appendChild(uidElement);

				Element itemId = doc.createElement("itemId");
				itemId.setTextContent("" + o.getItemId());
				offer.appendChild(itemId);

				Element sale = doc.createElement("sale");
				sale.setTextContent("" + o.isSell());
				offer.appendChild(sale);

				Element amount = doc.createElement("amount");
				amount.setTextContent("" + o.getAmount());
				offer.appendChild(amount);

				Element completedAmount = doc.createElement("completedAmount");
				completedAmount.setTextContent("" + o.getCompletedAmount());
				offer.appendChild(completedAmount);

				Element offeredValue = doc.createElement("offeredValue");
				offeredValue.setTextContent("" + o.getOfferedValue());
				offer.appendChild(offeredValue);

				Element timeStamp = doc.createElement("timeStamp");
				timeStamp.setTextContent("" + o.getTimeStamp());
				offer.appendChild(timeStamp);

				Element offerState = doc.createElement("offerState");
				offerState.setTextContent("" + o.getState().ordinal());
				offer.appendChild(offerState);

				Element totalCoinExchange = doc.createElement("totalCoinExchange");
				totalCoinExchange.setTextContent("" + o.getTotalCoinExchange());
				offer.appendChild(totalCoinExchange);

				Element playerUID = doc.createElement("playerUID");
				playerUID.setTextContent("" + o.getPlayerUID());
				offer.appendChild(playerUID);

				Attr idAttr = doc.createAttribute("id");
				Attr amtAttr = doc.createAttribute("amount");

				for(int i = 0; i < o.getWithdraw().length; i++) {
					Item item = o.getWithdraw()[i];
					if(item == null){
						continue;
					}
					Element withdrawItem = doc.createElement("withdrawItem");
					idAttr.setValue("" + item.getId());
					amtAttr.setValue("" + item.getAmount());
					withdrawItem.setAttributeNode(idAttr);
					withdrawItem.setAttributeNode(amtAttr);
					offer.appendChild(withdrawItem);
				}
			}
			Element offsetUIDElement = doc.createElement("offsetUID");
			offsetUIDElement.setTextContent("" + offsetUID);
			root.appendChild(offsetUIDElement);
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);
			transformer.transform(source,result);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public boolean call() {
		init();
		setDelay(1);
		GameWorld.Pulser.submit(this);
		return true;
	}

	@Override
	public boolean pulse() {
		if ((GameWorld.getTicks() % 24000) == 0) {
			for (GrandExchangeOffer offer : OFFER_MAPPING.values()) {
				if (offer.isActive() && offer.isLimitation()) {
					updateOffer(offer);
				}
			}
			BuyingLimitation.clear();
		}
		return false;
	}

	/**
	 * Dispatches an offer.
	 * @param player The player.
	 * @param offer The grand exchange offer.
	 * @return {@code True} if successful.
	 */
	public static boolean dispatch(Player player, GrandExchangeOffer offer) {
		if (offer.getAmount() < 1) {
			player.getPacketDispatch().sendMessage("You must choose the quantity you wish to buy!");
			System.out.println("amountthing");
			return false;
		}
		if (offer.getOfferedValue() < 1) {
			player.getPacketDispatch().sendMessage("You must choose the price you wish to buy for!");
			System.out.println("pricethng");
			return false;
		}
		if (offer.getState() != OfferState.PENDING || offer.getUid() != 0) {
			System.out.println("pendingthing");
			return false;
		}
		offer.setPlayerUID(player.getDetails().getUid());
		offer.setUid(nextUID());
		offer.setState(OfferState.REGISTERED);
		OFFER_MAPPING.put(offer.getUid(), offer);
		offer.setTimeStamp(System.currentTimeMillis());
		player.getGrandExchange().update(offer);
		dumpDatabase = true;
		return true;
	}

	/**
	 * Updates the offer.
	 * @param offer The G.E. offer to update.
	 */
	public static void updateOffer(GrandExchangeOffer offer) {
		if (!offer.isActive()) {
			return;
		}
		for (GrandExchangeOffer o : OFFER_MAPPING.values()) {
			if (o.isSell() != offer.isSell() && o.getItemId() == offer.getItemId() && o.isActive()) {
				exchange(offer, o);
				if (offer.getState() == OfferState.COMPLETED) {
					break;
				}
			}
		}
		if (offer.getState() != OfferState.COMPLETED) {
			for (GrandExchangeOffer o : GEAutoStock.getStock()) {
				if (o.isSell() != offer.isSell() && o.getItemId() == offer.getItemId() && o.isActive()) {
					exchange(offer, o);
					if (offer.getState() == OfferState.COMPLETED) {
						break;
					}
				}
			}
		}
	}

	/**
	 * Exchanges between 2 offers.
	 * @param offer The grand exchange offer to update.
	 * @param o The other offer to exchange with.
	 */
	private static void exchange(GrandExchangeOffer offer, GrandExchangeOffer o) {
		if (o.isSell() == offer.isSell()) {
			return;
		}
		if ((offer.isSell() && o.getOfferedValue() < offer.getOfferedValue()) || (!offer.isSell() && o.getOfferedValue() > offer.getOfferedValue())) {
			return;
		}
		int amount = offer.getAmountLeft(true);
		if (amount > o.getAmountLeft(true)) {
			amount = o.getAmountLeft(true);
		}
		if (amount < 1) {
			return;
		}
		int coinDifference = offer.isSell() ? (o.getOfferedValue() - offer.getOfferedValue()) : (offer.getOfferedValue() - o.getOfferedValue());
		if (coinDifference < 0) {
			return;
		}
		if (EconomyManagement.getEcoState() == EcoStatus.DRAINING) {
			coinDifference *= (1.0 - EconomyManagement.getModificationRate());
		}
		offer.setCompletedAmount(offer.getCompletedAmount() + amount);
		o.setCompletedAmount(o.getCompletedAmount() + amount);
		offer.setState(offer.getAmountLeft() < 1 ? OfferState.COMPLETED : OfferState.UPDATED);
		o.setState(o.getAmountLeft() < 1 ? OfferState.COMPLETED : OfferState.UPDATED);
		if (offer.isSell()) {
			if (offer.getAmountLeft() < 1 && offer.getPlayer() != null) {
				offer.getPlayer().getAudioManager().send(new Audio(4042, 1, 1));
			}
			offer.addWithdraw(995, amount * offer.getOfferedValue());
			o.addWithdraw(o.getItemId(), amount);
			BuyingLimitation.updateBoughtAmount(o.getItemId(), o.getPlayerUID(), amount);
		} else {
			if (o.getAmountLeft() < 1 && o.getPlayer() != null) {
				o.getPlayer().getAudioManager().send(new Audio(4042, 1, 1));
			}
			offer.addWithdraw(offer.getItemId(), amount);
			o.addWithdraw(995, amount * o.getOfferedValue());
			BuyingLimitation.updateBoughtAmount(offer.getItemId(), offer.getPlayerUID(), amount);
		}
		if (coinDifference > 0) {
			addCoinDifference(offer, o, coinDifference, amount);
		}
		offer.getEntry().influenceValue(offer.getOfferedValue());
		offer.notify(UPDATE_NOTIFICATION);
		o.notify(UPDATE_NOTIFICATION);
		dumpDatabase = true;
	}

	/**
	 * Adds the coin difference between 2 offers.
	 * @param offer The offer.
	 * @param o The other offer.
	 * @param coinDifference The difference in prices.
	 */
	private static void addCoinDifference(GrandExchangeOffer offer, GrandExchangeOffer o, int coinDifference, int amount) {
		if (offer.isSell()) {
			o.addWithdraw(995, coinDifference * amount);
		} else {
			offer.addWithdraw(995, coinDifference * amount);
		}
	}

	/**
	 * Gets the offer for the given UID.
	 * @param uid The unique ID given to the offer.
	 * @return The grand exchange offer.
	 */
	public static GrandExchangeOffer forUID(long uid) {
		return OFFER_MAPPING.get(uid);
	}

	/**
	 * Removes the offer for the given UID.
	 * @param uid The UID.
	 * @return {@code True} if successfully removed.
	 */
	public static boolean remove(long uid) {
		return OFFER_MAPPING.remove(uid) != null;
	}

	/**
	 * Gets the next UID.
	 * @return The UID.
	 */
	static long nextUID() {
		long id = offsetUID++;
		if (id == 0) {
			return nextUID();
		}
		return id;
	}

	/**
	 * Gets the offerMapping.
	 * @return the offerMapping
	 */
	public static Map<Long, GrandExchangeOffer> getOfferMapping() {
		return OFFER_MAPPING;
	}

	/**
	 * Sets the dumping flag.
	 * @param dump The dump to set.
	 */
	public static void setDumpDatabase(boolean dump) {
		GEOfferDispatch.dumpDatabase = dump;
	}
}