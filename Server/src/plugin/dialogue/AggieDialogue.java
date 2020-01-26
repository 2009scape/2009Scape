package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.map.Location;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.update.flag.context.Animation;

/**
 * Represents the dialogue plugin used for the aggie npc.
 * @author 'Vexia
 * @version 1.5
 */
@InitializablePlugin
public final class AggieDialogue extends DialoguePlugin {

	/**
	 * Represents the animaton used to make the dye with aggie.
	 */
	private static final Animation ANIMATION = new Animation(4352);

	/**
	 * Represents the ingredients needed to make paste.
	 */
	private static final Item[] PASTE_INGREDIENTS = new Item[] { new Item(592), new Item(1951), new Item(1929), new Item(1933) };

	/**
	 * Represents the cauldron location
	 */
	private final static Location CAULDRON_LOCATION = Location.create(3085, 3258, 0);

	/**
	 * Represents the coins item.
	 */
	private static final Item COINS = new Item(995, 5);

	/**
	 * Represents the woad leaves item.
	 */
	private static final Item WOAD_LEAVES = new Item(1793, 2);

	/**
	 * Represents the onions item.
	 */
	private static final Item ONIONS = new Item(1957, 2);

	/**
	 * Represents the redberries item.
	 */
	private static final Item REDBERRIES = new Item(1951, 3);

	/**
	 * Represents the skin paste item.
	 */
	private static final Item PASTE = new Item(2424);

	/**
	 * Represents the blue dye.
	 */
	private static final Item BLUE_DYE = new Item(1767);

	/**
	 * Represents the yellow dye item.
	 */
	private static final Item YELLOW_DYE = new Item(1765);

	/**
	 * Represents the red dye item.
	 */
	private static final Item RED_DYE = new Item(1763);

	/**
	 * Represents the quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code AggieDialogue} {@code Object}.
	 */
	public AggieDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code AggieDialogue} {@code Object}.
	 * @param player the player.
	 */
	public AggieDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new AggieDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (args.length >= 2) {
			interpreter.sendOptions("Select an Option", "What do you need to make a red dye?", "What do you need to make yellow dye?", "What do you need to make blue dye?");
			stage = 42;
			return true;
		}
		quest = player.getQuestRepository().getQuest("Prince Ali Rescue");
		npc("What can I help you with?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			if (quest.getStage(player) == 20 || quest.getStage(player) == 30 || quest.getStage(player) == 40 || quest.getStage(player) == 50 || quest.getStage(player) == 60) {
				interpreter.sendOptions("Select an Option", "Could you think of a way to make skin paste?", "What could you make for me?", "Cool, do you turn people into frogs?", "You mad old witch, you can't help me.", "Can you make dyes for me please?");
				stage = 720;
				return true;
			}
			interpreter.sendOptions("Select an Option", "What could you make for me?", "Cool, do you turn people into frogs?", "You mad old witch, you can't help me.", "Can you make dyes for me please?");
			stage = 1;
			break;
		case 720:
			switch (buttonId) {
			case 1:
				player("Could you think of a way to make skin paste?");
				stage = 721;
				break;
			case 2:
				player("What could you make for me?");
				stage = 10;
				break;
			case 3:
				player("Cool, do you turn people into frogs?");
				stage = 20;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.FURIOUS, "You mad old witch, you can't help me.");
				stage = 30;
				break;
			case 5:
				interpreter.sendDialogues(player, FacialExpression.FURIOUS, "Can you make dyes for me please?");
				stage = 40;
				break;
			}
			break;
		case 721:
			if (!hasIngredients(player)) {
				interpreter.sendDialogues(npc, null, "Why it's one of my most popular potions. The women", "here, they like to have smooth looking skin. And I must", "admit, some of the men buy it as well.");
				stage = 722;
			} else {
				interpreter.sendDialogues(npc, null, "Yes I can, I see you already have the ingredients.", "Would you like me to mix some for you now?");
				stage = 726;
			}
			break;
		case 722:
			interpreter.sendDialogues(npc, null, "I can make it for you, just get me what's needed.");
			stage = 723;
			break;
		case 723:
			player("What do you need to make it?");
			stage = 724;
			break;
		case 724:
			interpreter.sendDialogues(npc, null, "Well dearie, you need a base for the paste. That's a", "mix of ash, flour and water. Then you need redberries", "to colour it as you want. Bring me those four items", "and I will make you some.");
			stage = 725;
			break;
		case 725:
			end();
			break;
		case 726:
			interpreter.sendOptions("Select an Option", "Yes please. Mix me some skin paste.", "No thank you, I don't need any skin paste right now.");
			stage = 727;
			break;
		case 727:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Yes please. Mix me some skin paste.");
				stage = 731;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No thank you, I don't need any skin paste right now.");
				stage = 729;
				break;
			}
			break;
		case 729:
			npc("Okay dearie, that's always your choice.");
			stage = 730;
			break;
		case 730:
			end();
			break;
		case 731:
			npc("That should be simple. Hand the things to Aggie then.");
			stage = 732;
			break;
		case 732:
			if (player.getInventory().remove(PASTE_INGREDIENTS)) {
				interpreter.sendDialogue("You hand the ash, flour, water and redberries to Aggie.", "Aggie tips the ingredients into a cauldron", "and mutters some words.");
				stage = 733;
			}
			break;
		case 733:
			npc("Tourniquet, Fenderbaum, Tottenham, marshmaallow,", "MarbleArch.");
			stage = 734;
			break;
		case 734:
			if (player.getInventory().add(PASTE)) {
				interpreter.sendDialogue("Aggie hands you the skin paste.");
				stage = 735;
			}
			break;
		case 735:
			npc("There you go dearie, your skin potion. That will make", "you look good at the Varrock dances.");
			stage = 736;
			break;
		case 736:
			end();
			break;
		case 1:
			switch (buttonId) {
			case 1:
				player("What could you make for me?");
				stage = 10;
				break;
			case 2:
				player("Cool, do you turn people into frogs?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.FURIOUS, "You mad old witch, you can't help me.");
				stage = 30;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.FURIOUS, "Can you make dyes for me please?");
				stage = 40;
				break;
			}
			break;
		case 40:
			interpreter.sendDialogues(npc, FacialExpression.FURIOUS, "What sort of dye would you like? Red, yellow or blue?");
			stage = 41;
			break;
		case 41:
			interpreter.sendOptions("Select an Option", "What do you need to make a red dye?", "What do you need to make yellow dye?", "What do you need to make blue dye?");
			stage = 42;
			break;
		case 42:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.FURIOUS, "What do you need to make red dye?");
				stage = 410;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.FURIOUS, "What do you need to make yellow dye?");
				stage = 420;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.FURIOUS, "What do you need to make blue dye?");
				stage = 430;
				break;
			}
			break;
		case 430:
			interpreter.sendDialogues(npc, null, "2 woad leaves and 5 coins to you.");
			stage = 431;
			break;
		case 431:
			interpreter.sendDialogues(player, FacialExpression.FURIOUS, "Okay, make me some blue dye please.");
			stage = 432;
			break;
		case 432:
			if (player.getInventory().containsItem(COINS) && player.getInventory().containsItem(WOAD_LEAVES)) {
				player.getInventory().remove(COINS);
				player.getInventory().remove(WOAD_LEAVES);
				player.getInventory().add(BLUE_DYE);
				make(1767);
				interpreter.sendItemMessage(1767, "You hand the woad leaves and payment to Aggie. Aggie produces a blue bottle and hands it to you.");
			} else {
				interpreter.sendDialogue("You need 2 woad leaves and 5 coins.");
			}
			stage = 413;
			break;
		case 433:
			end();
			break;
		case 420:
			interpreter.sendDialogues(npc, null, "Yellow is a strange colour to get, comes from onion", "skins. I need 2 onions and 5 coins to make yellow dye.");
			stage = 421;
			break;
		case 421:
			interpreter.sendDialogues(player, FacialExpression.FURIOUS, "Okay, make me some yellow dye please.");
			stage = 422;
			break;
		case 422:
			if (player.getInventory().containsItem(COINS) && player.getInventory().containsItem(ONIONS)) {
				player.getInventory().remove(COINS);
				player.getInventory().remove(ONIONS);
				player.getInventory().add(YELLOW_DYE);
				make(1765);
				interpreter.sendItemMessage(1765, "You hand the onions and payment to Aggie. Aggie produces a yellow bottle and hands it to you.");
			} else {
				interpreter.sendDialogue("You need 2 onions and 5 coins.");
			}
			stage = 423;
			break;
		case 423:
			end();
			break;
		case 410:
			interpreter.sendDialogues(npc, null, "3 lots of redberries and 5 coins to you.");
			stage = 411;
			break;
		case 411:
			interpreter.sendDialogues(player, FacialExpression.FURIOUS, "Okay, make me some red dye please.");
			stage = 412;
			break;
		case 412:
			if (player.getInventory().containsItem(COINS) && player.getInventory().containsItem(REDBERRIES)) {
				player.getInventory().remove(COINS);
				player.getInventory().remove(REDBERRIES);
				player.getInventory().add(RED_DYE);
				make(1763);
				interpreter.sendItemMessage(1763, "You hand the berries and payment to Aggie. Aggie produces a red bottle and hands it to you.");
			} else {
				interpreter.sendDialogue("You need 3 redberrie leaves and 5 coins.");
			}
			stage = 413;
			break;
		case 413:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, null, "Oh, you like to call a witch names do you?");
			stage = 31;
			break;
		case 31:
			Item item = new Item(995, 20);
			if (player.getInventory().remove(item)) {
				interpreter.sendItemMessage(item, "Aggie waves her hands about, and you seem to be 20", "coins poorer.");
				stage = 32;
			} else {
				interpreter.sendDialogues(npc, null, "You should be careful about insulting a witch. You", "never know what shape you could wake up in.");
				stage = 34;
			}
			break;
		case 32:
			interpreter.sendDialogues(npc, null, "That's a fine for insulting a witch. You should learn", "some respect.");
			stage = 33;
			break;
		case 34:
			end();
			break;
		case 33:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, null, "Oh, not for years, but if you meet a talking chicken,", "you have probably met the professor in the manor north", "of here. A few years ago it was flying fish. That", "machine is a menace.");
			stage = 11;
			break;
		case 10:
			interpreter.sendDialogues(npc, null, "I mostly make what I find pretty. I sometimes", "make dye for the women's clothes to brighten the place", "up. I can make red, yellow and blue dyes. If you'd like", "some, just bring me the appropriate ingredients.");
			stage = 11;
			break;
		case 11:
			end();
			break;
		}
		return true;
	}

	/**
	 * Method used to make a dye.
	 * @param dye the dye.
	 */
	public final void make(int dye) {
		npc.animate(ANIMATION);
		npc.faceLocation(CAULDRON_LOCATION);
	}

	/**
	 * Method used to check if the player has the ingredients for the paste.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	private final boolean hasIngredients(final Player player) {
		for (Item i : PASTE_INGREDIENTS) {
			if (!player.getInventory().containsItem(i)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 922 };
	}
}
