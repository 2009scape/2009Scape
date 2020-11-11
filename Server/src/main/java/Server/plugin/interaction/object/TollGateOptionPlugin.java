package plugin.interaction.object;

import core.cache.def.impl.ObjectDefinition;
import core.game.content.global.action.DoorActionHandler;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.world.repository.Repository;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

@InitializablePlugin
public class TollGateOptionPlugin extends OptionHandler {

	@Override
	public boolean handle(Player player, Node node, String option) {
		if (option.equals("pay-toll(10gp)")) {
			if (player.getQuestRepository().getQuest("Prince Ali Rescue").getStage(player) > 50) {
				player.getPacketDispatch().sendMessage("The guards let you through for free.");
				DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
			} else {
				if (player.getInventory().contains(995, 10)) {
					player.getInventory().remove(new Item(995, 10));
					player.getPacketDispatch().sendMessage("You quickly pay the 10 gold toll and go through the gates.");
					DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
					player.getStatisticsManager().getAL_KHARID_GATE_PASSES().incrementAmount();
					return true;
				} else {
					player.getPacketDispatch().sendMessage("You need 10 gold to pass through the gates.");
				}
			}
		} else {
			player.getDialogueInterpreter().open(925, Repository.findNPC(925), (GameObject) node);
			return true;
		}
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(35551).getHandlers().put("option:open", this);
		ObjectDefinition.forId(35551).getHandlers().put("option:pay-toll(10gp)", this);
		ObjectDefinition.forId(35549).getHandlers().put("option:open", this);
		ObjectDefinition.forId(35549).getHandlers().put("option:pay-toll(10gp)", this);
		ObjectDefinition.forId(2882).getHandlers().put("option:pay-toll(10gp)", this);
		return this;
	}

}
