package utils;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageUtils {
/**
 * 
 * @param input
 * @param width
 * @param height
 * @return ImageIcon
 */
	public static ImageIcon resizeImageIcon(ImageIcon input, int width, int height) {
		Image img = input.getImage();
		Image newimg = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);
		return newIcon;
	}
}