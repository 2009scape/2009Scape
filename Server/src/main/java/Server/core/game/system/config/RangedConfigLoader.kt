package core.game.system.config

import core.ServerConstants
import core.cache.def.impl.ItemDefinition
import core.game.node.entity.combat.equipment.Ammunition
import core.game.node.entity.combat.equipment.BoltEffect
import core.game.node.entity.combat.equipment.RangeWeapon
import core.game.node.entity.impl.Projectile
import core.game.node.entity.npc.NPC
import core.game.system.SystemLogger
import core.game.world.map.Location
import core.game.world.update.flag.context.Animation
import core.game.world.update.flag.context.Graphics
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.FileReader

class RangedConfigLoader {
    val parser = JSONParser()
    var reader: FileReader? = null

    fun load(){
        var count = 0
        reader = FileReader(ServerConstants.CONFIG_PATH + "ammo_configs.json")
        var configs = parser.parse(reader) as JSONArray
        for(entry in configs){
            val e = entry as JSONObject
            var dbowgfx: Graphics? = null
            val projectile = e["projectile"].toString().split(",")
            if(e["darkbow_graphic"].toString().isNotBlank()){
                val darkbow =  e["darkbow_graphic"].toString().split(",")
                dbowgfx = Graphics(Integer.parseInt(darkbow[0]),Integer.parseInt(darkbow[1]))
            }
            val gfx = e["start_graphic"].toString().split(",")
            val ammo = Ammunition(
                    Integer.parseInt(e["itemId"] as String),
                    Graphics(Integer.parseInt(gfx[0]),Integer.parseInt(gfx[1])),
                    dbowgfx,
                    Projectile.create(
                            NPC(0, Location(0,0,0)),
                            NPC(0,Location(0,0,0)),
                            Integer.parseInt(projectile[0]),
                            Integer.parseInt(projectile[1]),
                            Integer.parseInt(projectile[2]),
                            Integer.parseInt(projectile[3]),
                            Integer.parseInt(projectile[4]),
                            Integer.parseInt(projectile[5]),
                            Integer.parseInt(projectile[6])),
                    Integer.parseInt(e["poison_damage"].toString()))
            val effect = BoltEffect.forId(Integer.parseInt(e["itemId"].toString()))
            if (effect != null) {
                ammo.effect = effect
            }
            Ammunition.getAmmunition().putIfAbsent(Integer.parseInt(e["itemId"].toString()),ammo)
            count++
        }
        SystemLogger.log("[Config Parser]: Parsed $count ammo configs...")

        count = 0
        reader = FileReader(ServerConstants.CONFIG_PATH + "ranged_weapon_configs.json")
        configs = parser.parse(reader) as JSONArray
        for(entry in configs){
            val e = entry as JSONObject
            val id = Integer.parseInt(e["itemId"].toString())
            val weapon = RangeWeapon(
                    id,
                    Animation(Integer.parseInt(e["animation"].toString())),
                    ItemDefinition.forId(id).getConfiguration("attack_speed",4),
                    Integer.parseInt(e["ammo_slot"].toString()),
                    Integer.parseInt(e["weapon_type"].toString()),
                    (e["drop_ammo"].toString().toBoolean()),
                    e["ammunition"].toString().split(",").map { Integer.parseInt(it) }
            )
            RangeWeapon.getRangeWeapons().putIfAbsent(weapon.itemId,weapon)
            count++
        }
        SystemLogger.log("[Config Parser]: Parsed $count ranged weapon configs...")
    }
}