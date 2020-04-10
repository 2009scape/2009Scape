package plugin.quest.witchs_house;

import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.plugin.InitializablePlugin;

/**
 * Created for 2009Scape
 * User: Ethan Kyle Millard
 * Date: March 25, 2020
 * Time: 8:28 PM
 */
@InitializablePlugin
public class WitchsExperimentNPC extends AbstractNPC {

    private ExperimentType type;
    private ExperimentSession session;
    private boolean commenced;

    public WitchsExperimentNPC() {
        super(0, null);
    }

    WitchsExperimentNPC(int id, Location location, ExperimentSession session) {
        super(id, location);
        this.setWalks(true);
        this.session = session;
        this.setRespawn(false);
        this.type = WitchsExperimentNPC.ExperimentType.forId(id);
    }

    @Override
    public void handleTickActions() {
        super.handleTickActions();
        if (session == null) {
            return;
        }
        if (!session.getPlayer().isActive() || session.getPlayer().getLocation().getDistance(getLocation()) > 15 || session.getPlayer().getSavedData().getQuestData().isWitchsExerimentKilled()) {
            session.getPlayer().removeAttribute("exerimentAlive");
            clear();
            session.close();
            return;
        }
        if (commenced && !getProperties().getCombatPulse().isAttacking()) {
            getProperties().getCombatPulse().attack(session.getPlayer());
        }
    }

    @Override
    public void startDeath(Entity killer) {
        if (killer == session.getPlayer()) {
            type.transform(this, session.getPlayer());
            return;
        }
        super.startDeath(killer);
    }

    @Override
    public boolean isAttackable(Entity entity, CombatStyle style) {
        if (session == null) {
            return false;
        }
        return session.getPlayer() == entity;
    }

    @Override
    public boolean canSelectTarget(Entity target) {
        if (target instanceof Player) {
            Player p = (Player) target;
            return p == session.getPlayer();
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
            player.getSavedData().getQuestData().setWitchsExerimentStage(newType.ordinal());
            GameWorld.submit(new Pulse(1, npc, player) {
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
                            if (newType.getMessage() != null) {
                                player.sendMessage(newType.getMessage()[0]);
                                player.sendMessage(newType.getMessage()[1]);
                            }
                            if (newType == END) {
                                player.getSavedData().getQuestData().setWitchsExerimentKilled(true);
                                player.removeAttribute("exerimentAlive");
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
            return experimentTypes[(this.ordinal()+1) % experimentTypes.length];
        }

        public int getId() {
            return id;
        }

        public String[] getMessage() {
            return message;
        }

    }

}