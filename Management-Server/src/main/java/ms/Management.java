package ms;

import ms.classloader.ClassLoadServer;
import ms.net.NioReactor;
import ms.net.packet.WorldPacketRepository;
import ms.system.ShutdownSequence;
import ms.system.mysql.SQLManager;
import ms.system.util.Command;
import ms.world.GameServer;
import ms.world.PlayerSession;
import ms.world.WorldDatabase;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Scanner;

/**
 * The main class.
 * @author Emperor
 *
 */
public final class Management {

	/**
	 * If the shutdown hook is active.
	 */
	public static boolean active = true;

	/**
	 * The commands.
	 */
	private static final Command[] COMMANDS = {
		new Command("-commands", "Print a list of all commands.") {
			@Override
			public void run(String...args) {
				for (Command c : COMMANDS) {
					System.out.println("Command " + c.getName() + ": " + c.getInfo());
				}
			}
		},
		new Command("-s", "Safely shuts down the server.") {
			@Override
			public void run(String...args) {
				System.out.println("Shutting down Management server...");
				ShutdownSequence.shutdown();
			}
		},
		new Command("-debug", "Debug world info.") {
			@Override
			public void run(String...args) {
				System.out.println("---------------------------------------------");
				for (GameServer server : WorldDatabase.getWorlds()) {
					if (server != null) {
						System.out.println("World [id=" + server.getInfo().getWorldId() + ", IP=" + server.getInfo().getAddress() + ", country=" + server.getInfo().getCountry() + ", members=" + server.getInfo().isMembers() + ", players=" + server.getPlayers().size() + ", active=" + server.isActive() + "].");
					}
				}
			}
		},
		new Command("-pinfo", "Debugs player information (usage: -pinfo emperor).") {
			@Override
			public void run(String...args) {
				String name = args[1];
				PlayerSession player = WorldDatabase.getPlayer(name);
				if (player == null) {
					System.out.println("Player " + name + " was not registered!");
					return;
				}
				System.out.println("Player [name=" + name + ", world=" + player.getWorldId() + ", active=" + player.isActive() + "].");
			}
		},
		new Command("-update", "Calls an update on all the game servers (-update -1 to cancel).") {
			@Override
			public void run(String...args) {
				int ticks = Integer.parseInt(args[1]);
				for (GameServer server : WorldDatabase.getWorlds()) {
					if (server != null && server.isActive()) {
						WorldPacketRepository.sendUpdate(server, ticks);
					}
				}
			}
		},
		new Command("-reloadconfig", "Reloads the configurations of all worlds.") {

			@Override
			public void run(String... args) {
				for (GameServer server : WorldDatabase.getWorlds()) {
					if(server == null) {
						continue;
					}
					WorldPacketRepository.sendConfigReload(server);
				}
			}			
		},
		
		new Command("-rlcache", "Reloads launcher/client resource cache") {
			@Override
			public void run(String... args) {
				ClassLoadServer.resetResourceCache();
				System.out.println("Reloaded resource cache!");
			}	
		},
		
		new Command("-kick", "Kicks a player from the MS (not ingame).") {
			@Override
			public void run(String... args) {
				String name = args[1];
				PlayerSession player = WorldDatabase.getPlayer(name);
				if (player == null) {
					System.out.println("Player " + name + " was not registered!");
					return;
				}
				player.getWorld().getPlayers().remove(name);
				player.setWorldId(0);
				System.out.println("Kicked player " + name + "!");
			}	
		}
	};
	
	/**
	 * The main method.
	 * @param args The arguments cast on runtime.
	 * @throws Throwable When an exception occurs.
	 */
	public static void main(String...args) throws Throwable {
		if (!isLocallyHosted(ServerConstants.HOST_ADDRESS)) {
			System.err.println("WARNING: Configure host address in server constants!");
		}
		System.out.println("-------- 530 Management server --------");
		System.out.println("Starting up...");
		SQLManager.init();
		//NioReactor.configure(ServerConstants.PORT).start();
		NioReactor.configure(5555).start();
		new ClassLoadServer().start();
		Runtime.getRuntime().addShutdownHook(new ShutdownSequence());
		System.out.println("Status: ready.");
		System.out.println("Use -commands for a list of commands!");
		Scanner s = new Scanner(System.in);
		while (s.hasNext()) {
			try {
				String command = s.nextLine();
				if (!command.startsWith("-")) {
					continue;
				}
				String[] arguments = command.split(" ");
				command = arguments[0];
				for (Command c : COMMANDS) {
					if (c.getName().equals(command)) {
						System.out.println("Handling command \"" + command + "\"!");
						c.run(arguments);
					}
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		s.close();
	}
	
	/**
	 * Checks if the Management server is locally hosted.
	 * @return {@code True} if so.
	 * @throws IOException When an I/O exception occurs.
	 */
	private static boolean isLocallyHosted(String ip) throws IOException {
		InetAddress address = InetAddress.getByName(ip);
		if (address.isAnyLocalAddress() || address.isLoopbackAddress()) {
			return true;
		}
		return NetworkInterface.getByInetAddress(address) != null;
	}
}