package plugin.skill.crafting.pottery;

import core.game.world.map.Location;
import plugin.skill.SkillPulse;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.game.world.update.flag.context.Animation;

/**
 * Represents the pulse used to fire pottery.
 *
 * @author 'Vexia
 */
public final class FirePotteryPulse extends SkillPulse<Item> {

    /**
     * Represents the animation to use.
     */
    private static final Animation ANIMATION = new Animation(899);

    /**
     * Represents the pottery item.
     */
    private final PotteryItem pottery;

    /**
     * Represents the amount to make.
     */
    private int amount;

    /**
     * Represents the ticks passed.
     */
    private int ticks;

    /**
     * Constructs a new {@code FirePotteryPulse} {@code Object}.
     *
     * @param player  the player.
     * @param node    the node.
     * @param pottery the pottery.
     * @param amount  the amount.
     */
    public FirePotteryPulse(Player player, Item node, final PotteryItem pottery, final int amount) {
        super(player, node);
        this.pottery = pottery;
        this.amount = amount;
    }

    @Override
    public boolean checkRequirements() {
        if (player.getSkills().getLevel(Skills.CRAFTING) < pottery.getLevel()) {
            player.getPacketDispatch().sendMessage("You need a crafting level of " + pottery.getLevel() + " in order to do this.");
            return false;
        }
        if (!player.getInventory().containsItem(pottery.getUnfinished())) {
            player.getPacketDispatch().sendMessage("You need a " + pottery.name().toLowerCase() + "in order to do this.");
            return false;
        }
        return true;
    }

    @Override
    public void animate() {
        if (ticks % 5 == 0) {
            player.animate(ANIMATION);
        }
    }

    @Override
    public boolean reward() {
        if (++ticks % 5 != 0) {
            return false;
        }
        if (player.getInventory().remove(pottery.getUnfinished())) {
            final Item item = pottery.getProduct();
            player.getInventory().add(item);
            player.getSkills().addExperience(Skills.CRAFTING, pottery.getFireExp(), true);
            player.getPacketDispatch().sendMessage("You put the " + pottery.getUnfinished().getName().toLowerCase() + " in the oven.");
            player.getPacketDispatch().sendMessage("You remove a " + pottery.getProduct().getName().toLowerCase() + " from the oven.");

            // Spin a bowl on the pottery wheel and fire it in the oven in<br><br>Barbarian Village
            if (pottery == PotteryItem.BOWL
                    && player.getLocation().withinDistance(Location.create(3085,3408,0))
                    && player.getAttribute("diary:varrock:spun-bowl", false)) {
                player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 0, 9);
            }

            // Fire a pot in the kiln in the Barbarian Village potter's<br><br>house
            if (pottery == PotteryItem.POT && player.getLocation().withinDistance(Location.create(3085,3408,0))) {
                player.getAchievementDiaryManager().finishTask(player, DiaryType.LUMBRIDGE, 0, 7);
            }
        }
        amount--;
        return amount < 1;
    }

}
