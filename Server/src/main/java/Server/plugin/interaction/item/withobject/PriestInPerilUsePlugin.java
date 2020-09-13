package plugin.interaction.item.withobject;

import plugin.dialogue.FacialExpression;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import plugin.quest.members.PriestInPeril;

/**
 * @author 'Vexia
 */
@InitializablePlugin
public class PriestInPerilUsePlugin extends UseWithHandler {
	private int[] monumentIds = new int[] { 3499, 3493, 3494, 3497, 3495, 3498, 3496 };
	/**
	 * Constructs a new {@code PriestInPerilUsePlugin.java} {@code Object}.
	 */
	public PriestInPerilUsePlugin() {
		super(2944, 1925, 2945, 2954, 2347, 1733, 1931, 314, 36, 590);
	}

	/**
	 * (non-Javadoc)
	 * @see Plugin#newInstance(Object)
	 */
	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for(int i = 0; i < monumentIds.length; i++){
			addHandler(monumentIds[i], OBJECT_TYPE, this);
		}
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
		int objUse = ((GameObject) event.getUsedWith()).getId();
		int itemUse = ((Item) event.getUsedItem()).getId();
		switch(objUse) {
			case 3499:
				if (!event.getPlayer().getGameAttributes().getAttributes().containsKey("priest_in_peril:key") && event.getPlayer().getInventory().remove(new Item(2944))) {
					event.getPlayer().getInventory().add(new Item(2945));
					event.getPlayer().getPacketDispatch().sendMessage("You swap the Golden key for the Iron key.");
					event.getPlayer().getGameAttributes().setAttribute("/save:priest_in_peril:key", true);
				} else {
					return true;
				}
				break;
			case 3493:
				if(!player.getGameAttributes().getAttributes().containsKey("priest_in_peril:tinderbox")){
					if (itemUse == 590) {
						if (event.getPlayer().getInventory().remove(new Item(590))) {
							event.getPlayer().getInventory().add(new Item(2946));
							event.getPlayer().getPacketDispatch().sendMessage("You swap the tinderbox for the golden tinderbox.");
							event.getPlayer().getGameAttributes().setAttribute("/save:priest_in_peril:tinderbox", true);
						}
					}
				}
				break;
			case 3494:
				if(!player.getGameAttributes().getAttributes().containsKey("priest_in_peril:candle")){
					if (itemUse == 36) {
						if (event.getPlayer().getInventory().remove(new Item(36))) {
							event.getPlayer().getInventory().add(new Item(2947));
							event.getPlayer().getPacketDispatch().sendMessage("You swap the candle for the golden candle.");
							event.getPlayer().getGameAttributes().setAttribute("/save:priest_in_peril:candle", true);
						}
					}
				}
				break;
			case 3497:
				if(!player.getGameAttributes().getAttributes().containsKey("priest_in_peril:feather")){
					if (itemUse == 314) {
						if (event.getPlayer().getInventory().remove(new Item(314))) {
							event.getPlayer().getInventory().add(new Item(2950));
							event.getPlayer().getPacketDispatch().sendMessage("You swap the feather for the golden feather.");
							event.getPlayer().getGameAttributes().setAttribute("/save:priest_in_peril:feather", true);
						}
					}
				}
				break;
			case 3495:
				if(!player.getGameAttributes().getAttributes().containsKey("priest_in_peril:pot")){
					if (itemUse == 1931) {
						if (event.getPlayer().getInventory().remove(new Item(1931))) {
							event.getPlayer().getInventory().add(new Item(2948));
							event.getPlayer().getPacketDispatch().sendMessage("You swap the empty pot for the golden pot.");
							event.getPlayer().getGameAttributes().setAttribute("/save:priest_in_peril:pot", true);
						}
					}
				}
				break;
			case 3498:
				if(!player.getGameAttributes().getAttributes().containsKey("priest_in_peril:needle")){
					if (itemUse == 1733) {
						if (event.getPlayer().getInventory().remove(new Item(1733))) {
							event.getPlayer().getInventory().add(new Item(2951));
							event.getPlayer().getPacketDispatch().sendMessage("You swap the needle for the golden needle.");
							event.getPlayer().getGameAttributes().setAttribute("/save:priest_in_peril:needle", true);
						}
					}
				}
				break;
			case 3496:
				if(!player.getGameAttributes().getAttributes().containsKey("priest_in_peril:hammer")){
					if (itemUse == 2347) {
						if (event.getPlayer().getInventory().remove(new Item(2347))) {
							event.getPlayer().getInventory().add(new Item(2949));
							event.getPlayer().getPacketDispatch().sendMessage("You swap the hammer for the golden hammer.");
							event.getPlayer().getGameAttributes().setAttribute("/save:priest_in_peril:hammer", true);
						}
					}
				}
				break;
			case 3485: {
				if (event.getPlayer().getInventory().remove(new Item(1925))) {
					event.getPlayer().getInventory().add(new Item(2953));
					event.getPlayer().getPacketDispatch().sendMessage("You fill the bucket from the well.");
				}
			}
			break;
			case 3463: {
				if (player.getInventory().remove(new Item(2945))) {
					Quest quest = player.getQuestRepository().getQuest(PriestInPeril.NAME);
					quest.setStage(player, 15);
					player.getPacketDispatch().sendMessage("You have unlocked the cell door.");
					NPC npc = NPC.create(1047, player.getLocation());
					npc.setName("Drezel");
					player.getDialogueInterpreter().sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oh! Thank you! You have found the key!");
				}
			}
			break;
			case 30728: {
				if (player.getInventory().remove(new Item(2954))) {
					player.getInventory().add(new Item(1925));
					Quest quest = player.getQuestRepository().getQuest(PriestInPeril.NAME);
					quest.setStage(player, 16);
					player.getPacketDispatch().sendMessage("You pour the blessed water over the coffin...");
				}
			}
			break;
		}
		return true;
	}
}
