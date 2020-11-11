package core.game.node.entity.player.info.login

import core.ServerConstants
import core.game.container.Container
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.IronmanMode
import core.game.system.SystemLogger
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import plugin.interaction.item.brawling_gloves.BrawlingGloves
import plugin.skill.Skills
import plugin.skill.summoning.familiar.BurdenBeast
import plugin.skill.summoning.pet.Pet
import java.io.FileWriter
import java.io.IOException
import java.lang.Math.ceil
import java.util.*
import javax.script.ScriptEngineManager


/**
 * Class used for saving the player's data in JSON format.
 * Files are saved in the directory defined in ServerConstants.PLAYER_SAVE_PATH
 * @param player: the player to save for.
 * @author Ceikry
 */
class PlayerSaver (val player: Player){
    private fun populate(): JSONObject = runBlocking{
        val saveFile = JSONObject()
        val a = launch {
            saveCoreData(saveFile)
            saveSkills(saveFile)
            saveSettings(saveFile)
            saveSlayer(saveFile)
            saveQuests(saveFile)
            saveAppearance(saveFile)
            saveSpellbook(saveFile)
        }
        val b = launch {
            saveGraveType(saveFile)
            saveGrandExchangeData(saveFile)
            saveSavedData(saveFile)
            saveAutocast(saveFile)
            saveFarming(saveFile)
            saveConfigs(saveFile)
            savePlayerMonitor(saveFile)
        }
        val c = launch {
            saveMusicPlayer(saveFile)
            saveFamiliarManager(saveFile)
            saveBarCrawl(saveFile)
            saveStateManager(saveFile)
            saveAntiMacroHandler(saveFile)
            saveTreasureTrails(saveFile)
            saveBankPinData(saveFile)
        }
        val d = launch {
            saveHouseData(saveFile)
            saveAchievementData(saveFile)
            saveIronManData(saveFile)
            saveEmoteData(saveFile)
            saveStatManager(saveFile)
            saveBrawlingGloves(saveFile)
            saveAttributes(saveFile)
        }
        a.join()
        b.join()
        c.join()
        d.join()
        savePouches(saveFile)
        saveFile
    }
    fun save() = runBlocking {
        val manager = ScriptEngineManager()
        val scriptEngine = manager.getEngineByName("JavaScript")
        scriptEngine.put("jsonString", populate().toJSONString())
        scriptEngine.eval("result = JSON.stringify(JSON.parse(jsonString), null, 2)")
        val prettyPrintedJson = scriptEngine["result"] as String

        try {
            FileWriter("${ServerConstants.PLAYER_SAVE_PATH}${player.name}.json").use { file ->
                file.write(prettyPrintedJson)
                file.flush()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        player.gameAttributes.dump(player.name + ".xml")
    }

    fun savePouches(root: JSONObject){
        player.pouchManager.save(root)
    }

    fun saveAttributes(root: JSONObject){
        if(player.gameAttributes.savedAttributes.isNotEmpty()){
            val attrs = JSONArray()
            for(key in player.gameAttributes.savedAttributes){
                val value = player.gameAttributes.attributes[key]
                value ?: continue
                val attr = JSONObject()
                val type = when(value){
                    is Int -> "int"
                    is Boolean -> "bool"
                    is Long -> "long"
                    is Short -> "short"
                    is String -> "str"
                    is Byte -> "byte"
                    else -> "null".also { SystemLogger.log("Invalid attribute type for key: $key") }
                }
                attr.put("type",type)
                attr.put("key",key)
                if(value is Byte){
                    val asString = Base64.getEncoder().encodeToString(byteArrayOf(value))
                    attr.put("value",asString)
                } else {
                    attr.put("value", if (value is Boolean) value else value.toString())
                }
                attrs.add(attr)
            }
            root.put("attributes",attrs)
        }
    }

    fun saveBrawlingGloves(root: JSONObject){
        if(player.brawlingGlovesManager.GloveCharges.isNotEmpty()){
            val brawlingGloves = JSONArray()
            player.brawlingGlovesManager.GloveCharges.map {
                val brawlingGlove = JSONObject()
                brawlingGlove.put("gloveId",BrawlingGloves.forId(it.key).indicator.toString())
                brawlingGlove.put("charges",it.value.toString())
                brawlingGloves.add(brawlingGlove)
            }
            root.put("brawlingGloves",brawlingGloves)
        }
    }

    fun saveStatManager(root: JSONObject){
        val statistics = JSONArray()
        var index = 0
        player.statisticsManager.statistics.map {
           val statistic = JSONObject()
            statistic.put("index",index.toString())
            statistic.put("value",it.statisticalAmount.toString())
            statistics.add(statistic)
        }
        root.put("statistics",statistics)
    }

    fun saveEmoteData(root: JSONObject){
        if(player.emoteManager.isSaveRequired) {
            val emoteData = JSONArray()
            player.emoteManager.emotes.map {
                emoteData.add(it.ordinal.toString())
            }
            root.put("emoteData",emoteData)
        }
    }

    fun saveIronManData(root: JSONObject){
        if(player.ironmanManager.mode != IronmanMode.NONE){
            root.put("ironManMode",player.ironmanManager.mode.ordinal.toString())
        }
    }

    fun saveAchievementData(root: JSONObject){
        val achievementData = JSONArray()
        player.achievementDiaryManager.diarys.map {
            val diary = JSONObject()
            val startedLevels = JSONArray()
            it.levelStarted.map {
                startedLevels.add(it)
            }
            diary.put("startedLevels",startedLevels)
            val completedLevels = JSONArray()
            it.taskCompleted.map {
                val level = JSONArray()
                it.map {
                    level.add(it)
                }
                completedLevels.add(level)
            }
            diary.put("completedLevels",completedLevels)
            val rewardedLevels = JSONArray()
            it.levelRewarded.map {
                rewardedLevels.add(it)
            }
            diary.put("rewardedLevels",rewardedLevels)
            val diaryCollector = JSONObject()
            diaryCollector.put(it.type.name, diary)
            achievementData.add(diaryCollector)
        }
        root.put("achievementDiaries",achievementData)
    }

    fun saveHouseData(root: JSONObject){
        val manager = player.houseManager
        val houseData = JSONObject()
        houseData.put("location",manager.location.ordinal.toString())
        houseData.put("style",manager.style.ordinal.toString())
        if(manager.hasServant()){
            val servant = JSONObject()
            servant.put("type",manager.servant.type.ordinal.toString())
            servant.put("uses",manager.servant.uses.toString())
            if(manager.servant.item != null){
                val item = JSONObject()
                item.put("id",manager.servant.item.id.toString())
                item.put("amount",manager.servant.item.amount.toString())
                servant.put("item",item)
            }
            servant.put("greet",manager.servant.isGreet)
            houseData.put("servant",servant)
        }
        val rooms = JSONArray()
        var z = 0
         for(room in player.houseManager.rooms){
            var x = 0
            for(xr in room){
                var y = 0
                for(yr in xr){
                    if(yr != null) {
                        val r = JSONObject()
                        r.put("z", z.toString())
                        r.put("x", x.toString())
                        r.put("y", y.toString())
                        r.put("properties", yr.properties.ordinal.toString())
                        r.put("rotation", yr.rotation.toInteger().toString())
                        val hotspots = JSONArray()
                        var hotspotIndex = 0
                        yr.hotspots.map {
                            if (it.decorationIndex > -1) {
                                val hotspot = JSONObject()
                                hotspot.put("hotspotIndex", hotspotIndex.toString())
                                hotspot.put("decorationIndex", it.decorationIndex.toString())
                                hotspots.add(hotspot)
                            }
                            hotspotIndex++
                        }
                        r.put("hotspots", hotspots)
                        rooms.add(r)
                    }
                    y++
                }
                x++
            }
            z++
        }
        houseData.put("rooms",rooms)
        root.put("houseData",houseData)
    }

    fun saveBankPinData(root: JSONObject){
        val bankPinManager = JSONObject()
        if(player.bankPinManager.hasPin()){
            bankPinManager.put("pin",player.bankPinManager.pin.toString())
        }
        bankPinManager.put("longRecovery",player.bankPinManager.isLongRecovery)
        if(player.bankPinManager.status.ordinal != 0){
            bankPinManager.put("status",player.bankPinManager.status.ordinal.toString())
        }
        if(player.bankPinManager.pendingDelay != -1L && player.bankPinManager.pendingDelay > System.currentTimeMillis()){
            bankPinManager.put("pendingDelay",player.bankPinManager.pendingDelay.toString())
        }
        if(player.bankPinManager.tryDelay > System.currentTimeMillis()){
            bankPinManager.put("tryDelay",player.bankPinManager.tryDelay.toString())
        }
        root.put("bankPinManager",bankPinManager)
    }

    fun saveTreasureTrails(root: JSONObject){
        val treasureTrailManager = JSONObject()
        if(player.treasureTrailManager.hasTrail()){
            val trail = JSONObject()
            trail.put("clueId",player.treasureTrailManager.clueId.toString())
            trail.put("length",player.treasureTrailManager.trailLength.toString())
            trail.put("stage",player.treasureTrailManager.trailStage.toString())
            treasureTrailManager.put("trail",trail)
        }
        val completedClues = JSONArray()
        player.treasureTrailManager.completedClues.map {
            completedClues.add(it.toString())
        }
        treasureTrailManager.put("completedClues",completedClues)
        root.put("treasureTrails",treasureTrailManager)
    }

    fun saveAntiMacroHandler(root: JSONObject){
        if(player.antiMacroHandler.isSaveRequired){
            val antiMacroEvent = JSONObject()
            if(player.antiMacroHandler.hasEvent()){
                antiMacroEvent.put("eventName",player.antiMacroHandler.event.name)
                root.put("antiMacroEvent",antiMacroEvent)
            }
        }
    }

    fun saveStateManager(root: JSONObject){
        if(player.stateManager.isSaveRequired){
            val states = JSONArray()
            player.stateManager.states.map {
                val state = JSONObject()
                state.put("stateId",it.key.ordinal.toString())
                state.put("isActive",it.value.isRunning.toString())
            }
            root.put("states",states)
        }
    }

    fun saveBarCrawl(root: JSONObject){
        val barCrawl = JSONObject()
        barCrawl.put("started",player.barcrawlManager.isStarted)
        val barsVisited = JSONArray()
        player.barcrawlManager.bars.map {
            barsVisited.add(it)
        }
        barCrawl.put("bars",barsVisited)
        root.put("barCrawl",barCrawl)
    }

    fun saveFamiliarManager(root: JSONObject){
        val familiarManager = JSONObject()
        val petDetails = JSONArray()
        player.familiarManager.petDetails.map {
            val detail = JSONObject()
            detail.put("petId",it.key.toString())
            detail.put("hunger",it.value.hunger.toString())
            detail.put("growth",it.value.growth.toString())
            detail.put("stage",it.value.stage.toString())
            petDetails.add(detail)
        }
        familiarManager.put("petDetails",petDetails)
        if(player.familiarManager.hasPet()){
            familiarManager.put("currentPet",(player.familiarManager.familiar as Pet).pet.babyItemId.toString())
        } else if (player.familiarManager.hasFamiliar()){
            val familiar = JSONObject()
            familiar.put("originalId",player.familiarManager.familiar.originalId.toString())
            familiar.put("ticks",player.familiarManager.familiar.ticks.toString())
            familiar.put("specialPoints",player.familiarManager.familiar.specialPoints.toString())
            if(player.familiarManager.familiar.isBurdenBeast && !(player.familiarManager.familiar as BurdenBeast).container.isEmpty){
                val familiarInventory = saveContainer((player.familiarManager.familiar as BurdenBeast).container)
                familiar.put("inventory",familiarInventory)
            }
            familiar.put("lifepoints",player.familiarManager.familiar.skills.lifepoints)
            familiarManager.put("familiar",familiar)

        }
        root.put("familiarManager",familiarManager)
    }

    fun saveMusicPlayer(root: JSONObject){
        val unlockedMusic = JSONArray()
        player.musicPlayer.unlocked.values.map {
            unlockedMusic.add(it.id.toString())
        }
        root.put("unlockedMusic",unlockedMusic)
    }

    fun savePlayerMonitor(root: JSONObject){
        val playerMonitor = JSONObject()
        if(player.monitor.duplicationLog != null && player.monitor.duplicationLog.flag != 0){
            playerMonitor.put("duplicationFlag",player.monitor.duplicationLog.flag.toString())
        }
        if(player.monitor.macroFlag != 0){
            playerMonitor.put("macroFlag",player.monitor.macroFlag.toString())
        }
        if(player.monitor.duplicationLog != null && player.monitor.duplicationLog.isLoggingFlagged){
            playerMonitor.put("lastIncreaseFlag",player.monitor.duplicationLog.lastIncreaseFlag.toString())
        }
        root.put("playerMonitor",playerMonitor)
    }

    fun saveConfigs(root: JSONObject){
        val configs = JSONArray()
        var idx = 0
        player.configManager.savedConfigurations.map {
            if(it != 0){
                val config = JSONObject()
                config.put("index",idx.toString())
                config.put("value",it.toString())
                configs.add(config)
            }
            idx++
        }
        root.put("configs",configs)
    }

    fun saveFarming(root: JSONObject){
        val farming = JSONObject()
        if(player.farmingManager.equipment.container.itemCount() != 0) {
            val equipment = saveContainer(player.farmingManager.equipment.container)
            farming.put("equipment",equipment)
        }
        if(player.farmingManager.compostManager.bins.size != 0){
            val bins = JSONArray()
            player.farmingManager.compostManager.bins.map { compostBin ->
                val bin = JSONObject()
                bin.put("wrapperId",compostBin.wrapperId.toString())
                if(compostBin.timeStamp != 0L){
                    bin.put("timeStamp",compostBin.timeStamp.toString())
                }
                if(compostBin.compostLevel > 0) {
                    bin.put("compostLevel",compostBin.compostLevel.toString())
                }
                if(compostBin.container.itemCount() > 0) {
                    val compostContainer = saveContainer(compostBin.container)
                    bin.put("compostContainer", compostContainer)
                }
                bins.add(bin)
            }
            farming.put("bins",bins)
        }

        if(player.farmingManager.patches.size != 0){
            val wrappers = JSONArray()
            player.farmingManager.patches.map {
                val wrapper = JSONObject()
                wrapper.put("wrapperId",it.wrapperId.toString())
                val cycle = JSONObject()
                cycle.put("compostThreshold",it.cycle.compostThreshold.toString())
                cycle.put("growthTime",it.cycle.growthTime.toString())
                cycle.put("harvestAmount",it.cycle.harvestAmount.toString())
                cycle.put("protection",it.cycle.isProtected)
                wrapper.put("cycle",cycle)
                if(it.node != null){
                    wrapper.put("nodeId",it.patch.getNodePosition(it.node).toString())
                }
                wrappers.add(wrapper)
            }
            farming.put("wrappers",wrappers)
        }
        root.put("farming",farming)
    }

    fun saveAutocast(root: JSONObject){
        player.properties.autocastSpell ?: return
        val spell = JSONObject()
        spell.put("book",player.properties.autocastSpell.book.ordinal.toString())
        spell.put("spellId",player.properties.autocastSpell.spellId.toString())
        root.put("autocastSpell",spell)
    }

    fun saveSavedData(root: JSONObject) {
        saveActivityData(root)
        saveQuestData(root)
        saveGlobalData(root)
    }

    fun saveGlobalData(root: JSONObject){
        val globalData = JSONObject()
        globalData.put("tutorialStage",player.savedData.globalData.tutorialStage.toString())
        globalData.put("homeTeleportDelay",player.savedData.globalData.homeTeleportDelay.toString())
        globalData.put("lumbridgeRope",player.savedData.globalData.hasTiedLumbridgeRope())
        globalData.put("apprentice",player.savedData.globalData.hasSpokenToApprentice())
        globalData.put("assistTime",player.savedData.globalData.assistTime.toString())
        val assistExperience = JSONArray()
        player.savedData.globalData.assistExperience.map {
            assistExperience.add(it.toString())
        }
        globalData.put("assistExperience",assistExperience)
        val strongholdRewards = JSONArray()
        player.savedData.globalData.strongHoldRewards.map {
            strongholdRewards.add(it)
        }
        globalData.put("strongHoldRewards",strongholdRewards)
        globalData.put("chatPing",player.savedData.globalData.chatPing.toString())
        globalData.put("tutorClaim",player.savedData.globalData.tutorClaim.toString())
        globalData.put("luthasTask",player.savedData.globalData.isLuthasTask)
        globalData.put("karamjaBananas",player.savedData.globalData.karamjaBananas.toString())
        globalData.put("silkSteal",player.savedData.globalData.silkSteal.toString())
        globalData.put("zafAmount",player.savedData.globalData.zaffAmount.toString())
        globalData.put("zafTime",player.savedData.globalData.zafTime.toString())
        globalData.put("fritzGlass",player.savedData.globalData.isFritzGlass)
        globalData.put("wydinEmployee",player.savedData.globalData.isWydinEmployee)
        globalData.put("draynorRecording",player.savedData.globalData.isDraynorRecording)
        globalData.put("geTutorial",player.savedData.globalData.isGeTutorial)
        globalData.put("essenceTeleporter",player.savedData.globalData.essenceTeleporter.toString())
        globalData.put("recoilDamage",player.savedData.globalData.recoilDamage.toString())
        globalData.put("doubleExpDelay",player.savedData.globalData.doubleExpDelay.toString())
        globalData.put("joinedMonastery",player.savedData.globalData.isJoinedMonastery)
        val readPlaques = JSONArray()
        player.savedData.globalData.readPlaques.map {
            readPlaques.add(it)
        }
        globalData.put("readPlaques",readPlaques)
        globalData.put("forgingUses",player.savedData.globalData.forgingUses.toString())
        globalData.put("ectoCharges",player.savedData.globalData.ectoCharges.toString())
        globalData.put("braceletClayUses",player.savedData.globalData.braceletClayUses.toString())
        globalData.put("dropDelay",player.savedData.globalData.dropDelay.toString())
        val abyssData = JSONArray()
        player.savedData.globalData.abyssData.map {
            abyssData.add(it)
        }
        globalData.put("abyssData",abyssData)
        val rcDecays = JSONArray()
        player.savedData.globalData.rcDecays.map {
            rcDecays.add(it.toString())
        }
        globalData.put("rcDecays",rcDecays)
        globalData.put("disableDeathScreen",player.savedData.globalData.isDeathScreenDisabled)
        globalData.put("playerTestStage",player.savedData.globalData.playerTestStage.toString())
        globalData.put("charmingDelay",player.savedData.globalData.charmingDelay.toString())
        val travelLogs = JSONArray()
        player.savedData.globalData.travelLogs.map {
            travelLogs.add(it)
        }
        globalData.put("travelLogs",travelLogs)
        val godBooks = JSONArray()
        player.savedData.globalData.godBooks.map {
            godBooks.add(it)
        }
        globalData.put("godBooks",godBooks)
        globalData.put("disableNews",player.savedData.globalData.isDisableNews)
        val godPages = JSONArray()
        player.savedData.globalData.godPages.map {
            godPages.add(it)
        }
        globalData.put("godPages",godPages)
        globalData.put("overChargeDelay",player.savedData.globalData.overChargeDelay.toString())
        val bossCounters = JSONArray()
        player.savedData.globalData.bossCounters.map {
            bossCounters.add(it.toString())
        }
        globalData.put("bossCounters",bossCounters)
        globalData.put("barrowsLoots",player.savedData.globalData.barrowsLoots.toString())
        globalData.put("lootSharePoints",player.savedData.globalData.lootSharePoints.toString())
        globalData.put("lootShareDelay",player.savedData.globalData.lootShareDelay.toString())
        globalData.put("doubleExp",player.savedData.globalData.doubleExp.toString())
        globalData.put("avasDevice",player.savedData.globalData.isAvasDisabled)
        globalData.put("globalTeleporterDelay",player.savedData.globalData.globalTeleporterDelay.toString())
        globalData.put("starSpriteDelay",player.savedData.globalData.starSpriteDelay.toString())
        globalData.put("runReplenishDelay",player.savedData.globalData.runReplenishDelay.toString())
        globalData.put("runReplenishCharges",player.savedData.globalData.runReplenishCharges.toString())
        globalData.put("lowAlchemyCharges",player.savedData.globalData.lowAlchemyCharges.toString())
        globalData.put("lowAlchemyDelay",player.savedData.globalData.lowAlchemyDelay.toString())
        globalData.put("magicSkillCapeDelay",player.savedData.globalData.magicSkillCapeDelay.toString())
        globalData.put("hunterCapeDelay",player.savedData.globalData.hunterCapeDelay.toString())
        globalData.put("hunterCapeCharges",player.savedData.globalData.hunterCapeCharges.toString())
        globalData.put("taskAmount",player.savedData.globalData.taskAmount.toString())
        globalData.put("taskPoints",player.savedData.globalData.taskPoints.toString())
        globalData.put("macroDisabled",player.savedData.globalData.macroDisabled)
        root.put("globalData",globalData)
    }

    fun saveQuestData(root: JSONObject){
        val questData = JSONObject()
        val draynorLever = JSONArray()
        player.savedData.questData.draynorLever.map {
            draynorLever.add(it)
        }
        questData.put("draynorLever",draynorLever)
        val dslayer = JSONArray()
        player.savedData.questData.dragonSlayer.map {
            dslayer.add(it)
        }
        questData.put("dragonSlayer",dslayer)
        questData.put("dragonSlayerPlanks",player.savedData.questData.dragonSlayerPlanks.toString())
        val demonSlayer = JSONArray()
        player.savedData.questData.demonSlayer.map {
            demonSlayer.add(it)
        }
        questData.put("demonSlayer",demonSlayer)
        val cooksAssistant = JSONArray()
        player.savedData.questData.cooksAssistant.map {
            cooksAssistant.add(it)
        }
        questData.put("cooksAssistant",cooksAssistant)
        questData.put("gardenerAttack",player.savedData.questData.isGardenerAttack)
        questData.put("talkedDrezel",player.savedData.questData.isTalkedDrezel)
        val desertTreasureNode = JSONArray()
        player.savedData.questData.desertTreasure.map {
            val item = JSONObject()
            item.put("id",it.id.toString())
            item.put("amount",it.amount.toString())
            desertTreasureNode.add(item)
        }
        questData.put("desertTreasureNode",desertTreasureNode)
        questData.put("witchsExperimentStage",player.savedData.questData.witchsExperimentStage.toString())
        questData.put("witchsExperimentKilled",player.savedData.questData.isWitchsExperimentKilled)
        root.put("questData",questData)
    }

    fun saveActivityData(root: JSONObject){
        val activityData = JSONObject()
        activityData.put("pestPoints",player.savedData.activityData.pestPoints.toString())
        activityData.put("warriorGuildTokens",player.savedData.activityData.warriorGuildTokens.toString())
        activityData.put("bountyHunterRate",player.savedData.activityData.bountyHunterRate.toString())
        activityData.put("bountyRogueRate",player.savedData.activityData.bountyRogueRate.toString())
        activityData.put("barrowKills",player.savedData.activityData.barrowKills.toString())
        val barrowBrothers = JSONArray()
        player.savedData.activityData.barrowBrothers.map {
            barrowBrothers.add(it)
        }
        activityData.put("barrowBrothers",barrowBrothers)
        activityData.put("barrowTunnelIndex",player.savedData.activityData.barrowTunnelIndex.toString())
        activityData.put("kolodionStage",player.savedData.activityData.kolodionStage.toString())
        val godCasts = JSONArray()
        player.savedData.activityData.godCasts.map {
            godCasts.add(it.toString())
        }
        activityData.put("godCasts",godCasts)
        activityData.put("kolodionBoss",player.savedData.activityData.kolodionBoss.toString())
        activityData.put("elnockSupplies",player.savedData.activityData.isElnockSupplies)
        activityData.put("lastBorkBattle",player.savedData.activityData.lastBorkBattle.toString())
        activityData.put("startedMta",player.savedData.activityData.isStartedMta)
        activityData.put("lostCannon",player.savedData.activityData.isLostCannon)
        val pizazzPoints = JSONArray()
        player.savedData.activityData.pizazzPoints.map {
            pizazzPoints.add(it.toString())
        }
        activityData.put("pizazzPoints",pizazzPoints)
        activityData.put("bonesToPeaches",player.savedData.activityData.isBonesToPeaches)
        activityData.put("solvedMazes",player.savedData.activityData.solvedMazes.toString())
        activityData.put("fogRating",player.savedData.activityData.fogRating.toString())
        activityData.put("borkKills",player.savedData.activityData.borkKills.toString())
        activityData.put("hardcoreDeath",player.savedData.activityData.hardcoreDeath)
        activityData.put("topGrabbed",player.savedData.activityData.isTopGrabbed)
        root.put("activityData",activityData)
    }


    fun saveGrandExchangeData(root: JSONObject){
        val grandExchange = JSONObject()
        if(player.grandExchange.hasActiveOffer()){
            val offers = JSONArray()
            player.grandExchange.offers.map {
                if(it != null){
                    val offer = JSONObject()
                    offer.put("offerIndex",it.index.toString())
                    offer.put("offerUID",it.uid.toString())
                    offers.add(offer)
                }
            }
            val history = JSONArray()
            player.grandExchange.history.map {
                if(it != null){
                    val historyEntry = JSONObject()
                    historyEntry.put("isSell",it.isSell)
                    historyEntry.put("itemId",it.itemId.toString())
                    historyEntry.put("totalCoinExchange",it.totalCoinExchange.toString())
                    historyEntry.put("completedAmount",it.completedAmount.toString())
                    history.add(historyEntry)
                }
            }
            grandExchange.put("offers",offers)
            grandExchange.put("history",history)
            root.put("grand_exchange",grandExchange)
        }
    }

    fun saveGraveType(root: JSONObject){
        root.put("grave_type",player.graveManager.type.ordinal.toString())
    }

    fun saveSpellbook(root: JSONObject){
        root.put("spellbook",player.spellBookManager.spellBook.toString())
    }

    fun saveAppearance(root: JSONObject){
        val appearance = JSONObject()
        appearance.put("gender",player.appearance.gender.toByte().toString())
        val appearanceCache = JSONArray()
        player.appearance.appearanceCache.map {
            val bodyPart = JSONObject()
            bodyPart.put("look",it.look.toString())
            bodyPart.put("color",it.color.toString())
            appearanceCache.add(bodyPart)
        }
        appearance.put("appearance_cache", appearanceCache)
        root.put("appearance",appearance)
    }

    fun saveQuests(root: JSONObject){
        val quests = JSONObject()
        quests.put("points",player.questRepository.points.toString())
        val questStages = JSONArray()
        player.questRepository.questList.map {
            val quest = JSONObject()
            quest.put("questId",it.key.toString())
            quest.put("questStage",it.value.toString())
            questStages.add(quest)
        }
        quests.put("questStages",questStages)
        root.put("quests",quests)
    }

    fun saveSlayer(root: JSONObject){
        val slayer = JSONObject()
        val slayerManager = player.slayer
        if(slayerManager.hasTask()){
            slayer.put("master",slayerManager.master.npc.toString())
            slayer.put("taskId",slayerManager.task.ordinal.toString())
            slayer.put("taskAmount",slayerManager.amount.toString())
        }
        slayer.put("points",slayerManager.slayerPoints.toString())
        slayer.put("taskStreak",slayerManager.taskCount.toString())
        val learnedRewards = JSONArray()
        slayerManager.learned.map {
            learnedRewards.add(it)
        }
        slayer.put("learned_rewards",learnedRewards)
        if(slayerManager.removed.isNotEmpty()) {
            val removedTasks = JSONArray()
            slayerManager.removed.map {
                removedTasks.add(it.ordinal.toString())
            }
            slayer.put("removedTasks",removedTasks)
        }
        slayer.put("totalTasks",slayerManager.totalTasks.toString())
        slayer.put("canEarnPoints",slayerManager.isCanEarnPoints)
        root.put("slayer",slayer)
    }

    fun saveSettings(root: JSONObject){
        val settings = JSONObject()
        settings.put("brightness",player.settings.brightness.toString())
        settings.put("musicVolume",player.settings.musicVolume.toString())
        settings.put("soundEffectVolume",player.settings.soundEffectVolume.toString())
        settings.put("areaSoundVolume",player.settings.areaSoundVolume.toString())
        settings.put("publicChatSetting",player.settings.publicChatSetting.toString())
        settings.put("privateChatSetting",player.settings.privateChatSetting.toString())
        settings.put("clanChatSetting",player.settings.clanChatSetting.toString())
        settings.put("tradeSetting",player.settings.tradeSetting.toString())
        settings.put("assistSetting",player.settings.assistSetting.toString())
        settings.put("runEnergy",player.settings.runEnergy.toString())
        settings.put("specialEnergy",player.settings.specialEnergy.toString())
        settings.put("attackStyle",player.settings.attackStyleIndex.toString())
        settings.put("singleMouse",player.settings.isSingleMouseButton)
        settings.put("disableChatEffects",player.settings.isDisableChatEffects)
        settings.put("splitPrivate",player.settings.isSplitPrivateChat)
        settings.put("acceptAid",player.settings.isAcceptAid)
        settings.put("runToggled",player.settings.isRunToggled)
        settings.put("retaliation",player.properties.isRetaliating)
        root.put("settings",settings)
    }

    fun saveSkills(root: JSONObject){
        val skills = JSONArray()
        for(i in 0 until 24){
            val skill = JSONObject()
            skill.put("id",i.toString())
            skill.put("static",player.skills.staticLevels[i].toString())
            if(i == Skills.HITPOINTS){
                skill.put("dynamic",player.skills.lifepoints.toString())
            } else if (i == Skills.PRAYER){
                skill.put("dynamic",ceil(player.skills.prayerPoints).toInt().toString())
            } else {
                skill.put("dynamic",player.skills.dynamicLevels[i].toString())
            }
            skill.put("experience",player.skills.getExperience(i).toString())
            skills.add(skill)
        }
        root.put("skills",skills)
        root.put("totalEXP",player.skills.experienceGained.toString())
        root.put("exp_multiplier",player.skills.experienceMutiplier.toString())
        if(player.skills.combatMilestone > 0 || player.skills.skillMilestone > 0){
            val milestone = JSONObject()
            milestone.put("combatMilestone",player.skills.combatMilestone.toString())
            milestone.put("skillMilestone",player.skills.skillMilestone.toString())
            root.put("milestone",milestone)
        }
    }

    fun saveContainer(container: Container): JSONArray {
        val json = JSONArray()
        container.toArray().map{
            if (it != null) {
                val item = JSONObject()
                item.put("slot", it.slot.toString())
                item.put("id", it.id.toString())
                item.put("amount", it.amount.toString())
                item.put("charge", it.charge.toString())
                json.add(item)
            }
        }
        return json
    }

    fun saveCoreData(root: JSONObject){
        val coreData = JSONObject()
        val inventory = saveContainer(player.inventory)
        coreData.put("inventory",inventory)

        val bank = saveContainer(player.bank)
        coreData.put("bank",bank)

        val bankTabs = JSONArray()
        for(i in player.bank.tabStartSlot.indices){
            val tab = JSONObject()
            tab.put("index",i.toString())
            tab.put("startSlot",player.bank.tabStartSlot[i].toString())
            bankTabs.add(tab)
        }
        coreData.put("bankTabs",bankTabs)

        val equipment = saveContainer(player.equipment)
        coreData.put("equipment",equipment)

        val loctemp = player.location
        val locStr = "${loctemp.x},${loctemp.y},${loctemp.z}"
        coreData.put("location",locStr)

        root.put("core_data",coreData)
    }
}
