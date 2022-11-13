/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamepesawat;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
/**
 *
 * @author My Name is Methos
 */
public class bullet {
   private int x, y;
    private Image image;
    boolean visible;
    private int width, height;

    private final int BOARD_WIDTH = 500;
    private final int MISSILE_SPEED = 1;

    public bullet(int x, int y) {

        ImageIcon ii =
            new ImageIcon(this.getClass().getResource("bullet.png"));
        image = ii.getImage();
        visible = true;
        width = image.getWidth(null);
        height = image.getHeight(null);
        this.x = x;
        this.y = y;
    }


    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void move() {
        x += MISSILE_SPEED;
        if (x > BOARD_WIDTH) {
            visible = false;
        }
    }
} 

