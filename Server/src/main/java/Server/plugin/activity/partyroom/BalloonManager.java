package plugin.activity.partyroom;

import java.util.ArrayList;
import java.util.List;

import core.cache.def.impl.ObjectDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.GroundItem;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.game.world.update.flag.context.Animation;
import core.plugin.Plugin;
import core.tools.RandomFunction;

/**
 * Manages the droped party balloons.
 * @author Vexia
 */
public final class BalloonManager extends OptionHandler {

	/**
	 * The list of dropped balloons.
	 */
	private static final List<GameObject> balloons = new ArrayList<>();

	/**
	 * The count down time until droping.
	 */
	private int countdown;

	/**
	 * Constructs a new {@Code BalloonManager} {@Code Object}
	 */
	public BalloonManager() {
		/**
		 * empty.
		 */
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (PartyBalloon balloon : PartyBalloon.values()) {
			ObjectDefinition.forId(balloon.getBalloonId()).getConfigurations().put("option:burst", this);
		}
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (option) {
		case "burst":
			PartyBalloon.forId(node.getId()).burst(player, node.asObject());
			return true;
		}
		return true;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		return n.getLocation();
	}

	/**
	 * Starts the count down.
	 */
	public void start() {
		if (isCountingDown()) {
			return;
		}
		countdown = GameWorld.getTicks() + getDropDelay();
		final NPC partyPete = RegionManager.getNpc(new Location(3052, 3373, 0), 659, 1);
		GameWorld.Pulser.submit(new Pulse(1) {
			@Override
			public boolean pulse() {
				int realCount = --countdown - GameWorld.getTicks();
				for (ChestViewer viewer : PartyRoomPlugin.getViewers().values()) {
					viewer.getPlayer().getConfigManager().set(1135, realCount);
				}
				if (--realCount - GameWorld.getTicks() <= 0) {
					drop();
					return true;
				}
				partyPete.sendChat("" + realCount);
				return realCount < 0;
			}
		});
	}

	/**
	 * Drops the balloons.
	 */
	private void drop() {
		countdown = 0;
		balloons.clear();
		PartyRoomPlugin.getPartyChest().addAll(PartyRoomPlugin.getChestQueue());
		PartyRoomPlugin.getChestQueue().clear();
		PartyRoomPlugin.update();
		GameWorld.Pulser.submit(new Pulse(1) {
			int waves;

			@Override
			public boolean pulse() {
				if (waves == 0 || waves == 3 || waves == 5 || waves == 8 || waves == 10 || waves == 12 || waves == 15 || waves == 18 || waves == 20) {
					for (int i = 0; i < 30; i++) {
						GameObject balloon = getBalloon();
						if (balloon != null) {
							balloons.add(balloon);
							ObjectBuilder.add(balloon, RandomFunction.random(200, 300));
						}
					}
				}
				return ++waves > 20;
			}

		});
	}

	/**
	 * Gets the balloon drop.
	 * @return the balloon.
	 */
	private GameObject getBalloon() {
		final Location location = new Location(3045 + RandomFunction.randomSign(RandomFunction.getRandom(8)), 3378 + RandomFunction.randomSign(RandomFunction.getRandom(6)), 0);
		if (!RegionManager.isTeleportPermitted(location) || RegionManager.getObject(location) != null) {
			return null;
		}
		return new GameObject(PartyBalloon.values()[RandomFunction.random(PartyBalloon.values().length)].getBalloonId(), location);
	}

	/**
	 * Checks if the floor is cluttered.
	 * @return {@code True} if so.
	 */
	public boolean isCluttered() {
		for (GameObject object : balloons) {
			if (RegionManager.getObject(object.getLocation()) != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the drop delay depending on wealth.
	 * @return the delay.
	 */
	public int getDropDelay() {
		int wealth = getWealth();
		if (wealth <= 50000) {
			return 10;
		} else if (wealth >= 50000 && wealth <= 150000) {
			return 100;
		} else if (wealth >= 150000 && wealth <= 1000000) {
			return 500;
		} else if (wealth > 1000000) {
			return 1000;
		}
		return 0;
	}

	/**
	 * Gets the amount of wealth in the chest.
	 * @return the wealth.
	 */
	public int getWealth() {
		return PartyRoomPlugin.getChestQueue().getWealth() + PartyRoomPlugin.getPartyChest().getWealth();
	}

	/**
	 * Checks if the balloon manager is counting down.
	 * @return {@code True} if so.
	 */
	public boolean isCountingDown() {
		return countdown > GameWorld.getTicks();
	}

	/**
	 * Gets the balloons.
	 * @return the balloons
	 */
	public static List<GameObject> getBalloons() {
		return balloons;
	}

	/**
	 * Gets the countdown.
	 * @return the countdown
	 */
	public int getCountdown() {
		return countdown;
	}

	/**
	 * A party balloon.
	 * @author Vexia
	 */
	enum PartyBalloon {
		YELLOW(115, 123), RED(116, 124), BLUE(117, 125), GREEN(118, 126), PURPLE(119, 127), WHITE(120, 128), GREEN_BLUE(121, 129), TRI(122, 130);

		/**
		 * The balloon id.
		 */
		private final int balloonId;

		/**
		 * The popping id.
		 */
		private final int popId;

		/**
		 * Constructs a new {@Code PartyBallon} {@Code Object}
		 * @param balloonId the balloon id.
		 * @param popId the pop id.
		 */
		private PartyBalloon(int balloonId, int popId) {
			this.balloonId = balloonId;
			this.popId = popId;
		}

		/**
		 * Bursts a party balloon.
		 * @param player the player.
		 * @param object the object.
		 */
		public void burst(final Player player, final GameObject object) {
			final GameObject popped = object.transform(popId);
			if (!getBalloons().contains(object)) {
				player.sendMessage("Error! Balloon not registered.");
				return;
			}
			player.lock(2);
			ObjectBuilder.remove(object);
			ObjectBuilder.add(popped);
			getBalloons().remove(object);
			player.animate(Animation.create(10017));
			GameWorld.Pulser.submit(new Pulse(1) {
				int counter;

				@Override
				public boolean pulse() {
					switch (++counter) {
					case 1:
						ObjectBuilder.remove(popped);
						if (!player.getIronmanManager().isIronman() && RandomFunction.random(3) == 1) {
							GroundItem ground = getGround(object.getLocation(), player);
							if (ground != null) {
								GroundItemManager.create(ground);
								PartyRoomPlugin.getPartyChest().shift();
								PartyRoomPlugin.update();
							}
						}
						return true;
					}
					return false;
				}
			});
		}

		/**
		 * Gets the ground item.
		 * @param location the location.
		 * @param player the player.
		 * @return the ground item.
		 */
		private GroundItem getGround(Location location, Player player) {
			final Item item = PartyRoomPlugin.getPartyChest().toArray()[RandomFunction.random(PartyRoomPlugin.getPartyChest().itemCount())];
			if (item == null) {
				return null;
			}
			if (PartyRoomPlugin.getPartyChest().remove(item)) {
				final Item dropItem;
				int newamt;
				if (item.getAmount() > 1) {
					newamt = RandomFunction.random(1, item.getAmount());
					if (item.getAmount() - newamt > 0) {
						Item newItem = new Item(item.getId(), item.getAmount() - newamt);
						PartyRoomPlugin.getPartyChest().add(newItem);
					}
					dropItem = new Item(item.getId(), newamt);
				} else {
					dropItem = item;
				}
				return new GroundItem(dropItem, location, player);
			}
			return null;
		}

		/**
		 * Gets a party balloon.
		 * @param id the id.
		 * @return the balloon.
		 */
		public static PartyBalloon forId(int id) {
			for (PartyBalloon balloon : values()) {
				if (balloon.getBalloonId() == id) {
					return balloon;
				}
			}
			return null;
		}

		/**
		 * Gets the balloonId.
		 * @return the balloonId
		 */
		public int getBalloonId() {
			return balloonId;
		}

		/**
		 * Gets the popId.
		 * @return the popId
		 */
		public int getPopId() {
			return popId;
		}

	}

}
