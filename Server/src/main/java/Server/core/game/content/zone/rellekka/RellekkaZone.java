package core.game.content.zone.rellekka;

import core.cache.def.impl.NPCDefinition;
import core.cache.def.impl.ObjectDefinition;
import core.game.component.Component;
import core.plugin.Initializable;
import core.game.node.entity.skill.agility.AgilityHandler;
import core.game.interaction.Option;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.impl.ForceMovement;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.game.system.task.LocationLogoutTask;
import core.game.system.task.LogoutTask;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.zone.MapZone;
import core.game.world.map.zone.ZoneBorders;
import core.game.world.map.zone.ZoneBuilder;
import core.game.world.update.flag.context.Animation;
import core.plugin.Plugin;
import core.plugin.PluginManager;

/**
 * Handles the rellekka zone.
 * @author Vexia
 */
@Initializable
public final class RellekkaZone extends MapZone implements Plugin<Object> {

	/**
	 * Constructs a new {@code RellekkaZone} {@code Object}.
	 */
	public RellekkaZone() {
		super("rellekka", true);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		PluginManager.definePlugin(new SailorDialogue());
		PluginManager.definePlugin(new JarvaldDialogue());
		PluginManager.definePlugins(new RellekaOptionHandler(), new MariaGunnarsDialogue());
		PluginManager.definePlugin(new OptionHandler() {

			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				NPCDefinition.forId(5507).getHandlers().put("option:ferry-rellekka", this);
				return this;
			}

			@Override
			public boolean handle(Player player, Node node, String option) {
				sail(player, "Relleka", new Location(2644, 3710, 0));
				return true;
			}

		});
		return this;
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		if (e instanceof Player) {
			final Player player = (Player) e;
			switch (target.getId()) {
			case 4306:
			case 4310:
			case 4309:
			case 4304:
			case 4308:
				player.sendMessage("Only Fremenniks may use this " + target.getName().toLowerCase() + ".");
				return true;
				/*
				 * case 1301: player.sendMessage("Only Fremenniks may " +
				 * (option.equals("Trade") ? "buy clothes here" :
				 * "change their shoes here") + "."); return true;
				 */
			case 4165:
				player.getDialogueInterpreter().open(1288);
				return true;
			case 4166:
				player.sendMessage("This door is locked tightly shut.");
				return true;
			case 1288:
				player.getDialogueInterpreter().sendDialogues((NPC) target, null, "I have no interest in talking to you just now", "outerlander.");
				return true;
			case 34286:
				player.getDialogueInterpreter().sendDialogues(1289, null, "Outerlander... do not test my patience. I do not take", "kindly to people wandering in here and acting as though", "they own the place.");
				return true;
			case 4148:
				player.getDialogueInterpreter().sendDialogues(1278, null, "Hey, outerlander. You can't go through there. Talent", "only, backstage.");
				return true;
			case 100:
				player.getDialogueInterpreter().sendDialogue("You try to open the trapdoor but it won't budge! It looks like the", "trapdoor can only be opened from the other side.");
				return true;
			case 2435:
			case 2436:
			case 2437:
			case 2438:
				if (option.getName().equals("Travel")) {
					player.getDialogueInterpreter().open(target.getId(), target, true);
					return true;
				}
				break;
			case 5508:
				if (option.getName().equals("Ferry-Neitiznot")) {
					sail(player, "Neitiznot", new Location(2310, 3782, 0));
				}
				return true;
			}
		}
		return super.interact(e, target, option);
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	@Override
	public void configure() {
		register(new ZoneBorders(2602, 3639, 2739, 3741));
	}

	/**
	 * Sails a player using the relleka ships.
	 * @param player the player.
	 * @param name the name.
	 * @param destination the destination.
	 */
	public static void sail(final Player player, final String name, final Location destination) {
		player.lock();
		player.getInterfaceManager().open(new Component(224));
		player.addExtension(LogoutTask.class, new LocationLogoutTask(5, destination));
		GameWorld.getPulser().submit(new Pulse(1, player) {
			int count;

			@Override
			public boolean pulse() {
				switch (++count) {
				case 5:
					player.unlock();
					player.getInterfaceManager().close();
					player.getProperties().setTeleportLocation(destination);
					player.getDialogueInterpreter().sendDialogue("The ship arrives at " + name + ".");
					return true;
				}
				return false;
			}

		});
	}

	/**
	 * Handles options related to relleka.
	 * @author Vexia
	 */
	public static final class RellekaOptionHandler extends OptionHandler {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			ObjectDefinition.forId(4616).getHandlers().put("option:cross", this);
			ObjectDefinition.forId(4615).getHandlers().put("option:cross", this);
			ObjectDefinition.forId(5847).getHandlers().put("option:climb-over", this);
			ObjectDefinition.forId(5008).getHandlers().put("option:enter",this);
			return this;
		}

		@Override
		public boolean handle(Player player, Node node, String option) {
			switch (option) {
			case "cross":
				switch (node.getId()) {
				case 4616:
				case 4615:
					crossRellekaBridge(player, (GameObject) node);
					break;
				}
				break;
			case "climb-over":
				switch (node.getId()) {
				case 5847:
					AgilityHandler.forceWalk(player, -1, player.getLocation(), player.getLocation().transform(0, player.getLocation().getY() <= 3657 ? 3 : -3, 0), Animation.create(840), 20, 1, null, 0);
					break;
				}
				break;
			case "enter":
				switch(node.getId()){
					case 5008:
						player.getProperties().setTeleportLocation(Location.create(2773, 10162, 0));
						break;
				}
				break;
			}
			return true;
		}

		/**
		 * Crosses the relleka bridge.
		 * @param player the player.
		 * @param node the node.
		 */
		private void crossRellekaBridge(Player player, GameObject node) {
			boolean east = node.getId() == 4616;
			player.lock(2);
			if (player.getLocation().equals(node.getLocation())) {
				AgilityHandler.forceWalk(player, -1, player.getLocation(), player.getLocation().transform(east ? -2 : 2, 0, 0), Animation.create(1115), 20, 1.0, null, 1);
				return;
			}
			AgilityHandler.forceWalk(player, -1, player.getLocation(), player.getLocation().transform(east ? -1 : 1, 0, 0), ForceMovement.WALK_ANIMATION, 10, 0.0, null);
			AgilityHandler.forceWalk(player, -1, player.getLocation().transform(east ? -1 : 1, 0, 0), player.getLocation().transform(east ? -3 : 3, 0, 0), Animation.create(1115), 20, 1.0, null, 1);
		}

	}
}
