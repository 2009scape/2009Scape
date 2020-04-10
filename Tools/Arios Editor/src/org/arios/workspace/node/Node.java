package org.arios.workspace.node;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.arios.cache.def.Definition;

/**
 * Represents a node to configure.
 * @author Vexia
 */
public abstract class Node <T extends Definition> {

	/**
	 * The definition.
	 */
	protected T definition;

	/**
	 * The id of the config.
	 */
	protected final int id;

	/**
	 * Constructs a new {@code node} {@code Object}
	 */
	@SuppressWarnings("unchecked")
	public Node(int id) {
		this.id = id;
		this.definition = (T) forId(id);
		setDefaultConfigs();
	}
	
	/**
	 * Sets the defaults configs.
	 */
	public abstract void setDefaultConfigs();

	/**
	 * Gets the definition for the id.
	 * @param id the id.
	 * @return the definition.
	 */
	public abstract Definition forId(int id);
	
	/**
	 * Parses the config for the byte buffer.
	 * @param buffer
	 */
	public void parse(ByteBuffer buffer) {
		int opcode;
		Configuration<?> config;
		while ((opcode = buffer.get() & 0xFF) != 0) {
			config = getByOpcode(opcode);
			if (config != null) {
				config.parse(buffer);
			}
		}
	}

	/**
	 * Saves the configs to the byte buffer.
	 * @param buffer the buffer.
	 */
	public void save(ByteBuffer buffer) {
		for (Configuration<?> config : getConfigurations().values()) {
			if (config.canSave()) {
				try {
				config.save(buffer.put((byte) config.getOpcode()));
				} catch (ClassCastException e) {
					System.err.println("CONFIG OPCODE=" + config.getOpcode() + " VALUE=" + config.getValue() + " SET TYPE=" + config.getType());
					e.printStackTrace();
				}
			}
		}
		buffer.put((byte) 0);
	}
	
	/**
	 * Sets a config.
	 * @param name the name.
	 * @param value the value.
	 */
	public void setConfig(String name, Object value) {
		Configuration<?> config = definition.getConfiguration(name);
		if (config == null) {
			System.err.println("Error! No config set for name " + name  + ", value=" + value);
			return;
		} 
		config.setValue(value);
	}
	
	/**
	 * Gets the config by the opcode.
	 * @param opcode the opcode.
	 * @return the config.
	 */
	public Configuration<?> getByOpcode(int opcode) {
		for (Configuration<?> c : definition.getConfigurations().values()) {
			if (c.getOpcode() == opcode) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Gets the config value.
	 * @param name the name.
	 * @return the value.
	 */
	public Object getConfigValue(String name) {
		if (!definition.getConfigurations().containsKey(name)) {
			System.err.println("unfound config name - " + name);
			return "null";
		}
		return definition.getConfigurations().get(name).getValue();
	}
	
	/**
	 * Checks if this is a default config.
	 * @return {@code True} if so.
	 */
	public boolean isDefault() {
		for (Configuration<?> config : definition.getConfigurations().values()) {
			if (!config.isDefaultValue()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gets the name.
	 * @return the name.
	 */
	public String getName() {
		if (definition == null) {
			return "Null";
		}
		return definition.getName();
	}

	/** 
	 * Gets the id.
	 * @return the id.
	 */
	public int getId() {
		return id;
	}

	/** 
	 * Gets the configurations.
	 * @return the configurations.
	 */
	public Map<String, Configuration<?>> getConfigurations() {
		if (definition == null) {
			return new HashMap<>();
		}
		return definition.getConfigurations();
	}

	/** 
	 * Gets the definition.
	 * @return the definition.
	 */
	public T getDefinition() {
		return definition;
	}

	/**
	 * Sets the definition.
	 * @param definition the definition to set.
	 */
	public void setDefinition(T definition) {
		this.definition = definition;
	}

	/**
	 * Prints the configs for a node.
	 */
	public void printConfigs() {
		System.err.println("For type -" + this);
		for (Entry<String, Configuration<?>> s : getConfigurations().entrySet()) {
			System.err.println("Config name - " + s.getKey() + ", value=" + s.getValue().getValue() + "!");
		}
	}
	
	@Override
	public String toString() {
		return getName() + " - " + id;
	}
}
