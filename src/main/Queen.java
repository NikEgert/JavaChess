package main;
public class Queen extends Piece {

    public Queen(int x, int y, boolean colour, PieceUpdate pieceUpdate){
        super(x, y, colour, pieceUpdate);
    }

    @Override
    public boolean canMove(int endX, int endY){
        return true;
    }
    
    @Override
    public String getImageFileName(){
        return colour ? "src/assets/white/Queen.png" : "src/assets/black/Queen.png";
    }
}
