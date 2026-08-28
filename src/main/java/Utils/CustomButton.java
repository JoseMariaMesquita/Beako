package Utils;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CustomButton extends JButton {
    public CustomButton(String text, Icon icon) {
        super(text, icon);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(true);
        this.setOpaque(true);
        this.setBorder(new LineBorder(new Color(240, 200, 215),2));
        this.setPreferredSize(new Dimension(180, 32));
        this.setMaximumSize(new Dimension(180, 32));
        this.setFont(new Font("SansSerif", Font.PLAIN, 12));
        this.setHorizontalTextPosition(SwingConstants.RIGHT);
        this.setBackground(Color.WHITE);
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setIconTextGap(10);

    }

    public CustomButton(String text) {
        super(text);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(true);
        this.setOpaque(true);
        this.setBorder(new LineBorder(new Color(240, 200, 215),2));
        this.setPreferredSize(new Dimension(180, 32));
        this.setMaximumSize(new Dimension(180, 32));
        this.setFont(new Font("SansSerif", Font.PLAIN, 12));
        this.setHorizontalTextPosition(SwingConstants.RIGHT);
        this.setBackground(Color.WHITE);
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setIconTextGap(10);
    }
}
