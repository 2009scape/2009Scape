package plugin.activity.pyramidplunder;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.content.global.action.ClimbActionHandler;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.ImpactHandler;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;
import org.crandor.tools.RandomFunction;

import java.util.Random;

/**
 * Handle pyramid plunder object interactions
 * @author ceik
 */

@InitializablePlugin
public final class PyramidPlunderOptions extends OptionHandler {
    Item[][] ARTIFACTS = { {new Item(9032),new Item(9036), new Item(9026)}, {new Item(9042), new Item(9030), new Item(9038)}, {new Item(9040), new Item(9028), new Item(9034)} };
    private static final Animation[] animations = new Animation[] { new Animation(2247), new Animation(2248), new Animation(1113), new Animation(2244) };
    int reqLevel;
    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ObjectDefinition.forId(16517).getConfigurations().put("option:pass",this);
        ObjectDefinition.forId(16503).getConfigurations().put("option:search",this);
        ObjectDefinition.forId(16502).getConfigurations().put("option:search",this);
        ObjectDefinition.forId(16501).getConfigurations().put("option:search",this);
        ObjectDefinition.forId(16473).getConfigurations().put("option:search",this);
        ObjectDefinition.forId(16495).getConfigurations().put("option:open",this);
        ObjectDefinition.forId(16475).getConfigurations().put("option:pick-lock",this);
        return this;
    }
    public void rollSceptre(Player player){
        if(RandomFunction.random(1000) == 4){
            player.getInventory().add(new Item(9050));
        }
    }
    public final boolean success(final Player player, final int skill) {
        double level = player.getSkills().getLevel(skill);
        double successChance = Math.ceil((level * 50 - reqLevel) / reqLevel / 3 * 4);
        int roll = RandomFunction.random(99);
        if (successChance >= roll) {
            return true;
        }
        player.getImpactHandler().manualHit(player,RandomFunction.random(4), ImpactHandler.HitsplatType.NORMAL);
        return false;
    }
    @Override
    public boolean handle(Player player, Node node, String option) {
        Location room_entrance[] = {new Location(1927,4477), new Location(1927,4453), new Location(1943,4421), new Location(1954,4477), new Location(1974,4420), new Location(1977,4471), new Location(1927, 4424)};
        int currentX = player.getLocation().getX();
        int currentY = player.getLocation().getY();
        int level = player.getSkills().getLevel(Skills.THIEVING);
        int room = 0;
        int spearX = 0;
        int spearY = 0;
        double droom = 0.0;
        if(currentX >= 1923 && currentX <= 1932 && currentY <= 4477 && currentY >= 4464 ){
            room = 1;
            reqLevel = 21;
            spearX = 0;
            spearY = -2;

        } else if(currentX >= 1925 && currentX <= 1941 && currentY <= 4458 && currentY >= 4449 ){
            room = 2;
            reqLevel = 31;
            spearX = 2;
            spearY = 0;
        } else if(currentX >= 1941 && currentX <= 1954 && currentY >=4421 && currentY <= 4432 ){
            room = 3;
            reqLevel = 41;
            spearX = 0;
            spearY = 2;
        } else if(currentX >= 1949 && currentX <= 1959 && currentY <= 4477 && currentY >= 4464){
            room = 4;
            reqLevel = 51;
            spearX = 0;
            spearY = -2;
        } else if(currentX >= 1968 && currentX <= 1978 && currentY <= 4436 && currentY >= 4420){
            room = 5;
            reqLevel = 61;
            spearX = 0;
            spearY = 2;
        } else if(currentX >= 1970 && currentX <= 1979 && currentY <= 4471 && currentY >= 4424){
            room = 6;
            reqLevel = 71;
            spearX = 0;
            spearY = -2;
        } else if(currentX >= 1923 && currentX <= 1931 && currentY <= 4439 && currentY >= 4424){
            room = 8;
            reqLevel = 81;
            spearX = 0;
            spearY = 2;
        }
        GameObject object = node.asObject();
        droom = (double) room;
        switch (object.getId()) {
            case 16517:
                if (option.equals("Pass") || option.equals("pass")) {
                    if (reqLevel > level){
                        player.getPacketDispatch().sendMessage("You need to be at least level " + reqLevel + " thieving.");
                        break;
                    }
                    boolean success = success(player, Skills.THIEVING);
                    player.animate(animations[success ? 1 : 0]);
                    if (success) {
                        player.getPacketDispatch().sendMessage("You successfully pass the spears.");
                        player.getSkills().addExperience(Skills.THIEVING, 30 + (room * 20), true);
                        int moveX = player.getLocation().getX();
                        int moveY = player.getLocation().getY();
                        Location moveto = new Location(moveX + spearX, moveY + spearY);
                        player.getProperties().setTeleportLocation(moveto);
                        //player.moveStep();
                    } else {
                        player.getPacketDispatch().sendMessage("You fail to pass the spears.");
                    }
                }
                break;
            case 16503:
            case 16502:
            case 16501:
                if (option.equals("search") || option.equals("Search")) {
                    if (reqLevel > level){
                        player.getPacketDispatch().sendMessage("You need to be at least level " + reqLevel + " thieving.");
                        break;
                    }
                    boolean success = success(player, Skills.THIEVING);
                    player.animate(animations[success ? 1 : 0]);
                    player.lock(2);
                    if (success) {
                        player.getPacketDispatch().sendMessage("You successfully search the urn...");
                        player.getSkills().addExperience(Skills.THIEVING, 25 + (room * 20), true);
                        player.getInventory().add(ARTIFACTS[((int)Math.floor(droom / 3))][RandomFunction.random(3)]);
                        rollSceptre(player);
                    } else {
                        player.getPacketDispatch().sendMessage("You failed and got bit by a snake.");
                    }
                }
                break;
            case 16473:
                if(option.equals("search") || option.equals("Search")) {
                    if (reqLevel > level){
                        player.getPacketDispatch().sendMessage("You need to be at least level " + reqLevel + " thieving.");
                        break;
                    }
                    player.getPacketDispatch().sendMessage("You search the chest...");
                    player.animate(animations[1]);
                    player.lock(2);
                    player.getInventory().add(ARTIFACTS[RandomFunction.random(1,3)][RandomFunction.random(3)]);
                    rollSceptre(player);
                    player.getPacketDispatch().sendMessage("And you find an artifact!");
                    player.getSkills().addExperience(Skills.THIEVING,40 + (room * 20));
                }
                break;
            case 16495:
                if(option.equals("open") || option.equals("Open")) {
                    if (reqLevel > level){
                        player.getPacketDispatch().sendMessage("You need to be at least level " + reqLevel + " thieving.");
                        break;
                    }
                    boolean willSpawnMummy = (RandomFunction.random(1,5) == 3);
                    player.animate(animations[1]);
                    player.getPacketDispatch().sendMessage("You open the sarcophagus and....");
                    if(willSpawnMummy) {
                        player.getPacketDispatch().sendMessage("A mummy crawls out!");
                        NPC mummy = NPC.create(1958, player.getLocation());
                        mummy.setRespawn(false);
                        mummy.setAggressive(true);
                        mummy.init();
                    } else {
                        player.getPacketDispatch().sendMessage("You find some loot inside.");
                        player.getInventory().add(ARTIFACTS[RandomFunction.random(0,3)][RandomFunction.random(3)]);
                        rollSceptre(player);
                        player.getSkills().addExperience(Skills.STRENGTH,50 + (room * 20));
                    }
                }
                break;
            case 16475:
                if(option.equals("pick-lock") && room < 8) {
                    if (reqLevel > level){
                        player.getPacketDispatch().sendMessage("You need to be at least level " + reqLevel + " thieving.");
                        break;
                    }
                    player.animate(animations[1]);
                    player.lock(2);
                    boolean doesOpen = success(player, Skills.THIEVING);
                    if (doesOpen) {
                        player.getPacketDispatch().sendMessage("The door opens!");
                        player.getProperties().setTeleportLocation(room_entrance[room]);
                    } else {
                        player.getPacketDispatch().sendMessage("You fail to unlock the door.");
                    }
                } else if(room == 8) {
                    ClimbActionHandler.climb(player, ClimbActionHandler.CLIMB_UP, Location.create(3288, 2801, 0));
                }
                break;
        }
        return true;
    }
}