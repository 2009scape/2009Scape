package core.game.node.entity.player.link.emote;

import core.game.container.impl.EquipmentContainer;
import plugin.quest.tutorials.tutorialisland.TutorialSession;
import plugin.quest.tutorials.tutorialisland.TutorialStage;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;

/**
 * Represents an emote.
 * @author Vexia
 *
 */
public enum Emotes {
	YES(2, Animation.create(855)),
	NO(3, Animation.create(856)),
	BOW(4, Animation.create(858)) {
		@Override
		public void play(Player player) {
			Item legs = player.getEquipment().get(EquipmentContainer.SLOT_LEGS);
			if (legs != null && legs.getId() == 10396) {
				forceEmote(player, Animation.create(5312), null);
				return;
			}
			super.play(player);
		}
	},
	ANGRY(5, Animation.create(859)) {
		@Override
		public void play(Player player) {
			Item hat = player.getEquipment().get(EquipmentContainer.SLOT_HAT);
			if (hat != null && hat.getId() == 10392) {
				forceEmote(player, Animation.create(5315), null);
				return;
			}
			super.play(player);
		}
	},
	THINK(6, Animation.create(857)),
	WAVE(7, Animation.create(863)),
	SHRUG(8, Animation.create(2113)),
	CHEER(9, Animation.create(862)),
	BECKON(10, Animation.create(864)),
	LAUGH(11, Animation.create(2109)),
	JUMP_FOR_JOY(12, Animation.create(861)),
	YAWN(13, Animation.create(2111)) {
		@Override
		public void play(Player player) {
			Item hat = player.getEquipment().get(EquipmentContainer.SLOT_HAT);
			if (hat != null && hat.getId() == 10398) {
				forceEmote(player, Animation.create(5313), null);
				return;
			}
			super.play(player);
		}
	},
	DANCE(14, Animation.create(866)) {
		@Override
		public void play(Player player) {
			Item legs = player.getEquipment().get(EquipmentContainer.SLOT_LEGS);
			if (legs != null && legs.getId() == 10394) {
				forceEmote(player, Animation.create(5316), null);
				return;
			}
			super.play(player);
		}
	},
	JIG(15, Animation.create(2106)),
	SPIN(16, Animation.create(2107)),
	HEADBANG(17, Animation.create(2108)),
	CRY(18, Animation.create(860)),
	BLOW_KISS(19, Animation.create(1368), Graphics.create(574)),
	PANIC(20, Animation.create(2105)),
	RASPBERRY(21, Animation.create(2110)),
	CLAP(22, Animation.create(865)),
	SALUTE(23, Animation.create(2112)),
	GOBLIN_BOW(24, null, "This emote can be unlocked during the Lost Tribe quest."),
	GOBLIN_SALUTE(25, null, "This emote can be unlocked during the Lost Tribe quest."),
	GLASS_BOX(26, null, "This emote can be unlocked during the Mime random event."),
	CLIMB_ROPE(27, null, "This emote can be unlocked during the Mime random event."),
	LEAN(28, null, "This emote can be unlocked during the Mime random event."),
	GLASS_WALL(29, null, "This emote can be unlocked during the Mime random event."),
	IDEA(33, Animation.create(4276), Graphics.create(712), "You can't use this emote yet. <br>Visit the Stronghold of Security to unlock it."),
	STOMP(31, Animation.create(4278), "You can't use this emote yet. <br>Visit the Stronghold of Security to unlock it."),
	FLAP(32, Animation.create(4280), "You can't use this emote yet. <br>Visit the Strongshold of Security to unlock it.") {
		@Override
		public void play(Player player) {
			Item head = player.getEquipment().get(EquipmentContainer.SLOT_HAT);
			Item wings = player.getEquipment().get(EquipmentContainer.SLOT_CHEST);
			Item legs = player.getEquipment().get(EquipmentContainer.SLOT_LEGS);
			Item feet = player.getEquipment().get(EquipmentContainer.SLOT_FEET);
			if (head != null && wings != null && legs != null && feet != null) {
				if (head.getId() == 11021 && wings.getId() == 11020 && legs.getId() == 11022 && feet.getId() == 11019) {
					forceEmote(player, Animation.create(3859), null);
					return;
				}
			}
			super.play(player);
		}
	},
	SLAP_HEAD(30, Animation.create(4275), "You can't use this emote yet. <br>Visit the Stronghold of Security to unlock it."),
	ZOMBIE_WALK(34, Animation.create(3544), "This emote can be unlocked during the Gravedigger random event."),
	ZOMBIE_DANCE(35,Animation.create(3543),"This emote can be unlocked during the Gravedigger random event."),
	ZOMBIE_HAND(36, Animation.create(7272), Graphics.create(1244), "This emote can be unlocked during the Gravedigger random event."),
	SCARED(37, Animation.create(2836), "This emote can be unlocked by playing a Halloween holiday event."),
	BUNNY_HOP(38, Animation.create(6111), "This emote can be unlocked by playing an Easter holiday event."),
	SKILLCAPE(39) {
		@Override
		public void play(Player player) {
			Item cape = player.getEquipment().get(EquipmentContainer.SLOT_CAPE);
			if (cape == null) {
				player.getPacketDispatch().sendMessage("You need to be wearing a skillcape in order to perform this emote.");
				return;
			}
			int capeId = cape.getId();
			for (int i = 0; i < SKILLCAPE_INFO.length; i++) {
				if (capeId == SKILLCAPE_INFO[i][0] || capeId == SKILLCAPE_INFO[i][1]) {
					player.getPacketDispatch().sendGraphic(SKILLCAPE_INFO[i][2]);
					player.getPacketDispatch().sendAnimation(SKILLCAPE_INFO[i][3]);
					int duration = Animation.create(SKILLCAPE_INFO[i][3]).getDuration();
					player.getLocks().lock("emote", duration);
					player.getLocks().lock(duration);
					return;
				}
			}
			player.getPacketDispatch().sendMessage("You need to be wearing a skillcape in order to perform this emote.");
		}
	},
	SNOWMAN_DANCE(40, Animation.create(7531), "This emote can be unlocked by playing a Christmas holiday event."),
	AIR_GUITAR(41, Animation.create(2414), Graphics.create(1537), "This emote can be accessed by unlocking 200 pieces of music."),
	SAFETY_FIRST(42, Animation.create(8770), Graphics.create(1553), "You can't use this emote yet. Visit the Stronghold of Player safety to<br>unlock it."),
	EXPLORE(43, Animation.create(9990), Graphics.create(1734), "You can't use this emote yet. You must complete all the Lumbridge <br>and Draynor beginner tasks to unlock it."),
	TRICK(44, Animation.create(10530), Graphics.create(1863), "This emote can be unlocked by playing a Halloween holiday event."),
	FREEZE(45, Animation.create(11044), Graphics.create(1973), "This emote can be unlocked by playing a Christmas holiday event."),
	FLAP_ENHANCED(999,Animation.create(3859)),
	GIVE_THANKS(46, "This emote can be unlocked by playing a Thanksgiving holiday event."){
		@Override
		public void play(final Player player){
			GameWorld.Pulser.submit(new Pulse(1, player) {
			    int counter;
			    @Override
			    public boolean pulse() {
				switch (counter++) {
				case 1:
					player.lock(17);
					forceEmote(player, Animation.create(10994), Graphics.create(86));
				    break;
				case 3:
					player.getAppearance().transformNPC(8499);
					forceEmote(player, Animation.create(10996), null);
					break;
				case 16:
					player.getAppearance().transformNPC(-1);
					forceEmote(player, Animation.create(10995), Graphics.create(86));
				    break;
				}
				return false;
			    }
			});
		}
	};

	/**
	 * Represents the skillcape info.
	 */
	private static final int[][] SKILLCAPE_INFO = { { 9747, 9748, 823, 4959 }, { 9750, 9751, 828, 4981 }, { 9753, 9754, 824, 4961 }, { 9756, 9757, 832, 4973 }, { 9759, 9760, 829, 4979 }, { 9762, 9763, 813, 4939 }, { 9765, 9766, 817, 4947 }, { 9768, 9769, 833, 4971 }, { 9771, 9772, 830, 4977 }, { 9774, 9775, 835, 4969 }, { 9777, 9778, 826, 4965 }, { 9780, 9781, 818, 4949 }, { 9783, 9784, 812, 4937 }, { 9786, 9787, 1656, 4967 }, { 9789, 9790, 820, 4953 }, { 9792, 9793, 814, 4941 }, { 9795, 9796, 815, 4943 }, { 9798, 9799, 819, 4951 }, { 9801, 9802, 821, 4955 }, { 9804, 9805, 831, 4975 }, { 9807, 9808, 822, 4957 }, { 9810, 9811, 825, 4963 }, { 12169, 12170, 1515, 8525 }, { 9813, -1, 816, 4945 }, { 9948, 9949, 907, 5158 } };

	/**
	 * The button id.
	 */
	private final int buttonId;

	/**
	 * The animation.
	 */
	private final Animation animation;

	/**
	 * The graphics to play.
	 */
	private final Graphics graphics;

	/**
	 * The message to show when the emote is locked.
	 */
	private final String lockedMessage;

	/**
	 * Constructs a new {@Code Emotes} {@Code Object}
	 * @param buttonId the button id.
	 * @param animation the animation.
	 * @param graphics the graphics. 
	 * @param lockedMessage the locked message.
	 */
	private Emotes(int buttonId, Animation animation, Graphics graphics, String lockedMessage) {
		this.buttonId = buttonId;
		this.animation = animation;
		this.graphics = graphics;
		this.lockedMessage = lockedMessage;
	}

	/**
	 * Constructs a new {@Code Emotes} {@Code Object}
	 * @param buttonId the button id.
	 */
	private Emotes(int buttonId) {
		this(buttonId, null, null, null);
	}

	/**
	 * Constructs a new {@Code Emotes} {@Code Object}
	 * @param buttonId the button id. 
	 * @param animation the animation.
	 */
	private Emotes(int buttonId, Animation animation) {
		this(buttonId, animation, null, null);
	}

	/**
	 * Constructs a new {@Code Emotes} {@Code Object}
	 * @param buttonId the button id.
	 * @param animation the animation.
	 * @param graphics the graphics.
	 */
	private Emotes(int buttonId, Animation animation, Graphics graphics) {
		this(buttonId, animation, graphics, null);
	}

	/**
	 * Constructs a new {@Code Emotes} {@Code Object}
	 * @param buttonId the button id.
	 * @param animation the animation.
	 * @param lockedMessage the locked message.
	 */
	private Emotes(int buttonId, Animation animation, String lockedMessage) {
		this(buttonId, animation, null, lockedMessage);
	}
	
	/**
	 * Constructs a new {@Code Emotes} {@Code Object}
	 * @param buttonId the button id.
	 * @param lockedMessage the locked message.
	 */
	private Emotes(int buttonId, String lockedMessage) {
		this(buttonId, null, null, lockedMessage);
	}

	/**
	 * Handles the reward button for an emote.
	 * @param player the player.
	 * @param buttonId the button id.
	 */
	public static void handle(Player player, int buttonId) {
		if (player.getLocks().isLocked("emote")) {
			player.getPacketDispatch().sendMessage("You're already doing an emote!");
			return;
		}
		if (player.getProperties().getCombatPulse().isAttacking() || player.inCombat()) {
			player.getPacketDispatch().sendMessage("You can't perform an emote while being in combat.");
			return;
		}
		Emotes emote = Emotes.forId(buttonId);
		if (emote == null) {
			player.debug("Unhandled emote for button id - " + buttonId);
			return;
		}
		if (!player.getEmoteManager().isUnlocked(emote)) {
			String message = emote.getLockedMessage();
			if (message == null) {
				message = "You can't use this emote.";
			}
			player.getDialogueInterpreter().sendDialogue(message);
			return;
		}
		//set flap to the enhanced version if player is wearing chicken costume
		if(emote == FLAP && (player.getEquipment().contains(11019,1) && player.getEquipment().contains(11020,1) && player.getEquipment().contains(11021,1) && player.getEquipment().contains(11022,1))){
			emote = FLAP_ENHANCED;
		}
		if (!player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).isComplete(1, 3) && (buttonId >= 30 && buttonId <= 33)) {
			if (!player.getAttribute("emote-" + buttonId, false)) {
				player.setAttribute("emote-" + buttonId, true);
			}
			boolean good = true;
			int b = 33;
			for (int i = 0; i < 4; i++) {
				if (i != 0) {
					b = i == 1 ? 31 : i == 2 ? 32 : 30;
				}
				if (!player.getAttribute("emote-" + b, false)) {
					good = false;
					break;
				}
			}
			if (!player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).isComplete(1, 3)) {
				player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).updateTask(player, 1, 3, good);
			}
		}
		if (TutorialSession.getExtension(player).getStage() == 24) {
		    TutorialStage.load(player, 25, false);
		}
		player.getPulseManager().clear();
		emote.play(player);
	}

	/**
	 * Plays the emote.
	 * @param player the player.
	 */
	public void play(Player player) {
		forceEmote(player, animation, graphics);
	}

	/**
	 * Forces the animation to be played.
	 * @param player the player.
	 * @param animation the animation.
	 * @param graphic the graphic.
	 */
	private static void forceEmote(Player player, Animation animation, Graphics graphic) {
		if (animation != null) {
			player.getAnimator().animate(animation, graphic);
			player.getLocks().lock("emote", animation.getDuration());
		}
	}

	/**
	 * Gets the emote for the button id.
	 * @param buttonId the button id.
	 * @return the emote type.
	 */
	public static Emotes forId(int buttonId) {
		for (Emotes emote : values()) {
			if (emote.getButtonId() == buttonId) {
				return emote;
			}
		}
		return null;
	}

	/**
	 * Gets the buttonId.
	 * @return the buttonId.
	 */
	public int getButtonId() {
		return buttonId;
	}

	/**
	 * Gets the animation.
	 * @return the animation.
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * Gets the graphics.
	 * @return the graphics.
	 */
	public Graphics getGraphics() {
		return graphics;
	}

	/**
	 * Gets the lockedMessage.
	 * @return the lockedMessage.
	 */
	public String getLockedMessage() {
		return lockedMessage;
	}

}
