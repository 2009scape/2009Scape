package plugin.random.quizmaster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.DisplayModelContext;
import org.crandor.net.packet.context.DisplayModelContext.ModelType;
import org.crandor.net.packet.out.DisplayModel;
import org.crandor.tools.RandomFunction;

/**
 * Handles the quiz master dialogue.
 * @author Vexia
 */
public final class QuizMasterDialogue extends DialoguePlugin {

	/**
	 * The correct dialogues.
	 */
	private static final String[][] CORRECT = new String[][] { { "Hey, you're good at this!", "CORRECT!", "Okay, next question!" }, { "Absolutely RIGHT!", "Keep going for the win!", "Okay, next question!" }, { "Wow, you're a smart one!", "You're absolutely right!", "Okay, next question!" }, { "COR-RECT!", "Okay, next question!" }, { "DING DING DING", "That's RIGHT! Good for you!", "Okay, next question!" }, { "YES!", "You're RIGHT!", "Okay, next question!" } };

	/**
	 * The wrong dialogues.
	 */
	private static final String[][] WRONG = new String[][] { { "Huh...? Didn't you know that one?", "You're supposed to pick the ODD ONE OUT.", "Now, let's start again..." }, { "No. No, that's not right at all.", "You're supposed to pick the ODD ONE OUT.", "Now let's start again..." }, { "No, sorry, Try harder!", "You're supposed to pick the ODD ONE OUT.", "Now, let's start again..." }, { "Better luck next time!", "You're supposed to pick the ODD ONE OUT.", "Now, let's start again..." }, { "WRONG! That's just WRONG!", "You're supposed to pick the ODD ONE OUT.", "Now, let's start again..." }, { "WRONG WRONG WRONG!", "You're supposed to pick the ODD ONE OUT.", "Now, let's start again..." }, { "No, no, no... That's completely WRONG!", "You're supposed to pick the ODD ONE OUT.", "Now, let's start again..." }, { "Nope, that's not it.", "You're supposed to pick the ODD ONE OUT.", "Now, let's start again..." }, { "BZZZZZZZ! WRONG!", "You're supposed to pick the ODD ONE OUT.", "Now, let's start again..." } };

	/**
	 * The coin reward.
	 */
	private static final Item COINS = new Item(995, 1000);

	/**
	 * The mystery box reward.
	 */
	private static final Item MYSTERY_BOX = new Item(6199);

	/**
	 * The quiz master event.
	 */
	private QuizMasterEvent event;

	/**
	 * The wrong button id.
	 */
	private int wrongId;

	/**
	 * Constructs a new {@code QuizMasterDialogue} {@code Object}.
	 */
	public QuizMasterDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code QuizMasterDialogue} {@code Object}.
	 * @param player the player.
	 */
	public QuizMasterDialogue(final Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new QuizMasterDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		event = (QuizMasterEvent) args[0];
		if (!event.isStartedQuiz()) {
			event.setStartedQuiz(true);
		} else {
			if (event.getScore() == 4) {
				npc(BLUE + "CONGRATULATIONS!", "You are a " + RED + "WINNER</col>!", "Please choose your " + BLUE + "PRIZE</col>!");
				stage = 4;
				return true;
			}
			npc(event.getScore() == 0 ? ("Okay! First question!") : "Okay! Next question!");
			stage = 2;
			return true;
		}
		npc("WELCOME to the GREATEST QUIZ SHOW in the", "whole of " + GameWorld.getName() + ".", "<col=7f0000>O <col=6f000f>D <col=5f001f>D <col=4f002f>O <col=3f003f>N <col=2f004f>E <col=1f005f>O <col=0f006f>U <col=00007f>T");
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.THINKING, "I'm sure I didn't ask to take part in a quiz show...");
			stage++;
			break;
		case 1:
			npc("Please welcome our newest contestant:", RED + player.getUsername(), "Just pick the ODD ONE OUT", "Four questions right, and then you win!");
			stage++;
			break;
		case 2:
			display(QuizSet.getQuizSet());
			player.getInterfaceManager().openChatbox(191);
			stage++;
			break;
		case 3:
			buttonId -= 4;
			final boolean wrong = wrongId != buttonId;
			if (wrong) {
				event.resetScore();
			} else {
				event.incrementScore();
			}
			if (event.getScore() == 4) {
				npc(BLUE + "CONGRATULATIONS!", "You are a " + RED + "WINNER</col>!", "Please choose your " + BLUE + "PRIZE</col>!");
				stage = 4;
				break;
			}
			npc(getDialogue(wrong ? WRONG : CORRECT));
			stage = 2;
			break;
		case 4:
			options("1000 Coins", "Mystery Box");
			stage++;
			break;
		case 5:
			reward(buttonId == 1 ? COINS : MYSTERY_BOX);
			break;
		default:
			System.out.println("UNHANDLED QUIZ STAGE " + stage + ", " + buttonId + ", " + interfaceId);
			break;
		}
		return true;
	}

	/**
	 * Rewards the player.
	 * @param item the item.
	 */
	private void reward(Item item) {
		end();
		event.terminate();
		Location destination = player.getAttribute("ame:location");
		if (destination != null) {
			if (!player.getInventory().hasSpaceFor(item)) {
				GroundItemManager.create(item, destination, player);
			} else {
				player.getInventory().add(item);
			}
			player.getProperties().setTeleportLocation(destination);
		}
	}

	/**
	 * Displays a quiz.
	 * @param quiz the quiz.
	 */
	private void display(QuizSet[] quiz) {
		QuizSet correct = quiz[0];
		QuizSet wrong = quiz[1];
		List<Integer> childs = new ArrayList<>();
		childs.add(1);
		childs.add(2);
		childs.add(3);
		Collections.shuffle(childs);
		for (int i = 0; i < 2; i++) {
			sendItem(correct.getModel(i), childs.get(i));
		}
		wrongId = childs.get(2);
		sendItem(wrong.getModel(RandomFunction.random(wrong.ids.length)), wrongId);
	}

	/**
	 * Sends an item.
	 * @param model the model.
	 * @param index the index.
	 */
	private void sendItem(int model, int index) {
		PacketRepository.send(DisplayModel.class, new DisplayModelContext(player, ModelType.MODEL, model, 190, 191, 4 + index));
	}

	/**
	 * Gets the dialogues of a 2d array.
	 * @param dialogues the dialogues.
	 * @return the dialogues.
	 */
	public String[] getDialogue(String[][] dialogues) {
		return dialogues[RandomFunction.random(dialogues.length)];
	}

	@Override
	public int[] getIds() {
		return new int[] { 2477 };
	}

	/**
	 * Fish, 6189, 6190, Weapons - 6191, 6192, Armour - 6193, 6194, Craftings -
	 * 6195, 6196, Jewllery - 6197, 6198
	 */

	/**
	 * Represents a quiz set.
	 * @author Vexia
	 */
	public enum QuizSet {
		FISH(6189, 6190), WEAPONRY(6191, 6192), ARMOUR(6193, 6194), TOOLS(6195, 6196), JEWELLERY(6197, 6198);

		/**
		 * The item ids.
		 */
		private int[] ids;

		/**
		 * Constructs a new {@code QuizSet} {@code Object}.
		 * @param ids the ids.
		 */
		private QuizSet(int... ids) {
			this.ids = ids;
		}

		/**
		 * Gets the quiz set.
		 * @return the set.
		 */
		public static QuizSet[] getQuizSet() {
			List<QuizSet> sets = new ArrayList<>();
			for (QuizSet s : values()) {
				sets.add(s);
			}
			Collections.shuffle(sets);
			QuizSet[] set = new QuizSet[2];
			set[0] = sets.get(0);
			sets.remove(set[0]);
			set[1] = sets.get(RandomFunction.random(sets.size()));
			return set;
		}

		/**
		 * Gets the model id.
		 * @param index the index.
		 * @return the model.
		 */
		public int getModel(int index) {
			return ItemDefinition.forId(ids[index]).getInterfaceModelId();
		}

	}
}
