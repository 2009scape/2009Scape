package org.crandor.game.content.global;

import org.crandor.game.content.dialogue.DialogueAction;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.tools.StringUtils;

/**
 * A god type.
 * @author Vexia
 */
public enum GodType {
	SARADOMIN(new Item(2412), new Item(2415), 2873, 913, "The cape disappears in a flash of light as it touches the ground."), GUTHIX(new Item(2413), new Item(2416), 2875, 914, "The cape disintegrates as it touches the earth."), ZAMORAK(new Item(2414), new Item(2417), 2874, 912, "The cape ignites and burns up as it touches the ground.");

	/**
	 * The cape.
	 */
	private final Item cape;

	/**
	 * The staff.
	 */
	private final Item staff;

	/**
	 * The statue id.
	 */
	private final int statueId;

	/**
	 * The npc id.
	 */
	private final int npcId;

	/**
	 * The drop message.
	 */
	private final String dropMessage;

	/**
	 * Constructs a new {@code GodCape} {@code Object}.
	 * @param cape the cape.
	 * @param staff the staff.
	 * @param statueId the id.
	 * @param npc id the id.
	 * @param drop message the drop message.
	 */
	private GodType(Item cape, Item staff, int statueId, int npcId, String dropMessage) {
		this.cape = cape;
		this.staff = staff;
		this.statueId = statueId;
		this.npcId = npcId;
		this.dropMessage = dropMessage;
	}

	/**
	 * Prays at a statue.
	 * @param player the player.
	 * @param statue the statue.
	 */
	public void pray(final Player player, final GameObject statue) {
		if (hasAny(player)) {
			player.lock(3);
			player.animate(Animation.create(645));
			player.sendMessages("You kneel and begin to chant to " + getName() + "...", "...but there is no response.");
			return;
		}
		player.getDialogueInterpreter().sendDialogue("You kneel and begin to chant to " + getName() + "...");
		player.getDialogueInterpreter().addAction(new DialogueAction() {

			@Override
			public void handle(final Player player, int buttonId) {
				player.lock();
				player.animate(Animation.create(645));
				GameWorld.Pulser.submit(new Pulse(3, player) {
					@Override
					public boolean pulse() {
						Location loc = statue.getLocation().transform(0, -1, 0);
						GroundItem g = GroundItemManager.get(cape.getId(), loc, player);
						if (g == null) {
							GroundItemManager.create(cape, loc, player);
						}
						player.getPacketDispatch().sendPositionedGraphic(86, 0, 0, loc);
						player.unlock();
						return true;
					}
				});
			}

		});
	}

	/**
	 * Gets the god type by the statue id.
	 * @param object the object.
	 * @return the type.
	 */
	public static GodType forObject(int object) {
		for (GodType type : values()) {
			if (type.getStatueId() == object) {
				return type;
			}
		}
		return null;
	}

	/**
	 * Checks if they have a god cape & which one.
	 * @param player the player.
	 * @param if invy check only.
	 * @return {@code GodCape} the cape.
	 */
	public static GodType getCape(Player player, boolean invyOnly) {
		for (GodType cape : values()) {
			if (invyOnly ? player.getInventory().containsItems(cape.getCape()) : (player.getEquipment().containsItem(cape.getCape()) || player.getInventory().containsItems(cape.getCape()))) {
				return cape;
			}
		}
		return null;
	}

	/**
	 * Checks if they have a god cape.
	 * @param player the player.
	 * @return the cape.
	 */
	public static GodType getCape(Player player) {
		return getCape(player, false);
	}

	/**
	 * Gets a god cape type by the id.
	 * @param cape the cape.
	 * @return the cape.
	 */
	public static GodType forCape(final Item cape) {
		for (GodType g : values()) {
			if (g.getCape().getId() == cape.getId()) {
				return g;
			}
		}
		return null;
	}

	/**
	 * Checks if the player is friendly.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public boolean isFriendly(Player player) {
		return player.getEquipment().containsItem(cape);
	}

	/**
	 * Gets the mage type.
	 * @param id the id.
	 * @return te type.
	 */
	public static GodType forId(int id) {
		for (GodType type : values()) {
			if (type.getNpcId() == id) {
				return type;
			}
		}
		return null;
	}

	/**
	 * Checks if the player has any capes.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public static boolean hasAny(Player player) {
		for (GodType t : values()) {
			if (player.hasItem(t.getCape())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the cape.
	 * @return The cape.
	 */
	public Item getCape() {
		return cape;
	}

	/**
	 * Gets the staff.
	 * @return The staff.
	 */
	public Item getStaff() {
		return staff;
	}

	/**
	 * Gets the statueId.
	 * @return The statueId.
	 */
	public int getStatueId() {
		return statueId;
	}

	/**
	 * Gets the dropMessage.
	 * @return The dropMessage.
	 */
	public String getDropMessage() {
		return dropMessage;
	}

	/**
	 * Gets the npcId.
	 * @return The npcId.
	 */
	public int getNpcId() {
		return npcId;
	}

	/**
	 * Gets the name.
	 * @return the name.
	 */
	public String getName() {
		return StringUtils.formatDisplayName(name().toLowerCase());
	}

}
