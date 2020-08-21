package plugin.quest.free.demonslayer;

import plugin.activity.ActivityManager;
import plugin.activity.ActivityPlugin;
import plugin.activity.CutscenePlugin;
import plugin.dialogue.DialoguePlugin;
import plugin.skill.Skills;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.CombatSpell;
import core.game.node.entity.combat.CombatStyle;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.SpellBookManager.SpellBook;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.build.DynamicRegion;
import core.game.world.map.path.Path;
import core.game.world.map.path.Pathfinder;
import core.game.world.map.zone.ZoneBorders;
import core.game.world.update.flag.context.Animation;
import core.net.packet.PacketRepository;
import core.net.packet.context.CameraContext;
import core.net.packet.context.MinimapStateContext;
import core.net.packet.context.CameraContext.CameraType;
import core.net.packet.out.CameraViewPacket;
import core.net.packet.out.MinimapState;

/**
 * Represents the cutscene during the combat of fighting delrith the demon.
 * @author Vexia
 * 
 */
public final class DemonSlayerCutscene extends CutscenePlugin {

	/**
	 * Represents the id of delrith.
	 */
	public static final int DELRITH = 879;

	/**
	 * Represents the id of weakend delrith.
	 */
	public static final int WEAKEND_DELRITH = 880;

	/**
	 * Represents the wizard animation.
	 */
	private static final Animation WIZARD_ANIM = new Animation(4617);

	/**
	 * Represents the stone table.
	 */
	public GameObject stoneTable;

	/**
	 * Represents the delirth npc.
	 */
	public NPC delrith;

	/**
	 * Constructs a new {@code DemonSlayerCutscene} {@code Object}.
	 */
	public DemonSlayerCutscene() {
		super("Demon Slayer Cutscene");
	}

	/**
	 * Constructs a new {@code DemonSlayerCutscene} {@code Object}.
	 * @param player the player.
	 */
	public DemonSlayerCutscene(final Player player) {
		super("Demon Slayer Cutscene");
		this.player = player;
	}

	@Override
	public ActivityPlugin newInstance(Player p) throws Throwable {
		return new DemonSlayerCutscene(p);
	}

	@Override
	public boolean start(final Player player, final boolean login, Object... args) {
		final NPC npcs[] = new NPC[] { NPC.create(4658, base.transform(29, 43, 0)), NPC.create(4659, base.transform(29, 40, 0)), NPC.create(4662, base.transform(26, 40, 0)), NPC.create(4660, base.transform(26, 43, 0)) };
		for (NPC n : npcs) {
			n.init();
			n.faceLocation(base.transform(27, 42, 0));
			this.npcs.add(n);
		}
		player.lock();
		return super.start(player, login, args);
	}

	@Override
	public void open() {
		player.setAttribute("cutscene:original-loc", Location.create(3221, 3373, 0));
		player.setAttribute("real-end", Location.create(3221, 3373, 0));
		for (NPC n : npcs) {
			n.animate(WIZARD_ANIM);
		}
		PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, player.getLocation().getX(), player.getLocation().getY(), 450, 1, 100));
		PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, player.getLocation().getX() - 15, player.getLocation().getY() + -55, 450, 1, 100));
		player.lock();
		player.getDialogueInterpreter().open(4662, npcs.get(3), this);
	}

	@Override
	public void end() {
		super.end();
		player.unlock();
	}

	@Override
	public boolean enter(final Entity entity) {
		if (!(entity instanceof Player)) {
			return true;
		}
		final Player player = ((Player) entity);
		final Quest quest = player.getQuestRepository().getQuest("Demon Slayer");
		boolean in = player.getAttribute("demon-slayer:cutscene", false);
		if (quest.getStage(player) == 30 && !in && (player.getEquipment().containsItem(DemonSlayer.SILVERLIGHT) || player.getInventory().containsItem(DemonSlayer.SILVERLIGHT))) {
			ActivityManager.start(player, "Demon Slayer Cutscene", false);
			player.setAttribute("demon-slayer:cutscene", true);
			return super.enter(entity);
		}
		return super.enter(entity);
	}

	@Override
	public void locationUpdate(Entity entity, Location last) {
		if (!(entity instanceof Player)) {
			super.locationUpdate(entity, last);
			return;
		}
		Player player = ((Player) entity);
		if (base != null && player.getAttribute("demon-slayer:cutscene", false) && player.getLocation().getDistance(base.transform(27, 42, 0)) > 8) {
			stop(true);
		}
		super.locationUpdate(entity, last);
	}

	@Override
	public boolean leave(final Entity e, final boolean logout) {
		if (!(e instanceof Player)) {
			return true;
		}
		final Player player = ((Player) e);
		if (player.getAttribute("demon-slayer:cutscene", false)) {
			if (base != null && player.getLocation().getDistance(base.transform(27, 42, 0)) > 13) {
				player.removeAttribute("demon-slayer:cutscene");
			}
			return super.leave(player, logout);
		}
		return super.leave(player, logout);
	}

	@Override
	public Location getStartLocation() {
		return base.transform(29, 47, 0);
	}

	@Override
	public Location getSpawnLocation() {
		return null;
	}

	@Override
	public void configure() {
		region = DynamicRegion.create(12852);
		setRegionBase();
		registerRegion(region.getId());
	}

	@Override
	public void register() {
		register(new ZoneBorders(3222, 3364, 3234, 3375));
		try {
			new DarkWizardNPC().newInstance(null);
			new DelrithNPC().newInstance(null);
			new DelrithDialoguePlugin().init();
			new DenathDialogue().init();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * Handles the dark wizard npc.
	 * @author 'Vexia
	 */
	public static class DarkWizardNPC extends AbstractNPC {

		/**
		 * The NPC ids of NPCs using this plugin.
		 */
		private static final int[] IDS = { 4658 };

		/**
		 * Constructs a new {@code DarkWizardNPC} {@code Object}.
		 */
		public DarkWizardNPC() {
			super(0, null);
		}

		/**
		 * Constructs a new {@code DarkWizardNPC} {@code Object}.
		 * @param id The NPC id.
		 * @param location The location.
		 */
		private DarkWizardNPC(int id, Location location) {
			super(id, location, false);
		}

		@Override
		public AbstractNPC construct(int id, Location location, Object... objects) {
			return new DarkWizardNPC(id, location);
		}

		@Override
		public void init() {
			super.init();
			this.getSkills().setLevel(Skills.MAGIC, 11);
			this.setRespawn(false);
		}

		@Override
		public void tick() {
			super.tick();
			getProperties().setAutocastSpell((CombatSpell) SpellBook.MODERN.getSpell(4));
			getProperties().getCombatPulse().setStyle(CombatStyle.MAGIC);
		}

		@Override
		public int[] getIds() {
			return IDS;
		}

	}

	/**
	 * Handles the delrith NPC.
	 * @author 'Vexia
	 */
	public class DelrithNPC extends AbstractNPC {// 65, 64

		/**
		 * The NPC ids of NPCs using this plugin.
		 */
		private final int[] IDS = { WEAKEND_DELRITH, DELRITH };

		/**
		 * Constructs a new {@code DarkWizardNPC} {@code Object}.
		 */
		public DelrithNPC() {
			super(0, null);
		}

		/**
		 * Constructs a new {@code DarkWizardNPC} {@code Object}.
		 * @param id The NPC id.
		 * @param location The location.
		 */
		private DelrithNPC(int id, Location location) {
			super(id, location, false);
		}

		@Override
		public AbstractNPC construct(int id, Location location, Object... objects) {
			return new DelrithNPC(id, location);
		}

		@Override
		public void init() {
			super.init();
		}

		@Override
		public void finalizeDeath(final Entity killer) {
			if (getId() == DELRITH) {
				setAttribute("disable:drop", true);
				super.finalizeDeath(killer);
				super.setRespawnTick(-1);
				super.getWalkingQueue().reset();
				super.getLocks().lockMovement(10);
				transform(WEAKEND_DELRITH);
				getProperties().setTeleportLocation(null);
				lock();
			} else {
				super.finalizeDeath(killer);
			}
		}

		@Override
		public void tick() {
			super.tick();
		}

		@Override
		public int[] getIds() {
			return IDS;
		}

		@Override
		public int getWalkRadius() {
			return 3;
		}
	}

	/**
	 * Represents the dialogue for delrith.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class DelrithDialoguePlugin extends DialoguePlugin {

		/**
		 * Represents the word array.
		 */
		private static final String[] WORDS = new String[] { "Carlem", "Aber", "Camerinthum", "Purchai", "Gabindo" };

		/**
		 * Represents the incantationc ounter.
		 */
		private int counter = 0;

		/**
		 * Represents the cutscene.
		 */
		private DemonSlayerCutscene cutscene;

		/**
		 * Represents the string builder.
		 */
		private StringBuilder incantation = new StringBuilder();

		/**
		 * Constructs a new {@code DelrithDialoguePlugin} {@code Object}.
		 */
		public DelrithDialoguePlugin() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code DelrithDialoguePlugin} {@code Object}.
		 * @param player the player.
		 */
		public DelrithDialoguePlugin(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new DelrithDialoguePlugin(player);
		}

		@Override
		public boolean open(Object... args) {
			cutscene = player.getAttribute("cutscene", null);
			player("Now what was that incantation again?");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				interpreter.sendOptions("Select a word", WORDS);
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
				default:
					String text = ((String) WORDS[buttonId - 1]) + (counter != 4 ? "..." : "!");
					if (text.contains("!")) {
						incantation.append(text.replace("!", ""));
					} else {
						incantation.append(text + " ");
					}
					player.sendChat(text);
					player(text);
					if (counter == 4) {
						if (incantation.toString().equals(DemonSlayer.getIncantation(player).trim())) {
							stage = 4;
						} else {
							stage = 2;
						}
					} else {
						stage = 0;
					}
					break;
				}
				counter++;
				break;
			case 2:
				cutscene.delrith.reTransform();
				cutscene.delrith.animate(new Animation(4620));
				cutscene.delrith.unlock();
				interpreter.sendDialogue("The vortex collapses. That was the wrong incantation.");
				stage = 3;
				break;
			case 3:
				end();
				break;
			case 4:
				interpreter.sendPlainMessage(true, "Delrith is sucked into the vortex...");
				cutscene.delrith.animate(new Animation(4624));
				player.lock();
				GameWorld.Pulser.submit(new Pulse(10) {
					@Override
					public boolean pulse() {
						cutscene.delrith.clear();
						interpreter.sendDialogue("...back into the dark dimension from which he came.");
						player.unlock();
						cutscene.end();
						cutscene.delrith.clear();
						player.getConfigManager().set(222, 5653570, true);
						player.getQuestRepository().getQuest("Demon Slayer").finish(player);
						end();
						return true;
					}

				});
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 8427322 };
		}
	}

	/**
	 * Represents the dialogue which handles the denath NPC.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class DenathDialogue extends DialoguePlugin {

		/**
		 * Represents the animation to use.
		 */
		private static final Animation ANIMATION = new Animation(4623);

		/**
		 * Represents the cutscene plugin.
		 */
		public DemonSlayerCutscene cutscene;

		/**
		 * Constructs a new {@code DenathDialogue} {@code Object}.
		 */
		public DenathDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code DenathDialogue} {@code Object}.
		 * @param player the player.
		 */
		public DenathDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new DenathDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = ((NPC) args[0]);
			cutscene = ((DemonSlayerCutscene) args[1]);
			npc("Arise, O mighty Delrith! Bring destruction to this soft,", "weak city!");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				interpreter.sendDialogues(cutscene.getNPCS().get(0), null, "Arise, Delrith!");
				for (NPC n : cutscene.getNPCS()) {
					n.sendChat("Arise, Delrith!");
				}
				stage = 1;
				break;
			case 1:
				player.getProperties().setTeleportLocation(cutscene.getBase().transform(28, 35, 0));
				cutscene.stoneTable = new GameObject(17437, cutscene.getBase().transform(27, 41, 0));
				ObjectBuilder.add(cutscene.stoneTable);
				PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, player.getLocation().getX() - 1, player.getLocation().getY() + 2, 380, 1, 98));
				PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, player.getLocation().getX() + 19, player.getLocation().getY() - 55, 380, 1, 98));
				interpreter.sendPlainMessage(true, "The wizards cast an evil spell...");
				GameWorld.Pulser.submit(new Pulse(1, player) {
					int counter = 0;

					@SuppressWarnings("deprecation")
					@Override
					public boolean pulse() {
						switch (counter++) {
						case 5:
							cutscene.delrith = NPC.create(DemonSlayerCutscene.DELRITH, cutscene.getBase().transform(27, 40, 0));
							cutscene.delrith.init();
							cutscene.delrith.animate(ANIMATION);
							player.getProperties().setTeleportLocation(cutscene.getBase().transform(26, 48, 0));
							player.setLocation(cutscene.getBase().transform(26, 48, 0));
							PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, player.getLocation().getX() - 1, player.getLocation().getY() - 3, 390, 1, 100));
							PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, player.getLocation().getX() + 30, player.getLocation().getY() - 55, 390, 1, 100));
							player.getConfigManager().set(222, 14194946, true);
							ObjectBuilder.replace(cutscene.stoneTable, cutscene.stoneTable.transform(17438));
							npc("Ha ha ha! At last you are free, my demonic brother!", "Rest now, and then have your revenge on this pitiful", "city!");
							stage = 2;
							return true;
						}
						return false;
					}
				});
				break;
			case 2:
				interpreter.sendDialogues(cutscene.getNPCS().get(0), null, "Who's that?");
				for (NPC n : cutscene.getNPCS()) {
					n.animate(new Animation(-1));
					n.face(player);
				}
				stage = 3;
				break;
			case 3:
				npc = cutscene.getNPCS().get(2);
				npc("Noo! Not Silverlight! Delrith is not ready yet!");
				stage = 4;
				break;
			case 4:
				npc("I've got to get out of here...");
				npc.faceLocation(null);
				npc.face(null);
				Path path = Pathfinder.find(npc, cutscene.getBase().transform(18, 40, 0));
				path.walk(npc);
				stage = 5;
				break;
			case 5:
				player.getProperties().setTeleportLocation(cutscene.getBase().transform(24, 42, 0));
				npc.clear();
				cutscene.getNPCS().remove(npc);
				end();
				cutscene.delrith.moveStep();
				cutscene.delrith.setWalks(true);
				player.unlock();
				player.getInterfaceManager().restoreTabs();
				player.getInterfaceManager().close();
				PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
				PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.RESET, player.getLocation().getX(), player.getLocation().getY(), 390, 1, 100));
				for (NPC n : cutscene.getNPCS()) {
					n.clear();
				}
				cutscene.getNPCS().clear();
				final Location base = cutscene.getBase();
				cutscene.getNPCS().add(NPC.create(4658, base.transform(29, 40, 0)));
				cutscene.getNPCS().add(NPC.create(4658, base.transform(26, 40, 0)));
				cutscene.getNPCS().add(NPC.create(4658, base.transform(26, 43, 0)));
				for (NPC n : cutscene.getNPCS()) {
					n.init();
					n.setWalks(true);
					n.unlock();
				}
				PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.RESET, 0, 0, 0, 0, 0));
				cutscene.getNPCS().get(0).getProperties().getCombatPulse().attack(player);
				player.setAttribute("cutscene", cutscene);
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 4662 };
		}
	}
}
