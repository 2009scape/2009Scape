package plugin.skill.agility.shortcuts

import core.game.node.`object`.GameObject
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.diary.DiaryType
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.update.flag.context.Animation
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import plugin.skill.agility.AgilityHandler
import plugin.skill.agility.AgilityShortcut

/**
 * Handles a pipe shortcut.
 * Quick overview of what's included:
 * Pipe interactions, locations, animations
 * Quick OBJ lookup for pipe shortcut (ps):
 * ps2290, ps5099, ps5100, ps9293, ps20210, ps29370
 * Animations used:
 * 10578: pipe enter(Due to tweening this one may cause issues, player standing up/it showing through the pipe, use 10580 instead)
 * 10579: pipe exit, 10580: pipe enter + exit
 * @author Woah
 */
@InitializablePlugin
class PipeShortcut : AgilityShortcut {

    /**
     * Constructs a new {@Code PipeShortcut} {@Code Object}
     */
    constructor() : super(intArrayOf(), 0, 0.0, "")

    /**
     * Constructs a new {@Code PipeShortcut} {@Code Object}
     *
     * @param ids        the ids.
     * @param level      the level.
     * @param experience the experience.
     * @param options    the options.
     */
    constructor(ids: IntArray?, level: Int, experience: Double, vararg options: String?) : super(ids, level, experience, *options)

    /**
     * These are all the pipes that are deemed "Shortcuts"
     * Note: Barbarian Outpost is included in this list because it is not apart of the
     * Barbarian Outpost Agility Course.
     */
    override fun newInstance(arg: Any?): Plugin<Any> {
        configure(PipeShortcut(intArrayOf(2290), 49, 0.0, "squeeze-through")) //Yanille Dungeon
        configure(PipeShortcut(intArrayOf(5099), 22, 8.5, "squeeze-through")) //Brimhaven Dungeon Pipe Red Dragons -> Black Demons
        configure(PipeShortcut(intArrayOf(5100), 34, 10.0, "squeeze-through")) //Brimhaven Dungeon Pipe Moss Giants -> Moss Giants
        configure(PipeShortcut(intArrayOf(9293), 70, 10.0, "squeeze-through")) //Taverley Dungeon
        configure(PipeShortcut(intArrayOf(20210), 35, 10.0, "squeeze-through")) //Barbarian Outpost
        configure(PipeShortcut(intArrayOf(29370), 51, 10.0, "squeeze-through")) //Edgeville Dungeon
        return this
    }

    /**
     * Run for the main fire event
     * Use this for unlocking achievement diaries + any other unlocks
     */
    override fun run(player: Player, obj: GameObject, option: String, failed: Boolean) {
        if (obj.id == 29370)
            player.achievementDiaryManager.finishTask(player, DiaryType.VARROCK, 2, 1)

        /**
         * Pulse that starts the object interaction when clicked on one of these shortcuts
         */
        GameWorld.Pulser.submit(object : Pulse(1, player) {
            override fun pulse(): Boolean {
                when (obj.id) {

                /**
                * Quick breakdown of how Agility.forcewalk is used for pipe shortcuts:
                * AgilityHandler.forceWalk(player, -1, player.location, pipeDestination(player, obj, 6), Animation.create(10580), 10, 0.0, null)
                    *
                * player = player, the player that is being force-walked
                * -1 = course index, should this be counted as an agility course +1?
                * player.location, gets the player location (start of pipe)
                * pipeDestination(player, obj, 6) = custom made for pipes, player = player, obj is getting the game object location, 6 is the amount to move forward. || More info found in AgilityShortcut.java
                * Animation.create(10580) = what animation we should play when the user is interacting with that object
                * 10 = speed at which the player moves through the object
                * 0.0 = experience that we should award the player on successful completion* SET EXPERIENCE IN METHOD newInstance
                * null = message that should be displayed when entering the object
                */

                    /**
                     * Yanille Dungeon pipe (Obj id: 2290) LOC: (2577, 9506, 0)
                     * Taverley Dungeon pipe (Obj id: 9293) LOC: (2887, 9799, 0)
                     * Diagram:
                     * This one is pretty standard, does not to have X/Y checks because the pipe is built into the wall
                     * Player lock (standard 5 ticks for a 7 long pipe)
                     * Plays the get into + crawl + jump out of pipe animation
                     */
                    2290, 9293 -> {
                        player.lock(7)
                        AgilityHandler.forceWalk(player, -1, player.location, pipeDestination(player, obj, 6), Animation.create(10580), 10, 0.0, null)
                        player.animate(Animation(844), 4)
                        player.animate(Animation(10579), 5)
                        return true
                    }

                    /**
                     * //Brimhaven Dungeon Pipe Red Dragons -> Black Demons (Obj id: 5099) LOC: (2698, 9498, 0)
                     * //Brimhaven Dungeon Pipe Moss Giants -> Moss Giants (Obj id: 5100) LOC: (2655, 9567, 0)
                     * Diagram:
                     * This one is pretty standard, does not to have X/Y checks because the pipe is built into the wall
                     * Player lock (standard 5 ticks for a 7 long pipe)
                     * Plays the get into + crawl + jump out of pipe animation
                     */
                    5099, 5100 -> {
                        player.lock(5)
                        AgilityHandler.forceWalk(player, -1, player.location, pipeDestination(player, obj, 7), Animation.create(10580), 10, 0.0, null)
                        player.animate(Animation(844), 5)
                        player.animate(Animation(10579), 6)
                        return true
                    }

                    /**
                     * Barbarian Outpost Pipe (Obj id: 20210) LOC: (2552, 3560, 0)
                     * Diagram:
                     * If statement checks to see if the player is on the same X Axis as the pipe, by not having this here a player could enter
                     * the pipe at an angle and would be shot through the walls (kinda funny to watch)
                     * Player lock (standard 3 ticks for a 3 long pipe)
                     * plays the all-in-one animation for getting into + out of pipe
                     */
                    20210 -> {
                        if (player.location.x != 2552) {
                            player.packetDispatch.sendMessage("I can't get into this pipe at that angle.")
                            return true
                        }
                        player.lock(3)
                        AgilityHandler.forceWalk(player, -1, player.location, pipeDestination(player, obj, 3), Animation.create(10580), 15, 0.0, null)
                        return true
                    }

                    /**
                     * Edgeville Dungeon Pipe (Obj id: 29370) LOC: (3150, 9906, 0)
                     * Diagram:
                     * If statement checks to see if the player is on the same Y Axis as the pipe, by not having this here a player could enter
                     * the pipe at an angle and would be shot through the walls
                     * Player lock (standard 7 ticks for a 6 long pipe)
                     * Plays the get into + crawl + jump out of pipe animation
                     */
                    29370 -> {
                        if (player.location.y != 9906) {
                            player.packetDispatch.sendMessage("I can't get into this pipe at that angle.")
                            return true
                        }
                        player.lock(7)
                        AgilityHandler.forceWalk(player, -1, player.location, pipeDestination(player, obj, 6), Animation.create(10580), 10, 0.0, null)
                        player.animate(Animation(844), 4)
                        player.animate(Animation(10579), 5)
                        return true
                    }
                }
                return false
            }
        })
    }
}