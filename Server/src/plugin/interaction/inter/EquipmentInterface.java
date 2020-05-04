package plugin.interaction.inter;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.component.Component;
import org.crandor.game.component.ComponentDefinition;
import org.crandor.game.component.ComponentPlugin;
import org.crandor.game.container.Container;
import org.crandor.game.container.ContainerEvent;
import org.crandor.game.container.ContainerListener;
import org.crandor.game.container.access.BitregisterAssembler;
import org.crandor.game.container.access.InterfaceContainer;
import org.crandor.game.container.impl.EquipmentContainer;
import org.crandor.game.content.global.action.EquipHandler;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.entity.combat.DeathTask;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.prayer.PrayerType;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.ContainerContext;
import org.crandor.net.packet.out.ContainerPacket;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the equipment interface.
 * @author Emperor
 * 
 */
@InitializablePlugin
public final class EquipmentInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(387, this);
		ComponentDefinition.put(667, this);
		ComponentDefinition.put(670, this);
		return this;
	}

	@Override
	public boolean handle(final Player p, Component component, int opcode, int button, final int slot, final int itemId) {
		if (component.getId() == 667) {
			if (button != 14) {
				return false;
			}
			switch (opcode) {
			case 155:
				p.getPulseManager().clear();
				GameWorld.Pulser.submit(new Pulse(1, p) {
					@Override
					public boolean pulse() {
						EquipHandler.unequip(p, slot, itemId);
						return true;
					}
				});
				return true;
			case 9:
				p.sendMessage(p.getEquipment().get(slot).getDefinition().getExamine());
				return true;
			case 196:
				GameWorld.Pulser.submit(new Pulse(1, p) {
					@Override
					public boolean pulse() {
						operate(p, slot, itemId);
						return true;
					}
				});
				return true;
			}
			return false;
		}
		else if (component.getId() == 670) {
			switch (opcode) {
			case 155:
				p.getPulseManager().clear();
				final Item item = p.getInventory().get(slot);
				GameWorld.Pulser.submit(new Pulse(1, p) {
					@Override
					public boolean pulse() {
						EquipHandler.SINGLETON.handle(p, item, "equip");
						return true;
					}
				});
				return true;
			case 9:
				p.sendMessage(p.getInventory().get(slot).getDefinition().getExamine());
				return true;
			}
		}
		switch (opcode) {
		case 206:
			if (button != 28) {
				return false;
			}
			GameWorld.Pulser.submit(new Pulse(1, p) {
				@Override
				public boolean pulse() {
					operate(p, slot, itemId);
					return true;
				}
			});
			return true;
		default:
			switch (button) {
			case 52:
				if (p.getInterfaceManager().isOpened() && p.getInterfaceManager().getOpened().getId() == 102) {
					return true;
				}
				boolean skulled = p.getSkullManager().isSkulled();
				boolean usingProtect = p.getPrayer().get(PrayerType.PROTECT_ITEMS);
				p.getInterfaceManager().openComponent(102);
				p.getPacketDispatch().sendAccessMask(211, 0, 2, 6684690, 4);
				p.getPacketDispatch().sendAccessMask(212, 0, 2, 6684693, 42);
				Container[] itemArray = DeathTask.getContainers(p);
				Container kept = itemArray[0];
				int state = 0; // 1=familiar carrying items
				int keptItems = skulled ? (usingProtect ? 1 : 0) : (usingProtect ? 4 : 3);
				int zoneType = p.getZoneMonitor().getType();
				int pvpType = p.getSkullManager().isWilderness() ? 0 : 1;
				Object[] params = new Object[] { 11510, 12749, "", state, pvpType, kept.getId(3), kept.getId(2), kept.getId(1), kept.getId(0), keptItems, zoneType };
				PacketRepository.send(ContainerPacket.class, new ContainerContext(p, 149, 0, 91, itemArray[1], false));
				p.getPacketDispatch().sendRunScript(118, "iiooooiisii", params);
				break;
			case 28:
				if (opcode == 81) {
					p.getPulseManager().clear();
					GameWorld.Pulser.submit(new Pulse(1, p) {
						@Override
						public boolean pulse() {
							EquipHandler.unequip(p, slot, itemId);
							return true;
						}
					});
					return true;
				}
				break;
			case 55:
				if (p.getInterfaceManager().isOpened() && p.getInterfaceManager().getOpened().getId() == 667) {
					return true;
				}
				final ContainerListener listener = new ContainerListener() {
					@Override
					public void update(Container c, ContainerEvent e) {
						PacketRepository.send(ContainerPacket.class, new ContainerContext(p, -1, -1, 98, e.getItems(), false, e.getSlots()));
					}

					@Override
					public void refresh(Container c) {
						PacketRepository.send(ContainerPacket.class, new ContainerContext(p, -1, -1, 98, c, false));
					}
				};
				p.getInterfaceManager().openComponent(667).setCloseEvent((player, c) -> {
                    player.removeAttribute("equip_stats_open");
                    player.getInterfaceManager().closeSingleTab();
                    player.getInventory().getListeners().remove(listener);
                    return true;
                });
				p.setAttribute("equip_stats_open", true);
				EquipmentContainer.update(p);
				p.getInterfaceManager().openSingleTab(new Component(670));
				InterfaceContainer.generateItems(p, p.getInventory().toArray(), new String[] { "Equip" }, 670, 0, 7, 4, 93);
				BitregisterAssembler assembly = new BitregisterAssembler(new String[] { "Equip" });
				assembly.enableExamineOption();
				assembly.enableSlotSwitch();
				p.getPacketDispatch().sendAccessMask(assembly.calculateRegister(), 0, 670, 0, 27);
				p.getInventory().getListeners().add(listener);
				p.getInventory().refresh();
				ItemDefinition.statsUpdate(p);
				p.getPacketDispatch().sendAccessMask(1278, 14, 667, 0, 13);
				break;
			}
		}
		return true;
	}

	/**
	 * Operates an item.
	 * @param player The player.
	 * @param slot The container slot.
	 * @param itemId The item id.
	 */
	public void operate(Player player, int slot, int itemId) {
		if (slot < 0 || slot > 13) {
			return;
		}
		Item item = player.getEquipment().get(slot);
		if (item == null) {
			return;
		}
		OptionHandler handler = item.getOperateHandler();
		if (handler != null && handler.handle(player, item, "operate")) {
			return;
		}
		player.getPacketDispatch().sendMessage("You can't operate that.");
	}

}
