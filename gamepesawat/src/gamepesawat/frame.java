/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamepesawat;
import java.awt.Component;
import javax.swing.JFrame;
/**
 *
 * @author My Name is Methos
 */

    public class frame extends JFrame {

    public frame() {
        Component add;
        add = add(new background());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        setTitle("Battle Planes");
        setResizable(false);
        setVisible(true);
     
    }

    public static void main(String[] args) {
        frame frame = new frame();
    }
}
