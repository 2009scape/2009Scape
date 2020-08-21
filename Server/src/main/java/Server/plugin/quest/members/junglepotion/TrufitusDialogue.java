package plugin.quest.members.junglepotion;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;

import plugin.quest.members.junglepotion.JunglePotion.JungleObjective;

/**
 * Handles the trufitus dialogue.
 * @author Vexia
 */
public final class TrufitusDialogue extends DialoguePlugin {

	/**
	 * The objective.
	 */
	private JungleObjective objective;

	/**
	 * The quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code TrufitusDialogue} {@code Object}.
	 */
	public TrufitusDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code TrufitusDialogue} {@code Object}.
	 * @param player the player.
	 */
	public TrufitusDialogue(final Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new TrufitusDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		quest = player.getQuestRepository().getQuest(JunglePotion.NAME);
		switch (quest.getStage(player)) {
		case 0:
			npc("Greetings Bwana! I am Trufitus Shakaya of the Tai", "Bwo Wannai village.");
			break;
		case 10:
		case 20:
		case 30:
		case 40:
		case 50:
			objective = JungleObjective.forStage(quest.getStage(player));
			npc("Hello, Bwana, do you have the " + objective.getName() + "?");
			break;
		case 60:
			interpreter.sendDialogue("Trufitus shows you some techniques in Herblore. You gain some", "experience in Herblore.");
			break;
		case 100:
			npc("My greatest respect Bwana, I have communed with", "my gods and the future");
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 0:
			switch (stage) {
			case 0:
				npc("Welcome to our humble village.");
				stage++;
				break;
			case 1:
				options("What does Bwana mean?", "Tai Bwo Wannai? What does that mean?", "It's a nice village, where is everyone?");
				stage++;
				break;
			case 2:
				switch (buttonId) {
				case 1:
					player("What does Bwana mean?");
					stage = 10;
					break;
				case 2:
					player("Tai Bwo Wannai? What does that mean?");
					stage = 20;
					break;
				case 3:
					player("It's a nice village, where is everyone?");
					stage = 30;
					break;
				}
				break;
			case 10:
				npc("Gracious sir, it means friend. And friends come in", "peace. I assume that you come in peace?");
				stage++;
				break;
			case 11:
				player("Yes, of course I do.");
				stage++;
				break;
			case 12:
				npc("Well, that is good news, as I have a proposition for", "you.");
				stage++;
				break;
			case 13:
				player("A proposition eh? Sounds interesting!");
				stage++;
				break;
			case 14:
				npc("I hoped you would think so. My people are afraid to", "stay in the village.");
				stage++;
				break;
			case 15:
				npc("They have returned to the jungle and I need to", "commune with the gods");
				stage++;
				break;
			case 16:
				npc("to see what fate befalls us. You can help me by", "collecting some herbs that I need.");
				stage++;
				break;
			case 17:
				player("Me? How Can I help?");
				stage = 33;
				break;
			case 20:
				npc("it means 'small clearing in the jungle' but it is now the", "name of our village.");
				stage++;
				break;
			case 21:
				player("It's a nice village, where is everyone?");
				stage = 30;
				break;
			case 30:
				npc("My people are afraid to stay in the village. They have", "returned to the jungle. I need to commune with the", "gods to see what fate befalls us.");
				stage++;
				break;
			case 31:
				npc("You may be able to help with this.");
				stage++;
				break;
			case 32:
				player("Me? How Can I help?");
				stage++;
				break;
			case 33:
				npc("I need to make a special brew! A potion that helps", "to commune with the gods. For this potion, I need", "special herbs, that are only found deep in the jungle.");
				stage++;
				break;
			case 34:
				npc("I can only guide you so far as the herbs are not easy", "to find. With some luck, you will find each herb in turn", "and bring it to me. I will then give you details of where", "to find the next herb.");
				stage++;
				break;
			case 35:
				npc("In return for this great favour I will give you training", "in Herblore.");
				stage++;
				break;
			case 36:
				player("It sounds like just the challenge for me. And it would", "make a nice break from killing things!");
				stage++;
				break;
			case 37:
				npc("That is excellent Bwana! The first herb that you need", "to gather is called");
				stage++;
				break;
			case 38:
				npc("Snake Weed.");
				stage++;
				break;
			case 39:
				npc("It grows near vines in an area to the south west where");
				stage++;
				break;
			case 40:
				npc("the ground turns soft and the water kisses your feet.");
				stage++;
				break;
			case 41:
				quest.start(player);
				end();
				break;
			}
			break;
		case 10:
		case 20:
		case 30:
		case 40:
		case 50:
			switch (stage) {
			case 0:
				options("Of course!", "Not yet, sorry, what's the clue again?");
				stage++;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					player("Of course!");
					stage = 10;
					break;
				case 2:
					player("Not yet, sorry, what's the clue again?");
					stage = 20;
					break;
				}
				break;
			case 10:
				if (!player.getInventory().containsItem(objective.getHerb().getProduct())) {
					npc("Please, don't try to deceive me.");
					stage = 11;
				} else {
					if (player.getInventory().remove(objective.getHerb().getProduct())) {
						quest.setStage(player, quest.getStage(player) + 10);
						interpreter.sendItemMessage(objective.getHerb().getProduct(), "You give the " + objective.getName() + " to Trufitus.");
						stage = 50;
					}
				}
				break;
			case 11:
				npc("I really need that " + objective.getName() + " if I am to make this", "potion.");
				stage++;
				break;
			case 12:
				end();
				break;
			case 20:
				npc(objective.getClue());
				stage = 11;
				break;
			case 50:
				objective = JungleObjective.forStage(quest.getStage(player));
				switch (quest.getStage(player)) {
				case 20:
					npc("Great, you have the Snake Weed! Many thanks. Ok,", "the next herb is called " + objective.getName() + ". It is related to the palm", "and crows to the east in its brother's shady profusion.");
					stage = 200;
					break;
				case 30:
					npc("Great, you have the Ardrigal! Many thanks.");
					stage = 300;
					break;
				case 40:
					npc("Well done Bwana, just two more herbs to collect.");
					stage = 400;
					break;
				case 50:
					npc("Ah Volencia Moss, beautiful. One final herb and the", "potion will be complete.");
					stage = 500;
					break;
				}
				break;
			case 200:
				npc("To the east you will find a small peninsula, it is just", "after the cliffs come down to meet the sands, here is", "where you should search for it.");
				;
				stage++;
				break;
			case 201:
				end();
				break;
			case 300:
				npc("You are doing well Bwana. The next herb is called Sito", "Foil, and it grows best where the ground has been", "blackened by the living flame.");
				stage++;
				break;
			case 301:
				end();
				break;
			case 400:
				npc("The next herb is called Volencia Moss. It clings to", "rocks for its existence. It is difficult to see, so you", "must search for it well.");
				stage++;
				break;
			case 401:
				npc("It prefers rocks of high metal content and a frequently", "disturbed environment. There is some, I believe to the", "south east of this village.");
				stage++;
				break;
			case 402:
				end();
				break;
			case 500:
				npc("This is the most difficult to find as it is inhabits  the", "darkness of the underground. It is called Rogues", "Purse, and is only to be found in caverns");
				stage++;
				break;
			case 501:
				npc("in the northern part of this island. A secret entrance to", "the caverns is set int the northern cliffs of this land.", "Take care Bwana as it may be dangerous.");
				stage++;
				break;
			case 502:
				end();
				break;
			}
			break;
		case 60:
			switch (stage) {
			case 50:
				npc("Most excellent Bwana! You have returned all the herbs", "to me and, I can finish the preparations for the potion,", "and at last divine with the gods.");
				stage = 600;
				break;
			case 600:
				npc("Many blessings on you! I must now prepare, please", "excuse me while I make the arrangements.");
				stage = -1;
				break;
			case -1:
				interpreter.sendDialogue("Trufitus shows you some techniques in Herblore. You gain some", "experience in Herblore.");
				stage++;
				break;
			case 0:
				quest.finish(player);
				end();
				break;
			}
			break;
		case 100:
			switch (stage) {
			case 0:
				npc("looks good for my people. We are happy now that the", "gods are not angry with us.");
				stage++;
				break;
			case 1:
				npc("With some blessings we will be safe here.");
				stage++;
				break;
			case 2:
				npc("You should deliver the good news to Bwana Timfraku,", "Chief of Tai Bwo Wannai. He lives in a raised hut not", "far from here.");
				stage++;
				break;
			case 3:
				end();
				break;
			}
			break;
		}
		return true;
	}
	//merlin
	//ptreas
	//prince alli
	//roving elves

	@Override
	public int[] getIds() {
		return new int[] { 740 };
	}

}
