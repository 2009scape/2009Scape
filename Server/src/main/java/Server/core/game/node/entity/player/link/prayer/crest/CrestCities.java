package core.game.node.entity.player.link.prayer.crest;

import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import plugin.quest.free.dragonslayer.DragonSlayer;
import plugin.quest.free.shieldofarrav.ShieldofArrav;
import plugin.quest.members.lostcity.LostCity;

/**
 * Represents the crest cities
 * @author jamix77
 *
 */
public enum CrestCities {

	ARRAV("Arrav",1042),
	ASGARNIA("Asgarnia",1042),
	DORGESHUUN("Dorgeshuun",1042),
	DRAGON("Crandor",1042),
	FAIRY("The Lost City",1042),
	GUTHIX("Guthix",1042),
	HAM("The H.A.M",1042),
	HORSE("Horses",1042),
	JOGRE("Jiggig",1042),
	KANDARIN("Kandarin",1042),
	MISTHALIN("Misthalin",1042),
	MONEY("Wealthy men and women",1042),
	SARADOMIN("Saradomin",1042),
	SKULL("Wrongdoers",1042),
	VARROCK("Varrock",1042),
	ZAMORAK("Zamorak",1042);

	/**
	 * The name of the crest city.
	 */
	private String name;

	/**
	 * The id of the city's shield.
	 */
	private int shieldId;

	/**
	 * 
	 * Constructs a new @{Code CrestCities} object.
	 * @param name
	 * @param shieldId
	 */
	private CrestCities(String name, int shieldId) {
		this.setName(name);
		this.setShieldId(shieldId);
	}

	/**
	 * Checks if the player is eligable for this crest.
	 * @param city
	 * @return
	 */
	public static boolean eligable(CrestCities city, Player player) {
		if (player.getInventory().getAmount(995) < 5000) {
			return false;
		}
		switch (city) {
		case ARRAV:
			return player.getQuestRepository().isComplete(ShieldofArrav.NAME);
		case DORGESHUUN:
			return player.getQuestRepository().isComplete("The Lost Tribe");
		case DRAGON:
			return player.getQuestRepository().isComplete(DragonSlayer.NAME);
		case FAIRY:
			return player.getQuestRepository().isComplete(LostCity.NAME);
		case GUTHIX:
		case SARADOMIN:
		case ZAMORAK:
			return player.getSkills().getStaticLevel(Skills.PRAYER) >= 70;
		case ASGARNIA:
		case HAM:
		case JOGRE:
		case KANDARIN:
		case MISTHALIN:
		case VARROCK:
			return true;
		case SKULL:
			return player.getSkullManager().isSkulled();
		case MONEY:
			return player.getInventory().getAmount(995) > 500000;
		case HORSE:
			return player.getInventory().containsItems(new Item(2520),new Item(2522),new Item(2524),new Item(2526));
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getShieldId() {
		return shieldId;
	}

	public void setShieldId(int shieldId) {
		this.shieldId = shieldId;
	}
}
