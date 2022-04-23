package views;

import javax.swing.*;
import java.awt.*;

public class ControlView extends JPanel {
    Icon image = new ImageIcon("resources/images/cross.png");

    public ControlView() {
        super();
        this.setSize(140, 140);
        //this.setBackground(Color.BLACK);
        JLabel label = new JLabel(image);
        this.add(label);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(new Color(100,149,237));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
