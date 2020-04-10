package org.crandor.game.node.entity.player.link.quest;

import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.login.SavingModule;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Manages the systems/players quest repository.
 *
 * @author Vexia
 */
public final class QuestRepository implements SavingModule {

    /**
     * The static mapping of instanced quests.
     */
    private static final Map<String, Quest> QUESTS = new HashMap<>();

    /**
     * The mapping of quest indexes with related stages.
     */
    private final Map<Integer, Integer> quests = new HashMap<>();

    /**
     * The player instance for this manager.
     */
    private final Player player;

    /**
     * The current syncronized accumulated quest points.
     */
    private int points;

    /**
     * Constructs a new {@code QuestRepository} {@code Object}.
     *
     * @param player the player.
     */
    public QuestRepository(final Player player) {
        this.player = player;
        for (Quest quest : QUESTS.values()) {
            quests.put(quest.getIndex(), 0);
        }
    }

    @Override
    public void save(ByteBuffer buffer) {
        if (points != 0) {
            buffer.put((byte) 1).putInt(points);
        }
        for (Entry<Integer, Integer> entry : quests.entrySet()) {
            buffer.put((byte) 4).putInt(entry.getKey()).put((byte) ((int) entry.getValue()));
        }
        Map<Integer, Integer> backup = player.getAttribute("quest-backup", null);
        if (backup != null) {
            for (Entry<Integer, Integer> entry : backup.entrySet()) {
                buffer.put((byte) 4).putInt(entry.getKey()).put((byte) ((int) entry.getValue()));
            }
        }
        buffer.put((byte) 0);
    }

    @Override
    public void parse(ByteBuffer buffer) {
        int opcode;
        int stage = 0;
        int index = 0;
        while ((opcode = buffer.get() & 0xFF) != 0) {
            switch (opcode) {
                case 1:
                    points = buffer.getInt();
                    break;
                case 3:
                    index = buffer.getInt();
                    stage = buffer.getInt();
                    buffer.getInt();//state
                    if (forIndex(index) == null) {
                        //SystemLogger.error("[Quest Repository] parsed quest index -> " + index + " no quest found!");
                        break;
                    }
                    quests.put(index, stage);
                    break;
                case 4://new parsing.
                    index = buffer.getInt();
                    stage = buffer.get();
                    if (forIndex(index) == null) {
                        Map<Integer, Integer> backup = player.getAttribute("quest-backup", new HashMap<Integer, Integer>());
                        backup.put(index, stage);
                        player.setAttribute("quest-backup", backup);
                       // SystemLogger.error("[Quest Repository] parsed quest index -> " + index + " no quest found! Stored in quest data backup for reparse.");
                        break;
                    }
                    if (!quests.containsKey(index)) {//YOU FORGOT YOUR ! DAMMIT
                        break;
                    }
                    quests.put(index, stage);
                    break;
            }
        }
        syncPoints();
    }

    /**
     * Syncronizes the quest tab.
     *
     * @param player The player.
     */
    public void syncronizeTab(Player player) {
        player.getConfigManager().set(101, points);
        int[] config = null;
        for (Quest quest : QUESTS.values()) {
            config = quest.getConfig(player, getStage(quest));
            player.getConfigManager().set(config[0], config[1]);
//            System.out.println(quest.getName() + " - > stage =  " + getStage(quest) + " - configs = { " + config[0] + " " + config[1] + " }");
        }
    }

    /**
     * Sets the stage of a quest.
     *
     * @param quest The quest.
     * @param stage The stage.
     */
    public void setStage(Quest quest, int stage) {
        quests.put(quest.getIndex(), stage);
    }

    /**
     * Increments the obtained points by the value.
     *
     * @param value the value.
     */
    public void incrementPoints(int value) {
        points += value;
    }

    /**
     * Decrease the points by the value.
     *
     * @param value the value.
     */
    public void dockPoints(int value) { points -= value; }

    /**
     * Syncronizes the quest points.
     */
    public void syncPoints() {
        int points = 0;
        for (Quest quest : QUESTS.values()) {
            if (getStage(quest) >= 100) {
                points += quest.getQuestPoints();
            }
        }
        this.points = points;
    }

    /**
     * Gets the available quest points.
     *
     * @return The availble quest points.
     */
    public int getAvailablePoints() {
        int points = 0;
        for (Quest quest : QUESTS.values()) {
            points += quest.getQuestPoints();
        }
        return points;
    }

    /**
     * Gets the quest for the button id.
     *
     * @param buttonId The button id.
     * @return The quest.
     */
    public Quest forButtonId(int buttonId) {
        for (Quest q : QUESTS.values()) {
            if (q.getButtonId() == buttonId) {
                return q;
            }
        }
        return null;
    }

    /**
     * Gets the quest for the index.
     *
     * @param index The index.
     * @return The quest.
     */
    public Quest forIndex(int index) {
        for (Quest q : QUESTS.values()) {
            if (q.getIndex() == index) {
                return q;
            }
        }
        return null;
    }

    /**
     * Checks if all quests are completed.
     *
     * @return {@code True} if so.
     */
    public boolean hasCompletedAll() {
        return getPoints() >= getAvailablePoints();
    }

    /**
     * Checks if the quest is complete.
     *
     * @param name The name of the quest.
     * @return {@code True} if so.
     */
    public boolean isComplete(String name) {
        Quest quest = getQuest(name);
        if (quest == null) {
            System.err.println("Error can't check if quest is complete for " + name);
            return false;
        }
        return quest.getStage(player) >= 100;
    }

    /**
     * Gets the stage of quest by name.
     *
     * @param name The name of the quest.
     * @return The stage.
     */
    public int getStage(String name) {
        return getStage(QUESTS.get(name));
    }

    /**
     * Gets the stage of a quest.
     *
     * @param quest The quest.
     * @return The stage.
     */
    public int getStage(Quest quest) {
        return quests.get(quest.getIndex());
    }

    /**
     * Gets the quest by name.
     *
     * @param name The name.
     * @return The quest.
     */
    public Quest getQuest(String name) {
        return QUESTS.get(name);
    }

    /**
     * Gets the points.
     *
     * @return the points.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Gets the player.
     *
     * @return The player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Registers the quest to the repository.
     *
     * @param quest The quest.
     */
    public static void register(Quest quest) {
        QUESTS.put(quest.getName(), quest);
    }

    /**
     * Gets the quest repository.
     *
     * @return the quests.
     */
    public static Map<String, Quest> getQuests() {
        return QUESTS;
    }

}