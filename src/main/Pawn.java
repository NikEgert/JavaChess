package main;
public class Pawn extends Piece {

    public Pawn(int x, int y, Boolean colour){
        super(x, y, colour);
    }

    public boolean canMove(int newX, int newY){
        return true;
    }
    
    @Override
    public String getImageFileName(){
        return colour ? "white/P.png" : "black/P.png";
    }
}
