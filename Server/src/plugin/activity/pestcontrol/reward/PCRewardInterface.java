package plugin.activity.pestcontrol.reward;

import java.util.ArrayList;
import java.util.List;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.component.Component;
import org.crandor.game.component.ComponentDefinition;
import org.crandor.game.component.ComponentPlugin;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.herblore.Herbs;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.Plugin;
import org.crandor.tools.RandomFunction;

/**
 * Represents the pest control reward interface.
 * @author 'Vexia
 */
public final class PCRewardInterface extends ComponentPlugin {

	/**
	 * Represents the red colour.
	 */
	public static final String RED = "<col=FF0000>";

	/**
	 * Represents the green colour.
	 */
	public static final String GREEN = "<col=04B404>";

	/**
	 * Represents the white colour.
	 */
	public static final String WHITE = "<col=FFFFFF>";

	/**
	 * Represents the skill headers ordered by skill index.
	 */
	public static final int[] SKILL_HEADER = new int[] { 10, 12, 11, 15, 13, 16, 14 };

	/**
	 * Represents the skill array of exp rewards.
	 */
	public static final int[] SKILL_ARRAY = new int[] { Skills.ATTACK, Skills.STRENGTH, Skills.DEFENCE,  Skills.RANGE, Skills.MAGIC, Skills.HITPOINTS, Skills.PRAYER };

	/**
	 * Represents the skill options array.
	 */
	public static final int[] SKILL_POINTS = new int[] { 1, 10, 100 };

	/**
	 * Represents the charm points.
	 */
	public static final int[] CHARM_POINTS = new int[] { 2, 28, 56 };

	/**
	 * Represents the amount of charms to get the from the points.
	 */
	public static final int[] CHARM_AMOUNTS = new int[] { 1, 14, 28 };

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.forId(267).setPlugin(this);
		return this;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
		switch (button) {
		case 96:
			confirm(player);
			return true;
		default:
			if (button >= 34 && button <= 86) {
				if (player.getSavedData().getActivityData().getPestPoints() == 0) {
					player.getPacketDispatch().sendMessage("You don't have enough points.");
					return true;
				}
				select(player, button);
			}
			break;
		}
		return true;
	}

	/**
	 * Method used to open the pest control reward interface.
	 * @param player the player.
	 */
	public static void open(final Player player) {
		player.removeAttribute("pc-reward");
		sendString(player, "Points: " + player.getSavedData().getActivityData().getPestPoints(), 105);
		clear(player);
		player.getInterfaceManager().open(new Component(267));
	}

	/**
	 * Method used to send the skill headers.
	 * @param player the player.
	 */
	private static final void sendSkills(final Player player) {
		for (int skill : SKILL_ARRAY) {
			sendString(player, getSkillCondition(player, skill), getSkillChild(skill));
		}
	}

	/**
	 * Method used to send a string onto this interface.
	 * @param player the player instance.
	 * @param string the string to send.
	 * @param child the child to send it on.
	 */
	private static final void sendString(final Player player, String string, int child) {
		player.getPacketDispatch().sendString(string, 267, child);
	}

	/**
	 * Method used to select a reward.
	 * @param player the player.
	 * @param button the button.
	 */
	public void select(final Player player, final int button) {
		final Reward reward = Reward.forButton(button);
		final int option = reward.getOption(button);
		if (!reward.checkRequirements(player, option)) {
			return;
		}
		cacheReward(player, reward, option);
	}

	/**
	 * Method used to selected the current reward.
	 * @param player the player.
	 */
	public boolean deselect(final Player player) {
		return deselect(player, getReward(player));
	}

	/**
	 * Method used to deselect the reward.
	 * @param player the player.
	 * @param reward the reward.
	 */
	public boolean deselect(final Player player, final Reward reward) {
		if (reward == null) {
			return false;
		}
		clear(player);
		reward.deselect(player, getCachedOption(player));
		return true;
	}

	/**
	 * Method used to cache the reward.
	 * @param player
	 */
	public final void cacheReward(final Player player, final Reward reward, final int option) {
		deselect(player);// delect any previous ones.
		reward.select(player, option);
		sendString(player, "<col=F7DF22>Confirm:", 106);
		sendString(player, reward.getName(), 104);
		player.setAttribute("pc-reward", reward);
		player.setAttribute("pc-reward:option", option);
	}

	/**
	 * Method used to clear the interface with new data.
	 * @param player the player.
	 */
	public final static void clear(final Player player) {
		sendSkills(player);
		for (Reward reward : Reward.values()) {
			if (reward.isSkillReward()) {
				continue;
			}
			if (reward.charm) {
				sendString(player, (player.getSavedData().getActivityData().getPestPoints() < 2 ? RED + "You need 2 points." : GREEN + reward.getName()), reward.getHeader());
				continue;
			}
			sendString(player, (player.getSavedData().getActivityData().getPestPoints() < reward.getPoints() ? player.getSavedData().getActivityData().getPestPoints() < 1 ? RED + ("You need at least 1 point.") : RED + ("You need " + reward.getPoints() + " points.") : (GREEN + reward.getName())), reward.getHeader());
		}
	}

	/**
	 * Method used to calculate the experience the player can recieve in this
	 * skill.
	 * @param player the player.
	 * @param skill the skill.
	 * @return the experience as an integer.
	 */
	public static double calculateExperience(final Player player, final int skillId) {
		int level = player.getSkills().getStaticLevel(skillId);
		double divideBy = 30;//17.5-33 ideal range
		if (skillId == Skills.PRAYER) {
			divideBy = 67;// 34-75 ideal range
		} else if (skillId == Skills.MAGIC || skillId == Skills.RANGE) {
			divideBy = 29;//19.1-31 ideal range
		}
		return (int) ((level * level) / divideBy) * (player.getSkills().experienceMutiplier / 2);
	}

	/**
	 * Method used to get the skill condition string to send.
	 * @param player the player.
	 * @param skillId the skillId.
	 * @return the string to send.
	 */
	public static final String getSkillCondition(final Player player, final int skillId) {
		if (player.getSkills().getStaticLevel(skillId) < 25) {
			return RED + "Must reach level 25 first.";
		}
		return GREEN + getSkillXp(player, skillId);
	}

	/**
	 * Method used to get the skill experience string.
	 * @param player the player.
	 * @param skillId the skill id.
	 * @return the string.
	 */
	public static String getSkillXp(final Player player, int skillId) {
		return Skills.SKILL_NAME[skillId] + " - " + (int) calculateExperience(player, skillId) + " xp";
	}

	/**
	 * Method used to get the skill header by the index.
	 * @param skill the skill index.
	 * @return the skill child id.
	 */
	public static final int getSkillChild(final int skill) {
		return SKILL_HEADER[skill];
	}

	/**
	 * Method used to get the current reward.
	 * @param player the player.
	 * @return the reward.
	 */
	public static Reward getReward(final Player player) {
		return player.getAttribute("pc-reward", null);
	}

	/**
	 * Method used to check if the player has a reward set.
	 * @param player the player.
	 * @return the reward.
	 */
	public static boolean hasReward(final Player player) {
		return getReward(player) != null;
	}

	/**
	 * Method used to get the pest control reward option index.
	 * @param player the player.
	 * @return the option index.
	 */
	public static int getCachedOption(final Player player) {
		return player.getAttribute("pc-reward:option", 0);
	}

	/**
	 * Method used to confirm the reward.
	 * @param player the player.
	 */
	public final void confirm(final Player player) {
		if (!hasReward(player)) {
			player.getPacketDispatch().sendMessage("Please choose a reward.");
			return;
		}
		final Reward reward = getReward(player);
		if ((reward.charm || !reward.isSkillReward()) && player.getInventory().freeSlots() == 0) {
			player.getPacketDispatch().sendMessage("You don't have enough inventory space.");
			return;
		}
		final int option = getCachedOption(player);
		final int points = reward.getPoints(option);
		String message;
		player.getInterfaceManager().close();
		if (player.getSavedData().getActivityData().getPestPoints() >= points) {
			player.getSavedData().getActivityData().decreasePestPoints(points);
			if (reward.isSkillReward()) {
				final double experience = ((int) calculateExperience(player, reward.getSkill()) * points);
				player.getSkills().addExperience(reward.getSkill(), experience);
				message = "The Void Knight has granted you " + (int) (experience * player.getSkills().experienceMutiplier) + " " + reward.getName() + ".";
			} else {
				if (!reward.checkItemRequirement(player, option)) {
					return;
				}
				if (!reward.charm) {
					if (reward.getReward().length > 1) {
						Item[] pack = reward.constructPack();
						for (Item i : pack) {
							if (!player.getInventory().add(i)) {
								GroundItemManager.create(i, player);
							}
						}
					} else {
						if (!player.getInventory().add(reward.getReward()[0])) {
							GroundItemManager.create(reward.getReward()[0], player);
						}
					}
				} else {
					Item charm = new Item(reward.getReward()[0].getId());
					int amt = CHARM_AMOUNTS[option - 1];
					for (int i = 0; i < amt; i++) {
						if (!player.getInventory().add(charm)) {
							GroundItemManager.create(charm, player);
						}
					}
				}
				message = "The Void Knight has given you a " + reward.getName() + ".";
			}
			player.getDialogueInterpreter().sendDialogue(message, "<col=571D07>Remaining Void Knight Commendation Points: " + player.getSavedData().getActivityData().getPestPoints());
		}
	}

	/**
	 * Represents the rewards that are obtainable with this interface.
	 * @author 'Vexia
	 */
	public enum Reward {
		ATTACK(Skills.ATTACK, new int[] { 10, 34, 49, 56 }), STRENGTH(Skills.STRENGTH, new int[] { 11, 35, 50, 57 }), DEFENCE(Skills.DEFENCE, new int[] { 12, 36, 51, 58 }), RANGE(Skills.RANGE, new int[] { 13, 37, 52, 59 }), MAGIC(Skills.MAGIC, new int[] { 14, 38, 53, 60 }), HITPOINTS(Skills.HITPOINTS, new int[] { 15, 39, 54, 61 }), PRAYER(Skills.PRAYER, new int[] { 16, 40, 55, 62 }), HERB_PACK("Herb Pack", 30, new Item[] { Herbs.HARRALANDER.getHerb(), Herbs.RANARR.getHerb(), Herbs.TOADFLAX.getHerb(), Herbs.IRIT.getHerb(), Herbs.AVANTOE.getHerb(), Herbs.KWUARM.getHerb(), Herbs.GUAM.getHerb(), Herbs.MARRENTILL.getHerb() }, new int[] { 32, 45 }) {
			@Override
			public boolean checkItemRequirement(final Player player, final int option) {
				if (player.getSkills().getLevel(Skills.HERBLORE) < 25) {
					player.getPacketDispatch().sendMessage("You need level 25 herblore to purchase this pack.");
					return false;
				}
				return true;
			}
		},
		MINERAL_PACK("Mineral Pack", 15, new Item[] { new Item(453), new Item(440) }, new int[] { 47, 46 }) {
			@Override
			public boolean checkItemRequirement(final Player player, final int option) {
				if (player.getSkills().getLevel(Skills.MINING) < 25) {
					player.getPacketDispatch().sendMessage("You need level 25 mining to purchase this pack.");
					return false;
				}
				return true;
			}
		},
		SEED_PACK("Seed Pack", 15, new Item[] { new Item(5320), new Item(5322), new Item(5100) }, new int[] { 33, 48 }) {
			@Override
			public boolean checkItemRequirement(final Player player, final int option) {
				if (player.getSkills().getLevel(Skills.FARMING) < 25) {
					player.getPacketDispatch().sendMessage("You need level 25 farming to purchase this pack.");
					return false;
				}
				return true;
			}
		},
		VOID_MACE("Void Knight Mace", 250, new Item[] { new Item(8841) }, new int[] { 28, 41 }) {
			@Override
			public boolean checkItemRequirement(final Player player, final int option) {
				return hasVoidSkills(player);
			}
		},
		VOID_TOP("Void Knight Top", 250, new Item[] { new Item(8839) }, new int[] { 29, 42 }) {
			@Override
			public boolean checkItemRequirement(final Player player, final int option) {
				return hasVoidSkills(player);
			}
		},
		VOID_ROBES("Void Knight Robes", 250, new Item[] { new Item(8840) }, new int[] { 30, 43 }) {
			@Override
			public boolean checkItemRequirement(final Player player, final int option) {
				return hasVoidSkills(player);
			}
		},
		VOID_GLOVES("Void Knight Gloves", 150, new Item[] { new Item(8842) }, new int[] { 31, 44 }) {
			@Override
			public boolean checkItemRequirement(final Player player, final int option) {
				return hasVoidSkills(player);
			}
		},
		VOID_MAGE_HELM("Void Knight Mage Helm", 200, new Item[] { new Item(11663) }, new int[] { 63, 67 }) {
			@Override
			public boolean checkItemRequirement(final Player player, final int option) {
				return hasVoidSkills(player);
			}
		},
		VOID_RANGER_HELM("Void Knight Ranger Helm", 200, new Item[] { new Item(11664) }, new int[] { 64, 68 }) {
			@Override
			public boolean checkItemRequirement(final Player player, final int option) {
				return hasVoidSkills(player);
			}
		},
		VOID_MELEE_HELM("Void Knight Melee Helm", 200, new Item[] { new Item(11665) }, new int[] { 65, 69 }) {
			@Override
			public boolean checkItemRequirement(final Player player, final int option) {
				return hasVoidSkills(player);
			}
		},
		VOID_KNIGHT_SEAL("Void Knight Seal", 10, new Item[] { new Item(11666) }, new int[] { 66, 70 }), SPINNER_CHARM("Spinner Charm", new Item(12166), 71, 75, 76, 77), RAVAGER_CHARM("Ravager Charm", new Item(12164), 72, 81, 82, 83), TORCHER_CHARM("Torcher Charm", new Item(12167), 74, 78, 79, 80), SHIFTER_CHAR("Shifter Charm", new Item(12165), 73, 84, 85, 86);

		/**
		 * Represents the void required skills.
		 */
		private final int[] VOID_SKILLS = new int[] { Skills.HITPOINTS, Skills.ATTACK, Skills.DEFENCE, Skills.STRENGTH, Skills.RANGE, Skills.MAGIC, Skills.PRAYER };

		/**
		 * Represents the childs(header, pts).
		 */
		private final int[] childs;

		/**
		 * Represents the skill id.
		 */
		private int skill;

		/**
		 * Represents the reward items.
		 */
		private Item[] reward;

		/**
		 * Represents the reward name.
		 */
		private String name;

		/**
		 * Represents the points required for this reward.
		 */
		private int points;

		/**
		 * Represents if the reward is a charm.
		 */
		private boolean charm = false;

		/**
		 * Constructs a new {@code PCRewardInterface} {@code Object}.
		 * @param childs the childs.
		 * @param buttons the buttons.
		 */
		Reward(final int[] childs) {
			this.childs = childs;
		}

		/**
		 * Constructs a new {@code PCRewardInterface} {@code Object}.
		 * @param skill the skill.
		 * @param childs the childs.
		 */
		Reward(int skill, final int[] childs) {
			this.skill = skill;
			this.childs = childs;
		}

		/**
		 * Constructs a new {@code PCRewardInterfacce} {@code Object}.
		 * @param name the name.
		 * @param points the points.
		 * @param rewards the rewards.
		 * @param childs the childs.
		 */
		Reward(String name, int points, final Item[] reward, final int[] childs) {
			this.name = name;
			this.points = points;
			this.reward = reward;
			this.childs = childs;
		}

		/**
		 * Constructs a new {@code PCRewardInterface} {@code Object}.
		 * @param name the name.
		 * @param charm the charm.
		 * @param childs the child.
		 */
		Reward(final String name, final Item charm, final int... childs) {
			this.name = name;
			this.charm = true;
			this.reward = new Item[] { charm };
			this.childs = childs;
		}

		/**
		 * Method used to check the requirements of a reward.
		 * @param player the player.
		 * @param option the option.
		 * @return <code>True</code> if so.
		 */
		public boolean checkRequirements(final Player player, final int option) {
			if (player.getSavedData().getActivityData().getPestPoints() < getPoints(option)) {
				player.getPacketDispatch().sendMessage("You don't have enough points.");
				return false;
			}
			return isSkillReward() ? checkSkillRequirement(player, option) : checkItemRequirement(player, option);
		}

		/**
		 * Method used to select a reward.
		 * @param player the player.
		 * @param option the option.
		 */
		public void select(final Player player, final int option) {
			if (isSkillReward()) {
				skillSelect(player, option);
			} else {
				itemSelect(player, option);
			}
		}

		/**
		 * Method used to delect a reward.
		 * @param player the player.
		 * @param option the option.
		 */
		public void deselect(final Player player, final int option) {
			if (isSkillReward()) {
				skillDeselect(player, option);
			} else {
				itemDeselect(player, option);
			}
		}

		/**
		 * Method used to select a skill.
		 * @param player the player.
		 * @param option the option index.
		 */
		public final void skillSelect(final Player player, final int option) {
			sendString(player, WHITE + getSkillXp(player, skill), getHeader());
			sendString(player, WHITE + getOptionString(option), getChilds()[option]);
		}

		/**
		 * Method used to handle the item select.
		 * @param player the player.
		 * @param option the option.
		 */
		public final void itemSelect(final Player player, final int option) {
			sendString(player, WHITE + getName(), getHeader());
			if (charm) {
				sendString(player, WHITE + getOptionString(option), getChilds()[option]);
			}
		}

		/**
		 * Method used to deselect a skill.
		 * @param player the player.
		 * @param option the option.
		 */
		public final void skillDeselect(final Player player, final int option) {
			sendString(player, "<col=784F1C>" + getOptionString(option), getChilds()[option]);
		}

		/**
		 * Method used to handle the item deselect.
		 * @param player the player.
		 * @param option the option.
		 */
		public final void itemDeselect(final Player player, final int option) {
			if (charm) {
				sendString(player, "<col=784F1C>" + getOptionString(option), getChilds()[option]);
			}
		}

		/**
		 * Method used to get the option string.
		 * @param option the option index.
		 * @return the string.
		 */
		public String getOptionString(int option) {
			if (charm) {
				return (option == 1 ? "(2 Pts)" : option == 2 ? "(28 Pts)" : "(56 Pts)");
			}
			return (option == 1 ? "(1 Pt)" : option == 2 ? "(10 Pts)" : "(100 Pts)");
		}

		/**
		 * Method used to get the option choosen.
		 * @param button the button.
		 * @return the optopms.
		 */
		public int getOption(int button) {
			int index = 0;
			for (int i : getChilds()) {
				if (i == button) {
					return index;
				}
				index++;
			}
			return -1;
		}

		/**
		 * Gets the amt of required points.
		 * @param options the options.
		 * @return the points.
		 */
		public int getPoints(final int option) {
			if (charm) {
				return CHARM_POINTS[option - 1];
			}
			return isSkillReward() ? SKILL_POINTS[option - 1] : getPoints();
		}

		/**
		 * Method used to check if the player has the skills to buy void.
		 * @param player the player.
		 * @return <code>True</code> if so.
		 */
		public boolean hasVoidSkills(final Player player) {
			for (int skill : VOID_SKILLS) {
				if (player.getSkills().getLevel(skill) < (skill != Skills.PRAYER ? 42 : 22)) {
					player.getPacketDispatch().sendMessage("You need level 42 in hitpoints, attack, defence, strength, ranged, magic, and");
					player.getPacketDispatch().sendMessage("22 prayer to purchase the " + name.toLowerCase().replace("_", " ").replace("void knight", "").trim() + ".");
					return false;
				}
			}
			return true;
		}

		/**
		 * Gets the name of this reward.
		 * @return the name.
		 */
		public String getName() {
			return isSkillReward() ? Skills.SKILL_NAME[skill] + " xp" : name;
		}

		/**
		 * Method used to check if it's a charm.
		 * @return <code>True</code> if so.
		 */
		public boolean isCharm() {
			return charm;
		}

		/**
		 * Method used to check a skill requirement.
		 * @param player the player.
		 * @return <code>True</code> if so.
		 */
		public boolean checkSkillRequirement(final Player player, final int option) {
			if (player.getSkills().getLevel(skill) < 25) {
				player.getPacketDispatch().sendMessage("The Void Knights will not offer training in skills which you have a level of");
				player.getPacketDispatch().sendMessage("less than 25.");
				return false;
			}
			return true;
		}

		/**
		 * Method used to check the item requirement reward.
		 * @param player the player.
		 * @param option the option.
		 * @return <code>True</code> if so.
		 */
		public boolean checkItemRequirement(final Player player, final int option) {
			/*
			 * empty.
			 */
			return true;
		}

		/**
		 * Represents the maximum build of an item array pack.
		 */
		private static final int MAX_BUILD = 18;

		/**
		 * Represents the minimum build of an item array pack.
		 */
		private static final int MIN_BUILD = 13;

		/**
		 * Method used to generate an item back.
		 * @return the item pack.
		 */
		public Item[] constructPack() {
			final int build = this == SEED_PACK || this == HERB_PACK ? RandomFunction.random(MIN_BUILD, MAX_BUILD) : RandomFunction.random(38, 43);
			int left = build;
			List<Item> pack = new ArrayList<>();
			int amt = 0;
			for (Item i : getReward()) {
				amt = this == SEED_PACK || this == HERB_PACK ? RandomFunction.random(1, 5) : RandomFunction.random(16, 25);
				if (amt > left) {
					amt = left;
				}
				if (amt < 1) {
					continue;
				}
				pack.add(new Item(this != SEED_PACK ? ItemDefinition.forId(i.getId()).getNoteId() : i.getId(), amt));
				left -= amt;
			}
			return pack.toArray(new Item[] {});
		}

		/**
		 * Method used to get the reward type based off the button.
		 * @param button the button.
		 * @return the reward type.
		 */
		public static Reward forButton(final int button) {
			for (Reward reward : values()) {
				for (int i : reward.getChilds()) {
					if (i == button) {
						return reward;
					}
				}
			}
			return null;
		}

		/**
		 * Checks if this reward is a skill reward.
		 * @return <code>True</code> if so.
		 */
		public boolean isSkillReward() {
			return getChilds().length > 2 && !charm;
		}

		/**
		 * Gets the skill.
		 * @return the skill.
		 */
		public int getSkill() {
			return skill;
		}

		/**
		 * Gets the header.
		 * @return the header.
		 */
		public int getHeader() {
			return childs[0];
		}

		/**
		 * Gets the childs.
		 * @return The childs.
		 */
		public int[] getChilds() {
			return childs;
		}

		/**
		 * Gets the points.
		 * @return The points.
		 */
		public int getPoints() {
			return points;
		}

		/**
		 * Gets the reward.
		 * @return The reward.
		 */
		public Item[] getReward() {
			return reward;
		}
	}

}
