package main;
public class Knight extends Piece {

    public Knight(int x, int y, boolean colour, PieceUpdate pieceUpdate){
        super(x, y, colour, pieceUpdate);
    }

    public boolean canMove(int endX, int endY){
        return true;
    }
    
    @Override
    public String getImageFileName(){
        return colour ? "src/assets/white/Knight.png" : "src/assets/black/Knight.png";
    }
}
