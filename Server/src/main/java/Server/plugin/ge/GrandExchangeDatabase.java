package plugin.ge;

import core.cache.def.impl.ItemDefinition;
import core.game.node.item.Item;
import core.game.system.SystemLogger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the grand exchange database.
 * @author Ceikry
 */
public final class GrandExchangeDatabase {

	/**
	 * The grand exchange database mapping.
	 */
	private static final Map<Integer, GrandExchangeEntry> DATABASE = new HashMap<>();

	/**
	 * The minimum amount of unique trades required for an entry to change its
	 * value.
	 */
	private static final int MINIMUM_TRADES = 10;// 200

	/**
	 * The amount of hours between each update cycle.
	 */
	private static final int UPDATE_CYCLE_HOURS = 3;

	/**
	 * The next update.
	 */
	private static long nextUpdate;

	/**
	 * If the G.E database has initialized.
	 */
	private static boolean initialized;

	static String path = "data" + File.separator + "eco" + File.separator;


	/**
	 * Initializes the database
	 */
	public static void init() {
		try {
			File db = new File(path + "gedb.xml");
			if(!db.exists()){
				initNewDB();
				return;
			}
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
			Document doc = builder.parse(db);

			doc.getDocumentElement().normalize();
			NodeList offers = doc.getElementsByTagName("offer");
			for(int i = 0; i < offers.getLength(); i++){
				Node offer = offers.item(i);
				if(offer.getNodeType() == Node.ELEMENT_NODE){
					Element offerElement = (Element) offer;
					int itemId = Integer.parseInt(offerElement.getElementsByTagName("id").item(0).getTextContent());
					int value = Integer.parseInt(offerElement.getElementsByTagName("value").item(0).getTextContent());
					if(value < 1){
						value = 1;
					}
					int uniqueTrades = Integer.parseInt(offerElement.getElementsByTagName("uniqueTrades").item(0).getTextContent());
					long totalValue = Integer.parseInt(offerElement.getElementsByTagName("totalValue").item(0).getTextContent());
					long lastUpdate = Integer.parseInt(offerElement.getElementsByTagName("lastUpdate").item(0).getTextContent());


					GrandExchangeEntry e = new GrandExchangeEntry(itemId);
					e.setValue(value);
					e.setUniqueTrades(uniqueTrades);
					e.setTotalValue(totalValue);
					e.setLastUpdate(lastUpdate);
					NodeList valueLog = offerElement.getElementsByTagName("valueLog");
					e.setLogLength(valueLog.getLength());
					for(int logEntry = 0; logEntry < valueLog.getLength(); logEntry++){
						int val = Integer.parseInt(valueLog.item(logEntry).getTextContent());
						e.getValueLog()[logEntry] = val;
					}
					DATABASE.put(itemId,e);
				}
			}
			initialized = true;
		} catch (Exception e){
			initNewDB();
		}
	}

	public static void initNewDB(){
		SystemLogger.log("Initializing new Grand Exchange DB! This may take a moment...");
		ItemDefinition.getDefinitions().values().forEach(def -> {
			GrandExchangeEntry e = new GrandExchangeEntry(def.getId());
			e.setValue(def.getValue());
			e.setLogLength(0);
			DATABASE.put(def.getId(),e);
		});
		initialized = true;
	}


		/**
         * Updates the entry values, if needed.
         */
	public static void checkUpdate() {
		if (nextUpdate < System.currentTimeMillis()) {
			updateValues();
		}
	}

	/**
	 * Dumps the grand exchange database.
	 * @param directory The directory to save to.
	 */
	public static void save(String directory) {
		File f = new File(path + "gedb.xml");
		if(f.exists()){
			f.delete();
		}
		try{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbFactory.newDocumentBuilder();
			Document doc = builder.newDocument();

			Element root = doc.createElement("database");
			doc.appendChild(root);

			for(GrandExchangeEntry e : DATABASE.values()) {
				Element offer = doc.createElement("offer");
				root.appendChild(offer);

				Element itemId = doc.createElement("id");
				itemId.setTextContent("" + e.getItemId());
				offer.appendChild(itemId);

				Element value = doc.createElement("value");
				value.setTextContent("" + e.getValue());
				offer.appendChild(value);

				Element uniqueTrades = doc.createElement("uniqueTrades");
				uniqueTrades.setTextContent("" + e.getUniqueTrades());
				offer.appendChild(uniqueTrades);

				Element totalValue = doc.createElement("totalValue");
				totalValue.setTextContent("" + e.getTotalValue());
				offer.appendChild(totalValue);

				Element lastUpdate = doc.createElement("lastUpdate");
				lastUpdate.setTextContent("" + e.getLastUpdate());
				offer.appendChild(lastUpdate);

				int[] vLog = e.getValueLog();
				for (int item : vLog) {
					if (item == 0) {
						continue;
					}
					Element valueLog = doc.createElement("valueLog");
					valueLog.setTextContent("" + item);
					offer.appendChild(valueLog);
				}
			}

			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(f);
			transformer.transform(source,result);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Updates the item values.
	 */
	public static void updateValues() {
		try {
			for (GrandExchangeEntry entry : DATABASE.values()) {
				if (entry.getUniqueTrades() < MINIMUM_TRADES || entry.getTotalValue() == 0) {
					continue;
				}
				double newAverage = entry.getTotalValue() / entry.getUniqueTrades();
				double changePercentage = newAverage / (double) (entry.getValue() + .001);
				if (changePercentage == 1.0) {
					continue;
				} else if (changePercentage > 1.15) {
					changePercentage = 1.15;
				} else if (changePercentage < 0.85) {
					changePercentage = 0.85;
				}
				int newValue = (int) (entry.getValue() * changePercentage);
				if (newValue == entry.getValue()) {
					if (changePercentage > 1.0) { // Fixes 1gp not being
						// influenced.
						newValue++;
					} else if (newValue > 0) {
						newValue--;
					}
				}
				entry.updateValue(newValue);
				entry.setLastUpdate(nextUpdate);
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
		nextUpdate = System.currentTimeMillis() + (UPDATE_CYCLE_HOURS * (60 * 60 * 1000));
	}

	/**
	 * Gets the database.
	 * @return The database.
	 */
	public static Map<Integer, GrandExchangeEntry> getDatabase() {
		return DATABASE;
	}

	/**
	 * Gets the nextUpdate.
	 * @return The nextUpdate.
	 */
	public static long getNextUpdate() {
		return nextUpdate;
	}

	/**
	 * Sets the nextUpdate.
	 * @param nextUpdate The nextUpdate to set.
	 */
	public static void setNextUpdate(long nextUpdate) {
		GrandExchangeDatabase.nextUpdate = nextUpdate;
	}

	/**
	 * Checks if the grand exchange database has initialized.
	 * @return {@code True} if so.
	 */
	public static boolean hasInitialized() {
		return initialized;
	}

}