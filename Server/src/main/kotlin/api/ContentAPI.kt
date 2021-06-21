package api

import core.cache.def.impl.ItemDefinition
import core.game.component.Component
import core.game.node.Node
import core.game.node.`object`.GameObject
import core.game.node.`object`.ObjectBuilder
import core.game.node.entity.Entity
import core.game.node.entity.combat.ImpactHandler
import core.game.node.entity.impl.Animator
import core.game.node.entity.impl.Projectile
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.audio.Audio
import core.game.node.entity.player.link.emote.Emotes
import core.game.node.entity.skill.Skills
import core.game.node.entity.skill.gather.SkillingTool
import core.game.node.item.GroundItem
import core.game.node.item.GroundItemManager
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.map.Location
import core.game.world.map.RegionManager
import core.game.world.map.path.Pathfinder
import core.game.world.update.flag.context.Animation
import rs09.ServerConstants
import rs09.game.content.dialogue.DialogueFile
import rs09.game.system.SystemLogger
import rs09.game.world.GameWorld
import rs09.game.world.GameWorld.Pulser
import rs09.game.world.repository.Repository

object ContentAPI {
    /**
     * Gets a skilling tool which the player has the level to use and is in their inventory.
     * @param player the player to get the tool for
     * @param pickaxe whether or not we are trying to get a pickaxe.
     * @return the tool which meets the requirements or null if none.
     */
    @JvmStatic
    fun getTool(player: Player, pickaxe: Boolean): SkillingTool? {
        return if(pickaxe) SkillingTool.getPickaxe(player) else SkillingTool.getHatchet(player)
    }

    /**
     * Check if a player has a given level (buffable/debuffable)
     * @param player the player to check the stat for
     * @param skill the Skill to check. There is an enum for this called Skills. Example: Skills.STRENGTH
     * @param level the level to check against
     * @return true if the check succeeds, false otherwise
     */
    @JvmStatic
    fun hasLevelDyn(player: Player, skill: Int, level: Int): Boolean {
        return player.skills.getLevel(skill) >= level
    }

    /**
     * Check if a player's static skill level meets a certain value
     * @param player the player to check the stat for
     * @param skill the Skill to check. There is an enum for this called Skills. Example: Skills.STRENGTH
     * @param level the level to check against
     * @return true if the check succeeds, false otherwise
     */
    @JvmStatic
    fun hasLevelStat(player: Player, skill: Int, level: Int): Boolean {
        return player.skills.getStaticLevel(skill) >= level
    }

    /**
     * Check if an item exists in a player's inventory
     * @param player the player whose inventory to check
     * @param item the ID of the item to check for
     * @param amount the amount to check for
     * @return true if the player has >= the given item in the given amount, false otherwise.
     */
    @JvmStatic
    fun inInventory(player: Player, item: Int, amount: Int = 1): Boolean {
        return player.inventory.contains(item, amount)
    }

    /**
     * Check the amount of a given item in the player's inventory
     * @param player the player whose inventory to check
     * @param id the ID of the item to check for the amount of
     * @return the amount of the given ID in the player's inventory
     */
    @JvmStatic
    fun amountInInventory(player: Player, id: Int): Int{
        return player.inventory.getAmount(id)
    }

    /**
     * Check the amount of a given item in the player's bank
     * @param player the player to check
     * @param id the ID of the item to check for
     * @return the amount of the ID in the player's bank.
     */
    @JvmStatic
    fun amountInBank(player: Player, id: Int): Int{
        return player.bank.getAmount(id)
    }

    /**
     * Check the amount of a given item in the player's equipment slots
     * @param player the player to check
     * @param id the ID of the item to check for
     * @return the amount of the ID in the player's equipment.
     */
    @JvmStatic
    fun amountInEquipment(player: Player, id: Int): Int{
        return player.equipment.getAmount(id)
    }

    /**
     * Remove an item from a player's inventory
     * @param player the player whose inventory to remove the item from
     * @param item the ID or Item object to remove from the player's inventory
     */
    @JvmStatic
    fun <T> removeItem(player: Player, item: T): Boolean {
        item ?: return false
        when (item) {
            is Item -> return player.inventory.remove(item)
            is Int -> return player.inventory.remove(Item(item))
            else -> SystemLogger.logErr("Attempted to pass a non-item and non-int to removeItem")
        }
        return false
    }

    /**
     * Add an item to a player's inventory
     * @param player the player whose inventory to add an item to
     * @param id the ID of the item to add
     * @param amount the amount of the item to add, defaults to 1
     * @return true if the item exists in the given amount in the player's inventory
     */
    @JvmStatic
    fun addItem(player: Player, id: Int, amount: Int = 1): Boolean{
        return player.inventory.add(Item(id,amount))
    }

    /**
     * Add an item with a variable quantity or drop it if a player does not have enough space
     * @param player the player whose inventory to add to
     * @param id the ID of the item to add to the player's inventory
     * @param amount the amount of the ID to add to the player's inventory, defaults to 1
     */
    @JvmStatic
    fun addItemOrDrop(player: Player, id: Int, amount: Int = 1){
        val item = Item(id, amount)
        if(!player.inventory.add(item)) GroundItemManager.create(item,player)
    }

    /**
     * Check if an item exists in a player's bank
     * @param player the player whose bank to check
     * @param item the ID of the item to check for
     * @param amount the amount to check for, defaults to 1
     * @return true if the item exists in the given amount in the player's bank
     */
    @JvmStatic
    fun inBank(player: Player, item: Int, amount: Int = 1): Boolean {
        return player.bank.contains(item, amount)
    }

    /**
     * Check if an item exists in a player's equipment
     * @param player the player whose equipment to check
     * @param item the ID of the item to check for
     * @param amount the amount to check for, defaults to 1
     * @return true if the item exists in the given amount in the player's equipment
     */
    @JvmStatic
    fun inEquipment(player: Player, item: Int, amount: Int = 1): Boolean {
        return player.equipment.contains(item, amount)
    }

    /**
     * Get number of free slots in a player's inventory
     * @param player the player to check
     * @return the number of free slots in the player's inventory
     */
    @JvmStatic
    fun freeSlots(player: Player): Int {
        return player.inventory.freeSlots()
    }

    /**
     * Get an animation by ID.
     * @param id the ID of the animation to use
     * @return an Animation object with the given ID.
     */
    @JvmStatic
    fun getAnimation(id: Int): Animation {
        return Animation(id)
    }

    /**
     * Get an animation by ID with priority
     * @param id the ID of the animation to get
     * @param priority the Animator.Priority enum instance to represent the desired priority
     * @return an Animation object with the given ID and priority
     */
    @JvmStatic
    fun getAnimationWithPriority(id: Int, priority: Animator.Priority): Animation {
        return Animation(id, Animator.Priority.values()[priority.ordinal])
    }

    /**
     * Reset a player's animator
     * @param player the player whose animator to reset
     */
    @JvmStatic
    fun resetAnimator(player: Player) {
        player.animator.animate(Animation(-1, Animator.Priority.VERY_HIGH))
    }

    /**
     *  Get the number of ticks an animation lasts
     *  @param animation the Animation object to check the duration of
     *  @return the number of ticks the given animation lasts for
     */
    @JvmStatic
    fun animationDuration(animation: Animation): Int {
        return animation.definition.durationTicks
    }

    /**
     * Give a player some amount of experience in a specific skill
     * @param player the player to award XP to
     * @param skill the Skill ID to reward XP for. There is a Skills enum you can use for this. Example: Skills.STRENGTH
     * @param amount the amount, including decimal place, of experience to award
     */
    @JvmStatic
    fun rewardXP(player: Player, skill: Int, amount: Double) {
        player.skills.addExperience(skill, amount)
    }

    /**
     * Replace an object with the given revert timer
     * @param toReplace the GameObject instance we are replacing
     * @param with the ID of the GameObject we wish to replace toReplace with
     * @param for_ticks the number of ticks the object should be replaced for. Use -1 for permanent.
     */
    @JvmStatic
    fun replaceObject(toReplace: GameObject, with: Int, for_ticks: Int) {
        if (for_ticks == -1) {
            ObjectBuilder.replace(toReplace, toReplace.transform(with))
        } else {
            ObjectBuilder.replace(toReplace, toReplace.transform(with), for_ticks)
        }
        toReplace.isActive = false
    }

    /**
     * Gets the name of an item.
     * @param id the ID of the item to get the name of
     * @return the name of the item
     */
    @JvmStatic
    fun getItemName(id: Int): String {
        return ItemDefinition.forId(id).name
    }

    /**
     * Removes a ground item
     * @param node the GroundItem object to remove
     */
    @JvmStatic
    fun removeGroundItem(node: GroundItem) {
        GroundItemManager.destroy(node)
    }

    /**
     * Checks if a ground item is valid/still exists/should exist
     * @param node the GroundItem object to check the validity of
     * @return true if the node is valid, false otherwise
     */
    @JvmStatic
    fun isValidGroundItem(node: GroundItem): Boolean {
        return GroundItemManager.getItems().contains(node)
    }

    /**
     * Checks if a player has space for an item
     * @param player the player whose inventory to check
     * @param item the Item to check against
     * @return true if the player's inventory has space for the item
     */
    @JvmStatic
    fun hasSpaceFor(player: Player, item: Item): Boolean {
        return player.inventory.hasSpaceFor(item)
    }

    /**
     * Get the number of ticks passed since server startup
     */
    @JvmStatic
    fun getWorldTicks(): Int {
        return GameWorld.ticks
    }

    /**
     * Gets an Audio object with specified id, volume, etc
     */
    @JvmStatic
    fun getAudio(id: Int, volume: Int = 10, delay: Int = 1): Audio {
        return Audio(id, volume, delay)
    }

    /**
     * Impact an enemy with the given amount of damage and the given hitsplat type
     * @param entity the entity to damage
     * @param amount the amount of damage to deal
     * @param type the type of hit splat to use, ImpactHandler.HitsplatType is an enum containing these options.
     */
    @JvmStatic
    fun impact(entity: Entity, amount: Int, type: ImpactHandler.HitsplatType) {
        entity.impactHandler.manualHit(entity, amount, type)
    }

    /**
     * Get an item's definition
     * @param id the ID of the item to get the definition of
     * @return the ItemDefinition for the given ID.
     */
    @JvmStatic
    fun itemDefinition(id: Int): ItemDefinition {
        return ItemDefinition.forId(id)
    }

    /**
     * Send an object animation
     */
    @JvmStatic
    fun animateObject(player: Player, obj: GameObject, animationId: Int, global: Boolean = false) {
        player.packetDispatch.sendObjectAnimation(obj, getAnimation(animationId), global)
    }

    /**
     * Produce a ground item owned by the player
     */
    @JvmStatic
    fun produceGroundItem(player: Player, item: Int) {
        GroundItemManager.create(Item(item), player)
    }

    /**
     * Spawns a projectile
     */
    @JvmStatic
    fun spawnProjectile(source: Entity, dest: Entity, projectileId: Int) {
        Projectile.create(source, dest, projectileId).send()
    }

    /**
     * Causes the given entity to face the given toFace
     * @param entity the entity you wish to face something
     * @param toFace the thing to face
     * @param duration how long you wish to face the thing for
     */
    @JvmStatic
    fun face(entity: Entity, toFace: Node, duration: Int = -1) {
        if (duration == -1) {
            when (toFace) {
                is Location -> entity.faceLocation(toFace)
                is Entity -> entity.face(toFace)
            }
        } else {
            when (toFace) {
                is Location -> entity.faceTemporary(toFace.asNpc(), duration)
                else -> entity.faceTemporary(toFace as Entity, duration)
            }
        }
    }

    /**
     * Opens the given interface for the given player
     * @param player the player to open the interface for
     * @param id the ID of the interface to open
     */
    @JvmStatic
    fun openInterface(player: Player, id: Int) {
        player.interfaceManager.open(Component(id))
    }

    /**
     * Runs the given Emote for the given Entity
     * @param entity the entity to run the emote on
     * @param emote the Emotes enum entry to run
     */
    @JvmStatic
    fun emote(entity: Entity, emote: Emotes) {
        entity.animate(emote.animation)
    }

    /**
     * Sends a message to the given player.
     * @param player the player to send the message to.
     */
    @JvmStatic
    fun sendMessage(player: Player, message: String) {
        player.sendMessage(message)
    }

    /**
     * Forces an above-head chat message for the given entity
     * @param entity the entity to send the chat for
     * @param message the message to display
     */
    @JvmStatic
    fun sendChat(entity: Entity, message: String) {
        entity.sendChat(message)
    }

    /**
     * Sends a message to a player's dialogue box
     * @param player the player to send the dialogue to
     * @param message the message to send, lines are split automatically.
     */
    @JvmStatic
    fun sendDialogue(player: Player, message: String) {
        player.dialogueInterpreter.sendDialogue(*DialUtils.splitLines(message))
    }

    /**
     * Plays an animation on the entity
     * @param entity the entity to animate
     * @param anim the animation to play
     * @param forced whether or not to force the animation (usually not necessary)
     */
    @JvmStatic
    fun animate(entity: Entity, anim: Animation, forced: Boolean = false) {
        if (forced) {
            entity.animator.forceAnimation(anim)
        } else {
            entity.animator.animate(anim)
        }
    }

    /**
     * Plays the given Audio for the given Entity
     * @param player the player to play the audio for
     * @param audio the Audio to play
     * @param global if other nearby entities should be able to hear it
     */
    @JvmStatic
    fun playAudio(player: Player, audio: Audio, global: Boolean = false) {
        player.audioManager.send(audio, global)
    }

    /**
     * Opens a dialogue with the given dialogue key or dialogue file, depending which is passed.
     * @param player the player to open the dialogue for
     * @param dialogue either the dialogue key or an instance of a DialogueFile
     * @param args various args to pass to the opened dialogue
     */
    @JvmStatic
    fun openDialogue(player: Player, dialogue: Any, vararg args: Any) {
        when (dialogue) {
            is Int -> player.dialogueInterpreter.open(dialogue, args)
            is DialogueFile -> player.dialogueInterpreter.open(dialogue, args)
            else -> SystemLogger.logErr("Invalid object type passed to openDialogue() -> ${dialogue.javaClass.simpleName}")
        }
    }

    /**
     * Gets an NPC with the given ID from the repository.
     * @param id the ID of the NPC to locate
     * @returns an NPC instance matching the ID if it finds one, null otherwise
     */
    @JvmStatic
    fun findNPC(id: Int): NPC? {
        return Repository.findNPC(id)
    }

    /**
     * Gets an NPC within render distance of the refLoc that matches the given ID
     * @param refLoc the Location to find the closes NPC to
     * @param id the ID of the NPC to locate
     * @returns an NPC instance matching the ID if it finds one, null otherwise
     */
    @JvmStatic
    fun findNPC(refLoc: Location, id: Int): NPC? {
        return Repository.npcs.firstOrNull { it.id == id && it.location.withinDistance(refLoc) }
    }

    /**
     * Gets an NPC with the given ID in the same general area  as the given Entity
     * @param entity the entity to search around
     * @param id the ID of the NPC to locate
     * @returns an NPC matching the given ID or null if none is found
     */
    @JvmStatic
    fun findLocalNPC(entity: Entity, id: Int): NPC? {
        return RegionManager.getLocalNpcs(entity).firstOrNull { it.id == id }
    }

    /**
     * Gets the value of an attribute key from the Entity's attributes store
     * @param entity the entity to get the attribute from
     * @param attribute the attribute key to use
     * @param default the default value to return if the attribute does not exist
     */
    @JvmStatic
    fun <T> getAttribute(entity: Entity, attribute: String, default: T): T {
        return entity.getAttribute(attribute, default)
    }

    /**
     * Sets an attribute key to the given value in an Entity's attribute store
     * @param entity the entity to set the attribute for
     * @param attribute the attribute key to use
     * @param value the value to set the attribute to
     */
    @JvmStatic
    fun <T> setAttribute(entity: Entity, attribute: String, value: T) {
        entity.setAttribute(attribute, value)
    }

    /**
     * Locks the given entity for the given number of ticks
     * @param entity the entity to lock
     * @param duration the number of ticks to lock for
     */
    @JvmStatic
    fun lock(entity: Entity, duration: Int) {
        entity.lock(duration)
    }

    /**
     * Locks specifically an entity's interactions, allowing movement still
     * @param entity the entity to lock
     * @param duration the duration in ticks to lock for
     */
    @JvmStatic
    fun lockInteractions(entity: Entity, duration: Int) {
        entity.locks.lockInteractions(duration)
    }

    /**
     * Unlocks the given entity
     * @param entity the entity to unlock
     */
    @JvmStatic
    fun unlock(entity: Entity) {
        entity.unlock()
    }

    /**
     * Transforms an NPC for the given number of ticks
     * @param npc the NPC object to transform
     * @param transformTo the ID of the NPC to turn into
     * @param restoreTicks the number of ticks until the NPC returns to normal
     */
    @JvmStatic
    fun transformNpc(npc: NPC, transformTo: Int, restoreTicks: Int){
        npc.transform(transformTo)
        Pulser.submit(object : Pulse(restoreTicks){
            override fun pulse(): Boolean {
                npc.reTransform()
                return true
            }
        })
    }

    /**
     * Produces a Location object using the given x,y,z values
     */
    @JvmStatic
    fun location(x: Int, y: Int, z: Int): Location{
        return Location.create(x,y,z)
    }

    /**
     * AHeals the given entity for the given number of hitpoints
     */
    @JvmStatic
    fun heal(entity: Entity, amount: Int){
        entity.skills.heal(amount)
    }

    /**
     * Sets the given varbit for the given player
     * @param player the player to set the varbit for
     * @param varpIndex the index of the VARP that contains the desired varbit.
     * @param offset the offset of the desired varbit inside the varp.
     * @param value the value to set the varbit to
     */
    @JvmStatic
    fun setVarbit(player: Player, varpIndex: Int, offset: Int, value: Int){
        player.varpManager.get(varpIndex).setVarbit(offset,value).send(player)
    }

    /**
     * Clears all bits for a given varp index
     * @param player the player to clear for
     * @param varpIndex the index of the varp to clear
     */
    @JvmStatic
    fun clearVarp(player: Player, varpIndex: Int){
        player.varpManager.get(varpIndex).clear()
    }

    /**
     * Gets the value of all bits collected together from a given varp
     * @param player the player to get the varp for
     * @param varpIndex the index of the varp to calculate the value of
     * @return the value of the varp
     */
    @JvmStatic
    fun getVarpValue(player: Player, varpIndex: Int): Int{
        return player.varpManager.get(varpIndex).getValue()
    }

    /**
     * Gets the value of a specific varbit
     * @param player the player to get the value for
     * @param varpIndex the index of th varp containinig the desired varbit
     * @param offset the offset of the varbit inside the varp
     * @return the value of the given varbit
     */
    @JvmStatic
    fun getVarbitValue(player: Player, varpIndex: Int, offset: Int): Int {
        return player.varpManager.get(varpIndex).getVarbitValue(offset)
    }

    /**
     * Force an entity to walk to a given destination.
     * @param entity the entity to forcewalk
     * @param dest the Location object to walk to
     * @param type the type of pathfinder to use. "smart" for the SMART pathfinder, anything else for DUMB.
     */
    @JvmStatic
    fun forceWalk(entity: Entity, dest: Location, type: String){
        val pathfinder = when(type){
            "smart" -> Pathfinder.SMART
            else -> Pathfinder.DUMB
        }
        val path = Pathfinder.find(entity, dest, true, pathfinder)
        path.walk(entity)
    }

    /**
     * Interrupts a given entity's walking queue
     * @param entity the entity to interrupt
     */
    @JvmStatic
    fun stopWalk(entity: Entity){
        entity.walkingQueue.reset()
    }

    /**
     * Gets the item in the given equipment slot for the given player
     * @param player the player whose equipment to pull from
     * @param slot the Equipment slot to use, EquipmentSlot enum contains the options.
     * @return the Item in the given slot, or null if none.
     */
    @JvmStatic
    fun getItemFromEquipment(player: Player, slot: EquipmentSlot): Item? {
        return player.equipment.get(slot.ordinal)
    }

    /**
     * Adjusts the charge for the given node.
     * @param node the node to adjust the charge of
     * @param amount the amount to adjust by
     */
    @JvmStatic
    fun adjustCharge(node: Node, amount: Int){
        when(node){
            is Item -> node.charge += amount
            is GameObject -> node.charge += amount
            else -> SystemLogger.logErr("Attempt to adjust the charge of invalid type: ${node.javaClass.simpleName}")
        }
    }

    /**
     * Get the current charge of the given node
     * @param node the node whose charge to check
     * @return amount of charges the node has, or -1 if the node does not accept charges.
     */
    @JvmStatic
    fun getCharge(node: Node): Int{
        when(node){
            is Item -> return node.charge
            is GameObject -> return node.charge
            else -> SystemLogger.logErr("Attempt to get charge of invalid type: ${node.javaClass.simpleName}").also { return -1 }
        }
    }

    /**
     * Set the charge of the given node to the given amount.
     * @param node the node to set the charge for
     * @param charge the amount to set the node's charge to (default is 1000)
     */
    @JvmStatic
    fun setCharge(node: Node, charge: Int){
        when(node){
            is Item -> node.charge = charge
            is GameObject -> node.charge = charge
            else -> SystemLogger.logErr("Attempt to set the charge of invalid type: ${node.javaClass.simpleName}")
        }
    }
}