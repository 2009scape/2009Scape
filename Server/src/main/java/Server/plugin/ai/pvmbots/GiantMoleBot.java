package plugin.ai.pvmbots;

import plugin.ai.AIPlayer;
import core.game.node.entity.player.link.prayer.PrayerType;
import core.game.node.item.Item;
import core.game.world.map.Location;

public class GiantMoleBot extends PvMBots{

	public GiantMoleBot(Location l) {
		super(l);
		// TODO Auto-generated constructor stub
	}
	
	int tick = 0;
	int movetimer = 5;
	
	private void checkLightSource()
	{
		if (!this.getInventory().containItems(33))
		{
			if (this.getInventory().containItems(36))
				this.getInventory().remove(new Item(36));
			this.getInventory().add(new Item(33));
		}
	}
	
	@Override
	public void tick()
	{
		checkLightSource();
		super.tick();
		PrayerType type[] = {PrayerType.PROTECT_FROM_MELEE, PrayerType.PIETY};
		this.CheckPrayer(type);
		
		//Despawn
		if (this.getSkills().getLifepoints() == 0)
			//this.teleport(new Location(500, 500));
			//Despawning not being delayed causes 3 errors in the console
			AIPlayer.deregister(this.getUid());
		
		//Npc Combat
		if (this.tick == 0)
		{
			if (!this.inCombat())
				AttackNpcsInRadius(this, 50);
			this.tick = 5;
		}

		else 
			this.tick--;
		
		/*if (this.movetimer == 0)
		{
			if (!this.inCombat())
			{
				this.randomWalk(10, 10);
			}
			this.movetimer = 4;
		}
		else
			this.movetimer--;*/
		//if (this.getSkills().getLifepoints() > 1)
			//this.eat();
		//this.getPrayer().toggle()
	}

}
