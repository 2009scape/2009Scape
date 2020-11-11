package core.game.node.entity.player.link.diary;

import core.game.content.ItemNames;
import core.game.node.item.Item;

/**
 * An achievement diary type.
 *
 * @author afaroutdude
 */
// TODO reset all players tasks upon commit to master
// TODO figure out secondary rewards for karamja, and fremennik
public enum DiaryType {
    KARAMJA("Karamja", 11,
            new String[]{"Easy", "Medium", "Hard"},
            new String[][]{
                    {
                            "Pick 5 bananas from the plantation located east of the <br><br>volcano",
                            "Use the rope swing to travel to the small island north-west<br><br>of Karamja, where the moss giants are",
                            "Mine some gold from the rocks on the north-west<br><br>peninsula of Karamja",
                            "Travel to Port Sarim via the dock, east of Musa Point",
                            "Travel to Ardougne via the port near Brimhaven",
                            "Explore Cairn Island to the west of Karamja",
                            "Use the Fishing spots north of the banana plantation",
                            "Collect 5 seaweed from anywhere on Karamja",
                            "Attempt the TzHaar Fight Pits or Fight Cave",
                            "Kill a Jogre in the Pothole dungeon"
                    },
                    {
                            "Claim a ticket from the Agility arena in Brimhaven",
                            "Discover hidden wall in the dungeon below the volcano",
                            "Visit the Isle of Crandor via the dungeon below the volcano",
                            "Use Vigroy and Hajedy's cart service",
                            "Earn 100% favour in the village of Tai Bwo Wannai Cleanup", // todo tai bwo wannai cleanup
                            "Cook a spider on stick", // todo tai bwo wannai cleanup
                            "Charter the Lady of the Waves from Cairn Isle to Port Khazard", // todo verify
                            "Cut a log from a teak tree",
                            "Cut a log from a mahogany tree",
                            "Catch a karambwan", // todo need Tai Bwo Wannai Trio
                            "Exchange gems, a gout tuber, trading sticks for a machete", // todo
                            "Use the gnome glider to travel to Karamja",
                            "Grow a healthy fruit tree in the patch near Brimhaven", // todo verify
                            "Trap a Horned Graahk", // todo need to implement pitfall trapping
                            "Chop the vines to gain deeper access to Brimhaven Dungeon",
                            "Cross the lava using the stepping stones within Brimhaven<br><br>Dungeon",
                            "Climb the stairs within Brimhaven Dungeon",
                            "Charter a ship from the shipyard in the far east of Karamja",
                            "Mine a red topaz from a gem rock"
                    },
                    {
                            "Become the Champion of the Fight Pits",
                            "Successfully kill a Ket-Zek in the Fight Caves",
                            "Eat an oomlie wrap",
                            "Craft some nature runes",
                            "Cook a karambwan thoroughly", // todo need Tai Bwo Wannai Trio
                            "Kill a deathwing in the dungeon under the Kharazi Jungle", // todo need legends quest
                            "Use the crossbow shortcut south of the volcano",
                            "Collect five palm leaves",
                            "Be assigned a Slayer task by Duradel or Lapalok in Shilo<br><br>Village.",
                            "Kill a metal dragon in Brimhaven Dungeon."
                    }
            },
            // Karamja gloves 1-3
            // 1k xp 30+, 5k xp any?, 10k xp any?
            new Item[][]{
                    {new Item(ItemNames.KARAMJA_GLOVES_1_11136), new Item(ItemNames.ANTIQUE_LAMP_11137)},
                    {new Item(ItemNames.KARAMJA_GLOVES_2_11138), new Item(ItemNames.ANTIQUE_LAMP_11139)},
                    {new Item(ItemNames.KARAMJA_GLOVES_3_11140), new Item(ItemNames.ANTIQUE_LAMP_11141)}
            },
            "To start marking off tasks in your journal, speak to Pirate<br><br>Jackie the Fruit in Brimhaven, Kaleb Paramay in Shilo<br><br>Village or one of the Jungle Foresters north of the<br><br>Kharazi Jungle.",
            new int[]{1055, 512, 401}
            /**
             * Secondary rewards
             * Easy
             * - cheaper prices on karamja and cheaper ship rides
             * Medium
             * - 10% extra agility xp in arena
             * - 10% extra xp redeeming tickets
             * Hard
             * - Gem mine ladder access // todo
             * - Teleport to underground gem mine // todo
             */
    ),
    // https://runescape.wiki/w/Varrock_achievements?oldid=891055
    VARROCK("Varrock", 15,
            new String[]{"Easy", "Medium", "Hard"},
            new String[][]{ // TODO verify implementation ingame
                    {
                            "Have Thessalia show you what outfits you can wear",
                            "Have Aubury teleport you to the essence mine",
                            "Mine some Iron in the south east mining patch near Varrock",
                            "Make a plank at the sawmill",
                            "Enter the second level of the Stronghold of Security",
                            "Jump over the fence south of Varrock",
                            "Chop down a dying tree in the Lumber Yard",
                            "Buy a copy of the Varrock Herald",
                            "Give a stray dog a bone",
                            "Spin a bowl on the pottery wheel and fire it in the oven in<br><br>Barbarian Village",
                            "Enter Edgeville Dungeon using the entrance to the south of<br><br>Edgeville",
                            "Move our player-owned house portal to a different location<br><br>using the Varrock estate agent",
                            "Speak to Haig Halen after obtaining at least 50 kudos", //TODO need to implement kudos
                            "Enter the Earth Altar using an earth tiara or talisman",
                            "Have Elsie tell you a story",
                            "Mine some limestone near Paterdomus, the temple to the east<br><br>of Varrock",
                            "Catch a trout in the river to the east of Barbarian Village.",
                            "Venture through the cobwebbed corridor in Varrock Sewers",
                            "Find the highest point in Varrock"
                    },
                    {
                            "Have the Apothecary in Varrock make you a strength potion.",
                            "Enter the Champions Guild.",
                            "Take the Dagon'Hai shortcut to the chaos portal",
                            "Get a full complement of rats on your rat pole", // TODO need rat catchers to start
                            "Escape from the spider lair in Varrock Sewers with some red<br><br>spiders eggs",
                            "Use the spirit tree north of Varrock",
                            "Perform the 4 emotes from the Stronghold of Security.",
                            "Select a colour for a new kitten", // TODO need ring of charos(a) and garden of tranquility to start
                            "Use the shortcut under the wall, north-west of the Grand<br><br>Exchange",
                            "Enter the A Soul's Bane rift",
                            "Teleport to the Digsite using a Digsite pendant", // TODO need Digsite and museum
                            "Craft an earth tiara on the Earth Altar",
                            "Pickpocket a guard in the Varrock Palace courtyard",
                            "Use the teleport to Varrock spell",
                            "Get a Slayer task from Vannaka",
                            "Buy twenty mahogany planks from the Sawmill Operator in one<br><br>transaction",
                            "Pick a fruit from the White Tree", // TODO Garden of Tranquility
                            "Use the hot air balloon to travel from Varrock to somewhere<br><br>else", // TODO Enlightened Journey
                            "Get a cat training medal from Gertrude", // TODO need rat catchers and cats fixed
                            "Dial to the fairy ring west of Varrock",
                            "Browse through Oziach's Armour Shop"
                    },
                    {
                            "Pick poison ivy from your bush Farming patch in Varrock<br><br>(west of Champions' Guild)",
                            "Use the pipe shortcut in Varrock Sewers, near the moss<br><br>giants",
                            "Trade furs with the Fancy Dress Seller for a spottier cape",
                            "Smith an adamantite medium helm on the south-east anvil in<br><br>Varrock, next to Aubury's Rune Shop",
                            "Speak to Orlando Smith when you have achieved 153 kudos", // TODO need to implement kudos
                            "Talk to Romily Weaklax and give him a wild pie",
                            "Craft an air battlestaff",
                            "Give your player-owned house a fancy stone or tropical wood<br><br>finish at the Varrock estate agent's",
                            "Make a Varrock teleport tablet on a mahogany lectern",
                            "Obtain a new set of Family Crest gauntlets from Dimintheis",  // TODO need family crest
                            "Make a Waka Canoe near Edgeville",
                            "Use the Home Teleport spell in the Ancient Magicks spellbook<br><br>to teleport to Edgeville",
                            "Use the skull sceptre to teleport to Barbarian Village"
                    }
            },
            new Item[][]{
                    {new Item(11756), new Item(11753)},
                    {new Item(11757), new Item(11754)},
                    {new Item(11758), new Item(11755)}
            },
            "To start marking off tasks in your journal, speak to<br><br>Rat Burgiss south of the city, Reldo in the palace<br><br>library, or Vannaka in the sewers.", // todo made up, can't find source
            // Varrock Armour 1-3
            // 1k xp 30 or higher, 5k xp 40 or higher, 10k 50 or higher
            // Rat Burgiss, Reldo (has an NPC config...), Vannaka
            new int[]{5833, 2660, 1597}
            /**
             * Secondary rewards
             * Easy
             * - chance of smelting two bars at once up to and including steel in edgeville furnace
             * - chance of mining two ores at once when mining ores up to and including coal
             * - better prices when buying and selling in Varrock Area // todo
             * - 16 battlestaffs may be bought from Zaff's staffs store daily (8 + 8)
             * - Option to change Varrock tele location to the GE by talking to Rat Burgiss // todo
             * Medium
             * - chance of smelting two bars at once up to and including Mithril in edgeville furnace
             * - chance of mining two ores at once when mining ores up to and including mithril
             * - chance of smithing at a slightly faster rate // todo
             * - 32 battlestaffs may be bought from Zaff's staffs store daily (8 + 24)
             * - Option to change Varrock tele location to the GE by talking to Reldo // todo
             * Hard
             * - chance of smelting two bars at once up to and including Adamantite in edgeville furnace
             * - chance of mining two ores at once when mining ores up to and including adamantite
             * - access to cook's guild 1st floor bank area
             * - use varrock armour to get into cook's guild rather than chef's hat
             * - 64 battlestaffs may be bought from Zaff's staffs store daily (8 + 56)
             * - Option to change Varrock tele location to the GE by talking to Vannaka // todo
             */
    ),
    // https://runescape.wiki/w/Lumbridge_achievements?oldid=866361
    LUMBRIDGE("Lumbridge", 2,
            new String[]{"Beginner", "Easy", "Medium"},
            new String[][]{
                    {
                            "Climb to the highest point in Lumbridge",
                            "Raise the flag on the roof of the Lumbridge Bank",
                            "Speak to the Duke of Lumbridge",
                            "Speak with the Doomsayer about the Warning System",
                            "Pass through the Al Kharid gate",
                            "Mine some clay in the Mining patch north of the Champions'<br><br>Guild",
                            "Make some soft clay in the Barbarian Village",
                            "Fire a pot in the kiln in the Barbarian Village potter's<br><br>house",
                            "Enter the courtyard of the spooky mansion in Draynor Village",
                            "Visit the Draynor Village market",
                            "Find out about the Rules of Conduct from the Draynor<br><br>Town Crier",
                            "Climb to the top of the Wizards' Tower",
                            "Mine some copper in the Mining spot to the south-east of<br><br>Lumbridge Swamp",
                            "Catch some shrimp in the Fishing spot to the east of<br><br>Lumbridge Swamp",
                            "Have the Fishing Tutor send you on an errand",
                            "Look through Father Aereck's selection of gravestones",
                            "Play the organ in the Lumbridge Church",
                            "Find out about the Stronghold of Security from the Lumbridge<br><br>Guide",
                            "Browse the Lumbridge General Store",
                            "Visit Fred the Farmer's chicken and sheep farm",
                            "Grind some flour in the windmill north of Lumbridge"
                    },
                    {
                            "Mine some iron ore from the Al Kharid Mining spot",
                            "Obtain a cow-hide from a cow in the field north-east of<br><br>Lumbridge",
                            "Have Ellis tan your cow-hide to make soft leather at his<br><br>shop in Al Kharid",
                            "Craft a pair of soft leather gloves",
                            "Catch a pike in the river to the east of Lumbridge Castle",
                            "Smelt a steel bar in the Lumbridge furnace",
                            "Search the shed in Lumbridge Swamp",
                            "Kill a giant rat in Lumbridge Swamp",
                            "Cut down a dead tree in Lumbridge Swamp",
                            "Light a campfire from normal logs in Lumbridge Swamp",
                            "Cook some rat meat on a campfire in Lumbridge Swamp",
                            "Craft a water rune at the Water Altar",
                            "Get a replacement Ghostspeak Amulet from Father Urhney",
                            "Taunt the demon at the top of the Wizard's Tower",
                            "Have Sedridor teleport you to the Essence Mine",
                            "Access the bank in Draynor Village",
                            "Have the Wise Old man check your bank for unnecessary<br><br>quest-related items",
                            "Discover what the Wise Old man is watching through his<br><br>telescope",
                            "Defeat a zombie in the sewers under the jail"
                    },
                    {
                            "Smith a steel longsword on the anvil in the jailhouse<br><br>sewers",
                            "Take a ride on Gnomecopter",
                            "Use the teleport Lumbridge spell",
                            "Light a willow log fire in Lumbridge Castle courtyard",
                            "Cook a lobster on the range in Lumbridge Castle kitchen",
                            "Obtain an Anti-dragonbreath shield from Duke Horacio",
                            "Cut a willow tree, east of Lumbridge Castle",
                            "Smelt a silver bar in the Lumbridge furnace",
                            "Craft a holy symbol in the Lumbridge furnace",
                            "Catch a salmon in the river to the east of Lumbridge Castle",
                            "Mine some silver from the mining spot north of Al Kharid",
                            "Mine some coal in the Mining spot south-west of Lumbridge<br><br>Swamp"
                    }
            },
            // Explorer's Rings 1-3
            // 500 any, 1k xp 30 or higher, 1.5k xp 35 or higher
            new Item[][]{
                    {new Item(13560), new Item(11185)},
                    {new Item(13561), new Item(11186)},
                    {new Item(13562), new Item(11187)}},
            "To start marking off tasks in your journal, speak to<br><br>Explorer Jack near the Lumbridge General Store, Bob<br><br>in his axe store or Ned in his house in Draynor Village.", // todo made up, can't find source
            // Explorer Jack, Bob, Ned // todo more accurate dialogues for these guys
            new int[]{7969, 519, 743}
            /**
             * Secondary Rewards
             * Beginner
             * - Explorer's ring 1 when worn replenishes 50% run energy per charge once a day
             * - Explore emote unlocked
             * Easy
             * - 50% run energy twice a day
             * - Low Level Alchemy 30 times per day without runes
             * - 10% Chance of extra rune (per essence) - air,earth,fire,water
             * Medium
             * - 50% run energy thrice a day
             * - 30 low alch per day
             * - 10% Chance of extra rune (per essence) - air,earth,fire,water
             * - cabbage port
             */

    ),
    // https://runescape.wiki/w/Falador_achievements?oldid=900390
    FALADOR("Falador", 23,
            new String[]{"Easy", "Medium", "Hard"},
            new String[][]{
                    {
                            "Buy a Farming amulet from Sarah on the farm north of Port<br><br>Sarim",
                            "Buy a stat-boosting beer from a waitress in the Rising Sun<br><br>Tavern",
                            "Buy a black chainbody from Wayne's Chains, and try it on in<br><br>the shop",
                            "Climb to the top of the White Knights' Castle",
                            "Discover your family crest from Sir Renitee",
                            "Enter the mole's lair under Falador Park",
                            "Feed Ridgeley, the hairdresser's pet",
                            "Fill a bucket from the pump north of the west Falador bank",
                            "Heal an elemental wizard by casting an appropriate elemental<br><br>spell on him (Air, Water, Earth, Fire)",
                            "Kill a duck in Falador Park",
                            "Kill a highwayman on the road south of Falador",
                            "Make an air tiara",
                            "Pop a party balloon",
                            "Recharge your Prayer points at the altar south-west of Port<br><br>Sarim",
                            "Take the boat to Entrana"
                    },
                    {
                            "Craft a Fruit basket using the loom at the farm north of<br><br>Port Sarim",
                            "Crawl under Falador's south wall",
                            "Grapple up, and then jump off the north Falador wall",
                            "Increase your reputation with the White Knights by killing<br><br>a black knight",
                            "Kill an ice giant in the Asgarnian Ice Dungeon",
                            "Light a bullseye lantern in the chemist's",
                            "Pickpocket a Falador guard",
                            "Place a scarecrow to protect your sweetcorn as it grows in<br><br>the patch north of Port Sarim",
                            "Salute Sir Tiffy Cashien while wearing full initiate armour",
                            "Smith blurite crossbow limbs on Thurgo's anvil",
                            "Travel from Port Sarim to Musa Point for free (with a little<br><br>help from Charos)", // TODO need Creature of Fenkenstrain to actually obtain Charos ring
                            "Visit the Port Sarim rat pits"
                    },
                    {
                            "Ascend the Dark Wizards' Tower while wearing full proselyte<br><br>armour",
                            "Change your family crest to the Saradomin symbol",
                            "Craft 196 or more air runes simultaneously",
                            "Cut down a Yew tree or Magic tree that you grew in Falador<br><br>Park",
                            "Dial to the fairy ring on Mudskipper Point",
                            "Dye a cape pink with Pink dye from Betty in Port Sarim",
                            "Enter the Mining Guild",
                            "Kill a mogre at Mudskipper Point",
                            "Kill a skeletal wyvern in the Asgarnian Ice Dungeon",
                            "Summon an Ibis in the Port Sarim fish store"
                    }
            },
            // Falador Shields 1-3
            // 1k XP, 5k XP, 10k XP
            new Item[][]{
                    {new Item(14577), new Item(14580)},
                    {new Item(14578), new Item(14581)},
                    {new Item(14579), new Item(14582)}
            },
            "To start marking off tasks in your journal, speak to<br><br>Redbeard Frank outside the bar in Port Sarim, The<br><br>Chemist west of Rimmington, or Sir Vyvin's Squire in the<br><br>Falador Castle courtyard.", // todo made up, can't find source
            // Redbeard Frank, Chemist, Squire
            new int[]{375, 367, 606}
            /**
             * Secondary Rewards
             * Easy
             * - shield 1 restores 25% prayer daily, +3 prayer bonus
             * - shields have emotes when you wield and hit operate
             * Medium
             * - shield 2 restores 50% prayer daily, +5 prayer bonus
             * - 10% extra farming xp north of port sarim
             * Hard
             * - shield 3 restores 100% prayer daily, +7 prayer bonus
             * - can trade Mole Skins in to Wyson in exchange for white lily seeds and birds nest
             */
    ),
    // https://runescape.wiki/w/Fremennik_achievements?oldid=877418
    FREMENNIK("Fremennik", 19,
            new String[]{"Easy", "Medium", "Hard"},
            new String[][]{
                    {
                            "Kill a cave crawler in the Fremennik Slayer Dungeon", // TODO
                            "Kill five rock crabs on the shore near Rellekka or on<br><br>Waterbirth Island", // TODO
                            "Find the highest tree on the Fremennik mainland", // TODO
                            "View the rewards in the Barbarian Assault tutorial", // TODO
                            "Speak to Otto Godblessed about barbarian training", // TODO
                            "Collect three seaweed from the shore north-east of Rellekka", // TODO
                            "Find the Hunting Expert on the northern ice plains", // TODO
                            "Catch a fish off one of Rellekka's piers", // TODO
                            "Recharge your Summoning points near Rellekka's gate", // TODO
                            "Kill an adult black unicorn" // TODO
                    },
                    {
                            "Learn of the history of the Fremennik and the Outerlanders<br><br>from Chieftain Brundt", // TODO
                            "Watch a shouting match between Fremennik Isles tower guards<br><br>(the guards can be found between Jatizso and Neitiznot in<br><br>one of the towers)", // TODO
                            "Interact with a Pet rock", // TODO
                            "Make three vials in the furnace building at Rellekka", // TODO
                            "Charm the Fossegrimen into accepting a raw bass", // TODO
                            "Wear yak-hide armour and kill an ice troll", // TODO
                            "Make cheese in the dairy churn in Rellekka", // TODO
                            "Use a fairy ring to appear on a mountaintop, near the<br><br>windswept tree", // TODO
                            "Look at Yrsa's options for recolouring your boots in her<br><br>clothes shop in Rellekka", // TODO
                            "Successfully hunt a sabre-toothed kyatt", // TODO
                            "Steal a fish from the fishing stall in the Rellekka<br><br>marketplace" // TODO
                    },
                    {
                            "Kill three dagannoths in the first layer of the Waterbirth<br><br>Island Dungeon", // TODO
                            "Wear rockshell, spined or skeletal armour and have the<br><br>locals use an honorific with your Fremennik name", // TODO
                            "Complete the Barbarian Outpost Agility Course", // TODO
                            "Mine pure essence on Lunar Isle", // TODO
                            "Make a barbarian pyre ship from arctic pine", // TODO
                            "Catch a tuna without a harpoon", // TODO
                            "Bake a pie using Magic", // TODO
                            "Kill a Mithril dragon", // TODO
                            "Get mahogany from your Etceterian subjects" // TODO
                    }
            },
            // Fremennik sea boots 1-3
            // 5k, 10k, 15k xp
            new Item[][]{
                    {new Item(14571), new Item(14574)},
                    {new Item(14572), new Item(14575)},
                    {new Item(14573), new Item(14576)}
            },
            "", // todo
            // Council Workman, Yrsa, Advisor Ghrim  // TODO dialogues for all these
            new int[]{1287, 1301, 1375}
    ),
    // https://runescape.wiki/w/Seers%27_Village_achievements?oldid=900527
    // https://www.youtube.com/watch?v=chYdp5HKJAg easy
    SEERS_VILLAGE("Seers' Village", 27,
            new String[]{"Easy", "Medium", "Hard"},
            new String[][]{
                    {
                            "Pick five flax from the flax field",
                            "Walk clockwise around the big mysterious statue",
                            "Have Sir Galahad make you a cup of tea",
                            "Take the poison chalice to King Arthur",
                            "Spin five bow strings",
                            "Fill five pots with flour from the Sinclair Mansion",
                            "Give five locals a glass of cider in the Forester's Arms",
                            "Plant some jute",
                            "Use the churn in the Sinclair Mansion garden",
                            "Buy a candle from the candle-maker",
                            "Pray at the Seers' Village altar",
                            "Catch a mackerel"
                    },
                    {
                            "Use the Sinclair Mansion to Fremennik agility shortcut",
                            "Talk to Thormac the Sorcerer about making mystic staves",
                            "Transport a full load of coal to Seers' Village",
                            "Find the highest point in Seers' Village",
                            "Defeat each type of elemental in the Elemental Workshop",
                            "Teleport to Camelot",
                            "Kill one guard on each tower of the Ranging Guild using a longbow",
                            "Have the Ranging Guild competition judge congratulate you<br><br>for acquiring over 1,000 Archery tickets",
                            "Buy something from the ticket exchange in the Ranging Guild",
                            "Use a familiar to make a fire from maple logs within Seers'<br><br>Village",
                            "Get a pet fish from Harry",
                            "Catch and cook a bass in Catherby"
                    },
                    {
                            "Teleport to the Ranging Guild",
                            "Cut five sets of yew logs",
                            "Fletch a magic shortbow in the Seers' Village Bank",
                            "Enter the Seers' Village courthouse with your Piety prayer<br><br>turned on",
                            "Use the fairy ring in McGrubor's Wood",
                            "Burn a magic log in Seers' Village",
                            "High Alch a magic shortbow in the Seers' Village bank",
                            "Catch five sharks in Catherby",
                            "Cook 5 sharks in Catherby using the Cooking gauntlets",
                            "Charge five water orbs in one go",
                            "Use the grapple shortcut to get from the water obelisk<br><br>island to Catherby shore"
                    }
            },
            // Seers Headbad 1-3
            // 1k 30 and up, 5k, 10k xp lamps
            new Item[][]{
                    {new Item(14631), new Item(14633)},
                    {new Item(14631), new Item(14634)},
                    {new Item(14631), new Item(14635)}
            },
            "To start marking off tasks in your journal, speak to<br><br>any seer in Seers' Village, Stankers by the coal<br><br>trucks or Sir Kay in Camelot.", // todo made up, can't find source
            // Seer, Stankers, Sir Kay
            new int[]{388, 383, 241}
            /**
             * Secondary Rewards
             * Easy
             * - 30 flax a day from Flax (Geoffrey)
             * Medium
             * - 60 flax a day from Geoffrey
             * - 10% more experience cutting maples while wearing Seer's Headband
             * - Extra log given when cutting normal trees in Seer's Village
             * Hard ( see https://www.youtube.com/watch?v=64tRpc6oASM )
             * - 120 flax a day from Geoffrey
             * - Significant increase in speed spinning wheel in Seer's village
             * - Enhanced Excalibur - lady of the lake
             */
    );

    /**
     * The name of the diary.
     */
    private final String name;

    /**
     * The child id.
     */
    private final int child;

    /**
     * Level names
     */
    private final String[] levelNames;

    /**
     * The achievement descriptions.
     */
    private final String[][] achievements;

    /**
     * The item rewards.
     */
    private final Item[][] rewards;

    /**
     * The info to start the diary.
     */
    private final String info;

    /**
     * The npcs for the task levels.
     */
    private final int[] npcs;

    /**
     * Constructs a new {@code DiaryType} {@code Object}
     *
     * @param name         the name of the diary.
     * @param child        the child id.
     * @param achievements the achievements.
     * @param rewards      the rewards.
     * @param info         the info about the diary.
     * @param npcs         the npcs for the task levels.
     */
    DiaryType(String name, int child, String[] levelNames, String[][] achievements, Item[][] rewards, String info, int[] npcs) {
        this.name = name;
        this.child = child;
        this.levelNames = levelNames;
        this.achievements = achievements;
        this.rewards = rewards;
        this.info = info;
        this.npcs = npcs;
    }

    /**
     * Gets the diary type for the child id.
     *
     * @param child the child.
     * @return the diary type.
     */
    public static DiaryType forChild(int child) {
        for (DiaryType type : values()) {
            for (int i = 0; i < 4; i++) {
                if (child == type.getChild() + i) {
                    return type;
                }
            }
        }
        return null;
    }

    /**
     * Gets the npc for the level.
     *
     * @param level the level.
     * @return the npc id.
     */
    public int getNpc(int level) {
        return npcs[level];
    }

    /**
     * Gets the rewards for the task level.
     *
     * @param level the level.
     * @return the rewards.
     */
    public Item[] getRewards(int level) {
        return rewards[level];
    }

    /**
     * Gets the achievements for the task level.
     *
     * @param level the level.
     * @return the achievements.
     */
    public String[] getAchievements(int level) {
        return achievements[level];
    }

    /**
     * Gets the achievements.
     *
     * @return the achievements
     */
    public String[][] getAchievements() {
        return achievements;
    }

    /**
     * Gets the rewards.
     *
     * @return the rewards
     */
    public Item[][] getRewards() {
        return rewards;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the level names
     *
     * @return the level names
     */
    public String[] getLevelNames() {
        return levelNames;
    }

    /**
     * Gets the info.
     *
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * Gets the child.
     *
     * @return the child
     */
    public int getChild() {
        return child;
    }

    /**
     * Gets the npcs.
     *
     * @return the npcs
     */
    public int[] getNpcs() {
        return npcs;
    }
}