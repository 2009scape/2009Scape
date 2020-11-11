package org.runite.client;

import java.awt.*;

public class ColorCore {

    /** @woah
     * A repository filled with colors that can be set to whatever the user sees fit
     * Each will be labeled for what their original purpose is for and the files that
     * They reside in. A few values need to be played with only tested some of the colors
     * to the corresponding name
     */

    /** Used to display the color difference between two players
     * Files that use these colors:
     * Class72.java - combatLevelColor
     * Class3_Sub13_Sub30.java - getCombatLevelDifferenceColor
     * -
     * Level equation for difference (-otherPlayer + yourPlayer)
     * Used in values of 3, -9 to 9 ,
     * Following a gradient Dark Red -> Yellow -> Bright Green
     */
    static RSString LvlDiffN9 = RSString.parse("<col=ff0000>");//Solid Red OG: <col=ff0000>
    static RSString LvlDiffN6 = RSString.parse("<col=ff3000>");//Dark Orange OG: <col=ff3000>
    static RSString LvlDiffN3 = RSString.parse("<col=ff7000>");//Orange OG: <col=ff7000>
    static RSString LvlDiffN0 = RSString.parse("<col=ffb000>");//Yellow Orange OG: <col=ffb000>
    static RSString LvlDiffDefault = RSString.parse("<col=ffff00>");//Yellow <Default> OG: <col=ffff00>
    static RSString LvlDiffP0 = RSString.parse("<col=c0ff00>");//Yellow OG: <col=c0ff00>
    static RSString LvlDiffP3 = RSString.parse("<col=80ff00>");//Yellow Green OG: <col=80ff00>
    static RSString LvlDiffP6 = RSString.parse("<col=40ff00>");//Green OG: <col=40ff00>
    static RSString LvlDiffP9 = RSString.parse("<col=00ff00>");//Bright Green OG: <col=00ff00>



    //Used to display the color of coins
    /** Used to display the color of stacks of items/coins
     * Files that use these colors:
     * Class3_Sub7.java - Method123
     * Class36.java - Method1013
     */
    static RSString MillionStackColor = RSString.parse("<col=00ff80>");//Green
    static RSString ThousandStackColor = RSString.parse("<col=ffffff>");//White
    static RSString DefaultStackColor = RSString.parse("<col=ffff00>");//Yellow


    /** Used to display the color of Usernames/items/etc. "Context_Menu_Color"
     * Files that use these colors:
     * Class3_Sub13_Sub30.java - method312
     * Class3_Sub30_Sub1.java - method806
     */
    static RSString ContextColor = RSString.parse("<col=ffffff>");//White


    //private message Class3_Sub28_Sub4.aClass94_3573
    static RSString ObjectNameColor = RSString.parse("<col=00ffff>");//Light blue <col=00ffff>
    static RSString PMColor = RSString.parse(" )2> <col=ff0000>");

    //bank will be orange
    //entity color? Item color?
    //Class47(items), Class3_Sub30_Sub1, Class3_sub24_Sub4, WaterfallShader,
    static RSString ItemInterfaceColor = RSString.parse("<col=ff9040>");//offwhite
    static RSString GroundItemColor = RSString.parse("<col=ff9040>");//offwhite WaterfallShader //C
    static RSString ItemBackpackColor = RSString.parse("<col=ff9040>");//offwhite Class3_Sub24_Sub4 //C
    static RSString ContextColor2 = RSString.parse("<col=ff9040>");//offwhite

    //Class144
    static RSString NPCRightClickColor = RSString.parse("<col=ffff00>");//Yellow

    //Class130 Orange Possible Bank
    static RSString BankItemColor = RSString.parse(" )2> <col=ff9040>");

    //Class3 Sub28 Sub16 Possible Text Color
    static RSString TextColor = RSString.parse(" )2> <col=ffff00>");


    /** Used to change the loading bar color on client launch
     * Files that use these colors:
     * Class3_Sub28_Sub1.java - method updateLoadingBar
     */
    static Color loadingbarcolor = new Color(140, 17, 17);

    /**
     * Fun little RGB rainbow method
     */
    static float hue = 0.0f;
    static float sat = 1.0f;
    static float bri = 1.0f;

    static int getHexColors() {
        hue += 0.005;  //Bigger# faster the speed
        Color base = new Color(Color.HSBtoRGB(hue, sat, bri));
        Color color = new Color(base.getRed(), base.getGreen(), base.getBlue(), 125);
        return color.getRGB();
    }

}
