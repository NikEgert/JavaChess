package main;

public class Rook extends Piece {

    public Rook(int x, int y, boolean colour, PieceUpdate pieceUpdate) {
        super(x, y, colour, pieceUpdate);
    }

    public boolean canMove(int endX, int endY) {
        if (endX != x && endY == y) {
            Piece targetPiece = pieceUpdate.getPieceAt(endX, endY);
            return (targetPiece == null || targetPiece.getColour() != colour) &&
                    pieceUpdate.isHorizontalPathClear(x, y, endX);
        }

        if (endX == x && endY != y) {
            Piece targetPiece = pieceUpdate.getPieceAt(endX, endY);
            return (targetPiece == null || targetPiece.getColour() != colour) &&
                    pieceUpdate.isVerticalPathClear(x, y, endY);
        }
        return false;
    }

    @Override
    public String getImageFileName() {
        return colour ? "src/assets/white/Rook.png" : "src/assets/black/Rook.png";
    }
}
