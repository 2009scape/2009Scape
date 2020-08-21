package plugin.quest.members.witchshouse;

import core.game.node.entity.Entity;
import core.game.node.entity.combat.CombatStyle;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;

/**
 * Created for 2009Scape
 * User: Ethan Kyle Millard
 * Date: March 25, 2020
 * Time: 8:28 PM
 */
@InitializablePlugin
public class WitchsExperimentNPC extends AbstractNPC {

    private ExperimentType type;
    private boolean commenced;
    Player p;

    public WitchsExperimentNPC() {
        super(0, null);
    }

    WitchsExperimentNPC(int id, Location location, Player player) {
        super(id, location);
        this.setWalks(true);
        this.setRespawn(false);
        this.type = WitchsExperimentNPC.ExperimentType.forId(id);
        p = player;
    }

    @Override
    public void handleTickActions() {
        super.handleTickActions();
        if (!getProperties().getCombatPulse().isAttacking()) {
            getProperties().getCombatPulse().attack(p);
        }
    }

    @Override
    public void startDeath(Entity killer) {
        if (killer == p) {
            type.transform(this, p);
            return;
        }
        super.startDeath(killer);
    }

    @Override
    public boolean isAttackable(Entity entity, CombatStyle style) {
        return p == entity;
    }

    @Override
    public boolean canSelectTarget(Entity target) {
        if (target instanceof Player) {
            Player p = (Player) target;
            return p == this.p;
        }
        return true;
    }


    @Override
    public AbstractNPC construct(int id, Location location, Object... objects) {
        return new WitchsExperimentNPC(id, location, null);
    }

    @Override
    public int[] getIds() {
        return new int[] { 897, 898, 899, 900 };
    }

    public void setType(ExperimentType type) {
        this.type = type;
    }

    public ExperimentType getType() {
        return type;
    }

    public void setCommenced(boolean commenced) {
        this.commenced = commenced;
    }

    public enum ExperimentType {
        FIRST(897, ""),
        SECOND(898, "The shapeshifters' body begins to deform!", "The shapeshifter turns into a spider!"),
        THIRD(899, "The shapeshifters' body begins to twist!", "The shapeshifter turns into a bear!"),
        FOURTH(900, "The shapeshifters' body pulses!", "The shapeshifter turns into a wolf!"),
        END(-1, ""),

        ;

        private int id;
        private String[] message;

        ExperimentType(int id, String... message) {
            this.id = id;
            this.message = message;
        }

        /**
         * Transforms the new npc.
         */
        public void transform(final WitchsExperimentNPC npc, final Player player) {
            final ExperimentType newType = next();
            npc.lock();
            npc.getPulseManager().clear();
            npc.getWalkingQueue().reset();
            player.setAttribute("/save:witchs_house:experiment_id",this.id);
            GameWorld.Pulser.submit(new Pulse(1, npc, player) {
                int counter;

                @Override
                public boolean pulse() {
                    switch (++counter) {

                        case 1:
                            npc.unlock();
                            npc.getAnimator().reset();
                            npc.fullRestore();
                            npc.setType(newType);
                            npc.transform(newType.getId());
                            npc.getImpactHandler().setDisabledTicks(1);
                            if (newType != END) {
                                npc.getProperties().getCombatPulse().attack(player);
                            }
                            if (newType.getMessage() != null && newType != END) {
                                player.sendMessage(newType.getMessage()[0]);
                                player.sendMessage(newType.getMessage()[1]);
                            }
                            if (newType == END) {
                                player.setAttribute("witchs_house:experiment_killed",true);
                                npc.clear();
                                return false;
                            }
                            player.unlock();
                            return true;
                    }
                    return false;
                }

            });
        }

        public static ExperimentType forId(int id) {
            for (ExperimentType type : values()) {
                if (type.getId() == id) {
                    return type;
                }
            }
            return null;
        }

        private static ExperimentType[] experimentTypes = values();

        public ExperimentType next()
        {
            return experimentTypes[this.ordinal() + 1];
        }

        public int getId() {
            return id;
        }

        public String[] getMessage() {
            return message;
        }

    }

}