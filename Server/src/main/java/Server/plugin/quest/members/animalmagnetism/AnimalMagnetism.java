package plugin.quest.members.animalmagnetism;

import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.plugin.PluginManager;

import plugin.quest.members.animalmagnetism.AnimalMagnetismPlugin.ContainerHandler;
import plugin.quest.members.animalmagnetism.AnimalMagnetismPlugin.HammerMagnetPlugin;
import plugin.quest.members.animalmagnetism.AnimalMagnetismPlugin.ResearchNoteHandler;
import core.plugin.InitializablePlugin;
import plugin.quest.members.animalmagnetism.AnimalMagnetismPlugin.UndeadTreePlugin;

/**
 * Handles the animal magnetism quest.
 * @author Vexia
 */
@InitializablePlugin
public final class AnimalMagnetism extends Quest {

	/**
	 * The name of this quest.
	 */
	public static String NAME = "Animal Magnetism";

	/**
	 * The crone made amulet item.
	 */
	public static final Item CRONE_AMULET = new Item(10500);

	/**
	 * The selected iron item.
	 */
	public static final Item SELECTED_IRON = new Item(10488);

	/**
	 * The bar magnet item.
	 */
	public static final Item BAR_MAGNET = new Item(10489);

	/**
	 * The undead twigs item.
	 */
	public static final Item UNDEAD_TWIGS = new Item(10490);

	/**
	 * The blessed axe item.
	 */
	public static final Item BLESSED_AXE = new Item(10491);

	/**
	 * The research notes.
	 */
	public static final Item RESEARCH_NOTES = new Item(10492);

	/**
	 * The translated notes.
	 */
	public static final Item TRANSLATED_NOTES = new Item(10493);

	/**
	 * The patern item.
	 */
	public static final Item PATTERN = new Item(10494);

	/**
	 * The container item.
	 */
	public static final Item CONTAINER = new Item(10495);

	/**
	 * The polished items.
	 */
	public static final Item POLISHED_BUTTONS = new Item(10496);

	/**
	 * The hard leather item.
	 */
	public static final Item HARD_LEATHER = new Item(1743);

	/**
	 * The avas attractor item.
	 */
	public static final Item AVAS_ATTRACTOR = new Item(10498);

	/**
	 * The avas accumulator item.
	 */
	public static final Item AVAS_ACCUMULATOR = new Item(10499);

	/**
	 * The requirements messages.
	 */
	private static final String[] REQS = new String[] { "I must have completed Restless Ghost.", "I must have completed Ernest the Chicken", "I must have completed Priest in Peril.", "Level 30 Ranged", "Level 18 Slayer", "Level 19 Crafting", "Level 35 Wooductting" };

	/**
	 * The requirements.
	 */
	private boolean[] requirements = new boolean[7];

	/**
	 * Constructs a new {@code AnimalMagnetism} {@code Object}.
	 * @param player the player.
	 */
	public AnimalMagnetism() {
		super("Animal Magnetism", 33, 32, 1);
	}
	
	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugin(new AvaDialogue());
		PluginManager.definePlugin(new AliceDialogue());
		PluginManager.definePlugin(new WitchDialogue());
		PluginManager.definePlugin(new ContainerHandler());
		PluginManager.definePlugin(new UndeadTreePlugin());
		PluginManager.definePlugin(new AvasDevicePlugin());
		PluginManager.definePlugin(new HammerMagnetPlugin());
		PluginManager.definePlugin(new ResearchNoteHandler());
		PluginManager.definePlugin(new AliceHusbandDialogue());
		PluginManager.definePlugin(new AnimalMagnetismPlugin());
		return this;
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (stage) {
		case 0:
			line(player, "<blue>I can start this quest by talking to", 4+ 7);
			line(player, "<red>Ava <blue>who lives in <red>Draynor Manor.", 5+ 7);
			line(player, "<blue>Minimum requirements:", 7+ 7);
			drawRequirements(player);
			break;
		case 10:
			line(player, "<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward<br><br><blue>making her bed more comfortable, the other will be used in<br><br><blue>some unexplained reward for me.<br><br><blue>I need to find someone who will supply <red>undead chickens <blue>to<br><br><blue>me. Perhaps the <red>farm near Port Phasmatys <blue>sells them...", 4+ 7);
			break;
		case 11:
			line(player, "<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward<br><br><blue>making her bed more comfortable, the other will be used in<br><br><blue>some unexplained reward for me.<br><br><str>I need to find someone who will supply undead chickens to<br><br><str>me. Perhaps the farm near Port Phasmatys sells them...<br><br><blue>The<red> ghost farmer<blue> wants me to talk to his <red>wife<blue> for him. I<br><br><blue>need to do this before he will sell chickens.", 11);
			break;
		case 12:
			line(player, "<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward<br><br><blue>making her bed more comfortable, the other will be used in<br><br><blue>some unexplained reward for me.<br><br><str>I need to find someone who will supply undead chickens to<br><br><str>me. Perhaps the farm near Port Phasmatys sells them...<br><br><blue>The<red> ghost farmer<blue> wants me to talk to his <red>wife<blue> for him. I<br><br><blue>need to do this before he will sell chickens.<br><br><blue>The <red>ghost farmer<blue>'s <red>wife <blue>needs to know bank information<br><br><blue>that only the farmer can supply.", 11);
			break;
		case 13:
			line(player, "<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward<br><br><blue>making her bed more comfortable, the other will be used in<br><br><blue>some unexplained reward for me.<br><br><str>I need to find someone who will supply undead chickens to<br><br><str>me. Perhaps the farm near Port Phasmatys sells them...<br><br><str>The ghost farmer wants me to talk to his wife for him. I<br><br><str>need to do this before he will sell chickens.<br><br><blue>The <red>ghost farmer<blue> won't tell me the information his <red>wife <blue>is<br><br><blue>after. Perhaps I should talk to her again.", 11);
			break;
		case 14:
			line(player, "<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward<br><br><blue>making her bed more comfortable, the other will be used in<br><br><blue>some unexplained reward for me.<br><br><str>I need to find someone who will supply undead chickens to<br><br><str>me. Perhaps the farm near Port Phasmatys sells them...<br><br><str>The ghost farmer wants me to talk to his wife for him. I<br><br><str>need to do this before he will sell chickens.<br><br><blue>The <red>ghost farmer<blue>'s <red>wife <blue>still needs to know bank<br><br><blue>information that only the farmer can supply.", 11);
			break;
		case 15:
			line(player, "<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward<br><br><blue>making her bed more comfortable, the other will be used in<br><br><blue>some unexplained reward for me.<br><br><str>I need to find someone who will supply undead chickens to<br><br><str>me. Perhaps the farm near Port Phasmatys sells them...<br><br><str>The ghost farmer wants me to talk to his wife for him. I<br><br><str>need to do this before he will sell chickens.<br><br><blue>I still need to find a way to allow the <red>undead farmer<blue> and his<br><br><red>wife <blue>to communicate with each other.", 11);
			break;
		case 16:
			line(player, "<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward<br><br><blue>making her bed more comfortable, the other will be used in<br><br><blue>some unexplained reward for me.<br><br><str>I need to find someone who will supply undead chickens to<br><br><str>me. Perhaps the farm near Port Phasmatys sells them...<br><br><str>The ghost farmer wants me to talk to his wife for him. I<br><br><str>need to do this before he will sell chickens.<br><br><blue>I should talk to the <red>crone <blue>west of the undead farm and ask<br><br><blue>about <red>ghostspeak amulet<blue>s. Perhaps she can enable the<br><br><red>ghost farmer<blue> to talk to his <red>wife<blue> directly.", 11);
			break;
		case 17:
			line(player, "<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward<br><br><blue>making her bed more comfortable, the other will be used in<br><br><blue>some unexplained reward for me.<br><br><str>I need to find someone who will supply undead chickens to<br><br><str>me. Perhaps the farm near Port Phasmatys sells them...<br><br><str>The ghost farmer wants me to talk to his wife for him. I<br><br><str>need to do this before he will sell chickens.<br><br><str>I should talk to the crone west of the undead farm and ask<br><br><str>about ghostspeak amulets. Perhaps she can enable the<br><br><str>ghost farmer to talk to his wife directly.<br><br><blue>I need to talk the <red>crone<blue> while I have a <red>ghostspeak<br><br><red>amulet <blue>so that she can create a new amulet specifically<br><br><blue>for the <red>ghost farmer.", 11);
			break;
		case 18:
			line(player, "<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward<br><br><blue>making her bed more comfortable, the other will be used in<br><br><blue>some unexplained reward for me.<br><br><str>I need to find someone who will supply undead chickens to<br><br><str>me. Perhaps the farm near Port Phasmatys sells them...<br><br><str>The ghost farmer wants me to talk to his wife for him. I<br><br><str>need to do this before he will sell chickens.<br><br><str>I should talk to the crone west of the undead farm and ask<br><br><str>about ghostspeak amulets. Perhaps she can enable the<br><br><str>ghost farmer to talk to his wife directly.<br><br><str>I need to talk the crone while I have a ghostspeak<br><br><str>amulet so that she can create a new amulet specifically<br><br><str>for the ghost farmer.<br><br><blue>I should give the <red>ghost farmer <blue>a <red>crone-made amulet <blue>so<br><br><blue>that he can talk directly to his <red>wife.", 11);
			break;
		case 19:
			line(player, "<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward<br><br><blue>making her bed more comfortable, the other will be used in<br><br><blue>some unexplained reward for me.<br><br><str>I need to find someone who will supply undead chickens to<br><br><str>me. Perhaps the farm near Port Phasmatys sells them...<br><br><str>The ghost farmer wants me to talk to his wife for him. I<br><br><str>need to do this before he will sell chickens.<br><br><str>I should talk to the crone west of the undead farm and ask<br><br><str>about ghostspeak amulets. Perhaps she can enable the<br><br><str>ghost farmer to talk to his wife directly.<br><br><str>I need to talk the crone while I have a ghostspeak<br><br><str>amulet so that she can create a new amulet specifically<br><br><str>for the ghost farmer.<br><br><str>I should give the ghost farmer a crone-made amulet so<br><br><str>that he can talk directly to his wife.<br><br><blue>The <red>farmer <blue>seems friendlier now; I need to talk to him<br><br><blue>about the <red>undead chickens.", 11);
			break;
		case 20:
			line(player, "<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward<br><br><blue>making her bed more comfortable, the other will be used in<br><br><blue>some unexplained reward for me.<br><br><str>I need to find someone who will supply undead chickens to<br><br><str>me. Perhaps the farm near Port Phasmatys sells them...<br><br><str>The ghost farmer wants me to talk to his wife for him. I<br><br><str>need to do this before he will sell chickens.<br><br><str>I should talk to the crone west of the undead farm and ask<br><br><str>about ghostspeak amulets. Perhaps she can enable the<br><br><str>ghost farmer to talk to his wife directly.<br><br><str>I need to talk the crone while I have a ghostspeak<br><br><str>amulet so that she can create a new amulet specifically<br><br><str>for the ghost farmer.<br><br><str>I should give the ghost farmer a crone-made amulet so<br><br><str>that he can talk directly to his wife.<br><br><str>The farmer seems friendlier now; I need to talk to him<br><br><str>about the undead chickens.<br><br><str>The farmer has agreed to sell chickens; now he needs to<br><br><str>catch one for me.<br><br><blue>The <red>ghost farmer<blue> caught some chickens; now I need to buy<br><br><blue>2 from him and deliver them to Ava.", 11);
			break;
		case 25:
			line(player, "<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward<br><br><blue>making her bed more comfortable, the other will be used in<br><br><blue>some unexplained reward for me.<br><br><str>I need to find someone who will supply undead chickens to<br><br><str>me. Perhaps the farm near Port Phasmatys sells them...<br><br><str>The ghost farmer wants me to talk to his wife for him. I<br><br><str>need to do this before he will sell chickens.<br><br><str>I should talk to the crone west of the undead farm and ask<br><br><str>about ghostspeak amulets. Perhaps she can enable the<br><br><str>ghost farmer to talk to his wife directly.<br><br><str>I need to talk the crone while I have a ghostspeak<br><br><str>amulet so that she can create a new amulet specifically<br><br><str>for the ghost farmer.<br><br><str>I should give the ghost farmer a crone-made amulet so<br><br><str>that he can talk directly to his wife.<br><br><str>The farmer seems friendlier now; I need to talk to him<br><br><str>about the undead chickens.<br><br><str>The farmer has agreed to sell chickens; now he needs to<br><br><str>catch one for me.<br><br><str>The ghost farmer caught some chickens; now I need to buy<br><br><str>2 from him and deliver them to Ava.<br><br><blue>I need to talk to the <red>Witch <blue>in <red>Draynor Manor <blue>about<br><br><red>magically attuned magnets<blue>. Apparently, the <red>undead<br><br><red>chicken <blue>will be using magnets in my reward.", 11);
			break;
		case 26:
			line(player, "<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward<br><br><blue>making her bed more comfortable, the other will be used in<br><br><blue>some unexplained reward for me.<br><br><str>I need to find someone who will supply undead chickens to<br><br><str>me. Perhaps the farm near Port Phasmatys sells them...<br><br><str>The ghost farmer wants me to talk to his wife for him. I<br><br><str>need to do this before he will sell chickens.<br><br><str>I should talk to the crone west of the undead farm and ask<br><br><str>about ghostspeak amulets. Perhaps she can enable the<br><br><str>ghost farmer to talk to his wife directly.<br><br><str>I need to talk the crone while I have a ghostspeak<br><br><str>amulet so that she can create a new amulet specifically<br><br><str>for the ghost farmer.<br><br><str>I should give the ghost farmer a crone-made amulet so<br><br><str>that he can talk directly to his wife.<br><br><str>The farmer seems friendlier now; I need to talk to him<br><br><str>about the undead chickens.<br><br><str>The farmer has agreed to sell chickens; now he needs to<br><br><str>catch one for me.<br><br><str>The ghost farmer caught some chickens; now I need to buy<br><br><str>2 from him and deliver them to Ava.<br><br><str>I need to talk to the Witch in Draynor Manor about<br><br><str>magically attuned magnets. Apparently, the undead<br><br><str>chicken will be using magnets in my reward<br><br><blue>I need to deliver 5 <red>iron bars <blue>to the <red>Witch <blue>in <red>Draynor Manor.<br><br><blue>She will select one most suitable for both magnetising and<br><br>mystical use.", 11);
			break;
		case 27:
			line(player, "<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward<br><br><blue>making her bed more comfortable, the other will be used in<br><br><blue>some unexplained reward for me.<br><br><str>I need to find someone who will supply undead chickens to<br><br><str>me. Perhaps the farm near Port Phasmatys sells them...<br><br><str>The ghost farmer wants me to talk to his wife for him. I<br><br><str>need to do this before he will sell chickens.<br><br><str>I should talk to the crone west of the undead farm and ask<br><br><str>about ghostspeak amulets. Perhaps she can enable the<br><br><str>ghost farmer to talk to his wife directly.<br><br><str>I need to talk the crone while I have a ghostspeak<br><br><str>amulet so that she can create a new amulet specifically<br><br><str>for the ghost farmer.<br><br><str>I should give the ghost farmer a crone-made amulet so<br><br><str>that he can talk directly to his wife.<br><br><str>The farmer seems friendlier now; I need to talk to him<br><br><str>about the undead chickens.<br><br><str>The farmer has agreed to sell chickens; now he needs to<br><br><str>catch one for me.<br><br><str>The ghost farmer caught some chickens; now I need to buy<br><br><str>2 from him and deliver them to Ava.<br><br><str>I need to talk to the Witch in Draynor Manor about<br><br><str>magically attuned magnets. Apparently, the undead<br><br><str>chicken will be using magnets in my reward<br><br><str>I need to deliver 5 iron bars to the Witch in Draynor Manor.<br><br><str>She will select one most suitable for both magnetising and<br><br><str>mystical use.<br><br><blue>I need to make a <red>magnet <blue>by hammering the <red>selected iron<br><br><blue>bar while facing north in <red>Rimmington mines. <blue>I then need<br><br><blue>to pass this magnet to <red>Ava.", 11);
			break;
		case 28:
			line(player, "<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward<br><br><blue>making her bed more comfortable, the other will be used in<br><br><blue>some unexplained reward for me.<br><br><str>I need to find someone who will supply undead chickens to<br><br><str>me. Perhaps the farm near Port Phasmatys sells them...<br><br><str>The ghost farmer wants me to talk to his wife for him. I<br><br><str>need to do this before he will sell chickens.<br><br><str>I should talk to the crone west of the undead farm and ask<br><br><str>about ghostspeak amulets. Perhaps she can enable the<br><br><str>ghost farmer to talk to his wife directly.<br><br><str>I need to talk the crone while I have a ghostspeak<br><br><str>amulet so that she can create a new amulet specifically<br><br><str>for the ghost farmer.<br><br><str>I should give the ghost farmer a crone-made amulet so<br><br><str>that he can talk directly to his wife.<br><br><str>The farmer seems friendlier now; I need to talk to him<br><br><str>about the undead chickens.<br><br><str>The farmer has agreed to sell chickens; now he needs to<br><br><str>catch one for me.<br><br><str>The ghost farmer caught some chickens; now I need to buy<br><br><str>2 from him and deliver them to Ava.<br><br><str>I need to talk to the Witch in Draynor Manor about<br><br><str>magically attuned magnets. Apparently, the undead<br><br><str>chicken will be using magnets in my reward<br><br><str>I need to deliver 5 iron bars to the Witch in Draynor Manor.<br><br><str>She will select one most suitable for both magnetising and<br><br><str>mystical use.<br><br><str>I need to make a magnet by hammering the selected iron<br><br><str>bar while facing north in Rimmington mines. I then need<br><br><str>to pass this magnet to Ava.<br><br><blue>I need to chop some wood from the <red>undead trees <blue>near<br><br><red>Draynor Manor. Ava <blue>can use this wood as a source of<br><br><blue>unending arrow shafts in my reward. She suggested that I<br><br><blue>use a Woodcutting axe made of nothing less powerful than<br><br><blue>mithril.", 11);
			break;
		case 29:
			line(player, "<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward<br><br><blue>making her bed more comfortable, the other will be used in<br><br><blue>some unexplained reward for me.<br><br><str>I need to find someone who will supply undead chickens to<br><br><str>me. Perhaps the farm near Port Phasmatys sells them...<br><br><str>The ghost farmer wants me to talk to his wife for him. I<br><br><str>need to do this before he will sell chickens.<br><br><str>I should talk to the crone west of the undead farm and ask<br><br><str>about ghostspeak amulets. Perhaps she can enable the<br><br><str>ghost farmer to talk to his wife directly.<br><br><str>I need to talk the crone while I have a ghostspeak<br><br><str>amulet so that she can create a new amulet specifically<br><br><str>for the ghost farmer.<br><br><str>I should give the ghost farmer a crone-made amulet so<br><br><str>that he can talk directly to his wife.<br><br><str>The farmer seems friendlier now; I need to talk to him<br><br><str>about the undead chickens.<br><br><str>The farmer has agreed to sell chickens; now he needs to<br><br><str>catch one for me.<br><br><str>The ghost farmer caught some chickens; now I need to buy<br><br><str>2 from him and deliver them to Ava.<br><br><str>I need to talk to the Witch in Draynor Manor about<br><br><str>magically attuned magnets. Apparently, the undead<br><br><str>chicken will be using magnets in my reward<br><br><str>I need to deliver 5 iron bars to the Witch in Draynor Manor.<br><br><str>She will select one most suitable for both magnetising and<br><br><str>mystical use.<br><br><str>I need to make a magnet by hammering the selected iron<br><br><str>bar while facing north in Rimmington mines. I then need<br><br><str>to pass this magnet to Ava.<br><br><blue>I need to find some way of chopping the <red>undead trees<br><br><blue>near <red>Draynor Manor <blue>so that <red>Ava <blue>can use this wood as a<br><br><blue>source of unending arrow shafts.<br><br><blue>Perhaps <red>Ava<blue> could give me some advice...", 11);
			break;
		case 30:
		case 31:
			line(player, "<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward<br><br><blue>making her bed more comfortable, the other will be used in<br><br><blue>some unexplained reward for me.<br><br><str>I need to find someone who will supply undead chickens to<br><br><str>me. Perhaps the farm near Port Phasmatys sells them...<br><br><str>The ghost farmer wants me to talk to his wife for him. I<br><br><str>need to do this before he will sell chickens.<br><br><str>I should talk to the crone west of the undead farm and ask<br><br><str>about ghostspeak amulets. Perhaps she can enable the<br><br><str>ghost farmer to talk to his wife directly.<br><br><str>I need to talk the crone while I have a ghostspeak<br><br><str>amulet so that she can create a new amulet specifically<br><br><str>for the ghost farmer.<br><br><str>I should give the ghost farmer a crone-made amulet so<br><br><str>that he can talk directly to his wife.<br><br><str>The farmer seems friendlier now; I need to talk to him<br><br><str>about the undead chickens.<br><br><str>The farmer has agreed to sell chickens; now he needs to<br><br><str>catch one for me.<br><br><str>The ghost farmer caught some chickens; now I need to buy<br><br><str>2 from him and deliver them to Ava.<br><br><str>I need to talk to the Witch in Draynor Manor about<br><br><str>magically attuned magnets. Apparently, the undead<br><br><str>chicken will be using magnets in my reward<br><br><str>I need to deliver 5 iron bars to the Witch in Draynor Manor.<br><br><str>She will select one most suitable for both magnetising and<br><br><str>mystical use.<br><br><str>I need to make a magnet by hammering the selected iron<br><br><str>bar while facing north in Rimmington mines. I then need<br><br><str>to pass this magnet to Ava.<br><br><str>I need to find some way of chopping the undead trees<br><br><str>near Draynor Manor so that Ava can use this wood as a<br><br><str>source of unending arrow shafts.<br><br><str>Ava suspects that Turael, the Slayer Master in Burthorpe,<br><br><str>might be able to help.<br><br><blue>I need to collect a <red>holy symbol of Saradomin<blue> and a <red>mithril<br><br><red>axe. Turael<blue>, the Burthorpe Slayer, can use these to<br><br><blue> construct a new axe for my undead tree cutting.", 11);
			break;
		case 32:
			line(player, "<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward<br><br><blue>making her bed more comfortable, the other will be used in<br><br><blue>some unexplained reward for me.<br><br><str>I need to find someone who will supply undead chickens to<br><br><str>me. Perhaps the farm near Port Phasmatys sells them...<br><br><str>The ghost farmer wants me to talk to his wife for him. I<br><br><str>need to do this before he will sell chickens.<br><br><str>I should talk to the crone west of the undead farm and ask<br><br><str>about ghostspeak amulets. Perhaps she can enable the<br><br><str>ghost farmer to talk to his wife directly.<br><br><str>I need to talk the crone while I have a ghostspeak<br><br><str>amulet so that she can create a new amulet specifically<br><br><str>for the ghost farmer.<br><br><str>I should give the ghost farmer a crone-made amulet so<br><br><str>that he can talk directly to his wife.<br><br><str>The farmer seems friendlier now; I need to talk to him<br><br><str>about the undead chickens.<br><br><str>The farmer has agreed to sell chickens; now he needs to<br><br><str>catch one for me.<br><br><str>The ghost farmer caught some chickens; now I need to buy<br><br><str>2 from him and deliver them to Ava.<br><br><str>I need to talk to the Witch in Draynor Manor about<br><br><str>magically attuned magnets. Apparently, the undead<br><br><str>chicken will be using magnets in my reward<br><br><str>I need to deliver 5 iron bars to the Witch in Draynor Manor.<br><br><str>She will select one most suitable for both magnetising and<br><br><str>mystical use.<br><br><str>I need to make a magnet by hammering the selected iron<br><br><str>bar while facing north in Rimmington mines. I then need<br><br><str>to pass this magnet to Ava.<br><br><str>I need to find some way of chopping the undead trees<br><br><str>near Draynor Manor so that Ava can use this wood as a<br><br><str>source of unending arrow shafts.<br><br><str>Ava suspects that Turael, the Slayer Master in Burthorpe,<br><br><str>might be able to help.<br><br><str>I need to collect a holy symbol of Saradomin and a mithril<br><br><str>axe. Turael, the Burthorpe Slayer, can use these to<br><br><str> construct a new axe for my undead tree cutting.<br><br><str>I need to chop some undead wood with the silver-edged<br><br><str>mithril axe. Then Ava will want the wood for constructing<br><br><str>my reward.<br><br><blue>I should ask <red>Ava <blue>for the <red>garbled research notes <blue>that she<br><br><blue>cannot translate. When translated, these notes will tell<br><br><blue>her how to combine the <red>undead wood, undead chicken <blue>and<br><br><red>magnet <blue>into some bizarre device.", 11);
			break;
		case 33:
			if (!player.hasItem(TRANSLATED_NOTES)) {
				line(player, "<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward<br><br><blue>making her bed more comfortable, the other will be used in<br><br><blue>some unexplained reward for me.<br><br><str>I need to find someone who will supply undead chickens to<br><br><str>me. Perhaps the farm near Port Phasmatys sells them...<br><br><str>The ghost farmer wants me to talk to his wife for him. I<br><br><str>need to do this before he will sell chickens.<br><br><str>I should talk to the crone west of the undead farm and ask<br><br><str>about ghostspeak amulets. Perhaps she can enable the<br><br><str>ghost farmer to talk to his wife directly.<br><br><str>I need to talk the crone while I have a ghostspeak<br><br><str>amulet so that she can create a new amulet specifically<br><br><str>for the ghost farmer.<br><br><str>I should give the ghost farmer a crone-made amulet so<br><br><str>that he can talk directly to his wife.<br><br><str>The farmer seems friendlier now; I need to talk to him<br><br><str>about the undead chickens.<br><br><str>The farmer has agreed to sell chickens; now he needs to<br><br><str>catch one for me.<br><br><str>The ghost farmer caught some chickens; now I need to buy<br><br><str>2 from him and deliver them to Ava.<br><br><str>I need to talk to the Witch in Draynor Manor about<br><br><str>magically attuned magnets. Apparently, the undead<br><br><str>chicken will be using magnets in my reward<br><br><str>I need to deliver 5 iron bars to the Witch in Draynor Manor.<br><br><str>She will select one most suitable for both magnetising and<br><br><str>mystical use.<br><br><str>I need to make a magnet by hammering the selected iron<br><br><str>bar while facing north in Rimmington mines. I then need<br><br><str>to pass this magnet to Ava.<br><br><str>I need to find some way of chopping the undead trees<br><br><str>near Draynor Manor so that Ava can use this wood as a<br><br><str>source of unending arrow shafts.<br><br><str>Ava suspects that Turael, the Slayer Master in Burthorpe,<br><br><str>might be able to help.<br><br><str>I need to collect a holy symbol of Saradomin and a mithril<br><br><str>axe. Turael, the Burthorpe Slayer, can use these to<br><br><str> construct a new axe for my undead tree cutting.<br><br><str>I need to chop some undead wood with the silver-edged<br><br><str>mithril axe. Then Ava will want the wood for constructing<br><br><str>my reward.<br><br><str>I should ask Ava for the garbled research notes that she<br><br><str>cannot translate. When translated, these notes will tell<br><br><str>her how to combine the undead wood, undead chicken and<br><br><str>magnet into some bizarre device.<br><br><blue>The <red>research notes <blue>must be translated. I should try to<br><br><blue>decipher them even though they look like total gibberish.<br><br><blue>to me.", 11);
			} else {
				line(player, "<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward<br><br><blue>making her bed more comfortable, the other will be used in<br><br><blue>some unexplained reward for me.<br><br><str>I need to find someone who will supply undead chickens to<br><br><str>me. Perhaps the farm near Port Phasmatys sells them...<br><br><str>The ghost farmer wants me to talk to his wife for him. I<br><br><str>need to do this before he will sell chickens.<br><br><str>I should talk to the crone west of the undead farm and ask<br><br><str>about ghostspeak amulets. Perhaps she can enable the<br><br><str>ghost farmer to talk to his wife directly.<br><br><str>I need to talk the crone while I have a ghostspeak<br><br><str>amulet so that she can create a new amulet specifically<br><br><str>for the ghost farmer.<br><br><str>I should give the ghost farmer a crone-made amulet so<br><br><str>that he can talk directly to his wife.<br><br><str>The farmer seems friendlier now; I need to talk to him<br><br><str>about the undead chickens.<br><br><str>The farmer has agreed to sell chickens; now he needs to<br><br><str>catch one for me.<br><br><str>The ghost farmer caught some chickens; now I need to buy<br><br><str>2 from him and deliver them to Ava.<br><br><str>I need to talk to the Witch in Draynor Manor about<br><br><str>magically attuned magnets. Apparently, the undead<br><br><str>chicken will be using magnets in my reward<br><br><str>I need to deliver 5 iron bars to the Witch in Draynor Manor.<br><br><str>She will select one most suitable for both magnetising and<br><br><str>mystical use.<br><br><str>I need to make a magnet by hammering the selected iron<br><br><str>bar while facing north in Rimmington mines. I then need<br><br><str>to pass this magnet to Ava.<br><br><str>I need to find some way of chopping the undead trees<br><br><str>near Draynor Manor so that Ava can use this wood as a<br><br><str>source of unending arrow shafts.<br><br><str>Ava suspects that Turael, the Slayer Master in Burthorpe,<br><br><str>might be able to help.<br><br><str>I need to collect a holy symbol of Saradomin and a mithril<br><br><str>axe. Turael, the Burthorpe Slayer, can use these to<br><br><str> construct a new axe for my undead tree cutting.<br><br><str>I need to chop some undead wood with the silver-edged<br><br><str>mithril axe. Then Ava will want the wood for constructing<br><br><str>my reward.<br><br><str>I should ask Ava for the garbled research notes that she<br><br><str>cannot translate. When translated, these notes will tell<br><br><str>her how to combine the undead wood, undead chicken and<br><br><str>magnet into some bizarre device.<br><br><str>The research notes must be translated. I should try to<br><br><str>decipher them even though they look like total gibberish.<br><br><str>to me.<br><br><blue>The notes look less confusing now. <red>Ava <blue>will want to see<br><br><blue>these <red>translated research notes.", 11);
			}
			break;
		case 34:
			line(player, "<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward<br><br><blue>making her bed more comfortable, the other will be used in<br><br><blue>some unexplained reward for me.<br><br><str>I need to find someone who will supply undead chickens to<br><br><str>me. Perhaps the farm near Port Phasmatys sells them...<br><br><str>The ghost farmer wants me to talk to his wife for him. I<br><br><str>need to do this before he will sell chickens.<br><br><str>I should talk to the crone west of the undead farm and ask<br><br><str>about ghostspeak amulets. Perhaps she can enable the<br><br><str>ghost farmer to talk to his wife directly.<br><br><str>I need to talk the crone while I have a ghostspeak<br><br><str>amulet so that she can create a new amulet specifically<br><br><str>for the ghost farmer.<br><br><str>I should give the ghost farmer a crone-made amulet so<br><br><str>that he can talk directly to his wife.<br><br><str>The farmer seems friendlier now; I need to talk to him<br><br><str>about the undead chickens.<br><br><str>The farmer has agreed to sell chickens; now he needs to<br><br><str>catch one for me.<br><br><str>The ghost farmer caught some chickens; now I need to buy<br><br><str>2 from him and deliver them to Ava.<br><br><str>I need to talk to the Witch in Draynor Manor about<br><br><str>magically attuned magnets. Apparently, the undead<br><br><str>chicken will be using magnets in my reward<br><br><str>I need to deliver 5 iron bars to the Witch in Draynor Manor.<br><br><str>She will select one most suitable for both magnetising and<br><br><str>mystical use.<br><br><str>I need to make a magnet by hammering the selected iron<br><br><str>bar while facing north in Rimmington mines. I then need<br><br><str>to pass this magnet to Ava.<br><br><str>I need to find some way of chopping the undead trees<br><br><str>near Draynor Manor so that Ava can use this wood as a<br><br><str>source of unending arrow shafts.<br><br><str>Ava suspects that Turael, the Slayer Master in Burthorpe,<br><br><str>might be able to help.<br><br><str>I need to collect a holy symbol of Saradomin and a mithril<br><br><str>axe. Turael, the Burthorpe Slayer, can use these to<br><br><str> construct a new axe for my undead tree cutting.<br><br><str>I need to chop some undead wood with the silver-edged<br><br><str>mithril axe. Then Ava will want the wood for constructing<br><br><str>my reward.<br><br><str>I should ask Ava for the garbled research notes that she<br><br><str>cannot translate. When translated, these notes will tell<br><br><str>her how to combine the undead wood, undead chicken and<br><br><str>magnet into some bizarre device.<br><br><str>The research notes must be translated. I should try to<br><br><str>decipher them even though they look like total gibberish.<br><br><str>to me.<br><br><str>The notes look less confusing now. Ava will want to see<br><br><str>these translated research notes.<br><br><blue>Almost finished! I must combine the <red>pattern <blue>which Ava<br><br><blue>gave to me with some <red>polished buttons <blue>and a bit of <red>hard<br><br><red>leather. Ava <blue>tells me that the <red>H.A.M hideout <blue>is a good<br><br>place to obtain <red>buttons.", 11);
			// Almost finished! I must combine the pattern which
			// Ava<br><br>gave to me with some polished buttons and a bit of
			// hard<br><br>leather. Ava tells me that the H.A.M hideout is a
			// good<br><br>place to obtain buttons.
			break;
		case 100:
			line(player, "<str>Ava has asked me for undead chickens. One will go<n><str>towards making her bed more comfortable, the other will<n><str>be used in some unexplained reward for me.<n><str>I need to find someone who will supply undead chickens to<n><str>me. Perhaps the farm near Port Phasmatys sells them...<n><str>The ghost farmer wants me to talk to his wife for him. I<n><str>need to do this before he will sell the chickens.<n><str>I should talk to the crone west of the undead farm and ask<n><str>about ghostspeak amulets. Perhaps she can enable the<n><str>ghost farmer to talk to his wife direcrly.<n><str>I need to talk to the crone while I have a ghostspeak<n><str>amulet so that she can create a new amulet specifically<n><str>for the ghost farmer.<n><str>I should give the ghost farmer a crone-made amulet so<n><str>that he can talk directly to his wife.<n><str>The farmer seems friendlier now; I need to talk to him<n><str>about the undead chickens.<n><str>The farmer has agreed to sell chickens; now he needs to<n><str>catch one for me.<n><str>The ghost farmer caught some chickens;now I need to buy<n><str>2 and deliver them to Ava.<n><str>I need to talk to the Witch in Draynor Manor about<n><str>magically attuned magnets. Apparently, the undead<n><str>chicken will be using magnets in my reward.<n><str>I need to deliver 5 iron bars to the Witch in Draynor Manor.<n><str>She will select one most suitable for both magnetising and<n><str>mystical use.<n><str>I need to make a magnet by hammering the selected iron<n><str>bar while facing north in Rimmington mines. I then need<n><str>to pass this magnet to Ava.<n><str>I need to find some way of chopping the undead trees<n><str>near Draynor manor so that Ava can use this wood as a<n><str>source of unending arrow shafts.<n><str>Ava suspects that Turael, the Slayer Master in Buthrope,<n><str>might be able to help.<n><str>I need to collect a holy symbol of Saradomin and a mithril<n><str>axe. Turael, the Burthorpe Slayer, can use these to<n><str>construct a new axe for my undead tree cutting.<n><str>I need to chop some undead wood with the silver edged<n><str>mithril axe. Then Ava will want the wood for constructing<n><str>my reward.<n><str>I should ask Ava for the garbled research notes that she<n><str>cannot translate. When translated, these notes will tell<n><str>her how to combine the undead wood, undead chicken and<n><str>magnet into some bizarre device.<n><str>The research notes must be translated. I should try to<n><str>decipher them even though they look like total gibberish<n><str>to me.<n><str>The notes look less confusing now. Ava will want to see", 11);
			line(player, "<str>these translated research notes.<n><str>Almost finished!I must combine the pattern which Ava<n><str>gave to me with some polished buttons and a bit of hard<n><str>leather.<n><str>Ava wants the completed container. She can then combine<n><str>it with the undead chicken, undead wood and magnet.<n><col=FF0000>QUEST COMPLETE!<n><red>Ava's reward for me is an arrow attracting and creating<n><red>backpack.<n><blue>The method is this: the <red>undead chicken<blue> can attract lost,<n><blue>stray arrowheads with a magnet, add wood from the<n><blue>undead twigs and then finish the arrows using its own<n><blue>feathers. This will give me an unending source of arrows.<n><blue>The cunning bird will also attract some of the arrows which I<n><blue>have fired, preventing these arrows from falling upon the<n><blue>ground.<n><blue>If I lost my device, I can talk to <red>Ava<blue> for a new one,<n><blue>although it will cost me around 1000 gold.<n><blue>Once I achieve a Ranger level of 50 or more, I can upgrade<n><blue>the attractor if I give <red>Ava <blue>75 steel arrows.", 63);
			break;
		}
	}

	/**
	 * Draws the requirements.
	 */
	private void drawRequirements(Player player) {
		hasRequirements(player);
		int line = 8 + 7;
		for (int i = 0; i < requirements.length; i++) {
			line(player, (requirements[i] ? "<str>" : "<red>") + REQS[i], line++);
		}
	}

	@Override
	public boolean hasRequirements(Player player) {
		requirements[0] = player.getQuestRepository().isComplete("The Restless Ghost");
		requirements[1] = player.getQuestRepository().isComplete("Ernest the Chicken");
		requirements[2] = player.getQuestRepository().isComplete("Priest in Peril");
		requirements[3] = player.getSkills().getStaticLevel(Skills.RANGE) >= 30;
		requirements[4] = player.getSkills().getStaticLevel(Skills.SLAYER) >= 18;
		requirements[5] = player.getSkills().getStaticLevel(Skills.CRAFTING) >= 19;
		requirements[6] = player.getSkills().getStaticLevel(Skills.WOODCUTTING) >= 35;
		for (boolean bool : requirements) {
			if (!bool) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		Item item = player.getSkills().getStaticLevel(Skills.RANGE) >= 50 ? AVAS_ACCUMULATOR : AVAS_ATTRACTOR;
		player.getPacketDispatch().sendString("1000 XP in each of Crafting,", 277, 8 + 2);
		player.getPacketDispatch().sendString("Fletching and Slayer", 277, 9 +2);
		player.getPacketDispatch().sendString("2500 Wooducuting XP", 277, 10 + 2);
		player.getPacketDispatch().sendString("1 Quest Point", 277, 11 + 2);
		player.getPacketDispatch().sendString("Ava's device", 277, 12 + 2);
		player.getPacketDispatch().sendItemZoomOnInterface(item.getId(), 235, 277, 3 + 2);
		player.getSkills().addExperience(Skills.CRAFTING, 1000);
		player.getSkills().addExperience(Skills.SLAYER, 1000);
		player.getSkills().addExperience(Skills.FLETCHING, 1000);
		player.getSkills().addExperience(Skills.WOODCUTTING, 2500);
		player.getInventory().add(item);
		player.getQuestRepository().syncronizeTab(player);
	}

	@Override
	public int[] getConfig(Player player, int stage) {
		if (getStage(player) >= 28 && getStage(player) != 100) {
			return new int[] { 939, 150 };
		}
		int val = stage < 100 && stage > 0 ? 10 : stage >= 100 ? 240 : 0;
		return new int[] { 939, val };
	}

}
