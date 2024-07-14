package main;
public class Bishop extends Piece {

    public Bishop(int x, int y, Boolean colour, PieceUpdate pieceUpdate){
        super(x, y, colour, pieceUpdate);
    }

    @Override
    public boolean canMove(int endX, int endY){
        return true;
    }

    @Override
    public String getImageFileName(){
        return colour ? "src/assets/white/Bishop.png" : "src/assets/black/Bishop.png";
    }
}
