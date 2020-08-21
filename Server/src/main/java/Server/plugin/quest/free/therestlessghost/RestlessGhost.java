package plugin.quest.free.therestlessghost;

import plugin.quest.members.animalmagnetism.OldCronDialogue;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * Represents the restless ghost quest.
 * @author Vexia
 * 
 */
@InitializablePlugin
public class RestlessGhost extends Quest {
	
	/**
	 * The ghost speak amulet.
	 */
	public static final Item AMULET = new Item(552);
	
	/**
	 * Constructs a new {@Code RestlessGhost} {@Code Object}
	 */
	public RestlessGhost() {
		super("The Restless Ghost", 25, 24, 1, 107, 0, 4, 5);
	}	

	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugin(new OldCronDialogue());
		return this;
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		if (stage == 0) {
			player.getPacketDispatch().sendString("<col=08088A>I can start this quest by speaking to <col=8A0808>Father Aereck</col> <col=08088A>in the", 275, 4+ 7);
			player.getPacketDispatch().sendString("<col=8A0808>church</col> <col=08088A>next to <col=8A0808>Lumbridge Castle.<col=8A0808>", 275, 5+ 7);
			player.getPacketDispatch().sendString("<col=08088A>I must be unafraid of a <col=8A0808>Level 13 Skeleton ", 275, 6+ 7);
		}
		if (stage == 10) {
			player.getPacketDispatch().sendString("<str>Father Aereck asked me to help him deal with the Ghost in", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>the graveyard next to the church.", 275, 5+ 7);
			player.getPacketDispatch().sendString("<col=08088A>I should find <col=8A0808>Father Urhney</col><col=08088A> who is an expert on </col><col=8A0808>ghosts.</col>  ", 275, 6+ 7);
			player.getPacketDispatch().sendString("<col=08088A>He lives in a <col=8A0808>shack</col><col=08088A> in <col=8A0808>Lumbridge Swamp.", 275, 7+ 7);

		}
		if (stage == 20) {
			player.getPacketDispatch().sendString("<str>Father Aereck asked me to help him deal with the Ghost in", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>the graveyard next to the church.", 275, 5+ 7);
			player.getPacketDispatch().sendString("<str>I should find Father Urhney who is an expert on ghosts.</str>  ", 275, 6+ 7);
			player.getPacketDispatch().sendString("<str>He lives in a shack in Lumbridge Swamp.</str>", 275, 7+ 7);
			player.getPacketDispatch().sendString("<col=08088A>I should talk to the</col> <col=8A0808>Ghost</col> <col=08088A>to find out why it is haunting the", 275, 8+ 7);
			player.getPacketDispatch().sendString("<col=8A0808>graveyard crypt", 275, 9+ 7);
		}
		if (stage == 30) {
			player.getPacketDispatch().sendString("<str>Father Aereck asked me to help him deal with the Ghost in", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>the graveyard next to the church.", 275, 5+ 7);
			player.getPacketDispatch().sendString("<str>I found Father Urhney in the swamp south of Lumbridge.He</str>  ", 275, 6+ 7);
			player.getPacketDispatch().sendString("<str>gave me an Amulet of Ghostspeak to talk to the ghost.</str>", 275, 7+ 7);
			player.getPacketDispatch().sendString("<str>I spoke to the Ghost and he told me he could not rest in</str>", 275, 8+ 7);
			player.getPacketDispatch().sendString("<str>peace because an evil wizard had stolen his skull.</str>", 275, 9+ 7);
			player.getPacketDispatch().sendString("<col=08088A>I should go and search the <col=8A0808>Wizard's Tower South West of", 275, 10+ 7);
			player.getPacketDispatch().sendString("<col=8A0808>Lumbridge</col> <col=08088A>for the <col=8A0808>Ghost's Skull.", 275, 11+ 7);
		} 
		if (stage == 40) {
			player.getPacketDispatch().sendString("<str>Father Aereck asked me to help him deal with the Ghost in", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>the graveyard next to the church.", 275, 5+ 7);
			player.getPacketDispatch().sendString("<str>I found Father Urhney in the swamp south of Lumbridge.He</str>  ", 275, 6+ 7);
			player.getPacketDispatch().sendString("<str>gave me an Amulet of Ghostspeak to talk to the ghost.</str>", 275, 7+ 7);
			player.getPacketDispatch().sendString("<str>I spoke to the Ghost and he told me he could not rest in</str>", 275, 8+ 7);
			player.getPacketDispatch().sendString("<str>peace because an evil wizard had stolen his skull.</str>", 275, 9+ 7);
			player.getPacketDispatch().sendString("<str>I found the Ghost's Skull in the basement of the Wizards'", 275, 10+ 7);
			player.getPacketDispatch().sendString("<str>Tower. It was guarded by a skeleton, but I took it anyways.</str>", 275, 11+ 7);
			player.getPacketDispatch().sendString("<col=08088A>I should take the <col=8A0808>Skull</col> <col=08088A>back to the <col=8A0808>Ghost</col> <col=08088A>so it can rest.", 275, 12+ 7);
		}
		if (stage == 100) {
			player.getPacketDispatch().sendString("<str>Father Aereck asked me to help him deal with the Ghost in", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>the graveyard next to the church.", 275, 5+ 7);
			player.getPacketDispatch().sendString("<str>I found Father Urhney in the swamp south of Lumbridge.He</str>  ", 275, 6+ 7);
			player.getPacketDispatch().sendString("<str>gave me an Amulet of Ghostspeak to talk to the ghost.</str>", 275, 7+ 7);
			player.getPacketDispatch().sendString("<str>I spoke to the Ghost and he told me he could not rest in</str>", 275, 8+ 7);
			player.getPacketDispatch().sendString("<str>peace because an evil wizard had stolen his skull.</str>", 275, 9+ 7);
			player.getPacketDispatch().sendString("<str>I found the Ghost's Skull in the basement of the Wizards'", 275, 10+ 7);
			player.getPacketDispatch().sendString("<str>Tower. It was guarded by a skeleton, but I took it anyways.</str>", 275, 11+ 7);
			player.getPacketDispatch().sendString("<str>I placed the Skull in the Ghost's coffin, and allowed it to", 275, 12+ 7);
			player.getPacketDispatch().sendString("<str>rest in peace once more, with gratitude for my help.", 275, 13+ 7);
			player.getPacketDispatch().sendString("<col=FF0000>QUEST COMPLETE!", 275, 16+ 7);
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getPacketDispatch().sendString("1 Quest Point", 277, 8 + 2);
		player.getPacketDispatch().sendString("1125 Prayer XP", 277, 9 + 2);
		player.getPacketDispatch().sendItemZoomOnInterface(964, 240, 277, 3 + 2);
		player.getSkills().addExperience(Skills.PRAYER, 1125);
		player.getInterfaceManager().closeChatbox();
		player.getPacketDispatch().sendString("You have completed The Restless Ghost Quest!", 277, 2 + 2);
		player.getConfigManager().set(728, 31, true);
		player.getPacketDispatch().sendMessage("Congratulations! Quest complete!");
		player.getGameAttributes().removeAttribute("restless-ghost:urhney");
	}

}
