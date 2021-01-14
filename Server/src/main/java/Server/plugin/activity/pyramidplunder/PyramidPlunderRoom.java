package plugin.activity.pyramidplunder;

import core.game.world.map.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//defines room properties for pyramid plunder
//@author ceik
public enum PyramidPlunderRoom {
    ROOM_1(1, 21, 0, -2,new Location(1927,4477), new Location[] {Location.create(1924, 4472, 0), Location.create(1931, 4472, 0), Location.create(1932, 4466, 0),Location.create(1923, 4466, 0)}),
    ROOM_2(2, 31, 2, 0,new Location(1927,4453), new Location[] {Location.create(1937, 4459, 0),Location.create(1941, 4456, 0),Location.create(1937, 4448, 0),Location.create(1932, 4450, 0)}),
    ROOM_3(3, 41, 0, 2,new Location(1943,4421), new Location[] {Location.create(1946, 4433, 0),Location.create(1955, 4430, 0),Location.create(1954, 4424, 0),Location.create(1949, 4422, 0)}),
    ROOM_4(4, 51, 0, -2,new Location(1954,4477), new Location[] {Location.create(1957, 4472, 0),Location.create(1960, 4468, 0),Location.create(1959, 4465, 0),Location.create(1949, 4465, 0)}),
    ROOM_5(5, 61, 0, 2,new Location(1974,4420), new Location[] {Location.create(1969, 4427, 0),Location.create(1967, 4432, 0),Location.create(1973, 4437, 0),Location.create(1979, 4430, 0)}),
    ROOM_6(6, 71, 0, -2,new Location(1977,4471), new Location[] {Location.create(1980, 4458, 0),Location.create(1976, 4453, 0),Location.create(1974, 4453, 0),Location.create(1969, 4460, 0)}),
    ROOM_7(7, 81, 0, 2,new Location(1927, 4424), new Location[] {Location.create(1923, 4432, 0),Location.create(1925, 4440, 0),Location.create(1929, 4440, 0),Location.create(1931, 4432, 0)}),
    ROOM_8(8, 91, -2, 0,new Location(1965,4444), new Location[] {Location.create(1952, 4447, 0),Location.create(1953, 4445, 0),Location.create(1957, 4444, 0),Location.create(1962, 4448, 0)});

    public int roomNum, reqLevel, spearX, spearY;
    Location entrance;
    public List<Location> doorLocations;
    PyramidPlunderRoom(int roomNum, int reqLevel, int spearX, int spearY, Location entrance, Location[] door_locations){
        this.roomNum = roomNum;
        this.reqLevel = reqLevel;
        this.spearX = spearX;
        this.spearY = spearY;
        this.entrance = entrance;
        this.doorLocations = Arrays.asList(door_locations);
    }

    public static PyramidPlunderRoom forRoomNum(int roomNum){
        for(PyramidPlunderRoom room : PyramidPlunderRoom.values()){
            if(room.roomNum == roomNum){
                return room;
            }
        }
        return null;
    }
}
