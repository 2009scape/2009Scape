package ms.launcher;

import java.awt.*;

/**
 * Keldagrim Launcher/Updater
 * @author Clayton Williams
 * @date June 17, 2015
 */
public class Initializer {
	
	/**
	 * Start our launcher/updater
	 */
	public Initializer() {
		SplashFrame.getInstance().init();
	}
	
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		/*try {
//			UIManager.setLookAndFeel(new org.pushingpixels.substance.api.skin.SubstanceTwilightLookAndFeel());
			JFrame.setDefaultLookAndFeelDecorated(true);
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
					SplashFrame.getInstance().init();
			
			}
		});
		SplashFrame.getInstance().init();
	}
	
}
