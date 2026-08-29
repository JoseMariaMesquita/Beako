package Utils;

import javax.swing.*;
import java.awt.*;

/**
 * Class made for the sole purpose of resizing the icons used for the GUI
 */
public class ImageResizer {
    /**
     * Resizes the ImageIcon passed
     * @param imgIco - ImageIcon to resize
     * @return - ImageIcon after resizing
     */
    public static ImageIcon resizeImages(ImageIcon imgIco){
        Image image = imgIco.getImage();
        Image resizeImage = image.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        ImageIcon newImage = new ImageIcon(resizeImage);
        return newImage;
    }
}
