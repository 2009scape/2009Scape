package plugin.skill.runecrafting;

import core.cache.def.impl.ItemDefinition;
import core.cache.def.impl.NPCDefinition;
import core.cache.def.impl.ObjectDefinition;
import core.game.component.Component;
import core.game.content.global.action.ClimbActionHandler;
import core.game.content.global.travel.EssenceTeleport;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.Rights;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.net.packet.PacketRepository;
import core.net.packet.context.MinimapStateContext;
import core.net.packet.out.MinimapState;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManager;
import plugin.quest.free.RuneMysteries;
import plugin.skill.runecrafting.abyss.AbyssPlugin;

/**
 * Handles runecraftign related options.
 * @author Vexia
 */
@InitializablePlugin
public class RunecraftingPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addNodes();
		PluginManager.definePlugin(new AbyssPlugin());
		PluginManager.definePlugin(new TiaraPlugin());
		PluginManager.definePlugin(new RunePouchPlugin());
		PluginManager.definePlugin(new EnchantTiaraPlugin());
		PluginManager.definePlugin(new MysteriousRuinPlugin());
		PluginManager.definePlugin(new CombinationRunePlugin());
		ObjectDefinition.forId(2492).getHandlers().put("option:use", this);
		NPCDefinition.forId(553).getHandlers().put("option:teleport", this);
		NPCDefinition.forId(2328).getHandlers().put("option:teleport", this);
		ObjectDefinition.forId(26849).getHandlers().put("option:climb", this);
		ObjectDefinition.forId(26850).getHandlers().put("option:climb", this);
		ObjectDefinition.forId(268).getHandlers().put("option:climb", this);
		ObjectDefinition.forId(26844).getHandlers().put("option:squeeze-through", this);
		ObjectDefinition.forId(26845).getHandlers().put("option:squeeze-through", this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		if (!player.getQuestRepository().isComplete(RuneMysteries.NAME) && player.getDetails().getRights() != Rights.ADMINISTRATOR) {
			player.getPacketDispatch().sendMessage("You need to finish the Rune Mysteries Quest in order to do this.");
			return true;
		}
		switch (node.getId()) {
		case 2492:
			EssenceTeleport.home(player);
			return true;
		case 26844:
		case 26845:
			final Location location = node.getId() == 26845 ? new Location(3309, 4820, 0) : new Location(3311, 4817, 0);
			player.lock(4);
			player.getInterfaceManager().openOverlay(new Component(115));
			PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 1));
			GameWorld.getPulser().submit(new Pulse(4, player) {

				@Override
				public boolean pulse() {
					player.teleport(location);
					player.getInterfaceManager().close();
					player.getInterfaceManager().closeOverlay();
					PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
					return true;
				}

			});
			return true;
		case 553:
		case 2328:
		case 844:
			EssenceTeleport.teleport(((NPC) node), player);
			return true;
		}
		switch (option) {
		case "use":
			final Altar altar = Altar.forObject(((GameObject) node));
			player.getProperties().setTeleportLocation(altar.getRuin().getBase());
			break;
		case "craft-rune":
			if(node.getLocation().equals(new Location(3151, 3484))){
				player.sendMessage("You can only craft Astral runes on Lunar Isle.");
				return true;
			}
			player.getPulseManager().run(new RuneCraftPulse(player, null, Altar.forObject(((GameObject) node)), false, null));
			break;
		case "locate":
			final Talisman talisman = Talisman.forItem(((Item) node));
			talisman.locate(player);
			break;
		case "enter":
			final MysteriousRuin ruin = MysteriousRuin.forObject(((GameObject) node));
			if (ruin == null) {
				return true;
			}
			ruin.enter(player);
			break;
		case "climb":
			int id = ((GameObject) node).getId();
			switch (id) {
			case 26849:
				ClimbActionHandler.climb(player, null, new Location(3271, 4861, 0));
				break;
			case 26850:
				ClimbActionHandler.climb(player, null, new Location(2452, 3230, 0));
				break;
			}
			break;
		}
		return true;
	}

	/**
	 * Adds the nodes to this plugin.
	 */
	private void addNodes() {
		for (Altar altar : Altar.values()) {
			ObjectDefinition.forId(altar.getObject()).getHandlers().put("option:craft-rune", this);
			ObjectDefinition.forId(altar.getPortal()).getHandlers().put("option:use", this);
		}
		for (MysteriousRuin ruin : MysteriousRuin.values()) {
			for (int i : ruin.getObject()) {
				ObjectDefinition.forId(i).getHandlers().put("option:enter", this);
			}
		}
		for (Talisman talisman : Talisman.values()) {
			ItemDefinition.forId(talisman.getTalisman().getId()).getHandlers().put("option:locate", this);
		}
	}

	@Override
	public boolean isWalk() {
		return false;
	}

	@Override
	public boolean isWalk(final Player player, final Node node) {
		return !(node instanceof Item);
	}

}
