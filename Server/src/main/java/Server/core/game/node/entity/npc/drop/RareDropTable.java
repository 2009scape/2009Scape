package core.game.node.entity.npc.drop;

import core.ServerConstants;
import core.game.node.item.Item;
import core.game.node.item.WeightedChanceItem;
import core.game.system.SystemLogger;
import core.tools.RandomFunction;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the rare drop table.
 * @author Ceikry
 */
public final class RareDropTable {

	/**
	 * The item id of the item representing the rare drop table slot in a drop
	 * table.
	 */
	public static final int SLOT_ITEM_ID = 31;

	/**
	 * The rare drop table.
	 */
	private static final List<WeightedChanceItem> TABLE = new ArrayList<>();


	/**
	 * Initialize needed objects for xml reading/writing
	 */
	static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	static DocumentBuilder builder;

	static {
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public RareDropTable() throws ParserConfigurationException {}

	/**
	 * Initializes the rare drop table.
	 */
	public static void init(){
		if(!new File(ServerConstants.RDT_DATA_PATH).exists()){
			SystemLogger.log("Can't locate RDT file at " + ServerConstants.RDT_DATA_PATH);
			return;
		}
		parse(ServerConstants.RDT_DATA_PATH);
	}

	/**
	 * Parses the xml file for the RDT.
	 * @param file the .xml file containing the RDT.
	 */
	public static void parse(String file){
		try {
			Document doc = builder.parse(file);

			NodeList itemNodes = doc.getElementsByTagName("item");
			for(int i = 0; i < itemNodes.getLength(); i++){
				Node itemNode = itemNodes.item(i);
				if(itemNode.getNodeType() == Node.ELEMENT_NODE){
					Element item = (Element) itemNode;
					int itemId = Integer.parseInt(item.getAttribute("id"));
					int minAmt = Integer.parseInt(item.getAttribute("minAmt"));
					int maxAmt = Integer.parseInt(item.getAttribute("maxAmt"));
					int weight = Integer.parseInt(item.getAttribute("weight"));

					TABLE.add(new WeightedChanceItem(itemId,minAmt,maxAmt,weight));
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public static Item retrieve(){
		return RandomFunction.rollWeightedChanceTable(TABLE);
	}
}