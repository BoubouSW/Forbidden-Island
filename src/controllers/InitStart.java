package models;

import models.Start;
import views.InitView;

import java.util.ArrayList;

public class InitStart {

    private ArrayList<String> nomsJoueurs;
    private int waterlvl;

    public InitStart() {
        InitView startMenu = new InitView();
        startMenu.setUpMenu(this);
        startMenu.drawWin();
    }

    public void initPlayer(ArrayList<String> names) {
        this.nomsJoueurs = names;
    }

    public void initWaterLvl(int w) {
        this.waterlvl = w;
    }

    public void run() {
        Start start = new Start(this.nomsJoueurs,this.waterlvl);
    }

}
