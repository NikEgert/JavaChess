package main;
public class Queen extends Piece {

    public Queen(int x, int y, boolean colour){
        super(x, y, colour);
    }

    public boolean canMove(int newX, int newY){
        return true;
    }
    
    @Override
    public String getImageFileName(){
        return colour ? "src/assets/white/Queen.png" : "src/assets/black/Queen.png";
    }
}
