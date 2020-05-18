package org.crandor.game.content.skill.member.summoning.pet;

import org.crandor.game.content.skill.member.summoning.familiar.Familiar;
import org.crandor.game.content.skill.member.summoning.familiar.FamiliarSpecial;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents a pet.
 * @author Emperor
 * @author 'Vexia
 */
public final class Pet extends Familiar {

	/**
	 * The item id.
	 */
	private final int itemId;

	/**
	 * The pet details.
	 */
	private final PetDetails details;

	/**
	 * The growth rate of the pet.
	 */
	private double growthRate;

	/**
	 * The pets type.
	 */
	private final Pets pet;

	/**
	 * Constructs a new {@code Pet} {@code Object}.
	 * @param owner the owner.
	 * @param details the details.
	 * @param itemId the itemId.
	 * @param id the id.
	 */
	public Pet(Player owner, final PetDetails details, int itemId, int id) {
		super(owner, id, -1, -1, -1);
		this.pet = Pets.forId(itemId);
		this.details = details;
		this.itemId = itemId;
		this.growthRate = pet.getGrowthRate();
	}

	@Override
	public void sendConfiguration() {
		owner.getConfigManager().set(1175, ((int) details.getGrowth() << 1) | ((int) details.getHunger() << 9));
		owner.getConfigManager().set(1174, getId());
		owner.getConfigManager().set(448, itemId);
	}

	@Override
	public void handleTickActions() {
		final PetDetails petDetails = details;
		if (getPet().getFood().length > 0) {
			petDetails.updateHunger(petDetails.getStage() == 0 ? 0.025 : 0.018);
		}
		double hunger = petDetails.getHunger();
		if (hunger >= 90.0 && hunger < 90.025) {
			owner.getPacketDispatch().sendMessage("<col=ff0000>Your pet is starving, feed it before it runs off.</col>");
		}
		double growth = petDetails.getGrowth();
		if (pet.getGrowthRate() > 0.000) {
			petDetails.updateGrowth(pet.getGrowthRate());
			if (growth == 100.0) {
				growNextStage();
			}
		}
		if ((!isInvisible() && owner.getLocation().getDistance(getLocation()) > 12) || (isInvisible() && ticks % 25 == 0)) {
			if (!call()) {
				setInvisible(true);
			}
		} else if (!getPulseManager().hasPulseRunning()) {
			startFollowing();
		}
		owner.getConfigManager().set(1175, ((int) details.getGrowth() << 1) | ((int) details.getHunger() << 9));
	}

	/**
	 * Method used to grow the npc's next stage.
	 */
	public final void growNextStage() {
		if (details.getStage() == 3) {
			return;
		}
		if (pet == null) {
			return;
		}
		int npcId = pet.getNpcId(details.getStage() + 1);
		if (npcId < 1) {
			return;
		}
		details.setStage(details.getStage() + 1);
		int itemId = pet.getItemId(details.getStage());
		if (pet.getNpcId(details.getStage() + 1) > 0) {
			details.updateGrowth(-100.0);
		}
		clear();
		Pet newPet = new Pet(owner, details, itemId, npcId);
		newPet.growthRate = growthRate;
		owner.getFamiliarManager().setFamiliar(newPet);
		owner.getPacketDispatch().sendMessage("<col=ff0000>Your pet has grown larger.</col>");
		owner.getFamiliarManager().spawnFamiliar();
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return this;
	}

	@Override
	protected boolean specialMove(FamiliarSpecial special) {
		return false;
	}

	@Override
	public boolean isCombatFamiliar() {
		return false;
	}

	/**
	 * Gets the growthRate.
	 * @return The growthRate.
	 */
	public double getGrowthRate() {
		return growthRate;
	}

	/**
	 * Sets the growthRate.
	 * @param growthRate The growthRate to set.
	 */
	public void setGrowthRate(double growthRate) {
		this.growthRate = growthRate;
	}

	/**
	 * Gets the itemId.
	 * @return The itemId.
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * Gets the details.
	 * @return The details.
	 */
	public PetDetails getDetails() {
		return details;
	}

	/**
	 * Gets the pet.
	 * @return The pet.
	 */
	public Pets getPet() {
		return pet;
	}

	@Override
	public int[] getIds() {
		return new int[] { 761, 762, 763, 764, 765, 766, 3505, 3598, 6969, 7259, 7260, 6964, 7249, 7251, 6960, 7241, 7243, 6962, 7245, 7247, 6966, 7253, 7255, 6958, 7237, 7239, 6915, 7277, 7278, 7279, 7280, 7018, 7019, 7020, 6908, 7313, 7316, 6947, 7293, 7295, 7297, 7299, 6911, 7261, 7263, 7265, 7267, 7269, 6919, 7301, 7303, 7305, 7307, 6949, 6952, 6955, 6913, 7271, 7273, 6945, 7319, 7321, 7323, 7325, 7327, 6922, 6942, 7210, 7212, 7214, 7216, 7218, 7220, 7222, 7224, 7226, 6900, 6902, 6904, 6906, 768, 769, 770, 771, 772, 773, 3504, 6968, 7257, 7258, 6965, 7250, 7252, 6961, 7242, 7244, 6963, 7246, 7248, 6967, 7254, 7256, 6859, 7238, 7240, 6916, 7281, 7282, 7283, 7284, 7015, 7016, 7017, 6909, 7314, 7317, 6948, 7294, 7296, 7298, 7300, 6912, 7262, 7264, 7266, 7268, 7270, 6920, 7302, 7304, 7306, 7308, 6950, 6953, 6956, 6914, 7272, 7274, 6946, 7320, 7322, 7324, 7326, 7328, 6923, 6943, 7211, 7213, 7215, 7217, 7219, 7221, 7223, 7225, 7227, 6901, 6903, 6905, 6907, 774, 775, 776, 777, 778, 779, 3503, 6951, 6954, 6957 };
	}

}
