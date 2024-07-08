package main;
import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
    int tileSize = 100;
    int boardWidth = 8 * tileSize;
    int boardHeight = 8 * tileSize;
    PieceUpdate pieceUpdate;
    boolean gameStart = true;

    Thread gameThread;

    public GamePanel(LayoutManager layout){
        super(layout);
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
            update();
            repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

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
}
