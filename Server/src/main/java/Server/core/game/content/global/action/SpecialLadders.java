package core.game.content.global.action;

import core.game.world.map.Location;

import java.util.Arrays;
import java.util.HashMap;

public enum SpecialLadders {
    GEM_MINE(new Location(2821,2996,0), new Location(2838,9387,0)),
    GEM_MINE_UP(new Location(2838,9388,0), new Location(2820, 2996, 0)),
    BEAR_CAGE_UP(Location.create(3230, 9904, 0),Location.create(3231, 3504, 0)),
    BEAR_CAGE_DOWN(Location.create(3230,3508,0),Location.create(3229, 9904, 0));


    public static HashMap<Location,Location> ladderMap = new HashMap<>();
    static {
        Arrays.stream(SpecialLadders.values()).forEach(entry -> {
            ladderMap.putIfAbsent(entry.ladderLoc,entry.destLoc);
        });
    }
    private Location ladderLoc,destLoc;
    SpecialLadders(Location ladderLoc, Location destLoc){
        this.ladderLoc = ladderLoc;
        this.destLoc = destLoc;
    }

    public static Location forLocation(Location loc){
        return ladderMap.get(loc);
    }
}
