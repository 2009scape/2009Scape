package plugin.command;

import org.crandor.game.component.Component;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.Rights;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.command.CommandPlugin;
import org.crandor.game.system.command.CommandSet;
import org.crandor.game.system.communication.ClanRepository;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.repository.Repository;
import org.crandor.net.amsc.MSPacketRepository;
import org.crandor.net.amsc.WorldCommunicator;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.ContainerContext;
import org.crandor.net.packet.out.ContainerPacket;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Handles the moderators commands.
 * @author Vexia
 */
@InitializablePlugin
public final class ModeratorCommand extends CommandPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		link(CommandSet.MODERATOR);
		return this;
	}

	@Override
	public boolean parse(final Player player, String name, String[] args) {
		switch (name) {
		case "announce":
			Repository.sendNews(getArgumentLine(args));
			break;
		case "clear":
		case "kick":
			Player target = Repository.getPlayer(args[1]);
			if (WorldCommunicator.isEnabled()) {
				MSPacketRepository.sendPunishment(player, args[1], 6, 0l);
			} else if (target != null) {
				target.getPacketDispatch().sendLogout();
				target.getSession().disconnect();
				target.clear(true);
				player.getPacketDispatch().sendMessage("Kicked player " + args[1] + " from this world.");
			} else {
				player.getPacketDispatch().sendMessage("Player " + args[1] + " was already inactive.");
			}
			return true;
		case "getinfo":
		case "getmac":
		case "getip":
		case "getcompname":
			printInfo(player, args[1]);
			return true;
		case "unban":
			unpunish(player, args[1], 1);
			return true;
		case "unmute":
			unpunish(player, args[1], 0);
			return true;
		case "mute":
		case "permmute":
			punish(player, args[1], args, 0);
			return true;
		case "ban":
		case "permban":
			punish(player, args[1], args, 1);
			return true;
		case "ipban":
			punish(player, args[1], args, 2);
			return true;
		case "macban":
			punish(player, args[1], args, 3);
			return true;
		case "uidban":
		case "mskban":
			punish(player, args[1], args, 4);
			return true;
		case "sysban":
			punish(player, args[1], args, 5);
			return true;
		case "unccban":
			ClanRepository clan = ClanRepository.get(GameWorld.getSettings().getName());
			if (clan == null) {
				return true;
			}
			if (!clan.isBanned(args[1])) {
				player.sendMessage("The player is not banned.");
				return true;
			}
			clan.getBanned().remove(args[1]);
			player.sendMessage("Unbanned the player " + args[1] + " from the cc.");
			return true;
		case "checkbank":
			checkBank(player, args);
			return true;
		case "checkinvy":
			checkInvy(player, args);
			return true;
		case "players":
			if ((player.getInterfaceManager().isOpened() && player.getInterfaceManager().getOpened().getId() != 275) || player.getLocks().isMovementLocked() || player.getLocks().isTeleportLocked()) {
				player.sendMessage("Please finish what you're doing first.");
				return true;
			}
			player.getInterfaceManager().open(new Component(275));
			for (int i = 0; i < 257; i++) {
				player.getPacketDispatch().sendString("", 275, i);
			}
			String red = "<col=8A0808>";
			player.getPacketDispatch().sendString("<col=8A0808>" + "Players" + "</col>", 275, 2);
			StringBuilder builder = new StringBuilder("<br>");
			int count = 0;
			for (Player p : Repository.getPlayers()) {
				if (count > 45) {
					builder.append("<br>Max amount we can show on this interface.");
					break;
				}
				if (p == null || (p.isAdmin() && !GameWorld.getSettings().isDevMode()) && !player.isAdmin() || p.isArtificial()) {
					continue;
				}
				builder.append(red + "<img=" + (Rights.getChatIcon(p) - 1) + ">" + p.getUsername() + " [ip=" + p.getDetails().getIpAddress() + ", name=" + p.getDetails().getCompName() + "]<br><br>");
				count++;
			}
			player.getPacketDispatch().sendString(builder.toString(), 275, 11);
			return true;
		}
		return false;
	}

	/**
	 * Punishes a player.
	 * @param player the player.
	 * @param target The target.
	 * @param args the args.
	 * @param target the target.
	 * @param ban if banned.
	 */
	private Player punish(Player player, String target, String[] args, int type) {
		boolean perm = args[0].contains("perm");
		long duration = perm ? -1l : ((args.length > 2 ? Integer.parseInt(args[2]) : 2) * 24 * 60 * 60_000);
		if (WorldCommunicator.isEnabled()) {
			MSPacketRepository.sendPunishment(player, target, type, duration);
			return null;
		}
		player.sendMessage("Management server is offline, punishment could not be processed.");
		return null;
	}

	/**
	 * Removes the punishment of a player.
	 * @param player the player.
	 * @param target the target.
	 * @param ban if we are unbanning.
	 */
	private void unpunish(Player player, String target, int type) {
		if (WorldCommunicator.isEnabled()) {
			MSPacketRepository.sendPunishment(player, target, type, 0L);
			return;
		}
		player.sendMessage("Management server is offline, removing punishment could not be processed.");
	}

	/**
	 * Prints the info of a player.
	 * @param player the player.
	 * @param target the target.
	 */
	private void printInfo(Player player, String target) {
		if (WorldCommunicator.isEnabled()) {
			MSPacketRepository.sendPunishment(player, target, 7, 0L);
			return;
		}
	}

	/**
	 * Checks another players bank.
	 * @param player the player.
	 * @param args the args.
	 */
	private void checkBank(Player player, String[] args) {
		if (player.getDetails().getRights() == Rights.PLAYER_MODERATOR && !player.getZoneMonitor().isInZone("Moderator Zone")) {
			player.sendMessage("You can only use this command in the moderator room.");
			return;
		}
		Player o = Repository.getPlayer(args[1], true);
		if (o == null) {
			player.sendMessage("Unable to load player " + args[1]);
			return;
		}
		Item[] items = o.getBank().toArray();
		int size = 0;
		int[] slots = new int[items.length];
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null) {
				slots[size++] = i;
			}
		}
		int[] slot = new int[size];
		for (int i = 0; i < size; i++) {
			slot[i] = slots[i];
		}
		PacketRepository.send(ContainerPacket.class, new ContainerContext(player, 762, 89, 90, new Item[] {}, 0, false));
		PacketRepository.send(ContainerPacket.class, new ContainerContext(player, 762, 89, 90, o.getBank().toArray(), false, slots));
		player.getInterfaceManager().open(new Component(12));
		player.getPacketDispatch().sendMessage("Checking " + o.getName() + "'s bank.");
	}

	/**
	 * Checks a players inventory.
	 * @param player the player.
	 * @param args the arguments.
	 */
	private void checkInvy(Player player, String[] args) {
		if (player.getDetails().getRights() == Rights.PLAYER_MODERATOR && !player.getZoneMonitor().isInZone("Moderator Zone")) {
			player.sendMessage("You can only use this command in the moderator room.");
			return;
		}
		Player o = Repository.getPlayer(args[1], true);
		if (o == null) {
			player.sendMessage("Unable to load player " + args[1]);
			return;
		}
		Item[] items = o.getInventory().toArray();
		int size = 0;
		int[] slots = new int[items.length];
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null) {
				slots[size++] = i;
			}
		}
		int[] slot = new int[size];
		for (int i = 0; i < size; i++) {
			slot[i] = slots[i];
		}
		PacketRepository.send(ContainerPacket.class, new ContainerContext(player, 149, 0, 93, new Item[] {}, false));
		PacketRepository.send(ContainerPacket.class, new ContainerContext(player, 149, 0, 93, items, false, slots));
		player.getPacketDispatch().sendMessage("Checking " + o.getName() + "'s inventory.");
	}
}
