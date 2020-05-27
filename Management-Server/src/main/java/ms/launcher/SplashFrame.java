package ms.launcher;

import javax.swing.JFrame;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import ms.launcher.screen.SplashScreen;

/**
 * SplashFrame
 * @author Clayton Williams
 * @date Jul 18, 2015
 */
public class SplashFrame extends JFrame {
	
	/**
	 * Generated serial
	 */
	private static final long serialVersionUID = -6350549054538332575L;
	
	/**
	 * Instance of the splash
	 */
	private static final SplashFrame INSTANCE = new SplashFrame();
	
	/**
	 * Splash Screen
	 */
	private SplashScreen splashScreen;
	
	public void init() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setResizable(false);
		setSize(610, 290); //322
		setLayout(null);	
		//setBackground(new Color(0,0,0,0));
		getContentPane().setLayout(null);
		splashScreen = new SplashScreen();
		this.getContentPane().add(splashScreen);
		setLocation(Constants.getCenterPoint(this.getSize()));
		splashScreen.addAncestorListener(new AncestorListener () {
			@Override
	        public void ancestorAdded(AncestorEvent event) {
	            splashScreen.init();
	        }
			@Override
			public void ancestorRemoved(AncestorEvent event) {}

			@Override
			public void ancestorMoved(AncestorEvent event) {}
	    } );
		toFront();
		setVisible(true);
		setIconImage(Constants.getImage("splash/favicon.png"));
	}
	
	/**
	 * Gets the splash screen
	 * @return
	 */
	public SplashScreen getSplashScreen() {
		return splashScreen;
	}
	
	/**
	 * Pauses the current thread.
	 * @param duration The duration to pause for.
	 */
	public void pause(long duration) {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static SplashFrame getInstance() {
		return INSTANCE;
	}

}
