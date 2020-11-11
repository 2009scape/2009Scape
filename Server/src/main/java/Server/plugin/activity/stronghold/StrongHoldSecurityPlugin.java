package plugin.activity.stronghold;

import core.cache.def.impl.ObjectDefinition;
import core.game.component.Component;
import core.game.component.ComponentPlugin;
import plugin.dialogue.DialogueInterpreter;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.content.global.action.ClimbActionHandler;
import core.game.content.global.action.DoorActionHandler;
import core.game.interaction.Option;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.zone.MapZone;
import core.game.world.map.zone.ZoneBorders;
import core.game.world.map.zone.ZoneBuilder;
import core.game.world.update.flag.context.Animation;
import core.plugin.Plugin;
import core.plugin.PluginManager;
import core.plugin.InitializablePlugin;
import core.tools.RandomFunction;

/**
 * Handles the strong hold of security.
 * @author Vexia
 */
@InitializablePlugin
public final class StrongHoldSecurityPlugin extends MapZone implements Plugin<Object> {

	/**
	 * The portal data to use.
	 */
	private static final Object[][] PORTALS = new Object[][] { { 16150, Location.create(1914, 5222, 0) }, { 16082, Location.create(2021, 5223, 0) }, { 16116, Location.create(2146, 5287, 0) }, { 16050, Location.create(2341, 5219, 0) } };

	/**
	 * Constructs a new {@code StrongHoldSecurityPlugin} {@code Object}.
	 */
	public StrongHoldSecurityPlugin() {
		super("strong hold of security", true);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		PluginManager.definePlugin(new ExplorerDialogue());
		PluginManager.definePlugin(new StrongholdDialogue());
		PluginManager.definePlugin(new GrainOfPlentyDialogue());
		PluginManager.definePlugin(new GiftOfPeaceDialogue());
		PluginManager.definePlugin(new CradleOfLifeDialogue());
		PluginManager.definePlugin(new BoxOfHealthDialogue());
		PluginManager.definePlugin(new OptionHandler() {
			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				ObjectDefinition.forId(16154).getHandlers().put("option:climb-down", this);
				return this;
			}

			@Override
			public boolean handle(Player player, Node node, String option) {
				flagDoor(player, false);
				ClimbActionHandler.climb(player, new Animation(828), Location.create(1859, 5243, 0));
				player.getDialogueInterpreter().open(70099, "You squeeze through the hole and find a ladder a few feet down", "leading into the Stronghold of Security.");
				return true;
			}
		});
		return this;
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		if (e instanceof Player) {
			final Player player = (Player) e;
			switch (target.getId()) {
			case 16148:// ladder
				ladder(player, Location.create(3081, 3421, 0));
				player.getPacketDispatch().sendMessage("You climb up the ladder to the surface.");
				return true;
			case 16146:// ladder.
				ladder(player, new Location(1859, 5243, 0));
				player.getPacketDispatch().sendMessage("You climb the ladder which seems to twist and wind in all directions.");
				return true;
			case 16078:// ladder
				ladder(player, new Location(2042, 5245, 0));
				player.getPacketDispatch().sendMessage("You climb the ladder which seems to twist and wind in all directions.");
				return true;
			case 16080:// lader
				ladder(player, Location.create(1859, 5243, 0));
				return true;
			case 16048:// bone chain.
				ladder(player, Location.create(3081, 3421, 0));
				player.getPacketDispatch().sendMessage("You climb the ladder which seems to twist and wind in all directions.");
				return true;
			case 16049:// boney ladder to get to third floor.
				ladder(player, Location.create(2123, 5252, 0));
				return true;
			case 16112:// third vine.
				ladder(player, Location.create(2123, 5252, 0));
				player.getPacketDispatch().sendMessage("You climb the ladder which seems to twist and wind in all directions.");
				return true;
			case 16114:// third ladder.
				ladder(player, Location.create(2042, 5245, 0));
				player.getPacketDispatch().sendMessage("You climb up the ladder to the level above.");
				return true;
			case 16081:// second ladder going down.
				openComponent(player, Location.create(2123, 5252, 0));
				return true;
			case 16115:// third ladder going down.
				openComponent(player, Location.create(2358, 5215, 0));
				return true;
			case 16149:// fourth ladder going down.
				player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 0, 4);
				openComponent(player, Location.create(2042, 5245, 0));
				return true;
			case 16135:// claim gift of piece.
			case 16118:// claim box of health.
				if (player.getSavedData().getGlobalData().hasStrongholdReward(target.getId() == 16135 ? 1 : 3)) {
					player.getDialogueInterpreter().open(70099, "You have already claimed your reward from this level.");
				} else {
					player.getDialogueInterpreter().open(target.getId() == 16118 ? 96878 : 54678);
				}
				return true;
			case 16077:// grain of plenty
				if (player.getSavedData().getGlobalData().hasStrongholdReward(2)) {
					player.getDialogueInterpreter().open(70099, "You have already claimed your reward from this level.");
				} else {
					player.getDialogueInterpreter().open(56875);
				}
				return true;
			case 16047:// cradle
				player.getDialogueInterpreter().open(96873);
				return true;
			case 16152:// skeleton
				player.getDialogueInterpreter().open(16152);
				return true;
			case 16150:
			case 16082:
			case 16116:
			case 16050:
				handlePortal(player, (GameObject) target);
				return true;
			case 16123:
			case 16124:
			case 16126:
			case 16127:
			case 16065:
			case 16066:
			case 16067:
			case 16068:
			case 16089:
			case 16090:
			case 16092:
			case 16043:
			case 16044:
			case 16045:
			case 16046:
				handleDoor(player, (GameObject) target);
				return true;
			}
		}
		return super.interact(e, target, option);
	}

	/**
	 * Handles a ladder in strong hold.
	 * @param player the player.
	 * @param destination the destination.
	 */
	private void ladder(final Player player, final Location destination) {
		ClimbActionHandler.climb(player, new Animation(828), destination);
	}

	/**
	 * Handles a portal.
	 * @param player the player.
	 * @param objet the object.
	 */
	private void handlePortal(final Player player, final GameObject object) {
		final int index = getPortalIndex(object.getId());
		if (!player.getSavedData().getGlobalData().hasStrongholdReward(index + 1)) {
			player.getPacketDispatch().sendMessage("You are not of sufficient experience to take the shortcut through this level.");
		} else {
			player.getProperties().setTeleportLocation((Location) PORTALS[index][1]);
			player.getPacketDispatch().sendMessage("You enter the portal to be whisked through to the treasure room.");
		}
	}

	/**
	 * Handles a stronghold of security door.
	 * @param player the player.
	 * @param object the object.
	 */
	private static void handleDoor(final Player player, final GameObject object) {
		final boolean force = isForced(player, object);
		if (force || RandomFunction.random(40) < 2) {
			openDoor(player, object);
			return;
		}
		player.getDialogueInterpreter().open("strong-hold", object);
	}

	/**
	 * Opens the door.
	 * @param player the player.
	 * @param object the object.
	 */
	private static void openDoor(final Player player, final GameObject object) {
		player.lock(3);
		player.animate(Animation.create(4282));
		GameWorld.getPulser().submit(new Pulse(1, player) {
			int counter;

			@Override
			public boolean pulse() {
				switch (++counter) {
				case 1:
					final java.awt.Point p = DoorActionHandler.getRotationPoint(object.getRotation());
					Location destination = !player.getLocation().equals(object.getLocation()) ? object.getLocation() : object.getLocation().transform((int) p.getX(), (int) p.getY(), 0);
					player.getProperties().setTeleportLocation(destination);
					break;
				case 2:
					player.animate(new Animation(4283));
					flagDoor(player, !isFlagged(player));
					return true;
				}
				return false;
			}
		});
	}

	/**
	 * Opens the component.
	 * @param player the player.
	 * @param location the location.
	 */
	private void openComponent(Player player, Location location) {
		final Component component = new Component(579);
		component.setPlugin(new StrongholdComponentPlugin(location));
		player.getInterfaceManager().open(component);
	}

	/**
	 * Flags a door as set or not.
	 * @param player the player.
	 * @param completed if completed.
	 */
	private static void flagDoor(Player player, boolean completed) {
		player.setAttribute("/save:strong-hold:door", completed);
	}

	/**
	 * Checks if the door is flagged.
	 * @param player the player.
	 * @return {@code True} if flagged.
	 */
	private static boolean isFlagged(final Player player) {
		return player.getAttribute("strong-hold:door", false);
	}

	/**
	 * Checks if a door is forced to let you through.
	 * @param player the player.
	 * @param object the object.
	 * @return {@code True} if its forced.
	 */
	private static boolean isForced(final Player player, final GameObject object) {
		if (player.inCombat() || player.getProperties().getCombatPulse().isAttacking()) {
			return true;
		}
		return isFlagged(player);
	}

	/**
	 * Gets the portal index.
	 * @param id the id.
	 * @return the portal index.
	 */
	private int getPortalIndex(int id) {
		for (int i = 0; i < PORTALS.length; i++) {
			if ((int) PORTALS[i][0] == id) {
				return i;
			}
		}
		return 0;
	}

	@Override
	public void configure() {
		register(new ZoneBorders(1836, 5174, 1930, 5257));// first level.
		register(new ZoneBorders(1977, 5176, 2066, 5265));// second level.
		register(new ZoneBorders(2090, 5246, 2197, 5336));// third level.
		register(new ZoneBorders(2297, 5166, 2391, 5261));// fourth level.
	}

	/**
	 * Handles the strong hold dialogues.
	 * @author Vexia
	 */
	public static final class StrongholdDialogue extends DialoguePlugin {

		/**
		 * The door being interacted with.
		 */
		private GameObject door;

		/**
		 * The npc id.
		 */
		private int npcId;

		/**
		 * Constructs a new {@code StrongholdDialogue} {@code Object}.
		 * @param player the player.
		 */
		public StrongholdDialogue(final Player player) {
			super(player);
		}

		/**
		 * Constructs a new {@code StrongholdDialogue} {@code Object}.
		 */
		public StrongholdDialogue() {
			/**
			 * empty.
			 */
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new StrongholdDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			door = (GameObject) args[0];
			npcId = getNpcId(door.getName());
			if (player.getLocation().getX() == 1859 && player.getLocation().getY() == 5239 || player.getLocation().getX() == 1858 && player.getLocation().getY() == 5239) {
				interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Greetings Adventurer. This place is kept safe by the", "spirits within the doors. As you pass through you will be", "asked questions about security. Hopefully you will learn", "much from us.");
				stage = 900;
				return true;
			}
			final int rand = RandomFunction.random(0, 18);
			switch (rand) {
			case 0:
				interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "To pass you must answer me this: What do I do if a", "moderator asks me for my account details?");
				stage = 100;
				break;
			case 1:
				interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "To pass you must answer me this: My friend uses this", "great add-on program he got from a website, should I?");
				stage = 1000;
				break;
			case 2:
				interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "To pass you must answer me this: Who is it ok to", "share my account with?");
				stage = 200;
				break;
			case 3:
				interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "To pass you must answer me this: Why do I need to", "type in recovery questions?");
				stage = 300;
				break;
			case 4:
				interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "To pass you must answer me this: Who is it ok to", "share my account with?");
				stage = 400;
				break;
			case 5:
				interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "To pass you must answer me this: Who can I give my", "password to?");
				stage = 500;
				break;
			case 6:
				interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "To pass you must answer me this: How will " + GameWorld.getSettings().getName() + "", "contact me if I have been chosen to be a moderator?");
				stage = 600;
				break;
			case 7:
				interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "To pass you must answer me this: How often should", "you change your recovery questions?");
				stage = 700;
				break;
			case 8:
				interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "To pass you must answer me this: A website says I", "can become a player moderator by giving", "them my password what do I do?");
				stage = 800;
				break;
			case 9:
				interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "To pass you must answer me this: Will " + GameWorld.getSettings().getName() + " block me", "from saying my PIN in game?");
				stage = 1900;
				break;
			case 10:
				interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "To pass you must answer me this: Can I leave my", "account logged in while I'm out of the room?");
				stage = 1100;
				break;
			case 11:
				interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "To pass you must answer me this: Where should I", "enter my " + GameWorld.getSettings().getName() + " Password?");
				stage = 1111;
				break;
			case 12:
				interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "To pass you must answer me this: What is an", "example of a good bank PIN?");
				stage = 1200;
				break;
			case 13:
				interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "To pass you must answer me this: What do I do if I", "think I have a keylogger or virus?");
				stage = 1300;
				break;
			case 14:
				interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "To pass you must answer me this: Recovery answers", "should be...");
				stage = 1400;
				break;
			case 15:
				interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "To pass you must answer me this: What do you do", "if someone tells you that you have won the " + GameWorld.getSettings().getName() + "", "Lottery and asks for your password or recoveries?");
				stage = 1500;
				break;
			case 16:
				interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "To pass you must answer me this: What should I", "do if I think someone knows my recoveries?");
				stage = 1600;
				break;
			case 17:
				interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "To pass you must answer me this: What do you do", "if someone asks you for your password or recoveries", "to make you a player moderator?");
				stage = 1700;
				break;
			case 18:
				interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "To pass you must answer me this: Where can i", "find cheats for " + GameWorld.getSettings().getName() + "?");
				stage = 1800;
				break;
			}
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 900:
				interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Please pass through and begin your adventure, beware", "of the various monsters that dwell within.");
				stage = 901;
				break;
			case 901:
				openDoor(player, door);
				interpreter.sendDialogues(player, FacialExpression.OLD_NORMAL, "Oh my! I just got sucked through that door... what a", "weird feeling! Still, I guess I should expect it as these", "evidently aren't your average kind of doors.... they talk", "and look creepy!");
				stage = 902;
				break;
			case 902:
				end();
				break;
			case 100:
				interpreter.sendOptions("Select an Option", "Tell them whatever they want to know.", "Politely tell them no.", "Politely tell them no and then use the 'Report Abuse' button.");
				stage = 101;
				break;
			case 101:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! Never give your account details to anyone!", "This includes things like recovery answers, contact", "details and passwords. Never use personal details for", "recoveries or bank PINs!");
					stage = 99;
					break;
				case 2:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Ok! Don't tell them the details. But reporting the", "incident to " + GameWorld.getSettings().getName() + " would help. Use the Report Abuse", "button. Never use personal details for recoveries or", "bank PINs!");
					stage = 69;
					break;
				case 3:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Correct! Report any attempt to gain your account", "details as it is a very serious breach of " + GameWorld.getSettings().getName() + "'s", "rules. Never use personal details for recoveries or bank", "PINs!");
					stage = 69;
					break;
				}
				break;
			case 99:
				end();
				break;
			case 1000:
				interpreter.sendOptions("Select an Option", "No, it might steal my password.", "I'll gave it a try and see if I like it.", "Sure, he's used it alot, so can I.");
				stage = 10001;
				break;
			case 10001:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Correct! The only safe add-on for " + GameWorld.getSettings().getName() + " is the Window", "client available from our " + GameWorld.getSettings().getName() + " Website.");
					stage = 69;
					break;
				case 2:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! Some programs steal your password without", "you even knowing, this only requires running the", "program once, even if you don't use it.");
					stage = 99;
					break;
				case 3:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! The program may steal your password and is", "against the rules to use.");
					stage = 99;
					break;
				}
				break;
			case 200:

				interpreter.sendOptions("Select an Option", "My friends.", "Relatives.", "Nobody.");
				stage = 201;
				break;
			case 201:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! You account may only be used by you.");
					stage = 99;
					break;
				case 2:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! Your account may only be used by you.");
					stage = 99;
					break;
				case 3:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Correct! You, can you only may use your account.");
					stage = 69;
					break;
				}
				break;
			case 300:
				interpreter.sendOptions("Select an Option", "To help me recover my password if I forget it or it is stolen.", "To let " + GameWorld.getSettings().getName() + " know more about its players.", "To see if I can type in random letters on my keyboard.");
				stage = 301;
				break;
			case 301:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Correct! Your recovery questions will help " + GameWorld.getSettings().getName() + " staff protect", "and return your account if it is stolen. Never use personal", "details for recoveries or bank PINs!");
					stage = 69;
					break;
				case 2:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! " + GameWorld.getSettings().getName() + " values players opinons, but we use polls", "and forums to see what you think. The recoveries are not there to gain personal", "information about anybody but to protect your account.", "Never use personal details");
					stage = 99;
					break;
				case 3:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! Typing random letters into your recoveries", "won't help you or the " + GameWorld.getSettings().getName() + " staff - you'll never", "remember them anyway! Never use personal details for", "recoveries or bank PINs!");
					stage = 99;
					break;
				}
				break;
			case 400:
				interpreter.sendOptions("Select an Option", "My friends", "Relatives", "Nobody");
				stage = 401;
				break;
			case 401:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! You account may only be used by you.");
					stage = 99;
					break;
				case 2:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! You account may only be used by you.");
					stage = 99;
					break;
				case 3:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Correct! You, and you only may use your account.");
					stage = 69;
					break;
				}
				break;
			case 500:
				interpreter.sendOptions("Select an Option", "My friends", "My brother", "Nobody");
				stage = 501;
				break;
			case 501:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! Your password should be kept secret from", "everyone. You should *never* give it out under any", "circumstances.");
					stage = 99;
					break;
				case 2:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! Your password should be kept secret from", "everyone. You should *never* give it out under any", "circumstances.");
					stage = 99;
					break;
				case 3:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Correct! Your password should be kept secret from", "everyone. You should *never* give it out under any", "circumstances.");
					stage = 69;
					break;
				}
				break;
			case 600:
				interpreter.sendOptions("Select an Option", "Email.", "Website popup.", "Game Inbox on the " + GameWorld.getSettings().getName() + " Website.");
				stage = 601;
				break;
			case 601:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! " + GameWorld.getSettings().getName() + " never uses email to contact you, this is a", "scam and a fake, do not reply to it and delete it", "straight away. " + GameWorld.getSettings().getName() + " will only contact you through your", "Game Inbox avaibale on our website.");
					stage = 99;
					break;
				case 2:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! " + GameWorld.getSettings().getName() + " would never use such an insecure", "method to pick you. We will contact you through your", "Game Inbox available on our website.");
					stage = 99;
					break;
				case 3:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Correct! We only contact our players via the game", "Inbox which you can access from our " + GameWorld.getSettings().getName() + "", "website.");
					stage = 69;
					break;
				}
				break;
			case 700:
				interpreter.sendOptions("Select an Option", "Never", "Every couple of months", "Every day");
				stage = 701;
				break;
			case 701:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Never changing your recovery questions will lead to", "an insecure account due to keyloggers or friends knowing", "enough about you to guess them. Don't use personal details for your recoveries.");
					stage = 99;
					break;
				case 2:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Correct! it is the ideal option to change your questions", "but make sure you can remember the answers!", "Don't use personal details for your recoveries.");
					stage = 69;
					break;
				case 3:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Normally recovery questions will take 14 days to", "become active, so there's no point in changing them", "everyday! Don't use personal details for your", "recoveries.");
					stage = 99;
					break;
				}
				break;
			case 800:
				interpreter.sendOptions("Select an Option", "Nothing.", "Give them my password.", "Don't tell them anything and inform " + GameWorld.getSettings().getName() + " through the game website.");
				stage = 801;
				break;
			case 801:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "This is one solution, however someone will fall for", "this scam sooner or later. Tell us about it through", "the " + GameWorld.getSettings().getName() + " website. Remember that  moderators are hand", "picked by " + GameWorld.getSettings().getName() + ".");
					stage = 69;
					break;
				case 2:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! This will almost certainly lead to your accout", "being hijacked. No website can make you a moderator", "as they are hand picked by " + GameWorld.getSettings().getName() + ".");
					stage = 99;
					break;
				case 3:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Correct! By informing us we can have the site taken", "down so other people will not have their accounts", "hijacked by this scam.");
					stage = 69;
					break;
				}
				break;
			case 1900:
				interpreter.sendOptions("Select an Option", "Yes.", "No.");
				stage = 1901;
				break;
			case 1901:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! " + GameWorld.getSettings().getName() + " does NOT block your PIN so don't type", "it!");
					stage = 99;
					break;
				case 2:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Correct! " + GameWorld.getSettings().getName() + " will not block your PIN so don't type", "it! Never use personal details for reccoveries or bank", "PINs!");
					stage = 69;
					break;
				}
				break;
			case 1100:
				interpreter.sendOptions("Select an Option", "Yes.", "No.", "If I'm going to be quick.");
				stage = 1101;
				break;
			case 1101:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! You should logout in case you are attacked or", "receive a random event. Leaving your character logged", "in can also allow someone to steal your items or entire account!");
					stage = 99;
					break;
				case 2:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Correct! This is the safest, both in terms of security", "and keeping your items! Leaving you character logged", "in can also allow someone to steal your items or entitre", "account!");
					stage = 69;
					break;
				case 3:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! You should logout in case you are attacked or", "receive a random event. Leaving your character logged", "in can also allow someone to steal your items or entire account!");
					stage = 99;
					break;
				}
				break;
			case 1111:
				interpreter.sendOptions("Select an Opion", "On " + GameWorld.getSettings().getName() + " and all fansites.", "Only on the " + GameWorld.getSettings().getName() + " website.", "On all websites I visit.");
				stage = 1112;
				break;
			case 1112:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! Always use a unique password purely for your " + GameWorld.getSettings().getName() + " account.");
					stage = 99;
					break;
				case 2:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Correct! Always make sure you are entering your", "password only on the " + GameWorld.getSettings().getName() + " Website as other sites", "may try to steal it.");
					stage = 69;
					break;
				case 3:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! This is very insecure and will may lead to", "your account being stolen.");
					stage = 99;
					break;
				}
				break;
			case 1200:
				interpreter.sendOptions("Select an Option", "Your real life bank PIN.", "Your birthday.", "The birthday of a famous person or event.");
				stage = 1201;
				break;
			case 1201:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "This is a bad idea as if someone happens to find out your bank", "PIN on " + GameWorld.getSettings().getName() + ", they then have acces to your bank account.");
					stage = 99;
					break;
				case 2:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Not a good idea because you know how many presents", "you get for your birthday. So you can imagine how", "many people know this date. Never use personal details", "for recoveries or bank PINs!");
					stage = 99;
					break;
				case 3:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Well done! Unless you tell someone, they are unlikely", "to guess who or what you have chosen, and you can", "always look it up, Never use personal details for recoveries or", "bank PINs!");
					stage = 69;
					break;
				}
				break;
			case 1300:
				interpreter.sendOptions("Select an Option", "Virus scan my computer then change my password and recoveries.", "Change my password and recoveries then virus scan my computer.", "Nothing, it will go away on its own.");
				stage = 1301;
				break;
			case 1301:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Correct! Removing the keylogger must be the priority", "otherwise anything you type can be given away.", "Remember to change your password and recovery", "questions afterwards.");
					stage = 69;
					break;
				case 2:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! If you change your password and recoveries", "while you still have the keylogger, they will still be", "insecure. Remove the keylogger first. Never use", "personal details for recoveries or bank PINs!");
					stage = 99;
					break;
				case 3:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! This could mean your account may be", "accessed by someone else. Remove the keylogger then", "change your password and recoveries. Never use", "personal details for recoveries or bank PINs!");
					stage = 99;
					break;
				}
				break;
			case 1400:
				interpreter.sendOptions("Select an Option", "Memorable", "Easy to guess", "Random gibberish");
				stage = 1401;
				break;
			case 1401:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Correct! A good recovery answer that not many people", "will know, you won't forget, will stay the same and that", "you wouldn't accidentally give away. Remember: Don't", "use personal details for your recoveries.");
					stage = 69;
					break;
				case 2:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "This is a bad idea as anyone who knows you could", "guess them. Remember: Don't use personal details for", "your recoveries.");
					stage = 99;
					break;
				case 3:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "This is a bad idea because it is very difficult to", "remember and you won't be able to recover your", "account! Remember: Don't use personal details for", "your recoveries.");
					stage = 99;
					break;
				}
				break;
			case 1500:
				interpreter.sendOptions("Select an Option", "Give them the information they asked for.", "Don't tell them anything and ignore them.");
				stage = 1501;
				break;
			case 1501:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! There is no " + GameWorld.getSettings().getName() + " Lottery! Never give", "your account details to anyone. Press the 'Report Abuse'", "button and fill in the offending player's name and", "the correct category.", "Don't tell them anything and click the 'Report Abuse' button.");
					stage = 99;
					break;
				case 2:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Quite good. But we should try to stop scammers.", "So please report them using the 'Report Abuse' button.");
					stage = 69;
					break;
				case 3:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Correct! Press the 'Report Abuse' button and", "fill in the offending player's name and the correct category.");
					stage = 69;
					break;
				}
				break;
			case 1600:
				interpreter.sendOptions("Select an Option", "Tell them never to use them.", "Use the Account Managemnt section on the " + GameWorld.getSettings().getName() + " website.", "'Recover a Lost Password' section on the " + GameWorld.getSettings().getName() + " website.");
				stage = 1601;
				break;
			case 1601:
				if (buttonId == 1) {
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! This does nothing to help the security of your", "account. You should reset your questions through", "the'Lost password' link on our website.");
					stage = 99;
				} else if (buttonId == 2) {
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! If you use the Account Management section to", "change your recovery questions, it will take 14 days", "to come into effect, someone may have acces to your", "account this time.");
					stage = 99;
				} else if (buttonId == 3) {
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Correct! If you provide all the correct information", "this will reset your questions within 24 hours and", "make your account secure again.");
					stage = 69;
				}
				break;
			case 1700:
				interpreter.sendOptions("Select an Option", " Don't give them any information and send an 'Abuse report'.", "Don't tell them anything and ignore them.", "Give them the information they asked for.");
				stage = 1701;
				break;
			case 1701:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Correct! Press the 'Report Abuse' button and fill", "in the offending player's name and the correct", "category.");
					stage = 69;
					break;
				case 2:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Quite good. But we should try to stop scammers.", "So please report them using the 'Report Abuse'", "button.");
					stage = 69;
					break;
				case 3:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! " + GameWorld.getSettings().getName() + " never ask for your account", "information especially to become a player moderator.", "Press the 'Report Abuse' button and fill in the offending player's", "name and the correct cattegory.");
					stage = 99;
					break;
				}
				break;
			case 1800:
				interpreter.sendOptions("Select an Option", "On the " + GameWorld.getSettings().getName() + " website.", "By searching the internet.", "Nowhere.");
				stage = 1801;
				break;
			case 1801:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! There are NO " + GameWorld.getSettings().getName() + " cheats coded", "into the game and any sites claiming to have cheats", "are fakes and may lead to your account being stolen if you", "give them your password.");
					stage = 99;
					break;
				case 2:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Wrong! There are NO " + GameWorld.getSettings().getName() + " cheats coded", "into the game and any sites claiming to have cheats", "are fakes and may lead to your account being stolen if you", "give them your password.");
					stage = 99;
					break;
				case 3:
					interpreter.sendDialogues(npcId, FacialExpression.OLD_NORMAL, "Correct! There are NO " + GameWorld.getSettings().getName() + " cheats coded into", "the game. Any sites claiming to have cheats are", "fakes and may lead to your account being stolen if you give", "them your password.");
					stage = 69;
					break;
				}
				break;
			case 69:
				end();
				openDoor(player, door);
				break;
			}
			return true;
		}

		/**
		 * Gets the npc id from the door name.
		 * @param name the name.
		 * @return the name.
		 */
		private int getNpcId(String name) {
			switch (name) {
			case "Gate of War":
				return 4377;
			case "Rickety door":
				return 4378;
			case "Oozing barrier":
				return 4379;
			case "Portal of Death":
				return 4380;
			}
			return 0;
		}

		@Override
		public int[] getIds() {
			return new int[] { DialogueInterpreter.getDialogueKey("strong-hold") };
		}

	}

	/**
	 * Represents the explorer dialogue.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class ExplorerDialogue extends DialoguePlugin {

		/**
		 * Represents the stronghold notes item.
		 */
		private static final Item STRONGHOLD_NOTES = new Item(9004);

		/**
		 * Represents the animation specific to pickpocketing.
		 */
		private static final Animation ANIMATION = new Animation(881);

		/**
		 * Constructs a new {@code ExplorerDialogue} {@code Object}.
		 * @param player the player.
		 */
		public ExplorerDialogue(final Player player) {
			super(player);
		}

		/**
		 * Constructs a new {@code ExplorerDialogue} {@code Object}.
		 */
		public ExplorerDialogue() {
			/**
			 * empty.
			 */
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new ExplorerDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			player.animate(ANIMATION);
			if (player.getInventory().containsItem(STRONGHOLD_NOTES) || player.getBank().containsItem(STRONGHOLD_NOTES)) {
				player.getPacketDispatch().sendMessage("You don't find anything.");
				end();
				return true;
			}
			interpreter.sendDialogue("You rummage around in the dead explorer's bag.....");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				interpreter.sendDialogue("You find a book of hand written notes.");
				stage = 1;
				break;
			case 1:
				player.getInventory().add(STRONGHOLD_NOTES, player);
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 16152 };
		}

	}

	/**
	 * Represents the stronghold component plugin.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class StrongholdComponentPlugin extends ComponentPlugin {

		/**
		 * Represents the destination.
		 */
		private final Location destination;

		/**
		 * Constructs a new {@code StrongholdComponentPlugin} {@code Object}.
		 * @param destination the destination.
		 */
		public StrongholdComponentPlugin(final Location destination) {
			this.destination = destination;
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			return this;
		}

		@Override
		public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
			switch (button) {
			case 18:
				if (player.getInterfaceManager().close())
					player.getDialogueInterpreter().open(8000, "No thanks, I don't want to die!");
				return true;
			case 17:
				player.getInterfaceManager().close();
				ladder(player, destination);
				player.getPacketDispatch().sendMessage("You climb down the ladder to the next level.");
				return true;
			}
			return true;
		}

	}
}
