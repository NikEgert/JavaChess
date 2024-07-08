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

    public boolean getColour(boolean colour){
        return colour;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    protected abstract String getImageFileName();

    private void loadImage() {
        try {
            image = ImageIO.read(new File(getImageFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(image, x * 100, y * 100, null);
    }

}
