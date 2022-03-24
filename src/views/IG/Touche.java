package IG;
import javax.swing.*;
import java.awt.event.*;

public abstract class Touche extends JFrame implements KeyListener {
    JLabel label;
    JTextField text;

    public abstract void keyPressed(KeyEvent e);
    public abstract void keyReleased(KeyEvent e);
    public abstract void keyTyped(KeyEvent e);
}
