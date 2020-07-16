package plugin.ai.resource;

import plugin.skill.gather.SkillingResource;
import core.game.node.entity.player.Player;
import plugin.ai.AIPlayer;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.repository.Repository;

import java.util.List;

public class ResourceAIPActions {

	public static List<AIPlayer> resource_players = null;

	protected SkillingResource resource;
	
	public static void syncBotThread(final Player player) {
		if (resource_players == null || resource_players.size() == 0) {
			return;
		}
		for (int aip_index = 0; aip_index < resource_players.size(); aip_index++) {
			final AIPlayer bot = resource_players.get(aip_index);
			GameWorld.Pulser.submit(new Pulse(1, bot) {
				int ticks;
				int ov;
				@Override
				public boolean pulse() {
					/*if (bot.getInventory().freeSlots() < 1) {
						//flee(bot);
						return true;
					}*/
					//Inactivity logout timer.
					if (ticks++ == 5000/* && bot.getWalkingQueue().getQueue().isEmpty()*/) {
						AIPlayer.deregister(bot.getUid());
						resource_players.remove(bot);
						return true;
					}
					if (ov++ >= 3) {
                        chopTree(bot);
                        ov = 0;
                    }
//					ov = 0;
                    System.out.println("ResourceAIPActions" + ov);
					return false;
				}
			});
		}
	}
	
	private static boolean chopTree(final AIPlayer bot) {
        /*for (GameObject[] o : RegionManager.forId(bot.getLocation().getRegionId()).getPlanes()[0].getObjects()) {
            for (GameObject obj : o) {
                if (obj != null) {
                    for (SkillingResource r : SkillingResource.values()) {
                        if (obj.getId() == r.getId()) {
                            Pathfinder.find(bot, obj).walk(bot);
                            bot.sendChat("WE CHOPPIN");
                        }
                    }
                }
            }
        }*/
        bot.attack(Repository.getNpcs().get(106));

//		Pulse pulse = new GatheringSkillPulse(bot, node);
//        bot.getPulseManager().run(new GatheringSkillPulse(bot, node));
//		pulse.start();
        return true;
	}	
}
