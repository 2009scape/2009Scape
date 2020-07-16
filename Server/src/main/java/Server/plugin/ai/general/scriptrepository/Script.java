package plugin.ai.general.scriptrepository;

import plugin.ai.AIPlayer;
import plugin.ai.general.ScriptAPI;
import core.game.node.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Script {

    public ScriptAPI scriptAPI;
    public ArrayList<Item> inventory = new ArrayList<>();
    public ArrayList<Item> equipment = new ArrayList<>();
    public Map<Integer, Integer> skills = new HashMap<>();


    public AIPlayer bot;

    public void init()
    {
        bot.init();
        scriptAPI = new ScriptAPI(bot);

        for (Item i : inventory)
        {
            bot.getInventory().add(i);
        }
        for (Item i : equipment)
        {
            bot.getEquipment().add(i, true, false);
        }
        for (Map.Entry<Integer, Integer> skill : skills.entrySet())
        {
            setLevel(skill.getKey(), skill.getValue());
        }
    }

    public abstract void runLoop();

    public void setLevel(int skill, int level) {
        bot.getSkills().setLevel(skill, level);
        bot.getSkills().setStaticLevel(skill, level);
        bot.getSkills().updateCombatLevel();
        bot.getAppearance().sync();
    }
}
