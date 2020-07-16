package plugin.ai.wilderness;

import java.util.ArrayList;

import core.game.container.impl.EquipmentContainer;
import plugin.skill.Skills;
import core.game.node.entity.combat.CombatSpell;
import plugin.ai.AIPlayer;
import plugin.ai.pvmbots.DragonKiller;
import plugin.ai.pvmbots.NoobBot;
import plugin.ai.pvmbots.PvMBots;
import core.game.node.entity.player.link.SpellBookManager;
import core.game.node.entity.player.link.appearance.Gender;
import core.game.node.item.Item;
import core.game.world.map.Location;
import core.tools.RandomFunction;

public final class PvPBotsBuilder{
	
	public static WildernessBot create(Location l)
	{
		return new WildernessBot(l);
	}
	
	public static void generateClass(WildernessBot p) {
		int combatType = RandomFunction.getRandom(1);
		switch(combatType) {
		case 0:
			buildMeleeStats(p);
			buildMeleeEquipment(p);
			correctHitpointsStat(p);
			break;
		case 1:
			//max rune
			buildMaxMeleeStats(p);
			buildMaxMeleeEquipment(p);
			correctHitpointsStat(p);
			break;
		}
		p.getInventory().add(new Item(385, 20));
		p.getProperties().setCombatLevel(126);

		p.getSkills().setLevel(Skills.PRAYER, 98);
		p.getSkills().setStaticLevel(Skills.PRAYER, 98);
		p.getSkills().updateCombatLevel();
		p.getAppearance().sync();
	}


	private static void buildMeleeStats(WildernessBot p) {
		int attackLevel = RandomFunction.random(80, 98);
		int strengthLevel = RandomFunction.random(attackLevel - 10, attackLevel + 10);
		int defenceLevel = RandomFunction.random(60, 98);

		p.getSkills().setLevel(Skills.ATTACK, attackLevel);
		p.getSkills().setStaticLevel(Skills.ATTACK, attackLevel);
		p.getSkills().setLevel(Skills.STRENGTH, strengthLevel);
		p.getSkills().setStaticLevel(Skills.STRENGTH, strengthLevel);
		p.getSkills().setLevel(Skills.DEFENCE, defenceLevel);
		p.getSkills().setStaticLevel(Skills.DEFENCE, defenceLevel);

		p.getSkills().updateCombatLevel();
		p.getAppearance().sync();
	}

	private static void buildMeleeEquipment(WildernessBot p) {
		int[] helms = {
				1163, //Rune Full Helm
				3751, //Berserker Helm
				3753, //Warrior Helm
				10828,
				1038,
				4753,
		};
		int[] shield = {
				1185, //Rune sq shield
				1201, //Rune kiteshield
				6524, //Obsidian shield
				8850, //Rune defender
				11286,
				14767,
		};
		int[] platebody = {
				1113, //Rune chainmail.
				1127, //Rune platebody.
				10551,
				4757,
				11724,
				4720,
		};
		int[] platelegs = {
				1079, //Rune platelegs.
				4759,
				11726,
				1075,
		};
		
		p.getEquipment().replace(new Item(helms[RandomFunction.random(helms.length)]), EquipmentContainer.SLOT_HAT);
		p.getEquipment().replace(new Item(shield[RandomFunction.random(shield.length)]), EquipmentContainer.SLOT_SHIELD);
		p.getEquipment().replace(new Item(platebody[RandomFunction.random(platebody.length)]), EquipmentContainer.SLOT_CHEST);
		p.getEquipment().replace(new Item(platelegs[RandomFunction.random(platelegs.length)]), EquipmentContainer.SLOT_LEGS);
		
		Item weapon = new Item(weapons[RandomFunction.random(weapons.length)]);
		p.setMainWeapon(weapon.getId());
		p.getEquipment().replace(weapon, EquipmentContainer.SLOT_WEAPON);
		
		p.getEquipment().replace(new Item(generateNecklace()), EquipmentContainer.SLOT_AMULET);
		p.getEquipment().replace(new Item(generateBoots()), EquipmentContainer.SLOT_FEET);
		p.getEquipment().replace(new Item(generateCape()), EquipmentContainer.SLOT_CAPE);
		p.getEquipment().replace(new Item(generateGloves()), EquipmentContainer.SLOT_HANDS);
	}

	public static final int[] weapons = {
			4151, //Whip
			4587, //Dragon Scimitar
			
	};


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
		p.getEquipment().replace(new Item(helms[RandomFunction.random(helms.length)]), EquipmentContainer.SLOT_HAT);
		p.getEquipment().replace(new Item(shield[RandomFunction.random(shield.length)]), EquipmentContainer.SLOT_SHIELD);
		p.getEquipment().replace(new Item(platebody[RandomFunction.random(platebody.length)]), EquipmentContainer.SLOT_CHEST);
		p.getEquipment().replace(new Item(platelegs[RandomFunction.random(platelegs.length)]), EquipmentContainer.SLOT_LEGS);
		p.getEquipment().replace(new Item(weapons2[RandomFunction.random(weapons2.length)]), EquipmentContainer.SLOT_WEAPON);
		p.getEquipment().replace(new Item(amulets[RandomFunction.random(amulets.length)]), EquipmentContainer.SLOT_AMULET);
		p.getEquipment().replace(new Item(boots[RandomFunction.random(boots.length)]), EquipmentContainer.SLOT_FEET);
		p.getEquipment().replace(new Item(cape[RandomFunction.random(cape.length)]), EquipmentContainer.SLOT_CAPE);
		p.getEquipment().replace(new Item(gloves[RandomFunction.random(gloves.length)]), EquipmentContainer.SLOT_HANDS);
	}

	public static final int[] weapons2 = {
			4151, //Whip
	};

	private static int generateCape() {
		int[] capes = {
				1052, //Legends Cape
				6570, //Fire cape
		};
		return capes[RandomFunction.random(capes.length)];
	}

	private static int generateNecklace() {
		int[] amulets = {
				1712, //Glory
				1725, //Strength ammy
				6585, //fury
		};
		return amulets[RandomFunction.random(amulets.length)];
	}

	private static int generateGloves() {
		int[] gloves = {
			1059,
			7462
		};
		return gloves[RandomFunction.random(gloves.length)];
	}

	private static int generateBoots() {
		int[] boots = {
				1061, //Leather boots.
				1837, //Desert boots.
				3105, //Climbing boots.
				3791, //Fremmy boots.
				11732,
		};
		return boots[RandomFunction.random(boots.length)];
	}
	
	public static void generateMinLevels(PvMBots p) 
	{
		//Slayer so they can attack alls monsters
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
		p.getSkills().setLevel(Skills.RANGE, 55);
		p.getSkills().setStaticLevel(Skills.RANGE, 55);
		
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
		p.getSkills().setLevel(Skills.ATTACK, 55);
		p.getSkills().setStaticLevel(Skills.ATTACK, 55);
		p.getSkills().setLevel(Skills.STRENGTH, 55);
		p.getSkills().setStaticLevel(Skills.STRENGTH, 55);
		p.getSkills().setLevel(Skills.DEFENCE, 45);
		p.getSkills().setStaticLevel(Skills.DEFENCE, 45);
		
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
		final WildernessBot bot = PvPBotsBuilder.create(new Location(0, 0));
		bot.teleport(loc);
		bot.getAppearance().setGender(RandomFunction.random(3) == 1 ? Gender.FEMALE : Gender.MALE);

		generateClass(bot);

	}
	
	private static void correctHitpointsStat(AIPlayer player) {
		player.getSkills().setLevel(Skills.HITPOINTS, 10);
		player.getSkills().setStaticLevel(Skills.HITPOINTS, 10);
		player.getSkills().updateCombatLevel();
			int rangedLevel = RandomFunction.random(80, 98);
			int defenceLevel = RandomFunction.random(80, 98);

			player.getSkills().setLevel(Skills.HITPOINTS, rangedLevel);
			player.getSkills().setStaticLevel(Skills.HITPOINTS, rangedLevel);
			player.getSkills().setLevel(Skills.HITPOINTS, defenceLevel);
			player.getSkills().setStaticLevel(Skills.HITPOINTS, defenceLevel);

			player.getSkills().updateCombatLevel();
			player.getAppearance().sync();
	}
	
	public void RandomItem()
	{
		Item test = null;
		ArrayList<Item> tests = new ArrayList<Item>();
		for (int x = 0; x < 9999; x++)
			test = new Item(x);
			if (test.getDefinition().getEquipId() != 0)
				tests.add(test);
	}
}
