package plugin.skill.gather.woodcutting;

import core.cache.def.impl.ItemDefinition;
import core.game.container.impl.EquipmentContainer;
import core.tools.ItemNames;
import plugin.dialogue.FacialExpression;
import core.game.content.global.BirdNest;
import core.game.content.global.SkillcapePerks;
import core.game.content.global.SkillingPets;
import plugin.quest.tutorials.tutorialisland.TutorialSession;
import plugin.quest.tutorials.tutorialisland.TutorialStage;
import plugin.skill.Skills;
import plugin.skill.farming.patch.Trees;
import plugin.skill.gather.SkillingTool;
import plugin.skill.farming.wrapper.PatchWrapper;
import core.game.node.entity.impl.Animator;
import core.game.node.entity.impl.Projectile;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.system.task.Pulse;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.tools.RandomFunction;

import java.util.concurrent.TimeUnit;

/**
 * Woodcutting skill pulse
 *
 * @author ceik
 */
public class WoodcuttingSkillPulse extends Pulse {
    private WoodcuttingNode resource;
    private int ticks;
    private Player player;
    private GameObject node;
    protected boolean resetAnimation = true;


    public WoodcuttingSkillPulse(Player player, GameObject node) {
        super(1, player, node);
        this.player = player;
        this.node = node;
        super.stop();
    }

    public void message(int type) {
        if (type == 0) {
            player.getPacketDispatch().sendMessage("You swing your axe at the tree...");
            if (TutorialSession.getExtension(player).getStage() == 6) {
                player.lock(7);
                TutorialStage.load(player, 7, false);
            }
        }
    }

    @Override
    public boolean pulse() {
        if (!checkRequirements()) {
            return true;
        }
        animate();
        return reward();
    }

    @Override
    public void stop() {
        if (resetAnimation) {
            player.animate(new Animation(-1, Animator.Priority.HIGH));
        }
        super.stop();
        message(1);
    }

    @Override
    public void start() {
        resource = WoodcuttingNode.forId(node.getId());
        if (resource == null) {
            return;
        }
        if (checkRequirements()) {
            super.start();
            message(0);
        }
    }

    public boolean checkRequirements() {
        if (player.getSkills().getLevel(Skills.WOODCUTTING) < resource.getLevel()) {
            player.getPacketDispatch().sendMessage("You need a woodcutting level of " + resource.getLevel() + " to chop this tree.");
            return false;
        }
        if (SkillingTool.getHatchet(player) == null) {
            player.getPacketDispatch().sendMessage("You do not have a hatchet to use.");
            return false;
        }
        if (player.getInventory().freeSlots() < 1) {
            player.getDialogueInterpreter().sendDialogue("Your inventory is too full to hold any more " + ItemDefinition.forId(resource.getReward()).getName().toLowerCase() + ".");
            return false;
        }
        return true;
    }

    public void animate() {
        player.animate(SkillingTool.getHatchet(player).getAnimation());
    }

    public boolean reward() {
        if (++ticks % 4 != 0) {
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


        // If player is in donator zone
        if (player.getLocation().getRegionId() == 12102) {
            player.getAntiMacroHandler().fireEvent("tree spirit");
            return true;
        }

        // 20% chance to auto burn logs when using "inferno adze" item
        if (SkillingTool.getHatchet(player).getId() == 13661 && RandomFunction.random(100) < 20) {
            player.sendMessage("Your chop some logs. The heat of the inferno adze incinerates them.");
            Projectile.create(player, null, 1776, 35, 30, 20, 25).transform(player, new Location(player.getLocation().getX() + 2, player.getLocation().getY()), true, 25, 25).send();
            player.getSkills().addExperience(Skills.WOODCUTTING, resource.getExperience());
            player.getSkills().addExperience(Skills.FIREMAKING, resource.getExperience());
            player.getStatisticsManager().getLOGS_OBTAINED().incrementAmount();
            return false;
        }

        //actual reward calculations
        int reward = resource.getReward();
        int rewardAmount = 0;
        if (reward > 0) {
            reward = calculateReward(reward); // calculate rewards
            rewardAmount = calculateRewardAmount(reward); // calculate amount
            SkillingPets.checkPetDrop(player, SkillingPets.BEAVER); // roll for pet

            //add experience
            double experience = calculateExperience(resource.reward, rewardAmount);

            player.getSkills().addExperience(Skills.WOODCUTTING, experience, true);

            //send the message for the resource reward
            if (resource == WoodcuttingNode.DRAMEN_TREE) {
                player.getPacketDispatch().sendMessage("You cut a branch from the Dramen tree.");
            } else {
                player.getPacketDispatch().sendMessage("You get some " + ItemDefinition.forId(reward).getName().toLowerCase() + ".");
                player.getStatisticsManager().getLOGS_OBTAINED().incrementAmount();
            }
            //give the reward
            player.getInventory().add(new Item(reward, rewardAmount));

            //calculate bonus bird nest for mining
            int chance = 282;
            if (SkillcapePerks.hasSkillcapePerk(player, SkillcapePerks.WOODCUTTING)) {
                chance /= 1.88;
            }
            if (RandomFunction.random(chance) == chance / 2) {
                BirdNest.drop(player);
            }

            applyAchievementTask(reward); // apply achievements
        }
        // Tutorial stuff, maybe?
        if (tutorialStage == 7) {
            TutorialStage.load(player, 8, false);
        }

        //transform to depleted version
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

    private int calculateRewardAmount(int reward) {
        int amount = 1;

        // 3239: Hollow tree (bark) 10% chance of obtaining
        if (reward == 3239 && RandomFunction.random(100) >= 10) {
            amount = 0;
        }

        // Seers village medium reward - extra normal log while in seer's village
        if (reward == 1511
                && player.getAchievementDiaryManager().getDiary(DiaryType.SEERS_VILLAGE).isComplete(1)
                && player.getViewport().getRegion().getId() == 10806) {
            amount = 2;
        }

        return amount;
    }

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

        // Seers village medium reward - extra 10% xp from maples while wearing headband
        if (reward == 1517
                && player.getAchievementDiaryManager().getDiary(DiaryType.SEERS_VILLAGE).isComplete(1)
                && player.getEquipment().get(EquipmentContainer.SLOT_HAT) != null
                && player.getEquipment().get(EquipmentContainer.SLOT_HAT).getId() == 14631) {
            experience *= 1.10;
        }

        return experience * amount;
    }

    private int calculateReward(int reward) {
        return reward;
    }

    /**
     * Checks if the has completed any achievements from their diary
     */
    private void applyAchievementTask(int reward) {
        // Cut a log from a teak tree
        if (reward == ItemNames.TEAK_LOGS_6333) {
            player.getAchievementDiaryManager().finishTask(player, DiaryType.KARAMJA, 1, 7);
        }
        // Cut a log from a mahogany tree
        if (reward == ItemNames.MAHOGANY_LOGS_6332) {
            player.getAchievementDiaryManager().finishTask(player, DiaryType.KARAMJA, 1, 8);
        }

        // Chop down a dying tree in the Lumber Yard
        if (node.getId() == 24168 && player.getViewport().getRegion().getId() == 13110) {
            player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 0, 6);
        }
        if (resource == WoodcuttingNode.YEW && player.getViewport().getRegion().getId() == 10806) {
            if (!player.getAchievementDiaryManager().hasCompletedTask(DiaryType.SEERS_VILLAGE, 2, 1)) {
                player.setAttribute("/save:diary:seers:cut-yew", player.getAttribute("diary:seers:cut-yew", 0) + 1);
            }
            System.out.println(player.getAttribute("diary:seers:cut-yew", 0));
            if (player.getAttribute("diary:seers:cut-yew", 0) >= 5) {
                player.getAchievementDiaryManager().finishTask(player, DiaryType.SEERS_VILLAGE, 2, 1);
            }
        }

        if (resource.isFarming()) {
            PatchWrapper tree = player.getFarmingManager().getPatchWrapper(node.getWrapper().getId());
            if (node.getId() == 8389
                    && node.getLocation().equals(3003, 3372, 0)
                    && Trees.forNode(tree.getNode()) != null
                    && (Trees.forNode(tree.getNode()) == Trees.YEW || Trees.forNode(tree.getNode()) == Trees.MAGIC)) {
                player.getAchievementDiaryManager().finishTask(player, DiaryType.FALADOR, 2, 3);
            }
        }

        // Cut down a dead tree in Lumbridge Swamp
        if (resource.name().toLowerCase().startsWith("dead") && player.getViewport().getRegion().getId() == 12593) {
            player.getAchievementDiaryManager().finishTask(player, DiaryType.LUMBRIDGE, 1, 8);
        }

        // Cut a willow tree, east of Lumbridge Castle
        if (resource.name().toLowerCase().startsWith("willow") && player.getViewport().getRegion().getId() == 12850) {
            player.getAchievementDiaryManager().finishTask(player, DiaryType.LUMBRIDGE, 2, 6);
        }
    }

    /**
     * Checks if the player gets rewarded.
     *
     * @return {@code True} if so.
     */
    private boolean checkReward() {
        int skill = Skills.WOODCUTTING;
        int level = 1 + player.getSkills().getLevel(skill) + player.getFamiliarManager().getBoost(skill);
        double hostRatio = Math.random() * (100.0 * resource.getRate());
        double clientRatio = Math.random() * ((level - resource.getLevel()) * (1.0 + SkillingTool.getHatchet(player).getRatio()));
        return hostRatio < clientRatio;
    }
}
