package plugin.ge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import core.cache.def.impl.ItemDefinition;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.PlayerDetails;
import core.game.node.entity.player.info.portal.PlayerSQLManager;
import core.game.node.item.Item;
import core.game.world.GameWorld;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Auto stocking feature for the grand exchange. 
 * @author Ceikry
 *
 */
public class GEAutoStock {

	public static List<GrandExchangeOffer> STOCK = new ArrayList<>();

	public static void parse(String file){
		if(GameWorld.getSettings().getAutostock_ge()) {
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(file);

				doc.getDocumentElement().normalize();

				Player p = new Player(PlayerDetails.getDetails(doc.getElementsByTagName("player").item(0).getTextContent()));

				NodeList entries = doc.getElementsByTagName("AutoStock");

				for (int i = 0; i < entries.getLength(); i++) {
					Node node = entries.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element entry = (Element) node;
						int amount = Integer.parseInt(entry.getAttribute("amount"));
						int itemId = Integer.parseInt(entry.getAttribute("itemId"));
						double modifier = Double.parseDouble(entry.getAttribute("modifier"));
						int value = (int) new Item(itemId).getValue();
						value *= modifier;
						GrandExchangeOffer o = new GrandExchangeOffer(itemId, true);
						o.setAmount(amount);
						o.setOfferedValue(value);
						GEOfferDispatch.dispatch(p, o);
						System.out.println("Stocked " + new Item(itemId).getName() + " x" + amount + " at " + value + " each.");
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static List<GrandExchangeOffer> getStock(){
		return STOCK;
	}
}
