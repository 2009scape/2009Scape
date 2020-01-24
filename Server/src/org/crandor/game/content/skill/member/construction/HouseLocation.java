package org.crandor.game.content.skill.member.construction;


import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.world.map.Location;
import org.crandor.tools.StringUtils;

/**
 * The house locations.
 * @author Emperor
 */
@SuppressWarnings("all")
public enum HouseLocation {

	//Nowhere is used for players who do not have a house
	NOWHERE(-1, null, 0, 0),
	
	RIMMINGTON(15478, Location.create(2953, 3224, 0), 5000, 1),
	
	TAVERLY(15477, Location.create(2893, 3465, 0), 5000, 10),
	
	POLLNIVNEACH(15479, Location.create(3340, 3003, 0), 7500, 20),
	
	RELLEKKA(15480, Location.create(2670, 3631, 0), 10000, 30),
	
	BRIMHAVEN(15481, Location.create(2757, 3178, 0), 15000, 40),
	
	YANILLE(15482, Location.create(2544, 3096, 0), 25000, 50);

	//custom island thing that previous source users had.
	//WHITERIDGE(43832, Location.create(3965, 3546, 0), 100_000, 60);
	
	/**
	 * The portal object id for this location.
	 */
	private final int portalId;
	
	/**
	 * The exit location.
	 */
	private final Location exitLocation;
	
	/**
	 * The cost to move
	 */
	private final int cost;
	
	/**
	 * Level requirement
	 */
	private final int levelRequirement;
	
	/**
	 * The house options
	 */
	//public static TabbedOption HOUSE_OPTIONS = new TabbedOption("House Locations", "Select an Option");
	
	/**
	 * Sets up a page action tabbed option
	 */
	static {
		/*int index = 0;
		PageAction[] pageActions = new PageAction[5];
		for (HouseLocation hl : values()) {		
			if (hl == HouseLocation.NOWHERE || (hl == HouseLocation.WHITERIDGE && GameWorld.getSettings().isDeadmanMode()))
				continue;
			if (index > 4) {
				index = 0;
				HOUSE_OPTIONS.addPage(new Page(pageActions));
				pageActions = new PageAction[5];
			}
			pageActions[index++] = new PageAction(hl.name() + " - " + hl.cost + "gp") {
				@Override
				public boolean run(Player player) {
					if (player.getHouseManager().getLocation() == hl) {
						player.sendMessage("<col=FF0000>Your house already resides in " + hl.name() + "!");
						return true;
					}
					if (player.getSkills().hasLevel(Skills.CONSTRUCTION, hl.levelRequirement)) {
						if (player.getInventory().contains(995, hl.cost)) {
							player.getInventory().remove(new Item(995, hl.cost));
							player.getHouseManager().setLocation(hl);
							player.sendMessage("<col=006600>You have changed your house location to " + hl.name() + "!");
						} else {
							player.sendMessage("<col=FF0000>You need " + hl.cost + "gp in order to move there!");
						}
					} else {
						player.sendMessage("<col=FF0000>You need a construction level of " + hl.levelRequirement + " to move there!");
					}
					return true;						
				}
			};
		}
		if (pageActions[0] != null) {
			HOUSE_OPTIONS.addPage(new Page(pageActions));
		}*/
	}

	/**
	 * Checks if the player has the level.
	 *
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public boolean hasLevel(Player player) {
		return player.getSkills().getStaticLevel(Skills.CONSTRUCTION) >= levelRequirement;
	}
	
	/**
	 * Constructs a new {@code HouseLocation} {@code Object}
	 * @param portalId The portal id.
	 * @param exitLocation The exit location.
	 */
	private HouseLocation(int portalId, Location exitLocation, int cost, int levelRequirement) {
		this.portalId = portalId;
		this.exitLocation = exitLocation;
		this.cost = cost;
		this.levelRequirement = levelRequirement;
	}
	/**
	 * Gets the name formatted.
	 *
	 * @return the formatted name.
	 */
	public String getName() {
		return StringUtils.formatDisplayName(name().toLowerCase());
	}


	/**
	 * Gets the portalId.
	 * @return the portalId
	 */
	public int getPortalId() {
		return portalId;
	}

	/**
	 * Gets the exitLocation.
	 * @return the exitLocation
	 */
	public Location getExitLocation() {
		return exitLocation;
	}

	/**
	 * Gets the cost to move to here
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Gets the level requirement to move to here
	 * @return the level requirement
	 */
	public int getLevelRequirement() {
		return levelRequirement;
	}
	
	
}