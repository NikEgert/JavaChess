package main;
public class King extends Piece {

    public King(int x, int y, Boolean colour, PieceUpdate pieceUpdate){
        super(x, y, colour, pieceUpdate);
    }

    @Override
    public boolean canMove(int endX, int endY){
        return true;
    }
    
    @Override
    public String getImageFileName(){
        return colour ? "src/assets/white/King.png" : "src/assets/black/King.png";
    }
}
