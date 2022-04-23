package views;

import javax.swing.*;
import java.awt.*;

public class VictoryView extends JPanel {

    Image image = new ImageIcon("resources/images/victory2.png").getImage();

    public VictoryView() {
        super();
        this.setSize(500, 185);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.RED);
        //g.fillRect(0, 0, getWidth(), getHeight());
        int x = (this.getWidth() - this.image.getWidth(null)) / 2;
        int y = (this.getHeight() - this.image.getHeight(null)) / 2;
        g.drawImage(this.image,x,y,null);
    }
}
