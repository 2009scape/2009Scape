package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the dialogue used for the klarense npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class KlarenseDialogue extends DialoguePlugin {

	/**
	 * Represents the amount of coins.
	 */
	private static final Item COINS = new Item(995, 2000);

	/**
	 * Represents the quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code KlarenseDialogue} {@code Object}.
	 */
	public KlarenseDialogue() {
		/**
		 * emptpy.
		 */
	}

	/**
	 * Constructs a new {@code KlarenseDialogue} {@code Object}.
	 * @param player the player.
	 */
	public KlarenseDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new KlarenseDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("Dragon Slayer");
		if (args.length > 1) {
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Hey, stay off my ship! That's private property!");
			stage = 0;
			return true;
		}
		switch (quest.getStage(player)) {
		case 100:
			player("Hey, that's my ship!");
			stage = -1;
			break;
		default:
			if (player.getSavedData().getQuestData().getDragonSlayerAttribute("ship")) {
				npc("Hello, captain! Here to inspect your new ship? Just a", "little work and she'll be seaworthy again.");
				stage = 400;
				return true;
			}
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "So, are you interested in buying the Lady Lumbridge?", "Now, I'll be straight with you: she's not quite seaworthy", "right now, but give her a bit of work and she'll be the", "nippiest ship this side of Port Khazard.");
			stage = 1;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		if (stage == 400) {
			end();
			return true;
		}
		switch (quest.getStage(player)) {
		case 100:
			switch (stage) {
			case 0:
				end();
				break;
			case -1:
				npc("No, it's not. It may, to the untrained eye, at first", "appear to be the Lady Lumbridge, but it is definitely", "not. It's my new ship. It just happens to look slightly", "similar, is all.");
				stage = 1;
				break;
			case 1:
				player("It has Lady Lumbridge pained out and 'Klarense's", "Cruiser' painted over it!");
				stage = 2;
				break;
			case 2:
				npc("Nope, you're mistaken. It's my new boat.");
				stage = 3;
				break;
			case 3:
				end();
				break;
			}
			break;
		case 20:
			switch (stage) {
			case 0:
				end();
				break;
			case 1:
				player("Would you take me to Crandor when she's ready?");
				stage = 2;
				break;
			case 2:
				npc("Crandor? You're joking, right?");
				stage = 3;
				break;
			case 3:
				player("No. I want to go to Crandor.");
				stage = 4;
				break;
			case 4:
				npc("Then you must be crazy.");
				stage = 5;
				break;
			case 5:
				npc("That island is surrounded by reefs that would rip this", "ship to shreds. Even if you found a map, you'd need", "an experienced captain to stand a chance of getting", "through");
				stage = 6;
				break;
			case 6:
				npc("even if you gould get to it, there's no way I'm going", "any closer to that dragon than I have to. They say it", "can destroy whole ships with one bite.");
				stage = 7;
				break;
			case 7:
				player("I'd like to buy her.");
				stage = 8;
				break;
			case 8:
				npc("Of course! I'm sure the work needed to do on it", "wouldn't be too expensive.");
				stage = 9;
				break;
			case 9:
				npc("How does, 2,000 gold sound? I'll even throw in my", "cabin boy, Jenkiins, for free! He'll swab the decks and", "splice the mainsails for you!");
				stage = 10;
				break;
			case 10:
				player("Yep, sounds good.");
				stage = 11;
				break;
			case 11:
				if (!player.getInventory().containsItem(COINS)) {
					player("...except I don't have that kind of money on me...");
					stage = 12;
				} else {
					if (!player.getInventory().containsItem(COINS)) {
						end();
						return false;
					}
					if (player.getInventory().remove(COINS)) {
						npc("Okey dokey, she's all yours!");
						player.getSavedData().getQuestData().setDragonSlayerAttribute("ship", true);
						stage = 12;
					}
				}
				break;
			case 12:
				end();
				break;
			}
			break;
		default:
			switch (stage) {
			case 0:
				end();
				break;
			case 1:
				interpreter.sendOptions("Select an Option", "Do you know when she will be seaworthy?", "Why is she damaged?", "Ah, well, never mind.");
				stage = 2;
				break;
			case 2:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Do you know when she will be seaworthy?");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Why is she damaged?");
					stage = 20;
					break;
				case 3:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Ah, well, never mind.");
					stage = 30;
					break;
				}
				break;
			case 10:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "No, not really. Port Sarim's shipbuilders aren't very", "efficient so it could be quite a while.");
				stage = 11;
				break;
			case 11:
				end();
				break;
			case 20:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Oh, there was no particular accident. It's just years of", "wear and tear.");
				stage = 21;
				break;
			case 21:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "The Lady Lumbridge is an old crandorian fishing ship -", "the last one of her kind, as far as I know. That kind of", "ship was always mightily manoeuvrable, but not too", "tough.");
				stage = 22;
				break;
			case 22:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "She happend to be somewhere else when Crandor was", "destroyed, and she's had serveral owners since then. Not", "all of them have looked after her too well,");
				stage = 23;
				break;
			case 23:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "but once she's patched up, she'll be good as new!");
				stage = 24;
				break;
			case 24:
				end();
				break;
			case 30:
				end();
				break;
			case 400:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 744 };
	}
}
