package plugin.activity.fishingtrawler

import core.game.component.Component
import core.game.node.`object`.GameObject
import core.game.node.`object`.ObjectBuilder
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.entity.player.info.stats.FISHING_TRAWLER_GAMES_WON
import core.game.node.entity.player.info.stats.FISHING_TRAWLER_SHIPS_SANK
import core.game.node.entity.player.info.stats.STATS_BASE
import core.game.node.entity.state.EntityState
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Location
import core.game.world.map.build.DynamicRegion
import core.game.world.update.flag.context.Animation
import core.plugin.Plugin
import core.tools.Items
import core.tools.RandomFunction
import core.tools.secondsToTicks
import core.tools.ticksToSeconds
import java.util.concurrent.TimeUnit
import kotlin.random.Random


/**
 * Handles a fishing trawler session
 * @author Ceikry
 */
private const val OVERLAY_ID = 366
private const val TUTORIAL_ID = 368
private val HOLE_X_COORDS = intArrayOf(29,30,31,32,33,34,35,36)
private const val HOLE_NORTH_Y = 26
private const val HOLE_SOUTH_Y = 23
private const val LEAKING_ID = 2167
private const val PATCHED_ID = 2168
class FishingTrawlerSession(var region: DynamicRegion, val activity: FishingTrawlerActivity) {
    var players: ArrayList<Player> = ArrayList()
    var netRipped = false
    var fishAmount = 0
    var timeLeft = secondsToTicks(600)
    var base = region.baseLocation
    var isActive = false
    var boatSank = false
    var hole_locations = ArrayList<Location>()
    var used_locations = ArrayList<Location>()
    var maxHoles = 0
    var waterAmount = 0
    var murphy: NPC? = null
    val murphyLocations = ArrayList<Location>()
    val npcs = ArrayList<NPC>()
    var inactiveTicks = 0

    fun start(pl: ArrayList<Player>){
        this.players.addAll(pl)
        isActive = true
        initHoles()
        initMurphy(29,25)
        initGulls()
        GameWorld.Pulser.submit(TrawlerPulse(this))
        for(player in pl){
            player.interfaceManager.openOverlay(Component(OVERLAY_ID))
            player.interfaceManager.open(Component(TUTORIAL_ID))
            updateOverlay(player)
            player.properties.teleportLocation = base.transform(36,24,0)
            player.setAttribute("ft-session",this)
            player.logoutPlugins.add(TrawlerLogoutPlugin())
            player.stateManager.set(EntityState.TELEBLOCK,timeLeft)
        }
    }

    fun swapBoatType(fromRegion: Int){
        val newRegion = DynamicRegion.create(fromRegion)
        GameWorld.Pulser.submit(SwapBoatPulse(players,newRegion))
    }

    class SwapBoatPulse(val playerList: ArrayList<Player>,val newRegion: DynamicRegion) : Pulse(3){
        override fun pulse(): Boolean {
            val session: FishingTrawlerSession? = playerList[0].getAttribute("ft-session",null)
            session ?: return true
            session.region = newRegion
            session.base = newRegion.baseLocation
            session.clearNPCs()
            session.initMurphy(26,26)
            session.initGulls()
            for(player in playerList){
                player.interfaceManager.closeOverlay()
                player.appearance.setAnimations(Animation(188))
                player.properties.teleportLocation = session.base.transform(36,24,0)
                player.incrementAttribute("/save:$STATS_BASE:$FISHING_TRAWLER_SHIPS_SANK")
                player.stateManager.remove(EntityState.TELEBLOCK)
            }
            return true
        }
    }

    class TrawlerPulse(val session: FishingTrawlerSession) : Pulse(){
        var ticks = 0
        override fun pulse(): Boolean {
            ticks++
            session.timeLeft--

            if(session.boatSank){
                session.tickMurphy()
                return true
            }

            if(ticks % 15 == 0 && !session.netRipped){
                if(RandomFunction.random(100) <= 10){
                    session.ripNet()
                    session.murphy?.sendChat("Arrh! Check that net!")
                } else {
                    session.fishAmount += 3
                }
            }

            session.waterAmount += (session.getLeakingHoles())
            if(session.waterAmount >= 500){
                session.boatSank = true
                session.isActive = false
                session.swapBoatType(7755)
            }

            if(RandomFunction.random(100) <= 9){
                session.spawnHole()
            }

            if(session.timeLeft <= 0){
                session.isActive = false
                for(player in session.players){
                    player.interfaceManager.closeOverlay()
                    player.properties.teleportLocation = Location.create(2666, 3162, 0)
                    player.logoutPlugins.clear()
                    player.incrementAttribute("/save:$STATS_BASE:$FISHING_TRAWLER_GAMES_WON")
                }
            }

            for(player in session.players){
                session.updateOverlay(player)
            }
            session.tickMurphy()
            return !session.isActive
        }
    }

    fun initHoles(){
        maxHoles = players.size
        if(maxHoles > 16) maxHoles = 16
        if(maxHoles < 5) maxHoles = 5
        val tempLocationList = ArrayList<Location>()
        while(tempLocationList.size < maxHoles){
            val x = HOLE_X_COORDS.random()
            val y = if(Random.nextBoolean()) HOLE_NORTH_Y else HOLE_SOUTH_Y
            val loc = Location.create(x,y,0)
            var alreadyHas = false
            for(location in tempLocationList){
                if(location.equals(loc)) {
                    alreadyHas = true
                    break
                }
            }
            if(!alreadyHas) {
                tempLocationList.add(base.transform(loc.x, loc.y, 0))
            }
        }
        hole_locations.addAll(tempLocationList)
    }

    fun initMurphy(localX: Int, localY: Int){
        murphy = NPC(463)
        murphy?.isWalks = false
        murphy?.isPathBoundMovement = true
        //29,25 -> 36,25
        murphy?.location = base.transform(localX,localY,0)
        murphy?.isRespawn = false
        for(i in 29..36){
            murphyLocations.add(Location.create(base.transform(i,25,0)))
        }
        murphy?.init()
        npcs.add(murphy!!)
    }

    fun clearNPCs(){
        npcs.forEach {
            it.clear()
        }
        npcs.clear()
    }

    fun tickMurphy(){
        var phrase = if(boatSank){
            arrayOf("No fishes for you today!","Keep your head above water, shipmate.", "Arrrgh! We sunk!","You'll be joining Davy Jones!").random()
        } else if(waterAmount < 200){
            arrayOf("Blistering barnacles!","Let's get a net full of fishes!").random()
        } else {
            arrayOf("We'll all end up in a watery grave!","My mother could bail better than that!","It's a fierce sea today traveller.").random()
        }
        if(getLeakingHoles() > 0 && RandomFunction.random(100) <= 15){
            phrase = "The water is coming in matey!"
        }
        if(RandomFunction.random(100) <= 10){
            murphy?.sendChat(phrase)
        }
        if(RandomFunction.random(100) <= 6){
            val dest = murphyLocations.random()
            murphy?.walkingQueue?.reset()
            murphy?.walkingQueue?.addPath(dest.x,dest.y,true)
        }
    }

    fun initGulls(){
        for(loc in arrayOf(base.transform(38,17,0),base.transform(33,18,0),base.transform(28,16,0),base.transform(28,30,0),base.transform(34,32,0))){
            val npc = NPC(1179)
            npc.location = loc
            npcs.add(npc)
            npc.isRespawn = false
            npc.isWalks = true
            npc.walkRadius = 6
            npc.init()
        }
    }

    fun spawnHole(){
        if(hole_locations.isEmpty() && used_locations.isEmpty()) return
        val holeLocation = hole_locations.random().also { hole_locations.remove(it) }
        if(!ObjectBuilder.replace(GameObject(PATCHED_ID, holeLocation), GameObject(LEAKING_ID, holeLocation, if (holeLocation.y == HOLE_NORTH_Y) 1 else 3)) && !ObjectBuilder.replace(GameObject(2177, holeLocation), GameObject(LEAKING_ID, holeLocation, if (holeLocation.y == HOLE_NORTH_Y) 1 else 3))) {
            maxHoles -= 1
        }
    }

    fun getLeakingHoles(): Int{
        return maxHoles - hole_locations.size
    }

    fun repairHole(player: Player,obj: GameObject){
        if(player.inventory.remove(Item(Items.SWAMP_PASTE_1941))){
            ObjectBuilder.replace(obj,GameObject(PATCHED_ID,obj.location,obj.rotation))
            hole_locations.add(obj.location)
            if(RandomFunction.random(100) <= 30){
                murphy?.sendChat("That's the stuff! Fill those holes!")
            }
        } else {
            player.dialogueInterpreter.sendDialogue("You need swamp paste to repair this.")
        }
    }

    fun ripNet(){
        netRipped = true
    }

    fun repairNet(player: Player){
        if(player.inventory.remove(Item(Items.ROPE_954))){
            netRipped = false
            player.dialogueInterpreter.sendDialogue("You repair the net.")
        } else {
            player.dialogueInterpreter.sendDialogue("You need rope to repair this net.")
        }
    }

    fun updateOverlay(player: Player){
        FishingTrawlerOverlay.sendUpdate(player,((waterAmount / 500.0) * 100).toInt(),netRipped,fishAmount,TimeUnit.SECONDS.toMinutes(ticksToSeconds(timeLeft).toLong()).toInt() + 1)
    }
}

class TrawlerLogoutPlugin : Plugin<Player>{
    override fun newInstance(arg: Player?): Plugin<Player> {
        arg?.location = Location.create(2667, 3161, 0)
        val session: FishingTrawlerSession? = arg?.getAttribute("ft-session",null)
        session ?: return this
        session.players.remove(arg)
        return this
    }

    override fun fireEvent(identifier: String?, vararg args: Any?): Any {
        return Unit
    }

}