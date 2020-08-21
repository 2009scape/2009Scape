package plugin.quest.members.dwarfcannon;

import plugin.dialogue.SkillDialogueHandler;
import plugin.dialogue.SkillDialogueHandler.SkillDialogue;
import plugin.skill.SkillPulse;
import plugin.skill.Skills;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.update.flag.context.Animation;
import core.plugin.Plugin;

/**
 * Handles the cannon ball making.
 * @author Vexia
 */
public class CannonBallPlugin extends UseWithHandler {

	/**
	 * The furnaces.
	 */
	private static final int[] FURNACES = new int[] { 4304, 6189, 11010, 11666, 12100, 12809, 18497, 26814, 30021, 30510, 36956, 37651 };

	/**
	 * Constructs a new {@Code CannonBallPlugin} {@Code Object}
	 */
	public CannonBallPlugin() {
		super(2353);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (int id : FURNACES) {
			addHandler(id, OBJECT_TYPE, this);
		}
		return this;
	}

	@Override
	public boolean handle(final NodeUsageEvent event) {
		final Player player = event.getPlayer();
		if (!player.getQuestRepository().isComplete(DwarfCannon.NAME)) {
			player.getDialogueInterpreter().sendDialogue("You need to complete the Dwarf Cannon quest in order to do this.");
			return true;
		}
		SkillDialogueHandler dialogue = new SkillDialogueHandler(player, SkillDialogue.ONE_OPTION, event.getUsedItem()) {

			@Override
			public void create(final int amount, int index) {
				player.getPulseManager().run(new CannonBallPulse(player, event.getUsedItem(), amount));
			}

			@Override
			public int getAll(int index) {
				return player.getInventory().getAmount(event.getUsedItem());
			}

		};
		dialogue.open();
		return true;
	}

	/**
	 * Handles the pulse used to create a cannon ball.
	 * @author Vexia
	 */
	public class CannonBallPulse extends SkillPulse<Item> {

		/**
		 * The amount to make.
		 */
		private int amount;

		/**
		 * The ticks.
		 */
		private int ticks;

		/**
		 * Constructs a new {@Code CannonBallPulse} {@Code Object}
		 * @param player the player.
		 * @param node the node.
		 * @param amount the amount.
		 */
		public CannonBallPulse(Player player, Item node, int amount) {
			super(player, node);
			this.amount = amount;
		}

		@Override
		public boolean checkRequirements() {
			if (player.getSkills().getLevel(Skills.SMITHING) < 35) {
				player.getDialogueInterpreter().sendDialogue("You need a Smithing level of at least 35 in order to do this.");
				return false;
			}
			if (!player.getInventory().contains(4, 1)) {
				player.getDialogueInterpreter().sendDialogue("You need an ammo mould in order to make a cannon ball.");
				return false;
			}
			if (!player.getInventory().containsItem(node)) {
				stop();
				return false;
			}
			return true;
		}

		@Override
		public void animate() {
			if (ticks == 0 || ticks % 5 == 0) {
				player.sendMessage("You heat the steel bar into a liquid state.");
				player.animate(Animation.create(899));
			} else if (ticks % 3 == 0) {
				player.sendMessage("You pour the molten metal into your cannonball mould.");
			}
		}

		@Override
		public boolean reward() {
			if (++ticks % 5 != 0) {
				return false;
			}
			amount--;
			if (player.getInventory().remove(node)) {
				player.getInventory().add(new Item(2, 4));
				player.getSkills().addExperience(Skills.SMITHING, 25.6, true);
				player.sendMessage("You remove the cannonballs from the mould.");
			}
			return amount <= 0;
		}

		@Override
		public void message(int type) {
		}
	}

}
