package org.crandor.game.node.entity.player.ai.pvmbots;

import java.util.ArrayList;

import org.crandor.game.container.impl.EquipmentContainer;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.combat.CombatSpell;
import org.crandor.game.node.entity.player.ai.AIPlayer;
import org.crandor.game.node.entity.player.ai.minigamebots.pestcontrol.PestControlTestBot;
import org.crandor.game.node.entity.player.link.SpellBookManager;
import org.crandor.game.node.entity.player.link.appearance.Gender;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.map.Location;
import org.crandor.tools.RandomFunction;

public final class PvMBotsBuilder{
	
	public static PestControlTestBot createPestControlTestBot(Location l)
	{
		return new PestControlTestBot(l);
	}
	
	public static PvMBots create(Location l)
	{
		return new PvMBots(l);
	}
	
	public static LowestBot createLowest(Location l)
	{
		return new LowestBot(l);
	}
	
	public static NoobBot createNoob(Location l)
	{
		return new NoobBot(l);
	}
	
	public static DragonKiller createDragonKiller(Location l)
	{
		return new DragonKiller(l);
	}
	
	public static GiantMoleBot createGiantMoleBot(Location l)
	{
		return new GiantMoleBot(l);
	}
	
	public static void generateGiantMoleBot(PvMBots p)
	{
		p.getSkills().setLevel(Skills.SLAYER, 99);
		p.getSkills().setStaticLevel(Skills.SLAYER, 99);
		p.getSkills().setLevel(Skills.STRENGTH, 99);
		p.getSkills().setStaticLevel(Skills.STRENGTH, 99);
		p.getSkills().setLevel(Skills.ATTACK, 99);
		p.getSkills().setStaticLevel(Skills.ATTACK, 99);
		p.getSkills().setStaticLevel(Skills.HITPOINTS, 99);
		p.getSkills().setLevel(Skills.HITPOINTS, 99);
		p.getSkills().setStaticLevel(Skills.DEFENCE, 99);
		p.getSkills().setLevel(Skills.DEFENCE, 99);
		p.getSkills().setStaticLevel(Skills.PRAYER, 99);
		p.getSkills().setLevel(Skills.PRAYER, 99);
		
		p.getSkills().updateCombatLevel();
		p.getAppearance().sync();
		
		p.getEquipment().replace(new Item(4720), EquipmentContainer.SLOT_CHEST);
		p.getEquipment().replace(new Item(4722), EquipmentContainer.SLOT_LEGS);
		p.getEquipment().replace(new Item(4716), EquipmentContainer.SLOT_HAT);
		p.getEquipment().replace(new Item(4718), EquipmentContainer.SLOT_WEAPON);
		p.getEquipment().replace(new Item(-1), EquipmentContainer.SLOT_SHIELD);
		
		p.getInventory().add(new Item(952));
		p.getInventory().add(new Item(33));
		
	}
	
	public static void generateMinLevels(PvMBots p) 
	{
		//Slayer so they can attack all monsters
		p.getSkills().setLevel(Skills.SLAYER, 99);
		p.getSkills().setStaticLevel(Skills.SLAYER, 99);
		int combatType = RandomFunction.getRandom(2);
		p.getSkills().setLevel(Skills.HITPOINTS, 15);
		p.getSkills().setStaticLevel(Skills.HITPOINTS, 15);
		p.getInventory().add(new Item(329, 5));
		switch(combatType) 
		{
			case 0:
			{
				buildArcherStats(p);
				buildArcherEquipment(p);
				break;
			}
			case 1:
			{
				buildMeleeStats(p);
				buildMeleeEquipment(p);
				break;
			}
			case 2:
			{
				buildMageStats(p);
				buildMagicEquipment(p);
				setupWizard(p);
				break;
			}
			default:
			{
				buildMeleeStats(p);
				buildMeleeEquipment(p);
				break;
			}
				
		}
	}
	
	public static void createDragonKiller(DragonKiller p)
	{
		p.getSkills().setLevel(Skills.SLAYER, 99);
		p.getSkills().setStaticLevel(Skills.SLAYER, 99);
		int combatType = RandomFunction.getRandom(2);
		p.getSkills().setLevel(Skills.HITPOINTS, 55);
		p.getSkills().setStaticLevel(Skills.HITPOINTS, 55);
		switch(combatType) 
		{
			case 0:
			{
				buildDragonArcherStats(p);
				buildDragonArcherEquipment(p);
				break;
			}
			case 1:
			{
				buildDragonMeleeStats(p);
				buildDragonMeleeEquipment(p);
				break;
			}
			default:
			{
				buildDragonMeleeStats(p);
				buildDragonMeleeEquipment(p);
				break;
			}
		}
	}
	
	private static void buildMaxMeleeStats(AIPlayer p)
	{
		p.getSkills().setLevel(Skills.ATTACK, 99);
		p.getSkills().setStaticLevel(Skills.ATTACK, 99);
		p.getSkills().setLevel(Skills.STRENGTH, 99);
		p.getSkills().setStaticLevel(Skills.STRENGTH, 99);
		p.getSkills().setLevel(Skills.DEFENCE, 99);
		p.getSkills().setStaticLevel(Skills.DEFENCE, 99);

		p.getSkills().updateCombatLevel();
		p.getAppearance().sync();
	}

	private static void buildMaxMeleeEquipment(AIPlayer p) {
		int[] helms = {
				3751, //Berserker Helm
		};
		int[] shield = {
				14767, //Dragon defender
		};
		int[] platebody = {
				11724, //Bandos chainmail.
		};
		int[] platelegs = {
				11726, //Bandos Tassets.
		};
		int[] amulets = {
				6585, //fury
		};
		int[] boots = {
				3105, //Climbing boots.
		};
		int[] cape = {
				6570, //Fire cape.
		};
		int[] gloves = {
				7462, //Barrows Gloves.
		};
		
		int[] weapons2 = {
				4151, //Whip
		};

		int[] arrows = {
			892, //Rune arrow
		};

		p.getEquipment().replace(new Item(helms[RandomFunction.random(helms.length)]), EquipmentContainer.SLOT_HAT);
		p.getEquipment().replace(new Item(shield[RandomFunction.random(shield.length)]), EquipmentContainer.SLOT_SHIELD);
		p.getEquipment().replace(new Item(platebody[RandomFunction.random(platebody.length)]), EquipmentContainer.SLOT_CHEST);
		p.getEquipment().replace(new Item(platelegs[RandomFunction.random(platelegs.length)]), EquipmentContainer.SLOT_LEGS);
		p.getEquipment().replace(new Item(weapons2[RandomFunction.random(weapons2.length)]), EquipmentContainer.SLOT_WEAPON);
		p.getEquipment().replace(new Item(weapons2[RandomFunction.random(arrows.length)], 99999), EquipmentContainer.SLOT_ARROWS);
		p.getEquipment().replace(new Item(amulets[RandomFunction.random(amulets.length)]), EquipmentContainer.SLOT_AMULET);
		p.getEquipment().replace(new Item(boots[RandomFunction.random(boots.length)]), EquipmentContainer.SLOT_FEET);
		p.getEquipment().replace(new Item(cape[RandomFunction.random(cape.length)]), EquipmentContainer.SLOT_CAPE);
		p.getEquipment().replace(new Item(gloves[RandomFunction.random(gloves.length)]), EquipmentContainer.SLOT_HANDS);
	}
	
	public static void createNoob(NoobBot p)
	{
		p.getSkills().setLevel(Skills.SLAYER, 99);
		p.getSkills().setStaticLevel(Skills.SLAYER, 99);
		int combatType = RandomFunction.getRandom(3);
		p.getSkills().setLevel(Skills.HITPOINTS, 10 + RandomFunction.getRandom(10));
		p.getSkills().setStaticLevel(Skills.HITPOINTS, 10 + RandomFunction.getRandom(10));
		p.getSkills().setLevel(Skills.DEFENCE, 1 + RandomFunction.getRandom(9));
		p.getSkills().setStaticLevel(Skills.DEFENCE, 1 + RandomFunction.getRandom(9));
		p.getInventory().add(new Item(329, 5));
		switch(combatType) 
		{
			case 0:
			{
				buildNoobArcherStats(p);
				buildNoobArcherEquipment(p);
				break;
			}
			case 1:
			{
				buildNoobMeleeStats(p);
				buildNoobMeleeEquipment(p);
				break;
			}
			case 2:
			{
				buildNoobMageStats(p);
				buildNoobMagicEquipment(p);
				setupWizard(p);
			}
			default:
			{
				buildNoobMeleeStats(p);
				buildNoobMeleeEquipment(p);
				break;
			}
		}
	}
	
	private static void buildArcherStats(PvMBots p)
	{
		p.getSkills().setLevel(Skills.RANGE, 10);
		p.getSkills().setStaticLevel(Skills.RANGE, 10);
		p.getSkills().setLevel(Skills.DEFENCE, 10);
		p.getSkills().setStaticLevel(Skills.DEFENCE, 10);
		
		p.getSkills().updateCombatLevel();
		p.getAppearance().sync();
	}

	private static void buildDragonArcherStats(DragonKiller p)
	{
		p.getSkills().setStaticLevel(Skills.RANGE, 55 + RandomFunction.getRandom(15));
		p.getSkills().setLevel(Skills.RANGE, p.getSkills().getLevel(Skills.RANGE));
		
		p.getSkills().updateCombatLevel();
		p.getAppearance().sync();
	}
	
	private static void buildNoobArcherStats(NoobBot p)
	{
		p.getSkills().setLevel(Skills.RANGE, 1 + RandomFunction.getRandom(10));
		p.getSkills().setStaticLevel(Skills.RANGE, 1 + RandomFunction.getRandom(10));
		
		p.getSkills().updateCombatLevel();
		p.getAppearance().sync();
	}
	
	public static void buildMeleeStats(PvMBots p)
	{
		p.getSkills().setLevel(Skills.ATTACK, 10);
		p.getSkills().setStaticLevel(Skills.ATTACK, 10);
		p.getSkills().setLevel(Skills.STRENGTH, 10);
		p.getSkills().setStaticLevel(Skills.STRENGTH, 10);
		p.getSkills().setLevel(Skills.DEFENCE, 10);
		p.getSkills().setStaticLevel(Skills.DEFENCE, 10);
		
		p.getSkills().updateCombatLevel();
		p.getAppearance().sync();
	}
	
	public static void buildDragonMeleeStats(DragonKiller p)
	{
		
		p.getSkills().setStaticLevel(Skills.ATTACK, 55 + RandomFunction.getRandom(15));
		p.getSkills().setLevel(Skills.ATTACK, p.getSkills().getLevel(Skills.ATTACK));
		p.getSkills().setStaticLevel(Skills.STRENGTH, 55 + RandomFunction.getRandom(15));
		p.getSkills().setLevel(Skills.STRENGTH, p.getSkills().getLevel(Skills.STRENGTH));
		p.getSkills().setLevel(Skills.DEFENCE, 45 + RandomFunction.getRandom(15));
		p.getSkills().setLevel(Skills.DEFENCE, p.getSkills().getLevel(Skills.DEFENCE));
		
		p.getSkills().updateCombatLevel();
		p.getAppearance().sync();
	}
	
	private static void buildNoobMeleeStats(NoobBot p)
	{
		p.getSkills().setLevel(Skills.ATTACK, 1 + RandomFunction.getRandom(10));
		p.getSkills().setStaticLevel(Skills.ATTACK, 1 + RandomFunction.getRandom(10));
		p.getSkills().setLevel(Skills.STRENGTH, 1 + RandomFunction.getRandom(10));
		p.getSkills().setStaticLevel(Skills.STRENGTH, 1 + RandomFunction.getRandom(10));
		
		p.getSkills().updateCombatLevel();
		p.getAppearance().sync();
	}
	
	private static void buildMageStats(PvMBots p)
	{
		p.getSkills().setLevel(Skills.MAGIC, 10);
		p.getSkills().setStaticLevel(Skills.MAGIC, 10);
		p.getSkills().setLevel(Skills.DEFENCE, 10);
		p.getSkills().setStaticLevel(Skills.DEFENCE, 10);
		
		p.getSkills().updateCombatLevel();
		p.getAppearance().sync();
	}
	
	private static void buildNoobMageStats(NoobBot p)
	{
		p.getSkills().setLevel(Skills.MAGIC, 1 + RandomFunction.getRandom(10));
		p.getSkills().setStaticLevel(Skills.MAGIC, 1 + RandomFunction.getRandom(10));
		
		p.getSkills().updateCombatLevel();
		p.getAppearance().sync();
	}
	
	private static void setupWizard(PvMBots p)
	{
			//final int SPELL_IDS[] = new int[] {1, 4, 6, 8, 10, 14, 17, 20, 24, 27, 33, 38, 45, 48, 52, 55 };
			p.getProperties().setAutocastSpell(((CombatSpell) SpellBookManager.SpellBook.MODERN.getSpell(1)));
			p.getInventory().add(new Item(556, 1000)); //Air runes
			p.getInventory().add(new Item(558, 1000)); //Mind runes
	}
	
	private static void buildArcherEquipment(PvMBots p)
	{
		p.getEquipment().replace(new Item(1169), EquipmentContainer.SLOT_HAT);
		p.getEquipment().replace(new Item(1129), EquipmentContainer.SLOT_CHEST);
		p.getEquipment().replace(new Item(1095), EquipmentContainer.SLOT_LEGS);
		p.getEquipment().replace(new Item(841), EquipmentContainer.SLOT_WEAPON);
		p.getEquipment().replace(new Item(884, 5000), EquipmentContainer.SLOT_ARROWS);
		p.getEquipment().replace(new Item(1007), EquipmentContainer.SLOT_CAPE);
		p.getEquipment().replace(new Item(1478), EquipmentContainer.SLOT_AMULET);
	}
	
	private static void buildDragonArcherEquipment(DragonKiller p)
	{
		p.getEquipment().replace(new Item(1169), EquipmentContainer.SLOT_HAT);
		p.getEquipment().replace(new Item(13483), EquipmentContainer.SLOT_CHEST);
		p.getEquipment().replace(new Item(1099), EquipmentContainer.SLOT_LEGS);
		p.getEquipment().replace(new Item(9185), EquipmentContainer.SLOT_WEAPON);
		p.getEquipment().replace(new Item(1540), EquipmentContainer.SLOT_SHIELD);
		p.getEquipment().replace(new Item(9140, 500), EquipmentContainer.SLOT_ARROWS);
		p.getEquipment().replace(new Item(10498), EquipmentContainer.SLOT_CAPE);
		p.getEquipment().replace(new Item(1478), EquipmentContainer.SLOT_AMULET);
	}
	
	private static void buildNoobArcherEquipment(PvMBots p)
	{
		int hats[] = {1169, 1169, 1139, 1137, 1153, 579};
		int legs[] = {1095, 7366, 1075,  1087, 1095};
		int chest[] = {1129, 1103, 1101, 1129, 1117, 577};
		
		p.getEquipment().replace(new Item(hats[RandomFunction.getRandom(hats.length - 1)]), EquipmentContainer.SLOT_HAT);
		p.getEquipment().replace(new Item(chest[RandomFunction.getRandom(chest.length - 1)]), EquipmentContainer.SLOT_CHEST);
		p.getEquipment().replace(new Item(legs[RandomFunction.getRandom(legs.length - 1)]), EquipmentContainer.SLOT_LEGS);
		p.getEquipment().replace(new Item(841), EquipmentContainer.SLOT_WEAPON);
		p.getEquipment().replace(new Item(884, 5000), EquipmentContainer.SLOT_ARROWS);
		p.getEquipment().replace(new Item(1007), EquipmentContainer.SLOT_CAPE);
		p.getEquipment().replace(new Item(1478), EquipmentContainer.SLOT_AMULET);
	}
	
	private static void buildMeleeEquipment(PvMBots p)
	{
		p.getEquipment().replace(new Item(1153), EquipmentContainer.SLOT_HAT);
		p.getEquipment().replace(new Item(1115), EquipmentContainer.SLOT_CHEST);
		p.getEquipment().replace(new Item(1067), EquipmentContainer.SLOT_LEGS);
		p.getEquipment().replace(new Item(1309), EquipmentContainer.SLOT_WEAPON);
		p.getEquipment().replace(new Item(884), EquipmentContainer.SLOT_ARROWS);
		p.getEquipment().replace(new Item(1007), EquipmentContainer.SLOT_CAPE);
		p.getEquipment().replace(new Item(1725), EquipmentContainer.SLOT_AMULET);
	}
	
	private static void buildDragonMeleeEquipment(DragonKiller p)
	{
		p.getEquipment().replace(new Item(1163), EquipmentContainer.SLOT_HAT);
		p.getEquipment().replace(new Item(1127), EquipmentContainer.SLOT_CHEST);
		p.getEquipment().replace(new Item(1079), EquipmentContainer.SLOT_LEGS);
		p.getEquipment().replace(new Item(1333), EquipmentContainer.SLOT_WEAPON);
		p.getEquipment().replace(new Item(1540), EquipmentContainer.SLOT_SHIELD);
		p.getEquipment().replace(new Item(1007), EquipmentContainer.SLOT_CAPE);
		p.getEquipment().replace(new Item(1725), EquipmentContainer.SLOT_AMULET);
	}
	
	private static void buildNoobMeleeEquipment(PvMBots p)
	{
		int hats[] = {1169, 1169, 1139, 1137, 1153, 579};
		int legs[] = {1095, 7366, 1075,  1087, 1095};
		int chest[] = {1129, 1103, 1101, 1129, 1117, 577};
		int weapons[] = {1307, 1321, 1375, 1203, 1239, 1267, 1293, 1323, 1335};
		
		p.getEquipment().replace(new Item(hats[RandomFunction.getRandom(hats.length - 1)]), EquipmentContainer.SLOT_HAT);
		p.getEquipment().replace(new Item(chest[RandomFunction.getRandom(chest.length - 1)]), EquipmentContainer.SLOT_CHEST);
		p.getEquipment().replace(new Item(legs[RandomFunction.getRandom(legs.length - 1)]), EquipmentContainer.SLOT_LEGS);
		p.getEquipment().replace(new Item(weapons[RandomFunction.getRandom(weapons.length - 1)]), EquipmentContainer.SLOT_WEAPON);
		p.getEquipment().replace(new Item(884), EquipmentContainer.SLOT_ARROWS);
		p.getEquipment().replace(new Item(1007), EquipmentContainer.SLOT_CAPE);
		p.getEquipment().replace(new Item(1725), EquipmentContainer.SLOT_AMULET);
	}
	
	private static void buildMagicEquipment(PvMBots p)
	{
		p.getEquipment().replace(new Item(579), EquipmentContainer.SLOT_HAT);
		p.getEquipment().replace(new Item(577), EquipmentContainer.SLOT_CHEST);
		p.getEquipment().replace(new Item(1011), EquipmentContainer.SLOT_LEGS);
		p.getEquipment().replace(new Item(1389), EquipmentContainer.SLOT_WEAPON);
		p.getEquipment().replace(new Item(884), EquipmentContainer.SLOT_ARROWS);
		p.getEquipment().replace(new Item(1007), EquipmentContainer.SLOT_CAPE);
		p.getEquipment().replace(new Item(1727), EquipmentContainer.SLOT_AMULET);
	}
	
	private static void buildNoobMagicEquipment(PvMBots p)
	{
		int hats[] = {1169, 1169, 1139, 1137, 1153, 579};
		int legs[] = {1095, 7366, 1075,  1087, 1095};
		int chest[] = {1129, 1103, 1101, 1129, 1117, 577};
		int weapons[] = {1379, 1383, 1385, 1387, 1389}; 
		
		p.getEquipment().replace(new Item(hats[RandomFunction.getRandom(hats.length - 1)]), EquipmentContainer.SLOT_HAT);
		p.getEquipment().replace(new Item(chest[RandomFunction.getRandom(chest.length - 1)]), EquipmentContainer.SLOT_CHEST);
		p.getEquipment().replace(new Item(legs[RandomFunction.getRandom(legs.length - 1)]), EquipmentContainer.SLOT_LEGS);
		p.getEquipment().replace(new Item(weapons[RandomFunction.getRandom(weapons.length - 1)]), EquipmentContainer.SLOT_WEAPON);
		p.getEquipment().replace(new Item(884), EquipmentContainer.SLOT_ARROWS);
		p.getEquipment().replace(new Item(1007), EquipmentContainer.SLOT_CAPE);
		p.getEquipment().replace(new Item(1727), EquipmentContainer.SLOT_AMULET);
	}
	
	public static void spawn(Location loc)
	{
		final PvMBots bot = PvMBotsBuilder.create(loc);
		bot.getAppearance().setGender(RandomFunction.random(3) == 1 ? Gender.FEMALE : Gender.MALE);
	}
	
	public static void spawnLowest(Location loc)
	{
		final LowestBot bot = PvMBotsBuilder.createLowest(loc);

		generateMinLevels(bot);
	}
	
	public static void spawnNoob(Location loc)
	{
		final NoobBot  bot = PvMBotsBuilder.createNoob(loc);

		createNoob(bot);
	}
	
	public static void spawnDragonKiller(Location loc)
	{
		final DragonKiller bot = PvMBotsBuilder.createDragonKiller(loc);
		bot.getAppearance().setGender(RandomFunction.random(3) == 1 ? Gender.FEMALE : Gender.MALE);


		
		createDragonKiller(bot);
	}
	
	public static void spawnGiantMoleBot(Location loc)
	{
		final GiantMoleBot bot = PvMBotsBuilder.createGiantMoleBot(new Location(0, 0));
		bot.teleport(loc);
		bot.getAppearance().setGender(RandomFunction.random(3) == 1 ? Gender.FEMALE : Gender.MALE);


		
		generateGiantMoleBot(bot);
	}
	
	public static void immersiveSpawns()
	{
		//Lumbridge
		//GOBLINS
		spawnLowest(new Location(3259, 3224));
		spawnLowest(new Location(3251, 3235));
		spawnLowest(new Location(3244, 3247));
		spawnNoob(new Location(3244, 3247));
		//COWS
		spawnLowest(new Location(3259, 3245));
		spawnLowest(new Location(3258, 3261));
		spawnLowest(new Location(3263, 3263));
		spawnLowest(new Location(3263, 3271));
		spawnLowest(new Location(3254, 3288));
		//CHICKENS
		spawnLowest(new Location(3234, 3294));
		spawnLowest(new Location(3229, 3297));
		//GOBLINS2
		spawnLowest(new Location(3182, 3255));
		spawnLowest(new Location(3169, 3254));
		spawnLowest(new Location(3167, 3233));
		spawnLowest(new Location(3152, 3228));
		//Lumby castle
		spawnLowest(new Location(3221, 3219));
		spawnNoob(new Location(3193, 3208));
		//Lumby swamp
		spawnNoob(new Location(3224, 3186));
		spawnNoob(new Location(3217, 3177));
		spawnNoob(new Location(3209, 3191));
		
		//Edge
		spawnLowest(new Location(3096, 3509));
		
		//Edge dungeon
		//Hill giants
		spawnLowest(new Location(3118, 9848));
		spawnLowest(new Location(3110, 9842));
		spawnLowest(new Location(3115, 9836));
		spawnLowest(new Location(3107, 9832));
		spawnLowest(new Location(3099, 9834));
		//brass key room
		spawnLowest(new Location(3127, 9863));
		
		//Dark wizzards
		spawnLowest(new Location(3226, 3367));
		spawnLowest(new Location(3226, 3368));
		spawnLowest(new Location(3230, 3373));
		spawnLowest(new Location(3209, 3378));
		
		//Varrock palace
		spawnLowest(new Location(3218, 3465));
		spawnLowest(new Location(3209, 3462));
		
		//Varrock Sewers
		spawnLowest(new Location(3235, 9868));
		spawnLowest(new Location(3242, 9915));
		spawnLowest(new Location(3176, 9883));
		
		//Dragons Wilderness
		//WEST
		spawnDragonKiller(new Location(2977, 3614));
		spawnDragonKiller(new Location(2987, 3619));
		
		//Burthrope Dungeon
		//Blue dragons
		spawnDragonKiller(new Location(2901, 9799));
		spawnDragonKiller(new Location(2910, 9804));
		//Al kharid
		//palace
		spawnLowest(new Location(3301, 3173));
		spawnNoob(new Location(3293, 3170));
		spawnNoob(new Location(3286, 3171));
		spawnNoob(new Location(3289, 3169));
		
		//Slayer Tower
		spawnLowest(new Location(3412, 3173));
		spawnNoob(new Location(3412, 3539));
		spawnNoob(new Location(3420, 3545));
		spawnNoob(new Location(3420, 3545));
		spawnNoob(new Location(3420, 3558));
		spawnNoob(new Location(3433, 3571));
		//Replace with something like rune bots
		spawnDragonKiller(new Location(3437, 3562, 1));
		spawnDragonKiller(new Location(3437, 3562, 1));
		spawnDragonKiller(new Location(3437, 3562, 1));
		//Bloodvelds
		spawnDragonKiller(new Location(3417, 3561, 1));
		spawnDragonKiller(new Location(3417, 3561, 1));
		spawnDragonKiller(new Location(3417, 3561, 1));
		//Brimhaven dragons
		spawnDragonKiller(new Location(2704, 9450));
		spawnDragonKiller(new Location(2704, 9450));
	}
	public void randomItem()
	{
		Item test = null;
		ArrayList<Item> tests = new ArrayList<Item>();
		for (int x = 0; x < 9999; x++)
			test = new Item(x);
			if (test.getDefinition().getEquipId() != 0)
				tests.add(test);
	}
}
