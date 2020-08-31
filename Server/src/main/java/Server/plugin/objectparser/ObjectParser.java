package plugin.objectparser;

import core.ServerConstants;
import core.game.node.object.GameObject;
import core.game.world.GameWorld;
import core.game.world.map.build.LandscapeParser;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManifest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import plugin.CorePluginTypes.StartupPlugin;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

@InitializablePlugin
@PluginManifest(name="ObjectParser")
public class ObjectParser extends StartupPlugin {

    public void parseObjects(){
        File f = new File(ServerConstants.OBJECT_PARSER_PATH);
        if(!f.exists()){
            System.out.println("[ObjectParser]: Can't find file " + ServerConstants.OBJECT_PARSER_PATH);
            return;
        }

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(f);

            NodeList parseList = doc.getElementsByTagName("ObjectAction");

            System.out.println("Parsing " + parseList.getLength() + " object modifications.");

            for(int i = 0; i < parseList.getLength(); i++){
                Node parseNode = parseList.item(i);
                if(parseNode.getNodeType() == Node.ELEMENT_NODE){
                    Element parseElement = (Element) parseNode;
                    String type = parseElement.getAttribute("mode");
                    switch(type){
                        case "add": {
                            int id = Integer.parseInt(parseElement.getAttribute("id"));
                            int x = Integer.parseInt(parseElement.getAttribute("x"));
                            int y = Integer.parseInt(parseElement.getAttribute("y"));
                            int z = Integer.parseInt(parseElement.getAttribute("z"));
                            int objType = 10;
                            if (parseElement.hasAttribute("type")) {
                                objType = Integer.parseInt(parseElement.getAttribute("type"));
                            }
                            String rawDir = parseElement.getAttribute("direction");
                            int dir = 1;
                            switch (rawDir) {
                                case "n":
                                    dir = 1;
                                    break;
                                case "ne":
                                    dir = 2;
                                    break;
                                case "nw":
                                    dir = 0;
                                    break;
                                case "w":
                                    dir = 3;
                                    break;
                                case "e":
                                    dir = 4;
                                    break;
                                case "sw":
                                    dir = 5;
                                    break;
                                case "se":
                                    dir = 7;
                                case "s":
                                    dir = 6;
                                    break;
                            }
                            LandscapeParser.addGameObject(new GameObject(id, x, y, z, objType, dir));
                            break;
                        }
                        case "remove": {
                            int id = Integer.parseInt(parseElement.getAttribute("id"));
                            int x = Integer.parseInt(parseElement.getAttribute("x"));
                            int y = Integer.parseInt(parseElement.getAttribute("y"));
                            int z = Integer.parseInt(parseElement.getAttribute("z"));
                            int objType = 10;
                            LandscapeParser.removeGameObject(new GameObject(id,x,y,z));
                        }
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        parseObjects();
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        GameWorld.STARTUP_PLUGINS.add(this);
        return this;
    }

    @Override
    public Object fireEvent(String identifier, Object... args) {
        return null;
    }
}
