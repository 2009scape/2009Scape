package plugin.quest.tutorials.tutorialisland;

import core.game.component.Component;
import core.game.node.entity.combat.equipment.WeaponInterface;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.HintIconManager;
import core.game.node.object.GameObject;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.game.world.repository.Repository;

/**
 * Represents a tutorial stage.
 * @author 'Vexia
 * @date 06/03/2013
 */


public enum TutorialStage {
	STAGE_0(0) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.lock(1);
				player.getProperties().setTeleportLocation(Location.create(3094, 3107, 0));
				player.getInterfaceManager().hideTabs(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
				player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Repository.findNPC(945)));
				GameWorld.Pulser.submit(new Pulse(1) {
					@Override
					public boolean pulse() {
						CharacterDesign.open(player);
						return true;
					}
				});
			}
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Getting started", "To start the tutorial use your left mouse button to click on the", GameWorld.getName() + " Guide in this room. He is indicated by a flashing", "yellow arrow above his head. If you can't see him, use your", "keyboard's arrow keys to rotate the view."));
		}
	},
	STAGE_1(1) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13);
				player.getConfigManager().set(1021, 12);
			} else {
				removeHintIcon(player);
			}
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("", "Player controls", "Please click on the flashing spanner icon found at the bottom", "right of your screen. This will display your player controls.", ""));
		}
	},
	STAGE_2(2) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13);
			} else {
				player.getConfigManager().set(1021, 0);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Repository.findNPC(945)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendScrollMessageWithBlueTitle("Player controls", "On the side panel you can now see a variety of options from", "changing the brightness of the screen and the volume of", "music, to selecting whether your player should accept help", "from other players. Don't worry about these too much for now,", "they will become clearer as you explore the game. Talk to the", GameWorld.getName() + " Guide to continue."));
		}
	},
	STAGE_3(3) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13);
			} else {
				removeHintIcon(player);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Location.create(3098, 3107, 0), 1, -1, player.getHintIconManager().freeSlot(), 125, 3));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendScrollMessageWithBlueTitle("Interacting with scenery", "You can interact with many items of scenery by simply clicking", "on them. Right clicking will also give more options. Feel free to", "try it with the things in this room, then click on the door", "indicated with the yellow arrow to go though to the next", "instructor."));
		}
	},
	STAGE_4(4) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13);
			} else {
				removeHintIcon(player);
			}
			player.getConfigManager().set(406, 2);
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Repository.findNPC(943)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Moving around", "Follow the path to find the next instructor. Clicking on the", "ground will walk you to that point. Talk to the Survival Expert by", "the pond to continue the tutorial. Remember you can rotate", "the view by pressing the arrow keys."));
		}
	},
	STAGE_5(5) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 12, 13);
				player.getConfigManager().set(406, 2);
			} else {
				player.getInterfaceManager().openTab(new Component(149));
				removeHintIcon(player);
			}
			player.getConfigManager().set(1021, 4);
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Viewing the items that you were given.", "Click on the flashing backpack icon to the right hand side of", "the main window to view your inventory. Your inventory is a list", "of everything you have in your backpack.", ""));
		}
	},
	STAGE_6(6) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 12, 13);
				player.getConfigManager().set(406, 2);
			} else {
				player.getConfigManager().set(1021, 0);
			}
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Cut down a tree", "You can click on the backpack icon at any time to view the", "items that you currently have in your inventory. You will see", "that you now have an axe in your inventory. Use this to get", "some logs by clicking on one of the trees in the area."));
		}
	},
	STAGE_7(7) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 12, 13);
				player.getConfigManager().set(406, 2);
			} else {
				removeHintIcon(player);
			}
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Please wait.", "", "Your character is now attempting to cut down the tree. Sit back", "for a moment while " + (player.getAppearance().isMale() ? "he" : "she") + " does all the hard work.", ""));
		}
	},
	STAGE_8(8) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 12, 13);
				player.getConfigManager().set(406, 2);
			}
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Making a fire", "Well done! You managed to cut some logs from the tree! Next,", "use the tinderbox in your inventory to light the logs.", "First click on the tinderbox to 'use' it.", "Then click on the logs in your inventory to light them."));
		}
	},
	STAGE_9(9) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 12, 13);
				player.getConfigManager().set(406, 2);
			}
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Please wait.", "", "Your character is now attempting to light a fire.", "This should only take a few seconds.", ""));
		}
	},
	STAGE_10(10) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 2, 4, 5, 6, 7, 8, 9, 10, 12, 13);
				player.getConfigManager().set(406, 2);
			} else {
				player.getInterfaceManager().openTab(new Component(320));
			}
			player.getConfigManager().set(1021, 2);
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("You gained some experience.", "Click on the flashing bar graph icon near the inventory button to see", "your skill state.", "", ""));
		}
	},
	STAGE_11(11) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 2, 4, 5, 6, 7, 8, 9, 10, 12, 13);
				player.getConfigManager().set(406, 2);
			} else {
				player.getConfigManager().set(1021, 0);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Repository.findNPC(943)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Your skill stats.", "Here you will see how good your skills are. As you move your mouse", "over any of the icons in this tab, the small yellow popup box will show", "you the exact amount of experience you have and how much is", "needed to get to the next level. Speak to the survival guide."));
		}
	},
	STAGE_12(12) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 2, 4, 5, 6, 7, 8, 9, 10, 12, 13);
				player.getConfigManager().set(406, 2);
			} else {
				removeHintIcon(player);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Repository.findNPC(952)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Catch some shrimp", "Click on the bubbling fishing spot indicated by the flashing arrow.", "Remember, you can check your inventory by clicking the backpack", "icon.", ""));
		}
	},
	STAGE_13(13) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 2, 4, 5, 6, 7, 8, 9, 10, 12, 13);
				player.getConfigManager().set(406, 2);
			} else {
				removeHintIcon(player);
			}
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Please wait.", "", "This should only take a few seconds.", "As you gain Fishing experience you'll find that there are many types", "of fish and many ways to catch them."));
		}
	},
	STAGE_14(14) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 2, 4, 5, 6, 7, 8, 9, 10, 12, 13);
			}
			player.getConfigManager().set(406, 3);
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Cooking your shrimp", "Now you have caught some shrimp, let's cook it. First light a fire: chop", "down a tree and then use the tinderbox on the logs. If you've lost", "your hatchet or tinderbox Brynna will give you another.", ""));
		}
	},
	STAGE_15(15) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 2, 4, 5, 6, 7, 8, 9, 10, 12, 13);
				player.getConfigManager().set(406, 3);
			}
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Burning your shrimp", "You have just burnt your first shrimp. This is normal. As you get", "more experience in Cooking you will burn stuff less often. Let's try", "cooking without burning it this time.", ""));/*
																																																																															 * "First catch some more shrimp,"
																																																																															 * ,
																																																																															 * "then use them on a fire."
																																																																															 * )
																																																																															 * )
																																																																															 * ;
																																																																															 */
		}
	},
	STAGE_16(16) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 2, 4, 5, 6, 7, 8, 9, 10, 12, 13);
				player.getConfigManager().set(406, 3);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Location.create(3089, 3091, 0), 1, -1, player.getHintIconManager().freeSlot(), 75, 4));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Well done, you've just cooked your first " + GameWorld.getName() + " meal.", "If you'd like a recap on anything you've learnt so far, speak to", "the Survival Expert. You can now move on to the next", "instructor. Click on the gate shown and follow the path.", "Remember, you can move the camera with the arrow keys."));
		}
	},
	STAGE_17(17) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 2, 4, 5, 6, 7, 8, 9, 10, 12, 13);
			} else {
				removeHintIcon(player);
			}
			player.getConfigManager().set(406, 4);
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Location.create(3078, 3084, 0), 1, -1, player.getHintIconManager().freeSlot(), 125, 4));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Find your next instructor.", "Follow the path until you get to the door with the yellow arrow", "above it. Click on the door to open it. Notice the mini-map in", "the top right, this shows a top down view of the area around", "you. This can also be used for navigation."));
		}
	},
	STAGE_18(18) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 2, 4, 5, 6, 7, 8, 9, 10, 12, 13);
				player.getConfigManager().set(406, 4);
			} else {
				removeHintIcon(player);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Repository.findNPC(942)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Find your next instructor", "Talk to the chef indicated. He will teach you the more advanced", "aspects of Cooking such as combining ingredients. He will also", "teach you about your music player menu as well.", ""));
		}
	},
	STAGE_19(19) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 2, 4, 5, 6, 7, 8, 9, 10, 12, 13);
				player.getConfigManager().set(406, 4);
			} else {
				removeHintIcon(player);
			}
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Making dough", "This is the base for many of the meals. To make dough we must mix", "flour and water. First right click the bucket of water and select use.", "then left click on the pot of flour.", ""));
		}
	},
	STAGE_20(20) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 2, 4, 5, 6, 7, 8, 9, 10, 12, 13);
				player.getConfigManager().set(406, 4);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Location.create(3075, 3081, 0), 1, -1, player.getHintIconManager().freeSlot(), 75, 2));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Cooking dough", "Now you have made dough you can cook it. To cook the dough use", "it with the range shown by the arrow. If you lose your dough talk to", "Lev he will give you more ingredients.", ""));
		}
	},
	STAGE_21(21) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 2, 4, 5, 6, 7, 8, 9, 10, 12);
				player.getConfigManager().set(406, 4);
			} else {
				removeHintIcon(player);
				player.getInterfaceManager().openTab(new Component(187));
			}
			player.getConfigManager().set(1021, 14);
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Cooking dough", "Well done! Your first loaf of bread. As you gain experience in", "Cooking, you will be able to make other things like pies, cakes and even ", "kebabs. Now you've got the hang of cooking, lets move on. Click on", "the flashing icon in the bottom right to see the Music Player."));
		}
	},
	STAGE_22(22) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 2, 4, 5, 6, 7, 8, 9, 10, 12);
				player.getConfigManager().set(406, 4);
			} else {
				player.getConfigManager().set(1021, 0);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Location.create(3072, 3090, 0), 1, -1, player.getHintIconManager().freeSlot(), 125, 4));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("The Music Player", "From this interface you can control the music that is played. As you", "explore the world and complete quests, more of the tunes will become", "unlocked. Once you've examined this menu use the next door to", "continue."));
		}
	},
	STAGE_23(23) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 2, 4, 5, 6, 7, 8, 9, 10);
			} else {
				removeHintIcon(player);
				player.getInterfaceManager().openTab(new Component(464));
			}
			player.getWalkingQueue().reset();
			player.getLocks().lockMovement(GameWorld.getTicks() + 100000);
			player.getConfigManager().set(406, 6);
			player.getConfigManager().set(1021, 13);
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Emotes", "Now how about showing some feelings? You will see a flashing icon in", "the shape of a person. Click on that to acces your emotes.", "", ""));
		}
	},
	STAGE_24(24) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 2, 4, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 6);
			}
			player.getLocks().unlockMovement();
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Emotes", "For those situtation where words don't quite describe how you feel try", "an emote. Go ahead try one out! You might notice that some of the", "emotes are grey and cannot be used now. Don't worry! As you", "progress further into the game you'll gain acces to all sorts of things."));
		}
	},
	STAGE_25(25) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 2, 4, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 6);
			}
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Running", "It's only a short distance to the next guide.", "Why not try running there? To do this click on the run icon in", "your settings tab.", ""));
		}
	},
	STAGE_26(26) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 2, 4, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 6);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Repository.findNPC(949)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendScrollMessageWithBlueTitle("Run to the next guide", "Now that you have the run button turned on, follow the path", "until you come to the end. You may notice that the number on", "the button goes down. This is your run energy. If your run", "energy reaches zero, you'll stop running. Click on the door to", "pass through it."));
		}
	},
	STAGE_27(27) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 2, 4, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 7);
			}
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Talk with the Quest Guide.", "", "He will tell you all about quests.", "", ""));
		}
	},
	STAGE_28(28) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 4, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 7);
			}
			player.getConfigManager().set(1021, 0);
			player.getInterfaceManager().openTab(new Component(274));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Your quest Journal", "This is your quest Journal, a list of all the quests in the game. Talk", "to the Quest Guide again for an explanation.", "", ""));
		}
	},
	STAGE_29(29) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 4, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 7);
			} else {
				removeHintIcon(player);
			}
			player.getConfigManager().set(1021, 0);
			player.getInterfaceManager().openTab(new Component(274));
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, RegionManager.getObject(0, 3088, 3119)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("", "Moving on.", "It's time to enter some caves. Click on the ladder to go down to the", "next area.", ""));
		}
	},
	STAGE_30(30) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 4, 5, 6, 7, 8, 9, 10);
			} else {
				removeHintIcon(player);
			}
			player.getConfigManager().set(1021, 0);
			player.getInterfaceManager().openTab(new Component(274));
			player.getConfigManager().set(406, 8);
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Repository.findNPC(948)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Mining and Smithing", "Next let's get you a weapon, or more to the point, you can make", "your first weapon yourself. Don't panic, the Mining Instructor will", "help you. Talk to him and he'll tell you all about it.", ""));
		}
	},
	STAGE_31(31) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 4, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 8);
			} else {
				removeHintIcon(player);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, RegionManager.getObject(0, 3076, 9504)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Prospecting", "To prospect a mineable rock, just right click it and select the prospect", "rock option. This will tell you the type of ore you can mine from it.", "Try it now on one of the rocks indicated", ""));
		}
	},
	STAGE_32(32) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 4, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 8);
			} else {
				removeHintIcon(player);
			}
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("", "", "Please wait.", "Your character is now attempting to prospect the rock. This should", "Only take a few seconds."));
		}
	},
	STAGE_33(33) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 4, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 8);
			} else {
				removeHintIcon(player);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, RegionManager.getObject(0, 3086, 9501)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("It's tin.", "So now you know there's tin in the grey rocks, try prospecting the", "brown ones next.", "", ""));
		}
	},
	STAGE_34(34) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 4, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 8);
			} else {
				removeHintIcon(player);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Repository.findNPC(948)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendScrollMessageWithBlueTitle("It's copper.", "Talk to the Mining Instructor to find out about these types of", "ore and how you can mine them.", "He'll even give you the required tools.", ""));
		}
	},
	STAGE_35(35) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 4, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 8);
			} else {
				removeHintIcon(player);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, RegionManager.getObject(0, 3076, 9504)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Mining", "It's quite simple really. All you need to do is right click on the rock", "and select 'mine'. You can only mine when you have a pickaxe. So", "give it a try: first mine one tin ore.", ""));
		}
	},
	STAGE_36(36) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 4, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 8);
			} else {
				removeHintIcon(player);
			}
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("", "Please wait.", "Your character is now attempting to mine the rock.", "This should only take a few seconds.", ""));
		}
	},
	STAGE_37(37) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 4, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 8);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, RegionManager.getObject(0, 3086, 9501)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Mining", "Now you have some tin ore you just need some copper ore, then", "you'll have all you need to create a bronze bar. As you did before", "right click on the copper rock and select 'mine'.", ""));
		}
	},
	STAGE_38(38) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 4, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 8);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, RegionManager.getObject(0, 3076, 9504)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Mining", "Now you have some copper ore you just need some tin ore, then", "you'll have all you need to create a bronze bar. As you did before", "right click on the tin rock and select 'mine'.", ""));
		}
	},
	STAGE_39(39) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 4, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 9);
			} else {
				removeHintIcon(player);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, RegionManager.getObject(0, 3078, 9495)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Smelting", "You should now have both some copper and tin ore. So let's smelt", "them to make a bronze bar. To do this right click on either tin or", "copper ore and select use then left click on the furnance. Try it now.", ""));
		}
	},
	STAGE_40(40) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 4, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 9);
			} else {
				removeHintIcon(player);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Repository.findNPC(948)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("You've made a bronze bar!", "Speak to the Mining Instructor and he'll show you how to make", "it into a weapon.", "", ""));
		}
	},
	STAGE_41(41) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 4, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 9);
			} else {
				removeHintIcon(player);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, RegionManager.getObject(Location.create(3083, 9499, 0))));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Smithing a dagger", "To smith you'll need a hammer - like the one you were given by", "Dezzick - access to an anvil like the one with the arrow over it", "and enough metal bars to make what you are trying to smith.", "To start the process, use the bar on one of the anvils."));
		}
	},
	STAGE_42(42) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 4, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 9);
			} else {
				removeHintIcon(player);
			}
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Smithing a dagger.", "Now you have the Smithing menu open, you will see a list of all", "the thing you can make. Only the dagger can be made at this time", "this is shown by the white text under it. You'll need", "to select the dagger to continue."));
		}
	},
	STAGE_43(43) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 4, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 9);
			} else {
				removeHintIcon(player);
			}
			GameObject object = RegionManager.getObject(Location.create(3094, 9502, 0));
			if (object != null) {
				player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, object));
			} else {
				System.out.println("Object is null tutorial stage 43!");
			}
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("You've finished in this area.", "So let's move on. Go through the gates shown by the arrow.", "Remember, you may need to move the camera to see your", "surroundings.", ""));
		}
	},
	STAGE_44(44) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 4, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 10);
			} else {
				removeHintIcon(player);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Repository.findNPC(944)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Combat.", "In this area you will find out about combat, both melee and", "ranged. Speak to the guide and he'll tell you about it.", "", ""));
		}
	},
	STAGE_45(45) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 4, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 10);
			} else {
				removeHintIcon(player);
			}
			player.getInterfaceManager().openTab(new Component(387));
			player.getConfigManager().set(1021, 5);
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Wielding weapons.", "", "You now have access to a new interface. Click on the flashing", "icon of a man, the one to the right of your backpack icon.", ""));
		}
	},
	STAGE_46(46) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 10);
			}
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("This is your worn inventory.", "From here you can see what items you have equipped. Let's", "get one of those slots filled, go back to your inventory and", "right click your dagger, select wield from the menu.", ""));
		}
	},
	STAGE_47(47) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 10);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Repository.findNPC(944)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendScrollMessageWithBlueTitle("You're now holding your dagger.", "Clothes, armor, weapons and many other items are equipped", "like this. You can unequip items by clicking on the item in the", "worn inventory. You can close this window by clicking on the", "small 'x' in the top right hand corner. Speak to the Combat", "Instructor to continue."));
		}
	},
	STAGE_48(48) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 10);
			} else {
				removeHintIcon(player);
			}
			player.getInterfaceManager().openTab(new Component(387));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendScrollMessageWithBlueTitle("Unequipping items.", "In your worn inventory panel, right click on the dagger and", "select the remove option from the drop down list. After you've", "unequipped the dagger, wield the sword and shield. As you", "pass the mouse over an item you will see its name appear at", "the top left of the screen."));
		}
	},
	STAGE_49(49) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(0, 5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 10);
			}
			player.getConfigManager().set(406, 10);
			player.getConfigManager().set(1021, 1);
			WeaponInterface inter = player.getExtension(WeaponInterface.class);
			if (inter == null) {
				player.addExtension(WeaponInterface.class, inter = new WeaponInterface(player));
			}
			player.getInterfaceManager().openTab(inter);
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Combat interface.", "Click on the flashing crossed swords icon to see the combat", "interface.", "", ""));
		}
	},
	STAGE_50(50) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 11);
			}
			player.getConfigManager().set(406, 11);
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, RegionManager.getObject(Location.create(3110, 9518, 0))));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendScrollMessageWithBlueTitle("This is your combat interface.", "From this interface you can select the type of attack your", "character will use. Different monsters have different", "weaknesses. If you hover your mouse over the buttons, you", "will see the type of XP you will receive when using each type of", "attack. Now you have the tools needed for battle why not slay", "some rats. Click on the gates indicated to continue."));
		}
	},
	STAGE_51(51) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 11);
			} else {
				removeHintIcon(player);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Repository.findNPC(87)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Attacking", "To attack the rat, click it and select the attack option. You", "will then walk over to it and start hitting it.", "", ""));
		}
	},
	STAGE_52(52) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 11);
			} else {
				removeHintIcon(player);
			}
			player.getConfigManager().set(406, 11);
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Sit back and watch", "While you are fighting you will see a bar over your head. The", "bar shows how much health you have left. Your opponent will", "have one too. You will continue to attack the rat until it's dead", "or you do something else."));
		}
	},
	STAGE_53(53) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 12);
			}
			player.getConfigManager().set(406, 12);
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Well done, you've made your first kill!", "Pass through the gate and talk to the Combat Instructor; he", "will give you your next task.", "", ""));
		}
	},
	STAGE_54(54) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 12);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Repository.findNPC(87)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Rat ranging.", "Now you have a bow and some arrows. Before you can use", "them you'll need to equip them. Once equipped with the", "ranging gear try killing another rat. Remember, to attack right", "click on the monster and select attack."));
		}
	},
	STAGE_55(55) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 12);
			} else {
				removeHintIcon(player);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, RegionManager.getObject(Location.create(3111, 9526, 0))));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Moving on.", "You have completed the tasks here, to move on click on the", "ladder shown. If you need to go over any of what you learned", "here just talk to Vannaka and he'll tell you what he can.", ""));
		}
	},
	STAGE_56(56) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 14);
			} else {
				removeHintIcon(player);
			}
			player.getConfigManager().set(406, 14);
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, RegionManager.getObject(Location.create(3122, 3124, 0))));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Banking.", "Follow the path and you will come to the front of a building.", "This is the 'Bank of " + GameWorld.getName() + "' where you can store all your", "most valued items. To open your bank box just right click on an", "open booth indicated and select use."));
		}
	},
	STAGE_57(57) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 14);
			} else {
				removeHintIcon(player);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, RegionManager.getObject(Location.create(3125, 3124, 0))));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("This is your bank box.", "You can store stuff here for safekeeping. If you die, anything", "in your bank will be saved. To deposit something, right click it", "and select 'store'. One you've had a good look, close the", "window and move on through the door indicated."));
		}
	},
	STAGE_58(58) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 14);
			} else {
				removeHintIcon(player);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Repository.findNPC(947)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Financial advice.", "The guide here will tell you about making cash. Just click on", "him to hear what he's got to say.", "", ""));
		}
	},
	STAGE_59(59) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 14);
			} else {
				removeHintIcon(player);
			}
			GameObject object = RegionManager.getObject(Location.create(3130, 3124, 0));
			if (object != null) {
				player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, object));
			} else {
				System.out.println("Object is null tutorial stage 59!");
			}
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("", "Continue through the next door.", "", "", ""));
		}
	},
	STAGE_60(60) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 16);
			} else {
				removeHintIcon(player);
			}
			player.getConfigManager().set(406, 16);
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Repository.findNPC(954)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Prayer", "Follow the path to the chapel and enter it.", "Once inside talk to the monk. He'll tell you all about the prayer", "skill.", ""));
		}
	},
	STAGE_61(61) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(5, 6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 16);
			} else {
				removeHintIcon(player);
			}
			player.getInterfaceManager().openTab(new Component(271));
			player.getConfigManager().set(1021, 6);
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Your Prayer menu", "Click on the flashing icon to open the Prayer menu.", "", "", ""));

		}
	},
	STAGE_62(62) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 16);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Repository.findNPC(954)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("", "Your Prayer menu.", "Talk with Brother Brace and he'll tell you all about prayer.", "", ""));
		}
	},
	STAGE_63(63) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(6, 7, 8, 9, 10);
				player.getConfigManager().set(406, 16);
			}
			player.getInterfaceManager().openTab(new Component(550));
			player.getConfigManager().set(1021, 9);
			removeHintIcon(player);
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("", "Friends list.", "You should now see another new icon. Click on the flashing", "smiling face to open your friend list.", ""));
		}
	},
	STAGE_64(64) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(6, 7, 10);
				player.getConfigManager().set(406, 16);
			}
			player.getInterfaceManager().openTab(new Component(589));
			player.getInterfaceManager().openTab(new Component(551));
			player.getInterfaceManager().openTab(new Component(589));
			player.getConfigManager().set(1021, 10);
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("This is your friends list.", "This will be explained by Brother Brace shortly, but first click", "the other flashing icon next to the friends list one.", "", ""));
		}
	},
	STAGE_65(65) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(6, 7);
				player.getConfigManager().set(406, 16);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Repository.findNPC(954)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("This is your ignore list.", "The two lists, friends and ignore, can be very helpful for", "keeping track of when your friends are online or for blocking", "messages from people you simply don't like. Speak with", "Brother Brace and he will tell you more."));
		}
	},
	STAGE_66(66) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(6, 7);
				player.getConfigManager().set(406, 16);
			}
			removeHintIcon(player);
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, RegionManager.getObject(Location.create(3122, 3102, 0))));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Your final instructor!", "You're almost finished on tutorial island. Pass through the", "door to find the path leading to your last instructor.", "", ""));
		}
	},
	STAGE_67(67) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(6, 7);
				player.getConfigManager().set(406, 17);
			} else {
				removeHintIcon(player);
			}
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Repository.findNPC(946)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Your final instructor!", "Just follow the path to the wizards house, where you will be", "shown how to cast spells. Just talk with the mage indicated to", "find out more.", ""));
		}
	},
	STAGE_68(68) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getInterfaceManager().hideTabs(6, 7);
				player.getConfigManager().set(406, 17);
			} else {
				removeHintIcon(player);
			}
			player.getInterfaceManager().openTab(new Component(player.getSpellBookManager().getSpellBook()));
			player.getConfigManager().set(1021, 7);
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("Open up your final menu.", "Open up the Magic Spellbook tab by clicking on the flashing spellbook", "icon next to the Prayer List tab you just learned about.", "", ""));
		}
	},
	STAGE_69(69) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getConfigManager().set(406, 19);
			}
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("", "This is your spell list.", "", "Ask the mage about it.", ""));
		}
	},
	STAGE_70(70) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getConfigManager().set(406, 20);
			}
			player.getInterfaceManager().restoreTabs();
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Repository.findNPC(41)));
			Component.setUnclosable(player, player.getDialogueInterpreter().sendScrollMessageWithBlueTitle("Cast Wind Strike at a chicken.", "Now you have the runes you should see the Wind Strike icon", "at the top-left of your spellbook, second from the left. Walk over to", "the caged chickens, click the Wind Strike icon and then  ", "select one of the chickens to cast it on. It may take", "several tries. If you need more runes ask Terrova"));
		}
	},
	STAGE_71(71) {
		@Override
		public void run(final Player player) {
			if (login) {
				player.getConfigManager().set(406, 20);
			} else {
				removeHintIcon(player);
			}
			TutorialSession.getExtension(player).getDelayPulse().stop();
			player.getInterfaceManager().restoreTabs();
			player.setAttribute("tut-island:hi_slot", HintIconManager.registerHintIcon(player, Repository.findNPC(946)));
			player.removeAttribute("tut-island");
			Component.setUnclosable(player, player.getDialogueInterpreter().sendPlaneMessageWithBlueTitle("You have almost completed the tutorial!", "All you need to do now is teleport to the mainland. Just speak with", "Terrova and he'll tell you how to do that.", "You have almost completed the tutorial!", ""));
		}
	};

	/**
	 * The stage.
	 */
	private final int stage;

	/**
	 * Represents if it's a login, when the stage is loaded.
	 */
	protected boolean login;

	/**
	 * Constructs a new {@code TutorialStage} {@code Object}.
	 * @param stage the stage,
	 */
	TutorialStage(int stage) {
		this.stage = stage;
	}

	/**
	 * Method used to handle when the stage is loaded,
	 * @param player the player.
	 */
	public void run(final Player player) {
	}

	/**
	 * Represents if it is a login stage.
	 * @param login the login value.
	 * @return {@code True} if so.
	 */
	public boolean isLogin(boolean login) {
		return this.login = login;
	}

	/**
	 * @return the stage.
	 */
	public int getStage() {
		return stage;
	}

	/**
	 * Method used to load a new stage of the tutorial.
	 * @param stage the stage.
	 * @param login the login.
	 */
	public static void load(final Player player, int stage, boolean login) {
		if (TutorialSession.getExtension(player) != null) {
			TutorialSession.extend(player);
		}

		final TutorialStage tutorial_stage = forStage(stage);
		if (tutorial_stage == null) {
			System.err.println("Unhandled tutorial stage " + stage + ".");
			return;
		}
		player.setAttribute("tut-island", true);
		tutorial_stage.isLogin(login);
		tutorial_stage.run(player);
		TutorialSession.getExtension(player).setStage(stage);
		TutorialSession.getExtension(player).getDelayPulse().reset();
		TutorialSession.getExtension(player).getDelayPulse().hide();
	}

	/**
	 * Gets the tutorial stage by the stage id.
	 * @param id the id.
	 * @return the tutorial stage.
	 */
	public static TutorialStage forStage(int id) {
		for (TutorialStage stage : TutorialStage.values()) {
			if (stage.getStage() == id) {
				return stage;
			}
		}
		return null;
	}

	/**
	 * Removes the current hint icon (if any).
	 * @param player The player.
	 */
	public static void removeHintIcon(Player player) {
		int slot = player.getAttribute("tut-island:hi_slot", -1);
		if (slot < 0 || slot >= HintIconManager.MAXIMUM_SIZE) {
			return;
		}
		player.removeAttribute("tut-island:hi_slot");
		HintIconManager.removeHintIcon(player, slot);
	}
}
