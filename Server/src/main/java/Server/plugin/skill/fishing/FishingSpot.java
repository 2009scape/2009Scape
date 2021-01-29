package plugin.skill.fishing;

import java.util.HashMap;

/**
 * Represents a fishing spot.
 * @author ceikry
 */
public enum FishingSpot {

	NET_BAIT(new int[] { 952, 316, 319, 320, 323, 325, 326, 327, 330, 332, 404, 1331, 2067, 2068, 2724, 4908, 5748, 5749, 7045 }, FishingOption.SMALL_NET, FishingOption.BAIT), 
	LURE_BAIT(new int[] { 309, 310, 311, 314, 315, 317, 318, 328, 329, 331, 403, 927, 1189, 1190, 3019 }, FishingOption.LURE, FishingOption.L_BAIT), 
	CAGE_HARPOON(new int[] { 312, 321, 324, 333, 405, 1332, 1399, 3804, 5470, 7046}, FishingOption.CAGE, FishingOption.HARPOON), 
	NET_HARPOON(new int[] { 313, 322, 334, 406, 1191, 1333, 1405, 1406, 3574, 3575, 5471, 7044 }, FishingOption.BIG_NET, FishingOption.N_HARPOON), 
	HARPOON_NET(new int[] { 3848, 3849 }, FishingOption.HARPOON, FishingOption.H_NET, FishingOption.BARB_HARPOON),
	CRAB_CAGE(new int[] {8665}, FishingOption.C_CAGE);

	public static HashMap<Integer,FishingSpot> spotMap = new HashMap<>();

	static{
		FishingSpot[] spots = values();
		int spotsLength = spots.length;
		for (int x = 0; x < spotsLength; x++) {
			FishingSpot spot = spots[x];
			int[] spotIds = spot.ids;
			int spotIdsLength = spotIds.length;
			for (int y = 0; y < spotIdsLength; y++) {
				spotMap.putIfAbsent(spotIds[y],spot);
			}
		}
	}

	public static FishingSpot forId(int npcId) {
		return spotMap.get(npcId);
	}


	/**
	 * The NPC ids.
	 */
	private final int[] ids;

	/**
	 * The fishing options.
	 */
	private FishingOption options[];

	/**
	 * Constructs a new {@code FishingSpot} {@code Object}.
	 * @param ids The NPC ids.
	 */
	private FishingSpot(int[] ids, FishingOption... options) {
		this.ids = ids;
		this.options = options;
	}

	public FishingOption getOptionByName(String name){
		for(FishingOption o : this.options){
			if(o.getName().equals(name)){return o;}
		}
		System.out.println("Unhandled fishing spot option, spot id: " + this.ids[0] + " desired fishing option: " + name);
		return FishingOption.SMALL_NET; //safe, default return.
	}

	/**
	 * Gets the ids.
	 * @return The ids.
	 */
	public int[] getIds() {
		return ids;
	}

	/**
	 * Gets the options.
	 * @return The options.
	 */
	public FishingOption[] getOptions() {
		return options;
	}

}