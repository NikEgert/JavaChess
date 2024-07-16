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
    public boolean check() {
        for (int i = 1; x - i >= 0; i++) {
            Piece piece = pieceUpdate.getPieceAt(x - i, y);
            if (piece != null) {
                if (piece instanceof King && piece.getColour() != colour) {
                    return true;
                }
                break;
            }
        }

        for (int i = 1; x + i <= 7; i++) {
            Piece piece = pieceUpdate.getPieceAt(x + i, y);
            if (piece != null) {
                if (piece instanceof King && piece.getColour() != colour) {
                    return true;
                }
                break;
            }
        }

        for (int i = 1; y - i >= 0; i++) {
            Piece piece = pieceUpdate.getPieceAt(x, y - i);
            if (piece != null) {
                if (piece instanceof King && piece.getColour() != colour) {
                    return true;
                }
                break;
            }
        }

        for (int i = 1; y + i <= 7; i++) {
            Piece piece = pieceUpdate.getPieceAt(x, y + i);
            if (piece != null) {
                if (piece instanceof King && piece.getColour() != colour) {
                    return true;
                }
                break;
            }
        }

        for (int i = 1; x - i >= 0 && y - i >= 0; i++) {
            Piece piece = pieceUpdate.getPieceAt(x - i, y - i);
            if (piece != null) {
                if (piece instanceof King && piece.getColour() != colour) {
                    return true;
                }
                break;
            }
        }

        for (int i = 1; x - i >= 0 && y + i <= 7; i++) {
            Piece piece = pieceUpdate.getPieceAt(x - i, y + i);
            if (piece != null) {
                if (piece instanceof King && piece.getColour() != colour) {
                    return true;
                }
                break;
            }
        }

        for (int i = 1; x + i <= 7 && y - i >= 0; i++) {
            Piece piece = pieceUpdate.getPieceAt(x + i, y - i);
            if (piece != null) {
                if (piece instanceof King && piece.getColour() != colour) {
                    return true;
                }
                break;
            }
        }

        for (int i = 1; x + i <= 7 && y + i <= 7; i++) {
            Piece piece = pieceUpdate.getPieceAt(x + i, y + i);
            if (piece != null) {
                if (piece instanceof King && piece.getColour() != colour) {
                    return true;
                }
                break;
            }
        }

        return false;
    }

    @Override
    public String getImageFileName() {
        return colour ? "src/assets/white/Queen.png" : "src/assets/black/Queen.png";
    }
}
