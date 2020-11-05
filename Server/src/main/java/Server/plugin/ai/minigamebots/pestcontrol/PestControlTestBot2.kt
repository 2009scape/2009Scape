package plugin.ai.minigamebots.pestcontrol;

import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.player.link.prayer.PrayerType;
import core.game.world.map.Location;
import core.net.packet.in.InteractionPacket;
import core.tools.RandomFunction;
import plugin.ai.pvmbots.CombatBotAssembler;
import plugin.ai.pvmbots.PvMBots;
import java.util.List;
import java.util.Random;

import static plugin.activity.pestcontrol.PestControlHelper.*;

public class PestControlTestBot2 extends PvMBots {

	public int tick = 0;
	public int combatMoveTimer = 0;
	public boolean justStartedGame = true;
	public int movetimer = 0;

	public int randomType;
	public boolean openedGate;
private BoatInfo myBoat = BoatInfo.INTERMEDIATE;

	private CombatState2 combathandler = new CombatState2(this);

	enum State {
		OUTSIDE_GANGPLANK,
		WAITING_IN_BOAT,
		PLAY_GAME,
		GET_TO_PC
	}

	//Novice Lander co-ords (2657, 2639, 0)
	//Intermediate lander co-ords (2644, 2644, 0)
	//Veteran lander co-ords (2638, 2653 0)
	public PestControlTestBot2(Location l) {
		super("pestcontrolcopies.txt", legitimizeLocation(l));
		int num = RandomFunction.random(3);
		if(num == 1){
			new CombatBotAssembler().gearPCiMeleeBot(this);
		} else {
			new CombatBotAssembler().gearPCiRangedBot(this,new Random().nextInt() % 2 == 0);
		}
		randomType = new Random().nextInt(100);
	}

	private static Location legitimizeLocation(Location l) {
		return landerContainsLoc(l) ? new Location(2644, 2644, 0) : l;
	}

	@Override
	public void tick()
	{
		super.tick();
		movetimer --;

		if (movetimer <= 0)
		{
			movetimer = 0;
			State state = getState();
			this.setCustomState(String.valueOf(state) + movetimer);

			switch (state)
			{
				case GET_TO_PC:
					getToPC();
					break;
				case OUTSIDE_GANGPLANK:
					enterBoat();
					break;
				case WAITING_IN_BOAT:
					idleInBoat();
					break;
				case PLAY_GAME:
					attackNPCs();
					break;
			}
		}
	}

	private State getState() {
		if (landerContainsLoc(this.getLocation()))
		{
			return State.WAITING_IN_BOAT;
		}
		if (isInPestControlInstance(this))
		{
			return State.PLAY_GAME;
		}
		if (outsideGangplankContainsLoc(this.getLocation()))
		{
			return State.OUTSIDE_GANGPLANK;
		}
		return State.GET_TO_PC;
	}

	private void attackNPCs() {
		this.getWalkingQueue().setRunning(true);
		List<Entity> creatures = FindTargets(this, 20);
		if (creatures == null || creatures.isEmpty())
		{
			if (randomType > 10)
			{
				this.setCustomState("Going to portals");
				combathandler.goToPortals();
			} else {
				try {
					randomWalkAroundPoint(getMyPestControlSession(this).getSquire().getLocation(), 3);
				} catch (NullPointerException e) {
					//Do nothing, game just finished
				}
				movetimer = new Random().nextInt(15) + 6;
			}
		} else {
			if (randomType < 15 && new Random().nextInt(5) == 0)
			{
				randomWalkAroundPoint(getMyPestControlSession(this).getSquire().getLocation(), 3);
				movetimer = new Random().nextInt(15) + 6;
			} else {
				this.setCustomState("Fighting NPCs");
				combathandler.fightNPCs();
			}
		}

	}

	private int insideBoatWalks = 3;
	private void idleInBoat() {
		justStartedGame = true;
		openedGate = false;
		if (randomType < 20)
		{
			if (new Random().nextInt(insideBoatWalks) <= 1)
			{
				insideBoatWalks *= 1.5;
				if (new Random().nextInt(4) == 1)
				{
					this.getWalkingQueue().setRunning(!this.getWalkingQueue().isRunning());
				}
				if (new Random().nextInt(7) == 1)
				{
					this.walkToPosSmart(new Location(2660, 2638));
				} else {
					this.walkToPosSmart(myBoat.boatBorder.getRandomLoc());
				}
			}
			if (new Random().nextInt(3) == 1)
			{
				insideBoatWalks += 2;
			}
		}
	}

	private void enterBoat() {
		if (getPrayer().getActive().contains(PrayerType.PROTECT_FROM_MELEE)) {
			getPrayer().toggle(PrayerType.PROTECT_FROM_MELEE);
		}

		if (new Random().nextInt(3) <= 1) //Don't join instantly
		{
			return;
		}
		if (randomType > 20 && new Random().nextInt(6) == 0) //Idle outside ladder
		{
			if (new Random().nextInt(10) == 0)
			{
				this.walkToPosSmart(myBoat.outsideBoatBorder.getRandomLoc());
				movetimer += RandomFunction.normalPlusWeightRandDist(400, 200);
			}
			movetimer = RandomFunction.normalPlusWeightRandDist(100, 50);
			return;
		}
		Node test = getClosestNodeWithEntry(50, myBoat.ladderId);
		InteractionPacket.handleObjectInteraction(this, 0, test.getLocation(), test.getId());
		insideBoatWalks = 3;
	}

	private void getToPC() {
		Node test = getClosestNodeWithEntry(50, myBoat.ladderId);
		if (test == null)
		{
			this.teleport(PestControlIslandLocation2);
		} else {
			InteractionPacket.handleObjectInteraction(this, test);
		}
	}

}