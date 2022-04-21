package views;

import javax.swing.*;
import java.awt.*;

public class GameOverView extends JPanel {

    Icon image = new ImageIcon("resources/images/gameover.png");

    public GameOverView() {
        super();
        this.setSize(500, 285);
        this.setBackground(Color.RED);
        JLabel label = new JLabel(this.image);
        this.add(label);
    }
}
