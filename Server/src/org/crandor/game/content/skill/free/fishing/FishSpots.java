package org.crandor.game.content.skill.free.fishing;

import org.crandor.game.world.map.Location;

import java.util.HashMap;

/**
 * Fishing spot locations
 * @author Ceikry
 */
public enum FishSpots {
	CATHERBY(Location.create(2844, 3429, 0), Location.create(2839, 3431, 0), new Location(2836, 3431, 0), new Location(2837, 3431, 0), new Location(2838, 3431, 0), new Location(2839, 3431, 0), new Location(2840, 3431, 0), new Location(2844, 3429, 0), new Location(2845, 3429, 0), new Location(2846, 3429, 0), new Location(2853, 3423, 0), new Location(2854, 3423, 0), new Location(2855, 3423, 0), new Location(2859, 3426, 0), new Location(2860, 3426, 0)), 
	ENTRANA(new Location(2842, 3359, 0), new Location(2843, 3359, 0), new Location(2844, 3359, 0), new Location(2847, 3361, 0), new Location(2848, 3361, 0), new Location(2849, 3361, 0), new Location(2850, 3361, 0)), 
	ENTRANA_PLATFORM(new Location(2879, 3334, 0), new Location(2879, 3335, 0), new Location(2879, 3338, 0), new Location(2879, 3339, 0), new Location(2877, 3342, 0), new Location(2876, 3342, 0), new Location(2875, 3342, 0), new Location(2875, 3331, 0), new Location(2876, 3331, 0), new Location(2877, 3331, 0)), 
	SEERS(new Location(2714, 3533, 0), new Location(2714, 3532, 0), new Location(2726, 3524, 0), new Location(2727, 3524, 0), new Location(2728, 3524, 0)),
	DRAYNOR(new Location(3085, 3230, 0), new Location(3086, 3227, 0)), 
	BARBARIAN(new Location(3110, 3432, 0), new Location(3110, 3433, 0), new Location(3110, 3434, 0), Location.create(3104, 3425, 0), Location.create(3104, 3424, 0)), 
	LUMBRIDGE(new Location(3239, 3243, 0), new Location(3239, 3241, 0), new Location(3239, 3242, 0), Location.create(3238, 3251, 0), Location.create(3238, 3252, 0), Location.create(3238, 3253, 0)), 
	LUMBRIDGE_SWAMP(Location.create(3242, 3143, 0), Location.create(3242, 3148, 0), Location.create(3241, 3148, 0), Location.create(3240, 3147, 0), Location.create(3240, 3146, 0), Location.create(3244, 3150, 0), Location.create(3245, 3152, 0), Location.create(3246, 3155, 0), Location.create(3246, 3156, 0), new Location(3239, 3241, 0)), 
	LUMBRIDGE_MARSH(Location.create(3169, 3266, 0), Location.create(3168, 3266, 0), Location.create(3174, 3274, 0), Location.create(3173, 3274, 0)), 
	ALKHARID(new Location(3267, 3148, 0), new Location(3268, 3147, 0), new Location(3276, 3140, 0), new Location(3275, 3140, 0)), 
	FISHING_GUILD(new Location(2612, 3411, 0), new Location(2602, 3423, 0), new Location(2602, 3411, 0), new Location(2602, 3412, 0), new Location(2602, 3414, 0), new Location(2602, 3415, 0), new Location(2603, 3417, 0), new Location(2604, 3417, 0), new Location(2605, 3416, 0), new Location(2606, 3416, 0), new Location(2607, 3416, 0), new Location(2608, 3416, 0), new Location(2609, 3416, 0), new Location(2610, 3416, 0), new Location(2611, 3416, 0), new Location(2612, 3415, 0), new Location(2612, 3414, 0), new Location(2607, 3410, 0), new Location(2606, 3410, 0), new Location(2605, 3410, 0), new Location(2599, 3419, 0), new Location(2600, 3419, 0), new Location(2602, 3419, 0), new Location(2603, 3419, 0), new Location(2604, 3419, 0), new Location(2605, 3420, 0), new Location(2605, 3421, 0), new Location(2604, 3422, 0), new Location(2603, 3422, 0), new Location(2602, 3422, 0), new Location(2601, 3422, 0), new Location(2605, 3424, 0), new Location(2605, 3425, 0), new Location(2602, 3426, 0), new Location(2603, 3426, 0), new Location(2604, 3426, 0)),
	GNOME(new Location(2388, 3424, 0), new Location(2390, 3422, 0), new Location(2391, 3421, 0), new Location(2382, 3413, 0), new Location(2382, 3414, 0), new Location(2382, 3415, 0)), CASTLEWAR(new Location(2465, 3156, 0), new Location(2464, 3155, 0), new Location(2468, 3157, 0), new Location(2469, 3157, 0), new Location(2471, 3158, 0), new Location(2472, 3156, 0), new Location(2462, 3145, 0), new Location(2461, 3150, 0), new Location(2461, 3151, 0)), SHILO(new Location(2460, 3144, 0), new Location(2461, 3150, 0), new Location(2461, 3151, 0), new Location(2853, 2977, 0), new Location(2854, 2977, 0), new Location(2855, 2977, 0), new Location(2856, 2977, 0), new Location(2858, 2976, 0), new Location(2859, 2976, 0), new Location(2860, 2976, 0), new Location(2863, 2975, 0), new Location(2863, 2975, 0), new Location(2864, 2975, 0), new Location(2865, 2972, 0), new Location(2860, 2972, 0), new Location(2855, 2974, 0), new Location(2852, 2973, 0)), KARAMJA(new Location(2923, 3178, 0), new Location(2923, 3179, 0), new Location(2923, 3180, 0), new Location(2924, 3181, 0), new Location(2925, 3181, 0), new Location(2926, 3180, 0), new Location(2926, 3179, 0), new Location(2926, 3178, 0), new Location(2926, 3177, 0), new Location(2926, 3176, 0)), DONATOR_ISLAND(Location.create(159, 41, 0), Location.create(160, 41, 0), Location.create(161, 41, 0), Location.create(162, 41, 0), Location.create(162, 30, 0)), 
	PISCATORIS(Location.create(2341, 3702, 0), Location.create(2342, 3702, 0), Location.create(2343, 3702, 0), Location.create(2344, 3702, 0), Location.create(2345, 3702, 0), Location.create(2346, 3702, 0), Location.create(2347, 3702, 0), Location.create(2349, 3702, 0), Location.create(2352, 3703, 0)),
	TUTORIAL_ISLAND(Location.create(3101,3092, 0)),
	WILDERNESS_ARENA(Location.create(3187, 3927, 0),Location.create(3185, 3926, 0),Location.create(3183, 3926, 0), Location.create(3181, 3926, 0));

	FishSpots(final Location... locations) {
		this.locations = locations;
	}

	private final Location[] locations;

	public Location[] getLocations() {
		return locations;
	}

	public static HashMap<Location,FishSpots> locationMap = new HashMap<>();

	static{
		FishSpots[] spots = values();
		int spotsLength = spots.length;
		for(int x = 0; x < spotsLength; x++){
			Location[] locations = spots[x].locations;
			int locationsLength = locations.length;
			for(int y = 0; y < locationsLength; y++){
				locationMap.putIfAbsent(locations[y],spots[x]);
			}
		}
	}

	//statically gets the fishing spot (loops suck btw)
	public static FishSpots forLocation(Location loc) {
		return locationMap.get(loc);
	}
}