package ms.launcher.screen.impl;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import ms.launcher.Constants;

public class LoadingBar extends JPanel {
	
	/**
	 * Generated serial
	 */
	private static final long serialVersionUID = 2607031854150505152L;
	
	/**
	 * Size of our LoadingBar
	 */
	private static final Dimension SIZE = new Dimension(489, 113);
	
	/**
	 * Width of the sub fill bar
	 */
	private static final int SUB_BAR_WIDTH = 150;
	
	/**
	 * The top and bottom layers of our loading bar
	 */
	private Image outline, fill;
	
	/**
	 * Percent of the bar loaded
	 */
	private int percentLoaded = 0;
	
	/**
	 * Load images
	 */
	public LoadingBar() {
		this.setLocation(50, 120);
		setSize(SIZE);
		setOpaque(false);
		outline = Constants.getImage("splash/loading_outline.png");
		fill = Constants.getImage("splash/loading_fill.png");	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(outline, 0, 0, this);
		if (percentLoaded > 0) {
			int fillLength = getFillLength();
			int dx1 = (fillLength - SUB_BAR_WIDTH);	
			g.drawImage(fill, dx1, 0, fillLength, SIZE.height, dx1, 0, fillLength, SIZE.height, this);
		}
	}
	
	/**
	 * Returns the fill length of the bar using percentLoaded
	 * @return
	 */
	private int getFillLength() {
		float widthModifier = ((float) (SIZE.getWidth() + SUB_BAR_WIDTH) / 100);
		int totalWidth = (int) Math.ceil(percentLoaded * widthModifier);
		return totalWidth > (SIZE.width + SUB_BAR_WIDTH) ? (SIZE.width + SUB_BAR_WIDTH) : totalWidth;		
	}
	
	/**
	 * Sets the percent loaded 0 to 100 inclusive
	 * @param percent
	 */
	public void setPercentLoaded(int percent) {
		this.percentLoaded = percent > 100 ? 100 : percent < 0 ? 0 : percent;
	}
	
	/**
	 * Sets and updates the progress bar
	 * @param percent
	 */
	public void updatePercent(int percent) {
		setPercentLoaded(percent);
		repaint();
	}
	
	

}
