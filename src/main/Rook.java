package main;
public class Rook extends Piece {

    public Rook(int x, int y, boolean colour, PieceUpdate pieceUpdate){
        super(x, y, colour, pieceUpdate);
    }

    public boolean canMove(int endX, int endY){
        return true;
    }
    
    @Override
    public String getImageFileName(){
        return colour ? "src/assets/white/Rook.png" : "src/assets/black/Rook.png";
    }
}
