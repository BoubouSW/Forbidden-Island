package models;

public class CarteInnonde extends Carte{
    private static int lastNewId = -1;
    private int x;
    private int y;

    public CarteInnonde(int x, int y){
        super(CarteInnonde.lastNewId + 1);
        this.x = x;
        this.y = y;
    }

    public int[] getCoord() {
        return new int[] {this.x,this.y};
    }
}
