package plugin.ai.general.scriptrepository

import core.game.interaction.DestinationFlag
import core.game.interaction.MovementPulse
import core.game.node.Node
import core.game.node.`object`.GameObject
import core.game.node.entity.combat.CombatStyle
import core.game.node.entity.combat.CombatSwingHandler
import core.game.node.entity.skill.Skills
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Location
import core.game.world.map.RegionManager
import core.game.world.map.zone.ZoneBorders
import core.game.world.repository.Repository.sendNews
import core.game.world.update.flag.*
import core.game.world.update.flag.context.Animation
import core.game.world.update.flag.context.ChatMessage
import core.game.world.update.flag.context.Graphics
import core.game.world.update.flag.player.ChatFlag
import core.tools.RandomFunction
import plugin.ai.AIPlayer
import plugin.ai.AIRepository
import plugin.ai.pvmbots.CombatBotAssembler
import plugin.ai.pvp.PVPAIPActions
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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
class Adventurer(val style: CombatStyle): Script() {

    val yanille: Location = Location.create(2615, 3104, 0)
    val ardougne: Location = Location.create(2662, 3304, 0)
    val seers: Location = Location.create(2726, 3485, 0)
    val edgeville: Location = Location.create(3088, 3486, 0)
    val ge: Location = Location.create(3168, 3487, 0)
    val ge2: Location = Location.create(3161, 3493, 0)
    val catherby: Location = Location.create(2809, 3435, 0)
    val falador: Location = Location.create(2965, 3380, 0)
    val varrock: Location = Location.create(3213, 3428, 0)
    val draynor: Location = Location.create(3080, 3250, 0)
    val rimmington: Location = Location.create(2956, 3220, 0)
    val lumbridge: Location = Location.create(3222, 3219, 0)
    var city: Location = lumbridge

    fun dialogue() {
        val player = RegionManager.getLocalPlayers(bot).random()
        val real = if (!player.isArtificial) player else player

        val dialogue = listOf(
                "Hey!",
                "Hello ${real.username}",
                "What are you doing ${real.username}?",
                "Fishing level ${real.username}?",
                "How do I make gp ${real.username}",
                "Wyd ${real.username}?",
                "How do i get to Varrock ${real.username}?",
                "How do i get to Camelot ${real.username}?",
                "How do i get to Taverly ${real.username}?",
                "How do i get to Ardougne ${real.username}?",
                "How do i get to Yanille ${real.username}?",
                "How do i get to Catherby ${real.username}?",
                "Gotta go srry.",
                " /hop world 1 got a noob here!",
                "woot",
                "How's your day been ${real.username}?",
                "heyy ${real.username} :)",
                "gtg srry",
                "I won't answer your questions ${real.username}",
                "Stop asking questions ${real.username}",
                "Roflmao",
                "Can you stop following me ${real.username}?",
                "I swear ${real.username} is following me.",
                "${real.username}'s gear could really use an upgrade lol",
                "Quack!",
                "Sit.",
                "Doubling gold trade me ${real.username}",
                "Reeee!",
                "Know any house parties ${real.username}?",
                "Where am i ${real.username}??",
                "Is there Nightmarezone ${real.username}?",
                "Nice Armor ${real.username}!",
                "Nice Weapon ${real.username}!",
                "Venezuela #1",
                "2009Scape and chill ${real.username}?",
                "${real.username} is my girlfriend",
                "Buying gf",
                "Bank sale pm me for info",
                "#1 trusted dicing site",
                "Scary movie is a great movie",
                "Cod Cold War sucks",
                "Idek what game this is",
                "Can you teach me how to dougie?",
                "Check this out baby girl cat daddy",
                "Fuckin sit ${real.username}.",
                "Error: Botting client broken tell Evilwaffles.",
                "${real.username} bots all the time",
                "report ${real.username}",
                "apparently ${real.username} bots",
                "${real.username} hates kermit",
                "There are no mods on to help you",
                "Report me, you won't",
                "Yes, I'm botting. And?",
                "ERROR: BOTSCRIPT NPE. REPORT TO BOT OWNER (Evilwaffles)",
                "flash2:wave: FUCK",
                "I love 2009Scape!",
                "Ja Ja Ja Ja",
                "This is fun!",
                "Ironman or you're a scrub ${real.username}.",
                "Who even trains hunter ${real.username}?",
                "Where do i get rune armor ${real.username}?",
                "How do i get to the ge ${real.username}?",
                "Don't come to falador tomorrow ${real.username}...",
                "Woah!",
                " /where are you??",
                "How did i even get here ${real.username}",
                "Why don't they add warding ${real.username}?",
                "Where do i start dragon slayer ${real.username}?",
                "I love this server!",
                "How do i change my xp rate ${real.username}?",
                "What quests are available ${real.username}?",
                "Are you a real player ${real.username}?",
                "Are you real ${real.username}?",
                "Are you a bot ${real.username}?",
                "I'm real lol",
                "Why don't you respond to me ${real.username}?",
                "Why can't i talk in clan chat ${real.username}?",
                "I love Kermit",
                "Add me as a friend ${real.username} :)",
                "I'm a player lol",
                "I'm a player lol",
                "I'm a player lol",
                "Hey ${real.username} :)",
                "HEY ${real.username}",
                "Hey ${real.username}!!!!",
                "Lol wyd?",
                "Patch Notes is more of an ultimate tin man",
                "More like Rusty ass skills lmao",
                "Trade me ${real.username}",
                "LOL",
                "How do I get to lumbrich",
                "bruh",
                "poggers",
                "shitpost",
                "I wish i could find an RS Gf",
                "Where do you find runite ore ${real.username}?",
                "Where is the best coal location ${real.username}?",
                "Where do i find dragon weapons ${real.username}?",
                "Can i have some free stuff ${real.username}?",
                "Wyd here ${real.username}?",
                "Didn't know anyone was on rn",
                "I see ${real.username} all the time",
                "How many times have i seen you",
                "I see you a lot",
                "Do you train summoning ${real.username}?",
                "Where did you level hunter ${real.username}?",
                "I wish they would add global pvp",
                "Meet me in the wilderness ${real.username}",
                "Why?",
                "Praise our glorious frog overlord!",
                "Kermit is bae tbh",
                "Kermit is my god.",
                " /Yeah I think ${real.username} is a bot",
                "100% sure this is a bot",
                "Oh no, not ${real.username} again.",
                "Same as you",
                "Me too",
                "I knew ${real.username} was a bot lol",
                "I'm not a bot",
                "Nah I'm a real person",
                "Bruh are you even a real person lol?",
                "e",
                "Hellooooo ${real.username}",
                "wc lvl ${real.username}?",
                "fletching level ${real.username}?",
                "firemaking level ${real.username}?",
                "Have you seen the dude in the ge?",
                "Wonderful weather today ${real.username}",
                "Lowkey drunk af rn",
                "I am so tired",
                "Wassup ${real.username}",
                "follow me ${real.username}!",
                "Server goes brrrr",
                "bruh i am not a bot",
                "I think ${real.username} is a bot",
                "Are you a bot ${real.username}?",
                "insert spiderman meme here",
                "pot calling the kettle black etc",
                "ooh, a piece of candy",
                "I love woodcutting",
                "I'm going to go level up later",
                "I love mining shooting stars",
                " /this ${real.username} looks dumb",
                "AAAAAAAAAAAAAAAAAAAAAAAAHHHHHH!!!",
                "como estas ${real.username}",
                "Summer = best community manaer",
                "so how about that ceikry guy",
                "so how about that kermit dude",
                "woah is an abusive mod",
                "I heard Woah and Ceikry are dating now",
                "House party in Yanille!",
                "Can i have some gp ${real.username}?",
                "Where do i find partyhats?",
                "Why do mobs drop boxes?",
                "What exp rate do you play on ${real.username}?",
                "Have you met Summer?",
                "Hey ${real.username}",
                "Hey ${real.username}",
                "Hey ${real.username}",
                "Hey ${real.username}",
                "Hey ${real.username}",
                "Wyd?",
                "Wyd?",
                "Wyd?",
                "Wyd?",
                "${real.username} have you met Kermit?",
                "${real.username} have you met Ceikry?",
                "${real.username} have you met Woah?",
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
                "I don't use discord ${real.username}",
                "I don't use the CC ${real.username}",
                "Why should i use discord?",
                "2009Scape is life",
                "brb gotta make dinner",
                "I need to go to the GE",
                "${real.username} can i have a ge tele?",
                "lol ${real.username} shut up",
                "Where are teak trees?",
                "How do i make planks ${real.username}?",
                "Idk about that scraggley ass alex guy.",
                "Rusty? More like Crusty af lmfao.",
                "I need to sell some stuff on the ge",
                "I have so many logs to sell lol",
                "nah",
                "yes",
                "Where can i mine iron?",
                "Where can i mine tin?",
                "Where can i mine copper?",
                "Where can i mine clay?",
                "Where can i mine coal?",
                "no",
                "We're no strangers to love",
                "You know the rules and so do I",
                "A full commitment's what I'm thinking of",
                "You wouldn't get this from any other guy",
                "Never gonna give you up",
                "Never gonna let you down",
                "why",
                "why",
                "why",
                "why",
                "Why does it not show your messages in the chatbox?",
                "Why do you not show up in chat?",
                "Why do you not show up in chat ${real.username}?",
                "Why do you not show up in chat ${real.username}?",
                "When did you start playing ${real.username}?",
                "When did you start on this server ${real.username}?",
                "When did you first get here ${real.username}?",
                "russia's greatest love machine",
                "Never gonna run around and desert you",
                "Two things are infinite: the universe & ${real.username}'s stupidity",
                "If you tell the truth, you don't have to remember anything.",
                "We accept the love we think we deserve.",
                "Without music, life would be a mistake.",
                "Self esteem, motivation ${real.username}",
                "A blessing in disguise ${real.username}",
                "Break a leg",
                "Cut somebody some slack",
                "You’re in the right place!",
                "Thanks so much.",
                "I really appreciate you ${real.username}",
                "Excuse me ${real.username}?",
                "I’m sorry.",
                "What do you think ${real.username}?",
                "How does that sound ${real.username}?",
                "That sounds great ${real.username}.",
                "I’m learning English.",
                "I don’t understand.",
                "Could you repeat that please ${real.username}?",
                "Could you please talk slower ${real.username}?",
                "Thank you. That helps a lot.",
                "How do you spell that ${real.username}?",
                "What do you mean ${real.username}",
                "Hi! I’m paul.",
                "Nice to meet you.",
                "Where are you from ${real.username}?",
                "What do you do ${real.username}",
                "What do you like to do",
                "Do you have Facebook ${real.username}",
                "How can I help you ${real.username}?",
                "I’ll be with you in a moment ${real.username}",
                "What time is our meeting ${real.username}?",
                "Excellent ${real.username}",
                "Good idea ${real.username}",
                "He's very annoying.",
                "How are you?",
                "I can't hear you.",
                "I'd like to go for a walk.",
                "I don't know how to use it.",
                "I don't like him ${real.username}",
                "I don't like it ${real.username}",
                "I'm an American.",
                "I'm going to leave.",
                "I'm happy ${real.username}",
                "I'm not ready yet ${real.username}",
                "I'm very busy. I don't have time now.",
                "Is that enough ${real.username}?",
                "I thought the clothes were cheaper ${real.username}",
                "I've been here for two days ${real.username}.",
                "Let me think about it",
                "Never mind ${real.username}",
                "Nonsense ${real.username}",
                "Sorry to bother you",
                "Take it outside ${real.username}",
                "Thanks for your help",
                "Thank you very much ${real.username}",
                "That's not right ${real.username}",
                "Very good, thanks ${real.username}",
                "Your things are all here i think?",
                "Long time no see ${real.username}",
                "I couldn’t agree more ${real.username}",
                "It cost me a fortune ${real.username}",
                "I am dog tired",
                "Don’t take it personally",
                "We will be having a good time",
                "Same as always ${real.username}",
                "No problem",
                "Anyway, I should get going ${real.username}",
                "I can’t help you there ${real.username}",
                "I agree 100% ${real.username}"
        )
        val current = LocalDateTime.now()

        //val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatter2 = DateTimeFormatter.ofPattern("MMdd")
        val formatter3 = DateTimeFormatter.ofPattern("HHmm")
        val formatted: String = current.format(formatter)
        val formatted2: String = current.format(formatter2)
        val formatted3: String = current.format(formatter3)

        //2020-11-28
        //1128
        //1201 to 1225
        //2020-11-28 00:00:00.000
        val SnowDay = 1225
        val lead = formatted2.toInt()
        val celebrate = formatted3.toInt()
        val until = SnowDay - lead

        val Leading2Christmas = listOf(
                "Only $until days left till christmas!",
                "$until days till christmas ${real.username}!!!",
                "I can't believe theres only $until days left till christmas",
                "Isn't there $until days left till christmas??",
                "I am so excited for christmas ${real.username}!",
                "Guess what's in $until days ${real.username}?",
                "I'm so excited for christmas in $until days!",
                "I can't believe it's December already ${real.username}!",
                "Do you like christmas ${real.username}?",
                "I love december it's my favourite month ${real.username}",
                "I love winter so much ${real.username}",
                "I hate when it's cold outside ${real.username}",
                "You need to put some warm clothes on ${real.username}",
                "Wanna build a snowman ${real.username}?",
                "Frozen is a terrible movie ${real.username}",
                "${real.username} is a winter meme",
                "${real.username} do you have an advent calendar?",
                "${real.username} builds gingerbread houses",
                "${real.username} likes liquorice",
                "Do you drink egg nog ${real.username}?",
                "${real.username} bites candy canes",
                "${real.username} is my north star",
                "Kermit is green, the grinch is green, coincidence?",
                "${real.username} who do you thinks the server scrooge?",
                "Do you like snow ${real.username}",
                "I think there are $until days left then its christmas?",
                "What day is christmas on ${real.username}?",
                "Has it snowed where you live ${real.username}?",
                "I wonder when it is going to snow",
                "Building snow men for GP",
                "Buying santa hats",
                "Wanna buy a santa hat ${real.username}?",
                "Where can i get a santa hat?",
                "I need to put my christmas tree up",
                "Do you like gingerbread cookies ${real.username}?",
                "Do you decorate your christmas tree ${real.username}?",
                "Oopsie looks like me and ${real.username} are under a mistletoe",
                "I need ideas for stocking stuffers",
                "I have $until days left to fill our stockings",
                "Rusty = the grinch, ${real.username} = santa",
                "${real.username} the red nose reindeer",
                "Put one foot in front of the other",
                "and soon you'll be walking out the doooor",
                "Elf on the shelf time",
                "${real.username} loves pinecones",
                "I love the smell of pinecones",
                "All i want for christmas is ${real.username}",
                "${real.username} is underneath some mistletoe",
                "Woah used to be a sled dog",
                "Did you know woah was a sled dog before",
                "I bet ${real.username} loves mariah carey",
                "We have $until days to setup christmas lights",
                "Christmas crackers when",
                "${real.username} wanna do a christmas cracker??",
                "I need to start wrapping presents i bought",
                "I love fireside chats on cold days like this",
                "Jingle bells batman smells",
                "Jingle bells Rusty smells",
                "Jingle bells Patch Notes smells",
                "Jingle bells Evilwaffles smells",
                "Jingle bells ${real.username} smells",
                "Jingle all the way",
                "In a one horse open sleigh",
                "Oh, what fun it is to ride",
                "Oh, what fun it is to ride, finish it ${real.username}",
                "Jingle bells, jingle bells",
                "Jingle all the way",
                "I want a hippopotamus for christmas",
                "Only a hippopotamus will do",
                "I want a hippopotamus",
                "I don't want a lot for Christmas",
                "All I Want for Christmas Is You",
                "I really can't stay, Baby it's cold outside",
                "Christmas isn't a season. It's a feeling",
                "Christmas is doing a little something extra for someone",
                "There is nothing cozier than a Christmas tree all lit up",
                "Christmas is a necessity",
                "Christmas countdown: $until days.",
                "Christmas in $until days ${real.username}",
                "Frosty the Snowmannnn",
                "Up on the housetop with old saint nick",
                "Here comes santa claus, here comes santa claus",
                "I saw mommy kissing santaaaa claus",
                "I saw Kermit kissing santaaaa claus",
                "You're a mean one, Mr. Grinch",
                "Deck the hallsss",
                "ITS BEGINNING TO LOOK A LOT LIKE CHRISTAAMASSSSS"

        )

        val ChristmasEve = listOf(
                "I can't wait for christmas tomorrow ${real.username}!!",
                "It's almost christmas!!!",
                "1 more day!!!!!",
                "Don't forget to put cookies and milk out tonight!!",
                "${real.username} do you like christmas??",
                "Happy Christmas eve ${real.username} :)"
        )

        val Christmas = listOf(
                "Merry Christmas ${real.username}!!",
                "What did you get for christmas ${real.username}?",
                "ITS CHRISTMASSSS!!!",
                "Christmas party time!!",
                "${real.username} vibing with the christmas spirit",
                "Can't believe it's christmas!!",
                "We need to have a christmas party ${real.username}!!",
                "Merry christmas ${real.username} :))",
                "Hope you're having an amazing christmas ${real.username} :)",
                "I love winter so much ${real.username}",
                "I hate when it's cold outside ${real.username}",
                "You need to put some warm clothes on ${real.username}",
                "Wanna build a snowman ${real.username}?",
                "Do you like christmas ${real.username}?"
        )

        val NewYearsEve = listOf(
                "What are you doing for New Years ${real.username}?",
                "New Years Eve party orrr?",
                "We should do something for New Years eve today ${real.username}!",
                "Happy New Years Eve ${real.username}",
                "1 More day left in 2021 thank god",
                "What's your New Years resolution ${real.username}?"
        )

        val NewYears = listOf(
                "Happy New Years ${real.username}!!!",
                "It's New Years Day ${real.username}!",
                "HAPPY NEW YEAR ${real.username}!!!",
                "2022 let's fucking gooooooo",
                "What are your goals for 2022 ${real.username}?"
        )

        when {

            //Celebrates lead up to Christmas!
            until in 2..24 -> {
                if (Random.nextBoolean()){
                    val chat = Leading2Christmas.random()
                    bot.sendChat(chat)
                    bot.updateMasks.register(ChatFlag(ChatMessage(bot, chat, 0, 0)))
                }else{
                    val chat = dialogue.random()
                    bot.sendChat(chat)
                    bot.updateMasks.register(ChatFlag(ChatMessage(bot, chat, 0, 0)))
                }
            }

            //Celebrates Christmas Day!
            formatted.contentEquals("2021-12-25") -> {
                if (celebrate in 0..60){
                    if (celebrate == 0) sendNews("MERRY CHRISTMAS HEAD TO THE GE!!!")
                    val chat = Christmas.random()
                    bot.sendChat(chat)
                    bot.updateMasks.register(ChatFlag(ChatMessage(bot, chat, 0, 0)))
                    if (Random.nextBoolean()) scriptAPI.teleport(ge) else scriptAPI.teleport(ge2)
                    city = if (Random.nextBoolean()) ge else ge2
                    if (Random.nextBoolean()) state = State.EXPLORE else bot.animator.animate(Animation.create(11044), Graphics.create(1973))
                }
                else if (Random.nextBoolean()) {
                    val chat = Christmas.random()
                    bot.sendChat(chat)
                    bot.updateMasks.register(ChatFlag(ChatMessage(bot, chat, 0, 0)))
                }else{
                    val chat = dialogue.random()
                    bot.sendChat(chat)
                    bot.updateMasks.register(ChatFlag(ChatMessage(bot, chat, 0, 0)))
                }
            }

            //Celebrates Christmas Eve
            formatted.contentEquals("2021-12-24") -> {
                if (Random.nextBoolean()) {
                    val chat = ChristmasEve.random()
                    bot.sendChat(chat)
                    bot.updateMasks.register(ChatFlag(ChatMessage(bot, chat, 0, 0)))
                }else{
                    val chat = dialogue.random()
                    bot.sendChat(chat)
                    bot.updateMasks.register(ChatFlag(ChatMessage(bot, chat, 0, 0)))
                }
            }

            //Celebrates New Years Eve
            formatted.contentEquals("2021-12-31") -> {
                if (Random.nextBoolean()) {
                    val chat = NewYearsEve.random()
                    bot.sendChat(chat)
                    bot.updateMasks.register(ChatFlag(ChatMessage(bot, chat, 0, 0)))
                }else{
                    val chat = dialogue.random()
                    bot.sendChat(chat)
                    bot.updateMasks.register(ChatFlag(ChatMessage(bot, chat, 0, 0)))
                }
            }

            //Celebrates New Years Day!!!
            formatted.contentEquals("2022-01-01") -> {
                if (celebrate in 0..60){
                    if (celebrate == 0) sendNews("HAPPY NEW YEAR HEAD TO THE GE!!!")
                    val chat = NewYears.random()
                    bot.sendChat(chat)
                    bot.updateMasks.register(ChatFlag(ChatMessage(bot, chat, 0, 0)))
                    if (Random.nextBoolean()) scriptAPI.teleport(ge) else scriptAPI.teleport(ge2)
                    city = if (Random.nextBoolean()) ge else ge2
                    state = State.EXPLORE
                }
                else if (Random.nextBoolean()) {
                    val chat = NewYears.random()
                    bot.sendChat(chat)
                    bot.updateMasks.register(ChatFlag(ChatMessage(bot, chat, 0, 0)))
                }else{
                    val chat = dialogue.random()
                    bot.sendChat(chat)
                    bot.updateMasks.register(ChatFlag(ChatMessage(bot, chat, 0, 0)))
                }
            }

            else -> {
                val chat = dialogue.random()
                bot.sendChat(chat)
                bot.updateMasks.register(ChatFlag(ChatMessage(bot, chat, 0, 0)))
            }
        }
    }

    var citygroupA = listOf(falador, varrock, draynor, rimmington, lumbridge, edgeville)
    var citygroupB = listOf(yanille, ardougne, seers, catherby)

    var bankMap = hashMapOf<Location, ZoneBorders>(
            falador to ZoneBorders(2950, 3374, 2943, 3368),
            varrock to ZoneBorders(3182, 3435, 3189, 3446),
            draynor to ZoneBorders(3092, 3240, 3095, 3246),
            edgeville to ZoneBorders(3093, 3498, 3092, 3489),
            yanille to ZoneBorders(2610, 3089, 2613, 3095),
            ardougne to ZoneBorders(2649, 3281, 2655, 3286),
            seers to ZoneBorders(2729, 3493, 2722, 3490),
            catherby to ZoneBorders(2807, 3438, 2811, 3441)
    )

    var handler1: CombatSwingHandler? = null

    var ticks = 0
    var freshspawn = true
    var new_city = false
    val badedge = ZoneBorders(3094, 3494, 3096, 3497)
    val badedge2 = Location.create(3094, 3492, 0)
    val badedge3 = Location.create(3094, 3490, 0)
    val badedge4 = Location.create(3094, 3494, 0)
    var sold = false
    val geloc: Location = if (Random.nextBoolean()){
        Location.create(3165, 3487, 0)
    }else{
        Location.create(3164, 3492, 0)
    }

    var counter: Int = 0
    var random: Int = (5..30).random()

    val type = when(style){
        CombatStyle.MELEE -> CombatBotAssembler.Type.MELEE
        CombatStyle.MAGIC -> CombatBotAssembler.Type.MAGE
        CombatStyle.RANGE -> CombatBotAssembler.Type.RANGE
    }

    init {

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
        return listOf(yanille, ardougne, seers, catherby, falador, varrock,
                draynor, rimmington, lumbridge, ge, ge2, edgeville).random()
    }

    fun immerse() {
        if (counter++ == 180) {state = State.TELEPORTING}
        val items = AIRepository.groundItems[bot]
        if (Random.nextBoolean()) {
            if (items.isNullOrEmpty()) {
                scriptAPI.attackNpcsInRadius(bot, 8)
                state = State.LOOT_DELAY
            }
            if (bot.inventory.isFull) {
                if(bankMap[city] == null){
                    scriptAPI.teleport(getRandomCity().also { city = it })
                } else {
                    if(bankMap[city]?.insideBorder(bot) == true){
                        state = State.FIND_BANK
                    } else {
                        scriptAPI.walkTo(bankMap[city]?.randomLoc ?: Location(0, 0, 0))
                    }
                }
            }

        } else {
            if (bot.inventory.isFull){
                if(bankMap[city] == null){
                    scriptAPI.teleport(getRandomCity().also { city = it })
                } else {
                    if(bankMap[city]?.insideBorder(bot) == true){
                        state = State.FIND_BANK
                    } else {
                        scriptAPI.walkTo(bankMap[city]?.randomLoc ?: Location(0, 0, 0))
                    }
                }
            } else {
                val oak: Node? = scriptAPI.getNearestNode("Oak", true)
                val tree: Node? = scriptAPI.getNearestNode("Tree", true)
                val willow: Node? = scriptAPI.getNearestNode("Willow", true)
                val rock: Node? = scriptAPI.getNearestNode("Rocks", true)
                val star: Node? = scriptAPI.getNearestNode("Crashed star", true)
                val node = listOf(oak, tree, willow, rock, star).random()
                try {
                    node?.interaction?.handle(bot, node.interaction[0])
                } catch (e: Exception){}
            }
        }
        return
    }

    fun refresh() {
        scriptAPI.teleport(lumbridge)
        freshspawn = true
        state = State.START
    }

    //Adventure Bots v3.0.1 -Chat Edition-
    override fun tick() {
        ticks++
        if (ticks++ >= 800) {
            ticks = 0
            refresh()
            return
        }

        when(state){

            State.LOOT_DELAY -> {
                bot.pulseManager.run(object : Pulse() {
                    var counter1 = 0
                    override fun pulse(): Boolean {
                        when (counter1++) {
                            4 -> return true.also { state = State.LOOT }
                        }
                        return false
                    }
                })
            }

            State.LOOT -> {
                val items = AIRepository.groundItems[bot]
                if (items?.isNotEmpty() == true && !bot.inventory.isFull) {
                    items.forEach {
                        scriptAPI.takeNearestGroundItem(it.id)
                    }
                } else {
                    state = State.EXPLORE
                }
            }

            State.START -> {
                if (freshspawn) {
                    freshspawn = false
                    scriptAPI.randomWalkTo(lumbridge, 20)
                } else {
                    city = getRandomCity()
                    state = State.TELEPORTING
                }
            }

            State.TELEPORTING -> {
                ticks = 0
                counter = 0
                if (bot.location != city) {
                    scriptAPI.teleport(city)
                } else {
                    state = State.EXPLORE
                }
            }

            State.EXPLORE -> {
                if (counter++ == 300) {
                    state = State.TELEPORTING
                }

                var chance = if (city == ge || city == ge2) 5000 else 2500
                if (RandomFunction.random(chance) <= 10) {
                        val nearbyPlayers = RegionManager.getLocalPlayers(bot)
                        if (nearbyPlayers.isNotEmpty()) {
                            ticks = 0
                            dialogue()
                        }
                }

                if (RandomFunction.random(1000) <= 150) {
                    var roamDistance = if (city != ge && city != ge2) 200 else 7
                    if ((city == ge || city == ge2) && RandomFunction.random(100) < 90) {
                        if (!bot.bank.isEmpty) {
                            state = State.FIND_GE
                        }
                        return
                    }
                    scriptAPI.randomWalkTo(city, roamDistance)
                    return
                }

                if (RandomFunction.random(1000) <= 50) {
                    if (city != ge && city != ge2) {
                        immerse()
                        return
                    } else {
                        return
                    }
                }

                if ((city == ge || city == ge2) && RandomFunction.random(1000) >= 999) {
                    ticks = 0
                    city = getRandomCity()
                    state = State.TELEPORTING
                }

                if (city == ge || city == ge2) {
                    return
                }

                if (counter++ >= 240 && RandomFunction.random(100) >= 10) {
                    city = getRandomCity()
                    if (RandomFunction.random(100) % 2 == 0) {
                        counter = 0
                        ticks = 0
                        state = State.TELEPORTING
                    } else {
                        if (citygroupA.contains(city)) {
                            city = citygroupA.random()
                        } else {
                            city = citygroupB.random()
                        }
                        counter = 0
                        ticks = 0
                        state = State.FIND_CITY
                    }
                    counter = 0
                    return
                }
                return
            }

            State.GE -> {
                var ge = false
                if (counter++ == 180) {
                    state = State.TELEPORTING
                }
                if (!sold) {
                    if (counter++ >= 15) {
                        sold = true
                        ge = true
                        counter = 0
                        ticks = 0
                        scriptAPI.sellAllOnGeAdv()
                        return
                    }
                } else if (ge && sold) {
                    ge = false
                    city = getRandomCity()
                    state = State.TELEPORTING
                }
                return
            }

            State.FIND_GE -> {
                if (counter++ == 180) {
                    state = State.TELEPORTING
                }
                sold = false
                val ge: GameObject? = scriptAPI.getNearestNode("Desk", true) as GameObject?
                if (ge == null || bot.bank.isEmpty) state = State.EXPLORE
                class GEPulse : MovementPulse(bot, ge, DestinationFlag.OBJECT) {
                    override fun pulse(): Boolean {
                        bot.faceLocation(ge?.location)
                        state = State.GE
                        return true
                    }
                }
                if (ge != null && !bot.bank.isEmpty) {
                    counter = 0
                    scriptAPI.randomWalkTo(geloc, 3)
                    GameWorld.Pulser.submit(GEPulse())
                }
                return
            }

            State.FIND_BANK -> {
                if (counter++ == 300) {
                    state = State.TELEPORTING
                }
                val bank: GameObject? = scriptAPI.getNearestNode("Bank booth", true) as GameObject?
                if (badedge.insideBorder(bot) || bot.location == badedge2 || bot.location == badedge3 || bot.location == badedge4) {
                    bot.randomWalk(5, 5)
                }
                if (bank == null) state = State.EXPLORE
                class BankingPulse : MovementPulse(bot, bank, DestinationFlag.OBJECT) {
                    override fun pulse(): Boolean {
                        bot.faceLocation(bank?.location)
                        state = State.IDLE_BANKS
                        return true
                    }
                }
                if (bank != null) {
                    bot.pulseManager.run(BankingPulse())
                }
                return
            }

            State.IDLE_BANKS -> {
                if (counter++ == 300) {
                    state = State.TELEPORTING
                }
                if (RandomFunction.random(1000) < 100) {
                    for (item in bot.inventory.toArray()) {
                        item ?: continue
                        when (item.id) {
                            1357, 590, 1271, 995 -> continue
                        }
                        bot.bank.add(item)
                        bot.inventory.remove(item)
                    }
                    counter = 0
                    state = State.EXPLORE
                }
                return
            }

            State.FIND_CITY -> {
                if (counter++ >= 600 || (city == ge || city == ge2)) {
                    counter = 0
                    scriptAPI.teleport(getRandomCity().also { city = it })
                    state = State.EXPLORE
                }
                if (bot.location.equals(city)) {
                    state = State.EXPLORE
                } else {
                    scriptAPI.randomWalkTo(city, 5)
                }
                return
            }

            State.IDLE_CITY -> {
                if (counter++ == 300) {
                    state = State.TELEPORTING
                }
                var random = (120..300).random()
                if (counter++ == random && RandomFunction.random(1000) % 33 == 0) {
                    counter = 0
                    state = State.EXPLORE
                }
                return
            }
        }

    }

    enum class State{
        START,
        EXPLORE,
        FIND_BANK,
        IDLE_BANKS,
        FIND_CITY,
        IDLE_CITY,
        GE,
        TELEPORTING,
        LOOT,
        LOOT_DELAY,
        FIND_GE
    }


    override fun newInstance(): Script {
        val script = Adventurer(style)
        script.state = State.START
        val tier = CombatBotAssembler.Tier.MED
        if (type == CombatBotAssembler.Type.RANGE)
            script.bot = CombatBotAssembler().RangeAdventurer(tier, bot.startLocation)
        else
            script.bot = CombatBotAssembler().MeleeAdventurer(tier, bot.startLocation)
        return script
    }
}