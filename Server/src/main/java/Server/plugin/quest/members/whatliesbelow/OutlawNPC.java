package plugin.quest.members.whatliesbelow;

import core.game.node.entity.Entity;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.game.world.map.Location;

/**
 * Handles the outlaw npc.
 * @author adam
 */
public class OutlawNPC extends AbstractNPC {

	/**
	 * Constructs a new {@Code OutlawNPC} {@Code Object}
	 */
	public OutlawNPC() {
		super(-1, null);
	}

	/**
	 * Constructs a new {@Code OutlawNPC} {@Code Object}
	 * @param id the id.
	 * @param location the location.
	 */
	public OutlawNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public void onAttack(Entity e) {
		sendChat("Stand and deliver!");
	}

	@Override
	public void finalizeDeath(Entity killer) {
		super.finalizeDeath(killer);
		if (killer instanceof Player) {
			Player p = killer.asPlayer();
			Quest quest = p.getQuestRepository().getQuest(WhatLiesBelow.NAME);
			if (quest.getStage(p) == 10) {
				int amount = p.getInventory().getAmount(WhatLiesBelow.RATS_PAPER) + p.getBank().getAmount(WhatLiesBelow.RATS_PAPER);
				if (amount < 5) {
					GroundItemManager.create(WhatLiesBelow.RATS_PAPER, getLocation());
				}
			}
		}
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new OutlawNPC(id, location);
	}

	@Override
	public int[] getIds() {
		return new int[] { 5842, 5843, 5844, 5845, 5846, 5847, 5848, 5849, 5850, 5852 };
	}

}
