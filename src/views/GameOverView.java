package views;

import javax.swing.*;
import java.awt.*;

public class GameOverView extends JPanel {

    Image image = new ImageIcon("resources/images/gameover.png").getImage();

    public GameOverView() {
        super();
        this.setSize(500, 285);
    }

    public void paintComponent(Graphics g) {
        int x = (this.getWidth() - this.image.getWidth(null)) / 2;
        int y = (this.getHeight() - this.image.getHeight(null)) / 2;
        g.drawImage(this.image,x,y,null);
    }
}
