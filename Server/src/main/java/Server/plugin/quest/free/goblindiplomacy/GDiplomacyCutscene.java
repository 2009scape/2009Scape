package plugin.quest.free.goblindiplomacy;

import plugin.activity.ActivityManager;
import plugin.activity.ActivityPlugin;
import plugin.activity.CutscenePlugin;
import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.Entity;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.game.world.map.build.DynamicRegion;
import core.game.world.map.path.Pathfinder;
import core.game.world.repository.Repository;
import core.game.world.update.flag.context.Animation;
import core.net.packet.PacketRepository;
import core.net.packet.context.CameraContext;
import core.net.packet.context.CameraContext.CameraType;
import core.net.packet.out.CameraViewPacket;
import core.tools.RandomFunction;
import core.tools.StringUtils;

/**
 * Represents the goblin diplomacy cutscene plugin.
 * @author 'Vexia
 * @version 1.0
 */
public final class GDiplomacyCutscene extends CutscenePlugin {

	/**
	 * Constructs a new {@code GDiplomacyCutscene} {@code Object}.
	 */
	public GDiplomacyCutscene() {
		this(null);
	}

	/**
	 * Constructs a new {@code GDiplomacyCutscene} {@code Object}.
	 */
	public GDiplomacyCutscene(final Player player) {
		super("Goblin Diplomacy Cutscene");
		this.player = player;
	}

	@Override
	public boolean start(final Player player, final boolean login, Object... args) {
		Quest quest = player.getQuestRepository().getQuest(GoblinDiplomacy.NAME);
		final NPC grubfoot = NPC.create(quest.getStage(player) == 10 ? 4495 : quest.getStage(player) == 20 ? 4497 : quest.getStage(player) == 30 ? 4498 : 4496, getBase().transform(10, 55, 0));
		grubfoot.setWalks(false);
		npcs.add(grubfoot);
		npcs.add(NPC.create(4494, getBase().transform(14, 55, 0)));
		npcs.add(NPC.create(4493, getBase().transform(14, 56, 0)));
		for (NPC npc : npcs) {
			npc.setDirection(Direction.WEST);
			npc.init();
		}
		return super.start(player, login, args);
	}

	@Override
	public void open() {
		player.getDialogueInterpreter().open(4494, npcs.get(1), this);
		PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, player.getLocation().getX() + 4, player.getLocation().getY() + 1, 390, 1, 100));
		PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, player.getLocation().getX() + 2, player.getLocation().getY(), 390, 1, 100));
	}

	@Override
	public Location getSpawnLocation() {
		return null;
	}

	@Override
	public void configure() {
		region = DynamicRegion.create(11830);
		setRegionBase();
		registerRegion(region.getId());
	}

	@Override
	public void register() {
		new GoblinGeneralDialogue().init();
	}

	@Override
	public Location getStartLocation() {
		return getBase().transform(13, 57, 0);
	}

	@Override
	public ActivityPlugin newInstance(Player p) throws Throwable {
		return new GDiplomacyCutscene(p);
	}

	/**
	 * Represents the dialogue which handles the dialogues between the goblin
	 * generals.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class GoblinGeneralDialogue extends DialoguePlugin {

		/**
		 * Represents an array of dialogues to send.
		 */
		private static final String[][] DIALOGUES = new String[][] { { "@base (@color) armour best.", "@other No it has to be @ocolor.", "@base Go away human, we busy." }, { "@base I tell all goblins in village to wear @color armour now!", "@other They not listen to you! I already tell them wear @ocolor /n armour!", "@base They listen to me not you! They know me bigger /n general!", "@other Me bigger general! They listen to me!", "@base Human! What colour armour they wearing out there?", "@player Half of them wearing red and half of them green.", "@base Shut up human! They wearing green armour really! /n Human lying because he scared of you!", "@other Human scared of me not you! THen you think me /n bigger general!", "@base What? Me mean...", "@base Shut up! Me bigger general!" }, { "@base All goblins should wear @color armour!", "@other Not @ocolor! @ocolor make you look fat.", "@base Everything make YOU look fat!", "@other Shut up!", "@base Fatty!", "@other SHUT UP!", "@base Even this human think you look fat! Don't you, human?", "@player Um...", "@player No, he doesn't look fat.", "@base Shut up human! @oname fat and human stupid!", "@other Shut up @bname" }, { "@other (@ocolor) armour best.", "@base No no @color every time.", "@other Go away human, we busy." }, };

		/**
		 * Represents the cutscene.
		 */
		private GDiplomacyCutscene cutscene;

		/**
		 * Represents the instanced quest.
		 */
		private Quest quest;

		/**
		 * Represents the other general.
		 */
		private NPC other;

		/**
		 * Represents the grubfoot npc.
		 */
		private NPC grubfoot;

		/**
		 * Represents the dialogue index we're at.
		 */
		private int dialIndex;

		/**
		 * Represents the dialogue index we're at.
		 */
		private int index;

		/**
		 * The current grubfoot type.
		 */
		private GrubFoot type;

		/**
		 * Constructs a new {@code GeneralWartface} {@code Object}.
		 */
		public GoblinGeneralDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code GeneralWartface} {@code Object}.
		 * @param player the player.
		 */
		public GoblinGeneralDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new GoblinGeneralDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			type = GrubFoot.forConfig(player);
			dialIndex = RandomFunction.random(DIALOGUES.length);
			other = Repository.findNPC(npc.getId() == 4494 ? 4493 : 4494);
			quest = player.getQuestRepository().getQuest(GoblinDiplomacy.NAME);
			switch (quest.getStage(player)) {
			case 100:
				npc("Now you've solved out argument we gotta think of", "something else to do.");
				stage = 0;
				break;
			default:
				if (args.length >= 2) {
					setUpCutScene(args);
					return true;
				} else {
					parseDialogue(DIALOGUES[dialIndex]);
					return true;
				}
			}
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (quest.getStage(player)) {
			case 0:
				handleStart(buttonId);
				break;
			case 10:
			case 20:
			case 30:
				handleDefault(buttonId);
				break;
			case 100:
				switch (stage) {
				case 0:
					interpreter.sendDialogues(other, null, "Yep we bored now.");
					stage = 1;
					break;
				case 1:
					end();
					break;
				}
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { /** wartface */
			4494, /** bentnoze */
			4493 };
		}

		/**
		 * Method used to parse the dialogue.
		 * @param dialogue the dialogue.
		 */
		public void parseDialogue(String[] dialogue) {
			if (index == DIALOGUES.length - 1) {
				if (quest.getStage(player) == 0) {
					interpreter.sendOptions("Select an Option", "Why are you arguing about the colour of your armour?", "Wouldn't you prefer peace?", "Do you want me to pick an armour colour for you?");
					stage = 1;
				} else {
					if (!player.getInventory().containsItem(type.getMail())) {
						options("Why are you arguing about the colour of your armour?", "Wouldn't you prefer peace?", "Where am I meant to get " + type.name().toLowerCase() + " amour?");
						stage = 1;
					} else {
						options("Why are you arguing about the colour of your armour?", "Wouldn't you prefer peace?", "I have some " + type.name().toLowerCase() + " armour here.");
						stage = 1;
					}
				}
				return;
			}
			final Entity entity = getSpeaker(dialogue[index]);
			final String[] lines = getLines(dialogue[index]);
			String line = null;
			for (int i = 0; i < lines.length; i++) {
				line = (String) lines[i];
				line = ((String) line).replace("(@color)", StringUtils.formatDisplayName(getColor((String) line)));
				line = ((String) line).replace("(@ocolor)", StringUtils.formatDisplayName(getOtherColor((String) line)));
				line = ((String) line).replace("@color", getColor((String) line));
				line = ((String) line).replace("@ocolor", getOtherColor((String) line));
				line = ((String) line).replace("@player", "").replace("@base", "").replace("@other", "").trim();
				line = ((String) line).trim();
				lines[i] = line;
			}
			interpreter.sendDialogues(entity, null, lines);
			index++;
		}

		/**
		 * Handles the default options for each stage.
		 */
		public void defaultOptions(int buttonId) {
			switch (stage) {
			case 10:
				npc("We decide to celebrate goblin new century by changing", "colour of our armour, brown get boring after a bit.", "We want change.");
				stage = 11;
				break;
			case 11:
				interpreter.sendDialogues(other, null, "Problem is they want different change to us.");
				stage = 12;
				break;
			case 12:
				end();
				break;
			case 20:
				interpreter.sendDialogues(other, null, "Yeah peace is good as long as it peace wearing my", "armour.");
				stage = 21;
				break;
			case 21:
				npc("But his color too ugly. Nearly make you look", "like an ugly goblin!");
				stage = 22;
				break;
			case 22:
				end();
				break;
			case 30:
				npc("Well first you get goblin armour...");
				stage = 31;
				break;
			case 31:
				interpreter.sendDialogues(other, null, "...And then you dye it " + type.name().toLowerCase() + "!");
				stage = 32;
				break;
			case 32:
				npc("Even human should be able to work that out!");
				stage = 33;
				break;
			case 33:
				interpreter.sendOptions("Select an Option", "But where do I get goblin armour?", "But where do I get dye?");
				stage = 34;
				break;
			case 34:
				switch (buttonId) {
				case 1:
					player("But where do I get goblin armour?");
					stage = 35;
					break;
				case 2:
					player("But where do I get dye?");
					stage = 38;
					break;
				}
				break;
			case 35:
				npc("There some spare armour around village somewhere.", "You can take that.");
				stage = 36;
				break;
			case 36:
				interpreter.sendDialogues(other, null, "It in crates somewhere. Can't remember whcih crates", "now.");
				stage = 37;
				break;
			case 37:
				end();
				break;
			case 38:
				interpreter.sendDialogues(other, null, "You go north of here into wilderness. There you find", "many ways to die!");
				stage = 40;
				break;
			case 40:
				player("no, D-Y-E, not D-I-E.");
				stage = 41;
				break;
			case 41:
				npc("Stupid " + other.getName() + ", you not know how to spell!");
				stage = 42;
				break;
			case 42:
				interpreter.sendDialogues(other, null, "Shut up " + npc.getName() + "!");
				stage = 43;
				break;
			case 43:
				player("Do you know where I can get dye though?");
				stage = 44;
				break;
			case 44:
				interpreter.sendDialogues(other, null, "Me not know where dye come from.");
				stage = 45;
				break;
			case 45:
				player("Well where did you get your red and green dye from?");
				stage = 46;
				break;
			case 46:
				npc("Some goblin or other, he steal it. Say he steal it from", "old witch in Draynor Village.");
				stage = 47;
				break;
			case 47:
				interpreter.sendDialogues(other, null, "Maybe you can get more dye from here?");
				stage = 48;
				break;
			case 48:
				end();
				break;
			}
		}

		/**
		 * Handles the default started quest dialogue.
		 * @param buttonId the buttonId.
		 */
		public void handleDefault(int buttonId) {
			if (stage > 99) {
				handleFinishDialogues();
			}
			switch (stage) {
			case 0:
				parseDialogue(DIALOGUES[dialIndex]);
				break;
			case 1:
				switch (buttonId) {
				case 1:
					player("Why are you arguing about the colour of", "your armour?");
					stage = 10;
					break;
				case 2:
					player("Wouldn't you prefer peace?");
					stage = 20;
					break;
				case 3:
					if (!player.getInventory().containsItem(type.getMail())) {
						player("Where am I meant to get " + type.name().toLowerCase() + " armour?");
						stage = 30;
					} else {
						player("I have some " + type.name().toLowerCase() + " armour here.");
						stage = 50;
					}
					break;
				}
				break;
			case 50:
				end();
				ActivityManager.start(player, "Goblin Diplomacy Cutscene", false);
				break;
			case 60:
				player.face(grubfoot);
				interpreter.sendDialogues(grubfoot, null, "Yes General Wartface sir?");
				Pathfinder.find(grubfoot, npc.getLocation().transform(-1, 0, 0)).walk(grubfoot);
				stage = 61;
				break;
			case 61:
				npc("Put on this armour!");
				player.face(npc);
				stage = 62;
				break;
			case 62:
				sendGrubFoot(type, 100);
				break;
			case 39:
				end();
				break;
			default:
				defaultOptions(buttonId);
				break;
			}
		}

		/**
		 * Sets up the cutscene.
		 */
		private void setUpCutScene(Object... args) {
			player.lock();
			cutscene = (GDiplomacyCutscene) args[1];
			other = cutscene.getNPCS().get(2);
			grubfoot = cutscene.getNPCS().get(0);
			npc("Grubfoot!");
			player.face(npc);
			stage = 60;
		}

		/**
		 * Sends the new grubfoot.
		 * @param grubFoot the grubfoot.
		 * @param the end stage.
		 */
		public void sendGrubFoot(final GrubFoot grubFoot, final int endStage) {
			Pathfinder.find(grubfoot, grubfoot.getLocation().transform(-4, 0, 0)).walk(grubfoot);
			GameWorld.Pulser.submit(new Pulse(1, player) {
				int counter;

				@Override
				public boolean pulse() {
					switch (++counter) {
					case 1:
						player.getDialogueInterpreter().sendPlainMessage(true, "Grubfoot goes into the changing room...");
						break;
					case 7:
						grubfoot.transform(grubFoot.getId());
						grubfoot.getProperties().setTeleportLocation(grubfoot.getLocation().transform(-1, 0, 0));
						player.getPacketDispatch().sendObjectAnimation(RegionManager.getObject(cutscene.getBase().transform(9, 55, 0)), new Animation(24));
						break;
					case 11:
						grubfoot.getProperties().setTeleportLocation(grubfoot.getLocation().transform(1, 0, 0));
						player.getPacketDispatch().sendObjectAnimation(RegionManager.getObject(cutscene.getBase().transform(9, 55, 0)), new Animation(24));
						break;
					case 12:
						player.getDialogueInterpreter().sendPlainMessage(true, "...And emerges wearing " + type.name().toLowerCase() + " armour.");
						break;
					case 13:
						Pathfinder.find(grubfoot, npc.getLocation().transform(-1, 0, 0)).walk(grubfoot);
						break;
					case 16:
						stage = endStage;
						player.faceLocation(grubfoot.getLocation());
						player.getDialogueInterpreter().sendDialogues(grubfoot, null, "What do you think?");
						return true;
					}
					return false;
				}

			});
		}

		/**
		 * Handles the starting of the quest.
		 * @param buttonId the buttonId.
		 */
		private void handleStart(int buttonId) {
			switch (stage) {
			case 0:
				parseDialogue(DIALOGUES[dialIndex]);
				break;
			case 1:
				switch (buttonId) {
				case 1:
					player("Why are you arguing about the colour of", "your armour?");
					stage = 10;
					break;
				case 2:
					player("Wouldn't you prefer peace?");
					stage = 20;
					break;
				case 3:
					player("Do you want me to pick an armour colour for you?");
					stage = 30;
					break;
				}
				break;
			case 30:
				npc("Yes as long as you pick " + (npc.getId() == 4494 ? "green" : "red") + ".");
				stage = 31;
				break;
			case 31:
				interpreter.sendDialogues(other, null, "No you have to pick " + (npc.getId() == 4493 ? "green" : "red") + "!");
				stage = 32;
				break;
			case 32:
				player("What about a different colour? Not green or red?");
				stage = 33;
				break;
			case 33:
				npc("That would mean me wrong... but at least", "" + other.getName() + " not right!");
				stage = 34;
				break;
			case 34:
				interpreter.sendDialogues(other, null, "Me dunno what that look like. Have to see armour", "before we decide.");
				stage = 35;
				break;
			case 35:
				npc("Human! You bring us armour in new colour!");
				stage = 36;
				break;
			case 36:
				interpreter.sendDialogues(other, null, "What colour we try?");
				stage = 37;
				break;
			case 37:
				npc("Orange armour might be good.");
				stage = 38;
				break;
			case 38:
				quest.start(player);
				interpreter.sendDialogues(other, null, "Yes bring us orange armour.");
				stage = 39;
				break;
			default:
				defaultOptions(buttonId);
				break;
			}
		}

		/**
		 * Handles the finishing dialogues.
		 */
		private void handleFinishDialogues() {
			if (stage == 120) {
				if (player.getInventory().remove(type.getMail())) {
					quest.setStage(player, quest.getStage(player) + 10);
				}
				end();
				cutscene.unpause();
				type.setConfig(player);
				return;
			}
			switch (quest.getStage(player)) {
			case 10:
				switch (stage) {
				case 100:
					interpreter.sendDialogues(npc, null, "No I don't like that much.");
					player.face(npc);
					stage++;
					break;
				case 101:
					interpreter.sendDialogues(other, null, "It clashes with skin colour.");
					player.face(other);
					stage++;
					break;
				case 102:
					interpreter.sendDialogues(npc, null, "We need darker colour, like blue.");
					player.face(npc);
					stage++;
					break;
				case 103:
					interpreter.sendDialogues(other, null, "Yeah blue might be good.");
					player.face(other);
					stage++;
					break;
				case 104:
					interpreter.sendDialogues(npc, null, "Human! Get us blue armour!");
					player.face(npc);
					stage = 120;
					break;
				}
				break;
			case 20:
				switch (stage) {
				case 100:
					interpreter.sendDialogues(other, null, "That not right. Not goblin colour at all.");
					player.face(other);
					stage++;
					break;
				case 101:
					interpreter.sendDialogues(npc, null, "Goblins wear dark earthy colours like brown.");
					player.face(npc);
					stage++;
					break;
				case 102:
					interpreter.sendDialogues(other, null, "Yeah brown might be good.");
					player.face(other);
					stage++;
					break;
				case 103:
					interpreter.sendDialogues(npc, null, "Human! Get us brown armour!");
					player.face(npc);
					stage++;
					break;
				case 104:
					interpreter.sendDialogues(player, null, "I thought that was the armour you were changing", "from. Never mind, anything is worth a try.");
					stage = 120;
					break;
				}
				break;
			case 30:
				switch (stage) {
				case 100:
					interpreter.sendDialogues(npc, null, "That colour quite nice. Me can see myself wearing", "that.");
					player.face(npc);
					stage++;
					break;
				case 101:
					interpreter.sendDialogues(other, null, "It a deal then. Brown armour it is.");
					player.face(other);
					stage++;
					break;
				case 102:
					interpreter.sendDialogues(npc, null, "Thank you for sorting out our argument. Take this", "gold bar as reward!");
					npc.face(player);
					other.face(player);
					stage++;
					break;
				case 103:
					end();
					cutscene.unpause();
					if (player.getInventory().remove(GrubFoot.BROWN.getMail())) {
						quest.finish(player);
						GrubFoot.BROWN.setConfig(player);
					}
					break;
				}
				break;
			}
		}

		/**
		 * Gets the speaker of the dialogue.
		 * @param line the line.
		 * @return the entity speaker.
		 */
		public Entity getSpeaker(final String line) {
			return line.contains("@player") ? player : line.contains("@base") ? npc : other;
		}

		/**
		 * Gets the color of the base entity.
		 * @param line the line.
		 * @return the color.
		 */
		public String getColor(final String line) {
			return npc.getId() == 4494 ? "green" : "red";
		}

		/**
		 * Gets the other color of the general.
		 * @param line the line.
		 * @return the other color.
		 */
		public String getOtherColor(final String line) {
			return other.getId() == 4493 ? "red" : "green";
		}

		/**
		 * Gets the lines of the dialogue.
		 * @param line the line.
		 * @return the lines.
		 */
		public String[] getLines(final String line) {
			return line.split("/n");
		}
	}
}
