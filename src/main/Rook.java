package main;
public class Rook extends Piece {

    public Rook(int x, int y, Boolean colour){
        super(x, y, colour);
    }

    public boolean canMove(int newX, int newY){
        return true;
    }
    
    @Override
    public String getImageFileName(){
        return colour ? "src/assets/white/Rook.png" : "src/assets/black/Rook.png";
    }
}
