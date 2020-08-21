package plugin.ai.skillingbot;

import java.util.ArrayList;

import core.game.container.impl.EquipmentContainer;
import plugin.quest.tutorials.tutorialisland.CharacterDesign;
import plugin.skill.Skills;
import plugin.ai.AIPlayer;
import core.game.node.entity.player.link.appearance.Gender;
import core.game.node.item.Item;
import core.game.world.map.Location;
import core.tools.RandomFunction;

public final class SkillingBotsBuilder extends AIPlayer {

	public SkillingBotsBuilder(Location l) {
		super(l);
		// TODO Auto-generated constructor stub
	}
	
	private static SkillingBot generateMiningBot(Location loc, ArrayList<Integer> entrys)
	{
		SkillingBot bot = new SkillingBot(loc, Skills.MINING, entrys);
		bot.getAppearance().setGender(RandomFunction.random(3) == 1 ? Gender.FEMALE : Gender.MALE);


		bot.getEquipment().replace(new Item(1265), EquipmentContainer.SLOT_WEAPON);
		return bot;
	}
	
	private static SkillingBot generateWoodcuttingBot(Location loc, ArrayList<Integer> entrys)
	{
		SkillingBot bot = new SkillingBot(loc, Skills.WOODCUTTING, entrys);
		bot.getAppearance().setGender(RandomFunction.random(3) == 1 ? Gender.FEMALE : Gender.MALE);


		bot.getEquipment().replace(new Item(1351), EquipmentContainer.SLOT_WEAPON);
		return bot;
	}
	
	private static SkillingBot generateFishingBot(Location loc, ArrayList<Integer> entrys)
	{
		SkillingBot bot = new SkillingBot(loc, Skills.FISHING, entrys);
		bot.getAppearance().setGender(RandomFunction.random(3) == 1 ? Gender.FEMALE : Gender.MALE);
        CharacterDesign.randomize(bot, false);


		return bot;
	}
	
	public static void spawnClayBotVarrock(Location loc)
	{
		ArrayList<Integer> entrys = new ArrayList<Integer>();
		entrys.add(15503);
		entrys.add(15505);
		
		SkillingBot bot = generateMiningBot(loc, entrys);
		
		bot.getSkills().setLevel(Skills.MINING, 10);
	}
	
	public static void spawnIronBotVarrock(Location loc)
	{
		ArrayList<Integer> entrys = new ArrayList<Integer>();
		entrys.add(11954);
		entrys.add(11955);
		entrys.add(11956);
		
		SkillingBot bot = generateMiningBot(loc, entrys);
		
		bot.getSkills().setLevel(Skills.MINING, 25);
	}
	
	public static void spawnSilverBotVarrock(Location loc)
	{
		ArrayList<Integer> entrys = new ArrayList<Integer>();
		entrys.add(11948);
		entrys.add(11949);
		entrys.add(11950);
		
		SkillingBot bot = generateMiningBot(loc, entrys);
		
		bot.getSkills().setLevel(Skills.MINING, 30);
	}
	
	public static void spawnTinBotVarrock(Location loc)
	{
		ArrayList<Integer> entrys = new ArrayList<Integer>();
		entrys.add(11957);
		entrys.add(11959);
		entrys.add(11958);
		
		SkillingBot bot = generateMiningBot(loc, entrys);
		
		bot.getSkills().setLevel(Skills.MINING, 5);
	}
	
	public static void spawnTinBotLumbridge(Location loc)
	{
		ArrayList<Integer> entrys = new ArrayList<Integer>();
		entrys.add(11933);
		entrys.add(11934);
		entrys.add(11935);
		
		SkillingBot bot = generateMiningBot(loc, entrys);
		
		bot.getSkills().setLevel(Skills.MINING, 5);
	}
	
	public static void spawnCopperBotLumbridge(Location loc)
	{
		ArrayList<Integer> entrys = new ArrayList<Integer>();
		entrys.add(11936);
		entrys.add(11937);
		entrys.add(11938);
		
		SkillingBot bot = generateMiningBot(loc, entrys);
		
		bot.getSkills().setLevel(Skills.MINING, 5);
	}
	
	public static void spawnOakTreeBotLumbridge(Location loc)
	{
		ArrayList<Integer> entrys = new ArrayList<Integer>();
		entrys.add(1281);
		entrys.add(1278);
		entrys.add(1276);
		
		SkillingBot bot = generateWoodcuttingBot(loc, entrys);
		
		bot.getSkills().setLevel(Skills.WOODCUTTING, 25);
		bot.setInteractionRange(25);
	}
	
	public static void spawnDeadTreeBotLumbridge(Location loc)
	{
		ArrayList<Integer> entrys = new ArrayList<Integer>();
		entrys.add(1282);
		entrys.add(1286);
		
		SkillingBot bot = generateWoodcuttingBot(loc, entrys);
		
		bot.getSkills().setLevel(Skills.WOODCUTTING, 25);
		bot.setInteractionRange(15);
	}
	
	public static void spawnShrimpFisherLumbridge(Location loc)
	{
		ArrayList<Integer> entrys = new ArrayList<Integer>();
		entrys.add(323);
		entrys.add(326);
		
		SkillingBot bot = generateFishingBot(loc, entrys);
		
		bot.getInventory().add(new Item(303));
		// don't drop net
		bot.setFromWhereDoIdrop(1);
		
		bot.getSkills().setLevel(Skills.FISHING, 25);
		bot.setInteractionRange(25);
	}
	
	public static void spawnTroutLumbridge(Location loc)
	{
		ArrayList<Integer> entrys = new ArrayList<Integer>();
		entrys.add(310);
		
		SkillingBot bot = generateFishingBot(loc, entrys);
		
		bot.getInventory().add(new Item(309));
		bot.getInventory().add(new Item(314, 20000));
		// don't drop net
		bot.setFromWhereDoIdrop(2);
		
		bot.getSkills().setLevel(Skills.FISHING, 25);
		bot.setInteractionRange(25);
	}

	//These bots are disabled because they somehow break pets
	public static void immersiveSpawnsSkillingBots()
	{
		// Varrock Mine
		SkillingBotsBuilder.spawnClayBotVarrock(new Location(3181, 3368));
		SkillingBotsBuilder.spawnSilverBotVarrock(new Location(3181, 3368));
		SkillingBotsBuilder.spawnIronBotVarrock(new Location(3181, 3368));
		SkillingBotsBuilder.spawnTinBotVarrock(new Location(3181, 3368));
		
		// Lumbridge woodcutting
		spawnOakTreeBotLumbridge(new Location(3227, 3243));
		spawnOakTreeBotLumbridge(new Location(3186, 3251));
		spawnOakTreeBotLumbridge(new Location(3188, 3223));
		spawnOakTreeBotLumbridge(new Location(3162, 3222));
		spawnOakTreeBotLumbridge(new Location(3162, 3219));
		spawnOakTreeBotLumbridge(new Location(3152, 3228));
		spawnOakTreeBotLumbridge(new Location(3146, 3244));
		spawnDeadTreeBotLumbridge(new Location(3247, 3240));
		
		// Lumbridge mining
		spawnTinBotLumbridge(new Location(3224, 3147));
		spawnCopperBotLumbridge(new Location(3224, 3147));
		
		// Lumbridge Fishing
		spawnShrimpFisherLumbridge(new Location(3242, 3151));
		spawnShrimpFisherLumbridge(new Location(3238, 3148));
		spawnShrimpFisherLumbridge(new Location(3245, 3161));
		spawnTroutLumbridge(new Location(3241, 3242));
	}
	
}
