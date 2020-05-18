package org.crandor.game.content.global;

import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.repository.Repository;

/**
 * Represents the skilling pets obtained randomly.
 * @author Empathy
 *
 */
public enum SkillingPets {
	
	BABY_RED_CHINCHOMPA(new Item(14823), "Baby Chinchompa", Skills.HUNTER),
	BABY_GREY_CHINCHOMPA(new Item(14824), "Baby Chinchompa", Skills.HUNTER),
	BEAVER(new Item(14821), "Beaver", Skills.WOODCUTTING),
	GOLEM(new Item(14822), "Rock Golem", Skills.MINING),
	HERON(new Item(14827), "Heron", Skills.FISHING);

	/**
	 * The pet item drop.
	 */
	private final Item pet;
	
	/**
	 * The name.
	 */
	private final String name;

	/**
	 * The skill.
	 */
	private final int skill;
	
	/**
	 * Constructs a new {@code SkillingPets} object.
	 * @param skill The skill id.
	 * @param pet The pet item.
	 */
	SkillingPets(Item pet, String name, int skill) {
		this.pet = pet;
		this.name = name;
		this.skill = skill;
	}
	
	/**
	 * Checks the pet drop.
	 * @param player The player.
	 * @param pet The pet drop to check.
	 */
	public static void checkPetDrop(Player player, SkillingPets pet) {
		if (pet == null) {
			return;
		}
		int defaultChance = 15000;
		int newChance = (defaultChance / player.getSkills().getStaticLevel(pet.getSkill()) * 55);
		int outOf = (newChance > defaultChance ? defaultChance : newChance);
		int getChance = outOf;
		if (getChance != 1) {
			return;
		}
		if (player.hasItem(pet.getPet())) {
			return;
		}
		if (player.getFamiliarManager().hasFamiliar() && player.getInventory().isFull()) {
			return;
		}
		if (player.getFamiliarManager().hasFamiliar()) {
			if (player.getFamiliarManager().getFamiliar().getName().equalsIgnoreCase(pet.getName())) {
				return;
			}
			player.getInventory().add(pet.getPet());
			player.sendNotificationMessage("You feel something weird sneaking into your backpack.");
		} else {
			player.getFamiliarManager().summon(pet.getPet(), true);
			player.sendNotificationMessage("You have a funny feeling like you're being followed.");
		}
		Repository.sendNews(player.getUsername() + " has found a " + pet.getPet().getName() + "!");
	}
	 

	/**
	 * @return the pet
	 */
	public Item getPet() {
		return pet;
	}
	
	/**
	 * @return the pet name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the skill.
	 */
	public int getSkill() {
		return skill;
	}
}
