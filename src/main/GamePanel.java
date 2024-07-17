package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Collections;
import java.util.List;

public class GamePanel extends JPanel implements Runnable, MouseListener, MouseMotionListener {
    int tileSize = 100;
    int boardWidth = 8 * tileSize;
    int boardHeight = 8 * tileSize;
    PieceUpdate pieceUpdate;
    boolean gameStart = true;

    Thread gameThread;

    public int mouseX;
    public int mouseY;
    public int matrixColIndex;
    public int matrixRowIndex;

    public boolean mouseFollow;

    public Piece clickedPiece;
    public Piece king;
    public int kingX = -1;
    public int kingY = -1;

    public boolean turn;
    public boolean check;
    public boolean checkMate;

    public List<Piece> moveablePieces;

    private int originalX = -1;
    private int originalY = -1;

    public GamePanel(LayoutManager layout) {
        super(layout);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setPreferredSize(new Dimension(boardWidth, boardHeight));
        this.setBackground(new Color(112, 162, 163));
        this.setDoubleBuffered(true);
        pieceUpdate = new PieceUpdate();
        pieceUpdate.initialPositions();
        this.turn = true;
        this.check = false;
        this.checkMate = false;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Draw colour squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    g2.setColor(new Color(177, 228, 185));
                    g2.fillRect(i * tileSize, j * tileSize, tileSize, tileSize);
                }
            }
        }

        if (check && king != null) {
            g2.setColor(new Color(255, 165, 0));
            g2.fillRect(kingX * tileSize, kingY * tileSize, tileSize, tileSize);
        }

        if (checkMate) {
            g2.setColor(new Color(255, 0, 0));
            g2.fillRect(kingX * tileSize, kingY * tileSize, tileSize, tileSize);
        }

        Piece[][] grid = pieceUpdate.getGrid();

        // Draw pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = grid[i][j];
                if (piece != null) {
                    piece.draw(g2, tileSize);
                }
            }
        }

        if (mouseFollow && clickedPiece != null) {
            g2.drawImage(clickedPiece.getImage(), mouseX - tileSize / 2, mouseY - tileSize / 2, tileSize, tileSize,
                    null);
        }
    }

    public int getMatrixRowIndex(int y) {
        matrixRowIndex = y / tileSize;
        if (matrixRowIndex < 0)
            matrixRowIndex = 0;
        if (matrixRowIndex >= 8)
            matrixRowIndex = 8;
        return matrixRowIndex;
    }

    public int getMatrixColIndex(int x) {
        matrixColIndex = x / tileSize;
        if (matrixColIndex < 0)
            matrixColIndex = 0;
        if (matrixColIndex >= 8)
            matrixColIndex = 8;
        return matrixColIndex;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        matrixColIndex = getMatrixColIndex(x);
        matrixRowIndex = getMatrixRowIndex(y);

        if (clickedPiece == null) {
            clickedPiece = pieceUpdate.getPieceAt(matrixColIndex, matrixRowIndex);
            boolean colour = clickedPiece.getColour();
            if (colour != turn) {
                clickedPiece = null;
            } else if (check && !checkMoveable(clickedPiece)) {
                clickedPiece = null;
            } else {
                if (clickedPiece != null) {
                    originalX = matrixColIndex;
                    originalY = matrixRowIndex;
                    pieceUpdate.erasePiece(matrixColIndex, matrixRowIndex);
                    mouseFollow = true;
                }
            }
        } else {
            boolean canMove = pieceUpdate.setPiece(matrixColIndex, matrixRowIndex, clickedPiece);
            mouseFollow = false;
            if (canMove) {
                if (pieceUpdate.checkCheck(clickedPiece) != null) {
                    king = pieceUpdate.checkCheck(clickedPiece);
                    Boolean kingColour = king.getColour();
                    List<Piece> blockingPieces = pieceUpdate.getBlockingPieces(kingColour);
                    check = true;

                    System.out.println("Can king move around piece? " + pieceUpdate.canMoveAroundPiece(king));
                    System.out.println("Blocking pieces: " + blockingPieces);
                    System.out.println("Is blockingPieces empty? " + blockingPieces.isEmpty());

                    if (!pieceUpdate.canMoveAroundPiece(king) && blockingPieces.isEmpty()) {
                        System.out.println("checkmate");
                        checkMate = true;
                        pieceUpdate.checkMate();
                    } else {
                        blockingPieces.add(king);
                        moveablePieces = blockingPieces;
                    }
                    kingX = king.getX();
                    kingY = king.getY();
                } else {
                    check = false;
                    moveablePieces = Collections.<Piece>emptyList();
                    king = null;
                }
                turn = !turn;
            } else {
                pieceUpdate.setOriginal(originalX, originalY, clickedPiece);
            }
            clickedPiece = null;
        }
    }

    public boolean checkMoveable(Piece p) {
        for (Piece piece : moveablePieces) {
            if (piece.equals(p)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if (mouseFollow) {
            mouseX = e.getX();
            mouseY = e.getY();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

}
