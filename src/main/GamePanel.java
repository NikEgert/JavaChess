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
    public boolean mouseClicked;
    public boolean mouseFollow;

    public Piece clickedPiece;

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

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        if (mouseClicked) {
            g2.drawImage(clickedPiece.getImage(), mouseX - 50, mouseY - 50, null);
        }

        //drawing grid
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                g2.drawLine(0, i*tileSize, boardWidth, i*tileSize);
                g2.drawLine(i*tileSize, 0, i*tileSize, boardHeight);
            }
        }

        //intialising pieces
        if (gameStart){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Piece piece = pieceUpdate.getPieceAt(i, j);
                    if (piece != null) {
                        piece.draw(g2);
                    }
                }
            }
        }
    }
    
    @Override
        public void mouseClicked(MouseEvent e) {
            int x = (e.getX() + 50) / 100; // Calculate grid X position based on mouse click
            int y = (e.getY() + 50) / 100; // Calculate grid Y position based on mouse click
            clickedPiece = pieceUpdate.getPieceAt(x, y); // Get the piece at the clicked grid position
            System.out.println(pieceUpdate.grid[x][y].toString());
            pieceUpdate.erasePiece(x, y);
            if (pieceUpdate.erasePiece(x, y) == null){
                System.out.println("null");
            }else{
                System.out.println(pieceUpdate.erasePiece(x, y).toString());
            }
            
            if (clickedPiece == null && mouseFollow) {
                // Place the piece at the current mouse position
                pieceUpdate.setPiece(x, y, clickedPiece);
                
                // Reset states after placing the piece
                mouseFollow = false;
                mouseClicked = false;
                
                repaint();
            } else {
                mouseClicked = true;
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
        if (mouseClicked){
            mouseX = e.getX();
            mouseY = e.getY();
            mouseFollow = true;
            System.out.println(mouseX + "," + mouseY);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // not used
    }
}
