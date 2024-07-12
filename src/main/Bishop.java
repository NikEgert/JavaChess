package main;
public class Bishop extends Piece {

    public Bishop(int x, int y, Boolean colour){
        super(x, y, colour);
    }

    public boolean canMove(int newX, int newY){
        return true;
    }

    @Override
    public String getImageFileName(){
        return colour ? "src/assets/white/B.png" : "src/assets/black/B.png";
    }
}
