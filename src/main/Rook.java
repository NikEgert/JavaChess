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
    public boolean check() {
        for (int i = 1; i < 8; i++) {
            if (x - i < 0) {
                break;
            }
            if (pieceUpdate.getPieceAt(x - i, y) != null) {
                Piece piece = pieceUpdate.getPieceAt(x - i, y);
                if (piece instanceof King) {
                    King foundKing = (King) piece;
                    if (foundKing.getColour() != colour) {
                        return true;
                    }
                }
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            if (x + i > 7) {
                break;
            }
            if (pieceUpdate.getPieceAt(x + i, y) != null) {
                Piece piece = pieceUpdate.getPieceAt(x + i, y);
                if (piece instanceof King) {
                    King foundKing = (King) piece;
                    if (foundKing.getColour() != colour) {
                        return true;
                    }
                }
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            if (y - i < 0) {
                break;
            }
            if (pieceUpdate.getPieceAt(x, y - i) != null) {
                Piece piece = pieceUpdate.getPieceAt(x, y - i);
                if (piece instanceof King) {
                    King foundKing = (King) piece;
                    if (foundKing.getColour() != colour) {
                        return true;
                    }
                }
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            if (y + i > 7) {
                break;
            }
            if (pieceUpdate.getPieceAt(x, y + i) != null) {
                Piece piece = pieceUpdate.getPieceAt(x, y + i);
                if (piece instanceof King) {
                    King foundKing = (King) piece;
                    if (foundKing.getColour() != colour) {
                        return true;
                    }
                }
                break;
            }
        }
        return false;
    }

    @Override
    public String getImageFileName() {
        return colour ? "src/assets/white/Rook.png" : "src/assets/black/Rook.png";
    }
}
