package plugin.ai.pvmbots;

import plugin.ai.AIPlayer;
import core.game.world.map.Location;

public class NoobBot extends PvMBots{

	private int tick = 0;
	private int movetimer = 0;
	
	public NoobBot(Location l) {
		super(l);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void tick()
	{
		super.tick();
		
		//Despawn
		if (this.getSkills().getLifepoints() == 0)
			//this.teleport(new Location(500, 500));
			//Despawning not being delayed causes 3 errors in the console
			AIPlayer.deregister(this.getUid());
		
		//Npc Combat
		if (tick == 0)
		{
			if (!this.inCombat())
				AttackNpcsInRadius(this, 5);
			this.tick = 5;
		}
		else 
			this.tick--;
		
		this.eat(329);
		//this.getPrayer().toggle()
		//System.out.println(this.getPulseManager().getCurrent());
		
		if (!this.inCombat())
		{
			if (movetimer == 0)
			{
				if (this.FindTargets(this, 5) == null)
					this.randomWalk(5, 5);
					this.movetimer = 10;
			}
			else 
				movetimer --;
		}
	}

}
