/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.infrastruktur.desa;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 *
 * @author encep
 */
public class logo extends JPanel {
    private Image image; // membuat variable image
     public logo() {
        image = new ImageIcon(getClass().getResource("/si/infrastruktur/desa/img/Logo.png")).getImage();
        //memanggil sumber daya gambar
    }
     @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
     
        Graphics gd = (Graphics2D) g.create();
     
        gd.drawImage(image, 0,0,getWidth(),getHeight(), this);
        // menggambar image
        gd.dispose();
    }
    
}
