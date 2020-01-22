package org.crandor.game.content.skill.free.gather;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.container.impl.EquipmentContainer;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.global.BirdNest;
import org.crandor.game.content.global.SkillcapePerks;
import org.crandor.game.content.global.SkillingPets;
import org.crandor.game.content.global.tutorial.TutorialSession;
import org.crandor.game.content.global.tutorial.TutorialStage;
import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.farming.wrapper.PatchWrapper;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.portal.Perks;
import org.crandor.game.node.entity.player.link.diary.DiaryType;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.world.map.Location;
import org.crandor.tools.RandomFunction;
import org.crandor.tools.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Handles a gathering skill, such as woodcutting, mining, ...
 * @author Emperor
 */
public final class GatheringSkillPulse extends SkillPulse<GameObject> {

	/**
	 * The gem rewards.
	 */
	private static final Item[] GEM_REWARDS = { new Item(1623), new Item(1621), new Item(1619), new Item(1617) };

	/**
	 * Is the player is mining.
	 */
	private boolean isMining;

	/**
	 * Is the player is mining essence.
	 */
	private boolean isMiningEssence;

	/**
	 * Is the player is mining gems.
	 */
	private boolean isMiningGems;

	/**
	 * Is the player is woodcutting.
	 */
	private boolean isWoodcutting;

	/**
	 * The amount of ticks it takes to get a log.
	 */
	private int ticks;

	/**
	 * Constructs a new {@code GatheringSkillPulse} {@code Object}.
	 * @param player The player.
	 * @param node The gathering resource.
	 */
	public GatheringSkillPulse(Player player, GameObject node) {
		super(player, node);
	}

	@Override
	public void start() {
		resource = SkillingResource.forId(
		        node.getId());
		if (SkillingResource.isEmpty(node.getId())) {
			player.getPacketDispatch().sendMessage("This rock contains no ore.");
			return;
		}
		if (resource == null) {
			return;
		}
		if (TutorialSession.getExtension(player).getStage() == 35) {
			TutorialStage.load(player, 36, false);
		}
		isMining = resource.getSkillId() == Skills.MINING;
		isMiningEssence = resource == SkillingResource.RUNE_ESSENCE;
		isMiningGems = resource.getReward() == SkillingResource.GEM_ROCK_0.getReward();
		isWoodcutting = resource.getSkillId() == Skills.WOODCUTTING;
		super.start();
	}

	@Override
	public boolean checkRequirements() {
		if (player.getSkills().getLevel(resource.getSkillId()) < resource.getLevel()) {
			player.getPacketDispatch().sendMessage("You need a " + Skills.SKILL_NAME[resource.getSkillId()] + " level of " + resource.getLevel() + " to " + (isMining ? "mine this rock." : "cut this tree."));
			return false;
		}
		if (setTool() == null) {
			player.getPacketDispatch().sendMessage("You do not have a" + (isMining ? " pickaxe" : "n axe") + " to use.");
			return false;
		}
		if (player.getInventory().freeSlots() < 1) {
			player.getDialogueInterpreter().sendDialogue("Your inventory is too full to hold any more " + ItemDefinition.forId(resource.getReward()).getName().toLowerCase() + ".");
			return false;
		}
		return true;
	}

	@Override
	public void animate() {
		if (resource.getAnimation() != null) {
			player.animate(resource.getAnimation());
		} else if (tool.getAnimation() != null) {
			player.animate(tool.getAnimation());
		}
	}

	@Override
	public boolean reward() {
		if (++ticks % (isMiningEssence ? 3 : 4) != 0) {
			return false;
		}
		if (node.getId() == 10041) {
			player.getDialogueInterpreter().sendDialogues(2574, FacialExpression.FURIOUS, RandomFunction.random(2) == 1 ? "You'll blow my cover! I'm meant to be hidden!" : "Will you stop that?");
			return true;
		}
		if (!checkReward()) {
			return false;
		}
		int tutorialStage = TutorialSession.getExtension(player).getStage();
		if (tutorialStage == 36 && node.getId() == 3042) {
			TutorialStage.load(player, 38, false);
		} else if (tutorialStage == 36 && node.getId() == 3043) {
			TutorialStage.load(player, 37, false);
		}
		if (tutorialStage == 38 && node.getId() == 3043) {
			TutorialStage.load(player, 39, false);
		} else if (tutorialStage == 37 && node.getId() == 3042) {
			TutorialStage.load(player, 39, false);
		}
		// If player is in donator zone
		if (isWoodcutting && player.getLocation().getRegionId() == 12102) {
			player.getAntiMacroHandler().fireEvent("tree spirit");
			return true;
		}
		// 20% chance to auto burn logs when using "inferno adze" item
		if (isWoodcutting && tool.getId() == 13661 && RandomFunction.random(100) < 20){
			player.sendMessage("Your chop some logs. The heat of the inferno adze incinerates them.");
			Projectile.create(player, null, 1776, 35, 30, 20, 25).transform(player, new Location(player.getLocation().getX() + 2, player.getLocation().getY()), true, 25, 25).send();
			player.getSkills().addExperience(Skills.WOODCUTTING, resource.getExperience());
			player.getSkills().addExperience(Skills.FIREMAKING, resource.getExperience());
			return false;
		}
		int reward = resource.getReward();
		if (reward > 0) {
			reward = calculateReward(reward);
			applyAchievementTask(reward);
			// Give the player the items
			int rewardAmount = calculateRewardAmount(reward);
			Item item = new Item(reward, rewardAmount);
			player.getInventory().add(item);
			Perks.addDouble(player, item);
			// Apply the experience points
			double experience = calculateExperience(reward, rewardAmount);
			player.getSkills().addExperience(resource.getSkillId(), experience, true);
			// Send a message to the player
			if (isMiningGems) {
				String gemName = ItemDefinition.forId(reward).getName().toLowerCase();
				player.sendMessage("You get " + (StringUtils.isPlusN(gemName) ? "an" : "a") + " " + gemName + ".");
			} else if (resource == SkillingResource.DRAMEN_TREE) {
				player.getPacketDispatch().sendMessage("You cut a branch from the Dramen tree.");
			} else {
				player.getPacketDispatch().sendMessage("You get some " + ItemDefinition.forId(reward).getName().toLowerCase() + ".");
			}
			// Calculate if the player should receive a bonus gem
			if (!isMiningEssence && isMining) {
				int chance = 282;
				boolean altered = false;
				if (player.getEquipment().getNew(EquipmentContainer.SLOT_RING).getId() == 2572) {
					chance /= 1.5;
					altered = true;
				}
				Item necklace = player.getEquipment().get(EquipmentContainer.SLOT_AMULET);
				if (necklace != null && (necklace.getId() > 1705 && necklace.getId() < 1713)) {
					chance /= 1.5;
					altered = true;
				}
				if (!altered && player.getDetails().getShop().hasPerk(Perks.STONER)) {
					chance /= 1.5;
				}
				if (RandomFunction.random(chance) == 0) {
					Item gem = RandomFunction.getRandomElement(GEM_REWARDS);
					player.getPacketDispatch().sendMessage("You find a " + gem.getName() + "!");
					if (!player.getInventory().add(gem, player)) {
						player.getPacketDispatch().sendMessage("You do not have enough space in your inventory, so you drop the gem on the floor.");
					}
				}
			}
			// Calculate if the player should receive a bonus birds nest
			if (isWoodcutting) {
				int chance = 282;
				if (player.getDetails().getShop().hasPerk(Perks.BIRD_MAN)) {
					chance /= 1.5;
				}
				if (SkillcapePerks.hasSkillcapePerk(player, SkillcapePerks.WOODCUTTING)) {
					chance /= 1.88;
				}
				if (RandomFunction.random(chance) == 0) {
					BirdNest.drop(player);
				}
			}
		}
		// Tutorial stuff, maybe?
		if (tutorialStage == 7) {
			TutorialStage.load(player, 8, false);
		}
		// not sure what this is exactly
		if (resource.getRespawnRate() != 0) {
			int charge = 1000 / resource.getRewardAmount();
			node.setCharge(node.getCharge() - RandomFunction.random(charge, charge << 2));
			if (node.getCharge() < 1) {
				node.setCharge(1000);
				if (resource.isFarming()) {
					PatchWrapper tree = player.getFarmingManager().getPatchWrapper(node.getWrapper().getId());
					tree.addConfigValue(tree.getNode().getStumpBase());
					tree.getCycle().setGrowthTime(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(resource.getRespawnDuration() + 10));
					return true;
				}
				if (resource.getEmptyId() > -1) {
					ObjectBuilder.replace(node, node.transform(resource.getEmptyId()), resource.getRespawnDuration());
				} else {
					ObjectBuilder.replace(node, node.transform(0), resource.getRespawnDuration());
				}
				node.setActive(false);
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the has completed any achievements from their diary
	 */
	private void applyAchievementTask(int reward) {
		if (reward == 6333 && !player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).isComplete(1, 4)) {
			player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).updateTask(player, 1, 4, true);
		} else if (reward == 6332 && !player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).isComplete(1, 5)) {
			player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).updateTask(player, 1, 5, true);
		}
		if (reward == 440 && player.getLocation().withinDistance(new Location(3285, 3363, 0)) && !player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).isComplete(0, 2)) {
			player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).updateTask(player, 0, 2, true);
		}
		if (node.getId() == 24168 && !player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).isComplete(0, 6)) {
			player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).updateTask(player, 0, 6, true);
		}
		if (reward == 440 && player.getViewport().getRegion().getId() == 13107 && !player.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE).isComplete(0, 8)) {
			player.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE).updateTask(player, 0, 8, true);
		}
		if (reward == 1519 && player.getViewport().getRegion().getId() == 12338 && !player.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE).isComplete(1, 5)) {
			player.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE).updateTask(player, 1, 5, true);
		}
		if (reward == 444 && !player.getAchievementDiaryManager().hasCompletedTask(DiaryType.KARAMJA, 0, 2)) {
			if (player.getLocation().getRegionId() == 10801 || player.getLocation().getRegionId() == 10802) {
				player.getAchievementDiaryManager().updateTask(player, DiaryType.KARAMJA, 0, 2, true);
			}
		}
		if (reward == 1629) {
			if (!player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).isComplete(1, 11)) {
				player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).updateTask(player, 1, 11, true);
			}
		}
	}

	/**
	 * Checks if the player gets rewarded.
	 * @return {@code True} if so.
	 */
	private boolean checkReward() {
		int skill = isMining ? Skills.MINING : Skills.WOODCUTTING;
		int level = 1 + player.getSkills().getLevel(skill) + player.getFamiliarManager().getBoost(skill);
		double hostRatio = Math.random() * (100.0 * resource.getRate());
		double clientRatio = Math.random() * ((level - resource.getLevel()) * (1.0 + tool.getRatio()));
		return hostRatio < clientRatio;
	}
	
	private int calculateReward(int reward) {
		// If the player is mining sandstone or granite, then i'm not sure what this does?
		if (resource == SkillingResource.SANDSTONE || resource == SkillingResource.GRANITE) {
			int value = RandomFunction.randomize(resource == SkillingResource.GRANITE ? 3 : 4);
			reward += value << 1;
			player.getSkills().addExperience(resource.getSkillId(), value * 10, true);
		}
		
		// If the player is mining clay
		else if (reward == SkillingResource.CLAY_0.getReward()) {
			// Check if they have a bracelet of clay equiped
			if (player.getEquipment().contains(11074, 1)) {
				player.getSavedData().getGlobalData().incrementBraceletOfClay();
				if (player.getSavedData().getGlobalData().getBraceletClayUses() >= 28) {
					player.getSavedData().getGlobalData().setBraceletClayUses(0);
					player.getEquipment().remove(new Item(11074));
					player.sendMessage("Your bracelet of clay has disinegrated.");
				}
				// Give soft clay
				reward = 1761;
			}
		}
		
		// Convert rune essence to pure essence if the player is above level 30 mining
		else if (isMiningEssence && player.getSkills().getLevel(Skills.MINING) >= 30) {
			reward = 7936;
		}
		
		// Calculate a random gem for the player
		else if (isMiningGems) {
			int random = RandomFunction.random(100);
			List<Integer> gems = new ArrayList<>();
			if (random < 2) {
				gems.add(1617);
			} else if (random < 25) {
				gems.add(1619);
				gems.add(1623);
				gems.add(1621);
			} else if (random < 40) {
				gems.add(1629);
			} else {
				gems.add(1627);
				gems.add(1625);
			}
			reward = gems.get(RandomFunction.random(gems.size()));
		}
		
		return reward;
	}

	/**
	 * Calculate the total amount of items the player should receive
	 * @return amount of items
	 */
	private int calculateRewardAmount(int reward) {
		int amount = 1;
		
		if (isMining && !isMiningEssence) {
			// Not sure what this bonus is for
			if (isMining && player.getSavedData().getGlobalData().getStarSpriteDelay() > System.currentTimeMillis() && TimeUnit.MILLISECONDS.toMinutes(player.getSavedData().getGlobalData().getStarSpriteDelay() - System.currentTimeMillis()) >= 1425) {
				amount += 1;
			}
			// Not sure what this bonus is for
			else if (isMining && !isMiningEssence && player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).getLevel() != -1 && player.getAchievementDiaryManager().checkMiningReward(reward) && RandomFunction.random(100) <= 10) {
				amount += 1;
				player.sendMessage("Through the power of the varrock armour you receive an extra ore.");
			}
			// If the player has a skill cape, 10% chance of finding an extra item
			else if (isMining && !isMiningEssence && SkillcapePerks.hasSkillcapePerk(player, SkillcapePerks.MINING) && RandomFunction.getRandom(100) <= 10) {
				amount += 1;
				player.sendNotificationMessage("Your " + player.getEquipment().get(EquipmentContainer.SLOT_CAPE).getName() + " allows you to obtain two ores from this rock!");
			}
		}
		
		// 3239: Hollow tree (bark) 10% chance of obtaining
		if (reward == 3239 && RandomFunction.random(100) >= 10) {
			amount = 0;
		}
		
		SkillingPets.checkPetDrop(player, isMining ? SkillingPets.GOLEM : SkillingPets.BEAVER);
		
		return amount;
	}

	/**
	 * Calculate the total experience the player should receive
	 * @return amount of experience
	 */
	private double calculateExperience(int reward, int amount) {
		double experience = resource.getExperience();
		
		// Bark
		if (reward == 3239) {
			// If we receive the item, give the full experience points otherwise give the base amount
			if (amount >= 1) {
				experience = 275.2;
			} else {
				amount = 1;
			}
		}
		
		return experience * amount;
	}
	

	@Override
	public void message(int type) {
		switch (type) {
		case 0:
			player.getPacketDispatch().sendMessage("You swing your " + (isMining ? "pickaxe at the rock..." : "axe at the tree..."));
			if (TutorialSession.getExtension(player).getStage() == 6) {
				player.lock(7);
				TutorialStage.load(player, 7, false);
			}
			break;
		}
	}

	/**
	 * Sets the tool used.
	 */
	private SkillingTool setTool() {
		if (!isMining) {
			tool = SkillingTool.getHatchet(player);
		} else {
			tool = SkillingTool.getPickaxe(player);
		}
		return tool;
	}
}