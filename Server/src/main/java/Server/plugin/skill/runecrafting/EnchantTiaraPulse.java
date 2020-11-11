package plugin.skill.runecrafting;

import core.game.content.ItemNames;
import core.game.node.entity.impl.Animator;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import plugin.skill.SkillPulse;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;

/**
 * Represents the enchanting of a tiara pulse.
 * @author 'Vexia
 * @date 02/11/2013
 */
public class EnchantTiaraPulse extends SkillPulse<Item> {

	/**
	 * Represents the tiara.
	 */
	private final Tiara tiara;

	/**
	 * Represents the item tiara.
	 */
	private final Item TIARA = new Item(5525);

	private static final Animation ANIMATION = new Animation(791, Animator.Priority.HIGH);
	private static final Graphics GRAPHICS = new Graphics(186, 100);

	/**
	 * Represents the amount.
	 */
	private int amount;

	/**
	 * Constructs a new {@code EnchantTiaraPulse.java} {@code Object}.
	 * @param player the player.
	 * @param node the node.
	 */
	public EnchantTiaraPulse(Player player, Item node, final Tiara tiara, final int amount) {
		super(player, node);
		this.tiara = tiara;
		this.amount = amount;
	}

	@Override
	public void start() {
		super.start();
		int tiaraAmt = player.getInventory().getAmount(TIARA);
		int talsminAmt = player.getInventory().getAmount(node);
		if (tiaraAmt > talsminAmt) {
			amount = talsminAmt;
		} else {
			amount = tiaraAmt;
		}
	}

	@Override
	public boolean checkRequirements() {
		if (!player.getInventory().containsItem(TIARA)) {
			player.getPacketDispatch().sendMessage("You need a tiara.");
			return false;
		}
		return true;
	}

	@Override
	public void animate() {
		player.animate(ANIMATION);
		player.graphics(GRAPHICS);
	}

	@Override
	public boolean reward() {
		if (getDelay() == 1) {
			setDelay(2);
			return false;
		}
		if (player.getInventory().remove(TIARA) && player.getInventory().remove(tiara.getTalisman().getTalisman())) {
			player.getInventory().add(tiara.getTiara());
			player.getSkills().addExperience(Skills.RUNECRAFTING, tiara.getExperience(), true);

			if (tiara == Tiara.AIR) {
				player.getAchievementDiaryManager().finishTask(player, DiaryType.FALADOR, 0, 11);
			}
			// Craft an earth tiara on the Earth Altar
			if (tiara == Tiara.EARTH) {
				player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 1, 11);
			}
		}
		amount--;
		return amount == 0;
	}

	@Override
	public void message(int type) {
		if (type == 1) {
			player.getPacketDispatch().sendMessage("You bind the power of the talisman into your tiara.");
		}
	}

}
