package models;

public class WaterLevel {
    private int currentLvl; // entre 1 et 4 -> 4 niveaux de difficulte
    private final int maxLvl = 10; //10 normalement

    public int getCurrentLvl() {return this.currentLvl;}
    public int getMaxLvl() {return this.maxLvl;}

    public WaterLevel(int start) {
        if (start > this.maxLvl || start < 1)
            throw new RuntimeException("erreur niveau eau");
        this.currentLvl = start;
    }

    public void augmenteLvl() {
        if (this.currentLvl >= this.maxLvl)
            return; //throw new RuntimeException("niveau max atteint");
        this.currentLvl += 1;
    }

    public int nbCaseInonde() {
        int lvl = this.currentLvl;
        if (lvl <= 2)
            return 2;
        if (lvl <= 5)
            return 3;
        if (lvl <= 7)
            return 4;
        if (lvl <= 9)
            return 5;
        else return -1; //fin de partie
    }

}
