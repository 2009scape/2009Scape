package core.game.node.entity.combat.equipment;

import core.game.component.Component;
import core.game.component.ComponentDefinition;
import core.game.component.InterfaceType;
import core.game.container.impl.EquipmentContainer;
import core.game.node.entity.combat.CombatSpell;
import core.game.node.entity.impl.Animator.Priority;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.SpellBookManager;
import core.game.node.item.Item;
import core.game.system.config.ItemConfigParser;
import core.game.world.update.flag.context.Animation;
import core.net.packet.PacketRepository;
import core.net.packet.context.InterfaceConfigContext;
import core.net.packet.context.InterfaceContext;
import core.net.packet.context.StringContext;
import core.net.packet.out.Interface;
import core.net.packet.out.InterfaceConfig;
import core.net.packet.out.StringPacket;

import static core.tools.Interfaces.*;

/**
 * Represents the weapon interface component.
 * @author Emperor
 */
public final class WeaponInterface extends Component {

	/**
	 * The modern spell ids.
	 */
	private static final int[] MODERN_SPELL_IDS = { 1, 4, 6, 8, 10, 14, 17, 20, 24, 27, 33, 38, 45, 48, 52, 55 };

	/**
	 * The ancient spell ids.
	 */
	private static final int[] ANCIENT_SPELL_IDS = { 8, 12, 4, 0, 10, 14, 6, 2, 9, 13, 5, 1, 11, 15, 7, 3, 16, 17, 18, 19 };

	/**
	 * The slayer staff spell ids
	 */
	private static final int[] SLAYER_STAFF_SPELL_IDS = { 22, 31, 45, 48, 52, 55 };

	/**
	 * The void staff spell ids
	 */
	private static final int[] VOID_STAFF_SPELL_IDS = { 22, 42, 45, 48, 52, 55 };

	/**
	 * The default attack animations.
	 */
	public static final Animation[] DEFAULT_ANIMS = { new Animation(422, Priority.HIGH), new Animation(423, Priority.HIGH), new Animation(422, Priority.HIGH), new Animation(422, Priority.HIGH) };

	/**
	 * The stab equipment bonus index.
	 */
	public static final int BONUS_STAB = 0;

	/**
	 * The slash equipment bonus index.
	 */
	public static final int BONUS_SLASH = 1;

	/**
	 * The crush equipment bonus index.
	 */
	public static final int BONUS_CRUSH = 2;

	/**
	 * The magic equipment bonus index.
	 */
	public static final int BONUS_MAGIC = 3;

	/**
	 * The range equipment bonus index.
	 */
	public static final int BONUS_RANGE = 4;

	/**
	 * The accurate melee attack style
	 */
	public static final int STYLE_ACCURATE = 0;

	/**
	 * The aggressive attack style
	 */
	public static final int STYLE_AGGRESSIVE = 1;

	/**
	 * The controlled attack style
	 */
	public static final int STYLE_CONTROLLED = 2;

	/**
	 * The defensive attack style
	 */
	public static final int STYLE_DEFENSIVE = 3;

	/**
	 * The accurate range attack style
	 */
	public static final int STYLE_RANGE_ACCURATE = 4;

	/**
	 * The rapid range attack style
	 */
	public static final int STYLE_RAPID = 5;

	/**
	 * The long range attack style
	 */
	public static final int STYLE_LONG_RANGE = 6;

	/**
	 * The defensive spell cast attack style
	 */
	public static final int STYLE_DEFENSIVE_CAST = 7;

	/**
	 * The spell cast attack style
	 */
	public static final int STYLE_CAST = 8;

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The current weapon interface.
	 */
	private WeaponInterfaces current;

	/**
	 * If the player has the special attack bar shown.
	 */
	private boolean specialBar;

	/**
	 * The player's attack animations.
	 */
	private Animation[] attackAnimations;

	/**
	 * Constructs a new {@code WeaponInterface} {@code Object}.
	 * @param player The player.
	 */
	public WeaponInterface(Player player) {
		super(92);
		this.player = player;
		player.addExtension(WeaponInterface.class, this);
	}

	@Override
	public void open(Player player) {
		current = null;
		updateInterface();
	}

	/**
	 * Opens the interface.
	 */
	private void open() {
		ComponentDefinition definition = ComponentDefinition.forId(WEAPON_FISTS_SEL_92);
		boolean resizable = player.getInterfaceManager().isResizable();
		PacketRepository.send(Interface.class, new InterfaceContext(player, definition.getWindowPaneId(resizable), definition.getChildId(resizable), id, definition.isWalkable()));
		int slot = ensureStyleIndex(player, player.getSettings().getAttackStyleIndex());
		if (slot != player.getSettings().getAttackStyleIndex()) {
			player.getSettings().toggleAttackStyleIndex(slot);
		}
		player.getProperties().setAttackStyle(current.getAttackStyles()[slot]);
		checkStaffConfigs(slot);
	}

	/**
	 * Ensures the style index.
	 * @param player The player.
	 * @param slot The attack style index.
	 * @return The index, ensured to be smaller than styles.length and larger
	 * than 0.
	 */
	private int ensureStyleIndex(Player player, int slot) {
		AttackStyle style = player.getProperties().getAttackStyle();
		if (slot >= current.getAttackStyles().length) {
			slot = current.getAttackStyles().length - 1;
			if (style != null) {
				for (int i = slot; i >= 0; i--) {
					if (current.getAttackStyles()[i].style == style.style) {
						return i;
					}
				}
			}
			return slot;
		}
		if (style != null && current.getAttackStyles()[slot].style != style.style) {
			for (int i = current.getAttackStyles().length - 1; i >= 0; i--) {
				if (current.getAttackStyles()[i].style == style.style) {
					return i;
				}
			}
		}
		return slot;
	}

	/**
	 * Get the proper config for the special bar.
	 * @return the config.
	 */
	private int getConfig(int buttons, int interfaceId){
		if(interfaceId == WEAPON_STAFF_SEL_90){
			return 87;//Return Config
		}
		if(interfaceId != WEAPON_WHIP_SEL_93 &&
                interfaceId != WEAPON_WARHAMMER_SEL_76 &&
                interfaceId != WEAPON_XBOW_SEL_79 &&
                interfaceId != WEAPON_HALBERD_SEL_84 &&
                interfaceId != WEAPON_THROWN_SEL_91)
		{
			switch(buttons){
			case 3:
				return 13;
			case 4:
				return 12;
			}	
		} else {
			return 10;
		}
		return 10;
	}
	
	/**
	 * Updates the interface.
	 */
	public void updateInterface() {
		player.getInterfaceManager().getTabs()[0] = this;
		Item weapon = player.getEquipment().get(EquipmentContainer.SLOT_WEAPON);
		WeaponInterfaces inter = getWeaponInterface(weapon);
		String name;
		if (weapon != null) {
			name = weapon.getDefinition().getName();
			specialBar = weapon.getDefinition().getConfiguration(ItemConfigParser.HAS_SPECIAL, false);
			attackAnimations = weapon.getDefinition().getConfiguration(ItemConfigParser.ATTACK_ANIMS, DEFAULT_ANIMS);
		} else {
			name = "Unarmed";
			specialBar = false;
			attackAnimations = DEFAULT_ANIMS;
		}
		if (inter != current) {
			id = inter.interfaceId;
			current = inter;
			open();
			player.getProperties().getCombatPulse().updateStyle();
		}
		if (player.getSettings().getAttackStyleIndex() < attackAnimations.length && !player.getAppearance().isNpc()) {
			player.getProperties().setAttackAnimation(attackAnimations[player.getSettings().getAttackStyleIndex()]);
		}
		if (current != WeaponInterfaces.STAFF) {
			selectAutoSpell(-1, false);
			PacketRepository.send(InterfaceConfig.class, new InterfaceConfigContext(player, id, getConfig(current.getAttackStyles().length, current.getInterfaceId()), !specialBar));
		} else { //if staff
			PacketRepository.send(InterfaceConfig.class, new InterfaceConfigContext(player, id, 87, !specialBar));
		}
		if (!canAutocast(false)) {
			if (current == WeaponInterfaces.STAFF && player.getAttribute("autocast_select", false)) {
				open();
			}
			selectAutoSpell(-1, true);
		}
		PacketRepository.send(StringPacket.class, new StringContext(player, name, id, 0));
		if (player.getSettings().isSpecialToggled()) {
			player.getSettings().toggleSpecialBar();
		}
	}

	/**
	 * Sets the current attack style.
	 * @param button The button the player has pressed.
	 * @return {@code True} if the attack style got set.
	 */
	public boolean setAttackStyle(int button) {
		int slot = button - 1;
		if (current != WeaponInterfaces.STAFF) {
			slot--;
		}
		if (current == WeaponInterfaces.WARHAMMER_MAUL || (current.attackStyles.length > 2 && current.attackStyles[2].bonusType == BONUS_RANGE && current.getInterfaceId() != WEAPON_THROWN_SEL_91)) {
			slot = button == 4 ? 1 : button == 3 ? 2 : 0;
		} else if (current == WeaponInterfaces.CLAWS) {
			slot = button == 5 ? 1 : button == 3 ? 3 : slot;
		}
		if (current == WeaponInterfaces.AXE) {
			if (button == 5) {
				slot = 1;
			} else if (button == 3) {
				slot = 3;
			}
		}
		if (slot < 0 || slot >= current.getAttackStyles().length) {
			return false;
		}
		AttackStyle style = current.getAttackStyles()[slot];
		player.getProperties().setAttackStyle(style);
		player.getSettings().toggleAttackStyleIndex(slot);
		if (slot < attackAnimations.length && !player.getAppearance().isNpc()) {
			player.getProperties().setAttackAnimation(attackAnimations[slot]);
		}
		checkStaffConfigs(button - 1);
		return true;
	}

	/**
	 * Checks the staff configurations.
	 * @param slot The slot of the current attack style selected.
	 */
	private void checkStaffConfigs(int slot) {
		if (current != WeaponInterfaces.STAFF) {
			selectAutoSpell(-1, false);
			return;
		}
		boolean defensive = slot == 3;
		player.getConfigManager().set(439, defensive ? -5 : 0);
		if (slot > 2) {
			player.getConfigManager().set(43, defensive ? -1 : 3);
		}
	};

	/**
	 * Gets the autocast tab component id.
	 * @param spellId The spell id.
	 * @return The component id for the autocast select tab.
	 */
	public int getAutospellId(int spellId) {
		boolean modern = player.getSpellBookManager().getSpellBook() == MAGIC_192;
		int[] data = modern ? MODERN_SPELL_IDS : ANCIENT_SPELL_IDS;
		if (modern && player.getEquipment().getNew(3).getName().equalsIgnoreCase("Slayer's staff")) {
			data = SLAYER_STAFF_SPELL_IDS;
		}
		if (modern && player.getEquipment().getNew(3).getName().equalsIgnoreCase("Void knight mace")) {
			data = VOID_STAFF_SPELL_IDS;
		}
		for (int i = 0; i < data.length; i++) {
			if (data[i] == spellId) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Selects the current autocast spell.
	 * @param buttonId The button id.
	 * @param adjustAttackStyle If the attack style should be adjusted.
	 */
	public void selectAutoSpell(int buttonId, boolean adjustAttackStyle) {
		boolean modern = player.getSpellBookManager().getSpellBook() == MAGIC_192;
		int[] data = modern ? MODERN_SPELL_IDS : ANCIENT_SPELL_IDS;
		if (modern && player.getEquipment().getNew(3).getName().equalsIgnoreCase("Slayer's staff")) {
			data = SLAYER_STAFF_SPELL_IDS;
		}
		if (modern && player.getEquipment().getNew(3).getName().equalsIgnoreCase("Void knight mace")) {
			data = VOID_STAFF_SPELL_IDS;
		}
		CombatSpell current = player.getProperties().getAutocastSpell();
		if (buttonId >= data.length) {
			return;
		}
		int configStart = modern ? 45 : 13;
		if (current != null) {
			for (int index = 0; index < data.length; index++) {
				if (data[index] == current.getSpellId()) {
					player.getPacketDispatch().sendInterfaceConfig(WEAPON_STAFF_SEL_90, configStart + (2 * index), true);
					player.getPacketDispatch().sendInterfaceConfig(WEAPON_STAFF_SEL_90, 100 + configStart + (2 * index), true);
				}
			}
		}
		if (buttonId < 0) {
			player.getProperties().setAutocastSpell(null);
			if (adjustAttackStyle && current != null) {
				setAttackStyle(3);
				player.getProperties().getCombatPulse().updateStyle();
			}
			return;
		}
		boolean defensive = player.getSettings().getAttackStyleIndex() == 3;
		player.getPacketDispatch().sendInterfaceConfig(WEAPON_STAFF_SEL_90, 183, defensive);
		player.getPacketDispatch().sendInterfaceConfig(WEAPON_STAFF_SEL_90, 83, !defensive);
		current = (CombatSpell) (modern ? SpellBookManager.SpellBook.MODERN.getSpell(data[buttonId]) : SpellBookManager.SpellBook.ANCIENT.getSpell(data[buttonId]));
		player.getProperties().setAutocastSpell(current);
		int configId = configStart + (2 * buttonId);
		if (modern && player.getEquipment().getNew(3).getName().equalsIgnoreCase("Slayer's staff") || modern && player.getEquipment().getNew(3).getName().equalsIgnoreCase("Void knight mace")) {
			boolean slayer = player.getEquipment().getNew(3).getName().equalsIgnoreCase("Slayer's staff");
			switch (buttonId) {
			case 0:
				configId = 77;
				break;
			case 1:
				if (slayer) {
					configId = 79;
				} else {
					configId = 81;
				}
				break;
			case 2:
				configId = 69;
				break;
			case 3:
				configId = 71;
				break;
			case 4:
				configId = 73;
				break;
			case 5:
				configId = 75;
				break;

			}
			if (configId >= 85 || configId <= 65) {
				return;
			} else {
				player.getPacketDispatch().sendInterfaceConfig(WEAPON_STAFF_SEL_90, (defensive ? 100 : 0) + configId, false);
			}
		} else {
			configId += defensive ? 100 : 0;
			switch (buttonId) {
			case 16:
				configId = defensive ? 265 : 253;
				break;
			case 17:
				configId = defensive ? 268 : 256;
				break;
			case 18:
				configId = defensive ? 271 : 259;
				break;
			case 19:
				configId = defensive ? 274 : 262;
				break;
			}
			player.getPacketDispatch().sendInterfaceConfig(WEAPON_STAFF_SEL_90, configId, false);
		}

	}

	/**
	 * Opens the autocast select.
     */
	public void openAutocastSelect() {
		if (current != WeaponInterfaces.STAFF) {
			return;
		}
		if (!canAutocast(true)) {
			setAttackStyle(3);
			return;
		}
		player.setAttribute("autocast_select", true);
		int id = player.getSpellBookManager().getSpellBook() == 193 ? 797 : 319;
		boolean slayer = player.getEquipment().getNew(3).getName().equalsIgnoreCase("Slayer's staff");
		boolean mace = player.getEquipment().getNew(3).getName().equalsIgnoreCase("Void knight mace");
		if (slayer) {
			id = 310;
		}
		if (mace) {
			id = 406;
		}
		Component component = new Component(id);
		component.getDefinition().setTabIndex(0);
		component.getDefinition().setType(InterfaceType.TAB);
		player.getInterfaceManager().openTab(component);
	}

	/**
	 * Checks if the player is currently able to autocast.
	 * @param message If we should notify the player if he's unable to autocast.
	 * @return {@code True} if so.
	 */
	public boolean canAutocast(boolean message) {
		if (current != WeaponInterfaces.STAFF) {
			return false;
		}
		if (player.getSpellBookManager().getSpellBook() == SpellBookManager.SpellBook.LUNAR.getInterfaceId()) {
			if (message) {
				player.getPacketDispatch().sendMessage("You can't autocast Lunar magic.");
			}
			return false;
		}
		boolean ancientStaff = player.getEquipment().getNew(3).getName().contains("ncient staff") || player.getEquipment().getNew(3).getName().contains("uriel's staff");;
		if ((player.getSpellBookManager().getSpellBook() == MAGIC_192 && ancientStaff) || (player.getSpellBookManager().getSpellBook() == MAGIC_ZAROS_193 && !ancientStaff)) {
			if (message) {
				player.getPacketDispatch().sendMessage("You can only autocast ancient magicks with an Ancient or Zuriel's staff.");
			}
			return false;
		}
		return true;
	}

	/**
	 * Gets the current weapon interface id.
	 * @return The component id.
	 */
	public static WeaponInterfaces getWeaponInterface(Item weapon) {
		if (weapon == null) {
			return WeaponInterfaces.values()[0];
		}
		int slot = weapon.getDefinition().getConfiguration(ItemConfigParser.WEAPON_INTERFACE, 0);
		return WeaponInterfaces.values()[slot];
	}

	/**
	 * Represents an attack style.
	 * @author Emperor
	 */
	public static class AttackStyle {

		/**
		 * The style type.
		 */
		private final int style;

		/**
		 * The bonus type.
		 */
		private final int bonusType;

		/**
		 * Constructs a new {@code AttackStyle} {@code Object}.
		 * @param style The style type.
		 * @param bonusType The bonus type.
		 */
		public AttackStyle(int style, int bonusType) {
			this.style = style;
			this.bonusType = bonusType;
		}

		/**
		 * Gets the style.
		 * @return The style.
		 */
		public int getStyle() {
			return style;
		}

		/**
		 * Gets the bonusType.
		 * @return The bonusType.
		 */
		public int getBonusType() {
			return bonusType;
		}
	}

	/**
	 * Represents the weapon interfaces.
	 * @author Emperor
	 */
	public static enum WeaponInterfaces {

		/**
		 * The unarmed weapon interface (ordinal=0)
		 */
		UNARMED(WEAPON_FISTS_SEL_92, new AttackStyle(STYLE_ACCURATE, BONUS_CRUSH), new AttackStyle(STYLE_AGGRESSIVE, BONUS_CRUSH), new AttackStyle(STYLE_DEFENSIVE, BONUS_CRUSH)),

		/**
		 * The staff weapon interface (ordinal=1)
		 */
		STAFF(WEAPON_STAFF_SEL_90, new AttackStyle(STYLE_ACCURATE, BONUS_CRUSH), new AttackStyle(STYLE_AGGRESSIVE, BONUS_CRUSH), new AttackStyle(STYLE_DEFENSIVE, BONUS_CRUSH), new AttackStyle(STYLE_DEFENSIVE_CAST, BONUS_MAGIC), new AttackStyle(STYLE_CAST, BONUS_MAGIC)),

		/**
		 * The (battle) axe weapon interface (ordinal=2)
		 */
		AXE(WEAPON_BAXE_SEL_75, new AttackStyle(STYLE_ACCURATE, BONUS_SLASH), new AttackStyle(STYLE_AGGRESSIVE, BONUS_SLASH), new AttackStyle(STYLE_AGGRESSIVE, BONUS_CRUSH), new AttackStyle(STYLE_DEFENSIVE, BONUS_SLASH)),

		/**
		 * The scepter weapon interface (ordinal=3)
		 */
		SCEPTER(WEAPON_SCEPTER_SEL_85, new AttackStyle(STYLE_ACCURATE, BONUS_CRUSH), new AttackStyle(STYLE_AGGRESSIVE, BONUS_CRUSH), new AttackStyle(STYLE_DEFENSIVE, BONUS_CRUSH)),

		/**
		 * The pickaxe weapon interface (ordinal=4)
		 */
		PICKAXE(WEAPON_PICKAXE_SEL_83, new AttackStyle(STYLE_ACCURATE, BONUS_STAB), new AttackStyle(STYLE_AGGRESSIVE, BONUS_STAB), new AttackStyle(STYLE_AGGRESSIVE, BONUS_CRUSH), new AttackStyle(STYLE_DEFENSIVE, BONUS_STAB)),

		/**
		 * The sword/dagger weapon interface (ordinal=5)
		 */
		SWORD_DAGGER(WEAPON_DAGGER_SEL_89, new AttackStyle(STYLE_ACCURATE, BONUS_STAB), new AttackStyle(STYLE_AGGRESSIVE, BONUS_STAB), new AttackStyle(STYLE_AGGRESSIVE, BONUS_SLASH), new AttackStyle(STYLE_DEFENSIVE, BONUS_STAB)),

		/**
		 * The scimitar/silverlight/silver sickle/... weapon interface
		 * (ordinal=6)
		 */
		SCIMITAR(WEAPON_SCIMITAR_SEL_81, new AttackStyle(STYLE_ACCURATE, BONUS_SLASH), new AttackStyle(STYLE_AGGRESSIVE, BONUS_SLASH), new AttackStyle(STYLE_CONTROLLED, BONUS_STAB), new AttackStyle(STYLE_DEFENSIVE, BONUS_SLASH)),

		/**
		 * The 2-h sword weapon interface (ordinal=7)
		 */
		TWO_H_SWORD(WEAPON_2H_SWORD_SEL_82, new AttackStyle(STYLE_ACCURATE, BONUS_SLASH), new AttackStyle(STYLE_AGGRESSIVE, BONUS_SLASH), new AttackStyle(STYLE_AGGRESSIVE, BONUS_CRUSH), new AttackStyle(STYLE_DEFENSIVE, BONUS_SLASH)),

		/**
		 * The mace weapon interface (ordinal=8)
		 */
		MACE(WEAPON_MACE_SEL_88, new AttackStyle(STYLE_ACCURATE, BONUS_CRUSH), new AttackStyle(STYLE_AGGRESSIVE, BONUS_CRUSH), new AttackStyle(STYLE_CONTROLLED, BONUS_STAB), new AttackStyle(STYLE_DEFENSIVE, BONUS_CRUSH)),

		/**
		 * The claws weapon interface (ordinal=9)
		 */
		CLAWS(WEAPON_CLAWS_SEL_78, new AttackStyle(STYLE_ACCURATE, BONUS_SLASH), new AttackStyle(STYLE_AGGRESSIVE, BONUS_SLASH), new AttackStyle(STYLE_CONTROLLED, BONUS_STAB), new AttackStyle(STYLE_DEFENSIVE, BONUS_SLASH)),

		/**
		 * The warhammer/maul weapon interface (ordinal=10)
		 */
		WARHAMMER_MAUL(WEAPON_WARHAMMER_SEL_76, new AttackStyle(STYLE_ACCURATE, BONUS_CRUSH), new AttackStyle(STYLE_AGGRESSIVE, BONUS_CRUSH), new AttackStyle(STYLE_DEFENSIVE, BONUS_CRUSH)),

		/**
		 * The abyssal whip weapon interface (ordinal=11)
		 */
		WHIP(WEAPON_WHIP_SEL_93, new AttackStyle(STYLE_ACCURATE, BONUS_SLASH), new AttackStyle(STYLE_CONTROLLED, BONUS_SLASH), new AttackStyle(STYLE_DEFENSIVE, BONUS_SLASH)),

		/**
		 * The flowers weapon interface (ordinal=12)
		 */
		FLOWERS(WEAPON_WARHAMMER_SEL_76, new AttackStyle(STYLE_ACCURATE, BONUS_CRUSH), new AttackStyle(STYLE_AGGRESSIVE, BONUS_CRUSH), new AttackStyle(STYLE_DEFENSIVE, BONUS_CRUSH)),

		/**
		 * The mud pie weapon interface (ordinal=13)
		 */
		MUD_PIE(WEAPON_THROWN_SEL_91, new AttackStyle(STYLE_RANGE_ACCURATE, BONUS_RANGE), new AttackStyle(STYLE_RAPID, BONUS_RANGE), new AttackStyle(STYLE_LONG_RANGE, BONUS_RANGE)),

		/**
		 * The spear weapon interface (ordinal=14)
		 */
		SPEAR(WEAPON_SPEAR_SEL_87, new AttackStyle(STYLE_CONTROLLED, BONUS_STAB), new AttackStyle(STYLE_CONTROLLED, BONUS_SLASH), new AttackStyle(STYLE_CONTROLLED, BONUS_CRUSH), new AttackStyle(STYLE_DEFENSIVE, BONUS_STAB)),

		/**
		 * The halberd weapon interface (ordinal=15)
		 */
		HALBERD(WEAPON_HALBERD_SEL_84, new AttackStyle(STYLE_CONTROLLED, BONUS_STAB), new AttackStyle(STYLE_AGGRESSIVE, BONUS_SLASH), new AttackStyle(STYLE_DEFENSIVE, BONUS_STAB)),

		/**
		 * The bow weapon interface (ordinal=16)
		 */
		BOW(WEAPON_BOW_SEL_77, new AttackStyle(STYLE_RANGE_ACCURATE, BONUS_RANGE), new AttackStyle(STYLE_RAPID, BONUS_RANGE), new AttackStyle(STYLE_LONG_RANGE, BONUS_RANGE)),

		/**
		 * The crossbow weapon interface (ordinal=17)
		 */
		CROSSBOW(WEAPON_XBOW_SEL_79, new AttackStyle(STYLE_RANGE_ACCURATE, BONUS_RANGE), new AttackStyle(STYLE_RAPID, BONUS_RANGE), new AttackStyle(STYLE_LONG_RANGE, BONUS_RANGE)),

		/**
		 * The thrown weapons weapon interface (ordinal=18)
		 */
		THROWN_WEAPONS(WEAPON_THROWN_SEL_91, new AttackStyle(STYLE_RANGE_ACCURATE, BONUS_RANGE), new AttackStyle(STYLE_RAPID, BONUS_RANGE), new AttackStyle(STYLE_LONG_RANGE, BONUS_RANGE)),

		/**
		 * The thrown weapons weapon interface (ordinal=19)
		 */
		CHINCHOMPA(WEAPON_CHINCHOMPA_SEL_473, new AttackStyle(STYLE_RANGE_ACCURATE, BONUS_RANGE), new AttackStyle(STYLE_RAPID, BONUS_RANGE), new AttackStyle(STYLE_LONG_RANGE, BONUS_RANGE)),

		/**
		 * The fixed device weapon interface (ordinal=20)
		 */
		FIXED_DEVICE(WEAPON_FIXED_DEVICE_SEL_80, new AttackStyle(STYLE_RANGE_ACCURATE, BONUS_RANGE), new AttackStyle(STYLE_AGGRESSIVE, BONUS_CRUSH)),

		/**
		 * The salamander weapon interface (ordinal=21)
		 */
		SALAMANDER(WEAPON_SALAMANDER_SEL_474, new AttackStyle(STYLE_AGGRESSIVE, BONUS_SLASH), new AttackStyle(STYLE_RANGE_ACCURATE, BONUS_RANGE), new AttackStyle(STYLE_DEFENSIVE_CAST, BONUS_MAGIC)),

		/**
		 * The scythe weapon interface (ordinal=22)
		 */
		SCYTHE(WEAPON_SCYTHE_SEL_86, new AttackStyle(STYLE_ACCURATE, BONUS_SLASH), new AttackStyle(STYLE_AGGRESSIVE, BONUS_STAB), new AttackStyle(STYLE_AGGRESSIVE, BONUS_CRUSH), new AttackStyle(STYLE_DEFENSIVE, BONUS_SLASH)),

		/**
		 * The ivandis flail weapon interface (ordinal=23) TODO: Find correct
		 * interface id!
		 */
		IVANDIS_FLAIL(WEAPON_SCEPTER_SEL_85, new AttackStyle(STYLE_ACCURATE, BONUS_CRUSH), new AttackStyle(STYLE_AGGRESSIVE, BONUS_CRUSH), new AttackStyle(STYLE_DEFENSIVE, BONUS_CRUSH));

		/**
		 * The interface id.
		 */
		private final int interfaceId;

		/**
		 * The attack styles.
		 */
		private final AttackStyle[] attackStyles;

		/**
		 * Constructs a new {@code WeaponInterface} {@code Object}.
		 * @param interfaceId The interface id.
		 * @param attackStyles The attack styles.
		 */
		private WeaponInterfaces(int interfaceId, AttackStyle... attackStyles) {
			this.interfaceId = interfaceId;
			this.attackStyles = attackStyles;
		}

		/**
		 * Gets the interfaceId.
		 * @return The interfaceId.
		 */
		public int getInterfaceId() {
			return interfaceId;
		}

		/**
		 * Gets the attackStyles.
		 * @return The attackStyles.
		 */
		public AttackStyle[] getAttackStyles() {
			return attackStyles;
		}
	}

	/**
	 * Gets the currently opened weapon interface.
	 * @return The current weapon interface.
	 */
	public WeaponInterfaces getWeaponInterface() {
		return current;
	}

	/**
	 * If the special bar is enabled.
	 * @return {@code True} if so.
	 */
	public boolean isSpecialBar() {
		return specialBar;
	}

}