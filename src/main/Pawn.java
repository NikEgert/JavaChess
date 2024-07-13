package main;
public class Pawn extends Piece {

    public Pawn(int x, int y, boolean colour){
        super(x, y, colour);
    }

    public boolean canMove(int newX, int newY, boolean colour){
        if (colour == true){
            if (moveCount == 0 && newY == y - 2 && newX == x){
                return true;
                }
            if (newY == y - 1 && newX == x){
                return true;
            }
        } else{
            if(moveCount == 0 && newY == y + 2 && newX == x){
                return true;
            }
            if (newY == y + 1 && newX == x){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String getImageFileName(){
        return colour ? "src/assets/white/Pawn.png" : "src/assets/black/Pawn.png";
    }
}
