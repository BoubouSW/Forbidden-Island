package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class BoutonSelection extends IG.ZoneCliquable {
    public static boolean[] isSelectionned;
    public static int howMany;
    public static int goal;
    private int indice;

    public BoutonSelection(String str, int x, int y, int i){
        super(str, 100, 25,15);
        this.setSize(100, 25);
        this.indice = i;
        this.goal = goal;
    }

    public void clicDroit(){}
    public void clicGauche(){
        if(BoutonSelection.isSelectionned[this.indice]){
            BoutonSelection.isSelectionned[this.indice] = false;
            this.setBackground(Color.WHITE);
            howMany--;
        }else{
            BoutonSelection.isSelectionned[this.indice] = true;
            this.setBackground(Color.GRAY);
            howMany++;
        }
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    }

