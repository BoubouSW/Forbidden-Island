package views;

import controllers.BoutonValiderController;
import models.CarteTresor;
import models.Clef;
import models.Player;
import models.PlayerController;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

// classe reliee au player controller (la creation de cette classe depend du player controller)
public class EncadreSelection extends JPanel {
    public enum typeDeSelection {JOUEUR, CLEF};

    private typeDeSelection type;
    private Set<Player> playersToSelect;
    private Set<Player> playersRes;
    private Set<CarteTresor> carteToSelect;
    private Set<CarteTresor> carteRes;
    private CarteTresor[] carteTmp;
    private BoutonValiderController button;
    private boolean theButtonHasBeenPressed = false;

    public EncadreSelection(){
        // affiche un encadre bleu vide
        super();
        this.setBackground(new Color(10,149,237));
        this.setSize(100,100);
    }

    public void setForPlayers(Set<Player> players){
        this.theButtonHasBeenPressed = false;
        this.type = typeDeSelection.JOUEUR;
        this.playersToSelect = players;
        this.playersRes = new HashSet<Player>();
        this.setBackground(new Color(10,149,237));
        this.setSize(100,(players.size())*50 + 30);
        Set<Player> res = new HashSet<Player>();
        JPanel panel = new JPanel();
        panel.setSize(200,(players.size())*50 + 30);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        int size = players.size();
        BoutonSelection.isSelectionned = new boolean[size];
        int i = 0;
        Player[] pArray = new Player[size];
        for(Player p: players){
            System.out.println(p.getName());
            panel.add(new BoutonSelection(p.getName(), 80, 50, p.getIdentifier()));
        }
        boolean b = false;
        BoutonValiderController bvc = new BoutonValiderController("Valider",50, 20, 13, this);
        JPanel sub = new JPanel();
        bvc.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1,Color.BLACK));
        this.button = bvc;
        sub.add(bvc);
        panel.add(sub);
        panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        this.add(panel);
    }

    public void setForClef(Set<CarteTresor> cartesTresor){
        this.theButtonHasBeenPressed = false;
        this.type = typeDeSelection.CLEF;
        this.carteToSelect = cartesTresor;
        this.carteRes = new HashSet<CarteTresor>();
        this.setBackground(new Color(10,149,237));
        this.setSize(100,(cartesTresor.size())*50 + 30);
        Set<CarteTresor> res = new HashSet<CarteTresor>();
        JPanel panel = new JPanel();
        int size = cartesTresor.size();
        panel.setSize(200,(size)*50 + 30);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        BoutonSelection.isSelectionned = new boolean[size];
        int i = 0;
        CarteTresor[] cArray = new CarteTresor[size];
        for(CarteTresor c: cartesTresor){
            cArray[i] = c;
            panel.add(new BoutonSelection(EncadreInventaireView.stringForInventory(c.getValeurCarte()), 80, 50, i));
            i++;
        }
        this.carteTmp = cArray;
        BoutonValiderController bvc = new BoutonValiderController("Valider",50, 20, 13, this);
        JPanel sub = new JPanel();
        bvc.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1,Color.BLACK));
        this.button = bvc;
        sub.add(bvc);
        panel.add(sub);
        panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        this.add(panel);
    }

    public Set<Player> getPlayerRes(){
        if(type == typeDeSelection.CLEF){
            throw new RuntimeException("Selection de clef");
        }
        return this.playersRes;
    }

    public typeDeSelection getType(){ return this.type; }

    public boolean hasButtonBeenPressed(){ return this.theButtonHasBeenPressed; }

    public void clear(){
        this.removeAll();
        this.repaint();
    }

    public void TheButtonHasBeenPRESSED(){
        if(this.type == typeDeSelection.JOUEUR){
            this.playersRes = new HashSet<Player>();
            for(Player p: this.playersToSelect){
                if(BoutonSelection.isSelectionned[p.getIdentifier()]){
                    playersRes.add(p);
                }
            }
            System.out.println(playersRes);
        }
        if(this.type == typeDeSelection.CLEF){
            this.carteRes = new HashSet<CarteTresor>();
            for(int i = 0; i < this.carteTmp.length; i++){
                if(BoutonSelection.isSelectionned[i]){
                    carteRes.add(carteTmp[i]);
                }
            }
            System.out.println(carteRes);
        }
        this.clear();
        theButtonHasBeenPressed = true;
    }
}
