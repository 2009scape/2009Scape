package plugin.ai.pvmbots;

import plugin.ai.AIPlayer;
import core.game.world.map.Location;

public class LowestBot extends PvMBots{

	public LowestBot(Location l) {
		super(l);
	}

	private int tick = 0;

	@Override
	public void tick(){
		super.tick();

		this.tick++;

		//Despawn
		if (this.getSkills().getLifepoints() == 0)
			//this.teleport(new Location(500, 500));
			//Despawning not being delayed causes 3 errors in the console
			AIPlayer.deregister(this.getUid());

		//Npc Combat
		if (this.tick % 10 == 0) {
			if (!this.inCombat())
				AttackNpcsInRadius(this, 5);
		}

		if (this.tick == 100) this.tick = 0;

		this.eat(329);
		//this.getPrayer().toggle()
	}

}
