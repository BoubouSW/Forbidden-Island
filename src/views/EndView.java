package views;

import javax.swing.*;
import java.awt.*;

public class EndView extends JFrame {
    private int widthSetup = 1000;
    private int heightSetup = 1000;
    private boolean lose;

    public EndView(boolean b) {
        super("End");
        this.setUndecorated(true);
        this.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        this.pack();
        this.setLayout(null);
        this.setSize(this.widthSetup,this.heightSetup);
        this.lose = b;
    }

    public void drawWin() {
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (this.lose)
            this.drawLose();
        else
            this.drawVictory();
    }

    public void drawLose() {
        GameOverView jPanel = new GameOverView();

        int width = this.getWidth() - jPanel.getWidth();
        int height = this.getHeight() - jPanel.getHeight();
        jPanel.setLocation(width/2, height/2);

        this.add(jPanel);
    }

    public void drawVictory() {
        VictoryView jPanel = new VictoryView();

        int width = this.getWidth() - jPanel.getWidth();
        int height = this.getHeight() - jPanel.getHeight();
        jPanel.setLocation(width/2, height/2);

        this.add(jPanel);
    }
}
