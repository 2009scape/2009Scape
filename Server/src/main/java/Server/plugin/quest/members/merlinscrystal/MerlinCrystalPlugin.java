package plugin.quest.members.merlinscrystal;

import core.cache.def.impl.ItemDefinition;
import core.cache.def.impl.ObjectDefinition;
import plugin.activity.ActivityManager;
import plugin.activity.CutscenePlugin;
import plugin.dialogue.DialogueInterpreter;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.content.global.action.ClimbActionHandler;
import core.game.content.global.action.DoorActionHandler;
import core.game.content.global.action.DropItemHandler;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.OptionHandler;
import core.game.interaction.UseWithHandler;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.ImpactHandler.HitsplatType;
import core.game.node.entity.impl.ForceMovement;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.plugin.Plugin;
import core.plugin.PluginManager;

/**
 * Handles the Merlin's Crystal Dialogue/Interactions.
 * @author Splinter
 */
public final class MerlinCrystalPlugin extends OptionHandler {

	/**
	 * The empty folder item.
	 */
	public final static Item REPELLENT = new Item(28);

	/**
	 * The empty folder item.
	 */
	public static final Item BUCKET = new Item(1925);

	/**
	 * The empty folder item.
	 */
	public static final Item BUCKET_OF_WAX = new Item(30);

	/**
	 * The empty folder item.
	 */
	public static final Item BLACK_CANDLE = new Item(38);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		PluginManager.definePlugin(new MerlinCrystalDialogue());
		PluginManager.definePlugin(new MerlinCrystalItemHandler());
		ObjectDefinition.forId(63).getConfigurations().put("option:hide-in", this);
		ObjectDefinition.forId(40026).getConfigurations().put("option:climb-up", this);
		ObjectDefinition.forId(72).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(71).getConfigurations().put("option:open", this);
		ItemDefinition.forId(530).getConfigurations().put("option:drop", this);
		ObjectDefinition.forId(62).getConfigurations().put("option:smash", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final Quest quest = player.getQuestRepository().getQuest("Merlin's Crystal");
		final int id = node instanceof Item ? ((Item) node).getId() : node instanceof GameObject ? ((GameObject) node).getId() : ((NPC) node).getId();
		switch (id) {
		case 62:
			if (quest.getStage(player) == 90 && player.getEquipment().contains(35, 1)) {
				player.sendMessages("You attempt to smash the crystal...", "... and it shatters under the force of Excalibur!");
				player.animate(Animation.create(390));
				player.lock();
				ObjectBuilder.remove(node.asObject(), 60);
				quest.setStage(player, 99);
				final NPC merlin = NPC.create(249, Location.create(2767, 3493, 2));
				merlin.init();
				player.getDialogueInterpreter().open(merlin.getId(), merlin);
				GameWorld.Pulser.submit(new Pulse(100, player, merlin) {

					@Override
					public boolean pulse() {
						return true;
					}

					@Override
					public void stop() {
						super.stop();
						if (merlin != null && merlin.isActive()) {
							merlin.clear();
						}
					}

				});
			} else {
				player.sendMessage("You need something stronger to smash it.");
			}
			return true;
		case 63:
			player.getDialogueInterpreter().open("merlin_dialogue");
			return true;
		case 71:
		case 72:
			if (quest.getStage(player) <= 40) {
				player.getDialogueInterpreter().sendDialogue("The door is barred tightly shut.");
			} else {
				DoorActionHandler.handleAutowalkDoor(player, node.asObject());
			}
			return true;
		case 530:
			if (player.getLocation().equals(new Location(2780, 3515, 0)) && quest.getStage(player) == 80) {
				if (player.getInventory().contains(32, 1) && player.getAttribute("thrantax_npc") == null) {
					ForceMovement.run(player, player.getLocation().transform(-2, 0, 0)).setDirection(Direction.EAST);
					NPC npc = new ThrantaxNPC(player, Location.create(2780, 3515, 0));
					npc.init();
					npc.setRespawn(false);
					player.setAttribute("thrantax_npc", npc);
					npc.setAttribute("thrantax_owner", player.getUsername());
					player.getDialogueInterpreter().open("thrantax_dialogue", 34, 248);
				} else {
					player.getDialogueInterpreter().sendDialogue("You'll need to light the black candle before the spirit", "will appear.");
				}
				return true;
			} else {
				DropItemHandler.drop(player, node.asItem());
			}
			return true;
		case 40026:
			if (player.getLocation().withinDistance(Location.create(3013, 3245, 0)) && quest.getStage(player) == 60 && player.getAttribute("beggar_npc") == null) {
				NPC npc = new BeggarNPC(player, Location.create(3012, 3248, 1));
				npc.init();
				player.setAttribute("beggar_npc", 1);
				npc.setAttribute("beggar_owner", player.getUsername());
				ClimbActionHandler.climb(player, new Animation(828), Location.create(3013, 3245, 1));
			} else {
				ClimbActionHandler.climbLadder(player, (GameObject) node, option);
				return true;
			}
			return true;
		}
		return true;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		return null;
	}

	/**
	 * Handles the dialogue
	 * @author Splinter
	 */
	public final class MerlinCrystalDialogue extends DialoguePlugin {

		/**
		 * Constructs a new {@code JogreCavernDialogue} {@code Object}.
		 */
		public MerlinCrystalDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code JogreCavernDialogue} {@code Object}.
		 * @param player the player.
		 */
		public MerlinCrystalDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new MerlinCrystalDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			final Quest quest = player.getQuestRepository().getQuest("Merlin's Crystal");
			if (quest.getStage(player) == 99) {
				npc = (NPC) args[0];
				npc("Thank you! Thank you! Thank you!");
				stage = 900;
				return true;
			}
			if (quest.getStage(player) == 40 && player.getLocation().withinDistance(new Location(2801, 3442, 0), 2)) {
				player.getDialogueInterpreter().sendDialogue("The crate is empty. It's just about big enough to hide inside.");
				stage = 0;
			}
			if (quest.getStage(player) == 50 && player.getLocation().withinDistance(new Location(2770, 3402, 2), 8)) {
				interpreter.sendDialogues(248, FacialExpression.SCARED, "STOP! Please... spare my son.");
				player.lock();
				stage = 34;
			}
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 900:
				npc("It's not fun being trapped in a giant crystal!");
				stage++;
				break;
			case 901:
				npc("Go speak to King Arthur, I'm sure he'll reward you!");
				stage++;
				break;
			case 902:
				interpreter.sendDialogue("You have set Merlin free. Now talk to King Arthur.");
				stage++;
				break;
			case 903:
				end();
				player.unlock();
				npc.clear();
				break;
			case 0:
				player.getDialogueInterpreter().sendOptions("Hide inside the crate?", "Yes.", "No.");
				stage++;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogue("You climb inside the crate and wait.");
					player.lock();
					player.animate(new Animation(827));
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogue("You leave the empty crate alone.");
					stage = 200;
					break;
				}
				break;
			case 10:
				ActivityManager.start(player, "Merlin Crate Cutscene", false, this);
				stage = 11;
				break;
			case 11:
				interpreter.sendDialogue("And wait...");
				stage = 13;
				break;
			case 13:
				interpreter.sendDialogue("You hear voices outside the crate.", "<col=08088A>Is this your crate Arnhein?</col>");
				stage = 14;
				break;
			case 14:
				interpreter.sendDialogue("<col=8A0808>Yeah I think so. Pack it aboard as soon as you can.</col>", "<col=8A0808>I'm on a tight schedule for deliveries!</col>");
				stage = 15;
				break;
			case 15:
				interpreter.sendDialogue("You feel the crate being lifted", "<col=08088A>Oof. Wow, this is pretty heavy!</col>", "<col=08088A>I never knew candles weighed so much!</col>");
				stage = 16;
				break;
			case 16:
				interpreter.sendDialogue("<col=8A0808>Quit your whining, and stow it in the hold.</col>");
				stage = 17;
				break;
			case 17:
				interpreter.sendDialogue("You feel the crate being put down inside the ship.");
				stage = 18;
				break;
			case 18:
				interpreter.sendDialogue("You wait...");
				stage = 19;
				break;
			case 19:
				interpreter.sendDialogue("And wait...");
				stage = 20;
				break;
			case 20:
				interpreter.sendDialogue("<col=8A0808>Casting off!</col>");
				stage = 21;
				break;
			case 21:
				interpreter.sendDialogue("You feel the ship start to move.");
				stage = 22;
				break;
			case 22:
				interpreter.sendDialogue("Feels like you're now out at sea.");
				stage = 23;
				break;
			case 23:
				interpreter.sendDialogue("The ship comes to a stop.");
				stage = 24;
				break;
			case 24:
				interpreter.sendDialogue("<col=8A0808>Unload Mordred's deliveries onto the jetty.</col>", "<col=08088A>Aye-aye cap'n!</col>");
				stage = 25;
				break;
			case 25:
				interpreter.sendDialogue("You feel the crate being lifted.");
				stage = 26;
				break;
			case 26:
				interpreter.sendDialogue("You can hear someone mumbling outside the crate.", "<col=08088A>...stupid Arhein... making me... candles...", "<col=08088A>never weigh THIS much....hurts....union about this!...");
				stage = 27;
				break;
			case 27:
				interpreter.sendDialogue("<col=08088A>...if....MY ship be different!...", "<col=08088A>stupid Arhein...");
				stage = 28;
				break;
			case 28:
				interpreter.sendDialogue("You feel the crate being put down.");
				stage = 29;
				break;
			case 29:
				player.getDialogueInterpreter().sendOptions("Get out of the crate?", "Yes.", "No.");
				stage = 30;
				break;
			case 30:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogue("You climb out of the crate.");
					stage = 31;
					break;
				case 2:
					interpreter.sendDialogue("You decide to stay in the crate.");
					stage = 32;
					break;
				}
				break;
			case 31:
				CutscenePlugin p = player.getAttribute("cutscene", null);
				if (p != null) {
					p.stop(false);
				}
				if (player.getQuestRepository().getQuest("Merlin's Crystal").getStage(player) == 30) {
					player.getQuestRepository().getQuest("Merlin's Crystal").setStage(player, 40);
				}
				player.unlock();
				player.getProperties().setTeleportLocation(Location.create(2778, 3401, 0));
				end();
				break;
			case 32:
				interpreter.sendDialogue("You wait...");
				stage = 33;
				break;
			case 33:
				interpreter.sendDialogue("And wait...");
				stage = 29;
				break;
			case 34:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Tell me how to untrap Merlin and I might.");
				stage = 35;
				break;
			case 35:
				interpreter.sendDialogues(248, FacialExpression.HALF_GUILTY, "You have guessed correctly that I'm responsible for", "that.");
				stage = 36;
				break;
			case 36:
				interpreter.sendDialogues(248, FacialExpression.HALF_GUILTY, "I suppose I can live with that fool Merlin being loose", "for the sake of my son.");
				stage = 37;
				break;
			case 37:
				interpreter.sendDialogues(248, FacialExpression.HALF_GUILTY, "Setting him free won't be easy though.");
				stage = 38;
				break;
			case 38:
				interpreter.sendDialogues(248, FacialExpression.HALF_GUILTY, "You will need to find a magic symbol as close to the", "crystal as you can find.");
				stage = 39;
				break;
			case 39:
				interpreter.sendDialogues(248, FacialExpression.HALF_GUILTY, "You will then need to drop some bat's bones on the", "magic symbol while holding a lit black candle.");
				stage = 40;
				break;
			case 40:
				interpreter.sendDialogues(248, FacialExpression.HALF_GUILTY, "This will summon a mighty spirit named Thrantax.");
				stage = 41;
				break;
			case 41:
				interpreter.sendDialogues(248, FacialExpression.HALF_GUILTY, "You will need to bind him with magic words.");
				stage = 42;
				break;
			case 42:
				interpreter.sendDialogues(248, FacialExpression.HALF_GUILTY, "Then you will need the sword Excalibur with which the", "spell was bound in order to shatter the crystal.");
				stage = 43;
				break;
			case 43:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Where can I find the sword Excalibur?");
				stage = 44;
				break;
			case 44:
				interpreter.sendDialogues(248, FacialExpression.HALF_GUILTY, "The lady of the lake has it. I don't know if she'll give it", "to you though, she can be rather temperamental.");
				stage = 45;
				break;
			case 45:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What are these magic words?");
				stage = 46;
				break;
			case 46:
				interpreter.sendDialogues(248, FacialExpression.HALF_GUILTY, "You will find the magic words at the base of one of the", "chaos altars.");
				stage = 47;
				break;
			case 47:
				interpreter.sendDialogues(248, FacialExpression.HALF_GUILTY, "Which chaos altar I cannot remember.");
				stage = 48;
				break;
			case 48:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Okay, I can do all of that.");
				stage = 49;
				break;
			case 49:
				NPC n = player.getAttribute("morgan", null);
				if (n != null) {
					n.graphics(Graphics.create(86));
					n.clear();
				}
				interpreter.sendDialogue("Morgan Le Faye vanishes.");
				player.unlock();
				stage = 200;
				break;
			case 200:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { DialogueInterpreter.getDialogueKey("merlin_dialogue"), 248, 249 };
		}
	}

	/**
	 * Handles the beggar NPC.
	 * @author Vexia
	 */
	public final class BeggarNPC extends NPC {

		/**
		 * The player.
		 */
		private final Player player;

		/**
		 * Constructs a new {@code AnimatedArmour} {@code Object}.
		 * @param player The player.
		 * @param location The location to spawn.
		 */
		protected BeggarNPC(Player player, Location location) {
			super(252, location);
			this.player = player;
		}

		@Override
		public boolean isHidden(final Player player) {
			if (player.getQuestRepository().getQuest("Merlin's Crystal").getStage(player) == 60 && this.getAttribute("beggar_owner", "").equals(player.getUsername())) {
				return false;
			}
			return true;
		}

		@Override
		public void handleTickActions() {
			if (player != null && (!player.isActive())) {
				clear();
			}
		}

		@Override
		public void init() {
			super.init();
		}

		@Override
		public void clear() {
			super.clear();
			player.getHintIconManager().clear();
		}
	}

	/**
	 * Handles the thrantax npc.
	 * @author Vexia
	 */
	public final class ThrantaxNPC extends NPC {

		/**
		 * The player.
		 */
		private final Player player;

		/**
		 * Constructs a new {@code AnimatedArmour} {@code Object}.
		 * @param player The player.
		 * @param location The location to spawn.
		 */
		protected ThrantaxNPC(Player player, Location location) {
			super(238, location);
			this.player = player;
		}

		@Override
		public boolean isHidden(final Player player) {
			if (player.getQuestRepository().getQuest("Merlin's Crystal").getStage(player) == 80 && this.getAttribute("thrantax_owner", "").equals(player.getUsername())) {
				return false;
			}
			return true;
		}

		@Override
		public void init() {
			super.init();
		}

		@Override
		public void handleTickActions() {
			if (player != null && !player.isActive() || !player.getLocation().withinDistance(getLocation(), 20)) {
				clear();
			}
			super.handleTickActions();
		}

		@Override
		public void finalizeDeath(Entity entity) {
			if (entity instanceof Player) {
				Player p = entity.asPlayer();
				super.finalizeDeath(p);
			}
		}

		@Override
		public boolean canAttack(Entity entity) {
			if (getAttribute("thrantax_owner", entity) == entity) {
				return true;
			}
			return false;
		}

		@Override
		public void clear() {
			super.clear();
			player.getHintIconManager().clear();
		}
	}

	/**
	 * Used for item handling during the quest.
	 * @author Adam Rodrigues
	 */
	public static class MerlinCrystalItemHandler extends UseWithHandler {

		/**
		 * The object id of the beehives
		 */
		private static final int[] OBJECTS = new int[] { 68 };

		/**
		 * Constructs a new {@Code MerlinCrystalItemHandler} {@Code
		 *  Object}
		 */
		public MerlinCrystalItemHandler() {
			super(REPELLENT.getId(), BUCKET.getId());
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			for (int id : OBJECTS) {
				addHandler(id, OBJECT_TYPE, this);
			}
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			Player player = event.getPlayer();
			Item useditem = event.getUsedItem();
			final GameObject object = (GameObject) event.getUsedWith();
			if (player.getAttribute("cleared_beehives") != null && useditem.getId() == REPELLENT.getId() && object.getId() == 68) {
				player.getDialogueInterpreter().sendDialogue("You have already cleared the hive of its bees.", "You can now safely collect wax from the hive.");
			}
			if (useditem.getId() == REPELLENT.getId() && object.getId() == 68 && player.getAttribute("cleared_beehives") == null) {
				player.getDialogueInterpreter().sendDialogue("You pour insect repellant on the beehive. You see the bees leaving the", "hive.");
				player.getPacketDispatch().sendMessage("Suddenly the bees fly out of the hive and sting you.");
				player.getImpactHandler().manualHit(player, 2, HitsplatType.NORMAL);
				player.setAttribute("cleared_beehives", 1);
			}
			if (useditem.getId() == BUCKET.getId() && player.getAttribute("cleared_beehives") != null) {
				player.getDialogueInterpreter().sendDialogue("You get some wax from the beehive.");
				player.getInventory().remove(new Item(BUCKET.getId(), 1));
				player.getInventory().add(new Item(BUCKET_OF_WAX.getId(), 1));
			} else if (useditem.getId() == BUCKET.getId() && player.getAttribute("cleared_beehives") == null) {
				player.getDialogueInterpreter().sendDialogue("It would be dangerous to stick the bucket into the hive while", "the bees are still in it. Perhaps you can clear them out", "somehow.");
			}
			return true;
		}
	}
}
