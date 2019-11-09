package plugin.interaction.inter;

import org.crandor.game.component.Component;
import org.crandor.game.component.ComponentDefinition;
import org.crandor.game.component.ComponentPlugin;
import org.crandor.game.content.global.Lamps;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.audio.Audio;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the experience lamp interface.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class ExperienceLampInterface extends ComponentPlugin {

	/**
	 * Represents the sound to send.
	 */
	private static final Audio SOUND = new Audio(1270, 12, 1);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(134, this);
		return this;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
		Item lamp = player.getAttribute("lamp", null);
		if (lamp == null) {
			return true;
		}
		if (button == 37 && !player.getQuestRepository().isComplete("Drudic Ritual")) {
			player.getPacketDispatch().sendMessage("You need to complete the Druidic Ritual quest to start that skill.");
			return true;
		}
		if (button == 52 && !player.getQuestRepository().isComplete("Wolf Whistle")) {
			player.getPacketDispatch().sendMessage("You need to complete the Wolf Whistle quest to start that skill.");
			return true;
		}
		if (button == 47 && !player.getQuestRepository().isComplete("Rune Mysteries")) {
			player.getPacketDispatch().sendMessage("You need to complete the Rune Mysteries quest to start that skill.");
			return true;
		}
		if (button != 2) {
			player.setAttribute("lamp:experience", button);
		}
		if (button == 2) {
			if (!player.getAttributes().containsKey("lamp:experience")) {
				player.getPacketDispatch().sendMessage("You need to pick the skill you wish to gain the experience in first.");
				return true;
			}
			final SkillInterface skillType = SkillInterface.forId((int) player.getAttribute("lamp:experience"));
			if (skillType == null || skillType.button == 24) {
				return true;
			}
			if (skillType == SkillInterface.RUNECRAFTING && !player.getQuestRepository().isComplete("Rune Mysteries")) {
				player.getPacketDispatch().sendMessage("You need to complete the Rune Mysteries quest to start that skill.");
				return true;
			}
			if (!player.getInventory().containsItem(lamp)) {
				return true;
			}
			Lamps type = Lamps.forItem(lamp);
			if (player.getSkills().getStaticLevel(skillType.skill) <= type.getLevelRequirement()) {
				player.sendMessage("You need a level in this skill which is greater than " + type.getLevelRequirement() + ".");
				return true;
			}
			player.getAudioManager().send(SOUND);
			player.getInventory().remove(lamp);
			player.getInterfaceManager().close();
			int skillLevel = player.getSkills().getStaticLevel(skillType.skill);
			int modifier = 10;
			double experience = skillLevel * modifier;
			if (type != null && type != Lamps.GENIE_LAMP) {
				player.getDialogueInterpreter().open(70099, new Object[] { "The lamp gives you " + (int) type.getExp() + " " + Skills.SKILL_NAME[skillType.skill] + " experience." });
				experience =  type.getExp() / Skills.EXPERIENCE_MULTIPLIER;
				int skill = skillType.skill;
				if (skill == Skills.ATTACK || skill == Skills.DEFENCE || skill == Skills.HITPOINTS || skill == Skills.STRENGTH || skill == Skills.STRENGTH || skill == Skills.MAGIC || skill == Skills.RANGE) {
					experience /= 2;	
				}
			} else {
				player.getDialogueInterpreter().open(70099, new Object[] { "The lamp gives you " + (experience * (Skills.EXPERIENCE_MULTIPLIER)) + " " + Skills.SKILL_NAME[skillType.skill] + " experience." });
			}
			player.getSkills().addExperience(skillType.skill, experience, false);
		}
		return true;
	}

	/**
	 * Representa a skill to gain experience.
	 * @author 'Vexia
	 */
	public enum SkillInterface {
		ATTACK(29, Skills.ATTACK),
		STRENGTH(30, Skills.STRENGTH),
		RANGE(32, Skills.RANGE),
		MAGIC(35, Skills.MAGIC),
		DEFENCE(31, Skills.DEFENCE),
		CRAFTING(39, Skills.CRAFTING),
		HITPOINTS(34, Skills.HITPOINTS),
		PRAYER(33, Skills.PRAYER),
		AGILITY(36, Skills.AGILITY),
		HERBLORE(37, Skills.HERBLORE),
		THIEVING(38, Skills.THIEVING),
		FISHING(43, Skills.FISHING),
		RUNECRAFTING(47, Skills.RUNECRAFTING),
		SLAYER(48, Skills.SLAYER),
		FARMING(50, Skills.FARMING),
		MINING(41, Skills.MINING),
		SMITHING(42, Skills.SMITHING),
		HUNTER(49, Skills.HUNTER),
		SUMMONING(52, Skills.SUMMONING),
		COOKING(45, Skills.COOKING),
		FIREMAKING(44, Skills.FIREMAKING),
		WOODCUTTING(46, Skills.WOODCUTTING),
		FLETCHING(40, Skills.FLETCHING);

		/**
		 * Constructs a new {@code ExperienceLampInterface} {@code Object}.
		 * @param button the button.
		 * @param skill the skill.
		 */
		SkillInterface(int button, int skill) {
			this.button = button;
			this.skill = skill;
		}

		/**
		 * The button id.
		 */
		private final int button;

		/**
		 * The skill id.
		 */
		private final int skill;

		/**
		 * Gets the skill type for the button.
		 * @param id the id.
		 * @return the skill type.
		 */
		public static SkillInterface forId(int id) {
			for (SkillInterface lamp : SkillInterface.values()) {
				if (lamp.button == id) {
					return lamp;
				}
			}
			return null;
		}
	}
}
