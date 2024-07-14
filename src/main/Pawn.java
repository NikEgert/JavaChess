package main;

public class Pawn extends Piece {

    public Pawn(int x, int y, boolean colour, PieceUpdate pieceUpdate) {
        super(x, y, colour, pieceUpdate);
    }

    @Override
    public boolean canMove(int endX, int endY) {

        if (colour == true) {
            if (moveCount == 0 && endY == y - 2 && endX == x) {
                moveCount++;
                return true;
            }
            if (endY == y - 1 && endX == x) {
                moveCount++;
                return true;
            }
            if (endY == y - 1 && endX == x - 1 || endY == y - 1 && endX == x + 1) {
                Piece targetPiece = pieceUpdate.getPieceAt(endX, endY);
                if (targetPiece != null && targetPiece.getColour() != colour) {
                    return true; // takes piece
                }
            }

        } else {
            if (moveCount == 0 && endY == y + 2 && endX == x) {
                moveCount++;
                return true;
            }
            if (endY == y + 1 && endX == x) {
                moveCount++;
                return true;
            }
            if (endY == y + 1 && endX == x + 1 || endY == y + 1 && endX == x - 1) {
                Piece targetPiece = pieceUpdate.getPieceAt(endX, endY);
                if (targetPiece != null && targetPiece.getColour() != colour) {
                    return true; // takes piece
                }
            }
        }
        return false;
    }

    @Override
    public String getImageFileName() {
        return colour ? "src/assets/white/Pawn.png" : "src/assets/black/Pawn.png";
    }
}
