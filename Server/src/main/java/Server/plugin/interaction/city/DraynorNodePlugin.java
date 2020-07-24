package plugin.interaction.city;

import core.cache.def.impl.NPCDefinition;
import core.cache.def.impl.ObjectDefinition;
import core.game.component.Component;
import core.game.node.object.ObjectBuilder;
import plugin.activity.ActivityManager;
import plugin.activity.ActivityPlugin;
import plugin.activity.CutscenePlugin;
import plugin.dialogue.DialoguePlugin;
import core.game.content.global.action.ClimbActionHandler;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.net.packet.PacketRepository;
import core.net.packet.context.CameraContext;
import core.net.packet.context.CameraContext.CameraType;
import core.net.packet.out.CameraViewPacket;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the node plugin used to handle draynor interactions.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class DraynorNodePlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(922).getConfigurations().put("option:make-dyes", this);
		ObjectDefinition.forId(7092).getConfigurations().put("option:observe", this);
		ObjectDefinition.forId(6434).getConfigurations().put("option:open", this);
		ActivityManager.register(new TelescopeCutscene());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final int id = node instanceof NPC ? ((NPC) node).getId() : ((GameObject) node).getId();
		switch (id) {
		case 922:
			player.getDialogueInterpreter().open(((NPC) node).getId(), ((NPC) node), true);
			break;
		case 7092:
			ActivityManager.start(player, "draynor telescope", false);
			break;
		case 6434: // Trapdoors above NW and SE corners of Draynor sewer
			if (option.equalsIgnoreCase("open")) {
				ObjectBuilder.replace(node.asObject(), node.asObject().transform(6435), 500);
			}
			break;
		}
		return true;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		if (n instanceof GameObject) {
			final GameObject object = (GameObject) n;
			if (object.getId() == 7092) {
				return n.getLocation().transform(0, 1, 0);
			}
		}
		return null;
	}

	/**
	 * Represents the telescope cutscene.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class TelescopeCutscene extends CutscenePlugin {

		/**
		 * Represents the telescope interface.
		 */
		private static final Component INTERFACE = new Component(386);

		/**
		 * Represents the animation used to look into a telescope.
		 */
		private static final Animation TELESCOPE_ANIM = new Animation(2171);

		/**
		 * Constructs a new {@code TelescopeCutscene} {@code Object}.
		 */
		public TelescopeCutscene() {
			super("draynor telescope");
		}

		/**
		 * Constructs a new {@code TelescopeCutscene} {@code Object}.
		 * @param player the player.
		 */
		public TelescopeCutscene(Player player) {
			super("draynor telescope");
			this.player = player;
		}

		@Override
		public ActivityPlugin newInstance(Player p) throws Throwable {
			return new TelescopeCutscene(p);
		}

		@Override
		public boolean start(final Player player, boolean login, Object... args) {
			player.animate(TELESCOPE_ANIM);
			player.getDialogueInterpreter().sendPlainMessage(true, "You look through the telescope...");
			return super.start(player, login, args);
		}

		@Override
		public void open() {
			player.setAttribute("cutscene:original-loc", Location.create(3088, 3254, 0));
			player.setDirection(Direction.NORTH);
			player.faceLocation(player.getLocation().transform(0, 1, 0));
			int x = 3104;
			int y = 3175;
			int height = 900;
			PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, x, y, height, 1, 100));
			PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, x + 1, y - 30, height, 1, 100));
			x = 3111;
			y = 3172;
			height = 700;
			PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, x, y, height, 1, 2));
			PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, x - 1, y + 230, height, 1, 1));
			player.getInterfaceManager().open(INTERFACE);
			GameWorld.Pulser.submit(new Pulse(22, player) {
				@Override
				public boolean pulse() {
					TelescopeCutscene.this.stop(true);
					return true;
				}
			});
		}

		@Override
		public void register() {
			super.register();
			new EndDialogue().init();
		}

		@Override
		public void end() {
			player.getInterfaceManager().close();
			player.getDialogueInterpreter().open(32389023);
			super.end();

		}

		@Override
		public Location getSpawnLocation() {
			return null;
		}

		@Override
		public Location getStartLocation() {
			return new Location(3104, 3175, 0);
		}

		@Override
		public void configure() {

		}

		/**
		 * Represents the ending dialogue.
		 * @author 'Vexia
		 * @version 1.0
		 */
		public static final class EndDialogue extends DialoguePlugin {

			/**
			 * Constructs a new {@code EndDialogue} {@code Object}.
			 * @param player the player.
			 */
			public EndDialogue(final Player player) {
				super(player);
			}

			/**
			 * Constructs a new {@code EndDialogue} {@code Object}.
			 */
			public EndDialogue() {
				/**
				 * empty.
				 */
			}

			@Override
			public DialoguePlugin newInstance(Player player) {
				return new EndDialogue(player);
			}

			@Override
			public boolean open(Object... args) {
				player("I see you've got your telescope", "pointing at the Wizard's Tower.");
				return true;
			}

			@Override
			public boolean handle(int interfaceId, int buttonId) {
				switch (stage) {
				case 0:
					interpreter.sendDialogues(3820, null, "Oh, do I? Well, why does that interest you?");
					stage = 1;
					break;
				case 1:
					player("Well, you robbed a bank, and I bet you're now", "planning something to do with that Tower!");
					stage = 2;
					break;
				case 2:
					interpreter.sendDialogues(3820, null, "No, no, I'm not planning anything like that again.");
					stage = 3;
					break;
				case 3:
					player("Well I'll be watching you...");
					stage = 4;
					break;
				case 4:
					end();
					break;
				}
				return true;
			}

			@Override
			public int[] getIds() {
				return new int[] { 32389023 };
			}

		}

	}

}
