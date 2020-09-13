package plugin.skill.summoning;

import core.cache.def.impl.ObjectDefinition;
import core.game.component.Component;
import plugin.activity.ActivityManager;
import plugin.activity.ActivityPlugin;
import plugin.activity.CutscenePlugin;
import plugin.dialogue.DialoguePlugin;
import core.game.content.global.action.ClimbActionHandler;
import plugin.quest.members.WolfWhistle;
import plugin.skill.Skills;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.impl.Projectile;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.build.DynamicRegion;
import core.game.world.map.path.Pathfinder;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.net.packet.PacketRepository;
import core.net.packet.context.CameraContext;
import core.net.packet.context.CameraContext.CameraType;
import core.net.packet.context.MinimapStateContext;
import core.net.packet.out.CameraViewPacket;
import core.net.packet.out.MinimapState;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManager;

/**
 * Handles the summoning training room.
 * @author Emperor
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class SummoningTrainingRoom extends OptionHandler {

	/**
	 * Represents the wolf bones item.
	 */
	private static final Item BONES = new Item(2859, 2);

	/**
	 * Represents the trapdoor key.
	 */
	private static final Item TRAPDOOR_KEY = new Item(12528);

	/**
	 * Represents the howl scroll item.
	 */
	private static final Item HOWL_SCROLL = new Item(12425);

	/**
	 * Represents the wolf pouch.
	 */
	private static final Item WOLF_POUCH = new Item(12047);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(28675).getHandlers().put("option:open", this);
		ObjectDefinition.forId(28676).getHandlers().put("option:climb-down", this);
		ObjectDefinition.forId(28653).getHandlers().put("option:climb-down", this);
		ObjectDefinition.forId(28572).getHandlers().put("option:climb-up", this);
		ObjectDefinition.forId(28676).getHandlers().put("option:close", this);
		ObjectDefinition.forId(28714).getHandlers().put("option:climb", this);
		ObjectDefinition.forId(28586).getHandlers().put("option:search", this);
		ActivityManager.register(new FluffyCutscene());
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		GameObject object = (GameObject) node;
		Location loc = null;
		Quest quest = player.getQuestRepository().getQuest(WolfWhistle.NAME);
		int questVal = quest.getStage(player) == 0 ? 0 : quest.getStage(player) > 0 && quest.getStage(player) < 100 ? 5 : 28893;
		switch (option) {
		case "close":
			player.getConfigManager().set(1178, questVal == 28893 ? 32989 : (1 << 11) + questVal);
			return true;
		case "open":
			if (quest.getStage(player) < 40) {
				player.getPacketDispatch().sendMessage("The trapdoor is locked.");
				return true;
			} else if (quest.getStage(player) == 40) {
				if (player.getAttribute("has-key", false) || player.getInventory().remove(TRAPDOOR_KEY)) {
					player.setAttribute("has-key", true);
					player.getConfigManager().set(1178, (2 << 11) + questVal, true);
				} else {
					player.getPacketDispatch().sendMessage("The trapdoor is locked.");
					return true;
				}
			}
			player.getConfigManager().set(1178, questVal == 28893 ? 28893 : (2 << 11) + questVal);
			return true;
		case "climb-down":
			if (object.getId() == 28653) {
				if (quest.getStage(player) == 50) {
					final CutscenePlugin plugin = player.getAttribute("in-cutscene", null);
					if (plugin != null) {
						plugin.stop(true);
					} else {
						ClimbActionHandler.climbLadder(player, object, option);
					}
				} else {
					ClimbActionHandler.climbLadder(player, object, option);
				}
				return true;
			}
			if (!object.getLocation().equals(Location.create(2927, 3444, 0))) {
				return false;
			}
			loc = Location.create(2209, 5348, 0);
			break;
		case "climb":
			if (!object.getLocation().equals(Location.create(2209, 5349, 0))) {
				return false;
			}
			loc = Location.create(2926, 3444, 0);
			break;
		case "climb-up":
			switch (quest.getStage(player)) {
			case 10:
			case 50:
				if (player.getFamiliarManager().hasFamiliar()) {
					player.getPacketDispatch().sendMessage("You can't bring a familiar up there.");
					return true;
				}
				if (quest.getStage(player) == 50 && (!player.getInventory().containsItem(WOLF_POUCH) || !player.getInventory().containsItem(HOWL_SCROLL))) {
					player.getDialogueInterpreter().sendDialogues(player, null, "I should bring my spirit wolf pouch and howl scroll", "with me up there.");
					return true;
				}
				ActivityManager.start(player, "fluffy cutscene", false);
				break;
			case 100:
			case 60:
				ClimbActionHandler.climbLadder(player, (GameObject) node, option);
				break;
			default:
				player.getDialogueInterpreter().sendDialogue("There is a loud crash from upstairs, followed by the tinkling of", "broken glass.");
				break;
			}
			return true;
		case "search":
			if (quest.getStage(player) == 30 && !player.getAttribute("searched-body", false)) {
				if (!player.getInventory().hasSpaceFor(BONES)) {
					player.getPacketDispatch().sendMessage("You search the dead body but find nothing.");
					return true;
				}
				if (!player.getInventory().containsItem(BONES) && player.getInventory().add(BONES)) {
					player.setAttribute("/save:searched-body", true);
					player.getPacketDispatch().sendMessage("You search the dead body and find two wolf bones.");
					return true;
				} else {
					player.getPacketDispatch().sendMessage("You search the dead body but find nothing.");
					return true;
				}
			} else {
				player.getPacketDispatch().sendMessage("You search the dead body but find nothing.");
			}
			return true;
		default:
			return false;
		}
		ClimbActionHandler.climb(player, new Animation(828), loc);
		return true;
	}

	/**
	 * Represents the cutscene used during wolf whistle.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class FluffyCutscene extends CutscenePlugin {

		/**
		 * Constructs a new {@code FluffyCutscene} {@code Object}.
		 */
		public FluffyCutscene() {
			super("fluffy cutscene");
		}

		/**
		 * Constructs a new {@code FluffyCutscene} {@code Object}.
		 * @param player the player.
		 */
		public FluffyCutscene(final Player player) {
			this();
			this.player = player;
		}

		@Override
		public ActivityPlugin newInstance(Player p) throws Throwable {
			return new FluffyCutscene(p);
		}

		@Override
		public void open() {
			player.getDialogueInterpreter().open(392932, this);
			player.setAttribute("wolf-dial", player.getDialogueInterpreter().getDialogue());
			player.setAttribute("in-cutscene", this);
			player.lock();
		}

		@Override
		public void end() {
			super.end();
			player.removeAttribute("in-cutscene");
			player.getFamiliarManager().dismiss();
		}

		@Override
		public Location getSpawnLocation() {
			return null;
		}

		@Override
		public Location getStartLocation() {
			return base.transform(46, 50, 1);
		}

		@Override
		public void configure() {
			region = DynamicRegion.create(11573);
			setRegionBase();
			registerRegion(region.getId());
		}

		@Override
		public void register() {
			PluginManager.definePlugin(new FluffyDialogue());
		}

		/**
		 * Represents the dialogue used during the fluffy cutscene.
		 * @author 'Vexia
		 * @version 1.0
		 */
		public static final class FluffyDialogue extends DialoguePlugin {

			/**
			 * Represents the scared animation.
			 */
			private static final Animation SCARED_ANIMATION = new Animation(2836);

			/**
			 * Represents the wolpertiner graphic.
			 */
			private static final Graphics GRAPHIC = new Graphics(1522);

			/**
			 * Represents the shudder animation.
			 */
			private static final Animation SHUDDER_ANIMATION = new Animation(8506);

			/**
			 * Represents the death animation.
			 */
			private static final Animation DEATH_ANIMATION = new Animation(8507);

			/**
			 * Represents the cutscene instance.
			 */
			private CutscenePlugin cutscene;

			/**
			 * Represents the quest instance.
			 */
			private Quest quest;

			/**
			 * Represents the fluffy npc.
			 */
			private NPC fluffy;

			/**
			 * Represents the wolf npc.
			 */
			private NPC wolf;

			/**
			 * Constructs a new {@code FluffyDialogue} {@code Object}.
			 */
			public FluffyDialogue() {
				/**
				 * empty.
				 */
			}

			/**
			 * Constructs a new {@code FluffyDialogue} {@code Object}.
			 * @param player the player.
			 */
			public FluffyDialogue(final Player player) {
				super(player);
			}

			@Override
			public DialoguePlugin newInstance(Player player) {
				return new FluffyDialogue(player);
			}

			@Override
			public boolean open(Object... args) {
				cutscene = (CutscenePlugin) args[0];
				quest = player.getQuestRepository().getQuest(WolfWhistle.NAME);
				fluffy = NPC.create(6990, cutscene.getBase().transform(41, 52, 1));
				fluffy.init();
				fluffy.faceTemporary(player, 1);
				Location b = cutscene.getBase().transform(42, 50, 0);
				PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, b.getX(), b.getY(), 244, 1, 100));
				PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, b.getX() + 1, b.getY(), 244, 1, 100));
				switch (quest.getStage(player)) {
				case 10:
					player("Come on then little, fluffy...");
					break;
				case 50:
					player("Mustn't look round, bunny will eat me.", "Mustn't look round, bunny will eat me.", "Mustn't look round, bunny will eat me.");
					break;
				}
				return true;
			}

			@Override
			public boolean handle(int interfaceId, int buttonId) {
				player.sendMessage("ButtonId=" + buttonId + ", stage=" + stage);
				switch (quest.getStage(player)) {
				case 10:
					switch (stage) {
					case 0:
						close();
						GameWorld.getPulser().submit(new Pulse(1, player, fluffy) {
							int counter = 0;

							@Override
							public boolean pulse() {
								switch (++counter) {
								case 2:
									player.face(fluffy);
									break;
								case 5:
									player("Gigantic...");
									stage = 1;
									return true;
								}
								return false;
							}

						});
						break;
					case 1:
						player("scary-looking...");
						stage = 2;
						break;
					case 2:
						player("razor-fanged...");
						stage = 3;
						break;
					case 3:
						player("bunny...");
						stage = 4;
						break;
					case 4:
						close();
						int x = player.getLocation().getX() + 1;
						int y = player.getLocation().getY() - 1;
						int height = 300;
						PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, x, y, height, 1, 100));
						PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, x + 1000, y + 13, height, 1, 100));
						player.faceLocation(cutscene.getBase().transform(44, 50, 1));
						GameWorld.getPulser().submit(new Pulse(1, player, fluffy) {
							int counter = 0;

							@Override
							public boolean pulse() {
								switch (++counter) {
								case 3:
									fluffy.sendChat("Mrooowr?");
									break;
								case 6:
									player("What is that thing?");
									stage = 5;
									return true;
								}
								return false;
							}

						});
						break;
					case 5:
						player("Maybe, if I leave quitely, it won't notice me.");
						stage = 6;
						break;
					case 6:
						close();
						GameWorld.getPulser().submit(new Pulse(1, player, fluffy) {
							int counter = 0;

							@Override
							public boolean pulse() {
								switch (++counter) {
								case 2:
									Pathfinder.find(fluffy, cutscene.getBase().transform(41, 51, 1)).walk(fluffy);
									break;
								case 4:
									fluffy.face(player);
									fluffy.graphics(GRAPHIC);
									break;
								case 5:
									fluffy.sendChat("Raaaarw!");
									break;
								case 8:
									int x = player.getLocation().getX() - 4;
									int y = player.getLocation().getY();
									int height = 270;
									PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, x, y, height, 1, 100));
									PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, x + 1, y, height, 1, 100));
									interpreter.sendDialogue("A chilling, unnatural fear envelops you!");
									stage = 7;
									return true;
								}
								return false;
							}

						});
						break;
					case 7:
						player("It's coming to get me!");
						player.animate(SCARED_ANIMATION);
						player.sendChat("It's coming to get me!");
						stage = 8;
						break;
					case 8:
						end();
						GameWorld.getPulser().submit(new Pulse(2) {
							@Override
							public boolean pulse() {
								player.animate(Animation.create(827));
								return true;
							}

						});
						cutscene.stop(true);
						quest.setStage(player, 20);
						break;
					}
					break;
				case 50:// second cutscene.
					switch (stage) {
					case 0:
						fluffy.faceLocation(cutscene.getBase().transform(40, 53, 1));
						player("Okay, I have the spirit wolf pouch, so it can't hurt me...");
						stage = 1;
						break;
					case 1:
						player("...I hope.");
						stage = 2;
						break;
					case 2:
						int x = player.getLocation().getX() + 1;
						int y = player.getLocation().getY() - 1;
						int height = 300;
						PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, x, y, height, 1, 100));
						PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, x + 1000, y + 13, height, 1, 100));
						player.faceLocation(cutscene.getBase().transform(44, 50, 1));
						close();
						player("Oh, good. It's too busy gnawing to notice me.");
						stage = 3;
						break;
					case 3:
						fluffy.faceTemporary(player, 1);
						fluffy.sendChat("Raaaarw!");
						player("I spoke too soon!");
						stage = 4;
						break;
					case 4:
						player("I'll use the spirit wolf pouch. I hope this works.");
						stage = 5;
						break;
					case 5:
						PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.RESET, 0 + 1000, 0 + 13, 0, 1, 100));
						PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
						player.unlock();
						player.getLocks().lockMovement(10000000);
						player.getInterfaceManager().openTab(new Component(149));
						stage = -5;
						break;
					case -5:
						player.setAttribute("in-cutscene", cutscene);
						close();
						stage = 6;
						break;
					case 6:
						Location l = cutscene.getBase().transform(46, 50, 0);
						x = l.getX() + 1;
						y = l.getY() - 2;
						height = 440;
						player.lock();
						player.getLocks().lockMovement(100000);
						PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, x, y, height, 1, 95));
						PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, x + 1000, y + 17, height, 1, 95));
						wolf = player.getFamiliarManager().getFamiliar();
						GameWorld.getPulser().submit(new Pulse(1, player, fluffy) {
							int counter;

							@Override
							public boolean pulse() {
								switch (++counter) {
								case 1:
									wolf.sendChat("Grrrrrrrrrrrrrrrr...");
									break;
								case 2:
									fluffy.sendChat("Whiiiiiine!");
									break;
								case 4:
									player("Get that thing!");
									stage = 7;
									return true;
								}
								return false;
							}
						});
						break;
					case 7:
						player.getLocks().lockMovement(1000000);
						player.lock();
						close();
						fluffy.animate(SHUDDER_ANIMATION);
						if (player.getInventory().remove(HOWL_SCROLL)) {
							player.getDialogueInterpreter().setDialogue(this);
							player.getDialogueInterpreter().getDialogue().setStage(8);
							GameWorld.getPulser().submit(new Pulse(1, player, fluffy, wolf) {
								int counter;

								@Override
								public boolean pulse() {
									switch (++counter) {
									case 2:
										wolf.animate(Animation.create(8293));
										Projectile.magic(wolf, fluffy, 1333, 40, 36, 50, 5).send();
										player.getSkills().updateLevel(Skills.SUMMONING, -2, 0);
										break;
									case 5:
										fluffy.animate(DEATH_ANIMATION);
										break;
									case 12:
										fluffy.clear();
										wolf.face(player);
										player.face(player);
										player("Well, that certainly seems to have got rid of the giant", "wolpertinger.");
										stage = 8;
										player.getDialogueInterpreter().setDialogue(FluffyDialogue.this);
										player.getDialogueInterpreter().getDialogue().setStage(8);
										return true;
									}
									return false;
								}

							});
						}
						break;
					case 8:
						player("Come on, let's go tell Pikkupstix.");
						stage = 9;
						break;
					case 9:
						wolf.clear();
						player("They both vanished! I'm sure Pikkupstix will be able to", "explain this to me.");
						stage = 10;
						break;
					case 10:
						end();
						quest.setStage(player, 60);
						cutscene.stop(true);
						break;
					}
					break;
				}
				return true;
			}

			@Override
			public boolean close() {
				return super.close();
			}

			@Override
			public void end() {
				super.end();
				if (player.getFamiliarManager().hasFamiliar()) {
					player.getFamiliarManager().getFamiliar().dismiss();
				}
			}

			@Override
			public int[] getIds() {
				return new int[] { 392932 };
			}

		}
	}
}
