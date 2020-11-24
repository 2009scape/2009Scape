package plugin.activity.barrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import core.game.component.Component;
import core.game.container.access.InterfaceContainer;
import core.game.content.global.BossKillCounter;
import core.game.node.entity.npc.drop.DropFrequency;
import core.game.node.entity.player.Player;
import core.game.node.item.ChanceItem;
import core.game.node.item.Item;
import core.game.world.repository.Repository;
import core.tools.RandomFunction;

/**
 * The reward chest.
 * @author Emperor
 */
public final class RewardChest {

	/**
	 * The low profit drop table.
	 */
	private static final ChanceItem[] DROP_TABLE = { new ChanceItem(995, 1, 5306, 1000, 0.0, DropFrequency.COMMON), new ChanceItem(995, 306, 306, 1000, 0.0, DropFrequency.COMMON), new ChanceItem(558, 60, 60, 1000, 0.0, DropFrequency.COMMON), new ChanceItem(558, 250, 4900, 1000, 0.0, DropFrequency.COMMON), new ChanceItem(562, 115, 1890, 1000, 0.0, DropFrequency.COMMON), new ChanceItem(560, 15, 15, 1000, 0.0, DropFrequency.COMMON), new ChanceItem(560, 70, 1190, 1000, 0.0, DropFrequency.COMMON), new ChanceItem(565, 35, 630, 1000, 0.0, DropFrequency.COMMON), new ChanceItem(4740, 35, 280, 1000, 0.0, DropFrequency.COMMON), new ChanceItem(165, 1, 1, 1000, 0.0, DropFrequency.UNCOMMON), new ChanceItem(141, 1, 1, 1000, 0.0, DropFrequency.UNCOMMON), new ChanceItem(129, 1, 1, 1000, 0.0, DropFrequency.UNCOMMON), new ChanceItem(985, 1, 1, 1000, 0.0, DropFrequency.RARE), new ChanceItem(987, 1, 1, 1000, 0.0, DropFrequency.RARE), new ChanceItem(1149, 1, 1, 1000, 0.0, DropFrequency.RARE), new ChanceItem(4708, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4710, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4712, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4714, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4716, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4718, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4720, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4722, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4724, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4726, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4728, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4730, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4732, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4734, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4736, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4738, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4745, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4747, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4749, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4751, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4753, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4755, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4757, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), new ChanceItem(4759, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE), };

	/**
	 * Rewards the player.
	 * @param player The player.
	 */
	public static void reward(Player player) {
		for (boolean killed : player.getSavedData().getActivityData().getBarrowBrothers()) {
			if (killed) {
				killed = false;
			} else {
				player.sendMessage("You can't loot the chest until you kill all 6 barrows brothers.");
				player.removeAttribute("barrow:looted");// Because they haven't
														// actually looted the
														// chest yet.
				return;
			}
		}
		int mod = 1 + player.getSavedData().getActivityData().getBarrowKills();
		for (boolean killed : player.getSavedData().getActivityData().getBarrowBrothers()) {
			if (killed) {
				mod += 10;
			}
		}
		int totalKills = mod;
		if (mod > 80) {
			mod = 80;
		}
		Item[] rewards = new Item[5];
		for (int i = 0; i < 2 + RandomFunction.random(3); i++) {
			mod = RandomFunction.random(mod);
			ChanceItem reward = null;
			List<ChanceItem> list = Arrays.asList(DROP_TABLE);
			Collections.shuffle(list, new Random());
			for (ChanceItem item : list) {
				if (item.getId() > 4000 && player.getSavedData().getActivityData().getBarrowKills() <= 0) {
					continue;
				}
				if (reward == null && item.getDropFrequency() == DropFrequency.COMMON) {
					reward = item;
					continue;
				}
				double rarity = 2 << (1 + item.getDropFrequency().ordinal() << 1);
				if (RandomFunction.random((int) rarity) <= mod) {
					reward = item;
					break;
				}
			}
			Item item = null;
			if (reward.getMaximumAmount() > reward.getMinimumAmount()) {
				int amount = reward.getMinimumAmount();
				double mult = totalKills / 100;
				if (mult > 1.0) {
					mult = 1.0;
				}
				amount += RandomFunction.random((int) ((reward.getMaximumAmount() - reward.getMinimumAmount()) * mult));
				item = new Item(reward.getId(), amount);
			} else {
				item = new Item(reward.getId(), reward.getMinimumAmount());
			}
			rewards[i] = item;
			player.getInventory().add(item, player);
			player.getPacketDispatch().sendItemOnInterface(item.getId(),item.getAmount(),364,0);
			if (item.getDefinition().getName().contains("harok") || item.getDefinition().getName().contains("uthan") || item.getDefinition().getName().contains("aril") || item.getDefinition().getName().contains("orag") || item.getDefinition().getName().contains("erac") || item.getDefinition().getName().contains("hrim")) {
				Repository.sendNews(player.getUsername() + " has just received: " + item.getAmount() + " x " + item.getName() + " from Barrows.");
			}
		}
		InterfaceContainer.generateItems(player,rewards,new String[]{ "examine" },364,4);
		player.getInterfaceManager().open(new Component(364));
		BossKillCounter.addtoBarrowsCount(player);
	}

}