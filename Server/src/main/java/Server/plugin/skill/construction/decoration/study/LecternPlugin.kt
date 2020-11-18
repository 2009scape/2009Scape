package plugin.skill.construction.decoration.study

import core.cache.def.impl.ObjectDefinition
import core.game.component.Component
import core.game.component.ComponentDefinition
import core.game.component.ComponentPlugin
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.diary.DiaryType
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.update.flag.context.Animation
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import core.plugin.PluginManager.definePlugin
import plugin.interaction.item.TeleTabsOptionPlugin
import plugin.skill.Skills
import plugin.skill.construction.Decoration
import plugin.skill.construction.decoration.study.LecternPlugin.TeleTabButton

/**
 * Handles the lectern
 * LecternPlugin
 * @author Ceikry
 */
@InitializablePlugin
class LecternPlugin : OptionHandler() {
    /**
     * TeleTabButton
     */
    private enum class TeleTabButton(
            /**
             * The button id
             */
            val buttonId: Int,
            /**
             * The level and xp
             */
            val level: Int,
            val xp: Int,
            /**
             * The item
             */
            val tabItem: Item,
            /**
             * The required decoration (lectern)
             */
            private val requiredDecorations: Array<Decoration>, vararg requiredItems: Item) {
        ARDOUGNE(2, 51, 66, Item(TeleTabsOptionPlugin.TeleTabs.ADDOUGNE_TELEPORT.item), arrayOf<Decoration>(Decoration.TEAK_EAGLE_LECTERN, Decoration.MAHOGANY_EAGLE_LECTERN), SOFT_CLAY, Item(563, 2), Item(555, 2)),
        BONES_TO_BANANNAS(3, 15, 32, Item(8014), arrayOf<Decoration>(Decoration.DEMON_LECTERN, Decoration.TEAK_DEMON_LECTERN, Decoration.MAHOGANY_DEMON_LECTERN), SOFT_CLAY, Item(561, 1), Item(557, 2), Item(555, 2)),
        BONES_TO_PEACHES(4, 60, 36, Item(8015), arrayOf<Decoration>(Decoration.MAHOGANY_DEMON_LECTERN), SOFT_CLAY, Item(561, 2), Item(557, 4), Item(555, 4)),
        CAMELOT(5, 45, 56, Item(TeleTabsOptionPlugin.TeleTabs.CAMELOT_TELEPORT.item), arrayOf<Decoration>(Decoration.TEAK_EAGLE_LECTERN, Decoration.MAHOGANY_EAGLE_LECTERN), SOFT_CLAY, Item(563), Item(556, 5)),
        ENCHANT_DIAMOND(6, 57, 62, Item(8019), arrayOf<Decoration>(Decoration.TEAK_DEMON_LECTERN, Decoration.MAHOGANY_DEMON_LECTERN), SOFT_CLAY, Item(564), Item(557, 10)),
        ENCHANT_DRAGONSTONE(7, 68, 60, Item(8020), arrayOf<Decoration>(Decoration.MAHOGANY_DEMON_LECTERN), SOFT_CLAY, Item(564), Item(557, 15), Item(555, 15)),
        ENCHANT_EMERALD(8, 27, 34, Item(8017), arrayOf<Decoration>(Decoration.DEMON_LECTERN, Decoration.TEAK_DEMON_LECTERN, Decoration.MAHOGANY_DEMON_LECTERN), SOFT_CLAY, Item(564), Item(556, 3)),
        ENCHANT_ONYX(9, 87, 84, Item(8021), arrayOf<Decoration>(Decoration.MAHOGANY_DEMON_LECTERN), SOFT_CLAY, Item(564), Item(557, 20), Item(554, 20)),
        ENCHANT_RUBY(10, 49, 52, Item(8018), arrayOf<Decoration>(Decoration.TEAK_DEMON_LECTERN, Decoration.MAHOGANY_DEMON_LECTERN), SOFT_CLAY, Item(564), Item(554, 5)),
        ENCHANT_SAPPHIRE(11, 7, 12, Item(8016), arrayOf<Decoration>(Decoration.OAK_LECTERN, Decoration.EAGLE_LECTERN, Decoration.TEAK_EAGLE_LECTERN, Decoration.MAHOGANY_EAGLE_LECTERN, Decoration.DEMON_LECTERN, Decoration.TEAK_DEMON_LECTERN, Decoration.MAHOGANY_DEMON_LECTERN), SOFT_CLAY, Item(564), Item(555)),
        FALADOR(12, 37, 48, Item(TeleTabsOptionPlugin.TeleTabs.FALADOR_TELEPORT.item), arrayOf<Decoration>(Decoration.EAGLE_LECTERN, Decoration.TEAK_EAGLE_LECTERN, Decoration.MAHOGANY_EAGLE_LECTERN), SOFT_CLAY, Item(563), Item(555), Item(556, 3)),
        LUMBRIDGE(13, 31, 40, Item(TeleTabsOptionPlugin.TeleTabs.LUMBRIDGE_TELEPORT.item), arrayOf<Decoration>(Decoration.EAGLE_LECTERN, Decoration.TEAK_EAGLE_LECTERN, Decoration.MAHOGANY_EAGLE_LECTERN), SOFT_CLAY, Item(563), Item(557), Item(556, 3)),
        HOUSE(14, 40, 30, Item(8013), arrayOf<Decoration>(Decoration.MAHOGANY_EAGLE_LECTERN), SOFT_CLAY, Item(563), Item(557), Item(556)),
        VARROCK(15, 25, 35, Item(TeleTabsOptionPlugin.TeleTabs.VARROCK_TELEPORT.item), arrayOf<Decoration>(Decoration.OAK_LECTERN, Decoration.EAGLE_LECTERN, Decoration.TEAK_EAGLE_LECTERN, Decoration.MAHOGANY_EAGLE_LECTERN, Decoration.DEMON_LECTERN, Decoration.TEAK_DEMON_LECTERN, Decoration.MAHOGANY_DEMON_LECTERN), SOFT_CLAY, Item(563), Item(554), Item(556, 3)),
        WATCHTOWER(16, 58, 74, Item(TeleTabsOptionPlugin.TeleTabs.WATCH_TOWER_TELEPORT.item), arrayOf<Decoration>(Decoration.MAHOGANY_EAGLE_LECTERN), SOFT_CLAY, Item(563, 2), Item(557, 2));

        /**
         * The required items
         */
        val requiredItems: ArrayList<Item> = arrayListOf(*requiredItems)

        /**
         * Checks if the player can make this tab
         * @param player
         * @return
         */
        fun canMake(player: Player): Boolean {
            val objectId = player.getAttribute<Int>("ttb:objectid")
            if (player.skills.getLevel(Skills.MAGIC) < level && player.spellBookManager.spellBook == 192) {
                player.sendMessage("You need a magic level of $level to make that!")
                return false
            }
            if (this == BONES_TO_PEACHES && !player.savedData.activityData.isBonesToPeaches) {
                player.sendMessages("You need the Bones to Peaches ability purchased from MTA before making these.", "This requirement doesn't apply to actually using the tabs.")
                return false
            }
            var found = false
            for (d in requiredDecorations) if (d.objectId == objectId) found = true
            if (!found) {
                player.sendMessage("You're unable to make this tab on this specific lectern.")
                return false
            }
            for (item in requiredItems) {
                if (!player.inventory.containsItem(item)) {
                    //TODO staffs
                    player.sendMessage("You don't have enough materials.")
                    return false
                }
            }
            return true
        }

        companion object {
            /**
             * Gets the button for the id
             * @param id
             * @return
             */
            fun forId(id: Int): TeleTabButton? {
                for (ttb in values()) {
                    if (ttb.buttonId == id) return ttb
                }
                return null
            }
        }

    }

    @Throws(Throwable::class)
    override fun newInstance(arg: Any?): Plugin<Any>? {
        for (i in 13642..13648) {
            ObjectDefinition.forId(i).handlers["option:study"] = this
        }
        definePlugin(TeleTabInterface())
        return this
    }

    override fun handle(player: Player, node: Node, option: String): Boolean {
        val id = node.asObject().id
        player.setAttribute("ttb:objectid", id)
        GameWorld.Pulser.submit(object : Pulse(){
            var counter = 0
            override fun pulse(): Boolean {
                when(counter++){
                    0 -> player.animator.animate(Animation(4460)).also { player.lock() }
                    8 -> player.interfaceManager.open(Component(400)).also { player.unlock(); return true }
                }
                return false
            }
        })
        /*var bits = 0 We dumb so we comment it out haha code go brrrrr
        for (t in TeleTabButton.values()) {
            if (t.canMake(player)) bits = bits or (1 shl t.buttonId)
        }
        player.configManager[2005] = bits*/
        return true
    }

    /**
     * TeleTabInterface
     * @author Ceikry
     */
    private class TeleTabInterface : ComponentPlugin() {
        @Throws(Throwable::class)
        override fun newInstance(arg: Any?): Plugin<Any>? {
            ComponentDefinition.put(400, this)
            return this
        }

        override fun handle(player: Player, component: Component, opcode: Int, button: Int, slot: Int, itemId: Int): Boolean {
            val ttb = TeleTabButton.forId(button)
            if (ttb != null && ttb.canMake(player)) {
                player.interfaceManager.close()
                player.pulseManager.run(object : Pulse(1) {
                    override fun pulse(): Boolean {
                        if (!ttb.canMake(player)) {
                            return true
                        }
                        if (player.inventory.freeSlots() == 0) {
                            player.sendMessage("You don't have enough space in your inventory to make this.")
                            return true
                        }
                        if (player.inventory.remove(*ttb.requiredItems.toTypedArray())) {
                            player.inventory.add(ttb.tabItem)
                            player.skills.addExperience(Skills.MAGIC, ttb.xp / 2.toDouble(), true)
                            //TODO Add correct lectern animation.
                            player.animate(Animation(4460))
                            //////////////////////////////
                            super.setDelay(9)
                            if (ttb == TeleTabButton.VARROCK
                                    && (player.getAttribute("ttb:objectid", 0) == Decoration.MAHOGANY_EAGLE_LECTERN.objectId
                                            || player.getAttribute("ttb:objectid", 0) == Decoration.MAHOGANY_DEMON_LECTERN.objectId)) {
                                player.achievementDiaryManager.finishTask(player, DiaryType.VARROCK, 2, 8)
                            }
                        }
                        return false
                    }
                })
            }
            player.animate(Animation(-1))
            return true
        }
    }

    companion object {
        /**
         * Soft clay
         */
        private val SOFT_CLAY = Item(1761, 1)
    }
}