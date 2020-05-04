package plugin.interaction.inter;

import org.crandor.game.component.Component;
import org.crandor.game.component.ComponentDefinition;
import org.crandor.game.component.ComponentPlugin;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.free.crafting.GlassProduct;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.RunScript;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the glass making interface plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class GlassInterface extends ComponentPlugin {

	/**
	 * Represents the animation to use.
	 */
	private static final Animation ANIMATION = new Animation(884);

	/**
	 * Represents the molten glass item.
	 */
	private static final Item MOLTEN_GLASS = new Item(1775);

	/**
	 * Represents the soda ash item.
	 */
	private static final Item SODA_ASH = new Item(1781, 1);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(542, this);
		return this;
	}

	@Override
	public boolean handle(final Player p, Component component, int opcode, int button, int slot, int itemId) {
		final GlassProduct def = GlassProduct.forId(button);
		if (def == null) {
			return true;
		}
		if (!p.getInventory().contains(1785, 1)) {
			p.getPacketDispatch().sendMessage("You need a glassblowing pipe to do this.");
			return true;
		}
		if (!p.getInventory().contains(1775, 1)) {
			p.getPacketDispatch().sendMessage("You need molten glass to do this.");
			return true;
		}
		if (p.getSkills().getLevel(Skills.CRAFTING) < def.getLevel()) {
			p.getPacketDispatch().sendMessage("You need a crafting level of " + def.getLevel() + " to make this.");
			return true;
		}
		int real = -1;
		switch (opcode) {
		case 230:
			real = 1;
			break;
		case 205:
			real = 5;
			break;
		case 127:
			real = p.getInventory().getAmount(MOLTEN_GLASS);
			break;
		case 211:
			p.setAttribute("runscript", new RunScript() {
				@Override
				public boolean handle() {
					int ammount = (int) value;
					make(p, def, ammount);
					return true;
				}
			});
			p.getDialogueInterpreter().sendInput(false, "Enter the amount.");
			break;
		}
		if (opcode == 211) {
			return true;
		}
		make(p, def, real);
		return true;
	}

	/**
	 * Method used to make a glass product.
	 * @param player the player.
	 * @param glass the glass.
	 * @param amount the amount.
	 */
	public static void make(final Player player, final GlassProduct glass, final int amount) {
		player.getInterfaceManager().close();
		player.animate(ANIMATION);
		GameWorld.Pulser.submit(new Pulse(2, player) {
			int amt = amount;

			@Override
			public boolean pulse() {
				if (!player.getInventory().contains(1775, 1) || amt == 0) {
					return true;
				}
				player.animate(ANIMATION);
				player.getInventory().remove(MOLTEN_GLASS);
				player.getInventory().remove(SODA_ASH);
				player.getInventory().add(new Item(glass.getProduct(), 1));
				player.getSkills().addExperience(Skills.CRAFTING, glass.getExperience(), true);
				player.getPacketDispatch().sendMessage("You make a " + new Item(glass.getProduct()).getName().toLowerCase().replace("unpowered", "").trim() + ".");
				amt--;
				return false;
			}

		});
	}
}
