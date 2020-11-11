package plugin.dialogue;

import core.game.container.impl.EquipmentContainer;
import core.game.content.global.BirdNest;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;

/**
 * Represents the Wyson the gardener dialogue.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class WysonTheGardenerDialogue extends DialoguePlugin {

	/**
	 * Represents the coins item that can be used.
	 */
	private static final Item[] COINS = new Item[] { new Item(995, 15), new Item(995, 20) };

	/**
	 * Represents the woad leaf item.
	 */
	private static final Item WOAD_LEAF = new Item(1793, 1);

	/**
	 * The mole claw item.
	 */
	private static final Item MOLE_CLAW = new Item(7416);

	/**
	 * The mole skin.
	 */
	private static final Item MOLE_SKIN = new Item(7418);

	/**
	 * If its a bird nest reward.
	 */
	private boolean birdNest;
	private boolean whiteLily;

	/**
	 * Constructs a new {@code WysonTheGardenerDialogue} {@code Object}.
	 */
	public WysonTheGardenerDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code WysonTheGardenerDialogue} {@code Object}.
	 * @param player the player.
	 */
	public WysonTheGardenerDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new WysonTheGardenerDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		birdNest = player.getInventory().containsItem(MOLE_CLAW) || player.getInventory().containsItem(MOLE_SKIN);
		whiteLily = player.getInventory().containsItem(MOLE_SKIN)
				&& player.getEquipment().getId(EquipmentContainer.SLOT_SHIELD) == 14579;
		if (birdNest) {
			npc("If I'm not mistaken, you've got some mole bits there!", "I'll trade 'em for bird nest if ye likes.");
			stage = 100;
			return true;
		}
		npc("I'm the head gardener around here.", "If you're looking for woad leaves, or if you need help", "with owt, I'm yer man.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		int moleClaws = 0;
		int moleSkin = 0;
		if (birdNest) {
			moleClaws = player.getInventory().getAmount(MOLE_CLAW);
			moleSkin = player.getInventory().getAmount(MOLE_SKIN);
		}
		switch (stage) {
		case 0:
			options("Yes please, I need woad leaves.", "Sorry, but I'm not interested.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				player("Yes please, I need woad leaves.");
				stage = 10;
				break;
			case 2:
				player("Sorry, but I'm not interested.");
				stage = 200;
				break;

			}
			break;
		case 10:
			npc("How much are you willing to pay?");
			stage = 11;
			break;
		case 11:
			options("How about 5 coins?", "How about 10 coins?", "How about 15 coins?", "How about 20 coins?");
			stage = 12;
			break;
		case 12:
			switch (buttonId) {
			case 1:
				player("How about 5 coins?");
				stage = 110;
				break;
			case 2:
				player("How about 10 coins?");
				stage = 110;
				break;
			case 3:
				player("How about 15 coins?");
				stage = 130;
				break;
			case 4:
				player("How about 20 coins?");
				stage = 140;
				break;

			}
			break;
		case 110:
			npc("No no, that's far too little. Woad leaves are hard to get. I", "used to have plenty but someone kept stealing them off", "me.");
			stage = 111;
			break;
		case 111:
			end();
			break;
		case 130:
			npc("Mmmm... ok, that sounds fair.");
			stage = 131;
			break;
		case 131:
			if (player.getInventory().contains(995, 15)) {
				player.getInventory().remove(COINS[0]);
				player.getInventory().add(WOAD_LEAF);
				player("Thanks.");
				player.getPacketDispatch().sendMessage("You buy a woad leaf from Wyson.");
				stage = 132;
			} else {
				end();
				player.getPacketDispatch().sendMessage("You need 15 cold coins to buy a woad leaf.");
			}
			break;
		case 132:
			npc("I'll be around if you have any more gardening needs.");
			stage = 133;
			break;
		case 133:
			end();
			break;
		case 140:
			npc("Thanks for being generous", "here's an extra woad leave.");
			stage = 141;
			break;
		case 141:
			if (player.getInventory().contains(995, 20)) {
				player.getInventory().remove(COINS[1]);
				for (int i = 0; i < 2; i++) {
					player.getInventory().add(WOAD_LEAF, player);
				}
				player("Thanks.");
				player.getPacketDispatch().sendMessage("You buy two woad leaves from Wyson.");
				stage = 132;
			} else {
				end();
				player.getPacketDispatch().sendMessage("You need 15 cold coins to buy a woad leaf.");
			}
			break;
		case 200:
			npc("Fair enough.");
			stage = 201;
			break;
		case 201:
			end();
			break;
		case 100:
			options("Yes, I will trade the mole claws.", "Okay, I will trade the mole skin.", "I'd like to trade both.", "No, thanks.");
			stage = 900;
			break;
		case 900:
			switch (buttonId) {
			case 1:
				player("Yes, I will trade the mole claws.");
				stage = 910;
				break;
			case 2:
				player("Okay, I will trade the mole skin.");
				stage = 920;
				break;
			case 3:
				player("I'd like to trade both.");
				stage = 930;
				break;
			case 4:
				player("No, thanks.");
				stage = 999;
				break;
			}
			break;
		case 910:
			if (!player.getInventory().containsItem(MOLE_CLAW)) {
				player("Sorry, I don't have any mole claws.");
				stage = 999;
				break;
			}
			end();
			player.getInventory().remove(new Item(MOLE_CLAW.getId(), moleClaws));
			addRewards(moleClaws);
			break;
		case 920:
			if (!player.getInventory().containsItem(MOLE_SKIN)) {
				player("Sorry, I don't have any mole skins.");
				stage = 999;
				break;
			}
			end();
			player.getInventory().remove(new Item(MOLE_SKIN.getId(), moleSkin));
			addRewards(moleSkin);
			break;
		case 930:
			if (!player.getInventory().containsItem(MOLE_CLAW) && !player.getInventory().containsItem(MOLE_SKIN)) {
				player("Sorry, I don't have any.");
				stage = 999;
				break;
			}
			int total = moleClaws + moleSkin;
			player.getInventory().remove(new Item(MOLE_CLAW.getId(), moleClaws));
			player.getInventory().remove(new Item(MOLE_SKIN.getId(), moleSkin));
			addRewards(total, moleSkin);
			end();
			break;
		case 999:
			end();
			break;
		}
		return true;
	}

	/**
	 * Adds nests.
	 * @param nestAmount the amount.
	 */
	private void addRewards(int nestAmount){
		addRewards(nestAmount, 0);
	}

	private void addRewards(int nestAmount, int lilyAmount) {
		player.getInventory().add(new Item(14589, lilyAmount), player);
		for (int i = 0; i < nestAmount; i++) {
			player.getInventory().add(new Item(BirdNest.getRandomNest(true).getNest().getId(), 1), player);
		}
	}

	@Override
	public int[] getIds() {
		return new int[] { 36 };
	}
}
