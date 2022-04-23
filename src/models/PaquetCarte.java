package models;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;

public class PaquetCarte<T extends I> {
    private LinkedList<T> Pile;
    private LinkedList<T> Default;
    private int nbCartePioche;

    public PaquetCarte(){
        // creer un paquet de carte vide
        this.Pile = new LinkedList<T>();
        this.Default = new LinkedList<T>();
        this.nbCartePioche = 0;
    }

    public PaquetCarte(Set<T> s){
        this.Pile = new LinkedList<T>();
        this.nbCartePioche = 0;
        for(T c: s){
            this.nbCartePioche++;
            this.Pile.add(c);
        }
        this.Default = new LinkedList<T>();
    }

    public void addPile(T c){
        // ajoute une carte a la pile
        this.Pile.add(c);
        this.nbCartePioche++;
    }

    public T pioche(){
        // pioche la premiere carte de la pile
        if(this.nbCartePioche == 0)
            throw new RuntimeException("Plus de carte dans la pioche");
        T res = this.Pile.getFirst();
        this.Pile.removeFirst();
        this.nbCartePioche--;
        return res;
    }

    public void Defausse(T c){
        // defausse la carte c dans le default
        this.Default.add(c);
    }

    public void melangePile(){
        // complexite pas ouf
        int size = this.Pile.size();
        Object[] cArray = new Object[size];
        for(int i = 0; i < size; i++) {
            cArray[i] = this.Pile.getFirst();
            this.Pile.removeFirst();
        }
        for(int k = 0; k < 100; k++){
            int i = (int) (Math.random()*size);
            int j = (int) (Math.random()*size);
            Object tmp = cArray[i];
            cArray[i] = cArray[j];
            cArray[j] = tmp;
        }
        for(Object c: cArray){
            this.Pile.addFirst((T) c);
        }
    }

    public void melangeDefausse(){
        // complexite pas ouf
        int size = this.Default.size();
        Object[] cArray = new Object[size];
        for(int i = 0; i < size; i++) {
            cArray[i] = this.Default.getFirst();
            this.Default.removeFirst();
        }
        for(int k = 0; k < 100; k++){
            int i = (int) (Math.random()*size);
            int j = (int) (Math.random()*size);
            Object tmp = cArray[i];
            cArray[i] = cArray[j];
            cArray[j] = tmp;
        }
        for(Object c: cArray){
            this.Default.addFirst((T) c);
        }
    }

    public void retourneDefausse(){
        // retourne la defausse et la place au dos de la pile
        int pos = this.Pile.size();
        while(! this.Default.isEmpty()){
            this.Pile.addLast(this.Default.getLast());
            this.Default.removeLast();
        }
        this.nbCartePioche = this.Pile.size();
    }

    public void supprimeDefausse(T c) {
        this.Default.remove(c);
    }

    public String Str(){
        String s = "Pile : ";
        for(T c: this.Pile){
            s += c.Str() + " ";
        }
        s += "\nDefausse : ";
        for(T c: this.Default){
            s += c.Str() + " ";
        }
        return s + "\n";
    }

    public Boolean pileVide(){
        return this.nbCartePioche == 0;
    }
}

interface I{
    public String Str();
}
