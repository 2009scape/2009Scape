//package org.crandor.game.content.global.consumable;
//
//import org.crandor.game.content.global.SkillcapePerks;
//import org.crandor.game.content.skill.Skills;
//import org.crandor.game.node.entity.player.Player;
//import org.crandor.game.node.entity.player.info.portal.Perks;
//import org.crandor.game.node.entity.player.link.audio.Audio;
//import org.crandor.game.node.item.Item;
//import org.crandor.game.node.object.GameObject;
//
//import java.security.SecureRandom;
//
///**
// * Represents the properties used when cooking the food.
// * @author 'Vexia
// * @date 22/12/2013
// */
//public class CookingProperties {
//
//	/**
//	 * Represents the secure random.
//	 */
//	private static final SecureRandom RANDOM = new SecureRandom();
//
//	/**
//	 * Represents the cooking sound.
//	 */
//	private static final Audio SOUND = new Audio(65, 10, 0);
//
//	/**
//	 * Represents the cooking gauntlets item.
//	 */
//	private static final Item GAUNTLETS = new Item(775);
//
//	/**
//	 * Represents the fail message to display when burning food.
//	 */
//	public static final String FAIL_MESSAGE = "Oops! You accidently burnt the @name..";
//
//	/**
//	 * Represents the level needed to cook the item.
//	 */
//	private final int level;
//
//	/**
//	 * Represents the cooking experience gained.
//	 */
//	private final double experience;
//
//	/**
//	 * Represents the level the food stops burning at on the fire.
//	 */
//	private final int fireBurnLevel;
//
//	/**
//	 * Represents the level the food stops burning at on the range.
//	 */
//	private final int rangeBurnLevel;
//
//	/**
//	 * Represents the level the food stops burning at when wearing gauntlets.
//	 */
//	private final int gauntletsBurnLevel;
//
//	/**
//	 * Represents if its a range only food (meaning only can be used on a range
//	 * not fire).
//	 */
//	private final boolean range;
//
//	/**
//	 * Represents if its an iron spit roast.
//	 */
//	private final boolean spit;
//
//	/**
//	 * Represents the messages displayed when cooking.
//	 */
//	private String[] messages = null;
//
//	/**
//	 * Constructs a new {@code CookingProperties} {@code Object}.
//	 * @param level the level.
//	 * @param experience the experience.
//	 * @param fireBurnLevel the burning level.
//	 * @param rangeBurnLevel the burning level on the range.
//	 * @param gauntletsBurnLevel the burning level with gauntlets.
//	 * @param range if the food can only be used on a range.
//	 * @param spit if its an iron spit item.
//	 */
//	public CookingProperties(int level, double experience, int burnLevel, int rangeBurnLevel, int gauntletsBurnLevel, boolean range, boolean spit, final String... messages) {
//		this.level = level;
//		this.experience = experience;
//		this.fireBurnLevel = burnLevel;
//		this.rangeBurnLevel = rangeBurnLevel;
//		this.gauntletsBurnLevel = gauntletsBurnLevel;
//		this.range = range;
//		this.spit = spit;
//		this.messages = messages;
//	}
//
//	/**
//	 * Constructs a new {@code CookingProperties} {@code Object}.
//	 * @param level the level.
//	 * @param experience the experience.
//	 * @param fireBurnLevel the burning level.
//	 * @param messages the messages
//	 */
//	public CookingProperties(final int level, final double experience, final int burnLevel, final String... messages) {
//		this(level, experience, burnLevel, burnLevel, burnLevel, false, false, messages);
//	}
//
//	/**
//	 * Constructs a new {@code CookingProperties} {@code Object}.
//	 * @param level the level.
//	 * @param experience the experience.
//	 * @param fireBurnLevel the burning level.
//	 * @param range if its a range item.
//	 * @param spit if its on an iron spit.
//	 * @param messages the messages
//	 */
//	public CookingProperties(final int level, final double experience, final int burnLevel, boolean range, boolean spit, final String... messages) {
//		this(level, experience, burnLevel, burnLevel, burnLevel, false, spit, messages);
//	}
//
//	/**
//	 * Constructs a new {@code CookingProperties} {@code Object}.
//	 * @param level the level.
//	 * @param experience the experience.
//	 * @param fireBurnLevel the burning level.
//	 * @param range if its only cookable on the range
//	 * @param messages the messages
//	 */
//	public CookingProperties(final int level, final double experience, final int burnLevel, final boolean range, final String... messages) {
//		this(level, experience, burnLevel, burnLevel, burnLevel, range, false, messages);
//	}
//
//	/**
//	 * Constructs a new {@code CookingProperties} {@code Object}.
//	 * @param level the level.
//	 * @param experience the experience.
//	 * @param fireBurnLevel the burning level.
//	 * @param rangeBurnLevel the range burning level.
//	 * @param gauntLetsBurnLevel the gauntletsBurn level.
//	 */
//	public CookingProperties(final int level, final double experience, final int burnLevel, final int rangeBurnLevel, final int gauntletsBurnLevel) {
//		this(level, experience, burnLevel, rangeBurnLevel, gauntletsBurnLevel, false, false);
//	}
//
//	/**
//	 * Method used to cook a food. This method can be overried to intercept the
//	 * way a food is cooked. A prime example of how this can be used is with the
//	 * sinew consumable food.
//	 * @param food the food we're cooking.
//	 * @param player the player.
//	 * @param object the object.
//	 * @return <code>True</code> if we we're succesfull.
//	 */
//	public boolean cook(final Food food, final Player player, final GameObject object) {
//		return cook(food, player, object, isBurned(player, object));
//	}
//
//	/**
//	 * Method used to cook a food. This method can be overried to intercept the
//	 * way a food is cooked. A prime example of how this can be used is with the
//	 * sinew consumable food.
//	 * @param food the food we're cooking.
//	 * @param player the player.
//	 * @param object the object.
//	 * @param if it's burned.
//	 * @return <code>True</code> if we we're succesfull.
//	 */
//	public boolean cook(final Food food, final Player player, final GameObject object, final boolean burned) {
//		if (player.getInventory().remove(food.getRaw())) {
//			if (!burned) {
//				Item item = food.getItem();
//				player.getInventory().add(item);
//				Perks.addDouble(player, item);
//			} else {
//				player.getInventory().add(food.getBurnt());
//			}
//			player.getSkills().addExperience(Skills.COOKING, burned ? 0 : getExperience(), true);
//			player.getPacketDispatch().sendMessage(getMessage(food, player, object, burned));
//			player.getAudioManager().send(SOUND);
//			return true;
//		}
//		return false;
//	}
//
//	/**
//	 * Gets the level.
//	 * @return The level.
//	 */
//	public int getLevel() {
//		return level;
//	}
//
//	/**
//	 * Gets the experience.
//	 * @return The experience.
//	 */
//	public double getExperience() {
//		return experience;
//	}
//
//	/**
//	 * Gets the fireBurnLevel.
//	 * @return The fireBurnLevel.
//	 */
//	public int getBurnLevel() {
//		return fireBurnLevel;
//	}
//
//	/**
//	 * Gets the range.
//	 * @return The range.
//	 */
//	public boolean isRange() {
//		return range;
//	}
//
//	/**
//	 * Gets the fireBurnLevel.
//	 * @return The fireBurnLevel.
//	 */
//	public int getFireBurnLevel() {
//		return fireBurnLevel;
//	}
//
//	/**
//	 * Gets the rangeBurnLevel.
//	 * @return The rangeBurnLevel.
//	 */
//	public int getRangeBurnLevel() {
//		return rangeBurnLevel;
//	}
//
//	/**
//	 * Gets the gauntletsBurnLevel.
//	 * @return The gauntletsBurnLevel.
//	 */
//	public int getGauntletsBurnLevel() {
//		return gauntletsBurnLevel;
//	}
//
//	/**
//	 * Gets the spit.
//	 * @return The spit.
//	 */
//	public boolean isSpit() {
//		return spit;
//	}
//
//	/**
//	 * Gets the burning level depicted by if the player has cooking gauntlets
//	 * on, or effected by the object they are interactign with.
//	 * @param player the player cooking.
//	 * @param object the object we check.
//	 * @return the level at which the player stops burning.
//	 */
//	private final int getBurnLevel(final Player player, final GameObject object) {
//		return player.getEquipment().containsItem(GAUNTLETS) ? gauntletsBurnLevel : object.getName().toLowerCase().equals("fire") ? fireBurnLevel : rangeBurnLevel;
//	}
//
//	/**
//	 * Gets the true/false value if the food is burned by the player.
//	 * @param player the player.
//	 * @param object the object.
//	 * @return <code>True</code> if the food is burned or not.
//	 */
//	public boolean isBurned(final Player player, final GameObject object) {
//		if (SkillcapePerks.hasSkillcapePerk(player, SkillcapePerks.COOKING)) {
//			return false;
//		}
//		if (player.getSkills().getLevel(Skills.COOKING) > getBurnLevel(player, object)) {
//			return false;
//		}
//		double burn_chance = 60.0 + (object.getName().equals("fire") ? 1.00 : 0) - (object.getId() == 114 ? 1.00 : 0);
//		if (player.getDetails().getShop().hasPerk(Perks.MASTER_CHEF)) {
//			burn_chance -= (burn_chance * 0.20);
//		}
//		double cook_level = (double) player.getSkills().getLevel(Skills.COOKING);
//		double lev_needed = (double) getLevel();
//		double burn_stop = (double) getBurnLevel(player, object);
//		double multi_a = (burn_stop - lev_needed);
//		double burn_dec = (burn_chance / multi_a);
//		double multi_b = (cook_level - lev_needed);
//		burn_chance -= (multi_b * burn_dec);
//		double randNum = RANDOM.nextDouble() * 100.0;
//		return burn_chance <= randNum ? false : true;
//	}
//
//	/**
//	 * Gets the message displayed when the food is cooked or burned.
//	 * @param food the food we're cooking/burning.
//	 * @param player the player.
//	 * @param object the object we're cooking on.
//	 * @param burned if the food is burned.
//	 * @return the message to display.
//	 */
//	public String getMessage(final Food food, final Player player, final GameObject object, final boolean burned) {
//		return messages != null && messages.length != 0 ? messages[burned ? 1 : 0].replace("@name", food.getItem().getName().toLowerCase()) : burned ? FAIL_MESSAGE.replace("@name", food.getItem().getName().toLowerCase()) + "." : "You successfully cook the " + food.getItem().getName().toLowerCase() + ".";
//	}
//}
