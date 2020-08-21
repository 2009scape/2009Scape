package plugin.quest.members;

import core.game.component.CloseEvent;
import core.game.component.Component;
import plugin.skill.Skills;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.link.quest.Quest;

/**
 * The main type for the druidic ritual quest.
 * @author Vexia
 * 
 */
@InitializablePlugin
public class DruidicRitual extends Quest {

	/**
	 * Constructs a new {@code DruidicRitual} {@code Object}.
	 * @param player The player to construct the class for.
	 */
	public DruidicRitual() {
		super("Drudic Ritual", 48, 47, 4, 80, 0, 3, 4);
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		player.getPacketDispatch().sendString("<col=08088A>I can start this quest by speaking to the <col=8A0808>Kaqemeex</col> <col=08088A>who is at", 275, 4+ 7);
		player.getPacketDispatch().sendString("<col=08088A>the </col><col=8A0808>Druids Circle</col> <col=08088A>just <col=8A0808>North</col> <col=08088A>of</col> <col=8A0808>Taverley.<col=8A0808>", 275, 5+ 7);
		if (stage == 10) {
			player.getPacketDispatch().sendString("<str>I told Kaqemeex I would help them prepare their ceremony.</str>", 275, 4+ 7);
			player.getPacketDispatch().sendString("<col=08088A>I should speak to <col=8A0808>Sanfew</col> <col=08088A>in the village to the <col=8A0808>South</col>", 275, 5+ 7);
		}
		if (stage == 20) {
			player.getPacketDispatch().sendString("", 275, 5+ 7);
			player.getPacketDispatch().sendString("<str>I told Kaqemeex I would help them prepare their ceremony.</str>", 275, 4+ 7);
			player.getPacketDispatch().sendString("<col=8A0808>Sanfew</col> <col=08088A>told me for the ritual they would need me to place", 275, 6+ 7);
			player.getPacketDispatch().sendString("<col=8A0808>raw bear meat, raw chicken, raw rat meat, <col=08088A>and <col=8A0808>raw beef <col=08088A>in", 275, 7+ 7);
			player.getPacketDispatch().sendString("<col=08088A>the <col=8A0808>Cauldron of Thunder in the <col=08088A>dungeon South of <col=8A0808>Taverley", 275, 8+ 7);
		}
		if (stage == 99) {
			player.getPacketDispatch().sendString("<str>I told Kaqemeex I would help them prepare their ceremony.</str>", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>The cremeony required various meats being placed in the.</str>", 275, 5+ 7);
			player.getPacketDispatch().sendString("<str>Cauldron of Thunder. I did this and gave them to Sanfew.</str>", 275, 6+ 7);
			player.getPacketDispatch().sendString("<col=08088A>I should speak to <col=8A0808>Kaqemeex <col=08088A>again and claim my <col=8A0808>reward", 275, 7+ 7);
		}
		if (stage == 100) {
			player.getPacketDispatch().sendString("<str>I told Kaqemeex I would help them prepare their ceremony.</str>", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>The cremeony required various meats being placed in the.</str>", 275, 5+ 7);
			player.getPacketDispatch().sendString("<str>Cauldron of Thunder. I did this and gave them to Sanfew.</str>", 275, 6+ 7);
			player.getPacketDispatch().sendString("<str>Kaqemeex then taught me the basics of the skill Herblore</str>", 275, 7+ 7);
			player.getPacketDispatch().sendString("<col=FF0000>QUEST COMPLETE!", 275, 11+ 7);
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getInterfaceManager().open(new Component(277).setCloseEvent(new CloseEvent() {
			@Override
			public boolean close(Player player, Component c) {
				if (player != null) {
					player.getDialogueInterpreter().open(455, NPC.create(455, player.getLocation()), true);
				}
				return true;
			}
		}));
		player.getPacketDispatch().sendString("4 Quest Points", 277, 8 + 2);
		player.getPacketDispatch().sendString("250 Herblore XP", 277, 9 + 2);
		player.getPacketDispatch().sendString("Access to Herblore skill", 277, 10 + 2);
		player.getPacketDispatch().sendItemZoomOnInterface(249, 240, 277, 3 + 2);
		player.getSkills().addExperience(Skills.HERBLORE, 250);
		player.getInterfaceManager().closeChatbox();
	}

	@Override
	public Quest newInstance(Object object) {
		// TODO Auto-generated method stub
		return this;
	}
}
