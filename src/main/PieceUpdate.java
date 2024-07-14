package main;

public class PieceUpdate {
    Piece[][] grid;

    public PieceUpdate() {
        grid = new Piece[8][8];
    }

    public boolean setPiece(int toX, int toY, Piece piece){
        if (piece != null) {
            if (piece.canMove(toX, toY)){
                grid[toX][toY] = piece;
                piece.setPosition(toX , toY); 
                return true;
            } else {
                System.out.println("Invalid move!");
            }
        }
        return false;
    }

    public void setOriginal(int originalX, int originalY, Piece piece){
        grid[originalX][originalY] = piece;
        piece.setPosition(originalX , originalY); 
    }

    public Piece erasePiece(int fromX, int fromY){
        Piece removedPiece = grid[fromX][fromY];
        grid[fromX][fromY] = null;
        return removedPiece;
    }

    public void initialPositions() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                // Pawn
                if (j == 1) {
                    grid[i][j] = new Pawn(i, j, false, this);

                } else if (j == 6) {
                    grid[i][j] = new Pawn(i, j, true, this);
                }

                // King
                if (j == 0 && i == 4){
                    grid[i][j] = new King(i, j, false, this);

                } else if (j == 7 && i == 4){
                    grid[i][j] = new King(i, j, true, this);
                }

                // Bishop
                if (j == 0 && i == 2 || j == 0 && i == 5){
                    grid[i][j] = new Bishop(i, j, false, this);

                } else if (j == 7 && i == 2 || j == 7 && i == 5){
                    grid[i][j] = new Bishop(i, j, true, this);
                }

                // Knight
                if (j == 0 && i == 1 || j == 0 && i == 6){
                    grid[i][j] = new Knight(i, j, false, this);

                } else if (j == 7 && i == 1 || j == 7 && i == 6){
                    grid[i][j] = new Knight(i, j, true, this);
                }

                // Rook
                if (j == 0 && i == 0 || j == 0 && i == 7){
                    grid[i][j] = new Rook(i, j, false, this);

                } else if (j == 7 && i == 0 || j == 7 && i == 7){
                    grid[i][j] = new Rook(i, j, true, this);
                }

                // Queen
                if (j == 0 && i == 3){
                    grid[i][j] = new Queen(i, j, false, this);

                } else if (j == 7 && i == 3){
                    grid[i][j] = new Queen(i, j, true, this);
                }
            }
        }
    }

    public Piece getPieceAt(int x, int y) {
        return grid[x][y];
    }

    public void printBoard() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                Piece piece = grid[j][i];
                if (piece != null) {
                    System.out.print(piece.toString() + " ");
                } else {
                    System.out.print("null ");
                }
            }
            System.out.println();
        }
    }

    public Piece[][] getGrid(){
        return grid;
    }

    public boolean isVerticalPathClear(int startX, int startY, int endY){
        int step = (startY < endY) ? 1 : -1;
        for (int y = startY; y <= endY; y += step){
            if (getPieceAt(startX, y) != null){
                return true;
            }
        }
        return false;
    }

    public boolean isHorizontalPathClear(int startX, int startY, int endX){
        int step = (startX < endX) ? 1 : -1;
        for (int x = startX; x <= endX; x += step){
            if (getPieceAt(x, startY) != null){
                return true;
            }
        }
        return false;
    }

    public boolean isDiagonalPathClear(int startX, int startY, int endY, int endX){
        int x = startX;
        int y = startY;
        while (x <= endX && y <= endY){
                if (getPieceAt(x, y) != null){
                    return true;
                }
            x++;
            y++;
        }
        return false;
    }
}
