package plugin.quest.members.sheepherder;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.tools.ItemNames;

import java.util.HashMap;

@InitializablePlugin
public class SheepHerder extends Quest {
    public static Item CATTLE_PROD = new Item(ItemNames.CATTLEPROD_278);
    public static Item POISON = new Item(279);
    public static Item PLAGUE_TOP = new Item(284);
    public static Item PLAGUE_BOTTOM = new Item(285);
    public static Item RED_SHEEP_BONES = new Item(ItemNames.SHEEP_BONES_1_280);
    public static Item GREEN_SHEEP_BONES = new Item(ItemNames.SHEEP_BONES_2_281);
    public static Item BLUE_SHEEP_BONES = new Item(ItemNames.SHEEP_BONES_3_282);
    public static Item YELLOW_SHEEP_BONES = new Item(ItemNames.SHEEP_BONES_4_283);
    public static int RED_SHEEP = 2345;
    public static int GREEN_SHEEP = 2346;
    public static int BLUE_SHEEP = 2347;
    public static int YELLOW_SHEEP = 2348;
    public static int FARMER_BRUMTY = 291;

    public static HashMap<Integer,Item> boneMap = new HashMap<>();
    static{
        boneMap.put(RED_SHEEP,RED_SHEEP_BONES);
        boneMap.put(GREEN_SHEEP,GREEN_SHEEP_BONES);
        boneMap.put(YELLOW_SHEEP,YELLOW_SHEEP_BONES);
        boneMap.put(BLUE_SHEEP,BLUE_SHEEP_BONES);
    }

    public SheepHerder(){super("Sheep Herder",113,112,4,60,0,1,3);}

    @Override
    public void drawJournal(Player player, int stage) {
        boolean hasGear = (player.getInventory().containsItem(PLAGUE_BOTTOM) && player.getInventory().containsItem(PLAGUE_TOP) || (player.getEquipment().containsItem(PLAGUE_BOTTOM) && player.getEquipment().containsItem(PLAGUE_TOP))) || stage >= 20;
        int line = 11;
        boolean sheepDead = player.getAttribute("sheep_herder:all_dead",false);
        super.drawJournal(player, stage);
        if(stage < 10){
            line(player,"I can start this quest by speaking to !!Councillor Halgrive??",line++);
            line(player, "near to the !!Zoo?? in !!East Ardougne.??",line++);
        } else {
            switch(stage){
                case 10:
                    line(player, "!!Councillor Halgrive?? said I should speak to !!Doctor Orbon?? about", line++, hasGear);
                    line(player, "Getting some protective gear.", line++, hasGear);
                    line(player, "I need to !!locate the diseased sheep?? and corral them !!into the pin??", line++,sheepDead);
                    line(player, "After which, I need to !!poison them?? and !!incinerate their bones.??", line++,sheepDead);
                    if(sheepDead) {
                        line(player,"I should inform !!Councillor Halgrive?? that I have taken care of the problem.",line++);
                    } else {
                        line(player, "I still need:", line++);
                        line(player, "A !!Red Sheep??", line++, player.getAttribute("sheep_herder:red_dead", false));
                        line(player, "A !!Blue Sheep??", line++, player.getAttribute("sheep_herder:blue_dead", false));
                        line(player, "A !!Green Sheep??", line++, player.getAttribute("sheep_herder:green_dead", false));
                        line(player, "A !!Yellow Sheep??", line++, player.getAttribute("sheep_herder:yellow_dead", false));
                    }
                    break;
                case 100:
                    line(player,"I helped Councillor Halgrive by putting down",line++,true);
                    line(player,"plague-bearing sheep.",line++,true);
                    line(player,"!!QUEST COMPLETE",line++);
                    break;
            }
        }
    }

    @Override
    public void finish(Player player) {
        int ln = 10;
        super.finish(player);
        player.getPacketDispatch().sendItemZoomOnInterface(995, 230, 277, 5);
        drawReward(player,"4 Quest Points",ln++);
        drawReward(player,"3100 coins",ln++);
        player.removeAttribute("sheep_herder:red_dead");
        player.removeAttribute("sheep_herder:blue_dead");
        player.removeAttribute("sheep_herder:green_dead");
        player.removeAttribute("sheep_herder:yellow_dead");
        player.removeAttribute("sheep_herder:all_dead");
        player.getInventory().add(new Item(995,3100));
    }

    @Override
    public Quest newInstance(Object object) {
        new HerderSheepNPC(RED_SHEEP, Location.create(2609, 3343, 0)).init();
        new HerderSheepNPC(RED_SHEEP,Location.create(2610, 3344, 0)).init();
        new HerderSheepNPC(RED_SHEEP,Location.create(2609, 3345, 0)).init();
        new HerderSheepNPC(RED_SHEEP,Location.create(2615, 3343, 0)).init();
        new HerderSheepNPC(GREEN_SHEEP,Location.create(2622, 3366, 0)).init();
        new HerderSheepNPC(GREEN_SHEEP,Location.create(2622, 3366, 0)).init();
        new HerderSheepNPC(GREEN_SHEEP,Location.create(2619, 3371, 0)).init();
        new HerderSheepNPC(GREEN_SHEEP,Location.create(2617, 3365, 0)).init();
        new HerderSheepNPC(YELLOW_SHEEP,Location.create(2610, 3390, 0)).init();
        new HerderSheepNPC(YELLOW_SHEEP,Location.create(2613, 3389, 0)).init();
        new HerderSheepNPC(YELLOW_SHEEP,Location.create(2606, 3391, 0)).init();
        new HerderSheepNPC(YELLOW_SHEEP,Location.create(2608, 3387, 0)).init();
        new HerderSheepNPC(BLUE_SHEEP,Location.create(2559, 3388, 0)).init();
        new HerderSheepNPC(BLUE_SHEEP,Location.create(2562, 3388, 0)).init();
        new HerderSheepNPC(BLUE_SHEEP,Location.create(2563, 3383, 0)).init();
        new HerderSheepNPC(BLUE_SHEEP,Location.create(2570, 3381, 0)).init();
        NPC Brumty = new NPC(FARMER_BRUMTY,Location.create(2594,3357,0));
        Brumty.setWalks(false);
        Brumty.setRespawn(true);
        Brumty.init();
        Brumty.setDirection(Direction.NORTH);
        return this;
    }
}
