package plugin.ai.general.scriptrepository

import core.game.interaction.DestinationFlag
import core.game.interaction.MovementPulse
import core.game.node.Node
import core.game.node.`object`.GameObject
import core.game.node.entity.Entity
import core.game.node.entity.combat.CombatStyle
import core.game.node.entity.combat.CombatSwingHandler
import core.game.node.entity.combat.InteractionType
import core.game.node.entity.combat.handlers.MagicSwingHandler
import core.game.node.entity.combat.handlers.MeleeSwingHandler
import core.game.node.entity.combat.handlers.RangeSwingHandler
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.game.system.SystemLogger
import core.game.system.task.Pulse
import core.game.world.map.Location
import core.game.world.map.RegionManager
import core.tools.ItemNames
import core.tools.RandomFunction
import plugin.ai.AIPlayer
import plugin.ai.AIRepository
import plugin.ai.general.ScriptAPI
import plugin.ai.pvmbots.CombatBotAssembler
import plugin.skill.Skills
import kotlin.random.Random


/**
 * A bot script for Adventurers who explore the world!
 * @param counter used in the bots random idling function.
 * @param random is used to generate random number.
 * @param city determines the home city of the bot.
 * @param freshspawn determines if the bot has just been spawned.
 * @param random_city is the list of cities that can be randomly chosen as the home city.
 * @param tree_list is the list of trees that a bot can start cutting randomly.
 * @author Sir Kermit
 * @author Ceikry
 */
class Adventurer (val style: CombatStyle): Script() {

    val yanille: Location = Location.create(2615,3104,0)
    val ardougne: Location = Location.create(2662,3304,0)
    val seers: Location = Location.create(2726,3485,0)
    val edgeville: Location = Location.create(3088, 3486, 0)
    val ge: Location = Location.create(3169, 3486, 0)
    val ge2: Location = Location.create(3160, 3494, 0)
    val catherby: Location = Location.create(2809,3435,0)
    val falador: Location = Location.create(2965,3380,0)
    val varrock: Location = Location.create(3213,3428,0)
    val draynor: Location = Location.create(3083,3244,0)
    val rimmington: Location = Location.create(2956,3220,0)
    val lumbridge: Location = Location.create(3222,3219,0)
    var city: Location = lumbridge

    private val dialogue = listOf("Hey!",
            "Hello",
            "What are you doing?",
            "Fishing level?",
            "How do I make gp",
            "Wyd?",
            "How do i get to Varrock?",
            "How do i get to Camelot?",
            "How do i get to Taverly?",
            "How do i get to Ardougne?",
            "How do i get to Yanille?",
            "How do i get to Catherby?",
            "Gotta go srry.",
            " /hop world 1 got a noob here!",
            "woot",
            "heyy :)",
            "gtg srry",
            "I won't answer your questions",
            "Stop asking questions",
            "Roflmao",
            "Can you stop following me?",
            "I swear you're following me.",
            "Your gear could really use an upgrade lol",
            "Quack!",
            "Sit.",
            "Doubling gold trade me",
            "Reeee!",
            "Know any house parties?",
            "Where am i??",
            "Is there Nightmarezone?",
            "Nice Armor!",
            "Nice Weapon!",
            "Venezuela #1",
            "2009Scape and chill?",
            "Evilwaffles is my girlfriend",
            "Buying gf",
            "Bank sale pm me for info",
            "#1 trusted dicing site",
            "Scary movie is a great movie",
            "Cod Cold War sucks",
            "Idek what game this is",
            "Can you teach me how to dougie?",
            "Check this out baby girl cat daddy",
            "Fuckin sit.",
            "Error: Botting client broken tell Evilwaffles.",
            "There are no mods on to help you",
            "Report me faggot, you won't",
            "Yes, I'm botting. And?",
            "ERROR: BOTSCRIPT 404. REPORT TO BOT OWNER (Evilwaffles)",
            "flash2:wave: FUCK",
            "I love 2009Scape!",
            "Ja Ja Ja Ja",
            "This is fun!",
            "Ironman or you're a scrub.",
            "Who even trains hunter?",
            "Where do i get rune armor?",
            "How do i get to the ge?",
            "Don't come to falador tomorrow...",
            "Woah!",
            " /where are you??",
            "How did i even get here",
            "Why don't they add warding?",
            "Where do i start dragon slayer?",
            "I love this server!",
            "How do i change my xp rate?",
            "What quests are available?",
            "Are you a real player?",
            "Are you real?",
            "Are you a bot?",
            "I'm real lol",
            "Why don't you respond to me?",
            "Why can't i talk in clan chat?",
            "I love Kermit",
            "Add me as a friend :)",
            "I'm a player lol",
            "I'm a player lol",
            "I'm a player lol",
            "Hey :)",
            "HEY",
            "Hey!!!!",
            "Lol wyd?",
            "More like Rusty ass skills lmao",
            "Trade me",
            "LOL",
            "How do I get to lumbrich",
            "bruh",
            "poggers",
            "shitpost",
            "I wish i could find an RS Gf",
            "Where do you find runite ore?",
            "Where is the best coal location?",
            "Where do i find dragon weapons?",
            "Can i have some free stuff?",
            "Wyd here?",
            "Didn't know anyone was on rn",
            "I see you all the time",
            "How many times have i seen you",
            "I see you a lot",
            "Do you train summoning?",
            "Where did you level hunter?",
            "I wish they would add global pvp",
            "Meet me in the wilderness",
            "Why?",
            "Praise our glorious frog overlord!",
            "Kermit is bae tbh",
            "Kermit is my god.",
            " /Yeah I think this is a bot",
            "100% sure this is a bot",
            "Oh no, not you again.",
            "Same as you",
            "Me too",
            "I knew you were a bot lol",
            "I'm not a bot",
            "Nah I'm a real person",
            "Bruh are you even a real person lol?",
            "e",
            "Hellooooo",
            "wc lvl?",
            "fletching level?",
            "firemaking level?",
            "Have you seen the dude in the ge?",
            "Wonderful weather today",
            "Lowkey drunk af rn",
            "I am so tired",
            "Wassup",
            "follow me!",
            "Server goes brrrr",
            "bruh i am not a bot",
            "I think he is a bot",
            "Are you a bot?",
            "insert spiderman meme here",
            "pot calling the kettle black etc",
            "ooh, a piece of candy",
            "I love woodcutting",
            "I'm going to go level up later",
            "I love mining shoot stars",
            " /this dude looks dumb",
            "AAAAAAAAAAAAAAAAAAAAAAAAHHHHHH!!!",
            "como estas",
            "Summer = best community manaer",
            "so how about that ceikry guy",
            "so how about that kermit dude",
            "woah is an abusive mod",
            "I heard Woah and Ceikry are dating now",
            "House party in Yanille!",
            "Can i have some gp?",
            "Where do i find partyhats?",
            "Why do mobs drop boxes?",
            "What exp rate do you play on?",
            "Have you met Summer?",
            "Hey",
            "Hey",
            "Hey",
            "Hey",
            "Hey",
            "Wyd?",
            "Wyd?",
            "Wyd?",
            "Wyd?",
            "Have you met Kermit?",
            "Have you met Ceikry?",
            "Have you met Woah?",
            "Wanna chill at my poh?",
            "Whats the best place to train attack?",
            "Good Waffles > Evil Waffles",
            "Ladies man? More like lame man LOL",
            "NobodyCLP has big feet",
            "Chicken tendies for dindin",
            "Spicey Chicken tendies is litty as a mf titty",
            "red bracket stinky",
            "ra ra rasputin",
            "lover of the russian queen",
            "When's the next update coming out?",
            "How many players are maxed?",
            "I don't use discord",
            "I don't use the CC",
            "Why should i use discord?",
            "2009Scape is life",
            "brb gotta make dinner",
            "I need to go to the GE",
            "Do you have a ge tele i can have?",
            "lol shut up",
            "nah",
            "yes",
            "no",
            "why",
            "why",
            "why",
            "why",
            "Why does it not show your messages in the chatbox?",
            "Why do you not show up in chat?",
            "Why do you not show up in chat?",
            "Why do you not show up in chat?",
            "When did you start playing?",
            "When did you start on this server?",
            "When did you first get here?",
            "russia's greatest love machine")

    var citygroupA = listOf(falador,varrock,draynor,rimmington,lumbridge,ge,ge2,edgeville)
    var citygroupB = listOf(yanille,ardougne,seers,catherby)

    var handler1: CombatSwingHandler? = null

    var freshspawn = true
    var new_city = false
    var sold = false

    var counter: Int = 0
    var random: Int = (5..30).random()

    val type = when(style){
        CombatStyle.MELEE -> CombatBotAssembler.Type.MELEE
        CombatStyle.MAGIC -> CombatBotAssembler.Type.MAGE
        CombatStyle.RANGE -> CombatBotAssembler.Type.RANGE
    }

    init {
        handler1 = when(style){
            CombatStyle.MELEE -> MeleeSwingerAdv(this)
            CombatStyle.MAGIC -> MageSwingerAdv(this)
            CombatStyle.RANGE -> RangeSwingerAdv(this)
        }
        skills[Skills.AGILITY] = 99
        inventory.add(Item(1357))//Addy Axe
        skills[Skills.WOODCUTTING] = 50
        inventory.add(Item(590))//Tinderbox
        skills[Skills.FISHING] = 50
        inventory.add(Item(1271))//Addy Pickaxe
        skills[Skills.MINING] = 90
    }

    private var state = State.START

    fun getRandomCity(): Location{
        return listOf(yanille,ardougne,seers,catherby,falador,varrock,
                draynor,rimmington,lumbridge,ge,ge2,edgeville).random()
    }

    fun immerse() {
        val items = AIRepository.groundItems[bot]
        city = getRandomCity()
        if (Random.nextBoolean()) {
            if (items.isNullOrEmpty()) {
                scriptAPI.attackNpcsInRadius(bot, 7)
                state = State.LOOT_DELAY
            }
            if (bot.inventory.isFull) {
                scriptAPI.teleport(city)
                state = State.FIND_BANK
            }

        } else {
            if (bot.inventory.isFull){
                scriptAPI.teleport(city)
                state = State.FIND_BANK
            } else {
                val oak = scriptAPI.getNearestNode("Oak",true)
                val tree = scriptAPI.getNearestNode("Tree",true)
                val willow = scriptAPI.getNearestNode("Willow",true)
                val rock = scriptAPI.getNearestNode("Rocks",true)
                val star = scriptAPI.getNearestNode("Crashed star",true)
                val node = listOf(oak,tree,willow,rock,star).random()
                try {
                    node?.interaction?.handle(bot, node.interaction[0])
                } catch (e: Exception){}
            }
        }
    }

    override fun tick() {
        when(state){

            State.LOOT_DELAY -> {
                bot.pulseManager.run(object: Pulse(){
                    var counter = 0
                    override fun pulse(): Boolean {
                        when(counter++){
                            3 -> return true.also {state = State.LOOT}
                        }
                        return false
                    }
                })
            }

            State.LOOT -> {
                val items = AIRepository.groundItems[bot]
                if(items?.isNotEmpty() == true && !bot.inventory.isFull){
                    items.forEach{
                        scriptAPI.takeNearestGroundItem(it.id)
                    }
                } else {
                    state = State.EXPLORE
                }
            }

            State.START -> {
                if (counter++ == random && freshspawn) {
                    freshspawn = false
                    scriptAPI.randomWalkTo(lumbridge, 20)
                    counter = 0
                    return
                } else {
                    state = State.TELEPORTING
                    city = getRandomCity()
                }
            }

            State.TELEPORTING -> {
                if(bot.location != city) {
                    scriptAPI.teleport(city)
                } else {
                    scriptAPI.randomWalkTo(city, 5)
                    state = State.EXPLORE
                }
            }

            State.EXPLORE -> {
                var random = (60..300).random()
                if (RandomFunction.random(1000) <= 10){
                    val nearbyPlayers = RegionManager.getLocalPlayers(bot)
                    if(nearbyPlayers.isNotEmpty()){
                        bot.sendChat(dialogue.random())
                    }
                }

                if (RandomFunction.random(1000)  <= 150) {
                    var roamDistance = if(city == ge || city == ge2) 10 else 200
                    if((city == ge || city == ge2) && RandomFunction.random(100) < 85){
                        return
                    }
                    scriptAPI.randomWalkTo(city, roamDistance)
                    return
                }

                if(RandomFunction.random(1000) <= 40){
                    immerse()
                    return
                }

                if(counter++ >= random && RandomFunction.random(1000) % 33 == 0){
                    city = getRandomCity()
                    if(RandomFunction.random(100) % 2 == 0) {
                        state = State.TELEPORTING
                    } else {
                        if(citygroupA.contains(city)){
                            city = citygroupA.random()
                        } else {
                            city = citygroupB.random()
                        }
                        state = State.FIND_CITY
                    }
                    counter = 0
                    return
                }

                if(counter++ >= random && RandomFunction.random(1000) < 100){
                    state = State.FIND_BANK
                    counter = 0
                    return
                }
            }

            State.GE -> {
                var random = (15..120).random()
                if((city == ge || city == ge2) && !sold) {
                    if (counter++ == random) {
                        sold = true
                        counter = 0
                        scriptAPI.sellAllOnGe()
                    } else if (sold && counter++ == random) {
                        counter = 0
                        state = State.TELEPORTING
                    }
                }else if ((counter++ == random) && sold){
                    counter = 0
                    state = State.TELEPORTING
                }
            }

            State.FIND_BANK -> {
                val ge: GameObject? = scriptAPI.getNearestGameObject(bot.location, 28089)
                val bank: GameObject? = scriptAPI.getNearestGameObject(bot.location, 2213)
                if (bank == null || ge == null) state = State.EXPLORE
                class BankingPulse : MovementPulse(bot, bank, DestinationFlag.OBJECT) {
                    override fun pulse(): Boolean {
                        bot.faceLocation(bank?.location)
                        state = State.IDLE_BANKS
                        return true
                    }
                }
                class GEPulse : MovementPulse(bot, ge, DestinationFlag.OBJECT) {
                    override fun pulse(): Boolean {
                        bot.faceLocation(ge?.location)
                        state = State.GE
                        return true
                    }
                }
                if (bank != null) {
                    bot.pulseManager.run(BankingPulse())
                }
                if (ge != null){
                    sold = false
                    bot.pulseManager.run(GEPulse())
                }
            }

            State.IDLE_BANKS -> {
                if (RandomFunction.random(1000) < 100){
                    for(item in bot.inventory.toArray()){
                        item ?: continue
                        when(item.id){
                            1357, 590, 1271, 995 -> continue
                        }
                        bot.bank.add(item)
                        bot.inventory.remove(item)
                    }
                    state = State.EXPLORE
                    counter = 0
                }
            }

            State.FIND_CITY -> {
                var random = (600..900).random()
                if(counter++ >= random){
                    scriptAPI.teleport(city)
                    state = State.EXPLORE
                    counter = 0
                }
                if(bot.location.equals(city)){
                    state = State.EXPLORE
                } else {
                    scriptAPI.randomWalkTo(city,5)
                }
            }

            State.IDLE_CITY -> {
                var random = (120..300).random()
                if (counter++ == random && RandomFunction.random(1000) % 33 == 0){
                    state = State.EXPLORE
                    counter = 0
                }
            }

            else -> State.START
        }

    }

    enum class State{
        START,
        EXPLORE,
        FIND_BANK,
        IDLE_BANKS,
        FIND_CITY,
        IDLE_CITY,
        IDLE,
        GE,
        TELEPORTING,
        LOOT,
        LOOT_DELAY
    }

    class RespawnPulse(val script: Script) : Pulse(20) {
        override fun pulse(): Boolean {
            AIPlayer.deregister(script.bot.uid)
            script.bot.clear()
            script.newInstance().init()
            return true
        }
    }

    override fun newInstance(): Script {
        val script = Adventurer(style)
        script.state = State.START

        val tier = CombatBotAssembler.Tier.HIGH
        if(type == CombatBotAssembler.Type.RANGE)
            script.bot = CombatBotAssembler().RangeAdventurer(tier,bot.startLocation)
        else
            script.bot = CombatBotAssembler().MeleeAdventurer(tier, bot.startLocation)
        return script
    }

    internal class MeleeSwingerAdv(val script: Adventurer) : MeleeSwingHandler() {
        override fun canSwing(entity: Entity, victim: Entity): InteractionType? {
            if(script.state == State.IDLE_BANKS) {script.bot.pulseManager.current.stop()}
            if(script.state == State.FIND_BANK) {script.bot.pulseManager.current.stop()}
            if(script.state == State.IDLE_CITY) {script.bot.pulseManager.current.stop()}
            if(script.state == State.FIND_CITY) {script.bot.pulseManager.current.stop()}
            if(victim is Player) {script.state = State.EXPLORE; script.bot.pulseManager.current.stop()}
            return super.canSwing(entity, victim)
        }
    }

    internal class MageSwingerAdv(val script: Adventurer) : MagicSwingHandler() {
        override fun canSwing(entity: Entity, victim: Entity): InteractionType? {
            if(script.state == State.IDLE_BANKS) {script.bot.pulseManager.current.stop()}
            if(script.state == State.FIND_BANK) {script.bot.pulseManager.current.stop()}
            if(script.state == State.IDLE_CITY) {script.bot.pulseManager.current.stop()}
            if(script.state == State.FIND_CITY) {script.bot.pulseManager.current.stop()}
            if(victim is Player) {script.state = State.EXPLORE; script.bot.pulseManager.current.stop()}
            return super.canSwing(entity, victim)
        }
    }

    internal class RangeSwingerAdv(val script: Adventurer) : RangeSwingHandler() {
        override fun canSwing(entity: Entity, victim: Entity): InteractionType? {
            if(script.state == State.IDLE_BANKS) {script.bot.pulseManager.current.stop()}
            if(script.state == State.FIND_BANK) {script.bot.pulseManager.current.stop()}
            if(script.state == State.IDLE_CITY) {script.bot.pulseManager.current.stop()}
            if(script.state == State.FIND_CITY) {script.bot.pulseManager.current.stop()}
            return super.canSwing(entity, victim)
        }
    }
}