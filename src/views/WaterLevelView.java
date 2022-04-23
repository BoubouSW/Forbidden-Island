package views;

import models.WaterLevel;

import javax.swing.*;
import java.awt.*;

public class WaterLevelView extends JPanel {
    Image image = new ImageIcon("resources/images/waterlvl2.png").getImage();
    Image cursor = new ImageIcon("resources/images/curseur2.png").getImage();
    WaterLevel waterLevel;

    public WaterLevelView() {
        super();
        this.setSize(140, 300);
        //this.setBackground(Color.BLACK);
        //JLabel label = new JLabel(this.image);
        //JLabel curseur = new JLabel(this.cursor);

        //this.add(curseur);
        //this.add(label);
    }

    public void setWaterLevel(WaterLevel waterLevel) {this.waterLevel = waterLevel;}

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(new Color(239,240,241));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(this.image, this.getWidth() / 2 - this.image.getWidth(null) / 2, this.getHeight() / 2 - this.image.getHeight(null) / 2, null);
        int minY = this.getHeight() / 2 + this.image.getHeight(null) / 2 - 38;
        int space = 34;
        int lvl = this.waterLevel.getCurrentLvl();
        g.drawImage(this.cursor, this.getWidth() / 2 - this.image.getWidth(null) / 2 - 20, minY - space*lvl, null);
    }

}
