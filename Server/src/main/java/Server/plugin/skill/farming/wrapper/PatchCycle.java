package plugin.skill.farming.wrapper;

import core.game.system.SystemLogger;
import plugin.skill.farming.FarmingNode;
import plugin.skill.farming.wrapper.handler.DeathHandler;
import plugin.skill.farming.wrapper.handler.DiseaseHandler;
import plugin.skill.farming.wrapper.handler.GrowthHandler;
import plugin.skill.farming.wrapper.handler.WaterHandler;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.login.SavingModule;
import core.game.node.entity.player.link.SavedData;

import java.nio.ByteBuffer;

/**
 * Represents a patches life cycle.
 * @author Vexia
 */
public final class PatchCycle implements SavingModule {

	/**
	 * Represents the growth effect handler.
	 */
	private final GrowthHandler growthHandler = new GrowthHandler(this);

	/**
	 * Represents the disease effect handler.
	 */
	private final DiseaseHandler diseaseHandler = new DiseaseHandler(this);

	/**
	 * Represents the death effect handler.
	 */
	private final DeathHandler deathHandler = new DeathHandler(this);

	/**
	 * Represents the watering effect handler.
	 */
	private final WaterHandler waterHandler = new WaterHandler(this);

	/**
	 * Represents the patch wrapper.
	 */
	private final PatchWrapper wrapper;

	/**
	 * Represents the player instance.
	 */
	private final Player player;

	/**
	 * Represents the next growth update.
	 */
	private long growthTime;

	/**
	 * Represents the compost threshold.
	 */
	private int compostThreshold;

	/**
	 * Represents the amount left to harvest.
	 */
	private int harvestAmount;

	/**
	 * Represents if the patch has protection(farmers, flowers, etc..)
	 */
	private boolean protection;

	/**
	 * Constructs a new {@code PatchCycle} {@code Object}.
	 * @param wrapper the wrapper.
	 */
	public PatchCycle(final PatchWrapper wrapper) {
		this.wrapper = wrapper;
		this.player = wrapper.getPlayer();
	}

	@Override
	public void save(ByteBuffer buffer) {
		/**
		 * Binary saving has been deprecated, for actual save location start at PlayerSaver.kt, saveFarming()
		 */
	}

	@Override
	public void parse(ByteBuffer buffer) {
		int opcode;
		while ((opcode = buffer.get() & 0xFF) != 0) {
			switch (opcode) {
			case 1:
				compostThreshold = buffer.getInt();
				break;
			case 2:
				buffer.getLong();// N/A
				break;
			case 3:
				growthTime = buffer.getLong();
				break;
			case 4:
				harvestAmount = buffer.getInt();
				break;
			case 5:
				SavedData.getBoolean(buffer.get());// N/A
				break;
			case 6:
				protection = SavedData.getBoolean(buffer.get());
				break;
			}
		}
	}

	/**
	 * Method used to cycle a patch wrapper.
	 * @param player the player.
	 */
	public void cycle(final Player player) {
		if (!getGrowthHandler().canGrow()) {
			return;
		}
		getGrowthHandler().handle();
	}

	/**
	 * Method used to clear a patch.
	 * @param player the player.
	 */
	public void clear(final Player player) {
		compostThreshold = 0;
		harvestAmount = 0;
		protection = false;
		wrapper.setNode(null);
		wrapper.addConfigValue(3);
		growthHandler.setGrowthUpdate();
	}

	/**
	 * Method wrapper to add a config value.
	 * @param value the value.
	 */
	public void addConfigValue(int value) {
		getWrapper().addConfigValue(value);
	}

	/**
	 * Gets the passed stages.
	 * @return the stages passed.
	 */
	public int getPassedStages() {
		long timeValue = getGrowthTime();
		int stages = 1;
		int cycles = getGrowthHandler().isWeedy() ? 3 : wrapper.getNode().getGrowthCycles();
		for (int i = 0; i < cycles; i++) {
			timeValue += getGrowthHandler().getGrowthUpdate();
			if (timeValue <= System.currentTimeMillis()) {
				stages++;
			}
		}
		return stages > (getGrowthHandler().isWeedy() ? 3 : wrapper.getNode().getGrowthCycles()) ? (getGrowthHandler().isWeedy() ? 3 : wrapper.getNode().getGrowthCycles()) : stages;
	}

	/**
	 * Sets the growthTime.
	 * @param growthTime The growthTime to set.
	 */
	public void setGrowthTime(long growthTime) {
		this.growthTime = growthTime;
	}

	/**
	 * Sets the harvestAmount.
	 * @param harvestAmount The harvestAmount to set.
	 */
	public void setHarvestAmount(int harvestAmount) {
		this.harvestAmount = harvestAmount;
	}

	/**
	 * Sets the compostThreshold.
	 * @param compostThreshold The compostThreshold to set.
	 */
	public void setCompostThreshold(int compostThreshold) {
		this.compostThreshold = compostThreshold;
	}

	/**
	 * Checks if the patch is composted.
	 * @return {@code True} if so.
	 */
	public boolean isComposted() {
		return compostThreshold > 0;
	}

	/**
	 * Gets hte compost name.
	 * @return the name.
	 */
	public String getCompostName() {
		return (compostThreshold == 2) || (compostThreshold == 3) ? "supercompost" : compostThreshold == 1 ? "compost" : "";
	}

	/**
	 * Gets the compostThreshold.
	 * @return The compostThreshold.
	 */
	public int getCompostThreshold() {
		return compostThreshold;
	}

	/**
	 * Gets the wrapper.
	 * @return The wrapper.
	 */
	public PatchWrapper getWrapper() {
		return wrapper;
	}

	/**
	 * Gets the growthTime.
	 * @return The growthTime.
	 */
	public long getGrowthTime() {
		return growthTime;
	}

	/**
	 * Gets the harvestAmount.
	 * @return The harvestAmount.
	 */
	public int getHarvestAmount() {
		return harvestAmount;
	}

	/**
	 * Gets the farming node.
	 * @return the node.
	 */
	public FarmingNode getNode() {
		return wrapper.getNode();
	}

	/**
	 * Gets the state.
	 * @return the state.
	 */
	public int getState() {
		return wrapper.getState();
	}

	/**
	 * Gets the player.
	 * @return The player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the diseaseHandler.
	 * @return The diseaseHandler.
	 */
	public DiseaseHandler getDiseaseHandler() {
		return diseaseHandler;
	}

	/**
	 * Gets the deathHandler.
	 * @return The deathHandler.
	 */
	public DeathHandler getDeathHandler() {
		return deathHandler;
	}

	/**
	 * Gets the waterHandler.
	 * @return The waterHandler.
	 */
	public WaterHandler getWaterHandler() {
		return waterHandler;
	}

	/**
	 * Gets the growthHandler.
	 * @return The growthHandler.
	 */
	public GrowthHandler getGrowthHandler() {
		return growthHandler;
	}

	/**
	 * Gets the hasProtection.
	 * @return The hasProtection.
	 */
	public boolean isProtected() {
		return protection;
	}

	/**
	 * Sets the hasProtection.
	 * @param hasProtection The hasProtection to set.
	 */
	public void setProtection(boolean hasProtection) {
		this.protection = hasProtection;
	}
}
