package main;
public class Knight extends Piece {

    public Knight(int x, int y, Boolean colour){
        super(x, y, colour);
    }

    public boolean canMove(int newX, int newY){
        return true;
    }
    
    @Override
    public String getImageFileName(){
        return colour ? "src/assets/white/Knight.png" : "src/assets/black/Knight.png";
    }
}
