/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.editor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Travis
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Console.redirectSystemStreams();
        new Console().setVisible(true);
        new ToolSelection().setVisible(true);
        Main.log("Main", "FrostyCacheEditor v0.5");
    }

    /**
     * Logs the messages sent to the Console
     *
     * @param className the class name
     * @param message the message
     */
    public static void log(String className, String message) {
	//if (!message.contains("FAILED LOADING")) {
	    System.out.println("[" + className + "]: " + message);
	    printDebug(className, message);
	//}
    }

    private static void printDebug(String className, String message) {
            File f = new File(System.getProperty("user.home") + "/FrostyCacheEditor/logs/");
            File f1 = new File(System.getProperty("user.home") + "/FrostyCacheEditor/logs/" + Calendar.MONTH+Calendar.DAY_OF_MONTH+Calendar.YEAR+Calendar.HOUR_OF_DAY + ".txt");
            
            f.mkdirs();
        try {
            f1.createNewFile();
        } catch (IOException ex) {
            log("Main", "Could not create log file.");
        }
        String strFilePath = System.getProperty("user.home") + "/FrostyCacheEditor/logs/" + Calendar.MONTH+Calendar.DAY_OF_MONTH+Calendar.YEAR+Calendar.HOUR_OF_DAY + ".txt";

        try {
            FileOutputStream fos = new FileOutputStream(strFilePath, true);
                String strContent = new Date() + ": [" + className + "]: " + message;
                String lineSep = System.getProperty("line.separator");

                fos.write(strContent.getBytes());
                fos.write(lineSep.getBytes());

        } catch (FileNotFoundException ex) {
            log("Main","FileNotFoundException : " + ex);
        } catch (IOException ioe) {
            log("Main", "IOException : " + ioe);
        }
    }
}
