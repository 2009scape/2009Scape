package plugin.quest.miniquest.barcrawl;

import core.game.component.Component;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.login.SavingModule;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.nio.ByteBuffer;

/**
 * Manages the players barcrawl quest.
 * @author 'Vexia
 */
@InitializablePlugin
public final class BarcrawlManager implements SavingModule {

	/**
	 * The barcrawl card.
	 */
	public static final Item BARCRAWL_CARD = new Item(455);

	/**
	 * The name of bars.
	 */
	public static final String[] NAMES = new String[] { "BlueMoon Inn", "Blueberry's Bar", "Dead Man's Chest", "Dragon Inn", "Flying Horse Inn", "Foresters Arms", "Jolly Boar Inn", "Karamja Spirits bar", "Rising Sun Inn", "Rusty Anchor Inn" };

	/**
	 * The component used to draw the completed bars on.
	 */
	public static final Component COMPONENT = new Component(220);

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The bars completed.
	 */
	private final boolean[] bars = new boolean[10];

	/**
	 * If the quest has been started.
	 */
	private boolean started;

	/**
	 * Constructs a new {@code BarcrawlManager} {@code Object}.
	 * @param player the player.
	 */
	public BarcrawlManager(final Player player) {
		this.player = player;
	}

	@Override
	public void save(ByteBuffer buffer) {
		if (started) {
			buffer.put((byte) 1);
		}
		for (int i = 0; i < bars.length; i++) {
			if (bars[i]) {
				buffer.put((byte) 2);
				buffer.put((byte) i);
			}
		}
		buffer.put((byte) 0);
	}

	public void parse(JSONObject data){
		started = (boolean) data.get("started");
		JSONArray barsVisisted = (JSONArray) data.get("bars");
		for(int i = 0; i < barsVisisted.size(); i++){
			bars[i] = (boolean) barsVisisted.get(i);
		}
	}

	@Override
	public void parse(ByteBuffer buffer) {
		int opcode;
		while ((opcode = buffer.get() & 0xFF) != 0) {
			switch (opcode) {
			case 1:
				started = true;
				break;
			case 2:
				bars[buffer.get()] = true;
				break;
			}
		}
	}

	/**
	 * Method used to read the card.
	 */
	public void read() {
		if (isFinished()) {
			player.getPacketDispatch().sendMessage("You are too drunk to be able to read the barcrawl card.");
			;
			return;
		}
		player.getInterfaceManager().open(COMPONENT);
		drawCompletions();
	}

	/**
	 * Draws the completed bars on the interface.
	 */
	private void drawCompletions() {
		player.getPacketDispatch().sendString("<col=0000FF>The Official Alfred Grimhand Barcrawl!", 220, 1);
		boolean complete;
		for (int i = 0; i < bars.length; i++) {
			complete = bars[i];
			player.getPacketDispatch().sendString((complete ? "<col=00FF00>" : "<col=FF0000>") + NAMES[i] + " - " + (complete ? "Complete!" : "Not Completed..."), 220, 3 + i);
		}
	}

	/**
	 * Completes a bar challenge.
	 * @param index the index.
	 */
	public void complete(int index) {
		bars[index] = true;
	}

	/**
	 * Checks if the barcrawl quest is completed.
	 * @return {@code True} if so.
	 */
	public boolean isFinished() {
		for (int i = 0; i < bars.length; i++) {
			if (!isCompleted(i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Resets the bars.
	 */
	public void reset() {
		started = false;
		for (int i = 0; i < bars.length; i++) {
			bars[i] = false;
		}
	}

	/**
	 * Checks if a bar is completed.
	 * @param index the index.
	 * @return {@code True} if completed.
	 */
	public boolean isCompleted(int index) {
		return bars[index];
	}

	/**
	 * Checks if the player has the card.
	 * @return the card.
	 */
	public boolean hasCard() {
		return player.getInventory().containsItem(BARCRAWL_CARD) || player.getBank().containsItem(BARCRAWL_CARD);
	}

	/**
	 * Gets the started.
	 * @return The started.
	 */
	public boolean isStarted() {
		return started;
	}

	/**
	 * Sets the started.
	 * @param started The started to set.
	 */
	public void setStarted(boolean started) {
		this.started = started;
	}

	public boolean[] getBars() {
		return bars;
	}
}
