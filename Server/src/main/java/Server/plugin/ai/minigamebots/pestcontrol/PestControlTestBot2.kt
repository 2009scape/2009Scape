package plugin.ai.minigamebots.pestcontrol

import core.game.node.entity.player.link.prayer.PrayerType
import core.game.world.map.Location
import core.net.packet.`in`.InteractionPacket
import core.tools.RandomFunction
import plugin.activity.pestcontrol.PestControlHelper
import plugin.activity.pestcontrol.PestControlHelper.BoatInfo
import plugin.ai.pvmbots.CombatBotAssembler
import plugin.ai.pvmbots.PvMBots
import java.util.*
//"pestcontrolcopies.txt",
class PestControlTestBot2(l: Location) : PvMBots(legitimizeLocation(l)) {

        var tick = 0
    var combatMoveTimer = 0
    var justStartedGame = true
    var movetimer = 0
    var openedGate = false
    var myCounter = 0
    var random30 = Random().nextInt(30)
    var random5 = Random().nextInt(5)
    var randomType = Random().nextInt(100)
    var random10 = Random().nextInt(10)
    var random20 = Random().nextInt(20)
    val num = Random().nextInt(4)
    private val myBoat = BoatInfo.INTERMEDIATE
    private val combathandler = CombatState2(this)

     enum class State {
        OUTSIDE_GANGPLANK,
        WAITING_IN_BOAT,
        PLAY_GAME,
        GET_TO_PC
    }
    //Novice Lander co-ords (2657, 2639, 0)
    //Intermediate lander co-ords (2644, 2644, 0)
    //Veteran lander co-ords (2638, 2653 0)
    init {
        if (num <= 2) {
            CombatBotAssembler().gearPCiMeleeBot(this)
        } else {
            CombatBotAssembler().gearPCiRangedBot(this, Random().nextInt() % 2 == 0)
        }
    }


    override fun tick() {
        super.tick()
        movetimer--
        if (movetimer <= 0) {
            movetimer = 0
            val state = state
            customState = state.toString() + movetimer
            when (state) {
                State.GET_TO_PC -> toPC
                State.OUTSIDE_GANGPLANK -> enterBoat()
                State.WAITING_IN_BOAT -> idleInBoat()
                State.PLAY_GAME -> attackNPCs()
            }
        }
    }

    private val state: State
        private get() {
            if (PestControlHelper.landerContainsLoc(getLocation())) {
                return State.WAITING_IN_BOAT
            }
            if (PestControlHelper.isInPestControlInstance(this)) {
                return State.PLAY_GAME
            }
            return if (PestControlHelper.outsideGangplankContainsLoc2(getLocation())) {
                State.OUTSIDE_GANGPLANK
            } else State.GET_TO_PC
        }

    private fun attackNPCs() {
        walkingQueue.isRunning = true
        val creatures = FindTargets(this, 30)
        if (creatures == null || creatures.isEmpty()) {
            if (randomType > 50) {
                customState = "Going to portals"
                combathandler.goToPortals()
            } else {
                try {
                    randomWalkAroundPoint(PestControlHelper.getMyPestControlSession(this).squire.location, 3)
                } catch (e: NullPointerException) {
                    State.GET_TO_PC
                    //Do nothing, game just finished
                }
                movetimer = random20 + 6
            }
        } else {
            if (Random().nextInt(100) < 20 && random10 <= 1) {
                randomWalkAroundPoint(PestControlHelper.getMyPestControlSession(this).squire.location, 3)
                movetimer = random20 + 6
            } else {
                customState = "Fighting NPCs"
                combathandler.fightNPCs()
            }
        }
    }

    private var insideBoatWalks = 3
    private fun idleInBoat() {
        justStartedGame = true
        openedGate = false
        if (prayer.active.contains(PrayerType.PROTECT_FROM_MELEE)) {
            prayer.toggle(PrayerType.PROTECT_FROM_MELEE) }
        if (randomType < 40) {
            if (Random().nextInt(insideBoatWalks) <= 1) {
                (insideBoatWalks * 1.5).toInt()
                if (Random().nextInt(4) === 1) {
                    walkingQueue.isRunning = !walkingQueue.isRunning
                }
                if (Random().nextInt(7) === 1) {
                    this.walkToPosSmart(Location(2660, 2638))
                } else {
                    this.walkToPosSmart(myBoat.boatBorder.randomLoc)
                }
            }
            if (Random().nextInt(3) === 1) {
                insideBoatWalks += 2
            }
        }
    }

    private fun enterBoat() {
        if (prayer.active.contains(PrayerType.PROTECT_FROM_MELEE)) {
            prayer.toggle(PrayerType.PROTECT_FROM_MELEE)
        }
        if (Random().nextInt(3) <= 1)
        {
            return
        }
        if (Random().nextInt(5) == 1)
        {
            movetimer = Random().nextInt(2)
            this.walkToPosSmart(myBoat.outsideBoatBorder.getWeightedRandomLoc(2))
        }
        if (Random().nextInt(100) > 20 && Random().nextInt(6) == 0)
        {
            if (Random().nextInt(16) == 0)
            {
                this.walkToPosSmart(myBoat.outsideBoatBorder.randomLoc)
                movetimer += RandomFunction.normalPlusWeightRandDist(400, 200)
            }
            movetimer = RandomFunction.normalPlusWeightRandDist(100, 50)
            return
        }
        val test = getClosestNodeWithEntry(15, myBoat.ladderId)
        InteractionPacket.handleObjectInteraction(this, 0, test.location, test.id)
        insideBoatWalks = 3
    }

    private val toPC: Unit
        private get() {
            val test = getClosestNodeWithEntry(25, myBoat.ladderId)
            if (test == null) {
                this.teleport(PestControlHelper.PestControlIslandLocation2)
            } else {
                InteractionPacket.handleObjectInteraction(this, test)
            }
        }

    companion object {
        private fun legitimizeLocation(l: Location): Location {
            return if (PestControlHelper.landerContainsLoc(l)) Location(2648, 2648, 0) else l
        }
    }
}