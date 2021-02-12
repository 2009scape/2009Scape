package core.game.content.global.action;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.world.map.Location;

import java.util.Arrays;
import java.util.HashMap;

public enum SpecialLadders implements LadderAchievementCheck {
    HAM_STORAGE_UP(new Location(2567,5185,0), new Location(3166,9623,0)),
    GEM_MINE(new Location(2821,2996,0), new Location(2838,9387,0)),
    GEM_MINE_UP(new Location(2838,9388,0), new Location(2820, 2996, 0)),
    BEAR_CAGE_UP(Location.create(3230, 9904, 0),Location.create(3231, 3504, 0)),
    BEAR_CAGE_DOWN(Location.create(3230,3508,0),Location.create(3229, 9904, 0)),
    GLARIAL_EXIT(Location.create(2556,9844,0),Location.create(2557,3444,0)),
    SWENSEN_DOWN(Location.create(2644, 3657, 0), Location.create(2631, 10006, 0)),
    SWENSEN_UP(Location.create(2665, 10037, 0),Location.create(2649, 3661, 0)),
    FOG_ENTER(Location.create(3240,3575,0),Location.create(1675,5599,0)),
    FOG_LEAVE(Location.create(1673,5598,0),Location.create(3242, 3574, 0)),
    INTRO_LEAVE(Location.create(2522, 4999, 0),Location.create(3230, 3240, 0)),

    DRAYNOR_SEWER_SOUTHEAST_DOWN(new Location(3118, 3244, 0), new Location(3118, 9643, 0)),
    DRAYNOR_SEWER_SOUTHEAST_UP(new Location(3118, 9643, 0), new Location(3118, 3243, 0)),
    DRAYNOR_SEWER_NORTHWEST_DOWN(new Location(3084, 3272, 0), new Location(3085, 9672, 0)),
    DRAYNOR_SEWER_NORTHWEST_UP(new Location(3084, 9672, 0), new Location(3084, 3271, 0)),

    PORT_SARIM_RAT_PITS_DOWN(new Location(3018,3232,0), new Location(2962,9650,0)) {
        @Override
        public void checkAchievement(Player player) {
            player.getAchievementDiaryManager().finishTask(player,DiaryType.FALADOR,1,11);
        }
    },
    PORT_SARIM_RAT_PITS_UP(new Location(2962,9651,0), new Location(3018,3231,0)),
    PHASMATYS_BAR_DOWN(new Location(3681,3498,0), new Location(3682,9961,0)),
    PHASMATYS_BAR_UP(new Location(3682,9962,0), new Location(3681,3497,0)),
    SEERS_VILLAGE_SPINNING_HOUSE_ROOFTOP_UP(new Location(2715,3472,1), new Location(2714,3472,3)) {
        @Override
        public void checkAchievement(Player player) {
            player.getAchievementDiaryManager().finishTask(player,DiaryType.SEERS_VILLAGE,1,3);
        }
    },
    SEERS_VILLAGE_SPINNING_HOUSE_ROOFTOP_DOWN(new Location(2715,3472,3), new Location(2714,3472,1)),
    ELEMENTAL_WORKSHOP_STAIRS_DOWN(Location.create(2710,3497, 0), Location.create(2713,9887, 0)),
    ELEMENTAL_WORKSHOP_STAIRS_UP(Location.create(2714,9887, 0), Location.create(2709,3498, 0)),
    TORMENTED_DEMONS_ENTER(Location.create(3353,3951,0),Location.create(3218, 9532, 2));

    private static HashMap<Location,Location> destinationMap = new HashMap<>();
    private static HashMap<Location,SpecialLadders> ladderMap = new HashMap<>();
    static {
        Arrays.stream(SpecialLadders.values()).forEach(entry -> {
            destinationMap.putIfAbsent(entry.ladderLoc,entry.destLoc);
        });
        Arrays.stream(SpecialLadders.values()).forEach(entry -> {
            ladderMap.putIfAbsent(entry.ladderLoc, entry);
        });
    }

    private Location ladderLoc,destLoc;
    SpecialLadders(Location ladderLoc, Location destLoc){
        this.ladderLoc = ladderLoc;
        this.destLoc = destLoc;
    }

    public static Location getDestination(Location loc){
        return destinationMap.get(loc);
    }
    public static SpecialLadders getSpecialLadder(Location loc) {
        return ladderMap.get(loc);
    }

}

interface LadderAchievementCheck {
    default void checkAchievement(Player player) {
        return;
    }
}