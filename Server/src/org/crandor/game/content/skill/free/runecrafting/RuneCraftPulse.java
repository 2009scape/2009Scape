package org.crandor.game.content.skill.free.runecrafting;

import org.crandor.game.container.impl.EquipmentContainer;
import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.impl.Animator.Priority;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.portal.Perks;
import org.crandor.game.node.entity.player.link.diary.DiaryType;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.tools.RandomFunction;

/**
 * A class used to craft runes.
 * @author Vexia
 */
public final class RuneCraftPulse extends SkillPulse<Item> {

	/**
	 * Represent the rune essence item.
	 */
	private static final Item RUNE_ESSENCE = new Item(1436);

	/**
	 * Represents the pure essence item.
	 */
	private static final Item PURE_ESSENCE = new Item(7936);

	/**
	 * Represents the binding necklace item.
	 */
	private static final Item BINDING_NECKLACE = new Item(5521);

	/**
	 * Represents the animation used for this pulse.
	 */
	private static final Animation ANIMATION = new Animation(791, Priority.HIGH);

	/**
	 * Represents the graphics used for this pulse.
	 */
	private static final Graphics GRAPHICS = new Graphics(186, 100);

	/**
	 * Represents the altar.
	 */
	private final Altar altar;

	/**
	 * Represents the rune we're crafting.
	 */
	private final Rune rune;

	/**
	 * Represents the combination rune(if any)
	 */
	private final CombinationRune combo;

	/**
	 * Represents if it's a combination pulse.
	 */
	private final boolean combination;

	/**
	 * Represents the talisman to remove.
	 */
	private Talisman talisman;

	/**
	 * Constructs a new {@code RuneCraftPulse} {@code Object}.
	 * @param player the player.
	 * @param node the node.
	 * @param altar the altar.
	 */
	public RuneCraftPulse(Player player, Item node, final Altar altar, final boolean combination, final CombinationRune combo) {
		super(player, node);
		this.altar = altar;
		this.rune = altar.getRune();
		this.combination = combination;
		this.combo = combo;
		this.resetAnimation = false;
	}

	@Override
	public boolean checkRequirements() {
		if (!altar.isOurania() && player.getSkills().getLevel(Skills.RUNECRAFTING) < rune.getLevel()) {
			player.getPacketDispatch().sendMessage("You need a Runecrafting level of at least " + rune.getLevel() + " to craft this rune.");
			return false;
		}
		if (combination && !player.getInventory().containsItem(PURE_ESSENCE)) {
			player.getPacketDispatch().sendMessage("You need pure essence to craft this rune.");
			return false;
		}
		if (!altar.isOurania() && !rune.isNormal() && !player.getInventory().containsItem(PURE_ESSENCE)) {
			player.getPacketDispatch().sendMessage("You need pure essence to craft this rune.");
			return false;
		}
		if (!altar.isOurania() && rune.isNormal() && !player.getInventory().containsItem(PURE_ESSENCE) && !player.getInventory().containsItem(RUNE_ESSENCE)) {
			player.getPacketDispatch().sendMessage("You need rune essence or pure essence in order to craft this rune.");
			return false;
		}
		if (altar.isOurania() && !player.getInventory().containsItem(PURE_ESSENCE)) {
			player.getPacketDispatch().sendMessage("You need pure essence to craft this rune.");
			return false;
		}
		if (combination && player.getSkills().getLevel(Skills.RUNECRAFTING) < combo.getLevel()) {
			player.getPacketDispatch().sendMessage("You need a Runecrafting level of at least " + combo.getLevel() + " to combine this rune.");
			return false;
		}
		if (node != null) {
			if (node.getName().contains("rune") && !hasSpellImbue()) {
				final Rune r = Rune.forItem(node);
				final Talisman t = Talisman.forName(r.name());
				if (!player.getInventory().containsItem(t.getTalisman())) {
					player.getPacketDispatch().sendMessage("You don't have the correct talisman to combine this rune.");
					return false;
				}
				talisman = t;
			}
		}
		player.lock(4);
		return true;
	}

	@Override
	public void animate() {
		player.animate(ANIMATION);
		player.graphics(GRAPHICS);
	}

	@Override
	public boolean reward() {
		if (!combination) {
			craft();
		} else {
			combine();
		}
		return true;
	}

	@Override
	public void stop() {
		super.stop();
		player.animate(ANIMATION);
	}

	@Override
	public void message(int type) {
		switch (type) {
		case 1:
			if (altar != Altar.OURANIA) {
				player.getPacketDispatch().sendMessage("You bind the temple's power into " + (combination ? combo.getRune().getName().toLowerCase() : rune.getRune().getName().toLowerCase()) + "s.");
			} else {
				player.getPacketDispatch().sendMessage("You bind the temple's power into runes.");
			}
			break;
		}
	}

	/**
	 * Method used to craft runes.
	 */
	private final void craft() {
		final Item item = new Item(getEssence().getId(), getEssenceAmount());
		int amount = player.getInventory().getAmount(item);
		if (!altar.isOurania()) {
			Item i = new Item(rune.getRune().getId(), amount * getMultiplier());
			if (player.getInventory().remove(item) && player.getInventory().hasSpaceFor(i)) {
				if (altar == Altar.EARTH && !player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).isComplete(0, 10)) {
					player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).updateTask(player, 0, 10, true);
				} 
				if (altar == Altar.COSMIC && i.getAmount() == 56 && !player.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE).isComplete(2, 1)) {
					player.getAchievementDiaryManager().updateTask(player, DiaryType.LUMBRIDGE, 2, 1, true);
				}
			    player.getInventory().add(i);
				Perks.addDouble(player, i);
				player.getSkills().addExperience(Skills.RUNECRAFTING, rune.getExperience() * amount, true);
			}
		} else {
			if (player.getInventory().remove(item)) {
				for (int i = 0; i < amount; i++) {
					Rune rune = null;
					while (rune == null) {
						final Rune temp = Rune.values()[RandomFunction.random(Rune.values().length)];
						if (player.getSkills().getLevel(Skills.RUNECRAFTING) >= temp.getLevel()) {
							rune = temp;
						} else {
							if (RandomFunction.random(3) == 1) {
								rune = temp;
							}
						}
					}
					player.getSkills().addExperience(Skills.RUNECRAFTING, rune.getExperience() * 2, true);
					Item runeItem = rune.getRune();
					player.getInventory().add(runeItem);
					Perks.addDouble(player, runeItem);
				}
			}
		}
		if (altar == Altar.NATURE && !player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).isComplete(2, 2)) {
			player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).updateTask(player, 2, 2, true);
		}
		if (altar == Altar.WATER && !player.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE).isComplete(0, 2)) {
			player.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE).updateTask(player, 0, 2, true);
		}
	}

	/**
	 * Method used to combine runes.
	 */
	private final void combine() {
		final Item remove = node.getName().contains("talisman") ? node : talisman != null ? talisman.getTalisman() : Talisman.forName(Rune.forItem(node).name()).getTalisman();
		boolean imbued = hasSpellImbue();
		if (!imbued ? player.getInventory().remove(remove) : imbued) {
			int amount = 0;
			int essenceAmt = player.getInventory().getAmount(PURE_ESSENCE);
			final Item rune = node.getName().contains("rune") ? Rune.forItem(node).getRune() : Rune.forName(Talisman.forItem(node).name()).getRune();
			int runeAmt = player.getInventory().getAmount(rune);
			if (essenceAmt > runeAmt) {
				amount = runeAmt;
			} else {
				amount = essenceAmt;
			}
			if (combo == CombinationRune.LAVA) {
				if (!player.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE).isComplete(1, 9)) {
					player.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE).updateTask(player, 1, 9, true);
				}
			}
			if (player.getInventory().remove(new Item(PURE_ESSENCE.getId(), amount)) && player.getInventory().remove(new Item(rune.getId(), amount))) {
				for (int i = 0; i < amount; i++) {
					if (RandomFunction.random(1, 3) == 1 || hasBindingNeckalce()) {
						player.getInventory().add(new Item(combo.getRune().getId(), 1));
						player.getSkills().addExperience(Skills.RUNECRAFTING, combo.getExperience(), true);
					}
				}
				if (hasBindingNeckalce()) {
					player.getEquipment().get(EquipmentContainer.SLOT_HAT).setCharge(player.getEquipment().get(EquipmentContainer.SLOT_HAT).getCharge() - 1);
					if (1000 - player.getEquipment().get(EquipmentContainer.SLOT_HAT).getCharge() > 14) {
						player.getEquipment().remove(BINDING_NECKLACE, true);
						player.getPacketDispatch().sendMessage("Your binding necklace crumbles into dust.");
					}
				}
			}
		}
	}

	/**
	 * Checks if the player has the spell imbue.
	 * @return {@code True} if so.
	 */
	private boolean hasSpellImbue() {
		return player.getAttribute("spell:imbue", 0) > GameWorld.getTicks();
	}

	/**
	 * Gets the essence amount.
	 * @return the amount of essence.
	 */
	private int getEssenceAmount() {
		if (altar.isOurania() && player.getInventory().containsItem(PURE_ESSENCE)) {
			return player.getInventory().getAmount(PURE_ESSENCE);
		}
		if (!rune.isNormal() && player.getInventory().containsItem(PURE_ESSENCE)) {
			return player.getInventory().getAmount(PURE_ESSENCE);
		} else if (rune.isNormal() && player.getInventory().containsItem(PURE_ESSENCE)) {
			return player.getInventory().getAmount(PURE_ESSENCE);
		} else {
			return player.getInventory().getAmount(RUNE_ESSENCE);
		}
	}

	/**
	 * Gets the rune essence that needs to be defined.
	 * @return the item.
	 */
	private Item getEssence() {
		if (altar.isOurania() && player.getInventory().containsItem(PURE_ESSENCE)) {
			return PURE_ESSENCE;
		}
		if (!rune.isNormal() && player.getInventory().containsItem(PURE_ESSENCE)) {
			return PURE_ESSENCE;
		} else if (rune.isNormal() && player.getInventory().containsItem(PURE_ESSENCE)) {
			return PURE_ESSENCE;
		} else {
			return RUNE_ESSENCE;
		}
	}

	/**
	 * Gets the multiplied amount of runes to make.
	 * @return the amount.
	 */
	public int getMultiplier() {
		if (altar.isOurania()) {
			return 1;
		}
		int i = 0;
		for (int level : rune.getMultiple()) {
			if (player.getSkills().getLevel(Skills.RUNECRAFTING) >= level) {
				i++;
			}
		}
		
		if (player.hasPerk(Perks.RUNESTONE_KNOWLEDGE) && (altar == Altar.DEATH || altar == Altar.LAW || altar == Altar.COSMIC || altar == Altar.BLOOD || altar == Altar.NATURE)) {
			i *= 2;
		}

		return i != 0 ? i : 1;
	}

	/**
	 * Method used to check if the player has a binding necklace.
	 * @return <code>True</code> if so.
	 */
	public boolean hasBindingNeckalce() {
		return player.getEquipment().containsItem(BINDING_NECKLACE);
	}

	/**
	 * Gets the altar.
	 * @return The altar.
	 */
	public Altar getAltar() {
		return altar;
	}

}
