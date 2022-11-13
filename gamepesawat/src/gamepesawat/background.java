/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamepesawat;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 *
 * @author My Name is Methos
 */
public class background extends JPanel implements ActionListener {
  private Timer timer;
    
    private control enemy;
    private ArrayList aliens;
    private boolean ingame;
    private int B_WIDTH;
    private int B_HEIGHT;
    AudioClip soundTrack;
    AudioClip sound;
    Image gambar;
    private Image back;
    

    private int[][] pos = { 
        {2380, 29}, {2500, 59}, {1380, 89},
        {780, 109}, {580, 139}, {680, 239}, 
        {790, 259}, {760, 50}, {790, 150},
        {980, 209}, {560, 45}, {510, 70},
        {930, 159}, {590, 80}, {530, 60},
        {940, 59}, {990, 30}, {920, 200},
        {900, 259}, {660, 50}, {540, 90},
        {810, 220}, {860, 20}, {740, 180},
        {820, 128}, {490, 170}, {700, 30}
     };

    public background() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.black);
        setDoubleBuffered(true);
        ingame = true;

        setSize(600,400);
        enemy = new control();
        
       
        initAliens();
        soundTrack=Applet.newAudioClip(getClass().getResource("backsound.wav"));
        soundTrack.loop();
        timer = new Timer(20, this);
        timer.start();
        
    }
    @Override
    public void addNotify() {
        super.addNotify();
        B_WIDTH = getWidth();
        B_HEIGHT = getHeight();   
    }

    public final void initAliens() {
        aliens = new ArrayList();
        for (int i=0; i<pos.length; i++ ) {
            aliens.add(new enemy(pos[i][0], pos[i][1]));
            
        }
    }
    
     public static Image loadImage(String fileName) {
        ImageIcon icon = new ImageIcon(fileName);
        return icon.getImage();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (ingame) {

            Graphics2D g2d = (Graphics2D)g;

            if (enemy.isVisible()) {
                g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(),
                              this);
            }

            ArrayList ms = enemy.getMissiles();

            for (int i = 0; i < ms.size(); i++) {
                bullet m = (bullet)ms.get(i);
                g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
                
                
                
            }
            

            for (int i = 0; i < aliens.size(); i++) {
                enemy a = (enemy)aliens.get(i);
                if (a.isVisible()) {
                    g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
                }
            }
            g2d.setColor(Color.red);
            g2d.drawString("Aliens left: " + aliens.size(), 5, 15);
        } else {
            String msg = "Game Over";
            Font small = new Font("Helvetica", Font.BOLD, 50);
            FontMetrics metr = this.getFontMetrics(small);

            g.setColor(Color.red);
            g.setFont(small);
            g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) /2,
                         B_HEIGHT /2);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (aliens.isEmpty()) {
            ingame = false;
        }

        ArrayList ms = enemy.getMissiles();

        for (int i = 0; i < ms.size(); i++) {
            bullet m = (bullet) ms.get(i);
            if (m.isVisible()) { 
                m.move();
                
            }
            else {
                ms.remove(i);
            }
        }
        for (int i = 0; i < aliens.size(); i++) {
            enemy a = (enemy) aliens.get(i);
            if (a.isVisible()) { 
                a.move();
            }
            else {
                aliens.remove(i);
            }
        }
        enemy.move();
        checkCollisions();
        repaint();  
    }
    public void checkCollisions() {
        Rectangle r3 = enemy.getBounds();
        for (int j = 0; j<aliens.size(); j++) {
            enemy a = (enemy) aliens.get(j);
            Rectangle r2 = a.getBounds();

            if (r3.intersects(r2)) {
                enemy.setVisible(false);
                a.setVisible(false);
                ingame = false;
                sound = Applet.newAudioClip(getClass().getResource("explosion.wav"));
                     sound.play();
                
            }
        }

        ArrayList ms = enemy.getMissiles();

        for (int i = 0; i < ms.size(); i++) {
            bullet m = (bullet) ms.get(i);

            Rectangle r1 = m.getBounds();

            for (int j = 0; j<aliens.size(); j++) {
                enemy a = (enemy) aliens.get(j);
                Rectangle r2 = a.getBounds();

                if (r1.intersects(r2)) {
                    m.setVisible(false);
                    a.setVisible(false);
                    
                    if (r1.intersects(r2)) {
                    sound = Applet.newAudioClip(getClass().getResource("laser5.wav"));
                     sound.play();
                }
            }
        }
    }
    }
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
           enemy.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            enemy.keyPressed(e);
        }
    }
}  
