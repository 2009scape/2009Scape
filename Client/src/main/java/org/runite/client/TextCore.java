package org.runite.client;

import org.rs09.client.config.GameConfig;


import java.awt.*;

public class TextCore {

    /**
     * Configs that are set by the client
     */


    /** @woah
     * A repository filled with text that can be set to whatever the user sees fit
     * Each will be labeled for what their original purpose is for and the files that
     * They reside in.
     *
     * RSString information:
     * The way it was programmed certain symbols require a different type of value set
     * to determine said symbol. You may notice ex: )2 or a )3)3
     * )2 = spacer )3 = period(.)
     */

    /**Login Screen Text
     * Files that use these texts:
     */
    static RSString RSLoadingPleaseWait = RSString.parse(GameConfig.SERVER_NAME + " is loading )2 please wait)3)3)3");
    static RSString LoadingConfig = RSString.parse("Loading config )2 ");
    static RSString LoadedConfig = RSString.parse("Loaded config");
    static RSString LoadingSprites = RSString.parse("Loading sprites )2 ");
    static RSString LoadedSprites = RSString.parse("Loaded sprites");
    static RSString LoadingWLD = RSString.parse("Loading world list data");
    static RSString LoadedWLD = RSString.parse("Loaded world list data");
    static RSString LoadingPleaseWait = RSString.parse("Please wait)3)3)3");
    static RSString LoadingPleaseWait2 = RSString.parse("Loading )2 please wait)3");
    static RSString LoadingFonts = RSString.parse("Loading fonts )2 ");
    static RSString LoadedFonts = RSString.parse("Loaded fonts");
    static RSString LoadedWordPack = RSString.parse("Loaded wordpack");
    static RSString LoadingTextures = RSString.parse("Loading textures )2 ");
    static RSString LoadedTextures = RSString.parse("Loaded textures");
    static RSString LoadingInterfaces = RSString.parse("Loading interfaces )2 ");
    static RSString LoadedInterfaces = RSString.parse("Loaded interfaces");
    static RSString LoadingTitleScreen = RSString.parse("Loading title screen )2 ");
    static RSString LoadingGeneral = RSString.parse("Loading)3)3)3");
    static RSString LoadingWordPack = RSString.parse("Loading wordpack )2 ");
    static RSString LoadingConnecting = RSString.parse("Connecting.. This takes a LONG time.");
    static RSString LoadedUpdateList = RSString.parse("Loaded update list");
    static RSString AttemptingReestablish = RSString.parse("Please wait )2 attempting to reestablish)3");
    static RSString CreatedWorld = RSString.parse("Created gameworld");
    static RSString CheckingForUpdates = RSString.parse("Checking for updates )2 ");
    static RSString LoadedInputHandler = RSString.parse("Loaded input handler");
    static RSString OpenedTitleScreen = RSString.parse("Opened title screen");
    static RSString LoadedTitleScreen = RSString.parse("Loaded title screen");
    static RSString Starting3DLibrary = RSString.parse("Starting 3d Library");
    static RSString Started3DLibrary = RSString.parse("Started 3d Library");
    static RSString AllocatingMemory = RSString.parse("Allocating memory");
    static RSString AllocatedMemory = RSString.parse("Allocated memory");
    static RSString PreparedSoundEngine = RSString.parse("Prepared sound engine");
    static RSString ConxLost = RSString.parse("Connection lost)3");
    static RSString ConxUpdateServer = RSString.parse("Connected to update server");

    /**
     * User Login/ User Text
     */
    static RSString HasExpires = RSString.parse("; Expires=");
    static RSString HasMaxAge = RSString.parse("; Max)2Age=");
    static RSString HasAgeExpire = RSString.parse("; Expires=Thu)1 01)2Jan)21970 00:00:00 GMT; Max)2Age=0");
    static RSString HasLoggedIn = RSString.parse(" has logged in)3");
    static RSString HasLoggedOut = RSString.parse(" has logged out)3");
    static RSString HasFriendsListFull = RSString.parse("Your friend list is full)3 Max of 100 for free users)1 and 200 for members)3");
    static RSString HasIgnoreListFull = RSString.parse("Your ignore list is full)3 Max of 100 users)3");
    static RSString HasOnOwnFriendsList = RSString.parse("You can(Wt add yourself to your own friend list)3");
    static RSString HasOnOwnIgnoreList = RSString.parse("You can(Wt add yourself to your own ignore list)3");
    static RSString HasPleaseRemove = RSString.parse("Please remove ");
    static RSString HasIgnoreToFriends = RSString.parse(" from your ignore list first)3");
    static RSString HasFriendsToIgnore = RSString.parse(" from your friend list first)3");
    static RSString HasFriendsAlready = RSString.parse(" is already on your friend list)3");
    static RSString HasIgnoreAlready = RSString.parse(" is already on your ignore list)3");
    static RSString HasWishToTrade = RSString.parse("wishes to trade with you)3");
    static RSString HasAttack = RSString.parse("Attack");
    static RSString HasUse = RSString.parse("Use");
    static RSString HasExamine = RSString.parse("Examine");
    static RSString HasTake = RSString.parse("Take");
    static RSString HasWalkHere = RSString.parse("Walk here");
    static RSString HasDrop = RSString.parse("Drop");
    static RSString HasDiscard = RSString.parse("Discard");
    static RSString HasHidden = RSString.parse("Hidden");
    static RSString HasNull = RSString.parse("null");
    static RSString HasCancel = RSString.parse("Cancel");
    static RSString HasFaceHere = RSString.parse("Face here");
    static RSString HasContinue = RSString.parse("Continue");
    static RSString HasClose = RSString.parse("Close");
    static RSString HasOK = RSString.parse("Ok");
    static RSString HasSelect = RSString.parse("Select");
    static RSString HasChooseOptions = RSString.parse("Choose Option");
    static RSString HasMoreOptions = RSString.parse(" more options");
    static RSString HasUnableFind = RSString.parse("Unable to find ");
    static RSString HasSkill = RSString.parse("skill: ");
    static RSString HasScroll = RSString.parse("scroll:");
    static RSString HasLevel = RSString.parse("level: ");
    static RSString HasRating = RSString.parse("rating: ");

    /**
     * Money values (K (Thousand)), (M (Million))
     */
    static RSString ThousandK = RSString.parse("K");
    static RSString MillionM = RSString.parse("M");


    static RSString HasDuelFriend = RSString.parse(":duelfriend:");
    static RSString HasDuelStake = RSString.parse(":duelstake:");
    static RSString HasTradeRequest = RSString.parse(":tradereq:");
    static RSString HasTrade = RSString.parse(":trade:");
    static RSString HasAssist = RSString.parse(":assist:");
    static RSString HasAssistRequest = RSString.parse(":assistreq:");
    static RSString HasClanRequest = RSString.parse(":clanreq:");
    static RSString HasClan = RSString.parse(":clan:");
    static RSString HasAllyReq = RSString.parse(":allyreq:");
    static RSString cmdChalReq = RSString.parse(":chalreq:");


    /**
     * Archive Info for client cache lookup
     */
    static RSString HasLabels = RSString.parse("_labels");
    static RSString HasPlayerLabels = RSString.parse("_labels");
    static RSString HasULLookUp = RSString.parse("ul");
    static RSString HasHuffman = RSString.parse("huffman");


    /**Colored Text Commands * For Color editing use ColorCore.java
     * *Note not used to actually change the color of text
     * Used as color coding commands such as:
     * red: purple: etc.
     */

    static RSString TextColorYellow = RSString.parse("yellow:");
    static RSString TextColorRed = RSString.parse("red:");
    static RSString TextColorGreen = RSString.parse("green:");
    static RSString TextColorCyan = RSString.parse("cyan:");
    static RSString TextColorPurple = RSString.parse("purple:");
    static RSString TextColorWhite = RSString.parse("white:");
    static RSString TextFlashOne = RSString.parse("flash1:");
    static RSString TextFlashTwo = RSString.parse("flash2:");
    static RSString TextFlashThree = RSString.parse("flash3:");
    static RSString TextGlowOne = RSString.parse("glow1:");
    static RSString TextGlowTwo = RSString.parse("glow2:");
    static RSString TextGlowThree = RSString.parse("glow3:");
    static RSString TextWave = RSString.parse("wave:");
    static RSString TextWaveTwo = RSString.parse("wave2:");
    static RSString TextShake = RSString.parse("shake:");
    static RSString TextSlide = RSString.parse("slide:");




    /**Months of the year
     * This was being accessed multiple times for other methods
     */
    static RSString[] MonthsOfTheYear = new RSString[]{RSString.parse("Jan"),
                                                       RSString.parse("Feb"),
                                                       RSString.parse("Mar"),
                                                       RSString.parse("Apr"),
                                                       RSString.parse("May"),
                                                       RSString.parse("Jun"),
                                                       RSString.parse("Jul"),
                                                       RSString.parse("Aug"),
                                                       RSString.parse("Sep"),
                                                       RSString.parse("Oct"),
                                                       RSString.parse("Nov"),
                                                       RSString.parse("Dec")};

    static RSString[] DaysOfTheWeek = new RSString[]{  RSString.parse("Sun"),
                                                       RSString.parse("Mon"),
                                                       RSString.parse("Tue"),
                                                       RSString.parse("Wed"),
                                                       RSString.parse("Thu"),
                                                       RSString.parse("Fri"),
                                                       RSString.parse("Sat")};

    /**
     * Client Commands
     */
    static RSString COMMAND_HIGHRES_GRAPHICS_RESIZE = RSString.parse("::wm2");
    public static final RSString TOGGLE_ATK = RSString.parse("::toggleatk");
    public static final RSString TOGGLE_FK = RSString.parse("::togglefk");
    public static RSString COMMAND_BREAK_CLIENT_CONNECTION = RSString.parse("::clientdrop");
    static RSString COMMAND_SHIFT_DROP_CLICK = RSString.parse("::shiftclick");
    static RSString COMMAND_REPLACE_CANVAS = RSString.parse("::replacecanvas");
    static RSString COMMAND_HIGHRES_GRAPHICS_WINDOW = RSString.parse("::wm1");
    static RSString COMMAND_QA_OP_TEST = RSString.parse("::qa_op_test");
    static RSString COMMAND_NOCLIP = RSString.parse("::noclip");
    static RSString COMMAND_GARBAGE_COLLECTOR = RSString.parse("::gc");
    static RSString COMMAND_HIGHRES_GRAPHICS_FULLSCREEN = RSString.parse("::wm3");
    static RSString COMMAND_FPS = RSString.parse("::fps ");
    static RSString COMMAND_TWEENING = RSString.parse("::tween");
    static RSString COMMAND_MEMORY_MANAGEMENT = RSString.parse("::mm");
    static RSString COMMAND_BREAK_JS5_CLIENT_CONNECTION = RSString.parse("::clientjs5drop");
    static RSString COMMAND_BREAK_JS5_SERVER_CONNECTION = RSString.parse("::serverjs5drop");
    static RSString COMMAND_GRAPHICS_CARD_MEMORY = RSString.parse("::cardmem");
    static RSString COMMAND_TOGGLE_FPSOFF = RSString.parse("::fpsoff");
    static RSString COMMAND_PC_CACHE_SIZE = RSString.parse("::pcachesize");
    static RSString aClass94_853 = RSString.parse("::tele ");
    static RSString COMMAND_LOWRES_GRAPHICS = RSString.parse("::wm0");
    static RSString COMMAND_TOGGLE_FPSON = RSString.parse("::fpson");
    static RSString COMMAND_REBUILD = RSString.parse("::rebuild");
    static RSString COMMAND_BREAK_CONNECTION = RSString.parse("::breakcon");
    static RSString COMMAND_ERROR_TEST = RSString.parse("::errortest");
    static RSString COMMAND_SET_PARTICLES = RSString.parse("::setparticles");
    static RSString COMMAND_RECT_DEBUG = RSString.parse("::rect_debug");
    static RSString COMMAND_RENDER_INFO = RSString.parse("::renderinfo");

    /**
     * Used as text for client commands
     */
    static RSString Memoryk = RSString.parse("k");
    public static RSString aClass94_1622 = RSString.parse("Card:");
    public static RSString aClass94_4057 = RSString.parse("Mem:");
    static RSString aClass94_985 = RSString.parse("Fps:");
    static RSString aClass94_1630 = RSString.parse("Mem:");
    static RSString memoryBeforeCleanup = RSString.parse("Memory before cleanup=");
    static RSString aClass94_3653 = RSString.parse("Shift)2click disabled)3");
    static RSString aClass94_434 = RSString.parse("Shift)2click ENABLED(Q");
    static RSString forcedTweeningDisabled = RSString.parse("Forced tweening disabled)3");
    static RSString forcedTweeningEnabled = RSString.parse("Forced tweening / animation smoothing ENABLED(Q");

    /**
     * Website
     */
    static RSString aClass94_577 = RSString.parse("http:)4)4");
    public static RSString aClass94_4052 = RSString.parse("www");
    public static RSString aClass94_3601 = RSString.parse(")3runescape)3com)4l=");
    public static RSString aClass94_1932 = RSString.parse(")4a=");
    public static RSString aClass94_1885 = RSString.parse("cookiehost");
    public static RSString aClass94_3637 = RSString.parse(")4p=");

    /**
     * Displayed if user is on a f2p world
     */
    static RSString MembersObject = RSString.parse("Members object");


    /**
     * Fonts
     */
    static Font Helvetica = new Font("Helvetica", Font.BOLD, 13);

    /**
     * Holiday Event Text
     */
    //Halloween
    public static RSString TrickorTreat = RSString.of("Trick-or-treat");
    public static RSString GazeInto = RSString.of("Gaze-into");

    /**
     * Unsorted
     */
    static RSString Spacer = RSString.parse(" ");
    static RSString aClass94_946 = RSString.parse(")2");
    static RSString aClass94_995 = RSString.parse("(Y<)4col>");
    static RSString aClass94_1645 = RSString.parse("Hidden)2");
    static RSString char_colon = RSString.parse(":");
    public static RSString aClass94_132 = RSString.parse("::");
    public static RSString aClass94_119 = RSString.parse("runes");
    public static RSString aClass94_37 = RSString.parse("0(U");
    public static RSString aClass94_38 = RSString.parse("tbrefresh");
    public static RSString aClass94_1698 = RSString.parse("(R");
    public static RSString aClass94_1133 = RSString.parse(")4j");
    public static RSString aClass94_4066 = RSString.parse("<br>");
    public static RSString aClass94_2598 = RSString.parse("<br>");
    public static RSString aClass94_1326 = RSString.parse(")2");
    public static RSString aClass94_4049 = RSString.parse("");
    public static RSString aClass94_1617 = RSString.parse(")1a2)1m");
    static RSString aClass94_148 = RSString.parse("(U(Y");
    static RSString aClass94_465 = RSString.parse(" ");
    static RSString aClass94_468 = RSString.parse("(U");
    static RSString worldmapUnderlay = RSString.parse("underlay");
    static RSString worldmapOverlay = RSString.parse("overlay");
    static RSString worldmapOverlay2 = RSString.parse("overlay2");
    static RSString aClass94_3133 = RSString.parse(")2");
    static RSString aClass94_422 = RSString.parse("loc");
    static RSString aClass94_3672 = RSString.parse("");
    static RSString clientDebugNotifier = RSString.parse("<img=2>Client debug notifier");
    static RSString aClass94_3209 = RSString.parse("showingVideoAd");
    static RSString aClass94_592 = RSString.parse("<img=0>");
    static RSString timeZone = RSString.parse(" GMT");
    static RSString aClass94_3190 = RSString.parse("<img=1>");
    static RSString aClass94_3192 = RSString.parse(" ");
    static RSString aClass94_3196 = RSString.parse("Fps:");
    static RSString openGLVersionText = RSString.parse("Client OpenGL Version:");
    static RSString graphicsCard = RSString.parse("Graphics Card:");
    static RSString aClass94_3039 = RSString.parse("0");
    static RSString aClass94_3055 = RSString.parse("k");
    static RSString aClass94_3145 = RSString.parse(")1 ");
    static RSString aClass94_3161 = RSString.parse("_");
    static RSString aClass94_3268 = RSString.parse(")1");
    static RSString aClass94_3339 = RSString.parse("null");
    static RSString aClass94_3357 = RSString.parse("");
    static RSString memoryEquals = RSString.parse("mem=");
    static RSString aClass94_3399 = RSString.parse("<br>(X");
    static RSString aClass94_3418 = RSString.parse("(U5");
    static RSString aClass94_2498 = RSString.parse("(U (X");
    static RSString aClass94_3574 = RSString.parse("titlebg");
    static RSString aClass94_3577 = RSString.parse(": ");
    static RSString aClass94_3703 = RSString.parse(" )2> ");
    static RSString aClass94_3777 = RSString.parse(" x ");
    static RSString aClass94_3807 = RSString.parse("m");
    static RSString aClass94_2608 = RSString.parse(")4l=");
    static RSString aClass94_2168 = RSString.parse("<br>");
    static RSString aClass94_106 = RSString.parse("showVideoAd");
    static RSString aClass94_331 = RSString.parse("(U1");
    static RSString aClass94_339 = RSString.parse("1");
    static RSString aClass94_341 = RSString.parse(")3");
    static RSString aClass94_436 = RSString.parse("Cache:");
    static RSString aClass94_442 = RSString.parse("Number of player models in cache:");
    static RSString aClass94_444 = RSString.parse("<img=1>");
    static RSString aClass94_516 = RSString.parse("unzap");
    static RSString aClass94_2610 = RSString.parse(")1o");
    static RSString aClass94_673 = RSString.parse(")0");
    static RSString aClass94_676 = null;
    static RSString aClass94_852 = RSString.parse("(U4");
    static RSString aClass94_892 = RSString.parse(" )2> <col=ffffff>");
    static RSString aClass94_1051 = RSString.parse("(Udns");
    static RSString LEFT_PARENTHESES = RSString.parse(" (X");
    static RSString aClass94_1076 = RSString.parse("<)4col>");
    static RSString aClass94_1151 = RSString.parse("settings=");
    static RSString aClass94_1301 = RSString.parse("(U3");
    static RSString aClass94_1333 = RSString.parse("um");
    static RSString aClass94_1341 = RSString.parse("logo");
    static RSString aClass94_1342 = RSString.parse("details");
    static RSString aClass94_2171 = RSString.parse("");
    static RSString aClass94_1687 = RSString.parse("(Z");
    static RSString aClass94_1694 = RSString.parse("document)3cookie=(R");
    static RSString aClass94_1724 = RSString.parse(" )2>");
    static RSString aClass94_1745 = RSString.parse("settings");
    static RSString aClass94_1760 = RSString.parse("");
    static RSString aClass94_2707 = RSString.parse("<br>(X100(U(Y");
    static RSString aClass94_2735 = RSString.parse(")4");
    static RSString aClass94_2765 = RSString.parse(" ");
    static RSString aClass94_2928 = RSString.parse("null");
    static RSString aClass94_1880 = RSString.parse(")1");
    static RSString aClass94_2006 = RSString.parse("null");
    static RSString aClass94_2018 = RSString.parse("Cabbage");
    static RSString aClass94_2025 = RSString.parse(")2");
    static RSString aClass94_2029 = RSString.parse("l");
    static RSString aClass94_2033 = RSString.parse("Memory after cleanup=");
    static RSString aClass94_2044 = RSString.parse("cookieprefix");
    static RSString aClass94_4007 = RSString.parse(":");
    static RSString aClass94_4023 = RSString.parse(")3");
    static RSString aClass94_3013 = RSString.parse("0");
    static RSString aClass94_2074 = RSString.parse("; version=1; path=)4; domain=");
    static RSString rectDebugEquals = RSString.parse("rect_debug=");
    static RSString aClass94_2080 = RSString.parse("(U2");
    static RSString aClass94_2116 = RSString.parse("Hidden)2use");
    static RSString aClass94_1915 = RSString.parse("Null");
    static RSString aClass94_2584 = RSString.parse("<)4col>");
    static RSString aClass94_209 = RSString.parse("event_opbase");
    static RSString aClass94_2304 = RSString.parse("details");
    static RSString aClass94_2306 = RSString.parse("<)4col> x");
    static RSString aClass94_2323 = RSString.parse("<img=0>");
    static RSString aClass94_2331 = RSString.parse("");
    static RSString RIGHT_PARENTHESES = RSString.parse("(Y");

}
