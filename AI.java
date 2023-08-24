package joueur;

import joueur.Joueur;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import jeu.*;
import objetaffichage.*;

public class AI extends Joueur{

    int niveau;

    public AI() throws Exception{
      //  this.setMoove(2);
    }
    public Morpion moove(Morpion morpion, Bouton[][] location)throws Exception{
        if(this.niveau == 1){
            System.out.println("Niveau 1");
            Morpion origine = new Morpion();
            origine.setBoard(morpion.deepCopyIntArray(morpion.getBoard()));
            morpion = this.parcourEnLarge(morpion);
            morpion = morpion.getFirstMove(origine.getBoard());
            morpion.fetchBoard(location);
            return morpion;
           // break;
        }
        else if(this.niveau == 2){
            // check other's position
            Joueur joueur = this;
            Joueur joueur2 = new Joueur();
            if(joueur.getMoove() == 2) joueur2.setMoove(1);
            else if(joueur.getMoove() == 1) joueur2.setMoove(2);

            if(morpion.blocIfWinning(joueur2, joueur)){
                System.out.println("Bloqued");
                morpion.fetchBoard(location);
                morpion.printBoard();
                return morpion;
            }
            else if(morpion.blocIfWinning(joueur2, joueur) == false){
                System.out.println("not bloqued");
                Morpion origine = new Morpion();
                origine.setBoard(Morpion.deepCopyIntArray(morpion.getBoard()));
                morpion = this.parcourEnLarge(morpion);
                morpion = morpion.getFirstMove(origine.getBoard());
                morpion.fetchBoard(location);
                return morpion;
            }        

            morpion.fetchBoard(location);
            
           // break;
        }
        else if(this.niveau == 3){
            System.out.println("Niveau 3");
            this.mooveMinMax(morpion, location);
           // break;
            return morpion;
        }
        return morpion;
    }
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
    public int getNiveau() {
        return niveau;
    }
    
    public void deplacementMoyen(){

    }
    
    public Morpion parcourEnLarge(Morpion morpion) throws Exception {

        Queue<Morpion> file = new LinkedList<>();
        HashSet<String> parcourues = new HashSet<>();
        Morpion racine = morpion;
        racine.setCout(0);
        
        file.add(racine);
        parcourues.add(racine.boardToString());

        while(!file.isEmpty()){
            Morpion lastmorpion = file.poll();
            List<Morpion> allPossibilities = lastmorpion.allPossibilities(this.moove);
            for ( Morpion m : allPossibilities){
                if(!parcourues.contains(m.boardToString())){
                    m.setCout(lastmorpion.getCout() + 1);
                    file.add(m);
                    m.setPrecedent(lastmorpion);
                    parcourues.add(m.boardToString());
                    if(m.win()){
                        System.out.println("finished");
                        m.printBoard();
                        return m;
                    }
                }
            }
        }
        throw new Exception("I give up");
    }
   
    public void mooveMinMax(Morpion morpion, Bouton[][] location) throws Exception {
        System.out.println("Minimax");
        int[][] matrice = morpion.getBoard();
        int joueurIA = this.moove; // L'IA est représentée par le joueur 2
        int meilleurScore = Integer.MIN_VALUE + 1; // Correction de l'initialisation du meilleur score
        System.out.println("min = "+meilleurScore);
      //  int meilleurScore = Integer.MIN_VALUE;
        int meilleurX = 0;
        int meilleurY = 0;
    
        for (int x = 0; x < matrice.length; x++) {
            for (int y = 0; y < matrice[x].length; y++) {
                if (matrice[x][y] == 0) {
                    matrice[x][y] = joueurIA; // Simulation du coup de l'IA
    
                    int score = minimax(matrice, 0, false);
    
                    matrice[x][y] = 0; // Annulation du coup de l'IA
    
                    if (score > meilleurScore) {
                        meilleurScore = score;
                        meilleurX = x;
                        meilleurY = y;
                    }
                }
            }
        }
        location[meilleurX][meilleurY].setText(morpion.getPions().get(String.valueOf(moove)));
        morpion.getBoard()[meilleurX][meilleurY] = this.getMoove();
    }
    
    private int minimax(int[][] matrice, int profondeur, boolean isMaximizingPlayer) {
        
        int joueurMax = this.moove; // Joueur maximisant (l'IA
        int joueurMin = 1; // Joueur minimisant (l'adversaire)
        if(joueurMax == 2) joueurMin = 1;
        else if(joueurMax == 1) joueurMin = 2;
    
        // Vérifier si le jeu est terminé ou si la profondeur maximale est atteinte
        if (jeuTermine(matrice) || profondeur == 5) {
            return evaluer(matrice); // Évaluer la position actuelle
        }
    
        if (isMaximizingPlayer) {
            int meilleurScore = Integer.MIN_VALUE;
    
            for (int x = 0; x < matrice.length; x++) {
                for (int y = 0; y < matrice[x].length; y++) {
                    if (matrice[x][y] == 0) {
                        matrice[x][y] = joueurMax;
    
                        int score = minimax(matrice, profondeur + 1, false);
    
                        matrice[x][y] = 0;
    
                        meilleurScore = Math.max(score, meilleurScore);
                    }
                }
            }
    
            return meilleurScore;
        } else {
            int meilleurScore = Integer.MAX_VALUE;
    
            for (int x = 0; x < matrice.length; x++) {
                for (int y = 0; y < matrice[x].length; y++) {
                    if (matrice[x][y] == 0) {
                        matrice[x][y] = joueurMin;
                        int score = minimax(matrice, profondeur + 1, true);
                        matrice[x][y] = 0;
                        meilleurScore = Math.min(score, meilleurScore);
                    }
                }
            }
    
            return meilleurScore;
        }
    }
    
    private boolean jeuTermine(int[][] matrice) {
        // Vérification des lignes
        for (int i = 0; i < 3; i++) {
            if (matrice[i][0] != 0 && matrice[i][0] == matrice[i][1] && matrice[i][0] == matrice[i][2]) {
                return true;
            }
        }
    
        // Vérification des colonnes
        for (int j = 0; j < 3; j++) {
            if (matrice[0][j] != 0 && matrice[0][j] == matrice[1][j] && matrice[0][j] == matrice[2][j]) {
                return true;
            }
        }
    
        // Vérification des diagonales
        if (matrice[0][0] != 0 && matrice[0][0] == matrice[1][1] && matrice[0][0] == matrice[2][2]) {
            return true;
        }
        if (matrice[2][0] != 0 && matrice[2][0] == matrice[1][1] && matrice[2][0] == matrice[0][2]) {
            return true;
        }
    
        // Vérification du match nul
        boolean matchNul = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrice[i][j] == 0) {
                    matchNul = false;
                    break;
                }
            }
            if (!matchNul) {
                break;
            }
        }
    
        if (matchNul) {
            return true;
        }
    
        return false;
    }
    
    private int evaluer(int[][] matrice) {
        // Valeur des lignes et des colonnes
        int[] valeurs = new int[8];
    
        // Évaluation des lignes
        for (int i = 0; i < 3; i++) {
            valeurs[i] = evaluerLigne(matrice[i][0], matrice[i][1], matrice[i][2]);
        }
    
        // Évaluation des colonnes
        for (int j = 0; j < 3; j++) {
            valeurs[j + 3] = evaluerLigne(matrice[0][j], matrice[1][j], matrice[2][j]);
        }
    
        // Évaluation des diagonales
        valeurs[6] = evaluerLigne(matrice[0][0], matrice[1][1], matrice[2][2]);
        valeurs[7] = evaluerLigne(matrice[2][0], matrice[1][1], matrice[0][2]);
    
        // Somme des valeurs
        int somme = 0;
        for (int valeur : valeurs) {
            somme += valeur;
        }
    
        return somme;
    }
    
    private int evaluerLigne(int cellule1, int cellule2, int cellule3) {
        int joueurMax = this.moove; // Joueur maximisant (l'IA
        int joueurMin = 1; // Joueur minimisant (l'adversaire)
        if(joueurMax == 2) joueurMin = 1;
        else if(joueurMax == 1) joueurMin = 2;
    
        if (cellule1 == joueurMax && cellule2 == joueurMax && cellule3 == joueurMax) {
            return 100;
        } else if (cellule1 == joueurMin && cellule2 == joueurMin && cellule3 == joueurMin) {
            return -100;
        } else {
            return 0;
        }
    }
}