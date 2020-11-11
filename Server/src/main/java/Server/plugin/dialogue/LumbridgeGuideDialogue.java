package plugin.dialogue;

import java.util.ArrayList;
import java.util.List;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.Rights;
import core.game.node.entity.player.link.IronmanMode;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.world.GameWorld;
import core.game.world.map.zone.impl.ModeratorZone;
import core.plugin.InitializablePlugin;
import core.net.amsc.MSPacketRepository;

/**
 * Represents the lumbridge guide dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class LumbridgeGuideDialogue extends DialoguePlugin {

	/**
	 * The list of options.
	 */
	private List<String> options;

	/**
	 * Constructs a new {@code LumbridgeGuideDialogue} {@code Object}.
	 */
	public LumbridgeGuideDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code LumbridgeGuideDialogue} {@code Object}.
	 * @param player the player.
	 */
	public LumbridgeGuideDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new LumbridgeGuideDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		npc("Greetings, adventurer. I am Phileas, the Lumbridge", "Guide. I am here to give information and directions to", "new players. Do you require any help?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			if (!player.isStaff() && !player.getIronmanManager().isIronman()) {
				options("Where can I find a quest to go on?", "What monsters should I fight?", "Where can I make money?", "I'd like to know more about security.", "Where can I find a bank?");
				stage = 103;
				return true;
			}
			options = new ArrayList<>();
			options.add("Yes please.");
			if (player.getRights() == Rights.PLAYER_MODERATOR && ModeratorZone.isOpen() || player.isAdmin()) {
				options.add("I would like to access the P-Mod room.");
			}
			if (player.getIronmanManager().isIronman()) {
				options.add("Would you like to remove Ironman Mode?");
			}
			options(options.toArray(new String[] {}));
			stage = 900;
			break;
		case 900:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "Yes please.");
				stage = 102;
				break;
			case 2:
				if (options.get(1).contains("remove")) {
					options("Yes, please.", "No.");
					stage = 804;
					break;
				}
				if (options.get(1).contains("room")) {
					stage = 690;
					npc("Yes, of course.");
				} else {
					stage = 901;
					npc("Yes, of course.");
				}
				break;
			case 3:
				if (options.get(2).contains("remove")) {
					options("Yes, please.", "No.");
					stage = 804;
					break;
				}
				stage = 901;
				npc("Yes, of course.");
				break;
			}
			break;
		case 804:
			if (buttonId == 1) {
				player("Yes, please.");
				stage = 806;
			} else {
				player("No.");
				stage = 805;
			}
			break;
		case 805:
			end();
			break;
		case 806:
			npc("You are no longer an Ironman.");
			stage++;
			break;
		case 807:
			player.getIronmanManager().setMode(IronmanMode.NONE);
			MSPacketRepository.sendInfoUpdate(player);
			end();
			break;
		case 10:
			options("Tell me about the town of Lumbridge.", "I'm fine for now, thanks.");
			stage = 11;
			break;
		case 1:
		case 11:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "Yes please.");
				stage = 100;
				break;
			case 2:
				interpreter.sendDialogues(player, null, "No, I can find things myself thank you.");
				stage = 200;
				break;
			}
			break;
		case 200:
			end();
			break;
		case 100:
			npc("First I must warn you to take every precaution to", "keep your " + GameWorld.getSettings().getName() + " password and PIN secure. The", "most important thing to remember is to never give your", "password to, or share you account with, anyone.");
			stage = 101;
			break;
		case 101:
			npc("I have much more information to impart; what would", "you like to know about?");
			stage = 102;
			break;
		case 102:
			options("Where can I find a quest to go on?", "What monsters should I fight?", "Where can I make money?", "I'd like to know more about security.", "Where can I find a bank?");
			stage = 103;
			break;
		case 103:
			switch (buttonId) {
			case 1:
				player("Where can I find a quest to go on?");
				stage = 1100;
				break;
			case 2:
				player("What monsters should I fight?");
				stage = 1200;
				break;
			case 3:
				player("Where can I make money?");
				stage = 1300;
				break;
			case 4:
				player("I'd like to know more about security.");
				stage = 1400;
				break;
			case 5:
				player("Where can I find a bank?");
				stage = 1500;
				break;
			}
			break;
		case 1400:
			npc("I can tell you about password security, avoiding item", "scamming and in-game moderation. I can also tell you", "about a place called the Stronghold of Security, where", "you can learn more about account security and have a");
			stage = 1401;
			break;
		case 1401:
			npc("bit of an adventure at the same time.");
			stage = 1402;
			player.getAchievementDiaryManager().finishTask(player, DiaryType.LUMBRIDGE, 0, 17);
			break;
		case 1402:
			end();
			break;
		case 1300:
			npc("There are many ways to make money in the game. I", "would suggest either killing monsters or doing a trade", "skill such as Smithing or Fishing.");
			stage = 1301;
			break;
		case 1301:
			npc("Please don't try to get money by begging off other", "players. It will make you unpopular. Nobody likes a", "beggar. It is very irritating to have other players asking", "for your hard-earned cash.");
			stage = 1302;
			break;
		case 1302:
			end();
			break;
		case 1200:
			npc("You're strong enough to work out what monsters to", "fight for yourself now, but the combat tutors might help", "you with any questions you have about the skills;", "they're just over there to the south of the general store.");
			stage = 1201;
			break;
		case 1201:
			npc("There are things to kill all over the place! At your level,", "you might like to try wandering westwards to the", "Wizards' Tower or north-west to the Barbarian Village.", "Non-player characters usually appear as yellow dots on");
			stage = 1202;
			break;
		case 1202:
			npc("your mini-map, although there are some that you won't", "be able to fight, such as myself. If you explore further", "afield you may be able to find areas that are less", "crowded, but watch out for monsters which are tougher");
			stage = 1203;
			break;
		case 1203:
			npc("than you. A monster's combat level is shown next to", "their 'Attack' option. If that level is coloured green it", "means the monster is weaker than you. If it is red, it", "means that the monster is tougher than you.");
			stage = 1204;
			break;
		case 1204:
			npc("Remember, you wil do better if you have better", "armour and weapons and it's always worth carrying a", "bit of food to heal yourself.");
			stage = 1205;
			break;
		case 1205:
			end();
			break;
		case 1500:
			npc("You'll find a bank upstairs in Lumbridge Castle - go", "right to the top.");
			stage = 1501;
			break;
		case 1501:
			end();
			break;
		case 1100:
			stage = 1101;
			if (!player.getQuestRepository().isComplete("Cook's Assistant")) {
				npc("You can try talking to the Cook in the Lumbridge Castle", "I hear he is always looking for some help.");
				return true;
			}
			if (!player.getQuestRepository().isComplete("Sheep Shearer")) {
				npc("You can try talking to Fred the Farmer north-west of here", "I hear he is always looking for some help.");
				return true;
			}
			interpreter.sendDialogues(npc, null, "You are such an accomplished adventurer already; you", "should be telling me some good quests to go on.");
			break;
		case 1101:
			end();
			break;
		case 104:
			end();
			break;
		case 680:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "Yes please.");
				stage = 100;
				break;
			case 2:
				stage = 690;
				npc("Yes, of course.");
				break;
			case 3:
				interpreter.sendDialogues(player, null, "No, I can find things myself thank you.");
				stage = 200;
				break;
			}
			break;
		case 690:
			end();
			if (ModeratorZone.isOpen() && player.getDetails().getRights() == Rights.PLAYER_MODERATOR) {
				ModeratorZone.teleport(player);
			} else if (player.getDetails().getRights() == Rights.ADMINISTRATOR) {
				ModeratorZone.teleport(player);
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2244 };
	}
}
