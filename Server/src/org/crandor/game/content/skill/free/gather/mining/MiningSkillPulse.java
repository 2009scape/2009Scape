package org.crandor.game.content.skill.free.gather.mining;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.container.impl.EquipmentContainer;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.global.SkillcapePerks;
import org.crandor.game.content.global.SkillingPets;
import org.crandor.game.content.global.tutorial.TutorialSession;
import org.crandor.game.content.global.tutorial.TutorialStage;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.free.gather.SkillingResource;
import org.crandor.game.content.skill.free.gather.SkillingTool;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.impl.Animator;
import org.crandor.game.node.entity.npc.drop.DropFrequency;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.diary.DiaryType;
import org.crandor.game.node.item.ChanceItem;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.item.WeightedChanceItem;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.tools.RandomFunction;
import org.crandor.tools.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Mining skill pulse
 * @author ceik
 */
public class MiningSkillPulse extends Pulse {
    private MiningNode resource;
    private boolean isMiningEssence = false;
    private boolean isMiningGems = false;
    private int ticks;
    private static final ChanceItem[] GEM_REWARDS = { new ChanceItem(1623,1, DropFrequency.COMMON), new ChanceItem(1621,1,DropFrequency.COMMON), new ChanceItem(1619,1,DropFrequency.UNCOMMON), new ChanceItem(1617,1,DropFrequency.RARE) };
    private Player player;
    private Node node;
    protected boolean resetAnimation = true;


    public MiningSkillPulse(Player player, Node node) {
        super(1, player, node);
        this.player = player;
        this.node = node;
        super.stop();
    }

    public void message(int type) {
        if(type == 0) {
            player.getPacketDispatch().sendMessage("You swing your pickaxe at the rock...");
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
        resource = MiningNode.forId(node.getId());
        if(MiningNode.isEmpty(node.getId())){
            player.getPacketDispatch().sendMessage("This rock contains no ore.");
        }
        if(resource == null){
            return;
        }
        if(resource.getId() == 2491){
            isMiningEssence = true;
        }
        if(resource.identifier == MiningNode.GEM_ROCK_0.identifier){
            isMiningGems = true;
        }
        if (TutorialSession.getExtension(player).getStage() == 35) {
            TutorialStage.load(player, 36, false);
        }
        if(checkRequirements()) {
            super.start();
            message(0);
        }
    }

    public boolean checkRequirements(){
        if(player.getSkills().getLevel(Skills.MINING) < resource.getLevel()){
            player.getPacketDispatch().sendMessage("You need a mining level of " + resource.getLevel() + " to mine this rock.");
            return false;
        }
        if(SkillingTool.getPickaxe(player) == null){
            player.getPacketDispatch().sendMessage("You do not have a pickaxe to use.");
            return false;
        }
        if(player.getInventory().freeSlots() < 1){
            player.getDialogueInterpreter().sendDialogue("Your inventory is too full to hold any more " + ItemDefinition.forId(resource.getReward()).getName().toLowerCase() + ".");
            return false;
        }
        return true;
    }

    public void animate() {
        player.animate(SkillingTool.getPickaxe(player).getAnimation());
    }

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

        //Handle tutorial stuff
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

        //actual reward calculations
        int reward = resource.getReward();
        int rewardAmount = 0;
        if (reward > 0){
            reward = calculateReward(reward); // calculate rewards
            rewardAmount = calculateRewardAmount(reward); // calculate amount
            applyAchievementTask(reward); // apply achievements
            SkillingPets.checkPetDrop(player,SkillingPets.GOLEM); // roll for pet

            //add experience
            double experience = resource.getExperience() * rewardAmount;
            player.getSkills().addExperience(Skills.MINING, experience, true);

            //send the message for the resource reward
            if (isMiningGems) {
                String gemName = ItemDefinition.forId(reward).getName().toLowerCase();
                player.sendMessage("You get " + (StringUtils.isPlusN(gemName) ? "an" : "a") + " " + gemName + ".");
            }else {
                player.getPacketDispatch().sendMessage("You get some " + ItemDefinition.forId(reward).getName().toLowerCase() + ".");
            }

            //give the reward
            player.getInventory().add(new Item(reward,rewardAmount));

            //calculate bonus gem for mining
            if(!isMiningEssence){
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
                if (RandomFunction.random(chance) == chance / 2) {
                    Item gem = RandomFunction.rollChanceTable(true, GEM_REWARDS).get(0);
                    player.getPacketDispatch().sendMessage("You find a " + gem.getName() + "!");
                    if (!player.getInventory().add(gem, player)) {
                        player.getPacketDispatch().sendMessage("You do not have enough space in your inventory, so you drop the gem on the floor.");
                    }
                }
            }
        }

        //transform to depleted version
        if (!isMiningEssence && resource.getRespawnRate() != 0) {
            ObjectBuilder.replace((GameObject) node, new GameObject(resource.getEmptyId(), node.getLocation(), ((GameObject) node).getRotation()), resource.getRespawnDuration());
            node.setActive(false);
            return true;
        }
        return false;
    }

    private int calculateRewardAmount(int reward){
        int amount = 1;

        //checks for varrock armor from varrock diary and rolls chance at extra ore
        if(!isMiningEssence && player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).getLevel() != -1 && player.getAchievementDiaryManager().checkMiningReward(reward) && RandomFunction.random(100) <= 10){
            amount += 1;
            player.sendMessage("Through the power of the varrock armour you receive an extra ore.");
        }

        // If the player has a skill cape, 10% chance of finding an extra item
        else if (!isMiningEssence && SkillcapePerks.hasSkillcapePerk(player, SkillcapePerks.MINING) && RandomFunction.getRandom(100) <= 10) {
            amount += 1;
            player.sendNotificationMessage("Your " + player.getEquipment().get(EquipmentContainer.SLOT_CAPE).getName() + " allows you to obtain two ores from this rock!");
        }

        //check for bonus ore from shooting star buff
        if((player.getAttribute("SS Mining Bonus", GameWorld.getTicks()) > GameWorld.getTicks())){
            if(RandomFunction.getRandom(5) == 3) {
                player.getPacketDispatch().sendMessage("...you manage to mine a second ore thanks to the Star Sprite.");
                amount += 1;
            }
        }

        return amount;
    }

    private int calculateReward(int reward) {
        // If the player is mining sandstone or granite, then get size of sandstone/granite and xp reward for that size
        if (resource == MiningNode.SANDSTONE || resource == MiningNode.GRANITE) {
            int value = RandomFunction.randomize(resource == MiningNode.GRANITE ? 3 : 4);
            reward += value << 1;
            player.getSkills().addExperience(Skills.MINING, value * 10, true);
        }

        //checks for bracelet of clay and softens it if so
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

        // Calculate a random gem for the player if mining gem rocks
        else if (isMiningGems) {
            reward = RandomFunction.rollWeightedChanceTable(MiningNode.gemRockGems).getId();
        }

        return reward;
    }

    /**
     * Checks if the has completed any achievements from their diary
     */
    private void applyAchievementTask(int reward) {
        if (reward == 440 && player.getLocation().withinDistance(new Location(3285, 3363, 0)) && !player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).isComplete(0, 2)) {
            player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).updateTask(player, 0, 2, true);
        }
        if (reward == 440 && player.getViewport().getRegion().getId() == 13107 && !player.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE).isComplete(0, 8)) {
            player.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE).updateTask(player, 0, 8, true);
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
        int skill = Skills.MINING;
        int level = 1 + player.getSkills().getLevel(skill) + player.getFamiliarManager().getBoost(skill);
        double hostRatio = Math.random() * (100.0 * resource.getRate());
        double clientRatio = Math.random() * ((level - resource.getLevel()) * (1.0 + SkillingTool.getPickaxe(player).getRatio()));
        return hostRatio < clientRatio;
    }
}
