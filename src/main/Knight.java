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
    public boolean check() {
        if (x + 1 < 8 && y - 2 >= 0 && pieceUpdate.getPieceAt(x + 1, y - 2) != null) {
            Piece piece1 = pieceUpdate.getPieceAt(x + 1, y - 2);
            if (piece1 instanceof King) {
                King foundKing1 = (King) piece1;
                if (foundKing1.getColour() != colour) {
                    return true;
                }
            }
        }

        if (x - 1 >= 0 && y - 2 >= 0 && pieceUpdate.getPieceAt(x - 1, y - 2) != null) {
            Piece piece1 = pieceUpdate.getPieceAt(x - 1, y - 2);
            if (piece1 instanceof King) {
                King foundKing1 = (King) piece1;
                if (foundKing1.getColour() != colour) {
                    return true;
                }
            }
        }

        if (x + 1 < 8 && y + 2 < 8 && pieceUpdate.getPieceAt(x + 1, y + 2) != null) {
            Piece piece1 = pieceUpdate.getPieceAt(x + 1, y + 2);
            if (piece1 instanceof King) {
                King foundKing1 = (King) piece1;
                if (foundKing1.getColour() != colour) {
                    return true;
                }
            }
        }

        if (x - 1 >= 0 && y + 2 < 8 && pieceUpdate.getPieceAt(x - 1, y + 2) != null) {
            Piece piece1 = pieceUpdate.getPieceAt(x - 1, y + 2);
            if (piece1 instanceof King) {
                King foundKing1 = (King) piece1;
                if (foundKing1.getColour() != colour) {
                    return true;
                }
            }
        }

        if (x + 2 < 8 && y + 1 < 8 && pieceUpdate.getPieceAt(x + 2, y + 1) != null) {
            Piece piece1 = pieceUpdate.getPieceAt(x + 2, y + 1);
            if (piece1 instanceof King) {
                King foundKing1 = (King) piece1;
                if (foundKing1.getColour() != colour) {
                    return true;
                }
            }
        }

        if (x + 2 < 8 && y - 1 >= 0 && pieceUpdate.getPieceAt(x + 2, y - 1) != null) {
            Piece piece1 = pieceUpdate.getPieceAt(x + 2, y - 1);
            if (piece1 instanceof King) {
                King foundKing1 = (King) piece1;
                if (foundKing1.getColour() != colour) {
                    return true;
                }
            }
        }

        if (x - 2 >= 0 && y + 1 < 8 && pieceUpdate.getPieceAt(x - 2, y + 1) != null) {
            Piece piece1 = pieceUpdate.getPieceAt(x - 2, y + 1);
            if (piece1 instanceof King) {
                King foundKing1 = (King) piece1;
                if (foundKing1.getColour() != colour) {
                    return true;
                }
            }
        }

        if (x - 2 >= 0 && y - 1 >= 0 && pieceUpdate.getPieceAt(x - 2, y - 1) != null) {
            Piece piece1 = pieceUpdate.getPieceAt(x - 2, y - 1);
            if (piece1 instanceof King) {
                King foundKing1 = (King) piece1;
                if (foundKing1.getColour() != colour) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public String getImageFileName() {
        return colour ? "src/assets/white/Knight.png" : "src/assets/black/Knight.png";
    }
}
