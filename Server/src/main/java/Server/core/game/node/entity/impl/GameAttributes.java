package core.game.node.entity.impl;

import core.ServerConstants;
import core.cache.misc.buffer.ByteBufferUtils;
import core.game.system.SystemLogger;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles an entity's game attributes.
 * @author Emperor
 */
public final class GameAttributes {

	/**
	 * The attributes mapping.
	 */
	private final Map<String, Object> attributes = new HashMap<>();

	/**
	 * The list of attributes to save.
	 */
	private final List<String> savedAttributes = new ArrayList<>();

	/**
	 * Constructs a new {@code GameAttributes} {@code Object}.
	 */
	public GameAttributes() {
		/*
		 * Empty.
		 */
	}

	/**
	 * Writes the attribute data to the player buffer.
	 * @param buffer The player's data buffer.
	 */
	public void dump(String file) {
		//buffer.put((byte) savedAttributes.size());
		File pathDir = new File(ServerConstants.PLAYER_ATTRIBUTE_PATH);
		File saveFile = new File(ServerConstants.PLAYER_ATTRIBUTE_PATH + file);
		if(!pathDir.exists()){
			pathDir.mkdirs();
		}
		if(saveFile.exists()){
			saveFile.delete();
		}
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();

			Element root = doc.createElement("attributes");
			doc.appendChild(root);

			for (String key : savedAttributes){
				Element attrElement = doc.createElement("GameAttribute");
				Attr keyAttr = doc.createAttribute("key");
				Attr valAttr = doc.createAttribute("value");
				Attr typeAttr = doc.createAttribute("type");
				Object value = attributes.get(key);
				keyAttr.setValue(key);
				valAttr.setValue("" + value);
				if(value instanceof Integer){
					typeAttr.setValue("int");
				} else if (value instanceof Short){
					typeAttr.setValue("short");
				} else if (value instanceof Long){
					typeAttr.setValue("long");
				} else if (value instanceof Byte){
					typeAttr.setValue("byte");
				} else if (value instanceof Boolean){
					typeAttr.setValue("bool");
				} else if (value instanceof  String){
					typeAttr.setValue("string");
				}
				attrElement.setAttributeNode(keyAttr);
				attrElement.setAttributeNode(valAttr);
				attrElement.setAttributeNode(typeAttr);
				root.appendChild(attrElement);
			}
			TransformerFactory tfactory = TransformerFactory.newInstance();
			Transformer transformer = tfactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(saveFile);
			transformer.transform(source,result);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Parses the saved attributes from the buffer.
	 * @param buffer The buffer.
	 */
	public void parse(String file) {
		File saveFile = new File(ServerConstants.PLAYER_ATTRIBUTE_PATH + file);
		if(!saveFile.exists()){
			return;
		}
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(saveFile);

			NodeList attributesList = doc.getElementsByTagName("GameAttribute");
			for(int i = 0; i < attributesList.getLength(); i++){
				Node attrNode = attributesList.item(i);
				if(attrNode.getNodeType() == Node.ELEMENT_NODE){
					Element attr = (Element) attrNode;
					String key = attr.getAttribute("key");
					switch(attr.getAttribute("type")){
						case "bool": {
							boolean value = Boolean.parseBoolean(attr.getAttribute("value"));
							attributes.put(key, value);
							if (!savedAttributes.contains(key)) {
								savedAttributes.add(key);
							}
							break;
						}
						case "long": {
							long value = Long.parseLong(attr.getAttribute("value"));
							attributes.put(key, value);
							if (!savedAttributes.contains(key)) {
								savedAttributes.add(key);
							}
							break;
						}
						case "short":{
							short value = Short.parseShort(attr.getAttribute("value"));
							attributes.put(key, value);
							if (!savedAttributes.contains(key)) {
								savedAttributes.add(key);
							}
							break;
						}
						case "int":{
							int value = Integer.parseInt(attr.getAttribute("value"));
							attributes.put(key, value);
							if (!savedAttributes.contains(key)) {
								savedAttributes.add(key);
							}
							break;
						}
						case "byte":{
							byte value = Byte.parseByte(attr.getAttribute("value"));
							attributes.put(key, value);
							if (!savedAttributes.contains(key)) {
								savedAttributes.add(key);
							}
							break;
						}
						case "string":{
							String value = attr.getAttribute("value");
							attributes.put(key, value);
							if (!savedAttributes.contains(key)) {
								savedAttributes.add(key);
							}
							break;
						}
					}
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Sets an attribute value.
	 * @param key The attribute name.
	 * @param value The attribute value.
	 */
	public void setAttribute(String key, Object value) {
		if (key.startsWith("/save:")) {
			key = key.substring(6, key.length());
			if (!savedAttributes.contains(key)) {
				savedAttributes.add(key);
			}
		}
		attributes.put(key, value);
	}

	/**
	 * Gets an attribute.
	 * @param key The attribute name.
	 * @return The attribute value.
	 */
	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String key) {
		if (!attributes.containsKey(key)) {
			return null;
		}
		return (T) attributes.get(key);
	}

	/**
	 * Gets an attribute.
	 * @param string The attribute name.
	 * @param fail The value to return if the attribute is null.
	 * @return The attribute value, or the fail argument when null.
	 */
	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String string, T fail) {
		Object object = attributes.get(string);
		if (object != null) {
			return (T) object;
		}
		return fail;
	}

	/**
	 * Removes an attribute.
	 * @param string The attribute name.
	 */
	public void removeAttribute(String string) {
		savedAttributes.remove(string);
		attributes.remove(string);
	}

	/**
	 * Gets the attributes.
	 * @return The attributes.
	 */
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	/**
	 * Gets the savedAttributes.
	 * @return The savedAttributes.
	 */
	public List<String> getSavedAttributes() {
		return savedAttributes;
	}
}