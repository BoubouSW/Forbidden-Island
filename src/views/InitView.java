package views;

import models.InitStart;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

/*
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
 */

public class InitView extends JFrame {
    private JSlider slider;
    private JSlider slider2;

    private int widthSetup = 800;
    private int heightSetup = 400;

    private ArrayList<String> names;
    private int waterlvl = 2;

    private ArrayList<JLabel> labels;
    private ArrayList<JTextField> texts;
    private JPanel namePlayers;
//resources/images/islandSetup.jpg
    public InitView() {
        super("Start");
        this.getContentPane().setBackground(new Color(100,149,237));
        // SI ON VEUT AJOUTER UN FOND
        /**
        JLabel background=new JLabel(new ImageIcon("resources/images/islandSetup.jpg"));

        add(background);
        **/
        //background.setLayout(new FlowLayout());

        this.pack();
    }

    public void drawWin() {
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setUpMenu(InitStart initStart) {
        setSize(widthSetup, heightSetup);

        // Add the elements for number of players
        JPanel nbPlayers = new JPanel();
        nbPlayers.setBackground(new Color(100,149,237));
        add(nbPlayers, BorderLayout.NORTH);

        slider = new JSlider(2, 4);
        slider.setValue(4);
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.setMinorTickSpacing(1);
        slider.setLabelTable(slider.createStandardLabels(1));

        // Add the name input for players
        namePlayers = new JPanel(new GridBagLayout());
        namePlayers.setBackground(new Color(100,149,237));
        texts = new ArrayList<JTextField>();
        labels = new ArrayList<JLabel>();
        for (int i = 0; i < 4; i++) {
            JLabel label = new JLabel("Nom joueur " + (i + 1) + " : ");
            JTextField text = new JTextField(10);
            labels.add(label);
            texts.add(text);

        }

        drawInName(slider.getValue());

        add(namePlayers, BorderLayout.CENTER);

        slider.addChangeListener(l -> {
            drawInName(slider.getValue());
        });

        JLabel playerNumber = new JLabel("Nombre de joueurs : ");
        playerNumber.setLabelFor(slider);

        nbPlayers.add(playerNumber);
        nbPlayers.add(slider);


        //add difficulty slider
        slider2 = new JSlider(1, 4);
        slider2.setMinorTickSpacing(1);
        slider2.setMajorTickSpacing(5);
        slider2.setPaintTicks(true);

        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(1, new JLabel("Noob"));
        labels.put(2, new JLabel("EZ"));
        labels.put(3, new JLabel("Hard"));
        labels.put(4, new JLabel("God"));
        slider2.setLabelTable(labels);
        slider2.setPaintLabels(true);

        slider2.addChangeListener(l -> {
            this.waterlvl = slider2.getValue();
        });

        JLabel difficulty = new JLabel("                 |                 Difficulté : ");
        difficulty.setLabelFor(slider2);

        nbPlayers.add(difficulty);
        nbPlayers.add(slider2);


        // Start button
        JPanel button = new JPanel();
        button.setBackground(new Color(100,149,237));

        add(button, BorderLayout.SOUTH);
        JButton start = new JButton("Jouer");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                names = new ArrayList<String>();
                for (int i = 0; i < slider.getValue(); i++) {
                    if (texts.get(i).getText().isEmpty()) {
                        texts.get(i).setText("J" + (i + 1));
                    }
                    names.add(texts.get(i).getText().toString());
                }
                closeWin();
                Thread run = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        initStart.initPlayer(names);
                        initStart.initWaterLvl(waterlvl);
                        initStart.run();
                    }
                });
                run.start();
            }
        });
        start.setBackground(new Color(30,144,255));
        button.add(start);
        /**
        try {
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("resources/images/IslandSetup.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }**/
    }

    private void drawInName(int nb) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        namePlayers.removeAll();
        for (int i = 0; i < nb; i++) {
            constraints.gridx = 0;
            constraints.gridy = i;
            namePlayers.add(labels.get(i), constraints);
            constraints.gridx = 1;
            namePlayers.add(texts.get(i), constraints);
        }
        namePlayers.repaint();
    }

    public void closeWin() {
        setVisible(false);
        dispose();
    }
}
/**
class ImagePanel extends JComponent {
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
**/