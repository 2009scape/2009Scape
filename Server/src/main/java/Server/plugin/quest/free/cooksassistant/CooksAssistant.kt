package plugin.quest.free.cooksassistant

import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.plugin.InitializablePlugin
import plugin.skill.Skills

/**
 * The Quest Journal and Configuration for the Cook's Assistant Quest.
 * @author Qweqker
 */

@InitializablePlugin
class CooksAssistant : Quest("Cook's Assistant",15, 14, 1, 29, 0, 1, 2){
    val MILK = 1927
    val FLOUR = 1933
    val EGG = 1944

    override fun drawJournal(player: Player?, stage: Int) {

        super.drawJournal(player, stage)

        var line = 12
        var stage = player?.questRepository?.getStage("Cook's Assistant")!!


        if(stage < 10){ //If the quest has not been started

            line(player,"I can start this quest by speaking to the !!Cook?? in the", line++)
            line(player,"!!Kitchen?? on the ground floor of !!Lumbridge Castle.??", line)

        } else if (stage in 10..99) { //Player has started Cook's Assistant

            //Situation
            line(player,"It's the !!Duke of Lumbridge's?? birthday and I have to help", line++)
            line(player,"his !!Cook?? make him a !!birthday cake.?? To do this I need to", line++)
            line(player,"bring him the following ingredients:", line++)

            //MILK
            if (player.getAttribute("cooks_assistant:milk_submitted", false) || player.getAttribute("cooks_assistant:all_submitted", false)) { //If the player has handed in the bucket of milk
                line(player,"---I have given the cook a bucket of milk./--", line++)
            } else if (player.inventory.contains(MILK, 1)){ // If the player has a bucket of milk in their inventory
                line(player, "I have found a !!bucket of milk?? to give to the cook.", line++)
            } else { //If the player satisfies none of the above
                line(player,"I need to find a !!bucket of milk.?? There's a cattle field east", line++)
                line(player,"of Lumbridge, I should make sure I take an empty bucket", line++)
                line(player, "with me.", line++)
            }

            //FLOUR
            if (player.getAttribute("cooks_assistant:flour_submitted", false) || player.getAttribute("cooks_assistant:all_submitted", false)) { //If the player has handed in the pot of flour
                line(player,"---I have given the cook a pot of flour./--", line++)
            } else if (player.inventory.contains(FLOUR, 1)){ // If the player has a pot of flour in their inventory
                line(player, "I have found a !!pot of flour?? to give to the cook.", line++)
            } else { //If the player satisfies none of the above
                line(player,"I need to find a !!pot of flour.?? There's a mill found north-", line++)
                line(player,"west of Lumbridge, I should take an empty pot with me.", line++)
            }

            //EGG
            if (player.getAttribute("cooks_assistant:egg_submitted", false) || player.getAttribute("cooks_assistant:all_submitted", false)) { //If the player has handed in the egg
                line(player,"---I have given the cook an egg./--", line++)
            } else if (player.inventory.contains(EGG, 1)){ // If the player has an egg in their inventory
                line(player, "I have found an !!egg?? to give to the cook.", line++)
            } else { //If the player satisfies none of the above
                line(player,"I need to find an !!egg.?? The cook normally gets his eggs from", line++)
                line(player,"the Groats' farm, found just to the west of the cattle", line++)
                line(player,"field.", line)
            }

            //If the player has handed everything in but was interrupted the final dialogue
            if (player.getAttribute("cooks_assistant:all_submitted", false) || (player.getAttribute("cooks_assistant:milk_submitted", false) && player.getAttribute("cooks_assistant:flour_submitted", false) && player.getAttribute("cooks_assistant:egg_submitted", false))) {
                line(player,"I should return to the !!Cook.??", line)
            }

        } else if (stage >= 100) { //If the player has completed the quest
            line(player,"---It was the Duke of Lumbridge's birthday,  but his cook had/--", line++)
            line(player,"---forgotten to buy the ingredients he needed to make him a/--", line++)
            line(player,"---cake. I brought the cook an egg, some flour and some milk/--", line++)
            line(player,"---and then cook made a delicious looking cake with them./--", line++)
            line += 1
            line(player,"---As a reward he now lets me use his high quality range/--", line++)
            line(player,"---which lets me burn things less whenever I wish to cook/--",line++)
            line(player,"---there./--", line++)
            line += 1
            line(player,"<col=FF0000>QUEST COMPLETE!</col>",line)
        }
    }

    //The Quest Finish Certificate
    override fun finish(player: Player) {
        var ln = 10
        super.finish(player)
        player.packetDispatch.sendString("You have completed the Cook's Assistant Quest!", 277, 4)
        player.packetDispatch.sendItemZoomOnInterface(1891, 240, 277, 5)

        drawReward(player,"1 Quest Point", ln++)
        drawReward(player,"300 Cooking XP", ln)
        player.skills.addExperience(Skills.COOKING, 300.0)

        //Removing these attributes in the event that they weren't already cleared in the Cook's Dialogue
        player.removeAttribute("cooks_assistant:milk_submitted")
        player.removeAttribute("cooks_assistant:flour_submitted")
        player.removeAttribute("cooks_assistant:egg_submitted")
        player.removeAttribute("cooks_assistant:all_submitted")
        player.removeAttribute("cooks_assistant:submitted_some_items")
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }
}