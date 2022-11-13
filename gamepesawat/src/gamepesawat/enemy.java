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
public class enemy {
   private String control = "enemy.png";

    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible;
    private Image image;

    public enemy(int x, int y) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(control));
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        visible = true;
        this.x = x;
        this.y = y;
    }


    public void move() {
        if (x < 0) {
            x = 600;
        }
        x -= 1;
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

    public Image getImage() {
        return image;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}  

