package plugin.cutscene;

import org.crandor.game.component.Component;
import org.crandor.game.component.ComponentDefinition;
import org.crandor.game.component.ComponentPlugin;
import org.crandor.game.content.activity.ActivityManager;
import org.crandor.game.content.activity.ActivityPlugin;
import org.crandor.game.content.activity.CutscenePlugin;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.eco.ge.GEGuidePrice;
import org.crandor.game.content.eco.ge.GEGuidePrice.GuideType;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.CameraContext;
import org.crandor.net.packet.context.CameraContext.CameraType;
import org.crandor.net.packet.out.CameraViewPacket;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the cutscene used for the grand exchange tutorial.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class GECutscenePlugin extends CutscenePlugin {

	/**
	 * Represents the main g.e interface.
	 */
	private static final Component MAIN_INTERFACE = new Component(106);

	/**
	 * Represents the config offer interface.
	 */
	private static final Component CONFIRM_INTERFACE = new Component(108);

	/**
	 * Represents the choose item interface.
	 */
	private static final Component CHOOSE_ITEM = new Component(110);

	/**
	 * Represents the hour glass interface.
	 */
	private static final Component HOUR_GLASS = new Component(646);

	/**
	 * Constructs a new {@code GECutscenePlugin} {@code Object}.
	 */
	public GECutscenePlugin() {
		this(null);
	}

	/**
	 * Constructs a new {@code GECutscenePlugin} {@code Object}.
	 * @param player the player.
	 */
	public GECutscenePlugin(final Player player) {
		super("ge tutorial");
		this.player = player;
	}

	@Override
	public void open() {
		player.setAttribute("ge-stage", 0);
		player.setAttribute("ge-cutscene", this);
		camera(3164, 3455, 0, 0, 800, 100);
		player.lock();
		player.getDialogueInterpreter().sendDialogue(" - The Grand Exchange - ");
	}

	@Override
	public ActivityPlugin newInstance(Player p) throws Throwable {
		return new GECutscenePlugin(p);
	}

	@Override
	public void register() {
		new BrugsenBursenDialogue().init();
		try {
			new TutorialInterfacePlugin().newInstance(null);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	public Location getSpawnLocation() {
		return null;
	}

	@Override
	public void configure() {

	}

	/**
	 * Method used to handle the camera.
	 * @param x the x offset.
	 * @param y the y offset.
	 * @param xRot the xRotation.
	 * @param yRot the yRotation.
	 * @Param height the height.
	 */
	private static void camera(final Player player, int x, int y, int xRot, int yRot, int height, int speed) {
		PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, x, y, height, 1, speed));
		PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, x + xRot, y + yRot, height, 1, speed));
	}

	/**
	 * Method used to handle the camera.
	 * @param x the x pos.
	 * @param y the y pos.
	 * @param xRot the xRot.
	 * @param yRot the yRot.
	 * @param height the height.
	 * @param speed the speed.
	 */
	private final void camera(int x, int y, int xRot, int yRot, int height, int speed) {
		camera(player, x, y, xRot, yRot, height, speed);
	}

	/**
	 * Represents the brugsen bursen dialogue plugin.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class BrugsenBursenDialogue extends DialoguePlugin {

		/**
		 * Constructs a new {@code BrugsenBursenDialogue} {@code Object}.
		 */
		public BrugsenBursenDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code BrugsenBursenDialogue} {@code Object}.
		 * @param player the player.
		 */
		public BrugsenBursenDialogue(final Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new BrugsenBursenDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			if (!player.getSavedData().getGlobalData().isGeTutorial()) {
				player("What is this place?");
				stage = 0;
			} else {
				npc("It's the young entrepreneur! How can I help you?");
				stage = 112;
			}
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				npc("Well, this is the fantastic Grand Exchange!");
				stage = 1;
				break;
			case 1:
				npc("I am only too happy to help teach you everything you", "could possibly want to know. The Tutor nearby can", "give a brief introduction, too, but he's not as fun as me!");
				stage = 2;
				break;
			case 2:
				options("I want to know everything from you!", "I'd rather speak to the Tutor and get a plain idea.", "I'm not interested in either!");
				stage = 3;
				break;
			case 3:
				switch (buttonId) {
				case 1:
					player("I want to know everything from you!");
					stage = 10;
					break;
				case 2:
					player("I'd rather speak to the Tutor and get a plain idea.");
					stage = 20;
					break;
				case 3:
					player("I'm not interested in either!");
					stage = 30;
					break;
				}
				break;
			case 10:
				npc("Hahaha! Well, let's begin, my friend!");
				stage = 11;
				break;
			case 11:
				close();
				ActivityManager.start(player, "ge tutorial", false);
				stage = 100;// room for g.e cutscene
				break;
			case 20:
				npc("Fine, have it your way.");
				stage = 21;
				break;
			case 21:
				end();
				break;
			case 30:
				npc("Okay, if you ever need help don't", "hesitated to ask me.");
				stage = 31;
				break;
			case 31:
				end();
				break;
			case 100:
				close();
				camera(player, 3149, 3470, 1, 1, 870, 10);
				GameWorld.Pulser.submit(new Pulse(16, player) {
					@Override
					public boolean pulse() {
						npc("Welcome, my friend to the Grand Exchange! From", "here you can simply tell us what you want to buy or", "sell and for how much, and we'll pair you up with", "another player and make the trade!");
						stage = 101;
						return true;
					}
				});
				break;
			case 101:
				camera(player, 3172, 3465, -20, 210, 870, 20);
				npc("Let me start by telling you how to buy and sell items.", "They are both quite similar, and can be explained in", "five simple steps.");
				stage = 102;
				break;
			case 102:
				PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.RESET, 0, 0, 580, 1, 100));
				final CutscenePlugin cutscene = player.getAttribute("ge-cutscene", null);
				cutscene.stop(false);
				player.getSavedData().getGlobalData().setGeTutorial(true);
				npc("<col=8A0808>Step 1</col>: You decide what to buy or sell and come here", "with the items to sell or the money to buy with.");
				stage = 103;
				break;
			case 103:
				npc("<col=8A0808>Step 2</col>: Speak with one of the clerks, behind the desk in", "the middle of the building and you'll place an offer as", "follows...");
				stage = 104;
				break;
			case 104:
				close();
				player.getPacketDispatch().sendString("First! you all see a selection of boxes, each of which represent a possible offer you can place.", 106, 124);
				player.getInterfaceManager().open(MAIN_INTERFACE);
				for (int i = 0; i < 130; i++) {
					player.getPacketDispatch().sendInterfaceConfig(106, i, false);
				}
				int[] childs = new int[] { 33, 56, 57, 64, 98, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29 };
				for (int i : childs) {
					player.getPacketDispatch().sendInterfaceConfig(106, i, true);
				}
				stage = 105;
				break;
			case 105:
				npc("<col=8A0808>Step 3</col>: The clerks will have taken the items or money", "off you and will look for someone to complete the trade.");
				stage = 106;
				break;
			case 106:
				npc("<col=8A0808>Step 4</col>: You then need to wait perhaps a matter of", "moments or maybe days until someone is looking for", "what you have offered.");
				stage = 107;
				break;
			case 107:
				close();
				player.getInterfaceManager().open(HOUR_GLASS);
				stage = 108;
				break;
			case 108:
				npc("To see costs of commonly traded items, you can talk to", "one of the characters around the outside of the building.");
				stage = 109;
				break;
			case 109:
				npc("Taking note of past successes and failures is important, ", "so the clerks will show you your previous buy and sell", "attempts on the Grand Exchange.");
				stage = 110;
				break;
			case 110:
				npc("There's a lot to learn, but you're now free to use the", "Grand Exchange. If you speak with me further I'm", "more than happy to repeat this tutorial and give more", "information.");
				stage = 111;
				break;
			case 111:
				npc("This extra information will be crucial if you wish to", "make the best deals!");
				stage = 112;
				break;
			case 112:
				options("Can you teach me about the Grand Exchange again?", "Can you tell me more about how the system works?", "Can you tell me prices for common items, like....", "Where did the Grand Exchange come from?");
				stage = 113;
				break;
			case 113:
				switch (buttonId) {
				case 1:
					player("Can you teach me about the Grand Exchange again?");
					stage = 210;
					break;
				case 2:
					player("Can you tell me more about how the system works?");
					stage = 220;
					break;
				case 3:
					player("Can you tell me prices for common items, like....");
					stage = 230;
					break;
				case 4:
					player("Where did the Grand Exchange come from?");
					stage = 240;
					break;
				}
				break;
			case 210:
				npc("Hahaha. It would be my absolute pleasure!");
				stage = 11;
				break;
			case 220:
				npc("Oh, I simply love passing on knowledge. Okay, let me hit", "you with some facts...");
				stage = 221;
				break;
			case 221:
				npc("The Grand Exchange calculated a guide price for each", "item that can be traded through it, based on the price", "people paid for that item over the previous days.");
				stage = 222;
				break;
			case 222:
				npc("An item just a suggestion value, you can", "offer any price you like when setting up your bids.");
				stage = 223;
				break;
			case 223:
				end();
				break;
			case 230:
				options("The price of ores.", "The price of runes.", "The price of logs.", "The price of herbs.", "The price of weapons and armour.");
				stage = 231;
				break;
			case 231:
				switch (buttonId) {
				case 1:
					player("The price of ores.");
					stage = 310;
					break;
				case 2:
					player("The price of runes.");
					stage = 320;
					break;
				case 3:
					player("The price of logs.");
					stage = 330;
					break;
				case 4:
					player("The price of herbs.");
					stage = 340;
					break;
				case 5:
					player("The price of weapons and armour.");
					stage = 350;
					break;
				}
				break;
			case 310:
				npc("By all means, but you can probably get at this", "information quicker by visiting Farid M.");
				stage = 311;
				break;
			case 311:
				end();
				GEGuidePrice.open(player, GuideType.ORES);
				break;
			case 320:
				npc("My pleasure, but you can probably get this", "information quicker by visitng Murky Matt.");
				stage = 321;
				break;
			case 321:
				end();
				GEGuidePrice.open(player, GuideType.RUNES);
				break;
			case 330:
				npc("Sure thing, but you can probably get this", "information quicker by visiting Relobo.");
				stage = 331;
				break;
			case 331:
				end();
				GEGuidePrice.open(player, GuideType.LOGS);
				break;
			case 340:
				npc("Of course, but you can probably get at this", "information quicker by visiting Bob Barter.");
				stage = 341;
				break;
			case 341:
				end();
				GEGuidePrice.open(player, GuideType.HERBS);
				break;
			case 350:
				npc("That's easy, but you can probably get at this", "information quicker by visiting Hofuthand.");
				stage = 351;
				break;
			case 351:
				end();
				GEGuidePrice.open(player, GuideType.WEAPONS_AND_ARMOUR);
				break;
			case 240:
				npc("I'm glad you ask! I like telling this story. Are you sitting", "comfortably?");
				stage = 241;
				break;
			case 241:
				player("Erm, I'll stand if that's okay.");
				stage = 242;
				break;
			case 242:
				npc("Fine. *Ahem* I shall tell you a story of hard work,", "dedication and success. I grew up here in Varrock", "at my parent's general store. I got a good feel for how", "the price of items would rise and fall depending on supply");
				stage = 243;
				break;
			case 243:
				npc("and demand; I always found it interesting how", "items would go from one person to another, in this long", " chain of transactions.");
				stage = 244;
				break;
			case 244:
				player("So you lived here?");
				stage = 245;
				break;
			case 245:
				npc("Oh, yes. I'd never consider leaving. Anyway, as I became", "an adult, I got to know other shop owners around Varrock", "along with the rich traders that would exchange vast", "quantities of items in one go.");
				stage = 246;
				break;
			case 246:
				player("How do you class a big quantity?");
				stage = 247;
				break;
			case 247:
				npc("Well, the quantities are always increasing, I", "remember the day when you could buy some cooked shark", "for five coins!");
				stage = 248;
				break;
			case 248:
				player("Impossible!");
				stage = 249;
				break;
			case 249:
				npc("Nope. Straight up. Anyway. I organised a group of us to", "meet each week to see what deals could be made. It", "seemed to work so well and we developed a system that", "become so popular, that each meeting would a see a variety");
				stage = 250;
				break;
			case 250:
				npc("of new people joining. I kept improving the system that", "I had created, but soon it became too big a thing to", "manage.");
				stage = 251;
				break;
			case 251:
				player("I can imagine!");
				stage = 252;
				break;
			case 252:
				npc("So, in the end, I decided why not make this a " + GameWorld.getName() + "-", "wide phenomenon? Make it public and allow anyone to join", "in. Up to this point, it catered for people buying and selling", "large quantities, but I knew it would work on a smaller");
				stage = 253;
				break;
			case 253:
				npc("scale.");
				stage = 254;
				break;
			case 254:
				npc("And I was also in for a bit of luck. You see, one of the", "initial patrons had deep connections to the banks of", "" + GameWorld.getName() + ". Together, I think you'll agree we have a most", "friendly system.");
				stage = 255;
				break;
			case 255:
				player("I feel quite exited now! I have a strange urge to shout", "'Buy, buy, sell, sell!'");
				stage = 256;
				break;
			case 256:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 6522 };
		}

	}

	/**
	 * Represents the interface plugin for the tutorial.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class TutorialInterfacePlugin extends ComponentPlugin {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			int[] ids = new int[] { 106, 108, 110, 646 };
			for (int id : ids) {
				ComponentDefinition.forId(id).setPlugin(this);
			}
			return this;
		}

		@Override
		public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
			switch (component.getId()) {
			case 106:
				switch (button) {
				case 145:// next
					if (player.getAttribute("ge-stage", 0) == 0) {
						player.getPacketDispatch().sendInterfaceConfig(106, 131, false);
						player.getPacketDispatch().sendString("Upon clicking on one of the boxes you will see two buttons appear - one to make a buy offer and one to make a sell offer", 106, 124);
						player.setAttribute("ge-stage", 1);
					} else if (player.getAttribute("ge-stage", 0) == 4) {
						player.setAttribute("ge-stage", 5);
						player.getInterfaceManager().close();
						player.getDialogueInterpreter().sendDialogues(6522, null, "Sellig items is a very much similar process, just that", "you are picking an item you already have.");
					} else {
						player.getPacketDispatch().sendString("If you selected the buy option you would the see this screen. Here you define what you buy by clicking on the box with the magnifying glass and choosing an item.", 110, 89);
						player.setAttribute("ge-stage", 2);
						player.getInterfaceManager().open(CHOOSE_ITEM);
					}
					break;
				}
				break;
			case 108:
				player.setAttribute("ge-stage", 4);
				player.getInterfaceManager().open(MAIN_INTERFACE);
				for (int i = 0; i < 130; i++) {
					player.getPacketDispatch().sendInterfaceConfig(106, i, false);
				}
				int[] childs = new int[] { 33, 56, 57, 64, 98, 131 };
				for (int i : childs) {
					player.getPacketDispatch().sendInterfaceConfig(106, i, true);
				}
				player.getPacketDispatch().sendString("Now the offer is placed! You can click on this anytime you want to see the details of your offer. The progress is shown with a progress bar underndeath", 106, 124);
				break;
			case 110:
				switch (button) {
				case 92:
					player.setAttribute("ge-stage", 3);
					player.getInterfaceManager().open(CONFIRM_INTERFACE);
					player.getPacketDispatch().sendString("In this example we have selected a staff of air. You can then define the quantity and cost before selecting the Confirm Offer button", 108, 94);
					break;
				}
				break;
			case 646:
				switch (button) {
				case 13:
					player.getInterfaceManager().close();
					player.setAttribute("ge-stage", 5);
					player.getDialogueInterpreter().sendDialogues(6522, null, "<col=8A0808>Step 5</col>: When the trade is complete, we will let you", "know with a message and you can pick up your", "winnings by talking to the clerks or by visiting any", "banker in " + GameWorld.getName() + ".");
					break;
				}
				break;
			}
			return true;
		}

	}

}
