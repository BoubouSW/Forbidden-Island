package views;

import javax.swing.*;
import java.awt.*;

public class AllInventoryView extends JPanel {
    public EncadreInventaireView[] inventoriesViews;
    public AllInventoryView(EncadreInventaireView[] invArray){
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setSize(300, 500);
        this.inventoriesViews = invArray;
        for(EncadreInventaireView EIV: invArray){
            this.add(EIV);
        }
    }
}
