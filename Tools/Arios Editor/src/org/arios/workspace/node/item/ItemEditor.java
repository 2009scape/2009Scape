package org.arios.workspace.node.item;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.arios.cache.ServerStore;
import org.arios.cache.misc.ByteBufferUtils;
import org.arios.cache.misc.DefinitionSize;
import org.arios.workspace.editor.EditorTab;

/**
 * An item editor.
 * @author Vexia
 *
 */
public class ItemEditor extends EditorTab {

	/**
	 * The serial UID.
	 */
	private static final long serialVersionUID = 9106740527672015864L;
	
	/**
	 * If we're converting the old format.
	 */
	private static boolean convert = false;

	/**
	 * Constructs a new {@Code Itemditor} {@Code Object}
	 * @param name the name.
	 */
	public ItemEditor(String name) {
		super(name);
	}
	
	@Override
	public void parse() {
		ByteBuffer buf = ServerStore.getArchive("item_config");
		Item item;
		for (int id = 0; id < DefinitionSize.getItemDefinitionsSize(); id++) {
			item = new Item(id);
			if (convert) {
				convertDump(item, buf);
 			} else {
			item.parse(buf);
 			}
			nodes.put(id, item);
		}
	}

	@Override
	public boolean save() {
		ByteBuffer buffer = ByteBuffer.allocate(3145680 << 1);
		for (int id = 0; id < DefinitionSize.getItemDefinitionsSize(); id++) {
			Item item = (Item) nodes.get(id);
			item.save(buffer);
		}
		buffer.flip();
		ServerStore.setArchive("item_config", buffer, false);
		return true;
	}
	
	private void convertDump(Item item, ByteBuffer buf) {
		int opcode;
		int size;
		while ((opcode = buf.get() & 0xFF) != 0) {
			switch (opcode) {
			case 1://Tradable.
				item.setConfig("tradeable", true);
				break;
			case 2://Lendable.
				item.setConfig("lendable", true);
				break;
			case 3://High alch.
				item.setConfig("high_alch", buf.getInt());
				break;
			case 4://Low alch.
				item.setConfig("low_alch", buf.getInt());
				break;
			case 5://Destroy.
				item.setConfig("destroy", true);
				break;
			case 6://Shop price.
				item.setConfig("shop_price", buf.getInt());
				break;
			case 7://GE price.
				item.setConfig("ge_price", buf.getInt());
				break;
			case 8://Examine.
				String s = ByteBufferUtils.getString(buf);
				while (s.length() > 0 && s.charAt(0) == ' ') {
					s = s.substring(1, s.length());
				}
				if (s.length() > 255) {
					s = s.substring(0, 255);
				}
				item.setConfig("examine", s);
				break;
			case 9://Weight.
				item.setConfig("weight", buf.getShort());
				break;
			case 10://Bonuses
				Short[] bonuses = new Short[15];
				for (int i = 0; i < bonuses.length; i++) {
					bonuses[i] = buf.getShort();
				}
				item.setConfig("bonuses", bonuses);
				break;
			case 11://Absorb.
				Short[] absorb = new Short[3];
				for (int i = 0; i < absorb.length; i++) {
					absorb[i] = buf.getShort();
				}
				item.setConfig("absorb", absorb);
				break;
			case 12://Two handed.
				item.setConfig("two_handed", true);
				break;
			case 13://Equipment slot.
				item.setConfig("equip_slot", buf.get());
				break;
			case 14://Attack speed.
				item.setConfig("attack_speed", buf.get());
				break;
			case 15:
				item.setConfig("remove_head", true);
				break;
			case 16:
				item.setConfig("remove_beard", true);
				break;
			case 17:
				item.setConfig("remove_sleeves", true);
				break;
			case 18:
				int anim = buf.getShort() & 0xFFFF;
				if (anim < DefinitionSize.getAnimationDefinitionsSize()) {
					
				}
				anim = buf.getShort() & 0xFFFF;
				if (anim < DefinitionSize.getAnimationDefinitionsSize()) {
					item.setConfig("stand_turn_anim", anim);
				}
				anim = buf.getShort() & 0xFFFF ;
				if (anim < DefinitionSize.getAnimationDefinitionsSize()) {
					item.setConfig("walk_anim", anim);
				}
				anim = buf.getShort() & 0xFFFF;
				if (anim < DefinitionSize.getAnimationDefinitionsSize()) {
					item.setConfig("run_anim", anim);
				}
				anim = buf.getShort() & 0xFFFF;
				if (anim < DefinitionSize.getAnimationDefinitionsSize()) {
					item.setConfig("turn_180_anim", anim);
				}
				anim = buf.getShort() & 0xFFFF;
				if (anim < DefinitionSize.getAnimationDefinitionsSize()) {
					item.setConfig("turn_90_cw_anim", anim);
				}
				anim = buf.getShort() & 0xFFFF;
				if (anim < DefinitionSize.getAnimationDefinitionsSize()) {
					item.setConfig("turn_90_ccw_anim", anim);
				}
				break;
			case 19://Weapon interface.
				item.setConfig("weapon_interface", buf.get());
				break;
			case 20: //Has special attack bar.
				item.setConfig("has_special", true);
				break;
			case 21:
				size = buf.get();
				Short[] animation = new Short[size];
				for (int i = 0; i < size; i++) {
					animation[i] = buf.getShort();
				}
				item.setConfig("attack_anims", animation);
				break;
			case 22:
				item.setConfig("destroy_message", ByteBufferUtils.getString(buf));
				break;
			case 23:
				size = buf.get() & 0xFF;
				Map<Integer, Integer> requirements = new HashMap<>();
				for (int i = 0; i < size; i++) {
					requirements.put(buf.get() & 0xFF, buf.get() & 0xFF);
				}
				item.setConfig("requirements", requirements);
				break;
			case 24:
				item.setConfig("ge_limit", buf.getShort() & 0xFFFF);
				break;
			case 25:
				item.setConfig("defence_animation", buf.getShort() & 0xFFFF);
				break;
			}
		}
	}
}
