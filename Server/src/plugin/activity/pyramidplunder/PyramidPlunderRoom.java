package plugin.activity.pyramidplunder;

import org.crandor.game.world.map.Location;

//defines room properties for pyramid plunder
//@author ceik
public enum PyramidPlunderRoom {
    ROOM_1(1, 21, 0, -2,new Location(1927,4477)),
    ROOM_2(2, 31, 2, 0,new Location(1927,4453)),
    ROOM_3(3, 41, 0, 2,new Location(1943,4421)),
    ROOM_4(4, 51, 0, -2,new Location(1954,4477)),
    ROOM_5(5, 61, 0, 2,new Location(1974,4420)),
    ROOM_6(6, 71, 0, -2,new Location(1977,4471)),
    ROOM_7(7, 81, 0, 2,new Location(1927, 4424)),
    ROOM_8(8, 91, -2, 0,new Location(1965,4444));

    public int roomNum, reqLevel, spearX, spearY;
    Location entrance;
    PyramidPlunderRoom(int roomNum, int reqLevel, int spearX, int spearY, Location entrance){
        this.roomNum = roomNum;
        this.reqLevel = reqLevel;
        this.spearX = spearX;
        this.spearY = spearY;
        this.entrance = entrance;
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
