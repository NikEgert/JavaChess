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
    
    public boolean mouseClicked;
    public boolean mouseFollow;

    public Piece clickedPiece;
    public Piece setPiece;

    public GamePanel(LayoutManager layout){
        super(layout);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setPreferredSize(new Dimension(boardWidth, boardHeight));
        this.setBackground(Color.LIGHT_GRAY);
        this.setDoubleBuffered(true);
        pieceUpdate = new PieceUpdate();
        pieceUpdate.initialPositions();
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

        // Draw grid
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                g2.drawLine(0, j * tileSize, boardWidth, j * tileSize);
                g2.drawLine(i * tileSize, 0, i * tileSize, boardHeight);
            }
        }

        Piece[][] grid = pieceUpdate.grid;

        // Draw pieces
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                Piece piece = grid[i][j];
                if (piece != null) {
                    piece.draw(g2, tileSize);
                }
            }
        }

        if (mouseFollow && clickedPiece != null) {
            g2.drawImage(clickedPiece.getImage(), mouseX - 50, mouseY - 50, null);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        matrixColIndex = x / tileSize;
        matrixRowIndex = y / tileSize;

        // Ensure indices are within bounds
        if (matrixRowIndex < 0) matrixRowIndex = 0;
        if (matrixRowIndex >= 8) matrixRowIndex = 7;
        if (matrixColIndex < 0) matrixColIndex = 0;
        if (matrixColIndex >= 8) matrixColIndex = 7;

        // Get the piece at the clicked position
        clickedPiece = pieceUpdate.getPieceAt(matrixColIndex, matrixRowIndex);

        if (clickedPiece != null) {
            setPiece = clickedPiece;
            pieceUpdate.erasePiece(matrixColIndex, matrixRowIndex);
            mouseFollow = true;
            pieceUpdate.printBoard();
        } else {
            pieceUpdate.setPiece(matrixColIndex, matrixRowIndex, setPiece);
            pieceUpdate.printBoard();
            mouseFollow = false;
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (mouseFollow){
            mouseX = e.getX();
            mouseY = e.getY();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // not used
    }
}
