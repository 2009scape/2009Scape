package plugin.trivia

import core.game.node.entity.player.Player
import core.game.node.item.WeightedChanceItem
import core.game.world.GameWorld
import core.game.world.repository.Repository
import core.plugin.Plugin
import core.tools.RandomFunction
import org.w3c.dom.Element
import org.w3c.dom.Node
import plugin.CorePluginTypes.ManagerPlugin
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class TriviaManager : ManagerPlugin(){
    var hasSession = false
    var continuous = false
    var question: QuestionLoader.TriviaQuestion? = null
    private val rewards = arrayListOf<WeightedChanceItem>()
    private var nextQuestion = 0
    val CONFIG_DIR = "Server" + File.separator + "data" + File.separator + "plugin" + File.separator + "Trivia" + File.separator
    var questions = QuestionLoader().loadQuestions()
    val DELAY = 30

    override fun tick() {
        if(continuous && !hasSession && GameWorld.getTicks() > nextQuestion){
            getNewQuestion()
            announce()
        }
    }

    fun dumpExample(){
        val xmlFile = File(CONFIG_DIR + "TriviaRewards.xml")
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val doc = builder.newDocument()

        val root = doc.createElement("rewards")
        doc.appendChild(root)

        val exampleReward = doc.createElement("Reward")
        val idAttr = doc.createAttribute("itemId")
        val amtAttr = doc.createAttribute("amount")
        val wtAttr = doc.createAttribute("weight")
        idAttr.value = "995"
        amtAttr.value = "500"
        wtAttr.value = "100"
        exampleReward.setAttributeNode(idAttr)
        exampleReward.setAttributeNode(amtAttr)
        exampleReward.setAttributeNode(wtAttr)
        root.appendChild(exampleReward)

        val tfactory = TransformerFactory.newInstance()
        val transformer = tfactory.newTransformer()
        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2")
        val source = DOMSource(doc)
        val result = StreamResult(xmlFile)
        transformer.transform(source,result)
    }

    fun parseRewards(){
        var xmlFile = File(CONFIG_DIR)
        if (!xmlFile.exists()) {xmlFile.mkdirs(); dumpExample()}
        xmlFile = File(CONFIG_DIR + "TriviaRewards.xml")

        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val doc = builder.parse(xmlFile)

        val nodes = doc.getElementsByTagName("Reward")

        for(i in 0 until nodes.length){
            val node = nodes.item(i)
            if(node.nodeType == Node.ELEMENT_NODE){
                val element = node as Element
                val id = Integer.parseInt(element.getAttribute("itemId"))
                val amount = Integer.parseInt(element.getAttribute("amount"))
                val weight = Integer.parseInt(element.getAttribute("weight"))
                rewards.add(WeightedChanceItem(id,amount,weight))
            }
        }
    }

    fun getNewQuestion(){
        question = questions.get(RandomFunction.random(questions.size))
        hasSession = true
    }

    fun endSession(){hasSession=false}
    fun start(){
        if(!continuous) continuous = true
    }
    fun stop(){
        continuous=false
        hasSession=false
    }

    fun submit(player: Player?, answer: String?){
        if(answer?.toLowerCase().equals(question?.answer?.toLowerCase())){
            Repository.sendNews("${player?.username} has won the trivia question!")
            endSession()
            val item = RandomFunction.rollWeightedChanceTable(rewards)
            player?.inventory?.add(item!!)
            nextQuestion = GameWorld.getTicks() + DELAY;
        }
    }

    fun announce(){Repository.sendNews(question?.question)}

    override fun newInstance(arg: Any?): Plugin<Any> {
        return this
    }

}