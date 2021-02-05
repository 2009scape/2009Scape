package plugin.interaction.inter

import core.game.component.Component
import core.game.component.ComponentDefinition
import core.game.component.ComponentPlugin
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.TeleportManager
import core.game.system.SystemLogger
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Location
import core.game.world.map.RegionManager
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import core.tools.RandomFunction

val RING_1 = arrayOf('a','d','c','b')
val RING_2 = arrayOf('i','l','k','j')
val RING_3 = arrayOf('p','s','r','q')

/**
 * Handles the fairy ring interface
 * @author Ceikry
 */
@InitializablePlugin
class FairyRingInterface : ComponentPlugin(){
    override fun open(player: Player?, component: Component?) {
        player ?: return
        super.open(player, component)
        if(component?.id == 734) {
            player.setAttribute("fr:ring1", 0)
            player.setAttribute("fr:ring2", 0)
            player.setAttribute("fr:ring3", 0)
            FairyRing.drawLog(player)

            component.setCloseEvent { pl, _ ->
                pl.removeAttribute("fr:ring1")
                pl.removeAttribute("fr:ring2")
                pl.removeAttribute("fr:ring3")
                pl.configManager.forceSet(816,0,false)
                pl.interfaceManager.closeSingleTab()
                true
            }
        }
    }

    override fun handle(player: Player?, component: Component?, opcode: Int, button: Int, slot: Int, itemId: Int): Boolean {
        player ?: return false
        val delay = player.getAttribute("fr:time",0L)
        if(delay > System.currentTimeMillis()){
            return true
        }

        var delayIncrementer = 1750L
        when(button){
            23 -> delayIncrementer += increment(player,1)
            25 -> delayIncrementer += increment(player,2)
            27 -> delayIncrementer += increment(player,3)
            24 -> decrement(player,1)
            26 -> decrement(player,2)
            28 -> decrement(player,3)
            21 -> confirm(player)
            12 -> delayIncrementer += toggleSortOrder(player)
        }
        player.setAttribute("fr:time",System.currentTimeMillis() + delayIncrementer)
        return true
    }

    fun toggleSortOrder(player: Player): Long{
        val ring1index = player.getAttribute("fr:ring1",0)
        var toSet = player.getAttribute("fr:sortorder",true)
        toSet = !toSet
        SystemLogger.log("$toSet")
        player.setAttribute("fr:sortorder",toSet)
        if(toSet) {
            player.configManager.forceSet(816, ring1index, false)
            player.setAttribute("fr:ring2",0)
            player.setAttribute("fr:ring3",0)
        }
        return -1750L
    }

    fun increment(player: Player,ring: Int): Long{
        val curIndex = player.getAttribute("fr:ring$ring",0)
        var nextIndex = 0
        if(curIndex == 3) nextIndex = 0
        else if(curIndex == 1) nextIndex = 3
        else if(curIndex == 2) nextIndex = 2
        else nextIndex = curIndex + 1
        player.setAttribute("fr:ring$ring",nextIndex)
        return if (curIndex == 1) 1750L else 0L
    }

    fun decrement(player: Player,ring: Int){
        val curIndex = player.getAttribute("fr:ring$ring",0)
        var nextIndex = 0
        if(curIndex == 0) nextIndex = 3
        else nextIndex = curIndex - 1
        player.setAttribute("fr:ring$ring",nextIndex)
    }

    fun confirm(player: Player){
        val ring1index = player.getAttribute("fr:ring1",0)
        val ring2index = player.getAttribute("fr:ring2",0)
        val ring3index = player.getAttribute("fr:ring3",0)
        val code = "${RING_1[ring1index]}${RING_2[ring2index]}${RING_3[ring3index]}"
        val ring: FairyRing? = FairyRing.valueOf(code.toUpperCase())
        var tile = ring?.tile
        if(ring == FairyRing.CIP){
            player.dialogueInterpreter.sendDialogue("The ring seems to reject you.")
        }
        if (ring == null || tile == null) {
            val center = Location(2412, 4434, 0)
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
                    return true
                }
            })
        } else {
            if (!player.savedData.globalData.hasTravelLog(ring.ordinal)) {
                player.savedData.globalData.setTravelLog(ring.ordinal)
            }
        }
        player.interfaceManager.close()
        player.teleporter.send(tile,TeleportManager.TeleportType.FAIRY_RING)
    }

    override fun newInstance(arg: Any?): Plugin<Any> {
        ComponentDefinition.put(734,this)
        ComponentDefinition.put(735,this)
        return this
    }

}

enum class FairyRing(val tile: Location?, val tip: String = "", val childId: Int = -1) {
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
    BLP(Location.create(2437, 5126, 0), "Dungeons: TzHaar area", 42),
    BLQ(null, "Yu'biusk", 43),//Location.create(2229, 4244, 1)
    BLR(Location.create(2740, 3351, 0), "Kandarin: Legends' Guild", 44),
    CIP(null, "Islands: Miscellania", 46), //Location.create(2513, 3884, 0)
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
            for (i in FairyRing.values().indices) {
                if (!player.savedData.globalData.hasTravelLog(i)) {
                    continue
                }
                val ring = FairyRing.values()[i]
                if (ring.childId == -1) {
                    continue
                }
                player.packetDispatch.sendString("<br>" + ring.tip, 735, ring.childId)
            }
        }
    }
}
