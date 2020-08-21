package plugin.zone;

import plugin.dialogue.DialogueAction;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.content.global.action.DoorActionHandler;
import plugin.quest.tutorials.tutorialisland.TutorialSession;
import plugin.quest.tutorials.tutorialisland.TutorialStage;
import plugin.skill.SkillPulse;
import plugin.skill.Skills;
import plugin.skill.gather.SkillingResource;
import plugin.skill.gather.SkillingTool;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.Option;
import core.game.interaction.UseWithHandler;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.BattleState;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.zone.MapZone;
import core.game.world.map.zone.ZoneBorders;
import core.game.world.map.zone.ZoneBuilder;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the Wilderness Area Zone.
 * @author Empathy
 * @author Vexia
 *
 */
@InitializablePlugin
public class WildernessAreaZone extends MapZone implements Plugin<Object> {

	/**
	 * The list of players in the arena.
	 */
	private final List<Player> players = new ArrayList<>();

	/**
	 * Constructs a new {@code WildernessAreaZone} {@code Object}
	 */
	public WildernessAreaZone() {
		super("Wilderness Area Zone", true);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		PluginManager.definePlugins(new MandrithDialoguePlugin(), new PilesDialoguePlugin(), new PilesItemHandler(), new RuniteGolemNPC());
		return this;
	}

	@Override
	public boolean interact(Entity e, final Node node, Option option) {
		if (e instanceof Player) {
			Player player = e.asPlayer();
			switch (node.getId()) {
			case 42044:
				switch (option.getName()) {
				case "Open":
					if (player.getLocation().equals(Location.create(3184, 3945, 0))) {
						if (player.getInventory().contains(995, 7500)) {
							player.getDialogueInterpreter().sendOptions("Pay 7500 coins?", "Yes", "No");
							player.getDialogueInterpreter().addAction(new DialogueAction() {

								@Override
								public void handle(Player player, int buttonId) {
									switch (buttonId) {
									case 2:
										if (player.getInventory().remove(new Item(995, 7500))) {
											DoorActionHandler.handleDoor(player, node.asObject());
											player.sendMessage("You pay 7500 coins and enter the resource arena.");
										}
										break;
									}
								}

							});
							return true;
						}
						player.sendMessage("You do not have enough coins to enter the Arena.");
						return true;
					}
					DoorActionHandler.handleDoor(player, node.asObject());
					return true;
				case "Players-inside":
					if (player.getLocation().getY() < 3495) {
						player.sendMessage("All you see is the barren wasteland of the Wilderness.");
						return true;
					}
					String message = "You peek inside the gate and see no adventurers inside the arena.";
					if (players.size() > 0) {
						message = players.size() == 1 ? "You peek inside the gate and see 1 adventurer inside the arena." : "You peek inside the gate and see " + players.size() + " inside the arena.";
					}
					player.getDialogueInterpreter().sendDialogue(message);
					break;
				}
				break;
			case 8666:
				switch (option.getName()) {
				case "Mine":
					player.getPulseManager().run(new RuniteGolemMinePulse(player, node.asNpc()));
					return true;
				case "Prospect":
					player.sendMessages("You examine the rock for ores...", "This rock contains runite ore.");
					return true;
				}
				break;
			}
		}
		return super.interact(e, node, option);
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	@Override
	public void configure() {
		register(new ZoneBorders(3174, 3924, 3196, 3945));
	}

	@Override
	public boolean enter(Entity e) {
		if (e instanceof Player) {
			Player p = e.asPlayer();
			players.add(p);
		}
		return super.enter(e);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean leave(Entity e, boolean logout) {
		if  (e instanceof Player) {
			Player p = e.asPlayer();
			players.remove(p);
			if (logout && p.getLocation().getY() <= 3944) {
				p.setLocation(Location.create(3184, 3945, 0));
				return true;
			}
		}
		return super.leave(e, logout);
	}

	/**
	 * The mining of a runite golem.
	 * @author Vexia
	 *
	 */
	public class RuniteGolemMinePulse extends SkillPulse<NPC> {

		/**
		 * The skilling tool used.
		 */
		private SkillingTool tool;
		
		/**
		 * The ticks.
		 */
		private int ticks;

		/**
		 * Constructs a new @{Code RuniteGolemMinePulse} object.
		 * @param player The player.
		 * @param node The node.
		 */
		public RuniteGolemMinePulse(Player player, NPC node) {
			super(player, node);
		}
		
		@Override
		public void start() {
			resource = SkillingResource.RUNITE_ORE_0;
			if (node.getId() != 8666) {
				player.getPacketDispatch().sendMessage("This rock contains no ore.");
				return;
			}
			if (resource == null) {
				return;
			}
			if (TutorialSession.getExtension(player).getStage() == 35) {
				TutorialStage.load(player, 36, false);
			}
			super.start();
		}


		@Override
		public boolean checkRequirements() {
			if (node.getId() != 8666) {
				return false;
			}
			if (player.getSkills().getLevel(resource.getSkillId()) < resource.getLevel()) {
				player.getPacketDispatch().sendMessage("You need a " + Skills.SKILL_NAME[resource.getSkillId()] + " level of " + resource.getLevel() + " to mine this rock.");
				return false;
			}
			if ((tool = SkillingTool.getPickaxe(player)) == null) {
				player.getPacketDispatch().sendMessage("You do not have a pickaxe to use.");
				return false;
			}
			if (player.getInventory().freeSlots() < 1) {
				player.getDialogueInterpreter().sendDialogue("Your inventory is too full to hold any more runite ore.");
				return false;
			}
			return true;
		}

		@Override
		public void animate() {
			if (tool != null) {
				player.animate(tool.getAnimation());
			}
		}

		@Override
		public boolean reward() {
			if (++ticks % 4 != 0) {
				return false;
			}
			if (!checkReward()) {
				return false;
			}
			node.transform(8667);
			node.setAttribute("reward-tick", GameWorld.getTicks() + resource.getRespawnDuration());
			final Item item = new Item(resource.getReward());
		    player.getInventory().add(item);
			player.getSkills().addExperience(resource.getSkillId(), resource.getExperience(), true);
			return true;
		}

		/**
		 * Checks if the player gets rewarded.
		 * @return {@code True} if so.
		 */
		private boolean checkReward() {
			int skill = Skills.MINING;
			int level = 1 + player.getSkills().getLevel(skill) + player.getFamiliarManager().getBoost(skill);
			double hostRatio = Math.random() * (100.0 * resource.getRate());
			double clientRatio = Math.random() * ((level - resource.getLevel()) * (1.0 + tool.getRatio()));
			return hostRatio < clientRatio;
		}
	}

	/**
	 * Handles the runite golem npc.
	 * @author Vexia
	 *
	 */
	public class RuniteGolemNPC extends AbstractNPC {

		/**
		 * The respawn time.
		 */
		private int respawn = -1;

		/**
		 * Constructs a new @{Code RuniteGolemNPC} object.
		 * @param id The npc id.
		 * @param location The location.
		 */
		public RuniteGolemNPC(int id, Location location) {
			super(id, location);
		}

		/**
		 * Constructs a new @{Code RuniteGolemNPC} object.
		 */
		public RuniteGolemNPC() {
			super(-1, null);
		}

		@Override
		public AbstractNPC construct(int id, Location location, Object... objects) {
			return new RuniteGolemNPC(id, location);
		}
		
		@Override
		public void init() {
			super.init();
			setAggressive(false);
		}

		@Override
		public void sendImpact(BattleState state) {
			super.sendImpact(state);
			if (state.getEstimatedHit() > 16) {
				state.setEstimatedHit(16);
			}
		}

		@Override
		public void handleTickActions() {
			super.handleTickActions();
			if (isAggressive()) {
				setAggressive(false);
			}
			if (respawn != -1 && respawn < GameWorld.getTicks() && getId() > 8664) {
				transform(8664);
				unlock();
			}
			if (getId() == 8667) {
				int rewardTick = getAttribute("reward-tick", -1);
				if (rewardTick != -1 && rewardTick < GameWorld.getTicks()) {
					transform(8666);
				}
			}
		}

		@Override
		public void finalizeDeath(Entity killer) {
			respawn = GameWorld.getTicks() + 500;
			fullRestore();
			getProperties().setTeleportLocation(getLocation());
			setRespawnTick(0);
			setInvisible(false);
			transform(8666);
			lock();
			faceLocation(null);
			face(null);
		}

		@Override
		public int[] getIds() {
			return new int[] {8664};
		}


	}

	/**
	 * Handles the item on piles interaction.
	 * @author Vexia
	 *
	 */
	public static class PilesItemHandler extends UseWithHandler {

		/**
		 * The allowed items for piles.
		 */
		private static final int[] ALLOWED = new int[] {14937, 14939, 449, 447, 440, 444, 453, 451, 1515, 1513, 2349, 9467,2351,2355,2353,2357,2359,2361,2363};

		/**
		 * Constructs a new @{Code PilesItemHandler} object.
		 */
		public PilesItemHandler() {
			super();
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			addHandler(8663, NPC_TYPE, this);
			return this;
		}

		@Override
		public boolean handle(final NodeUsageEvent event) {
			final Player player = event.getPlayer();
			for (int itemId : ALLOWED) {
				if (event.getUsedItem().getId() == itemId) {
					final int amount = player.getInventory().getAmount(itemId);
					final int price = 50 * amount;
					if (!player.getInventory().contains(995, price)) {
						player.getDialogueInterpreter().sendDialogues(8663, FacialExpression.OLD_NORMAL, "I'll need 50 coins per item.");
						return true;
					}
					player.getDialogueInterpreter().sendOptions("Banknote " + amount + " x " + event.getUsedItem().getName()  + "?", "Yes - " + price + " gp", "Cancel");
					player.getDialogueInterpreter().addAction((player1, buttonId) -> {
                        if (player1.getInventory().remove(new Item(995, price)) && player1.getInventory().remove(new Item(event.getUsedItem().getId(), amount))) {
                            player1.getInventory().add(new Item(event.getUsedItem().getNoteChange(), amount));
                            player1.getDialogueInterpreter().sendItemMessage(event.getUsedItem().getId(), "Piles converts your items to banknotes.");
                        }
                    });
					return true;
				}
			}
			player.getDialogueInterpreter().sendDialogues(8663, FacialExpression.OLD_NORMAL, "Sorry, I wasn't expecting anyone to want to convert", "that sort of item, so I haven't any banknotes for it.");
			return true;
		}

		@Override
		public boolean isDynamic() {
			return true;
		}
	}

	/**
	 * Handles the Piles dialogue.
	 * @author Vexia
	 *
	 */
	public class PilesDialoguePlugin extends DialoguePlugin {

		/**
		 * Constructs a new @{Code PilesDialoguePlugin} object.
		 */
		public PilesDialoguePlugin() {
			/*
			 * empty.
			 */
		}

		/**
		 * Constructs a new @{Code PilesDialoguePlugin} object.
		 * @param player The player.
		 */ 
		public PilesDialoguePlugin(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new PilesDialoguePlugin(player);
		}

		@Override
		public boolean open(Object... args) {
			npc("Hello. I can convert items to banknotes, for 50 coins", "per item. Just hand me the items you'd like me to", "convert.");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				options("Who are you?", "Thanks.");
				stage++;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					player("Who are you?");
					stage = 10;
					break;
				case 2:
					player("Thanks.");
					stage = 2;
					break;
				}
				break;
			case 2:
				end();
				break;
			case 10:
				npc("I'm Piles. I lived in Draynor Village when I was", "young, where I saw three men working in the market,", "converting items to banknotes.");
				stage++;
				break;
			case 11:
				npc("Their names were Niles, Miles and Giles. I'm trying to", "be like them, so I've changed my name and started this", "business here.");
				stage++;
				break;
			case 12:
				player("Thanks.");
				stage = 2;
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] {8663};
		}

	}

	/**
	 * Handles the Mandrith dialogue.
	 * @author Empathy
	 *
	 */
	public class MandrithDialoguePlugin extends DialoguePlugin {

		/**
		 * 
		 * Constructs a new @{Code MandrithDialoguePlugin} object.
		 */
		public MandrithDialoguePlugin() {
			/*
			 * empty
			 */
		}

		/**
		 * 
		 * Constructs a new @{Code MandrithDialoguePlugin} object.
		 * @param player
		 */
		public MandrithDialoguePlugin(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new MandrithDialoguePlugin(player);
		}

		@Override
		public boolean open(Object... args) {
			player("Who are you and what is this place?" );
			stage = 1;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 1:
				npc("My name is Mandrith." );
				stage = 2;
				break;
			case 2:
				npc("I collect valuable resources and pawn off access to them", "to foolish adventurers, like yourself" );
				stage = 3;
				break;
			case 3:
				npc("You should take a look inside my arena. There's an", "abundance of valuable resources inside." );
				stage = 4;
				break;
			case 4:
				player("And I can take whatever I want?" );
				stage = 5;
				break;
			case 5:
				npc("It's all yours. All I ask is that you pay the upfront fee." );
				stage = 6;
				break;
			case 6:
				player("Will others be able to kill me inside?" );
				stage = 7;
				break;
			case 7:
				npc("Yes. These walls will only hold them back for so long." );
				stage = 8;
				break;
			case 8:
				player("You'll stop them though, right?" );
				stage = 9;
				break;
			case 9:
				npc("Haha! For the right price, I won't deny anyone access", "to my arena. Even if their intention is to kill you." );
				stage = 10;
				break;
			case 10:
				player("Right..." );
				stage = 11;
				break;
			case 11:
				npc("My arena holds many treasure that I've acquired at", "great expense, adventurer. Their bounty can come at a", "price." );
				stage = 12;
				break;
			case 12:
				npc("One day, adventurer, I will boast ownership of a much", "larger, much more dangerous arena than this. Take", "advantage of this offer while it lasts." );
				stage = 13;
				break;
			case 13:
				end();
				break;
			}
			return false;
		}

		@Override
		public int[] getIds() {
			return new int[] { 8662 };
		}
	}
}
