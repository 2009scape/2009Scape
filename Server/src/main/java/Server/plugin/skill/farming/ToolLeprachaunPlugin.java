package plugin.skill.farming;

import core.cache.def.impl.NPCDefinition;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import plugin.skill.herblore.Herbs;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.OptionHandler;
import core.game.interaction.UseWithHandler;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the option handler used for a tool leprachaun.
 * @author 'Vexia
 * @versio 1.0
 */
@InitializablePlugin
public final class ToolLeprachaunPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(3021).getHandlers().put("option:exchange", this);
		NPCDefinition.forId(4965).getHandlers().put("option:exchange", this);
		NPCDefinition.forId(3021).getHandlers().put("option:teleport", this);
		NPCDefinition.forId(4965).getHandlers().put("option:teleport", this);
		new ToolLeprechaunDialogue().init();
		new BankNotePlugin().newInstance(arg);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (option) {
		case "exchange":
			player.getFarmingManager().getEquipment().open(player);
			break;
		case "teleport":
			player.getPacketDispatch().sendMessage("The Leprechaun is not offering teleportation services at the moment.");
			// player.getTeleporter().send(Location.create(1638, 4709, 0),
			// TeleportType.NORMAL, 3);
			break;
		}
		return true;
	}

	/**
	 * Represents the plugin used to exchange farming products in bank notes.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class BankNotePlugin extends UseWithHandler {

		/**
		 * Constructs a new {@code BankNotePlugin} {@code Object}.
		 */
		public BankNotePlugin() {
			super(getProducts());
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			addHandler(3021, NPC_TYPE, this);
			addHandler(4965, NPC_TYPE, this);
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			FarmingNode node = null;
			for (FarmingPatch patch : FarmingPatch.values()) {
				for (FarmingNode n : patch.getNodes()) {
					if (n.getProduct().getId() == event.getUsedItem().getId()) {
						node = n;
						break;
					}
				}
			}
			if (node == null || event.getUsedItem().getDefinition().getNoteId() == -1) {
				event.getPlayer().getDialogueInterpreter().sendDialogues((NPC) event.getUsedWith(), null, "Nay, I can't turn that into a banknote.");
			} else {
				int amount = event.getPlayer().getInventory().getAmount(event.getUsedItem());
				if (event.getPlayer().getInventory().remove(new Item(event.getUsedItem().getId(), amount))) {
					event.getPlayer().getInventory().add(new Item(event.getUsedItem().getDefinition().getNoteId(), amount));
				}
				event.getPlayer().getDialogueInterpreter().sendItemMessage(event.getUsedItem(), "The leprechaun exchanges your items for banknotes.");
			}
			return true;
		}

		/**
		 * Gets the products.
		 * @return the ids.
		 */
		public static int[] getProducts() {
			final List<Integer> ids = new ArrayList<>();
			for (FarmingPatch patch : FarmingPatch.values()) {
				for (FarmingNode node : patch.getNodes()) {
					ids.add(node.getProduct().getId());
				}
			}
			for (Herbs h : Herbs.values()) {
				ids.add(h.getHerb().getId());
			}
			final int[] array = new int[ids.size()];
			for (int i = 0; i < array.length; i++) {
				array[i] = ids.get(i);
			}
			return array;
		}

	}

	/**
	 * Represents the dialogue plugin used for the tool leprachaun.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class ToolLeprechaunDialogue extends DialoguePlugin {

		/**
		 * Constructs a new {@code ToolLeprechaunDialogue} {@code Object}.
		 */
		public ToolLeprechaunDialogue() {
			/**
			 * empty.
			 */
		}

		public ToolLeprechaunDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new ToolLeprechaunDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			npc(FacialExpression.OLD_NORMAL, "Ah, 'tis a foine day to be sure! Were yez wantin' me to", "store yer tools, or maybe ye might be wantin' yer stuff", "back from me?");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				options("Yes please.", "What can you store?", "What do you do with the tools you're storing?", "No thanks, I'll keep hold of my stuff.");
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					player("Yes please.");
					stage = 10;
					break;
				case 2:
					player("What can you store?");
					stage = 20;
					break;
				case 3:
					player("What do you do with the tools you're storing?", "They can't possibly all fit in your pockets!");
					stage = 30;
					break;
				case 4:
					player("No thanks, I'll keep hold of my stuff.");
					stage = 40;
					break;
				}
				break;
			case 10:
				end();
				player.getFarmingManager().getEquipment().open(player);
				break;
			case 20:
				npc(FacialExpression.OLD_NORMAL, "We'll hold onto yer rake, yer seed dibber, yer spade,", "yer secateurs, yer waterin' can and yer trowel - but", "mind it's not one of them fancy trowels only", "achaeologists use!");
				stage = 21;
				break;
			case 21:
				npc(FacialExpression.OLD_NORMAL, "We'll take a few buckets off yer hands too, and even", "yer compost and yer supercompost. There's room in", "our shed for plenty of compost, so bring it on!");
				stage = 22;
				break;
			case 22:
				npc(FacialExpression.OLD_NORMAL, "Also, if you hand me yer farming produce, I might be", "able to change it into banknotes.");
				stage = 23;
				break;
			case 23:
				end();
				break;
			case 30:
				npc(FacialExpression.OLD_NORMAL, "We leprechauns have a shed where we keep 'em. It's a", "magic shed, so ye can get yer items back from any of", "us leprechauns whenever ye want. Saves ye havin' to", "carry loads of stuff around the country!");
				stage = 31;
				break;
			case 31:
				end();
				break;
			case 40:
				npc(FacialExpression.OLD_NORMAL, "Ye must be dafter then ye look if ye likes luggin' yer", "tools everywhere ye goes!");
				stage = 41;
				break;
			case 41:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 3021, 4965 };
		}
	}
}
