package plugin.skill.slayer;

import core.game.container.impl.EquipmentContainer;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.BattleState;
import core.game.node.entity.combat.CombatStyle;
import core.game.node.entity.combat.CombatSwingHandler;
import core.game.node.entity.combat.equipment.SwitchAttack;
import core.game.node.entity.combat.handlers.DragonfireSwingHandler;
import core.game.node.entity.combat.handlers.MultiSwingHandler;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.entity.player.link.prayer.PrayerType;
import core.game.node.item.Item;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.plugin.InitializablePlugin;
import core.tools.RandomFunction;

/**
 * Handles the skeletal wyvern npc.
 * @author Vexia
 * @author Splinter
 * @version 1.0
 */
@InitializablePlugin
public final class SkeletalWyvernNPC extends AbstractNPC {

	/**
	 * The combat swing handler.
	 */
	private static final MultiSwingHandler COMBAT_HANDLER = new MultiSwingHandler(new SwitchAttack(CombatStyle.MELEE.getSwingHandler(), new Animation(2985)), new SwitchAttack(CombatStyle.RANGE.getSwingHandler(), new Animation(2989), new Graphics(499)), DragonfireSwingHandler.get(false, 54, new Animation(2988), new Graphics(501), null, null, false));

	/**
	 * The combat swing handler for far combat (5+ tile distance)
	 */
	private static final MultiSwingHandler COMBAT_HANDLER_FAR = new MultiSwingHandler(new SwitchAttack(CombatStyle.RANGE.getSwingHandler(), new Animation(2989), new Graphics(499)));

	/**
	 * Constructs a new {@code SkeletalWyvernNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	public SkeletalWyvernNPC(int id, Location location) {
		super(id, location);
	}

	/**
	 * Constructs a new {@code SkeletalWyvernNPC} {@code Object}.
	 */
	public SkeletalWyvernNPC() {
		super(0, null);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new SkeletalWyvernNPC(id, location);
	}

	@Override
	public void sendImpact(BattleState state) {
		if (state.getStyle() == CombatStyle.MAGIC && state.getVictim() != null && state.getVictim().isPlayer()) {
			Player p = state.getVictim().asPlayer();
			Item item = p.getEquipment().get(EquipmentContainer.SLOT_SHIELD);
			if (item != null && (item.getId() == 2890 || item.getId() == 9731 || item.getId() == 11283) && state.getEstimatedHit() > 10) {
				state.setEstimatedHit(RandomFunction.random(10));
			}
		}
		Player p = state.getVictim().asPlayer();
		Item item = p.getEquipment().get(EquipmentContainer.SLOT_SHIELD);
		if(state.getVictim().getLocation().getDistance(state.getAttacker().getLocation()) >= 5 
				&& state.getVictim().asPlayer().getPrayer().get(PrayerType.PROTECT_FROM_MAGIC) && (item.getId() == 2890 || item.getId() == 9731 || item.getId() == 11283)){
			state.setEstimatedHit(0);  
		}
	}

	@Override
	public CombatSwingHandler getSwingHandler(boolean swing) {
		if (this.getProperties().getCombatPulse().getVictim() != null && this.getProperties().getCombatPulse().getVictim().getLocation().getDistance(this.getLocation()) >= 5){
			return COMBAT_HANDLER_FAR;
		}
		return COMBAT_HANDLER;
	}

	@Override
	public void finalizeDeath(final Entity killer) {
		super.finalizeDeath(killer);
		if (killer instanceof Player) {
			final Player player = killer.asPlayer();
			player.getAchievementDiaryManager().finishTask(player,DiaryType.FALADOR, 2, 8);
		}
	}

	@Override
	public int[] getIds() {
		return Tasks.SKELETAL_WYVERN.getNpcs();
	}

}
