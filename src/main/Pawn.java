package main;

public class Pawn extends Piece {

    public Pawn(int x, int y, boolean colour, PieceUpdate pieceUpdate) {
        super(x, y, colour, pieceUpdate);
    }

    @Override
    public boolean canMove(int endX, int endY) {

        if (colour == true) {
            if (moveCount == 0 && endY == y - 2 && endX == x) {
                if (pieceUpdate.getPieceAt(endX, endY) != null) {
                    return false;
                }
                moveCount++;
                return true;
            }
            if (endY == y - 1 && endX == x) {
                if (pieceUpdate.getPieceAt(endX, endY) != null) {
                    return false;
                }
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
                if (pieceUpdate.getPieceAt(endX, endY) != null) {
                    return false;
                }
                moveCount++;
                return true;
            }
            if (endY == y + 1 && endX == x) {
                if (pieceUpdate.getPieceAt(endX, endY) != null) {
                    return false;
                }
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
        if (colour) {
            if (x - 1 >= 0 && y - 1 >= 0) {
                Piece piece1 = pieceUpdate.getPieceAt(x - 1, y - 1);
                if (piece1 != null) {
                    if (piece1 instanceof King && piece1.getColour() != colour) {
                        return true;
                    }
                }
            }

            if (x + 1 < 8 && y - 1 >= 0) {
                Piece piece2 = pieceUpdate.getPieceAt(x + 1, y - 1);
                if (piece2 != null) {
                    if (piece2 instanceof King && piece2.getColour() != colour) {
                        return true;
                    }
                }
            }
        } else {
            if (x - 1 >= 0 && y + 1 < 8) {
                Piece piece3 = pieceUpdate.getPieceAt(x - 1, y + 1);
                if (piece3 != null) {
                    if (piece3 instanceof King && piece3.getColour() != colour) {
                        return true;
                    }
                }
            }

            if (x + 1 < 8 && y + 1 < 8) {
                Piece piece4 = pieceUpdate.getPieceAt(x + 1, y + 1);
                if (piece4 != null) {
                    if (piece4 instanceof King && piece4.getColour() != colour) {
                        return true;
                    }
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
