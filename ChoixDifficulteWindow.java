package objetaffichage;

import joueur.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChoixDifficulteWindow extends JFrame {
    private String difficulteChoisie;

    public ChoixDifficulteWindow(int choix, int IA) throws Exception{
        super("Choix de la difficulté");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 150);
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Choisissez la difficulté :");
        add(label);

        JButton buttonFacile = new JButton("Facile");
        buttonFacile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    difficulteChoisie = "Facile";
                    Joueur j1 = new Joueur("Fabien", choix);
                    AI j2 = new AI();
                    j2.setMoove(IA);
                    j2.setNiveau(1);
                    Plateau plateau = new Plateau(j1, j2);
                    plateau.setVisible(true);
                    dispose(); 
                }
                catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
                dispose();
            }
        });
        add(buttonFacile);

        JButton buttonMoyen = new JButton("Moyen");
        buttonMoyen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    difficulteChoisie = "Moyen";
                    Joueur j1 = new Joueur("Fabien", choix);
                    AI j2 = new AI();
                    j2.setMoove(IA);
                    j2.setNiveau(2);
                    Plateau plateau = new Plateau(j1, j2);
                    plateau.setVisible(true);
                    dispose(); // Ferme la fenêtre de choix de difficulté
                }
                catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
              //  dispose(); // Ferme la fenêtre de choix de difficulté
            }
        });
        add(buttonMoyen);

        JButton buttonDifficile = new JButton("Difficile");
        buttonDifficile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    difficulteChoisie = "Difficile";
                    Joueur j1 = new Joueur("Fabien", choix);
                    AI j2 = new AI();
                    j2.setMoove(IA);
                    j2.setNiveau(3);
                    Plateau plateau = new Plateau(j1, j2);
                    plateau.setVisible(true);
                    dispose(); // Ferme la fenêtre de choix de difficulté
                }
                catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
        add(buttonDifficile);

        setVisible(true);
    }

    public String getDifficulteChoisie() {
        return difficulteChoisie;
    }

    public static void main(String[] args) {
       // ChoixDifficulteWindow choixDifficulteWindow = new ChoixDifficulteWindow();
        //String difficulteChoisie = choixDifficulteWindow.getDifficulteChoisie();
        //System.out.println("Difficulté choisie : " + difficulteChoisie);
    }
}
