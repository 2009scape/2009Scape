package ms.launcher.screen;

import ms.launcher.Constants;
import ms.launcher.SplashFrame;
import ms.launcher.screen.impl.LoadingBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.Timer;

/**
 * Splash Screen
 * Note: All images are loaded in terms of a fixed position on a 610x290 grid (all have same width and height, but just overlay)
 * @author Clayton Williams (Hope)
 * @date Jun-11-2015
 */
public class SplashScreen extends JPanel implements MouseMotionListener, MouseListener { 
	
	/**
	 * Generated serial
	 */
	private static final long serialVersionUID = 2637078526635123434L;
	
	/**
	 * Various client related settings
	 */	
	private LoadingBar loadingBar;

	private Image splashbg, logo;
	private String loaderStatus = "Starting Up...";
	private List<InteractiveOverlay> interactiveOverlays = new ArrayList<InteractiveOverlay>();

	public SplashScreen() {
		
		setLayout(null);
		setBounds(0, 0, 610, 290);
		setOpaque(false);
		this.add(loadingBar = new LoadingBar());
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		/**
		 * Add Images
		 */
		splashbg = Constants.getImage("splash/splashbg.png");
		logo = Constants.getImage("splash/logo.png");
		interactiveOverlays.add(new InteractiveOverlay("exitButton", Constants.getImage("splash/exit.png"), 
				Constants.getImage("splash/exit_hover.png"), new Point(580, 15)));
		interactiveOverlays.add(new InteractiveOverlay("settingsButton", Constants.getImage("splash/settings.png"), 
				Constants.getImage("splash/settings_hover.png"), new Point(555, 15)));
		interactiveOverlays.add(new InteractiveOverlay("normalClient", Constants.getImage("splash/client.png"), 
				Constants.getImage("splash/client_hover.png"), new Point(200, 175)));
		setOverlayActive("exitButton", true);
		
	}
	
	/**
	 * 
	 */
	public void init() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			int i = 0;
			int cycles = 0;
			@Override
			public void run() {
				if (i == 100) {
					if (cycles == 1) {
						remove(loadingBar);
						setOverlayActive("settingsButton", true);
						setOverlayActive("normalClient", true);
						setOverlayActive("swiftkitClient", true);
						loaderStatus = "Select an Option";
						repaint();
						this.cancel();
					} else if (cycles == 0) {
						updateStatus("Retrieving Settings...");
					}
					i = 0;
					cycles++;
				}
				loadingBar.updatePercent(++i);
		
			}
			
		}, 0, 25);
	}
	
	/**
	 * Updates and repains the status
	 * @param status
	 */
	public void updateStatus(String status) {
		loaderStatus = status;
		repaint();
	}
	
	/**
	 * Sets an overlay active
	 * @param overlayName
	 * @param active
	 */
	public void setOverlayActive(String overlayName, boolean active) {
		for (int i = 0; i < interactiveOverlays.size(); i++) {
			InteractiveOverlay io = interactiveOverlays.get(i);
			if (io.getName().equalsIgnoreCase(overlayName))
				io.setDisplay(active);		
		}
	}
	
	/**
	 * Destroys the frame
	 */
	public void destroy() {
		setVisible(false);
		SplashFrame.getInstance().dispose();
	}
	
	@Override
	public void paintComponent(Graphics g){  
		super.paintComponent(g);		
    	g.drawImage(splashbg, 0, 0, this);
    	g.drawImage(logo, -150, -105, this);
    	for(InteractiveOverlay io : interactiveOverlays) {
    		if (io.isActive())
    			g.drawImage(io.getImage(), (int) io.getLocation().getX(), (int) io.getLocation().getY(), this);
    	}
    	
    	/**
    	 * Drawing status of loader
    	 */
    	FontMetrics fm = this.getFontMetrics(getFont());
    	int statusLength = fm.stringWidth(loaderStatus);
    	g.setColor(Color.white);
    	g.drawString(loaderStatus, 595 - statusLength, 280);
    }
	
	/*********************************************************************
	 * Mouse Events 
	 ********************************************************************/

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point p = e.getPoint();
		//System.out.println("X: " + p.getX() + " Y: " + p.getY());
		Iterator<InteractiveOverlay> it = interactiveOverlays.iterator();
		boolean interaction = false;
		while(it.hasNext()) {
			InteractiveOverlay io = it.next();
			if (!io.isActive())
				continue;
			io.toggleHover(io.inInteractiveBounds(p));
			if (io.inInteractiveBounds(p))
				interaction = true;
		}				
		if (interaction)
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		else
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));								
		repaint();
	}	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		repaint();
		Point p = e.getPoint();
		Iterator<InteractiveOverlay> it = interactiveOverlays.iterator();
		while(it.hasNext()) {
			InteractiveOverlay io = it.next();
			if (!io.isActive())
				continue;
			if (io.inInteractiveBounds(p)) {
				try {
					switch(io.getName()) {
						case "normalClient":
						case "swiftkitClient":
							destroy();
							File file = new File(Constants.getCachePath() + File.separator + "resources/org/keldagrim/launcher/arios-gamepack-530.jar");
							try {
								System.out.println("[SplashScreen] Using Absolute File Path: " + file.getAbsolutePath() + " for runtime.");
								Runtime.getRuntime().exec(new String[]{"java", "-jar", file.getAbsolutePath(), io.getName().equalsIgnoreCase("swiftkitClient") ? "true" : "false"});
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							System.exit(0);
							break;
						case "exitButton":				
							System.exit(0);
							break;
						case "settingsButton":
							JOptionPane.showMessageDialog(new JFrame("Info"), "Client Settings will be implemented shortly.");
							break;
						default:
							System.out.println("No actions for mouseclick on overlay: " + io.getName());
							break;
					}
				} catch (Exception ex) { ex.printStackTrace(); }
			}
		}
	}
	
}
