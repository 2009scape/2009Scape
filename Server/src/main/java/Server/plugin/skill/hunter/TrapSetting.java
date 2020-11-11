package plugin.skill.hunter;

import core.cache.def.impl.ItemDefinition;
import core.cache.def.impl.ObjectDefinition;
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
import core.game.world.update.flag.context.Animation;
import core.tools.RandomFunction;

/**
 * A setting for a trap type.
 * @author Vexia
 */
public class TrapSetting {

	/**
	 * The node ids to create the trap.
	 */
	private final int[] nodeIds;

	/**
	 * The items required.
	 */
	private final Item[] items;

	/**
	 * The create option.
	 */
	private final String option;

	/**
	 * The level required to set the trap.
	 */
	private final int level;

	/**
	 * The setup animation of the trap.
	 */
	private final Animation setupAnimation;

	/**
	 * The animation
	 */
	private final Animation dismantleAnimation;

	/**
	 * The fail object id.
	 */
	private final int failId;

	/**
	 * The corresponding object ids.
	 */
	private final int[] objectIds;

	/**
	 * The bait ids.
	 */
	private final int[] baitIds;

	/**
	 * If it's an object trap.
	 */
	private final boolean objectTrap;

	/**
	 * Constructs a new {@code TrapSetting} {@code Object}.
	 * @param nodeIds the node ids.
	 * @param items the items.
	 * @param level the level.
	 * @param option the option.
	 * @param setupAnimation the animation.
	 * @param objectTrap if an object trap.
	 */
	public TrapSetting(int[] nodeIds, Item[] items, int[] objectIds, final int[] baitIds, final String option, int level, final int failId, final Animation setupAnimation, final Animation dismantleAnimation, boolean objectTrap) {
		this.nodeIds = nodeIds;
		this.items = items;
		this.objectIds = objectIds;
		this.baitIds = baitIds;
		this.level = level;
		this.option = option;
		this.objectTrap = objectTrap;
		this.failId = failId;
		this.setupAnimation = setupAnimation;
		this.dismantleAnimation = dismantleAnimation;
	}

	/***
	 * Constructs a new {@code TrapSetting} {@code Object}.
	 * @param nodeId the node id.
	 * @param items the items.
	 * @param option the option.
	 * @param level the level.
	 * @param objectTrap if an object trap.
	 */
	public TrapSetting(int nodeId, Item[] items, int[] objectIds, final int[] baitIds, final String option, int level, final int failId, final Animation setupAnimation, final Animation dismantleAnimation, boolean objectTrap) {
		this(new int[] { nodeId }, items, objectIds, baitIds, option, level, failId, setupAnimation, dismantleAnimation, objectTrap);
	}

	/**
	 * Constructs a new {@code TrapSetting} {@code Object}.
	 * @param nodeId the node id.
	 * @param option the option.
	 * @param objectIds the ids.
	 * @param level the level.
	 */
	public TrapSetting(int nodeId, int[] objectIds, final int[] baitIds, String option, final int failId, final Animation setupAnimation, final Animation dismantleAnimation, int level) {
		this(new int[] { nodeId }, objectIds, baitIds, option, level, failId, setupAnimation, dismantleAnimation, false);
	}

	/**
	 * Constructs a new {@code TrapSetting} {@code Object}.
	 * @param nodeIds the node ids.
	 * @param option the option.
	 * @param level the level.
	 * @param objectTrap the object trap.
	 */
	public TrapSetting(int[] nodeIds, int[] objectIds, final int[] baitIds, String option, int level, final int failId, final Animation setupAnimation, final Animation dismantleAnimation, boolean objectTrap) {
		this(nodeIds, new Item[] { new Item(nodeIds[0]) }, objectIds, baitIds, option, level, failId, setupAnimation, dismantleAnimation, objectTrap);
	}

	/**
	 * Clears the trap.
	 * @param wrapper the wrapper.
	 * @param type the clear type (0=groundItems, 1=inventory)
	 * @return {@code True} if cleared.
	 */
	public boolean clear(TrapWrapper wrapper, int type) {
		GameObject object = wrapper.getObject();
		returnItems(object, wrapper, type);
		wrapper.getType().getHooks().remove(wrapper.getHook());
		removeObject(wrapper);
		return true;
	}

	/**
	 * Returns the items to the ground or player.
	 * @param object the object.
	 * @param wrapper the wrapper.
	 * @param type the type.
	 */
	public void returnItems(GameObject object, TrapWrapper wrapper, int type) {
		boolean ground = type == 0;
		Player player = wrapper.getPlayer();
		if (!isObjectTrap()) {
			if (ground) {
				createGroundItem(items[0], object.getLocation(), wrapper.getPlayer());
				return;
			}
			addTool(player, wrapper, type);
			player.getInventory().add(wrapper.getItems().toArray(new Item[] {}));
		} else {
			if (isObjectTrap() && !ground) {
				player.getInventory().add(wrapper.getItems().toArray(new Item[] {}));
				return;
			}
		}
	}

	/**
	 * Adds the tool back to the inventory.
	 * @param player the player.
	 * @param wrapper the wrapper.
	 * @param type the type.
	 */
	public void addTool(Player player, TrapWrapper wrapper, int type) {
		player.getInventory().add(items[0]);
	}

	/**
	 * Checks if the player has the required items.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public boolean hasItems(Player player) {
		for (Item item : items) {
			if (!player.getInventory().containsItem(item)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Creates a ground item.
	 * @param item the item.
	 * @param location the location.
	 * @param player the player.
	 */
	public void createGroundItem(Item item, Location location, Player player) {
		GroundItemManager.create(new GroundItem(item, location, player));
	}

	/**
	 * Removes the object from a wrapper.
	 * @param wrapper the object.
	 */
	public boolean removeObject(TrapWrapper wrapper) {
		return removeObject(wrapper.getObject());
	}

	/**
	 * Removes the object from the region.
	 * @param object the object.
	 * @return {@code True} if so.
	 */
	public boolean removeObject(GameObject object) {
		return ObjectBuilder.remove(object);
	}

	/**
	 * Builds a game object.
	 * @param node the node.
	 * @return the object.
	 */
	public GameObject buildObject(Player player, Node node) {
		return new GameObject(objectIds[0], player.getLocation());
	}

	/**
	 * Investigates a trap.
	 * @param player the player.
	 * @param object the object.
	 */
	public void investigate(Player player, GameObject object) {
		if (!player.getHunterManager().isOwner(object)) {
			player.sendMessage("This isn't your trap.");
			return;
		}
		TrapWrapper wrapper = player.getHunterManager().getWrapper(object);
		player.sendMessage("This trap " + (wrapper.isSmoked() ? "has" : "hasn't") + " been smoked.");
	}

	/**
	 * Catches an npc.
	 * @param wrapper the wrapper.
	 * @param npc the npc.
	 */
	public void catchNpc(final TrapWrapper wrapper, final TrapNode node, final NPC npc) {
		final boolean success = isSuccess(wrapper.getPlayer(), node);
		int ticks = success ? 3 : 2;
		npc.lock(ticks);
		wrapper.setBusyTicks(ticks);
		npc.getWalkingQueue().reset();
		npc.getPulseManager().clear();
		wrapper.setTicks(wrapper.getTicks() + 4);
		GameWorld.getPulser().submit(getCatchPulse(wrapper, node, npc, success));
	}

	/**
	 * Handles the catch on a pulse.
	 * @param counter the counter.
	 * @param wrapper the wrapper.
	 * @param node the node.
	 * @param npc the npc.
	 * @param success the success.
	 */
	public void handleCatch(int counter, TrapWrapper wrapper, TrapNode node, NPC npc, boolean success) {
	}

	/**
	 * Gets the catch pulse.
	 * @param wrapper the wrapper.
	 * @param node the node.
	 * @param npc the npc.
	 * @return the pulse.
	 */
	public Pulse getCatchPulse(final TrapWrapper wrapper, final TrapNode node, final NPC npc, final boolean success) {
		final Player player = wrapper.getPlayer();
		wrapper.setFailed(!success);
		return new Pulse(1) {
			int counter;

			@Override
			public boolean pulse() {
				switch (++counter) {
				case 2:
					handleCatch(counter, wrapper, node, npc, success);
					if (success) {
						int transformId = getTransformId(wrapper, node);
						npc.setAttribute("hunter", GameWorld.getTicks() + 6);
						npc.finalizeDeath(player);
						if (transformId != -1) {
							wrapper.setObject(getTransformId(wrapper, node));
						}
						npc.getProperties().setTeleportLocation(npc.getProperties().getSpawnLocation());
						break;
					}
					npc.moveStep();
					wrapper.setObject(getFailId(wrapper, node));
					break;
				case 3:
					handleCatch(counter, wrapper, node, npc, success);
					if (success) {
						wrapper.setTicks(GameWorld.getTicks() + 100);
						wrapper.setReward(node);
						wrapper.setObject(getFinalId(wrapper, node));
						return true;
					}
					npc.moveStep();
					return true;
				}
				return false;
			}

		};
	}

	/**
	 * Checks if the catch was successfull.
	 * @param player the player.
	 * @param node the node.
	 * @return {@code True} if so.
	 */
	public boolean isSuccess(Player player, final TrapNode node) {
		double level = player.getHunterManager().getStaticLevel();
		double req = node.getLevel();
		double successChance = Math.ceil((level * 50 - req * 17) / req / 3 * 4);
		int roll = RandomFunction.random(99);
		if (successChance >= roll) {
			return true;
		}
		return false;
	}

	/**
	 * Creates a trap hook.
	 * @param wrapper the wrapper.
	 * @return the hook.
	 */
	public TrapHook createHook(TrapWrapper wrapper) {
		return new TrapHook(wrapper, new Location[] { wrapper.getObject().getLocation() });
	}

	/**
	 * Calles when an object is setup.
	 * @param player the player.
	 * @param node the node.
	 * @param wrapper the wrapper.
	 */
	public void reward(Player player, Node node, TrapWrapper wrapper) {

	}

	/**
	 * A global method to check if a catch can be made.
	 * @param wrapper the wrapper.
	 * @param npc the npc.
	 * @return {@code True} if so.
	 */
	public boolean canCatch(TrapWrapper wrapper, NPC npc) {
		return true;
	}

	/**
	 * Checks if the bait corresponds.
	 * @param bait the bait.
	 * @return {@code True} if so.
	 */
	public boolean hasBait(Item bait) {
		for (int id : getBaitIds()) {
			if (id == bait.getId()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the node for the object.
	 * @param objectId the object.
	 * @return the node id.
	 */
	public int getNodeForObjectId(int objectId) {
		return getNodeForObject(getObjectIndex(objectId));
	}

	/**
	 * Gets the node for the object.
	 * @param index the index.
	 * @return the id.
	 */
	public int getNodeForObject(int index) {
		return nodeIds[index];
	}

	/**
	 * Gets the object id for the node.
	 * @param node the node. the id.
	 */
	public int getObjectForNode(Node node) {
		return getObjectForNode(getNodeIndex(node));
	}

	/**
	 * Gets the object id for the node.
	 * @param index the index.
	 * @return the id.
	 */
	public int getObjectForNode(int index) {
		return objectIds[index];
	}

	/**
	 * Gets the node index.
	 * @param node the node.
	 * @return the index.
	 */
	public int getNodeIndex(Node node) {
		for (int i = 0; i < nodeIds.length; i++) {
			if (node.getId() == nodeIds[i]) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Gets the object index.
	 * @param objectId the object.
	 * @return the index.
	 */
	public int getObjectIndex(int objectId) {
		for (int i = 0; i < objectIds.length; i++) {
			if (objectId == objectIds[i]) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Gets the transform id.
	 * @param node the node.
	 * @param wrapper the object.
	 * @return the id.
	 */
	public int getTransformId(TrapWrapper wrapper, TrapNode node) {
		return node.getTransformId();
	}

	/**
	 * Gets the final id.
	 * @param wrapper the wrapper.
	 * @param node the node.
	 * @return the id.
	 */
	public int getFinalId(TrapWrapper wrapper, TrapNode node) {
		return node.getFinalId();
	}

	/**
	 * Gets the failId.
	 * @param wrapper the wrapper.
	 * @param node the node.
	 * @return The failId.
	 */
	public int getFailId(TrapWrapper wrapper, TrapNode node) {
		return failId;
	}

	/**
	 * Gets the limit message.
	 * @param player the player.
	 * @return the message.
	 */
	public String getLimitMessage(Player player) {
		return "You don't have a high enough Hunter level to set up more than " + player.getHunterManager().getMaximumTraps() + " trap" + (player.getHunterManager().getMaximumTraps() == 1 ? "." : "s.");
	}

	/**
	 * Gets the name of the trap.
	 * @return the name.
	 */
	public String getName() {
		if (isObjectTrap()) {
			return ObjectDefinition.forId(nodeIds[0]).getName().toLowerCase();
		}
		return ItemDefinition.forId(nodeIds[0]).getName().toLowerCase();
	}

	/**
	 * Gets the time up message.
	 * @return the message.
	 */
	public String getTimeUpMessage() {
		return "The " + getName() + " " + (isObjectTrap() ? "trap that you constructed has collapsed." : "that you laid has fallen over.");
	}

	/**
	 * Checks if adding this trap will exceed the limit.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public boolean exceedsLimit(Player player) {
		return false;
	}

	/**
	 * Gets the objectTrap.
	 * @return The objectTrap.
	 */
	public boolean isObjectTrap() {
		return objectTrap;
	}

	/**
	 * Gets the nodeIds.
	 * @return The nodeIds.
	 */
	public int[] getNodeIds() {
		return nodeIds;
	}

	/**
	 * Gets the items.
	 * @return The items.
	 */
	public Item[] getItems() {
		return items;
	}

	/**
	 * Gets the option.
	 * @return The option.
	 */
	public String getOption() {
		return option;
	}

	/**
	 * Gets the level.
	 * @return The level.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets the setupAnimation.
	 * @return The setupAnimation.
	 */
	public Animation getSetupAnimation() {
		return setupAnimation;
	}

	/**
	 * Gets the objectIds.
	 * @return The objectIds.
	 */
	public int[] getObjectIds() {
		return objectIds;
	}

	/**
	 * Gets the dismantleAnimation.
	 * @return The dismantleAnimation.
	 */
	public Animation getDismantleAnimation() {
		return dismantleAnimation;
	}

	/**
	 * The fail id.
	 * @return the id.
	 */
	public int getFailId() {
		return failId;
	}

	/**
	 * Gets the baitIds.
	 * @return The baitIds.
	 */
	public int[] getBaitIds() {
		return baitIds;
	}

}
