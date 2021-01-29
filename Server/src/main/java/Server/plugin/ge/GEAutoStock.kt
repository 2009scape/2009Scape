package plugin.ge

import core.ServerConstants
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.File
import java.io.FileReader

class GEAutoStock {
    companion object {
        // autostock format should be identical to the botoffers json format.
        private val DB_PATH = "data" + File.separator + "eco" + File.separator + "autostock.json"

        fun autostock() {
            if(ServerConstants.GE_AUTOSTOCK_ENABLED) {
                val parser = JSONParser()
                val botReader: FileReader? = FileReader(DB_PATH)
                val botSave = parser.parse(botReader) as JSONObject
                if (botSave.containsKey("offers")) {
                    val offers = botSave["offers"] as JSONArray
                    for (offer in offers) {
                        val o = offer as JSONObject
                        OfferManager.addBotOffer(o["item"].toString().toInt(), o["qty"].toString().toInt())
                    }
                }
            }
        }
    }
}