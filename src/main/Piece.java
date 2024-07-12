package main;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public abstract class Piece {
    protected int x;
    protected int y;
    protected boolean colour;
    protected BufferedImage image;

    public Piece(int x, int y, boolean colour){
        this.x = x;
        this.y = y;
        this.colour = colour;
        loadImage();
    }

    public boolean getColour(){
        return colour;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected abstract String getImageFileName();

    private void loadImage() {
        try {
            image = ImageIO.read(new File(getImageFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, int tileSize) {
        g2.drawImage(image, x * tileSize, y * tileSize, tileSize, tileSize, null);
    }

    public BufferedImage getImage(){
        return image;
    }
}
