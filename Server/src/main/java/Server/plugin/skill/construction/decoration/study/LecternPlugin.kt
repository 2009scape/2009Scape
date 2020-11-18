package plugin.skill.construction.decoration.study;


import core.cache.def.impl.ObjectDefinition;
import core.game.component.Component;
import core.game.component.ComponentDefinition;
import core.game.component.ComponentPlugin;
import core.game.node.entity.player.link.diary.DiaryType;
import plugin.skill.Skills;
import plugin.skill.construction.Decoration;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManager;
import plugin.interaction.item.TeleTabsOptionPlugin;

/**
 * Handles the lectern
 * LecternPlugin.java
 * @author Clayton Williams
 * @date Oct 30, 2016
 */
@InitializablePlugin
public class LecternPlugin extends OptionHandler {
	
	/**
	 * Soft clay
	 */
	private static final Item SOFT_CLAY = new Item(1761, 1);
	
	/**
	 * TeleTabButton
	 */
	private enum TeleTabButton {
		
		ARDOUGNE(2, 51, 66, new Item(TeleTabsOptionPlugin.TeleTabs.ADDOUGNE_TELEPORT.getItem()), new Decoration[] { Decoration.TEAK_EAGLE_LECTERN, Decoration.MAHOGANY_EAGLE_LECTERN }, SOFT_CLAY, new Item(563, 2), new Item(555, 2)),
		BONES_TO_BANANNAS(3, 15, 32, new Item(8014), new Decoration[] { Decoration.DEMON_LECTERN, Decoration.TEAK_DEMON_LECTERN, Decoration.MAHOGANY_DEMON_LECTERN }, SOFT_CLAY, new Item(561, 1), new Item(557, 2), new Item(555, 2)),
		BONES_TO_PEACHES(4, 60, 36, new Item(8015), new Decoration[] { Decoration.MAHOGANY_DEMON_LECTERN }, SOFT_CLAY, new Item(561, 2), new Item(557, 4), new Item(555, 4)),
		CAMELOT(5, 45, 56, new Item(TeleTabsOptionPlugin.TeleTabs.CAMELOT_TELEPORT.getItem()), new Decoration[] { Decoration.TEAK_EAGLE_LECTERN, Decoration.MAHOGANY_EAGLE_LECTERN }, SOFT_CLAY, new Item(563), new Item(556, 5)),
		ENCHANT_DIAMOND(6, 57, 62, new Item(8019), new Decoration[] { Decoration.TEAK_DEMON_LECTERN, Decoration.MAHOGANY_DEMON_LECTERN }, SOFT_CLAY, new Item(564), new Item(557, 10)),
		ENCHANT_DRAGONSTONE(7, 68, 60, new Item(8020), new Decoration[] { Decoration.MAHOGANY_DEMON_LECTERN }, SOFT_CLAY, new Item(564), new Item(557, 15), new Item(555, 15)),
		ENCHANT_EMERALD(8, 27, 34, new Item(8017), new Decoration[] { Decoration.DEMON_LECTERN, Decoration.TEAK_DEMON_LECTERN, Decoration.MAHOGANY_DEMON_LECTERN }, SOFT_CLAY, new Item(564), new Item(556, 3)),
		ENCHANT_ONYX(9, 87, 84, new Item(8021), new Decoration[] { Decoration.MAHOGANY_DEMON_LECTERN }, SOFT_CLAY, new Item(564), new Item(557, 20), new Item(554, 20)),
		ENCHANT_RUBY(10, 49, 52, new Item(8018), new Decoration[] { Decoration.TEAK_DEMON_LECTERN, Decoration.MAHOGANY_DEMON_LECTERN }, SOFT_CLAY, new Item(564), new Item(554, 5)),
		ENCHANT_SAPPHIRE(11, 7, 12, new Item(8016), new Decoration[] { Decoration.OAK_LECTERN, Decoration.EAGLE_LECTERN, Decoration.TEAK_EAGLE_LECTERN, Decoration.MAHOGANY_EAGLE_LECTERN,  Decoration.DEMON_LECTERN, Decoration.TEAK_DEMON_LECTERN, Decoration.MAHOGANY_DEMON_LECTERN}, SOFT_CLAY, new Item(564), new Item(555)),
		FALADOR(12, 37, 48, new Item(TeleTabsOptionPlugin.TeleTabs.FALADOR_TELEPORT.getItem()), new Decoration[] { Decoration.EAGLE_LECTERN, Decoration.TEAK_EAGLE_LECTERN, Decoration.MAHOGANY_EAGLE_LECTERN }, SOFT_CLAY, new Item(563), new Item(555), new Item(556, 3)),
		LUMBRIDGE(13, 31, 40, new Item(TeleTabsOptionPlugin.TeleTabs.LUMBRIDGE_TELEPORT.getItem()), new Decoration[] { Decoration.EAGLE_LECTERN, Decoration.TEAK_EAGLE_LECTERN, Decoration.MAHOGANY_EAGLE_LECTERN }, SOFT_CLAY, new Item(563), new Item(557), new Item(556, 3)),
		HOUSE(14, 40, 30, new Item(8013), new Decoration[] { Decoration.MAHOGANY_EAGLE_LECTERN }, SOFT_CLAY, new Item(563), new Item(557), new Item(556)),
		VARROCK(15, 25, 35, new Item(TeleTabsOptionPlugin.TeleTabs.VARROCK_TELEPORT.getItem()), new Decoration[] { Decoration.OAK_LECTERN, Decoration.EAGLE_LECTERN, Decoration.TEAK_EAGLE_LECTERN, Decoration.MAHOGANY_EAGLE_LECTERN,  Decoration.DEMON_LECTERN, Decoration.TEAK_DEMON_LECTERN, Decoration.MAHOGANY_DEMON_LECTERN }, SOFT_CLAY, new Item(563), new Item(554), new Item(556, 3)),
		WATCHTOWER(16, 58, 74, new Item(TeleTabsOptionPlugin.TeleTabs.WATCH_TOWER_TELEPORT.getItem()), new Decoration[] { Decoration.MAHOGANY_EAGLE_LECTERN }, SOFT_CLAY, new Item(563, 2), new Item(557, 2)),
		
		;
	 
		/**
		 * The button id
		 */
		private final int buttonId;
		
		/**
		 * The level and xp
		 */
		private final int level, xp;
		
		/**
		 * The item
		 */
		private final Item tabItem;
		
		/**
		 * The required decoration (lectern)
		 */
		private final Decoration[] requiredDecorations;
		
		/**
		 * The required items
		 */
		private final Item[] requiredItems;
		
		/**
		 * TeleTabButton
		 * @param id
		 */
		TeleTabButton(int id, int level, int xp, Item tabItem, Decoration[] requiredDecorations, Item... requiredItems) {
			this.buttonId = id;
			this.level = level;
			this.xp = xp;
			this.tabItem = tabItem;
			this.requiredDecorations = requiredDecorations;
			this.requiredItems = requiredItems;
		}
		
		/**
		 * Checks if the player can make this tab
		 * @param player
		 * @return
		 */
		private boolean canMake(Player player) {
			int objectId = player.getAttribute("ttb:objectid");
			if (player.getSkills().getLevel(Skills.MAGIC) < level) {
				player.sendMessage("You need a magic level of " + level + " to make that!");
				return false;
			}
			if (this.equals(TeleTabButton.BONES_TO_PEACHES) && !player.getSavedData().getActivityData().isBonesToPeaches()) {
				player.sendMessages("You need the Bones to Peaches ability purchased from MTA before making these.", "This requirement doesn't apply to actually using the tabs.");
				return false;
			}
			boolean found = false;
			for (Decoration d : requiredDecorations)
				if (d.getObjectId() == objectId)
					found = true;
			if (!found) {
				player.sendMessage("You're unable to make this tab on this specific lectern.");
				return false;
			}
			for (Item item : requiredItems) {
				if (!player.getInventory().containsItem(item)) {
					//TODO staffs
					player.sendMessage("You don't have enough materials.");
					return false;
				}
			}
			return true;
		}
		
		/**
		 * Gets the button for the id
		 * @param id
		 * @return
		 */
		private static TeleTabButton forId(int id) {
			for (TeleTabButton ttb : values()) {
				if (ttb.buttonId == id)
					return ttb;
			}
			return null;
		}
		
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (int i = 13642; i <= 13648; i++) {
			ObjectDefinition.forId(i).getHandlers().put("option:study", this);
		}
		PluginManager.definePlugin(new TeleTabInterface());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		int id = node.asObject().getId();
		player.setAttribute("ttb:objectid", id);
		player.getInterfaceManager().openComponent(400);
		int bits = 0;
		for (TeleTabButton t : TeleTabButton.values()) {
			if (t.canMake(player))
				bits |= (1 << t.buttonId);
		}
		player.getConfigManager().set(2005, bits);
		return true;
	}
	
	/**
	 * TeleTabInterface.java
	 * @author Clayton Williams
	 * @date Oct 30, 2016
	 */
	private static class TeleTabInterface extends ComponentPlugin {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			ComponentDefinition.put(400, this);
			return this;
		}

		@Override
		public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
			final TeleTabButton ttb = TeleTabButton.forId(button);
			if (ttb != null && ttb.canMake(player)) {
				//player.animate(new Animation(733));
				player.getInterfaceManager().close();
				player.getPulseManager().run(new Pulse(1) {
					@Override
					public boolean pulse() {
						// Todo fix timing and animations
						if (!ttb.canMake(player)) {
							return true;
						}
						if (player.getInventory().freeSlots() == 0) {
							player.sendMessage("You don't have enough space in your inventory to make this.");
							return true;
						}
						//player.animate(new Animation(733));
						if (player.getInventory().remove(ttb.requiredItems)) {
							player.getInventory().add(ttb.tabItem);
							player.getSkills().addExperience(Skills.MAGIC, ttb.xp / 2, true);

							if (ttb == TeleTabButton.VARROCK
									&& (player.getAttribute("ttb:objectid", 0) == Decoration.MAHOGANY_EAGLE_LECTERN.getObjectId()
									|| player.getAttribute("ttb:objectid", 0) == Decoration.MAHOGANY_DEMON_LECTERN.getObjectId())) {
								player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 2, 8);
							}
						}
						//player.animate(new Animation(-1));
						return false;
					}					
				});
			}
			return true;
		}
		
	}

}
