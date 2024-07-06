package main;

public class PieceUpdate {
    Piece[][] grid;
    boolean turn;

    public PieceUpdate(){
        grid = new Piece[8][8];
    }

    public boolean getTurn(){
        return turn;
    }
    
    public void initialPositions() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                // Pawn
                if (i == 1) {
                    grid[i][j] = new Pawn(i, j, true);
                } else if (i == 6) {
                    grid[i][j] = new Pawn(i, j, false);
                }
            }
        }   
    }

    public Piece getPieceAt(int x, int y) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8) {
            return null;
        }
        return grid[x][y];
    }
}
