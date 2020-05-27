package ms.launcher;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Constants {
	
	/**
	 * The size of the frame.
	 */
	public static final Dimension SIZE = new Dimension(900, 500);
	
	/**
	 * The cache name.
	 */
	public static final String CACHE_NAME = File.separator + ".keldagrim_530";
	
	/**
	 * Gets the screen coordinates to place panels in the center
	 * @param {Dimension} the panel size, null to use default frame size
	 * @return {Point} the center dimension
	 */
	public static Point getCenterPoint(Dimension panelSize) {
		if (panelSize == null)
			panelSize = SIZE;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		return new Point((int) ((screen.getWidth() / 2) - (panelSize.getWidth() / 2)), 
				(int) ((screen.getHeight() / 2) - (panelSize.getHeight() / 2)));
	}
	
	/**
	 * Gets the image.
	 * @param name the name.
	 * @return the image.
	 */
	public static Image getImage(String name) {
		Image image = null;
		try {
			image = SplashFrame.getInstance().getToolkit().getImage(getImageUrl(name));
		} catch (NullPointerException e) {
			System.err.println("Nullasdf image for " + name);
		}
		return image;
	}
	
	/**
	 * Gets the image url.
	 * @param name the name.
	 * @return the url.
	 */
	public static URL getImageUrl(String name) {
		try {
			return SplashFrame.getInstance().getClass().getResource(name);
		} catch (NullPointerException e) {
			System.err.println("Null URL for " + name);
			return null;
		}
	}
	
	/**
	 * Reads a link and returns the data.
	 * @param link the link.
	 * @return the data.
	 */
	public static String readLink(String link) {
		String data = "";
		URL obj = null;
		try {
			obj = new URL(link);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		HttpURLConnection con;
		try {
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			int responseCode = con.getResponseCode();
			if (responseCode != 200) {
				return data;
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				data+= line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * Gets the cache path.
	 * @return The cache path.
	 */
	public static String getCachePath() { 
		final String OS = System.getProperty("os.name").toUpperCase();
		if (OS.contains("WIN")) {
			return new StringBuilder(System.getProperty("user.home") + Constants.CACHE_NAME).toString();
		} else if (OS.contains("MAC")) {
			return new StringBuilder(System.getProperty("user.home") + Constants.CACHE_NAME).toString();
		} else if (OS.contains("NUX")) {
			return System.getProperty("user.home") + Constants.CACHE_NAME;
		}
		return new StringBuilder(System.getProperty("user.dir")).toString() + Constants.CACHE_NAME;
	}
}
