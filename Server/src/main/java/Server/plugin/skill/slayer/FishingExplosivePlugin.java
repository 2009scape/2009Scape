package plugin.skill.slayer;

import core.cache.def.impl.ObjectDefinition;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.OptionHandler;
import core.game.interaction.UseWithHandler;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.CombatStyle;
import core.game.node.entity.combat.ImpactHandler.HitsplatType;
import core.game.node.entity.impl.Projectile;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.HintIconManager;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.tools.RandomFunction;

/**
 * Represents the plugin used to handle the fishing expolosive on a omnious
 * fishing spot.
 * @author 'Vexia
 */
@InitializablePlugin
public final class FishingExplosivePlugin extends OptionHandler {

	/**
	 * Represents the ominous fishing spot ids.
	 */
	private final static int[] IDS = new int[] { 10087, 10088, 10089 };

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (int id : IDS) {
			ObjectDefinition.forId(id).getHandlers().put("option:lure", this);
			ObjectDefinition.forId(id).getHandlers().put("option:bait", this);
		}
		new FishingExplosiveHandler().newInstance(arg);
		new MogreNPC().newInstance(arg);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.getPacketDispatch().sendMessage("Something seems to have scared all the fishes away...");
		return true;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		return node.getLocation();
	}

	/**
	 * Represents the handler for the fishing expolsive on a fishing spot.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class FishingExplosiveHandler extends UseWithHandler {

		/**
		 * Represents the throwing animation.
		 */
		private static final Animation ANIMATION = new Animation(385);

		/**
		 * Represents the splashing graphics.
		 */
		private static final Graphics SPLASH_GRAPHIC = new Graphics(68);

		/**
		 * Represents the npc mogre id.
		 */
		private static final int MOGRE_ID = 114;

		/**
		 * Represents the messages used to send for the mogres.
		 */
		private static final String[] MESSAGES = new String[] { "Da boom-boom kill all da fishies!", "I smack you good!", "Smash stupid human!", "Tasty human!", "Human hit me on the head!", "I get you!", "Human scare all da fishies!" };

		/**
		 * Constructs a new {@code FishingExplosiveHandler} {@code Object}.
		 */
		public FishingExplosiveHandler() {
			super(6664, 12633);
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			for (int i : IDS) {
				addHandler(i, OBJECT_TYPE, this);
			}
			return this;
		}

		@Override
		public boolean handle(final NodeUsageEvent event) {
			final Player player = event.getPlayer();
			if (player.getAttributes().containsKey("hasMogre"))  {
				player.getDialogueInterpreter().sendDialogues(player, null, "Sinister as that fishing spot is, why would I want to", "explode it?");
				return true;
			}
			if (!player.getInventory().remove(new Item(event.getUsedItem().getId(), 1))) {
				return true;
			}
			final int delay = (int) (2 + (player.getLocation().getDistance(event.getUsedWith().getLocation())) * 0.5);
			player.animate(ANIMATION);
			player.getPacketDispatch().sendMessage("You hurl the shuddering vial into the water...");
			sendProjectile(player, (GameObject) event.getUsedWith());
			GameWorld.getPulser().submit(new Pulse(delay, player) {
				@Override
				public boolean pulse() {
					Direction dir = event.getUsedWith().getDirection();
					NPC mogre = NPC.create(MOGRE_ID, event.getUsedWith().getLocation().transform(dir.getStepX() << 1, dir.getStepY() << 1, 0));
					mogre.init();
					mogre.moveStep();
					mogre.setRespawn(false);
					mogre.getProperties().getCombatPulse().attack(player);
					mogre.setAttribute("player", player);
					mogre.sendChat(MESSAGES[RandomFunction.random(MESSAGES.length)]);
					HintIconManager.registerHintIcon(player, mogre);
					if (event.getUsedItem().getId() == 12633) {
						mogre.getImpactHandler().manualHit(player, 15, HitsplatType.NORMAL);
					}
					player.setAttribute("hasMogre", true);
					mogre.graphics(SPLASH_GRAPHIC);
					player.getPacketDispatch().sendMessage("...and a Mogre appears!");
					return true;
				}
			});
			return true;
		}

		/**
		 * Method used to send the fishign explosive projectile.
		 * @param player the player.
		 * @param object the object.
		 */
		private void sendProjectile(final Player player, final GameObject object) {
			Projectile p = Projectile.create(player, null, 49, 30, 20, 30, Projectile.getSpeed(player, object.getLocation()));
			p.setEndLocation(object.getLocation());
			p.send();
		}

		@Override
		public Location getDestination(Player player, Node with) {
			return player.getLocation();
		}
	}

	/**
	 * Represents a mogre npc.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class MogreNPC extends AbstractNPC {

		/**
		 * Constructs a new {@code MogreNPC} {@code Object}.
		 * @param id the id.
		 * @param location the location.
		 */
		public MogreNPC(int id, Location location) {
			super(id, location, true);
		}

		/**
		 * Constructs a new {@code MogreNPC} {@code Object}.
		 */
		public MogreNPC() {
			super(0, null);
		}

		@Override
		public void tick() {
			super.tick();
			final Player pl = getAttribute("player", null);
			if (pl == null || pl.getLocation().getDistance(getLocation()) > 15) {
				clear();
			}
		}

		@Override
		public void clear() {
			super.clear();
			final Player pl = getAttribute("player", null);
			if (pl != null) {
				pl.removeAttribute("hasMogre");
			}
		}

		@Override
		public boolean isAttackable(final Entity entity, CombatStyle style) {
			final Player pl = getAttribute("player", null);
			return pl != null && pl == entity && super.isAttackable(entity, style);
		}

		@Override
		public void finalizeDeath(final Entity killer) {
			super.finalizeDeath(killer);
			if (killer instanceof Player) {
				final Player player = killer.asPlayer();
				player.getAchievementDiaryManager().finishTask(player,DiaryType.FALADOR, 2, 7);
			}
		}

		@Override
		public AbstractNPC construct(int id, Location location, Object... objects) {
			return new MogreNPC(id, location);
		}

		@Override
		public int[] getIds() {
			return new int[] { 114 };
		}

	}
}
