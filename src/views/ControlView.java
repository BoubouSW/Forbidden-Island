package views;

import javax.swing.*;
import java.awt.*;

public class ControlView extends JPanel {
    Icon image = new ImageIcon("resources/images/control2.png");

    public ControlView() {
        super();
        this.setSize(300, 237);
        JLabel label = new JLabel(image);
        this.add(label);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(new Color(100,149,237));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
