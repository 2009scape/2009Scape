package plugin.trivia;

import core.game.content.ItemNames;
import core.game.node.entity.player.Player;
import core.game.node.item.WeightedChanceItem;
import core.game.world.GameWorld;
import core.game.world.repository.Repository;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManifest;
import core.tools.RandomFunction;
import org.w3c.dom.*;
import plugin.CorePluginTypes.ManagerPlugin;
import plugin.CorePluginTypes.Managers;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@InitializablePlugin
@PluginManifest(name = "Trivia")
public class TriviaManager extends ManagerPlugin {
    public static boolean hasSession,continuous = false;
    public static TriviaQuestion question;
    private static WeightedChanceItem[] rewards;
    private static int  nextQuestion = 0;

    private static final int DELAY = 30; //delay between questions

    private static final String CONFIG_DIR = "data" + File.separator + "plugin" + File.separator + "Trivia" + File.separator;
    @Override
    public void tick() {
        if(continuous && !hasSession && (GameWorld.getTicks() > nextQuestion)){
            getNewQuestion();
            announce();
        }
    }

    public static void submit(Player player, String answer){
        if(answer.toLowerCase().equals(question.answer.toLowerCase())){
            Repository.sendNews(player.getUsername() + " has won the trivia question!");
            endSession();
            player.getInventory().add(RandomFunction.rollWeightedChanceTable(rewards));
            nextQuestion = GameWorld.getTicks() + DELAY;
        }
    }

    public static void dumpExample(){
        File f = new File(CONFIG_DIR + "TriviaRewards.xml");

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("rewards");
            doc.appendChild(root);

            Element exampleReward = doc.createElement("Reward");
            Attr idAttr = doc.createAttribute("itemId");
            Attr amtAttr = doc.createAttribute("amount");
            Attr wtAttr = doc.createAttribute("weight");
            idAttr.setValue("995");
            amtAttr.setValue("500");
            wtAttr.setValue("100");
            exampleReward.setAttributeNode(idAttr);
            exampleReward.setAttributeNode(amtAttr);
            exampleReward.setAttributeNode(wtAttr);
            root.appendChild(exampleReward);
            TransformerFactory tfactory = TransformerFactory.newInstance();
            Transformer transformer = tfactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(f);
            transformer.transform(source,result);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void parseRewards(){
        File f = new File(CONFIG_DIR);
        if(!f.exists()){
            f.mkdirs();
            dumpExample();
        }
        f = new File(CONFIG_DIR + "TriviaRewards.xml");

        try{
            List<WeightedChanceItem> itemList = new ArrayList<>();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(f);

            NodeList items = doc.getElementsByTagName("Reward");
            for(int i = 0; i < items.getLength(); i++){
                Node itemNode = items.item(i);
                if(itemNode.getNodeType() == Node.ELEMENT_NODE){
                    Element item = (Element) itemNode;
                    int weight = Integer.parseInt(item.getAttribute("weight"));
                    int itemId = Integer.parseInt(item.getAttribute("itemId"));
                    int amount = Integer.parseInt(item.getAttribute("amount"));
                    WeightedChanceItem it = new WeightedChanceItem(itemId,amount,weight);
                    itemList.add(it);
                }
            }
            rewards = itemList.toArray(new WeightedChanceItem[itemList.size()]);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        Managers.register(this);
        TriviaQuestions.loadQuestions();
        parseRewards();
        return this;
    }

    private static void getNewQuestion(){
        question = TriviaQuestions.getQuestion();
        hasSession = true;
    }

    private static void endSession(){
        hasSession = false;
    }

    public static void start(){
        if(!continuous){
            continuous = true;
        }
    }

    public static void stop(){
        continuous = false;
        hasSession = false;
    }

    public void announce(){
        Repository.sendNews(question.question);
    }
}
