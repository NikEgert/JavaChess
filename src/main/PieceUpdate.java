package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PieceUpdate {
    Piece[][] grid;
    SoundPlayer sound;

    public PieceUpdate() {
        grid = new Piece[8][8];
        sound = new SoundPlayer();
    }

    public Piece checkCheck(Piece piece) {
        if (piece.check()) {
            sound.checkSound();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] instanceof King) {
                        King king = (King) grid[i][j];
                        if (king.getColour() != piece.getColour()) {
                            return king;
                        }
                    }
                }
            }
        }
        return null;
    }

    public boolean setPiece(int toX, int toY, Piece piece) {
        if (piece == null) {
            return false;
        }

        if (piece instanceof King) {
            if (isSquareThreatened(toX, toY, piece.getColour())) {
                return false;
            }
        }

        if (piece.canMove(toX, toY)) {
            if (getPieceAt(toX, toY) != null) {
                if (getPieceAt(toX, toY) instanceof King) {
                    return false;
                } else {
                    sound.takeSound();
                }
            }
            grid[toX][toY] = piece;
            piece.setPosition(toX, toY);
            sound.moveSound();
            return true;
        } else {
            System.out.println("Invalid move!");
        }

        return false;
    }

    public boolean checkMate() {
        sound.mateSound();
        return true;
    }

    public boolean isSquareThreatened(int x, int y, boolean kingColour) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = grid[i][j];
                if (piece != null && piece.getColour() != kingColour) {
                    if (piece instanceof Pawn) {
                        int direction = kingColour ? 1 : -1;
                        if ((i + direction == x && j + 1 == y) || (i + direction == x && j - 1 == y)) {
                            return true;
                        }
                    } else if (piece.canMove(x, y)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<int[]> getThreatenedSquares(int kingX, int kingY, boolean kingColour) {
        List<int[]> threatenedSquares = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = grid[i][j];
                if (piece != null && piece.getColour() != kingColour) {
                    if (piece instanceof Pawn) {
                        int direction = kingColour ? 1 : -1;
                        if ((i + direction >= 0 && i + direction < 8) &&
                                ((j + 1 < 8 && i + direction == kingX && j + 1 == kingY) ||
                                        (j - 1 >= 0 && i + direction == kingX && j - 1 == kingY))) {
                            threatenedSquares.add(new int[] { kingX, kingY });
                        }
                    } else if (piece instanceof Rook || piece instanceof Queen) {
                        addThreatsInDirection(piece, kingX, kingY, threatenedSquares, 1, 0); // Right
                        addThreatsInDirection(piece, kingX, kingY, threatenedSquares, -1, 0); // Left
                        addThreatsInDirection(piece, kingX, kingY, threatenedSquares, 0, 1); // Up
                        addThreatsInDirection(piece, kingX, kingY, threatenedSquares, 0, -1); // Down
                    }
                    if (piece instanceof Bishop || piece instanceof Queen) {
                        addThreatsInDirection(piece, kingX, kingY, threatenedSquares, 1, 1); // Up-right
                        addThreatsInDirection(piece, kingX, kingY, threatenedSquares, 1, -1); // Up-left
                        addThreatsInDirection(piece, kingX, kingY, threatenedSquares, -1, 1); // Down-right
                        addThreatsInDirection(piece, kingX, kingY, threatenedSquares, -1, -1); // Down-left
                    }
                    if (piece instanceof Knight) {
                        int[][] knightMoves = {
                                { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 },
                                { 1, 2 }, { 1, -2 }, { -1, 2 }, { -1, -2 }
                        };
                        for (int[] move : knightMoves) {
                            int endX = kingX + move[0];
                            int endY = kingY + move[1];
                            if (endX >= 0 && endX < 8 && endY >= 0 && endY < 8) {
                                threatenedSquares.add(new int[] { endX, endY });
                            }
                        }
                    }
                }
            }
        }

        Set<String> uniqueThreatenedSquares = new HashSet<>();
        for (int[] square : threatenedSquares) {
            uniqueThreatenedSquares.add(square[0] + "," + square[1]);
        }
        return uniqueThreatenedSquares.stream()
                .map(s -> s.split(","))
                .map(a -> new int[] { Integer.parseInt(a[0]), Integer.parseInt(a[1]) })
                .collect(Collectors.toList());
    }

    private void addThreatsInDirection(Piece piece, int kingX, int kingY, List<int[]> threatenedSquares, int dx,
            int dy) {
        int x = kingX;
        int y = kingY;

        while (true) {
            x += dx;
            y += dy;

            if (x < 0 || x >= 8 || y < 0 || y >= 8)
                break;

            threatenedSquares.add(new int[] { x, y });

            Piece targetPiece = grid[x][y];
            if (targetPiece != null) {
                break;
            }
        }
    }

    public List<Piece> getBlockingPieces(Piece king) {
        List<int[]> threatenedSquares = getThreatenedSquares(king.getX(), king.getY(), king.getColour());
        List<Piece> blockingPieces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = grid[i][j];
                if (piece != null && piece.getColour() == king.getColour() && piece != king) {
                    for (int[] square : threatenedSquares) {
                        int x = square[0];
                        int y = square[1];
                        if (piece.canMove(x, y)) {
                            blockingPieces.add(piece);
                            break;
                        }
                    }
                }
            }
        }
        return blockingPieces;
    }

    public boolean canMoveAroundPiece(Piece piece) {
        if (!(piece instanceof King)) {
            return false;
        }

        int x = piece.getX();
        int y = piece.getY();
        boolean pieceColour = piece.getColour();
        boolean canMove = false;

        // Get all threatened squares
        List<int[]> threatenedSquares = getThreatenedSquares(x, y, pieceColour);
        Set<String> threatenedSquaresSet = new HashSet<>();

        // Convert list of threatened squares to a set for faster lookup
        for (int[] square : threatenedSquares) {
            threatenedSquaresSet.add(square[0] + "," + square[1]);
        }

        // Print the contents of the threatenedSquaresSet
        System.out.println("Threatened squares:");
        for (String square : threatenedSquaresSet) {
            System.out.println(square);
        }

        for (int offsetX = -1; offsetX <= 1; offsetX++) {
            for (int offsetY = -1; offsetY <= 1; offsetY++) {
                if (offsetX == 0 && offsetY == 0) {
                    continue;
                }

                int endX = x + offsetX;
                int endY = y + offsetY;

                // Check if the target position is within bounds
                if (endX >= 0 && endX < 8 && endY >= 0 && endY < 8) {
                    // Check if the square is not in the threatened squares set and the king can
                    // move there
                    if (!threatenedSquaresSet.contains(endX + "," + endY) && piece.canMove(endX, endY)) {
                        canMove = true;
                        System.out.println("King can move to: (" + endX + ", " + endY + ")");
                    }
                }
            }
        }

        return canMove;
    }

    public void setOriginal(int originalX, int originalY, Piece piece) {
        grid[originalX][originalY] = piece;
        piece.setPosition(originalX, originalY);
    }

    public Piece erasePiece(int fromX, int fromY) {
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
                if (j == 0 && i == 4) {
                    grid[i][j] = new King(i, j, false, this);

                } else if (j == 7 && i == 4) {
                    grid[i][j] = new King(i, j, true, this);
                }

                // Bishop
                if (j == 0 && i == 2 || j == 0 && i == 5) {
                    grid[i][j] = new Bishop(i, j, false, this);

                } else if (j == 7 && i == 2 || j == 7 && i == 5) {
                    grid[i][j] = new Bishop(i, j, true, this);
                }

                // Knight
                if (j == 0 && i == 1 || j == 0 && i == 6) {
                    grid[i][j] = new Knight(i, j, false, this);

                } else if (j == 7 && i == 1 || j == 7 && i == 6) {
                    grid[i][j] = new Knight(i, j, true, this);
                }

                // Rook
                if (j == 0 && i == 0 || j == 0 && i == 7) {
                    grid[i][j] = new Rook(i, j, false, this);

                } else if (j == 7 && i == 0 || j == 7 && i == 7) {
                    grid[i][j] = new Rook(i, j, true, this);
                }

                // Queen
                if (j == 0 && i == 3) {
                    grid[i][j] = new Queen(i, j, false, this);

                } else if (j == 7 && i == 3) {
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

    public Piece[][] getGrid() {
        return grid;
    }

    public boolean isVerticalPathClear(int startX, int startY, int endY) {
        int step = (startY < endY) ? 1 : -1;
        for (int y = startY + step; y != endY; y += step) {
            if (getPieceAt(startX, y) != null) {
                return false;
            }
        }
        return true;
    }

    public boolean isHorizontalPathClear(int startX, int startY, int endX) {
        int step = (startX < endX) ? 1 : -1;
        for (int x = startX + step; x != endX; x += step) {
            if (getPieceAt(x, startY) != null) {
                return false;
            }
        }
        return true;
    }

    public boolean isDiagonalPathClear(int startX, int startY, int endX, int endY) {
        int xDirection = (endX > startX) ? 1 : -1;
        int yDirection = (endY > startY) ? 1 : -1;
        int x = startX + xDirection;
        int y = startY + yDirection;

        while (x != endX && y != endY) {
            if (getPieceAt(x, y) != null) {
                return false;
            }
            x += xDirection;
            y += yDirection;
        }
        return true;
    }

}
