package plugin.quest.tutorials.tutorialisland;

import core.game.component.Component;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.world.GameWorld;
import core.plugin.InitializablePlugin;

/**
 * Hanldes the RuneScape guide's dialogue.
 * @author Vexia
 * @author Splinter
 */
@InitializablePlugin
public class RSGuideDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code RuneScapeGuideDialogue} {@code Object}.
	 */
	public RSGuideDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code RuneScapeGuideDialogue} {@code Object}.
	 * @param player The player.
	 */
	private RSGuideDialogue(Player player) {
		super(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		int tut_stage = TutorialSession.getExtension(player).getStage();
		if(tut_stage < TutorialSession.MAX_STAGE){
			switch (tut_stage) {
			case 0:
				Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Greetings! I see you are a new arrival to this land. My", "job is to welcome all new visitors. So welcome!"));
				return true;
			case 1:
				Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You will notice a flashing icon of a spanner; please click", "on this to continue the tutorial."));
				return true;
			case 2:
				Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I'm glad you're making progress!"));
				stage = 1;
				return true;
			}	
		} else {
			if(player.getSkills().getTotalLevel() < 300){
				interpreter.sendDialogues(npc, null, "Greetings, "+player.getUsername()+". Welcome to "+GameWorld.getName()+".", "The flashing icon above my head will disappear once", "you've reached a total level of three hundred.");
			} else {
				interpreter.sendDialogues(npc, null, "Greetings, "+player.getUsername()+". Welcome to "+GameWorld.getName()+".");	
			}
			stage = 10;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		final int tut_stage = TutorialSession.getExtension(player).getStage();
		if(tut_stage < TutorialSession.MAX_STAGE){
			switch (tut_stage) {
			case 0:
				switch (stage) {
				case 0:
					Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You have already learned the first thing needed to", "succeed in this world: talking to other people!"));
					stage = 1;
					break;
				case 1:
					Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You will find many inhabitants of this world have useful", "things to say to you. By clicking on them with your", "mouse you can talk to them."));
					stage = 2;
					break;
				case 2:
					Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I would also suggest reading through some of the", "supporting information on the website. There you can", "find the Knowledge Base, which contains all the", "additional information you're ever likely to need. It also"));
					stage = 3;
					break;
				case 3:
					Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "contains maps and helpful tips to help you on your", "journey."));
					stage = 4;
					break;
				case 4:
					Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You will notice a flashing icon of a wrench, please click", "on this to continue the tutorial."));
					player.getInterfaceManager().openTab(new Component(261));
					player.getConfigManager().set(1021, 12);
					stage = 5;
					break;
				case 5:
					end();
					TutorialStage.load(player, 1, false);
					stage = 1;
					return true;
				}
				break;
			case 2:
				switch (stage) {
				case 1:
					Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "To continue the tutorial go through that door over", "there and speak to your first instructor!"));
					stage = 2;
					break;
				case 2:
					end();
					TutorialStage.load(player, 3, false);
					stage = 1;
					break;
				}
				break;
			case 1:
				end();
				break;
			}
		} else {
			switch(stage){
			case 10:
				interpreter.sendDialogues(npc, null, "I am the "+GameWorld.getName()+" guide. I offer free and helpful assistance", "for newcomers. What would you like to ask about?");
				stage++;
				break;
			case 11:
				interpreter.sendOptions("Select a Question", "Money making", "Getting around", "List of game content");
				stage++;
				break;
			case 12:
				switch(buttonId){
				case 1:
					interpreter.sendDialogues(player, null, "I'd like to ask about money-making techniques.");
					stage = 13;
					break;
				case 2:
					interpreter.sendDialogues(player, null, "I'd like to know how to get around on "+GameWorld.getName()+".");
					stage = 18;
					break;
				case 3:
					interpreter.sendDialogues(player, null, "I'd like to know about all the content in "+GameWorld.getName()+".");
					stage = 25;
					break;
				}
				break;
			case 13:
				interpreter.sendDialogues(npc, null, "That is one of the most commonly asked questions.", "First of all, there are many different ways to make large", "amounts of coins in "+GameWorld.getName()+". There's hundreds", "of hours of emulated content awaiting you.");
				stage = 14;
				break;
			case 14:
				interpreter.sendDialogues(npc, null, "Many players prefer to start with leveling their Slayer skill.", "The skill is profitable because of the vast amount of items","that come with defeating monsters in combat. Speak", "to Turael to the east. He is the lowest level");
				stage = 15;
				break;
			case 15:
				interpreter.sendDialogues(npc, null, "Slayer Master available. He will get you started with Slayer.", "The other Slayer Masters may be found spread through", "out the game world.");
				stage = 16;
				break;
			case 16:
				interpreter.sendDialogues(npc, null, "As for other money-making methods, consider all of the other", "skills available to you. You may chop down Magic trees and","sell the valuable logs to players, or you may even catch", "sharks and sell them for over one thousand gold pieces each.");
				stage = 17;
				break;
			case 17:
				interpreter.sendDialogues(npc, null, GameWorld.getName()+" is an economy-driven server. All gathering skills", "are highly profitable and sought after. Herblore and", "Farming, for example, are guaranteed to make you", "profit.");
				stage = 11;
				break;
			case 18:
				interpreter.sendDialogues(npc, null, "A good question indeed. Traveling is the key to"," experiencing all the game content that "+GameWorld.getName()+" has to offer.");
				stage = 19;
				break;
			case 19:
				interpreter.sendDialogues(npc, null, "First of all, you may use your spellbook to teleport to", "major cities. There are three fully working spellbooks", "available to you. However, they require a Magic level", "in order to be used.");
				stage = 20;
				break;
			case 20:
				interpreter.sendDialogues(npc, null, "Next we have teletabs. They may be bought by accessing", "the green teleporter to the west of me.", "These tablets are very convenient and only require", "a single inventory space.");
				stage = 21;
				break;
			case 21:
				interpreter.sendDialogues(npc, null, "We also have Spirit trees. These talkative trees are", "willing to take you to a few odd locations.");
				stage = 22;
				break;
			case 22:
				interpreter.sendDialogues(npc, null, "Next up is the charter system. You make take a ferry boat", "to many coastal cities like Port Phasmatys and", "Catherby.");
				stage = 23;
				break;
			case 23:
				interpreter.sendDialogues(npc, null, "We also have the convenient Gnome glider system.", "The friendly Gnomes of "+GameWorld.getName()+" offer their services","completely free of charge.");
				stage = 24;
				break;
			case 24:
				interpreter.sendDialogues(npc, null, "Finally, we have the green teleporter located in the", "Grand Exchange and Fairy rings.", "All of these teleportation methods should assist you", "in traveling the wide-open world of "+GameWorld.getName()+".");
				stage = 50;
				break;
			case 25:
				interpreter.sendDialogues(npc, null, "First up, we have our emulated quests. You may", "type ::quests in your chatbox in order to view a full", "list of ones that fully work.", "I will now begin listing off a lot of other game content.");
				stage = 26;
				break;
			case 26:
				interpreter.sendDialogue("Current OSRS content:", "Rune packs, the Zamorakian Hasta, Lava dragons,","Boss pets, Ecumenical keys, Staff of the Dead,", "Armadyl C'bow, colored Dark bows, and the Dragon defender.");
				stage = 27;
				break;
			case 27:
				interpreter.sendDialogue("Boss content:", "The Tz-Tok Jad, King Black Dragon, Dagannoth kings, Bork,","Godwars Dungeon bosses, Giant Mole, Kalphite Queen,", "Chaos elemental, and the Corporeal beast.");
				stage = 28;
				break;
			case 28:
				interpreter.sendDialogue("Minigame content:", "Barrows, Bounty Hunter, Clan Wars, Duel Arena,","Gnomecopters, Magic Training Area, Falador Partyroom,","Pest Control, Puro Puro, The Stronghold of Security,");
				stage = 29;
				break;
			case 29:
				interpreter.sendDialogue("Minigame content continued:", "The Stronghold of Player Safety, the Warrior's Guild,", "Sorceress' Garden, and the Fight Pits.");
				stage = 30;
				break;
			case 30:
				interpreter.sendDialogue("Skill content:", "All skills except Construction are fully functional.");
				stage = 31;
				break;
			case 31:
				interpreter.sendDialogue("Misc content:", "Achievement Diaries, Grand Exchange, Clue scrolls,", "Game sounds, Random events, Lootshare, Slayer points,","Cutscenes, Bank tabs,");
				stage = 32;
				break;
			case 32:
				interpreter.sendDialogues(npc, null, "... and simply too much else to list.", "There's lots of content out there for you to explore.", "Don't just take my word for it. Experience our perfected", "game content for yourself.");
				stage = 11;
				break;
			case 50:
				interpreter.sendDialogues(npc, null, "Oh... I almost forgot.", "Our world also has unique teleport methods like the", "convenient ectophial and enchanted rings and amulets.");
				stage = 11;
				break;
				
			}
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new RSGuideDialogue(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 945 };
	}

}
