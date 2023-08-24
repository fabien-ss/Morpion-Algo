package jeu;

import joueur.*;
import objetaffichage.*;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Morpion {
    
    int cout = 0;
    Morpion precedent;
    int[][] board = new int[3][3];
    HashMap<String, String> pions = new HashMap<String, String>();

    public boolean blocIfWinning(Joueur joueur, Joueur joueur2) {

        int point1 = joueur.getMoove(); // point du joueur
        int point2 = joueur2.getMoove(); // point du joueur2

        for (int row = 0; row < 3; row++) {
            if (board[row][0] == point1 && board[row][1] == point1 && board[row][2] == 0) {
                board[row][2] = joueur2.getMoove();
                return true; 
            } else if (board[row][0] == point1 && board[row][2] == point1 && board[row][1] == 0) {
                board[row][1] = joueur2.getMoove();
                return true; 
            } else if (board[row][1] == point1 && board[row][2] == point1 && board[row][0] == 0) {
                board[row][0] = joueur2.getMoove();
                return true; 
            }
        }

        for (int col = 0; col < 3; col++) {
            if (board[0][col] == point1 && board[1][col] == point1 && board[2][col] == 0) {
                board[2][col] = joueur2.getMoove();
                return true; 
            } else if (board[0][col] == point1 && board[2][col] == point1 && board[1][col] == 0) {
                board[1][col] = joueur2.getMoove();
                return true; 
            } else if (board[1][col] == point1 && board[2][col] == point1 && board[0][col] == 0) {
                board[0][col] = joueur2.getMoove();
                return true; 
            }
        }

        if (board[0][0] == point1 && board[1][1] == point1 && board[2][2] == 0) {
            board[2][2] = joueur2.getMoove();
            return true; 
        } else if (board[0][0] == point1 && board[2][2] == point1 && board[1][1] == 0) {
            board[1][1] = joueur2.getMoove();
            return true; 
        } else if (board[1][1] == point1 && board[2][2] == point1 && board[0][0] == 0) {
            board[0][0] = joueur2.getMoove();
            return true; 
        } else if (board[0][2] == point1 && board[1][1] == point1 && board[2][0] == 0) {
            board[2][0] = joueur2.getMoove();
            return true; 
        } else if (board[0][2] == point1 && board[2][0] == point1 && board[1][1] == 0) {
            board[1][1] = joueur2.getMoove();
            return true; 
        } else if (board[1][1] == point1 && board[2][0] == point1 && board[0][2] == 0) {
            board[0][2] = joueur2.getMoove();
            return true; 
        }
        return false; 
    }

    public void setCout(int cout) {
        this.cout = cout;
    }
    public int getCout() {
        return cout;
    }
    public Morpion(){
        initHashMap();
        init_board();
    }
    public String boardToString(){
        String retour = "";
        for(int i=0; i<this.getBoard().length; i++){
            for(int j=0; j<this.getBoard()[0].length; j++){
                retour += this.getBoard()[i][j];
            }
        }
        return retour;
    }

    public Morpion getFirstMove(int[][] origine) {
        Morpion racine = this;
        while(racine.getCout() != 1){
            racine = racine.precedent;
        }
        return racine;
    }

    public static boolean compareArray(int[][] array1, int[][] array2){
        for(int i = 0; i < array1.length; i++){
            for(int j = 0; j < array2.length; j++){
                if(array1[i][j] != array2[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public void fetchBoard(Bouton[][] boardb){
        for(int i=0; i<this.getBoard().length; i++){
            for(int j=0; j<this.getBoard()[0].length; j++){
                boardb[i][j].setText(this.pions.get(String.valueOf(this.board[i][j])));
            }
        }
    }

    public boolean win(){
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                return true;
            }
        }
    
        // Vérification des colonnes
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != 0 && board[0][j] == board[1][j] && board[0][j] == board[2][j]) {
                return true;
            }
        }
    
        // Vérification des diagonales
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return true;
        }
        if (board[2][0] != 0 && board[2][0] == board[1][1] && board[2][0] == board[0][2]) {
            return true;
        }
        return false;
    }
    
    public List<Morpion> allPossibilities(int identity){
        List<Morpion> list = new ArrayList<Morpion>();
        for(int i=0; i<this.getBoard().length; i++){
            for(int j=0; j<this.getBoard()[0].length; j++){
                if(this.getBoard()[i][j] == 0){
                    Morpion morpion = new Morpion();
                    morpion.board = deepCopyIntArray(this.getBoard());
                    morpion.getBoard()[i][j] = identity;
                    list.add(morpion);
                }
            }
        }
        return list;
    }

    public static int[][] deepCopyIntArray(int[][] array) {
        if (array == null) {
            return null;
        }
    
        int[][] copy = new int[array.length][];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i].clone();
        }
    
        return copy;
    }
    
    public int[][] getBoard() {
        return board;
    }
    public void init_board(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                this.board[i][j] = 0;
            }
        }
    }
    public void initHashMap(){
        this.pions.put("1", "X");
        this.pions.put("2", "O");
        this.pions.put("0", "");
    }
    public void setPions(HashMap<String, String> pions) {
        this.pions = pions;
    }
    public HashMap<String, String> getPions() {
        return pions;
    }
    public void printBoard(){
        for(int i = 0; i < 3; i++){
            System.out.println("");
            for(int j = 0; j < 3; j++){
                System.out.print(this.board[i][j] + "-");
            }
            System.out.println("");
        }
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
    public void setPrecedent(Morpion precedent) {
        this.precedent = precedent;
    }
    public Morpion getPrecedent() {
        return precedent;
    }
}
