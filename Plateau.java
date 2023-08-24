package objetaffichage;

import joueur.Joueur;
import jeu.Morpion;

import javax.swing.JFrame;

public class Plateau extends JFrame {
    
    Joueur joueur1;
    Joueur joueur2;

    public Plateau(Joueur joueur1, Joueur joueur2) {
        this.setJoueur1(joueur1);
        this.setJoueur2(joueur2);
        
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        
        Morpion morpion = new Morpion();
        TicTacToePanel panel = new TicTacToePanel();

        if(joueur1.getMoove() < joueur2.getMoove()){
            panel.setJoueurMoving(true);
        }
        
        panel.morpion = morpion;
        panel.joueurs[0] = joueur1;
        panel.joueurs[1] = joueur2;
        panel.initBoard();
        getContentPane().add(panel);
    }    

    public void setJoueur1(Joueur joueur1) {
        this.joueur1 = joueur1;
    }
    public void setJoueur2(Joueur joueur2) {
        this.joueur2 = joueur2;
    }
    public Joueur getJoueur1() {
        return joueur1;
    }
    public Joueur getJoueur2() {
        return joueur2;
    }
}
