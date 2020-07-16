package plugin.consumable.potion;

import core.game.node.entity.player.Player;
import core.game.node.entity.state.EntityState;
import core.game.node.item.Item;
import core.game.world.update.flag.context.Animation;
import core.tools.ItemNames;
import core.tools.RandomFunction;
import plugin.consumable.Drink;
import plugin.consumable.potion.effects.*;
import plugin.skill.Skills;

import java.util.HashMap;

public enum Potions {
    STRENGTH(new int[] { 113, 115, 117, 119 }, new IncrementSkillEffect(Skills.STRENGTH,3,0.1)),
    ATTACK(new int[] { 2428, 121, 123, 125 }, new IncrementSkillEffect(Skills.ATTACK,3,0.1)),
    DEFENCE(new int[] { 2432, 133, 135, 137 }, new IncrementSkillEffect(Skills.DEFENCE,3,0.1)),
    RANGING(new int[] { 2444, 169, 171, 173 }, new IncrementSkillEffect(Skills.RANGE,3,0.1)),
    MAGIC(new int[] { 3040, 3042, 3044, 3046 }, new IncrementSkillEffect(Skills.MAGIC,3,0.1)),
    SUPER_STRENGTH(new int[] { 2440, 157, 159, 161 }, new IncrementSkillEffect(Skills.STRENGTH,3,0.2)),
    SUPER_ATTACK(new int[] { 2436, 145, 147, 149 }, new IncrementSkillEffect(Skills.ATTACK,3,0.2)),
    SUPER_DEFENCE(new int[] { 2442, 163, 165, 167 }, new IncrementSkillEffect(Skills.DEFENCE,3,0.2)),
    ANTIPOISON(new int[] { 2446, 175, 177, 179 },new MultiEffect(new SetAttributeEffect("poison:immunity",143),new RemoveStateEffect(EntityState.POISONED.ordinal()))),
    ANTIPOISON_(new int[]{ 5943, 5945, 5947, 5949 },new MultiEffect(new SetAttributeEffect("poison:immunity",863),new RemoveStateEffect(EntityState.POISONED.ordinal()))),
    ANTIPOISON__(new int[] { 5952, 5954, 5956, 5958 },new MultiEffect(new SetAttributeEffect("poison:immunity",2000),new RemoveStateEffect(EntityState.POISONED.ordinal()))),
    SUPER_ANTIP(new int[] { 2448, 181, 183, 185 },new MultiEffect(new SetAttributeEffect("poison:immunity",1000),new RemoveStateEffect(EntityState.POISONED.ordinal()))),
    RELICYM(new int[] { 4842, 4844, 4846, 4848 }, new MultiEffect(new SetAttributeEffect("disease:immunity",300), new RemoveStateEffect(EntityState.DISEASED.ordinal()))),
    AGILITY(new int[] { 3032, 3034, 3036, 3038 }, new IncrementSkillEffect(Skills.AGILITY,3,0)),
    HUNTER(new int[] { 9998, 10000, 10002, 10004 },new IncrementSkillEffect(Skills.HUNTER,3,0)),
    RESTORE(new int[] { 2430, 127, 129, 131 }, new RestoreEffect(10,0.3)),
    SARA_BREW(new int[] { 6685, 6687, 6689, 6691 }, new MultiEffect(new IncrementSkillEffect(Skills.HITPOINTS,0,0.15), new IncrementSkillEffect(Skills.ATTACK,0,-0.10), new IncrementSkillEffect(Skills.STRENGTH,0,-0.10), new IncrementSkillEffect(Skills.MAGIC,0, -0.10), new IncrementSkillEffect(Skills.RANGE,0, -0.10), new IncrementSkillEffect(Skills.DEFENCE,2,0.2))),
    SUMMONING(new int[] { 12140, 12142, 12144, 12146 }, new IncrementSkillEffect(Skills.SUMMONING,7,0.25)),
    COMBAT(new int[]{9739, 9741, 9743, 9745}, new MultiEffect(new IncrementSkillEffect(Skills.STRENGTH,3,.1),new IncrementSkillEffect(Skills.ATTACK,3,.1))),
    ENERGY(new int[] { 3008, 3010, 3012, 3014 }, new MultiEffect(new EnergyEffect(10), new HealingEffect(3))),
    FISHING(new int[] { 2438, 151, 153, 155 }, new IncrementSkillEffect(Skills.FISHING,3,0)),
    PRAYER(new int[] { 2434, 139, 141, 143 }, new PrayerEffect(7,0.25)),
    PRAYERMIX(new int[] { 11467,11465 }, new MultiEffect(new PrayerEffect(7,0.25), new HealingEffect(6))),
    SUPER_RESTO(new int[]{3024, 3026, 3028, 3030}, new MultiEffect(new RestoreEffect(8,0.25),new PrayerEffect(8,0.25))),
    ZAMMY_BREW(new int[]{2450, 189, 191, 193},new MultiEffect(new DamageEffect(0.1,true),new IncrementSkillEffect(Skills.ATTACK,0,0.15),new IncrementSkillEffect(Skills.STRENGTH,0,0.25),new IncrementSkillEffect(Skills.DEFENCE,0,-0.1),new PrayerEffect(RandomFunction.random(0,10),0))),
    ZAMMY_MIX(new int[]{11523,11521},new MultiEffect(new IncrementSkillEffect(Skills.HITPOINTS,0,-0.10),new IncrementSkillEffect(Skills.ATTACK,0,0.15),new IncrementSkillEffect(Skills.STRENGTH,0,0.25),new IncrementSkillEffect(Skills.DEFENCE,0,-0.1),new PrayerEffect(RandomFunction.random(0,10),0))),
    ANTIFIRE(new int[]{2452, 2454, 2456, 2458},new SetAttributeEffect("fire:immune",600,true)),
    GUTH_REST(new int[]{4417,4419,4421,4423}, new MultiEffect(new RemoveStateEffect(EntityState.POISONED.ordinal()),new EnergyEffect(5), new HealingEffect(5))),
    MAGIC_ESS(new int[]{11491,11489}, new IncrementSkillEffect(Skills.MAGIC,3,0)),
    SANFEW(new int[]{10925,10927,10929,10931}, new MultiEffect(new RestoreEffect(8,0.25),new PrayerEffect(8,0.25),new RemoveStateEffect(EntityState.POISONED.ordinal()), new RemoveStateEffect(EntityState.DISEASED.ordinal()))),
    ATT_MIX(new int[]{11431,11429},new MultiEffect(new IncrementSkillEffect(Skills.ATTACK,3,0.1),new HealingEffect(3))),
    ANTIP_MIX(new int[]{11435,11433}, new MultiEffect(new RemoveStateEffect(EntityState.POISONED.ordinal()), new SetAttributeEffect("poison:immunity",143),new HealingEffect(3))),
    RELIC_MIX(new int[]{11439,11437},new MultiEffect(new RemoveStateEffect(EntityState.DISEASED.ordinal()), new SetAttributeEffect("disease:immunity",300),new HealingEffect(3))),
    STR_MIX(new int[]{11443,11441}, new MultiEffect(new IncrementSkillEffect(Skills.STRENGTH,3,0.1),new HealingEffect(3))),
    RESTO_MIX(new int[]{11449,11451},new MultiEffect(new RestoreEffect(10,0.3),new HealingEffect(3))),
    ENERGY_MIX(new int[]{11453,11455},new MultiEffect(new EnergyEffect(10),new HealingEffect(6))),
    DEF_MIX(new int[]{11457,11459},new MultiEffect(new IncrementSkillEffect(Skills.DEFENCE,3,0.1),new HealingEffect(6))),
    AGIL_MIX(new int[]{11461,11463}, new MultiEffect(new IncrementSkillEffect(Skills.AGILITY,3,0),new HealingEffect(6))),
    COMBAT_MIX(new int[]{11445,11447},new MultiEffect(new IncrementSkillEffect(Skills.ATTACK,3,0.1),new IncrementSkillEffect(Skills.STRENGTH,3,0.1),new HealingEffect(6))),
    SUPER_ATT_MIX(new int[]{11469,11471},new MultiEffect(new IncrementSkillEffect(Skills.ATTACK,5,0.15),new HealingEffect(6))),
    FISH_MIX(new int[]{11477,11479},new MultiEffect(new IncrementSkillEffect(Skills.FISHING,3,0),new HealingEffect(6))),
    SUPER_ENERGY_MIX(new int[]{11481,11483},new MultiEffect(new EnergyEffect(20),new HealingEffect(6))),
    HUNTING_MIX(new int[]{11517,11519},new MultiEffect(new IncrementSkillEffect(Skills.HUNTER,3,0),new HealingEffect(6))),
    SUPER_STR_MIX(new int[]{11485,11487},new MultiEffect(new IncrementSkillEffect(Skills.STRENGTH,5,0.15),new HealingEffect(6))),
    SUPER_ENERGY(new int[]{3016,3018,3020,3022},new EnergyEffect(-1) );

    public static HashMap<Integer, Potions> potMap = new HashMap<>();
    static{
        for(Potions pot : Potions.values()){
            for(int i = 0; i < pot.ids.length; i++){
                potMap.putIfAbsent(pot.ids[i],pot);
            }
        }
    }
    public PotionEffect effect;
    public int[] ids;

    Potions(int[] ids, PotionEffect effect){
        this.effect = effect;
        this.ids = ids;
    }

    public static Potions forId(int id){
        return potMap.get(id);
    }

    public void consume(Player p, int id){
        p.getAnimator().animate(ANIMATION);
        p.getAudioManager().send(Drink.SOUND);
        Item newItem;
        p.getInventory().remove(new Item(id));
        int newId = handleDosing(id);
        if(newId == -1){
            newItem = new Item(ItemNames.VIAL);
        } else {
            newItem = new Item(newId);
        }
        p.getInventory().add(newItem);
        forId(id).effect.activate(p);
    }

    public int handleDosing(int potion_id){
        for(int i = 0; i < ids.length; i++){
            if (ids[i] == potion_id && i != (ids.length - 1)) return ids[i + 1];
        }
        return -1;
    }

    public static int getDose(Item potion){
        return Integer.parseInt(potion.getName().replaceAll("[^\\d.]",""));
    }

    protected static final Animation ANIMATION = new Animation(829);

}
