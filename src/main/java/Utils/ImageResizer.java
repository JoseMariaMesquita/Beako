package Utils;

import javax.swing.*;
import java.awt.*;

public class ImageResizer {
    public static ImageIcon resizeImages(ImageIcon imgIco){
        Image image = imgIco.getImage();
        Image resizeImage = image.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        ImageIcon newImage = new ImageIcon(resizeImage);
        return newImage;
    }
}
