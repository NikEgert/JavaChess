package main;

public class Bishop extends Piece {

    public Bishop(int x, int y, Boolean colour, PieceUpdate pieceUpdate) {
        super(x, y, colour, pieceUpdate);
    }

    @Override
    public boolean canMove(int endX, int endY) {
        if (Math.abs(endX - x) == Math.abs(endY - y)) {
            Piece targetPiece = pieceUpdate.getPieceAt(endX, endY);
            if (targetPiece == null || targetPiece.getColour() != colour) {
                return pieceUpdate.isDiagonalPathClear(x, y, endX, endY);
            }
        }

        return false;
    }

    @Override
    public String getImageFileName() {
        return colour ? "src/assets/white/Bishop.png" : "src/assets/black/Bishop.png";
    }
}
