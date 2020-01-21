-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 06, 2019 at 01:33 AM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `global`
--

-- --------------------------------------------------------

--
-- Table structure for table `dev_log`
--

CREATE TABLE `dev_log` (
  `id` int(11) UNSIGNED NOT NULL,
  `username` varchar(15) DEFAULT NULL,
  `content` longtext NOT NULL,
  `date` timestamp NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `highscores`
--

CREATE TABLE `highscores` (
  `id` int(11) UNSIGNED NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `overall_xp` int(11) NOT NULL DEFAULT 0,
  `total_level` int(11) NOT NULL DEFAULT 0,
  `ironManMode` varchar(15) NOT NULL DEFAULT 'NONE',
  `xp_0` int(11) NOT NULL DEFAULT 0,
  `xp_1` int(11) NOT NULL DEFAULT 0,
  `xp_2` int(11) NOT NULL DEFAULT 0,
  `xp_3` int(11) NOT NULL DEFAULT 0,
  `xp_4` int(11) NOT NULL DEFAULT 0,
  `xp_5` int(11) NOT NULL DEFAULT 0,
  `xp_6` int(11) NOT NULL DEFAULT 0,
  `xp_7` int(11) NOT NULL DEFAULT 0,
  `xp_8` int(11) NOT NULL DEFAULT 0,
  `xp_9` int(11) NOT NULL DEFAULT 0,
  `xp_10` int(11) NOT NULL DEFAULT 0,
  `xp_11` int(11) NOT NULL DEFAULT 0,
  `xp_12` int(11) NOT NULL DEFAULT 0,
  `xp_13` int(11) NOT NULL DEFAULT 0,
  `xp_14` int(11) NOT NULL DEFAULT 0,
  `xp_15` int(11) NOT NULL DEFAULT 0,
  `xp_16` int(11) NOT NULL DEFAULT 0,
  `xp_17` int(11) NOT NULL DEFAULT 0,
  `xp_18` int(11) NOT NULL DEFAULT 0,
  `xp_19` int(11) NOT NULL DEFAULT 0,
  `xp_20` int(11) NOT NULL DEFAULT 0,
  `xp_21` int(11) NOT NULL DEFAULT 0,
  `xp_22` int(11) NOT NULL DEFAULT 0,
  `xp_23` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `UID` int(11) UNSIGNED NOT NULL,
  `email` varchar(50) NOT NULL DEFAULT '',
  `username` varchar(15) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(35) DEFAULT NULL,
  `rights` int(1) NOT NULL DEFAULT 0,
  `email_activated` int(1) NOT NULL DEFAULT 0,
  `lastActive` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `donatorType` int(2) NOT NULL DEFAULT -1,
  `donationTotal` double(10,2) NOT NULL DEFAULT 0.00,
  `credits` int(5) NOT NULL DEFAULT 0,
  `icon` int(2) NOT NULL DEFAULT 0,
  `perks` varchar(500) NOT NULL DEFAULT '',
  `ip` longtext DEFAULT NULL,
  `mac` longtext DEFAULT NULL,
  `serial` longtext DEFAULT NULL,
  `computerName` varchar(2000) NOT NULL DEFAULT '',
  `monthlyVotes` int(11) NOT NULL DEFAULT 0,
  `netWorth` bigint(200) NOT NULL DEFAULT 0,
  `forumUID` int(11) NOT NULL DEFAULT -1,
  `ironManMode` varchar(15) NOT NULL DEFAULT 'NONE',
  `bank` longtext DEFAULT NULL,
  `inventory` longtext DEFAULT NULL,
  `equipment` longtext DEFAULT NULL,
  `ge` longtext DEFAULT NULL,
  `muteTime` bigint(20) NOT NULL DEFAULT -1,
  `banTime` bigint(20) NOT NULL DEFAULT -1,
  `profileImage` varchar(300) DEFAULT NULL,
  `contacts` longtext DEFAULT NULL,
  `blocked` longtext DEFAULT NULL,
  `clanName` varchar(15) NOT NULL DEFAULT '',
  `currentClan` varchar(15) DEFAULT NULL,
  `clanReqs` varchar(10) NOT NULL DEFAULT '1,0,8,9',
  `disconnectTime` bigint(20) NOT NULL DEFAULT 0,
  `lastWorld` int(3) NOT NULL DEFAULT -1,
  `chatSettings` varchar(10) NOT NULL DEFAULT '0,0,0',
  `timePlayed` bigint(20) DEFAULT 0,
  `lastLogin` bigint(20) NOT NULL DEFAULT 0,
  `lastGameIp` varchar(15) DEFAULT '',
  `countryCode` int(11) NOT NULL DEFAULT 0,
  `birthday` date DEFAULT NULL,
  `online` tinyint(1) NOT NULL DEFAULT 0,
  `signature` longtext DEFAULT NULL,
  `joined_date` timestamp NULL DEFAULT NULL,
  `posts` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`UID`, `email`, `username`, `password`, `salt`, `rights`, `email_activated`, `lastActive`, `donatorType`, `donationTotal`, `credits`, `icon`, `perks`, `ip`, `mac`, `serial`, `computerName`, `monthlyVotes`, `netWorth`, `forumUID`, `ironManMode`, `bank`, `inventory`, `equipment`, `ge`, `muteTime`, `banTime`, `profileImage`, `contacts`, `blocked`, `clanName`, `currentClan`, `clanReqs`, `disconnectTime`, `lastWorld`, `chatSettings`, `timePlayed`, `lastLogin`, `lastGameIp`, `countryCode`, `birthday`, `online`, `signature`, `joined_date`, `posts`) VALUES
(0, '', '2009Scape', '$2a$12$P0OU2A5S.lEYdkTq5kq3/u1UlfVkMYIS7WWbxsjjeyfxqTDbygpEe', '$2a$12$P0OU2A5S.lEYdkTq5kq3/u', 2, 0, '2019-11-06 00:29:03', 0, 0.00, 0, 0, '', '127.0.0.1', NULL, NULL, 'SERVER', 0, 0, -1, 'STANDARD', NULL, NULL, NULL, NULL, -1, -1, '', '', '', '2009Scape', '2009Scape', '0,0,8,9', 1572999889084, 1, '0,0,0', NULL, 1572999890885, '127.0.0.1', 0, NULL, 0, NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `id` int(11) UNSIGNED NOT NULL,
  `sender` varchar(15) NOT NULL DEFAULT '',
  `recipient` varchar(15) NOT NULL DEFAULT '',
  `subject` varchar(40) NOT NULL DEFAULT '',
  `content` longtext NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp(),
  `is_read` tinyint(1) NOT NULL,
  `s_delete` tinyint(11) NOT NULL,
  `r_delete` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `perks`
--

CREATE TABLE `perks` (
  `product_id` int(10) UNSIGNED NOT NULL,
  `name` varchar(100) NOT NULL DEFAULT 'No Name',
  `description` varchar(500) DEFAULT NULL,
  `price` int(10) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `perks`
--

INSERT INTO `perks` (`product_id`, `name`, `description`, `price`) VALUES
(2, 'Stamina Boost', 'Increase your stamina and run regeneration by a total of 40%.', 60),
(4, 'Green Thumb', 'With a 35% lifetime increase in your crops growing up healthy, they will be saved and you will also receive a better crop yield!', 100),
(5, 'Bird Man', 'Increase your rate of receiving a bird nest drop by 35%!', 50),
(6, 'Stoner', 'Increase your rate of receiving a gem stone drop by 35%!', 50),
(11, 'Unbreakable Forge', 'Ring of forging never breaks.', 30),
(12, 'Out of Grave Danger', 'Your gravestone will last up to twice as long with this perk.', 50),
(13, 'Sleight of Hand', 'With sleight of hand you will increase your success rates in all aspects of thieving. This includes pickpocketing and cracking wall safes for gems.', 50),
(14, 'Master Chef', 'As a soon to be Master Chef, you will receive a 20% increase in successfully cooking your food!', 50),
(16, 'Divine Intervention', 'The gods above intervene with your burying of bones. You have a 10% chance while burying a bone to keep it instead.', 70),
(17, 'Familiar Whisperer', 'Get to know your familiar better by increasing their lifespan by 50%.', 70),
(18, 'Barrows Befriender', 'Befriend the barrows brothers & never experience the wretched degrading of their armour again', 300),
(19, 'Abyss Befriender', 'Use the power of the abyss to make your Runecrafting pouches undegradable.', 150),
(21, 'Charge Befriender', 'The God\'s of the Hero\'s guild have blessed you with the power to use your jewerly free of charge.', 250),
(22, 'Golden Needle', 'Gain an extra 10% experience whilst spinning something on a spinning wheel, including flax. Creating an item made out of dragonhide rewards an extra 5% experience. Your crafting needle also never breaks and thread is consumed less often.\n', 50),
(24, 'Slayer Betrayer', 'Obtain the ability through the Slayer Masters to change your slayer task at will. Type ::cleartask to use.', 100),
(26, 'Thirst Quencher', 'The gods have blessed you with the knowledge of the deserts to gain the skills required to tap into an unlimited water supply.', 30),
(27, 'Double Trouble', 'Experience a chance of receiving double the resources through skills such as, mining, woodcutting, fishing, and many more.', 250),
(29, 'Godwars Befriender', 'Now blessed by the gods you have the ability to enter the chambers with a killcount of 30. You will also be granted half the time it takes to recharge at an altar.', 100),
(30, 'Prayer Betrayer', 'Experience half the prayer drain rate when this perk is enabled.', 150),
(31, 'Spell Swap', 'The ability to swap spell books without any charge of runes. Cannot be used in combat or in the wilderness.', 80),
(32, 'Dwarf Befriender', 'Befriended by the dwarfs you now have the ability to use double the cannon balls and experience no decay on your cannon.', 150),
(33, 'Powerpoint', 'This perk grants you double the points in all minigames.', 300),
(35, 'Charm Collector', 'Through the power of summoning you will automatically pick up any charms dropped in battle.', 100),
(36, 'Detective', 'You now have a solid 10% chance of a clue scroll drop from any monster that drops clues as well as a 50% better chance of <strong>super rare</strong> rewards such as 3rd age. You\'ll also experience a 50% increased chance to obtain more loot.', 250),
(40, 'Overcharge', 'The power from the overcharge lords is given to you. Your Dragonfire Shield will recharge fully every 10 minutes. The time between casts is also reduced by 50%.', 170),
(41, 'Unbreakable Crystal', 'This perk allows for your crystal bow to never degrade.', 350),
(42, 'Crusader', 'With this perk you will have a 25% chance to double loot the barrows chest.', 100),
(43, 'Pet Befriender', 'This perk gives you the ability to double your chances on getting boss/skilling pets!', 100),
(60, 'Bone Crusher', 'Automatically crushes your bones as they\'re dropped for prayer experience. Toggle this perk using ::bonecrusher', 100),
(70, 'Runestone Knowledge', 'You are given extended knowledge of the runecrafting skill and can now craft double death, law, cosmic, blood and nature runes.', 200),
(71, 'Coin machine', 'Automatically bank all coins dropped from NPC\'s and gives you 25% extra gold. Toggle this perk using ::coinmachine', 150),
(72, 'Fight Cave Fanatic', 'Eliminates the first 25 waves from the tzhaar fight caves.', 50),
(73, 'Decanter', 'Zahur will decant your noted potions if you have this perk.', 50);

-- --------------------------------------------------------

--
-- Table structure for table `player_logs`
--

CREATE TABLE `player_logs` (
  `username` varchar(22) NOT NULL DEFAULT '',
  `public_chat` longtext DEFAULT NULL,
  `private_chat` longtext DEFAULT NULL,
  `clan_chat` longtext DEFAULT NULL,
  `address_log` longtext DEFAULT NULL,
  `command_log` longtext DEFAULT NULL,
  `trade_log` longtext DEFAULT NULL,
  `ge_log` longtext DEFAULT NULL,
  `duplication_log` longtext DEFAULT NULL,
  `duel_log` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `punishments`
--

CREATE TABLE `punishments` (
  `address` varchar(100) NOT NULL DEFAULT '',
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `security`
--

CREATE TABLE `security` (
  `id` int(11) UNSIGNED NOT NULL,
  `ip` longtext NOT NULL,
  `type` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sys_logs`
--

CREATE TABLE `sys_logs` (
  `id` int(11) UNSIGNED NOT NULL,
  `message` longtext NOT NULL,
  `log_type` int(2) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `IP_ADDRESS` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `validations`
--

CREATE TABLE `validations` (
  `id` int(11) UNSIGNED NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `code` varchar(30) DEFAULT NULL,
  `type` int(2) NOT NULL DEFAULT 0,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `value` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `votes`
--

CREATE TABLE `votes` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL DEFAULT '',
  `site` varchar(50) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `voting_sites`
--

CREATE TABLE `voting_sites` (
  `name` varchar(20) NOT NULL DEFAULT 'Null',
  `wait` int(5) NOT NULL DEFAULT 12,
  `credits` int(2) NOT NULL DEFAULT 1,
  `link` varchar(500) NOT NULL DEFAULT 'http://ariosrsps.com',
  `get_command` varchar(20) NOT NULL DEFAULT '',
  `host_name` varchar(500) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `worlds`
--

CREATE TABLE `worlds` (
  `world` int(2) UNSIGNED NOT NULL,
  `ip` varchar(20) NOT NULL DEFAULT '127.0.0.1',
  `players` int(5) NOT NULL DEFAULT 0,
  `country` int(1) NOT NULL DEFAULT 0,
  `member` int(11) NOT NULL,
  `revision` int(3) NOT NULL DEFAULT 530,
  `lastResponse` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `worlds`
--

INSERT INTO `worlds` (`world`, `ip`, `players`, `country`, `member`, `revision`, `lastResponse`) VALUES
(1, '127.0.0.1', 0, 22, 1, 530, '2019-11-06 00:24:54');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dev_log`
--
ALTER TABLE `dev_log`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `highscores`
--
ALTER TABLE `highscores`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`UID`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `perks`
--
ALTER TABLE `perks`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `player_logs`
--
ALTER TABLE `player_logs`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `security`
--
ALTER TABLE `security`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sys_logs`
--
ALTER TABLE `sys_logs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `validations`
--
ALTER TABLE `validations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `votes`
--
ALTER TABLE `votes`
  ADD KEY `id` (`id`);

--
-- Indexes for table `voting_sites`
--
ALTER TABLE `voting_sites`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `worlds`
--
ALTER TABLE `worlds`
  ADD PRIMARY KEY (`world`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dev_log`
--
ALTER TABLE `dev_log`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `highscores`
--
ALTER TABLE `highscores`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `members`
--
ALTER TABLE `members`
  MODIFY `UID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `perks`
--
ALTER TABLE `perks`
  MODIFY `product_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT for table `security`
--
ALTER TABLE `security`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sys_logs`
--
ALTER TABLE `sys_logs`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `validations`
--
ALTER TABLE `validations`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `votes`
--
ALTER TABLE `votes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
