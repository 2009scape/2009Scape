package plugin.interaction.item.withobject;

import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.interaction.NodeUsageEvent;
import org.crandor.game.interaction.UseWithHandler;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * @author 'Vexia
 */
@InitializablePlugin
public class PriestInPerilUsePlugin extends UseWithHandler {
	/**
	 * Constructs a new {@code PriestInPerilUsePlugin.java} {@code Object}.
	 */
	public PriestInPerilUsePlugin() {
		super(2944, 1925, 2945, 2954);
	}

	/**
	 * (non-Javadoc)
	 * @see Plugin#newInstance(Object)
	 */
	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(3499, OBJECT_TYPE, this);
		addHandler(3485, OBJECT_TYPE, this);
		addHandler(3463, OBJECT_TYPE, this);
		addHandler(30728, OBJECT_TYPE, this);
		return this;
	}

	/**
	 * (non-Javadoc)
	 * @see UseWithHandler#handle(NodeUsageEvent)
	 */
	@Override
	public boolean handle(NodeUsageEvent event) {
		Player player = event.getPlayer();
		if (((GameObject) event.getUsedWith()).getId() == 3499) {
			if (!event.getPlayer().getGameAttributes().getAttributes().containsKey("priest_in_peril:key") && event.getPlayer().getInventory().remove(new Item(2944))) {
				event.getPlayer().getInventory().add(new Item(2945));
				event.getPlayer().getPacketDispatch().sendMessage("You swap the Golden key for the Iron key.");
				event.getPlayer().getGameAttributes().setAttribute("/save:priest_in_peril:key", true);
			} else {
				return true;
			}
		}
		if (((GameObject) event.getUsedWith()).getId() == 3485) {
			if (event.getPlayer().getInventory().remove(new Item(1925))) {
				event.getPlayer().getInventory().add(new Item(2953));
				event.getPlayer().getPacketDispatch().sendMessage("You fill the bucket from the well.");
			}
		}
		if (((GameObject) event.getUsedWith()).getId() == 3463) {
			if (player.getInventory().remove(new Item(2945))) {
				Quest quest = player.getQuestRepository().getQuest("Priest in Peril");
				quest.setStage(player, 15);
				player.getPacketDispatch().sendMessage("You have unlocked the cell door.");
				NPC npc = NPC.create(1047, player.getLocation());
				npc.setName("Dezel");
				player.getDialogueInterpreter().sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oh! Thank you! You have found the key!");
			}
		}
		if (((GameObject) event.getUsedWith()).getId() == 30728) {
			if (player.getInventory().remove(new Item(2954))) {
				player.getInventory().add(new Item(1925));
				Quest quest = player.getQuestRepository().getQuest("Priest in Peril");
				quest.setStage(player, 16);
				player.getPacketDispatch().sendMessage("You pour the blessed water over the coffin...");
			}
		}
		return true;
	}
}
