package plugin.ai.minigamebots.pestcontrol;

import core.game.node.Node;
import core.game.node.entity.player.link.prayer.PrayerType;
import core.game.world.map.Direction;
import plugin.skill.Skills;

import java.util.Random;

import static plugin.activity.pestcontrol.PestControlHelper.*;

public class CombatState2 {
    private PestControlTestBot2 bot;
    private Random Random = new Random(); //Ya idc if that's bad java, it's killing me right now lmfao

    public CombatState2(PestControlTestBot2 pestControlTestBot2) {
        this.bot = pestControlTestBot2;
    }
    //public CombatState2(PestControlTestBot2 pestControlTestBot2) =

    public void goToPortals() {
        bot.setCustomState("I'm at portals.");
        Node gate = bot.getClosestNodeWithEntry(75, GATE_ENTRIES);
        Node portal = bot.getClosestNodeWithEntry(80, PORTAL_ENTRIES);
        if (bot.justStartedGame && new Random().nextInt(2) == 0)
        {
            return;
        }
        if (bot.justStartedGame || gate == null && portal == null)
        {
            bot.setCustomState("Walking randomly");
            bot.justStartedGame = false;
            bot.randomWalkAroundPoint(getMyPestControlSession(bot).getSquire().getLocation(), 10);
            bot.movetimer = new Random().nextInt(7) + 6;
            return;
        }
        if (gate != null)
        {
            bot.setCustomState("Interacting gate ID " + gate.getId());
            bot.interact(gate);
            bot.openedGate = true;
            if (Random.nextInt(4) == 1 && bot.randomType < 30)
            {
                bot.movetimer = Random.nextInt(2) + 1;
            }
            return;
        }
        if (portal != null)
        {
            bot.setCustomState("Walking to portals");
            bot.randomWalkAroundPoint(portal.getLocation(), 25);
            bot.movetimer = new Random().nextInt(5) + 5;
        }
        bot.setCustomState("Absolutely nothing. Everything is dead");
    }

    public void fightNPCs() {
        bot.setCustomState("Fight NPCs");
        //Npc Combat
        if (bot.tick == 0)
        {
            if (!bot.inCombat())
                bot.AttackNpcsInRadius(30);
            bot.tick = 10;
        }
        else
            bot.tick--;

        bot.eat(379);
        if (!(bot.getPrayer().getActive().contains(PrayerType.PROTECT_FROM_MELEE)))
            bot.getPrayer().toggle(PrayerType.PROTECT_FROM_MELEE);
            bot.getPrayer().toggle(PrayerType.PIETY);


        if (!bot.inCombat())
        {
            if (bot.combatMoveTimer <= 0)
            {
                if (bot.FindTargets(bot, 30) == null)
                    bot.randomWalk(5, 5);
                bot.combatMoveTimer = 5;
            }
        }

    }

    private void goToEastPortals() {
        bot.setCustomState("Go to east portals");

        Node easternGate = bot.getClosestNodeWithEntryAndDirection(75, 14233, Direction.SOUTH);
        Node easternPortal = getMyPestControlSession(bot).getPortals()[1];
        if (easternGate != null)
        {
            bot.interact(easternGate);
        } else if (easternPortal != null ){
            bot.walkToPosSmart(easternPortal.getLocation());
        } else {
            bot.setCustomState("Everything is null!");
        }
    }
}
