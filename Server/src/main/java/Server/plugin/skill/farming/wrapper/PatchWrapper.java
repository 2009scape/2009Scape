package plugin.skill.farming.wrapper;

import core.cache.def.impl.VarbitDefinition;
import core.cache.def.impl.ObjectDefinition;
import plugin.skill.farming.FarmingNode;
import plugin.skill.farming.FarmingPatch;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.login.SavingModule;

import java.nio.ByteBuffer;

/**
 * A representation of a farming patch.
 * @author 'Vexia
 */
public final class PatchWrapper implements SavingModule {

	/**
	 * Represents the patch cycle handler.
	 */
	private final PatchCycle cycle;

	/**
	 * Represents the patch interactor handler.
	 */
	private final PatchInteractor interactor;

	/**
	 * Represents the player instance.
	 */
	private final Player player;

	/**
	 * Represents the farming patch type.
	 */
	private final FarmingPatch patch;

	/**
	 * Represents the farming node on the patch.
	 */
	private FarmingNode node;

	/**
	 * Represents the wrapper id of the object.
	 */
	private final int wrapperId;

	/**
	 * Constructs a new {@code PatchWrapper} {@code Object}.
	 * @param wrapperId the wrapper id.
	 */
	public PatchWrapper(final Player player, final int wrapperId) {
		this.player = player;
		this.wrapperId = wrapperId;
		this.patch = FarmingPatch.forObject(wrapperId);
		this.cycle = new PatchCycle(this);
		this.interactor = new PatchInteractor(this);
	}

	@Override
	public void save(ByteBuffer buffer) {
		buffer.putInt(wrapperId);
		cycle.save(buffer.put((byte) 1));
		if (node != null) {
			buffer.put((byte) 2);
			buffer.putInt(patch.getNodePosition(node));
		}
		buffer.put((byte) 0);
	}

	@Override
	public void parse(ByteBuffer buffer) {
		int opcode;
		while ((opcode = buffer.get() & 0xFF) != 0) {
			switch (opcode) {
			case 1:
				cycle.parse(buffer);
				break;
			case 2:
				node = patch.getNodes()[buffer.getInt()];
				break;
			}
		}
	}

	/**
	 * Checks if the patch is set default.
	 * @return {@code True} if so.
	 */
	public boolean isDefault() {
		return getState() == 0;
	}

	/**
	 * Checks if the patch has weeds.
	 * @return {@code True} if so.
	 */
	public boolean isWeedy() {
		return getState() < 3 || hasScarecrow();
	}

	/**
	 * Checks if the patch is empty.
	 * @return {@code True} if so.
	 */
	public boolean isEmpty() {
		return getState() == 3 || (getPatch() == FarmingPatch.FLOWER && getState() == 36);
	}

	/**
	 * Checks if the patch has a scarecrow.
	 * @return {@code True} if so.
	 */
	public boolean hasScarecrow() {
		return getPatch() == FarmingPatch.FLOWER && getState() > 32 && getState() < 37;
	}

	/**
	 * Gets the patches name.
	 * @return the name.
	 */
	public String getName() {
		if (patch == null) {
			return "report-me";
		}
		return patch.getName();
	}

	/**
	 * Method used to add a config value.
	 * @param value the value.
	 */
	public void addConfigValue(final int value) {
		player.varpManager.get(getConfigId()).setVarbit(getBitShift(),value);
		player.getConfigManager().set(getConfigId(), (player.getConfigManager().get(getConfigId()) - getConfigValue()) + (value << getBitShift()), true);
	}

	/**
	 * Method used to get the config value of this compost bin.
	 * @return the value set.
	 */
	public int getConfigValue() {
		return getState() << getBitShift();
	}

	/**
	 * Gets the wrapper config state.
	 * @return {@code True} if so.
	 */
	public int getState() {
		VarbitDefinition def = VarbitDefinition.forObjectID(ObjectDefinition.forId(wrapperId).getVarbitID());
		if (def == null) {
			return 0;
		}
		return def.getValue(player);
	}

	/**
	 * Gets the config id.
	 * @return the id.
	 */
	public int getConfigId() {
		return VarbitDefinition.forObjectID(ObjectDefinition.forId(wrapperId).getVarbitID()).getConfigId();
	}

	/**
	 * Gets the bitshift for the wrapper.
	 * @return the bitshift.
	 */
	public int getBitShift() {
		return VarbitDefinition.forObjectID(ObjectDefinition.forId(wrapperId).getVarbitID()).getBitShift();
	}

	/**
	 * Gets the patch.
	 * @return The patch.
	 */
	public FarmingPatch getPatch() {
		return patch;
	}

	/**
	 * Gets the wrapperId.
	 * @return The wrapperId.
	 */
	public int getWrapperId() {
		return wrapperId;
	}

	/**
	 * Gets the interactor.
	 * @return The interactor.
	 */
	public PatchInteractor getInteractor() {
		return interactor;
	}

	/**
	 * Gets the cycle.
	 * @return The cycle.
	 */
	public PatchCycle getCycle() {
		return cycle;
	}

	/**
	 * Gets the node.
	 * @return The node.
	 */
	public FarmingNode getNode() {
		return node;
	}

	/**
	 * Sets the node.
	 * @param node The node to set.
	 */
	public void setNode(FarmingNode node) {
		this.node = node;
	}

	/**
	 * Gets the player.
	 * @return The player.
	 */
	public Player getPlayer() {
		return player;
	}

}
