package plugin.skill.construction;


import core.game.node.object.GameObject;
import core.game.world.map.Region;
import core.game.world.map.RegionChunk;
import core.game.world.map.RegionManager;

/**
 * Represents the room properties.
 * @author Emperor
 * >ORDINAL BOUND<
 */
public enum RoomProperties {

	/**
	 * The parlour.
	 */
	PARLOUR(1000, 1, 0, 0, 7, Room.CHAMBER, new Hotspot(BuildHotspot.BOOKCASE, 0, 1),
			new Hotspot(BuildHotspot.WINDOW, 0, 2),
			new Hotspot(BuildHotspot.WINDOW, 0, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 0),
			new Hotspot(BuildHotspot.WINDOW, 2, 7),
			new Hotspot(BuildHotspot.WINDOW, 5, 0),
			new Hotspot(BuildHotspot.WINDOW, 5, 7),
			new Hotspot(BuildHotspot.WINDOW, 7, 2),
			new Hotspot(BuildHotspot.WINDOW, 7, 5),
			new Hotspot(BuildHotspot.CURTAINS, 2, 0),
			new Hotspot(BuildHotspot.CURTAINS, 0, 2),
			new Hotspot(BuildHotspot.CURTAINS, 0, 5),
			new Hotspot(BuildHotspot.CURTAINS, 2, 7),
			new Hotspot(BuildHotspot.CURTAINS, 5, 0),
			new Hotspot(BuildHotspot.CURTAINS, 5, 7),
			new Hotspot(BuildHotspot.CURTAINS, 7, 2),
			new Hotspot(BuildHotspot.CURTAINS, 7, 5),
			new Hotspot(BuildHotspot.RUG, 2, 2),
			new Hotspot(BuildHotspot.RUG2, 2, 3),
			new Hotspot(BuildHotspot.RUG2, 2, 4),
			new Hotspot(BuildHotspot.RUG, 2, 5),
			new Hotspot(BuildHotspot.RUG2, 3, 2),
			new Hotspot(BuildHotspot.RUG3, 3, 3),
			new Hotspot(BuildHotspot.RUG3, 3, 4),
			new Hotspot(BuildHotspot.RUG2, 3, 5),
			new Hotspot(BuildHotspot.RUG2, 4, 2),
			new Hotspot(BuildHotspot.RUG3, 4, 3),
			new Hotspot(BuildHotspot.RUG3, 4, 4),
			new Hotspot(BuildHotspot.RUG2, 4, 5),
			new Hotspot(BuildHotspot.RUG, 5, 2),
			new Hotspot(BuildHotspot.RUG2, 5, 3),
			new Hotspot(BuildHotspot.RUG2, 5, 4),
			new Hotspot(BuildHotspot.RUG, 5, 5),
			new Hotspot(BuildHotspot.CHAIRS_1, 2, 4),
			new Hotspot(BuildHotspot.FIREPLACE, 3, 7),
			new Hotspot(BuildHotspot.CHAIRS_3, 4, 3),
			new Hotspot(BuildHotspot.CHAIRS_2, 5, 4),
			new Hotspot(BuildHotspot.BOOKCASE, 7, 1)),


	/**
	 * The garden. (centrepiece has to be first!)
	 */
	GARDEN(1000, 1, 0, 0, 1, Room.LAND, new Hotspot(BuildHotspot.CENTREPIECE_1, 3, 3), 
			new Hotspot(BuildHotspot.BIG_PLANT_2, 0, 0), 
			new Hotspot(BuildHotspot.BIG_TREE_1, 1, 5), 
			new Hotspot(BuildHotspot.SMALL_PLANT_1, 3, 1), 
			new Hotspot(BuildHotspot.SMALL_PLANT_2, 4, 5), 
			new Hotspot(BuildHotspot.BIG_PLANT_1, 6, 0), 
			new Hotspot(BuildHotspot.TREE_1, 6, 6)),

	/**
	 * Woman's place.
	 */
	KITCHEN(5000, 5, 0, 2, 7, Room.CHAMBER, new Hotspot(BuildHotspot.CAT_BLANKET, 0, 0),
			new Hotspot(BuildHotspot.WINDOW, 0, 2),
			new Hotspot(BuildHotspot.WINDOW, 0, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 0),
			new Hotspot(BuildHotspot.WINDOW, 2, 7),
			new Hotspot(BuildHotspot.WINDOW, 5, 0),
			new Hotspot(BuildHotspot.WINDOW, 5, 7),
			new Hotspot(BuildHotspot.WINDOW, 7, 2),
			new Hotspot(BuildHotspot.WINDOW, 7, 5),
			new Hotspot(BuildHotspot.BARRELS, 0, 6),
			new Hotspot(BuildHotspot.KITCHEN_TABLE, 3, 3),
			new Hotspot(BuildHotspot.STOVE, 3, 7),
			new Hotspot(BuildHotspot.LARDER, 6, 0),
			new Hotspot(BuildHotspot.SINK, 7, 3),
			new Hotspot(BuildHotspot.SHELVES, 1, 7),
			new Hotspot(BuildHotspot.SHELVES, 6, 7),
			new Hotspot(BuildHotspot.SHELVES_2, 7, 6)),

	/**
	 * Dining room.
	 */
	DINING_ROOM(5000, 10, 0, 4, 7, Room.CHAMBER, new Hotspot(BuildHotspot.FIREPLACE_DINING, 3, 7),
			new Hotspot(BuildHotspot.WINDOW, 0, 2),
			new Hotspot(BuildHotspot.WINDOW, 0, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 0),
			new Hotspot(BuildHotspot.WINDOW, 5, 0),
			new Hotspot(BuildHotspot.WINDOW, 7, 2),
			new Hotspot(BuildHotspot.WINDOW, 7, 5),
			new Hotspot(BuildHotspot.DINING_CURTAINS, 0, 2),
			new Hotspot(BuildHotspot.DINING_CURTAINS, 0, 5),
			new Hotspot(BuildHotspot.DINING_CURTAINS, 2, 0),
			new Hotspot(BuildHotspot.DINING_CURTAINS, 5, 0),
			new Hotspot(BuildHotspot.DINING_CURTAINS, 7, 2),
			new Hotspot(BuildHotspot.DINING_CURTAINS, 7, 5),
			new Hotspot(BuildHotspot.WALL_DECORATION, 2, 7),
			new Hotspot(BuildHotspot.WALL_DECORATION, 5, 7),
			new Hotspot(BuildHotspot.DINING_BENCH_2, 2, 5),
			new Hotspot(BuildHotspot.DINING_BENCH_2, 3, 5),
			new Hotspot(BuildHotspot.DINING_BENCH_2, 4, 5),
			new Hotspot(BuildHotspot.DINING_BENCH_2, 5, 5),
			new Hotspot(BuildHotspot.DINING_BENCH_1, 2, 2),
			new Hotspot(BuildHotspot.DINING_BENCH_1, 3, 2),
			new Hotspot(BuildHotspot.DINING_BENCH_1, 4, 2),
			new Hotspot(BuildHotspot.DINING_BENCH_1, 5, 2),
			new Hotspot(BuildHotspot.ROPE_BELL_PULL, 0, 0),
			new Hotspot(BuildHotspot.DINING_TABLE, 2, 3)),

	/**
	 * Workshop.
	 */
	WORKSHOP(10000, 15, 0, 0, 5, Room.CHAMBER, new Hotspot(BuildHotspot.WORKBENCH, 3, 4),
			new Hotspot(BuildHotspot.WINDOW, 0, 2),
			new Hotspot(BuildHotspot.WINDOW, 0, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 0),
			new Hotspot(BuildHotspot.WINDOW, 5, 0),
			new Hotspot(BuildHotspot.WINDOW, 7, 2),
			new Hotspot(BuildHotspot.WINDOW, 7, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 7),
			new Hotspot(BuildHotspot.WINDOW, 5, 7),
			new Hotspot(BuildHotspot.REPAIR, 7, 3),
			new Hotspot(BuildHotspot.HERALDRY, 7, 6),
			new Hotspot(BuildHotspot.CRAFTING, 0, 3),
			new Hotspot(BuildHotspot.WORKBENCH, 3, 4),
			new Hotspot(BuildHotspot.TOOL4, 7, 1),
			new Hotspot(BuildHotspot.TOOL2, 6, 0),
			new Hotspot(BuildHotspot.TOOL1, 1, 0),
			new Hotspot(BuildHotspot.TOOL3, 0, 1),
			new Hotspot(BuildHotspot.TOOL5, 0, 6)),

	/**
	 * Bedroom.
	 */
	BEDROOM(10000, 20, 0, 6, 7, Room.CHAMBER, new Hotspot(BuildHotspot.BED, 3, 6), 
			new Hotspot(BuildHotspot.FIREPLACE2, 7, 3),
			new Hotspot(BuildHotspot.CLOCK, 7, 0),
			new Hotspot(BuildHotspot.DRESSER, 0, 7),
			new Hotspot(BuildHotspot.DRAWERS, 6, 7),
			new Hotspot(BuildHotspot.WINDOW, 0, 2),
			new Hotspot(BuildHotspot.WINDOW, 0, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 0),
			new Hotspot(BuildHotspot.WINDOW, 5, 0),
			new Hotspot(BuildHotspot.WINDOW, 7, 2),
			new Hotspot(BuildHotspot.WINDOW, 7, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 7),
			new Hotspot(BuildHotspot.WINDOW, 5, 7),
			new Hotspot(BuildHotspot.BEDROOM_CURTAINS, 0, 2),
			new Hotspot(BuildHotspot.BEDROOM_CURTAINS, 0, 5),
			new Hotspot(BuildHotspot.BEDROOM_CURTAINS, 2, 0),
			new Hotspot(BuildHotspot.BEDROOM_CURTAINS, 5, 0),
			new Hotspot(BuildHotspot.BEDROOM_CURTAINS, 7, 2),
			new Hotspot(BuildHotspot.BEDROOM_CURTAINS, 7, 5),
			new Hotspot(BuildHotspot.BEDROOM_CURTAINS, 2, 7),
			new Hotspot(BuildHotspot.BEDROOM_CURTAINS, 5, 7),
			new Hotspot(BuildHotspot.BEDROOM_RUG, 2, 2),
			new Hotspot(BuildHotspot.BEDROOM_RUG, 2, 3),
			new Hotspot(BuildHotspot.BEDROOM_RUG, 3, 2),
			new Hotspot(BuildHotspot.BEDROOM_RUG, 3, 3),
			new Hotspot(BuildHotspot.BEDROOM_RUG, 4, 2),
			new Hotspot(BuildHotspot.BEDROOM_RUG, 4, 3),
			new Hotspot(BuildHotspot.BEDROOM_RUG, 5, 2),
			new Hotspot(BuildHotspot.BEDROOM_RUG, 5, 3),
			new Hotspot(BuildHotspot.BEDROOM_RUG2, 2, 1),
			new Hotspot(BuildHotspot.BEDROOM_RUG2, 3, 1),
			new Hotspot(BuildHotspot.BEDROOM_RUG2, 4, 1),
			new Hotspot(BuildHotspot.BEDROOM_RUG2, 5, 1),
			new Hotspot(BuildHotspot.BEDROOM_RUG2, 2, 4),
			new Hotspot(BuildHotspot.BEDROOM_RUG2, 3, 4),
			new Hotspot(BuildHotspot.BEDROOM_RUG2, 4, 4),
			new Hotspot(BuildHotspot.BEDROOM_RUG2, 5, 4),
			new Hotspot(BuildHotspot.BEDROOM_RUG2, 6, 2),
			new Hotspot(BuildHotspot.BEDROOM_RUG2, 6, 3),
			new Hotspot(BuildHotspot.BEDROOM_RUG2, 1, 2),
			new Hotspot(BuildHotspot.BEDROOM_RUG2, 1, 3),
			new Hotspot(BuildHotspot.BEDROOM_RUG2, 3, 2),
			new Hotspot(BuildHotspot.BEDROOM_RUG2, 3, 5),
			new Hotspot(BuildHotspot.BEDROOM_RUG2, 4, 5),
			new Hotspot(BuildHotspot.BEDROOM_RUG2, 4, 2),
			new Hotspot(BuildHotspot.BEDROOM_RUG3, 1, 1),
			new Hotspot(BuildHotspot.BEDROOM_RUG3, 1, 4),
			new Hotspot(BuildHotspot.BEDROOM_RUG3, 6, 1),
			new Hotspot(BuildHotspot.BEDROOM_RUG3, 6, 4)),

	/**
	 * Skill hall room.
	 */
	SKILL_HALL(15000, 25, 0, 1, 6, Room.CHAMBER, new Hotspot(BuildHotspot.STAIRWAYS, 3, 3),
			new Hotspot(BuildHotspot.ARMOUR_SPACE, 2, 3),
			new Hotspot(BuildHotspot.ARMOUR_SPACE2, 5, 3),
			new Hotspot(BuildHotspot.HEAD_TROPHY, 6, 7),
			new Hotspot(BuildHotspot.RUNE_CASE, 0, 6),
			new Hotspot(BuildHotspot.FISHING_TROPHY, 1, 7),
			new Hotspot(BuildHotspot.WINDOW, 0, 2),
			new Hotspot(BuildHotspot.WINDOW, 0, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 0),
			new Hotspot(BuildHotspot.WINDOW, 5, 0),
			new Hotspot(BuildHotspot.WINDOW, 7, 2),
			new Hotspot(BuildHotspot.WINDOW, 7, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 7),
			new Hotspot(BuildHotspot.WINDOW, 5, 7),
			new Hotspot(BuildHotspot.HALL_RUG, 2, 2),
			new Hotspot(BuildHotspot.HALL_RUG, 3, 2),
			new Hotspot(BuildHotspot.HALL_RUG, 4, 2),
			new Hotspot(BuildHotspot.HALL_RUG, 5, 2),
			new Hotspot(BuildHotspot.HALL_RUG, 2, 3),
			new Hotspot(BuildHotspot.HALL_RUG, 3, 3),
			new Hotspot(BuildHotspot.HALL_RUG, 4, 3),
			new Hotspot(BuildHotspot.HALL_RUG, 5, 3),
			new Hotspot(BuildHotspot.HALL_RUG, 2, 4),
			new Hotspot(BuildHotspot.HALL_RUG, 3, 4),
			new Hotspot(BuildHotspot.HALL_RUG, 4, 4),
			new Hotspot(BuildHotspot.HALL_RUG, 5, 4),
			new Hotspot(BuildHotspot.HALL_RUG, 2, 5),
			new Hotspot(BuildHotspot.HALL_RUG, 3, 5),
			new Hotspot(BuildHotspot.HALL_RUG, 4, 5),
			new Hotspot(BuildHotspot.HALL_RUG, 5, 5),
			new Hotspot(BuildHotspot.HALL_RUG2, 1, 2),
			new Hotspot(BuildHotspot.HALL_RUG2, 1, 3),
			new Hotspot(BuildHotspot.HALL_RUG2, 1, 4),
			new Hotspot(BuildHotspot.HALL_RUG2, 1, 5),
			new Hotspot(BuildHotspot.HALL_RUG2, 6, 2),
			new Hotspot(BuildHotspot.HALL_RUG2, 6, 3),
			new Hotspot(BuildHotspot.HALL_RUG2, 6, 4),
			new Hotspot(BuildHotspot.HALL_RUG2, 6, 5),
			new Hotspot(BuildHotspot.HALL_RUG2, 2, 1),
			new Hotspot(BuildHotspot.HALL_RUG2, 3, 1),
			new Hotspot(BuildHotspot.HALL_RUG2, 4, 1),
			new Hotspot(BuildHotspot.HALL_RUG2, 5, 1),
			new Hotspot(BuildHotspot.HALL_RUG2, 2, 6),
			new Hotspot(BuildHotspot.HALL_RUG2, 3, 6),
			new Hotspot(BuildHotspot.HALL_RUG2, 4, 6),
			new Hotspot(BuildHotspot.HALL_RUG2, 5, 6),
			new Hotspot(BuildHotspot.HALL_RUG3, 1, 1),
			new Hotspot(BuildHotspot.HALL_RUG3, 1, 6),
			new Hotspot(BuildHotspot.HALL_RUG3, 6, 1),
			new Hotspot(BuildHotspot.HALL_RUG3, 6, 6)),

	/**
	 * Games room.
	 */
	GAMES_ROOM(25000, 30, 0, 5, 4, Room.CHAMBER,
			new Hotspot(BuildHotspot.RANGING_GAME, 1, 0),
			new Hotspot(BuildHotspot.ATTACK_STONE, 2, 4),
			new Hotspot(BuildHotspot.PRIZE_CHEST, 3, 7),
			new Hotspot(BuildHotspot.ELEMENTAL_BALANCE, 5, 4),
			new Hotspot(BuildHotspot.GAME_SPACE, 6, 0),
			new Hotspot(BuildHotspot.WINDOW, 0, 2),
			new Hotspot(BuildHotspot.WINDOW, 0, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 0),
			new Hotspot(BuildHotspot.WINDOW, 5, 0),
			new Hotspot(BuildHotspot.WINDOW, 7, 2),
			new Hotspot(BuildHotspot.WINDOW, 7, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 7),
			new Hotspot(BuildHotspot.WINDOW, 5, 7)),

	/**
	 * Combat room.
	 */
	COMBAT_ROOM(25000, 32, 0, 3, 4, Room.CHAMBER, new Hotspot(BuildHotspot.STORAGE_SPACE, 3, 7),
			new Hotspot(BuildHotspot.WALL_DECORATION2, 1, 7),
			new Hotspot(BuildHotspot.WALL_DECORATION2, 6, 7),
			new Hotspot(BuildHotspot.WINDOW, 0, 2),
			new Hotspot(BuildHotspot.WINDOW, 0, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 0),
			new Hotspot(BuildHotspot.WINDOW, 5, 0),
			new Hotspot(BuildHotspot.WINDOW, 7, 2),
			new Hotspot(BuildHotspot.WINDOW, 7, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 7),
			new Hotspot(BuildHotspot.WINDOW, 5, 7),
			new Hotspot(BuildHotspot.CR_CORNER, 6, 1),
			new Hotspot(BuildHotspot.CR_CORNER2, 1, 1),
			new Hotspot(BuildHotspot.CR_CORNER3, 1, 6),
			new Hotspot(BuildHotspot.CR_CORNER4, 6, 6),
			new Hotspot(BuildHotspot.CR_RING, 2, 1),
			new Hotspot(BuildHotspot.CR_RING, 3, 1),
			new Hotspot(BuildHotspot.CR_RING, 4, 1),
			new Hotspot(BuildHotspot.CR_RING2, 5, 1),
			new Hotspot(BuildHotspot.CR_RING, 1, 2),
			new Hotspot(BuildHotspot.CR_RING, 1, 3),
			new Hotspot(BuildHotspot.CR_RING, 1, 4),
			new Hotspot(BuildHotspot.CR_RING3, 1, 5),
			new Hotspot(BuildHotspot.CR_RING3, 2, 6),
			new Hotspot(BuildHotspot.CR_RING4, 3, 6),
			new Hotspot(BuildHotspot.CR_RING4, 4, 6),
			new Hotspot(BuildHotspot.CR_RING4, 5, 6),
			new Hotspot(BuildHotspot.CR_RING4, 6, 5),
			new Hotspot(BuildHotspot.CR_RING, 6, 4),
			new Hotspot(BuildHotspot.CR_RING, 6, 3),
			new Hotspot(BuildHotspot.CR_RING2, 6, 2),
			new Hotspot(BuildHotspot.CR_FLOOR7, 2, 2),
			new Hotspot(BuildHotspot.CR_FLOOR4, 2, 3),
			new Hotspot(BuildHotspot.CR_FLOOR4, 2, 4),
			new Hotspot(BuildHotspot.CR_FLOOR3, 2, 5),
			new Hotspot(BuildHotspot.CR_FLOOR6, 3, 2),
			new Hotspot(BuildHotspot.CR_FLOOR5, 3, 3),
			new Hotspot(BuildHotspot.CR_FLOOR5, 3, 4),
			new Hotspot(BuildHotspot.CR_FLOOR, 3, 5),
			new Hotspot(BuildHotspot.CR_FLOOR6, 4, 2),
			new Hotspot(BuildHotspot.CR_FLOOR5, 4, 3),
			new Hotspot(BuildHotspot.CR_FLOOR5, 4, 4),
			new Hotspot(BuildHotspot.CR_FLOOR, 4, 5),
			new Hotspot(BuildHotspot.CR_FLOOR8, 5, 2),
			new Hotspot(BuildHotspot.CR_FLOOR4, 5, 3),
			new Hotspot(BuildHotspot.CR_FLOOR4, 5, 4),
			new Hotspot(BuildHotspot.CR_FLOOR2, 5, 5),
			new Hotspot(BuildHotspot.CR_INVISIBLE_WALL2, 2, 4),
			new Hotspot(BuildHotspot.CR_INVISIBLE_WALL, 3, 5),
			new Hotspot(BuildHotspot.CR_INVISIBLE_WALL, 5, 3),
			new Hotspot(BuildHotspot.CR_INVISIBLE_WALL, 4, 2)),
	/**
	 * Quest trophy hall.
	 */
	QUEST_HALL(25000, 35, 0, 5, 6, Room.CHAMBER, new Hotspot(BuildHotspot.QUEST_STAIRWAYS, 3, 3),
			new Hotspot(BuildHotspot.MAP, 7, 1),
			new Hotspot(BuildHotspot.SWORD, 7, 6),
			new Hotspot(BuildHotspot.LANDSCAPE, 6, 7),
			new Hotspot(BuildHotspot.PORTRAIT, 1, 7),
			new Hotspot(BuildHotspot.GUILD_TROPHY, 0, 6),
			new Hotspot(BuildHotspot.BOOKCASE2, 0, 1),
			new Hotspot(BuildHotspot.WINDOW, 0, 2),
			new Hotspot(BuildHotspot.WINDOW, 0, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 0),
			new Hotspot(BuildHotspot.WINDOW, 5, 0),
			new Hotspot(BuildHotspot.WINDOW, 7, 2),
			new Hotspot(BuildHotspot.WINDOW, 7, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 7),
			new Hotspot(BuildHotspot.WINDOW, 5, 7),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 2, 2),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 3, 2),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 4, 2),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 5, 2),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 2, 3),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 3, 3),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 4, 3),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 5, 3),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 2, 4),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 3, 4),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 4, 4),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 5, 4),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 2, 5),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 3, 5),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 4, 5),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 5, 5),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 1, 2),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 1, 3),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 1, 4),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 1, 5),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 6, 2),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 6, 3),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 6, 4),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 6, 5),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 2, 1),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 3, 1),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 4, 1),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 5, 1),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 2, 6),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 3, 6),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 4, 6),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 5, 6),
			new Hotspot(BuildHotspot.Q_HALL_RUG3, 1, 1),
			new Hotspot(BuildHotspot.Q_HALL_RUG3, 1, 6),
			new Hotspot(BuildHotspot.Q_HALL_RUG3, 6, 1),
			new Hotspot(BuildHotspot.Q_HALL_RUG3, 6, 6)),

	/**
	 * Study.
	 */
/*	Menagerie(30000, 37, 0, 7, 2, Room.LAND,
			new Hotspot(BuildHotspot.PET_HOUSE, 1, 1),
			new Hotspot(BuildHotspot.PET_FEEDER, 5, 1),
			new Hotspot(BuildHotspot.OBELISK, 5, 5),
			new Hotspot(BuildHotspot.HABITAT_2, 1, 5),
			new Hotspot(BuildHotspot.HABITAT_1, 6, 6)),*/

	/**
	 * Study.
	 */
	STUDY_ROOM(50000, 40, 0, 4, 5, Room.CHAMBER, new Hotspot(BuildHotspot.GLOBE, 1, 4),
			new Hotspot(BuildHotspot.LECTERN, 2, 2),
			new Hotspot(BuildHotspot.CRYSTAL_BALL, 5, 4),
			new Hotspot(BuildHotspot.BOOKCASE3, 4, 7),
			new Hotspot(BuildHotspot.BOOKCASE3, 3, 7),
			new Hotspot(BuildHotspot.WALL_CHART, 1, 7),
			new Hotspot(BuildHotspot.WALL_CHART, 6, 7),
			new Hotspot(BuildHotspot.WALL_CHART, 7, 1),
			new Hotspot(BuildHotspot.WALL_CHART, 0, 1),
			new Hotspot(BuildHotspot.TELESCOPE, 5, 7),
			new Hotspot(BuildHotspot.WINDOW, 0, 2),
			new Hotspot(BuildHotspot.WINDOW, 0, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 0),
			new Hotspot(BuildHotspot.WINDOW, 5, 0),
			new Hotspot(BuildHotspot.WINDOW, 7, 2),
			new Hotspot(BuildHotspot.WINDOW, 7, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 7),
			new Hotspot(BuildHotspot.WINDOW, 5, 7)),

	/**
	 * Costume room.
	 */
	COSTUME_ROOM(50000, 42, 0, 6, 1, Room.CHAMBER, new Hotspot(BuildHotspot.WINDOW, 0, 2),
			new Hotspot(BuildHotspot.WINDOW, 0, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 0),
			new Hotspot(BuildHotspot.WINDOW, 5, 0),
			new Hotspot(BuildHotspot.WINDOW, 7, 2),
			new Hotspot(BuildHotspot.WINDOW, 7, 5),
			new Hotspot(BuildHotspot.TREASURE_CHEST, 0, 3),
			new Hotspot(BuildHotspot.ARMOUR_CASE, 2, 7),
			new Hotspot(BuildHotspot.MAGIC_WARDROBE, 3, 7),
			new Hotspot(BuildHotspot.CAPE_RACK, 6, 6),
			new Hotspot(BuildHotspot.TOY_BOX, 7, 3),
			new Hotspot(BuildHotspot.COSTUME_BOX, 3, 3)),

	/**
	 * Chapel room.
	 */
	CHAPEL(50000, 45, 0, 2, 5, Room.CHAMBER,
			new Hotspot(BuildHotspot.ALTAR, 3, 5),
			new Hotspot(BuildHotspot.STATUE, 7, 0),
			new Hotspot(BuildHotspot.STATUE, 0, 0),
			new Hotspot(BuildHotspot.ICON, 3, 7),
			new Hotspot(BuildHotspot.MUSICAL, 7, 3),
			new Hotspot(BuildHotspot.BURNERS, 1, 5),
			new Hotspot(BuildHotspot.BURNERS, 6, 5),
			new Hotspot(BuildHotspot.CHAPEL_RUG, 3, 1),
			new Hotspot(BuildHotspot.CHAPEL_RUG, 4, 1),
			new Hotspot(BuildHotspot.CHAPEL_RUG, 3, 2),
			new Hotspot(BuildHotspot.CHAPEL_RUG, 4, 2),
			new Hotspot(BuildHotspot.CHAPEL_RUG, 3, 3),
			new Hotspot(BuildHotspot.CHAPEL_RUG, 4, 3),
			new Hotspot(BuildHotspot.CHAPEL_RUG2, 3, 0),
			new Hotspot(BuildHotspot.CHAPEL_RUG2, 4, 0),
			new Hotspot(BuildHotspot.CHAPEL_RUG2, 3, 4),
			new Hotspot(BuildHotspot.CHAPEL_RUG2, 4, 4),
			new Hotspot(BuildHotspot.CHAPEL_WINDOW, 0, 2),
			new Hotspot(BuildHotspot.CHAPEL_WINDOW, 0, 5),
			new Hotspot(BuildHotspot.CHAPEL_WINDOW, 2, 7),
			new Hotspot(BuildHotspot.CHAPEL_WINDOW, 5, 7),
			new Hotspot(BuildHotspot.CHAPEL_WINDOW, 7, 5),
			new Hotspot(BuildHotspot.CHAPEL_WINDOW, 7, 2)),

	/**
	 * Portal chamber.
	 */
	PORTAL_CHAMBER(100000, 50, 0, 1, 4, Room.CHAMBER, new Hotspot(BuildHotspot.TELEPORT_FOCUS, 3, 3),
			new Hotspot(BuildHotspot.PORTAL1, 0, 3),
			new Hotspot(BuildHotspot.PORTAL2, 3, 7),
			new Hotspot(BuildHotspot.PORTAL3, 7, 3),
			new Hotspot(BuildHotspot.WINDOW, 0, 2),
			new Hotspot(BuildHotspot.WINDOW, 0, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 0),
			new Hotspot(BuildHotspot.WINDOW, 5, 0),
			new Hotspot(BuildHotspot.WINDOW, 7, 2),
			new Hotspot(BuildHotspot.WINDOW, 7, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 7),
			new Hotspot(BuildHotspot.WINDOW, 5, 7)),

	/**
	 * The formal garden.
	 * Centrepiece should be first!
	 */
	FORMAL_GARDEN(75000, 55, 0, 2, 1, Room.LAND, new Hotspot(BuildHotspot.CENTREPIECE_2, 3, 3),
			new Hotspot(BuildHotspot.FENCING, 0, 0),
			new Hotspot(BuildHotspot.FENCING, 1, 0),
			new Hotspot(BuildHotspot.FENCING, 2, 0),
			new Hotspot(BuildHotspot.FENCING, 5, 0),
			new Hotspot(BuildHotspot.FENCING, 6, 0),
			new Hotspot(BuildHotspot.FENCING, 7, 0),
			new Hotspot(BuildHotspot.FENCING, 7, 1),
			new Hotspot(BuildHotspot.FENCING, 7, 2),
			new Hotspot(BuildHotspot.FENCING, 7, 5),
			new Hotspot(BuildHotspot.FENCING, 7, 6),
			new Hotspot(BuildHotspot.FENCING, 7, 7),
			new Hotspot(BuildHotspot.FENCING, 6, 7),
			new Hotspot(BuildHotspot.FENCING, 5, 7),
			new Hotspot(BuildHotspot.FENCING, 2, 7),
			new Hotspot(BuildHotspot.FENCING, 1, 7),
			new Hotspot(BuildHotspot.FENCING, 0, 7),
			new Hotspot(BuildHotspot.FENCING, 0, 6),
			new Hotspot(BuildHotspot.FENCING, 0, 5),
			new Hotspot(BuildHotspot.FENCING, 0, 2),
			new Hotspot(BuildHotspot.FENCING, 0, 1),
			new Hotspot(BuildHotspot.HEDGE3, 0, 0),
			new Hotspot(BuildHotspot.HEDGE2, 1, 0),
			new Hotspot(BuildHotspot.HEDGE1, 2, 0),
			new Hotspot(BuildHotspot.HEDGE1, 5, 0),
			new Hotspot(BuildHotspot.HEDGE2, 6, 0),
			new Hotspot(BuildHotspot.HEDGE3, 7, 0),
			new Hotspot(BuildHotspot.HEDGE2, 7, 1),
			new Hotspot(BuildHotspot.HEDGE1, 7, 2),
			new Hotspot(BuildHotspot.HEDGE1, 7, 5),
			new Hotspot(BuildHotspot.HEDGE2, 7, 6),
			new Hotspot(BuildHotspot.HEDGE3, 7, 7),
			new Hotspot(BuildHotspot.HEDGE2, 6, 7),
			new Hotspot(BuildHotspot.HEDGE1, 5, 7),
			new Hotspot(BuildHotspot.HEDGE1, 2, 7),
			new Hotspot(BuildHotspot.HEDGE2, 1, 7),
			new Hotspot(BuildHotspot.HEDGE3, 0, 7),
			new Hotspot(BuildHotspot.HEDGE2, 0, 6),
			new Hotspot(BuildHotspot.HEDGE1, 0, 5),
			new Hotspot(BuildHotspot.HEDGE1, 0, 2),
			new Hotspot(BuildHotspot.HEDGE2, 0, 1),
			new Hotspot(BuildHotspot.SMALL_PLANT2, 2, 1),
			new Hotspot(BuildHotspot.SMALL_PLANT2, 1, 2),
			new Hotspot(BuildHotspot.SMALL_PLANT2, 5, 6),
			new Hotspot(BuildHotspot.SMALL_PLANT2, 6, 5),
			new Hotspot(BuildHotspot.SMALL_PLANT2, 2, 6),
			new Hotspot(BuildHotspot.SMALL_PLANT2, 1, 5),
			new Hotspot(BuildHotspot.BIG_PLANT2, 1, 1),
			new Hotspot(BuildHotspot.BIG_PLANT2, 6, 6),
			new Hotspot(BuildHotspot.BIG_PLANT2, 1, 6),
			new Hotspot(BuildHotspot.SMALL_PLANT1, 5, 1),
			new Hotspot(BuildHotspot.SMALL_PLANT1, 6, 2),
			new Hotspot(BuildHotspot.SMALL_PLANT1, 1, 5),
			new Hotspot(BuildHotspot.SMALL_PLANT1, 2, 1),
			new Hotspot(BuildHotspot.SMALL_PLANT1, 2, 6),
			new Hotspot(BuildHotspot.BIG_PLANT1, 1, 6),
			new Hotspot(BuildHotspot.BIG_PLANT1, 1, 1),
			new Hotspot(BuildHotspot.BIG_PLANT1, 6, 1)),

	/**
	 * Throne room.
	 */
	THRONE_ROOM(150000, 60, 0, 6, 5, Room.CHAMBER,
			new Hotspot(BuildHotspot.WINDOW, 0, 2),
			new Hotspot(BuildHotspot.WINDOW, 0, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 0),
			new Hotspot(BuildHotspot.WINDOW, 5, 0),
			new Hotspot(BuildHotspot.WINDOW, 7, 2),
			new Hotspot(BuildHotspot.WINDOW, 7, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 7),
			new Hotspot(BuildHotspot.WINDOW, 5, 7),
			new Hotspot(BuildHotspot.THRONE, 3, 6),
			new Hotspot(BuildHotspot.THRONE, 4, 6),
			new Hotspot(BuildHotspot.LEVER, 6, 6),
			new Hotspot(BuildHotspot.FLOOR, 3, 3),
			new Hotspot(BuildHotspot.FLOOR, 3, 4),
			new Hotspot(BuildHotspot.FLOOR, 4, 4),
			new Hotspot(BuildHotspot.FLOOR, 4, 3),
			new Hotspot(BuildHotspot.TRAPDOOR, 1, 6),
			new Hotspot(BuildHotspot.SEATING1, 0, 0),
			new Hotspot(BuildHotspot.SEATING1, 0, 1),
			new Hotspot(BuildHotspot.SEATING1, 0, 2),
			new Hotspot(BuildHotspot.SEATING1, 0, 3),
			new Hotspot(BuildHotspot.SEATING1, 0, 4),
			new Hotspot(BuildHotspot.SEATING1, 0, 5),
			new Hotspot(BuildHotspot.SEATING2, 7, 0),
			new Hotspot(BuildHotspot.SEATING2, 7, 1),
			new Hotspot(BuildHotspot.SEATING2, 7, 2),
			new Hotspot(BuildHotspot.SEATING2, 7, 3),
			new Hotspot(BuildHotspot.SEATING2, 7, 4),
			new Hotspot(BuildHotspot.SEATING2, 7, 5),
			new Hotspot(BuildHotspot.DECORATION, 3, 7),
			new Hotspot(BuildHotspot.DECORATION, 4, 7)),

	/**
	 * Oubliette (dungeon).
	 */
	OUBILETTE(150000, 65, 0, 6, 3, Room.DUNGEON,
			new Hotspot(BuildHotspot.FLOOR_CORNER, 2, 2),
			new Hotspot(BuildHotspot.FLOOR_CORNER, 5, 2),
			new Hotspot(BuildHotspot.FLOOR_CORNER, 5, 5),
			new Hotspot(BuildHotspot.FLOOR_CORNER, 2, 5),
			new Hotspot(BuildHotspot.FLOOR_SIDE, 3, 2),
			new Hotspot(BuildHotspot.FLOOR_SIDE, 4, 2),
			new Hotspot(BuildHotspot.FLOOR_SIDE, 2, 3),
			new Hotspot(BuildHotspot.FLOOR_SIDE, 2, 4),
			new Hotspot(BuildHotspot.FLOOR_SIDE, 5, 3),
			new Hotspot(BuildHotspot.FLOOR_SIDE, 5, 4),
			new Hotspot(BuildHotspot.FLOOR_SIDE, 3, 5),
			new Hotspot(BuildHotspot.FLOOR_SIDE, 4, 5),
			new Hotspot(BuildHotspot.FLOOR_MID, 3, 3),
			new Hotspot(BuildHotspot.FLOOR_MID, 3, 4),
			new Hotspot(BuildHotspot.FLOOR_MID, 4, 3),
			new Hotspot(BuildHotspot.FLOOR_MID, 4, 4),
			new Hotspot(BuildHotspot.OUBLIETTE_FLOOR, 2, 2),
			new Hotspot(BuildHotspot.OUBLIETTE_FLOOR, 5, 2),
			new Hotspot(BuildHotspot.OUBLIETTE_FLOOR, 5, 5),
			new Hotspot(BuildHotspot.OUBLIETTE_FLOOR, 2, 5),
			new Hotspot(BuildHotspot.OUBLIETTE_FLOOR, 3, 2),
			new Hotspot(BuildHotspot.OUBLIETTE_FLOOR, 4, 2),
			new Hotspot(BuildHotspot.OUBLIETTE_FLOOR, 2, 3),
			new Hotspot(BuildHotspot.OUBLIETTE_FLOOR, 2, 4),
			new Hotspot(BuildHotspot.OUBLIETTE_FLOOR, 5, 3),
			new Hotspot(BuildHotspot.OUBLIETTE_FLOOR, 5, 4),
			new Hotspot(BuildHotspot.OUBLIETTE_FLOOR, 3, 5),
			new Hotspot(BuildHotspot.OUBLIETTE_FLOOR, 4, 5),
			new Hotspot(BuildHotspot.OUBLIETTE_FLOOR, 3, 3),
			new Hotspot(BuildHotspot.OUBLIETTE_FLOOR, 3, 4),
			new Hotspot(BuildHotspot.OUBLIETTE_FLOOR_1, 4, 3),
			new Hotspot(BuildHotspot.OUBLIETTE_FLOOR, 4, 4),
			new Hotspot(BuildHotspot.GUARD, 0, 0),
			new Hotspot(BuildHotspot.DUNGEON_DECO, 0, 2),
			new Hotspot(BuildHotspot.DUNGEON_DECO, 2, 7),
			new Hotspot(BuildHotspot.DUNGEON_DECO, 7, 5),
			new Hotspot(BuildHotspot.DUNGEON_DECO, 5, 0),
			new Hotspot(BuildHotspot.OUBLIETTE_LIGHT, 2, 0),
			new Hotspot(BuildHotspot.OUBLIETTE_LIGHT, 7, 2),
			new Hotspot(BuildHotspot.OUBLIETTE_LIGHT, 5, 7),
			new Hotspot(BuildHotspot.OUBLIETTE_LIGHT, 0, 5),
			new Hotspot(BuildHotspot.LADDER, 1, 6),
			new Hotspot(BuildHotspot.PRISON, 2, 2),
			new Hotspot(BuildHotspot.PRISON, 2, 5),
			new Hotspot(BuildHotspot.PRISON, 2, 3),
			new Hotspot(BuildHotspot.PRISON, 2, 4),
			new Hotspot(BuildHotspot.PRISON, 5, 2),
			new Hotspot(BuildHotspot.PRISON, 5, 5),
			new Hotspot(BuildHotspot.PRISON, 3, 2),
			new Hotspot(BuildHotspot.PRISON, 4, 2),
			new Hotspot(BuildHotspot.PRISON, 5, 3),
			new Hotspot(BuildHotspot.PRISON, 5, 4),
			new Hotspot(BuildHotspot.PRISON, 4, 5),
			new Hotspot(BuildHotspot.PRISON_DOOR, 3, 5)),
	/**
	 * Dungeon corridor.
	 */
	DUNGEON_CORRIDOR(7500, 70, 0, 4, 3, Room.DUNGEON,
			new Hotspot(BuildHotspot.DUNGEON_DOOR_RIGHT, 3, 1),
			new Hotspot(BuildHotspot.DUNGEON_DOOR_LEFT, 4, 1),
			new Hotspot(BuildHotspot.DUNGEON_DOOR_LEFT2, 3, 6),
			new Hotspot(BuildHotspot.DUNGEON_DOOR_RIGHT2, 4, 6),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT, 3, 1),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT, 4, 1),
			new Hotspot(BuildHotspot.DUNGEON_TRAP2, 3, 2),
			new Hotspot(BuildHotspot.DUNGEON_TRAP2, 4, 2),
			new Hotspot(BuildHotspot.DUNGEON_GUARD, 3, 3),
			new Hotspot(BuildHotspot.DUNGEON_DECO, 4, 3),
			new Hotspot(BuildHotspot.DUNGEON_DECO, 3, 4),
			new Hotspot(BuildHotspot.DUNGEON_TRAP, 3, 5),
			new Hotspot(BuildHotspot.DUNGEON_TRAP, 4, 5),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT, 3, 6),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT, 4, 6)),

	/**
	 * Dungeon junction.
	 */
	DUNGEON_JUNCTION(7500, 70, 0, 0, 3, Room.DUNGEON,
			new Hotspot(BuildHotspot.DUNGEON_DOOR_RIGHT, 3, 1),
			new Hotspot(BuildHotspot.DUNGEON_DOOR_LEFT, 4, 1),
			new Hotspot(BuildHotspot.DUNGEON_DOOR_LEFT2, 3, 6),
			new Hotspot(BuildHotspot.DUNGEON_DOOR_RIGHT2, 4, 6),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT, 3, 1),
			new Hotspot(BuildHotspot.DUNGEON_TRAP2, 3, 2),
			new Hotspot(BuildHotspot.DUNGEON_TRAP2, 4, 2),
			new Hotspot(BuildHotspot.DUNGEON_GUARD, 3, 3),
			new Hotspot(BuildHotspot.DUNGEON_TRAP, 3, 5),
			new Hotspot(BuildHotspot.DUNGEON_TRAP, 4, 5),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT, 4, 6),
			new Hotspot(BuildHotspot.DUNGEON_DECO, 1, 3),
			new Hotspot(BuildHotspot.DUNGEON_DECO, 6, 4),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT, 1, 4),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT, 6, 3)),

	/**
	 * Dungeon stairs.
	 */
	DUNGEON_STAIRS(7500, 70, 0, 2, 3, Room.DUNGEON, 
			new Hotspot(BuildHotspot.STAIRWAYS_DUNGEON, 3, 3),
			new Hotspot(BuildHotspot.HALL_RUG, 3, 3),
			new Hotspot(BuildHotspot.HALL_RUG, 4, 3),
			new Hotspot(BuildHotspot.HALL_RUG, 3, 4),
			new Hotspot(BuildHotspot.HALL_RUG, 4, 4),
			new Hotspot(BuildHotspot.HALL_RUG2, 2, 3),
			new Hotspot(BuildHotspot.HALL_RUG2, 2, 4),
			new Hotspot(BuildHotspot.HALL_RUG2, 5, 3),
			new Hotspot(BuildHotspot.HALL_RUG2, 5, 4),
			new Hotspot(BuildHotspot.HALL_RUG2, 3, 2),
			new Hotspot(BuildHotspot.HALL_RUG2, 4, 2),
			new Hotspot(BuildHotspot.HALL_RUG2, 3, 5),
			new Hotspot(BuildHotspot.HALL_RUG2, 4, 5),
			new Hotspot(BuildHotspot.HALL_RUG3, 2, 2),
			new Hotspot(BuildHotspot.HALL_RUG3, 2, 5),
			new Hotspot(BuildHotspot.HALL_RUG3, 5, 2),
			new Hotspot(BuildHotspot.HALL_RUG3, 5, 5),
			new Hotspot(BuildHotspot.DUNGEON_DOOR_RIGHT, 3, 1),
			new Hotspot(BuildHotspot.DUNGEON_DOOR_LEFT, 4, 1),
			new Hotspot(BuildHotspot.DUNGEON_DOOR_LEFT2, 3, 6),
			new Hotspot(BuildHotspot.DUNGEON_DOOR_RIGHT2, 4, 6),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT2, 2, 1),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT2, 5, 1),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT2, 2, 6),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT2, 5, 6),
			new Hotspot(BuildHotspot.DUNGEON_GUARD3, 1, 1),
			new Hotspot(BuildHotspot.DUNGEON_GUARD2, 5, 5),
			new Hotspot(BuildHotspot.DUNGEON_DECO, 6, 1),
			new Hotspot(BuildHotspot.DUNGEON_DECO, 1, 6)),

	DUNGEON_PIT(10000, 70, 0, 5, 2, Room.DUNGEON,
			new Hotspot(BuildHotspot.DUNGEON_DOOR_RIGHT3, 3, 1),
			new Hotspot(BuildHotspot.DUNGEON_DOOR_LEFT3, 4, 1),
			new Hotspot(BuildHotspot.DUNGEON_DOOR_LEFT3, 3, 6),
			new Hotspot(BuildHotspot.DUNGEON_DOOR_RIGHT3, 4, 6),
			new Hotspot(BuildHotspot.DUNGEON_DOOR_RIGHT3, 1, 4),
			new Hotspot(BuildHotspot.DUNGEON_DOOR_LEFT3, 1, 3),
			new Hotspot(BuildHotspot.DUNGEON_DOOR_LEFT3, 6, 4),
			new Hotspot(BuildHotspot.DUNGEON_DOOR_RIGHT3, 6, 3),
			new Hotspot(BuildHotspot.DUNGEON_DECO, 5, 1),
			new Hotspot(BuildHotspot.DUNGEON_DECO, 1, 6),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT2, 2, 1),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT, 2, 6),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT, 5, 1),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT2, 5, 6),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT, 1, 2),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT2, 1, 5),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT2, 6, 2),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT, 6, 5),
			new Hotspot(BuildHotspot.DUNGEON_PIT_GUARD, 3, 3)),

	/**
	 * Treasure room.
	 */
	TREASURE_ROOM(250000, 75, 0, 7, 4, Room.DUNGEON,
			new Hotspot(BuildHotspot.DUNGEON_DOOR_RIGHT2, 3, 1),
			new Hotspot(BuildHotspot.DUNGEON_DOOR_LEFT2, 4, 1),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT, 2, 1),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT, 5, 1),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT, 1, 5),
			new Hotspot(BuildHotspot.DUNGEON_LIGHT, 6, 5),
			new Hotspot(BuildHotspot.DUNGEON_DECO, 1, 2),
			new Hotspot(BuildHotspot.DUNGEON_DECO, 6, 2),
			new Hotspot(BuildHotspot.MONSTER, 3, 3),
			new Hotspot(BuildHotspot.TREASURE_CHEST1, 2, 6),
			new Hotspot(BuildHotspot.WALL_DECORATION1, 3, 6),
			new Hotspot(BuildHotspot.WALL_DECORATION1, 4, 6)),

	/**
	 * The roof top with 2 exits.
	 */
	ROOF_2_EXIT(0, 0, 0, 1, 2, Room.ROOF),

	/**
	 * The roof top with 3 exits.
	 */
	ROOF_3_EXIT(0, 0, 0, 3, 2, Room.ROOF),

	/**
	 * The roof top with 4 exits.
	 */
	ROOF_4_EXIT(0, 0, 0, 5, 2, Room.ROOF),

	/**
	 * Skill hall (upstairs) room.
	 */
	SKILL_HALL_2(0, 25, 0, 3, 6, Room.CHAMBER, new Hotspot(BuildHotspot.STAIRS_DOWN, 3, 3),
			new Hotspot(BuildHotspot.ARMOUR_SPACE, 2, 3),
			new Hotspot(BuildHotspot.ARMOUR_SPACE2, 5, 3),
			new Hotspot(BuildHotspot.HEAD_TROPHY, 6, 7),
			new Hotspot(BuildHotspot.RUNE_CASE, 0, 6),
			new Hotspot(BuildHotspot.FISHING_TROPHY, 1, 7),
			new Hotspot(BuildHotspot.WINDOW, 0, 2),
			new Hotspot(BuildHotspot.WINDOW, 0, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 0),
			new Hotspot(BuildHotspot.WINDOW, 5, 0),
			new Hotspot(BuildHotspot.WINDOW, 7, 2),
			new Hotspot(BuildHotspot.WINDOW, 7, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 7),
			new Hotspot(BuildHotspot.WINDOW, 5, 7),
			new Hotspot(BuildHotspot.HALL_RUG, 2, 2),
			new Hotspot(BuildHotspot.HALL_RUG, 3, 2),
			new Hotspot(BuildHotspot.HALL_RUG, 4, 2),
			new Hotspot(BuildHotspot.HALL_RUG, 5, 2),
			new Hotspot(BuildHotspot.HALL_RUG, 2, 3),
			new Hotspot(BuildHotspot.HALL_RUG, 3, 3),
			new Hotspot(BuildHotspot.HALL_RUG, 4, 3),
			new Hotspot(BuildHotspot.HALL_RUG, 5, 3),
			new Hotspot(BuildHotspot.HALL_RUG, 2, 4),
			new Hotspot(BuildHotspot.HALL_RUG, 3, 4),
			new Hotspot(BuildHotspot.HALL_RUG, 4, 4),
			new Hotspot(BuildHotspot.HALL_RUG, 5, 4),
			new Hotspot(BuildHotspot.HALL_RUG, 2, 5),
			new Hotspot(BuildHotspot.HALL_RUG, 3, 5),
			new Hotspot(BuildHotspot.HALL_RUG, 4, 5),
			new Hotspot(BuildHotspot.HALL_RUG, 5, 5),
			new Hotspot(BuildHotspot.HALL_RUG2, 1, 2),
			new Hotspot(BuildHotspot.HALL_RUG2, 1, 3),
			new Hotspot(BuildHotspot.HALL_RUG2, 1, 4),
			new Hotspot(BuildHotspot.HALL_RUG2, 1, 5),
			new Hotspot(BuildHotspot.HALL_RUG2, 6, 2),
			new Hotspot(BuildHotspot.HALL_RUG2, 6, 3),
			new Hotspot(BuildHotspot.HALL_RUG2, 6, 4),
			new Hotspot(BuildHotspot.HALL_RUG2, 6, 5),
			new Hotspot(BuildHotspot.HALL_RUG2, 2, 1),
			new Hotspot(BuildHotspot.HALL_RUG2, 3, 1),
			new Hotspot(BuildHotspot.HALL_RUG2, 4, 1),
			new Hotspot(BuildHotspot.HALL_RUG2, 5, 1),
			new Hotspot(BuildHotspot.HALL_RUG2, 2, 6),
			new Hotspot(BuildHotspot.HALL_RUG2, 3, 6),
			new Hotspot(BuildHotspot.HALL_RUG2, 4, 6),
			new Hotspot(BuildHotspot.HALL_RUG2, 5, 6),
			new Hotspot(BuildHotspot.HALL_RUG3, 1, 1),
			new Hotspot(BuildHotspot.HALL_RUG3, 1, 6),
			new Hotspot(BuildHotspot.HALL_RUG3, 6, 1),
			new Hotspot(BuildHotspot.HALL_RUG3, 6, 6)),

	/**
	 * Quest trophy hall (upstairs).
	 */
	QUEST_HALL_2(25000, 35, 0, 7, 6, Room.CHAMBER, new Hotspot(BuildHotspot.STAIRS_DOWN2, 3, 3),
			new Hotspot(BuildHotspot.MAP, 7, 1),
			new Hotspot(BuildHotspot.SWORD, 7, 6),
			new Hotspot(BuildHotspot.LANDSCAPE, 6, 7),
			new Hotspot(BuildHotspot.PORTRAIT, 1, 7),
			new Hotspot(BuildHotspot.GUILD_TROPHY, 0, 6),
			new Hotspot(BuildHotspot.BOOKCASE2, 0, 1),
			new Hotspot(BuildHotspot.WINDOW, 0, 2),
			new Hotspot(BuildHotspot.WINDOW, 0, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 0),
			new Hotspot(BuildHotspot.WINDOW, 5, 0),
			new Hotspot(BuildHotspot.WINDOW, 7, 2),
			new Hotspot(BuildHotspot.WINDOW, 7, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 7),
			new Hotspot(BuildHotspot.WINDOW, 5, 7),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 2, 2),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 3, 2),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 4, 2),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 5, 2),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 2, 3),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 3, 3),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 4, 3),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 5, 3),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 2, 4),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 3, 4),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 4, 4),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 5, 4),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 2, 5),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 3, 5),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 4, 5),
			new Hotspot(BuildHotspot.Q_HALL_RUG, 5, 5),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 1, 2),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 1, 3),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 1, 4),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 1, 5),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 6, 2),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 6, 3),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 6, 4),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 6, 5),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 2, 1),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 3, 1),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 4, 1),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 5, 1),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 2, 6),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 3, 6),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 4, 6),
			new Hotspot(BuildHotspot.Q_HALL_RUG2, 5, 6),
			new Hotspot(BuildHotspot.Q_HALL_RUG3, 1, 1),
			new Hotspot(BuildHotspot.Q_HALL_RUG3, 1, 6),
			new Hotspot(BuildHotspot.Q_HALL_RUG3, 6, 1),
			new Hotspot(BuildHotspot.Q_HALL_RUG3, 6, 6)),
	;

	/**
	 * The amount this room costs.
	 */
	private final int cost;

	/**
	 * The level required.
	 */
	private final int level;

	/**
	 * The chunk plane.
	 */
	private final int z;

	/**
	 * The chunk x-offset.
	 */
	private final int chunkX;

	/**
	 * The chunk y-offset.
	 */
	private final int chunkY;

	/**
	 * If doors should be placed.
	 */
	private final int configuration;

	/**
	 * The hotspots in this room.
	 */
	private final Hotspot[] hotspots;

	/**
	 * Constructs a new {@code RoomProperties} {@code Object}.
	 * @param cost The cost of building this room.
	 * @param level The construction level required.
	 * @param z The chunk plane.
	 * @param chunkX The chunk x-offset.
	 * @param chunkY The chunk y-offset.
	 * @param hotspots The hotspots.
	 */
	private RoomProperties(int cost, int level, int z, int chunkX, int chunkY, int configuration, Hotspot...hotspots) {
		this.cost = cost;
		this.level = level;
		this.z = z;
		this.chunkX = chunkX;
		this.chunkY = chunkY;
		this.configuration = configuration;
		this.hotspots = hotspots;
	}
	
	/**
	 * Gets the name of the room.
	 * @return The name.
	 */
	public String getName() {
		return name().toLowerCase().replaceAll("_", " ").replaceAll("\\d","");
	}

	/**
	 * Gets the available exits for the room.
	 * @return The exits.
	 */
	public boolean[] getExits() {
		Region region = RegionManager.forId(7503);
		Region.load(region, true);
		RegionChunk chunk = region.getPlanes()[0].getRegionChunk(chunkX, chunkY);
		return new boolean[] { isExit(chunk, 7, 3), isExit(chunk, 3, 0), isExit(chunk, 0, 3), isExit(chunk, 3, 7) };
	}

	/**
	 * Checks if the object on the given chunk coordinates is a door.
	 * @return {@code True} if so.
	 */
	private boolean isExit(RegionChunk chunk, int x, int y) {
		for (GameObject object : chunk.getObjects(x, y)) {
			if (object != null && (object.getId() == 15313 || object.getId() == 15314 || object.getId() == 15317)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the room is a chamber inside the house (not a garden or roof).
	 * @return {@code True} if so.
	 */
	public boolean isChamber() {
		return !isLand() && !isRoof(); //&& !isDungeon();
	}

	/**
	 * Checks if this room is used for landscape (garden).
	 * @return {@code True} if so.
	 */
	public boolean isLand() {
		return (configuration & Room.LAND) != 0;
	}

	/**
	 * Checks if the room is used for roofs.
	 * @return {@code True} if so.
	 */
	public boolean isRoof() {
		return (configuration & Room.ROOF) != 0;
	}

	/**
	 * Checks if the room is used for dungeons.
	 * @return {@code True} if so.
	 */
	public boolean isDungeon() {
		return (configuration & Room.DUNGEON) != 0;
	}

	/**
	 * Gets the z.
	 * @return The z.
	 */
	public int getZ() {
		return z;
	}

	/**
	 * Gets the chunkX.
	 * @return The chunkX.
	 */
	public int getChunkX() {
		return chunkX;
	}

	/**
	 * Gets the chunkY.
	 * @return The chunkY.
	 */
	public int getChunkY() {
		return chunkY;
	}

	/**  
	 * Gets the hotspots.
	 * @return The hotspots.
	 */
	public Hotspot[] getHotspots() {
		return hotspots;
	}

	/**
	 * Gets the cost.
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Gets the level.
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

}