package core.game.node.entity.skill.thieving;

import core.game.world.map.zone.ZoneBorders;
import core.game.node.entity.skill.SkillPulse;
import core.game.node.entity.skill.Skills;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.DeathTask;
import core.game.node.entity.combat.ImpactHandler.HitsplatType;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.audio.Audio;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.entity.state.EntityState;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.game.world.GameWorld;
import core.game.world.update.flag.context.Animation;
import core.tools.RandomFunction;

import java.util.List;

/**
 * Represents the pulse used to pickpocket an npc.
 *
 * @author Vexia
 */
public final class PickpocketPulse extends SkillPulse<NPC> {

    /**
     * Represents the animation specific to pickpocketing.
     */
    private static final Animation ANIMATION = new Animation(881);

    /**
     * Represents the npc animation.
     */
    private static final Animation NPC_ANIM = new Animation(422);

    /**
     * Represents the animation specific to pickpocketing.
     */
    private static final Animation STUN_ANIMATION = new Animation(424);

    /**
     * Represents the sound to send when failed.
     */
    private static final Audio SOUND = new Audio(2727, 1, 0);

    /**
     * Represents the pickpocket type.
     */
    private final Pickpocket type;

    /**
     * Represents the tickets to be rewarded.
     */
    private int ticks;

    /**
     * Constructs a new {@code PickpocketPulse} {@code Object}.
     *
     * @param player the player.
     * @param node   the node.
     * @param type   the type.
     */
    public PickpocketPulse(Player player, NPC node, final Pickpocket type) {
        super(player, node);
        this.type = type;
        this.resetAnimation = false;
    }

    @Override
    public boolean checkRequirements() {
        if (!interactable() && !player.getLocks().isInteractionLocked()) {
            return false;
        }
        if (player.getSkills().getLevel(Skills.THIEVING) < type.getLevel()) {
            player.getPacketDispatch().sendMessage("You need to be a level " + type.getLevel() + " thief in order to pick this pocket.");
            return false;
        }
        if (!hasInventorySpace()) {
            player.getPacketDispatch().sendMessage("You do not have enough space in your inventory to pick this pocket.");
            return false;
        }
        player.getLocks().lockInteractions(2);
        player.faceTemporary(node, 2);
        node.getWalkingQueue().reset();
        node.getLocks().lockMovement(1);
        return true;
    }

    @Override
    public void animate() {
    }

    @Override
    public boolean reward() {
        if (ticks == 0) {
            player.animate(ANIMATION);
        }
        if (++ticks % 2 != 0) {
            return false;
        }
        final boolean success = success();
        if (success) {
            player.getPacketDispatch().sendMessage(type.getRewardMessage().replace("@name", node.getName().toLowerCase()));
            player.getSkills().addExperience(Skills.THIEVING, type.getExperience(), true);
            List<Item> loot = type.getRandomLoot(player);
            loot.stream().forEach(item -> {
                if (!player.getInventory().add(item)) {
                    GroundItemManager.create(item, player.getLocation(), player);
                }
            });
            if (type == Pickpocket.GUARD && node.getId() == 9) {
                player.getAchievementDiaryManager().finishTask(player, DiaryType.FALADOR, 1, 6);
            }
            if (type == Pickpocket.GUARD && node.getId() == 5920
                    && new ZoneBorders(3202, 3459, 3224, 3470, 0).insideBorder(player)) {
                player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 1, 12);
            }
            player.getLocks().unlockInteraction();
        } else {
            node.animate(NPC_ANIM);
            node.faceTemporary(player, 1);
            node.sendChat(type.getForceMessage());
            player.animate(STUN_ANIMATION);
            player.getAudioManager().send(new Audio(1842));
            player.getStateManager().set(EntityState.STUNNED, 4);
            player.getAudioManager().send(SOUND);
            player.setAttribute("thief-delay", GameWorld.getTicks() + 4);
            player.getImpactHandler().manualHit(player, type.getStunDamage(), HitsplatType.NORMAL);
            player.getPacketDispatch().sendMessage(type.getFailMessage().replace("@name", node.getName().toLowerCase()));
        }
        return true;
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void message(int type) {
        switch (type) {
            case 0:
                player.getPacketDispatch().sendMessage(this.type.getStartMessage().replace("@name", node.getName().toLowerCase()));
                break;
        }
    }

    /**
     * Checks if the npc is interable.
     *
     * @return {@code True} if so.
     */
    private boolean interactable() {
        if (DeathTask.isDead(((Entity) node)) || ((NPC) node).isHidden(player) || !node.isActive() || player.getAttribute("thief-delay", 0) > GameWorld.getTicks()) {
            return false;
        } else if (player.inCombat()) {
            player.getPacketDispatch().sendMessage("You can't pickpocket during combat.");
            return false;
        } else if (!hasInventorySpace()) {
            player.getPacketDispatch().sendMessage("You don't have enough inventory space.");
            return false;
        }
        return true;
    }

    /**
     * Checks if the pickpocket is a success.
     *
     * @return {@code True} if so.
     */
    private boolean success() {
        double level = player.getSkills().getLevel(Skills.THIEVING);
        double req = type.getLevel();
        double successChance = Math.ceil((level * 50 - req * 15) / req / 3 * 4);
        int roll = RandomFunction.random(99);
        if (RandomFunction.random(12) < 2) {
            return false;
        }
        if (successChance >= roll) {
            return true;
        }
        return false;
    }

    /**
     * Checks if player has enough inventory space to pickpocket npc.
     * @return {@code True} if player has enough inventory space.
     */
    private boolean hasInventorySpace() {
        if (player.getInventory().isFull() && type.getLoot().length > 0) {
            if (!(type.getLoot().length == 1 && player.getInventory().hasSpaceFor(
                    new Item(type.getLoot()[0].getId(), type.getLoot()[0].getMaximumAmount())))) {
                return false;
            }
        }
        return true;
    }

}
