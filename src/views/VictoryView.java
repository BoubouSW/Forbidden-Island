package views;

import javax.swing.*;
import java.awt.*;

public class VictoryView extends JPanel {

    Icon image = new ImageIcon("resources/images/victory2.png");

    public VictoryView() {
        super();
        this.setSize(500, 185);
        this.setBackground(Color.RED);
        JLabel label = new JLabel(this.image);
        this.add(label);
    }
}
