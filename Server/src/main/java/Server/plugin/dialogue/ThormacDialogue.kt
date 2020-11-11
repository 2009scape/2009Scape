package plugin.dialogue

import core.game.component.Component
import core.game.content.ItemNames
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.diary.DiaryType
import core.game.node.item.Item
import core.plugin.InitializablePlugin

/**
 * Thormac
 * Involved in Scorpion Catcher
 * @author afaroutdude
 */
@InitializablePlugin
class ThormacDialogue(player: Player? = null) : DialoguePlugin(player) {
    val scorpionCageEmpty = Item(ItemNames.SCORPION_CAGE_456)
    val scorpionCageFull = Item(ItemNames.SCORPION_CAGE_463)
    val scorpionCages: IntArray = intArrayOf(
        ItemNames.SCORPION_CAGE_456,
        ItemNames.SCORPION_CAGE_457,
        ItemNames.SCORPION_CAGE_458,
        ItemNames.SCORPION_CAGE_459,
        ItemNames.SCORPION_CAGE_460,
        ItemNames.SCORPION_CAGE_461,
        ItemNames.SCORPION_CAGE_462,
        ItemNames.SCORPION_CAGE_463
    )

    val COMPONENT = Component(332)

    override fun open(vararg args: Any?): Boolean {
        val scorpionStage = try {
            player?.questRepository?.getStage("Scorpion Catcher") ?: 100
        } catch (e: Exception) {
            100
        }
        when (scorpionStage) {
            0 -> {
                npc("Hello I am Thormac the Sorcerer, I don't suppose you could be of assistance to me?");stage = 0
            }
            10, 20, 30, 40, 50, 60, 70, 80, 90 -> {
                npc("How goes your quest?");stage = 70
            }
            100 -> {
                npc("Thank you for rescuing my scorpions.");stage = 100
            }
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            999 -> {end()}

            // initial convo
            0 -> {options("What do you need assistance with?", "I'm a little busy.");stage++}
            1 -> when(buttonId){
                1 -> {player ("What do you need assistance with?");stage=2}
                2 -> {player ("I'm a little busy.");stage=50}
            }
            2 -> {npc ("I've lost my pet scorpions. They're lesser Kharid scorpions, a very rare breed. I left their cage door open, now I don't know where they've gone. There's three of them, and they're quick little beasties. They're all over Runescape.")}
            3 -> {options ("So how would I go about catching them then?", "What's in it for me?", "I'm not interested then.");stage++}
            4 -> when(buttonId){
                1 -> {player("So how would I go about catching them then?");stage++}
                2 -> {player("What's in it for me?");stage=20}}
                3 -> {player ("I'm not interested then.");stage=51}
            5 -> {npc("Well I have a scorpion cage here which you can use to catch them in.");stage++}
            6 -> {
                sendDialogue("Thormac gives you a cage.")
                player.inventory.add(scorpionCageEmpty)
                stage++
            }
            7 -> {npc ("If you go up to the village of Seers, to the North of here, one of them will be able to tell you where the scorpions are now.");stage++}
            8 -> {options ("What's in it for me?", "Ok, I will do it then.");stage++}
            9 -> when(buttonId){
                1 -> {player("What's in it for me?");stage=20}
                2 -> {
                    player("Ok, I will do it then.")
                    // start quest here
                    stage = 999
                }
            }

            20 -> {npc ("Well I suppose I can aid you with my skills as a staff sorcerer. Most battlestaffs around here are a bit puny. I can beef them up for you a bit. If you go up to the village of Seers, to the North of here, one of them will be able to tell you where the scorpions are now.");stage++}
            21 -> {options("So how would I go about catching them then?","Ok, I will do it then.");stage++}
            22 -> when(buttonId) {
                1 -> {player("So how would I go about catching them then?");stage=5}
                2 -> {
                    player("Ok, I wil do it then.")
                    // start quest here
                    stage = 999
                }
            }
            50 -> {npc ("Come back if you have some spare time.");stage=999} // invention
            51 -> {npc ("Come back if you change your mind.");stage=999} // invention

            // after that
            70 -> {
                if (!player.inventory.containsOneItem(scorpionCages)
                        && !player.bank.containsOneItem(scorpionCages)) {
                    player("I've lost my cage.")
                    stage = 71
                } else if (!player.inventory.containsOneItem(scorpionCageFull.id)) {
                    player("I've not caught all the scorpions yet.")
                    stage = 72
                } else {
                    player("I have retrieved all your scorpions.")
                    stage = 73
                }
            }
            71 -> {
                // if lost or destroyed cage
                npc("Ok, here's another cage. You're almost as bad at losing things as me.")
                player.inventory.add(scorpionCageEmpty)
                stage=999
            }
            72 -> {npc("Well remember to go speak to the Seers, North of here, if you need any help.");stage=999}
            73 -> {
                npc("Aha, my little scorpions home at last!")
                // complete quest here
                stage=999
            }

            // post-quest
            100 -> {
                if (!player.inventory.contains(84,1)) {
                    options("That's okay.", "You said you'd enchant my battlestaff for me.")
                } else {
                    options("That's okay.", "You said you'd enchant my battlestaff for me.", "Could you enchant my Armadyl battlestaff?")
                }
                stage++
            }
            101 -> when (buttonId) {
                1 -> {player("That's okay.");stage=999}
                2 -> {player ("You said you'd enchant my battlestaff for me.");stage=110}
                3 -> {player ("Could you enchant my Armadyl battlestaff?");stage=150}
            }
            110 -> {npc("Yes, although it'll cost you 40,000 coins for the","materials. What kind of staff did you want enchanting?");stage++}
            111 -> {
                end()
                player.interfaceManager.open(COMPONENT)
                player.achievementDiaryManager.finishTask(player, DiaryType.SEERS_VILLAGE, 1, 1)
            }
        }
        return true;
    }


    override fun newInstance(player: Player?): DialoguePlugin {
        return ThormacDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(389)
    }
}