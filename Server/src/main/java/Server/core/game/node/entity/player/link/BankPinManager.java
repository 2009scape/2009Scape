package core.game.node.entity.player.link;

import core.cache.misc.buffer.ByteBufferUtils;
import core.game.component.CloseEvent;
import core.game.component.Component;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.login.SavingModule;
import core.game.world.GameWorld;
import core.net.packet.PacketRepository;
import core.net.packet.context.ChildPositionContext;
import core.net.packet.context.StringContext;
import core.net.packet.out.RepositionChild;
import core.net.packet.out.StringPacket;
import core.tools.RandomFunction;
import org.json.simple.JSONObject;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Manages the players bank pin.
 * @author Vexia
 * @author Emperor
 */
public class BankPinManager implements SavingModule {

	/**
	 * THe setting messages to display on default.
	 */
	private static final String[] SETTINGS_MESSAGES = { "Customers are reminded that", "they should NEVER tell", "anyone their Bank PINs or", "passwords, nor should they", "ever enter their PINs on any", "website form.", null, "Have you read the PIN guide", "on the website?", null, null, null, null };

	/**
	 * The pin child constants.
	 */
	public static final int SET_PIN = 60, CHANGE_RECOVERY = 61, CHANGE_PIN = 62, DELETE_PIN = 63, CANCEL_PENDING = 65;

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The digits.
	 */
	private final List<Integer> digits = new ArrayList<>();

	/**
	 * The bank pin.
	 */
	private String pin;

	/**
	 * If the manager has been unlocked.
	 */
	private boolean unlocked;

	/**
	 * If we're using the short or long recovery.
	 */
	private boolean longRecovery;

	/**
	 * The pin status.
	 */
	private PinStatus status = PinStatus.NO_PIN;

	/**
	 * The temporary pin.
	 */
	private String tempPin;

	/**
	 * The pending delay.
	 */
	private long pendingDelay = -1;

	/**
	 * The open id after confirmation interface.
	 */
	private int openId;

	/**
	 * The amount of attempts on opening the bank pin.
	 */
	private int tries;

	/**
	 * The pin changing state.
	 */
	private int changeState;

	/**
	 * If we're deleting the pin.
	 */
	private boolean deleting;

	/**
	 * The delay between
	 */
	private long tryDelay;

	/**
	 * Constructs a new {@Code BankPinManager} {@Code Object}
	 */
	public BankPinManager(final Player player) {
		this.player = player;
		for (int i = 0; i < 10; i++) {
			digits.add(i);
		}
	}

	@Override
	public void save(ByteBuffer buffer) {
		if (hasPin()) {
			ByteBufferUtils.putString(pin, buffer.put((byte) 1));
		}
		buffer.put((byte) 2).put((byte) (longRecovery ? 1 : 0));
		if (status.ordinal() != 0) {
			buffer.put((byte) 3).put(((byte) status.ordinal()));
		}
		if (pendingDelay != -1 && pendingDelay > System.currentTimeMillis()) {
			buffer.put((byte) 4).putLong(pendingDelay);
		}
		if (tryDelay > System.currentTimeMillis()) {
			buffer.put((byte) 5).putLong(tryDelay);
		}
		buffer.put((byte) 0);
	}

	public void parse(JSONObject data){
		if(data.containsKey("pin")) {
			pin = data.get("pin").toString();
		}
		if(data.containsKey("status")){
			int st = Integer.parseInt( data.get("status").toString());
			status = PinStatus.values()[st];
		}
		if(data.containsKey("pendingDelay")){
			this.pendingDelay = Long.parseLong(data.get("pendingDelay").toString());
		}
		if(data.containsKey("tryDelay")){
			this.tryDelay = Long.parseLong(data.get("tryDelay").toString());
		}
		longRecovery = (boolean) data.get("longRecovery");
	}

	@Override
	public void parse(ByteBuffer buffer) {
		int opcode;
		while ((opcode = buffer.get()) != 0) {
			switch (opcode) {
			case 1:
				pin = ByteBufferUtils.getString(buffer);
				break;
			case 2:
				longRecovery = buffer.get() == 1;
				break;
			case 3:
				status = PinStatus.values()[buffer.get()];
				break;
			case 4:
				pendingDelay = buffer.getLong();
				break;
			case 5:
				tryDelay = buffer.getLong();
				break;
			}
		}
	}

	/**
	 * Opens the settings.
	 */
	public void openSettings() {
		checkPendingActivity();
		openSettings(status.getMessages(this));
	}

	/**
	 * Opens the bank pin settings.
	 */
	public void openSettings(String... messages) {
		player.getInterfaceManager().close();
		tempPin = null;
		sendMessages(messages);
		player.getAudioManager().send(1040);
		player.getInterfaceManager().open(new Component(14));
	}

	/**
	 * Sends the messages.
	 * @param messages the messages.
	 */
	private void sendMessages(String... messages) {
		for (int i = 0; i < messages.length; i++) {
			String message = messages[i];
			if (message == null) {
				continue;
			}
			PacketRepository.send(StringPacket.class, new StringContext(player, message, 14, 42 + i));
		}
		status.draw(this, player);
		PinStatus.drawString(player, "Messages", 41);
		PinStatus.drawString(player, getRecoveryDelay() + " days", 71);
		PinStatus.drawString(player, "Use the buttons below to change your PIN settings", 86);
	}

	/**
	 * Used to open a component type that requires pin unlocking.
	 * @param buttonId the buttonId.
	 */
	public void openType(int buttonId) {
		checkPendingActivity();
		if (buttonId == 2) {
			openSettings();
			return;
		}
		openId = buttonId;
		if (hasPin() && !unlocked) {
			if (hasPendingPin()) {
				openSettings();
				toggleConfirmInterface(true);
				PinStatus.drawString(player, "A PIN will be set on your bank account in another " + getPendingDays() + " day" + (getPendingDays() > 1 ? "s" : "") + ".", 73);
				PinStatus.drawString(player, "Yes, I asked for this. I want this PIN.", 89);
				PinStatus.drawString(player, "No, I didn't ask for this. Cancel it.", 91);
				return;
			}
			openPin();
			return;
		}
		openForId(buttonId);
	}

	/**
	 * Opens the interface for the id.
	 * @param buttonId the button id.
	 */
	private void openForId(int buttonId) {
		if (buttonId == 1) {
			{
				player.getBank().open();
			}
		} else if (buttonId == 3) {
			player.getPlayerGrandExchange().openCollectionBox();
		} else if (buttonId == 4) {
			player.getPlayerGrandExchange().open();
		}
	}

	/**
	 * Handles the confirmation interface.
	 * @param button the button.
	 */
	public void handleConfirmInterface(int button) {
		boolean confirm = button != 91;
		switch (status) {
		case NO_PIN:
			if (!confirm) {
				toggleConfirmInterface(false);
				player.getInterfaceManager().close();
				openSettings("No changes made.");
				break;
			}
			openPin();
			break;
		case PENDING:
			if (confirm) {
				unlock();
			} else {
				cancelPin("The PIN has been cancelled", "and will NOT be set.", "", "You still do not have a Bank", "PIN.");
			}
			break;
		case ACTIVE:
			if (confirm) {
				if (unlocked) {
					cancelPin("Your Bank PIN has now been", "deleted.", "", "This means that there is no", "PIN protection on your bank", "account.");
				} else {
					deleting = true;
					openPin();
				}
			} else {
				openSettings("No changes made.");
			}
			break;
		default:
			break;
		}
	}

	/**
	 * Unlocks the pin manager.
	 */
	private void unlock() {
		unlocked = true;
		openForId(openId);
	}

	/**
	 * Opens the set bank PIN confirmation screen.
	 * @param show If we should show the confirm screen.
	 */
	public void toggleConfirmInterface(boolean show) {
		for (int i = 60; i < 66; i++) {
			player.getPacketDispatch().sendInterfaceConfig(14, i, show);
		}
		player.getAudioManager().send(1040);
		player.getPacketDispatch().sendInterfaceConfig(14, 89, !show);
		player.getPacketDispatch().sendInterfaceConfig(14, 91, !show);
		player.getPacketDispatch().sendInterfaceConfig(14, 87, !show);
		if (show && status == PinStatus.NO_PIN) {
			PinStatus.drawString(player, "Do you really wish to set a PIN on your bank account?", 73);
			PinStatus.drawString(player, "Yes, I really want a Bank PIN. I will never forget it!", 89);
			PinStatus.drawString(player, "No, I might forget it!", 91);
		} else if (show && status == PinStatus.ACTIVE) {
			PinStatus.drawString(player, "Do you really wish to delete your Bank PIN?", 73);
			PinStatus.drawString(player, "Yes, I don't need a PIN anymore.", 89);
			PinStatus.drawString(player, "No thanks, I'd rather keep the extra security.", 91);
		}
	}

	/**
	 * Updates the temporary pin.
	 * @param button the button.
	 */
	public void updateTempPin(int button) {
		tempPin += digits.get(button);
		handlePinStage(getStage());
	}

	/**
	 * Checks the pending activity.
	 */
	private void checkPendingActivity() {
		if (status == PinStatus.PENDING && pendingDelay < System.currentTimeMillis()) {
			pendingDelay = -1;
			status = PinStatus.ACTIVE;
			return;
		}
	}

	/**
	 * Opens the set pin interface.
	 */
	public void openPin() {
		if (isPinBlocked()) {
			sendTryDialogue();
			return;
		}
		tempPin = "";
		player.getPacketDispatch().sendString("Bank of " + GameWorld.getSettings().getName(), 13, 31);
		if (!hasPin()) {
			player.getPacketDispatch().sendInterfaceConfig(13, 29, true);
			player.getPacketDispatch().sendString("Please choose a new FOUR DIGIT PIN using the buttons below.", 13, 28);
		} else {
			player.getPacketDispatch().sendString("Please enter your PIN using the buttons below.", 13, 28);
		}
		player.getInterfaceManager().open(new Component(13).setCloseEvent(new CloseEvent() {
			@Override
			public boolean close(Player player, Component c) {
				if (changeState != 0) {
					changeState = 0;
				}
				if (deleting) {
					deleting = false;
				}
				return true;
			}
		}));
		handlePinStage(getStage());
	}

	/**
	 * Handles a pin stage.
	 * @param stage the stage.
	 */
	public void handlePinStage(int stage) {
		if (stage == 4) {
			if (!hasPin() || changeState == 2) {
				player.getAudioManager().send(1040);
				player.getPacketDispatch().sendString("Now please enter that number again!", 13, 28);
				shuffleDigits();
				return;
			} else {
				if (!tempPin.equals(pin)) {
					player.getAudioManager().send(1042);
					player.getInterfaceManager().close();
					player.sendMessage("The PIN you entered is incorrect.");
					if (setTries(getTries() + 1) >= 2) {
						setTryLock();
					}
					return;
				}
				if (changeState == 1) {
					tempPin = "";
					player.getPacketDispatch().sendString("Please choose a new FOUR DIGIT PIN using the buttons below.", 13, 28);
					changeState = 2;
					shuffleDigits();
					return;
				}
				if (deleting) {
					cancelPin("Your Bank PIN has now been", "deleted.", "", "This means that there is no", "PIN protection on your bank", "account.");
					return;
				}
				unlock();
				player.sendMessage("You have correctly entered your PIN.");
			}
			return;
		} else if (stage == 8) {
			boolean success = checkTempPin();
			if (isEasyGuess()) {
				openSettings("That number wouldn't be very", "hard to guess. Please try", "something different!");
				return;
			}
			if (changeState == 2 && success) {
				if (pin.equals(tempPin.substring(0, 4))) {
					player.getPacketDispatch().sendMessage("Your current PIN matches your new one.");
				} else {
					pin = new String(Arrays.copyOf(tempPin.toCharArray(), 4));
					player.getPacketDispatch().sendMessage("You have changed your PIN.");
				}
				openSettings();
				return;
			}
			if (success) {
				setPin();
			}
			openSettings(success ? new String[] { "You have requested that a", "PIN be set on your bank", "account. This will take effect", "in another " + getRecoveryDelay() + " days.", "", "If you wish to cancel this PIN,", "please use the button", "on the left." } : new String[] { "Those numbers did not", "match.", "", "Your PIN has not been set;", "please try again if you wish", "to set a new PIN." });
			return;
		}
		shuffleDigits();
		if (stage != 0) {
			player.getAudioManager().send(1041);
		}
	}

	/**
	 * Sets the try locks.
	 */
	private void setTryLock() {
		setTryDelay(System.currentTimeMillis() + getPinSeconds() * 1000);
		sendTryDialogue();
	}

	/**
	 * Sends the try dialogue.
	 */
	private void sendTryDialogue() {
		long ticks = (tryDelay - System.currentTimeMillis()) / 1000;
		int original = getPinSeconds();
		String suffix = (original > 15 ? "minutes" : "seconds");
		player.getInterfaceManager().close();
		if (suffix.equals("minutes") || ticks > 15) {
			ticks /= 100;
			suffix = "minutes";
		}
		if (suffix.equals("minutes") && ticks <= 1) {
			player.getDialogueInterpreter().sendDialogue("You will be able to access your bank in less than 1 minute.");
			return;
		}
		player.getDialogueInterpreter().sendDialogue("You will be able to access your bank pin in " + ticks + " " + suffix + ".");
	}

	/**
	 * Shuffles the digits.
	 */
	private void shuffleDigits() {
		Collections.shuffle(digits);
		int bitShift = 0, bitValue = 0;
		for (int i = 0; i < 8; i++) {
			bitValue |= digits.get(i) << bitShift;
			bitShift += 4;
		}
		int stage = getStage();
		if (stage >= 4) {
			stage = stage - 4;
		}
		player.getConfigManager().set(562, bitValue);
		player.getConfigManager().set(563, digits.get(8) | digits.get(9) << 4 | stage << 26);
		for (int i = 0; i < 9; i++) {
			int child = (i > 2 ? i + 1 : i) + 11;
			int positionX = 37 + ((i % 3) * 95) + RandomFunction.random(2, 45);
			int positionY = 157 + ((i / 3) * 70) - RandomFunction.random(3, 48);
			PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 13, child, positionX, positionY));
		}
		PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 13, 14, 308 + RandomFunction.random(2, 45), 155 - RandomFunction.random(3, 45)));
	}

	/**
	 * Sets the pin.
	 */
	private void setPin() {
		status = PinStatus.PENDING;
		pendingDelay = System.currentTimeMillis() + (GameWorld.getSettings().isDevMode() ? TimeUnit.SECONDS.toMillis(30) : TimeUnit.DAYS.toMillis(this.getRecoveryDelay()));
		pin = new String(Arrays.copyOf(tempPin.toCharArray(), 4));
	}

	/**
	 * Sets the pin.
	 * @param pin the pin.
	 */
	public void setPin(String pin) {
		this.pin = pin;
	}

	/**
	 * Cancels the pin.
	 */
	public void cancelPin(String... messages) {
		status = PinStatus.NO_PIN;
		pendingDelay = -1;
		pin = null;
		unlocked = false;
		player.getAudioManager().send(1042);
		openSettings(messages);
	}

	/**
	 * Checks if the pin is an easy guess.
	 * @return {@code True} if so.
	 */
	private boolean isEasyGuess() {
		boolean badCombo = true;
		String currentPin = tempPin;
		for (int i = 0; i < 4; i++) {
			if (currentPin.charAt(0) != currentPin.charAt(i)) {
				badCombo = false;
				break;
			}
		}
		if (!badCombo) {
			badCombo = true;
			for (int i = 0; i < 3; i++) {
				int value = Byte.valueOf((byte) currentPin.charAt(i));
				int next = Byte.valueOf((byte) currentPin.charAt(i + 1));
				if (!(Character.valueOf((char) (value + 1)) == next || Character.valueOf((char) (value - 1)) == next)) {
					badCombo = false;
					break;
				}
			}
		}
		return badCombo;
	}

	/**
	 * Checks the temporary pin match.
	 * @return {@code True} if matched.
	 */
	private boolean checkTempPin() {
		for (int i = 0; i < 4; i++) {
			if (tempPin.charAt(i) != tempPin.charAt(4 + i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gets the pending
	 * @return the pending days.
	 */
	public int getPendingDays() {
		if (pendingDelay < System.currentTimeMillis()) {
			return 0;
		}
		return (int) (pendingDelay - System.currentTimeMillis()) / (GameWorld.getSettings().isDevMode() ? 1000 : 86400000);
	}

	/**
	 * Gets the stage of the pin setting.
	 * @return the stage.
	 */
	public int getStage() {
		if (tempPin == null) {
			return 0;
		}
		return tempPin.length();
	}

	/**
	 * Draws the login message.
	 */
	public void drawLoginMessage() {
		player.getPacketDispatch().sendString(status.getLoginMessage(this), 378, 62);
		checkPendingActivity();
	}

	/**
	 * Sets the chaning pin.
	 * @param state the changing state.
	 */
	public void setChangingState(int state) {
		this.changeState = state;
	}

	/**
	 * Gets the long recovery delay.
	 * @return the delay.
	 */
	public int getRecoveryDelay() {
		return longRecovery ? 7 : 3;
	}

	/**
	 * Checks if the manager has a pin.
	 * @return the pin.
	 */
	public boolean hasPin() {
		return pin != null;
	}

	/**
	 * Checks if there is a pending pin.
	 * @return {@code True} if so.
	 */
	public boolean hasPendingPin() {
		return getPendingDays() > 0;
	}

	/**
	 * Gets the pin.
	 * @return the pin.
	 */
	public String getPin() {
		return pin;
	}

	/**
	 * Gets the unlocked.
	 * @return the unlocked
	 */
	public boolean isUnlocked() {
		if (!hasPin()) {
			return true;
		}
		return unlocked;
	}

	/**
	 * Sets the baunlocked.
	 * @param unlocked the unlocked to set.
	 */
	public void setUnlocked(boolean unlocked) {
		this.unlocked = unlocked;
	}

	/**
	 * Gets the player.
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the longRecovery.
	 * @return the longRecovery
	 */
	public boolean isLongRecovery() {
		return longRecovery;
	}

	/**
	 * Sets the status.
	 * @param status the status.
	 */
	public void setStatus(PinStatus status) {
		this.status = status;
	}

	/**
	 * Sets the balongRecovery.
	 * @param longRecovery the longRecovery to set.
	 */
	public void setLongRecovery(boolean longRecovery) {
		this.longRecovery = longRecovery;
	}

	/**
	 * Switches the recovery delay.
	 */
	public void switchRecovery() {

		setLongRecovery(!longRecovery);
		PinStatus.drawString(player, getRecoveryDelay() + " days", 71);
		PinStatus.drawString(player, "Your recovery delay has", 42);
		PinStatus.drawString(player, "now been set to " + getRecoveryDelay() + " days.", 43);
		PinStatus.drawString(player, "", 44);
		PinStatus.drawString(player, "You would have to wait this", 45);
		PinStatus.drawString(player, "long to delete your PIN if", 46);
		PinStatus.drawString(player, "you forgot it. But you", 47);
		PinStatus.drawString(player, "haven't got one...", 48);
	}

	/**
	 * Checks if the pin is blocked.
	 * @return {@code True} if so.
	 */
	public boolean isPinBlocked() {
		return tryDelay > System.currentTimeMillis();
	}

	/**
	 * Gets the PIN lock ticks.
	 * @return The PIN lock ticks.
	 */
	public int getPinSeconds() {
		if (tries == 2) {
			return 10;
		} else if (tries == 3) {
			return 15;
		} else if (tries > 3) {
			return 1000;
		}
		return 0;
	}

	/**
	 * Gets the tries.
	 * @return the tries
	 */
	public int getTries() {
		return tries;
	}

	/**
	 * Sets the batries.
	 * @param tries the tries to set.
	 */
	public int setTries(int tries) {
		this.tries = tries;
		return tries;
	}

	/**
	 * Gets the tryDelay.
	 * @return the tryDelay
	 */
	public long getTryDelay() {
		return tryDelay;
	}

	/**
	 * Sets the batryDelay.
	 * @param tryDelay the tryDelay to set.
	 */
	public void setTryDelay(long tryDelay) {
		this.tryDelay = tryDelay;
	}

	/**
	 * A pin status.
	 * @author Vexia/k
	 */
	public enum PinStatus {
		NO_PIN() {

			@Override
			public void draw(BankPinManager manager, Player player) {
				drawString(player, "NO PIN set", 69);
				removeLines(player, 89, 55, 56, 57, 91, 64, 65, 63, 62);
			}

		},
		PENDING() {
			@Override
			public void draw(BankPinManager manager, Player player) {
				drawString(player, "PIN coming soon", 69);
				removeLines(player, 89, 55, 56, 57, 91, 64, 63, 62, 60, 61);
			}

			@Override
			public String getLoginMessage(BankPinManager manager) {
				int days = manager.getPendingDays();
				String message = "Your Bank PIN will become active in another " + days + " day" + (days > 1 ? "s" : "") + ".";
				if (days < 1) {
					return "Your Bank PIN has just become active.";
				}
				return message;
			}

			@Override
			public String[] getMessages(BankPinManager manager) {
				int days = manager.getPendingDays();
				return new String[] { "You have requested that a", "PIN be set on your bank", "account. This will take effect", "in another " + days + " day" + (days > 1 ? "s" : "") + ".", "", "If you wish to cancel this", "PIN, please use the button", "on the left." };
			}
		},
		ACTIVE() {

			@Override
			public void draw(BankPinManager manager, Player player) {
				drawString(player, "You have a PIN", 69);
				removeLines(player, 89, 55, 56, 57, 91, CANCEL_PENDING, CHANGE_RECOVERY, SET_PIN);
			}

			@Override
			public String getLoginMessage(BankPinManager manager) {
				return "You you currently have a Bank PIN.";
			}

		};

		/**
		 * Draws the status on the interface.
		 * @param player the player.
		 */
		public void draw(BankPinManager manager, Player player) {

		}

		/**
		 * Gets the login message.
		 * @param manager the manager.
		 * @return the message.
		 */
		public String getLoginMessage(BankPinManager manager) {
			return "You do not have a Bank PIN. Please visit a bank if you would like one.";
		}

		/**
		 * Draws a string.
		 * @param player the player.
		 * @param string the string.
		 * @param line the line.
		 */
		public static void drawString(Player player, String string, int line) {
			player.getPacketDispatch().sendString(string, 14, line);
		}

		/**
		 * Removes the lines.
		 * @param player the player.
		 * @param lines the lines.
		 */
		public static void removeLines(Player player, int... lines) {
			for (int line : lines) {
				player.getPacketDispatch().sendInterfaceConfig(14, line, true);
			}
		}

		/**
		 * Gets the messages to display.
		 * @return the messages.
		 */
		public String[] getMessages(BankPinManager manager) {
			return SETTINGS_MESSAGES;
		}

	}

	public PinStatus getStatus() {
		return status;
	}

	public long getPendingDelay() {
		return pendingDelay;
	}
}
