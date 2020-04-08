//package plugin.skill.fletching;
//
//import org.crandor.game.content.dialogue.SkillDialogueHandler;
//import org.crandor.game.content.dialogue.SkillDialogueHandler.SkillDialogue;
//import org.crandor.game.content.skill.member.fletching.items.gem.GemBolt;
//import org.crandor.game.content.skill.member.fletching.items.gem.GemBoltPulse;
//import org.crandor.game.interaction.NodeUsageEvent;
//import org.crandor.game.interaction.UseWithHandler;
//import org.crandor.game.node.entity.player.Player;
//import org.crandor.net.packet.PacketRepository;
//import org.crandor.net.packet.context.ChildPositionContext;
//import org.crandor.net.packet.out.RepositionChild;
//import org.crandor.plugin.InitializablePlugin;
//import org.crandor.plugin.Plugin;
//
///**
// * Represents the plugin used to handle gem bolt making.
// * @author 'Vexia
// * @version 1.0
// */
//@InitializablePlugin
//public class BoltGemPlugin extends UseWithHandler {
//
//	/**
//	 * Constructs a new {@code BoltGemPlugin} {@code Object}.
//	 */
//	public BoltGemPlugin() {
//		super(45, 46, 9187, 9188, 9189, 9190, 9191, 9192, 9193, 9194);
//	}
//
//	@Override
//	public Plugin<Object> newInstance(Object arg) throws Throwable {
//		for (GemBolt bolt : GemBolt.values()) {
//			addHandler(bolt.getBase().getId(), ITEM_TYPE, this);
//		}
//		return this;
//	}
//
//	@Override
//	public boolean handle(final NodeUsageEvent event) {
//		final Player player = event.getPlayer();
//
//	}
//
//}
