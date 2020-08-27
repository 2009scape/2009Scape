package core.tools;

import core.ServerConstants;
import core.game.node.item.Item;
import core.game.system.SystemLogger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import plugin.ge.GrandExchangeEntry;
import plugin.ge.GrandExchangeOffer;
import plugin.ge.OfferState;

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
import java.util.HashMap;

public class BinaryDBConverter {
    private static HashMap<Long, GrandExchangeOffer> OFFER_MAPPING = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Found GE DB... attempting to load.");
        File file = new File(ServerConstants.GRAND_EXCHANGE_DATA_PATH + "offer_dispatch_db.emp");
        Long offsetUID = null;
        if (!file.exists()) {
            System.err.println("[GEOfferDispatch]: Could not locate database! [path=" + file.getAbsolutePath() + "]");
            return;
        }
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw"); FileChannel c = raf.getChannel()) {
            ByteBuffer b = c.map(FileChannel.MapMode.READ_WRITE, 0, c.size());
            offsetUID = b.getLong();
            long uid;
            while ((uid = b.getLong()) != 0) {
                int itemId = b.getShort();
                boolean sale = b.get() == 1;
                GrandExchangeOffer offer = new GrandExchangeOffer(itemId, sale);
                offer.setUid(uid);
                offer.setAmount(b.getInt());
                offer.setCompletedAmount(b.getInt());
                offer.setOfferedValue(b.getInt());
                offer.setTimeStamp(b.getLong());
                offer.setState(OfferState.values()[b.get()]);
                offer.setTotalCoinExchange(b.getInt());
                offer.setPlayerUID(b.getInt());
                int idx = -1;
                while ((idx = b.get()) != -1) {
                    offer.getWithdraw()[idx] = new Item(b.getShort(), b.getInt());
                }
                OFFER_MAPPING.put(uid, offer);
            }
            raf.close();
            c.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }

        File f = new File(ServerConstants.GRAND_EXCHANGE_DATA_PATH + "offer_dispatch.xml");
        if(f.exists()){
            f.delete();
        }
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("database");
            doc.appendChild(root);

            System.out.println("Writing to " + file);

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

                for(int i = 0; i < o.getWithdraw().length; i++) {
                    Attr idAttr = doc.createAttribute("id");
                    Attr amtAttr = doc.createAttribute("amount");
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
            StreamResult result = new StreamResult(f);
            transformer.transform(source,result);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
