package core.game.node.entity.combat.equipment;

import core.game.node.entity.impl.Animator.Priority;
import core.game.world.update.flag.context.Animation;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class holding all the range weapon definitions.
 * @author Emperor
 */
public final class RangeWeapon {

	/**
	 * The range weapons mapping.
	 */
	private static final Map<Integer, RangeWeapon> RANGE_WEAPONS = new HashMap<Integer, RangeWeapon>();

	/**
	 * The mapping containing all the possible ammunition types.
	 */
	private final List<Integer> ammunition;

	/**
	 * The item id.
	 */
	private final int itemId;

	/**
	 * The attack animation id.
	 */
	private final Animation animation;

	/**
	 * The attack speed.
	 */
	private final int attackSpeed;

	/**
	 * The equipment slot the ammunition uses, if any.
	 */
	private final int ammunitionSlot;

	/**
	 * The weapon type.
	 */
	private final int type;

	/**
	 * If we should drop ammo.
	 */
	private final boolean dropAmmo;

	/**
	 * Constructs a new {@code RangeWeapon} {@code Object}.
	 * @param itemId The item id.
	 * @param animation The animation.
	 * @param attackSpeed The attack speed.
	 * @param ammunitionSlot The ammunition's equipment slot, or -1 if not worn.
	 * @param type The weapon type.
	 * @param dropAmmo If the ammunition should be dropped.
	 * @param ammunition The possible ammunition vector list.
	 */
	public RangeWeapon(int itemId, Animation animation, int attackSpeed, int ammunitionSlot, int type, boolean dropAmmo, List<Integer> ammunition) {
		this.itemId = itemId;
		this.animation = animation;
		this.attackSpeed = attackSpeed;
		this.ammunitionSlot = ammunitionSlot;
		this.type = type;
		this.dropAmmo = dropAmmo;
		this.ammunition = ammunition;
	}

	/**
	 * Populates the range weapons mapping.
	 * @return {@code True}.
	 */
	public static boolean initialize() {
		Document doc;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(new File("This file never existed. I have no idea how this code works, but it probably doesn't get called anywhere"));
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
		NodeList nodeList = doc.getDocumentElement().getChildNodes();
		System.out.println("Loading range weapon info...");
		for (short i = 1; i < nodeList.getLength(); i += 2) {
			Node n = nodeList.item(i);
			if (n != null) {
				if (n.getNodeName().equalsIgnoreCase("Weapon")) {
					NodeList list = n.getChildNodes();
					int itemId = 0;
					int animationId = 426;
					int attackSpeed = 4;
					int slot = 13;
					int type = 0;
					boolean dropAmmo = true;
					List<Integer> ammo = new ArrayList<Integer>();
					for (int a = 1; a < list.getLength(); a += 2) {
						Node node = list.item(a);
						if (node.getNodeName().equalsIgnoreCase("itemId")) {
							itemId = Integer.parseInt(node.getTextContent());
						} else if (node.getNodeName().equalsIgnoreCase("animationId")) {
							animationId = Integer.parseInt(node.getTextContent());
						} else if (node.getNodeName().equalsIgnoreCase("attackSpeed")) {
							attackSpeed = Integer.parseInt(node.getTextContent());
						} else if (node.getNodeName().equalsIgnoreCase("ammunitionSlot")) {
							slot = Integer.parseInt(node.getTextContent());
						} else if (node.getNodeName().equalsIgnoreCase("type")) {
							type = Integer.parseInt(node.getTextContent());
						} else if (node.getNodeName().equalsIgnoreCase("drop_ammo")) {
							dropAmmo = Boolean.parseBoolean(node.getTextContent());
						} else if (node.getNodeName().equalsIgnoreCase("ammunition")) {
							int ammunitionId = Integer.parseInt(node.getTextContent());
							ammo.add(ammunitionId);
						}
					}
					RANGE_WEAPONS.put(itemId, new RangeWeapon(itemId, new Animation(animationId, 0, Priority.HIGH), attackSpeed, slot, type, dropAmmo, ammo));
				}
			}
		}
		System.out.println("Loaded " + RANGE_WEAPONS.size() + " range weapon definitions.");
		return true;
	}

	/**
	 * Gets the range weapons mapping.
	 * @return The range weapons.
	 */
	public static Map<Integer, RangeWeapon> getRangeWeapons() {
		return RANGE_WEAPONS;
	}

	/**
	 * Gets a range weapon instance from the mapping.
	 * @param id The item id.
	 * @return The instance.
	 */
	public static RangeWeapon get(int id) {
		return RANGE_WEAPONS.get(id);
	}

	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * @return the animation
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * @return the ammunitionSlot
	 */
	public int getAmmunitionSlot() {
		return ammunitionSlot;
	}

	/**
	 * @return the ammunition
	 */
	public List<Integer> getAmmunition() {
		return ammunition;
	}

	/**
	 * @return the attackSpeed
	 */
	public int getAttackSpeed() {
		return attackSpeed;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Gets the weapon type.
	 * @return The weapon type.
	 */
	public Weapon.WeaponType getWeaponType() {
		return Weapon.WeaponType.values()[type];
	}

	/**
	 * @return the dropAmmo
	 */
	public boolean isDropAmmo() {
		return dropAmmo;
	}
}