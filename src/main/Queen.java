package main;
public class Queen extends Piece {

    public Queen(int x, int y, Boolean colour){
        super(x, y, colour);
    }

    public boolean canMove(int newX, int newY){
        return true;
    }
    
    @Override
    public String getImageFileName(){
        return colour ? "src/assets/white/Q.png" : "src/assets/black/Q.png";
    }
}