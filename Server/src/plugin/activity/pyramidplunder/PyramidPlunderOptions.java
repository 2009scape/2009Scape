/*
package plugin.activity.pyramidplunder;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.content.global.action.ClimbActionHandler;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.combat.ImpactHandler;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.zone.ZoneMonitor;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;
import org.crandor.tools.RandomFunction;

*/
/**
 * Handle pyramid plunder object interactions
 * @author ceik
 *//*

*/
/**
 * PyramidPlunderOptions defines interactions for pyramid plunder
 * Copyright (C) 2020  2009scape, et. al
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the modified GNU General Public License
 * as published by the Free Software Foundation and included in this repository; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *//*


@InitializablePlugin
public final class PyramidPlunderOptions extends OptionHandler {
    Item[][] ARTIFACTS = { {new Item(9032),new Item(9036), new Item(9026)}, {new Item(9042), new Item(9030), new Item(9038)}, {new Item(9040), new Item(9028), new Item(9034)} };
    private static final Animation[] animations = new Animation[] { new Animation(2247), new Animation(2248), new Animation(1113), new Animation(2244) };
    int reqLevel;
    //Player player;
    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ObjectDefinition.forId(16517).getConfigurations().put("option:pass",this);
        ObjectDefinition.forId(16503).getConfigurations().put("option:search",this);
        ObjectDefinition.forId(16502).getConfigurations().put("option:search",this);
        ObjectDefinition.forId(16501).getConfigurations().put("option:search",this);
        ObjectDefinition.forId(16501).getConfigurations().put("option:check for snakes",this);
        ObjectDefinition.forId(16502).getConfigurations().put("option:check for snakes",this);
        ObjectDefinition.forId(16503).getConfigurations().put("option:check for snakes",this);
        ObjectDefinition.forId(16473).getConfigurations().put("option:search",this);
        ObjectDefinition.forId(16495).getConfigurations().put("option:open",this);
        ObjectDefinition.forId(16475).getConfigurations().put("option:pick-lock",this);
        ObjectDefinition.forId(16509).getConfigurations().put("option:search",this);
        ObjectDefinition.forId(16510).getConfigurations().put("option:search",this);
        ObjectDefinition.forId(16511).getConfigurations().put("option:search",this);


        return this;
    }
    public void rollSceptre(Player player){
        if(RandomFunction.random(1000) == 451){
            if(!player.getInventory().isFull()) {
                player.getInventory().add(new Item(9044));
                player.getPacketDispatch().sendMessage("<col=7f03ff>You find a strange object.");
            } else {
                player.getBank().add(new Item(9044));
                player.getPacketDispatch().sendMessage("<col=7f03ff>You sense that something has appeared in your bank.");
            }
        }
    }
    public final boolean success(final Player player, final int skill) {
        double level = player.getSkills().getLevel(skill);
        double successChance = Math.ceil((level * 50 - reqLevel) / reqLevel / 3 * 4);
        int roll = RandomFunction.random(99);
        if (successChance >= roll) {
            return true;
        }
        return false;
    }
    @Override
    public boolean handle(Player player, Node node, String option) {
        PlunderObjectManager manager = player.getPlunderObjectManager();
        int NPCDeathTime = GameWorld.getTicks() + (1000 / 6);
        Location room_entrance[] = {new Location(1927,4477), new Location(1927,4453), new Location(1943,4421), new Location(1954,4477), new Location(1974,4420), new Location(1977,4471), new Location(1927, 4424), new Location(1965,4444)};
        int level = player.getSkills().getLevel(Skills.THIEVING);
        int room = 0;
        int spearX = 0;
        int spearY = 0;
        double droom = 0.0;
        ZoneMonitor zmon = player.getZoneMonitor();
        if(zmon.isInZone("plunder:room1")){
            room = 1;
            reqLevel = 21;
            spearX = 0;
            spearY = -2;
        } else if(zmon.isInZone("plunder:room2")){
            room = 2;
            reqLevel = 31;
            spearX = 2;
            spearY = 0;
        } else if(zmon.isInZone("plunder:room3")){
            room = 3;
            reqLevel = 41;
            spearX = 0;
            spearY = 2;
        } else if(zmon.isInZone("plunder:room4")){
            room = 4;
            reqLevel = 51;
            spearX = 0;
            spearY = -2;
        } else if(zmon.isInZone("plunder:room5")){
            room = 5;
            reqLevel = 61;
            spearX = 0;
            spearY = 2;
        } else if(zmon.isInZone("plunder:room6")){
            room = 6;
            reqLevel = 71;
            spearX = 0;
            spearY = -2;
        } else if(zmon.isInZone("plunder:room7")){
            room = 7;
            reqLevel = 81;
            spearX = 0;
            spearY = 2;
        } else if(zmon.isInZone("plunder:room8")){
            room = 8;
            reqLevel = 91;
            spearX = -2;
            spearY = 0;
        }
        PlunderObject object = new PlunderObject(node.asObject().getId(),node.asObject().getLocation(),player);
        droom = (double) room;
        switch (object.getId()) {
            case 16517:
                if (option.equalsIgnoreCase("pass")) {
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
                if (option.equalsIgnoreCase("search")) {
                    if (reqLevel > level){
                        player.getPacketDispatch().sendMessage("You need to be at least level " + reqLevel + " thieving.");
                        break;
                    }
                    if (manager.objectList.contains(object) ? manager.objectList.get(manager.objectList.indexOf(object)).playerOpened : false){
                        player.getPacketDispatch().sendMessage("You've already looted this.");
                        break;
                    }
                    boolean success = success(player, Skills.THIEVING);
                    player.animate(animations[success ? 1 : 0]);
                    player.lock(2);
                    if (manager.objectList.contains(object) ? manager.objectList.get(manager.objectList.indexOf(object)).snakeCharmed : success) {
                        player.getPacketDispatch().sendMessage("You successfully search the urn...");
                        player.getSkills().addExperience(Skills.THIEVING, 25 + (room * 20), true);
                        player.getInventory().add(ARTIFACTS[((int)Math.floor(droom / 3))][RandomFunction.random(3)]);
                        rollSceptre(player);
                        if(!manager.objectList.contains(object)) {
                            object.playerOpened = true;
                            manager.register(object);
                        } else {
                            manager.objectList.get(manager.objectList.indexOf(object)).playerOpened = true;
                        }
                        switch(object.getId()){
                            case 16501:
                                ObjectBuilder.replace(node.asObject(), node.asObject().transform(16505), 5);
                                break;
                            case 16502:
                                ObjectBuilder.replace(node.asObject(), node.asObject().transform(16506), 5);
                                break;
                            case 16503:
                                ObjectBuilder.replace(node.asObject(), node.asObject().transform(16507), 5);
                                break;
                        }
                    } else {
                        player.getPacketDispatch().sendMessage("You failed and got bit by a snake.");
                        player.getImpactHandler().manualHit(player,RandomFunction.random(2,8), ImpactHandler.HitsplatType.NORMAL);
                    }
                } else if(option.equalsIgnoreCase("check for snakes")){
                    if(manager.objectList.contains(object) && manager.objectList.get(manager.objectList.indexOf(object)).snakeCharmed){
                        player.getPacketDispatch().sendMessage("You already checked for snakes.");
                    } else {
                        player.getPacketDispatch().sendMessage("You check the urn for snakes...");
                        if (!manager.objectList.contains(object)) {
                            manager.register(object);
                        }
                        manager.originalIndex = manager.objectList.indexOf(object);
                        switch(object.getId()){
                            case 16501:
                                ObjectBuilder.replace(node.asObject(), node.asObject().transform(16509), 5);
                                break;
                            case 16502:
                                ObjectBuilder.replace(node.asObject(), node.asObject().transform(16510), 5);
                                break;
                            case 16503:
                                ObjectBuilder.replace(node.asObject(), node.asObject().transform(16511), 5);
                                break;
                        }
                    }
                }
                break;
            case 16509:
            case 16510:
            case 16511:
                if(option.equalsIgnoreCase("search") && player.getInventory().containsItem(new Item(4605))){
                    player.animate(new Animation(1877));
                    player.getPacketDispatch().sendMessage("You charm the snake into docility.");
                    if(!manager.objectList.contains(manager.objectList.get(manager.originalIndex))) {
                        object.snakeCharmed = true;
                        manager.register(object);
                    } else {
                        manager.objectList.get(manager.originalIndex).snakeCharmed = true;
                    }
                } else {
                    player.getImpactHandler().manualHit(player, RandomFunction.random(2, 8), ImpactHandler.HitsplatType.NORMAL);
                    player.getPacketDispatch().sendMessage("The snake bites you.");
                }
                break;
            case 16473:
                if(option.equalsIgnoreCase("search")) {
                    boolean willSpawnSwarm = (RandomFunction.random(1,20) == 10);
                    if (reqLevel > level){
                        player.getPacketDispatch().sendMessage("You need to be at least level " + reqLevel + " thieving.");
                        break;
                    }
                    if (manager.objectList.contains(object) ? manager.objectList.get(manager.objectList.indexOf(object)).playerOpened : false){
                        player.getPacketDispatch().sendMessage("You've already looted this.");
                        break;
                    }
                    player.getPacketDispatch().sendMessage("You search the chest...");
                    player.animate(animations[1]);
                    player.lock(2);
                    if(willSpawnSwarm) {
                        player.getPacketDispatch().sendMessage("A swarm flies out!");
                        PyramidPlunderSwarmNPC swarm = new PyramidPlunderSwarmNPC(2001,player.getLocation(),player);
                        swarm.setRespawn(false);
                        swarm.setAggressive(true);
                        swarm.init();
                    } else {
                        player.getInventory().add(ARTIFACTS[RandomFunction.random(1, 3)][RandomFunction.random(3)]);
                        rollSceptre(player);
                        player.getPacketDispatch().sendMessage("And you find an artifact!");
                        player.getSkills().addExperience(Skills.THIEVING, 40 + (room * 20));
                    }
                    ObjectBuilder.replace(node.asObject(), node.asObject().transform(16474), 5);
                    if(!manager.objectList.contains(object)) {
                        object.playerOpened = true;
                        manager.register(object);
                    } else {
                        manager.objectList.get(manager.objectList.indexOf(object)).playerOpened = true;
                    }
                }
                break;
            case 16495:
                if(option.equalsIgnoreCase("open")) {
                    if (reqLevel > level){
                        player.getPacketDispatch().sendMessage("You need to be at least level " + reqLevel + " thieving.");
                        break;
                    }
                    if (manager.objectList.contains(object) ? manager.objectList.get(manager.objectList.indexOf(object)).playerOpened : false){
                        player.getPacketDispatch().sendMessage("You've already looted this.");
                        break;
                    }
                    boolean willSpawnMummy = (RandomFunction.random(1,5) == 3);
                    player.animate(animations[1]);
                    player.getPacketDispatch().sendMessage("You open the sarcophagus and....");
                    if(willSpawnMummy) {
                        player.getPacketDispatch().sendMessage("A mummy crawls out!");
                        PyramidPlunderMummyNPC mummy = new PyramidPlunderMummyNPC(1958, player.getLocation(),player);
                        mummy.setRespawn(false);
                        mummy.setAggressive(true);
                        mummy.init();
                    } else {
                        player.getPacketDispatch().sendMessage("You find some loot inside.");
                        player.getInventory().add(ARTIFACTS[RandomFunction.random(0,3)][RandomFunction.random(3)]);
                        rollSceptre(player);
                        player.getSkills().addExperience(Skills.STRENGTH,50 + (room * 20));
                    }
                    ObjectBuilder.replace(node.asObject(), node.asObject().transform(16496), 5);
                    if(!manager.objectList.contains(object)) {
                        object.playerOpened = true;
                        manager.register(object);
                    } else {
                        manager.objectList.get(manager.objectList.indexOf(object)).playerOpened = true;
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
}*/
