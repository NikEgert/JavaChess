package main;

import java.util.ArrayList;
import java.util.List;

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
                        if (i + direction >= 0 && i + direction < 8) {
                            if ((j + 1 < 8 && i + direction == kingX && j + 1 == kingY) ||
                                    (j - 1 >= 0 && i + direction == kingX && j - 1 == kingY)) {
                                threatenedSquares.add(new int[] { kingX, kingY });
                            }
                        }
                    } else if (piece.canMove(kingX, kingY)) {
                        // Handle other piece threats (queen, bishop, rook)
                        // THIS ISNT WORKING PROPERLY YET
                        int deltaX = Integer.signum(kingX - i);
                        int deltaY = Integer.signum(kingY - j);
                        int x = i + deltaX;
                        int y = j + deltaY;

                        while (x != kingX || y != kingY) {
                            threatenedSquares.add(new int[] { x, y });
                            x += deltaX;
                            y += deltaY;
                        }
                        threatenedSquares.add(new int[] { kingX, kingY });
                    }
                }
            }
        }

        return threatenedSquares;
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
        System.out.println(x + "," + y);
        boolean pieceColour = piece.getColour();
        boolean canMove = false;

        for (int offsetX = -1; offsetX <= 1; offsetX++) {
            for (int offsetY = -1; offsetY <= 1; offsetY++) {
                if (offsetX == 0 && offsetY == 0) {
                    continue;
                }

                int endX = x + offsetX;
                int endY = y + offsetY;

                if (endX >= 0 && endX < 8 && endY >= 0 && endY < 8) {
                    if (!isSquareThreatened(endX, endY, pieceColour)) {
                        if (piece.canMove(endX, endY)) {
                            canMove = true;
                            System.out.println("King can move to: (" + endX + ", " + endY + ")");
                        }
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

                // // Bishop
                // if (j == 0 && i == 2 || j == 0 && i == 5) {
                // grid[i][j] = new Bishop(i, j, false, this);

                // } else if (j == 7 && i == 2 || j == 7 && i == 5) {
                // grid[i][j] = new Bishop(i, j, true, this);
                // }

                // // Knight
                // if (j == 0 && i == 1 || j == 0 && i == 6) {
                // grid[i][j] = new Knight(i, j, false, this);

                // } else if (j == 7 && i == 1 || j == 7 && i == 6) {
                // grid[i][j] = new Knight(i, j, true, this);
                // }

                // // Rook
                // if (j == 0 && i == 0 || j == 0 && i == 7) {
                // grid[i][j] = new Rook(i, j, false, this);

                // } else if (j == 7 && i == 0 || j == 7 && i == 7) {
                // grid[i][j] = new Rook(i, j, true, this);
                // }

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
