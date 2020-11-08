package core.game.world

import core.game.node.entity.combat.CombatStyle
import core.game.world.map.Location
import core.game.world.map.zone.ZoneBorders
import plugin.ai.general.GeneralBotCreator
import plugin.ai.general.scriptrepository.*
import plugin.ai.pvmbots.CombatBotAssembler
import plugin.ai.skillingbot.SkillingBotAssembler
import java.util.concurrent.Executors

object ImmerseWorld {
    var assembler = CombatBotAssembler()
    var skillingBotAssembler = SkillingBotAssembler()
    @JvmStatic
    fun init() {
        Executors.newSingleThreadExecutor().execute {
            immerseSeersAndCatherby()
            immerseLumbridgeDraynor()
            immerseVarrock()
            immerseWilderness()
            immerseFalador()
            immerseFishingGuild()
           // immerseSlayer()
        }
    }


    fun immerseFishingGuild(){
        GeneralBotCreator(Location.create(2595, 3414, 0), SharkCatcher())
        GeneralBotCreator(Location.create(2595, 3414, 0), SharkCatcher())
        GeneralBotCreator(Location.create(2595, 3414, 0), SharkCatcher())
    }

    fun immerseSeersAndCatherby(){
        GeneralBotCreator(SeersMagicTrees(), skillingBotAssembler.produce(SkillingBotAssembler.Wealth.AVERAGE, Location.create(2702, 3397, 0)))
        GeneralBotCreator(SeersFlax(), skillingBotAssembler.produce(SkillingBotAssembler.Wealth.POOR, Location.create(2738, 3444, 0)))
        GeneralBotCreator(FletchingBankstander(), skillingBotAssembler.produce(SkillingBotAssembler.Wealth.AVERAGE, Location.create(2722, 3493, 0)))
        GeneralBotCreator(GlassBlowingBankstander(), skillingBotAssembler.produce(SkillingBotAssembler.Wealth.AVERAGE, Location.create(2807, 3441, 0)))
        GeneralBotCreator(Location.create(2805, 3435, 0), LobsterCatcher())
    }

    fun immerseLumbridgeDraynor(){
        GeneralBotCreator(CowKiller(), assembler.produce(CombatBotAssembler.Type.RANGE, CombatBotAssembler.Tier.MED, Location.create(3261, 3269, 0)))
        GeneralBotCreator(CowKiller(), assembler.produce(CombatBotAssembler.Type.MELEE, CombatBotAssembler.Tier.LOW, Location.create(3261, 3269, 0)))
        GeneralBotCreator(CowKiller(), assembler.produce(CombatBotAssembler.Type.MELEE, CombatBotAssembler.Tier.MED, Location.create(3257, 3267, 0)))
        GeneralBotCreator(ManThiever(), skillingBotAssembler.produce(SkillingBotAssembler.Wealth.POOR,Location.create(3235, 3213, 0)))
        GeneralBotCreator(FarmerThiever(), skillingBotAssembler.produce(SkillingBotAssembler.Wealth.POOR,Location.create(3094,3243,0)))
        GeneralBotCreator(FarmerThiever(), skillingBotAssembler.produce(SkillingBotAssembler.Wealth.POOR,Location.create(3094,3243,0)))
        GeneralBotCreator(DraynorWillows(), skillingBotAssembler.produce(SkillingBotAssembler.Wealth.values().random(),Location.create(3094, 3245, 0)))
        GeneralBotCreator(DraynorWillows(), skillingBotAssembler.produce(SkillingBotAssembler.Wealth.values().random(),Location.create(3094, 3245, 0)))
        GeneralBotCreator(DraynorWillows(), skillingBotAssembler.produce(SkillingBotAssembler.Wealth.values().random(),Location.create(3094, 3245, 0)))
        GeneralBotCreator(DraynorFisher(), skillingBotAssembler.produce(SkillingBotAssembler.Wealth.POOR,Location.create(3095, 3246, 0)))
        GeneralBotCreator(DraynorFisher(), skillingBotAssembler.produce(SkillingBotAssembler.Wealth.POOR,Location.create(3095, 3246, 0)))
        GeneralBotCreator(DraynorFisher(), skillingBotAssembler.produce(SkillingBotAssembler.Wealth.POOR,Location.create(3095, 3246, 0)))
    }

    fun immerseVarrock(){
        val WestBankIdlerBorders = ZoneBorders(3184, 3435,3187, 3444)
        GeneralBotCreator(GlassBlowingBankstander(), skillingBotAssembler.produce(SkillingBotAssembler.Wealth.RICH,Location.create(3189, 3435, 0)))
        GeneralBotCreator(FletchingBankstander(), skillingBotAssembler.produce(SkillingBotAssembler.Wealth.AVERAGE,Location.create(3189, 3439, 0)))
        GeneralBotCreator(Idler(), skillingBotAssembler.produce(SkillingBotAssembler.Wealth.RICH,WestBankIdlerBorders.randomLoc))
        GeneralBotCreator(GlassBlowingBankstander(), skillingBotAssembler.produce(SkillingBotAssembler.Wealth.POOR,Location.create(3256, 3420, 0)))
        GeneralBotCreator(VarrockEssenceMiner(), skillingBotAssembler.produce(SkillingBotAssembler.Wealth.POOR,Location.create(3253, 3420, 0)))
        GeneralBotCreator(VarrockSmither(), skillingBotAssembler.produce(SkillingBotAssembler.Wealth.RICH,Location.create(3189, 3436, 0)))
        GeneralBotCreator(NonBankingMiner(), skillingBotAssembler.produce(SkillingBotAssembler.Wealth.POOR,Location.create(3182, 3374, 0)))
    }

    fun immerseWilderness(){
        GeneralBotCreator(GreenDragonKiller(CombatStyle.MELEE), assembler.assembleMeleeDragonBot(CombatBotAssembler.Tier.HIGH,Location.create(2979, 3603, 0)))
        GeneralBotCreator(GreenDragonKiller(CombatStyle.MELEE), assembler.assembleMeleeDragonBot(CombatBotAssembler.Tier.HIGH,Location.create(2979, 3603, 0)))
        GeneralBotCreator(GreenDragonKiller(CombatStyle.MELEE), assembler.assembleMeleeDragonBot(CombatBotAssembler.Tier.MED,Location.create(2979, 3603, 0)))
        GeneralBotCreator(GreenDragonKiller(CombatStyle.MELEE), assembler.assembleMeleeDragonBot(CombatBotAssembler.Tier.MED,Location.create(2979, 3603, 0)))
        GeneralBotCreator(GreenDragonKiller(CombatStyle.RANGE), assembler.assembleRangedBot(CombatBotAssembler.Tier.HIGH,Location.create(2979, 3603, 0)))
    }

    fun immerseFalador(){
        GeneralBotCreator(CoalMiner(), skillingBotAssembler.produce(SkillingBotAssembler.Wealth.POOR,Location.create(3037, 9737, 0)))
    }

    fun immerseSlayer(){
        GeneralBotCreator(GenericSlayerBot(), assembler.produce(CombatBotAssembler.Type.MELEE,CombatBotAssembler.Tier.HIGH,Location.create(2673, 3635, 0)))
    }
}