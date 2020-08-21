package plugin.quest.free.princealirescue;

import java.util.List;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.game.world.GameWorld;
import core.game.world.map.RegionManager;

/**
 * Represents the dialogue which handles the lady keli transcript.
 * @author 'Vexia
 * @version 1.0
 */
public final class LadyKeliDialogue extends DialoguePlugin {

	/**
	 * Represents the soft clay item.
	 */
	private static final Item SOFT_CLAY = new Item(1761);

	/**
	 * Represents the key print item.
	 */
	private static final Item KEY_PRINT = new Item(2423);

	/**
	 * Represents the quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code LadyKeliDialogue} {@code Object}.
	 */
	public LadyKeliDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code LadyKeliDialogue} {@code Object}.
	 * @param player the player.
	 */
	public LadyKeliDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new LadyKeliDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("Prince Ali Rescue");
		switch (quest.getStage(player)) {
		case 60:
		case 100:
			npc.sendChat("You tricked me, and tied me up, Guards kill this stranger!");
			List<NPC> npcc = RegionManager.getLocalNpcs(player);
			for (NPC n : npcc) {
				if (n.getId() == 917) {
					n.sendChat("Yes M'lady");
					n.getProperties().getCombatPulse().attack(player);
				}
			}
			end();
			break;
		default:
			interpreter.sendDialogues(player, null, "Are you the famous Lady Keli? Leader of the toughest", "gang of mercenary killers around?");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		default:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(npc, null, "I am Keli, you have heard of me then?");
				stage = 1;
				break;
			case 1:
				interpreter.sendOptions("Select an Option", "Heard of you? You are famous in " + GameWorld.getName() + "!", "I have heard a little, but I think Katrine is tougher.", "I have heard rumours that you kill people.", "No I have never really heard of you.");
				stage = 2;
				break;
			case 2:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, null, "The great Lady Keli, of course I have heard of you.", "You are famous in " + GameWorld.getName() + "!");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogues(player, null, "I think Katrine is tougher.");
					stage = 20;
					break;
				case 3:
					interpreter.sendDialogues(player, null, "I have heard rumours that you kill people.");
					stage = 30;
					break;
				case 4:
					interpreter.sendDialogues(player, null, "No I have never really heard of you.");
					stage = 40;
					break;
				}
				break;
			case 10:
				interpreter.sendDialogues(npc, null, "That's very kind of you to say. Reputations are", "not easily earned. I have managed to succeed", "where many fail.");
				stage = 11;
				break;
			case 11:
				interpreter.sendOptions("Select an Option", "I think Katrine is still tougher.", "What is your latest plan then?", "You must have trained a lot for this work.", "I should not disturb someone as tough as you.");
				stage = 12;
				break;
			case 12:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, null, "I think Katrine is tougher.");
					stage = 20;
					break;
				case 2:
					interpreter.sendDialogues(player, null, "What is your latest plan then? Of course, you need", "not go into specific details.");
					stage = 22;
					break;
				case 3:
					interpreter.sendDialogues(player, null, "You must have trained a lot for this work.");
					stage = 25;
					break;
				case 4:
					interpreter.sendDialogues(player, null, "I should not disturb someone as tough as you.");
					stage = 27;
					break;
				}
				break;
			case 20:
				interpreter.sendDialogues(npc, null, "Well you can think that all you like. I know those", "blackarm cowards dare not leave the city. Out here, I", "am the toughest. You can tell them that! Now get out of", "my sight, before I call my guards.");
				stage = 21;
				break;
			case 21:
				end();
				break;
			case 22:
				interpreter.sendDialogues(npc, null, "Well, I can tell you I have a valuable prisoner here", "in my cells.");
				stage = 23;
				break;
			case 23:
				interpreter.sendDialogues(npc, null, "I can expect a high reward to be paid very soon for", "this guy. I can't tell you who he is, but he is a lot", "colder now.");
				stage = 24;
				break;
			case 24:
				interpreter.sendDialogues(player, null, "You must have been very skillful.");
				stage = 50;
				break;
			case 50:
				interpreter.sendDialogues(npc, null, "Yes. I did most of the work. We had to grab the Pr...");
				stage = 51;
				break;
			case 51:
				interpreter.sendDialogues(npc, null, "Er, we had to grab him without his ten bodyguards", "noticing. It was a stroke of genius.");
				stage = 52;
				break;
			case 52:
				interpreter.sendDialogues(player, null, "Can you be sure they will not try to get him out?");
				stage = 53;
				break;
			case 53:
				interpreter.sendDialogues(npc, null, "There is no way to release him. The only key to the", "door is on a chain around my neck and the locksmith", "who made the lock died suddenly when he had finished.");
				stage = 54;
				break;
			case 54:
				interpreter.sendDialogues(npc, null, "There is not another key like this in the world.");
				stage = 55;
				break;
			case 55:
				interpreter.sendDialogues(player, null, "Could I see the key please? Just for a moment. It", "would be something I can tell my grandchildren. When", "you are even more famous than you are now.");
				stage = 56;
				break;
			case 56:
				interpreter.sendDialogues(npc, null, "As you put it that way I am sure you can see it. You", "cannot steal the key, it is on a Runite chain.");
				stage = 57;
			case 57:
				interpreter.sendDialogue("Keli shows you a small key on a strong looking chain.");
				stage = 58;
				break;
			case 58:
				if (player.getInventory().remove(SOFT_CLAY)) {
					if (!player.getInventory().add(KEY_PRINT)) {
						GroundItemManager.create(KEY_PRINT, player);
					}
					interpreter.sendDialogues(player, null, "Could I touch the key for a moment please?");
					stage = 59;
				} else {
					interpreter.sendDialogues(npc, null, "There, run along now I am very busy.");
					stage = 63;
				}
				break;
			case 59:
				interpreter.sendDialogues(npc, null, "Only for a moment then.");
				stage = 60;
				break;
			case 60:
				interpreter.sendDialogue("You put a piece of your soft clay in your hand. As you touch the", "key, you take an imprint of it.");
				stage = 61;
				break;
			case 61:
				interpreter.sendDialogues(player, null, "Thank you so much, you are too kind, o great Keli.");
				stage = 62;
				break;
			case 62:
				interpreter.sendDialogues(npc, null, "You are welcome, run along now, I am very busy.");
				stage = 63;
				break;
			case 63:
				end();
				break;
			case 25:
				interpreter.sendDialogues(npc, null, "I have used a sword since I was a small girl. I stabbed", "three people before I was 6 years old.");
				stage = 26;
				break;
			case 26:
				end();
				break;
			case 27:
				interpreter.sendDialogues(npc, null, "I need to do a lot of work, goodbye. When you get a", "little tougher, maybe I will give you a job.");
				stage = 28;
				break;
			case 28:
				end();
				break;
			case 30:
				interpreter.sendDialogues(npc, null, "There's always someone ready to spread rumours. I", "hear all sort of ridiculous things these days.");
				stage = 31;
				break;
			case 31:
				end();
				break;
			case 40:
				interpreter.sendDialogues(npc, null, "You must be new to this land then. EVERYONE", "knows of Lady Keli and her prowess with the sword.");
				stage = 41;
				break;
			case 41:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 919 };
	}
}