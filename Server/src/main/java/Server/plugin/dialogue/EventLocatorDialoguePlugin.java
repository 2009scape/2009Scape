package plugin.dialogue;

import core.ServerConstants;
import plugin.quest.free.RuneMysteries;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Package -> plugin.dialogue
 * Created on -> 9/7/2016 @10:33 PM for 530
 *
 * @author Ethan Kyle Millard
 */
@InitializablePlugin
public class EventLocatorDialoguePlugin extends DialoguePlugin {

    private String eventName;

    private HashMap<Location[], String[]> GOLDEN_ESSENCE = new HashMap<>(), THIEVES_JACKPOT =  new HashMap<>(), HARVESTING_DOUBLES = new HashMap<>(), ALCHEMY_HELLENISTIC = new HashMap<>();


    private Map<String, HashMap<Location[], String[]>> HOURLY_EVENTS = new HashMap<>();


    public EventLocatorDialoguePlugin() {

    }

    public EventLocatorDialoguePlugin(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new EventLocatorDialoguePlugin(player);
    }

    @Override
    public boolean open(Object... args) {
        fill();
        eventName = (String) args[0];
        System.out.println(eventName);
        if (eventName == null) {
            player.sendMessage("There no events active currently.");
            return false;
        }
        for (Map.Entry<String, HashMap<Location[], String[]>> entry : HOURLY_EVENTS.entrySet()) {
            for (Map.Entry<Location[], String[]> values : entry.getValue().entrySet()) {
                for (int i = 0;  i < values.getKey().length; i ++)
                    System.out.println("Location Index -> " + getIndex(values.getKey(), i) + " for Location -> " + values.getKey());
                switch (eventName) {
                    case "Alchemy hellenistic":
                        if (entry.getKey().equalsIgnoreCase(eventName)) {
                            options(values.getValue());
                            stage = 0;
                        }
                        break;
                    case "Harvesting doubles":
                        if (entry.getKey().equalsIgnoreCase(eventName)) {
                            options("Mining", "Woodcutting", "Fishing");
                            stage = 1;
                        }
                        break;
                    case "Thieves jackpot":
                        if (entry.getKey().equalsIgnoreCase(eventName)) {
                            options(values.getValue());
                            stage = 5;
                        }
                        break;
                    case "Golden essence":
                        if (entry.getKey().equalsIgnoreCase(eventName)) {
                            options(values.getValue());
                            stage = 6;
                        }
                        break;
                }
            }
        }
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        for (Map.Entry<String, HashMap<Location[], String[]>> entry : HOURLY_EVENTS.entrySet()) {
            for (Map.Entry<Location[], String[]> values : entry.getValue().entrySet()) {//Minning
                switch (stage) {
                    case 0:
                        switch (buttonId) {
                            case 1:
                                player.teleport(values.getKey()[2]);//Home
                                end();
                                break;
                            case 2:
                                player.teleport(values.getKey()[3]);//Home
                                end();
                                break;
                        }
                        break;
                    case 1:
                        switch (buttonId) {
                            case 1:
                                options(Arrays.copyOfRange(values.getValue(), 4, 5));
                                stage = 2;
                                break;
                            case 2:
                                options(Arrays.copyOfRange(values.getValue(), 6, 7));
                                stage = 3;
                                break;
                            case 3:
                                options(Arrays.copyOfRange(values.getValue(), 8, 10));
                                stage = 4;
                                break;
                        }
                        break;
                    case 2:
                        switch (buttonId) {
                            case 1:
                                player.teleport(values.getKey()[4]);//Home
                                end();
                                break;
                            case 2:
                                player.teleport(values.getKey()[5]);//Home
                                end();
                                break;
                        }
                        break;
                    case 3:
                        switch (buttonId) {
                            case 1:
                                player.teleport(values.getKey()[6]);//Home
                                end();
                                break;
                            case 2:
                                player.teleport(values.getKey()[7]);//Home
                                end();
                                break;
                        }
                        break;
                    case 4:
                        switch (buttonId) {
                            case 1:
                                player.teleport(values.getKey()[8]);//Home
                                end();
                                break;
                            case 2:
                                if (player.getSkills().getStaticLevel(Skills.FISHING) < 68) {
                                    interpreter.sendDialogue("You must have at least 68 fishing to access this event location.");
                                    stage = -2;
                                } else {
                                    player.teleport(values.getKey()[9]);//Home
                                    end();
                                }
                                break;
                            case 3:
                                player.teleport(values.getKey()[10]);//Home
                                end();
                                break;
                        }
                        break;
                    case 5:
                        switch (buttonId) {
                            case 1:
                                player.teleport(values.getKey()[11]);//Home
                                end();
                                break;
                            case 2:
                                player.teleport(values.getKey()[12]);//Home
                                end();
                                break;
                        }
                        break;
                    case 6:
                        switch (buttonId) {
                            case 1:
                                if (player.getQuestRepository().getQuest(RuneMysteries.NAME).isCompleted(player)) {
                                    player.teleport(values.getKey()[0]);//Home
                                    end();
                                } else {
                                    interpreter.sendDialogue("You must have completed Rune Mysteries to access this", "event locator.");
                                    stage = -2;
                                }
                                break;
                            case 2:
                                if (player.getQuestRepository().getQuest(RuneMysteries.NAME).isCompleted(player) && player.getSkills().getLevel(Skills.MAGIC) >= 66) {
                                    player.teleport(values.getKey()[1]);//Home
                                    end();
                                } else {
                                    interpreter.sendDialogue("You must have completed Rune Mysteries and have a magic level of 66", " to access this event locator.");
                                    stage = -2;
                                }
                                break;
                        }
                        break;
                    case -2:
                        end();
                        break;
                }
            }
        }
        return false;
    }

    private void fill() {
        ALCHEMY_HELLENISTIC.put(
                new Location[]{
                        ServerConstants.HOME_LOCATION,
                        new Location(3164, 3485, 0)
                },
                new String[]{
                        "Home", "Grand Exchange"
                });
        HARVESTING_DOUBLES.put(
                new Location[]{
                        new Location(3228, 3147, 0),//lumby
                        new Location(3184, 3374, 0),//varrock
                        new Location(2730, 3485, 0),//seers
                        new Location(2776, 3437, 0),//catherby yews
                        new Location(2842, 3440, 0),//catherby fish
                        new Location(2596, 3414, 0),//fishing guild
                        new Location(2338, 3701, 0)//pfc
                },
                new String[]{
                        //Mining Locations
                        "Lumbridge Swamp Mine (Copper, Tin)", "Varrock Mining Site (Clay, Iron, Tin)",//add rock names
                        //Woodcutting Locations
                        "Seers Village Forest (Normal, Oak, Maple, Yew)", "Catherby Yew Forest (Normal, Willow, Yew)",//add tree names
                        //Fishing Locations
                        "Catherby Fishing spot (Lobster, Tuna/Swordfish, Shark)", "Fishing Guild (Lobster, Tuna/Swordfish, Shark)", "Piscatoris Fishing Colony (Monkfish)"//Add fish names
                });
        GOLDEN_ESSENCE.put(
                new Location[] {
                        new Location(3104, 9571, 0),
                        new Location(2591, 3086, 0)
                },
                new String[] {
                        "Wizards Tower Essence Mine",
                        "Magic Guild Essence Mine",
                });
        THIEVES_JACKPOT.put(
                new Location[]{
                        new Location(3231, 3238, 0),
                        new Location(3154, 9646, 0)
                },
                new String[]{
                        "Lumbridge men",
                        "H.A.M Outlet"
                });

        HOURLY_EVENTS.put("Alchemy hellenistic", ALCHEMY_HELLENISTIC);
        HOURLY_EVENTS.put("Harvesting Doubles", HARVESTING_DOUBLES);
        HOURLY_EVENTS.put("Thieves jackpot", THIEVES_JACKPOT);
        HOURLY_EVENTS.put("Golden essence", GOLDEN_ESSENCE);
    }

    private int getIndex(Location[] loc, int value) {
        for (int i = 0; i < loc.length; i ++) {
            if (loc[i].getIndex() == value) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public int[] getIds() {
        return new int[] {
            175869
        };
    }
}
