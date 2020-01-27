package plugin.dialogue;

import java.util.List;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.map.RegionManager;

/**
 * Represents the dialogue for the taverly pet shop owner.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class PetshopOwnerTaverly extends DialoguePlugin {

	/**
	 * Represents the dog name.
	 */
	private String dogName;

	/**
	 * Represents the puppy.
	 */
	private Item puppy;

	/**
	 * Constructs a new {@code PetshopOwnerTaverly} {@code Object}.
	 */
	public PetshopOwnerTaverly() {
		/**
		 * emppty.
		 */
	}

	/**
	 * Constructs a new {@code PetshopOwnerTaverly} {@code Object}.
	 * @param player the player.
	 */
	public PetshopOwnerTaverly(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new PetshopOwnerTaverly(player);
	}

	@Override
	public boolean open(Object... args) {
		if (args.length > 1) {
			List<NPC> npcs = RegionManager.getLocalNpcs(player);
			for (NPC n : npcs) {
				if (n.getId() == 6893) {
					npc = n;
					break;
				}
			}
			dogName = (String) args[0];
			puppy = (Item) args[1];
			player("No, the " + dogName + ".");
			stage = 699;
			return true;
		}
		npc = (NPC) args[0];
		interpreter.sendOptions("Choose an option:", "Can I see your shop, please?", "How much is that puppy in the window?", "So, what sorts of pets are available?");
		stage = 1;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can I see your shop please?");
				stage = 100;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "How much is that puppy in the window?");
				stage = 200;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "So, what sorts of pets are available?");
				stage = 300;
				break;
			}
			break;
		case 100:
			end();
			npc.openShop(player);
			break;
		case 200:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "The one with the waggly tail?");
			stage = 201;
			break;
		case 201:
			player.getInterfaceManager().openChatbox(668);
			stage = 202;
			break;
		case 202:
			break;
		case 300:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, here we sell dogs, but we also have supplies for any", "other creatures you might want to raise.");
			stage = 301;
			break;
		case 301:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Such as?");
			stage = 302;
			break;
		case 302:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, we sell nuts. Those can be used to feed squirrels. If", "you want to capture a squirrel, you'll need to use the nuts", "on the trap you set, as the little scamps won't be fooled", "by anything else.");
			stage = 303;
			break;
		case 303:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'll bear that in mind.");
			stage = 304;
			break;
		case 304:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "There are also a number of fabulous and exotic lizards in", "Karmja. Some can be caught easily in a box trap, while", "other will need to be raised from an egg.");
			stage = 305;
			break;
		case 305:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Thank's alot! You've been very helpfull.");
			stage = 306;
			break;
		case 306:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "It's always a pleasure to help a fellow animal-lover. Come", "back and visit soon.");
			stage = 307;
			break;
		case 307:
			end();
			break;
		case 700:
			player("Isn't that a little steep?");
			stage = 701;
			break;
		case 701:
			npc("Well, if we gave them away for free then people would", "just buy them and dump them without a care.");
			stage = 702;
			break;
		case 702:
			npc("Dogs are a big responsibility and should be cared for.");
			stage = 703;
			break;
		case 703:
			npc("If a person in unwilling to invest 500 coins, then they", "don't deserve to have the puppy in the first place.");
			stage = 704;
			break;
		case 704:
			npc("So, do you still want one?");
			stage = 705;
			break;
		case 705:
			options("Okay, I'll take the " + dogName + ".", "No thanks.");
			stage = 706;
			break;
		case 706:
			switch (buttonId) {
			case 1:
				player("Okay, I'll take the " + dogName + ".");
				stage = 707;
				break;
			case 2:
				end();
				break;
			}
			break;
		case 707:
			if (player.getInventory().freeSlots() == 0) {
				npc("You don't have enough inventory space.");
				stage = 708;
				return true;
			}
			if (!player.getInventory().containsItem(new Item(995, 500))) {
				end();
				return true;
			}
			if (player.getInventory().remove(new Item(995, 500))) {
				player.getInventory().add(puppy);
				npc("There you go! I hope you two get on.");
				stage = 708;
			} else {
				player.getPacketDispatch().sendMessage("You don't the required coins in order to do this.");
				end();
			}
			break;
		case 708:
			end();
			break;
		case 699:
			npc("500 gold.");
			stage = 700;
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 6893 };
	}
}
