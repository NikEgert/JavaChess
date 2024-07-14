package main;

public class Knight extends Piece {

    public Knight(int x, int y, boolean colour, PieceUpdate pieceUpdate) {
        super(x, y, colour, pieceUpdate);
    }

    public boolean canMove(int endX, int endY) {
        if (endY == y - 2 && endX == x + 1) {
            Piece targetPiece = pieceUpdate.getPieceAt(endX, endY);
            if (targetPiece == null || targetPiece.getColour() != colour) {
                return true;
            }
        }

        if (endY == y - 2 && endX == x - 1) {
            Piece targetPiece = pieceUpdate.getPieceAt(endX, endY);
            if (targetPiece == null || targetPiece.getColour() != colour) {
                return true;
            }
        }

        if (endY == y + 2 && endX == x + 1) {
            Piece targetPiece = pieceUpdate.getPieceAt(endX, endY);
            if (targetPiece == null || targetPiece.getColour() != colour) {
                return true;
            }
        }

        if (endY == y + 2 && endX == x - 1) {
            Piece targetPiece = pieceUpdate.getPieceAt(endX, endY);
            if (targetPiece == null || targetPiece.getColour() != colour) {
                return true;
            }
        }

        if (endY == y + 1 && endX == x + 2) {
            Piece targetPiece = pieceUpdate.getPieceAt(endX, endY);
            if (targetPiece == null || targetPiece.getColour() != colour) {
                return true;
            }
        }

        if (endY == y - 1 && endX == x + 2) {
            Piece targetPiece = pieceUpdate.getPieceAt(endX, endY);
            if (targetPiece == null || targetPiece.getColour() != colour) {
                return true;
            }
        }

        if (endY == y + 1 && endX == x - 2) {
            Piece targetPiece = pieceUpdate.getPieceAt(endX, endY);
            if (targetPiece == null || targetPiece.getColour() != colour) {
                return true;
            }
        }

        if (endY == y - 1 && endX == x - 2) {
            Piece targetPiece = pieceUpdate.getPieceAt(endX, endY);
            if (targetPiece == null || targetPiece.getColour() != colour) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getImageFileName() {
        return colour ? "src/assets/white/Knight.png" : "src/assets/black/Knight.png";
    }
}
