package plugin.skill.smithing;

import core.game.node.entity.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Emperor
 * @author 'Vexia
 */
public enum Bars {

	/**
	 * Bronze Dagger
	 */
	BRONZE_DAGGER(BarType.BRONZE, SmithingType.TYPE_DAGGER, 1205, 1),

	/**
	 * Bronze Axe
	 */
	BRONZE_AXE(BarType.BRONZE, SmithingType.TYPE_AXE, 1351, 1),

	/**
	 * Bronze Mace
	 */
	BRONZE_MACE(BarType.BRONZE, SmithingType.TYPE_MACE, 1422, 2),

	/**
	 * Bronze Medium helm
	 */
	BRONZE_MED_HELM(BarType.BRONZE, SmithingType.TYPE_MEDIUM_HELM, 1139, 3),

	/**
	 * Bronze Crossbow Bolt
	 */
	BRONZE_CROSSBOW_BOLT(BarType.BRONZE, SmithingType.TYPE_CROSSBOW_BOLT, 9375, 3),

	/**
	 * Bronze Sword
	 */
	BRONZE_SWORD(BarType.BRONZE, SmithingType.TYPE_SWORD, 1277, 4),

	/**
	 * Bronze Dart Tips
	 */
	BRONZE_DART_TIPS(BarType.BRONZE, SmithingType.TYPE_DART_TIP, 819, 4),

	/**
	 * Bronze Nails
	 */
	BRONZE_NAILS(BarType.BRONZE, SmithingType.TYPE_NAIL, 4819, 4),

	/**
	 * Bronze Wire
	 */
	BRONZE_WIRE(BarType.BRONZE, SmithingType.TYPE_WIRE, 1794, 4),

	/**
	 * Bronze Arrow Tips
	 */
	BRONZE_ARROW_TIPS(BarType.BRONZE, SmithingType.TYPE_ARROW_TIP, 39, 5),

	/**
	 * Bronze Scimitar
	 */
	BRONZE_SCIMITAR(BarType.BRONZE, SmithingType.TYPE_SCIMITAR, 1321, 5),

	/**
	 * Bronze Crossbow Limbs
	 */
	BRONZE_CROSSBOW_LIMBS(BarType.BRONZE, SmithingType.TYPE_CROSSBOW_LIMB, 9420, 6),

	/**
	 * Bronze longsword
	 */
	BRONZE_LONGSWORD(BarType.BRONZE, SmithingType.TYPE_LONGSWORD, 1291, 6),

	/**
	 * Bronze ThrowingKnife
	 */
	BRONZE_THROWINGKNFIE(BarType.BRONZE, SmithingType.TYPE_THROWING_KNIFE, 864, 7),

	/**
	 * Bronze Full helmet
	 */
	BRONZE_FULL_HELM(BarType.BRONZE, SmithingType.TYPE_FULL_HELM, 1155, 7),

	/**
	 * Bronze Square Shield
	 */
	BRONZE_SQUARE_SHIELD(BarType.BRONZE, SmithingType.TYPE_SQUARE_SHIELD, 1173, 8),

	/**
	 * Bronze Warhammer
	 */
	BRONZE_WAR_HAMMER(BarType.BRONZE, SmithingType.TYPE_WARHAMMER, 1337, 9),

	/**
	 * Bronze BattleAxe
	 */
	BRONZE_BATTLEAXE(BarType.BRONZE, SmithingType.TYPE_BATTLE_AXE, 1375, 10),

	/**
	 * Bronze ChainBody
	 */
	BRONZE_CHAINBODY(BarType.BRONZE, SmithingType.TYPE_CHAINBODY, 1103, 11),

	/**
	 * Bronze KitShield
	 */
	BRONZE_KITESHIELD(BarType.BRONZE, SmithingType.TYPE_KITE_SHIELD, 1189, 12),

	/**
	 * Bronze Claws
	 */
	BRONZE_CLAWS(BarType.BRONZE, SmithingType.TYPE_CLAWS, 3095, 13),

	/**
	 * Bronze 2h
	 */
	BRONZE_TWO_HANDED(BarType.BRONZE, SmithingType.TYPE_TWO_HAND_SWORD, 1307, 14),

	/**
	 * Bronze PlateSkirt
	 */
	BRONZE_PLATE_SKIRT(BarType.BRONZE, SmithingType.TYPE_PLATE_SKIRT, 1087, 16),

	/**
	 * Bronze PlateLegs
	 */
	BRONZE_PLATELEGS(BarType.BRONZE, SmithingType.TYPE_PLATELEG, 1075, 16),

	/**
	 * Bronze PlateBody
	 */
	BRONZE_PLATEBODY(BarType.BRONZE, SmithingType.TYPE_PLATEBODY, 1117, 18),

	/**
	 * Bronze Pickaxe
	 */
	BRONZE_PICKAXE(BarType.BRONZE, SmithingType.TYPE_PICKAXE, 1265, 5),

	/**
	 * Iron Dagger
	 */
	IRON_DAGGER(BarType.IRON, SmithingType.TYPE_DAGGER, 1203, 15),

	/**
	 * Iron Hatchet
	 */
	IRON_AXE(BarType.IRON, SmithingType.TYPE_AXE, 1349, 16),

	/**
	 * Iron Mace
	 */
	IRON_MACE(BarType.IRON, SmithingType.TYPE_MACE, 1420, 17),

	/**
	 * Iron Med Helm
	 */
	IRON_MED_HELM(BarType.IRON, SmithingType.TYPE_MEDIUM_HELM, 1137, 18),

	/**
	 * Iron Bolt
	 */
	IRON_BOLT(BarType.IRON, SmithingType.TYPE_CROSSBOW_BOLT, 9377, 18),

	/**
	 * Iron Sword
	 */
	IRON_SWORD(BarType.IRON, SmithingType.TYPE_SWORD, 1279, 19),

	/**
	 * Iron Dart Tips
	 */
	IRON_DART_TIPS(BarType.IRON, SmithingType.TYPE_DART_TIP, 820, 19),

	/**
	 * Iron Nails
	 */
	IRON_NAILS(BarType.IRON, SmithingType.TYPE_NAIL, 4820, 19),

	/**
	 * Iron Split
	 */
	IRON_SPIT(BarType.IRON, SmithingType.TYPE_SPIT_IRON, 7225, 16),

	/**
	 * Iron Arrow Tips
	 */
	IRON_ARROW_TIPS(BarType.IRON, SmithingType.TYPE_ARROW_TIP, 40, 20),

	/**
	 * Iron Scimitar
	 */
	IRON_SCIMITAR(BarType.IRON, SmithingType.TYPE_SCIMITAR, 1323, 20),

	/**
	 * Iron Crossbow Limbs
	 */
	IRON_CROSSBOW_LimbS(BarType.IRON, SmithingType.TYPE_CROSSBOW_LIMB, 9423, 23),

	/**
	 * Iron LongSword
	 */
	IRON_LONGSWORD(BarType.IRON, SmithingType.TYPE_LONGSWORD, 1293, 21),

	/**
	 * Iron Knife
	 */
	IRON_KNIFE(BarType.IRON, SmithingType.TYPE_THROWING_KNIFE, 863, 22),

	/**
	 * Iron Full Helm
	 */
	IRON_FULL_HELM(BarType.IRON, SmithingType.TYPE_FULL_HELM, 1153, 22),

	/**
	 * Iron Square Shield
	 */
	IRON_SQUARE_SHIELD(BarType.IRON, SmithingType.TYPE_SQUARE_SHIELD, 1175, 23),

	/**
	 * Oil Lantern Frame
	 */
	OIL_LANTERN_FRAME(BarType.IRON, SmithingType.TYPE_OIL_LANTERN, 4540, 26),
	/**
	 * Iron WarHammer
	 */
	IRON_WARHAMMER(BarType.IRON, SmithingType.TYPE_WARHAMMER, 1335, 24),

	/**
	 * Iron Battleaxe
	 */
	IRON_BATTLEAXE(BarType.IRON, SmithingType.TYPE_BATTLE_AXE, 1363, 25),

	/**
	 * Iron ChainBody
	 */
	IRON_CHAINBODY(BarType.IRON, SmithingType.TYPE_CHAINBODY, 1101, 26),

	/**
	 * Iron Kite Shield
	 */
	IRON_KITE_SHIELD(BarType.IRON, SmithingType.TYPE_KITE_SHIELD, 1191, 27),

	/**
	 * Iron Claws
	 */
	IRON_CLAWS(BarType.IRON, SmithingType.TYPE_CLAWS, 3096, 28),

	/**
	 * Iron 2H
	 */
	IRON_TWO_HANDED_SWORD(BarType.IRON, SmithingType.TYPE_TWO_HAND_SWORD, 1309, 29),

	/**
	 * Iron PlateSkirt
	 */
	IRON_PLATESKIRT(BarType.IRON, SmithingType.TYPE_PLATE_SKIRT, 1081, 31),

	/**
	 * Iron PlateLegs
	 */
	IRON_PLATELEGS(BarType.IRON, SmithingType.TYPE_PLATELEG, 1067, 31),

	/**
	 * Iron PlateBody
	 */
	IRON_PLATEBODY(BarType.IRON, SmithingType.TYPE_PLATEBODY, 1115, 33),

	/**
	 * Iron PickAxe
	 */
	IRON_PICKAXE(BarType.IRON, SmithingType.TYPE_PICKAXE, 1267, 20),

	/**
	 * Steel Dagger
	 */
	STEEL_DAGGER(BarType.STEEL, SmithingType.TYPE_DAGGER, 1207, 30),

	/**
	 * Steel Axe
	 */
	STEEL_AXE(BarType.STEEL, SmithingType.TYPE_AXE, 1353, 31),

	/**
	 * Steel Mace
	 */
	STEEL_MACE(BarType.STEEL, SmithingType.TYPE_MACE, 1424, 32),

	/**
	 * Steel Medium Helm
	 */
	STEEL_MED_HELM(BarType.STEEL, SmithingType.TYPE_MEDIUM_HELM, 1141, 33),

	/**
	 * Steel CrossBow bolts
	 */
	STEEL_CROSSBOW_BOLT(BarType.STEEL, SmithingType.TYPE_CROSSBOW_BOLT, 9378, 33),

	/**
	 * Steel Sword
	 */
	STEEL_SWORD(BarType.STEEL, SmithingType.TYPE_SWORD, 1281, 34),

	/**
	 * Steel Dart Tips
	 */
	STEEL_DART_TIPS(BarType.STEEL, SmithingType.TYPE_DART_TIP, 821, 34),

	/**
	 * Steel Nails
	 */
	STEEL_NAILS(BarType.STEEL, SmithingType.TYPE_NAIL, 1539, 34),

	/**
	 * Steel ArrowTips
	 */
	STEEL_ARROW_TIPS(BarType.STEEL, SmithingType.TYPE_ARROW_TIP, 41, 35),

	/**
	 * Steel Scimitar
	 */
	STEEL_SCIMITAR(BarType.STEEL, SmithingType.TYPE_SCIMITAR, 1325, 35),

	/**
	 * Steel Crossbow Limbs
	 */
	STEEL_CROSSBOW_LIMBS(BarType.STEEL, SmithingType.TYPE_CROSSBOW_LIMB, 9425, 36),

	/**
	 * Steel LongSword
	 */
	STEEL_LONGSWORD(BarType.STEEL, SmithingType.TYPE_LONGSWORD, 1295, 36),

	/**
	 * Steel Knife
	 */
	STEEL_THROWING_KNIFE(BarType.STEEL, SmithingType.TYPE_THROWING_KNIFE, 865, 37),

	/**
	 * Steel Full Helm
	 */
	STEEL_FULL_HELM(BarType.STEEL, SmithingType.TYPE_FULL_HELM, 1157, 37),

	/**
	 * Steel Studs
	 */
	STEEL_STUDS(BarType.STEEL, SmithingType.TYPE_STUDS, 2370, 36),

	/**
	 * Steel Square Shield
	 */
	STEEL_SQUARE_SHIELD(BarType.STEEL, SmithingType.TYPE_SQUARE_SHIELD, 1177, 38),

	/**
	 * Steel Lantern
	 */
	STEEL_LANTERN(BarType.STEEL, SmithingType.TYPE_LANTERN, 4527, 49),
	
	STEEL_BULLSEYE(BarType.STEEL, SmithingType.TYPE_BULLSEYE, 4544, 49),

	/**
	 * Steel WarHammer
	 */
	STEEL_WARHAMMER(BarType.STEEL, SmithingType.TYPE_WARHAMMER, 1339, 39),

	/**
	 * Steel battle axe
	 */
	STEEL_BATTLE_AXE(BarType.STEEL, SmithingType.TYPE_BATTLE_AXE, 1365, 40),

	/**
	 * Steel ChainBody
	 */
	STEEL_CHAINBODY(BarType.STEEL, SmithingType.TYPE_CHAINBODY, 1105, 41),

	/**
	 * Steel Kite Shield
	 */
	STEEL_KITE_SHIELD(BarType.STEEL, SmithingType.TYPE_KITE_SHIELD, 1193, 42),

	/**
	 * Steel Claws
	 */
	STEEL_CLAWS(BarType.STEEL, SmithingType.TYPE_CLAWS, 3097, 43),

	/**
	 * Steel 2h
	 */
	STEEL_TWO_HANDED_SWORD(BarType.STEEL, SmithingType.TYPE_TWO_HAND_SWORD, 1311, 44),

	/**
	 * Steel plate skirt
	 */
	STEEL_PLATE_SKIRT(BarType.STEEL, SmithingType.TYPE_PLATE_SKIRT, 1083, 46),

	/**
	 * Steel platelegs
	 */
	STEEL_PLATELEGS(BarType.STEEL, SmithingType.TYPE_PLATELEG, 1069, 46),

	/**
	 * Steel platebody
	 */
	STEEL_PLATEBODY(BarType.STEEL, SmithingType.TYPE_PLATEBODY, 1119, 48),

	/**
	 * Steel pickaxe
	 */
	STEEL_PICKAXE(BarType.STEEL, SmithingType.TYPE_PICKAXE, 1269, 35),

	/**
	 * Mithril Dagger
	 */
	MITHRIL_DAGGER(BarType.MITHRIL, SmithingType.TYPE_DAGGER, 1209, 50),

	/**
	 * Mithril Hatchet
	 */
	MITHRIL_HATCHET(BarType.MITHRIL, SmithingType.TYPE_AXE, 1355, 51),

	/**
	 * Mithril Mace
	 */
	MITHRIL_MACE(BarType.MITHRIL, SmithingType.TYPE_MACE, 1428, 52),

	/**
	 * Mithril Med Helm
	 */
	MITHRIL_MED_HELM(BarType.MITHRIL, SmithingType.TYPE_MEDIUM_HELM, 1143, 53),

	/**
	 * Mithril Crossbow Bolt
	 */
	MITHRIL_CROSSBOW_BOLT(BarType.MITHRIL, SmithingType.TYPE_CROSSBOW_BOLT, 9379, 53),

	/**
	 * Mithril Sword
	 */
	MITHRIL_SWORD(BarType.MITHRIL, SmithingType.TYPE_SWORD, 1285, 54),

	/**
	 * Mithril Dart Tips
	 */
	MITHRIL_DART_TIPS(BarType.MITHRIL, SmithingType.TYPE_DART_TIP, 822, 54),

	/**
	 * Mithril Nails
	 */
	MITHRIL_NAILS(BarType.MITHRIL, SmithingType.TYPE_NAIL, 4822, 54),

	/**
	 * Mithril Arrow Tips
	 */
	MITHRIL_ARROW_TIPS(BarType.MITHRIL, SmithingType.TYPE_ARROW_TIP, 42, 55),

	/**
	 * Mithril Scimitar
	 */
	MITHRIL_SCIMITAR(BarType.MITHRIL, SmithingType.TYPE_SCIMITAR, 1329, 55),

	/**
	 * Mithril Crossbow Limbs
	 */
	MITHRIL_CROSSBOW_LIMBS(BarType.MITHRIL, SmithingType.TYPE_CROSSBOW_LIMB, 9427, 56),

	/**
	 * Mithril LongSword
	 */
	MITHRIL_LONGSWORD(BarType.MITHRIL, SmithingType.TYPE_LONGSWORD, 1299, 56),

	/**
	 * Mithril Knife
	 */
	MITHRIL_KNIFE(BarType.MITHRIL, SmithingType.TYPE_THROWING_KNIFE, 866, 57),

	/**
	 * Mithril Full Helm
	 */
	MITHRIL_FULL_HELM(BarType.MITHRIL, SmithingType.TYPE_FULL_HELM, 1159, 57),

	/**
	 * Mithril SquareShield
	 */
	MITHRIL_SQUARE_SHIELD(BarType.MITHRIL, SmithingType.TYPE_SQUARE_SHIELD, 1181, 58),

	/**
	 * Mithril Grapple Tips
	 */
	MITHRIL_GRAPPLE_TIPS(BarType.MITHRIL, SmithingType.TYPE_GRAPPLE_TIP, 9416, 59),

	/**
	 * Mithril Warhammer
	 */
	MITHRIL_WARHAMMER(BarType.MITHRIL, SmithingType.TYPE_WARHAMMER, 1343, 59),

	/**
	 * Mithril BattleAxe
	 */
	MITHRIL_BATTLEAXE(BarType.MITHRIL, SmithingType.TYPE_BATTLE_AXE, 1369, 60),

	/**
	 * Mithril ChainBody
	 */
	MITHRIL_CHAINBODY(BarType.MITHRIL, SmithingType.TYPE_CHAINBODY, 1109, 61),

	/**
	 * Mithril KiteShield
	 */
	MITHRIL_KITE_SHIELD(BarType.MITHRIL, SmithingType.TYPE_KITE_SHIELD, 1197, 62),

	/**
	 * Mithril Claws
	 */
	MITHRIL_CLAWS(BarType.MITHRIL, SmithingType.TYPE_CLAWS, 3099, 63),

	/**
	 * Mithril 2H
	 */
	MITHRIL_TWO_HANDED_SWORD(BarType.MITHRIL, SmithingType.TYPE_TWO_HAND_SWORD, 1315, 64),

	/**
	 * Mithril PlateSkirt
	 */
	MITHRIL_PLATESKIRT(BarType.MITHRIL, SmithingType.TYPE_PLATE_SKIRT, 1085, 66),

	/**
	 * Mithril PlateLegs
	 */
	MITHRIL_PLATELEGS(BarType.MITHRIL, SmithingType.TYPE_PLATELEG, 1071, 66),

	/**
	 * Mithril PlateBody
	 */
	MITHRIL_PLATEBODY(BarType.MITHRIL, SmithingType.TYPE_PLATEBODY, 1121, 68),

	/**
	 * Mithril PickAxe
	 */
	MITHRIL_PICKAXE(BarType.MITHRIL, SmithingType.TYPE_PICKAXE, 1273, 55),

	/**
	 * Adamant Dagger
	 */
	ADAMANT_DAGGER(BarType.ADAMANT, SmithingType.TYPE_DAGGER, 1211, 70),

	/**
	 * Adamant Hatchet
	 */
	ADAMANT_AXE(BarType.ADAMANT, SmithingType.TYPE_AXE, 1357, 71),

	/**
	 * Adamant Mace
	 */
	ADAMANT_MACE(BarType.ADAMANT, SmithingType.TYPE_MACE, 1430, 72),

	/**
	 * Adamant Med Helm
	 */
	ADAMANT_MEDIUM_HELM(BarType.ADAMANT, SmithingType.TYPE_MEDIUM_HELM, 1145, 73),

	/**
	 * Adamant Crossbow Bolt
	 */
	ADAMANT_BOLT(BarType.ADAMANT, SmithingType.TYPE_CROSSBOW_BOLT, 9380, 73),

	/**
	 * Adamant Sword
	 */
	ADAMANT_SWORD(BarType.ADAMANT, SmithingType.TYPE_SWORD, 1287, 74),

	/**
	 * Adamant Dart Tips
	 */
	ADAMANT_DART_TIPS(BarType.ADAMANT, SmithingType.TYPE_DART_TIP, 823, 74),

	/**
	 * Adamant Nails
	 */
	ADAMANT_NAILS(BarType.ADAMANT, SmithingType.TYPE_NAIL, 4823, 74),

	/**
	 * Adamant Arrow Tips
	 */
	ADAMANT_ARROW_TIPS(BarType.ADAMANT, SmithingType.TYPE_ARROW_TIP, 43, 75),

	/**
	 * Adamant Scmitar
	 */
	ADAMANT_SCIMITAR(BarType.ADAMANT, SmithingType.TYPE_SCIMITAR, 1331, 75),

	/**
	 * Adamant Crossbow Limbs
	 */
	ADAMANT_LIMBS(BarType.ADAMANT, SmithingType.TYPE_CROSSBOW_LIMB, 9429, 76),

	/**
	 * Adamant LongSword
	 */
	ADAMANT_LONGSWORD(BarType.ADAMANT, SmithingType.TYPE_LONGSWORD, 1301, 76),

	/**
	 * Adamant Knife
	 */
	ADAMANT_KNIFE(BarType.ADAMANT, SmithingType.TYPE_THROWING_KNIFE, 867, 77),

	/**
	 * Adamant Full Helm
	 */
	ADAMANT_FULL_HELM(BarType.ADAMANT, SmithingType.TYPE_FULL_HELM, 1161, 77),

	/**
	 * Adamant Square Shield
	 */
	ADAMANT_SQUARE_SHIELD(BarType.ADAMANT, SmithingType.TYPE_SQUARE_SHIELD, 1183, 78),

	/**
	 * Adamant Warhammer
	 */
	ADAMANT_WARHAMMER(BarType.ADAMANT, SmithingType.TYPE_WARHAMMER, 1345, 79),

	/**
	 * Adamant BattleAxe
	 */
	ADAMANT_BATTLEAXE(BarType.ADAMANT, SmithingType.TYPE_BATTLE_AXE, 1371, 80),

	/**
	 * Adamant ChainBody
	 */
	ADAMANT_CHAINBODY(BarType.ADAMANT, SmithingType.TYPE_CHAINBODY, 1111, 81),

	/**
	 * Adamant KiteShield
	 */
	ADAMANT_KITESHIELD(BarType.ADAMANT, SmithingType.TYPE_KITE_SHIELD, 1199, 82),

	/**
	 * Adamant Claws
	 */
	ADAMANT_CLAWS(BarType.ADAMANT, SmithingType.TYPE_CLAWS, 3100, 83),

	/**
	 * Adamant 2H
	 */
	ADAMANT_TWO_HANDED_SWORD(BarType.ADAMANT, SmithingType.TYPE_TWO_HAND_SWORD, 1317, 84),

	/**
	 * Adamant PlateSkirt
	 */
	ADAMANT_PLATE_SKIRT(BarType.ADAMANT, SmithingType.TYPE_PLATE_SKIRT, 1091, 86),

	/**
	 * Adamant PlateLegs
	 */
	ADAMANT_PLATE_LEGS(BarType.ADAMANT, SmithingType.TYPE_PLATELEG, 1073, 86),

	/**
	 * Adamant PlateBody
	 */
	ADAMANT_PLATE_BODY(BarType.ADAMANT, SmithingType.TYPE_PLATEBODY, 1123, 88),

	/**
	 * Adamant PickAxe
	 */
	ADAMANT_PICKAXE(BarType.ADAMANT, SmithingType.TYPE_PICKAXE, 1271, 75),

	/**
	 * Rune Dagger
	 */
	RUNE_DAGGER(BarType.RUNITE, SmithingType.TYPE_DAGGER, 1213, 85),

	/**
	 * Rune Hatchet
	 */
	RUNITE_AXE(BarType.RUNITE, SmithingType.TYPE_AXE, 1359, 86),

	/**
	 * Rune Mace
	 */
	RUNITE_MACE(BarType.RUNITE, SmithingType.TYPE_MACE, 1432, 87),

	/**
	 * Rune Med Helm
	 */
	RUNITE_MEDIUM_HELM(BarType.RUNITE, SmithingType.TYPE_MEDIUM_HELM, 1147, 88),

	/**
	 * Rune Crossbow Bolt
	 */
	RUNITE_BOLT(BarType.RUNITE, SmithingType.TYPE_CROSSBOW_BOLT, 9381, 88),

	/**
	 * Rune Sword
	 */
	RUNITE_SWORD(BarType.RUNITE, SmithingType.TYPE_SWORD, 1289, 89),

	/**
	 * Rune Dart Tips
	 */
	RUNITE_DART_TIPS(BarType.RUNITE, SmithingType.TYPE_DART_TIP, 824, 89),

	/**
	 * Rune Nails
	 */
	RUNITE_NAILS(BarType.RUNITE, SmithingType.TYPE_NAIL, 4824, 89),

	/**
	 * Rune Arrow Tips
	 */
	RUNITE_ARROW_TIPS(BarType.RUNITE, SmithingType.TYPE_ARROW_TIP, 44, 90),

	/**
	 * Rune Scmitar
	 */
	RUNITE_SCIMITAR(BarType.RUNITE, SmithingType.TYPE_SCIMITAR, 1333, 90),

	/**
	 * Rune Crossbow Limbs
	 */
	RUNITE_LIMBS(BarType.RUNITE, SmithingType.TYPE_CROSSBOW_LIMB, 9431, 91),

	/**
	 * Rune LongSword
	 */
	RUNITE_LONGSWORD(BarType.RUNITE, SmithingType.TYPE_LONGSWORD, 1303, 91),

	/**
	 * Rune Knife
	 */
	RUNITE_KNIFE(BarType.RUNITE, SmithingType.TYPE_THROWING_KNIFE, 868, 92),

	/**
	 * Rune Full Helm
	 */
	RUNITE_FULL_HELM(BarType.RUNITE, SmithingType.TYPE_FULL_HELM, 1163, 92),

	/**
	 * Rune Square Shield
	 */
	RUNITE_SQUARE_SHIELD(BarType.RUNITE, SmithingType.TYPE_SQUARE_SHIELD, 1185, 93),

	/**
	 * Rune Warhammer
	 */
	RUNITE_WARHAMMER(BarType.RUNITE, SmithingType.TYPE_WARHAMMER, 1347, 94),

	/**
	 * Rune BattleAxe
	 */
	RUNITE_BATTLEAXE(BarType.RUNITE, SmithingType.TYPE_BATTLE_AXE, 1373, 95),

	/**
	 * Rune ChainBody
	 */
	RUNITE_CHAINBODY(BarType.RUNITE, SmithingType.TYPE_CHAINBODY, 1113, 96),

	/**
	 * Rune KiteShield
	 */
	RUNITE_KITESHIELD(BarType.RUNITE, SmithingType.TYPE_KITE_SHIELD, 1201, 97),

	/**
	 * Rune Claws
	 */
	RUNITE_CLAWS(BarType.RUNITE, SmithingType.TYPE_CLAWS, 3101, 98),

	/**
	 * Rune 2H
	 */
	RUNITE_TWO_HANDED_SWORD(BarType.RUNITE, SmithingType.TYPE_TWO_HAND_SWORD, 1319, 99),

	/**
	 * Rune PlateSkirt
	 */
	RUNITE_PLATE_SKIRT(BarType.RUNITE, SmithingType.TYPE_PLATE_SKIRT, 1093, 99),

	/**
	 * Rune PlateLegs
	 */
	RUNITE_PLATE_LEGS(BarType.RUNITE, SmithingType.TYPE_PLATELEG, 1079, 99),

	/**
	 * Rune PlateBody
	 */
	RUNITE_PLATE_BODY(BarType.RUNITE, SmithingType.TYPE_PLATEBODY, 1127, 99),

	/**
	 * Rune PickAxe
	 */
	RUNITE_PICKAXE(BarType.RUNITE, SmithingType.TYPE_PICKAXE, 1275, 90),

	/**
	 * Blurite CrossBow bolts
	 */
	BLURITE_CROSSBOW_BOLT(BarType.BLURITE, SmithingType.TYPE_CROSSBOW_BOLT, 9376, 8),

	/**
	 * Blurite Crossbow Limbs
	 */
	BLURITE_CROSSBOW_LIMBS(BarType.BLURITE, SmithingType.TYPE_CROSSBOW_LIMB, 9422, 13);

	/**
	 * A map of object ids to primary ingredients.
	 */
	private static Map<Short, Bars> bars = new HashMap<Short, Bars>();

	/**
	 * Gets a bar by an item id.
	 * @param item The item id.
	 * @return The bar or <code>null</code> if the object is not a bar.
	 */
	public static Bars forId(int item) {
		for (Bars bar : Bars.values()) {
			if (bar.getProduct() == item) {
				return bar;
			}
		}
		return null;
	}

	/**
	 * Populates the herb mapping.
	 */
	static {
		for (Bars bar : Bars.values()) {
			bars.put((short) bar.getProduct(), bar);
		}
	}

	private BarType Btype;
	private SmithingType Stype;
	private int product;
	private int level;

	private Bars(BarType Btype, SmithingType Stype, int productId, int level) {
		this.Btype = Btype;
		this.Stype = Stype;
		this.product = productId;
		this.level = level;
	}

	public BarType getBarType() {
		return Btype;
	}

	public SmithingType getSmithingType() {
		return Stype;
	}

	public int getProduct() {
		return product;
	}

	public int getLevel() {
		return level;
	}

	public static boolean constructInterface(Player player, int itemId) {
		switch (itemId) {
		case 2349:
		case 2351:
		case 2353:
		case 2359:
		case 2361:
		case 2363:
			return true;
		}
		return false;
	}

	public static Bars[] getBars(BarType type) {
		List<Bars> bars = new ArrayList<Bars>();
		for (Bars bar : Bars.values()) {
			if (bar.getBarType() == type) {
				bars.add(bar);
			}
		}
		Bars[] barss = new Bars[bars.size()];
		for (int i = 0; i < bars.size(); i++) {
			barss[i] = bars.get(i);
		}
		return barss;
	}

	public static int getItemId(int buttonId, BarType type) {
		for (Bars bar : Bars.values()) {
			if (bar.getBarType() != type) {
				continue;
			}
			for (int i : bar.getSmithingType().getButton()) {
				if (buttonId == i) {
					return bar.getProduct();
				}
			}
		}
		return -1;
	}

	public static int getIndex(Player player, int buttonId, BarType type) {
		int index = 0;
		for (Bars bar : Bars.values()) {
			if (bar.getBarType() != type) {
				continue;
			}
			bar = Bars.forId(bar.getProduct());
			for (int i = 0; i < bar.getSmithingType().getButton().length; i++) {
				if (buttonId != bar.getSmithingType().getButton()[i]) {
					index++;
					return index;
				}
			}
		}
		return -1;
	}
}
