package ms.system;


import ms.Management;

/**
 * The shutdown sequence used for safely turning off the Management server.
 * @author Emperor
 *
 */
public final class ShutdownSequence extends Thread {

	@Override
	public void run() {
		if (Management.active) {
			shutdown();
		}
	}
	
	/**
	 * Safely shuts down the Management server.
	 */
	public static void shutdown() {
		System.out.println("Management server successfully shut down!");
		Management.active = false;
	}
}