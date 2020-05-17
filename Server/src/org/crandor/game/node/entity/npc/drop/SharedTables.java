package org.crandor.game.node.entity.npc.drop;

import org.crandor.game.content.ItemNames;
import org.crandor.game.node.item.WeightedChanceItem;

public enum SharedTables {
    COMMON_SEEDS(new WeightedChanceItem[] {
            new WeightedChanceItem(ItemNames.LIMPWURT_SEED,1,137),
            new WeightedChanceItem(ItemNames.STRAWBERRY_SEED,1,131),
            new WeightedChanceItem(ItemNames.MARRENTILL_SEED,1,125),
            new WeightedChanceItem(ItemNames.JANGERBERRY_SEED,1,92),
            new WeightedChanceItem(ItemNames.TARROMIN_SEED,1,85),
            new WeightedChanceItem(ItemNames.WILDBLOOD_SEED,1,83),
            new WeightedChanceItem(ItemNames.WATERMELON_SEED,1,63),
            new WeightedChanceItem(ItemNames.HARRALANDER_SEED,1,56),
            new WeightedChanceItem(ItemNames.RANARR_SEED,1,39),
            new WeightedChanceItem(ItemNames.WHITEBERRY_SEED,1,34),
            new WeightedChanceItem(ItemNames.MUSHROOM_SPORE,1,29),
            new WeightedChanceItem(ItemNames.TOADFLAX_SEED,1,27),
            new WeightedChanceItem(ItemNames.BELLADONNA_SEED,1,18),
            new WeightedChanceItem(ItemNames.IRIT_SEED,1,18),
            new WeightedChanceItem(ItemNames.POISON_IVY_SEED,1,13),
            new WeightedChanceItem(ItemNames.AVANTOE_SEED,1,12),
            new WeightedChanceItem(ItemNames.CACTUS_SEED,1,12),
            new WeightedChanceItem(ItemNames.KWUARM_SEED,1,9),
            new WeightedChanceItem(ItemNames.SNAPDRAGON_SEED,1,5),
            new WeightedChanceItem(ItemNames.CADANTINE_SEED,1,4),
            new WeightedChanceItem(ItemNames.LANTADYME_SEED,1,3),
            new WeightedChanceItem(ItemNames.DWARF_WEED_SEED,1,2),
            new WeightedChanceItem(ItemNames.TORSTOL_SEED,1,1)
    }),

    FIXED_ALLOTMENT_SEEDS(new WeightedChanceItem[]{
            new WeightedChanceItem(ItemNames.POTATO_SEED, 4, 48),
            new WeightedChanceItem(ItemNames.ONION_SEED,4,36),
            new WeightedChanceItem(ItemNames.CABBAGE_SEED,4,24),
            new WeightedChanceItem(ItemNames.TOMATO_SEED,3,12),
            new WeightedChanceItem(ItemNames.SWEETCORN_SEED,3,6),
            new WeightedChanceItem(ItemNames.STRAWBERRY_SEED,2,3),
            new WeightedChanceItem(ItemNames.WATERMELON_SEED,2,2),
    }),

    RARE_SEEDS(new WeightedChanceItem[]{
            new WeightedChanceItem(ItemNames.TOADFLAX_SEED,1,24),
            new WeightedChanceItem(ItemNames.IRIT_SEED,1,16),
            new WeightedChanceItem(ItemNames.BELLADONNA_SEED,1,16),
            new WeightedChanceItem(ItemNames.AVANTOE_SEED,1,11),
            new WeightedChanceItem(ItemNames.POISON_IVY_SEED,1,11),
            new WeightedChanceItem(ItemNames.CACTUS_SEED,1,11),
            new WeightedChanceItem(ItemNames.KWUARM_SEED,1,8),
            new WeightedChanceItem(ItemNames.SNAPDRAGON_SEED,1,5),
            new WeightedChanceItem(ItemNames.CADANTINE_SEED,1,4),
            new WeightedChanceItem(ItemNames.LANTADYME_SEED,1,3),
            new WeightedChanceItem(ItemNames.DWARF_WEED_SEED,1,2),
            new WeightedChanceItem(ItemNames.TORSTOL_SEED,1,1)
    }),

    TREE_HERB_SEEDS(new WeightedChanceItem[]{
            new WeightedChanceItem(ItemNames.RANARR_SEED,1,8),
            new WeightedChanceItem(ItemNames.SNAPDRAGON_SEED,1,7),
            new WeightedChanceItem(ItemNames.TORSTOL_SEED,1,6),
            new WeightedChanceItem(ItemNames.WATERMELON_SEED,15,5),
            new WeightedChanceItem(ItemNames.WILLOW_SEED,1,5),
            new WeightedChanceItem(ItemNames.MAPLE_SEED,1,5),
            new WeightedChanceItem(ItemNames.YEW_SEED,1,5),
            new WeightedChanceItem(ItemNames.PAPAYA_TREE_SEED,1,4),
            new WeightedChanceItem(ItemNames.MAGIC_SEED, 1, 3),
            new WeightedChanceItem(ItemNames.PALM_TREE_SEED,1, 3),
            new WeightedChanceItem(ItemNames.SPIRIT_SEED,1,2)
    }),

    VARIABLE_ALLOTMENT_SEEDS(new WeightedChanceItem[]{
            new WeightedChanceItem(ItemNames.POTATO_SEED,1,4,64),
            new WeightedChanceItem(ItemNames.ONION_SEED,1,3,32),
            new WeightedChanceItem(ItemNames.CABBAGE_SEED,1,3,16),
            new WeightedChanceItem(ItemNames.TOMATO_SEED,1,2,8),
            new WeightedChanceItem(ItemNames.SWEETCORN_SEED,1,2,4),
            new WeightedChanceItem(ItemNames.STRAWBERRY_SEED,1,2),
            new WeightedChanceItem(ItemNames.WATERMELON_SEED,1,1)
    }),

    HOPS_DROP_TABLE(new WeightedChanceItem[]{
            new WeightedChanceItem(ItemNames.BARLEY_SEED,4,35),
            new WeightedChanceItem(ItemNames.HAMMERSTONE_SEED,4,28),
            new WeightedChanceItem(ItemNames.JUTE_SEED,2,19),
            new WeightedChanceItem(ItemNames.ASGARNIAN_SEED,3,17),
            new WeightedChanceItem(ItemNames.YANILLIAN_SEED,2,12),
            new WeightedChanceItem(ItemNames.KRANDORIAN_SEED,2,4),
            new WeightedChanceItem(ItemNames.WILDBLOOD_SEED,1, 1)
    }),

    HERBS(new WeightedChanceItem[]{
            new WeightedChanceItem(ItemNames.GRIMY_GUAM, 1, 11),
            new WeightedChanceItem(ItemNames.GRIMY_MARRENTILL,1,8),
            new WeightedChanceItem(ItemNames.GRIMY_TARROMIN,1,6),
            new WeightedChanceItem(ItemNames.GRIMY_HARRALANDER,1,5),
            new WeightedChanceItem(ItemNames.GRIMY_RANARR,1,4),
            new WeightedChanceItem(ItemNames.GRIMY_IRIT,1,3),
            new WeightedChanceItem(ItemNames.GRIMY_AVANTOE,1,2),
            new WeightedChanceItem(ItemNames.GRIMY_KWUARM,1,2),
            new WeightedChanceItem(ItemNames.GRIMY_CADANTINE,1,2),
            new WeightedChanceItem(ItemNames.GRIMY_LANTADYME,1,1),
            new WeightedChanceItem(ItemNames.GRIMY_DWARF_WEED,1,1)
    }),

    USEFUL_HERBS(new WeightedChanceItem[]{
            new WeightedChanceItem(ItemNames.GRIMY_AVANTOE_NOTED,1,2),
            new WeightedChanceItem(ItemNames.GRIMY_SNAPDRAGON_NOTED,1,1),
            new WeightedChanceItem(ItemNames.GRIMY_RANARR_NOTED,1,1),
            new WeightedChanceItem(ItemNames.GRIMY_TORSTOL_NOTED,1,1)
    });



    WeightedChanceItem[] table;
    SharedTables(WeightedChanceItem[] table){
        this.table = table;
    }


}
