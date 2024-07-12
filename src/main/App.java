package main;
import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) throws Exception {
        int boardWidth = 800;
        int boardHeight = 800;
        
        JFrame frame = new JFrame("Chess");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel gamePanel = new GamePanel(new GridLayout(8, 8));
        gamePanel.setBorder(BorderFactory.createLineBorder(new Color(68,82,53), 10));
        frame.add(gamePanel);
        frame.pack();
        frame.setVisible(true);

        gamePanel.startGameThread();
    }
}
