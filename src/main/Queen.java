package main;

public class Queen extends Piece {

    public Queen(int x, int y, boolean colour, PieceUpdate pieceUpdate) {
        super(x, y, colour, pieceUpdate);
    }

    @Override
    public boolean canMove(int endX, int endY) {
        if (Math.abs(endX - x) == Math.abs(endY - y)) {
            Piece targetPiece = pieceUpdate.getPieceAt(endX, endY);
            return (targetPiece == null || targetPiece.getColour() != colour) &&
                    pieceUpdate.isDiagonalPathClear(x, y, endX, endY);
        }

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
        return colour ? "src/assets/white/Queen.png" : "src/assets/black/Queen.png";
    }
}
