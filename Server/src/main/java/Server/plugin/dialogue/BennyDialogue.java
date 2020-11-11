package plugin.dialogue;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;

/**
 * Represents the dialogue plugin used for the benny npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class BennyDialogue extends DialoguePlugin {

	/**
	 * Represents the item coins related to benny.
	 */
	private static final Item COINS = new Item(995, 50);

	/**
	 * Represents the newspaper item related to benny.
	 */
	private static final Item NEWSPAPER = new Item(11169, 1);

	/**
	 * Constructs a new {@code BennyDialogue} {@code Object}.
	 */
	public BennyDialogue() {
		/*
		 * ( empty.
		 */
	}

	/**
	 * Constructs a new {@code BennyDialogue} {@code Object}.
	 * @param player the player.
	 */
	public BennyDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new BennyDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		options("Can I have a newspaper, please?", "How much does a paper cost?", "Varrock Herald? Never heard of it.", "Anything interesting in there?");
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			switch (buttonId) {
			case 1:
				player("Can I have a newspaper, please?");
				stage = 10;
				break;
			case 2:
				player("How much does a paper cost?");
				stage = 20;
				break;
			case 3:
				player("Varrock Herald? Never heard of it.");
				stage = 30;
				break;
			case 4:
				player("Anything interesting in there?");
				stage = 40;
				break;
			}
			break;
		case 10:
			npc("Certainly, Guv. That'll be 50 gold pieces, please.");
			stage = 11;
			break;
		case 11:
			options("Sure, here you go.", "Uh, no thanks, I've changed my mind");
			stage = 12;
			break;
		case 12:
			switch (buttonId) {
			case 1:
				player("Sure, here you go.");
				stage = 13;
				break;
			case 2:
				player("No, thanks.");
				stage = 14;
				break;
			}
			break;
		case 13:
			if (!player.getInventory().contains(995, 50)) {
				end();
				player.getPacketDispatch().sendMessage("You need 50 gold coins to buy a newspaper.");

			} else if (player.getInventory().freeSlot() == 0) {
				end();
				player.getPacketDispatch().sendMessage("You don't have enough inventory space.");
			} else {
				if (!player.getInventory().containsItem(COINS)) {
					end();
					return true;
				}
				player.getInventory().remove(COINS);
				player.getInventory().add(NEWSPAPER);
				player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 0, 7);
				end();
			}
			break;
		case 14:
			npc("Ok, suit yourself. Plenty more fish in the sea.");
			stage = 100;
			break;
		case 20:
			npc("Just 50 gold pieces! An absolute bargain! Want one?");
			stage = 21;
			break;
		case 21:
			options("Yes, please.", "No, thanks.");
			stage = 22;
			break;
		case 22:
			if (buttonId == 1) {
				player("Yes, please.");
				stage = 13;
			} else if (buttonId == 2) {
				player("No, thanks.");
				stage = 14;
			}
			break;
		case 30:
			npc("For the illiterate amongst us, I shall elucidate. The", "Varrock Herald is a new newspaper. It is edited, printed", "and published by myself, Benny Gutenberg, and each", "edition promises to enthrall the reader with captivating ");
			stage = 31;
			break;
		case 31:
			npc("material! Now, can I interest you in buying one for a mere", "50 gold?");
			stage = 21;
			break;
		case 40:
			npc("Of course there is, mate. Packed full of thought provoking", "insights, contentious interviews and celebrity", "scandalmongering! An excellent read and all for just 50", "coins! Want one?");
			stage = 21;
			break;
		case 100:
			end();
			break;
		}
		return false;
	}

	@Override
	public int[] getIds() {
		return new int[] { 5925 };
	}
}
