package plugin.ai.wilderness;

import java.util.ArrayList;
import java.util.List;

import core.game.container.impl.EquipmentContainer;
import plugin.consumable.Consumable;
import plugin.consumable.Consumables;
import plugin.consumable.Food;
import plugin.consumable.effects.HealingEffect;
import plugin.skill.Skills;
import core.game.interaction.Option;
import core.game.node.entity.Entity;
import core.game.node.entity.player.Player;
import plugin.ai.AIPlayer;
import core.game.node.entity.player.link.prayer.PrayerType;
import core.game.node.item.Item;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.tools.RandomFunction;

public class WildernessBot extends AIPlayer {

	public WildernessBot(Location l) {
		super(l);
		this.specWeapon = 1215;
		this.normalWeapon = 4151;
		this.getInventory().add(new Item(specWeapon));
		// TODO Auto-generated constructor stub
	}
	
	public WildernessBot(Location l, int normalWeaponId, int specWeaponId) {
		super(l);
		this.specWeapon = specWeaponId;
		this.normalWeapon = normalWeaponId;
		this.getInventory().add(new Item(specWeaponId));
	}
	
	int tick = 0;
	int movetimer = 0;
	int agressiveTimer = 2;
	int eatdelay = 2;
	boolean fleeing = false;
	boolean riskIt = false;
	int specWeapon;// = new Item(1215);
	int normalWeapon;// = new Item(4151);
	
	Item chest = this.getEquipment().get(EquipmentContainer.SLOT_CHEST);
	Item legs = this.getEquipment().get(EquipmentContainer.SLOT_LEGS);
	Item helmet = this.getEquipment().get(EquipmentContainer.SLOT_HAT);
	Item weapon = this.getEquipment().get(EquipmentContainer.SLOT_WEAPON);
	Item shield = this.getEquipment().get(EquipmentContainer.SLOT_SHIELD);

	
	public List<Entity> FindTargets(Entity entity, int radius) {
		List<Entity> targets = new ArrayList<>();
		for (Player player : RegionManager.getLocalPlayers(entity, radius)) { {
			if (checkValidTargets(player))
					targets.add(player);
			}
		}
		if (targets.size() == 0)
			return null;
		return targets;
	}

	public boolean checkValidTargets(Player target){
		if (!target.isActive()) 
		{
			return false;
		}
		if (!target.getProperties().isMultiZone() && target.inCombat()) {
			return false;
		}
		if (target.inCombat())
		{
			return false;
		}
		return true;
	}
	
	public void AttackPlayersInRadius(Player bot, int radius)
	{
		if (bot.inCombat())
			return;
		List<Entity> players = FindTargets(bot, radius);
		if (players == null)
		{
			return;
		}
		if (!(players.isEmpty()))
		{
			if (agressiveTimer <= 0)
				bot.attack(players.get(RandomFunction.getRandom((players.size() - 1))));
			return;
		}
	}
	
	private Entity getTarget()
	{
		return this.getProperties().getCombatPulse().getVictim();
	}
	
	private void checkSpecialAttack()
	{
		Entity target = getTarget();
		if (target == null)
			return;
		
		if (target.getSkills().getLifepoints() <= 75 && this.getSettings().getSpecialEnergy() >= 25)
		{
			//System.out.println("try special");
			Item weapon = this.getItemById(specWeapon);
			if (weapon != null) 
			{
				//System.out.println("found weapon " + weapon.getName());
				final Option option = weapon.getInteraction().get(1);
				if (option == null)
				{
					System.out.println("Option not found");
					return;
				}
				weapon.getInteraction().handleItemOption(this, option, this.getInventory());
				this.getSettings().setSpecialToggled(true);
				this.attack(target);
			}
			if (!this.getSettings().isSpecialToggled())
			{
				//System.out.println("toggle special");
				this.getSettings().setSpecialToggled(true);
				this.attack(target);
			}
		}
		else 
		{
			//System.out.println("try normal again");
			Item weapon = this.getItemById(normalWeapon);
			if (weapon != null) 
			{
				//System.out.println("found weapon " + weapon.getName());
				final Option option = weapon.getInteraction().get(1);
				if (option == null)
				{
					System.out.println("Option not found");
					return;
				}
				weapon.getInteraction().handleItemOption(this, option, this.getInventory());
				this.getSettings().setSpecialToggled(false);
				this.attack(target);
			}
		}
	}
	
	private void checkBarrowsSwitch()
	{
		System.out.println("WildernessBot: " + this.getSkills().getLifepoints());
		Entity target = getTarget();
		if (target == null)
			return;
		
		if (this.getSkills().getLifepoints() <= 35 && target.isPlayer() && target.getSkills().getLifepoints() <= 60)
		{
			this.getEquipment().replace(new Item(4720) ,EquipmentContainer.SLOT_CHEST);
			this.getEquipment().replace(new Item(4722) ,EquipmentContainer.SLOT_LEGS);
			this.getEquipment().replace(new Item(4716) ,EquipmentContainer.SLOT_HAT);
			this.getEquipment().replace(new Item(4719) ,EquipmentContainer.SLOT_WEAPON);
			this.getEquipment().replace(new Item(-1) ,EquipmentContainer.SLOT_SHIELD);
			riskIt = true;
		}
		else if (riskIt)
		{
			this.getEquipment().replace(chest, EquipmentContainer.SLOT_CHEST);
			this.getEquipment().replace(legs, EquipmentContainer.SLOT_LEGS);
			this.getEquipment().replace(helmet, EquipmentContainer.SLOT_HAT);
			this.getEquipment().replace(weapon, EquipmentContainer.SLOT_WEAPON);
			this.getEquipment().replace(shield, EquipmentContainer.SLOT_SHIELD);
			riskIt = false;
		}
	}
	
	private void checkPrayer()
	{
		if (this.inCombat())
		{
			if (!(this.getPrayer().getActive().contains(PrayerType.PIETY)))
				this.getPrayer().toggle((PrayerType.PIETY));
		}
	}
	
	@Override
	public void tick()
	{
		super.tick();
		//checkBarrowsSwitch();
		checkSpecialAttack();
		checkPrayer();
		//Despawn
		if (this.getSkills().getLifepoints() == 0)
			//this.teleport(new Location(500, 500));
			//Despawning not being delayed causes 3 errors in the console
			AIPlayer.deregister(this.getUid());
		if (agressiveTimer != 0)
		{
			agressiveTimer--;
		}
		
		//Combat
		if (!fleeing)
		{
			if (tick == 0)
			{
				if (!this.inCombat())
					AttackPlayersInRadius(this, 5);
				this.tick = 10;
			}
			else 
				this.tick--;
		
			if (!this.inCombat())
			{
				if (movetimer == 0)
				{
				//if (this.FindTargets(this, 5) == null)
					this.randomWalk(20, 20);
					this.movetimer = 10;
				}
				else 
					movetimer --;
			}
		}
		this.sendChat("Tik");
		this.checkFlee(385);
		this.eat(385);
		//this.getPrayer().toggle()
	}
	
	public void checkFlee(int foodId)
	{
		if (this.getInventory().contains(foodId, 18) == false)
		{
			//todo: add tp
			// if ()
			this.getProperties().setRetaliating(false);
			this.sendChat("Oi");
			walkPos(this.getLocation().getLocalX(), this.getLocation().getLocalY() - 5 - RandomFunction.getRandom(5));
			fleeing = true;
		}
    }
	
	public void eat(int foodId)
	{
		if (riskIt)
			return;
		
		Item foodItem = new Item(foodId);
	
		if (!(this.getInventory().contains(foodId, 1)))
			return;
	
		if((this.getSkills().getStaticLevel(Skills.HITPOINTS) >= this.getSkills().getLifepoints() * 3) && this.getInventory().containsItem(foodItem)) 
		{
			this.lock(3);
			//this.animate(new Animation(829));
			Item food = this.getInventory().getItem(foodItem);
		
			Consumable consumable = Consumables.getConsumableById(food.getId());
		
			if (consumable == null) {
				consumable = new Food(new int[] {food.getId()}, new HealingEffect(1));
			}
		
			consumable .consume(food, this);
			this.getProperties().getCombatPulse().delayNextAttack(3);
		}
	}
	
	public void setMainWeapon(int weapon)
	{
		this.normalWeapon = weapon;
	}
}
