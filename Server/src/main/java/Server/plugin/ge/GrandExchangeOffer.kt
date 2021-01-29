package plugin.ge

import core.game.node.entity.player.Player
import core.game.node.item.Item
import java.util.Arrays

import core.game.system.SystemManager.state

import core.cache.def.impl.ItemDefinition




/**
 * A struct holding all the data for grand exchange offers as stored in json database.
 *
 * @author Angle
 */

class GrandExchangeOffer() {
    var itemID = 0
    var amount = 0
    var completedAmount = 0
    var offeredValue = 0
    var index = 0
    var sell = false
    var offerState = OfferState.PENDING
    var uid: Long = 0
    var timeStamp: Long = 0
    var withdraw = arrayOfNulls<Item>(2)
    var totalCoinExchange = 0
    var player: Player? = null
    var playerUID = 0
    var isLimitation = false

    /**
     * Gets the total amount of money entered.
     * @return The total value.
     */
    val totalValue: Int
        get() = offeredValue * amount

    /**
     * Gets the amount of this item left to buy.
     * @return The amount.
     */
    val amountLeft: Int
        get() = amount - completedAmount

    /**
     * Checks if this offer is still active for dispatching.
     * @return `True` if so.
     */
    val isActive: Boolean
        get() = offerState != OfferState.ABORTED && offerState != OfferState.PENDING && offerState != OfferState.COMPLETED && offerState != OfferState.REMOVED

    override fun toString(): String {
        return "[name=" + ItemDefinition.forId(itemID).name + ", itemId=" + itemID + ", amount=" + amount + ", completedAmount=" + completedAmount + ", offeredValue=" + offeredValue + ", index=" + index + ", sell=" + sell + ", state=" + offerState + ", withdraw=" + withdraw.contentToString() + ", totalCoinExchange=" + totalCoinExchange + ", playerUID=" + playerUID + "]"
    }
}