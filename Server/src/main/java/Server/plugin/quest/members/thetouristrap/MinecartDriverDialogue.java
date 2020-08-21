package plugin.quest.members.thetouristrap;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;

/**
 * The dialogue plugin used to handle the minecart driver.
 * @author 'Vexia
 * @version 1.0
 */
public final class MinecartDriverDialogue extends DialoguePlugin {

	/**
	 * The coins item.
	 */
	private static final Item COINS = new Item(995, 100);

	/**
	 * The quest npc.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code MinecartDriverDialogue} {@code Object}.
	 * @param player the player.
	 */
	public MinecartDriverDialogue(final Player player) {
		super(player);
	}

	/**
	 * Constructs a new {@code MinecartDriverDialogue} {@code Object}.
	 */
	public MinecartDriverDialogue() {
		/**
		 * empty.
		 */
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MinecartDriverDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		quest = player.getQuestRepository().getQuest(TouristTrap.NAME);
		switch (quest.getStage(player)) {
		case 90:
			npc("Quickly, get in the back of the cart.");
			break;
		case 80:
			interpreter.sendDialogue("The cart driver seems to be fastidiously cleaning his cart.", "It doesn�t look as if he wants to be disturbed.");
			break;
		default:
			player("Hello!");
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 90:
			switch (stage) {
			case 0:
				player("Okay, sorry.");
				stage++;
				break;
			case 1:
				end();
				break;
			case 24:
				end();
				break;
			}
			break;
		case 80:
			switch (stage) {
			case 0:
				player("Nice cart.");
				stage++;
				break;
			case 1:
				interpreter.sendDialogue("The cart driver looks around at you and tries to weigh you up.");
				stage++;
				break;
			case 2:
				npc("Hmmm.");
				stage++;
				break;
			case 3:
				interpreter.sendDialogue("He tuts to himself and starts checking the wheels.");
				stage++;
				break;
			case 4:
				player("One wagon wheel says to the other, 'I'll see you", "around'.");
				stage++;
				break;
			case 5:
				npc("<col=08088A>-- The cart driver smirks a little. --");
				stage++;
				break;
			case 6:
				interpreter.sendDialogue("He starts checking the steering on the cart.");
				stage++;
				break;
			case 7:
				player("One good turn deserves another�.");
				stage++;
				break;
			case 8:
				interpreter.sendDialogue("The cart driver smiles a bit and then turns to you.");
				stage++;
				break;
			case 9:
				npc("Are you trying to get me fired?");
				stage++;
				break;
			case 10:
				player("Fired.... no, shot perhaps!");
				stage++;
				break;
			case 11:
				npc("Ha ha ha! <col=08088A>-- The cart driver checks that", "<col=08088A>guards aren't watching. -- </col>What're you in fer?");
				stage++;
				break;
			case 12:
				player("In for a penny in for a pound.");
				stage++;
				break;
			case 13:
				interpreter.sendDialogue("The cart drivers laughs at your pun...");
				stage++;
				break;
			case 14:
				npc("Ha ha ha, oh stop it!");
				stage++;
				break;
			case 15:
				interpreter.sendDialogue("The cart driver seems much happier now.");
				stage++;
				break;
			case 16:
				npc("What can I do for you anyway?");
				stage++;
				break;
			case 17:
				player("Well, you see, it's like this...");
				stage++;
				break;
			case 18:
				npc("Yeah!");
				stage++;
				break;
			case 19:
				player("There's ten gold in it for you if you leave now no", "questions asked.");
				stage++;
				break;
			case 20:
				npc("If you're going to bribe me, at least make it worth my", "while. Now, let's say 100 Gold pieces should we? Ha ha", "ha!");
				stage++;
				break;
			case 21:
				player("A hundred it is!");
				stage++;
				break;
			case 22:
				npc("Great!");
				stage++;
				break;
			case 23:
				if (!player.getInventory().containsItem(COINS)) {
					player.getPacketDispatch().sendMessage("You need 100 gold coins.");
					end();
					return true;
				}
				if (player.getInventory().remove(COINS)) {
					quest.setStage(player, 90);
					npc("Okay, get in the back of the cart then!");
					stage++;
				}
				break;
			case 24:
				end();
				break;
			}
			break;
		default:
			switch (stage) {
			case 0:
				npc("Hello, what do you want?");
				stage++;
				break;
			case 1:
				player("Nothing, just passing by.");
				stage++;
				break;
			case 2:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 841 };
	}

}
