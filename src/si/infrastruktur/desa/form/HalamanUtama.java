/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HalamanUtama.java
 *
 * Created on Dec 31, 2016, 2:37:35 PM
 */
package si.infrastruktur.desa.form;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
/**
 *
 * @author encep
 */
public final class HalamanUtama extends javax.swing.JFrame {
    si.infrastruktur.koneksi.koneksiDesa dbsetting;
    String driver,database,user,pass;
    int i = 0;
    Boolean login=false;
    public static String userakses;

    public  HalamanUtama() throws SQLException {
     
     dbsetting = new si.infrastruktur.koneksi.koneksiDesa();
     driver = dbsetting.SettingPanel("DBDriver");
     database = dbsetting.SettingPanel("DBDatabase");
     user =  dbsetting.SettingPanel("DBUsername");
     pass =  dbsetting.SettingPanel("DBPassword");
     
     this.setUndecorated(true);
     this.setResizable(false);
     initComponents();
     Toolkit tk = Toolkit.getDefaultToolkit();
     int xsize = (int) tk.getScreenSize().getWidth();
     int ysize = (int) tk.getScreenSize().getHeight();
     this.setSize(xsize, ysize);
     //Panel
     PanelMasyarakat.setVisible(false);
     PanelStaff.setVisible(false);
     PanelBendahara.setVisible(false);
     PanelLurah.setVisible(false);
     
     //panelmasyarakat
     PanelKebutuhanMasyarakat.setVisible(false);
     PanelPerbaikanMasyarakat.setVisible(false);
     
     //panel laporan
     PanelLurahPencarian.setVisible(false);
     InformasiUser.setVisible(false);
     PanelPembangunan.setVisible(false);
     PanelPerbaikan.setVisible(false);
     PanelKebPem.setVisible(false);
     
     PanelKebPerb.setVisible(false);
     
     //button

     userlogin1.setVisible(false);
     keluar.setVisible(false);



     
     dataKebutuhanMasyarakat.setModel(tableModel);
     dataPerbaikanMasyarakat.setModel(tableModel1);
     datadanakas.setModel(tableModel2);
     tabelLapKeb.setModel(tableModel3);
     tabelLapPer.setModel(tableModel4);
     TKebPem.setModel(tableModel5);
     TKebPerb.setModel(tableModel6);
     settableload();
     settableload1();
     settableload2();
     settableload3();
     settableload4();
     settableload5();
     settableload6();
     auto_idkebutuhan();
     auto_idperbaikan();
     auto_NoDanaKas();
     auto_NoPembangunan();
     auto_NoPerbaikan();
     auto_NoKebPembangunan();
     auto_NoKebPerbaikan();
     comboNoKebutuhan();
     comboNoPerbaikan();
     comboNoKebutuhanInf();
     comboNoPerbaikanInf();
     
     
     
    }
    
    private void comboNoKebutuhan() throws SQLException {
        Connection kon = (Connection) DriverManager.getConnection(database,user,pass);  
        java.sql.Statement stmt;
        stmt = kon.createStatement();
        String sql = "Select * From data_kebutuhan order by No_DataKebutuhan asc";
        java.sql.ResultSet rslt = stmt.executeQuery(sql);
        while (rslt.next()) {
           String DataKebut = rslt.getString("No_DataKebutuhan");
           CDataKebutuhan.addItem(DataKebut);
        }
      }
    
     private void comboNoPerbaikan() throws SQLException {
        Connection kon = (Connection) DriverManager.getConnection(database,user,pass);  
        java.sql.Statement stmt;
        stmt = kon.createStatement();
        String sql = "Select * From data_perbaikan order by No_DataPerbaikan asc";
        java.sql.ResultSet rslt = stmt.executeQuery(sql);
        while (rslt.next()) {
           String DataPerb = rslt.getString("No_DataPerbaikan");
           //combo_add_dokterpasien.addItem(nama_dokter+ " (" +jns_dokter+")");
           CDataPerbaikan.addItem(DataPerb);
        }
      }
     
     private void comboNoKebutuhanInf() throws SQLException {
        Connection kon = (Connection) DriverManager.getConnection(database,user,pass);  
        java.sql.Statement stmt;
        stmt = kon.createStatement();
        String sql = "Select * From bahan_pembangunan order by No_KebPembangunan asc";
        java.sql.ResultSet rslt = stmt.executeQuery(sql);
        while (rslt.next()) {
           String NoKebu = rslt.getString("No_KebPembangunan");
           //combo_add_dokterpasien.addItem(nama_dokter+ " (" +jns_dokter+")");
           CKebPembangunan.addItem(NoKebu);
        }
      }
     private void comboNoPerbaikanInf() throws SQLException {
        Connection kon = (Connection) DriverManager.getConnection(database,user,pass);  
        java.sql.Statement stmt;
        stmt = kon.createStatement();
        String sql = "Select * From bahan_perbaikan order by No_KebPerbaikan asc";
        java.sql.ResultSet rslt = stmt.executeQuery(sql);
        while (rslt.next()) {
           String NoPerb = rslt.getString("No_KebPerbaikan");
           //combo_add_dokterpasien.addItem(nama_dokter+ " (" +jns_dokter+")");
           CKebPerbaikan.addItem(NoPerb);
        }
      }
     
     

     public void tampil_Kebutuhan() {
      try { Connection kon = (Connection) DriverManager.getConnection(database, user, pass);
            Statement stt = (Statement) kon.createStatement();
            String sql = "Select Nama_Kebutuhan,lokasi From data_kebutuhan WHERE No_DataKebutuhan='"+CDataKebutuhan.getSelectedItem()+"'";
            ResultSet res = stt.executeQuery(sql);
            while(res.next()){
                  Object[] ob = new Object[2];
                  ob[0] = res.getString(1);
                  ob[1] = res.getString(2);
                  NamaPembangunan.setText((String) ob[0]);
                  LokasiPembangunan.setText((String) ob[1]);
            }
             res.close();
             stt.close();
          } catch (Exception ex){
            System.out.println(ex.getMessage());
          }
    }
     
     public void tampil_BahanPembangunan() {
      try { Connection kon = (Connection) DriverManager.getConnection(database, user, pass);
            Statement stt = (Statement) kon.createStatement();
            String sql = "Select Daftar_KebPembangunan,Total_Biaya From Bahan_Pembangunan WHERE No_KebPembangunan='"+CKebPembangunan.getSelectedItem()+"'";
            ResultSet res = stt.executeQuery(sql);
            while(res.next()){
                  Object[] ob = new Object[2];
                  ob[0] = res.getString(1);
                  ob[1] = res.getString(2);
                  DBPembangunan.setText((String) ob[0]);
                  TBPembangunan.setText((String) ob[1]);
                  
            }
             res.close();
             stt.close();
          } catch (Exception ex){
            System.out.println(ex.getMessage());
          }
    }
     
      public void tampil_Perbaikan() {
      try { Connection kon = (Connection) DriverManager.getConnection(database, user, pass);
            Statement stt = (Statement) kon.createStatement();
            String sql = "Select Nama_Perbaikan,lokasi From data_perbaikan WHERE No_DataPerbaikan='"+CDataPerbaikan.getSelectedItem()+"'";
            ResultSet res = stt.executeQuery(sql);
            while(res.next()){
                  Object[] ob = new Object[2];
                  ob[0] = res.getString(1);
                  ob[1] = res.getString(2);
                  NamaPerbaikan.setText((String) ob[0]);
                  LokasiPerbaikan.setText((String) ob[1]);
            }
             res.close();
             stt.close();
          } catch (Exception ex){
            System.out.println(ex.getMessage());
          }
    }
     
     public void tampil_BahanPerbaikan() {
      try { Connection kon = (Connection) DriverManager.getConnection(database, user, pass);
            Statement stt = (Statement) kon.createStatement();
            String sql = "Select Daftar_KebPerbaikan,Total_Biaya From Bahan_Perbaikan WHERE No_KebPerbaikan='"+CKebPerbaikan.getSelectedItem()+"'";
            ResultSet res = stt.executeQuery(sql);
            while(res.next()){
                  Object[] ob = new Object[2];
                  ob[0] = res.getString(1);
                  ob[1] = res.getString(2);
                  DBPerbaikan.setText((String) ob[0]);
                  TBPerbaikan.setText((String) ob[1]);
                  
            }
             res.close();
             stt.close();
          } catch (Exception ex){
            System.out.println(ex.getMessage());
          }
    }
     
     
    
    public void refreshtable() {
        int row = tableModel.getRowCount();
        for (int i = 0; i < row; i++)
        {
            tableModel.removeRow(0);
        }
    }
     public void refreshtable1() {
        int row = tableModel1.getRowCount();
        for (int i = 0; i < row; i++)
        {
            tableModel1.removeRow(0);
        }
    }
     public void refreshtable2() {
        int row = tableModel2.getRowCount();
        for (int i = 0; i < row; i++)
        {
            tableModel2.removeRow(0);
        }
    }
     
     public void refreshtable3() {
        int row = tableModel3.getRowCount();
        for (int i = 0; i < row; i++)
        {
            tableModel3.removeRow(0);
        }
    }
     
      public void refreshtable4() {
        int row = tableModel4.getRowCount();
        for (int i = 0; i < row; i++)
        {
            tableModel4.removeRow(0);
        }
    }
      
       public void refreshtable5() {
        int row = tableModel5.getRowCount();
        for (int i = 0; i < row; i++)
        {
            tableModel5.removeRow(0);
        }
    }
       
        public void refreshtable6() {
        int row = tableModel6.getRowCount();
        for (int i = 0; i < row; i++)
        {
            tableModel6.removeRow(0);
        }
    }
    
    private javax.swing.table.DefaultTableModel tableModel= getDefaultTabelModel();
    private javax.swing.table.DefaultTableModel getDefaultTabelModel()        
    {
        return new javax.swing.table.DefaultTableModel
                (
                    new Object[][] {},
                    new String[] {"No Data Kebutuhan", "Nama Kebutuhan", "Lokasi"}
                )
    {
        boolean[] canEdit = new boolean[] {
            false, false, false
        };
        
       @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
       return canEdit[columnIndex];
        }
     };
   }
    
     String data[]= new String[3];
     private void settableload() {
           String stat = "";
           try {
               Class.forName(driver);
                       Connection kon = (Connection) DriverManager.getConnection(database,user,pass);      
                       
                       Statement stt=(Statement) kon.createStatement();
                       String SQL = "select * from data_kebutuhan";
                       ResultSet res = stt.executeQuery(SQL);
                       while(res.next()) {
                        data[0] = res.getString(1); 
                        data[1] = res.getString(2); 
                        data[2] = res.getString(3); 
                        
                        tableModel.addRow(data);
                       }
                       res.close();
                       stt.close();
                       kon.close();
           } catch(Exception ex) {
                           System.err.println(ex.getMessage());
                           JOptionPane.showMessageDialog(null,ex.getMessage(),"Gagal Terhubung Ke Database",JOptionPane.INFORMATION_MESSAGE);
                       }
       }
       
    private javax.swing.table.DefaultTableModel tableModel1= getDefaultTabelModel1();
    private javax.swing.table.DefaultTableModel getDefaultTabelModel1()        
    {
        return new javax.swing.table.DefaultTableModel
                (
                    new Object[][] {},
                    new String[] {"No Data Perbaikan", "Nama Perbaikan", "Lokasi"}
                )
    {
        boolean[] canEdit = new boolean[] {
            false, false, false
        };
        
       @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
       return canEdit[columnIndex];
        }
     };
   }
    
     String data1[]= new String[3];
       private void settableload1() {
           String stat = "";
           try {
               Class.forName(driver);
                       Connection kon = (Connection) DriverManager.getConnection(database,user,pass);      
                       
                       Statement stt=(Statement) kon.createStatement();
                       String SQL = "select * from data_perbaikan";
                       ResultSet res = stt.executeQuery(SQL);
                       while(res.next()) {
                        data1[0] = res.getString(1); 
                        data1[1] = res.getString(2); 
                        data1[2] = res.getString(3); 
                        
                        tableModel1.addRow(data1);
                       }
                       res.close();
                       stt.close();
                       kon.close();
           } catch(Exception ex) {
                           System.err.println(ex.getMessage());
                           JOptionPane.showMessageDialog(null,ex.getMessage(),"Gagal Terhubung Ke Database",JOptionPane.INFORMATION_MESSAGE);
                       }
       }
       
    private javax.swing.table.DefaultTableModel tableModel2= getDefaultTabelModel2();
    private javax.swing.table.DefaultTableModel getDefaultTabelModel2()        
    {
        return new javax.swing.table.DefaultTableModel
                (
                    new Object[][] {},
                    new String[] {"No Dana Kas", "Penerimaan Dana", "Pengeluaran Dana", "Saldo", "Keterangan"}
                )
    {
        boolean[] canEdit = new boolean[] {
            false, false, false, false, false
        };
        
       @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
       return canEdit[columnIndex];
        }
     };
   }
    
     String data2[]= new String[5];
     private void settableload2() {
           String stat = "";
           try {
               Class.forName(driver);
                       Connection kon = (Connection) DriverManager.getConnection(database,user,pass);      
                       
                       Statement stt=(Statement) kon.createStatement();
                       String SQL = "select * from dana_kas";
                       ResultSet res = stt.executeQuery(SQL);
                       while(res.next()) {
                        data2[0] = res.getString(1); 
                        data2[1] = res.getString(2); 
                        data2[2] = res.getString(3); 
                        data2[3] = res.getString(4); 
                        data2[4] = res.getString(5);
                        tableModel2.addRow(data2);
                       }
                       res.close();
                       stt.close();
                       kon.close();
           } catch(Exception ex) {
                           System.err.println(ex.getMessage());
                           JOptionPane.showMessageDialog(null,ex.getMessage(),"Gagal Terhubung Ke Database",JOptionPane.INFORMATION_MESSAGE);
                       }
       }
     
     
    private javax.swing.table.DefaultTableModel tableModel3= getDefaultTabelModel3();
    private javax.swing.table.DefaultTableModel getDefaultTabelModel3()        
    {
        return new javax.swing.table.DefaultTableModel
                (
                    new Object[][] {},
                    new String[] {"No Lap.Pembangunan", "No Data Pembangunan", "Nama Pembangunan", "Lokasi", "No Keb.Pembangunan", "Daftar Keb.Pembangunan", "Total", "Keterangan"}
                )
    {
        boolean[] canEdit = new boolean[] {
            false, false, false, false, false, false, false
        };
        
       @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
       return canEdit[columnIndex];
        }
     };
   }
    
     String data3[]= new String[8];
     private void settableload3() {
           String stat = "";
           try {
               Class.forName(driver);
                       Connection kon = (Connection) DriverManager.getConnection(database,user,pass);      
                       
                       Statement stt=(Statement) kon.createStatement();
                       String SQL = "select * from laporan_pembangunan";
                       ResultSet res = stt.executeQuery(SQL);
                       while(res.next()) {
                        data3[0] = res.getString(1); 
                        data3[1] = res.getString(2); 
                        data3[2] = res.getString(3); 
                        data3[3] = res.getString(4); 
                        data3[4] = res.getString(5);
                        data3[5] = res.getString(6);
                        data3[6] = res.getString(7);
                        data3[7] = res.getString(8);
                        tableModel3.addRow(data3);
                       }
                       res.close();
                       stt.close();
                       kon.close();
           } catch(Exception ex) {
                           System.err.println(ex.getMessage());
                           JOptionPane.showMessageDialog(null,ex.getMessage(),"Gagal Terhubung Ke Database",JOptionPane.INFORMATION_MESSAGE);
                       }
       }
     
     private javax.swing.table.DefaultTableModel tableModel4= getDefaultTabelModel4();
    private javax.swing.table.DefaultTableModel getDefaultTabelModel4()        
    {
        return new javax.swing.table.DefaultTableModel
                (
                    new Object[][] {},
                    new String[] {"No Lap.Perbaikan", "No Data Perbaikan", "Nama Perbaikan", "Lokasi", "No Keb.Perbaikan", "Daftar Keb.Perbaikan", "Total", "Keterangan"}
                )
    {
        boolean[] canEdit = new boolean[] {
            false, false, false, false, false, false, false, false
        };
        
       @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
       return canEdit[columnIndex];
        }
     };
   }
    
     String data4[]= new String[8];
     private void settableload4() {
           String stat = "";
           try {
               Class.forName(driver);
                       Connection kon = (Connection) DriverManager.getConnection(database,user,pass);      
                       
                       Statement stt=(Statement) kon.createStatement();
                       String SQL = "select * from laporan_perbaikan";
                       ResultSet res = stt.executeQuery(SQL);
                       while(res.next()) {
                        data4[0] = res.getString(1); 
                        data4[1] = res.getString(2); 
                        data4[2] = res.getString(3); 
                        data4[3] = res.getString(4); 
                        data4[4] = res.getString(5);
                        data4[5] = res.getString(6);
                        data4[6] = res.getString(7);
                        data4[7] = res.getString(8);
                        tableModel4.addRow(data4);
                       }
                       res.close();
                       stt.close();
                       kon.close();
           } catch(Exception ex) {
                           System.err.println(ex.getMessage());
                           JOptionPane.showMessageDialog(null,ex.getMessage(),"Gagal Terhubung Ke Database",JOptionPane.INFORMATION_MESSAGE);
                       }
       }
     
    private javax.swing.table.DefaultTableModel tableModel5= getDefaultTabelModel5();
    private javax.swing.table.DefaultTableModel getDefaultTabelModel5()        
    {
        return new javax.swing.table.DefaultTableModel
                (
                    new Object[][] {},
                    new String[] {"No Kebutuhan Pembangunan", "Daftar Kebutuhan", "Total Biaya", "Keterangan"}
                )
    {
        boolean[] canEdit = new boolean[] {
            false, false, false, false
        };
        
       @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
       return canEdit[columnIndex];
        }
     };
   }
    
     String data5[]= new String[4];
     private void settableload5() {
           String stat = "";
           try {
               Class.forName(driver);
                       Connection kon = (Connection) DriverManager.getConnection(database,user,pass);      
                       
                       Statement stt=(Statement) kon.createStatement();
                       String SQL = "select * from bahan_pembangunan";
                       ResultSet res = stt.executeQuery(SQL);
                       while(res.next()) {
                        data5[0] = res.getString(1); 
                        data5[1] = res.getString(2); 
                        data5[2] = res.getString(3); 
                        data5[3] = res.getString(4); 
                        
                        tableModel5.addRow(data5);
                       }
                       res.close();
                       stt.close();
                       kon.close();
           } catch(Exception ex) {
                           System.err.println(ex.getMessage());
                           JOptionPane.showMessageDialog(null,ex.getMessage(),"Gagal Terhubung Ke Database",JOptionPane.INFORMATION_MESSAGE);
                       }
       }
     
      private javax.swing.table.DefaultTableModel tableModel6= getDefaultTabelModel6();
    private javax.swing.table.DefaultTableModel getDefaultTabelModel6()        
    {
        return new javax.swing.table.DefaultTableModel
                (
                    new Object[][] {},
                    new String[] {"No Kebutuhan Perbaikan", "Daftar Kebutuhan", "Total Biaya", "Keterangan"}
                )
    {
        boolean[] canEdit = new boolean[] {
            false, false, false, false
        };
        
       @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
       return canEdit[columnIndex];
        }
     };
   }
    
     String data6[]= new String[4];
     private void settableload6() {
           String stat = "";
           try {
               Class.forName(driver);
                       Connection kon = (Connection) DriverManager.getConnection(database,user,pass);      
                       
                       Statement stt=(Statement) kon.createStatement();
                       String SQL = "select * from bahan_perbaikan";
                       ResultSet res = stt.executeQuery(SQL);
                       while(res.next()) {
                        data6[0] = res.getString(1); 
                        data6[1] = res.getString(2); 
                        data6[2] = res.getString(3); 
                        data6[3] = res.getString(4); 
                        
                        tableModel6.addRow(data6);
                       }
                       res.close();
                       stt.close();
                       kon.close();
           } catch(Exception ex) {
                           System.err.println(ex.getMessage());
                           JOptionPane.showMessageDialog(null,ex.getMessage(),"Gagal Terhubung Ke Database",JOptionPane.INFORMATION_MESSAGE);
                       }
       }
       
        public void auto_idkebutuhan() {
        try {
            Class.forName(driver);
            Connection kon = (Connection) DriverManager.getConnection(database, user, pass);
            Statement stt = (Statement) kon.createStatement();
            String sql = "SELECT * FROM data_kebutuhan ORDER BY No_DataKebutuhan DESC";
            ResultSet rs = stt.executeQuery(sql);
            if (rs.next()) {
                String idkebutuhan = rs.getString("No_DataKebutuhan").substring(1);
                String AN = "" + (Integer.parseInt(idkebutuhan) + 1);
                String Nol = "";
                if(AN.length()==1)
                     {Nol = "000";}
                else if(AN.length()==2)
                     {Nol = "00";}
                else if(AN.length()==3)
                     {Nol = "0";}
                else if(AN.length()==4)
                     {Nol = "";}
                NoDataKebutuhan.setText("K" + Nol + AN);
                } else {
                NoDataKebutuhan.setText("K0001");
            } 
                rs.close();
                kon.close();
            } catch (Exception ex) {
                 System.err.println(ex.getMessage());
            }
    }
        
        public void auto_idperbaikan() {
        try {
            Class.forName(driver);
            Connection kon = (Connection) DriverManager.getConnection(database, user, pass);
            Statement stt = (Statement) kon.createStatement();
            String sql = "SELECT * FROM data_perbaikan ORDER BY No_DataPerbaikan DESC";
            ResultSet rs = stt.executeQuery(sql);
            if (rs.next()) {
                String idperbaikan = rs.getString("No_DataPerbaikan").substring(1);
                String AN = "" + (Integer.parseInt(idperbaikan) + 1);
                String Nol = "";
                if(AN.length()==1)
                     {Nol = "000";}
                else if(AN.length()==2)
                     {Nol = "00";}
                else if(AN.length()==3)
                     {Nol = "0";}
                else if(AN.length()==4)
                     {Nol = "";}
                NoDataPerbaikan.setText("P" + Nol + AN);
                } else {
                NoDataPerbaikan.setText("P0001");
            } 
                rs.close();
                kon.close();
            } catch (Exception ex) {
                 System.err.println(ex.getMessage());
            }
    }
        public void auto_NoDanaKas() {
        try {
            Class.forName(driver);
            Connection kon = (Connection) DriverManager.getConnection(database, user, pass);
            Statement stt = (Statement) kon.createStatement();
            String sql = "SELECT * FROM dana_kas ORDER BY No_DanaKas DESC";
            ResultSet rs = stt.executeQuery(sql);
            if (rs.next()) {
                String idperbaikan = rs.getString("No_DanaKas").substring(1);
                String AN = "" + (Integer.parseInt(idperbaikan) + 1);
                String Nol = "";
                if(AN.length()==1)
                     {Nol = "000";}
                else if(AN.length()==2)
                     {Nol = "00";}
                else if(AN.length()==3)
                     {Nol = "0";}
                else if(AN.length()==4)
                     {Nol = "";}
                NoDanaKas.setText("D" + Nol + AN);
                } else {
                NoDanaKas.setText("D0001");
            } 
                rs.close();
                kon.close();
            } catch (Exception ex) {
                 System.err.println(ex.getMessage());
            }
    }
        
        public void auto_NoPembangunan() {
        try {
            Class.forName(driver);
            Connection kon = (Connection) DriverManager.getConnection(database, user, pass);
            Statement stt = (Statement) kon.createStatement();
            String sql = "SELECT * FROM Laporan_pembangunan ORDER BY No_Lap_Pembangunan DESC";
            ResultSet rs = stt.executeQuery(sql);
            if (rs.next()) {
                String idperbaikan = rs.getString("No_Lap_Pembangunan").substring(1);
                String AN = "" + (Integer.parseInt(idperbaikan) + 1);
                String Nol = "";
                if(AN.length()==1)
                     {Nol = "000";}
                else if(AN.length()==2)
                     {Nol = "00";}
                else if(AN.length()==3)
                     {Nol = "0";}
                else if(AN.length()==4)
                     {Nol = "";}
                NoPembangunan.setText("L" + Nol + AN);
                } else {
                NoPembangunan.setText("L0001");
            } 
                rs.close();
                kon.close();
            } catch (Exception ex) {
                 System.err.println(ex.getMessage());
            }
    }
        
        public void auto_NoPerbaikan() {
        try {
            Class.forName(driver);
            Connection kon = (Connection) DriverManager.getConnection(database, user, pass);
            Statement stt = (Statement) kon.createStatement();
            String sql = "SELECT * FROM Laporan_Perbaikan ORDER BY No_Lap_Perbaikan DESC";
            ResultSet rs = stt.executeQuery(sql);
            if (rs.next()) {
                String idperbaikan = rs.getString("No_Lap_Perbaikan").substring(1);
                String AN = "" + (Integer.parseInt(idperbaikan) + 1);
                String Nol = "";
                if(AN.length()==1)
                     {Nol = "000";}
                else if(AN.length()==2)
                     {Nol = "00";}
                else if(AN.length()==3)
                     {Nol = "0";}
                else if(AN.length()==4)
                     {Nol = "";}
                NoPerbaikan.setText("E" + Nol + AN);
                } else {
                NoPerbaikan.setText("E0001");
            } 
                rs.close();
                kon.close();
            } catch (Exception ex) {
                 System.err.println(ex.getMessage());
            }
    }
    
         public void auto_NoKebPembangunan() {
        try {
            Class.forName(driver);
            Connection kon = (Connection) DriverManager.getConnection(database, user, pass);
            Statement stt = (Statement) kon.createStatement();
            String sql = "SELECT * FROM bahan_pembangunan ORDER BY No_KebPembangunan DESC";
            ResultSet rs = stt.executeQuery(sql);
            if (rs.next()) {
                String noKebutuhan = rs.getString("No_KebPembangunan").substring(1);
                String AN = "" + (Integer.parseInt(noKebutuhan) + 1);
                String Nol = "";
                if(AN.length()==1)
                     {Nol = "000";}
                else if(AN.length()==2)
                     {Nol = "00";}
                else if(AN.length()==3)
                     {Nol = "0";}
                else if(AN.length()==4)
                     {Nol = "";}
                NoKebPem.setText("B" + Nol + AN);
                } else {
                NoKebPem.setText("B0001");
            } 
                rs.close();
                kon.close();
            } catch (Exception ex) {
                 System.err.println(ex.getMessage());
            }
    }
         
         public void auto_NoKebPerbaikan() {
        try {
            Class.forName(driver);
            Connection kon = (Connection) DriverManager.getConnection(database, user, pass);
            Statement stt = (Statement) kon.createStatement();
            String sql = "SELECT * FROM bahan_perbaikan ORDER BY No_KebPerbaikan DESC";
            ResultSet rs = stt.executeQuery(sql);
            if (rs.next()) {
                String noKebPerb = rs.getString("No_KebPerbaikan").substring(1);
                String AN = "" + (Integer.parseInt(noKebPerb) + 1);
                String Nol = "";
                if(AN.length()==1)
                     {Nol = "000";}
                else if(AN.length()==2)
                     {Nol = "00";}
                else if(AN.length()==3)
                     {Nol = "0";}
                else if(AN.length()==4)
                     {Nol = "";}
                NoKebPerb.setText("R" + Nol + AN);
                } else {
                NoKebPerb.setText("R0001");
            } 
                rs.close();
                kon.close();
            } catch (Exception ex) {
                 System.err.println(ex.getMessage());
            }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        logo1 = new si.infrastruktur.desa.logo();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        PLoginAkses = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        TxtUser = new javax.swing.JTextField();
        TxtPassword = new javax.swing.JPasswordField();
        BtLogin1 = new javax.swing.JButton();
        BtReset1 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        userlogin1 = new javax.swing.JLabel();
        PanelMasyarakat = new javax.swing.JPanel();
        KebutuhanInfrastruktur = new javax.swing.JButton();
        PerbaikanInfrastruktur = new javax.swing.JButton();
        PanelKebutuhanMasyarakat = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nama_kebutuhan = new javax.swing.JTextField();
        lokasi_kebutuhan = new javax.swing.JTextField();
        SimpanKebutuhan = new javax.swing.JButton();
        BatalKebutuhan = new javax.swing.JButton();
        SelesaiKebutuhan = new javax.swing.JButton();
        NoDataKebutuhan = new javax.swing.JLabel();
        PanelPerbaikanMasyarakat = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        nama_perbaikan = new javax.swing.JTextField();
        lokasi_perbaikan = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        SimpanPerbaikan = new javax.swing.JButton();
        BatalPerbaikan = new javax.swing.JButton();
        SelesaiPerbaikan = new javax.swing.JButton();
        NoDataPerbaikan = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataKebutuhanMasyarakat = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        dataPerbaikanMasyarakat = new javax.swing.JTable();
        keluar = new javax.swing.JButton();
        PanelStaff = new javax.swing.JPanel();
        DataKebPembangunan = new javax.swing.JButton();
        DataPerbaik = new javax.swing.JButton();
        PanelPembangunan = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        LokasiPembangunan = new javax.swing.JTextField();
        TBPembangunan = new javax.swing.JTextField();
        NoPembangunan = new javax.swing.JLabel();
        CDataKebutuhan = new javax.swing.JComboBox();
        CKebPembangunan = new javax.swing.JComboBox();
        SimpanPembangunan = new javax.swing.JButton();
        UbahPembangunan = new javax.swing.JButton();
        HapusPembangunan = new javax.swing.JButton();
        DBPembangunan = new javax.swing.JTextField();
        KetPembangunan = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        NamaPembangunan = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        BatalPembangunan = new javax.swing.JButton();
        SelesaiPembangunan = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelLapKeb = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabelLapPer = new javax.swing.JTable();
        PanelPerbaikan = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        LokasiPerbaikan = new javax.swing.JTextField();
        TBPerbaikan = new javax.swing.JTextField();
        NoPerbaikan = new javax.swing.JLabel();
        CDataPerbaikan = new javax.swing.JComboBox();
        CKebPerbaikan = new javax.swing.JComboBox();
        SimpanDatPerbaikan = new javax.swing.JButton();
        UbahDatPerbaikan = new javax.swing.JButton();
        HapusDatPerbaikan = new javax.swing.JButton();
        DBPerbaikan = new javax.swing.JTextField();
        KetPerbaikan = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        NamaPerbaikan = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        BatalDatPerbaikan = new javax.swing.JButton();
        SelesaiDatPerbaikan = new javax.swing.JButton();
        DataPembang = new javax.swing.JButton();
        PanelKebPem = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        KetKeb = new javax.swing.JTextField();
        NoKebPem = new javax.swing.JLabel();
        SimpanKebPem = new javax.swing.JButton();
        UbahKebPem = new javax.swing.JButton();
        HapusKebPem = new javax.swing.JButton();
        DafKebutuhan = new javax.swing.JTextField();
        TBKebutuhan = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        BatalKebPem = new javax.swing.JButton();
        SelesaiKebPem = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        TKebPem = new javax.swing.JTable();
        DataKebPerbaikan = new javax.swing.JButton();
        PanelKebPerb = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        KetPerb = new javax.swing.JTextField();
        NoKebPerb = new javax.swing.JLabel();
        SimpanKebPerb = new javax.swing.JButton();
        UbahKebPerb = new javax.swing.JButton();
        HapusKebPerb = new javax.swing.JButton();
        DafKebPerb = new javax.swing.JTextField();
        TBKebPerb = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        BatalKebPerb = new javax.swing.JButton();
        SelesaiKebPerb = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        TKebPerb = new javax.swing.JTable();
        PanelBendahara = new javax.swing.JPanel();
        PanelDanaKas = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        KetKas = new javax.swing.JTextField();
        Saldo = new javax.swing.JTextField();
        Pengeluaran = new javax.swing.JTextField();
        Penerimaan = new javax.swing.JTextField();
        BatalKas = new javax.swing.JButton();
        SimpanKas = new javax.swing.JButton();
        UbahKas = new javax.swing.JButton();
        HapusKas = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        datadanakas = new javax.swing.JTable();
        NoDanaKas = new javax.swing.JLabel();
        PanelLurah = new javax.swing.JPanel();
        LapPembangunan = new javax.swing.JButton();
        LapPerbaikan = new javax.swing.JButton();
        LapLaporanDanaKas = new javax.swing.JButton();
        PanelLurahPencarian = new javax.swing.JPanel();
        Pencarian = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelPencarian = new javax.swing.JTable();
        Cari_status = new javax.swing.JLabel();
        InformasiUser = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        namauser = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout logo1Layout = new javax.swing.GroupLayout(logo1);
        logo1.setLayout(logo1Layout);
        logo1Layout.setHorizontalGroup(
            logo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 73, Short.MAX_VALUE)
        );
        logo1Layout.setVerticalGroup(
            logo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 84, Short.MAX_VALUE)
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel1.setText("Sistem Informasi Pengembangan Desa");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel2.setText("Biro Informasi Infrastruktur Desa");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel3.setText("Desa. Situraja Kecamatan. Gantar Kabupaten Indramayu - 45264");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
                    .addComponent(jLabel3))
                .addContainerGap())
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1397, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(logo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                        .addGap(10, 10, 10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));
        jPanel5.setForeground(new java.awt.Color(102, 102, 102));

        jLabel12.setText("Biro Informasi Infrastruktur Desa");

        PLoginAkses.setBorder(javax.swing.BorderFactory.createTitledBorder("Login Akses"));

        jLabel13.setText("Username :");

        jLabel14.setText("Password :");

        TxtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtPasswordKeyReleased(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtPasswordKeyPressed(evt);
            }
        });

        BtLogin1.setText("Masuk");
        BtLogin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtLogin1ActionPerformed(evt);
            }
        });

        BtReset1.setText("Batal");

        javax.swing.GroupLayout PLoginAksesLayout = new javax.swing.GroupLayout(PLoginAkses);
        PLoginAkses.setLayout(PLoginAksesLayout);
        PLoginAksesLayout.setHorizontalGroup(
            PLoginAksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PLoginAksesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PLoginAksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PLoginAksesLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(TxtPassword))
                    .addGroup(PLoginAksesLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(TxtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PLoginAksesLayout.createSequentialGroup()
                        .addComponent(BtReset1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(BtLogin1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PLoginAksesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BtLogin1, BtReset1});

        PLoginAksesLayout.setVerticalGroup(
            PLoginAksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PLoginAksesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PLoginAksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(TxtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PLoginAksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(TxtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PLoginAksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtReset1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtLogin1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PLoginAksesLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {BtLogin1, BtReset1});

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel18.setText("Selamat Datang Disistem Informasi Pengembangan Desa");

        userlogin1.setText("User Login");

        PanelMasyarakat.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel Masyarakat"));
        PanelMasyarakat.setName(""); // NOI18N

        KebutuhanInfrastruktur.setText("Masukkan Data Kebutuhan Infrastruktur Baru");
        KebutuhanInfrastruktur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KebutuhanInfrastrukturMouseClicked(evt);
            }
        });
        KebutuhanInfrastruktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KebutuhanInfrastrukturActionPerformed(evt);
            }
        });

        PerbaikanInfrastruktur.setText("Masukkan Data Perbaikan Infrastruktur");
        PerbaikanInfrastruktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PerbaikanInfrastrukturActionPerformed(evt);
            }
        });

        PanelKebutuhanMasyarakat.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Data Kebutuhan Infrastruktur"));

        jLabel4.setText("No Data Kebutuhan");

        jLabel5.setText("Nama Kebutuhan");

        jLabel6.setText("Lokasi");

        nama_kebutuhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nama_kebutuhanActionPerformed(evt);
            }
        });

        SimpanKebutuhan.setText("Simpan");
        SimpanKebutuhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SimpanKebutuhanMouseClicked(evt);
            }
        });
        SimpanKebutuhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SimpanKebutuhanActionPerformed(evt);
            }
        });

        BatalKebutuhan.setText("Batal");
        BatalKebutuhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BatalKebutuhanMouseClicked(evt);
            }
        });
        BatalKebutuhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BatalKebutuhanActionPerformed(evt);
            }
        });

        SelesaiKebutuhan.setText("Selesai");
        SelesaiKebutuhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SelesaiKebutuhanMouseClicked(evt);
            }
        });
        SelesaiKebutuhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelesaiKebutuhanActionPerformed(evt);
            }
        });

        NoDataKebutuhan.setText("0");

        javax.swing.GroupLayout PanelKebutuhanMasyarakatLayout = new javax.swing.GroupLayout(PanelKebutuhanMasyarakat);
        PanelKebutuhanMasyarakat.setLayout(PanelKebutuhanMasyarakatLayout);
        PanelKebutuhanMasyarakatLayout.setHorizontalGroup(
            PanelKebutuhanMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKebutuhanMasyarakatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelKebutuhanMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelKebutuhanMasyarakatLayout.createSequentialGroup()
                        .addGroup(PanelKebutuhanMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelKebutuhanMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NoDataKebutuhan)
                            .addComponent(lokasi_kebutuhan, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(nama_kebutuhan, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)))
                    .addGroup(PanelKebutuhanMasyarakatLayout.createSequentialGroup()
                        .addComponent(SimpanKebutuhan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BatalKebutuhan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addComponent(SelesaiKebutuhan)))
                .addContainerGap())
        );
        PanelKebutuhanMasyarakatLayout.setVerticalGroup(
            PanelKebutuhanMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKebutuhanMasyarakatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelKebutuhanMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(NoDataKebutuhan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelKebutuhanMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(nama_kebutuhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelKebutuhanMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lokasi_kebutuhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelKebutuhanMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SimpanKebutuhan)
                    .addComponent(BatalKebutuhan)
                    .addComponent(SelesaiKebutuhan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelPerbaikanMasyarakat.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Data Perbaikan Infrastruktur"));

        jLabel7.setText("Nama Perbaikan");

        jLabel8.setText("No Data Perbaikan");

        nama_perbaikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nama_perbaikanActionPerformed(evt);
            }
        });

        jLabel10.setText("Lokasi");

        SimpanPerbaikan.setText("Simpan");
        SimpanPerbaikan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SimpanPerbaikanMouseClicked(evt);
            }
        });
        SimpanPerbaikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SimpanPerbaikanActionPerformed(evt);
            }
        });

        BatalPerbaikan.setText("Batal");
        BatalPerbaikan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BatalPerbaikanMouseClicked(evt);
            }
        });
        BatalPerbaikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BatalPerbaikanActionPerformed(evt);
            }
        });

        SelesaiPerbaikan.setText("Selesai");
        SelesaiPerbaikan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SelesaiPerbaikanMouseClicked(evt);
            }
        });
        SelesaiPerbaikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelesaiPerbaikanActionPerformed(evt);
            }
        });

        NoDataPerbaikan.setText("0");

        javax.swing.GroupLayout PanelPerbaikanMasyarakatLayout = new javax.swing.GroupLayout(PanelPerbaikanMasyarakat);
        PanelPerbaikanMasyarakat.setLayout(PanelPerbaikanMasyarakatLayout);
        PanelPerbaikanMasyarakatLayout.setHorizontalGroup(
            PanelPerbaikanMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPerbaikanMasyarakatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPerbaikanMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelPerbaikanMasyarakatLayout.createSequentialGroup()
                        .addGroup(PanelPerbaikanMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelPerbaikanMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NoDataPerbaikan)
                            .addComponent(lokasi_perbaikan, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nama_perbaikan, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelPerbaikanMasyarakatLayout.createSequentialGroup()
                        .addComponent(SimpanPerbaikan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BatalPerbaikan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addComponent(SelesaiPerbaikan)))
                .addContainerGap())
        );
        PanelPerbaikanMasyarakatLayout.setVerticalGroup(
            PanelPerbaikanMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPerbaikanMasyarakatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPerbaikanMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(NoDataPerbaikan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPerbaikanMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(nama_perbaikan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPerbaikanMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lokasi_perbaikan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelPerbaikanMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SimpanPerbaikan)
                    .addComponent(BatalPerbaikan)
                    .addComponent(SelesaiPerbaikan))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        dataKebutuhanMasyarakat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(dataKebutuhanMasyarakat);

        dataPerbaikanMasyarakat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(dataPerbaikanMasyarakat);

        javax.swing.GroupLayout PanelMasyarakatLayout = new javax.swing.GroupLayout(PanelMasyarakat);
        PanelMasyarakat.setLayout(PanelMasyarakatLayout);
        PanelMasyarakatLayout.setHorizontalGroup(
            PanelMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMasyarakatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
                    .addGroup(PanelMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(PanelKebutuhanMasyarakat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(KebutuhanInfrastruktur, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)))
                .addGap(4, 4, 4)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, 0, 0, Short.MAX_VALUE)
                    .addGroup(PanelMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(PerbaikanInfrastruktur, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PanelPerbaikanMasyarakat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelMasyarakatLayout.setVerticalGroup(
            PanelMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMasyarakatLayout.createSequentialGroup()
                .addGroup(PanelMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelMasyarakatLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(KebutuhanInfrastruktur, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PerbaikanInfrastruktur, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PanelKebutuhanMasyarakat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PanelPerbaikanMasyarakat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelMasyarakatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        PanelMasyarakatLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {KebutuhanInfrastruktur, PerbaikanInfrastruktur});

        keluar.setText("Keluar");
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });

        PanelStaff.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel Staff"));

        DataKebPembangunan.setText("Data Bahan Pembangunan");
        DataKebPembangunan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DataKebPembangunanActionPerformed(evt);
            }
        });

        DataPerbaik.setText("Data Perbaikan");
        DataPerbaik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DataPerbaikActionPerformed(evt);
            }
        });

        PanelPembangunan.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel Data Pembangunan"));

        jLabel23.setText("No Pembangunan");

        jLabel25.setText("No Data Kebutuhan");

        jLabel26.setText("Lokasi");

        jLabel27.setText("No Kebutuhan Pembangunan");

        jLabel28.setText("Daftar Bahan Pembangunan");

        jLabel29.setText("Keterangan");

        LokasiPembangunan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LokasiPembangunanActionPerformed(evt);
            }
        });

        TBPembangunan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TBPembangunanActionPerformed(evt);
            }
        });

        NoPembangunan.setText("0");

        CDataKebutuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        CDataKebutuhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CDataKebutuhanActionPerformed(evt);
            }
        });

        CKebPembangunan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        CKebPembangunan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CKebPembangunanActionPerformed(evt);
            }
        });

        SimpanPembangunan.setText("Simpan");
        SimpanPembangunan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SimpanPembangunanActionPerformed(evt);
            }
        });

        UbahPembangunan.setText("Ubah");
        UbahPembangunan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UbahPembangunanActionPerformed(evt);
            }
        });

        HapusPembangunan.setText("Hapus");
        HapusPembangunan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HapusPembangunanActionPerformed(evt);
            }
        });

        DBPembangunan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DBPembangunanActionPerformed(evt);
            }
        });

        KetPembangunan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KetPembangunanActionPerformed(evt);
            }
        });

        jLabel30.setText("Total Biaya");

        NamaPembangunan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamaPembangunanActionPerformed(evt);
            }
        });

        jLabel24.setText("Nama Pembangunan");

        BatalPembangunan.setText("Batal");
        BatalPembangunan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BatalPembangunanActionPerformed(evt);
            }
        });

        SelesaiPembangunan.setText("Selesai");
        SelesaiPembangunan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelesaiPembangunanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelPembangunanLayout = new javax.swing.GroupLayout(PanelPembangunan);
        PanelPembangunan.setLayout(PanelPembangunanLayout);
        PanelPembangunanLayout.setHorizontalGroup(
            PanelPembangunanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPembangunanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPembangunanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jLabel23)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jLabel25)
                    .addComponent(jLabel24)
                    .addGroup(PanelPembangunanLayout.createSequentialGroup()
                        .addComponent(SimpanPembangunan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(UbahPembangunan)))
                .addGroup(PanelPembangunanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelPembangunanLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(PanelPembangunanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CDataKebutuhan, 0, 197, Short.MAX_VALUE)
                            .addComponent(DBPembangunan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                            .addComponent(NoPembangunan)
                            .addComponent(LokasiPembangunan, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                            .addComponent(CKebPembangunan, 0, 197, Short.MAX_VALUE)
                            .addComponent(TBPembangunan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                            .addComponent(NamaPembangunan, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                            .addComponent(KetPembangunan, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)))
                    .addGroup(PanelPembangunanLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(HapusPembangunan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BatalPembangunan)
                        .addGap(18, 18, 18)
                        .addComponent(SelesaiPembangunan)))
                .addContainerGap())
        );
        PanelPembangunanLayout.setVerticalGroup(
            PanelPembangunanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPembangunanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPembangunanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(NoPembangunan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPembangunanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(CDataKebutuhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPembangunanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(NamaPembangunan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPembangunanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(LokasiPembangunan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPembangunanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(CKebPembangunan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPembangunanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(DBPembangunan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPembangunanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TBPembangunan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addGap(11, 11, 11)
                .addGroup(PanelPembangunanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(KetPembangunan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelPembangunanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SimpanPembangunan)
                    .addComponent(UbahPembangunan)
                    .addComponent(HapusPembangunan)
                    .addComponent(BatalPembangunan)
                    .addComponent(SelesaiPembangunan))
                .addGap(325, 325, 325))
        );

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        tabelLapKeb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelLapKeb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelLapKebMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabelLapKebMouseEntered(evt);
            }
        });
        jScrollPane5.setViewportView(tabelLapKeb);

        tabelLapPer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelLapPer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelLapPerMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tabelLapPer);

        PanelPerbaikan.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel Data Perbaikan"));

        jLabel31.setText("No Perbaikan");

        jLabel32.setText("No Data Perbaikan");

        jLabel33.setText("Lokasi");

        jLabel34.setText("No Kebutuhan Perbaikan");

        jLabel35.setText("Daftar Bahan Perbaikan");

        jLabel36.setText("Keterangan");

        LokasiPerbaikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LokasiPerbaikanActionPerformed(evt);
            }
        });

        TBPerbaikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TBPerbaikanActionPerformed(evt);
            }
        });

        NoPerbaikan.setText("0");

        CDataPerbaikan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        CDataPerbaikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CDataPerbaikanActionPerformed(evt);
            }
        });

        CKebPerbaikan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        CKebPerbaikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CKebPerbaikanActionPerformed(evt);
            }
        });

        SimpanDatPerbaikan.setText("Simpan");
        SimpanDatPerbaikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SimpanDatPerbaikanActionPerformed(evt);
            }
        });

        UbahDatPerbaikan.setText("Ubah");
        UbahDatPerbaikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UbahDatPerbaikanActionPerformed(evt);
            }
        });

        HapusDatPerbaikan.setText("Hapus");
        HapusDatPerbaikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HapusDatPerbaikanActionPerformed(evt);
            }
        });

        DBPerbaikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DBPerbaikanActionPerformed(evt);
            }
        });

        KetPerbaikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KetPerbaikanActionPerformed(evt);
            }
        });

        jLabel37.setText("Total Biaya");

        NamaPerbaikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamaPerbaikanActionPerformed(evt);
            }
        });

        jLabel38.setText("Nama Perbaikan");

        BatalDatPerbaikan.setText("Batal");
        BatalDatPerbaikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BatalDatPerbaikanActionPerformed(evt);
            }
        });

        SelesaiDatPerbaikan.setText("Selesai");
        SelesaiDatPerbaikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelesaiDatPerbaikanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelPerbaikanLayout = new javax.swing.GroupLayout(PanelPerbaikan);
        PanelPerbaikan.setLayout(PanelPerbaikanLayout);
        PanelPerbaikanLayout.setHorizontalGroup(
            PanelPerbaikanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPerbaikanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPerbaikanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jLabel31)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37)
                    .addComponent(jLabel32)
                    .addComponent(jLabel38)
                    .addGroup(PanelPerbaikanLayout.createSequentialGroup()
                        .addComponent(SimpanDatPerbaikan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(UbahDatPerbaikan, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(PanelPerbaikanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LokasiPerbaikan, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addGroup(PanelPerbaikanLayout.createSequentialGroup()
                        .addComponent(HapusDatPerbaikan)
                        .addGap(18, 18, 18)
                        .addComponent(BatalDatPerbaikan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SelesaiDatPerbaikan))
                    .addComponent(NoPerbaikan)
                    .addComponent(DBPerbaikan, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addComponent(CKebPerbaikan, 0, 209, Short.MAX_VALUE)
                    .addComponent(NamaPerbaikan, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addComponent(CDataPerbaikan, 0, 209, Short.MAX_VALUE)
                    .addComponent(TBPerbaikan, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addComponent(KetPerbaikan, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelPerbaikanLayout.setVerticalGroup(
            PanelPerbaikanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPerbaikanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPerbaikanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(NoPerbaikan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPerbaikanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(CDataPerbaikan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPerbaikanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel38)
                    .addComponent(NamaPerbaikan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPerbaikanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(LokasiPerbaikan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPerbaikanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(CKebPerbaikan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPerbaikanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(DBPerbaikan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPerbaikanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TBPerbaikan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGap(11, 11, 11)
                .addGroup(PanelPerbaikanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(KetPerbaikan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelPerbaikanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SimpanDatPerbaikan)
                    .addComponent(UbahDatPerbaikan)
                    .addComponent(HapusDatPerbaikan)
                    .addComponent(BatalDatPerbaikan)
                    .addComponent(SelesaiDatPerbaikan))
                .addGap(325, 325, 325))
        );

        DataPembang.setText("Data Pembangunan");
        DataPembang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DataPembangActionPerformed(evt);
            }
        });

        PanelKebPem.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel Kebutuhan Pembangunan"));

        jLabel39.setText("No Kebutuhan Pembangunan");

        jLabel40.setText("Daftar Kebutuhan");

        jLabel44.setText("Keterangan");

        KetKeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KetKebActionPerformed(evt);
            }
        });

        NoKebPem.setText("0");

        SimpanKebPem.setText("Simpan");
        SimpanKebPem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SimpanKebPemActionPerformed(evt);
            }
        });

        UbahKebPem.setText("Ubah");
        UbahKebPem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UbahKebPemActionPerformed(evt);
            }
        });

        HapusKebPem.setText("Hapus");
        HapusKebPem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HapusKebPemActionPerformed(evt);
            }
        });

        DafKebutuhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DafKebutuhanActionPerformed(evt);
            }
        });

        TBKebutuhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TBKebutuhanActionPerformed(evt);
            }
        });

        jLabel46.setText("Total Biaya");

        BatalKebPem.setText("Batal");
        BatalKebPem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BatalKebPemActionPerformed(evt);
            }
        });

        SelesaiKebPem.setText("Selesai");
        SelesaiKebPem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelesaiKebPemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelKebPemLayout = new javax.swing.GroupLayout(PanelKebPem);
        PanelKebPem.setLayout(PanelKebPemLayout);
        PanelKebPemLayout.setHorizontalGroup(
            PanelKebPemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKebPemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelKebPemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40)
                    .addComponent(jLabel46)
                    .addGroup(PanelKebPemLayout.createSequentialGroup()
                        .addComponent(SimpanKebPem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(UbahKebPem))
                    .addComponent(jLabel44))
                .addGroup(PanelKebPemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelKebPemLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(HapusKebPem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BatalKebPem)
                        .addGap(18, 18, 18)
                        .addComponent(SelesaiKebPem))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelKebPemLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(PanelKebPemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DafKebutuhan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                            .addComponent(NoKebPem)
                            .addComponent(KetKeb, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                            .addComponent(TBKebutuhan, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))))
                .addContainerGap())
        );
        PanelKebPemLayout.setVerticalGroup(
            PanelKebPemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKebPemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelKebPemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(NoKebPem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelKebPemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel40)
                    .addComponent(DafKebutuhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelKebPemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TBKebutuhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelKebPemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(KetKeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelKebPemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SimpanKebPem)
                    .addComponent(UbahKebPem)
                    .addComponent(HapusKebPem)
                    .addComponent(SelesaiKebPem)
                    .addComponent(BatalKebPem))
                .addGap(325, 325, 325))
        );

        TKebPem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TKebPem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TKebPemMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(TKebPem);

        DataKebPerbaikan.setText("Data Bahan Perbaikan");
        DataKebPerbaikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DataKebPerbaikanActionPerformed(evt);
            }
        });

        PanelKebPerb.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel Kebutuhan Perbaikan"));

        jLabel41.setText("No Kebutuhan Perbaikan");

        jLabel42.setText("Daftar Kebutuhan");

        jLabel45.setText("Keterangan");

        KetPerb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KetPerbActionPerformed(evt);
            }
        });

        NoKebPerb.setText("0");

        SimpanKebPerb.setText("Simpan");
        SimpanKebPerb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SimpanKebPerbActionPerformed(evt);
            }
        });

        UbahKebPerb.setText("Ubah");
        UbahKebPerb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UbahKebPerbActionPerformed(evt);
            }
        });

        HapusKebPerb.setText("Hapus");
        HapusKebPerb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HapusKebPerbActionPerformed(evt);
            }
        });

        DafKebPerb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DafKebPerbActionPerformed(evt);
            }
        });

        TBKebPerb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TBKebPerbActionPerformed(evt);
            }
        });

        jLabel47.setText("Total Biaya");

        BatalKebPerb.setText("Batal");
        BatalKebPerb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BatalKebPerbActionPerformed(evt);
            }
        });

        SelesaiKebPerb.setText("Selesai");
        SelesaiKebPerb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelesaiKebPerbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelKebPerbLayout = new javax.swing.GroupLayout(PanelKebPerb);
        PanelKebPerb.setLayout(PanelKebPerbLayout);
        PanelKebPerbLayout.setHorizontalGroup(
            PanelKebPerbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKebPerbLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelKebPerbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42)
                    .addComponent(jLabel47)
                    .addGroup(PanelKebPerbLayout.createSequentialGroup()
                        .addComponent(SimpanKebPerb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(UbahKebPerb))
                    .addComponent(jLabel45))
                .addGroup(PanelKebPerbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelKebPerbLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(HapusKebPerb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(BatalKebPerb)
                        .addGap(18, 18, 18)
                        .addComponent(SelesaiKebPerb))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelKebPerbLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(PanelKebPerbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DafKebPerb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                            .addComponent(NoKebPerb)
                            .addComponent(KetPerb, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                            .addComponent(TBKebPerb, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))))
                .addContainerGap())
        );
        PanelKebPerbLayout.setVerticalGroup(
            PanelKebPerbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKebPerbLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelKebPerbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(NoKebPerb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelKebPerbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel42)
                    .addComponent(DafKebPerb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelKebPerbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TBKebPerb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelKebPerbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(KetPerb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelKebPerbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SimpanKebPerb)
                    .addComponent(UbahKebPerb)
                    .addComponent(HapusKebPerb)
                    .addComponent(SelesaiKebPerb)
                    .addComponent(BatalKebPerb))
                .addGap(325, 325, 325))
        );

        TKebPerb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TKebPerb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TKebPerbMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(TKebPerb);

        javax.swing.GroupLayout PanelStaffLayout = new javax.swing.GroupLayout(PanelStaff);
        PanelStaff.setLayout(PanelStaffLayout);
        PanelStaffLayout.setHorizontalGroup(
            PanelStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelStaffLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, 0, 0, Short.MAX_VALUE)
                    .addComponent(PanelKebPem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DataKebPembangunan, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, 0, 0, Short.MAX_VALUE)
                    .addComponent(DataPembang, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addComponent(PanelPembangunan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, 0, 0, Short.MAX_VALUE)
                    .addComponent(DataPerbaik, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                    .addComponent(DataKebPerbaikan, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                    .addComponent(PanelPerbaikan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, 0, 0, Short.MAX_VALUE)
                    .addComponent(PanelKebPerb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelStaffLayout.setVerticalGroup(
            PanelStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelStaffLayout.createSequentialGroup()
                .addGroup(PanelStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelStaffLayout.createSequentialGroup()
                        .addGroup(PanelStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DataPembang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DataPerbaik))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PanelPembangunan, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PanelPerbaikan, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PanelStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PanelStaffLayout.createSequentialGroup()
                                .addComponent(DataKebPembangunan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PanelKebPem, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelStaffLayout.createSequentialGroup()
                                .addComponent(DataKebPerbaikan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PanelKebPerb, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 809, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        PanelStaffLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {DataKebPembangunan, DataPerbaik});

        PanelBendahara.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel Bendahara"));

        PanelDanaKas.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel Dana Kas"));

        jLabel11.setText("No Dana Kas ");

        jLabel15.setText("Penerimaan");

        jLabel19.setText("Pengeluaran");

        jLabel20.setText("Saldo");

        jLabel21.setText("Keterangan");

        BatalKas.setText("Batal");
        BatalKas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BatalKasActionPerformed(evt);
            }
        });

        SimpanKas.setText("Simpan");
        SimpanKas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SimpanKasActionPerformed(evt);
            }
        });

        UbahKas.setText("Ubah");
        UbahKas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UbahKasActionPerformed(evt);
            }
        });

        HapusKas.setText("Hapus");
        HapusKas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HapusKasActionPerformed(evt);
            }
        });

        datadanakas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        datadanakas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datadanakasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(datadanakas);

        NoDanaKas.setText("0");

        javax.swing.GroupLayout PanelDanaKasLayout = new javax.swing.GroupLayout(PanelDanaKas);
        PanelDanaKas.setLayout(PanelDanaKasLayout);
        PanelDanaKasLayout.setHorizontalGroup(
            PanelDanaKasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDanaKasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDanaKasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .addGroup(PanelDanaKasLayout.createSequentialGroup()
                        .addGroup(PanelDanaKasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel21)
                            .addComponent(jLabel20)
                            .addComponent(SimpanKas)
                            .addComponent(jLabel19)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(PanelDanaKasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDanaKasLayout.createSequentialGroup()
                                .addComponent(UbahKas)
                                .addGap(18, 18, 18)
                                .addComponent(HapusKas)
                                .addGap(18, 18, 18)
                                .addComponent(BatalKas)
                                .addGap(63, 63, 63))
                            .addComponent(NoDanaKas)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDanaKasLayout.createSequentialGroup()
                                .addGroup(PanelDanaKasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Pengeluaran, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                                    .addComponent(Penerimaan))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDanaKasLayout.createSequentialGroup()
                                .addGroup(PanelDanaKasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(KetKas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                                    .addComponent(Saldo))
                                .addContainerGap())))))
        );
        PanelDanaKasLayout.setVerticalGroup(
            PanelDanaKasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDanaKasLayout.createSequentialGroup()
                .addGroup(PanelDanaKasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(NoDanaKas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDanaKasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(Penerimaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDanaKasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelDanaKasLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21))
                    .addGroup(PanelDanaKasLayout.createSequentialGroup()
                        .addComponent(Pengeluaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Saldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(KetKas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelDanaKasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SimpanKas)
                    .addComponent(HapusKas)
                    .addComponent(UbahKas)
                    .addComponent(BatalKas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelBendaharaLayout = new javax.swing.GroupLayout(PanelBendahara);
        PanelBendahara.setLayout(PanelBendaharaLayout);
        PanelBendaharaLayout.setHorizontalGroup(
            PanelBendaharaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelBendaharaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanelDanaKas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelBendaharaLayout.setVerticalGroup(
            PanelBendaharaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBendaharaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelDanaKas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelLurah.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel Lurah"));

        LapPembangunan.setText("Laporan Pembangunan");
        LapPembangunan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LapPembangunanActionPerformed(evt);
            }
        });

        LapPerbaikan.setText("Laporan Perbaikan");
        LapPerbaikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LapPerbaikanActionPerformed(evt);
            }
        });

        LapLaporanDanaKas.setText("Laporan Dana Kas");
        LapLaporanDanaKas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LapLaporanDanaKasActionPerformed(evt);
            }
        });

        PanelLurahPencarian.setBorder(javax.swing.BorderFactory.createTitledBorder("Daftar List"));

        Pencarian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PencarianKeyReleased(evt);
            }
        });

        tabelPencarian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tabelPencarian);

        Cari_status.setText("Cari");

        javax.swing.GroupLayout PanelLurahPencarianLayout = new javax.swing.GroupLayout(PanelLurahPencarian);
        PanelLurahPencarian.setLayout(PanelLurahPencarianLayout);
        PanelLurahPencarianLayout.setHorizontalGroup(
            PanelLurahPencarianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLurahPencarianLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelLurahPencarianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLurahPencarianLayout.createSequentialGroup()
                        .addComponent(Pencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
                        .addComponent(Cari_status)
                        .addGap(7, 7, 7))
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        PanelLurahPencarianLayout.setVerticalGroup(
            PanelLurahPencarianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLurahPencarianLayout.createSequentialGroup()
                .addGroup(PanelLurahPencarianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cari_status, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Pencarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelLurahLayout = new javax.swing.GroupLayout(PanelLurah);
        PanelLurah.setLayout(PanelLurahLayout);
        PanelLurahLayout.setHorizontalGroup(
            PanelLurahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLurahLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelLurahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelLurahLayout.createSequentialGroup()
                        .addComponent(LapPembangunan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LapPerbaikan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LapLaporanDanaKas))
                    .addComponent(PanelLurahPencarian, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        PanelLurahLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {LapLaporanDanaKas, LapPembangunan, LapPerbaikan});

        PanelLurahLayout.setVerticalGroup(
            PanelLurahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLurahLayout.createSequentialGroup()
                .addGroup(PanelLurahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LapPembangunan)
                    .addComponent(LapPerbaikan)
                    .addComponent(LapLaporanDanaKas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelLurahPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE))
                        .addGap(339, 339, 339)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userlogin1))
                        .addGap(227, 227, 227))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 1197, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PLoginAkses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelLurah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelBendahara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelMasyarakat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(userlogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelMasyarakat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PLoginAkses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelBendahara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelLurah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        InformasiUser.setBackground(new java.awt.Color(204, 204, 204));

        jLabel9.setText("Selamat Datang Disistem,");

        namauser.setText("Nama User Login");

        javax.swing.GroupLayout InformasiUserLayout = new javax.swing.GroupLayout(InformasiUser);
        InformasiUser.setLayout(InformasiUserLayout);
        InformasiUserLayout.setHorizontalGroup(
            InformasiUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InformasiUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InformasiUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(namauser))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        InformasiUserLayout.setVerticalGroup(
            InformasiUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InformasiUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(namauser)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(InformasiUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(InformasiUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtLogin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtLogin1ActionPerformed
        i=i+1; 
        try
        {
            Class.forName(driver);
            Connection kon = (Connection) DriverManager.getConnection(database,user,pass);
            Statement stt=(Statement) kon.createStatement();
            String SQL = "  SELECT id_user,pass,lev_acc " +
                    "       FROM pos_user " +
                    "       WHERE id_user='"+TxtUser.getText().replaceAll("'", "")+"' " +
                    "       AND pass = '"+TxtPassword.getText().replaceAll("'", "")+"'";
            ResultSet res = stt.executeQuery(SQL);
            if(res.next()){
                userakses = res.getString(3);
                userlogin1.setText(userakses);
                namauser.setText(userakses);
                    if (userakses.equals("Masyarakat")) {
                        PanelMasyarakat.setVisible(true);
                     } else if (userakses.equals("Staff")) {
                        PanelStaff.setVisible(true);
                        
                    } else if (userakses.equals("Bendahara")) {
                       PanelBendahara.setVisible(true);
                    } else if (userakses.equals("Lurah")) {
                       PanelLurah.setVisible(true);
                    }
                //panggil form
                PLoginAkses.setVisible(false);
                InformasiUser.setVisible(true);
                userlogin1.setVisible(true);
                keluar.setVisible(true);
                      
                
                login=true;
            }
            else{
                if((i==1)&&(login==false) ){
                    JOptionPane.showMessageDialog(null,":( Gagal Login, Silahkan Coba Lagi (2).");
                }
                else if(i == 2){
                    JOptionPane.showMessageDialog(null,":( Gagal Login, Silahkan Coba Lagi (1).");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Maaf, Anda telah gagal login 3 kali, sistem akan keluar");
                    System.exit(0);
                }
            }
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Gagal Terhubung Ke database",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }//GEN-LAST:event_BtLogin1ActionPerformed

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
     PLoginAkses.setVisible(true);
     InformasiUser.setVisible(false);
     userlogin1.setVisible(false);
     TxtPassword.setText("");
     TxtUser.setText("");
     keluar.setVisible(false);
     PanelMasyarakat.setVisible(false);
     PanelStaff.setVisible(false);
     PanelBendahara.setVisible(false);
     PanelLurah.setVisible(false);
     PanelLurahPencarian.setVisible(false);
     PanelKebPem.setVisible(false);
     PanelKebPerb.setVisible(false);

    }//GEN-LAST:event_keluarActionPerformed

    private void nama_kebutuhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nama_kebutuhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nama_kebutuhanActionPerformed

    private void SimpanKebutuhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SimpanKebutuhanMouseClicked
        String data[]=new String[3];
        
        if (nama_kebutuhan.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong, Silahkan dilengkapi");
            nama_kebutuhan.requestFocus();
        } else {
        try {
         Class.forName(driver);
         Connection kon = (Connection) DriverManager.getConnection(database, user, pass);
         Statement stt = (Statement) kon.createStatement();
         String SQL = "INSERT INTO data_kebutuhan (No_DataKebutuhan,"
                                                       + "Nama_Kebutuhan,"
                                                       + "Lokasi)" 
                                                       + "VALUES" 
                                                       + "('"+NoDataKebutuhan.getText()+"',"                                                   
                                                       + "'"+nama_kebutuhan.getText()+"',"
                                                       + "'"+lokasi_kebutuhan.getText()+"')";
                      stt.executeUpdate(SQL);
                      data[0]  = NoDataKebutuhan.getText();
                      data[1]  = nama_kebutuhan.getText();
                      data[2]  = lokasi_kebutuhan.getText();       
                      refreshtable();
                      settableload();
                      
                      stt.close();
                      kon.close();
                      auto_idkebutuhan();
                      nama_kebutuhan.setText("");
                      lokasi_kebutuhan.setText("");
                   
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Gagal Terhubung Ke Database", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_SimpanKebutuhanMouseClicked

    private void BatalKebutuhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BatalKebutuhanActionPerformed
        auto_idkebutuhan();
        nama_kebutuhan.setText("");
        lokasi_kebutuhan.setText("");
    }//GEN-LAST:event_BatalKebutuhanActionPerformed

    private void BatalKebutuhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BatalKebutuhanMouseClicked
        auto_idkebutuhan();
        nama_kebutuhan.setText("");
        lokasi_kebutuhan.setText("");
        
    }//GEN-LAST:event_BatalKebutuhanMouseClicked

    private void KebutuhanInfrastrukturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KebutuhanInfrastrukturMouseClicked
       PanelKebutuhanMasyarakat.setVisible(true);
    }//GEN-LAST:event_KebutuhanInfrastrukturMouseClicked

    private void SimpanKebutuhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SimpanKebutuhanActionPerformed
        
    }//GEN-LAST:event_SimpanKebutuhanActionPerformed

    private void nama_perbaikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nama_perbaikanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nama_perbaikanActionPerformed

    private void SimpanPerbaikanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SimpanPerbaikanMouseClicked
        String data1[]=new String[3];
        
        if (nama_perbaikan.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong, Silahkan dilengkapi");
            nama_perbaikan.requestFocus();
        } else {
        try {
         Class.forName(driver);
         Connection kon = (Connection) DriverManager.getConnection(database, user, pass);
         Statement stt = (Statement) kon.createStatement();
         String SQL = "INSERT INTO data_perbaikan (No_DataPerbaikan,"
                                                       + "Nama_Perbaikan,"
                                                       + "lokasi)" 
                                                       + "VALUES" 
                                                       + "('"+NoDataPerbaikan.getText()+"',"                                                   
                                                       + "'"+nama_perbaikan.getText()+"',"
                                                       + "'"+lokasi_perbaikan.getText()+"')";
                      stt.executeUpdate(SQL);
                      data1[0]  = NoDataPerbaikan.getText();
                      data1[1]  = nama_perbaikan.getText();
                      data1[2]  = lokasi_perbaikan.getText();       

                      refreshtable1();
                      settableload1();
                      stt.close();
                      kon.close();
                      auto_idperbaikan();
                      nama_perbaikan.setText("");
                      lokasi_perbaikan.setText("");
                      
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Gagal Terhubung Ke Database", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_SimpanPerbaikanMouseClicked

    private void SimpanPerbaikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SimpanPerbaikanActionPerformed

    }//GEN-LAST:event_SimpanPerbaikanActionPerformed

    private void BatalPerbaikanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BatalPerbaikanMouseClicked
        auto_idperbaikan();
        nama_perbaikan.setText("");
        lokasi_perbaikan.setText("");
    }//GEN-LAST:event_BatalPerbaikanMouseClicked

    private void BatalPerbaikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BatalPerbaikanActionPerformed
        auto_idperbaikan();
        nama_perbaikan.setText("");
        lokasi_perbaikan.setText("");
    }//GEN-LAST:event_BatalPerbaikanActionPerformed

    private void PerbaikanInfrastrukturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PerbaikanInfrastrukturActionPerformed
        PanelPerbaikanMasyarakat.setVisible(true);
        PanelKebutuhanMasyarakat.setVisible(false);
    }//GEN-LAST:event_PerbaikanInfrastrukturActionPerformed

    private void KebutuhanInfrastrukturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KebutuhanInfrastrukturActionPerformed
        PanelKebutuhanMasyarakat.setVisible(true);
        PanelPerbaikanMasyarakat.setVisible(false);
    }//GEN-LAST:event_KebutuhanInfrastrukturActionPerformed

    private void SelesaiKebutuhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelesaiKebutuhanMouseClicked
        PanelKebutuhanMasyarakat.setVisible(false);
        PanelPerbaikanMasyarakat.setVisible(false);
    }//GEN-LAST:event_SelesaiKebutuhanMouseClicked

    private void SelesaiKebutuhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelesaiKebutuhanActionPerformed
       PanelKebutuhanMasyarakat.setVisible(false);
        PanelPerbaikanMasyarakat.setVisible(false);
    }//GEN-LAST:event_SelesaiKebutuhanActionPerformed

    private void SelesaiPerbaikanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelesaiPerbaikanMouseClicked
        PanelKebutuhanMasyarakat.setVisible(false);
        PanelPerbaikanMasyarakat.setVisible(false);
    }//GEN-LAST:event_SelesaiPerbaikanMouseClicked

    private void SelesaiPerbaikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelesaiPerbaikanActionPerformed
        PanelKebutuhanMasyarakat.setVisible(false);
        PanelPerbaikanMasyarakat.setVisible(false);
    }//GEN-LAST:event_SelesaiPerbaikanActionPerformed

    private void TxtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtPasswordKeyPressed
       if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
        i=i+1; 
        try
        {
            Class.forName(driver);
            Connection kon = (Connection) DriverManager.getConnection(database,user,pass);
            Statement stt=(Statement) kon.createStatement();
            String SQL = "  SELECT id_user,pass,lev_acc " +
                    "       FROM pos_user " +
                    "       WHERE id_user='"+TxtUser.getText().replaceAll("'", "")+"' " +
                    "       AND pass = '"+TxtPassword.getText().replaceAll("'", "")+"'";
            ResultSet res = stt.executeQuery(SQL);
            if(res.next()){
                userakses = res.getString(3);
                userlogin1.setText(userakses);
                namauser.setText(userakses);
                    if (userakses.equals("Masyarakat")) {
                        PanelMasyarakat.setVisible(true);
                     } else if (userakses.equals("Staff")) {
                        PanelStaff.setVisible(true);
                        
                    } else if (userakses.equals("Bendahara")) {
                       PanelBendahara.setVisible(true);
                    } else if (userakses.equals("Lurah")) {
                       PanelLurah.setVisible(true);
                    }
                //panggil form
                PLoginAkses.setVisible(false);
                InformasiUser.setVisible(true);
                userlogin1.setVisible(true);
                keluar.setVisible(true);
                      
                
                login=true;
            }
            else{
                if((i==1)&&(login==false) ){
                    JOptionPane.showMessageDialog(null,":( Gagal Login, Silahkan Coba Lagi (2).");
                }
                else if(i == 2){
                    JOptionPane.showMessageDialog(null,":( Gagal Login, Silahkan Coba Lagi (1).");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Maaf, Anda telah gagal login 3 kali, sistem akan keluar");
                    System.exit(0);
                }
            }
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Gagal Terhubung Ke database",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
       }
    }//GEN-LAST:event_TxtPasswordKeyPressed

    private void SimpanKasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SimpanKasActionPerformed
       String data2[]=new String[5];
        
        if (Saldo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong, Silahkan dilengkapi");
            Saldo.requestFocus();
        } else {
        try {
         Class.forName(driver);
         Connection kon = (Connection) DriverManager.getConnection(database, user, pass);
         Statement stt = (Statement) kon.createStatement();
         String SQL = "INSERT INTO dana_kas (No_DanaKas,"
                                                       + "Penerimaan,"
                                                       + "Pengeluaran,"
                                                       + "Saldo,"
                                                       + "Keterangan)"
                                                       + "VALUES" 
                                                       + "('"+NoDanaKas.getText()+"',"                                                   
                                                       + "'"+Penerimaan.getText()+"',"
                                                       + "'"+Pengeluaran.getText()+"',"
                                                       + "'"+Saldo.getText()+"',"
                                                       + "'"+KetKas.getText()+"')";
                      stt.executeUpdate(SQL);
                      data2[0]  = NoDanaKas.getText();
                      data2[1]  = Penerimaan.getText();
                      data2[2]  = Pengeluaran.getText();
                      data2[3]  = Saldo.getText();
                      data2[4]  = KetKas.getText();
                      refreshtable2();
                      settableload2();
                      auto_NoDanaKas();
                      Penerimaan.setText("");
                      Pengeluaran.setText("");
                      Saldo.setText("");
                      KetKas.setText("");
                      
                      stt.close();
                      kon.close();
                      
                              
                   
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Gagal Terhubung Ke Database", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_SimpanKasActionPerformed

    int row = 0;
    public void tampil_danakas() {
        row = datadanakas.getSelectedRow();
        
        NoDanaKas.setText(tableModel2.getValueAt(row, 0).toString());
        Penerimaan.setText(tableModel2.getValueAt(row, 1).toString());
        Pengeluaran.setText(tableModel2.getValueAt(row, 2).toString());
        Saldo.setText(tableModel2.getValueAt(row, 3).toString());
        KetKas.setText(tableModel2.getValueAt(row, 4).toString());
      
      UbahKas.setEnabled(true);  
      HapusKas.setEnabled(true);
      SimpanKas.setEnabled(false);
           
    }
    
    int row1 = 0;
    public void tampil_LapPem() {
        row1 = tabelLapKeb.getSelectedRow();
        
        NoPembangunan.setText(tableModel3.getValueAt(row1, 0).toString());
        CDataKebutuhan.setSelectedItem(tableModel3.getValueAt(row1, 1).toString());
        NamaPembangunan.setText(tableModel3.getValueAt(row1, 2).toString());
        LokasiPembangunan.setText(tableModel3.getValueAt(row1, 3).toString());
        CKebPembangunan.setSelectedItem(tableModel3.getValueAt(row1, 4).toString());
        DBPembangunan.setText(tableModel3.getValueAt(row1, 5).toString());
        TBPembangunan.setText(tableModel3.getValueAt(row1, 6).toString());
        KetPembangunan.setText(tableModel3.getValueAt(row1, 7).toString());
      
      UbahPembangunan.setEnabled(true);  
      HapusPembangunan.setEnabled(true);
      SimpanPembangunan.setEnabled(false);    
     }
    
    
    int row2 = 0;
    public void tampil_LapPer() {
        row2 = tabelLapPer.getSelectedRow();
        
        NoPerbaikan.setText(tableModel4.getValueAt(row2, 0).toString());
        CDataPerbaikan.setSelectedItem(tableModel4.getValueAt(row2, 1).toString());
        NamaPerbaikan.setText(tableModel4.getValueAt(row2, 2).toString());
        LokasiPerbaikan.setText(tableModel4.getValueAt(row2, 3).toString());
        CKebPerbaikan.setSelectedItem(tableModel4.getValueAt(row2, 4).toString());
        DBPerbaikan.setText(tableModel4.getValueAt(row2, 5).toString());
        TBPerbaikan.setText(tableModel4.getValueAt(row2, 6).toString());
        KetPerbaikan.setText(tableModel4.getValueAt(row2, 7).toString());
      
      UbahDatPerbaikan.setEnabled(true);  
      HapusDatPerbaikan.setEnabled(true);
      SimpanDatPerbaikan.setEnabled(false);    
     }
    
     int row3 = 0;
    public void tampil_KebPem() {
        row3 = TKebPem.getSelectedRow();
        
        NoKebPem.setText(tableModel5.getValueAt(row3, 0).toString());
        DafKebutuhan.setText(tableModel5.getValueAt(row3, 1).toString());
        TBKebutuhan.setText(tableModel5.getValueAt(row3, 2).toString());
        KetKeb.setText(tableModel5.getValueAt(row3, 3).toString());
        
      UbahKebPem.setEnabled(true);  
      HapusKebPem.setEnabled(true);
      SimpanKebPem.setEnabled(false);    
     }
    
    int row4 = 0;
    public void tampil_KebPer() {
        row4 = TKebPerb.getSelectedRow();
        
        NoKebPerb.setText(tableModel6.getValueAt(row4, 0).toString());
        DafKebPerb.setText(tableModel6.getValueAt(row4, 1).toString());
        TBKebPerb.setText(tableModel6.getValueAt(row4, 2).toString());
        KetPerb.setText(tableModel6.getValueAt(row4, 3).toString());
        
      UbahKebPerb.setEnabled(true);  
      HapusKebPerb.setEnabled(true);
      SimpanKebPerb.setEnabled(false);    
     }
    
    private void UbahKasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UbahKasActionPerformed
       int  ubah= JOptionPane.showConfirmDialog(null,"Apakah anda ingin Mengubah data ini?", "Konfirmasi",JOptionPane.YES_NO_OPTION);
        
        if(ubah==JOptionPane.YES_OPTION) {
                
            String nodanakas   = NoDanaKas.getText();
            String penerimaan  = Penerimaan.getText();
            String pengeluaran = Pengeluaran.getText();
            String saldo       = Saldo.getText();
            String keterangan  = KetKas.getText();
            
          
        if ((nodanakas.isEmpty()) || (saldo.isEmpty())){
            JOptionPane.showMessageDialog(null,"Data Tidak Boleh Kosong, Slilahkan Dilengkapi");
            Saldo.requestFocus();
        } else {
            try {
                    Class.forName(driver);
                    Connection kon = (Connection) DriverManager.getConnection(database,user, pass);
                    Statement stt = (Statement) kon.createStatement();
                    String SQL = "UPDATE `dana_kas` "
                                    + "SET `No_DanaKas`= '"+nodanakas+"',"
                                    + "`Penerimaan`= '"+penerimaan+"',"
                                    + "`Pengeluaran`= '"+pengeluaran+"',"
                                    + "`Saldo`= '"+saldo+"',"
                                    + "`Keterangan`= '"+keterangan+"'"
                            + "WHERE "
                            + "`No_DanaKas`='"+tableModel2.getValueAt(row, 0).toString()+"';";
                    stt.executeUpdate(SQL);
                    data2[0] = nodanakas;
                    data2[1] = penerimaan;
                    data2[2] = pengeluaran;
                    data2[3] = saldo;
                    data2[4] = keterangan;
                    
                    tableModel2.removeRow(row);
                    tableModel2.insertRow(row, data2);
                    stt.close();
                    kon.close();
                    datadanakas.clearSelection();

                    tableModel2.setRowCount(0);
                    settableload2();
                    auto_NoDanaKas();
                    Penerimaan.setText("");
                    Pengeluaran.setText("");
                    Saldo.setText("");
                    KetKas.setText("");
                    SimpanKas.setEnabled(true);
                    HapusKas.setEnabled(true);
                } catch (ClassNotFoundException | SQLException ex) {
                            System.err.println(ex.getMessage());
            }
        }
            } else if(ubah==JOptionPane.NO_OPTION) {
            BatalKasActionPerformed(evt);
        }
        
    }//GEN-LAST:event_UbahKasActionPerformed

    private void BatalKasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BatalKasActionPerformed
         auto_NoDanaKas();
         Penerimaan.setText("");
         Pengeluaran.setText("");
         Saldo.setText("");
         KetKas.setText("");
    }//GEN-LAST:event_BatalKasActionPerformed

    private void datadanakasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datadanakasMouseClicked
      if(evt.getClickCount() == 1) {
            tampil_danakas();
        }
    }//GEN-LAST:event_datadanakasMouseClicked

    private void HapusKasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HapusKasActionPerformed
      int  hapus= JOptionPane.showConfirmDialog(null,"Apakah anda ingin Menghapus data ini?", "Konfirmasi",JOptionPane.YES_NO_OPTION);
        
        if(hapus==JOptionPane.YES_OPTION) {
        int baris = datadanakas.getSelectedRow();
            if (baris != -1) {
                String nodanakas = datadanakas.getValueAt(baris, 0).toString();
                String SQL = "DELETE FROM dana_kas WHERE No_DanaKas='"+nodanakas+"'";
                tableModel2.removeRow(row);
                Penerimaan.setText("");
                Pengeluaran.setText("");
                Saldo.setText("");
                KetKas.setText("");
                int status = si.infrastruktur.koneksi.koneksiDesa.execute(SQL);
                if (status==1) {
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    auto_NoDanaKas();
                } else {
                    JOptionPane.showMessageDialog(this, "Data gagal dihapus", "Gagal", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pilih Baris Data Terlebih dahulu", "Error !!", JOptionPane.WARNING_MESSAGE);
            }
        } else if(hapus==JOptionPane.NO_OPTION) {
            BatalKasActionPerformed(evt);        }
            SimpanKas.setEnabled(true);
            UbahKas.setEnabled(true);
            BatalKas.setEnabled(true);
    }//GEN-LAST:event_HapusKasActionPerformed

    private void LapPembangunanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LapPembangunanActionPerformed
       PanelLurahPencarian.setVisible(true);
        tabelPencarian.setModel(tableModel3);
        Cari_status.setText("Cari Laporan Pembangunan");
    }//GEN-LAST:event_LapPembangunanActionPerformed

    private void LapLaporanDanaKasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LapLaporanDanaKasActionPerformed
        PanelLurahPencarian.setVisible(true);
        tabelPencarian.setModel(tableModel2);
        Cari_status.setText("Cari Laporan Dana Kas");
    }//GEN-LAST:event_LapLaporanDanaKasActionPerformed

    public void fungsitampil(){
        PanelLurahPencarian.setVisible(true);
        switch (Cari_status.getText()) {
            case "Cari Laporan Dana Kas":
                tabelPencarian.setModel(tableModel2);
                break;
            case "Cari Laporan Perbaikan":
                tabelPencarian.setModel(tableModel4);
                break;
            case "Cari Laporan Pembangunan":
                tabelPencarian.setModel(tableModel3);
                break;
        }
    }
    
    public void fungsicari(){
        switch (Cari_status.getText()) {
            case "Cari Laporan Dana Kas":
                tableModel2.setRowCount(0);
                                         try {
                                            Class.forName(driver);
                                            Connection kon = (Connection) DriverManager.getConnection(database, user, pass);
                                            Statement stt = (Statement) kon.createStatement();
                                            String SQL = "SELECT * FROM dana_kas WHERE No_DanaKas LIKE '%"+Pencarian.getText()+"%' OR Keterangan LIKE '%"+Pencarian.getText()+"%' ";

                                            ResultSet res = stt.executeQuery(SQL);
                                            while (res.next()) {
                                                data2[0] = res.getString(1); 
                                                data2[1] = res.getString(2); 
                                                data2[2] = res.getString(3); 
                                                data2[3] = res.getString(4); 
                                                data2[4] = res.getString(5); 
                                                tableModel2.addRow(data2);
                                            }
                                            res.close();
                                            stt.close();
                                            kon.close();
                                        } catch (Exception ex) {
                                            System.err.println(ex.getMessage());
                                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Gagal Terhubung ke Database", JOptionPane.INFORMATION_MESSAGE);
                                            System.exit(0);
                                        }               
                                       break;
            case "Cari Laporan Perbaikan":
                tableModel4.setRowCount(0);
                                       try {
                                               Class.forName(driver);
                                               Connection kon = (Connection) DriverManager.getConnection(database, user, pass);
                                               Statement stt = (Statement) kon.createStatement();
                                               String SQL = "SELECT * FROM laporan_perbaikan WHERE No_Lap_Perbaikan LIKE '%"+Pencarian.getText()+"%' OR Nama_Perbaikan LIKE '%"+Pencarian.getText()+"%' OR Lokasi LIKE '%"+Pencarian.getText()+"%' OR Keterangan LIKE '%"+Pencarian.getText()+"%' ";

                                               ResultSet res = stt.executeQuery(SQL);
                                               while (res.next()) {
                                                   data4[0] = res.getString(1); 
                                                   data4[1] = res.getString(2); 
                                                   data4[2] = res.getString(3); 
                                                   data4[3] = res.getString(4); 
                                                   data4[4] = res.getString(5); 
                                                   data4[5] = res.getString(6); 
                                                   data4[6] = res.getString(7); 
                                                   data4[7] = res.getString(8); 
                                                   tableModel4.addRow(data4);
                                               }
                                               res.close();
                                               stt.close();
                                               kon.close();
                                            } catch (Exception ex) {
                                               System.err.println(ex.getMessage());
                                               JOptionPane.showMessageDialog(null, ex.getMessage(), "Gagal Terhubung ke Database", JOptionPane.INFORMATION_MESSAGE);
                                               System.exit(0);
                                            }               
                                       break;
            case "Cari Laporan Pembangunan":
                tableModel3.setRowCount(0);
                                       try{
                                            Class.forName(driver);
                                            Connection kon = (Connection) DriverManager.getConnection(database, user, pass);
                                            Statement stt = (Statement) kon.createStatement();
                                            String SQL = "SELECT * FROM laporan_pembangunan WHERE No_Lap_Pembangunan LIKE '%"+Pencarian.getText()+"%' OR Nama_Kebutuhan LIKE '%"+Pencarian.getText()+"%' OR Lokasi LIKE '%"+Pencarian.getText()+"%' OR Keterangan LIKE '%"+Pencarian.getText()+"%' ";

                                            ResultSet res = stt.executeQuery(SQL);
                                            while (res.next()) {
                                                data3[0] = res.getString(1); 
                                                data3[1] = res.getString(2); 
                                                data3[2] = res.getString(3); 
                                                data3[3] = res.getString(4); 
                                                data3[4] = res.getString(5); 
                                                data3[5] = res.getString(6); 
                                                data3[6] = res.getString(7); 
                                                data3[7] = res.getString(8); 
                                                tableModel3.addRow(data3);
                                            }
                                            res.close();
                                            stt.close();
                                            kon.close();
                                        } catch (Exception ex) {
                                            System.err.println(ex.getMessage());
                                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Gagal Terhubung ke Database", JOptionPane.INFORMATION_MESSAGE);
                                            System.exit(0);
                                        }               
                                     break;
                                 }
    }
    
    private void PencarianKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PencarianKeyReleased
        if ("".equals(Pencarian.getText())){   
            fungsitampil();     
          } else {
                tableModel2.setRowCount(0);
                fungsicari();
          } 
    }//GEN-LAST:event_PencarianKeyReleased

    private void NamaPembangunanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaPembangunanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaPembangunanActionPerformed

    private void LokasiPembangunanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LokasiPembangunanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LokasiPembangunanActionPerformed

    private void TBPembangunanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TBPembangunanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TBPembangunanActionPerformed
    
    private void CDataKebutuhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CDataKebutuhanActionPerformed
        tampil_Kebutuhan();
    }//GEN-LAST:event_CDataKebutuhanActionPerformed

    private void CKebPembangunanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CKebPembangunanActionPerformed
        tampil_BahanPembangunan();
    }//GEN-LAST:event_CKebPembangunanActionPerformed

    private void SimpanPembangunanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SimpanPembangunanActionPerformed
        String data3[]=new String[8];
        
        if (NamaPembangunan.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong, Silahkan dilengkapi");
            NamaPembangunan.requestFocus();
        } else {
        try {
         Class.forName(driver);
         Connection kon = (Connection) DriverManager.getConnection(database, user, pass);
         Statement stt = (Statement) kon.createStatement();
         String SQL = "INSERT INTO laporan_pembangunan (No_Lap_Pembangunan,"
                                                       + "No_DataKebutuhan,"
                                                       + "Nama_Kebutuhan,"
                                                       + "Lokasi,"
                                                       + "No_KebPembangunan,"
                                                       + "Daftar_KebPembangunan,"
                                                       + "Total_Biaya,"
                                                       + "Keterangan)" 
                                                       + "VALUES" 
                                                       + "('"+NoPembangunan.getText()+"',"
                                                        + "'"+CDataKebutuhan.getSelectedItem().toString()+"',"                
                                                       + "'"+NamaPembangunan.getText()+"',"
                                                       + "'"+LokasiPembangunan.getText()+"',"
                                                        + "'"+CKebPembangunan.getSelectedItem().toString()+"',"
                                                        + "'"+DBPembangunan.getText().toString()+"',"
                                                       + "'"+TBPembangunan.getText()+"',"
                                                       + "'"+KetPembangunan.getText()+"')";
                      stt.executeUpdate(SQL);
                      data3[0]  = NoPembangunan.getText();                     
                      data3[1]  = CDataKebutuhan.getSelectedItem().toString();
                      data3[2]  = NamaPembangunan.getText();
                      data3[3]  = LokasiPembangunan.getText();      
                      data3[4]  = CKebPembangunan.getSelectedItem().toString();       
                      data3[5]  = DBPembangunan.getText();       
                      data3[6]  = TBPembangunan.getText();       
                      data3[7]  = KetPembangunan.getText();       

                      refreshtable3();
                      settableload3();
                      stt.close();
                      kon.close();
                      auto_NoPembangunan();
                      CDataKebutuhan.setSelectedItem("-");
                      NamaPembangunan.setText("");
                      LokasiPembangunan.setText("");      
                      CKebPembangunan.setSelectedItem("-");       
                      DBPembangunan.setText("");       
                      TBPembangunan.setText("");       
                      KetPembangunan.setText("");
                      SimpanPembangunan.setEnabled(true);
                      HapusPembangunan.setEnabled(true);
                      
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Gagal Terhubung Ke Database", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_SimpanPembangunanActionPerformed

    private void UbahPembangunanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UbahPembangunanActionPerformed
        int  ubah= JOptionPane.showConfirmDialog(null,"Apakah anda ingin Mengubah data ini?", "Konfirmasi",JOptionPane.YES_NO_OPTION);
        
        if(ubah==JOptionPane.YES_OPTION) {
                
            String NoPem  = NoPembangunan.getText();                     
            String DatKeb = CDataKebutuhan.getSelectedItem().toString();
            String NamaPem  = NamaPembangunan.getText();
            String LokasiPem = LokasiPembangunan.getText();      
            String KebPem  = CKebPembangunan.getSelectedItem().toString();       
            String DbPem = DBPembangunan.getText();       
            String TBPem = TBPembangunan.getText();       
            String KetPem = KetPembangunan.getText();  
            
          
        if ((NamaPem.isEmpty()) || (TBPem.isEmpty())){
            JOptionPane.showMessageDialog(null,"Data Tidak Boleh Kosong, Slilahkan Dilengkapi");
            CDataKebutuhan.requestFocus();
        } else {
            try {
                    Class.forName(driver);
                    Connection kon = (Connection) DriverManager.getConnection(database,user, pass);
                    Statement stt = (Statement) kon.createStatement();
                    String SQL = "UPDATE `laporan_pembangunan` "
                                    + "SET `No_Lap_Pembangunan`= '"+NoPem+"',"
                                    + "`No_DataKebutuhan`= '"+DatKeb+"',"
                                    + "`Nama_Kebutuhan`= '"+NamaPem+"',"
                                    + "`Lokasi`= '"+LokasiPem+"',"
                                    + "`No_KebPembangunan`= '"+KebPem+"',"
                                    + "`Daftar_KebPembangunan`= '"+DbPem+"',"
                                    + "`Total_Biaya`= '"+TBPem+"',"
                                    + "`Keterangan`= '"+KetPem+"'"
                            + "WHERE "
                            + "`No_Lap_Pembangunan`='"+tableModel3.getValueAt(row, 0).toString()+"';";
                    stt.executeUpdate(SQL);
                    data3[0] = NoPem;
                    data3[1] = DatKeb;
                    data3[2] = NamaPem;
                    data3[3] = LokasiPem;
                    data3[4] = KebPem;
                    data3[5] = DbPem;
                    data3[6] = TBPem;
                    data3[7] = KetPem;
                    
                    tableModel3.removeRow(row);
                    tableModel3.insertRow(row, data3);
                    stt.close();
                    kon.close();
                    tabelLapKeb.clearSelection();

                    tableModel3.setRowCount(0);
                    settableload3();
                    auto_NoPembangunan();
                                        
                    CDataKebutuhan.setSelectedItem("-");
                    NamaPembangunan.setText("");
                    LokasiPembangunan.setText("");      
                    CKebPembangunan.setSelectedItem("-");       
                    DBPembangunan.setText("");       
                    TBPembangunan.setText("");       
                    KetPembangunan.setText("");
                    SimpanPembangunan.setEnabled(true);
                    HapusPembangunan.setEnabled(true);
                } catch (Exception ex) {
                            System.err.println(ex.getMessage());
            }
        }
            } else if(ubah==JOptionPane.NO_OPTION) {
            BatalKasActionPerformed(evt);
        }
        
    }//GEN-LAST:event_UbahPembangunanActionPerformed

    private void DBPembangunanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DBPembangunanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DBPembangunanActionPerformed

    private void KetPembangunanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KetPembangunanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetPembangunanActionPerformed

    private void DataKebPembangunanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DataKebPembangunanActionPerformed
     PanelKebPem.setVisible(true);
     PanelKebPerb.setVisible(false);
     PanelPembangunan.setVisible(false);
     PanelPerbaikan.setVisible(false);
    }//GEN-LAST:event_DataKebPembangunanActionPerformed

    private void LokasiPerbaikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LokasiPerbaikanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LokasiPerbaikanActionPerformed

    private void TBPerbaikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TBPerbaikanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TBPerbaikanActionPerformed

    private void CDataPerbaikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CDataPerbaikanActionPerformed
       tampil_Perbaikan();
    }//GEN-LAST:event_CDataPerbaikanActionPerformed

    private void CKebPerbaikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CKebPerbaikanActionPerformed
        tampil_BahanPerbaikan();
    }//GEN-LAST:event_CKebPerbaikanActionPerformed

    private void SimpanDatPerbaikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SimpanDatPerbaikanActionPerformed
       String data4[]=new String[8];
        
        if (NamaPerbaikan.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong, Silahkan dilengkapi");
            NamaPerbaikan.requestFocus();
        } else {
        try {
         Class.forName(driver);
         Connection kon = (Connection) DriverManager.getConnection(database, user, pass);
         Statement stt = (Statement) kon.createStatement();
         String SQL = "INSERT INTO laporan_Perbaikan (No_Lap_Perbaikan,"
                                                       + "No_DataPerbaikan,"
                                                       + "Nama_Perbaikan,"
                                                       + "Lokasi,"
                                                       + "No_KebPerbaikan,"
                                                       + "Daftar_KebPerbaikan,"
                                                       + "Total_Biaya,"
                                                       + "Keterangan)" 
                                                       + "VALUES" 
                                                       + "('"+NoPerbaikan.getText()+"',"
                                                        + "'"+CDataPerbaikan.getSelectedItem().toString()+"',"                
                                                       + "'"+NamaPerbaikan.getText()+"',"
                                                       + "'"+LokasiPerbaikan.getText()+"',"
                                                        + "'"+CKebPerbaikan.getSelectedItem().toString()+"',"
                                                        + "'"+DBPerbaikan.getText().toString()+"',"
                                                       + "'"+TBPerbaikan.getText()+"',"
                                                       + "'"+KetPerbaikan.getText()+"')";
                      stt.executeUpdate(SQL);
                      data4[0]  = NoPerbaikan.getText();                     
                      data4[1]  = CDataPerbaikan.getSelectedItem().toString();
                      data4[2]  = NamaPerbaikan.getText();
                      data4[3]  = LokasiPerbaikan.getText();      
                      data4[4]  = CKebPerbaikan.getSelectedItem().toString();       
                      data4[5]  = DBPerbaikan.getText();       
                      data4[6]  = TBPerbaikan.getText();       
                      data4[7]  = KetPerbaikan.getText();       

                      refreshtable4();
                      settableload4();
                      stt.close();
                      kon.close();
                      auto_NoPerbaikan();
                      CDataKebutuhan.setSelectedItem("-");
                    NamaPembangunan.setText("");
                    LokasiPembangunan.setText("");      
                    CKebPembangunan.setSelectedItem("-");       
                    DBPembangunan.setText("");       
                    TBPembangunan.setText("");       
                    KetPembangunan.setText("");
                    SimpanPembangunan.setEnabled(true);
                    HapusPembangunan.setEnabled(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Gagal Terhubung Ke Database", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_SimpanDatPerbaikanActionPerformed

    private void UbahDatPerbaikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UbahDatPerbaikanActionPerformed
        int  ubah= JOptionPane.showConfirmDialog(null,"Apakah anda ingin Mengubah data ini?", "Konfirmasi",JOptionPane.YES_NO_OPTION);
        
        if(ubah==JOptionPane.YES_OPTION) {
                
            String NoPer  = NoPerbaikan.getText();                     
            String DatPer = CDataPerbaikan.getSelectedItem().toString();
            String NamaPer  = NamaPerbaikan.getText();
            String LokasiPer = LokasiPerbaikan.getText();      
            String KebPer  = CKebPerbaikan.getSelectedItem().toString();       
            String DbPer = DBPerbaikan.getText();       
            String TBPer = TBPerbaikan.getText();       
            String KetPer = KetPerbaikan.getText();  
            
          
        if ((NamaPer.isEmpty()) || (TBPer.isEmpty())){
            JOptionPane.showMessageDialog(null,"Data Tidak Boleh Kosong, Slilahkan Dilengkapi");
            NamaPerbaikan.requestFocus();
        } else {
            try {
                    Class.forName(driver);
                    Connection kon = (Connection) DriverManager.getConnection(database,user, pass);
                    Statement stt = (Statement) kon.createStatement();
                    String SQL = "UPDATE `laporan_Perbaikan` "
                                    + "SET `No_Lap_Perbaikan`= '"+NoPer+"',"
                                    + "`No_DataPerbaikan`= '"+DatPer+"',"
                                    + "`Nama_Perbaikan`= '"+NamaPer+"',"
                                    + "`Lokasi`= '"+LokasiPer+"',"
                                    + "`No_KebPerbaikan`= '"+KebPer+"',"
                                    + "`Daftar_KebPerbaikan`= '"+DbPer+"',"
                                    + "`Total_Biaya`= '"+TBPer+"',"
                                    + "`Keterangan`= '"+KetPer+"'"
                            + "WHERE "
                            + "`No_Lap_Perbaikan`='"+tableModel4.getValueAt(row, 0).toString()+"';";
                    stt.executeUpdate(SQL);
                    data4[0] = NoPer;
                    data4[1] = DatPer;
                    data4[2] = NamaPer;
                    data4[3] = LokasiPer;
                    data4[4] = KebPer;
                    data4[5] = DbPer;
                    data4[6] = TBPer;
                    data4[7] = KetPer;
                   
                    tableModel4.removeRow(row);
                    tableModel4.insertRow(row, data4);
                    stt.close();
                    kon.close();
                    tabelLapKeb.clearSelection();

                    tableModel4.setRowCount(0);
                    settableload4();
                    auto_NoPembangunan();
                                        
                    CDataKebutuhan.setSelectedItem("-");
                    NamaPembangunan.setText("");
                    LokasiPembangunan.setText("");      
                    CKebPembangunan.setSelectedItem("-");       
                    DBPembangunan.setText("");       
                    TBPembangunan.setText("");       
                    KetPembangunan.setText("");
                    SimpanPembangunan.setEnabled(true);
                    HapusPembangunan.setEnabled(true);
                } catch (Exception ex) {
                            System.err.println(ex.getMessage());
            }
        }
            } else if(ubah==JOptionPane.NO_OPTION) {
            BatalKasActionPerformed(evt);
        }
        
    }//GEN-LAST:event_UbahDatPerbaikanActionPerformed

    private void DBPerbaikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DBPerbaikanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DBPerbaikanActionPerformed

    private void KetPerbaikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KetPerbaikanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetPerbaikanActionPerformed

    private void NamaPerbaikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaPerbaikanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaPerbaikanActionPerformed

    private void tabelLapKebMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelLapKebMouseClicked
        if(evt.getClickCount() == 1) {
            tampil_LapPem();
        }
    }//GEN-LAST:event_tabelLapKebMouseClicked

    private void DataPerbaikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DataPerbaikActionPerformed
     PanelPembangunan.setVisible(false);
        PanelPerbaikan.setVisible(true);
    }//GEN-LAST:event_DataPerbaikActionPerformed

    private void tabelLapKebMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelLapKebMouseEntered
        
    }//GEN-LAST:event_tabelLapKebMouseEntered

    private void tabelLapPerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelLapPerMouseClicked
        if(evt.getClickCount() == 1) {
            tampil_LapPer();
        }
    }//GEN-LAST:event_tabelLapPerMouseClicked

    private void HapusPembangunanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HapusPembangunanActionPerformed
        int  hapus= JOptionPane.showConfirmDialog(null,"Apakah anda ingin Menghapus data ini?", "Konfirmasi",JOptionPane.YES_NO_OPTION);
        
        if(hapus==JOptionPane.YES_OPTION) {
        int baris = tabelLapKeb.getSelectedRow();
            if (baris != -1) {
                String NoLapPem = tabelLapKeb.getValueAt(baris, 0).toString();
                String SQL = "DELETE FROM laporan_pembangunan WHERE No_Lap_Pembangunan='"+NoLapPem+"'";
                tableModel3.removeRow(row);
               CDataKebutuhan.setSelectedItem("-");
                    NamaPembangunan.setText("");
                    LokasiPembangunan.setText("");      
                    CKebPembangunan.setSelectedItem("-");       
                    DBPembangunan.setText("");       
                    TBPembangunan.setText("");       
                    KetPembangunan.setText("");
                int status = si.infrastruktur.koneksi.koneksiDesa.execute(SQL);
                if (status==1) {
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    auto_NoPembangunan();
                } else {
                    JOptionPane.showMessageDialog(this, "Data gagal dihapus", "Gagal", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pilih Baris Data Terlebih dahulu", "Error !!", JOptionPane.WARNING_MESSAGE);
            }
        } else if(hapus==JOptionPane.NO_OPTION) {
            BatalPembangunanActionPerformed(evt);        }
            SimpanPembangunan.setEnabled(true);
            UbahPembangunan.setEnabled(true);
            BatalPembangunan.setEnabled(true);
    }//GEN-LAST:event_HapusPembangunanActionPerformed

    private void HapusDatPerbaikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HapusDatPerbaikanActionPerformed
         int  hapus= JOptionPane.showConfirmDialog(null,"Apakah anda ingin Menghapus data ini?", "Konfirmasi",JOptionPane.YES_NO_OPTION);
        
        if(hapus==JOptionPane.YES_OPTION) {
        int baris = tabelLapPer.getSelectedRow();
            if (baris != -1) {
                String NoLapPer = tabelLapPer.getValueAt(baris, 0).toString();
                String SQL = "DELETE FROM laporan_Perbaikan WHERE No_Lap_Perbaikan='"+NoLapPer+"'";
                tableModel4.removeRow(row);
                    CDataPerbaikan.setSelectedItem("-");
                    NamaPerbaikan.setText("");
                    LokasiPerbaikan.setText("");      
                    CKebPerbaikan.setSelectedItem("-");       
                    DBPerbaikan.setText("");       
                    TBPerbaikan.setText("");       
                    KetPerbaikan.setText("");
                int status = si.infrastruktur.koneksi.koneksiDesa.execute(SQL);
                if (status==1) {
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    auto_NoPerbaikan();
                } else {
                    JOptionPane.showMessageDialog(this, "Data gagal dihapus", "Gagal", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pilih Baris Data Terlebih dahulu", "Error !!", JOptionPane.WARNING_MESSAGE);
            }
        } else if(hapus==JOptionPane.NO_OPTION) {
            BatalPembangunanActionPerformed(evt);        }
            SimpanPembangunan.setEnabled(true);
            UbahPembangunan.setEnabled(true);
            BatalPembangunan.setEnabled(true);
    }//GEN-LAST:event_HapusDatPerbaikanActionPerformed

    private void BatalPembangunanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BatalPembangunanActionPerformed
        CDataKebutuhan.setSelectedItem("-");
        NamaPembangunan.setText("");
        LokasiPembangunan.setText("");      
        CKebPembangunan.setSelectedItem("-");       
        DBPembangunan.setText("");       
        TBPembangunan.setText("");       
        KetPembangunan.setText("");
        auto_NoPembangunan();
    }//GEN-LAST:event_BatalPembangunanActionPerformed

    private void BatalDatPerbaikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BatalDatPerbaikanActionPerformed
        CDataPerbaikan.setSelectedItem("-");
        NamaPerbaikan.setText("");
        LokasiPerbaikan.setText("");      
        CKebPerbaikan.setSelectedItem("-");       
        DBPerbaikan.setText("");       
        TBPerbaikan.setText("");       
        KetPerbaikan.setText("");
        auto_NoPerbaikan();
    }//GEN-LAST:event_BatalDatPerbaikanActionPerformed

    private void SelesaiPembangunanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelesaiPembangunanActionPerformed
        PanelPembangunan.setVisible(false);
        PanelPerbaikan.setVisible(false);
    }//GEN-LAST:event_SelesaiPembangunanActionPerformed

    private void SelesaiDatPerbaikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelesaiDatPerbaikanActionPerformed
        PanelPembangunan.setVisible(false);
        PanelPerbaikan.setVisible(false);
    }//GEN-LAST:event_SelesaiDatPerbaikanActionPerformed

    private void DataPembangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DataPembangActionPerformed
        PanelPembangunan.setVisible(true);
        PanelPerbaikan.setVisible(false);
    }//GEN-LAST:event_DataPembangActionPerformed

    private void KetKebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KetKebActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetKebActionPerformed

    private void SimpanKebPemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SimpanKebPemActionPerformed
        String data5[]=new String[4];
        
        if (DafKebutuhan.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong, Silahkan dilengkapi");
            DafKebutuhan.requestFocus();
        } else {
        try {
         Class.forName(driver);
         Connection kon = (Connection) DriverManager.getConnection(database, user, pass);
         Statement stt = (Statement) kon.createStatement();
         String SQL = "INSERT INTO bahan_pembangunan (No_KebPembangunan,"
                                                       + "Daftar_KebPembangunan,"
                                                       + "Total_Biaya,"
                                                       + "Keterangan)" 
                                                       + "VALUES" 
                                                       + "('"+NoKebPem.getText()+"',"              
                                                       + "'"+DafKebutuhan.getText()+"',"
                                                       + "'"+TBKebutuhan.getText()+"',"
                                                       + "'"+KetKeb.getText()+"')";
                      stt.executeUpdate(SQL);
                      data5[0]  = NoKebPem.getText();                     
                      data5[1]  = DafKebutuhan.getText();
                      data5[2]  = TBKebutuhan.getText();
                      data5[3]  = KetKeb.getText();
                      
                      refreshtable5();
                      settableload5();
                      stt.close();
                      kon.close();
                      auto_NoKebPembangunan();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Gagal Terhubung Ke Database", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_SimpanKebPemActionPerformed

    private void UbahKebPemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UbahKebPemActionPerformed
        int  ubah= JOptionPane.showConfirmDialog(null,"Apakah anda ingin Mengubah data ini?", "Konfirmasi",JOptionPane.YES_NO_OPTION);
        
        if(ubah==JOptionPane.YES_OPTION) {
                
            String Nokebpem = NoKebPem.getText();                     
            String Dafkebpem = DafKebutuhan.getText();
            String tbkebpem  = TBKebutuhan.getText();   
            String Ketkebpem = KetKeb.getText();  
            
          
        if ((Dafkebpem.isEmpty()) || (tbkebpem.isEmpty())){
            JOptionPane.showMessageDialog(null,"Data Tidak Boleh Kosong, Slilahkan Dilengkapi");
            DafKebutuhan.requestFocus();
        } else {
            try {
                    Class.forName(driver);
                    Connection kon = (Connection) DriverManager.getConnection(database,user, pass);
                    Statement stt = (Statement) kon.createStatement();
                    String SQL = "UPDATE `bahan_pembangunan` "
                                    + "SET `No_KebPembangunan`= '"+Nokebpem+"',"
                                    + "`Daftar_KebPembangunan`= '"+Dafkebpem+"',"
                                    + "`Total_Biaya`= '"+tbkebpem+"',"
                                    + "`Keterangan`= '"+Ketkebpem+"'"
                            + "WHERE "
                            + "`No_KebPembangunan`='"+tableModel5.getValueAt(row, 0).toString()+"';";
                    stt.executeUpdate(SQL);
                    data5[0] = Nokebpem;
                    data5[1] = Dafkebpem;
                    data5[2] = tbkebpem;
                    data5[3] = Ketkebpem;
                    
                   
                    tableModel5.removeRow(row);
                    tableModel5.insertRow(row, data5);
                    stt.close();
                    kon.close();
                    TKebPem.clearSelection();

                    tableModel5.setRowCount(0);
                    settableload5();
                    auto_NoKebPembangunan();
                                        
                    DafKebutuhan.setText("");
                    TBKebutuhan.setText("");
                    KetKeb.setText("");
                    SimpanPembangunan.setEnabled(true);
                    HapusPembangunan.setEnabled(true);
                } catch (Exception ex) {
                            System.err.println(ex.getMessage());
            }
        }
            } else if(ubah==JOptionPane.NO_OPTION) {
            BatalKebPemActionPerformed(evt);
        }
    }//GEN-LAST:event_UbahKebPemActionPerformed

    private void HapusKebPemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HapusKebPemActionPerformed
         int  hapus= JOptionPane.showConfirmDialog(null,"Apakah anda ingin Menghapus data ini?", "Konfirmasi",JOptionPane.YES_NO_OPTION);
        
        if(hapus==JOptionPane.YES_OPTION) {
        int baris = TKebPem.getSelectedRow();
            if (baris != -1) {
                String NoKebPem = TKebPem.getValueAt(baris, 0).toString();
                String SQL = "DELETE FROM bahan_pembangunan WHERE No_KebPembangunan='"+NoKebPem+"'";
                tableModel5.removeRow(row);
                    DafKebutuhan.setText("");
                    TBKebutuhan.setText("");      
                    KetKeb.setText("");       
                int status = si.infrastruktur.koneksi.koneksiDesa.execute(SQL);
                if (status==1) {
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    auto_NoKebPembangunan();
                } else {
                    JOptionPane.showMessageDialog(this, "Data gagal dihapus", "Gagal", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pilih Baris Data Terlebih dahulu", "Error !!", JOptionPane.WARNING_MESSAGE);
            }
        } else if(hapus==JOptionPane.NO_OPTION) {
            BatalKebPemActionPerformed(evt);        }
            SimpanKebPem.setEnabled(true);
            UbahKebPem.setEnabled(true);
            BatalKebPem.setEnabled(true);
    }//GEN-LAST:event_HapusKebPemActionPerformed

    private void DafKebutuhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DafKebutuhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DafKebutuhanActionPerformed

    private void TBKebutuhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TBKebutuhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TBKebutuhanActionPerformed

    private void BatalKebPemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BatalKebPemActionPerformed
        DafKebutuhan.setText("");
        TBKebutuhan.setText("");
        KetKeb.setText("");
        auto_NoKebPembangunan();
    }//GEN-LAST:event_BatalKebPemActionPerformed

    private void SelesaiKebPemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelesaiKebPemActionPerformed
        PanelKebPem.setVisible(false);
        PanelKebPerb.setVisible(false);
    }//GEN-LAST:event_SelesaiKebPemActionPerformed

    private void DataKebPerbaikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DataKebPerbaikanActionPerformed
     PanelKebPem.setVisible(false);
     PanelKebPerb.setVisible(true);
     PanelPembangunan.setVisible(false);
     PanelPerbaikan.setVisible(false);
    }//GEN-LAST:event_DataKebPerbaikanActionPerformed

    private void KetPerbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KetPerbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetPerbActionPerformed

    private void SimpanKebPerbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SimpanKebPerbActionPerformed
        String data6[]=new String[4];
        
        if (DafKebPerb.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong, Silahkan dilengkapi");
            DafKebPerb.requestFocus();
        } else {
        try {
         Class.forName(driver);
         Connection kon = (Connection) DriverManager.getConnection(database, user, pass);
         Statement stt = (Statement) kon.createStatement();
         String SQL = "INSERT INTO bahan_perbaikan (No_KebPerbaikan,"
                                                       + "Daftar_KebPerbaikan,"
                                                       + "Total_Biaya,"
                                                       + "Keterangan)" 
                                                       + "VALUES" 
                                                       + "('"+NoKebPerb.getText()+"',"              
                                                       + "'"+DafKebPerb.getText()+"',"
                                                       + "'"+TBKebPerb.getText()+"',"
                                                       + "'"+KetPerb.getText()+"')";
                      stt.executeUpdate(SQL);
                      data6[0]  = NoKebPerb.getText();                     
                      data6[1]  = DafKebPerb.getText();
                      data6[2]  = TBKebPerb.getText();
                      data6[3]  = KetPerb.getText();
                      
                      refreshtable6();
                      settableload6();
                      stt.close();
                      kon.close();
                      auto_NoKebPerbaikan();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Gagal Terhubung Ke Database", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_SimpanKebPerbActionPerformed

    private void UbahKebPerbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UbahKebPerbActionPerformed
        int  ubah= JOptionPane.showConfirmDialog(null,"Apakah anda ingin Mengubah data ini?", "Konfirmasi",JOptionPane.YES_NO_OPTION);
        
        if(ubah==JOptionPane.YES_OPTION) {
                
            String Nokebperb = NoKebPerb.getText();                     
            String Dafkebperb = DafKebPerb.getText();
            String tbkebperb  = TBKebPerb.getText();   
            String Ketkebperb = KetPerb.getText();  
            
          
        if ((Dafkebperb.isEmpty()) || (tbkebperb.isEmpty())){
            JOptionPane.showMessageDialog(null,"Data Tidak Boleh Kosong, Slilahkan Dilengkapi");
            DafKebPerb.requestFocus();
        } else {
            try {
                    Class.forName(driver);
                    Connection kon = (Connection) DriverManager.getConnection(database,user, pass);
                    Statement stt = (Statement) kon.createStatement();
                    String SQL = "UPDATE `bahan_perbaikan` "
                                    + "SET `No_KebPerbaikan`= '"+Nokebperb+"',"
                                    + "`Daftar_KebPerbaikan`= '"+Dafkebperb+"',"
                                    + "`Total_Biaya`= '"+tbkebperb+"',"
                                    + "`Keterangan`= '"+Ketkebperb+"'"
                            + "WHERE "
                            + "`No_KebPerbaikan`='"+tableModel6.getValueAt(row, 0).toString()+"';";
                    stt.executeUpdate(SQL);
                    data6[0] = Nokebperb;
                    data6[1] = Dafkebperb;
                    data6[2] = tbkebperb;
                    data6[3] = Ketkebperb;
                   
                    tableModel6.removeRow(row);
                    tableModel6.insertRow(row, data6);
                    stt.close();
                    kon.close();
                    TKebPerb.clearSelection();

                    tableModel6.setRowCount(0);
                    settableload6();
                    auto_NoKebPerbaikan();
                                        
                    DafKebPerb.setText("");
                    TBKebPerb.setText("");
                    KetPerb.setText("");
                    SimpanKebPerb.setEnabled(true);
                    HapusKebPerb.setEnabled(true);
                } catch (ClassNotFoundException | SQLException ex) {
                            System.err.println(ex.getMessage());
            }
        }
            } else if(ubah==JOptionPane.NO_OPTION) {
            BatalKebPerbActionPerformed(evt);
        }
    }//GEN-LAST:event_UbahKebPerbActionPerformed

    private void HapusKebPerbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HapusKebPerbActionPerformed
         int  hapus= JOptionPane.showConfirmDialog(null,"Apakah anda ingin Menghapus data ini?", "Konfirmasi",JOptionPane.YES_NO_OPTION);
        
        if(hapus==JOptionPane.YES_OPTION) {
        int baris = TKebPerb.getSelectedRow();
            if (baris != -1) {
                String NoKebPerb = TKebPerb.getValueAt(baris, 0).toString();
                String SQL = "DELETE FROM bahan_perbaikan WHERE No_KebPerbaikan='"+NoKebPerb+"'";
                tableModel6.removeRow(row);
                    DafKebPerb.setText("");
                    TBKebPerb.setText("");      
                    KetPerb.setText("");       
                int status = si.infrastruktur.koneksi.koneksiDesa.execute(SQL);
                if (status==1) {
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    auto_NoKebPerbaikan();
                } else {
                    JOptionPane.showMessageDialog(this, "Data gagal dihapus", "Gagal", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pilih Baris Data Terlebih dahulu", "Error !!", JOptionPane.WARNING_MESSAGE);
            }
        } else if(hapus==JOptionPane.NO_OPTION) {
            BatalKebPerbActionPerformed(evt);        }
            SimpanKebPerb.setEnabled(true);
            UbahKebPerb.setEnabled(true);
            BatalKebPerb.setEnabled(true);
    }//GEN-LAST:event_HapusKebPerbActionPerformed

    private void DafKebPerbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DafKebPerbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DafKebPerbActionPerformed

    private void TBKebPerbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TBKebPerbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TBKebPerbActionPerformed

    private void BatalKebPerbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BatalKebPerbActionPerformed
        DafKebPerb.setText("");
        TBKebPerb.setText("");
        KetPerb.setText("");
        auto_NoKebPerbaikan();
    }//GEN-LAST:event_BatalKebPerbActionPerformed

    private void SelesaiKebPerbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelesaiKebPerbActionPerformed
        PanelKebPem.setVisible(false);
        PanelKebPerb.setVisible(false);
    }//GEN-LAST:event_SelesaiKebPerbActionPerformed

    private void TKebPemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TKebPemMouseClicked
        if(evt.getClickCount() == 1) {
            tampil_KebPem();
        }
    }//GEN-LAST:event_TKebPemMouseClicked

    private void TKebPerbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TKebPerbMouseClicked
       if(evt.getClickCount() == 1) {
            tampil_KebPer();
        }
    }//GEN-LAST:event_TKebPerbMouseClicked

    private void LapPerbaikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LapPerbaikanActionPerformed
        PanelLurahPencarian.setVisible(true);
        tabelPencarian.setModel(tableModel4);
        Cari_status.setText("Cari Laporan Perbaikan");
    }//GEN-LAST:event_LapPerbaikanActionPerformed

    private void TxtPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtPasswordKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtPasswordKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HalamanUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new HalamanUtama().setVisible(true);
                } catch (SQLException ex) {
                }
                
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BatalDatPerbaikan;
    private javax.swing.JButton BatalKas;
    private javax.swing.JButton BatalKebPem;
    private javax.swing.JButton BatalKebPerb;
    private javax.swing.JButton BatalKebutuhan;
    private javax.swing.JButton BatalPembangunan;
    private javax.swing.JButton BatalPerbaikan;
    private javax.swing.JButton BtLogin1;
    private javax.swing.JButton BtReset1;
    private javax.swing.JComboBox CDataKebutuhan;
    private javax.swing.JComboBox CDataPerbaikan;
    private javax.swing.JComboBox CKebPembangunan;
    private javax.swing.JComboBox CKebPerbaikan;
    private javax.swing.JLabel Cari_status;
    private javax.swing.JTextField DBPembangunan;
    private javax.swing.JTextField DBPerbaikan;
    private javax.swing.JTextField DafKebPerb;
    private javax.swing.JTextField DafKebutuhan;
    private javax.swing.JButton DataKebPembangunan;
    private javax.swing.JButton DataKebPerbaikan;
    private javax.swing.JButton DataPembang;
    private javax.swing.JButton DataPerbaik;
    private javax.swing.JButton HapusDatPerbaikan;
    private javax.swing.JButton HapusKas;
    private javax.swing.JButton HapusKebPem;
    private javax.swing.JButton HapusKebPerb;
    private javax.swing.JButton HapusPembangunan;
    private javax.swing.JPanel InformasiUser;
    private javax.swing.JButton KebutuhanInfrastruktur;
    private javax.swing.JTextField KetKas;
    private javax.swing.JTextField KetKeb;
    private javax.swing.JTextField KetPembangunan;
    private javax.swing.JTextField KetPerb;
    private javax.swing.JTextField KetPerbaikan;
    private javax.swing.JButton LapLaporanDanaKas;
    private javax.swing.JButton LapPembangunan;
    private javax.swing.JButton LapPerbaikan;
    private javax.swing.JTextField LokasiPembangunan;
    private javax.swing.JTextField LokasiPerbaikan;
    private javax.swing.JTextField NamaPembangunan;
    private javax.swing.JTextField NamaPerbaikan;
    private javax.swing.JLabel NoDanaKas;
    private javax.swing.JLabel NoDataKebutuhan;
    private javax.swing.JLabel NoDataPerbaikan;
    private javax.swing.JLabel NoKebPem;
    private javax.swing.JLabel NoKebPerb;
    private javax.swing.JLabel NoPembangunan;
    private javax.swing.JLabel NoPerbaikan;
    private javax.swing.JPanel PLoginAkses;
    private javax.swing.JPanel PanelBendahara;
    private javax.swing.JPanel PanelDanaKas;
    private javax.swing.JPanel PanelKebPem;
    private javax.swing.JPanel PanelKebPerb;
    private javax.swing.JPanel PanelKebutuhanMasyarakat;
    private javax.swing.JPanel PanelLurah;
    private javax.swing.JPanel PanelLurahPencarian;
    private javax.swing.JPanel PanelMasyarakat;
    private javax.swing.JPanel PanelPembangunan;
    private javax.swing.JPanel PanelPerbaikan;
    private javax.swing.JPanel PanelPerbaikanMasyarakat;
    private javax.swing.JPanel PanelStaff;
    private javax.swing.JTextField Pencarian;
    private javax.swing.JTextField Penerimaan;
    private javax.swing.JTextField Pengeluaran;
    private javax.swing.JButton PerbaikanInfrastruktur;
    private javax.swing.JTextField Saldo;
    private javax.swing.JButton SelesaiDatPerbaikan;
    private javax.swing.JButton SelesaiKebPem;
    private javax.swing.JButton SelesaiKebPerb;
    private javax.swing.JButton SelesaiKebutuhan;
    private javax.swing.JButton SelesaiPembangunan;
    private javax.swing.JButton SelesaiPerbaikan;
    private javax.swing.JButton SimpanDatPerbaikan;
    private javax.swing.JButton SimpanKas;
    private javax.swing.JButton SimpanKebPem;
    private javax.swing.JButton SimpanKebPerb;
    private javax.swing.JButton SimpanKebutuhan;
    private javax.swing.JButton SimpanPembangunan;
    private javax.swing.JButton SimpanPerbaikan;
    private javax.swing.JTextField TBKebPerb;
    private javax.swing.JTextField TBKebutuhan;
    private javax.swing.JTextField TBPembangunan;
    private javax.swing.JTextField TBPerbaikan;
    private javax.swing.JTable TKebPem;
    private javax.swing.JTable TKebPerb;
    private javax.swing.JPasswordField TxtPassword;
    private javax.swing.JTextField TxtUser;
    private javax.swing.JButton UbahDatPerbaikan;
    private javax.swing.JButton UbahKas;
    private javax.swing.JButton UbahKebPem;
    private javax.swing.JButton UbahKebPerb;
    private javax.swing.JButton UbahPembangunan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTable dataKebutuhanMasyarakat;
    private javax.swing.JTable dataPerbaikanMasyarakat;
    private javax.swing.JTable datadanakas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JButton keluar;
    private si.infrastruktur.desa.logo logo1;
    private javax.swing.JTextField lokasi_kebutuhan;
    private javax.swing.JTextField lokasi_perbaikan;
    private javax.swing.JTextField nama_kebutuhan;
    private javax.swing.JTextField nama_perbaikan;
    private javax.swing.JLabel namauser;
    private javax.swing.JTable tabelLapKeb;
    private javax.swing.JTable tabelLapPer;
    private javax.swing.JTable tabelPencarian;
    private javax.swing.JLabel userlogin1;
    // End of variables declaration//GEN-END:variables
}