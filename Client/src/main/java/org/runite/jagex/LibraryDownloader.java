package org.runite.jagex;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author Shadowy on rune-server
 * @date 4th June 2014
 */
public class LibraryDownloader {

	public LibraryDownloader() {}

	private final int BUFFER = 1024;

	public void drawLoadingText(String text) {
		println(text);
	}

	private String cacheDir = "NEEDS TO BE SET--1";
	private String getLibsDir() {
		return cacheDir;
	}

	public static String getCachePathFromDll(String dll) {
		int lastSlashIndex = dll.lastIndexOf('/');
		if (lastSlashIndex >= 0 && lastSlashIndex < dll.length() -1) {
			//println("getArchivedName returning "+getCacheLink().substring(lastSlashIndex + 1));
			//return getCacheLink().substring(lastSlashIndex + 1);
			return dll.substring(0, lastSlashIndex + 1);//(lastSlashIndex + 1);
		} else {
			System.err.println("Failed to get archived cache name.");
		}
		return "";
	}

	private String getCacheLink() {
		return "https://dl.dropboxusercontent.com/u/9942513/530_project/live/530hdlibs.zip";
	}

	private String fileToExtract = "NEEDS TO BE SET--2";
	private boolean downloaded;
	public void updateDlls(String cacheDir1) {
		if(downloaded){
			println("Libraries already aquired.");
			return;
		}
		this.cacheDir = getCachePathFromDll(cacheDir1);
		println("[FindCache] set to "+this.cacheDir+" using file path "+cacheDir1);
		fileToExtract = getLibsDir() + getArchivedName();
		try {
			File location = new File(this.cacheDir+"/jogl_32.dll");
			/**
			 * User doesn't have the cache.
			 */
			if(location.exists()) {
				println("You have the required HD libs.");
				downloaded=true;
			} else {
				println("Aquiring  required HD libraries...");
				if(downloadFile(getCacheLink(), getArchivedName())){
					if(unZip()){
						deleteZIP(fileToExtract);
						println("Libraries aquired.");
						downloaded = true;
					}
				}
			}
        } catch(Exception e) {
			e.printStackTrace();
		}
		return;
	}

	private boolean downloadFile(String adress, String localFileName) {
		OutputStream out = null;
		URLConnection conn;
		InputStream in = null;
		try {
			URL url = new URL(adress);
			out = new BufferedOutputStream( new FileOutputStream(getLibsDir() + "/" +localFileName)); 

			conn = url.openConnection();
			in = conn.getInputStream(); 

			byte[] data = new byte[BUFFER]; 

			int numRead;
			long numWritten = 0;
			int length = conn.getContentLength();

			int lastPercent = -1;
			while((numRead = in.read(data)) != -1) {
				out.write(data, 0, numRead);
				numWritten += numRead;
				int percentage = (int)(((double)numWritten / (double)length) * 100D);
				if(percentage != lastPercent){
					lastPercent = percentage;
					drawLoadingText("Downloading library " + percentage + "%");
				}
			}

			println(localFileName + "\t" + numWritten);
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
				return true;
			} catch (IOException ioe) {
			}
		}
		return false;
	}

	private String getArchivedName() {
		int lastSlashIndex = getCacheLink().lastIndexOf('/');
		if (lastSlashIndex >= 0 && lastSlashIndex < getCacheLink().length() -1) {
			//println("getArchivedName returning "+getCacheLink().substring(lastSlashIndex + 1));
			return getCacheLink().substring(lastSlashIndex + 1);
		} else {
			System.err.println("Failed to get archived cache name.");
		}
		return "";
	}

	private boolean unZip() {
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(fileToExtract));
			ZipInputStream zin = new ZipInputStream(in);
			ZipEntry e;

			drawLoadingText("Extracting... please wait");
			while((e=zin.getNextEntry()) != null) {
				if(e.isDirectory()) {
					(new File(getLibsDir() + e.getName())).mkdir();
				} else {
					println("Unzipping " + e.getName());
					if (e.getName().equals(fileToExtract)) {
						unzip(zin, fileToExtract);
						break;
					}
					unzip(zin, getLibsDir() + e.getName());
				}
			}
			zin.close();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private void unzip(ZipInputStream zin, String s) throws IOException {
		FileOutputStream out = new FileOutputStream(s);
		byte [] b = new byte[BUFFER];
		int len;
		while ((len = zin.read(b)) != -1) {
			out.write(b,0,len);
		}
		out.close();
	}

	private void deleteZIP(String fileToDelete) {
		File ZIPFile=new File(fileToDelete);
		boolean exists = ZIPFile.exists();
		if (exists) {
			try{
				if(ZIPFile.delete()){
					println(ZIPFile.getName()+ " was deleted.");
				}else{
					println(ZIPFile.getName()+ " was not deleted.");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		} else {
			println("Unable to find unneeded .ZIP data to delete.");
		}
    }

	private void println(String string) {
		System.out.println(string);
	}
}