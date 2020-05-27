package ms.launcher.screen;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * Represents an Interactive Image for the splash screen
 * @author Hope (Clayton Williams)
 * @date 6-12-15
 */
public class InteractiveOverlay extends ImageIcon {

	/**
	 * Generated serial
	 */
	private static final long serialVersionUID = -4862170207406421173L;
	
	/**
	 * The name of the overlay
	 */
	private String name;
	
	/**
	 * Location on screen
	 */
	private Point location;
	
	/**
	 * The interactive bounds for mouse actions relative to the image's icon on the screen
	 */
	private Rectangle interactiveBounds;
	
	/**
	 * The default and hover image for this interactive image
	 */
	private Image defaultImage, hoverImage;
	
	/**
	 * Whether the content should be displayed
	 */
	private boolean display = false;
	
	/**
	 * InteractiveOverlay
	 * @param name - name of overlay
	 * @param defaultImage - the default image
	 * @param hoverImage - the image to be displayed on hover
	 * @param point - the upper left x,y point of the overlay
	 */
	public InteractiveOverlay(String name, Image defaultImage, Image hoverImage, Point point) {
		this(name, defaultImage, hoverImage, point, new Rectangle(0, 0, new ImageIcon(defaultImage).getIconWidth(),
				new ImageIcon(defaultImage).getIconHeight()));	
	}
	
	/**
	 * Constructor - creates a new ImageIcon from given Image
	 * @param image
	 * @param hoverImage
	 * @param location - {Point} of location on screen
	 * @param interactiveBounds
	 */
	public InteractiveOverlay(String name, Image defaultImage, Image hoverImage, Point location, Rectangle interactiveBounds) {
		this.name = name;
		this.defaultImage = defaultImage;
		this.hoverImage = hoverImage;
		this.interactiveBounds = interactiveBounds;
		this.location = location;
		this.setImage(this.defaultImage);		
	}
	
	/**
	 * Sets the interactive bounds for this icon
	 * @param bounds
	 */
	public void setInteractiveBounds(Rectangle bounds) {
		this.interactiveBounds = bounds;
	}
	
	/**
	 * Toggles the hovered image
	 * @param hover - true for hover image
	 */
	public void toggleHover(boolean hover) {
		if (hover)
			this.setImage(hoverImage);
		else 
			this.setImage(defaultImage);
	}
	
	/**
	 * Sets the default image
	 * @param image
	 */
	public void setDefaultImage(Image image) {
		this.defaultImage = image;
	}
	
	/**
	 * Sets the hovered image
	 * @param image
	 */
	public void setHoverImage(Image image) {
		this.hoverImage = image;
	}
	
	/**
	 * Returns the name of this overlay
	 * @return String name
	 */
	public String getName() {
		return name == null ? "" : name;
	}
	
	/**
	 * Returns if the given point is in the interactive bounds
	 * @param p - the point to check
	 * @return - true if in bounds
	 */
	public boolean inInteractiveBounds(Point p) {		
		Rectangle newBounds = new Rectangle((int) (location.getX() + interactiveBounds.getX()), (int) (location.getY() + interactiveBounds.getY()),
				(int) interactiveBounds.getWidth(), (int) interactiveBounds.getHeight());
		return newBounds.contains(p);
	}
	
	/**
	 * Gets the location of this overlay
	 * @return {Point}
	 */
	public Point getLocation() {
		return this.location;
	}
	
	/**
	 * Sets the display of this overlay
	 * @param display
	 */
	public void setDisplay(boolean display) {
		this.display = display;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isActive() {
		return this.display;
	}
	
}
