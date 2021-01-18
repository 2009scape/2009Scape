package core.game.node.entity.combat.equipment;

import core.game.node.entity.Entity;
import core.game.node.entity.combat.BattleState;
import core.game.node.entity.combat.CombatStyle;
import core.game.node.entity.combat.CombatSwingHandler;
import core.game.node.entity.impl.Projectile;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;

/**
 * Represents a possible attack the entity can switch to.
 * @author Emperor
 */
public class SwitchAttack {

	/**
	 * The combat swing handler.
	 */
	private CombatSwingHandler handler;

	/**
	 * The start graphic.
	 */
	private final Graphics startGraphic;

	/**
	 * The animation.
	 */
	private final Animation animation;

	/**
	 * The end graphic.
	 */
	private final Graphics endGraphic;

	/**
	 * The projectile.
	 */
	private final Projectile projectile;

	/**
	 * If the handler should be used for visualizing/handling combat.
	 */
	private boolean useHandler;
	
	/**
	 * The maximum hit of this attack.
	 */
	private int maximumHit = -1;

	/**
	 * Constructs a new {@code SwitchAttack} {@code Object}.
	 * @param handler The combat handler.
	 * @param animation The animation.
	 */
	public SwitchAttack(CombatSwingHandler handler, Animation animation) {
		this(handler, animation, null, null, null);
	}

	/**
	 * Constructs a new {@code SwitchAttack} {@code Object}.
	 * @param handler The combat handler.
	 * @param animation The animation.
	 * @param startGraphic The start graphic.
	 */
	public SwitchAttack(CombatSwingHandler handler, Animation animation, Graphics startGraphic) {
		this(handler, animation, startGraphic, null, null);
	}

	/**
	 * Constructs a new {@code SwitchAttack} {@code Object}.
	 * @param handler The combat handler.
	 * @param animation The animation.
	 * @param startGraphic The start graphic.
	 * @param endGraphic The end graphic.
     */
	public SwitchAttack(CombatSwingHandler handler, Animation animation, Graphics startGraphic, Graphics endGraphic) {
		this(handler, animation, startGraphic, endGraphic, null);
	}

	/**
	 * Constructs a new {@code SwitchAttack} {@code Object}
	 * @param handler the handler.
	 * @param projectile the projectile.
	 */
	public SwitchAttack(CombatSwingHandler handler, Animation animation, Projectile projectile) {
		this(handler, animation, null, null, projectile);
	}

	/**
	 * Constructs a new {@code SwitchAttack} {@code Object}
	 * @param style the style.
	 */
	public SwitchAttack(CombatStyle style) {
		this(style.getSwingHandler(), null, null, null);
	}

	/**
	 * Constructs a new {@code SwitchAttack} {@code Object}.
	 * @param handler The combat handler.
	 * @param animation The animation.
	 * @param startGraphic The start graphic.
	 * @param endGraphic The end graphic.
	 * @param projectile The projectile.
	 */
	public SwitchAttack(CombatSwingHandler handler, Animation animation, Graphics startGraphic, Graphics endGraphic, Projectile projectile) {
		this.handler = handler;
		this.animation = animation;
		this.startGraphic = startGraphic;
		this.endGraphic = endGraphic;
		this.projectile = projectile;
	}

	/**
	 * Checks if this attack can be selected.
	 * @param entity the entity.
	 * @param victim the victim.
	 * @param state the state.
	 * @return {@code True} if selected.
	 */
	public boolean canSelect(Entity entity, Entity victim, BattleState state) {
		return true;
	}

	/**
	 * Gets the combat swing handler.
	 * @return The swing handler.
	 */
	public CombatSwingHandler getHandler() {
		return handler;
	}

	/**
	 * Sets the combat swing handler.
	 * @param handler The swing handler.
	 */
	public void setHandler(CombatSwingHandler handler) {
		this.handler = handler;
	}

	/**
	 * Gets the style.
	 * @return The style.
	 */
	public CombatStyle getStyle() {
		return handler.getType();
	}

	/**
	 * Gets the startGraphic.
	 * @return The startGraphic.
	 */
	public Graphics getStartGraphic() {
		return startGraphic;
	}

	/**
	 * Gets the animation.
	 * @return The animation.
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * Gets the endGraphic.
	 * @return The endGraphic.
	 */
	public Graphics getEndGraphic() {
		return endGraphic;
	}

	/**
	 * Gets the projectile.
	 * @return The projectile.
	 */
	public Projectile getProjectile() {
		return projectile;
	}

	/**
	 * Gets the useHandler.
	 * @return The useHandler.
	 */
	public boolean isUseHandler() {
		return useHandler;
	}

	/**
	 * Sets the useHandler.
	 * @param useHandler The useHandler to set.
	 * @return This instance, for chaining.
	 */
	public SwitchAttack setUseHandler(boolean useHandler) {
		this.useHandler = useHandler;
		return this;
	}

	/**
	 * Gets the maximumHit value.
	 * @return The maximumHit.
	 */
	public int getMaximumHit() {
		return maximumHit;
	}

	/**
	 * Sets the maximumHit value.
	 * @param maximumHit The maximumHit to set.
	 */
	public SwitchAttack setMaximumHit(int maximumHit) {
		this.maximumHit = maximumHit;
		return this;
	}
}