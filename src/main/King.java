package main;
public class King extends Piece {

    public King(int x, int y, Boolean colour){
        super(x, y, colour);
    }

    public boolean canMove(int newX, int newY){
        return true;
    }
    
    @Override
    public String getImageFileName(){
        return colour ? "src/assets/white/Ki.png" : "src/assets/black/Ki.png";
    }
}
