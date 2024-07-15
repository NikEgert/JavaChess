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
    public boolean check() {
        for (int i = 1; i < 8; i++) {
            if (x - i < 0 || y - i < 0) {
                break;
            }
            if (pieceUpdate.getPieceAt(x - i, y - i) != null) {
                Piece piece = pieceUpdate.getPieceAt(x - i, y - i);
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
            if (x - i < 0 || y + i > 7) {
                break;
            }
            if (pieceUpdate.getPieceAt(x - i, y + i) != null) {
                Piece piece = pieceUpdate.getPieceAt(x - i, y + i);
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
            if (x + i > 7 || y - i < 0) {
                break;
            }
            if (pieceUpdate.getPieceAt(x + i, y - i) != null) {
                Piece piece = pieceUpdate.getPieceAt(x + i, y - i);
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
            if (x + i > 7 || y + i > 7) {
                break;
            }
            if (pieceUpdate.getPieceAt(x + i, y + i) != null) {
                Piece piece = pieceUpdate.getPieceAt(x + i, y + i);
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
        return colour ? "src/assets/white/Bishop.png" : "src/assets/black/Bishop.png";
    }
}
