package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.map.Location;

/**
 * Represents the dialogue plugin used for shantay.
 * @author Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class ShantayDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code ShantayDialogue} {@code Object}.
	 */
	public ShantayDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code ShantayDialogue} {@code Object}.
	 * @param player the player.
	 */
	public ShantayDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ShantayDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		if (args[0] != null && args[0] instanceof NPC) {
			npc = (NPC) args[0];
		}
		if (args.length == 2) {
			player.getPacketDispatch().sendMessage("Shantay saunters over to talk with you.");
			interpreter.sendDialogues(836, FacialExpression.NO_EXPRESSION, "If you want to be let out, you have to pay a fine of", "five gold. Do you want to pay now?");
			stage = 703;
			return true;
		}
		interpreter.sendDialogues(836, FacialExpression.NO_EXPRESSION, "Hello effendi, I am Shantay.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(836, FacialExpression.NO_EXPRESSION, "I see you're new. Please read the billboard poster", "before going into the desert. It'll give yer details on the", "dangers you can face.");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(836, FacialExpression.NO_EXPRESSION, "There is a heartbroken mother just past the gates and", "in the desert. Her name is Irena and she mourns her", "lost daughter. Such a shame.");
			stage = 2;
			break;
		case 2:
			interpreter.sendOptions("Select an Option", "What is this place?", "Can I see what you have to sell please?", "I must be going.", "I want to buy a Shantay pass for 5 gold coins.");
			stage = 3;
			break;
		case 3:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What is this place?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Can I see what you have to sell please?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I must be going.");
				stage = 30;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I want to buy a Shantay pass for 5 gold coins.");
				stage = 40;
				break;

			}
			break;
		case 40:
			if (!player.getInventory().contains(995, 5)) {
				end();
				return true;
			}
			if (player.getInventory().remove(new Item(995, 5))) {
				player.getInventory().add(new Item(1854));
				interpreter.sendItemMessage(1854, "You purchase a Shantay Pass.");
			} else {
				interpreter.sendDialogues(player, null, "Sorry, I don't seem to have enough money.");
			}
			stage = 41;
			break;
		case 41:
			end();
			break;
		case 30:
			interpreter.sendDialogues(836, FacialExpression.NO_EXPRESSION, "So long...");
			stage = 31;
			break;
		case 31:
			end();
			break;
		case 10:
			interpreter.sendDialogues(836, FacialExpression.NO_EXPRESSION, "This is the pass of Shantay. I guard this area with my", "men. I am responsible for keeping this pass open and", "repaired.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(836, FacialExpression.NO_EXPRESSION, "My men and I prevent outlaws from getting out of the", "desert. And we stop the inexeperienced from a dry death", "in the sands. Which would you say you were?");
			stage = 12;
			break;
		case 12:
			interpreter.sendOptions("Select an Option", "I am definitely an outlaw, prepare to die!", "I am a little inexperienced.", "Er, neither, I'm an adventurer.");
			stage = 13;
			break;
		case 13:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "I am definitely an outlaw, prepare to die.");
				stage = 700;
				break;
			case 2:
				interpreter.sendDialogues(player, null, "I am a little inexperienced.");
				stage = 710;
				break;
			case 3:
				interpreter.sendDialogues(player, null, "Er, neither, I'm an adventurer.");
				stage = 720;
				break;
			}
			break;
		case 20:
			interpreter.sendDialogues(836, FacialExpression.NO_EXPRESSION, "Absolutely Effendi!");
			stage = 21;
			break;
		case 21:
			end();
			npc.openShop(player);
			break;
		case 700:
			interpreter.sendDialogues(836, null, "Ha, very funny.....");
			stage = 701;
			break;
		case 701:
			interpreter.sendDialogues(836, null, "Guards arrest him!");
			stage = 702;
			break;
		case 702:
			player.getPacketDispatch().sendMessage("The guards arrest you and place you in the jail.");
			close();
			player.lock(10);
			GameWorld.submit(new Pulse(3, player) {
				@Override
				public boolean pulse() {
					player.setAttribute("/save:shantay-jail", true);
					player.getProperties().setTeleportLocation(Location.create(3298, 3123, 0));
					interpreter.sendDialogues(836, null, "You'll have to stay in there until you pay the fine of", "five gold pieces. Do you want to pay now?");
					stage = 703;
					return true;
				}
			});
			break;
		case 703:
			interpreter.sendOptions("Select an Option", "Yes, okay.", "No thanks, you're not having my money.");
			stage = 704;
			break;
		case 704:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "Yes, okay.");
				stage = 705;
				break;
			case 2:
				interpreter.sendDialogues(player, null, "No thanks, you're not having my money.");
				stage = 800;
				break;
			}
			break;
		case 705:
			if (player.getInventory().remove(new Item(995, 5))) {
				player.getPacketDispatch().sendMessage("You hand over the five gold pieces to Shantay.");
				player.getPacketDispatch().sendMessage("Shantay unlocks the door to the cell.");
				interpreter.sendDialogues(836, null, "Great, Effendi, now please try to keep the peace.");
				player.removeAttribute("shantay-jail");
				stage = 822;
			} else {
				interpreter.sendDialogues(player, null, "Sorry, I don't seem to have enough money.");
				stage = 825;
			}
			break;
		case 800:
			interpreter.sendDialogues(836, null, "You have a choice. You can either pay five gold pieces", "or... You can be transported to a maximum security", "prision in Port Sarim");
			stage = 801;
			break;
		case 801:
			interpreter.sendDialogues(836, null, "Will you pay the five gold pieces?");
			stage = 802;
			break;
		case 802:
			interpreter.sendOptions("Select an Option", "Yes, okay.", "No, do your worst!");
			stage = 803;
			break;
		case 803:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "Yes, okay.");
				stage = 820;
				break;
			case 2:
				interpreter.sendDialogues(player, null, "No, do your worst!");
				stage = 804;
				break;
			}
			break;
		case 804:
			interpreter.sendDialogues(836, null, "You are to be transported to a maximum security", "prision in Port Sarim. I hope you've learn an important", "lesson from this.");
			stage = 805;
			break;
		case 805:
			player.getPacketDispatch().sendMessage("You find yourself in a prison.");
			player.removeAttribute("shantay-jail");
			player.getProperties().setTeleportLocation(Location.create(3018, 3188, 0));
			end();
			break;
		case 820:
			interpreter.sendDialogues(836, null, "Good, I see that you have come to your senses.");
			stage = 821;
			break;
		case 821:
			if (player.getInventory().remove(new Item(995, 5))) {
				player.getPacketDispatch().sendMessage("You hand over the five gold pieces to Shantay.");
				player.getPacketDispatch().sendMessage("Shantay unlocks the door to the cell.");
				interpreter.sendDialogues(836, null, "Great, Effendi, now please try to keep the peace.");
				stage = 822;
			} else {
				interpreter.sendDialogues(player, null, "Sorry, I don't seem to have enough money.");
				stage = 825;
			}
			break;
		case 822:
			end();
			break;
		case 825:
			end();
			break;
		case 710:
			interpreter.sendDialogues(836, null, "Can I recommend that you purchase a full waterskin", "and a knife! These items will no doubt save your life. A", "waterskin will keep water from evaporating in the desert.");
			stage = 711;
			break;
		case 711:
			interpreter.sendDialogues(836, null, "And a keen woodsman with a knife can extract juice", "from a cactus. Before you go into the desert, it's", "advisable to wear desert clothes. It's very hot in the", "desert and you'll surely cook if you wear amour.");
			stage = 712;
			break;
		case 712:
			end();
			break;
		case 720:
			interpreter.sendDialogues(836, null, "Great, I have just the thing for you the desert adventurer.", "I sell desert clothes which will keep you cool in the heat", "of the desert. I also sell waterskins so that you won't", "die in the desert.");
			stage = 721;
			break;
		case 721:
			interpreter.sendDialogues(836, null, "A waterskin and a knife help you survive from the juice", "of a cactus. Use the chest to store your items, we'll take", "them to the bank. It's hot in the desert, you'll bake in", "all that armour.");
			stage = 722;
			break;
		case 722:
			interpreter.sendDialogues(836, null, "To keep the pass open we ask for 5 gold pieces. And", "we give you a Shantay Pass, just ask to see what I sell", "to buy one.");
			stage = 723;
			break;
		case 723:
			end();
			break;
		case 730:
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 836 };
	}
}
