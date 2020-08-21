package plugin.quest.members.whatliesbelow;

import core.cache.def.impl.ObjectDefinition;
import core.game.component.Component;
import plugin.activity.ActivityManager;
import plugin.skill.gather.SkillingTool;
import plugin.skill.runecrafting.Altar;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.OptionHandler;
import core.game.interaction.UseWithHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.Plugin;
import core.plugin.PluginManager;

/**
 * Handles the what lies below options.
 * @author Vexia
 * 
 */
public class WLBelowPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		PluginManager.definePlugin(new OutlawNPC());
		PluginManager.definePlugin(new KingRoaldNPC());
		ActivityManager.register(new WLBelowCutscene());
		PluginManager.definePlugin(new FolderHandler());
		PluginManager.definePlugin(new MetalWandHandler());
		PluginManager.definePlugin(new AnnaJonesDialogue());
		PluginManager.definePlugin(new SurokMagisDialogue());
		PluginManager.definePlugin(new RatBurgissDialogue());
		ObjectDefinition.forId(23095).getConfigurations().put("option:use", this);
		ObjectDefinition.forId(23058).getConfigurations().put("option:enter", this);
		ObjectDefinition.forId(23057).getConfigurations().put("option:excavate", this);
		WhatLiesBelow.RATS_PAPER.getDefinition().getConfigurations().put("option:read", this);
		WhatLiesBelow.EMPTY_FOLDER.getDefinition().getConfigurations().put("option:read", this);
		WhatLiesBelow.USED_FOLDER.getDefinition().getConfigurations().put("option:read", this);
		WhatLiesBelow.FULL_FOLDER.getDefinition().getConfigurations().put("option:read", this);
		WhatLiesBelow.RATS_LETTER.getDefinition().getConfigurations().put("option:read", this);
		WhatLiesBelow.SUROKS_LETTER.getDefinition().getConfigurations().put("option:read", this);
		WhatLiesBelow.BEACON_RING.getDefinition().getConfigurations().put("option:summon", this);
		WhatLiesBelow.BEACON_RING.getDefinition().getConfigurations().put("option:operate", this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		final Quest quest = player.getQuestRepository().getQuest(WhatLiesBelow.NAME);
		switch (option) {
		case "summon":
		case "operate":
			if (quest.getStage(player) > 70) {
				player.sendMessage("Zaff has removed the summon charges from the ring. It will not summon him again.");
			} else {
				if (quest.getStage(player) == 70 && player.getZoneMonitor().isInZone("What Lies below")) {
					WLBelowCutscene cutscene = player.getAttribute("cutscene", null);
					cutscene.summonZaff();
					return true;
				}
				player.sendMessage("You need to use this at the proper time.");
			}
			break;
		case "enter":
			switch (node.getId()) {
			case 23058:
				player.animate(Animation.create(6103));
				player.teleport(new Location(3179, 5191, 0), 2);
				break;
			}
			break;
		case "use":
			switch (node.getId()) {
			case 23095:
				player.teleport(new Location(2270, 4836, 1));
				break;
			}
			break;
		case "excavate":
			switch (quest.getStage(player)) {
			case 30:
				final SkillingTool tool = SkillingTool.getPickaxe(player);
				if (tool == null) {
					player.sendMessage("You need a pickaxe in order to do that.");
					break;
				}
				player.lock();
				player.animate(tool.getAnimation());
				GameWorld.Pulser.submit(new Pulse(1, player) {
					int count;

					@Override
					public boolean pulse() {
						count++;
						int duration = 7;
						if (count == duration) {
							player.sendChat("Hmmm...");
						} else if (count == duration + 2) {
							player.animate(tool.getAnimation());
						} else if (count == duration * 2) {
							quest.setStage(player, 40);
							player.getAnimator().reset();
							player.getConfigManager().set(992, 1 << 8, true);
							player.getDialogueInterpreter().open(5837, true, true, true);
							player.unlock();
							return true;
						}
						return false;
					}

				});
				break;
			default:
				player.sendMessage("Nothing interesting happened.");
				break;
			}
			break;
		case "read":
			switch (node.getId()) {
			case 11003:
				String message = "The folder is empty at the moment so there is nothing inside to read!";
				player.sendMessage(message);
				break;
			case 11009:
			case 11010:
				player.sendMessage("You read the letter.");
				player.getInterfaceManager().open(new Component(node.getId() == 11009 ? 249 : 250));
				break;
			case 11008:
			case 11007:
			case 11006:
				player.getDialogueInterpreter().sendDialogue("The piece of papers appears to contain lots of facts and figures.", "They look like accounts and lists of items. You're", "not sure what they all mean.");
				break;
			}
			break;
		}
		return true;
	}

	/**
	 * Handles the infusion of the metal wand.
	 * @author Vexia
	 */
	public static class MetalWandHandler extends UseWithHandler {

		/**
		 * The chaos runes.
		 */
		private static final Item CHAOS_RUNES = new Item(562, 15);

		/**
		 * The chaos talisman.
		 */
		private static final Item CHAOS_TALISMAN = new Item(1452);

		/**
		 * The chaos tiara.
		 */
		private static final Item CHAOS_TIARA = new Item(5543);

		/**
		 * Constructs a new {@Code MetalWandHandler} {@Code
		 * Object}
		 */
		public MetalWandHandler() {
			super(WhatLiesBelow.WAND.getId());
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			addHandler(Altar.CHAOS.getObject(), OBJECT_TYPE, this);
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			Player player = event.getPlayer();
			if (!player.getInventory().containsItem(CHAOS_RUNES)) {
				player.sendMessage("You need 15 chaos runes.");
				return true;
			}
			/*if (!player.getInventory().containsItem(CHAOS_TALISMAN) && !player.getInventory().containsItem(CHAOS_TIARA)) {
				player.sendMessage("You need a chaos talisman or chaos tiara.");
				return true;
			}*/
			Item chaosItem = player.getInventory().containsItem(CHAOS_TALISMAN) ? CHAOS_TALISMAN : CHAOS_TIARA;
			if (chaosItem != null && player.getInventory().remove(CHAOS_RUNES)) {
				player.lock(5);
				player.getInventory().remove(WhatLiesBelow.WAND);
				player.getInventory().add(WhatLiesBelow.INFUSED_WAND);
				player.animate(Animation.create(6104));
				player.getDialogueInterpreter().sendDialogue("The metal wand bursts into life and crackles with arcane", "power. This is a powerful instrument indeed!");
			}
			return true;
		}

	}

	/**
	 * Handles the empty folder.
	 * @author Vexia
	 */
	public class FolderHandler extends UseWithHandler {

		/**
		 * Constructs a new {@Code FolderHandler} {@Code Object}
		 */
		public FolderHandler() {
			super(WhatLiesBelow.EMPTY_FOLDER.getId(), WhatLiesBelow.USED_FOLDER.getId());
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			addHandler(WhatLiesBelow.RATS_PAPER.getId(), ITEM_TYPE, this);
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			Player player = event.getPlayer();
			Item paper = event.getUsedItem();
			Item folder = event.getUsedWith().asItem();
			if (player.getInventory().remove(paper)) {
				if (folder.getId() == 11003) {
					player.getInventory().replace(WhatLiesBelow.USED_FOLDER, folder.getSlot());
					folder = player.getInventory().get(folder.getSlot());
				}
				folder.setCharge(folder.getCharge() + 1);
				int amount = 1005 - folder.getCharge();
				player.sendMessage("You add the page to the folder that Rat gave to you.");
				if (amount <= 0) {
					player.getInventory().replace(WhatLiesBelow.FULL_FOLDER, folder.getSlot());
					player.sendMessage("You add the last page to Rat's folder. You should take this back to Rat as soon as");
					player.sendMessage("possible.");
					player.getDialogueInterpreter().sendDialogue("You have added all the pages to the folder that Rat gave to you.", "You should take this folder back to Rat.");
					return true;
				}
				player.sendMessage("You need to find " + amount + " more page" + (amount <= 1 ? "." : "s."));
			}
			return true;
		}
	}
}
