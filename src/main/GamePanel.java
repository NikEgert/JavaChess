package main;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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

    public boolean turn;

    public GamePanel(LayoutManager layout){
        super(layout);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setPreferredSize(new Dimension(boardWidth, boardHeight));
        this.setBackground(new Color(177,228,185));
        this.setDoubleBuffered(true);
        pieceUpdate = new PieceUpdate();
        pieceUpdate.initialPositions();
        this.turn = true;
    }

    public void startGameThread(){
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
                    g2.setColor(new Color(112,162,163));
                    g2.fillRect(i * tileSize, j * tileSize, tileSize, tileSize);
                }
            }
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
            g2.drawImage(clickedPiece.getImage(), mouseX - tileSize / 2, mouseY - tileSize / 2, tileSize, tileSize, null);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        if (mouseFollow){
            mouseX = e.getX();
            mouseY = e.getY();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        matrixColIndex = x / tileSize;
        matrixRowIndex = y / tileSize;

        if (matrixRowIndex < 0) matrixRowIndex = 0;
        if (matrixRowIndex >= 8) matrixRowIndex = 7;
        if (matrixColIndex < 0) matrixColIndex = 0;
        if (matrixColIndex >= 8) matrixColIndex = 7;

        if (clickedPiece == null) {
            clickedPiece = pieceUpdate.getPieceAt(matrixColIndex, matrixRowIndex);
            Boolean colour = clickedPiece.getColour();
            if (colour != turn){
                System.out.println("Wrong turn");
                clickedPiece = null;
            }else{
                if (clickedPiece != null) {
                    pieceUpdate.erasePiece(matrixColIndex, matrixRowIndex);
                    mouseFollow = true;
                    pieceUpdate.printBoard();
                }
            }
        } else {
            pieceUpdate.setPiece(matrixColIndex, matrixRowIndex, clickedPiece);
            clickedPiece = null;
            mouseFollow = false;
            if (turn == false){
                turn = true;
            } else{
                turn = false;
            }
            pieceUpdate.printBoard();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}
}
