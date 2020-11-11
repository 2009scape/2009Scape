package plugin.skill.smithing;

import core.cache.def.impl.ItemDefinition;
import core.game.world.map.Location;
import plugin.quest.tutorials.tutorialisland.TutorialSession;
import plugin.quest.tutorials.tutorialisland.TutorialStage;
import plugin.skill.SkillPulse;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.game.world.update.flag.context.Animation;
import core.tools.StringUtils;

/**
 * Represents the pulse used to smith a bar.
 *
 * @author 'Vexia
 */
public class SmithingPulse extends SkillPulse<Item> {

    /**
     * Represents the animation to use.
     */
    private static final Animation ANIMATION = new Animation(898);

    /**
     * Represents the bar being made.
     */
    private final Bars bar;

    /**
     * Represents the amount to make.
     */
    private int amount;

    /**
     * Constructs a new {@code SmithingPulse} {@code Object}.
     *
     * @param player the player.
     * @param item   the item.
     */
    public SmithingPulse(Player player, Item item, Bars bar, int amount) {
        super(player, item);
        this.bar = bar;
        this.amount = amount;
    }

    @Override
    public boolean checkRequirements() {
        if (!player.getInventory().contains(bar.getBarType().getBarType(), bar.getSmithingType().getRequired() * amount)) {
            amount = player.getInventory().getAmount(new Item(bar.getBarType().getBarType()));
        }
        player.getInterfaceManager().close();
        if (player.getSkills().getLevel(Skills.SMITHING) < bar.getLevel()) {
            player.getDialogueInterpreter().sendDialogue("You need a Smithing level of " + bar.getLevel() + " to make a " + ItemDefinition.forId(bar.getProduct()).getName() + ".");
            return false;
        }
        if (!player.getInventory().contains(bar.getBarType().getBarType(), bar.getSmithingType().getRequired())) {
            player.getDialogueInterpreter().sendDialogue("You don't have enough " + ItemDefinition.forId(bar.getBarType().getBarType()).getName().toLowerCase() + "s to make a " + bar.getSmithingType().name().replace("TYPE_", "").replace("_", " ").toLowerCase() + ".");
            return false;
        }
        if (!player.getInventory().contains(2347, 1)) {
            player.getDialogueInterpreter().sendDialogue("You need a hammer to work the metal with.");
            return false;
        }
        if (!player.getQuestRepository().isComplete("The Tourist Trap") && bar.getSmithingType() == SmithingType.TYPE_DART_TIP) {
            player.getDialogueInterpreter().sendDialogue("You need to complete Tourist Trap to smith dart tips.");
            return false;
        }
        if (TutorialSession.getExtension(player).getStage() < TutorialSession.MAX_STAGE && node.getId() != Bars.BRONZE_DAGGER.getProduct()) {
            return false;
        }
        return true;
    }

    @Override
    public void animate() {
        player.animate(ANIMATION);
    }

    @Override
    public boolean reward() {
        if (getDelay() == 1) {
            setDelay(4);
            return false;
        }
        player.getInventory().remove(new Item(bar.getBarType().getBarType(), bar.getSmithingType().getRequired()));
        final Item item = new Item(node.getId(), bar.getSmithingType().getProductAmount());
        player.getInventory().add(item);
        player.getSkills().addExperience(Skills.SMITHING, bar.getBarType().getExperience() * bar.getSmithingType().getRequired(), true);
        String message = StringUtils.isPlusN(ItemDefinition.forId(bar.getProduct()).getName().toLowerCase()) ? "an" : "a";
        player.getPacketDispatch().sendMessage("You hammer the " + bar.getBarType().getBarName().toLowerCase().replace("smithing", "") + "and make " + message + " " + ItemDefinition.forId(bar.getProduct()).getName().toLowerCase() + ".");
        if (TutorialSession.getExtension(player).getStage() == 42) {
            TutorialStage.load(player, 43, false);
        }

        if (bar == Bars.BLURITE_CROSSBOW_LIMBS
                && player.getLocation().withinDistance(new Location(3000, 3145, 0), 10)) { // near Thurgo's anvil
            player.getAchievementDiaryManager().finishTask(player, DiaryType.FALADOR, 1, 9);
        }

        // Smith a steel longsword on the anvil in the jailhouse<br><br>sewers
        if (bar == Bars.STEEL_LONGSWORD && player.getLocation().withinDistance(Location.create(3112, 9688, 0))) {
            player.getAchievementDiaryManager().finishTask(player, DiaryType.LUMBRIDGE, 2, 0);
        }

        // Smith an adamantite medium helm on the south-east anvil in<br><br>Varrock, next to Aubury's Rune Shop
        if (bar == Bars.ADAMANT_MEDIUM_HELM && player.getLocation().withinDistance(Location.create(3247, 3404, 0))) {
            player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 2, 3);
        }

        amount--;
        return amount < 1;
    }

    @Override
    public void message(int type) {

    }

}
