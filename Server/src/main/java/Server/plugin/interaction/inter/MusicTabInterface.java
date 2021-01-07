package plugin.interaction.inter;

import core.game.component.Component;
import core.game.component.ComponentDefinition;
import core.game.component.ComponentPlugin;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.Rights;
import core.game.node.entity.player.link.music.MusicEntry;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Handles the interface tab buttons.
 * @author Emperor
 */
@InitializablePlugin
public final class MusicTabInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(187, this);
		return this;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
		switch (opcode) {
			case 155:
				switch (button) {
					case 11:
						player.getMusicPlayer().toggleLooping();
						return true;
					case 1:
						MusicEntry entry = player.getMusicPlayer().getUnlocked().get(slot);
						if (entry == null) {
							if(player.getRights().equals(Rights.ADMINISTRATOR)){
								for(MusicEntry ent : MusicEntry.getSongs().values()){
									if(ent.getIndex() == slot){
										player.getMusicPlayer().unlock(ent.getId());
										break;
									}
								}
							} else {
								player.getPacketDispatch().sendMessage("You have not unlocked this piece of music yet!</col>");
							}
							return true;
						}
						player.getMusicPlayer().setPlaying(false);
						player.getMusicPlayer().play(entry);
						return true;
				}
				break;
		}
		return false;
	}

}
