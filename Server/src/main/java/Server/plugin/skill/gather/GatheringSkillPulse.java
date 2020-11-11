package plugin.skill.gather;

import core.cache.def.impl.ItemDefinition;
import core.game.container.impl.EquipmentContainer;
import core.game.content.ItemNames;
import plugin.dialogue.FacialExpression;
import core.game.content.global.BirdNest;
import core.game.content.global.SkillcapePerks;
import core.game.content.global.SkillingPets;
import plugin.quest.tutorials.tutorialisland.TutorialSession;
import plugin.quest.tutorials.tutorialisland.TutorialStage;
import plugin.skill.SkillPulse;
import plugin.skill.Skills;
import plugin.skill.farming.wrapper.PatchWrapper;
import core.game.node.entity.impl.Projectile;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.tools.RandomFunction;
import core.tools.StringUtils;

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
			player.getStatisticsManager().getLOGS_OBTAINED().incrementAmount();
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
				player.getStatisticsManager().getLOGS_OBTAINED().incrementAmount();
			}
			// Calculate if the player should receive a bonus gem or bonus ore or both
			if (!isMiningEssence && isMining) {
				//check for bonus ore from shooting star buff
				if(isMining && (player.getAttribute("SS Mining Bonus", GameWorld.getTicks()) > GameWorld.getTicks())){
					if(RandomFunction.getRandom(7) == 5) {
						player.getPacketDispatch().sendMessage("...you manage to mine a second ore thanks to the Star Sprite.");
						player.getInventory().add(item);
					}
				}
				int chance = 282;
				boolean altered = false;
				if (new Item(player.getEquipment().getId(12)).getName().toLowerCase().contains("ring of wealth")) {
					chance /= 1.5;
					altered = true;
				}
				Item necklace = player.getEquipment().get(EquipmentContainer.SLOT_AMULET);
				if (necklace != null && (necklace.getId() > 1705 && necklace.getId() < 1713)) {
					chance /= 1.5;
					altered = true;
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