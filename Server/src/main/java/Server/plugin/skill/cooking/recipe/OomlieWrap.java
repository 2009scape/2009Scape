package plugin.skill.cooking.recipe;

import core.game.content.ItemNames;
import plugin.skill.Skills;
import plugin.skill.cooking.recipe.Recipe;
import core.game.interaction.NodeUsageEvent;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;

/**
 * @author afaroutdude
 */
public class OomlieWrap extends Recipe {

    private static final Item OOMLIE_WRAP = new Item(ItemNames.WRAPPED_OOMLIE_2341);
    private static final Item RAW_OOMLIE = new Item(ItemNames.RAW_OOMLIE_2337);
    private static final Item PALM_LEAF = new Item(ItemNames.PALM_LEAF_2339);

    @Override
    public void mix(final Player player, final NodeUsageEvent event) {
        if (player.getSkills().getLevel(Skills.COOKING) < 50) {
            player.getDialogueInterpreter().sendDialogue("You need a Cooking level of 50 in order to do that.");
            return;
        }
        super.mix(player, event);
    }

    @Override
    public Item getBase() {
        return RAW_OOMLIE;
    }

    @Override
    public Item getProduct() {
        return OOMLIE_WRAP;
    }

    @Override
    public Item[] getIngredients() {
        return new Item[] { PALM_LEAF };
    }

    @Override
    public Item[] getParts() {
        return new Item[] {};
    }

    @Override
    public String getMixMessage(NodeUsageEvent event) {
        return "You wrap the raw oomlie in the palm leaf.";
    }

    @Override
    public boolean isSingular() {
        return true;
    }

}
