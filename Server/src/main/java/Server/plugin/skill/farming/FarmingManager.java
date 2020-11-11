package plugin.skill.farming;

import core.game.system.SystemLogger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import plugin.skill.farming.compost.CompostManager;
import plugin.skill.farming.pot.SeedlingManager;
import plugin.skill.farming.wrapper.PatchWrapper;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.login.SavingModule;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a managing class used for farming.
 * @author Vexia
 */
public final class FarmingManager implements SavingModule {

	/**
	 * Represents the store of your farming equipment.
	 */
	private final FarmingEquipment equipment = new FarmingEquipment();

	/**
	 * Represents the managing class of composts.
	 */
	private final CompostManager compostManager = new CompostManager();

	/**
	 * Represents the farming patch representations if active.
	 */
	private final List<PatchWrapper> patches = new ArrayList<>();

	/**
	 * Represents the managing class of seedlings.
	 */
	private final SeedlingManager seedlingManager;

	/**
	 * Represents the player instance.
	 */
	private final Player player;

	/**
	 * Constructs a new {@code FarmingManager} {@code Object}.
	 * @param player the player.
	 */
	public FarmingManager(final Player player) {
		this.player = player;
		this.seedlingManager = new SeedlingManager(player);
	}

	@Override
	public void save(ByteBuffer buffer) {
		if (equipment.getContainer().itemCount() != 0) {
			equipment.save(buffer.put((byte) 1));
		}
		if (compostManager.getBins().size() != 0) {
			compostManager.save(buffer.put((byte) 2));
		}
		if (patches.size() != 0) {
			buffer.put((byte) 3);
			buffer.putInt(patches.size());
			for (PatchWrapper wrapper : patches) {
				wrapper.save(buffer);
			}
		}
		if (seedlingManager.getSeedlings().size() > 0) {
			seedlingManager.save(buffer.put((byte) 4));
		}
		buffer.put((byte) 0);
	}

	public void parseWrappers(JSONArray data){
		for (int i = 0; i < data.size(); i++) {
			JSONObject wrapperData = (JSONObject) data.get(i);
			JSONObject cycleData = (JSONObject) wrapperData.get("cycle");

			PatchWrapper wrapper = new PatchWrapper(player, Integer.parseInt(wrapperData.get("wrapperId").toString()));
			wrapper.getCycle().setCompostThreshold(Integer.parseInt(cycleData.get("compostThreshold").toString()));
			wrapper.getCycle().setGrowthTime(Long.parseLong(cycleData.get("growthTime").toString()));
			wrapper.getCycle().setHarvestAmount(Integer.parseInt(cycleData.get("harvestAmount").toString()));
			wrapper.getCycle().setProtection(Boolean.parseBoolean(cycleData.get("protection").toString()));
			if(wrapperData.containsKey("nodeId")) {
				wrapper.setNode(wrapper.getPatch().getNodes()[Integer.parseInt(wrapperData.get("nodeId").toString())]);
			}
			patches.add(wrapper);
		}
	}

	@Override
	public void parse(ByteBuffer buffer) {
		int opcode;
		while ((opcode = buffer.get() & 0xFF) != 0) {
			switch (opcode) {
			case 1:
				equipment.parse(buffer);
				break;
			case 2:
				compostManager.parse(buffer);
				break;
			case 3:
				int size = buffer.getInt();
				PatchWrapper wrapper;
				for (int i = 0; i < size; i++) {
					wrapper = new PatchWrapper(player, buffer.getInt());
					wrapper.parse(buffer);
					patches.add(wrapper);
				}
				break;
			case 4:
				seedlingManager.parse(buffer);
				break;
			}
		}
	}

	/**
	 * Method used to cycle the life of patches.
	 */
	public void cycle() {
		final Iterator<PatchWrapper> iterator = patches.iterator();
		PatchWrapper wrapper;
		while (iterator.hasNext()) {
			wrapper = iterator.next();
			if (wrapper.isDefault()) {
				continue;
			}
			wrapper.getCycle().cycle(player);
		}
	}

	/**
	 * Gets the patch wrapper by the id.
	 * @param wrapperId the id.
	 * @param newInstance if a new one should be created.
	 * @return the wrapper.
	 */
	public PatchWrapper getPatchWrapper(final int wrapperId, boolean newInstance) {
		for (PatchWrapper patch : patches) {
			if (patch.getWrapperId() == wrapperId) {
				return patch;
			}
		}
		if (!newInstance) {
			return null;
		}
		final PatchWrapper patch = new PatchWrapper(player, wrapperId);
		patches.add(patch);
		return patch;
	}

	/**
	 * Gets the patch wrapper by the id.
	 * @param wrapperId the id.
	 * @return the wrapper.
	 */
	public PatchWrapper getPatchWrapper(final int wrapperId) {
		return getPatchWrapper(wrapperId, true);
	}

	/**
	 * Gets the player.
	 * @return The player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the equipment.
	 * @return The equipment.
	 */
	public FarmingEquipment getEquipment() {
		return equipment;
	}

	/**
	 * Gets the patches.
	 * @return The patches.
	 */
	public List<PatchWrapper> getPatches() {
		return patches;
	}

	/**
	 * Gets the compostManager.
	 * @return The compostManager.
	 */
	public CompostManager getCompostManager() {
		return compostManager;
	}

	/**
	 * Gets the seedlingManager.
	 * @return The seedlingManager.
	 */
	public SeedlingManager getSeedlingManager() {
		return seedlingManager;
	}

}
