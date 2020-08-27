package plugin.trivia

import core.game.system.SystemLogger
import java.io.File

class QuestionLoader{
    class TriviaQuestion(val question:  String?,val answer: String?)

    val qfile = "Server/src/main/java/Server/plugin/trivia/questions.txt"
    val questions = arrayListOf<TriviaQuestion>()

    fun loadQuestions(): List<TriviaQuestion> {
        var count = 0
        File(qfile).forEachLine { line ->
            val args = line.split("::")
            questions.add(TriviaQuestion(args[0],args[1]))
            count++;
        }
        SystemLogger.log("[Trivia] Loaded $count questions.")
        return questions
    }
}