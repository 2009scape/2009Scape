package plugin.activity.magearena;

import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.combat.CombatSpell;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.combat.CombatSwingHandler;
import org.crandor.game.node.entity.combat.handlers.MagicSwingHandler;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.SpellBookManager.SpellBook;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.tools.RandomFunction;

/**
 * Handles the kolodion npc.
 * @author Vexia
 */
public final class KolodionNPC extends AbstractNPC {

	/**
	 * The combat swing handler.
	 */
	private static final CombatSwingHandler SWING_HANDLER = new MagicSwingHandler() {

		@Override
		public void impact(Entity entity, Entity victim, BattleState state) {
			super.impact(entity, victim, state);
			if (RandomFunction.random(10) < 4) {
				((KolodionNPC) entity).setRandomSpell();
			}
		}

	};

	/**
	 * The spell ids.
	 */
	private static final int[] SPELL_IDS = new int[] { 41, 42, 43 };

	/**
	 * The session.
	 */
	private final KolodionSession session;

	/**
	 * The kolodion type.
	 */
	private KolodionType type;

	/**
	 * If the fight has commenced.
	 */
	private boolean commenced;

	/**
	 * Constructs a new {@code KolodionNPC} {@code Object}.
	 */
	public KolodionNPC() {
		this(0, null, null);
	}

	/**
	 * Constructs a new {@code KolodionNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	public KolodionNPC(int id, Location location, final KolodionSession session) {
		super(id, location);
		this.setWalks(true);
		this.session = session;
		this.setRespawn(false);
		this.type = KolodionType.forId(id);
	}

	@Override
	public void init() {
		super.init();
		setRandomSpell();
	}

	@Override
	public void handleTickActions() {
		super.handleTickActions();
		if (session == null) {
			return;
		}
		if (!session.getPlayer().isActive()) {
			clear();
			return;
		}
		if (commenced && !getProperties().getCombatPulse().isAttacking()) {
			getProperties().getCombatPulse().attack(session.getPlayer());
		}
	}

	@Override
	public void startDeath(Entity killer) {
		if (killer == session.getPlayer()) {
			type.transform(this, session.getPlayer());
			return;
		}
		super.startDeath(killer);
	}

	/**
	 * Sets a random spell.
	 */
	public void setRandomSpell() {
		CombatSpell spell = (CombatSpell) SpellBook.MODERN.getSpell(SPELL_IDS[RandomFunction.random(SPELL_IDS.length)]);
		getProperties().setSpell(spell);
		getProperties().setAutocastSpell(spell);
	}

	@Override
	public CombatSwingHandler getSwingHandler(boolean swing) {
		return SWING_HANDLER;
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new KolodionNPC(id, location, null);
	}

	@Override
	public boolean isAttackable(Entity entity, CombatStyle style) {
		if (style != CombatStyle.MAGIC) {
			return false;
		}
		if (session == null) {
			return false;
		}
		if (session.getPlayer() != entity) {
			return false;
		}
		return true;
	}

	@Override
	public boolean canSelectTarget(Entity target) {
		if (target instanceof Player) {
			Player p = (Player) target;
			if (p != session.getPlayer()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 907, 908, 909, 910, 911 };
	}

	/**
	 * Gets the session.
	 * @return The session.
	 */
	public KolodionSession getSession() {
		return session;
	}

	/**
	 * Gets the commenced.
	 * @return The commenced.
	 */
	public boolean isCommenced() {
		return commenced;
	}

	/**
	 * Sets the commenced.
	 * @param commenced The commenced to set.
	 */
	public void setCommenced(boolean commenced) {
		this.commenced = commenced;
	}

	/**
	 * Gets the type.
	 * @return The type.
	 */
	public KolodionType getType() {
		return type;
	}

	/**
	 * Sets the type.
	 * @param type The type to set.
	 */
	public void setType(KolodionType type) {
		this.type = type;
	}

	/**
	 * Represents a kolodion type.
	 * @author Vexia
	 */
	public enum KolodionType {
		HUMAN(907, null, -1, "You must prove yourself... now!"), OGRE(908, null, 188, "This is only the beginning; you can't beat me!"), SPIDER(909, new Animation(5324), 190, "Foolish mortal; I am unstoppable."), GHOST(910, new Animation(715), 188, "Now you feel it.. The dark energy."), DEMON(911, new Animation(4623), 190, "Aaaaaaaarrgghhhh! The power!"), END(906, null, 188, null);

		/**
		 * The npc id.
		 */
		private final int npcId;

		/**
		 * The appear animation.
		 */
		private final Animation appearAnimation;

		/**
		 * The graphic id.
		 */
		private final int graphcId;

		/**
		 * The appeared message.
		 */
		private final String appearMessage;

		/**
		 * The spell ids.
		 */
		private final int[] spellIds;

		/**
		 * Constructs a new {@code KolodionType} {@code Object}.
		 * @param npcId the npc id.
		 * @param appearMessage the message.
		 */
		private KolodionType(int npcId, final Animation appearAnimation, final int graphicId, String appearMessage, int... spellIds) {
			this.npcId = npcId;
			this.appearMessage = appearMessage;
			this.appearAnimation = appearAnimation;
			this.graphcId = graphicId;
			this.spellIds = spellIds;
		}

		/**
		 * Transforms the new npc.
		 */
		public void transform(final KolodionNPC kolodion, final Player player) {
			final KolodionType newType = next();
			kolodion.lock();
			kolodion.getPulseManager().clear();
			kolodion.getWalkingQueue().reset();
			kolodion.getImpactHandler().setDisabledTicks(50);
			player.getSavedData().getActivityData().setKolodionBoss(newType.ordinal());
			if (newType == END) {
				player.lock();
			}
			player.lock(2);
			GameWorld.Pulser.submit(new Pulse(1, kolodion, player) {
				int counter;

				@Override
				public boolean pulse() {
					switch (++counter) {
					case 1:
						if (newType != GHOST) {
							kolodion.getAnimator().forceAnimation(kolodion.getProperties().getDeathAnimation());
						}
						break;
					case 3:
						if (newType == GHOST) {
							kolodion.getAnimator().forceAnimation(kolodion.getProperties().getDeathAnimation());
						}
						break;
					case 4:
						player.getPacketDispatch().sendPositionedGraphic(newType.getGraphcId(), 0, 0, kolodion.getLocation());
						if (newType.getAppearAnimation() != null) {
							kolodion.animate(newType.getAppearAnimation());
						}
						break;
					case 5:
						kolodion.unlock();
						kolodion.getAnimator().reset();
						kolodion.fullRestore();
						kolodion.setType(newType);
						kolodion.transform(newType.getNpcId());
						kolodion.getImpactHandler().setDisabledTicks(1);
						if (newType != END) {
							kolodion.getProperties().getCombatPulse().attack(player);
						}
						break;
					case 6:
						if (newType.getAppearMessage() != null) {
							kolodion.sendChat(newType.getAppearMessage());
						}
						if (newType == END) {
							return false;
						}
						return true;
					case 7:
						player.unlock();
						player.getSavedData().getActivityData().setKolodionStage(2);
						player.getProperties().setTeleportLocation(Location.create(2540, 4717, 0));
						return true;
					}
					return false;
				}

			});
		}

		/**
		 * Gets the kolodion type for the id.
		 * @param id the id.
		 * @return the kolodion type.
		 */
		public static KolodionType forId(int id) {
			for (KolodionType type : values()) {
				if (type.getNpcId() == id) {
					return type;
				}
			}
			return null;
		}

		/**
		 * Gets the next type.
		 * @return the type.
		 */
		public KolodionType next() {
			return values()[ordinal() + 1];
		}

		/**
		 * Gets the npcId.
		 * @return The npcId.
		 */
		public int getNpcId() {
			return npcId;
		}

		/**
		 * Gets the appearMessage.
		 * @return The appearMessage.
		 */
		public String getAppearMessage() {
			return appearMessage;
		}

		/**
		 * Gets the spellIds.
		 * @return The spellIds.
		 */
		public int[] getSpellIds() {
			return spellIds;
		}

		/**
		 * Gets the appearAnimation.
		 * @return The appearAnimation.
		 */
		public Animation getAppearAnimation() {
			return appearAnimation;
		}

		/**
		 * Gets the graphcId.
		 * @return The graphcId.
		 */
		public int getGraphcId() {
			return graphcId;
		}

	}

}
