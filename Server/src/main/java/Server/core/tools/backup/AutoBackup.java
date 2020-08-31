/*
 * Class AutoBackup
 *
 * Created by Mikee
 */

package core.tools.backup;

import core.game.system.SystemLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
 
public class AutoBackup {

    public AutoBackup() {
        File f1 = new File("./Server/src/");
        File f2 = new File((System.getProperty("user.name").equalsIgnoreCase("ethan") ? System.getProperty("user.home") + "/ Desktop/Keldagrim-Backups/src-backups/SRC-" + getDate() + ".zip" : "./Server/data/backups/src-backups/SRC-" + getDate() + ".zip"));
        if (!f2.exists()) {
            try {
                SystemLogger.log("[Auto-Backup] The mainsave has been automatically backed up.");
                zipDirectory(f1, f2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            SystemLogger.log("[Auto-Backup] The mainsave has already been backed up today.");
        }
    }

    private static void zipDirectory(File f, File zf) throws IOException {
        ZipOutputStream z = new ZipOutputStream(new FileOutputStream(zf));
        zip(f, f, z);
        z.close();
    }

    private static void zip(File directory, File base, ZipOutputStream zos) throws IOException {
        File[] files = directory.listFiles();
        byte[] buffer = new byte[8192];
        int read = 0;
        for (int i = 0, n = files.length; i < n; i++) {
            if (files[i].isDirectory()) {
                zip(files[i], base, zos);
            } else {
                FileInputStream in = new FileInputStream(files[i]);
                ZipEntry entry = new ZipEntry(files[i].getPath().substring(base.getPath().length() + 1));
                zos.putNextEntry(entry);
                while (-1 != (read = in.read(buffer))) {
                    zos.write(buffer, 0, read);
                }
                in.close();
            }
        }
    }

    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("MMM d, y");
        Date date = new Date();
        return dateFormat.format(date);
    }
}