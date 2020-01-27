package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.map.RegionManager;

/**
 * Handles the SilkMerchantPlugin dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class SilkMerchantPlugin extends DialoguePlugin {

	/**
	 * Represents the silk item.
	 */
	private final Item SILK = new Item(950);

	/**
	 * Represents the noted silk item.
	 */
	private final Item NOTED_SILK = new Item(951);

	public SilkMerchantPlugin() {

	}

	public SilkMerchantPlugin(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 574 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			if (player.getInventory().containsItem(SILK)) {
				interpreter.sendDialogues(player, null, "Hello. I have some fine silk from Al-Kharid to sell to", "you.");
				stage = 1;
			} else if (player.getInventory().containsItem(NOTED_SILK)) {
				interpreter.sendDialogues(player, null, "I've got some silk here. Will you buy it?");
				stage = 100;
			} else {
				interpreter.sendDialogues(player, null, "Sorry, I don't have any silk.");
				stage = 200;
			}
			break;
		case 200:
			end();
			break;
		case 201:
			end();
			break;
		case 1:
			interpreter.sendDialogues(npc, null, "Ah, I may be interested in that. What sort of price were", "you looking at per piece of silk?");
			stage = 2;
			break;
		case 2:
			interpreter.sendOptions("Select an Option", "20 coins.", "30 coins.", "120 coins.", "200 coins.");
			stage = 3;
			break;
		case 3:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "20 coins.");
				stage = 1000;
				break;
			case 2:
				interpreter.sendDialogues(player, null, "30 coins.");
				stage = 2000;
				break;
			case 3:
				interpreter.sendDialogues(player, null, "120 coins.");
				stage = 300;
				break;
			case 4:
				interpreter.sendDialogues(player, null, "200 coins.");
				stage = 400;
				break;
			}
			break;
		case 1000:
		case 2000:
			interpreter.sendDialogues(npc, null, "That price is fine by me! Hand over your silk.");
			stage = stage == 1000 ? 1010 : 1011;
			break;
		case 1010:
			buy(20);
			break;
		case 1011:
			buy(30);
			break;
		case 300:
		case 400:
			interpreter.sendDialogues(npc, null, "You'll never get that much for it. I'll be generous and", "give you 50 for it.");
			stage = 500;
			break;
		case 500:// use this for selling.
			interpreter.sendOptions("Select an Option", "Ok, I guess 50 will do.", "I'll give it to you for 60.", "No, that is not enough.");
			stage = 501;
			break;
		case 501:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "Ok, I guess 50 will do.");
				stage = 510;
				break;
			case 2:
				interpreter.sendDialogues(player, null, "I'll give it to you for 60.");
				stage = 520;
				break;
			case 3:
				interpreter.sendDialogues(player, null, "No, that is not enough.");
				stage = 530;
				break;
			}
			break;
		case 510:
			buy(50);
			break;
		case 520:
			interpreter.sendDialogues(npc, null, "You drive a hard bargain, but", "I guess that will have to do.");
			stage = 521;
			break;
		case 521:
			buy(60);
			break;
		case 530:
			end();
			break;
		case 100:
			interpreter.sendDialogues(npc, null, "Silk? Yo're not carrying any silk.");
			stage = 101;
			break;
		case 101:
			interpreter.sendDialogues(player, null, "Yes I am.");
			stage = 102;
			break;
		case 102:
			interpreter.sendDialogues(npc, null, "No, you're carrying " + player.getInventory().getAmount(NOTED_SILK) + " bits of paper that say they", "can be exchanged for silk at a bank or general store.", "I'm not buying those. Fetch me some real silk, then", "we'll trade.");
			stage = 103;
			break;
		case 103:
			interpreter.sendDialogues(player, null, "Oh, alright.");
			stage = 104;
			break;
		case 104:
			end();
			break;
		}
		return true;
	}

	/**
	 * Method used to buy silk.
	 * @param price
	 */
	public final void buy(int price) {
		end();
		int amt = player.getInventory().getAmount(SILK);
		Item remove = new Item(SILK.getId(), amt);
		if (!player.getInventory().containsItem(remove)) {
			return;
		}
		if (player.getInventory().remove(remove)) {
			player.getInventory().add(new Item(995, price * amt));
		}
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new SilkMerchantPlugin(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (player.getSavedData().getGlobalData().getSilkSteal() > System.currentTimeMillis()) {
			end();
			for (NPC npc : RegionManager.getLocalNpcs(player.getLocation(), 8)) {
				if (!npc.getProperties().getCombatPulse().isAttacking() && npc.getId() == 32) {
					npc.sendChat("Hey! Get your hands off there!");
					npc.getProperties().getCombatPulse().attack(player);
					break;
				}
			}
			GameWorld.submit(new Pulse(1) {
				int count = 0;

				@Override
				public boolean pulse() {
					if (count == 0)
						npc.sendChat("You're the one who stole something from me!");
					if (count == 2) {
						npc.sendChat("Guards guards!");
						return true;
					}
					count++;
					return false;
				}

			});
			return false;
		}
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I buy silk. If you ever want to", "sell any silk, bring it here.");
		stage = 0;
		return true;
	}
}
