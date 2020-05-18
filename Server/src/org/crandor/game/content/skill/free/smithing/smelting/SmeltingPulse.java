package org.crandor.game.content.skill.free.smithing.smelting;

import org.crandor.game.content.global.SkillcapePerks;
import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.diary.DiaryType;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.tools.RandomFunction;
import org.crandor.tools.StringUtils;

/**
 * Represents the pulse used to smelt.
 * @author 'Vexia
 */
public class SmeltingPulse extends SkillPulse<Item> {

	/**
	 * The ring of forging item.
	 */
	private static final Item RING_OF_FORGING = new Item(2568);

	/**
	 * Represents the bar to make.
	 */
	private final Bar bar;

	/**
	 * Represents if using the super heat spell.
	 */
	private final boolean superHeat;

	/**
	 * The ticks passed.
	 */
	private int ticks;

	/**
	 * Represents the amount to produce.
	 */
	private int amount;

	/**
	 * Constructs a new {@code SmeltingPulse} {@code Object}.
	 * @param player the player.
	 * @param node the node.
	 * @param bar the bar.
	 * @param amount the amount.
	 */
	public SmeltingPulse(Player player, Item node, Bar bar, int amount) {
		super(player, node);
		this.bar = bar;
		this.amount = amount;
		this.superHeat = false;
	}

	/**
	 * Constructs a new {@code SmeltingPulse} {@code Object}.
	 * @param player the player.
	 * @param node the node.
	 * @param bar the bar.
	 * @param amount the amount.
	 * @param heat the heat.
	 */
	public SmeltingPulse(Player player, Item node, Bar bar, int amount, boolean heat) {
		super(player, node);
		this.bar = bar;
		this.amount = amount;
		this.superHeat = heat;
		this.resetAnimation = false;
	}

	@Override
	public boolean checkRequirements() {
		player.getInterfaceManager().closeChatbox();
		if (bar == null || player == null) {
			return false;
		}
		if (bar == Bar.BLURITE && !player.getQuestRepository().isComplete("The Knight's Sword")) {
			return false;
		}
		if (player.getSkills().getLevel(Skills.SMITHING) < bar.getLevel()) {
			player.getPacketDispatch().sendMessage("You need a Smithing level of at least " + bar.getLevel() + " in order to smelt " + bar.getProduct().getName().toLowerCase().replace("bar", "") + ".");
			player.getInterfaceManager().closeChatbox();
			return false;
		}
		for (Item item : bar.getOres()) {
			if (!player.getInventory().contains(item.getId(), item.getAmount())) {
				player.getPacketDispatch().sendMessage("You do not have the required ores to make this bar.");
				return false;
			}
		}
		return true;
	}

	@Override
	public void animate() {
		if (ticks == 0 || ticks % 5 == 0) {
			if (superHeat) {
				player.visualize(Animation.create(725), new Graphics(148, 96));
			} else {
				player.animate(Animation.create(899));
			}
		}
	}

	@Override
	public boolean reward() {
		if (!superHeat && ++ticks % 5 != 0) {
			return false;
		}
		if (!superHeat) {
			player.getPacketDispatch().sendMessage("You place the required ores and attempt to create a bar of " + StringUtils.formatDisplayName(bar.toString().toLowerCase()) + ".");
		}
		for (Item i : bar.getOres()) {
			if (!player.getInventory().remove(i)) {
				return true;
			}
		}
		if (success(player)) {
			int amt = player.getInventory().freeSlots() != 0 && player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).getLevel() != -1 && player.getAchievementDiaryManager().checkSmithReward(bar) && RandomFunction.random(100) <= 10 ? 2 : 1;
			if (amt != 1) {
				player.sendMessage("Throught the powers of your varrock armour you recieve double the reward.");
			}
			player.getInventory().add(new Item(bar.getProduct().getId(), amt));
			double xp = bar.getExperience();
			if (player.getEquipment().get(9) != null && player.getEquipment().get(9).getId() == 776 && bar.getProduct().getId() == 2357){
				xp = 56.2;
			}
			if (SkillcapePerks.hasSkillcapePerk(player, SkillcapePerks.SMITHING) && bar.getProduct().getId() == 2357) {
				xp = 56.2;
			}
			player.getSkills().addExperience(Skills.SMITHING, xp, true);
			if (!superHeat) {
				player.getPacketDispatch().sendMessage("You retrieve a bar of " + bar.getProduct().getName().toLowerCase().replace(" bar", "") + ".");
			}
		} else {
			player.getPacketDispatch().sendMessage("The ore is too impure and you fail to refine it.");
		}
		amount--;
		return amount < 1;
	}

	/**
	 * Checks if the player has a ring of forging.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public boolean hasForgingRing(Player player) {
		return player.getEquipment().containsItem(RING_OF_FORGING);
	}

	/**
	 * Checks if the forging is a succes.
	 * @param player the player.
	 * @return {@code True} if success.
	 */
	public boolean success(Player player) {
		if (bar == Bar.IRON && !superHeat) {
			if (hasForgingRing(player) && bar == Bar.IRON) {
				return true;
			} else {
				return RandomFunction.getRandom(100) <= (player.getSkills().getLevel(Skills.SMITHING) >= 45 ? 80 : 50);
			}
		}
		return true;
	}

}
