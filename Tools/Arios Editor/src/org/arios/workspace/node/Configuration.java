package org.arios.workspace.node;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.arios.cache.misc.ByteBufferUtils;

/**
 * Represents a configuration.
 * @author Emperor
 */
public class Configuration<T> {

	/**
	 * The parsing opcode.
	 */
	private final int opcode;

	/**
	 * The value of the configuration.
	 */
	protected T value;

	/**
	 * The class of the generic type.
	 */
	private final Class<T> type;

	/**
	 * Checks if the value is default.
	 */
	private boolean defaultValue = true;

	/**
	 * Constructs a new {@code Configuration} {@code Object}.
	 * @param opcode The opcode.
	 * @param value The value.
	 */
	@SuppressWarnings("unchecked")
	public Configuration(int opcode, T value) {
		if (opcode < 1) {
			throw new IllegalStateException("Opcode can't be smaller than 1!");
		}
		this.opcode = opcode;
		this.value = value;
		this.type = (Class<T>) value.getClass();
	}

	/**
	 * Saves to the byte buffer.
	 */
	public void save(ByteBuffer buffer) {
		if (type == Integer[].class) {
			Integer[] array = (Integer[]) value;
			buffer.put((byte) array.length);
			for (int i = 0; i < array.length; i++) {
				buffer.putInt(array[i]);
			}
		} else if (type == Short[].class) {
			Short[] array = (Short[]) value;
			buffer.put((byte) array.length);
			for (int i = 0; i < array.length; i++) {
				buffer.putShort(array[i]);
			}
		} else if (type == Byte.class) {
			buffer.put((Byte) value);
		} else if (type == Integer.class) {
			buffer.putInt((Integer) value);
		} else if (type == Short.class) {
			buffer.putShort((Short) value);
		} else if (type == String.class) {
			ByteBufferUtils.putString((String) value, buffer);
		} else if (type == Double.class) {
			buffer.putDouble((Double) value);
		} else if (type == HashMap.class) {
			int size = buffer.get();
			Map<Integer, Integer> requirements = new HashMap<>();
			for (int i = 0; i < size; i++) {
				requirements.put(buffer.get() & 0xFF, buffer.get() & 0xFF);
			}
			setValue(requirements);
		} else if (type == Boolean.class) {

		} else {
			System.err.println("unknown type for "  + type);
		}
	}

	/**
	 * Parses a config.
	 * @param buffer the buffer.
	 */
	public void parse(ByteBuffer buffer) {
		if (type == Integer[].class) {
			int size = buffer.get();
			int[] array = new int[size];
			for (int i = 0; i < size; i++){
				array[i] = buffer.getInt();
			}
			setValue(array);
		} else if (type == Short[].class) {
			int size = buffer.get();
			short[] array = new short[size];
			for (int i = 0; i < size; i++){
				array[i] = buffer.getShort();
			}
			setValue(array);
		} else if (type == HashMap.class) {
			@SuppressWarnings("unchecked")
			HashMap<Integer, Integer> req = (HashMap<Integer, Integer>) value;
			buffer.put((byte) req.size());
			for (int skill : req.keySet()) {
				buffer.put((byte) skill);
				buffer.put((byte) (int) req.get(skill));
			}
		} else if (type == Byte.class) {
			setValue(buffer.get());
		} else if (type == Short.class) {
			setValue(buffer.getShort());
		} else if (type == Integer.class) {
			setValue(buffer.getInt());
		} else if (type == String.class) {
			setValue(ByteBufferUtils.getString(buffer));
		} else if (type == Double.class) {
			setValue(buffer.getDouble());
		} else if (type == Boolean.class) {
			setValue(true);
		} else {
			System.err.println("unknown type for "  + type);
		}
	}
	
	/**
	 * Checks if the config can be saved.
	 * @return {@code True} if so.
	 */
	public boolean canSave() {
		if (type == Boolean.class) {
			if ((Boolean) value == false) {
				return false;
			}
		}
		return !isDefaultValue();
	}

	/**
	 * Sets the value.
	 * @param value the value to set.
	 */
	@SuppressWarnings("unchecked")
	public void setValue(Object value) {
		if (value != this.value) {
			this.defaultValue = false;
		}
		this.value = (T) value;
	}

	/** 
	 * Gets the value.
	 * @return the value.
	 */
	public T getValue() {
		return value;
	}

	/** 
	 * Gets the opcode.
	 * @return the opcode.
	 */
	public int getOpcode() {
		return opcode;
	}

	/** 
	 * Gets the defaultValue.
	 * @return the defaultValue.
	 */
	public boolean isDefaultValue() {
		return defaultValue;
	}

	/**
	 * Sets the defaultValue.
	 * @param defaultValue the defaultValue to set.
	 */
	public void setDefaultValue(boolean defaultValue) {
		this.defaultValue = defaultValue;
	}

	/** 
	 * Gets the type.
	 * @return the type.
	 */
	public Class<T> getType() {
		return type;
	}
}