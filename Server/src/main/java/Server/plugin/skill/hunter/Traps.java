package plugin.skill.hunter;

import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a trap type.
 * @author Vexia
 */
public enum Traps {
	BIRD_SNARE(new TrapSetting(10006, new int[] { 19175 }, new int[] {}, "lay", 19174, Animation.create(5208), Animation.create(5207), 1),
			new TrapNode(new int[] { 5073 }, 1, 34, new int[] { 19179, 19180 }, new Item[] { new Item(10088, 8), new Item(9978), new Item(526) }),
			new TrapNode(new int[] { 5075 }, 5, 48, new int[] { 19183, 19184 }, new Item[] { new Item(10090, 8), new Item(9978), new Item(526) }),
			new TrapNode(new int[] { 5076 }, 9, 61, new int[] { 19185, 19186 }, new Item[] { new Item(10091, 8), new Item(9978), new Item(526) }),
			new TrapNode(new int[] { 5074 }, 11, 64.7, new int[] { 19181, 19182 }, new Item[] { new Item(10089, 8), new Item(9978), new Item(526) }),
			new TrapNode(new int[] { 5072 }, 19, 95.2, new int[] { 19177, 19178 }, new Item[] { new Item(10087, 8), new Item(9978), new Item(526) }),
			new TrapNode(new int[] { 7031 }, 39, 167, new int[] { 28931, 28930 }, new Item[] { new Item(11525, 8), new Item(9978), new Item(526) }) {
		@Override
		public boolean canCatch(TrapWrapper wrapper, final NPC npc) {
			return false;
		}
	}), 
	BOX_TRAP(new TrapSetting(10008, new int[] { 19187 }, new int[] { 1963, 12579, 1869, 9996, 5972, 12535 }, "lay", 19192, Animation.create(5208), new Animation(9726), 27),
			new BoxTrapNode(new int[] { 5081 }, 27, 100, new Item[] { new Item(10092) }, 1),
			new BoxTrapNode(new int[] { 6918, 7289, 7290, 7291, 7292 }, 27, 100, new Item[] { new Item(12184) }, 10),
			new BoxTrapNode(new int[] { 1487 }, 27, 100, new Item[] { new Item(4033, 1) }, 95),
			new BoxTrapNode(new int[] { 7021, 7022, 7023 }, 48, 150, new Item[] { new Item(12551, 1) }, 1),
			new BoxTrapNode(new int[] { 5079 }, 53, 198, new Item[] { new Item(10033, 1) }, 1),
			new BoxTrapNode(new int[] { 5428, 5430, 5449, 5450, 5451 }, 56, 150, new Item[] { new Item(12188) }, 1),
			new BoxTrapNode(new int[] { 5080 }, 63, 265, new Item[] { new Item(10034, 1) }, 1),
			new BoxTrapNode(new int[] { 7012, 7014 }, 66, 400, new Item[] { new Item(12535) }, 1),
			new BoxTrapNode(new int[] { 8654 }, 73, 315, new Item[] { new Item(14861) }, 1),
			new BoxTrapNode(new int[] { 7010, 7011 }, 77, 1100, new Item[] { new Item(12539, 1) }, 1)),
	RABBIT_SNARE(new TrapSetting(10031, new int[] { 19333 }, new int[] {}, "lay", -1, Animation.create(5208), Animation.create(9726), 27)), 
	IMP_BOX(new MagicBoxSetting(),
			new TrapNode(new int[] { 708, 709, 1531 }, 71, 450, new int[] { -1, 19226 }, new Item[] { new Item(10027) })),
	DEAD_FALL(new DeadfallSetting(),
			new TrapNode(new int[] { 5089 }, 23, 128, new int[] { 19213, 19214, 19218 }, new Item[] { new Item(10113), new Item(526) }),
			new TrapNode(new int[] { 5088 }, 33, 168, new int[] { 19211, 19212, 19217 }, new Item[] { new Item(10129), new Item(526) }),
			new TrapNode(new int[] { 5086 }, 37, 204, new int[] { 19208, 19208, 19217 }, new Item[] { new Item(10105), new Item(526) }),
			new TrapNode(new int[] { 7039 }, 44, 200, new int[] { 28939, 28940, 28941 }, new Item[] { new Item(12567), new Item(526) }),
			new TrapNode(new int[] { 5087 }, 51, 200, new int[] { 19209, 19210, 19216 }, new Item[] { new Item(10109), new Item(526) })),
	NET_TRAP(new NetTrapSetting(), new TrapNode(new int[] { 5117 }, 29, 152, new int[] {}, new Item[] { new Item(10149) }),
			new TrapNode(new int[] { 5114 }, 47, 224, new int[] {}, new Item[] { new Item(10146) }),
			new TrapNode(new int[] { 6921 }, 29, 152, new int[] {}, new Item[] { new Item(12130) }),
			new TrapNode(new int[] { 5115 }, 59, 272, new int[] {}, new Item[] { new Item(10147) }),
			new TrapNode(new int[] { 5116 }, 67, 304, new int[] {}, new Item[] { new Item(10148) }));

	/**
	 * The location hooks for this node.
	 */
	private final List<TrapHook> hooks = new ArrayList<>();

	/**
	 * The trap settings.
	 */
	private final TrapSetting settings;

	/**
	 * The nodes related to the trap.
	 */
	private final TrapNode[] nodes;

	/**
	 * Constructs a new {@code TrapType} {@code Object}.
	 * @param settings the settings.
	 * @param nodes the nodes.
	 */
	Traps(TrapSetting settings, TrapNode... nodes) {
		this.settings = settings;
		this.nodes = nodes;
	}

	/**
	 * Creates a trap instance.
	 * @param player the player.
	 */
	public void create(Player player, Node node) {
		player.getPulseManager().run(new TrapCreatePulse(player, node, this));
	}

	/**
	 * Dismantles a trap.
	 * @param player the player.
	 * @param object the object.
	 */
	public void dismantle(Player player, GameObject object) {
		if (!player.getHunterManager().isOwner(object)) {
			player.sendMessage("This isn't your trap!");
			return;
		}
		if (player.getHunterManager().getWrapper(object) == null) {
			System.err.println("NO WRAPPER (HUNTER DISMANTLE)");
			return;
		}
		player.faceLocation(object.getLocation());
		player.getPulseManager().run(new TrapDismantlePulse(player, object, player.getHunterManager().getWrapper(object)));
	}

	/**
	 * Investigates the trap.
	 * @param player the player.
	 * @param object the object.
	 */
	public void investigate(Player player, GameObject object) {
		getSettings().investigate(player, object);
	}

	/**
	 * Called when an npc is hooked.
	 * @param wrapper the wrapper.
	 * @param npc the npc.
	 */
	public void catchNpc(TrapWrapper wrapper, NPC npc) {
		final TrapNode trapNode = forNpc(npc);
		if (trapNode == null || !trapNode.canCatch(wrapper, npc) || !settings.canCatch(wrapper, npc)) {
			return;
		}
		settings.catchNpc(wrapper, trapNode, npc);
	}

	/**
	 * Adds a hook.
	 * @param wrapper the wrapper.
	 */
	public TrapHook addHook(TrapWrapper wrapper) {
		TrapHook hook = settings.createHook(wrapper);
		hooks.add(hook);
		return hook;
	}

	/**
	 * Gets a node by the npc.
	 * @param npc the npc.
	 * @return the node.
	 */
	public TrapNode forNpc(NPC npc) {
		for (TrapNode node : nodes) {
			for (int npcId : node.getNpcIds()) {
				if (npcId == npc.getId()) {
					return node;
				}
			}
		}
		return null;
	}

	/**
	 * Gets a trap by the node id.
	 * @param node the node.
	 * @return the {@code Traps}.
	 */
	public static Traps forNode(Node node) {
		for (Traps trap : Traps.values()) {
			for (int nodeId : trap.getSettings().getNodeIds()) {
				if (node.getId() == nodeId) {
					return trap;
				}
			}
			for (int objectId : trap.getSettings().getObjectIds()) {
				if (objectId == node.getId()) {
					return trap;
				}
			}
			for (TrapNode n : trap.getNodes()) {
				for (int id : n.getObjectIds()) {
					if (id == node.getId()) {
						return trap;
					}
				}
			}
			if (trap.getSettings().getFailId() == node.getId()) {
				return trap;
			}
			if (trap == NET_TRAP) {
				for (NetTrapSetting.NetTrap net : NetTrapSetting.NetTrap.values()) {
					if (net.getOriginal() == node.getId() || net.getFailed() == node.getId() || net.getNet() == node.getId() || net.getBent() == node.getId() || net.getCaught() == node.getId()) {
						return trap;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Gets a trap node by the id.
	 * @param id the id.
	 * @return the node.
	 */
	public static Object[] getNode(int id) {
		for (Traps trap : values()) {
			for (TrapNode t : trap.getNodes()) {
				for (int i : t.getNpcIds()) {
					if (i == id) {
						return new Object[] { trap, t };
					}
				}
			}
		}
		return null;
	}

	/**
	 * Gets the wrapper by the hook.
	 * @param location the location.
	 * @return the wrapper.
	 */
	public TrapWrapper getByHook(Location location) {
		for (TrapHook hook : hooks) {
			if (hook.isHooked(location)) {
				return hook.getWrapper();
			}
		}
		return null;
	}

	/**
	 * Gets the settings.
	 * @return The settings.
	 */
	public TrapSetting getSettings() {
		return settings;
	}

	/**
	 * Gets the nodes.
	 * @return The nodes.
	 */
	public TrapNode[] getNodes() {
		return nodes;
	}

	/**
	 * Gets the hooks.
	 * @return The hooks.
	 */
	public List<TrapHook> getHooks() {
		return hooks;
	}

}
