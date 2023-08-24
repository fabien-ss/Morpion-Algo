package objetaffichage;

import javax.swing.*;

import objetaffichage.ChoixDifficulteWindow;

import java.awt.*;
import java.awt.event.*;

public class ChoixPionWindow extends JFrame {
    private String pionChoisi;

    public ChoixPionWindow() {
        super("Choix du pion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 150);
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Choisissez votre pion :");
        add(label);

        JButton buttonX = new JButton("X");
        buttonX.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    pionChoisi = "X";
                    ChoixDifficulteWindow difficulteWindow = new ChoixDifficulteWindow(1, 2);
                    dispose(); // Ferme la fenêtre de choix du pion              
                }
                catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
        add(buttonX);

       //     this.joueurs[1].moove(this.panel.getMorpion(), panel.getButtons()); 
        JButton buttonO = new JButton("O");
        buttonO.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    pionChoisi = "O";
                    ChoixDifficulteWindow difficulteWindow = new ChoixDifficulteWindow(2, 1);
                    dispose(); // Ferme la fenêtre de choix du pion
                }
                catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
        add(buttonO);

        setVisible(true);
    }

    public String getPionChoisi() {
        return pionChoisi;
    }

    public static void main(String[] args) {
        ChoixPionWindow choixPionWindow = new ChoixPionWindow();
        String pionChoisi = choixPionWindow.getPionChoisi();
        System.out.println("Pion choisi : " + pionChoisi);
    }
}
