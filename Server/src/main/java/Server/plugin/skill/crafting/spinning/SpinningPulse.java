package plugin.skill.crafting.spinning;

import core.cache.def.impl.ItemDefinition;
import core.game.container.impl.EquipmentContainer;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.world.map.Location;
import plugin.skill.SkillPulse;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.update.flag.context.Animation;

/**
 * Represents the pulse used to spin an item.
 *
 * @author 'Vexia
 */
public final class SpinningPulse extends SkillPulse<Item> {

    /**
     * Represents the animation to use.
     */
    private static final Animation ANIMATION = new Animation(896);

    /**
     * Represents the type of spinning item.
     */
    private final SpinningItem type;

    /**
     * Represents the amount to spin.
     */
    private int ammount;

    /**
     * Represents the ticks passed.
     */
    private int ticks;

    /**
     * Constructs a new {@code SpinningPulse.java} {@Code Object}
     *
     * @param player
     * @param node
     */
    public SpinningPulse(Player player, Item node, int ammount, SpinningItem def) {
        super(player, node);
        this.type = def;
        this.ammount = ammount;
    }

    @Override
    public boolean checkRequirements() {
        player.getInterfaceManager().close();
        if (player.getSkills().getLevel(Skills.CRAFTING) < type.getLevel()) {
            player.getPacketDispatch().sendMessage("You need a crafting level of " + type.getLevel() + " to make this.");
            return false;
        }
        if (!player.getInventory().contains(type.getNeed(), 1)) {
            player.getPacketDispatch().sendMessage("You need " + ItemDefinition.forId(type.getNeed()).getName() + " to do this.");
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
		int tickThreshhold = 4;
		if (player.getAchievementDiaryManager().getDiary(DiaryType.SEERS_VILLAGE).isComplete(2)
				&& player.getLocation().withinDistance(Location.create(2711,3471,1))
				&& player.getEquipment().get(EquipmentContainer.SLOT_HAT) != null
				&& player.getEquipment().get(EquipmentContainer.SLOT_HAT).getId() == 14631) {
			tickThreshhold = 2;
		}
        if (++ticks % tickThreshhold != 0) {
            return false;
        }
        if (player.getInventory().remove(new Item(type.getNeed(), 1))) {
            final Item item = new Item(type.getProduct(), 1);
            player.getInventory().add(item);
            player.getSkills().addExperience(Skills.CRAFTING, type.getExp(), true);

            // Seers achievement diary
            if (player.getViewport().getRegion().getId() == 10806
                    && !player.getAchievementDiaryManager().getDiary(DiaryType.SEERS_VILLAGE).isComplete(0, 4)) {
                if (player.getAttribute("diary:seers:bowstrings-spun", 0) >= 4) {
                    player.setAttribute("/save:diary:seers:bowstrings-spun", 5);
                    player.getAchievementDiaryManager().finishTask(player, DiaryType.SEERS_VILLAGE, 0, 4);
                } else {
                    player.setAttribute("/save:diary:seers:bowstrings-spun", player.getAttribute("diary:seers:bowstrings-spun", 0) + 1);
                }
            }
        }
        ammount--;
        return ammount < 1;
    }

    @Override
    public void message(int type) {
    }

}
