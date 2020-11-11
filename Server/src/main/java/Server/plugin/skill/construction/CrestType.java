package plugin.skill.construction;

import core.game.node.entity.player.Player;
import core.tools.ItemNames;
import plugin.skill.Skills;

/**
 * Family crest types.
 *
 * @author Emperor
 * >ORDINAL BOUND<
 */
public enum CrestType implements CrestRequirement {

    ARRAV("the Shield of Arrav symbol") { // requires Shield of Arrav

        @Override
        public boolean eligible(Player player) {
            return player.getQuestRepository().isComplete("Shield of Arrav");
        }
    },
    ASGARNIA("the symbol of Asgarnia"), // no requirements
    DORGESHUUN("the Dorgeshuun brooch") { // requires The Lost Tribe

        @Override
        public boolean eligible(Player player) {
            return player.getQuestRepository().isComplete("The Lost Tribe");
        }
    },
    DRAGON("a dragon") { // requires Dragon Slayer

        @Override
        public boolean eligible(Player player) {
            return player.getQuestRepository().isComplete("Dragon Slayer");
        }
    },
    FAIRY("a fairy") { // requries Lost City

        @Override
        public boolean eligible(Player player) {
            return player.getQuestRepository().isComplete("Lost City");
        }
    },
    GUTHIX("the symbol of Guthix") { // Requires 70+ Prayer

        @Override
        public boolean eligible(Player player) {
            return player.getSkills().hasLevel(Skills.PRAYER, 70);
        }
    },
    HAM("the symbol of the HAM cult."), // no requirements
    HORSE("a horse") { // requires Toy Horsey in inventory

        @Override
        public boolean eligible(Player player) {
            return player.getInventory().containsOneItem(new int[]{
                    ItemNames.BLACK_TOY_HORSEY_2524,
                    ItemNames.BROWN_TOY_HORSEY_2520,
                    ItemNames.GREY_TOY_HORSEY_2526,
                    ItemNames.WHITE_TOY_HORSEY_2522
            });
        }
    },
    JOGRE("Jiggig"), // no requirements
    KANDARIN("the symbol of Kandarin"), // no requirements
    MISTHALIN("the symbol of Misthalin"), // no requirements
    MONEY("a bag of money", 500000), // Costs 500k
    SARADOMIN("the symbol of Saradomin") { // Requires 70+ Prayer

        @Override
        public boolean eligible(Player player) {
            return player.getSkills().hasLevel(Skills.PRAYER, 70);
        }
    },
    SKULL("a skull") { // requires Skulled while talking to Herald

    	@Override
		public boolean eligible(Player player) {
			return player.getSkullManager().isSkulled();
    	}
	},
    VARROCK("the symbol of Varrock"), // no requirements
    ZAMORAK("the symbol of Zamorak") { // Requires 70+ Prayer

        @Override
        public boolean eligible(Player player) {
            return player.getSkills().hasLevel(Skills.PRAYER, 70);
        }
    };

	private String name;
	private int cost;

	CrestType(String name, int cost) {
		this.name = name;
		this.cost = cost;
	}

	CrestType(String name) {
		this(name, 5000);
	}

	public String getName() {
		return this.name;
	}

	public int getCost() {
	    return this.cost;
    }
}

interface CrestRequirement {
    default boolean eligible(Player player) {
        return true;
    }
}