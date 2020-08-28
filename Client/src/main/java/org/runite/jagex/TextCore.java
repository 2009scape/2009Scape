package org.runite.jagex;

import org.runite.GameLaunch;

import java.awt.*;

public class TextCore {

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
    static RSString RSLoadingPleaseWait = RSString.createRSString(GameLaunch.SETTINGS.getName() + " is loading )2 please wait)3)3)3");
    static RSString LoadingConfig = RSString.createRSString("Loading config )2 ");
    static RSString LoadedConfig = RSString.createRSString("Loaded config");
    static RSString LoadingSprites = RSString.createRSString("Loading sprites )2 ");
    static RSString LoadedSprites = RSString.createRSString("Loaded sprites");
    static RSString LoadingWLD = RSString.createRSString("Loading world list data");
    static RSString LoadedWLD = RSString.createRSString("Loaded world list data");
    static RSString LoadingPleaseWait = RSString.createRSString("Please wait)3)3)3");
    static RSString LoadingPleaseWait2 = RSString.createRSString("Loading )2 please wait)3");
    static RSString LoadingFonts = RSString.createRSString("Loading fonts )2 ");
    static RSString LoadedFonts = RSString.createRSString("Loaded fonts");
    static RSString LoadedWordPack = RSString.createRSString("Loaded wordpack");
    static RSString LoadingTextures = RSString.createRSString("Loading textures )2 ");
    static RSString LoadedTextures = RSString.createRSString("Loaded textures");
    static RSString LoadingInterfaces = RSString.createRSString("Loading interfaces )2 ");
    static RSString LoadedInterfaces = RSString.createRSString("Loaded interfaces");
    static RSString LoadingTitleScreen = RSString.createRSString("Loading title screen )2 ");
    static RSString LoadingGeneral = RSString.createRSString("Loading)3)3)3");
    static RSString LoadingWordPack = RSString.createRSString("Loading wordpack )2 ");
    static RSString LoadingConnecting = RSString.createRSString("Connecting.. This takes a LONG time.");
    static RSString LoadedUpdateList = RSString.createRSString("Loaded update list");
    static RSString AttemptingReestablish = RSString.createRSString("Please wait )2 attempting to reestablish)3");
    static RSString CreatedWorld = RSString.createRSString("Created gameworld");
    static RSString CheckingForUpdates = RSString.createRSString("Checking for updates )2 ");
    static RSString LoadedInputHandler = RSString.createRSString("Loaded input handler");
    static RSString OpenedTitleScreen = RSString.createRSString("Opened title screen");
    static RSString LoadedTitleScreen = RSString.createRSString("Loaded title screen");
    static RSString Starting3DLibrary = RSString.createRSString("Starting 3d Library");
    static RSString Started3DLibrary = RSString.createRSString("Started 3d Library");
    static RSString AllocatingMemory = RSString.createRSString("Allocating memory");
    static RSString AllocatedMemory = RSString.createRSString("Allocated memory");
    static RSString PreparedSoundEngine = RSString.createRSString("Prepared sound engine");
    static RSString ConxLost = RSString.createRSString("Connection lost)3");
    static RSString ConxUpdateServer = RSString.createRSString("Connected to update server");

    /**User Login/ User Text
     *
     */
    static RSString HasExpires = RSString.createRSString("; Expires=");
    static RSString HasMaxAge = RSString.createRSString("; Max)2Age=");
    static RSString HasAgeExpire = RSString.createRSString("; Expires=Thu)1 01)2Jan)21970 00:00:00 GMT; Max)2Age=0");
    static RSString HasLoggedIn = RSString.createRSString(" has logged in)3");
    static RSString HasLoggedOut = RSString.createRSString(" has logged out)3");
    static RSString HasFriendsListFull = RSString.createRSString("Your friend list is full)3 Max of 100 for free users)1 and 200 for members)3");
    static RSString HasIgnoreListFull = RSString.createRSString("Your ignore list is full)3 Max of 100 users)3");
    static RSString HasOnOwnFriendsList = RSString.createRSString("You can(Wt add yourself to your own friend list)3");
    static RSString HasOnOwnIgnoreList = RSString.createRSString("You can(Wt add yourself to your own ignore list)3");
    static RSString HasPleaseRemove = RSString.createRSString("Please remove ");
    static RSString HasIgnoreToFriends = RSString.createRSString(" from your ignore list first)3");
    static RSString HasFriendsToIgnore = RSString.createRSString(" from your friend list first)3");
    static RSString HasFriendsAlready = RSString.createRSString(" is already on your friend list)3");
    static RSString HasIgnoreAlready = RSString.createRSString(" is already on your ignore list)3");
    static RSString HasWishToTrade = RSString.createRSString("wishes to trade with you)3");
    static RSString HasAttack = RSString.createRSString("Attack");
    static RSString HasUse = RSString.createRSString("Use");
    static RSString HasExamine = RSString.createRSString("Examine");
    static RSString HasTake = RSString.createRSString("Take");
    static RSString HasWalkHere = RSString.createRSString("Walk here");
    static RSString HasDrop = RSString.createRSString("Drop");
    static RSString HasDiscard = RSString.createRSString("Discard");
    static RSString HasHidden = RSString.createRSString("Hidden");
    static RSString HasNull = RSString.createRSString("null");
    static RSString HasCancel = RSString.createRSString("Cancel");
    static RSString HasFaceHere = RSString.createRSString("Face here");
    static RSString HasContinue = RSString.createRSString("Continue");
    static RSString HasClose = RSString.createRSString("Close");
    static RSString HasOK = RSString.createRSString("Ok");
    static RSString HasSelect = RSString.createRSString("Select");
    static RSString HasChooseOptions = RSString.createRSString("Choose Option");
    static RSString HasMoreOptions = RSString.createRSString(" more options");
    static RSString HasUnableFind = RSString.createRSString("Unable to find ");
    static RSString HasSkill = RSString.createRSString("skill: ");
    static RSString HasScroll = RSString.createRSString("scroll:");
    static RSString HasLevel = RSString.createRSString("level: ");
    static RSString HasRating = RSString.createRSString("rating: ");

    /**Money values (K (Thousand)), (M (Million))
     *
     */
    static RSString ThousandK = RSString.createRSString("K");
    static RSString MillionM = RSString.createRSString("M");


    static RSString HasDuelFriend = RSString.createRSString(":duelfriend:");
    static RSString HasDuelStake = RSString.createRSString(":duelstake:");
    static RSString HasTradeRequest = RSString.createRSString(":tradereq:");
    static RSString HasTrade = RSString.createRSString(":trade:");
    static RSString HasAssist = RSString.createRSString(":assist:");
    static RSString HasAssistRequest = RSString.createRSString(":assistreq:");
    static RSString HasClanRequest = RSString.createRSString(":clanreq:");
    static RSString HasClan = RSString.createRSString(":clan:");
    static RSString HasAllyReq = RSString.createRSString(":allyreq:");


    /**Archive Info for client cache lookup
     *
     */
    static RSString HasLabels = RSString.createRSString("_labels");
    static RSString HasPlayerLabels = RSString.createRSString("_labels");
    static RSString HasULLookUp = RSString.createRSString("ul");
    static RSString HasHuffman = RSString.createRSString("huffman");


    /**Colored Text Commands * For Color editing use ColorCore.java
     * *Note not used to actually change the color of text
     * Used as color coding commands such as:
     * red: purple: etc.
     */

    static RSString TextColorYellow = RSString.createRSString("yellow:");
    static RSString TextColorRed = RSString.createRSString("red:");
    static RSString TextColorGreen = RSString.createRSString("green:");
    static RSString TextColorCyan = RSString.createRSString("cyan:");
    static RSString TextColorPurple = RSString.createRSString("purple:");
    static RSString TextColorWhite = RSString.createRSString("white:");
    static RSString TextFlashOne = RSString.createRSString("flash1:");
    static RSString TextFlashTwo = RSString.createRSString("flash2:");
    static RSString TextFlashThree = RSString.createRSString("flash3:");
    static RSString TextGlowOne = RSString.createRSString("glow1:");
    static RSString TextGlowTwo = RSString.createRSString("glow2:");
    static RSString TextGlowThree = RSString.createRSString("glow3:");
    static RSString TextWave = RSString.createRSString("wave:");
    static RSString TextWaveTwo = RSString.createRSString("wave2:");
    static RSString TextShake = RSString.createRSString("shake:");
    static RSString TextSlide = RSString.createRSString("slide:");




    /**Months of the year
     * This was being accessed multiple times for other methods
     */
    static RSString[] MonthsOfTheYear = new RSString[]{RSString.createRSString("Jan"),
                                                       RSString.createRSString("Feb"),
                                                       RSString.createRSString("Mar"),
                                                       RSString.createRSString("Apr"),
                                                       RSString.createRSString("May"),
                                                       RSString.createRSString("Jun"),
                                                       RSString.createRSString("Jul"),
                                                       RSString.createRSString("Aug"),
                                                       RSString.createRSString("Sep"),
                                                       RSString.createRSString("Oct"),
                                                       RSString.createRSString("Nov"),
                                                       RSString.createRSString("Dec")};

    static RSString[] DaysOfTheWeek = new RSString[]{  RSString.createRSString("Sun"),
                                                       RSString.createRSString("Mon"),
                                                       RSString.createRSString("Tue"),
                                                       RSString.createRSString("Wed"),
                                                       RSString.createRSString("Thu"),
                                                       RSString.createRSString("Fri"),
                                                       RSString.createRSString("Sat")};


    /**Unsorted
     *
     */
    static RSString Memoryk = RSString.createRSString("k");
    static RSString Spacer = RSString.createRSString(" ");
    static RSString MembersObject = RSString.createRSString("Members object");

    /**Fonts
     *
     */
    static Font Helvetica = new Font("Helvetica", Font.BOLD, 13);
}
