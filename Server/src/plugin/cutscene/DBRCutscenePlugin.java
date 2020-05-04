package plugin.cutscene;

import org.crandor.game.component.Component;
import org.crandor.game.content.activity.ActivityPlugin;
import org.crandor.game.content.activity.CutscenePlugin;
import org.crandor.game.content.global.action.DoorActionHandler;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.SpellBookManager;
import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.item.ItemPlugin;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.map.build.DynamicRegion;
import org.crandor.game.world.map.path.Pathfinder;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.CameraContext;
import org.crandor.net.packet.context.CameraContext.CameraType;
import org.crandor.net.packet.out.CameraViewPacket;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.PluginManager;

/**
 * Represents the draynor bank robbery cutscene plugin.
 * 
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class DBRCutscenePlugin extends CutscenePlugin {

	/**
	 * Represents the cracked component.
	 */
	private static final Component CRACKED = new Component(385);

	/**
	 * Represents the stealing animation used for cool mom.
	 */
	private static final Animation STEAL_ANIMATION = new Animation(832);

	/**
	 * Represents the casting animation.
	 */
	private static final Animation CAST_ANIMATION = new Animation(1167);

	/**
	 * Represents the telekentic animation to use.
	 */
	private static final Animation TELEKENTIC_ANIMATION = new Animation(723);

	/**
	 * Represents the telekentic graphics.
	 */
	private static final Graphics TELEKENTIC_GRAPHIC = new Graphics(142, 96);

	/**
	 * Represents the casting animation.
	 */
	private static final Animation SECOND_CAST_ANIMATION = new Animation(1167);

	/**
	 * Represents the death animation to use.
	 */
	private static final Animation DEATH_ANIMATION = new Animation(2553);

	/**
	 * Represents the graphics to use.
	 */
	private static final Graphics SHOCK_GRAPHIC = new Graphics(432, 0, 0);

	/**
	 * Represents the teleport animation to use.
	 */
	private static final Animation TELEPORT_ANIMATION = new Animation(1816);

	/**
	 * Represents the graphics to use for teleother.
	 */
	private static final Graphics[] TELE_OTHERS = new Graphics[] { new Graphics(343), new Graphics(342) };

	/**
	 * Represents the pick up animation to use.
	 */
	private static final Animation PICK_UP_ANIMATION = new Animation(827);

	/**
	 * Represents the hitting animation.
	 */
	private static final Animation HIT_ANIMATION = new Animation(401);

	/**
	 * Represents the blocking animation.
	 */
	private static final Animation BLOCK_ANIMATION = new Animation(425);

	/**
	 * Represents the thunder animation.
	 */
	private static final Animation THUNDER_ANIMATION = new Animation(811);

	/**
	 * Represents the purple graphics.
	 */
	private static final Graphics PURPLE_GRAPHIC = new Graphics(301, 100);

	/**
	 * Represents the thunder graphic.
	 */
	private static final Graphics THUNDER_GRAPHIC = new Graphics(76);

	/**
	 * Represents the animation to use for the wise old man.
	 */
	private static final Animation WISE_JUMP = new Animation(2555);

	/**
	 * Represents the market guard jump animation.
	 */
	private static final Animation GUARD_JUMP = new Animation(2556);

	/**
	 * Represents the stomp animation.
	 */
	private static final Animation STOMP_ANIM = new Animation(1820);

	/**
	 * Represents the ashes item.
	 */
	private static final Item ASHES = new Item(592);

	/**
	 * Represents the coins item.
	 */
	private static final Item COINS = new Item(995, 60);

	/**
	 * Represents the party hat item.
	 */
	private static final Item PARTY_HAT = new Item(2422);

	/**
	 * Represents the recording pulse.
	 */
	private final RecordingPulse recordingPulse = new RecordingPulse();

	/**
	 * Represents the npcs to use in the cutscene.
	 */
	private static final NPC[] NPCS = new NPC[] {
	/** cool mom */
	NPC.create(2579, Location.create(2120, 4916, 0), Direction.NORTH),
	/** olivia */
	NPC.create(2572, Location.create(2117, 4913, 0), Direction.WEST),
	/** market guard */
	NPC.create(2571, Location.create(13, 48, 0), Direction.WEST),
	/** purepker */
	NPC.create(2575, new Location(2136, 4914, 0), Direction.WEST),
	/** first banker */
	NPC.create(2568, new Location(2130, 4909, 0), Direction.EAST),
	/** second last banker. */
	NPC.create(2569, new Location(2130, 4907, 0)),
	/** last banker */
	NPC.create(2570, new Location(2130, 4906, 0)),
	/** efinlocks */
	NPC.create(2578, new Location(2133, 4908, 0)),
	/** 1337sp34kr */
	NPC.create(2577, new Location(2134, 4906, 0), Direction.WEST),
	/** quitedoll */
	NPC.create(2576, new Location(2133, 4906, 0), Direction.EAST),
	/** oldman */
	NPC.create(2566, new Location(2128, 4915, 0), Direction.SOUTH) };

	/**
	 * Constructs a new {@code DBRCutscenePlugin} {@code Object}.
	 */
	public DBRCutscenePlugin() {
		super("dbr cutscene");
	}

	/**
	 * Constructs a new {@code DBRCutscenePlugin} {@code Object}.
	 * 
	 * @param player
	 *            the player.
	 */
	public DBRCutscenePlugin(final Player player) {
		super("dbr cutscene");
		this.player = player;
	}

	@Override
	public void open() {
		setNpcs();
		GameWorld.Pulser.submit(recordingPulse);
		player.lock();
		player.getLocks().lockMovement(10000000);
		camera(27, 45, -14, 2, 700, 100);
	}

	@Override
	public ActivityPlugin newInstance(Player p) throws Throwable {
		return new DBRCutscenePlugin(p);
	}

	@Override
	public void register() {
		super.register();
		try {
			new DBRecordingNPC().newInstance(null);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		PluginManager.definePlugin(new BluePhatItem());
	}

	@Override
	public Location getSpawnLocation() {
		return null;
	}

	@Override
	public Location getStartLocation() {
		return base.transform(17, 51, 0);
	}

	@Override
	public void configure() {
		region = DynamicRegion.create(8524);
		setRegionBase();
		registerRegion(region.getId());
		player.getDialogueInterpreter().sendPlainMessage(true, "You close your eyes and watch the recording...");
	}

	@Override
	public void end() {
		super.end();
		clearNpcs();
		player.getSavedData().getGlobalData().setDraynorRecording(true);
		player.getDialogueInterpreter().sendDialogue("End of recording.");
	}

	/**
	 * Gets the cool mom npc.
	 * 
	 * @return the cool mom npc.
	 */
	public NPC getCoolMom() {
		return getNpc(2579);
	}

	/**
	 * Gets the olivia npc.
	 * 
	 * @return the olivia npc.
	 */
	public NPC getOlivia() {
		return getNpc(2572);
	}

	/**
	 * Gets the market guard npc.
	 * 
	 * @return the market guard.
	 */
	public NPC getMarketGuard() {
		return getNpc(2571);
	}

	/**
	 * Gets the pure pker npc.
	 * 
	 * @return the pure pker npc.
	 */
	public NPC getPurePker() {
		return getNpc(2575);
	}

	/**
	 * Gets the elfinlocks npc.
	 * 
	 * @return the elfinocks npc.
	 */
	public NPC getElfinocks() {
		return getNpc(2578);
	}

	/**
	 * Gets the 1337sp34kr npc.
	 * 
	 * @return the 1337sp34kr npc.
	 */
	public NPC get1337() {
		return getNpc(2577);
	}

	/**
	 * Gets the quitedoll npc.
	 * 
	 * @return the quitedoll npc.
	 */
	public NPC getQuiteDoll() {
		return getNpc(2576);
	}

	/**
	 * Gets the wise old man npc.
	 * 
	 * @return the wise old man npc.
	 */
	public NPC getWiseOldMan() {
		int size = region.getPlanes()[0].getNpcs().size();
		return region.getPlanes()[0].getNpcs().get(size - 1);
	}

	/**
	 * Gets the first banker.
	 * 
	 * @return the first banker.
	 */
	public NPC getFirstBanker() {
		return getNpc(2568);
	}

	/**
	 * Gets the second banker.
	 * 
	 * @return the second banker.
	 */
	public NPC getSecondBanker() {
		return getNpc(2569);
	}

	/**
	 * Gets the last banker.
	 * 
	 * @return the last banker.
	 */
	public NPC getLastBanker() {
		return getNpc(2570);
	}

	/**
	 * Method used to walk an npc.
	 * 
	 * @param npc
	 *            the npc.
	 * @param location
	 *            the location.
	 */
	private void walk(NPC npc, final Location location) {
		Pathfinder.find(npc, location, true, Pathfinder.DUMB).walk(npc);
	}

	/**
	 * Method used to kill an npc.
	 * 
	 * @param npc
	 *            the npc.
	 */
	private void die(NPC npc) {
		npc.getAnimator().reset();
		npc.animate(DEATH_ANIMATION);
		npc.graphics(SHOCK_GRAPHIC);
		npc.getImpactHandler().manualHit(npc, npc.getSkills().getLifepoints(), HitsplatType.NORMAL);
	}

	/**
	 * Method used to cast a shock.
	 * 
	 * @param target
	 *            the target.
	 */
	private void castShock(Node target) {
		getWiseOldMan().animate(CAST_ANIMATION);
		getWiseOldMan().graphics(new Graphics(433));
		if (target instanceof Entity) {
			Projectile.create(getWiseOldMan(), target instanceof Entity ? ((Entity) target) : null, 434).send();
		} else {
			Projectile projectile = Projectile.create(getWiseOldMan(), null, 434, 30, 30, 41, 140, 0, 0);
			projectile.setEndLocation((Location) target);
			projectile.send();
		}
	}

	/**
	 * Method used to cast the telekinetic grab spell.
	 */
	private void castTelekinetic() {
		getWiseOldMan().animate(TELEKENTIC_ANIMATION);
		getWiseOldMan().graphics(TELEKENTIC_GRAPHIC);
		getWiseOldMan().getSkills().setStaticLevel(Skills.MAGIC, 99);
		getWiseOldMan().getSkills().setLevel(Skills.MAGIC, 99);
		SpellBookManager.SpellBook.MODERN.getSpell(65535).cast(getWiseOldMan(), GroundItemManager.get(PARTY_HAT.getId(), base.transform(20, 44, 0), player));
	}

	/**
	 * Method used to cast the thunder shock.
	 */
	private void castThunderShock() {
		getWiseOldMan().animate(THUNDER_ANIMATION);
		getWiseOldMan().graphics(PURPLE_GRAPHIC);
	}

	/**
	 * Method used to handle the camera.
	 * 
	 * @param x
	 *            the x offset.
	 * @param y
	 *            the y offset.
	 * @param xRot
	 *            the xRotation.
	 * @param yRot
	 *            the yRotation.
	 * @Param height the height.
	 */
	private void camera(int x, int y, int xRot, int yRot, int height, int speed) {
		Location loc = base.transform(x, y, 0);
		PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, loc.getX(), loc.getY(), height, 1, speed));
		PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, loc.getX() + xRot, loc.getY() + yRot, height, 1, speed));
	}

	/**
	 * Method used to set npcs and their locations.
	 */
	private void setNpcs() {
		for (NPC n : NPCS) {
			n = NPC.create(n.getId(), n.getLocation(), n.getDirection());
			n.setLocation(base.transform(n.getLocation().getLocalX(), n.getLocation().getLocalY(), 0));
			n.setRespawn(false);
			n.init();
			if (n.getName().equals("Banker")) {
				n.getSkills().setStaticLevel(Skills.HITPOINTS, 15);
				n.getSkills().setLevel(Skills.HITPOINTS, 15);
			}
			n.setWalks(false);
		}
	}

	/**
	 * Method used to clear all the npcs.
	 */
	private void clearNpcs() {
		GameWorld.Pulser.submit(new Pulse(5) {
			@Override
			public boolean pulse() {
				for (NPC n : region.getPlanes()[0].getNpcs()) {
					n.clear();
				}
				return true;
			}

		});
	}

	/**
	 * Gets the npc.
	 * 
	 * @param id
	 *            the id.
	 * @return the npc.
	 */
	private NPC getNpc(int id) {
		for (NPC n : region.getPlanes()[0].getNpcs()) {
			if (n.getId() == id) {
				return n;
			}
		}
		return null;
	}

	/**
	 * Represents the pulse used during recording.
	 * 
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class RecordingPulse extends Pulse {

		/**
		 * Represents the counter.
		 */
		private int counter;

		/**
		 * Constructs a new {@code FightPulse} {@code Object}.
		 */
		public RecordingPulse() {
			super(1, player);
		}

		@Override
		public boolean pulse() {
			switch (counter++) {
			case 1:
				getCoolMom().animate(STEAL_ANIMATION);
				getMarketGuard().getSkills().setStaticLevel(Skills.HITPOINTS, 99);
				getMarketGuard().getSkills().setLifepoints(99);
				getMarketGuard().sendChat("Hey! Get your hands off there!");
				getCoolMom().getLocks().lockMovement(1000);
				getMarketGuard().getProperties().getCombatPulse().attack(getCoolMom());
				break;
			case 8:
				walk(getPurePker(), base.transform(20, 45, 0));
				break;
			case 10:
				camera(27, 43, -37, -3, 600, 5);
				break;
			case 12:
				getQuiteDoll().sendChat("Thx");
				break;
			case 14:
				walk(getQuiteDoll(), base.transform(20, 42, 0));
				get1337().sendChat("Yw");
				getPurePker().faceLocation(base.getLocation().transform(19, 45, 0));
				break;
			case 16:
				getElfinocks().sendChat("Buying 2k nats - no noob offers");
				break;
			case 20:
				get1337().sendChat("I h4ve n4ts");
				break;
			case 22:
				getElfinocks().sendChat("You sell?");
				break;
			case 25:
				get1337().sendChat("Tr4de");
				get1337().faceTemporary(getElfinocks(), 5);
				walk(get1337(), base.transform(21, 43, 0));
				break;
			case 26:
				camera(23, 49, -37, 0, 500, 3);
				break;
			case 28:
				walk(getQuiteDoll(), base.transform(20, 49, 0));
				walk(getPurePker(), base.transform(16, 49, 0));
				break;
			case 29:
				walk(getOlivia(), base.transform(12, 48, 0));
				break;
			case 36:
				DoorActionHandler.handleAutowalkDoor(getWiseOldMan(), RegionManager.getObject(base.transform(16, 51, 0)));
				break;
			case 37:
				getQuiteDoll().sendChat("Huh?");
				getQuiteDoll().faceTemporary(getWiseOldMan(), 2);
				getWiseOldMan().sendChat("Please don't block my line of fire!");
				castShock(getPurePker());
				break;
			case 38:
				die(getPurePker());
				break;
			case 39:
				getOlivia().sendChat("Hey, what are you doing?");
				getWiseOldMan().faceTemporary(getOlivia(), 2);
				GroundItemManager.create(ASHES, base.transform(16, 49, 0), player);
				GroundItemManager.create(COINS, base.transform(16, 49, 0), player);
				break;
			case 42:
				getWiseOldMan().sendChat("Olivia - Please go away!");
				getWiseOldMan().animate(SECOND_CAST_ANIMATION);
				getWiseOldMan().graphics(TELE_OTHERS[0]);
				break;
			case 43:
				getOlivia().animate(TELEPORT_ANIMATION);
				getOlivia().graphics(TELE_OTHERS[1]);
				getOlivia().sendChat("Eeeek!");
				GroundItemManager.destroy(GroundItemManager.get(ASHES.getId(), base.transform(16, 49, 0), player));
				break;
			case 45:
				getOlivia().setInvisible(true);
				break;
			case 48:
				getWiseOldMan().sendChat("Ah, now I can get on with it...");
				getWiseOldMan().faceLocation(base.transform(16, 49, 0));
				break;
			case 49:
				getWiseOldMan().animate(PICK_UP_ANIMATION);
				break;
			case 50:
				GroundItemManager.destroy(GroundItemManager.get(COINS.getId(), base.transform(16, 49, 0), player));
				break;
			case 53:
				castShock(base.transform(16, 46, 0));
				break;
			case 55:
				GameObject wall = RegionManager.getObject(base.transform(16, 46, 0));
				ObjectBuilder.replace(wall, wall.transform(9151, 0, 10));
				getWiseOldMan().getWalkingQueue().reset();
				getWiseOldMan().getWalkingQueue().addPath(base.getX() + 16, base.getY() + 46);
				getWiseOldMan().getWalkingQueue().addPath(base.getX() + 17, base.getY() + 46);
				break;
			case 58:
				camera(21, 38, -36, 43, 495, 99);
				break;
			case 59:
				getWiseOldMan().sendChat("Could everyone please not move?");
				break;
			case 62:
				getFirstBanker().sendChat("Hey - you can't come in here!");
				walk(getFirstBanker(), base.transform(18, 46, 0));
				getFirstBanker().faceTemporary(getWiseOldMan(), 2);
				getWiseOldMan().faceTemporary(getFirstBanker(), 2);
				break;
			case 64:
				getWiseOldMan().animate(HIT_ANIMATION);
				getFirstBanker().animate(BLOCK_ANIMATION);
				getFirstBanker().getImpactHandler().manualHit(getWiseOldMan(), getFirstBanker().getSkills().getLifepoints(), HitsplatType.NORMAL);
				getSecondBanker().sendChat("Oi!");
				getLastBanker().sendChat("Uh-oh!");
				getSecondBanker().getWalkingQueue().reset();
				getSecondBanker().getWalkingQueue().addPath(base.getX() + 17, base.getY() + 43);
				getSecondBanker().getWalkingQueue().addPath(base.getX() + 16, base.getY() + 43);
				getSecondBanker().getWalkingQueue().addPath(base.getX() + 16, base.getY() + 46);
				break;
			case 70:
				walk(getLastBanker(), base.transform(18, 40, 0));
				getSecondBanker().faceTemporary(getWiseOldMan(), 2);
				getSecondBanker().animate(new Animation(422));
				getWiseOldMan().animate(new Animation(403));
				getWiseOldMan().faceTemporary(getSecondBanker(), 2);
				break;
			case 73:
				getWiseOldMan().animate(new Animation(401));
				getSecondBanker().animate(new Animation(435));
				getSecondBanker().getImpactHandler().manualHit(getWiseOldMan(), 11, HitsplatType.NORMAL);
				break;
			case 75:
				getSecondBanker().animate(new Animation(422));
				NPC npc = getWiseOldMan();
				if (npc != null) {
					getWiseOldMan().sendChat("I do wish you'd just stop it!");
					getWiseOldMan().animate(new Animation(403));
				}
				break;
			case 78:
				getWiseOldMan().animate(new Animation(401));
				walk(getLastBanker(), base.transform(16, 40, 0));
				walk(getElfinocks(), base.transform(20, 44, 0));
				break;
			case 80:
				getWiseOldMan().faceLocation(base.transform(17, 43, 0));
				getLastBanker().faceTemporary(getWiseOldMan(), 1);
				getSecondBanker().getImpactHandler().manualHit(getWiseOldMan(), getSecondBanker().getSkills().getLifepoints(), HitsplatType.NORMAL);
				break;
			case 85:
				getWiseOldMan().faceLocation(base.transform(17, 43, 0));
				getElfinocks().sendChat("Hey - how'd he get in there?");
				break;
			case 86:
				camera(26, 40, -35, 13, 545, 32);
				getLastBanker().sendChat("Help! Help!");
				get1337().sendChat("Hax!");
				get1337().faceLocation(base.transform(20, 43, 0));
				break;
			case 90:
				getWiseOldMan().sendChat("My sincerest regrets, dear lady...");
				break;
			case 92:
				getWiseOldMan().faceTemporary(getElfinocks(), 1);
				castShock(getElfinocks());
				break;
			case 94:
				die(getElfinocks());
				break;
			case 96:
				GroundItemManager.create(ASHES, base.transform(20, 44, 0), player);
				GroundItemManager.create(PARTY_HAT, base.transform(20, 44, 0), player);
				break;
			case 99:
				getWiseOldMan().sendChat("And also you, sir...");
				GroundItemManager.destroy(GroundItemManager.get(ASHES.getId(), base.transform(20, 44, 0), player));
				break;
			case 101:
				castShock(get1337());
				walk(getQuiteDoll(), base.transform(21, 44, 0));
				break;
			case 103:
				getQuiteDoll().faceTemporary(getWiseOldMan(), 1);
				break;
			case 104:
				getQuiteDoll().sendChat("How you do that?");
				die(get1337());
				break;
			case 107:
				GroundItemManager.create(ASHES, base.transform(21, 43, 0), player);
				break;
			case 109:
				getWiseOldMan().sendChat("Oh dear - another one...");
				walk(getWiseOldMan(), base.transform(18, 46, 0));
				getWiseOldMan().faceTemporary(getQuiteDoll(), 1);
				GroundItemManager.destroy(GroundItemManager.get(ASHES.getId(), base.transform(21, 43, 0), player));
				break;
			case 110:
				GroundItemManager.destroy(GroundItemManager.get(ASHES.getId(), base.transform(21, 43, 0), player));
				break;
			case 111:
				castShock(getQuiteDoll());
				break;
			case 114:
				die(getQuiteDoll());
				break;
			case 115:
				GroundItemManager.create(ASHES, base.transform(21, 44, 0), player);
				getWiseOldMan().sendChat("Ooh - a party hat?");
				getWiseOldMan().faceLocation(base.transform(20, 44, 0));
				break;
			case 116:
				GroundItemManager.destroy(GroundItemManager.get(ASHES.getId(), base.transform(21, 44, 0), player));
				castTelekinetic();
				break;
			case 118:
				getWiseOldMan().transform(2567);
				if (getOlivia().isActive()) {
					getOlivia().getImpactHandler().manualHit(getMarketGuard(), getOlivia().getSkills().getLifepoints(), HitsplatType.NORMAL);
				}
				getMarketGuard().setInvisible(true);
				break;
			case 120:
				getWiseOldMan().sendChat("Now, my dear...");
				walk(getWiseOldMan(), base.transform(18, 42, 0));
				break;
			case 121:
				getLastBanker().sendChat("Eeek!");
				break;
			case 124:
				getWiseOldMan().sendChat("Just give me the money, please.");
				break;
			case 126:
				getMarketGuard().setInvisible(false);
				getMarketGuard().getProperties().setTeleportLocation(base.transform(18, 46, 0));
				break;
			case 127:
				camera(25, 42, -20, 0, 400, 4);
				getMarketGuard().sendChat("Old man, you're for da cage!");
				walk(getMarketGuard(), base.transform(18, 43, 0));
				break;
			case 128:
				getWiseOldMan().faceTemporary(getMarketGuard(), 1);
				break;
			case 129:
				getWiseOldMan().sendChat("Oh, am I?");
				break;
			case 131:
				getWiseOldMan().sendChat("Ahh!");
				break;
			case 133:
				castThunderShock();
				break;
			case 136:
				getMarketGuard().graphics(THUNDER_GRAPHIC);
				getMarketGuard().getImpactHandler().manualHit(getWiseOldMan(), 40, HitsplatType.NORMAL);
				getMarketGuard().sendChat("Aaargh!");
				break;
			case 138:
				getWiseOldMan().sendChat("Yah!");
				getWiseOldMan().animate(WISE_JUMP);
				break;
			case 139:
				getMarketGuard().animate(GUARD_JUMP);
				break;
			case 142:
				getMarketGuard().clear();
				camera(24, 42, -20, 0, 400, 4);
				break;
			case 143:
				getWiseOldMan().sendChat("And YOU can stop spying on me!");
				getWiseOldMan().faceLocation(base.transform(24, 42, 0));
				break;
			case 145:
				getWiseOldMan().animate(STOMP_ANIM);
				Projectile projectile = Projectile.create(getWiseOldMan(), null, 434, 50, 130, 41, 140, 0, 0);
				projectile.setEndLocation((Location) base.transform(24, 42, 0));
				projectile.send();
				break;
			case 148:
				player.getInterfaceManager().open(CRACKED);
				break;
			case 150:
				DBRCutscenePlugin.this.stop(true);
				return true;
			}
			return !player.isActive();
		}

		@Override
		public void stop() {
			super.stop();
			clearNpcs();
		}

		/**
		 * Gets the counter.
		 * 
		 * @return the counter.
		 */
		public int getCounter() {
			return counter;
		}
	}

	/**
	 * Represents an npc during the draynor bank recording cutscene.
	 * 
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class DBRecordingNPC extends AbstractNPC {

		/**
		 * Represents the ids to use.
		 */
		private static final int[] IDS = new int[] { 2579 };

		/**
		 * Constructs a new {@code DBRecordingNPC} {@code Object}.
		 */
		public DBRecordingNPC() {
			super(0, null, false);
		}

		/**
		 * Constructs a new {@code DBRecordingNPC} {@code Object}.
		 * 
		 * @param id
		 *            the id.
		 * @param location
		 *            the locastion.
		 */
		private DBRecordingNPC(int id, Location location) {
			super(id, location);
		}

		@Override
		public AbstractNPC construct(int id, Location location, Object... objects) {
			return new DBRecordingNPC(id, location);
		}

		@Override
		public void init() {
			super.init();
			getSkills().setLevel(Skills.ATTACK, 1);
			getSkills().setStaticLevel(Skills.ATTACK, 1);
			getSkills().setLevel(Skills.DEFENCE, 2);
			getSkills().setStaticLevel(Skills.DEFENCE, 2);
		}

		@Override
		public boolean isAttackable(Entity entity, CombatStyle style) {
			return true;
		}

		@Override
		public int[] getIds() {
			return IDS;
		}
	}

	public static final class BluePhatItem extends ItemPlugin {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			register(PARTY_HAT.getId());
			return this;
		}

		@Override
		public boolean canPickUp(Player player, GroundItem item, int type) {
			if (type == 1) {
				player.sendMessage("Nice try ;)");
			}
			return false;
		}
	}
}
