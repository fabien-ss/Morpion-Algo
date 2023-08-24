package objetaffichage;

import javax.swing.JButton;

public class Bouton extends JButton{
    
    int x;
    int y;
    
    public Bouton(int x, int y){
        this.setXX(x);
        this.setYY(y);
    }
    public void setXX(int x) {
        this.x = x;
    }
    public int getXX() {
        // TODO Auto-generated method stub
        return x;
    }
    public void setYY(int y) {
        this.y = y;
    }
    public int getYY() {
        // TODO Auto-generated method stub
        return y;
    }
}
