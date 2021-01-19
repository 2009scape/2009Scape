package core.game

import core.game.node.entity.player.Player

/**
 * A class that represents Varps and aids in modifying/calculating them
 * @author Ceikry
 */
class Varp(val index: Int) {
    val varbits = ArrayList<Varbit>()
    var save = false

    fun setVarbit(offset: Int, value: Int): Varp{
        for(vb in varbits){
            if (vb.offset == offset){
                varbits.remove(vb)
                break
            }
        }
        varbits.add(Varbit(value,offset))
        return this
    }

    fun getValue(): Int{
        var config = 0
        for(varbit in varbits){
            config = config or (varbit.value shl varbit.offset)
        }
        return config
    }

    fun getVarbit(offset: Int): Varbit?{
        for(varbit in varbits){
           if(varbit.offset == offset) return varbit
        }
        return null
    }

    fun getVarbitValue(offset: Int): Int{
        return getVarbit(offset)?.value ?: 0
    }

    fun send(player: Player){
        player.packetDispatch.sendVarp(this)
    }
}