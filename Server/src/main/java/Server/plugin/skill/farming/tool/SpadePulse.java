package plugin.skill.farming.tool;

import core.game.container.impl.EquipmentContainer;
import plugin.skill.Skills;
import plugin.skill.farming.FarmingConstant;
import plugin.skill.farming.FarmingPatch;
import plugin.skill.farming.patch.*;
import plugin.skill.farming.wrapper.PatchWrapper;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.tools.RandomFunction;

/**
 * Represents the pulse used when spade.
 * @author 'Vexia
 * @version 1.0
 */
public final class SpadePulse extends ToolAction {

	/**
	 * Represents the spade animation to use.
	 */
	private static final Animation SPADE_ANIMATION = new Animation(830);

	/**
	 * Represents the herb picking animation.
	 */
	private static final Animation HERB_ANIMATION = new Animation(2282);

	/**
	 * Represents the roots obtained when digging up a stump.
	 */
	private static final Item[] ROOTS = new Item[] { new Item(6043), new Item(6045), new Item(6047), new Item(6049), new Item(6051) };

	/**
	 * Represents the forced command.
	 */
	private String command;

	/**
	 * Constructs a new {@code RakePulse} {@code Object}.
	 */
	public SpadePulse() {
		super(null, null, null);
	}

	/**
	 * Constructs a new {@code RakePulse} {@code Object}.
	 * @param player the player.
	 * @param wrapper the wrapper.
	 */
	public SpadePulse(final Player player, final PatchWrapper wrapper) {
		super(player, wrapper, null);
	}

	@Override
	public ToolAction newInstance(Player player, PatchWrapper wrapper, Item item) {
		return new SpadePulse(player, wrapper);
	}

	@Override
	public boolean pulse() {
		sendMessage();
		if (!isReward(3)) {
			return false;
		}
		boolean tree = false;
		if (wrapper.getNode() != null && wrapper.getNode() instanceof TreeNode) {
			TreeNode t = ((TreeNode) wrapper.getNode());
			if (t.isStump(wrapper.getCycle())) {
				tree = true;
			}
		}
		animate();
		if ((wrapper.getPatch() == FarmingPatch.BUSHES && !command.equals("pick"))
				|| (wrapper.getPatch() == FarmingPatch.FRUIT_TREE && !command.equals("pick"))
				|| wrapper.getCycle().getDeathHandler().isDead()
				|| wrapper.getCycle().getDiseaseHandler().isDiseased()
				|| tree) {
			return clearPatch();
		} else if (wrapper.getPatch() == FarmingPatch.BUSHES
				|| wrapper.getPatch() == FarmingPatch.FRUIT_TREE
				|| wrapper.getPatch() == FarmingPatch.CACTUS
				|| wrapper.getPatch() == FarmingPatch.CALQUAT) {
			return pickBush();
		} else if (wrapper.getCycle().getGrowthHandler().isFullGrown()) {
			return !harvestPatch();
		} else if (wrapper.hasScarecrow()) {
			return removeScarecrow();
		}
		return true;
	}

	@Override
	public void stop() {
		super.stop();
		player.getAnimator().reset();
	}

	/**
	 * Method used to send the beginning message.
	 */
	private void sendMessage() {
		if (ticks == 0) {
			if ((wrapper.getPatch() != FarmingPatch.BUSHES)) {
				if ((wrapper.getPatch() == FarmingPatch.TREE || wrapper.getPatch() == FarmingPatch.FRUIT_TREE) && !command.equals("pick")) {
					if (wrapper.getNode().isStump(wrapper.getCycle())) {
						player.getPacketDispatch().sendMessage("You start digging up the tree stump.");
					} else {
						player.getPacketDispatch().sendMessage("You start digging the farming patch...");
					}
				} else if (wrapper.getCycle().getGrowthHandler().isFullGrown()) {
					player.getPacketDispatch().sendMessage("You begin to harvest the " + wrapper.getName() + ".");
				} else {
					player.getPacketDispatch().sendMessage("You start digging the farming patch...");
				}
			}
			animate();
		}
	}

	/**
	 * Method used to animate the player.
	 */
	public void animate() {
		if (command != null && command.equals("pick")) {
			player.animate(HERB_ANIMATION);
			return;
		}
		player.animate(SPADE_ANIMATION);
	}

	/**
	 * Method used to clear the patch.
	 * @return {@code True} if cleared.
	 */
	private boolean clearPatch() {
		boolean success = RandomFunction.random(5) < 3;
		if (success) {
			if (wrapper.getPatch() != FarmingPatch.TREE && wrapper.getPatch() != FarmingPatch.FRUIT_TREE) {
				player.getPacketDispatch().sendMessage("You have successfully cleared this patch for new crops.");
			} else {
				if (wrapper.getNode().isStump(wrapper.getCycle())) {
					if (wrapper.getPatch() == FarmingPatch.TREE) {
						double xp = 6;

						// Check for Falador shield bonus
						if (hasFaladorShield(player) && player.getLocation().withinDistance(PatchProtection.FALADOR.getFlowerLocation(), 20)) {
							xp = xp * 1.1;
						}
						player.getSkills().addExperience(Skills.FARMING, xp, true);
						player.getInventory().add(ROOTS[Trees.forNode(wrapper.getNode()).ordinal()], player);
					}
					player.getPacketDispatch().sendMessage("You dig up the tree stump.");
				} else {
					player.getPacketDispatch().sendMessage("You have successfully cleared this patch for new crops.");
				}
			}
			wrapper.getCycle().clear(player);
		}
		return success;
	}

	/**
	 * Method used to remove the scarecrow.
	 * @return {@code True} if so.
	 */
	private boolean removeScarecrow() {
		wrapper.getCycle().clear(player);
		player.getInventory().add(FarmingConstant.SCARECROW);
		player.getPacketDispatch().sendMessage("You remove the scarecrow.");
		return true;
	}

	/**
	 * Method used to harvest the patch.
	 * @return {@code True} if harvested.
	 */
	private boolean harvestPatch() {
		final Item item = wrapper.getNode().getProduct();
	    player.getInventory().add(item);
		double xp = wrapper.getNode().getExperiences()[1];
		// Check for Falador shield bonus
		if (hasFaladorShield(player) && player.getLocation().withinDistance(PatchProtection.FALADOR.getFlowerLocation(), 20)) {
			xp = xp * 1.1;
		}
		player.getSkills().addExperience(Skills.FARMING, xp, true);

		// Pick poison ivy from your bush Farming patch in Varrock<br><br>(west of Champions' Guild
		if (wrapper.getNode() == Bushes.POISON_IVY.getFarmingNode() && player.getLocation().withinDistance(Location.create(3183,3358,0))) {
			player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 2, 0);
		}

		wrapper.getCycle().setHarvestAmount(wrapper.getCycle().getHarvestAmount() - 1);
		if (wrapper.getCycle().getHarvestAmount() < 1) {
			wrapper.getCycle().clear(player);
			player.getPacketDispatch().sendMessage("The " + wrapper.getName() + " patch is now empty.");
		}
		return wrapper.getNode() == null || player.getInventory().hasSpaceFor(wrapper.getNode().getProduct()) && wrapper.getCycle().getHarvestAmount() > 0;
	}

	/**
	 * Method used to pick the bush.
	 * @return {@code True} if so.
	 */
	public boolean pickBush() {
		return wrapper.getNode().pick(wrapper.getCycle());
	}

	@Override
	public boolean canInteract(String command) {
		final boolean needSpade = (wrapper.getPatch() != FarmingPatch.FRUIT_TREE && wrapper.getPatch() != FarmingPatch.FLOWER && wrapper.getPatch() != FarmingPatch.BUSHES);
		if (!player.getInventory().containsItem(FarmingConstant.SPADE) && needSpade) {
			player.getPacketDispatch().sendMessage("You need a spade to do that.");
			return false;
		}
		if (wrapper.hasScarecrow()) {
			if (!player.getInventory().hasSpaceFor(FarmingConstant.SCARECROW)) {
				player.getPacketDispatch().sendMessage("You don't have enough inventory space.");
				return false;
			}
			return true;
		}
		if (wrapper.isWeedy() || wrapper.isEmpty()) {
			player.getPacketDispatch().sendMessage("There aren't any crops in this patch to dig up.");
			return false;
		}
		boolean nodeIsTree = wrapper.getNode() != null && wrapper.getNode() instanceof TreeNode;
		if (((wrapper.getPatch() == FarmingPatch.BUSHES && ((PickingNode) wrapper.getNode()).getProductAmount(wrapper.getState()) != 0)
				&& command.equals("force")
				&& !wrapper.getCycle().getDiseaseHandler().isDiseased()
				&& !wrapper.getCycle().getDeathHandler().isDead())
				|| (wrapper.getCycle().getGrowthHandler().isGrowing() && !wrapper.getCycle().getGrowthHandler().isFullGrown())
				&& !wrapper.getCycle().getDiseaseHandler().isDiseased()
				&& (!nodeIsTree || !((TreeNode) wrapper.getNode()).isStump(wrapper.getCycle()))) {
			player.getDialogueInterpreter().sendDialogues(player, null, "Dig up these healthy plants? Why would I want to do", "that?");
			return false;
		}
		if ((wrapper.getPatch() == FarmingPatch.TREE || wrapper.getPatch() == FarmingPatch.FRUIT_TREE && !command.equals("pick"))
				&& !wrapper.getCycle().getDeathHandler().isDead()
				&& !wrapper.getCycle().getDiseaseHandler().isDiseased()) {
			if (!wrapper.getNode().isStump(wrapper.getCycle())) {
				player.getPacketDispatch().sendMessage("You can only dig up tree stumps.");
				return false;
			}
		}
		if (wrapper.getCycle().getGrowthHandler().isFullGrown() && !player.getInventory().hasSpaceFor(wrapper.getNode().getProduct())) {
			player.getPacketDispatch().sendMessage("You don't have enough inventory space.");
			return false;
		}
		this.command = command;
		return true;
	}

	boolean hasFaladorShield(Player player) {
		Item shield = player.getEquipment().get(EquipmentContainer.SLOT_SHIELD);
		return shield != null && (shield.getId() == 14577 || shield.getId() == 14580);
	}
}
