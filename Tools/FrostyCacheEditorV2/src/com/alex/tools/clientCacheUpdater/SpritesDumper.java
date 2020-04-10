package com.alex.tools.clientCacheUpdater;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;

import com.alex.store.Index;
import com.alex.store.Store;

public class SpritesDumper {

	
	
	/*public static void main(String[] args) throws IOException {
		Store cache = new Store("cache667_2/", false);
		ImagesFile file = new ImagesFile(cache, 498, 0);
		file.replaceImage(ImageIO.read(new File("498_0_0.png")), 0);
		cache.getIndexes()[8].putFile(2498, 0, file.encodeFile());
		file = new ImagesFile(cache, 2498, 0);
		for(int count = 0; count < file.getImages().length; count++) {
			String name = ""+498+"_2_"+0+"_"+count;
			BufferedImage image = file.getImages()[count];
			if(image == null) {
				System.out.println("NULL: "+name);
				continue;
			}
			ImageIO.write(image, "png", new File(name+".png"));
			System.out.println(name);
		}
	}*/
	
	/*private static BufferedImage internalResize(BufferedImage source, int destWidth, int destHeight) {
	    int sourceWidth = source.getWidth();
	    int sourceHeight = source.getHeight();
	    double xScale = ((double) destWidth) / (double) sourceWidth;
	    double yScale = ((double) destHeight) / (double) sourceHeight;
	    Graphics2D g2d = null;

	    BufferedImage resizedImage = new BufferedImage(destWidth, destHeight, BufferedImage.TRANSLUCENT);

	    try {

	        g2d = resizedImage.createGraphics();

	        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
	        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

	        AffineTransform at = AffineTransform.getScaleInstance(xScale, yScale);

	        g2d.drawRenderedImage(source, at);

	    } finally {
	        if (g2d != null)
	            g2d.dispose();
	    }

	//doesn't keep the transparency
	    if (source.getType() == BufferedImage.TYPE_BYTE_INDEXED) {

	        BufferedImage indexedImage = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_BYTE_INDEXED);

	        try {
	            Graphics g = indexedImage.createGraphics();
	            g.drawImage(resizedImage, 0, 0, null);
	        } finally {
	            if (g != null)
	                g.dispose();
	        }

	        return indexedImage;
	    }

	    return resizedImage;

	}*/

	
	/*
	 * divides backgorund
	 */
	public static void main2(String[] args) throws IOException {
		BufferedImage background = ImageIO.read(new File("bg/matrix.jpg"));
		int id = 3769;
		
		int sx = background.getWidth() / 2;
		int sy = background.getHeight() / 2;
		
		for(int y = 0; y < 2; y++) {
			for(int x = 0; x < 2; x++) {
				System.out.println("id "+id);
				BufferedImage part = background.getSubimage(x * sx, y * sy, sx, sy);
				ImageIO.write(part, "gif", new File("bg/"+(id++)+".gif"));
				
			}
		}
	
	}
	
	public static void main3(String[] args) throws IOException {
		Store cache = new Store("cache667_2/", false);
		UpdateCache.packLogo(cache);
		System.out.println("Adding donator icon...");
		UpdateCache.packDonatorIcon(cache);
		System.out.println("Adding Matrix icon...");
		UpdateCache.packMatrixIcon(cache);
		/*for(int i = 0; i < 4; i++) {
			int realid = 3769 + i;
			int id = 3769 + i;
			cache.getIndexes()[8].putFile(id, 0, new ImagesFile(ImageIO.read(new File("bg/"+realid+".gif"))).encodeFile());
			id = 3779 + i;
			cache.getIndexes()[8].putFile(id, 0, new ImagesFile(ImageIO.read(new File("bg/"+realid+".gif"))).encodeFile());
			id = 3783 + (i >= 2 ? (i-2) : i + 2);
			cache.getIndexes()[8].putFile(id, 0, new ImagesFile(ImageIO.read(new File("bg/"+realid+".gif"))).encodeFile());
			id = 3769 + i;
			cache.getIndexes()[34].putFile(id, 0, new ImagesFile(ImageIO.read(new File("bg/"+realid+".gif"))).encodeFile());
			id = 3779 + i;
			cache.getIndexes()[34].putFile(id, 0, new ImagesFile(ImageIO.read(new File("bg/"+realid+".gif"))).encodeFile());
			id = 3783 + (i >= 2 ? (i-2) : i + 2);
			cache.getIndexes()[34].putFile(id, 0, new ImagesFile(ImageIO.read(new File("bg/"+realid+".gif"))).encodeFile());
			id = 3769 + i;
			cache.getIndexes()[32].putFile(id, 0, getImage(new File("bg/"+realid+".png")));
			id = 3779 + i;
			cache.getIndexes()[32].putFile(id, 0, getImage(new File("bg/"+realid+".png")));
			id = 3783 + (i >= 2 ? (i-2) : i + 2);
			cache.getIndexes()[32].putFile(id, 0, getImage(new File("bg/"+realid+".png")));;
			
			
			System.out.println("added file: "+i);
		}*/
	}
	
	
	public static byte[] getImage(File file) throws IOException {
		ImageOutputStream stream = ImageIO.createImageOutputStream(file);
		byte[] data = new byte[(int) stream.length()];
		stream.read(data);
		return data;
	}
	public static void main(String[] args) throws IOException {
		Store cache = new Store("718/rscache/");
		Index sprites = cache.getIndexes()[32];
		for(int archiveId : sprites.getTable().getValidArchiveIds()) {
			for(int fileId : sprites.getTable().getArchives()[archiveId].getValidFileIds()) {
				byte[] data = sprites.getFile(archiveId, fileId);
				Image image = Toolkit.getDefaultToolkit().createImage(data);
				String name = "sprites32/"+archiveId+"_"+fileId;
				BufferedImage bi = toBufferedImage(image);
				if(bi == null) {
					System.out.println("failed "+name);
					continue;
				}
				ImageIO.write(bi, "png", new File(name+".png"));
			}
		}
	}
	
	// This method returns a buffered image with the contents of an image
	public static BufferedImage toBufferedImage(Image image) {
	    if (image instanceof BufferedImage) {
	        return (BufferedImage)image;
	    }

	    // This code ensures that all the pixels in the image are loaded
	    image = new ImageIcon(image).getImage();

	    // Determine if the image has transparent pixels; for this method's
	    // implementation, see Determining If an Image Has Transparent Pixels
	    boolean hasAlpha = true;//hasAlpha(image);

	    // Create a buffered image with a format that's compatible with the screen
	    BufferedImage bimage = null;
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    try {
	        // Determine the type of transparency of the new buffered image
	        int transparency = Transparency.OPAQUE;
	        if (hasAlpha) {
	            transparency = Transparency.BITMASK;
	        }

	        // Create the buffered image
	        GraphicsDevice gs = ge.getDefaultScreenDevice();
	        GraphicsConfiguration gc = gs.getDefaultConfiguration();
	        if(image.getWidth(null) < 0 || image.getHeight(null) < 0)
	        	return null;
	        bimage = gc.createCompatibleImage(
	            image.getWidth(null), image.getHeight(null), transparency);
	    } catch (HeadlessException e) {
	        // The system does not have a screen
	    }

	    if (bimage == null) {
	        // Create a buffered image using the default color model
	        int type = BufferedImage.TYPE_INT_RGB;
	        if (hasAlpha) {
	            type = BufferedImage.TYPE_INT_ARGB;
	        }
	        bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
	    }

	    // Copy image to buffered image
	    Graphics g = bimage.createGraphics();

	    // Paint the image onto the buffered image
	    g.drawImage(image, 0, 0, null);
	    g.dispose();

	    return bimage;
	}

	
	/*public static void main(String[] args) throws IOException {
		Store cache = new Store("cache667_2/", false);
		Index sprites = cache.getIndexes()[34];
		for(int archiveId : sprites.getTable().getValidArchiveIds()) {
			for(int fileId : sprites.getTable().getArchives()[archiveId].getValidFileIds()) {
				ImagesFile file = new ImagesFile(cache, 34, archiveId, fileId);
				/*if(file.getImages() == null)
					continue;*/
			/*	for(int count = 0; count < file.getImages().length; count++) {
					String name = "sprites34/"+archiveId+"_"+fileId+"_"+count;
					BufferedImage image = file.getImages()[count];
					if(image == null) {
						System.out.println("NULL: "+name);
						continue;
					}
					ImageIO.write(image, "png", new File(name+".png"));
					System.out.println(name);
				}
			}
		}
	}*/

}
