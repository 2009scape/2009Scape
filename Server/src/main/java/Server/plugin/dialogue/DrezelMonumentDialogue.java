package plugin.dialogue;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;

/**
 * Represents the dialogue plugin used for the drezel monument.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class DrezelMonumentDialogue extends DialoguePlugin {

	/**
	 * Represents the items related to the drezel monument.
	 */
	private static final Item[] ITEMS = new Item[] { new Item(1436), new Item(7936) };

	/**
	 * Constructs a new {@code DrezelMonumentDialogue} {@code Object}.
	 */
	public DrezelMonumentDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code DrezelMonumentDialogue} {@code Object}.
	 * @param player the player.
	 */
	public DrezelMonumentDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new DrezelMonumentDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		final Quest quest = player.getQuestRepository().getQuest("Priest in Peril");
		if (quest.getStage(player) == 17) {
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ah, " + player.getUsername() + ". I see you finally made it down here.", "Things are worse than I feared. I'm not sure if I will", "be able to repair the damage.");
			stage = 900;
			return true;
		}
		if (quest.getStage(player) == 18) {
			if (player.getInventory().contains(1436, 1) || player.getInventory().contains(7936, 1)) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I brought you some Rune Essence.");
				stage = 100;
			} else {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "How many more essence do I need to bring you?");
				stage = 120;
			}
			return true;
		}
		if (quest.getStage(player) == 100 && !player.getSavedData().getQuestData().isTalkedDrezel()) { // guy.
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "So can I pass through that barrier now?");
			stage = 400;
			return true;
		} else {
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Greetings again adventurer, How go your travels in", "Morytania? Is it as evil as I have heard?");
			stage = 420;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		final Quest quest = player.getQuestRepository().getQuest("Priest in Peril");
		switch (stage) {
		case 400:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ah, " + player.getUsername() + ". For all the assistance you have given", "both myself and Misthalin in your actions, I cannot let", "you pass without warning you.");
			stage = 401;
			break;
		case 401:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Morytania is an evil land, filled with creatures and", "monsters more terrifying than any you have yet", "encountered. Although I will pray for you");
			stage = 402;
			break;
		case 402:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "you should take some basic precautions before heading", "over the Salve into it. The first place you will come", "across is the Werewolf trading post.");
			stage = 403;
			break;
		case 403:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "In many ways Werewolves are like you and me, except", "never forget that they are evil vicious beasts at heart.", "The dagger I have given you is named 'Wolfbane'");
			stage = 404;
			break;
		case 404:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "and it is a holy relic that pevents the werewolf people from", "chaning form, I suggest if you battle with them", "that you keep it always equipped, for their");
			stage = 405;
			break;
		case 405:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "wolf form is incredibly powerful, and would savage you", "very quickly. Please adventurer, promise me this: I", "should hate for you to die foolishly.");
			stage = 406;
			break;
		case 406:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Okay, I will keep it equipped whenever I fight", "werwolves.");
			stage = 407;
			break;
		case 407:
			player.getSavedData().getQuestData().setTalkedDrezel(true);
			end();
			break;
		case 420:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Well, I'm going to look around a bit more.");
			stage = 421;
			break;
		case 421:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, that sounds like a good idea. Don't get into any", "trouble though!");
			stage = 422;
			break;
		case 422:
			end();
			break;
		case 120:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I need " + player.getGameAttributes().getAttribute("priest-in-peril:rune", 50) + " more.");
			stage = 121;
			break;
		case 121:
			end();
			break;
		case 100:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Quickly, give it to me!");
			stage = 101;
			break;
		case 101:
			int amt = player.getInventory().contains(1436, 1) ? player.getInventory().getAmount(ITEMS[0]) : player.getInventory().getAmount(ITEMS[1]);
			player.getInventory().remove(player.getInventory().contains(1436, 1) ? new Item(1436, amt) : new Item(7936, amt));
			int runes = player.getGameAttributes().getAttribute("priest-in-peril:rune", 50);
			if (runes > amt) {
				amt = runes - amt;
			}
			if (amt > runes) {
				amt = 0;
			}
			if (amt == runes) {
				amt = amt - runes;
			}
			if (amt < 0) {
				amt = 0;
			}
			player.getGameAttributes().setAttribute("/save:priest-in-peril:rune", amt);
			if (player.getGameAttributes().getAttribute("priest-in-peril:rune", 50) <= 0) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Excellent! That should do it! I will bless these stones", "and place them within the well, and Misthalin should be", "protected once more!");
				stage = 152;
			} else {
				end();
			}
			break;
		case 152:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Please take this dagger; it has been handed down within", "my family for generations and is filled with the power of", "Saradomin. You will find that");
			stage = 153;
			break;
		case 153:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "it has the power to prevent werewolves from adopting", "their wolf form in combat as long as you have it", "equipped.");
			stage = 154;
			break;
		case 154:
			quest.finish(player);
			end();
			break;
		case 900:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Why, what happened?");
			stage = 901;
			break;
		case 901:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "From what I can tell, after you killed the guard dog", "who protected the entrance to the mouments, those", "Zamorakians forced the door into the main chamber");
			stage = 902;
			break;
		case 902:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "and have used some kind of evil potion upon the well", "which leads to the source of the river Salve. As they", "have done this at the very mouth of the river");
			stage = 903;
			break;
		case 903:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "it will spread along the entire river, disrupting the", "blessing placed upon it and allowing the evil creatures of", "Morytania to invade at their leisure.");
			stage = 904;
			break;
		case 904:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What can we do to prevent that?");
			stage = 905;
			break;
		case 905:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, as you can see, I have placed a holy barrier on", "the entrance to this room from the South, but it is not", "very powerful and required me to remain");
			stage = 906;
			break;
		case 906:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "here focussing upon it to keep it intact. Should an", "attack come, they would be able to breach this defence", "very quickly indeed. What we need to do is");
			stage = 907;
			break;
		case 907:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "find some kind of way of removing or counteracting the", "evil magic that has been put into the river source at the", "well, so that the river will flow pure once again.");
			stage = 908;
			break;
		case 908:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Couldn't you bless the river to purify it? Like you did", "with the water I took from the well?");
			stage = 909;
			break;
		case 909:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "No, that would not work, the power I have from", "Saradomin is not great enough to cleanse an entire", "river of this foul Zamorakian pollutant.");
			stage = 910;
			break;
		case 910:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I have only one idea how we could possibly cleanse the", "river.");
			stage = 911;
			break;
		case 911:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What's that?");
			stage = 912;
			break;
		case 912:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I have heard rumours recently that Mages have found", "some secret ore that absorbs magic into it and allows", "them to create runes.");
			stage = 913;
			break;
		case 913:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Should you be able to collect enough of this ore, it is", "possible it will soak up the evil potion that has been", "poured into the river, and purify it.");
			stage = 914;
			break;
		case 914:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Kind of like a filter? Okay, I guess it's worth a try.", "How many should I get?");
			stage = 915;
			break;
		case 915:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, I have no knowledge of these ores other than", "speculation and gossip, but if the things I hear are true", "around fifty should be sufficient for the task.");
			stage = 916;
			break;
		case 916:
			quest.setStage(player, 18);
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 1049 };
	}
}
