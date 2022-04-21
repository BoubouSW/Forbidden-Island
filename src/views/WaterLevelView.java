package views;

import javax.swing.*;
import java.awt.*;

public class WaterLevelView extends JPanel {
    Icon image = new ImageIcon("resources/images/waterlvl2.png");
    Icon cursor = new ImageIcon("resources/images/curseur2.png");

    public WaterLevelView() {
        super();
        this.setSize(140, 300);
        //this.setBackground(Color.BLACK);
        JLabel label = new JLabel(this.image);
        JLabel curseur = new JLabel(this.cursor);

        //this.add(curseur);
        this.add(label);
    }
}
