package org.arios.workspace.node.item;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.arios.cache.def.Definition;
import org.arios.cache.def.impl.ItemDefinition;
import org.arios.cache.misc.ByteBufferUtils;
import org.arios.workspace.node.Configuration;
import org.arios.workspace.node.Node;

/**
 * An item.
 * @author Vexia
 *
 */
public final class Item extends Node<ItemDefinition> {

	/**
	 * Constructs a new {@Code Item} {@Code Object}
	 * @param id the id.
	 */
	public Item(int id) {
		super(id);
	}

	@Override
	public void setDefaultConfigs() {
		getConfigurations().put("tradeable", new Configuration<Boolean>(1, (boolean) false));
		getConfigurations().put("lendable", new Configuration<Boolean>(2, (boolean) false));
		getConfigurations().put("high_alch", new Configuration<Integer>(3, (int) 0));
		getConfigurations().put("low_alch", new Configuration<Integer>(4, (int) 0));
		getConfigurations().put("destroy", new Configuration<Boolean>(5, (boolean) false));
		getConfigurations().put("shop_price", new Configuration<Integer>(6, (int) 0));
		getConfigurations().put("ge_price", new Configuration<Integer>(7, (int) 0));
		getConfigurations().put("examine", new Configuration<String>(8, "It's an item.") {
			@Override
			public void parse(ByteBuffer buffer) {
				String s = ByteBufferUtils.getString(buffer);
				while (s.length() > 0 && s.charAt(0) == ' ') {
					s = s.substring(1, s.length());
				}
				if (s.length() > 255) {
					s = s.substring(0, 255);
				}
				setValue(s);
			}
		});
		getConfigurations().put("weight", new Configuration<Short>(9, (short) 0));
		getConfigurations().put("bonuses", new Configuration<Short[]>(10, new Short[15]) {

			@Override
			public void parse(ByteBuffer buf) {
				Short[] bonuses = new Short[15];
				for (int i = 0; i < bonuses.length; i++) {
					bonuses[i] = buf.getShort();
				}
				setValue(bonuses);
			}

			@Override
			public void save(ByteBuffer buf) {
				Short[] bonuses = (Short[]) value;
				for (int i = 0; i < bonuses.length; i++) {
					buf.putShort((Short) bonuses[i]);
				}
			}

			@Override
			public boolean canSave() {
				if (Arrays.equals(new Short[15], (Short[]) value)) {
					return false;
				}
				return super.canSave();
			}
		});
		getConfigurations().put("absorb", new Configuration<Short[]>(11, new Short[3]) {
			@Override
			public void parse(ByteBuffer buf) {
				Short[] absorb = new Short[3];
				for (int i = 0; i < absorb.length; i++) {
					absorb[i] = buf.getShort();
				}
				setValue(absorb);
			}

			@Override
			public void save(ByteBuffer buf) {
				Short[] bonuses = (Short[]) value;
				for (int i = 0; i < bonuses.length; i++) {
					buf.putShort((short) bonuses[i]);
				}
			}
		});
		getConfigurations().put("two_handed", new Configuration<Boolean>(12, false));
		getConfigurations().put("equip_slot", new Configuration<Byte>(13, (byte) -1));
		getConfigurations().put("attack_speed", new Configuration<Byte>(14, (byte) -1));
		getConfigurations().put("remove_head", new Configuration<Boolean>(15, false));
		getConfigurations().put("remove_beard", new Configuration<Boolean>(16, false));
		getConfigurations().put("remove_sleeves", new Configuration<Boolean>(17, false));
		getConfigurations().put("stand_anim", new Configuration<Integer>(18, (int) -1));
		getConfigurations().put("stand_turn_anim", new Configuration<Integer>(19, (int) -1));
		getConfigurations().put("walk_anim", new Configuration<Integer>(20, (int) -1));
		getConfigurations().put("run_anim", new Configuration<Integer>(21, (int) -1));
		getConfigurations().put("turn_180_anim", new Configuration<Integer>(22, (int) -1));
		getConfigurations().put("turn_90_cw_anim", new Configuration<Integer>(23, (int) -1));
		getConfigurations().put("turn_90_ccw_anim", new Configuration<Integer>(24, (int) -1));
		getConfigurations().put("weapon_interface", new Configuration<Byte>(25, (byte) 0));
		getConfigurations().put("has_special", new Configuration<Boolean>(26, (boolean) false));
		getConfigurations().put("attack_anims", new Configuration<Short[]>(27, new Short[] {}) {
			@Override
			public void parse(ByteBuffer buf) {
				int size;
				size = buf.get();
				Short[] animations = new Short[size];
				for (int i = 0; i < size; i++) {
					animations[i] = buf.getShort();
				}
				setValue(animations);
			}

			@Override
			public void save(ByteBuffer buf) {
				Short[] anims = (Short[]) value;
				buf.put((byte) anims.length);
				for (Short anim : anims) {
					buf.putShort(anim);
				}
			}

			@Override
			public boolean canSave() {
				Short[] val = (Short[]) value;
				return val.length > 0 && super.canSave();
			}
		});
		getConfigurations().put("destroy_message", new Configuration<String>(28, ""));
		getConfigurations().put("requirements", new Configuration<Map<Integer, Integer>>(29, new HashMap<Integer, Integer>()) {

			@Override
			public void parse(ByteBuffer buf) {
				int size = buf.get() & 0xFF;
				Map<Integer, Integer> requirements = new HashMap<>();
				for (int i = 0; i < size; i++) {
					int skill = buf.get() & 0xFF;
					requirements.put(skill, buf.get() & 0xFF);
				}
				setValue(requirements);
			}

			@Override
			public void save(ByteBuffer buffer) {
				Map<Integer, Integer> req = (Map<Integer, Integer>) value;
				buffer.put((byte) req.size());
				for (int skill : req.keySet()) {
					buffer.put((byte) skill);
					buffer.put((byte) (int) req.get(skill));
				}
			}
		});
		getConfigurations().put("ge_limit", new Configuration<Integer>(30, (int) 0));
		getConfigurations().put("defence_animation", new Configuration<Integer>(31, (int) -1));
		getConfigurations().put("attack_audios", new Configuration<Short[]>(33, new Short[4]) {
			@Override
			public void parse(ByteBuffer buf) {
				int size;
				size = buf.get();
				Short[] audios = new Short[size];
				for (int i = 0; i < size; i++) {
					audios[i] = buf.getShort();
				}
				setValue(audios);
			}

			@Override
			public void save(ByteBuffer buf) {
				Short[] anims = (Short[]) value;
				buf.put((byte) anims.length);
				//System.err.println(Arrays.toString(anims));
				for (Short anim : anims) {
					if (anim == null) {
						anim = 0;
					}
					buf.putShort(anim);
				}
			}

			@Override
			public boolean canSave() {
				Short[] anims = (Short[]) value;
				if (anims == null) {
					return false;
				}
				return super.canSave();
			}
		});
		getConfigurations().put("pk_price", new Configuration<Integer>(34, (int) 0));
		getConfigurations().put("spawnable", new Configuration<Boolean>(35, true) {
			@Override
			public void parse(ByteBuffer buf) {
				setValue(false);
			}
			@Override
			public boolean canSave() {
				return super.getValue() == false;
			}
		});
		getConfigurations().put("trade-override", new Configuration<Boolean>(36, false));
		getConfigurations().put("bankable", new Configuration<Boolean>(37, true) {
			@Override
			public void parse(ByteBuffer buf) {
				setValue(false);
			}
			@Override
			public boolean canSave() {
				return super.getValue() == false;
			}
		});
		getConfigurations().put("rare_item", new Configuration<Boolean>(38, false));
	}

	@Override
	public Definition forId(int id) {
		return ItemDefinition.forId(id);
	}

}
