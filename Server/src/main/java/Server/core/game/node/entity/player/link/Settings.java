package core.game.node.entity.player.link;

import core.game.system.config.ItemConfigParser;
import org.json.simple.JSONObject;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.system.communication.CommunicationInfo;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.net.packet.IoBuffer;

import java.nio.ByteBuffer;

/**
 * Holds a player's settings.
 * @author Emperor
 */
public final class Settings {

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The run energy.
	 */
	private double runEnergy = 100.0;

	/**
	 * The player's weight.
	 */
	private double weight;

	/**
	 * The brightness setting.
	 */
	private int brightness = 2;

	/**
	 * The music volume.
	 */
	private int musicVolume;

	/**
	 * The sound effects volume.
	 */
	private int soundEffectVolume;

	/**
	 * The area sounds volume.
	 */
	private int areaSoundVolume;

	/**
	 * If the player has the single mouse button setting enabled.
	 */
	private boolean singleMouseButton;

	/**
	 * If the chat effects should be disabled.
	 */
	private boolean disableChatEffects;

	/**
	 * If the private chat should be split from public chat.
	 */
	private boolean splitPrivateChat;

	/**
	 * If the player has the accept aid setting enabled.
	 */
	private boolean acceptAid;

	/**
	 * If the player's run button is toggled.
	 */
	private boolean runToggled;

	/**
	 * The public chat setting.
	 */
	private int publicChatSetting = 0;

	/**
	 * The private chat setting.
	 */
	private int privateChatSetting = 0;

	/**
	 * The clan chat setting.
	 */
	private int clanChatSetting = 0;

	/**
	 * The trade setting.
	 */
	private int tradeSetting = 0;

	/**
	 * The assist setting.
	 */
	private int assistSetting = 0;

	/**
	 * If the special attack is toggled.
	 */
	private boolean specialToggled;

	/**
	 * The current special energy the player has left.
	 */
	private int specialEnergy = 100;

	/**
	 * The current attack style index.
	 */
	private int attackStyleIndex = 0;

	/**
	 * Constructs a new {@code Settings} {@code Object}.
	 * @param player The player.
	 */
	public Settings(Player player) {
		this.player = player;
	}

	/**
	 * Updates the settings.
	 */
	public void update() {
		player.getConfigManager().set(ConfigurationManager.Configuration.BRIGHTNESS, brightness + 1);
		player.getConfigManager().set(ConfigurationManager.Configuration.MUSIC_VOLUME, musicVolume);
		player.getConfigManager().set(ConfigurationManager.Configuration.EFFECT_VOLUME, soundEffectVolume);
		player.getConfigManager().set(ConfigurationManager.Configuration.SURROUNDING_VOLUME, areaSoundVolume);
		player.getConfigManager().set(ConfigurationManager.Configuration.MOUSE_BUTTON, singleMouseButton);
		player.getConfigManager().set(ConfigurationManager.Configuration.CHAT_EFFECT, disableChatEffects);
		player.getConfigManager().set(ConfigurationManager.Configuration.SPLIT_PRIVATE, splitPrivateChat);
		player.getConfigManager().set(ConfigurationManager.Configuration.ACCEPT_AID, acceptAid);
		player.getConfigManager().set(ConfigurationManager.Configuration.RETALIATE, !player.getProperties().isRetaliating());
		player.getConfigManager().set(ConfigurationManager.Configuration.RUNNING, runToggled);
		player.getConfigManager().set(1054, clanChatSetting);
		player.getConfigManager().set(1055, assistSetting);
		player.getConfigManager().set(300, specialEnergy * 10);
		player.getConfigManager().set(43, attackStyleIndex);
		player.getPacketDispatch().sendRunEnergy();
		updateChatSettings();
		Pulse pulse = player.getAttribute("energy-restore", null);
		if (pulse == null || !pulse.isRunning()) {
			pulse = new Pulse(50, player) {
				@Override
				public boolean pulse() {
					if (specialEnergy < 100) {
						int heal = 100 - specialEnergy;
						setSpecialEnergy(specialEnergy + (heal > 10 ? 10 : heal));
					}
					return false;
				}
			};
			pulse.setTicksPassed(1);
			GameWorld.Pulser.submit(pulse);
			player.setAttribute("energy-restore", pulse);
		}
	}

	/**
	 * Toggles the attack style index.
	 * @param index The index.
	 */
	public void toggleAttackStyleIndex(int index) {
		this.attackStyleIndex = index;
		player.getConfigManager().set(43, attackStyleIndex);
	}

	/**
	 * Updates the chat settings.
	 */
	public void updateChatSettings() {
		player.getSession().write(new IoBuffer(232).put(publicChatSetting).put(privateChatSetting).put(tradeSetting));
	}

	/**
	 * Sets the chat settings.
	 * @param pub The public chat setting.
	 * @param priv The private chat setting.
	 * @param trade The trade setting.
	 */
	public void updateChatSettings(int pub, int priv, int trade) {
		boolean update = false;
		if (publicChatSetting != pub) {
			publicChatSetting = pub;
			update = true;
		}
		if (privateChatSetting != priv) {
			privateChatSetting = priv;
			update = true;
			CommunicationInfo.notifyPlayers(player, privateChatSetting != 2, true);
		}
		if (tradeSetting != trade) {
			tradeSetting = trade;
			update = true;
		}
		if (update) {
			updateChatSettings();
		}
	}

	/**
	 * Sets the chat settings.
	 * @param pub The public chat setting.
	 * @param priv The private chat setting.
	 * @param trade The trade setting.
	 */
	public void setChatSettings(int pub, int priv, int trade) {
		publicChatSetting = pub;
		privateChatSetting = priv;
		tradeSetting = trade;
	}

	/**
	 * Writes the settings on the byte buffer.
	 * @param buffer The byte buffer.
	 */
	public void save(ByteBuffer buffer) {
		buffer.put((byte) 1).put((byte) brightness).put((byte) musicVolume).put((byte) soundEffectVolume).put((byte) areaSoundVolume).put((byte) (singleMouseButton ? 1 : 0)).put((byte) (disableChatEffects ? 1 : 0)).put((byte) (splitPrivateChat ? 1 : 0)).put((byte) (acceptAid ? 1 : 0)).put((byte) (runToggled ? 1 : 0)).put((byte) publicChatSetting).put((byte) privateChatSetting).put((byte) clanChatSetting).put((byte) tradeSetting).put((byte) assistSetting).put(((byte) runEnergy));
		if (!player.getProperties().isRetaliating()) {
			buffer.put((byte) 2);
		}
		if (specialEnergy != 100) {
			buffer.put((byte) 3).put((byte) specialEnergy);
		}
		if (attackStyleIndex != 0) {
			buffer.put((byte) 4).put((byte) attackStyleIndex);
		}
		buffer.put((byte) 0);
	}

	/**
	 * Parses the settings from the byte buffer.
	 * @param buffer The byte buffer.
	 */
	public void parse(ByteBuffer buffer) {
		int opcode;
		while ((opcode = buffer.get() & 0xFF) != 0) {
			switch (opcode) {
			case 1:
				brightness = buffer.get();
				musicVolume = buffer.get();
				soundEffectVolume = buffer.get();
				areaSoundVolume = buffer.get();
				singleMouseButton = buffer.get() == 1;
				disableChatEffects = buffer.get() == 1;
				splitPrivateChat = buffer.get() == 1;
				acceptAid = buffer.get() == 1;
				runToggled = buffer.get() == 1;
				publicChatSetting = buffer.get();
				privateChatSetting = buffer.get();
				clanChatSetting = buffer.get();
				tradeSetting = buffer.get();
				assistSetting = buffer.get();
				runEnergy = buffer.get();
				break;
			case 2:
				player.getProperties().setRetaliating(false);
				break;
			case 3:
				specialEnergy = buffer.get() & 0xFF;
				break;
			case 4:
				attackStyleIndex = buffer.get();
				break;
			}
		}
	}

	public void parse(JSONObject settingsData){
		brightness = Integer.parseInt( settingsData.get("brightness").toString());
		musicVolume = Integer.parseInt( settingsData.get("musicVolume").toString());
		soundEffectVolume = Integer.parseInt( settingsData.get("soundEffectVolume").toString());
		areaSoundVolume = Integer.parseInt( settingsData.get("areaSoundVolume").toString());
		singleMouseButton = (boolean) settingsData.get("singleMouse");
		disableChatEffects = (boolean) settingsData.get("disableChatEffects");
		splitPrivateChat = (boolean) settingsData.get("splitPrivate");
		acceptAid = (boolean) settingsData.get("acceptAid");
		runToggled = (boolean) settingsData.get("runToggled");
		publicChatSetting = Integer.parseInt( settingsData.get("publicChatSetting").toString());
		privateChatSetting = Integer.parseInt( settingsData.get("privateChatSetting").toString());
		clanChatSetting = Integer.parseInt( settingsData.get("clanChatSetting").toString());
		tradeSetting = Integer.parseInt( settingsData.get("tradeSetting").toString());
		assistSetting = Integer.parseInt( settingsData.get("assistSetting").toString());
		runEnergy = Double.parseDouble( settingsData.get("runEnergy").toString());
		specialEnergy = Integer.parseInt( settingsData.get("specialEnergy").toString());
		attackStyleIndex = Integer.parseInt( settingsData.get("attackStyle").toString());
		player.getProperties().setRetaliating((boolean) settingsData.get("retaliation"));
	}

	/**
	 * Toggles the special attack bar.
	 */
	public void toggleSpecialBar() {
		setSpecialToggled(!specialToggled);
	}

	/**
	 * Toggles the special attack bar.
	 * @param enable If the special attack should be enabled.
	 */
	public void setSpecialToggled(boolean enable) {
		specialToggled = !specialToggled;
		player.getConfigManager().set(301, specialToggled ? 1 : 0);
	}

	/**
	 * Checks if the special attack bar is toggled.
	 * @return {@code True} if so.
	 */
	public boolean isSpecialToggled() {
		return specialToggled;
	}

	/**
	 * Drains an amount of special attack energy.
	 * @param amount The amount to drain.
	 * @return {@code True} if succesful, {@code false} if the special attack
	 * energy amount hasn't changed after calling this method.
	 */
	public boolean drainSpecial(int amount) {
		if (!specialToggled) {
			return false;
		}
		setSpecialToggled(false);
		if (amount > specialEnergy) {
			player.getPacketDispatch().sendMessage("You do not have enough special attack energy left.");
			return false;
		}
		setSpecialEnergy(specialEnergy - amount);
		return true;
	}

	/**
	 * Sets the special energy amount.
	 * @param value The amount to set.
	 */
	public void setSpecialEnergy(int value) {
		specialEnergy = value;
		player.getConfigManager().set(300, specialEnergy * 10);
	}

	/**
	 * Gets the amount of special energy left.
	 * @return The amount of energy.
	 */
	public int getSpecialEnergy() {
		return specialEnergy;
	}

	/**
	 * Toggles the retaliating button.
	 */
	public void toggleRetaliating() {
		player.getProperties().setRetaliating(!player.getProperties().isRetaliating());
		player.getConfigManager().set(172, !player.getProperties().isRetaliating() ? 1 : 0);
	}

	/**
	 * Toggles the singleMouseButton.
	 */
	public void toggleMouseButton() {
		singleMouseButton = !singleMouseButton;
		player.getConfigManager().set(170, singleMouseButton ? 1 : 0);
	}

	/**
	 * Toggles the disableChatEffects.
	 */
	public void toggleChatEffects() {
		disableChatEffects = !disableChatEffects;
		player.getConfigManager().set(171, disableChatEffects ? 1 : 0);
	}

	/**
	 * Toggles the splitPrivateChat.
	 */
	public void toggleSplitPrivateChat() {
		splitPrivateChat = !splitPrivateChat;
		player.getConfigManager().set(287, splitPrivateChat ? 1 : 0);
	}

	/**
	 * Toggles the acceptAid.
	 */
	public void toggleAcceptAid() {
		acceptAid = !acceptAid;
		player.getConfigManager().set(427, acceptAid ? 1 : 0);
	}

	/**
	 * Toggles the run button.
	 */
	public void toggleRun() {
		setRunToggled(!runToggled);
	}

	/**
	 * Toggles the run button.
	 * If the run button should be enabled.
	 */
	public void setRunToggled(boolean enabled) {
		runToggled = enabled;
		player.getConfigManager().set(173, runToggled ? 1 : 0);
	}

	/**
	 * Decreases the run energy with the given amount (drain parameter). <br> To
	 * increase, use a negative drain value.
	 * @param drain The drain amount.
	 */
	public void updateRunEnergy(double drain) {
		runEnergy -= drain;
		if (runEnergy < 0) {
			runEnergy = 0.0;
		} else if (runEnergy > 100) {
			runEnergy = 100.0;
		}
		player.getPacketDispatch().sendRunEnergy();
	}

	/**
	 * Updates the weight.
	 */
	public void updateWeight() {
		weight = 0.0;
		for (int i = 0; i < 28; i++) {
			Item item = player.getInventory().get(i);
			if (item == null) {
				continue;
			}
			double value = item.getDefinition().getConfiguration(ItemConfigParser.WEIGHT, 0.0);
			if (value > 0) {
				weight += value;
			}
		}
		for (int i = 0; i < 11; i++) {
			Item item = player.getEquipment().get(i);
			if (item == null) {
				continue;
			}
			weight += item.getDefinition().getConfiguration(ItemConfigParser.WEIGHT, 0.0);
		}
	}

	/**
	 * Gets the weight.
	 * @return The weight.
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Gets the brightness.
	 * @return The brightness.
	 */
	public int getBrightness() {
		return brightness;
	}

	/**
	 * Sets the brightness.
	 * @param brightness The brightness to set.
	 */
	public void setBrightness(int brightness) {
		this.brightness = brightness;
		player.getConfigManager().set(166, brightness + 1);
	}

	/**
	 * Gets the musicVolume.
	 * @return The musicVolume.
	 */
	public int getMusicVolume() {
		return musicVolume;
	}

	/**
	 * Sets the musicVolume.
	 * @param musicVolume The musicVolume to set.
	 */
	public void setMusicVolume(int musicVolume) {
		this.musicVolume = musicVolume;
		player.getConfigManager().set(168, musicVolume);
	}

	/**
	 * Gets the soundEffectVolume.
	 * @return The soundEffectVolume.
	 */
	public int getSoundEffectVolume() {
		return soundEffectVolume;
	}

	/**
	 * Sets the soundEffectVolume.
	 * @param soundEffectVolume The soundEffectVolume to set.
	 */
	public void setSoundEffectVolume(int soundEffectVolume) {
		this.soundEffectVolume = soundEffectVolume;
		player.getConfigManager().set(169, soundEffectVolume);
	}

	/**
	 * Gets the areaSoundVolume.
	 * @return The areaSoundVolume.
	 */
	public int getAreaSoundVolume() {
		return areaSoundVolume;
	}

	/**
	 * Sets the areaSoundVolume.
	 * @param areaSoundVolume The areaSoundVolume to set.
	 */
	public void setAreaSoundVolume(int areaSoundVolume) {
		this.areaSoundVolume = areaSoundVolume;
		player.getConfigManager().set(872, areaSoundVolume);
	}

	/**
	 * Gets the singleMouseButton.
	 * @return The singleMouseButton.
	 */
	public boolean isSingleMouseButton() {
		return singleMouseButton;
	}

	/**
	 * Gets the disableChatEffects.
	 * @return The disableChatEffects.
	 */
	public boolean isDisableChatEffects() {
		return disableChatEffects;
	}

	/**
	 * Gets the splitPrivateChat.
	 * @return The splitPrivateChat.
	 */
	public boolean isSplitPrivateChat() {
		return splitPrivateChat;
	}

	/**
	 * Gets the acceptAid.
	 * @return The acceptAid.
	 */
	public boolean isAcceptAid() {
		if (player.getIronmanManager().isIronman()) {
			return false;
		}
		return acceptAid;
	}

	/**
	 * Gets the runToggled.
	 * @return The runToggled.
	 */
	public boolean isRunToggled() {
		return runToggled;
	}

	/**
	 * Gets the publicChatSetting.
	 * @return The publicChatSetting.
	 */
	public int getPublicChatSetting() {
		return publicChatSetting;
	}

	/**
	 * Sets the publicChatSetting.
	 * @param publicChatSetting The publicChatSetting to set.
	 */
	public void setPublicChatSetting(int publicChatSetting) {
		this.publicChatSetting = publicChatSetting;
		updateChatSettings();
	}

	/**
	 * Gets the privateChatSetting.
	 * @return The privateChatSetting.
	 */
	public int getPrivateChatSetting() {
		return privateChatSetting;
	}

	/**
	 * Sets the privateChatSetting.
	 * @param privateChatSetting The privateChatSetting to set.
	 */
	public void setPrivateChatSetting(int privateChatSetting) {
		this.privateChatSetting = privateChatSetting;
		updateChatSettings();
	}

	/**
	 * Gets the clanChatSetting.
	 * @return The clanChatSetting.
	 */
	public int getClanChatSetting() {
		return clanChatSetting;
	}

	/**
	 * Sets the clanChatSetting.
	 * @param clanChatSetting The clanChatSetting to set.
	 */
	public void setClanChatSetting(int clanChatSetting) {
		this.clanChatSetting = clanChatSetting;
		player.getConfigManager().set(1054, clanChatSetting);
	}

	/**
	 * Gets the tradeSetting.
	 * @return The tradeSetting.
	 */
	public int getTradeSetting() {
		return tradeSetting;
	}

	/**
	 * Sets the tradeSetting.
	 * @param tradeSetting The tradeSetting to set.
	 */
	public void setTradeSetting(int tradeSetting) {
		this.tradeSetting = tradeSetting;
		updateChatSettings();
	}

	/**
	 * Gets the assistSetting.
	 * @return The assistSetting.
	 */
	public int getAssistSetting() {
		return assistSetting;
	}

	/**
	 * Sets the assistSetting.
	 * @param assistSetting The assistSetting to set.
	 */
	public void setAssistSetting(int assistSetting) {
		this.assistSetting = assistSetting;
		player.getConfigManager().set(1055, assistSetting);
	}

	/**
	 * @return the runEnergy
	 */
	public double getRunEnergy() {
		return runEnergy;
	}

	/**
	 * @param runEnergy the runEnergy to set
	 */
	public void setRunEnergy(double runEnergy) {
		this.runEnergy = runEnergy;
	}

	/**
	 * Gets the attackStyleIndex.
	 * @return The attackStyleIndex.
	 */
	public int getAttackStyleIndex() {
		return attackStyleIndex;
	}

	/**
	 * Sets the attackStyleIndex.
	 * @param attackStyleIndex The attackStyleIndex to set.
	 */
	public void setAttackStyleIndex(int attackStyleIndex) {
		this.attackStyleIndex = attackStyleIndex;
	}

}