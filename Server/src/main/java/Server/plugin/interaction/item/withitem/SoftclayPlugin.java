package plugin.interaction.item.withitem;

import core.game.node.entity.player.link.diary.DiaryType;
import plugin.dialogue.SkillDialogueHandler;
import plugin.dialogue.SkillDialogueHandler.SkillDialogue;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the plugin used to make soft clay.
 * @author 'Vexia
 * @date 1/14/14
 */
@InitializablePlugin
public final class SoftclayPlugin extends UseWithHandler {

	/**
	 * Represents the bucket of water item.
	 */
	private static final Item BUCKET_OF_WATER = new Item(1929);

	/**
	 * Represents the jug of water item.
	 */
	private static final Item JUG_OF_WATER = new Item(1937);

	/**
	 * Represents the jug item.
	 */
	private static final Item JUG = new Item(1935);

	/**
	 * Represents the clay item.
	 */
	private static final Item CLAY = new Item(434);

	/**
	 * Represents the soft clay item.
	 */
	private static final Item SOFT_CLAY = new Item(1761);

	/**
	 * Represents the empty bucket item.
	 */
	private static final Item BUCKET = new Item(1925);

	/**
	 * Constructs a new {@code SoftclayPlugin} {@code Object}.
	 */
	public SoftclayPlugin() {
		super(434);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(1929, ITEM_TYPE, this);
		addHandler(1937, ITEM_TYPE, this);
		return this;
	}

	@Override
	public boolean handle(final NodeUsageEvent event) {
		final Player player = event.getPlayer();
		SkillDialogueHandler handler = new SkillDialogueHandler(player, SkillDialogue.ONE_OPTION, SOFT_CLAY) {
			@Override
			public void create(final int amount, int index) {
				player.getPulseManager().run(new Pulse(2, player) {
					int count;

					@Override
					public boolean pulse() {
						if (!SoftclayPlugin.this.create(player, event)) {
							return true;
						}
						return ++count >= amount;
					}
				});
			}

			@Override
			public int getAll(int index) {
				return player.getInventory().getAmount(CLAY);
			}
		};
		if (player.getInventory().getAmount(CLAY) == 1) {
			create(player, event);
		} else {
			handler.open();
		}
		return true;
	}

	/**
	 * Creates a soft clay.
	 * @param player the player.
	 * @param event the event.
	 * @return {@code True} if so.
	 */
	private boolean create(final Player player, NodeUsageEvent event) {
		boolean bucket = event.getUsedItem().getId() == 1929 || event.getBaseItem().getId() == 1929;
		if (!player.getInventory().containsItem(CLAY)) {
			return false;
		}
		if ((!bucket ? player.getInventory().remove(JUG_OF_WATER) : player.getInventory().remove(BUCKET_OF_WATER)) && player.getInventory().remove(CLAY)) {
			player.getInventory().add(SOFT_CLAY);
			player.getInventory().add(bucket ? BUCKET : JUG);
			player.getPacketDispatch().sendMessage("You mix the clay and water. You now have some soft, workable clay.");
			// Make some soft clay in the Barbarian Village
			if (player.getViewport().getRegion().getId() == 12341) {
				player.getAchievementDiaryManager().finishTask(player, DiaryType.LUMBRIDGE, 0, 6);
			}
			return true;
		}
		return false;
	}
}
