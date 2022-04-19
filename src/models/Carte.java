package models;

public abstract class Carte implements I{
    private int id;

    public Carte(int i){
        this.id = i;
    }

    public String Str(){
        return Integer.toString(this.id);
    }
}
