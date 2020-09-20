/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.infrastruktur.desa;

import java.sql.SQLException;
import javax.swing.UIManager;

/**
 *
 * @author encep
 */
public class SIInfrastrukturDesa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException{
            // TODO code application logic here
            try {
             UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");   
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(SIInfrastrukturDesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(SIInfrastrukturDesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(SIInfrastrukturDesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(SIInfrastrukturDesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            si.infrastruktur.desa.form.HalamanUtama Tampil = new si.infrastruktur.desa.form.HalamanUtama();
            Tampil.setVisible(true);
          
                
    }
}
