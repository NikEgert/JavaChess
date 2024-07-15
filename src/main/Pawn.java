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
                    return true;
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
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean check() {
        if (pieceUpdate.getPieceAt(x - 1, y - 1) != null) {
            Piece piece1 = pieceUpdate.getPieceAt(x - 1, y - 1);
            if (piece1 instanceof King) {
                King foundKing1 = (King) piece1;
                if (foundKing1.getColour() != colour) {
                    return true;
                }
            }
        }
        if (pieceUpdate.getPieceAt(x + 1, y - 1) != null) {
            Piece piece2 = pieceUpdate.getPieceAt(x - 1, y - 1);
            if (piece2 instanceof King) {
                King foundKing2 = (King) piece2;
                if (foundKing2.getColour() != colour) {
                    return true;
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
