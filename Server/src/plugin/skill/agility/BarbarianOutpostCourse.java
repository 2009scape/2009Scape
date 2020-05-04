package plugin.skill.agility;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.cache.def.impl.NPCDefinition;
import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.global.action.ClimbActionHandler;
import org.crandor.game.content.global.action.DoorActionHandler;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.agility.AgilityCourse;
import org.crandor.game.content.skill.member.agility.AgilityHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.BarcrawlManager;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.PluginManager;

/**
 * Handles the barbarian outpost course.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class BarbarianOutpostCourse extends AgilityCourse {

	/**
	 * The rope delay.
	 */
	private static int ropeDelay;

	/**
	 * Constructs a new {@code BarbarianOutpostCourse} {@code Object}.
	 */
	public BarbarianOutpostCourse() {
		this(null);
	}

	/**
	 * Constructs a new {@code BarbarianOutpostCourse} {@code Object}.
	 * @param player the player.
	 */
	public BarbarianOutpostCourse(Player player) {
		super(player, 6, 46.2);
	}

	@Override
	public AgilityCourse createInstance(Player player) {
		return new BarbarianOutpostCourse(player);
	}

	@Override
	public boolean handle(final Player player, final Node node, final String option) {
		final int id = node.getId();
		getCourse(player);
		switch (id) {
		case 2115:
		case 2116:
			if (!player.getBarcrawlManager().isFinished() || player.getBarcrawlManager().isStarted()) {
				player.getDialogueInterpreter().open(384);
			} else {
				DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
			}
			break;
		case 20210:
			handlePipe(player, (GameObject) node);
			break;
		case 2282:
			handleRopeSwing(player, (GameObject) node);
			break;
		case 2294:
			handleLogBalance(player, (GameObject) node);
			break;
		case 2302:
			handleLedgeBalance(player, (GameObject) node);
			break;
		case 20211:
			if (player.getLocation().getX() < node.getLocation().getX()) {
				return true;
			}
			AgilityHandler.climb(player, 2, ClimbActionHandler.CLIMB_UP, player.getLocation().transform(-1, 0, 1), 8, "You climb the netting...");
			break;
		case 1948:
			if (player.getLocation().getX() > node.getLocation().getX()) {
				player.getPacketDispatch().sendMessage("You cannot climb from this side.");
				break;
			}
			final int flag = node.getLocation().equals(new Location(2536, 3553, 0)) ? 4 : node.getLocation().equals(new Location(2539, 3553, 0)) ? 5 : 6;
			player.getPacketDispatch().sendMessage("You climb the low wall...");
			AgilityHandler.forceWalk(player, flag, node.getLocation().transform(-1, 0, 0), node.getLocation().transform(1, 0, 0), Animation.create(839), 10, 13.5, null);
			break;
		case 455:
			player.getBarcrawlManager().read();
			break;
		case 385:
			player.getPacketDispatch().sendMessage("The scorpion stings you!");
			player.getImpactHandler().manualHit(player, 3, HitsplatType.NORMAL);
			break;
		}
		return true;
	}

	/**
	 * Handles the squeezing through of a pipe.
	 * @param player the player.
	 * @param object the object.
	 */
	private void handlePipe(final Player player, final GameObject object) {
		if (player.getSkills().getStaticLevel(Skills.AGILITY) < 35) {
			player.getDialogueInterpreter().sendDialogue("You need an Agility level of at least 35 to enter.");
			return;
		}
		AgilityHandler.forceWalk(player, 0, player.getLocation(), player.getLocation().transform(0, getPipeTransform(player), 0), Animation.create(746), 25, 22, null, 1);
		GameWorld.Pulser.submit(new Pulse(2, player) {
			@Override
			public boolean pulse() {
				player.faceLocation(object.getLocation());
				return true;
			}
		});
	}

	/**
	 * Handles the rope swing obstacle.
	 * @param player the player.
	 * @param object the object.
	 */
	private void handleRopeSwing(final Player player, final GameObject object) {
		if (player.getLocation().getY() < 3554) {
			player.getPacketDispatch().sendMessage("You cannot do that from here.");
			return;
		}
		if (ropeDelay > GameWorld.getTicks()) {
			player.getPacketDispatch().sendMessage("The rope is being used.");
			return;
		}
		if (AgilityHandler.hasFailed(player, 1, 0.1)) {
			AgilityHandler.fail(player, 0, Location.create(2549, 9951, 0), null, getHitAmount(player), "You slip and fall to the pit bellow.");
			return;
		}
		ropeDelay = GameWorld.getTicks() + 2;
		player.getPacketDispatch().sendObjectAnimation(object, Animation.create(497), true);
		AgilityHandler.forceWalk(player, 0, player.getLocation(), Location.create(2551, 3549, 0), Animation.create(751), 50, 22, "You skillfully swing across.", 1);
	}

	/**
	 * Handles the log balance obstacle.
	 * @param player the player.
	 * @param object the object.
	 */
	private void handleLogBalance(final Player player, final GameObject object) {
		final boolean failed = AgilityHandler.hasFailed(player, 1, 0.5);
		final Location end = failed ? Location.create(2545, 3546, 0) : Location.create(2541, 3546, 0);
		player.getPacketDispatch().sendMessage("You walk carefully across the slippery log...");
		AgilityHandler.walk(player, failed ? -1 : 1, Location.create(2551, 3546, 0), end, Animation.create(155), failed ? 0.0 : 13.5, failed ? null : "...You make it safely to the other side.");
		if (failed) {
			AgilityHandler.walk(player, -1, player.getLocation(), Location.create(2545, 3546, 0), Animation.create(155), 0.0, null);
			GameWorld.Pulser.submit(getSwimPulse(player));
			return;
		}
	}

	/**
	 * Handles the ledge balance obstacle.
	 * @param player the player.
	 * @param object the object.
	 */
	private void handleLedgeBalance(final Player player, final GameObject object) {
		final boolean failed = AgilityHandler.hasFailed(player, 1, 0.3);
		final Location end = failed ? Location.create(2534, 3547, 1) : Location.create(2532, 3547, 1);
		AgilityHandler.walk(player, failed ? -1 : 3, Location.create(2536, 3547, 1), end, Animation.create(157), failed ? 0.0 : 22, failed ? null : "You skillfully edge across the gap.");
		player.getPacketDispatch().sendMessage("You put your foot on the ledge and try to edge across..");
		if (failed) {
			AgilityHandler.fail(player, 3, Location.create(2534, 3545, 0), new Animation(760), getHitAmount(player), "You slip and fall to the pit bellow.");
			return;
		}
	}

	/**
	 * Gets the pulse used to swim when failing the log balance.
	 * @param player the player.
	 * @return {@code Pulse} the pulse.
	 */
	private Pulse getSwimPulse(final Player player) {
		return new Pulse(1, player) {
			int counter;

			@Override
			public boolean pulse() {
				switch (++counter) {
				case 6:
					player.animate(Animation.create(771));
					player.getPacketDispatch().sendMessages("...You loose your footing and fall into the water.", "Something in the water bites you.");
					break;
				case 7:
					player.graphics(Graphics.create(68));
					player.getProperties().setTeleportLocation(Location.create(2545, 3545, 0));
					player.getImpactHandler().manualHit(player, getHitAmount(player), HitsplatType.NORMAL);
					AgilityHandler.walk(player, -1, Location.create(2545, 3545, 0), Location.create(2545, 3543, 0), Animation.create(752), 0.0, null);
					break;
				case 11:
					player.getProperties().setTeleportLocation(Location.create(2545, 3542, 0));
					return true;
				}
				return false;
			}
		};
	}

	/**
	 * Gets the pipe transformation.
	 * @param player the player.
	 * @return the transform.
	 */
	private int getPipeTransform(final Player player) {
		return (player.getLocation().getY() > 3558 ? -3 : 3);
	}

	@Override
	public void configure() {
		ObjectDefinition.forId(2115).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(2116).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(20210).getConfigurations().put("option:squeeze-through", this);
		ObjectDefinition.forId(2282).getConfigurations().put("option:swing-on", this);
		ObjectDefinition.forId(2294).getConfigurations().put("option:walk-across", this);
		ObjectDefinition.forId(20211).getConfigurations().put("option:climb-over", this);
		ObjectDefinition.forId(2302).getConfigurations().put("option:walk-across", this);
		ObjectDefinition.forId(1948).getConfigurations().put("option:climb-over", this);
		ItemDefinition.forId(455).getConfigurations().put("option:read", this);
		NPCDefinition.forId(385).getConfigurations().put("option:pick-up", this);
		PluginManager.definePlugin(new BarbarianGuardDialogue());
	}

	@Override
	public Location getDestination(Node node, Node n) {
		if (n instanceof GameObject) {
			switch (n.getId()) {
			case 2282:// rope.
				return Location.create(2551, 3554, 0);
			case 20210:
				return node.getLocation().getY() > n.getLocation().getY() ? Location.create(2552, 3561, 0) : Location.create(2552, 3558, 0);
			}
		}
		return null;
	}

	/**
	 * The barbarian guard dialogue plugin.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class BarbarianGuardDialogue extends DialoguePlugin {

		/**
		 * Constructs a new {@code BarbarianGuardDialogue} {@code Object}.
		 */
		public BarbarianGuardDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code BarbarianGuardDialogue} {@code Object}.
		 * @param player the player.
		 */
		public BarbarianGuardDialogue(final Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new BarbarianGuardDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			if (!player.getBarcrawlManager().isStarted()) {
				npc("O, waddya want?");
			} else if (player.getBarcrawlManager().isFinished() && !player.getBarcrawlManager().isStarted()) {
				npc("'Ello friend.");
				stage = 50;
			} else {
				npc("So, how's the Barcrawl coming along?");
				stage = 20;
			}
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				options("I want to come through this gate.", "I want some money.");
				stage++;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					player("I want to come throught his gate.");
					stage = 5;
					break;
				case 2:
					player("I want some money.");
					stage = 3;
					break;
				}
				break;
			case 3:
				npc("Do I look like a bank to you?");
				stage++;
				break;
			case 4:
				end();
				break;
			case 5:
				if (player.getBarcrawlManager().isFinished()) {
					npc("You may pass if you like. You are a true", "barbarian now.");
					stage = 4;
				} else {
					npc("Barbarians only. Are you a barbarian? You don't look", "like one.");
					stage++;
				}
				break;
			case 6:
				options("Hmm, yep you've got me there.", "Looks can be deceiving, I am in fact a barbarian.");
				stage++;
				break;
			case 7:
				switch (buttonId) {
				case 1:
					player("Hmm, yep you've got me there.");
					stage = 4;
					break;
				case 2:
					player("Looks can be deceiving, I am in fact a barbarian.");
					stage++;
					break;
				}
				break;
			case 8:
				npc("If you're a barbarian you need to be able to drink like", "one. We barbarians like a good drink.");
				stage++;
				break;
			case 9:
				npc("I have the perfect challenge for you... the Alfred", "Grimhand Barcrawl! First completed by Alfred", "Grimhand.");
				stage++;
				break;
			case 10:
				player.getBarcrawlManager().reset();
				player.getBarcrawlManager().setStarted(true);
				player.getInventory().add(BarcrawlManager.BARCRAWL_CARD, player);
				interpreter.sendDialogue("The guard hands you a Barcrawl card.");
				stage++;
				break;
			case 11:
				npc("Take that card to each of the bards named on it. The", "bartenders will know what it means. We're kinda well", "known.");
				stage++;
				break;
			case 12:
				npc("They'll give you their strongest drink and sign your", "card. When you've done all that, we'll be happy to let", "you in.");
				stage++;
				break;
			case 13:
				end();
				break;
			case 20:
				if (!player.getBarcrawlManager().hasCard()) {
					player("I've lost my barcrawl card...");
					stage = 23;
				} else if (player.getBarcrawlManager().isFinished()) {
					player("I tink I jusht 'bout done dem all... but I losht count...");
					stage = 24;
				} else {
					player("I haven't finished it yet.");
					stage++;
				}
				break;
			case 21:
				npc("Well come back when you have, you lightweight.");
				stage++;
				break;
			case 22:
				end();
				break;
			case 23:
				npc("What are you like? You're gonna have to start all over", "now.");
				stage = 10;
				break;
			case 24:
				if (!player.getInventory().containsItem(BarcrawlManager.BARCRAWL_CARD)) {
					end();
					break;
				}
				player.getBarcrawlManager().setStarted(false);
				player.getBank().remove(BarcrawlManager.BARCRAWL_CARD);
				player.getInventory().remove(BarcrawlManager.BARCRAWL_CARD);
				interpreter.sendDialogue("You give the card to the barbarian.");
				stage++;
				break;
			case 25:
				npc("Yep that seems fine, you can come in now. I never", "learned to read, but you look like you've drunk plenty.");
				stage = 22;
				break;
			case 50:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 384 };
		}

	}
}
