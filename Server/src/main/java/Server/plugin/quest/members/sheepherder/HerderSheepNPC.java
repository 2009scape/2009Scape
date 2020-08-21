package plugin.quest.members.sheepherder;

import core.game.interaction.MovementPulse;
import core.game.node.entity.Entity;
import core.game.node.entity.npc.NPC;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.path.Pathfinder;

public class HerderSheepNPC extends NPC {
    int ticksTilReturn = 0;
    Location spawnLocation;

    public HerderSheepNPC(int id, Location location) {
        super(id, location);
        this.spawnLocation = location;
        this.setNeverWalks(false);
        this.unlock();
        this.setRespawn(true);
    }

    @Override
    public void handleTickActions() {
        if(getAttribute("recently-prodded",false)) {
            if(getLocation().withinDistance(Location.create(2593, 3362, 0),2)){
                getProperties().setTeleportLocation(Location.create(2599, 3360, 0));
            }
            if (ticksTilReturn < GameWorld.getTicks()) {
                sendChat("Baa");
                this.getPulseManager().run(new MovementPulse(this, spawnLocation) {
                    @Override
                    public boolean pulse() {
                        return true;
                    }
                });
                this.removeAttribute("recently-prodded");
            }
        } else {
            if(nextWalk < GameWorld.getTicks() && !getPulseManager().hasPulseRunning()){
                setNextWalk();
                Location to = getMovementDestination();
                if (canMove(to)) {
                    Pathfinder.find(this, to, true, Pathfinder.DUMB).walk(this);
                }
            }
        }
    }

    public void moveTo(Location l){
        this.getPulseManager().run(new MovementPulse(this,l) {
            @Override
            public boolean pulse() {
                return true;
            }
        });
    }

    @Override
    public void finalizeDeath(Entity killer) {
        this.setRespawnTick(GameWorld.getTicks() + 100);
        super.finalizeDeath(killer);
    }
}