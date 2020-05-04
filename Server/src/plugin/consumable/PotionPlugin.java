package plugin.consumable;

import org.crandor.game.content.global.consumable.Consumables;
import org.crandor.game.content.global.consumable.Potion;
import org.crandor.game.content.global.consumable.PotionEffect;
import org.crandor.game.content.global.consumable.PotionEffect.Effect;
import org.crandor.game.content.skill.SkillBonus;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.state.EntityState;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.PulseRunner;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the class of all potion plugins.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class PotionPlugin implements Plugin<Object> {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		new AntipoisonPotion().newInstance(arg);
		new CombatPotion().newInstance(arg);
		new EnergyPotion().newInstance(arg);
		new FishingPotion().newInstance(arg);
		new PrayerPotion().newInstance(arg);
		new PrayerMix().newInstance(arg);
		new RelicymsBalm().newInstance(arg);
		new RestorePotion().newInstance(arg);
		new SaradominBrew().newInstance(arg);
		new SummoningPotion().newInstance(arg);
		new SuperantiPoison().newInstance(arg);
		new SuperenergyPotion().newInstance(arg);
		new SuperestorePlugin().newInstance(arg);
		new ZamorakBrew().newInstance(arg);
		new AntifirePotion().newInstance(arg);
		new SuperOSRSPotionPlugin().newInstance(arg);
		//new SuperCombatPotionPlugin().newInstance(arg);
		return this;
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	/**
	 * Represents the plugin used for the anti poison potions.
	 * @author 'Vexia
	 */
	public final class AntipoisonPotion extends Potion {

		/**
		 * Constructs a new {@code AntipoisonPlugin} {@code Object}.
		 */
		public AntipoisonPotion() {
			/**
			 * empty.
			 */
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			Consumables.add(new AntipoisonPotion(PotionEffect.ANTI_POISON));
			Consumables.add(new AntipoisonPotion(PotionEffect.STRONG_ANTI_POISON));
			Consumables.add(new AntipoisonPotion(PotionEffect.SUPER_STRONG_ANTI_POISON));
			return this;
		}

		/**
		 * Constructs a new {@code AntipoisonPlugin} {@code Object}.
		 * @param effect the effect.
		 */
		public AntipoisonPotion(PotionEffect effect) {
			super(effect);
		}

		@Override
		public void effect(final Player player, final Item item) {
			player.setAttribute("poison:immunity", GameWorld.getTicks() + getTicks(item));
			player.getStateManager().remove(EntityState.POISONED);
		}

		/**
		 * Gets the amount of ticks for the potion.
		 * @param item the item.
		 * @return the ticks.
		 */
		private final int getTicks(Item item) {
			if (!item.getName().contains("+")) {
				return 143;
			}
			if (!item.getName().contains("++")) {
				return 863;
			}
			return 2000;
		}

	}

	/**
	 * Represents the potion used to raise attack and strength by 8%.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class CombatPotion extends Potion {

		/**
		 * Constructs a new {@code CombatPotionPlugin} {@code Object}.
		 */
		public CombatPotion() {
			/**
			 * empty.
			 */
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			Consumables.add(new CombatPotion(new Effect("Combat potion", new int[] { 9739, 9741, 9743, 9745 }, null)));
			Consumables.add(new CombatPotion(new Effect("Overload", new int[] { 14784, 14785, 14786, 14787 }, null)));
			return this;
		}

		/**
		 * Constructs a new {@code CombatPotionPlugin} {@code Object}.
		 * @param effect the effect.
		 */
		public CombatPotion(Effect effect) {
			super(effect);
			super.emptyItem = VIAL;
		}

		@Override
		public void effect(final Player player, final Item item) {
			if(item.getName().contains("Overload") && player.getZoneMonitor().isInZone("Wilderness")){
				player.sendMessage("The potion has no effect in the wilderness.");
				return;
			}
			if (item.getName().contains("Overload")) {
				effect(player, new SkillBonus(Skills.ATTACK, 0.22, 5));
				effect(player, new SkillBonus(Skills.STRENGTH, 0.22, 5));
				effect(player, new SkillBonus(Skills.DEFENCE, 0.22, 5));
				effect(player, new SkillBonus(Skills.RANGE, 0.22, 2));
				effect(player, new SkillBonus(Skills.MAGIC, 0.0, 7));
				Pulse pulse = player.getAttribute("overload");
				if (pulse != null) {
					pulse.stop();
				}
				GameWorld.Pulser.submit(pulse = new Pulse(2, player) {
					int count = 0;

					@Override
					public boolean pulse() {
						if(player.getZoneMonitor().isInZone("Wilderness")){
							return true;
						}
						if (++count < 6) {
							player.animate(Animation.create(3170));
						} else if (count == 6) {
							setTicksPassed(10);
							setDelay(25);
						} else if (count == 26) {
							player.getPacketDispatch().sendMessage("Your overload potion has worn off.");
							player.removeAttribute("overload");
							for (int i = 0; i < 7; i++) {
								if (i == 5 || i == 3) {
									continue;
								}
								if (player.getSkills().getLevel(i) > player.getSkills().getStaticLevel(i)) {
									player.getSkills().setLevel(i, player.getSkills().getStaticLevel(i));
								}
							}
							return true;
						} else {
							effect(player, new SkillBonus(Skills.ATTACK, 0.22, 5));
							effect(player, new SkillBonus(Skills.STRENGTH, 0.22, 5));
							effect(player, new SkillBonus(Skills.DEFENCE, 0.22, 5));
							effect(player, new SkillBonus(Skills.RANGE, 0.22, 2));
							effect(player, new SkillBonus(Skills.MAGIC, 0.0, 7));
						}
						return false;
					}
				});
				player.setAttribute("overload", pulse);
				remove(player, item);
				return;
			} else if (!(item.getName().contains("Overload"))){
				effect(player, new SkillBonus(Skills.ATTACK, 0.15));
				effect(player, new SkillBonus(Skills.STRENGTH, 0.15));
			}
		}
	}

	/**
	 * Represents the energy potion plugin.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class EnergyPotion extends Potion {

		/**
		 * Constructs a new {@code PrayerPotionPlugin} {@code Object}.
		 */
		public EnergyPotion() {
			/**
			 * empty.
			 */
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			Consumables.add(new EnergyPotion(new Effect("Energy potion", new int[] { 3008, 3010, 3012, 3014 }, null)));
			return this;
		}

		/**
		 * Constructs a new {@code PrayerPotionPlugin} {@code Object}.
		 * @param effect the effect.
		 */
		public EnergyPotion(Effect effect) {
			super(effect);
			super.emptyItem = VIAL;
		}

		@Override
		public void consume(final Item item, final Player player) {
			player.getSkills().heal(3);
			player.getSettings().updateRunEnergy(-(100 * 0.10));
			super.consume(item, player);
		}

	}

	/**
	 * Represents the fishing potion plugin.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public class FishingPotion extends Potion {

		/**
		 * Constructs a new {@code FishingPotionPlugin} {@code Object}.
		 */
		public FishingPotion() {
			/**
			 * empty.
			 */
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			Consumables.add(new FishingPotion(new Effect("Fishing potion", new int[] { 2438, 151, 153, 155 }, null)));
			return this;
		}

		/**
		 * Constructs a new {@code FishingPotionPlugin} {@code Object}.
		 * @param effect the effect.
		 */
		public FishingPotion(Effect effect) {
			super(effect);
			super.emptyItem = VIAL;
		}

		@Override
		public void effect(final Player player, final Item item) {
			player.getSkills().updateLevel(Skills.FISHING, 3, player.getSkills().getStaticLevel(Skills.FISHING) + 3);
		}

	}

	/**
	 * Represents the anti-poison plugin.
	 * @author 'Vexia
	 * @date 23/12/2013
	 */
	public class PrayerPotion extends Potion {

		/**
		 * Constructs a new {@code PrayerPotionPlugin} {@code Object}.
		 */
		public PrayerPotion() {
			/**
			 * empty.
			 */
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			Consumables.add(new PrayerPotion(new Effect("Prayer potion", new int[] { 2434, 139, 141, 143 }, null)));
			return this;
		}

		/**
		 * Constructs a new {@code PrayerPotionPlugin} {@code Object}.
		 * @param effect the effect.
		 */
		public PrayerPotion(Effect effect) {
			super(effect);
			super.emptyItem = VIAL;
		}

		@Override
		public void effect(final Player player, final Item item) {
			int level = player.getSkills().getStaticLevel(Skills.PRAYER);
			int amt = 7 + (level / 4);
			player.getSkills().incrementPrayerPoints(amt);

		}
	}

	/**
	 * Represents the barbarian prayer potion mix
	 * @author ceik
	 */
	public class PrayerMix extends Potion {

		public PrayerMix() {
			/**
			 * empty.
			 */
		}

		public PrayerMix(Effect effect) {
			super(effect);
			super.emptyItem = VIAL;
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			Consumables.add(new PrayerMix(new Effect("Prayer mix", new int[] { 11465, 11467 }, null)));
			return this;
		}

		@Override
		public void effect(final Player player, final Item item) {
			int level = player.getSkills().getStaticLevel(Skills.PRAYER);
			int amt = 7 + (level / 4);
			player.getSkills().incrementPrayerPoints(amt);
			int hpDeficit = player.getSkills().getLifepoints() - player.getSkills().getMaximumLifepoints();
			if(hpDeficit <= -6){
				player.getSkills().setLifepoints(player.getSkills().getLifepoints() + 6);
				return;
			} else if (hpDeficit <= 0){
				player.getSkills().setLifepoints(player.getSkills().getMaximumLifepoints());
				return;
			}
		}

	}

	/**
	 * Represents the anti-poison plugin.
	 * @author 'Vexia
	 * @date 23/12/2013
	 */
	public class RelicymsBalm extends Potion {

		/**
		 * Constructs a new {@code RelicymsBalmPlugin} {@code Object}.
		 */
		public RelicymsBalm() {
			/**
			 * empty.
			 */
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			Consumables.add(new RelicymsBalm(PotionEffect.RELICYMS_BALM));
			return this;
		}

		/**
		 * Constructs a new {@code RelicymsBalmPlugin} {@code Object}.
		 * @param effect the effect.
		 */
		public RelicymsBalm(PotionEffect effect) {
			super(effect);
			super.emptyItem = VIAL;
		}

		@Override
		public void effect(final Player player, final Item item) {
			player.getStateManager().remove(EntityState.DISEASED);
			player.setAttribute("disease:immunity", GameWorld.getTicks() + 300);
		}

	}

	/**
	 * Represents the energy potion plugin.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class RestorePotion extends Potion {

		/**
		 * Represents the array of skills to restore.
		 */
		private static final int[] SKILLS = new int[] { Skills.DEFENCE, Skills.ATTACK, Skills.STRENGTH, Skills.MAGIC, Skills.RANGE };

		/**
		 * Constructs a new {@code RestorePotionPlugin} {@code Object}.
		 */
		public RestorePotion() {
			/**
			 * empty.
			 */
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			Consumables.add(new RestorePotion(new Effect("Restore potion", new int[] { 2430, 127, 129, 131 }, null)));
			return this;
		}

		/**
		 * Constructs a new {@code RestorePotionPlugin} {@code Object}.
		 * @param effect the effect.
		 */
		public RestorePotion(Effect effect) {
			super(effect);
			super.emptyItem = VIAL;
		}

		@Override
		public void effect(final Player player, final Item item) {
			for (int skill : SKILLS) {
				int statLevel = player.getSkills().getStaticLevel(skill);
				if (player.getSkills().getLevel(skill) < statLevel) {
					int boost = (int) (10 + (statLevel * 0.3));
					player.getSkills().updateLevel(skill, boost, statLevel);
				}
			}
		}

	}

	/**
	 * Represents the saradomin brew.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class SaradominBrew extends Potion {

		/**
		 * Represents the skill bonuses.
		 */
		private static final SkillBonus[] BONUSES = new SkillBonus[] { new SkillBonus(Skills.HITPOINTS, 0.15), new SkillBonus(Skills.ATTACK, -0.10), new SkillBonus(Skills.STRENGTH, -0.10), new SkillBonus(Skills.MAGIC, -0.10), new SkillBonus(Skills.RANGE, -0.10) };

		/**
		 * Constructs a new {@code SaradominBrewPlugin} {@code Object}.
		 */
		public SaradominBrew() {
			/**
			 * empty.
			 */
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			Consumables.add(new SaradominBrew(new Effect("Saradomin brew", new int[] { 6685, 6687, 6689, 6691 }, null)));
			return this;
		}

		/**
		 * Constructs a new {@code SaradominBrewPlugin} {@code Object}.
		 */
		public SaradominBrew(Effect effect) {
			super(effect);
			super.emptyItem = VIAL;
		}

		@Override
		public void consume(final Item item, final Player player) {
			for (SkillBonus b : BONUSES) {
				addBonus(player, b);
			}
			player.getSkills().updateLevel(Skills.DEFENCE, (int) ((player.getSkills().getStaticLevel(Skills.DEFENCE) * 0.20) + 2));
			super.consume(item, player);
		}

	}

	/**
	 * Represents the summoning potion.
	 * @author 'Vexia
	 * @date 23/12/2013
	 */
	public final class SummoningPotion extends Potion {

		/**
		 * Constructs a new {@code SummoningPotionPlugin} {@code Object}.
		 */
		public SummoningPotion() {
			/**
			 * empty.
			 */
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			Consumables.add(new SummoningPotion(new Effect("Summoning potion", new int[] { 12140, 12142, 12144, 12146 }, null)));
			return this;
		}

		/**
		 * Constructs a new {@code PrayerPotionPlugin} {@code Object}.
		 * @param effect the effect.
		 */
		public SummoningPotion(Effect effect) {
			super(effect);
			super.emptyItem = VIAL;
		}

		@Override
		public void effect(final Player player, final Item item) {
			player.getSkills().updateLevel(Skills.SUMMONING, (int) (7 + player.getSkills().getLevel(Skills.SUMMONING) * 0.25), player.getSkills().getStaticLevel(Skills.SUMMONING));
		}

	}

	/**
	 * Represents the super anti-poison plugin.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class SuperantiPoison extends Potion {

		/**
		 * Constructs a new {@code SuperantiPoisonPlugin} {@code Object}.
		 */
		public SuperantiPoison() {
			/**
			 * empty.
			 */
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			Consumables.add(new SuperantiPoison(PotionEffect.SUPER_ANTIPOISON));
			return this;
		}

		/**
		 * Constructs a new {@code AntipoisonPlugin} {@code Object}.
		 * @param effect the effect.
		 */
		public SuperantiPoison(PotionEffect effect) {
			super(effect);
		}

		@Override
		public void effect(final Player player, final Item item) {
			player.getStateManager().remove(EntityState.POISONED);
			player.setAttribute("poison:immunity", GameWorld.getTicks() + 386);
		}

	}

	/**
	 * Represents the energy potion plugin.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class SuperenergyPotion extends Potion {

		/**
		 * Constructs a new {@code PrayerPotionPlugin} {@code Object}.
		 */
		public SuperenergyPotion() {
			/**
			 * empty.
			 */
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			Consumables.add(new SuperenergyPotion(new Effect("Super energy", new int[] { 3016, 3018, 3020, 3022 }, null)));
			return this;
		}

		/**
		 * Constructs a new {@code SuperenergyPotion} {@code Object}.
		 * @param effect the effect.
		 */
		public SuperenergyPotion(Effect effect) {
			super(effect);
			super.emptyItem = VIAL;
		}

		@Override
		public void effect(final Player player, final Item item) {
			player.getSettings().updateRunEnergy(-20);
		}

	}

	/**
	 * Represents the energy potion plugin.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class SuperestorePlugin extends Potion {

		/**
		 * Constructs a new {@code PrayerPotionPlugin} {@code Object}.
		 */
		public SuperestorePlugin() {
			/**
			 * empty.
			 */
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			Consumables.add(new SuperestorePlugin(new Effect("Super restore", new int[] { 3024, 3026, 3028, 3030 }, null)));
			return this;
		}

		/**
		 * Constructs a new {@code PrayerPotionPlugin} {@code Object}.
		 * @param effect the effect.
		 */
		public SuperestorePlugin(Effect effect) {
			super(effect);
			super.emptyItem = VIAL;
		}

		@Override
		public void effect(final Player player, final Item item) {
			for (int skill = 0; skill < Skills.SKILL_NAME.length; skill++) {
				if (skill == Skills.HITPOINTS) {
					continue;
				}
				int statLevel = player.getSkills().getStaticLevel(skill);
				if (player.getSkills().getLevel(skill) < statLevel) {
					int boost = (int) (statLevel * 0.25) + 8;
					player.getSkills().updateLevel(skill, boost, statLevel);
				}
			}
			int prayerLvl = player.getSkills().getStaticLevel(Skills.PRAYER);
			player.getSkills().incrementPrayerPoints((prayerLvl * 0.25) + 8);
		}
	}

	/**
	 * Represents the zamorak brew plugin.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class ZamorakBrew extends Potion {

		/**
		 * Reprsents the skill bonuses.
		 */
		private static final SkillBonus[] BONUSES = new SkillBonus[] { new SkillBonus(Skills.ATTACK, 0.25), new SkillBonus(Skills.STRENGTH, 0.15), new SkillBonus(Skills.DEFENCE, -0.10) };

		/**
		 * Constructs a new {@code ZamorakBrewPlugin} {@code Object}.
		 */
		public ZamorakBrew() {
			/**
			 * empty.
			 */
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			Consumables.add(new ZamorakBrew(new Effect("Zamorak brew", new int[] { 2450, 189, 191, 193 }, null)));
			Consumables.add(new ZamorakBrew(new Effect("Zamorak mix", new int[] { 11521, 11523 }, null)));
			return this;
		}

		/**
		 * Constructs a new {@code ZamorakBrewPlugin} {@code Object}.
		 * @param effect the effect.
		 */
		public ZamorakBrew(Effect effect) {
			super(effect);
			super.emptyItem = VIAL;
		}

		@Override
		public void consume(final Item item, final Player player) {
			for (SkillBonus b : BONUSES) {
				addBonus(player, b);
			}
			player.getImpactHandler().manualHit(player, (int) (player.getSkills().getLifepoints() * 0.10), HitsplatType.NORMAL);
			super.consume(item, player);
		}

	}

	/**
	 * Represents an antifire potion.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class AntifirePotion extends Potion {

		/**
		 * Constructs a new {@code AntifirePotion} {@code Object}.
		 */
		public AntifirePotion() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code AntifirePotion} {@code Object}.
		 * @param effect the effect.
		 */
		public AntifirePotion(final Effect effect) {
			super(effect);
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			Consumables.add(new AntifirePotion(new Effect("Antifire potion", new int[] { 2452, 2454, 2456, 2458 }, null)));
			Consumables.add(new AntifirePotion(new Effect("Antifire potion", new int[] { 14753, 14755, 14757, 14759  }, null)));
			return this;
		}

		@Override
		public void effect(final Player player, final Item item) {
			player.getStateManager().register(EntityState.FIRE_RESISTANT, true, (item.getId() > 14000 ? true : false));
		}

	}
	
	/**
	 * Represents the Super Magic and Super Ranging potions.
	 * @author Splinter
	 * 
	 */
	public static final class SuperOSRSPotionPlugin extends Potion {

		/**
		 * Constructs a new {@code SuperOSRSPotionPlugin} {@code Object}.
		 */
		public SuperOSRSPotionPlugin() {
			/**
			 * empty.
			 */
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			Consumables.add(new SuperOSRSPotionPlugin(new Effect("Super magic potion", new int[] { 14780, 14781, 14782, 14783 }, null)));
			Consumables.add(new SuperOSRSPotionPlugin(new Effect("Super range potion", new int[] { 14776, 14777, 14778, 14779 }, null)));
			return this;
		}

		/**
		 * Constructs a new {@code SuperOSRSPotionPlugin} {@code Object}.
		 * @param effect the effect.
		 */
		public SuperOSRSPotionPlugin(Effect effect) {
			super(effect);
			super.emptyItem = VIAL;
		}

		@Override
		public void effect(final Player player, final Item item) {
			if(item.getName().contains("uper magic")) {
				player.getSkills().updateLevel(Skills.MAGIC, (int) (5 + player.getSkills().getLevel(Skills.MAGIC) * 0.15), 118);	
			} else {
				player.getSkills().updateLevel(Skills.RANGE, (int) (5 + player.getSkills().getLevel(Skills.RANGE) * 0.15), 118);	
			}
		}

	}
	
	/**
	 * Represents the plugin used for super combat potions.
	 * @author Empathy
	 */
	public static final class SuperCombatPotionPlugin extends Potion {

		/**
		 * Constructs a new {@code SuperCombatPotionPlugin} {@code Object}.
		 */
		public SuperCombatPotionPlugin() {
			/**
			 * empty.
			 */
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			Consumables.add(new SuperCombatPotionPlugin(new Effect("Super Combat Potion", new int[] { 14871, 14869, 14867, 14865 }, null)));
			return this;
		}
		
		/**
		 * Constructs a new {@code SuperCombatPotionPlugin} {@code Object}.
		 * @param effect the effect
		 */
		public SuperCombatPotionPlugin(Effect effect) {
			super(effect);
			super.emptyItem = VIAL;
		}
		
		@Override
		public void effect(final Player player, final Item item) {
			effect(player, PotionEffect.SUPER_ATTACK.getSkillBonus());
			effect(player, PotionEffect.SUPER_STRENGTH.getSkillBonus());
			effect(player, PotionEffect.SUPER_DEFENCE.getSkillBonus());
		}		
	}
}
