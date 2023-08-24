package joueur;

import jeu.*;
import objetaffichage.*;

public class Joueur{

    String nom;
    int moove;
    int x;
    int y;

    public Morpion moove(Morpion morpion, Bouton[][] location) throws Exception{
        if(morpion.getBoard()[this.getX()][this.getY()] != 0) throw new Exception("Case déjà prise");
        morpion.getBoard()[this.x][this.y] = this.moove;
        location[this.x][this.y].setText(morpion.getPions().get(String.valueOf(moove)));
        return morpion;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Joueur(){}

    public Joueur(String nom, int moove) throws Exception{
        this.setNom(nom);
        this.setMoove(moove);
    }
    
    public void setXY(int x, int y){
        this.setX(x);
        this.setY(y);
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) throws Exception{
        if(nom == "") throw new Exception("Le nom ne peut pas être vide");
        this.nom = nom;
    }
    public int getMoove() {
        return moove;
    }
    public void setMoove(int moove) {
        this.moove = moove;
    }
}