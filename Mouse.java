package listener;

import joueur.*;
import objetaffichage.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {

    Bouton bouton;
    Joueur[] joueurs = new Joueur[2];
    Integer indiceJoueur;
    TicTacToePanel panel;

    public Mouse(TicTacToePanel panel){
        this.panel = panel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        this.panel.getError().setText("Check");
        int tour = 0;

        if(this.panel.isJoueurMoving()) tour = 0;
        else if(!this.panel.isJoueurMoving()) tour = 1;

        int x = this.getBouton().getXX();
        int y = this.getBouton().getYY();
        this.joueurs[tour].setX(x);
        this.joueurs[tour].setY(y);
        
        try{
            if(this.panel.getMorpion().win()) throw new Exception("We got a winner"); 
            this.panel.setMorpion(this.joueurs[tour].moove(this.panel.getMorpion(), panel.getButtons()));
            this.panel.getMorpion().printBoard();
            if(this.panel.isJoueurMoving()) this.panel.setJoueurMoving(false);
            else if(!this.panel.isJoueurMoving()) this.panel.setJoueurMoving(true);        
            if(this.panel.getMorpion().win()) throw new Exception("We got a winner");  
        }
        catch(Exception error){
            this.panel.getError().setText(error.getMessage());
        }
    }

    public Joueur[] getJoueurs() {
        return joueurs;
    }
    public void setJoueurs(Joueur[] joueurs) {
        this.joueurs = joueurs;
    }
    public Integer getIndiceJoueur() {
        return indiceJoueur;
    }
    public void setIndiceJoueur(Integer indiceJoueur) {
        this.indiceJoueur = indiceJoueur;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // Handle mouse press event
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Handle mouse release event
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Handle mouse enter event
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Handle mouse exit event
    }

    public void setBouton(Bouton bouton) {
        this.bouton = bouton;
    }
    public Bouton getBouton() {
        return bouton;
    }
    public void setPanel(TicTacToePanel panel) {
        this.panel = panel;
    }
    public TicTacToePanel getPanel() {
        return panel;
    }
}
