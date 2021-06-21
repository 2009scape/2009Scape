import rs09.game.system.SystemLogger
import java.util.*
import kotlin.system.exitProcess

object DialUtils {

    //Number of chars per line. Line is split at the nearest whole word that doesn't exceed this length. This should remain constant once a perfect value is found
    const val LINE_SIZE = 52.0

    @JvmStatic
            /**
             * Automatically split a single continuous string into multiple comma-separated lines.
             * Should this not work out for any reason, you should fallback to standard npc and player methods for dialogue.
             */
    fun splitLines(message: String): Array<String>{
        val chars = message.split("").toTypedArray().size.toDouble()
        val words = message.split(" ").toTypedArray()
        val lines = Math.ceil(chars / LINE_SIZE).toInt()

        if(lines > 4){
            SystemLogger.logErr("INVALID NUM LINES. NUM CHARS: $chars")
        }

        val messages = ArrayList<String>()

        var index = 0

        for (msg in 0 until lines) {
            var length = 0
            val line = StringBuilder()
            while (length <= LINE_SIZE && !(index == words.size)) {
                var currentWord = ""
                try {
                    currentWord = words[index] + if (length + words[index].length + 1 <= LINE_SIZE) " " else ""
                } catch (e: Exception){
                    SystemLogger.logWarn("INDEX: $index WORDSIZE: ${words.size}")
                    for(word in words.indices){
                        SystemLogger.logErr("Word $word: ${words[word]}")
                    }
                    exitProcess(0)
                }
                length += currentWord.length
                if (length <= LINE_SIZE) {
                    line.append(currentWord)
                    ++index
                }
            }
            messages.add(line.toString())
        }

        return messages.toTypedArray()
    }
}