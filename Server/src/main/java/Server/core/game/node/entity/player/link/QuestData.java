package core.game.node.entity.player.link;

import core.game.node.entity.player.info.login.SavingModule;
import core.game.node.item.Item;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Represents the quest data to save.
 * @author 'Vexia
 */
public final class QuestData implements SavingModule {

	/**
	 * Represents the cooks assist attribute array.
	 */
	private final boolean[] cooksAssistant = new boolean[4];

	/**
	 * Represents the demon slayer boolean array.
	 */
	private final boolean[] demonSlayer = new boolean[2];

	/**
	 * Represents the draynor levers for ernest the chicken.
	 */
	private final boolean[] draynorLever = new boolean[6];

	/**
	 * Represents the dragon slayer attribute array.
	 */
	private final boolean[] dragonSlayer = new boolean[9];

	/**
	 * The desert treasure items.
	 */
	private final Item[] desertTreasure = new Item[7];

	/**
	 * Represents the dragon slayer planks.
	 */
	private int dragonSlayerPlanks;

	/**
	 * Represents if the gardener has attacked.
	 */
	private boolean gardenerAttack;

	/**
	 * Represents if they talked to drezel.
	 */
	private boolean talkedDrezel;

	private int witchsExperimentStage;


	private boolean witchsExperimentKilled;


	/**
	 * Constructs a new {@code QuestData} {@code Object}.
	 */
	public QuestData() {
		Arrays.fill(draynorLever, true);
		populateDesertTreasureNode();
	}

	@Override
	public void save(ByteBuffer buffer) {
		SavedData.save(buffer, draynorLever, 1);
		SavedData.save(buffer, dragonSlayer, 2);
		SavedData.save(buffer, (byte) dragonSlayerPlanks, 3);
		SavedData.save(buffer, demonSlayer, 4);
		SavedData.save(buffer, cooksAssistant, 5);
		SavedData.save(buffer, gardenerAttack, 6);
		SavedData.save(buffer, talkedDrezel, 7);
		saveDesertTreasureNode(buffer);
		SavedData.save(buffer, witchsExperimentStage, 9);
		SavedData.save(buffer, witchsExperimentKilled, 10);
		buffer.put((byte) 0);
	}

	public void parse(JSONObject data){
		JSONArray dl = (JSONArray) data.get("draynorLever");
		for(int i = 0; i < dl.size(); i++){
			draynorLever[i] = (boolean) dl.get(i);
		}
		JSONArray drs = (JSONArray) data.get("dragonSlayer");
		for(int i = 0; i < drs.size(); i++){
			dragonSlayer[i] = (boolean) drs.get(i);
		}
		dragonSlayerPlanks = Integer.parseInt( data.get("dragonSlayerPlanks").toString());
		JSONArray des = (JSONArray) data.get("demonSlayer");
		for(int i = 0; i < des.size(); i++){
			demonSlayer[i] = (boolean) des.get(i);
		}
		JSONArray ca = (JSONArray) data.get("cooksAssistant");
		for(int i = 0; i < ca.size(); i++){
			cooksAssistant[i] = (boolean) ca.get(i);
		}
		gardenerAttack = (boolean) data.get("gardenerAttack");
		talkedDrezel = (boolean) data.get("talkedDrezel");
		JSONArray dtn = (JSONArray) data.get("desertTreasureNode");
		for (int i = 0; i < dtn.size(); i++){
			JSONObject item = (JSONObject) dtn.get(i);
			desertTreasure[i] = new Item(Integer.parseInt(item.get("id").toString()), Integer.parseInt(item.get("amount").toString()));
		}
		witchsExperimentKilled = (boolean) data.get("witchsExperimentKilled");
		witchsExperimentStage = Integer.parseInt( data.get("witchsExperimentStage").toString());
	}

	@Override
	public void parse(ByteBuffer buffer) {
		int opcode;
		while ((opcode = buffer.get()) != 0) {
			switch (opcode) {
			case 1:
				for (int i = 0; i < draynorLever.length; i++) {
					draynorLever[i] = SavedData.getBoolean(buffer.get());
				}
				break;
			case 2:
				for (int i = 0; i < dragonSlayer.length; i++) {
					dragonSlayer[i] = SavedData.getBoolean(buffer.get());
				}
				break;
			case 3:
				dragonSlayerPlanks = buffer.get();
				break;
			case 4:
				for (int i = 0; i < demonSlayer.length; i++) {
					demonSlayer[i] = SavedData.getBoolean(buffer.get());
				}
				break;
			case 5:
				for (int i = 0; i < cooksAssistant.length; i++) {
					cooksAssistant[i] = SavedData.getBoolean(buffer.get());
				}
				break;
			case 6:
				gardenerAttack = SavedData.getBoolean(buffer.get());
				break;
			case 7:
				talkedDrezel = SavedData.getBoolean(buffer.get());
				break;
			case 8:
				for (int i = 0; i < desertTreasure.length; i++) {
					desertTreasure[i] = new Item(buffer.getShort(), buffer.get());
				}
				break;
			case 9:
				witchsExperimentStage = buffer.getInt();
				break;
			case 10:
				witchsExperimentKilled = SavedData.getBoolean(buffer.get());
				break;
			}
		}
	}

	/**
	 * Saves the desert treasure node.
	 * @param buffer The buffer.
	 */
	private final void saveDesertTreasureNode(ByteBuffer buffer) {
		buffer.put((byte) 8);
		for (int i = 0; i < desertTreasure.length; i++) {
			Item item = desertTreasure[i];
			buffer.putShort((short) item.getId());
			buffer.put((byte) item.getAmount());
		}
	}

	/**
	 * Gets the draynorLever.
	 * @return The draynorLever.
	 */
	public boolean[] getDraynorLevers() {
		return draynorLever;
	}

	/**
	 * Gets the dragon slayer items.
	 * @return the dragon slayer.
	 */
	public boolean[] getDragonSlayerItems() {
		return dragonSlayer;
	}

	/**
	 * Gets the value of a inserted dragon slayer item.
	 * @param name the name.
	 * @return the value of the item being inserted.
	 */
	public boolean getDragonSlayerItem(String name) {
		return name == "lobster" ? dragonSlayer[0] : name == "wizard" ? dragonSlayer[3] : name == "silk" ? dragonSlayer[2] : name == "bowl" ? dragonSlayer[1] : dragonSlayer[0];
	}

	/**
	 * Gets the dragon slayer attribute.
	 * @param name the name.
	 * @return the value of the attribute.
	 */
	public boolean getDragonSlayerAttribute(String name) {
		return name == "ship" ? dragonSlayer[4] : name == "memorized" ? dragonSlayer[5] : name == "repaired" ? dragonSlayer[6] : name == "ned" ? dragonSlayer[7] : name == "poured" ? dragonSlayer[8] : dragonSlayer[8];
	}

	/**
	 * Method used to set a dragon slayer attribute.
	 * @param name the name.
	 * @param value the value.
	 */
	public void setDragonSlayerAttribute(String name, boolean value) {
		dragonSlayer[(name == "ship" ? 4 : name == "memorized" ? 5 : name == "repaired" ? 6 : name == "ned" ? 7 : name == "poured" ? 8 : 8)] = value;
	}

	/**
	 * Gets the cooks assistant attribute value.
	 * @param name the name.
	 * @return the value.
	 */
	public boolean getCookAssist(String name) {
		return name == "milk" ? cooksAssistant[0] : name == "egg" ? cooksAssistant[1] : name == "flour" ? cooksAssistant[2] : name == "gave" ? cooksAssistant[3] : cooksAssistant[3];
	}

	/**
	 * Method used to set a cooks assistant attribute.
	 * @param name the name.
	 * @param value the value.
	 */
	public void setCooksAssistant(String name, boolean value) {
		cooksAssistant[(name == "milk" ? 0 : name == "egg" ? 1 : name == "flour" ? 2 : name == "gave" ? 3 : 3)] = value;
	}

	/**
	 * Gets the dragonSlayerPlanks.
	 * @return The dragonSlayerPlanks.
	 */
	public int getDragonSlayerPlanks() {
		return dragonSlayerPlanks;
	}

	/**
	 * Sets the dragonSlayerPlanks.
	 * @param i The dragonSlayerPlanks to set.
	 */
	public void setDragonSlayerPlanks(int i) {
		this.dragonSlayerPlanks = i;
	}

	/**
	 * Gets the demonSlayer.
	 * @return The demonSlayer.
	 */
	public boolean[] getDemonSlayer() {
		return demonSlayer;
	}

	/**
	 * Gets the cooksAssistant.
	 * @return The cooksAssistant.
	 */
	public boolean[] getCooksAssistant() {
		return cooksAssistant;
	}

	/**
	 * Gets the gardenerAttack.
	 * @return The gardenerAttack.
	 */
	public boolean isGardenerAttack() {
		return gardenerAttack;
	}

	/**
	 * Sets the gardenerAttack.
	 * @param gardenerAttack The gardenerAttack to set.
	 */
	public void setGardenerAttack(boolean gardenerAttack) {
		this.gardenerAttack = gardenerAttack;
	}

	/**
	 * Gets the talkedDrezel.
	 * @return The talkedDrezel.
	 */
	public boolean isTalkedDrezel() {
		return talkedDrezel;
	}

	/**
	 * Sets the talkedDrezel.
	 * @param talkedDrezel The talkedDrezel to set.
	 */
	public void setTalkedDrezel(boolean talkedDrezel) {
		this.talkedDrezel = talkedDrezel;
	}

	/**
	 * Populates the desert treasure node.
	 */
	private final void populateDesertTreasureNode() {
		desertTreasure[0] = new Item(1513, 12);
		desertTreasure[1] = new Item(592, 10);
		desertTreasure[2] = new Item(1775, 6);
		desertTreasure[3] = new Item(2353, 6);
		desertTreasure[4] = new Item(526, 2);
		desertTreasure[5] = new Item(973, 2);
		desertTreasure[6] = new Item(565, 1);
	}

	/**
	 * Gets the desert treasure item.
	 * @param index The index.
	 * @return The item.
	 */
	public Item getDesertTreasureItem(int index) {
		if (index < 0 || index > desertTreasure.length) {
			throw new IndexOutOfBoundsException("Index out of bounds, index can only span from 0 - 6.");
		}
		return desertTreasure[index];
	}

	/**
	 * Sets the desert treasure item.
	 * @param index The index.
	 * @param item The item to set.
	 */
	public void setDesertTreasureItem(int index, Item item) {
		if (index < 0 || index > desertTreasure.length) {
			throw new IndexOutOfBoundsException("Index out of bounds, index can only span from 0 - 6.");
		}
		desertTreasure[index] = item;
	}

	public int getWitchsExperimentStage() {
		return witchsExperimentStage;
	}

	public void setWitchsExperimentStage(int witchsExperimentStage) {
		this.witchsExperimentStage = witchsExperimentStage;
	}
	public boolean isWitchsExperimentKilled() {
		return witchsExperimentKilled;
	}

	public void setWitchsExperimentKilled(boolean witchsExperimentKilled) {
		this.witchsExperimentKilled = witchsExperimentKilled;
	}

	public boolean[] getDraynorLever() {
		return draynorLever;
	}

	public boolean[] getDragonSlayer() {
		return dragonSlayer;
	}

	public Item[] getDesertTreasure() {
		return desertTreasure;
	}
}