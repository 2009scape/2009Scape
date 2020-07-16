package plugin.ai;

import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.appearance.Gender;
import core.game.node.item.Item;
import core.game.world.map.Location;
import core.tools.RandomFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Used for "building" artificial intelligent players.
 * @author Emperor
 */
public final class AIPBuilder {

	/**
	 * Creates a new artificial intelligent player.
	 * @return The AIPlayer object.
	 */
	public static AIPlayer create(Location l) {
		return new AIPlayer(l);
	}

	/**
	 * Makes an artificial intelligent copy of the player.
	 * @param player The player.
	 * @return The artificial intelligent player with the same name, stats,
	 * items, etc.
	 */
	public static AIPlayer copy(Player player) {
		return copy(player, player.getLocation());
	}

	/**
	 * Makes an artificial intelligent copy of the player.
	 * @param player The player.
	 * @param l The location the AIP should spawn on.
	 * @return The artificial intelligent player with the same name, stats,
	 * items, etc.
	 */
	public static AIPlayer copy(Player player, Location l) {
		AIPlayer p = new AIPlayer(l);
		p.getSkills().copy(player.getSkills());
		p.getInventory().copy(player.getInventory());
		p.getEquipment().copy(player.getEquipment());
		p.getBank().copy(player.getBank());
		p.getAppearance().copy(player.getAppearance());
		p.setControler(player);
		return p;
	}
	
	public static void RandomAfkPlayer(Location loc)
	{
		final AIPlayer bot = new AIPlayer(loc);
		bot.getAppearance().setGender(RandomFunction.random(3) == 1 ? Gender.FEMALE : Gender.MALE);


		
		bot.getSkills().setStaticLevel(Skills.MAGIC, RandomFunction.getRandom(99));
		bot.getSkills().setStaticLevel(Skills.DEFENCE, RandomFunction.getRandom(99));
		bot.getSkills().setStaticLevel(Skills.ATTACK, RandomFunction.getRandom(99));
		bot.getSkills().setStaticLevel(Skills.STRENGTH, RandomFunction.getRandom(99));
		bot.getSkills().setStaticLevel(Skills.RANGE, RandomFunction.getRandom(99));
		bot.getSkills().setStaticLevel(Skills.HITPOINTS, RandomFunction.getRandom(99));
	
		bot.getSkills().updateCombatLevel();
		bot.getAppearance().sync();
		
		//set orientation?
	}
	
	public static void immersiveSpawns()
	{
		//Edge
		RandomAfkPlayer(new Location(3094, 3491));
		RandomAfkPlayer(new Location(3094, 3493));
		RandomAfkPlayer(new Location(3092, 3497));
		
		//GE
		RandomAfkPlayer(new Location(3160, 3492));
		RandomAfkPlayer(new Location(3161, 3491));
		RandomAfkPlayer(new Location(3165, 3483));
		RandomAfkPlayer(new Location(3168, 3485));
		RandomAfkPlayer(new Location(3165, 3485));
		RandomAfkPlayer(new Location(3164, 3493));
		
		//Varrock Ge bank
		RandomAfkPlayer(new Location(3189, 3439));
		
		//Home Bank
		RandomAfkPlayer(new Location(2099, 3918));
	}

	private List<Item> ITEMS = new ArrayList<>(Arrays.asList(new Item(286), //Orange goblin mail

            new Item(287), //Blue goblin mail

            new Item(288), //Goblin mail

            new Item(426), //Priest gown

            new Item(428), //Priest gown

            new Item(466), //Pickaxe handle

            new Item(492), //Picture

            new Item(542), //Monk's robe

            new Item(544), //Monk's robe

            new Item(546), //Shade robe

            new Item(548), //Shade robe

            new Item(552), //Ghostspeak amulet

            new Item(577), //Wizard robe

            new Item(579), //Wizard hat

            new Item(581), //Black robe

            new Item(599), //Picture

            new Item(667), //Blurite sword

            new Item(713), //Picture

            new Item(762), //Picture

            new Item(764), //Picture

            new Item(766), //Picture

            new Item(767), //Phoenix crossbow

            new Item(770), //Picture

            new Item(837), //Crossbow

            new Item(839), //Longbow

            new Item(841), //Shortbow

            new Item(843), //Oak shortbow

            new Item(845), //Oak longbow

            new Item(847), //Willow longbow

            new Item(849), //Willow shortbow

            new Item(851), //Maple longbow

            new Item(853), //Maple shortbow

            new Item(877), //Bronze bolts

            new Item(882), //Bronze arrow

            new Item(884), //Iron arrow

            new Item(886), //Steel arrow

            new Item(888), //Mithril arrow

            new Item(890), //Adamant arrow

            new Item(1005), //White apron

            new Item(1007), //Cape

            new Item(1009), //Brass necklace

            new Item(1011), //Blue skirt

            new Item(1013), //Pink skirt

            new Item(1015), //Black skirt

            new Item(1017), //Wizard hat

            new Item(1019), //Cape

            new Item(1021), //Cape

            new Item(1023), //Cape

            new Item(1027), //Cape

            new Item(1029), //Cape

            new Item(1031), //Cape

            new Item(1037), //Bunny ears

            new Item(1038), //Red partyhat

            new Item(1040), //Yellow partyhat

            new Item(1042), //Blue partyhat

            new Item(1044), //Green partyhat

            new Item(1046), //Purple partyhat

            new Item(1048), //White partyhat

            new Item(1050), //Santa hat

            new Item(1053), //Green h'ween mask

            new Item(1055), //Blue h'ween mask

            new Item(1057), //Red h'ween mask

            new Item(1059), //Leather gloves

            new Item(1061), //Leather boots

            new Item(1063), //Leather vambraces

            new Item(1065), //Green d'hide vamb

            new Item(1067), //Iron platelegs

            new Item(1069), //Steel platelegs

            new Item(1071), //Mithril platelegs

            new Item(1073), //Adamant platelegs

            new Item(1075), //Bronze platelegs

            new Item(1077), //Black platelegs

            new Item(1079), //Rune platelegs

            new Item(1081), //Iron plateskirt

            new Item(1083), //Steel plateskirt

            new Item(1085), //Mithril plateskirt

            new Item(1087), //Bronze plateskirt

            new Item(1089), //Black plateskirt

            new Item(1091), //Adamant plateskirt

            new Item(1093), //Rune plateskirt

            new Item(1095), //Leather chaps

            new Item(1097), //Studded chaps

            new Item(1099), //Green d'hide chaps

            new Item(1101), //Iron chainbody

            new Item(1103), //Bronze chainbody

            new Item(1105), //Steel chainbody

            new Item(1107), //Black chainbody

            new Item(1109), //Mithril chainbody

            new Item(1111), //Adamant chainbody

            new Item(1113), //Rune chainbody

            new Item(1115), //Iron platebody

            new Item(1117), //Bronze platebody

            new Item(1119), //Steel platebody

            new Item(1121), //Mithril platebody

            new Item(1123), //Adamant platebody

            new Item(1125), //Black platebody

            new Item(1127), //Rune platebody

            new Item(1129), //Leather body

            new Item(1131), //Hardleather body

            new Item(1133), //Studded body

            new Item(1135), //Green d'hide body

            new Item(1137), //Iron med helm

            new Item(1139), //Bronze med helm

            new Item(1141), //Steel med helm

            new Item(1143), //Mithril med helm

            new Item(1145), //Adamant med helm

            new Item(1147), //Rune med helm

            new Item(1151), //Black med helm

            new Item(1153), //Iron full helm

            new Item(1155), //Bronze full helm

            new Item(1157), //Steel full helm

            new Item(1159), //Mithril full helm

            new Item(1161), //Adamant full helm

            new Item(1163), //Rune full helm

            new Item(1165), //Black full helm

            new Item(1167), //Leather cowl

            new Item(1169), //Coif

            new Item(1171), //Wooden shield

            new Item(1173), //Bronze sq shield

            new Item(1175), //Iron sq shield

            new Item(1177), //Steel sq shield

            new Item(1179), //Black sq shield

            new Item(1181), //Mithril sq shield

            new Item(1183), //Adamant sq shield

            new Item(1185), //Rune sq shield

            new Item(1189), //Bronze kiteshield

            new Item(1191), //Iron kiteshield

            new Item(1193), //Steel kiteshield

            new Item(1195), //Black kiteshield

            new Item(1197), //Mithril kiteshield

            new Item(1199), //Adamant kiteshield

            new Item(1201), //Rune kiteshield

            new Item(1203), //Iron dagger

            new Item(1205), //Bronze dagger

            new Item(1207), //Steel dagger

            new Item(1209), //Mithril dagger

            new Item(1211), //Adamant dagger

            new Item(1213), //Rune dagger

            new Item(1217), //Black dagger

            new Item(1265), //Bronze pickaxe

            new Item(1267), //Iron pickaxe

            new Item(1269), //Steel pickaxe

            new Item(1271), //Adamant pickaxe

            new Item(1273), //Mithril pickaxe

            new Item(1275), //Rune pickaxe

            new Item(1277), //Bronze sword

            new Item(1279), //Iron sword

            new Item(1281), //Steel sword

            new Item(1283), //Black sword

            new Item(1285), //Mithril sword

            new Item(1287), //Adamant sword

            new Item(1289), //Rune sword

            new Item(1291), //Bronze longsword

            new Item(1293), //Iron longsword

            new Item(1295), //Steel longsword

            new Item(1297), //Black longsword

            new Item(1299), //Mithril longsword

            new Item(1301), //Adamant longsword

            new Item(1303), //Rune longsword

            new Item(1307), //Bronze 2h sword

            new Item(1309), //Iron 2h sword

            new Item(1311), //Steel 2h sword

            new Item(1313), //Black 2h sword

            new Item(1315), //Mithril 2h sword

            new Item(1317), //Adamant 2h sword

            new Item(1319), //Rune 2h sword

            new Item(1321), //Bronze scimitar

            new Item(1323), //Iron scimitar

            new Item(1325), //Steel scimitar

            new Item(1327), //Black scimitar

            new Item(1329), //Mithril scimitar

            new Item(1331), //Adamant scimitar

            new Item(1333), //Rune scimitar

            new Item(1335), //Iron warhammer

            new Item(1337), //Bronze warhammer

            new Item(1339), //Steel warhammer

            new Item(1341), //Black warhammer

            new Item(1343), //Mithril warhammer

            new Item(1345), //Adamant warhammer

            new Item(1347), //Rune warhammer

            new Item(1349), //Iron axe

            new Item(1351), //Bronze axe

            new Item(1353), //Steel axe

            new Item(1355), //Mithril axe

            new Item(1357), //Adamant axe

            new Item(1359), //Rune axe

            new Item(1361), //Black axe

            new Item(1363), //Iron battleaxe

            new Item(1365), //Steel battleaxe

            new Item(1367), //Black battleaxe

            new Item(1369), //Mithril battleaxe

            new Item(1371), //Adamant battleaxe

            new Item(1373), //Rune battleaxe

            new Item(1375), //Bronze battleaxe

            new Item(1379), //Staff

            new Item(1381), //Staff of air

            new Item(1383), //Staff of water

            new Item(1385), //Staff of earth

            new Item(1387), //Staff of fire

            new Item(1389), //Magic staff

            new Item(1419), //Scythe

            new Item(1420), //Iron mace

            new Item(1422), //Bronze mace

            new Item(1424), //Steel mace

            new Item(1426), //Black mace

            new Item(1428), //Mithril mace

            new Item(1430), //Adamant mace

            new Item(1432), //Rune mace

            new Item(1478), //Amulet of accuracy

            new Item(1540), //Anti-dragon shield

            new Item(1589), //Picture

            new Item(1635), //Gold ring

            new Item(1637), //Sapphire ring

            new Item(1639), //Emerald ring

            new Item(1641), //Ruby ring

            new Item(1643), //Diamond ring

            new Item(1654), //Gold necklace

            new Item(1656), //Sapphire necklace

            new Item(1658), //Emerald necklace

            new Item(1660), //Ruby necklace

            new Item(1662), //Diamond necklace

            new Item(1692), //Gold amulet

            new Item(1694), //Sapphire amulet

            new Item(1696), //Emerald amulet

            new Item(1698), //Ruby amulet

            new Item(1700), //Diamond amulet

            new Item(1716), //Unblessed symbol

            new Item(1718), //Holy symbol

            new Item(1725), //Amulet of strength

            new Item(1727), //Amulet of magic

            new Item(1729), //Amulet of defence

            new Item(1731), //Amulet of power

            new Item(1757), //Brown apron

            new Item(1949), //Chef's hat

            new Item(2402), //Silverlight

            new Item(2420), //Picture

            new Item(2422), //Blue partyhat

            new Item(2425), //Picture

            new Item(2480), //Picture

            new Item(2512), //Picture

            new Item(2513), //Dragon chainbody

            new Item(2583), //Black platebody (t)

            new Item(2585), //Black platelegs (t)

            new Item(2587), //Black full helm(t)

            new Item(2589), //Black kiteshield (t)

            new Item(2591), //Black platebody (g)

            new Item(2593), //Black platelegs (g)

            new Item(2595), //Black full helm(g)

            new Item(2597), //Black kiteshield (g)

            new Item(2599), //Adam platebody (t)

            new Item(2601), //Adam platelegs (t)

            new Item(2603), //Adam kiteshield (t)

            new Item(2605), //Adam full helm(t)

            new Item(2607), //Adam platebody (g)

            new Item(2609), //Adam platelegs (g)

            new Item(2611), //Adam kiteshield (g)

            new Item(2613), //Adam full helm(g)

            new Item(2615), //Rune platebody (g)

            new Item(2617), //Rune platelegs (g)

            new Item(2619), //Rune full helm(g)

            new Item(2621), //Rune kiteshield (g)

            new Item(2623), //Rune platebody (t)

            new Item(2625), //Rune platelegs (t)

            new Item(2627), //Rune full helm (t)

            new Item(2629), //Rune kiteshield (t)

            new Item(2653), //Zamorak platebody

            new Item(2655), //Zamorak platelegs

            new Item(2657), //Zamorak full helm

            new Item(2659), //Zamorak kiteshield

            new Item(2661), //Saradomin platebody

            new Item(2663), //Saradomin platelegs

            new Item(2665), //Saradomin full helm

            new Item(2667), //Saradomin kiteshield

            new Item(2669), //Guthix platebody

            new Item(2671), //Guthix platelegs

            new Item(2673), //Guthix full helm

            new Item(2675), //Guthix kiteshield

            new Item(2902), //Gloves

            new Item(2912), //Gloves

            new Item(2922), //Gloves

            new Item(2932), //Gloves

            new Item(2942), //Gloves

            new Item(3057), //Mime mask

            new Item(3058), //Mime top

            new Item(3059), //Mime legs

            new Item(3060), //Mime gloves

            new Item(3061), //Mime boots

            new Item(3472), //Black plateskirt (t)

            new Item(3473), //Black plateskirt (g)

            new Item(3474), //Adam plateskirt (t)

            new Item(3475), //Adam plateskirt (g)

            new Item(3476), //Rune plateskirt (g)

            new Item(3477), //Rune plateskirt (t)

            new Item(3478), //Zamorak plateskirt

            new Item(3479), //Saradomin plateskirt

            new Item(3480), //Guthix plateskirt

            new Item(3667), //Picture

            new Item(4000), //Picture

            new Item(4076), //Picture

            new Item(4178), //Abyssal whip

            new Item(4180), //Dragon platelegs

            new Item(4315), //Team-1 cape

            new Item(4317), //Team-2 cape

            new Item(4319), //Team-3 cape

            new Item(4321), //Team-4 cape

            new Item(4323), //Team-5 cape

            new Item(4325), //Team-6 cape

            new Item(4327), //Team-7 cape

            new Item(4329), //Team-8 cape

            new Item(4331), //Team-9 cape

            new Item(4333), //Team-10 cape

            new Item(4335), //Team-11 cape

            new Item(4337), //Team-12 cape

            new Item(4339), //Team-13 cape

            new Item(4341), //Team-14 cape

            new Item(4343), //Team-15 cape

            new Item(4345), //Team-16 cape

            new Item(4347), //Team-17 cape

            new Item(4349), //Team-18 cape

            new Item(4351), //Team-19 cape

            new Item(4353), //Team-20 cape

            new Item(4355), //Team-21 cape

            new Item(4357), //Team-22 cape

            new Item(4359), //Team-23 cape

            new Item(4361), //Team-24 cape

            new Item(4363), //Team-25 cape

            new Item(4365), //Team-26 cape

            new Item(4367), //Team-27 cape

            new Item(4369), //Team-28 cape

            new Item(4371), //Team-29 cape

            new Item(4373), //Team-30 cape

            new Item(4375), //Team-31 cape

            new Item(4377), //Team-32 cape

            new Item(4379), //Team-33 cape

            new Item(4381), //Team-34 cape

            new Item(4383), //Team-35 cape

            new Item(4385), //Team-36 cape

            new Item(4387), //Team-37 cape

            new Item(4389), //Team-38 cape

            new Item(4391), //Team-39 cape

            new Item(4393), //Team-40 cape

            new Item(4395), //Team-41 cape

            new Item(4397), //Team-42 cape

            new Item(4399), //Team-43 cape

            new Item(4401), //Team-44 cape

            new Item(4403), //Team-45 cape

            new Item(4405), //Team-46 cape

            new Item(4407), //Team-47 cape

            new Item(4409), //Team-48 cape

            new Item(4411), //Team-49 cape

            new Item(4413), //Team-50 cape

            new Item(4565), //Basket of eggs

            new Item(4566), //Rubber chicken

            new Item(5525), //Tiara

            new Item(5527), //Air tiara

            new Item(5529), //Mind tiara

            new Item(5531), //Water tiara

            new Item(5533), //Body tiara

            new Item(5535), //Earth tiara

            new Item(5537), //Fire tiara

            new Item(6180), //Lederhosen top

            new Item(6181), //Lederhosen shorts

            new Item(6182), //Lederhosen hat

            new Item(6184), //Prince tunic

            new Item(6185), //Prince leggings

            new Item(6186), //Princess blouse

            new Item(6187), //Princess skirt

            new Item(6188), //Frog mask

            new Item(6201), //Picture

            new Item(6203), //Picture

            new Item(6205), //Picture

            new Item(6207), //Picture

            new Item(6208), //Man speak amulet

            new Item(6210), //Picture

            new Item(6381), //Picture

            new Item(6654), //Camo top

            new Item(6655), //Camo bottoms

            new Item(6656), //Camo helmet

            new Item(6659), //Camo helmet

            new Item(6856), //Bobble hat

            new Item(6857), //Bobble scarf

            new Item(6858), //Jester hat

            new Item(6859), //Jester scarf

            new Item(6860), //Tri-jester hat

            new Item(6861), //Tri-jester scarf

            new Item(6862), //Woolly hat

            new Item(6863), //Woolly scarf

            new Item(6864), //Marionette handle

            new Item(6967), //Dragon med helm

            new Item(7332), //Black shield(h1)

            new Item(7334), //Adamant shield(h1)

            new Item(7336), //Rune shield(h1)

            new Item(7338), //Black shield(h2)

            new Item(7340), //Adamant shield(h2)

            new Item(7342), //Rune shield(h2)

            new Item(7344), //Black shield(h3)

            new Item(7346), //Adamant shield(h3)

            new Item(7348), //Rune shield(h3)

            new Item(7350), //Black shield(h4)

            new Item(7352), //Adamant shield(h4)

            new Item(7354), //Rune shield(h4)

            new Item(7356), //Black shield(h5)

            new Item(7358), //Adamant shield(h5)

            new Item(7360), //Rune shield(h5)

            new Item(7362), //Studded body (g)

            new Item(7364), //Studded body (t)

            new Item(7366), //Studded chaps (g)

            new Item(7368), //Studded chaps (t)

            new Item(7370), //D'hide body(g)

            new Item(7372), //D'hide body (t)

            new Item(7378), //D'hide chaps (g)

            new Item(7380), //D'hide chaps (t)

            new Item(7386), //Blue skirt (g)

            new Item(7388), //Blue skirt (t)

            new Item(7390), //Wizard robe (g)

            new Item(7392), //Wizard robe (t)

            new Item(7394), //Wizard hat (g)

            new Item(7396), //Wizard hat (t)

            new Item(7414), //Paddle

            new Item(7592), //Zombie shirt

            new Item(7593), //Zombie trousers

            new Item(7594), //Zombie mask

            new Item(7595), //Zombie gloves

            new Item(7596), //Zombie boots

            new Item(7672), //Picture

            new Item(7674), //Perfect

            new Item(7803), //Yin yang amulet

            new Item(7927), //Easter ring

            new Item(9005), //Fancy boots

            new Item(9006), //Fighting boots

            new Item(9013), //Skull sceptre

            new Item(9054), //Red goblin mail

            new Item(9056), //Yellow goblin mail

            new Item(9057), //Green goblin mail

            new Item(9058), //Purple goblin mail

            new Item(9059), //Pink goblin mail

            new Item(9665), //Torch

            new Item(9702), //Stick

            new Item(9703), //Training sword

            new Item(9704), //Training shield

            new Item(9705), //Training bow

            new Item(9706), //Training arrows

            new Item(9906), //Ghost buster 500

            new Item(9907), //Ghost buster 500

            new Item(9908), //Ghost buster 500

            new Item(9909), //Ghost buster 500

            new Item(9910), //Ghost buster 500

            new Item(9911), //Ghost buster 500

            new Item(9920), //Jack lantern mask

            new Item(9921), //Skeleton boots

            new Item(9922), //Skeleton gloves

            new Item(9923), //Skeleton leggings

            new Item(9924), //Skeleton shirt

            new Item(9925), //Skeleton mask

            new Item(10280), //Willow comp bow

            new Item(10286), //Rune helm (h1)

            new Item(10288), //Rune helm (h2)

            new Item(10290), //Rune helm (h3)

            new Item(10292), //Rune helm (h4)

            new Item(10294), //Rune helm (h5)

            new Item(10296), //Adamant helm (h1)

            new Item(10298), //Adamant helm (h2)

            new Item(10300), //Adamant helm (h3)

            new Item(10302), //Adamant helm (h4)

            new Item(10304), //Adamant helm (h5)

            new Item(10306), //Black helm (h1)

            new Item(10308), //Black helm (h2)

            new Item(10310), //Black helm (h3)

            new Item(10312), //Black helm (h4)

            new Item(10314), //Black helm (h5)

            new Item(10364), //Strength amulet(t)

            new Item(10366), //Amulet of magic(t)

            new Item(10501), //Snowball

            new Item(10507), //Reindeer hat

            new Item(10567), //Picture

            new Item(10569), //Picture

            new Item(10571), //Picture

            new Item(10573), //Picture

            new Item(10575), //Picture

            new Item(10577), //Picture

            new Item(10579), //Picture

            new Item(10629), //Mime mask

            new Item(10630), //Princess blouse

            new Item(10631), //Zombie shirt

            new Item(10632), //Camo top

            new Item(10633), //Lederhosen top

            new Item(10634), //Shade robe

            new Item(10665), //Black shield(h1)

            new Item(10666), //Adamant shield(h1)

            new Item(10667), //Rune shield(h1)

            new Item(10668), //Black shield(h2)

            new Item(10669), //Adamant shield(h2)

            new Item(10670), //Rune shield(h2)

            new Item(10671), //Black shield(h3)

            new Item(10672), //Adamant shield(h3)

            new Item(10673), //Rune shield(h3)

            new Item(10674), //Black shield(h4)

            new Item(10675), //Adamant shield(h4)

            new Item(10676), //Rune shield(h4)

            new Item(10677), //Black shield(h5)

            new Item(10678), //Adamant shield(h5)

            new Item(10679), //Rune shield(h5)

            new Item(10680), //Studded body (g)

            new Item(10681), //Studded body (t)

            new Item(10682), //D'hide body(g)

            new Item(10683), //D'hide body (t)

            new Item(10686), //Wizard robe (g)

            new Item(10687), //Wizard robe (t)

            new Item(10690), //Black platebody (t)

            new Item(10691), //Black platebody (g)

            new Item(10697), //Adam platebody (t)

            new Item(10698), //Adam platebody (g)

            new Item(10699), //Black helm (h1)

            new Item(10700), //Black helm (h2)

            new Item(10701), //Black helm (h3)

            new Item(10702), //Black helm (h4)

            new Item(10703), //Black helm (h5)

            new Item(10704), //Rune helm (h1)

            new Item(10705), //Rune helm (h2)

            new Item(10706), //Rune helm (h3)

            new Item(10707), //Rune helm (h4)

            new Item(10708), //Rune helm (h5)

            new Item(10709), //Adamant helm (h1)

            new Item(10710), //Adamant helm (h2)

            new Item(10711), //Adamant helm (h3)

            new Item(10712), //Adamant helm (h4)

            new Item(10713), //Adamant helm (h5)

            new Item(10736), //Strength amulet(t)

            new Item(10738), //Amulet of magic(t)

            new Item(10776), //Zamorak platebody

            new Item(10778), //Saradomin plate

            new Item(10780), //Guthix platebody

            new Item(10798), //Rune platebody (g)

            new Item(10800), //Rune platebody (t)

            new Item(11019), //Chicken feet

            new Item(11020), //Chicken wings

            new Item(11021), //Chicken head

            new Item(11022), //Chicken legs

            new Item(11165), //Phoenix crossbow

            new Item(11167), //Phoenix crossbow

            new Item(11789), //Grim reaper hood

            new Item(11790), //Grim reaper hood

            new Item(11951), //Snowball

            new Item(12629), //Safety gloves

            new Item(12634), //Chocatrice cape

            new Item(12645), //Chocatrice cape

            new Item(12844), //Toy kite

            new Item(12845), //Stone of power

            new Item(12846), //Stone of power

            new Item(12847), //Stone of power

            new Item(12848), //Stone of power

            new Item(12849), //Stone of power

            new Item(12860), //Swordfish gloves

            new Item(12863), //Air runecrafting gloves

            new Item(12864), //Water runecrafting gloves

            new Item(12865), //Earth runecrafting gloves

            new Item(12887), //Druidic mage hood 100

            new Item(12888), //Druidic mage hood 80

            new Item(12889), //Druidic mage hood 60

            new Item(12890), //Druidic mage hood 40

            new Item(12891), //Druidic mage hood 20

            new Item(12892), //Druidic mage hood 0

            new Item(12894), //Druidic mage top 100

            new Item(12895), //Druidic mage top 80

            new Item(12896), //Druidic mage top 60

            new Item(12897), //Druidic mage top 40

            new Item(12898), //Druidic mage top 20

            new Item(12899), //Druidic mage top 0

            new Item(12901), //Druidic mage bottom 100

            new Item(12902), //Druidic mage bottom 80

            new Item(12903), //Druidic mage bottom 60

            new Item(12904), //Druidic mage bottom 40

            new Item(12905), //Druidic mage bottom 20

            new Item(12906), //Druidic mage bottom 0

            new Item(12908), //Adamant spikeshield 100

            new Item(12909), //Adamant spikeshield 80

            new Item(12910), //Adamant spikeshield 60

            new Item(12911), //Adamant spikeshield 40

            new Item(12912), //Adamant spikeshield 20

            new Item(12913), //Adamant spikeshield 0

            new Item(12915), //Adamant berserker shield 100

            new Item(12916), //Adamant berserker shield 80

            new Item(12917), //Adamant berserker shield 60

            new Item(12918), //Adamant berserker shield 40

            new Item(12919), //Adamant berserker shield 20

            new Item(12920), //Adamant berserker shield 0

            new Item(12922), //Rune spikeshield 100

            new Item(12923), //Rune spikeshield 80

            new Item(12924), //Rune spikeshield 60

            new Item(12925), //Rune spikeshield 40

            new Item(12926), //Rune spikeshield 20

            new Item(12927), //Rune spikeshield 0

            new Item(12929), //Rune berserker shield 100

            new Item(12930), //Rune berserker shield 80

            new Item(12931), //Rune berserker shield 60

            new Item(12932), //Rune berserker shield 40

            new Item(12933), //Rune berserker shield 20

            new Item(12934), //Rune berserker shield 0

            new Item(12936), //Green d'hide coif 100

            new Item(12937), //Green d'hide coif 80

            new Item(12938), //Green d'hide coif 60

            new Item(12939), //Green d'hide coif 40

            new Item(12940), //Green d'hide coif 20

            new Item(12941), //Green d'hide coif 0

            new Item(12964), //Combat hood 100

            new Item(12965), //Combat hood 80

            new Item(12966), //Combat hood 60

            new Item(12967), //Combat hood 40

            new Item(12968), //Combat hood 20

            new Item(12969), //Combat hood 0

            new Item(12971), //Combat robe top 100

            new Item(12972), //Combat robe top 80

            new Item(12973), //Combat robe top 60

            new Item(12974), //Combat robe top 40

            new Item(12975), //Combat robe top 20

            new Item(12976), //Combat robe top 0

            new Item(12978), //Combat robe bottom 100

            new Item(12979), //Combat robe bottom 80

            new Item(12980), //Combat robe bottom 60

            new Item(12981), //Combat robe bottom 40

            new Item(12982), //Combat robe bottom 20

            new Item(12983), //Combat robe bottom 0

            new Item(12985), //Bronze gauntlets

            new Item(12986), //Worn-out bronze gauntlets

            new Item(12988), //Iron gauntlets

            new Item(12989), //Worn-out iron gauntlets

            new Item(12991), //Steel gauntlets

            new Item(12992), //Worn-out steel gauntlets

            new Item(12994), //Black gauntlets

            new Item(12995), //Worn-out black gauntlets

            new Item(12997), //Mithril gauntlets

            new Item(12998), //Worn-out mithril gauntlets

            new Item(13000), //Adamant gauntlets

            new Item(13001), //Worn-out adamant gauntlets

            new Item(13003), //Rune gauntlets

            new Item(13004), //Worn-out rune gauntlets

            new Item(13469), //Rune axe

            new Item(13471), //Rune battleaxe

            new Item(13473), //Rune warhammer

            new Item(13474), //Rune longsword

            new Item(13476), //Rune scimitar

            new Item(13480), //Rune pickaxe

            new Item(13482), //Rune platebody

            new Item(13483), //Green d'hide body

            new Item(13487), //Rune platelegs

            new Item(13489), //Rune plateskirt

            new Item(13491), //Green d'hide chaps

            new Item(13496), //Rune full helm

            new Item(13497), //Green d'hide vamb

            new Item(13507), //Rune kiteshield

            new Item(13523), //Maple longbow

            new Item(13524), //Maple shortbow

            new Item(13531), //Red partyhat

            new Item(13532), //Yellow partyhat

            new Item(13533), //Blue partyhat

            new Item(13534), //Green partyhat

            new Item(13535), //Purple partyhat

            new Item(13536), //White partyhat

            new Item(13537), //Santa hat

            new Item(13538), //Green h'ween mask

            new Item(13539), //Blue h'ween mask

            new Item(13540), //Red h'ween mask

            new Item(13541), //Willow comp bow

            new Item(13560), //Explorer's ring 1

            new Item(13561), //Explorer's ring 2

            new Item(13562), //Explorer's ring 3

            new Item(13613), //Runecrafter hat

            new Item(13614), //Runecrafter robe

            new Item(13615), //Runecrafter hat

            new Item(13616), //Runecrafter hat

            new Item(13617), //Runecrafter skirt

            new Item(13618), //Runecrafter gloves

            new Item(13619), //Runecrafter robe

            new Item(13620), //Runecrafter hat

            new Item(13621), //Runecrafter hat

            new Item(13622), //Runecrafter skirt

            new Item(13623), //Runecrafter gloves

            new Item(13624), //Runecrafter robe

            new Item(13625), //Runecrafter hat

            new Item(13626), //Runecrafter hat

            new Item(13627), //Runecrafter skirt

            new Item(13628), //Runecrafter gloves

            new Item(13630), //Air talisman staff

            new Item(13631), //Mind talisman staff

            new Item(13632), //Water talisman staff

            new Item(13633), //Earth talisman staff

            new Item(13634), //Fire talisman staff

            new Item(13635), //Body talisman staff

            new Item(13643), //Yellow attractor

            new Item(13644), //Yellow repeller

            new Item(13645), //Green attractor

            new Item(13646), //Green repeller

            new Item(13657), //Runecrafter hat

            new Item(13658), //Runecrafter hat

            new Item(13765), //Rune dagger

            new Item(13777), //Rune sword

            new Item(13778), //Rune 2h sword

            new Item(13780), //Rune mace

            new Item(13781), //Rune chainbody

            new Item(13783), //Rune med helm

            new Item(13787), //Rune sq shield

            new Item(13800), //Rune platebody (g)

            new Item(13801), //Rune platelegs (g)

            new Item(13802), //Rune plateskirt (g)

            new Item(13803), //Rune full helm(g)

            new Item(13804), //Rune kiteshield (g)

            new Item(13805), //Rune platebody (t)

            new Item(13806), //Rune platelegs (t)

            new Item(13807), //Rune plateskirt (t)

            new Item(13808), //Rune full helm (t)

            new Item(13809), //Rune kiteshield (t)

            new Item(13820), //Zamorak platebody

            new Item(13821), //Zamorak platelegs

            new Item(13822), //Zamorak plateskirt

            new Item(13823), //Zamorak full helm

            new Item(13824), //Zamorak kiteshield

            new Item(13825), //Saradomin platebody

            new Item(13826), //Saradomin platelegs

            new Item(13827), //Saradomin plateskirt

            new Item(13828), //Saradomin full helm

            new Item(13829), //Saradomin kiteshield

            new Item(13830), //Guthix platebody

            new Item(13831), //Guthix platelegs

            new Item(13832), //Guthix plateskirt

            new Item(13833), //Guthix full helm

            new Item(13834), //Guthix kiteshield

            new Item(13958), //Corrupt dragon chainbody

            new Item(13960), //Corrupt dragon chainbody (deg)

            new Item(13961), //Corrupt dragon med helm

            new Item(13963), //Corrupt dragon med helm (deg)

            new Item(13964), //Corrupt dragon sq shield

            new Item(13966), //Corrupt dragon sq shield (deg)

            new Item(13967), //Corrupt dragon plateskirt

            new Item(13969), //Corrupt dragon plateskirt (deg)

            new Item(13970), //Corrupt dragon platelegs

            new Item(13972), //Corrupt dragon platelegs (deg)

            new Item(13973), //Corrupt dragon battleaxe

            new Item(13975), //C. dragon battleaxe (deg)

            new Item(13976), //Corrupt dragon dagger

            new Item(13978), //C. dragon dagger (deg)

            new Item(13979), //Corrupt dragon scimitar

            new Item(13981), //C. dragon scimitar (deg)

            new Item(13982), //Corrupt dragon longsword

            new Item(13984), //C. dragon longsword (deg)

            new Item(13985), //Corrupt dragon mace

            new Item(13987), //Corrupt dragon mace (deg)

            new Item(13988), //Corrupt dragon spear

            new Item(13990), //Corrupt dragon spear (deg)

            new Item(14057), //Broomstick

            new Item(14076), //Warlock top

            new Item(14077), //Warlock legs

            new Item(14078), //Witch top

            new Item(14079), //Witch skirt

            new Item(14080), //Witch cloak

            new Item(14081), //Warlock cloak

            new Item(14086), //Witch top

            new Item(14087), //Witch skirt

            new Item(14088), //Witch cloak

            new Item(14595), //Santa costume top

            new Item(14596), //Ice amulet

            new Item(14599), //Ice amulet

            new Item(14600), //Santa costume top

            new Item(14601), //Santa costume top

            new Item(14602), //Santa costume gloves

            new Item(14603), //Santa costume legs

            new Item(14604), //Santa costume legs

            new Item(14605), //Santa costume boots

            new Item(14812), //Black partyhat

            new Item(14819), //Rainbow partyhat

            new Item(14873), //Black santa hat

            new Item(14874)//Inverted santa hat
            ));
}