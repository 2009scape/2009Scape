package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the kaylee dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class KayleeDialogue extends DialoguePlugin {

	/**
	 * Represents the coins item.
	 */
	private static final Item COINS = new Item(995, 3);

	/**
	 * Represents the ale item.
	 */
	private static final Item ASGARNIAN_ALE = new Item(1905, 1);

	/**
	 * Represents the wizards mind bomb.
	 */
	private static final Item MIND_BOMB = new Item(1907, 1);

	/**
	 * Represents the dwarven stout item.
	 */
	private static final Item DWARVEN_STOUT = new Item(1913, 1);

	/**
	 * Constructs a new {@code KayleeDialogue} {@code Object}.
	 */
	public KayleeDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code KayleeDialogue} {@code Object}.
	 * @param player the player.
	 */
	public KayleeDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new KayleeDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hi! What can I get you?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			if (player.getInventory().contains(1919, 1)) {
				interpreter.sendOptions("What would you like to say?", "What ales are you serving?", "I've got this beer glass...");
				stage = 1;
			} else {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What ales are you serving?");
				stage = 20;
			}
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What ales are you serving?");
				stage = 20;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I've got these beer glasses....");
				stage = 200;
				break;
			}
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, we've got Asgarnian Ale, Wizard's Mind Bomb", "and Dwarven Stout, all for only 3 coins.");
			stage = 21;
			break;
		case 21:
			interpreter.sendOptions("What would you like to say?", "One Asgarnian Ale, please.", "I'll try the Mind Bomb.", "Can I have A Dwarven Stout?", "I don't feel like any of those.");
			stage = 22;
			break;
		case 22:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "One Asgarnian Ale, please.");
				stage = 100;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'll try the Mind Bomb.");
				stage = 120;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can I have a Dwarven Stout?");
				stage = 130;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I don't feel like any of those.");
				stage = 444;
				break;

			}
			break;
		case 100:
			if (!player.getInventory().containsItem(COINS)) {
				end();
				return true;
			}
			if (player.getInventory().remove(COINS)) {
				player.getInventory().add(ASGARNIAN_ALE);
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Thanks, " + npc.getName() + ".");
				player.getPacketDispatch().sendMessage("You buy a Asgarnian Ale.");
				stage = 101;
			} else {
				end();
				player.getPacketDispatch().sendMessage("You need 3 gold coins to buy ale.");
			}
			break;
		case 101:
			end();
			break;
		case 120:
			if (player.getInventory().remove(COINS)) {
				player.getInventory().add(MIND_BOMB);
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Thanks, " + npc.getName() + ".");
				player.getPacketDispatch().sendMessage("You buy a Wizard's Mind Bomb.");
				stage = 101;
			} else {
				end();
				player.getPacketDispatch().sendMessage("You need 3 gold coins to buy ale.");
			}
			break;
		case 130:
			if (player.getInventory().remove(COINS)) {
				player.getInventory().add(DWARVEN_STOUT);
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Thanks, " + npc.getName() + ".");
				player.getPacketDispatch().sendMessage("You buy a Dwarven Stout.");
				stage = 101;
			} else {
				end();
				player.getPacketDispatch().sendMessage("You need 3 gold coins to buy ale.");
			}
			break;
		case 200:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ooh, we'll buy those off you if", "you're interested. 2gp per glass.");
			stage = 201;
			break;
		case 201:
			interpreter.sendOptions("What would you like to say?", "Okay, sure.", "No thanks, I like empty beer glasses.");
			stage = 202;
			break;
		case 202:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Okay, sure.");
				stage = 190;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No thanks, I like empty beer glasses.");
				stage = 400;
				break;

			}
			break;
		case 190:
			if (player.getInventory().contains(1919, 1)) {
				player.getInventory().add(new Item(995, 2 * player.getInventory().getAmount(new Item(1919))));
				player.getInventory().remove(new Item(1919, player.getInventory().getAmount(new Item(1919))));
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "There you go.");
				stage = 191;
			} else
				end();
			stage = 191;
			break;
		case 191:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Thanks!");
			stage = 192;
			break;
		case 192:
			end();
			break;
		case 444:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3217, 736 };
	}
}
