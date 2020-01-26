package org.arios.workspace.node.npc;

import java.nio.ByteBuffer;
import java.util.List;

import org.arios.cache.def.Definition;
import org.arios.cache.def.impl.NPCDefinition;
import org.arios.workspace.node.Configuration;
import org.arios.workspace.node.Node;

/**
 * An npc.
 * @author Vexia
 *
 */
public final class NPC extends Node<NPCDefinition> {

	/**
	 * Constructs a new {@Code NPC} {@Code Object}
	 * @param id the id.
	 */
	public NPC(int id) {
		super(id);
	}

	@Override
	public void setDefaultConfigs() {
		getConfigurations().put("lifepoints", new Configuration<Short>(1, (short) 10));
		getConfigurations().put("attack_level", new Configuration<Short>(2, (short) 1));
		getConfigurations().put("strength_level", new Configuration<Short>(3, (short) 1));
		getConfigurations().put("defence_level", new Configuration<Short>(4, (short) 1));
		getConfigurations().put("range_level", new Configuration<Short>(5, (short) 1));
		getConfigurations().put("magic_level", new Configuration<Short>(6, (short) 1));
		getConfigurations().put("examine", new Configuration<String>(7, "It's an NPC."));
		getConfigurations().put("poison_amount", new Configuration<Byte>(8, (byte) 0));
		getConfigurations().put("poison_immune", new Configuration<Boolean>(9, false));
		getConfigurations().put("respawn", new Configuration<Byte>(10, (byte) 17));
		getConfigurations().put("attack_speed", new Configuration<Byte>(11, (byte) 4));
		getConfigurations().put("movement_radius", new Configuration<Byte>(12, (byte) 10));
		getConfigurations().put("aggressive_radius", new Configuration<Byte>(13, (byte) 0));
		getConfigurations().put("attack_animation", new Configuration<Short>(14, (short) -1));
		getConfigurations().put("defence_animation", new Configuration<Short>(15, (short) -1));
		getConfigurations().put("death_animation", new Configuration<Short>(16, (short) -1));
		getConfigurations().put("spawn_animation", new Configuration<Short>(17, (short) -1));
		getConfigurations().put("attack_graphic", new Configuration<Short>(18, (short) -1));
		getConfigurations().put("attack_projectile", new Configuration<Short>(19, (short) -1));
		getConfigurations().put("impact_graphic", new Configuration<Short>(20, (short) -1));
		getConfigurations().put("weakness", new Configuration<Byte>(21, (byte) -1));
		getConfigurations().put("slayer_task", new Configuration<Byte>(22, (byte) -1));
		getConfigurations().put("slayer_experience", new Configuration<Double>(23, 0.0));
		getConfigurations().put("combat_style", new Configuration<Byte>(24, (byte) 0));
		getConfigurations().put("poisonous", new Configuration<Boolean>(40, false));
		getConfigurations().put("aggressive", new Configuration<Boolean>(41, false));
		getConfigurations().put("start_height", new Configuration<Byte>(42, (byte) 0));
		getConfigurations().put("prj_height", new Configuration<Byte>(45, (byte) 42));
		getConfigurations().put("end_height", new Configuration<Byte>(46, (byte) 96));
		getConfigurations().put("magic_animation", new Configuration<Short>(43, (short) -1));
		getConfigurations().put("range_animation", new Configuration<Short>(44, (short) -1));
		getConfigurations().put("clue_level", new Configuration<Byte>(47, (byte) 0));
		getConfigurations().put("spell_id", new Configuration<Short>(48, (short) -1));
		for (int i = 0; i < 15; i++) {
			getConfigurations().put("bonus-" + i, new Configuration<Short>(25 + i, (short) 0));
		}
		getConfigurations().put("combat_audios", new Configuration<Short[]>(49, new Short[3]) {
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
	}
	
	/**
	 * Displays the drops in the output.
	 */
	public void displayDrops() {
		for (TableType type : TableType.values()) {
			List<NPCDrop> drops = NPCDropManager.getDrops(getId(), type);
			for (NPCDrop drop : drops) {
				System.err.println(drop);
			}
		}
	}
	
	/**
	 * Gets the npc drop table.
	 * @param type the type.
	 * @return the npc drops.
	 */
	public NPCDrop[] getDrobTable(TableType type) {
		return NPCDropManager.getDropTable(id, type);
	}
	
	/**
	 * Adds a drop.
	 * @param drop the drop.
	 * @param type the type.
	 */
	public void addDrop(NPCDrop drop, TableType type) {
		NPCDropManager.getDrops(id, type).add(drop);
	}
	
	/**
	 * Removes the drop.
	 * @param drop the drop.
	 */
	public void removeDrop(NPCDrop drop, TableType type) {
		NPCDropManager.getDrops(id, type).remove(drop);
	}

	@Override
	public Definition forId(int id) {
		return NPCDefinition.forId(id);
	}

	@Override
	public String toString() {
		return super.toString() + " (cb=" + definition.getCombatLevel() + ")";
	}


}
