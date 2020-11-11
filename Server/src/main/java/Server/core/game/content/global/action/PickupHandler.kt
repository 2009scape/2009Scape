package core.game.content.global.action

import core.game.content.ItemNames
import core.game.content.global.GodType
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.audio.Audio
import core.game.node.entity.player.link.diary.DiaryType
import core.game.node.item.GroundItem
import core.game.node.item.GroundItemManager
import core.game.node.item.Item
import core.game.system.SystemLogger.log
import core.game.system.config.GroundSpawnLoader.GroundSpawn
import core.game.world.GameWorld
import core.game.world.map.RegionManager
import core.game.world.update.flag.context.Animation
import plugin.ai.AIRepository.Companion.getItems
import plugin.dialogue.FacialExpression
import plugin.skill.runecrafting.RunePouch

/**
 * A class used to handle the picking up of ground items.
 * @author 'Vexia
 */
object PickupHandler {
    /**
     * Method used to take a ground item.
     * @param player the player.
     * @param item the item.
     * @return `True` if taken.
     */
    @JvmStatic
    fun take(player: Player, item: GroundItem): Boolean {
        if (item.location == null) {
            player.packetDispatch.sendMessage("Invalid ground item!")
            return true
        }
        if (!GroundItemManager.getItems().contains(item)) {
            player.packetDispatch.sendMessage("Too late!")
            return true
        }
        if (player.getAttribute("droppedItem:" + item.id, 0) > GameWorld.ticks) { //Splinter
            log("$player, tried to do the drop & quick pick-up Ground Item dupe.")
            return true
        }
        if (item !is GroundSpawn && item.isRemainPrivate && !item.droppedBy(player)) {
            player.sendMessage("You can't take that item!")
            return true
        }
        val add = Item(item.id, item.amount, item.charge)
        if (!player.inventory.hasSpaceFor(add)) {
            player.packetDispatch.sendMessage("You don't have enough inventory space to hold that item.")
            return true
        }
        if (!canTake(player, item, 0)) {
            return true
        }
        if (item.isActive && player.inventory.add(add)) {
            if (!RegionManager.isTeleportPermitted(item.location)) {
                player.animate(Animation.create(535))
            }

            if (item is GroundSpawn && item.getId() == ItemNames.SEAWEED
                    && player.zoneMonitor.isInZone("karamja")
                    && !player.achievementDiaryManager.hasCompletedTask(DiaryType.KARAMJA, 0, 7)) {
                var seaweed = player.getAttribute("seaweed", 0)
                seaweed++
                player.setAttribute("seaweed", seaweed)
                player.achievementDiaryManager.updateTask(player, DiaryType.KARAMJA, 0, 7, seaweed >= 5)
            }
            // Collect five palm leaves
            if (item.getId() == ItemNames.PALM_LEAF_2339 && player.zoneMonitor.isInZone("karamja") && !player.achievementDiaryManager.hasCompletedTask(DiaryType.KARAMJA, 2, 7)) {
                var palms = player.getAttribute("palms", 0)
                palms++
                player.setAttribute("palms", palms)
                player.achievementDiaryManager.updateTask(player, DiaryType.KARAMJA, 2, 7, palms >= 5)
            }
            GroundItemManager.destroy(item)
            if (item.dropper?.isArtificial == true) {
                getItems(item.dropper)?.remove(item)
            }
            player.audioManager.send(Audio(2582, 10, 1))
        }
        return true
    }

    /**
     * Checks if the player can take an item.
     * @param player the player.
     * @param item the item.
     * @param type the type (1= ground, 2=telegrab)
     * @return `True` if so.
     */
    @JvmStatic
    fun canTake(player: Player, item: GroundItem, type: Int): Boolean {
        if (item.dropper != null && !item.droppedBy(player) && player.ironmanManager.checkRestriction()) {
            return false
        }
        if (item.id == 8858 || item.id == 8859) {
            player.dialogueInterpreter.sendDialogues(4300, FacialExpression.FURIOUS, "Hey! You can't take that, it's guild property. Take one", "from the pile.")
            return false
        }
        if (GodType.forCape(item) != null) {
            if (GodType.hasAny(player)) {
                player.sendMessages("You may only possess one sacred cape at a time.", "The conflicting powers of the capes drive them apart.")
                return false
            }
        }
        if (RunePouch.forItem(item) != null) {
            if (player.hasItem(item)) {
                player.sendMessage("A mystical force prevents you from picking up the pouch.")
                return false
            }
        }
        return if (item.hasItemPlugin()) {
            item.plugin.canPickUp(player, item, type)
        } else true
    }
}
