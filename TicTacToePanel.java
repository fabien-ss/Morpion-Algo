package objetaffichage;

import jeu.*;
import joueur.*;
import listener.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TicTacToePanel extends JPanel {
    
    Bouton[][] buttons;
    Morpion morpion;
    Joueur[] joueurs = new Joueur[2];
    boolean joueurMoving; //comme
    JLabel error = new JLabel();

    public TicTacToePanel() {
        this.joueurMoving = false;
    }
    public void initBoard() {
        setLayout(new GridLayout(4, 3));
        buttons = new Bouton[3][3];
    
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new Bouton(row, col);
                buttons[row][col].setBackground(new Color(52, 152, 219));
                buttons[row][col].setForeground(Color.WHITE);
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 40));
                buttons[row][col].setFocusPainted(false);
    
                Mouse mouse = new Mouse(this);
                mouse.setJoueurs(this.getJoueurs());
                mouse.setBouton(buttons[row][col]);
                buttons[row][col].addMouseListener(mouse);
    
                add(buttons[row][col]);
            }
        }
    
        Bouton ia = new Bouton(4, 4);
        ia.setText("Play");
        ia.setFont(new Font("Arial", Font.BOLD, 16));
    
        Mouse mouse = new Mouse(this);
        mouse.setBouton(ia);
        mouse.setJoueurs(this.getJoueurs());
        ia.addMouseListener(mouse);
    
        this.error.setPreferredSize(new Dimension(200, 30));
    
        add(ia);
        add(this.error);
    }
    
    public Bouton[][] getButtons() {
        return buttons;
    }
    public void setButtons(Bouton[][] buttons) {
        this.buttons = buttons;
    }
    public Morpion getMorpion() {
        return morpion;
    }
    public void setMorpion(Morpion morpion) {
        this.morpion = morpion;
    }
    public Joueur[] getJoueurs() {
        return joueurs;
    }
    public void setJoueurs(Joueur[] joueurs) {
        this.joueurs = joueurs;
    }
    public JLabel getError() {
        return error;
    }
    public void setError(JLabel error) {
        this.error = error;
    }
    public void setJoueurMoving(boolean joueurMoving) {
        this.joueurMoving = joueurMoving;
    }
    public boolean isJoueurMoving() {
        return joueurMoving;
    }
}
