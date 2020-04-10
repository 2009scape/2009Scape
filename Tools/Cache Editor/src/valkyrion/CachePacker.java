package valkyrion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JOptionPane;

import com.alex.store.Store;
import com.alex.utils.Constants;
import com.alex.utils.Utils;

public class CachePacker {
	FileInputStream f2;

	public static void replaceMidi(String cacheDir, int archiveId, int fileId, String convertedFileDir) throws IOException {
		Store cache = new Store(cacheDir);
		if (cache.getIndexes()[6].putFile(archiveId, fileId, getBytesFromFile(new File(convertedFileDir)))) {
			JOptionPane.showMessageDialog(null, "MIDI packed successfully, if your client crashes on startup, use another midi and the backuped cache and try again.");
		} else {
			JOptionPane.showMessageDialog(null, "MIDI did not successfully pack!");
		}
	}
	public static void main(String...args) throws Throwable {
		String dir = "C:/Users/v4rg/Downloads/rs music/";
		Store store = new Store("./498/");
		Store from = new Store("./666/");
		if (true) {
			int index = 14;
			System.out.println("To amount=" + store.getIndexes()[index].getValidArchivesCount());
			System.out.println("From amount=" + from.getIndexes()[index].getValidArchivesCount());
			int count = 0;
			int fail = 0;
			for (int archive = 0; archive < from.getIndexes()[index].getValidArchivesCount(); archive++) {
				if (from.getIndexes()[index].archiveExists(archive)) {
					byte[] data = from.getIndexes()[index].getFile(archive, 0);
					if (data == null || data.length < 1) {
						fail++;
						//							System.out.println("Invalid archive " + archive);
						continue;
					}
					store.getIndexes()[index].putFile(archive, 0, data);
					if (store.getIndexes()[index].getFile(archive, 0) != null) {
						if (count++ % 100 == 0)
						System.out.println("Packed music " + archive);
					} else {
						System.out.println("Failed to pack music " + archive);
						fail++;
					}
					//						continue;
				} else {
					fail++;
				}
			}
			System.out.println("Packed " + count + "/" + (count + fail) + " music (" + 666 + ")!");
			return;
		}
		//		for (File f : new File(dir + "out/").listFiles()) {
		//			int index = Integer.parseInt(f.getName().replace(".mid", ""));
		//			boolean b = store.getIndexes()[6].putFile(index, 0, Constants.GZIP_COMPRESSION, getBytesFromFile(f), null, true, false, -1, -1);
		//			System.out.println(b ? "Successfully packed music " + index + "!" : "Failed to pack music " + index + "!");
		//		}
		//		store.getIndexes()[6].resetCachedFiles();
		//		BufferedWriter musicList = new BufferedWriter(new FileWriter("./music-list.txt"));
		//		new File(dir + "out/").mkdir();
		//		int index = 1;
		//		for (File f : new File(dir + "rs music/").listFiles()) {
		//			if (!f.getName().startsWith("runescape")) {
		//				continue;
		//			}
		//			System.out.println(f.getName());
		//			try { 
		//				convertMidi(dir + "rs music/" + f.getName(), dir + "out/" + index + ".mid");
		//				musicList.append((index++) + ": " + f.getName().replace(".mid", ""));
		//				musicList.newLine();
		//			} catch (Throwable t) {
		//				t.printStackTrace();
		//				musicList.append((index++) + ": " + f.getName().replace(".mid", "") + " //FAILED!");
		//				musicList.newLine();
		//			}
		//		}
		//		musicList.flush();
		//		musicList.close();
	}

	public static void convertMidi(String input, String output) throws Exception {
		MusicEncoder.convertMidi(input, output);
	}

	public static void addMusicFile(String cacheDir, String convertedFileDir, String musicName) throws IOException {
		Store cache = new Store(cacheDir);
		cache.getIndexes()[6].putFile(803, 0, Constants.GZIP_COMPRESSION, getBytesFromFile(new File(convertedFileDir)), null, true, false, Utils.getNameHash(musicName), -1);
	}

	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();

		// You cannot create an array using a long type.
		// It needs to be an int type.
		// Before converting to an int type, check
		// to ensure that file is not larger than Integer.MAX_VALUE.
		if (length > Integer.MAX_VALUE) {
			// File is too large
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int)length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			is.close();
			throw new IOException("Could not completely read file "+file.getName());
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}

}
