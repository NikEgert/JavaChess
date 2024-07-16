package main;

public class King extends Piece {

    public King(int x, int y, Boolean colour, PieceUpdate pieceUpdate) {
        super(x, y, colour, pieceUpdate);
    }

    @Override
    public boolean canMove(int endX, int endY) {
        if ((endX != x || endY != y) && Math.abs(endX - x) <= 1 && Math.abs(endY - y) <= 1) {
            Piece targetPiece = pieceUpdate.getPieceAt(endX, endY);
            if (targetPiece == null || targetPiece.getColour() != colour) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean check() {
        return false;
    }

    @Override
    public String getImageFileName() {
        return colour ? "src/assets/white/King.png" : "src/assets/black/King.png";
    }

}
