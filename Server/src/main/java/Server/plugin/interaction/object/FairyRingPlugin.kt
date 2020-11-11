package plugin.interaction.`object`

import core.cache.def.impl.ObjectDefinition
import core.game.component.Component
import core.game.component.ComponentPlugin
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.TeleportManager.TeleportType
import core.game.node.entity.player.link.diary.DiaryType
import core.game.system.SystemLogger
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Location
import core.game.world.map.RegionManager
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import core.plugin.PluginManager.definePlugin
import core.tools.RandomFunction

/**
 * Handles the fairy ring plugin.
 * @author Ceikry
 * @author Aero
 */
@InitializablePlugin
class FairyRingPlugin : OptionHandler() {
    @Throws(Throwable::class)
    override fun newInstance(arg: Any?): Plugin<Any> {
        for (i in RINGS) {
            ObjectDefinition.forId(i).handlers["option:use"] = this
        }
        ObjectDefinition.forId(MAIN_RING).handlers["option:use"] = this
        definePlugin(FairyInterfaceHandler())
        return this
    }

    override fun handle(player: Player, node: Node, option: String): Boolean {
        when (option) {
            "use" -> {
                if (!player.equipment.contains(772, 1) && !player.equipment.contains(9084, 1)) {
                    player.sendMessage("The fairy ring only works for those who wield fairy magic.")
                    return true
                }
                when (node.id) {
                    MAIN_RING -> openFairyRing(player)
                    else -> player.teleporter.send(Location.create(2412, 4434, 0), TeleportType.FAIRY_RING)
                }
            }
        }
        return true
    }

    /**
     * Handles the fairy interface handler.
     * @author Ceikry
     */
    class FairyInterfaceHandler : ComponentPlugin() {
        @Throws(Throwable::class)
        override fun newInstance(arg: Any?): Plugin<Any> {
           // ComponentDefinition.forId(734).plugin = this
           // ComponentDefinition.forId(735).plugin = this
            return this
        }

        override fun handle(player: Player, component: Component, opcode: Int, button: Int, slot: Int, itemId: Int): Boolean {
            var firstIndex = 0
            var secondIndex = 0
            var thirdIndex = 0
            when (component.id) {
                734 -> {
                    if (player.getAttribute("fairy-delay", 0) > GameWorld.ticks) {
                        return true
                    }
                    when (button) {
                        //counter-clockwise
                        24 -> {
                            updateFirst(player, true)
                        }
                        26 -> {
                            updateSecond(player, true)
                        }
                        28 -> {
                            updateThird(player, true)
                        }
                        //clockwise
                        23 -> {
                            updateFirst(player, false)
                        }
                        25 -> {
                            updateSecond(player, false)
                        }
                        27 -> {
                            updateThird(player, false)
                        }

                        //teleport
                        21 -> teleport(player, getFirst(player), getSecond(player), getThird(player)).also { return true }
                    }
                    SystemLogger.log("Letters Selected: ${SELECTOR_LETTERS[0][getFirst(player)]}${SELECTOR_LETTERS[1][getSecond(player)]}${SELECTOR_LETTERS[2][getThird(player)]}")
                    player.setAttribute("fairy-delay", GameWorld.ticks + 4)
                }
                735 -> when (button) {
                    12 -> {
                        TODO("Add handling for list re-sorting.")
                    }
                }
            }
            return true
        }
        fun updateFirst(player: Player, decrement: Boolean){
            if(player.getAttribute("firstLocked", false)){ //For some reason the client script for the interface resets all other wheels when you move the first
                player.configManager.set(817, 0)
                player.configManager.set(818, 0)
                player.removeAttribute("firstLocked")
            }
            val current = player.configManager.get(816)
            var new = 0
            if(decrement){
                new = if(current == 0) 3 else current - 1
            } else {
                new = if (current == 3) 0 else current + 1
                if(new == 2) new = 3 //have to do this because the client script always skips index 2 when rotating clock-wise (anti macro feature?)
            }
            player.configManager.set(816, new)
        }

        fun updateSecond(player: Player, decrement: Boolean){
            player.setAttribute("firstLocked", true)
            val current = player.configManager.get(817)
            var new = 0
            if(decrement){
                new = if(current == 0) 3 else current - 1
            } else {
                new = if (current == 3) 0 else current + 1
                if(new == 2) new = 3
            }
            player.configManager.set(817, new)
        }

        fun updateThird(player: Player, decrement: Boolean){
            player.setAttribute("firstLocked", true)
            val current = player.configManager.get(818)
            var new = 0
            if(decrement){
                new = if(current == 0) 3 else current - 1
            } else {
                new = if (current == 3) 0 else current + 1
                if(new == 2) new = 3
            }
            player.configManager.set(818, new)
        }

        fun getFirst(player: Player): Int {return player.configManager.get(816)}
        fun getSecond(player: Player): Int {return player.configManager.get(817)}
        fun getThird(player: Player): Int {return player.configManager.get(818)}
    }



    /**
     * Opens the fairy ring interface.
     * @param player the player.
     */
    private fun openFairyRing(player: Player) {
        reset(player)
        player.interfaceManager.open(Component(734).setCloseEvent { p, c ->
            player.interfaceManager.closeSingleTab()
            true
        })
        SystemLogger.log("GOT HERE")
        player.interfaceManager.openSingleTab(Component(735))
        FairyRing.drawLog(player)
        player.sendMessage("<col=FF0000>The interface skips an item sometimes, which is for some reason the way jagex wanted it.</col>")
    }

    /**
     * An enumeration holding data on the different fairy ring codes.
     * @author Aero Date: 2 Sep 2014, 18:18:41
     */
    enum class FairyRing
    /**
     * Constructs a new {@Code FairyRing} {@Code Object}
     * @param tile the tile.
     * @param tip the tip.
     */(
            /**
             * The tile.
             */
            val tile: Location?,
            /**
             * The tip.
             */
            val tip: String = "",
            /**
             * The child id.
             */
            val childId: Int = -1) {
        AIQ(Location.create(2996, 3114, 0), "Asgarnia: Mudskipper Point", 15),
        AIR(Location.create(2700, 3247, 0), "Islands: South of Witchaven", 16),
        AJQ(Location.create(2735, 5221, 0), "Dungeons: Dark cave south of Dorgesh-Kaann", 19),
        ALR(Location.create(3059, 4875, 0), "Other realms: Abyssal Area", 28),
        AJR(Location.create(2780, 3613, 0), "Kandarin: Slayer cave south-east of Rellekka", 20),
        AJS(Location.create(2500, 3896, 0), "Islands: Penguins near Miscellania", 21),
        AKQ(Location.create(2319, 3619, 0), "Kandarin: Piscatoris Hunter area", 23),
        AKS(Location.create(2571, 2956, 0), "Feldip Hills: Jungle Hunter area", 25),
        ALQ(Location.create(3597, 3495, 0), "Morytania: Haunted Woods east of Canifis", 27),
        ALS(Location.create(2644, 3495, 0), "Kandarin: McGrubor's Wood", 29),
        BIP(Location.create(3410, 3324, 0), "Islands: River Salve", 30),
        BIQ(Location.create(3251, 3095, 0), "Kharidian Desert: Near Kalphite hive", 31),
        BIS(Location.create(2635, 3266, 0), "Kandarin: Ardougne Zoo unicorns", 33),
        BJR(null, "Other Realms: Realm of the Fisher King", 36),
        BKP(Location.create(2385, 3035, 0), "Feldip Hills: South of Castle Wars", 38),
        BKQ(Location.create(3041, 4532, 0), "Other realms: Enchanted Valley", 39),
        BKR(Location.create(3469, 3431, 0), "Morytania: Mort Myre, south of Canifis", 40),
        BLR(Location.create(2740, 3351, 0), "Kandarin: Legends' Guild", 44),
        CIP(Location.create(2513, 3884, 0), "Islands: Miscellania", 46),
        CIQ(Location.create(2528, 3127, 0), "Kandarin: North-west of Yanille", 47),
        CJR(Location.create(2705, 3576, 0), "Kandarin: Sinclair Mansion", 52),
        CKP(Location.create(2075, 4848, 0), "Other realms: Cosmic Entity's plane", 54),
        CKR(Location.create(2801, 3003, 0), "Karamja: South of Tai Bwo Wannai Village", 56),
        CKS(Location.create(3447, 3470, 0), "Morytania: Canifis", 57),
        CLP(Location.create(3082, 3206, 0), "Islands: South of Draynor Village", 58),
        CLS(Location.create(2682, 3081, 0), "Islands: Jungle spiders near Yanille", 61),
        DIR(Location.create(3038, 5348, 0), "Other realms: Goraks' Plane", 64),
        DIS(Location.create(3108, 3149, 0), "Misthalin: Wizards' Tower", 65),
        DJP(Location.create(2658, 3230, 0), "Kandarin: Tower of Life", 66),
        DJR(Location.create(2676, 3587, 0), "Kandarin: Sinclair Mansion (west)", 68),
        DKP(Location.create(2900, 3111, 0), "Karamja: South of Musa Point", 70),
        DKR(Location.create(3129, 3496, 0), "Misthalin: Edgeville", 72),
        DKS(Location.create(2744, 3719, 0), "Kandarin: Snowy Hunter area", 73),
        DLQ(Location.create(3423, 3016, 0), "Kharidian Desert: North of Nardah", 75),
        DLR(Location.create(2213, 3099, 0), "Islands: Poison Waste south of Isafdar", 76),
        AIS(null), AIP(null), AKP(null), FAIRY_HOME(Location.create(2412, 4434, 0));

        companion object {
            /**
             * Draws the travel log.
             * @param player the player.
             */
            fun drawLog(player: Player) {
                for (i in values().indices) {
                    if (!player.savedData.globalData.hasTravelLog(i)) {
                        continue
                    }
                    val ring = values()[i]
                    if (ring.childId == -1) {
                        continue
                    }
                    player.packetDispatch.sendString("<br>" + ring.tip, 735, ring.childId)
                }
            }
        }
    }

    companion object {
        /**
         * The selector letters.
         */
        val SELECTOR_LETTERS = arrayOf(arrayOf("a", "d", "c", "b"), arrayOf("i", "l", "k", "j"), arrayOf("p", "s", "r", "q"))

        /**
         * The rings.
         */
        private val RINGS = intArrayOf(12003, 12094, 12095, 14058, 14061, 14064, 14067, 14070, 14073, 14076, 14079, 14082, 14085, 14088, 14091, 14094, 14097, 14100, 14103, 14106, 14109, 14112, 14115, 14118, 14121, 14124, 14127, 14130, 14133, 14136, 14139, 14142, 14145, 14148, 14151, 14154, 14157, 14160, 16181, 16184, 23047, 27325, 37727)

        /**
         * The main fairy ring.
         */
        private const val MAIN_RING = 12128

        /**
         * Teleports the player.
         * @param player the player.
         */
        private fun teleport(player: Player, first: Int, second: Int, third: Int) {
            val string = "${SELECTOR_LETTERS[0][first]}${SELECTOR_LETTERS[1][second]}${SELECTOR_LETTERS[2][third]}"
            System.out.println(string)
            var fairyRing: FairyRing? = null
            try {
                fairyRing = FairyRing.valueOf(string.toString().toUpperCase())
            } catch (e: IllegalArgumentException) {
            }
            var tile = fairyRing?.tile
            if (fairyRing == null || tile == null) {
                val center = Location(2412, 4434, 0)
                tile = Location.create(2412, 4431, 0)
                tile = if (RandomFunction.random(2) == 1) {
                    center.transform(RandomFunction.random(2, 6), RandomFunction.random(2, 6), 0)
                } else {
                    center.transform(RandomFunction.random(-2, -6), RandomFunction.random(-2, -6), 0)
                }
                if (!RegionManager.isTeleportPermitted(tile) || RegionManager.getObject(tile) != null) {
                    tile = Location.create(2412, 4431, 0)
                }
                GameWorld.Pulser.submit(object : Pulse(4, player) {
                    override fun pulse(): Boolean {
                        player.dialogueInterpreter.sendDialogues(player, null, "Wow, fairy magic sure is useful, I hardly moved at all!")
                        player.sendMessage("Please enter the code in slowly and <col=FF0000>one letter at a time</col> to avoid the interface")
                        player.sendMessage("teleporting you to the wrong location.")
                        return true
                    }
                })
            }
            if (fairyRing != null && fairyRing.tile != null) {
                player.sendMessage("" + fairyRing)
                if (!player.savedData.globalData.hasTravelLog(fairyRing.ordinal)) {
                    player.savedData.globalData.setTravelLog(fairyRing.ordinal)
                }
            }
            sendTeleport(player, tile)
        }

        /**
         * Resets the players combos.
         * @param player the player.
         */
        private fun reset(player: Player) {
            player.removeAttribute("fairy-delay")
            player.removeAttribute("fairy_location_combo")
            for (i in 0..2) {
                player.configManager[816 + i] = 0
            }
        }

        /**
         * Sends the fairy teleport for a player.
         * @param player The player.
         * @param tile The tile to teleport to.
         */
        fun sendTeleport(player: Player, tile: Location?) {
            reset(player)
            player.interfaceManager.close()
            player.teleporter.send(tile, TeleportType.FAIRY_RING)
        }
    }
}