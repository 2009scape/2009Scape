package plugin.npc;

import java.util.ArrayList;
import java.util.List;

import org.crandor.game.component.Component;
import org.crandor.game.content.activity.ActivityPlugin;
import org.crandor.game.content.activity.CutscenePlugin;
import org.crandor.game.content.dialogue.DialogueInterpreter;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.global.BossKillCounter;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.interaction.Option;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.ChanceItem;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.build.DynamicRegion;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.CameraContext;
import org.crandor.net.packet.context.MinimapStateContext;
import org.crandor.net.packet.context.CameraContext.CameraType;
import org.crandor.net.packet.out.CameraViewPacket;
import org.crandor.net.packet.out.MinimapState;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles the bork npc.
 * @author Vexia
 */
@InitializablePlugin
public class BorkNPC extends AbstractNPC {

	/**
	 * The chats the legion can say.
	 */
	private static final String[] LEGION_CHATS = new String[] { "For bork!", "Die human!", "Resistance is futile!", "We are the collective!", "From a triangle!!", "Hup! 2... 3... 4!!" };

	/**
	 * The chance items.
	 */
	private static final ChanceItem[] DROPS = new ChanceItem[] { new ChanceItem(532, 1, 1, 0.0), new ChanceItem(12163, 5, 5, 0.0), new ChanceItem(12160, 7, 7, 0.0), new ChanceItem(12159, 2, 2, 0.0), new ChanceItem(995, 2000, 10000, 0.0), new ChanceItem(1619, 1, 0.0), new ChanceItem(1621, 1, 1, 0.0), new ChanceItem(1623, 1, 1, 0.0) };

	/**
	 * The ring drops.
	 */
	private static final ChanceItem[] RING_DROPS = new ChanceItem[] { new ChanceItem(532, 1, 1, 0.0), new ChanceItem(12163, 5, 5, 0.0), new ChanceItem(12160, 10, 10, 0.0), new ChanceItem(12159, 3, 3, 0.0), new ChanceItem(1601, 1, 1, 0.0), new ChanceItem(995, 2000, 10000, 0.0), new ChanceItem(1619, 2, 2, 0.0), new ChanceItem(1621, 3, 3, 0.0) };

	/**
	 * The list of the legion npc.
	 */
	private final List<NPC> legions = new ArrayList<>();

	/**
	 * If the legion is spawned.
	 */
	private boolean spawnedLegion;

	/**
	 * The player.
	 */
	private Player player;

	/**
	 * The bork cutscene.
	 */
	private BorkCutscene cutscene;

	/**
	 * Constructs a new {@Code BorkNPC} {@Code Object}
	 */
	public BorkNPC() {
		super(-1, null);
	}

	/**
	 * Constructs a new {@Code BorkNPC} {@Code Object}
	 * @param id the id.
	 * @param location the location.
	 */
	public BorkNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new BorkNPC(id, location);
	}

	@Override
	public void handleTickActions() {
		if (player == null) {
			return;
		}
		super.handleTickActions();
		if (!getLocks().isMovementLocked() && player != null) {
			if (!getProperties().getCombatPulse().isAttacking()) {
				getProperties().getCombatPulse().attack(player);
			}
		}
	}

	@Override
	public void finalizeDeath(Entity killer){
		super.finalizeDeath(killer);
		BossKillCounter.addtoKillcount((Player) killer, this.getId());
	}

	@Override
	public void commenceDeath(Entity killer) {
		super.commenceDeath(killer);
		for (NPC l : legions) {
			l.clear();
		}
		player.lock();
		cutscene.wizard.clear();
		cutscene.wizard.lock();
		player.getInterfaceManager().hideTabs(0, 1, 2, 3, 4, 5, 6, 11, 12);
		PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 2));
		player.getInterfaceManager().open(new Component(693));
		if (player.getDialogueInterpreter().getDialogue() != null) {
			player.getDialogueInterpreter().getDialogue().end();
		}
		GameWorld.submit(new Pulse(10, player) {

			@Override
			public boolean pulse() {
				player.unlock();
				player.getDialogueInterpreter().sendDialogues(player, FacialExpression.FURIOUS, "That monk - he called to Zamorak for revenge!");
				player.sendMessage("Something is shaking the whole cavern! You should get out of here quick!");
				PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.SHAKE, 3, 2, 2, 2, 2));
				player.getInterfaceManager().restoreTabs();
				PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
				player.getInterfaceManager().close();
				return true;
			}

		});
	}

	@Override
	public void handleDrops(Player p, Entity killer) {
		if (player.getAttribute("first-bork", false)) {
			player.removeAttribute("first-bork");
			player.getSkills().addExperience(Skills.SLAYER, 5000, true);
		} else {
			player.getSkills().addExperience(Skills.SLAYER, 1500, true);
		}
		ChanceItem[] drops = player.getEquipment().contains(2572, 1) ? RING_DROPS : DROPS;
		for (int i = 0; i < drops.length; i++) {
			Item item = new Item(drops[i].getId(), RandomFunction.random(drops[i].getMinimumAmount(), drops[i].getMaximumAmount()));
			GroundItemManager.create(item, getLocation(), player);
		}
		if (RandomFunction.random(5) == 1) {
			super.handleDrops(p, killer);
		}
	}

	@Override
	public void checkImpact(BattleState state) {
		super.checkImpact(state);
		if (!spawnedLegion && getSkills().getLifepoints() < (getSkills().getStaticLevel(Skills.HITPOINTS) / 2)) {
			spawnLegion();
		}
	}

	/**
	 * Spawns the legion.
	 */
	private void spawnLegion() {
		player.getInterfaceManager().hideTabs(0, 1, 2, 3, 4, 5, 6, 11, 12);
		PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 2));
		spawnedLegion = true;
		player.lock();
		player.getImpactHandler().setDisabledTicks(14);
		lock();
		cutscene.wizard.lock();
		getProperties().getCombatPulse().stop();
		player.getProperties().getCombatPulse().stop();
		getAnimator().forceAnimation(Animation.create(8757));
		GameWorld.submit(new Pulse(1, player, this) {

			@Override
			public boolean pulse() {
				getAnimator().forceAnimation(Animation.create(8757));
				sendChat("Come to my aid, brothers!");
				player.sendMessage("Bork strikes the ground with his axe.");
				GameWorld.submit(new Pulse(4, player) {

					@Override
					public boolean pulse() {
						player.getInterfaceManager().open(new Component(691));
						return true;
					}

				});
				GameWorld.submit(new Pulse(13, player) {

					@Override
					public boolean pulse() {
						player.getInterfaceManager().close();
						for (int i = 0; i < 3; i++) {
							NPC legion = NPC.create(7135, getLocation().transform(RandomFunction.random(1, 3), RandomFunction.random(1, 3), 0), player);
							legion.init();
							legion.graphics(Graphics.create(1314));
							legion.setAggressive(true);
							legion.setRespawn(false);
							legion.attack(player);
							legions.add(legion);
							legion.sendChat(RandomFunction.getRandomElement(LEGION_CHATS));
						}
						player.unlock();
						cutscene.wizard.unlock();
						unlock();
						if (player != null) {
							attack(player);
						}
						player.getInterfaceManager().restoreTabs();
						PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
						for (NPC n : legions) {
							n.getProperties().getCombatPulse().attack(player);
						}
						return true;
					}

				});
				return true;
			}

		});
	}

	/**
	 * Sets the player.
	 * @param player the player.
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		PluginManager.definePlugin(new BorkCutscene());
		PluginManager.definePlugin(new DagonDialogue());
		PluginManager.definePlugin(new OrkLegion());
		PluginManager.definePlugin(new DagonElite());
		return super.newInstance(arg);
	}

	@Override
	public int[] getIds() {
		return new int[] { 7133 };
	}

	/**
	 * Handles the ork legion.
	 * @author Vexia
	 */
	public class OrkLegion extends AbstractNPC {

		private Player player;

		/**
		 * The last talk.
		 */
		private int lastTalk = GameWorld.getTicks() + 30;

		/**
		 * Constructs a new {@Code OrkLegion} {@Code Object}
		 * @param id the id.
		 * @param location the location.
		 */
		public OrkLegion(int id, Location location) {
			super(id, location);
			super.setAggressive(true);
		}

		/**
		 * Constructs a new {@Code OrkLegion} {@Code Object}
		 */
		public OrkLegion() {
			super(-1, null);
		}

		@Override
		public void handleTickActions() {
			if (player == null) {
				return;
			}

			if (lastTalk < GameWorld.getTicks()) {
				sendChat(LEGION_CHATS[RandomFunction.random(LEGION_CHATS.length)]);
				lastTalk = GameWorld.getTicks() + 30;
			}
		}

		@Override
		public boolean isIgnoreMultiBoundaries(Entity victim) {
			return true;
		}

		@Override
		public boolean canAttack(Entity e) {
			return true;
		}

		@Override
		public AbstractNPC construct(int id, Location location, Object... objects) {
			OrkLegion legion = new OrkLegion(id, location);
			legion.player = (Player) objects[0];
			return legion;
		}

		@Override
		public int[] getIds() {
			return new int[] { 7135 };
		}

	}

	/**
	 * Handles the dagone lite npc.
	 * @author Vexia
	 */
	public class DagonElite extends AbstractNPC {

		private Player player;

		/**
		 * Constructs a new {@Code DagonElite} {@Code Object}
		 * @param id the id.
		 * @param location the location.
		 */
		public DagonElite(int id, Location location) {
			super(id, location);
		}

		/**
		 * Constructs a new {@Code DagonElite} {@Code Object}
		 */
		public DagonElite() {
			super(-1, null);
		}

		@Override
		public void checkImpact(BattleState state) {
			state.neutralizeHits();
		}

		@Override
		public boolean isIgnoreMultiBoundaries(Entity victim) {
			return true;
		}

		@Override
		public boolean canAttack(Entity e) {
			return true;
		}

		@Override
		public boolean isAttackable(Entity e, CombatStyle style) {
			return false;
		}

		@Override
		public void handleTickActions() {
			if (player == null) {
				return;
			}
			super.handleTickActions();
			if (!getProperties().getCombatPulse().isAttacking()) {
				getProperties().getCombatPulse().attack(player);
			}
		}

		@Override
		public AbstractNPC construct(int id, Location location, Object... objects) {
			DagonElite elite = new DagonElite(id, location);
			elite.player = (Player) objects[0];
			return elite;
		}

		@Override
		public int[] getIds() {
			return new int[] { 7137 };
		}

	}

	/**
	 * Handles the bork cutscene.
	 * @author Vexia
	 */
	public class BorkCutscene extends CutscenePlugin {

		/**
		 * The bork npc.
		 */
		private BorkNPC bork;

		/**
		 * The wizard npc.
		 */
		private NPC wizard;

		/**
		 * Constructs a new {@Code BorkCutscene} {@Code Object}
		 */
		public BorkCutscene() {
			super("Bork cutscene");
			this.setMulticombat(true);
		}

		/**
		 * Constructs a new {@Code BorkCutscene} {@Code Object}
		 * @param player the player.
		 */
		public BorkCutscene(Player player) {
			this();
			this.player = player;
		}

		@Override
		public boolean interact(Entity e, Node target, Option option) {
			if (e instanceof Player) {
				switch (target.getId()) {
				case 29537:
					e.asPlayer().graphics(Graphics.create(110));
					e.asPlayer().teleport(Location.create(3143, 5545, 0));
					return true;
				}
			}
			return super.interact(e, target, option);
		}

		@Override
		public boolean leave(Entity entity, boolean logout) {
			if (entity instanceof Player) {
				PacketRepository.send(CameraViewPacket.class, new CameraContext(entity.asPlayer(), CameraType.RESET, 3, 2, 2, 2, 2));
			}
			return super.leave(entity, logout);
		}

		@Override
		public boolean start(Player player, boolean login, Object... args) {
			player.lock();
			bork = new BorkNPC(7133, base.transform(26, 33, 0));
			bork.init();
			bork.setPlayer(player);
			bork.setRespawn(false);
			bork.lock();
			bork.cutscene = this;
			wizard = NPC.create(7137, base.transform(38, 29, 0), player);
			wizard.init();
			wizard.lock();
			wizard.faceTemporary(player, 2);
			player.getImpactHandler().setDisabledTicks(5);
			player.faceTemporary(wizard, 2);
			return super.start(player, login, args);
		}

		@Override
		public void stop(boolean fade) {
			end();
		}

		/**
		 * Commences the fight.
		 */
		public void commenceFight() {
			bork.unlock();
			wizard.unlock();
			player.unlock();
			wizard.attack(player);
			bork.attack(player);
			wizard.setRespawn(false);
			player.getInterfaceManager().restoreTabs();
			PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
		}

		@Override
		public void open() {
			super.open();
			bork.cutscene.player = player;
			player.lock();
			player.getInterfaceManager().open(new Component(692));
			GameWorld.submit(new Pulse(13, player) {

				@Override
				public boolean pulse() {
					commenceFight();
					player.getInterfaceManager().close();

					player.getDialogueInterpreter().open("dagon-dialogue", wizard, BorkCutscene.this);
					return true;
				}

			});
		}

		@Override
		public ActivityPlugin newInstance(Player p) throws Throwable {
			return new BorkCutscene(player);
		}

		@Override
		public Location getStartLocation() {
			return base.transform(36, 33, 0);
		}

		@Override
		public Location getSpawnLocation() {
			return null;
		}

		@Override
		public void configure() {
			region = DynamicRegion.create(12374);
			region.setMulticombat(true);
			setRegionBase();
			registerRegion(region.getId());
		}
	}

	/**
	 * Handles the dagon dialogue.
	 * @author Vexia
	 */
	public class DagonDialogue extends DialoguePlugin {

		/**
		 * The bork cutscene.
		 */
		private BorkCutscene cutscene;

		/**
		 * Constructs a new {@Code DagonDialogue} {@Code Object}
		 */
		public DagonDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@Code DagonDialogue} {@Code Object}
		 * @param player the player.
		 */
		public DagonDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new DagonDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			cutscene = (BorkCutscene) args[1];
			npc("Our Lord Zamorak has power over life and death,", player.getUsername() + "! He has seen fit to resurrect Bork to", "continue his great work...and now you will fall before him");
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				boolean played = player.getSavedData().getActivityData().hasKilledBork();
				player(played ? "Uh -oh! Here we go again." : "Oh boy...");
				stage++;
				break;
			case 1:
				end();
				cutscene.commenceFight();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { DialogueInterpreter.getDialogueKey("dagon-dialogue") };
		}

	}
}
