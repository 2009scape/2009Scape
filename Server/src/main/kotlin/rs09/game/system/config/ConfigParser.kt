package rs09.game.system.config

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ConfigParser {
    fun prePlugin() = GlobalScope.launch{
        launch {
            NPCConfigParser().load()
            ItemConfigParser().load()
        }
        launch {
            ObjectConfigParser().load()
            XteaParser().load()
        }
        InterfaceConfigParser().load()
    }

    fun postPlugin() = GlobalScope.launch{
        launch {
            ShopParser().load()
            DropTableParser().load()
            NPCSpawner().load()
        }
        launch {
            DoorConfigLoader().load()
            GroundSpawnLoader().load()
            MusicConfigLoader().load()
        }
        RangedConfigLoader().load()
    }
}