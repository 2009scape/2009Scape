package core.game.node.entity.player.info.login

import core.JSONUtils
import core.ServerConstants
import core.game.node.entity.combat.CombatSpell
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.IronmanMode
import core.game.node.entity.player.link.SpellBookManager
import core.game.node.entity.player.link.emote.Emotes
import core.game.node.entity.player.link.grave.GraveType
import core.game.node.entity.player.link.music.MusicEntry
import core.game.node.entity.state.EntityState
import core.game.system.SystemLogger
import core.game.world.GameWorld
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import plugin.ame.AntiMacroHandler
import plugin.interaction.item.brawling_gloves.BrawlingGloves
import java.io.FileReader
import java.util.*

/**
 * Class used for parsing JSON player saves.
 * @author Ceikry
 * @param player: The player we are parsing.
 */
class PlayerSaveParser(val player: Player) {
    var parser = JSONParser()
    var reader: FileReader? = FileReader(ServerConstants.PLAYER_SAVE_PATH + player.name + ".json")
    var saveFile: JSONObject? = null
    var read = true

    init {
        reader
                ?: SystemLogger.log("Couldn't find save file for ${player.name}, or save is corrupted.").also { read = false }
        if (read) {
            saveFile = parser.parse(reader) as JSONObject
        }
    }

    fun parse() = GlobalScope.launch {
        if (read) {
            launch {
                parseCore()
                parseSkills()
                parseSettings()
                parseSlayer()
                parseQuests()
                parseAppearance()
                parseGrave()
            }
            launch {
                parseSpellbook()
                parseGrandExchange()
                parseSavedData()
                parseAutocastSpell()
                parseFarming()
                parseConfigs()
                parseMonitor()
            }
            launch {
                parseMusic()
                parseFamiliars()
                parseBarCrawl()
                parseStates()
                parseAntiMacro()
                parseTT()
                parseBankPin()
            }
            launch {
                parseHouse()
                parseIronman()
                parseEmoteManager()
                parseStatistics()
                parseBrawlingGloves()
                parseAttributes()
                parseAchievements()
                parsePouches()
            }
            parsePouches()
        }
    }

    fun parsePouches() {
        if (saveFile!!.containsKey("pouches"))
            player.pouchManager.parse(saveFile!!["pouches"] as JSONArray)
    }

    fun parseAttributes() {
        if (saveFile!!.containsKey("attributes")) {
            val attrData = saveFile!!["attributes"] as JSONArray
            for (a in attrData) {
                val attr = a as JSONObject
                val key = attr["key"].toString()
                val type = attr["type"].toString()
                player.gameAttributes.savedAttributes.add(key)
                player.gameAttributes.attributes.put(key, when (type) {
                    "int" -> attr["value"].toString().toInt()
                    "str" -> attr["value"].toString()
                    "short" -> attr["value"].toString().toShort()
                    "long" -> attr["value"].toString().toLong()
                    "bool" -> attr["value"] as Boolean
                    "byte" -> Base64.getDecoder().decode(attr["value"].toString())[0]
                    else -> null.also { SystemLogger.log("Invalid data type for key: $key") }
                })
            }
        } else {
            player.gameAttributes.parse(player.name + ".xml")
        }
    }

    fun parseBrawlingGloves() {
        if (saveFile!!.containsKey("brawlingGloves")) {
            val bgData: JSONArray = saveFile!!["brawlingGloves"] as JSONArray
            for (bg in bgData) {
                val glove = bg as JSONObject
                player.brawlingGlovesManager.registerGlove(BrawlingGloves.forIndicator(glove.get("gloveId").toString().toInt()).id, glove.get("charges").toString().toInt())
            }
        }
    }

    fun parseStatistics() {
        if (saveFile!!.containsKey("statistics")) {
            val stats: JSONArray = saveFile!!["statistics"] as JSONArray
            for (stat in stats) {
                val s = stat as JSONObject
                val index = (s.get("index") as String).toInt()
                val value = (s.get("value") as String).toInt()
                player.statisticsManager.statistics.get(index).statisticalAmount = value
            }
        }
    }

    fun parseEmoteManager() {
        if (saveFile!!.containsKey("emoteData")) {
            val emoteData: JSONArray = saveFile!!["emoteData"] as JSONArray
            for (emote in emoteData) {
                val e = Emotes.values()[(emote as String).toInt()]
                if (!player.emoteManager.emotes.contains(e)) {
                    player.emoteManager.emotes.add(e)
                }
            }
        }
    }

    fun parseIronman() {
        if (saveFile!!.containsKey("ironManMode")) {
            val ironmanMode = (saveFile!!["ironManMode"] as String).toInt()
            player.ironmanManager.mode = IronmanMode.values()[ironmanMode]
        }
    }

    fun parseAchievements() {
        if (saveFile!!.containsKey("achievementDiaries")) {
            val achvData = saveFile!!["achievementDiaries"] as JSONArray
            player.achievementDiaryManager.parse(achvData)
        } else {
            player.achievementDiaryManager.resetRewards()
        }
    }

    fun parseHouse() {
        val houseData = saveFile!!["houseData"] as JSONObject
        player.houseManager.parse(houseData)
    }

    fun parseBankPin() {
        val bpData = saveFile!!["bankPinManager"] as JSONObject
        player.bankPinManager.parse(bpData)
    }

    fun parseTT() {
        val ttData = saveFile!!["treasureTrails"] as JSONObject
        player.treasureTrailManager.parse(ttData)
    }

    fun parseAntiMacro() {
        if (saveFile!!.containsKey("antiMacroEvent")) {
            val event: JSONObject = saveFile!!["antiMacroEvent"] as JSONObject
            val ame = AntiMacroHandler.EVENTS.get(event.get("eventName") as String)
            ame?.create(player)
        }
    }

    fun parseStates() {
        if (saveFile!!.containsKey("states")) {
            val states: JSONArray = saveFile!!["states"] as JSONArray
            for (state in states) {
                val s = state as JSONObject
                val stateId = (s.get("stateId") as String).toInt()
                val active = s.get("isActive") as Boolean
                if (active)
                    player.stateManager.register(EntityState.values()[stateId], true)
            }
        }
    }

    fun parseBarCrawl() {
        val barCrawlData = saveFile!!["barCrawl"] as JSONObject
        player.barcrawlManager.parse(barCrawlData)
    }

    fun parseFamiliars() {
        val familiarData = saveFile!!["familiarManager"] as JSONObject
        player.familiarManager.parse(familiarData)
    }

    fun parseMusic() {
        val unlockedSongs = saveFile!!["unlockedMusic"] as JSONArray
        for (song in unlockedSongs) {
            val s = (song as String).toInt()
            val entry = MusicEntry.forId(s)
            player.musicPlayer.unlocked.put(entry.index, entry)
        }
    }

    fun parseMonitor() {
        val monitorData = saveFile!!["playerMonitor"] as JSONObject
        if (monitorData.containsKey("duplicationFlag")) {
            val duplicationFlag: Int = (monitorData.get("duplicationFlag") as String).toInt()
            player.monitor.duplicationLog.flag(duplicationFlag)
        }
        if (monitorData.containsKey("macroFlag")) {
            val macroFlag: Int = (monitorData.get("macroFlag") as String).toInt()
            player.monitor.macroFlag = macroFlag
        }
        if (monitorData.containsKey("lastIncreaseFlag")) {
            val lastIncreaseFlag: Long = (monitorData.get("lastIncreaseFlag") as String).toLong()
            player.monitor.duplicationLog.lastIncreaseFlag = lastIncreaseFlag
        }
    }

    fun parseConfigs() {
        val configs = saveFile!!["configs"] as JSONArray
        for (config in configs) {
            val c = config as JSONObject
            val index = (c.get("index") as String).toInt()
            val value = (c.get("value") as String).toInt()
            player.configManager.savedConfigurations[index] = value
        }
    }

    fun parseFarming() {
        val farmingData = saveFile!!["farming"] as JSONObject

        if (farmingData.containsKey("equipment")) {
            val equipmentData: JSONArray? = farmingData.get("equipment") as JSONArray
            player.farmingManager.equipment.container.parse(equipmentData)
        }
        if (farmingData.containsKey("bins")) {
            val compostData: JSONArray? = farmingData.get("bins") as JSONArray
            player.farmingManager.compostManager.parse(compostData)
        }
        if (farmingData.containsKey("wrappers")) {
            val wrapperData: JSONArray? = farmingData.get("wrappers") as JSONArray
            player.farmingManager.parseWrappers(wrapperData)
        }
        if(farmingData.containsKey("seedlings")){
            val seedlingData = farmingData.get("seedlings") as JSONArray
            player.farmingManager.seedlingManager.parse(seedlingData)
        }
        if(farmingData.containsKey("farmingAmuletWrapperID")){
            player.farmingManager.amuletBoundWrapper = player.farmingManager.getPatchWrapper(farmingData.get("farmingAmuletWrapperID").toString().toInt())
        }
    }

    fun parseAutocastSpell() {
        val autocastRaw = saveFile!!["autocastSpell"]
        autocastRaw ?: return
        val autocast = autocastRaw as JSONObject
        val book = (autocast.get("book") as String).toInt()
        val spellId = (autocast.get("spellId") as String).toInt()
        player.properties.autocastSpell = SpellBookManager.SpellBook.values()[book].getSpell(spellId) as CombatSpell
    }

    fun parseSavedData() {
        val activityData = saveFile!!["activityData"] as JSONObject
        val questData = saveFile!!["questData"] as JSONObject
        val globalData = saveFile!!["globalData"] as JSONObject
        player.savedData.activityData.parse(activityData)
        player.savedData.questData.parse(questData)
        player.savedData.globalData.parse(globalData)
    }

    fun parseGrandExchange() {
        val geData: Any? = saveFile!!["grand_exchange"]
        var ge: JSONObject? = null
        if (geData != null) {
            ge = geData as JSONObject; player.playerGrandExchange.parse(ge)
        }

    }

    fun parseSpellbook() {
        val spellbookData = (saveFile!!["spellbook"] as String).toInt()
        player.spellBookManager.setSpellBook(SpellBookManager.SpellBook.forInterface(spellbookData))
    }

    fun parseGrave() {
        saveFile ?: return
        val graveData = (saveFile!!["grave_type"] as String).toInt()
        player.graveManager.type = GraveType.values()[graveData]
    }

    fun parseAppearance() {
        saveFile ?: return
        val appearanceData = saveFile!!["appearance"] as JSONObject
        player.appearance.parse(appearanceData)
    }

    fun parseQuests() {
        saveFile ?: return
        val questData = saveFile!!["quests"] as JSONObject
        player.questRepository.parse(questData)
    }

    fun parseSlayer() {
        saveFile ?: return
        val slayerData = saveFile!!["slayer"] as JSONObject
        player.slayer.parse(slayerData)
    }

    fun parseCore() {
        saveFile ?: return
        val coreData = saveFile!!["core_data"] as JSONObject
        val inventory = coreData["inventory"] as JSONArray
        val bank = coreData["bank"] as JSONArray
        val equipment = coreData["equipment"] as JSONArray
        val location = coreData["location"] as String
        val bankTabData = coreData["bankTabs"]
        if (bankTabData != null) {
            val tabData = bankTabData as JSONArray
            for (i in tabData) {
                i ?: continue
                val tab = i as JSONObject
                val index = tab["index"].toString().toInt()
                val startSlot = tab["startSlot"].toString().toInt()
                player.bank.tabStartSlot[index] = startSlot
            }
        }
        player.inventory.parse(inventory)
        player.bank.parse(bank)
        player.equipment.parse(equipment)
        player.location = JSONUtils.parseLocation(location)
    }

    fun parseSkills() {
        saveFile ?: return
        val skillData = saveFile!!["skills"] as JSONArray
        player.skills.parse(skillData)
        player.skills.experienceGained = saveFile!!["totalEXP"].toString().toDouble()
        player.skills.experienceMutiplier = saveFile!!["exp_multiplier"].toString().toDouble()
        if (GameWorld.settings?.default_xp_rate != 5.0) {
            player.skills.experienceMutiplier = GameWorld.settings?.default_xp_rate!!
        }
        if (saveFile!!.containsKey("milestone")) {
            val milestone: JSONObject = saveFile!!["milestone"] as JSONObject
            player.skills.combatMilestone = (milestone.get("combatMilestone")).toString().toInt()
            player.skills.skillMilestone = (milestone.get("skillMilestone")).toString().toInt()
        }
    }

    fun parseSettings() {
        saveFile ?: return
        val settingsData = saveFile!!["settings"] as JSONObject
        player.settings.parse(settingsData)
    }


}