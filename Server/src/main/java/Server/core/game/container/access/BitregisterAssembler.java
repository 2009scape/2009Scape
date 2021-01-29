package core.game.container.access;

import core.game.node.entity.player.Player;

/**
 * Contains the mask value methods. The 'access mask' is actually just a bit
 * register that contains permissions for a specific interface (@Peterbjornx)
 * @date 5/02/2013
 * @author Stacx
 * @author Emperor
 */
public final class BitregisterAssembler {

	/**
	 * The register size.
	 */
	public static final int SIZE = 32 - 1;
	
	/**
	 * Examine option.
	 */
	public static final int EXAMINE_OPT = 9;
	
	/**
	 * Allow dragging.
	 */
	public static final int DRAGABLE = 17;

	/**
	 * Allow switching item slots.
	 */
	public static final int SLOT_SWITCH = 20;
	
	/**
	 * The flags.
	 */
	private boolean[] permissions = new boolean[SIZE];

	/**
	 * Constructs a new {@code BitregisterAssembler} {@code Object}.
	 * @param permissions The permissions.
	 */
	public BitregisterAssembler(int... permissions) {
		for (int i : permissions) {
			this.permissions[i] = true;
		}
	}
	
	/**
	 * Constructs a new {@code BitregisterAssembler} {@code Object}.
	 */
	public BitregisterAssembler(String[] options) {
		enableOptions(options);
	}
	
	/**
	 * Enables the given options ({@code null} options will remain disabled).
	 * @param options The options.
	 */
	public void enableOptions(String...options) {
		if (options.length > 9) {
			throw new IllegalStateException("Too many options specified - maximum 9 allowed!");
		}
		for (int i = 0; i < options.length; i++) {
			if (options[i] != null && !options[i].equals("null")) {
				permissions[i] = true;
			}
		}
	}
	
	/**
	 * Enables the examine option.
	 */
	public void enableExamineOption() {
		permissions[EXAMINE_OPT] = true;
	}
	
	/**
	 * Enables items being dragged.
	 */
	public void enableDragging() {
		permissions[DRAGABLE] = true;
	}
	
	/**
	 * Enables item switching slots (& dragging).
	 */
	public void enableSlotSwitch() {
		enableDragging();
		permissions[SLOT_SWITCH] = true;
	}
	
	/**
	 * <b>Send</b> and assemble a bit register for our
	 * @param player , the player instance
	 * @param interfaceIndex , the interface index
	 * @param childIndex , the child index for our interface
	 * @param offset , the offset for the loop in client
	 * @param length , the length of our loop
	 */
	public static void send(Player player, int interfaceIndex, int childIndex, int offset, int length, BitregisterAssembler assembler) {
		if (offset >= length) {
			throw new RuntimeException("Offset cannot excess length. length = " + length);
		}
		player.getPacketDispatch().sendAccessMask(assembler.calculateRegister(), childIndex, interfaceIndex, offset, length);
	}
	
	/**
	 * Calculates the register.
	 * @return The value.
	 */
	public int calculateRegister() {
		int value = 0;
		for (int i = 0; i < SIZE; i++) {
			if (permissions[i]) {
				value |= 2 << i;
			}
		}
		return value;
	}

}
