package core.game.content.dialogue

import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * @author qmqz
 */

@Initializable
class AfrahDialogue : DialoguePlugin {

    private val conversations = arrayOf (0, 4, 10, 11, 15, 17, 20, 22, 23, 24, 29, 32)

    override fun open(vararg args: Any): Boolean {
        player(FacialExpression.ASKING, "Hi!")
        stage = conversations.random()
        npc = args[0] as NPC
        return true
    }

    constructor() {}
    constructor(player: Player?) : super(player) {}

    override fun getIds(): IntArray {
        return intArrayOf(968)
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> {
                npc(FacialExpression.ASKING, "Ooh. This is exciting!")
                stage++
            }
            1 -> {
                player(FacialExpression.ASKING, "Yup!")
                stage = 99
            }
            2 -> {
                npc(FacialExpression.ASKING, "I wouldn't want to be the poor guy that has to", "clean up after the duels.")
                stage++
            }
            3 -> {
                player(FacialExpression.ASKING, "Me neither.")
                stage = 99
            }
            4 -> {
                npc(FacialExpression.ASKING, "My son just won his first duel!")
                stage++
            }
            5 -> {
                player(FacialExpression.ASKING, "Congratulations!")
                stage++
            }
            6 -> {
                npc(FacialExpression.ASKING, "He ripped his opponent in half!")
                stage++
            }
            7 -> {
                player(FacialExpression.ASKING, "That's gotta hurt!")
                stage++
            }
            8 -> {
                npc(FacialExpression.ASKING, "He's only 10 as well!")
                stage++
            }
            9 -> {
                player(FacialExpression.ASKING, "You gotta start 'em young!")
                stage = 99
            }
            10 -> {
                npc(FacialExpression.ASKING, "Hmph.")
                stage = 99
            }
            11 -> {
                npc(FacialExpression.ASKING, "My favourite fighter is Mubariz!")
                stage++
            }
            12 -> {
                player(FacialExpression.ASKING, "The guy at the information kiosk?")
                stage++
            }
            13 -> {
                npc(FacialExpression.ASKING, "Yeah! He rocks!")
                stage++
            }
            14 -> {
                player(FacialExpression.ASKING, "Takes all sorts, I guess.")
                stage = 99
            }
            15 -> {
                npc(FacialExpression.ASKING, "Hi! I'm here to watch the duels!")
                stage++
            }
            16 -> {
                player(FacialExpression.ASKING, "Me too!")
                stage = 99
            }
            17 -> {
                npc(FacialExpression.ASKING, "Did you know they think this place dates","back to the second age?!")
                stage++
            }
            18 -> {
                player(FacialExpression.ASKING, "Really?")
                stage++
            }
            19 -> {
                npc(FacialExpression.ASKING, "Yeah. The guy at the information kiosk was telling me.")
                stage = 99
            }
            20 -> {
                npc(FacialExpression.ANGRY, "Can't you see I'm watching the duels?")
                stage++
            }
            21 -> {
                player(FacialExpression.SAD, "I'm sorry!")
                stage = 99
            }
            22 -> {
                npc(FacialExpression.ASKING, "Well. This beats doing the shopping!")
                stage = 99
            }
            23 -> {
                npc(FacialExpression.ASKING, "Hi!")
                stage = 99
            }
            24 -> {
                npc(FacialExpression.ASKING, "Knock knock!")
                stage++
            }
            25 -> {
                player(FacialExpression.ASKING, "Who's there?")
                stage++
            }
            26 -> {
                npc(FacialExpression.ASKING, "Boo!")
                stage++
            }
            27 -> {
                player(FacialExpression.ASKING, "Boo who?")
                stage++
            }
            28 -> {
                npc(FacialExpression.LAUGH, "Don't cry, it's just me!")
                stage = 99
            }
            29 -> {
                npc(FacialExpression.ASKING, "Why did the skeleton burp?")
                stage++
            }
            30 -> {
                player(FacialExpression.ASKING, "I don't know?")
                stage++
            }
            31 -> {
                npc(FacialExpression.ASKING, "'Cause it didn't have the guts to fart!")
                stage = 99
            }
            32 -> {
                npc(FacialExpression.ASKING, "Waaaaassssssuuuuupp?!.")
                stage = 99
            }
            99 -> end()
        }
        return true
    }

    override fun newInstance(player: Player): DialoguePlugin {
        return AfrahDialogue(player)
    }
}