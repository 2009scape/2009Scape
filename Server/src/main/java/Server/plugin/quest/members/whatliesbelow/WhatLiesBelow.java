package plugin.quest.members.whatliesbelow;

import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * The what lies below quest.
 * @author Vexa
 * 
 */
@InitializablePlugin
public class WhatLiesBelow extends Quest {

	/**
	 * The name of the quest.
	 */
	public static final String NAME = "What Lies Below";

	/**
	 * The empty folder item.
	 */
	public static final Item EMPTY_FOLDER = new Item(11003);

	/**
	 * The rats paper item.
	 */
	public static final Item RATS_PAPER = new Item(11008);

	/**
	 * The used folder.
	 */
	public static final Item USED_FOLDER = new Item(11006);

	/**
	 * The full folder.
	 */
	public static final Item FULL_FOLDER = new Item(11007);

	/**
	 * The rats letter.
	 */
	public static final Item RATS_LETTER = new Item(11009);

	/**
	 * The sin keth diary.
	 */
	public static final Item SIN_KETH_DIARY = new Item(11002);

	/**
	 * The wand item.
	 */
	public static final Item WAND = new Item(11012);

	/**
	 * The infused item.
	 */
	public static final Item INFUSED_WAND = new Item(11013);

	/**
	 * The bowl item.
	 */
	public static final Item BOWL = new Item(1923);

	/**
	 * The suroks letter.
	 */
	public static final Item SUROKS_LETTER = new Item(11010);

	/**
	 * The bacon ring.
	 */
	public static final Item BEACON_RING = new Item(11014);

	/**
	 * The requirement messages.
	 */
	private static final String[] REQS = new String[] { "<blue>Have level 35 <red>Runecrafting.", "<blue>Be able to defeat a <red>level 47 enemy.", "<blue>I need to have completed the <red>Rune Mysteries <blue>quest.", "<blue>Have a <red>Mining <blue>level of 42 to use the <red>Chaos Tunnel." };

	/**
	 * The requirements.
	 */
	private final boolean[] requirements = new boolean[4];

	/**
	 * Constructs a new {@Code WhatLiesBelow} {@Code Object}
	 * @param player the player.
	 */
	public WhatLiesBelow() {
		super(NAME, 136, 135, 1);
	}

	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugin(new WLBelowPlugin());
		return this;
	}
	
	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (stage) {
		case 0:
			line(player, "<blue>I can start this quest by speaking to <red>Rat Burgiss <blue>on the<n><blue>road south of <red>Varrock.<n><blue>Before I begin I will need to:<n>" + getReqMessage(player), 11);
			break;
		case 10:
			line(player, "<red>Rat<blue>, a trader in Varrock, has asked me to help him with a task.<n><blue>I need to kill <red>outlaws<blue> west of Varrock so that I can collect 5 of <n><blue>Rat's <red>papers<blue>.", 11);
			break;
		case 20:
			line(player, "<red>Rat<blue>, a trader in Varrock, has asked me to help him with a task.<n><str>I need to kill outlaws west of Varrock so that I can collect<n><str>5 of Rat's papers.I have delivered Rat's folder to him. Perhaps I<n><str>should speak to him again.<n><blue>I need to deliver <red>Rat's<blue> letter to <red>Surok Magis<n><blue>in <red>Varrock.", 11);
			break;
		case 30:
		case 40:
			line(player, "<str>Rat, a trader in Varrock, has asked me to help him with a task.<n><str>Surok, a Wizard in Varrock, has asked me to complete a task for him.<n><str>I need to kill the outlaws west of Varrock so that I can collect<n><str>5 of Rat's papers.<str>I have delivered Rat's folder to him. Perhaps I<n><str>should speak to him again.<str>I need to deliver Rat's letter to <n><str>Surok Magis in Varrock. <str>I need to talk to Surok about the<n><str>secret he has for me.<n><blue>I need to infuse the <red>metal wand <blue>with <red>chaos runes <blue>at the <red>Chaos Altar<blue>.<n><blue>I also need<blue>to find or buy an empty <red>bowl.", 11);
			break;
		case 50:
			line(player, "<str>Rat, a trader in Varrock, has asked me to help him with a task.<n><str>Surok, a Wizard in Varrock, has asked me to complete a task for him.<n><str>I need to kill the outlaws west of Varrock so that I can collect<n><str>5 of Rat's papers.<str>I have delivered Rat's folder to him. Perhaps I<n><str>should speak to him again.<str>I need to deliver Rat's letter to <n><str>Surok Magis in Varrock. <str>I need to talk to Surok about the<n><str>secret he has for me.<n><str>I need to infuse the <str>metal wand <str>with chaos runes <str>at the <str>Chaos Altar<str>.<n><str>I also need<str> to find or buy an empty <str>bowl.<n><str>I need to infuse the metal wand with chaos runes at the Chaos Altar.<n><str>I also need to find or buy an empty bowl.<n><str>I need to take the glowing wand I have created back to Surok in Varrock<n><str>with an empty bowl.<n><str>I need to deliver Surok's letter to Rat who is waiting for me south<n><blue>of Varrock. <blue>I should speak to <red>Rat<blue> again; he is waiting for me <n><blue>south of Varrock", 11);
			break;
		case 60:
			line(player, "<str>Rat, a trader in Varrock, has asked me to help him with a task.<n><str>Surok, a Wizard in Varrock, has asked me to complete a task for him.<n><str>I need to kill the outlaws west of Varrock so that I can collect<n><str>5 of Rat's papers.<str>I have delivered Rat's folder to him. Perhaps I<n><str>should speak to him again.<str>I need to deliver Rat's letter to <n><str>Surok Magis in Varrock. <str>I need to talk to Surok about the<n><str>secret he has for me.<n><str>I need to infuse the <str>metal wand <str>with chaos runes <str>at the <str>Chaos Altar<str>.<n><str>I also need<str> to find or buy an empty <str>bowl.<n><str>I need to infuse the metal wand with chaos runes at the Chaos Altar.<n><str>I also need to find or buy an empty bowl.<n><str>I need to take the glowing wand I have created back to Surok in Varrock<n><str>with an empty bowl.<n><str>I need to deliver Surok's letter to Rat who is waiting for me south<n><str>of Varrock.<str>I should speak to Rat again; he is waiting for me <n><str>south of Varrock<n><blue>I need to speak to <red>Zaff <blue>of <red>Zaff's Staffs <blue>in Varrock.", 11);
			break;
		case 70:
			line(player, "<str>Rat, a trader in Varrock, has asked me to help him with a task.<n><str>Surok, a Wizard in Varrock, has asked me to complete a task for him.<n><str>I need to kill the outlaws west of Varrock so that I can collect<n><str>5 of Rat's papers.<str>I have delivered Rat's folder to him. Perhaps I<n><str>should speak to him again.<str>I need to deliver Rat's letter to <n><str>Surok Magis in Varrock. <str>I need to talk to Surok about the<n><str>secret he has for me.<n><str>I need to infuse the <str>metal wand <str>with chaos runes <str>at the <str>Chaos Altar<str>.<n><str>I also need<str> to find or buy an empty <str>bowl.<n><str>I need to infuse the metal wand with chaos runes at the Chaos Altar.<n><str>I also need to find or buy an empty bowl.<n><str>I need to take the glowing wand I have created back to Surok in Varrock<n><str>with an empty bowl.<n><str>I need to deliver Surok's letter to Rat who is waiting for me south<n><str>of Varrock.<str>I should speak to Rat again; he is waiting for me <n><str>south of Varrock<n><str>I need to speak to Zaff of Zaff's Staffs in Varrock.<n><blue>I need to tell <red>Surok <blue>in Varrock that he is under arrest.", 11);
			break;
		case 80:
		case 90:
			line(player, "<str>Rat, a trader in Varrock, has asked me to help him with a task.<n><str>Surok, a Wizard in Varrock, has asked me to complete a task for him.<n><str>I need to kill the outlaws west of Varrock so that I can collect<n><str>5 of Rat's papers.<str>I have delivered Rat's folder to him. Perhaps I<n><str>should speak to him again.<str>I need to deliver Rat's letter to <n><str>Surok Magis in Varrock. <str>I need to talk to Surok about the<n><str>secret he has for me.<n><str>I need to infuse the <str>metal wand <str>with chaos runes <str>at the <str>Chaos Altar<str>.<n><str>I also need<str> to find or buy an empty <str>bowl.<n><str>I need to infuse the metal wand with chaos runes at the Chaos Altar.<n><str>I also need to find or buy an empty bowl.<n><str>I need to take the glowing wand I have created back to Surok in Varrock<n><str>with an empty bowl.<n><str>I need to deliver Surok's letter to Rat who is waiting for me south<n><str>of Varrock.<str>I should speak to Rat again; he is waiting for me <n><str>south of Varrock<n><str>I need to speak to Zaff of Zaff's Staffs in Varrock.<n><str>I need to tell Surok in Varrock that he is under arrest.<n><str>I need to defeat King Roald in Varrock so that Zaff can remove the<n><str>mind-control spell.<n><blue>I need to tell <red>Rat <blue>what has happened; he is waiting for me<n><blue>south of Varrock.", 11);
			break;
		case 100:
			line(player, "<str>Rat, a trader in Varrock, has asked me to help him with a task.<n><str>Surok, a Wizard in Varrock, has asked me to complete a task for him.<n><str>I need to kill the outlaws west of Varrock so that I can collect<n><str>5 of Rat's papers.<str>I have delivered Rat's folder to him. Perhaps I<n><str>should speak to him again.<str>I need to deliver Rat's letter to <n><str>Surok Magis in Varrock. <str>I need to talk to Surok about the<n><str>secret he has for me.<n><str>I need to infuse the <str>metal wand <str>with chaos runes <str>at the <str>Chaos Altar<str>.<n><str>I also need<str> to find or buy an empty <str>bowl.<n><str>I need to infuse the metal wand with chaos runes at the Chaos Altar.<n><str>I also need to find or buy an empty bowl.<n><str>I need to take the glowing wand I have created back to Surok in Varrock<n><str>with an empty bowl.<n><str>I need to deliver Surok's letter to Rat who is waiting for me south<n><str>of Varrock.<str>I should speak to Rat again; he is waiting for me <n><str>south of Varrock<n><str>I need to speak to Zaff of Zaff's Staffs in Varrock.<n><str>I need to tell Surok in Varrock that he is under arrest.<n><str>I need to defeat King Roald in Varrock so that Zaff can remove the<n><str>mind-control spell.<n><str>I need to tell Rat what has happened; he is waiting for me<n><str>south of Varrock.<n><n><col=FF0000>QUEST COMPLETE!<n><blue>I have been given information about the <red>Chaos Tunnel<blue>.<n><blue>Zaff has given me the <red>Beacon Ring<blue>.", 11);
			break;
		}
	}

	@Override
	public void start(Player player) {
		super.start(player);
		player.getInventory().add(EMPTY_FOLDER, player);
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getPacketDispatch().sendString("8,000 Runecrafting XP", 277, 8+ 2);
		player.getPacketDispatch().sendString("2,000 Defence XP", 277, 9+ 2);
		player.getPacketDispatch().sendString("Beacon Ring", 277, 10+ 2);
		player.getPacketDispatch().sendString("Knowledge of Chaos Tunnel", 277, 11+ 2);
		player.getPacketDispatch().sendItemZoomOnInterface(BEACON_RING.getId(), 235, 277, 3+ 2);
		player.getSkills().addExperience(Skills.RUNECRAFTING, 8000);
		player.getSkills().addExperience(Skills.DEFENCE, 2000);
		player.getQuestRepository().syncronizeTab(player);
	}

	/**
	 * Gets the req message.
	 * @return the message.
	 */
	public String getReqMessage(Player player) {
		hasRequirements(player);
		String s = "";
		for (int i = 0; i < requirements.length; i++) {
			String l = REQS[i];
			if (requirements[i]) {
				l = l.replace("<blue>", "").replace("<red>", "").trim();
			}
			s += (requirements[i] ? "<str>" : "") + l + "<n>";
		}
		return s;
	}

	@Override
	public boolean hasRequirements(Player player) {
		requirements[0] = player.getSkills().getStaticLevel(Skills.RUNECRAFTING) >= 35;
		requirements[1] = false;
		requirements[3] = player.getSkills().getStaticLevel(Skills.MINING) >= 42;
		requirements[2] = player.getQuestRepository().isComplete("Rune Mysteries");
		return requirements[0] && requirements[2] && requirements[3];
	}

	@Override
	public int[] getConfig(Player player, int stage) {
		int id = 992;
		if (stage >= 40 && stage != 100) {
			return new int[] { id, (1 << 8) + 1 };
		}
		if (stage == 0) {
			return new int[] { id, 0 };
		} else if (stage > 0 && stage < 100) {
			return new int[] { id, 1 };
		}
		player.getConfigManager().set(1181, (1 << 8) + (1 << 9), true);
		return new int[] { id, 502 };
	}
	
}
