/*
 * Copyright (C) 2018 Juan J. Martínez
 * 
 * All rights reserved. This complete software or any portion thereof
 * can be used as reference but may not be reproduced in any manner 
 * whatsoever without the express written permission of the owner.
 * 
 * The purpose of this is to be consulted and used as a referece of 
 * functionallyty.
 * 
 * Developed in Mexico City
 * First version, 2018
 *
 */

/**
 *
 * @author Juan J. Martínez
 * @email juanjmtzs@gmail.com
 * @phone +52-1-55-1247-8044
 * @linkedin https://www.linkedin.com/in/juanjmtzs/
 *
 */

package Sphere;

import BMV.MDBMV;
import ITCH.ITCHMain;
import OUCH.OUCHMain;
import Properties.AppProperties;
import Requester.RequesterYesBytes;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import ncoder.*;

public class Main extends javax.swing.JFrame {

    RequesterYesBytes req = null;
    private JFrame windows;
    private boolean outputToFiles = false;
    public static OUCHMain ouch;
    public static ITCHMain itch;
    public static MDBMV mdbmv;
    public static String Key = "740577570" + 3;
    public static String licenseText = "Unlicensed";
    private SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy");
    private CryptoNCoder cryptoNCoder = new CryptoNCoder();
    private Date date = new Date();
    public static String copy;
    public static CopyRight copyRight;

    /**
     * Creates new form Main
     */
    /**
     *
     * @author Juan Jesús Martínez Serrano / juanjmtzs@gmail.com
     *
     *
     */
    public Main() {

        //this.setUndecorated(true);
        initComponents();
        //this.setBackground(new Color(1.0f,1.0f,1.0f,0.5f));

        windows = this;
        ClassLoader classLoader = getClass().getClassLoader();
	       ImageIcon img = new ImageIcon(classLoader.getResource("imgs/icon2.png"));

        windows.setIconImage(img.getImage());

        windows.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        windows.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                try {
                    exitAll();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            UIDefaults defaults = UIManager.getLookAndFeelDefaults();
            NimbusLookAndFeel localNimbusLookAndFeel = (NimbusLookAndFeel) UIManager.getLookAndFeel();
            Color derivedColor = localNimbusLookAndFeel.getDerivedColor("nimbusBase", 0.03054375F, -0.3835404F, -0.0980392F, 0, true);
            defaults.put("nimbusOrange", derivedColor);

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        AppProperties appProperties = new AppProperties();
        Properties properties;

        try {
            appProperties.loadPropertiesLicense();
        } catch (Exception e) {
        }
        properties = appProperties.getProperties();

        if (properties.getProperty("license").equals("")) {

        } else {
            try {

                String dateStri = cryptoNCoder.decrypt(Key, properties.getProperty("license")).substring(5, 18);
                Date expiry = new Date(Long.parseLong(dateStri));

                if (date.compareTo(expiry) > 0) {

                    licenseText = "License Type: " + cryptoNCoder.decrypt(Key, properties.getProperty("license")).substring(0, 4)
                            + "<br>"
                            + "License State: Expired"
                            + "<br>"
                            + "Expiry Date: " + df.format(expiry);

                } else if (date.compareTo(expiry) < 0) {

                    licenseText = "License Type: " + cryptoNCoder.decrypt(Key, properties.getProperty("license")).substring(0, 4)
                            + "<br>"
                            + "License State: Active"
                            + "<br>"
                            + "Expiry Date: " + df.format(expiry);
                } else if (date.compareTo(expiry) == 0) {

                    licenseText = "License Type: " + cryptoNCoder.decrypt(Key, properties.getProperty("license")).substring(0, 4)
                            + "<br>"
                            + "License State: Active"
                            + "<br>"
                            + "Expiry Date: " + df.format(expiry);
                }
            } catch (Exception e) {

            }
        }
        updateCopy();

        ouch = new OUCHMain();
        itch = new ITCHMain();
        mdbmv = new MDBMV();
        copyRight=new CopyRight();

//        DefaultTableModel model = (DefaultTableModel) orderbook.orderbookTable.getModel();
//        for (int count = 1; count <= 100;count++) {
//            Vector<Object> data = new Vector<Object>();
//            data.add(count);
//            data.add("Orderbook" + count);
//            data.add("SecCode"+count);
//            data.add("Board"+count);
//            data.add("MinQty");
//            data.add("Price");
//            model.addRow(data);
//        }
//        for (int x = 0; x < orderbook.orderbookTable.getRowCount();x++) {
//            
//            if(orderbook.orderbookTable.getValueAt(x, 0).equals(50)==true){
//                orderbook.orderbookTable.setValueAt("Precio Modificado", x, 4);
//            }
//            
//        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bivaMdItchButton = new javax.swing.JButton();
        bivaOmsOuchButton = new javax.swing.JButton();
        bmvMdIntraButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Sphere 4.0.0");
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.darkGray);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(238, 238, 238));

        bivaMdItchButton.setForeground(new java.awt.Color(38, 38, 38));
        bivaMdItchButton.setText("BIVA Market Data ITCH");
        bivaMdItchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bivaMdItchButtonActionPerformed(evt);
            }
        });

        bivaOmsOuchButton.setForeground(new java.awt.Color(38, 38, 38));
        bivaOmsOuchButton.setText("BIVA OMS OUCH");
        bivaOmsOuchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bivaOmsOuchButtonActionPerformed(evt);
            }
        });

        bmvMdIntraButton.setForeground(new java.awt.Color(38, 38, 38));
        bmvMdIntraButton.setText("BMV Market Data INTRA");
        bmvMdIntraButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bmvMdIntraButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bivaMdItchButton, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bivaOmsOuchButton, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bmvMdIntraButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(bivaMdItchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bivaOmsOuchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bmvMdIntraButton)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jMenu1.setText("About");

        jMenuItem1.setText("Copyright ©");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("by Juan J. Martínez");
        jMenu2.setEnabled(false);
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        copyRight.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void bivaMdItchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bivaMdItchButtonActionPerformed
        // TODO add your handling code here:
        AppProperties appProperties = new AppProperties();
        Properties properties;
        License license = new License();

        try {
            appProperties.loadPropertiesLicense();
        } catch (Exception e) {
        }
        properties = appProperties.getProperties();

        if (properties.getProperty("license").equals("")) {
            license.setVisible(true);
        } else {
            try {

                String dateStri = cryptoNCoder.decrypt(Key, properties.getProperty("license")).substring(5, 18);
                Date expiry = new Date(Long.parseLong(dateStri));

                if (date.compareTo(expiry) > 0) {
                    JOptionPane.showMessageDialog(this,
                            "Your License has expired, please contact us: contact@hiketech.com.mx",
                            "License Expired",
                            JOptionPane.ERROR_MESSAGE);
                    licenseText = "License Type: " + cryptoNCoder.decrypt(Key, properties.getProperty("license")).substring(0, 4)
                            + "<br>"
                            + "License State: Expired"
                            + "<br>"
                            + "Expiry Date: " + df.format(expiry);
                    license.setVisible(true);
                } else if (date.compareTo(expiry) < 0) {
                    itch.setVisible(true);
                    licenseText = "License Type: " + cryptoNCoder.decrypt(Key, properties.getProperty("license")).substring(0, 4)
                            + "<br>"
                            + "License State: Active"
                            + "<br>"
                            + "Expiry Date: " + df.format(expiry);
                    itch.windows.setTitle(windows.getTitle() + "\\" + bivaMdItchButton.getText());
                } else if (date.compareTo(expiry) == 0) {
                    JOptionPane.showMessageDialog(this,
                            "Your License expires today, please contact us: contact@hiketech.com.mx",
                            "License About to Expire",
                            JOptionPane.WARNING_MESSAGE);

                    itch.setVisible(true);
                    licenseText = "License Type: " + cryptoNCoder.decrypt(Key, properties.getProperty("license")).substring(0, 4)
                            + "<br>"
                            + "License State: Active"
                            + "<br>"
                            + "Expiry Date: " + df.format(expiry);
                    itch.windows.setTitle(windows.getTitle() + "\\" + bivaMdItchButton.getText());
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Your License is not valid, please contact us: contact@hiketech.com.mx",
                            "Invalid License",
                            JOptionPane.ERROR_MESSAGE);
                    license.setVisible(true);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Your License is not valid, please contact us: contact@hiketech.com.mx",
                        "Invalid License",
                        JOptionPane.ERROR_MESSAGE);
                license.setVisible(true);
            }
        }
        updateCopy();
    }//GEN-LAST:event_bivaMdItchButtonActionPerformed

    private void bivaOmsOuchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bivaOmsOuchButtonActionPerformed
        // TODO add your handling code here:

        AppProperties appProperties = new AppProperties();
        Properties properties;
        License license = new License();

        try {
            appProperties.loadPropertiesLicense();
        } catch (Exception e) {
        }
        properties = appProperties.getProperties();

        if (properties.getProperty("license").equals("")) {
            license.setVisible(true);
        } else {
            try {

                String dateStri = cryptoNCoder.decrypt(Key, properties.getProperty("license")).substring(5, 18);
                Date expiry = new Date(Long.parseLong(dateStri));

                if (date.compareTo(expiry) > 0) {
                    JOptionPane.showMessageDialog(this,
                            "Your License has expired, please contact us: contact@hiketech.com.mx",
                            "License Expired",
                            JOptionPane.ERROR_MESSAGE);
                    licenseText = "License Type: " + cryptoNCoder.decrypt(Key, properties.getProperty("license")).substring(0, 4)
                            + "<br>"
                            + "License State: Expired"
                            + "<br>"
                            + "Expiry Date: " + df.format(expiry);
                    license.setVisible(true);
                } else if (date.compareTo(expiry) < 0) {
                    ouch.setVisible(true);
                    licenseText = "License Type: " + cryptoNCoder.decrypt(Key, properties.getProperty("license")).substring(0, 4)
                            + "<br>"
                            + "License State: Active"
                            + "<br>"
                            + "Expiry Date: " + df.format(expiry);
                    ouch.windows.setTitle(windows.getTitle() + "\\" + bivaOmsOuchButton.getText());
                } else if (date.compareTo(expiry) == 0) {
                    JOptionPane.showMessageDialog(this,
                            "Your License expires today, please contact us: contact@hiketech.com.mx",
                            "License About to Expire",
                            JOptionPane.WARNING_MESSAGE);

                    ouch.setVisible(true);
                    licenseText = "License Type: " + cryptoNCoder.decrypt(Key, properties.getProperty("license")).substring(0, 4)
                            + "<br>"
                            + "License State: Active"
                            + "<br>"
                            + "Expiry Date: " + df.format(expiry);
                    ouch.windows.setTitle(windows.getTitle() + "\\" + bivaOmsOuchButton.getText());
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Your License is not valid, please contact us: contact@hiketech.com.mx",
                            "Invalid License",
                            JOptionPane.ERROR_MESSAGE);
                    license.setVisible(true);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Your License is not valid, please contact us: contact@hiketech.com.mx",
                        "Invalid License",
                        JOptionPane.ERROR_MESSAGE);
                license.setVisible(true);
            }
        }
        updateCopy();

    }//GEN-LAST:event_bivaOmsOuchButtonActionPerformed

    private void bmvMdIntraButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bmvMdIntraButtonActionPerformed
        // TODO add your handling code here:
        AppProperties appProperties = new AppProperties();
        Properties properties;
        License license = new License();

        try {
            appProperties.loadPropertiesLicense();
        } catch (Exception e) {
        }
        properties = appProperties.getProperties();

        if (properties.getProperty("license").equals("")) {
            license.setVisible(true);
        } else {
            try {

                String dateStri = cryptoNCoder.decrypt(Key, properties.getProperty("license")).substring(5, 18);
                Date expiry = new Date(Long.parseLong(dateStri));

                if (date.compareTo(expiry) > 0) {
                    JOptionPane.showMessageDialog(this,
                            "Your License has expired, please contact us: contact@hiketech.com.mx",
                            "License Expired",
                            JOptionPane.ERROR_MESSAGE);
                    licenseText = "License Type: " + cryptoNCoder.decrypt(Key, properties.getProperty("license")).substring(0, 4)
                            + "<br>"
                            + "License State: Expired"
                            + "<br>"
                            + "Expiry Date: " + df.format(expiry);
                    license.setVisible(true);
                } else if (date.compareTo(expiry) < 0) {
                    mdbmv.setVisible(true);
                    licenseText = "License Type: " + cryptoNCoder.decrypt(Key, properties.getProperty("license")).substring(0, 4)
                            + "<br>"
                            + "License State: Active"
                            + "<br>"
                            + "Expiry Date: " + df.format(expiry);
                    mdbmv.windows.setTitle(windows.getTitle() + "\\" + bmvMdIntraButton.getText());
                } else if (date.compareTo(expiry) == 0) {
                    JOptionPane.showMessageDialog(this,
                            "Your License expires today, please contact us: contact@hiketech.com.mx",
                            "License About to Expire",
                            JOptionPane.WARNING_MESSAGE);
                    mdbmv.setVisible(true);
                    licenseText = "License Type: " + cryptoNCoder.decrypt(Key, properties.getProperty("license")).substring(0, 4)
                            + "<br>"
                            + "License State: Active"
                            + "<br>"
                            + "Expiry Date: " + df.format(expiry);
                    mdbmv.windows.setTitle(windows.getTitle() + "\\" + bmvMdIntraButton.getText());
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Your License is not valid, please contact us: contact@hiketech.com.mx",
                            "Invalid License",
                            JOptionPane.ERROR_MESSAGE);
                    license.setVisible(true);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Your License is not valid, please contact us: contact@hiketech.com.mx",
                        "Invalid License",
                        JOptionPane.ERROR_MESSAGE);
                license.setVisible(true);
            }
        }
        updateCopy();

    }//GEN-LAST:event_bmvMdIntraButtonActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);

            }
        });

    }

    public void exitAll() throws IOException {

        System.out.flush();
        Runtime.getRuntime().halt(0);
    }

    public static void updateCopy() {
        copy = "<html>Copyright © 2018 by HikeTech LABS, CEO Juan J. Martínez<br><br><br>"
                + "All rights reserved. This complete software or any portion thereof<br>"
                + "may not be reproduced or used in any manner whatsoever<br>"
                + "without the express written permission of the owner.<br>"
                + "<br>"
                + "Developed in Mexico City<br>"
                + "<br>"
                + "First version, 2017<br>"
                + "<br>"
                + licenseText
                +"</html>";
    }

    /**
     *
     * @author Juan Jesús Martínez Serrano / juanjmtzs@gmail.com
     *
     *
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bivaMdItchButton;
    private javax.swing.JButton bivaOmsOuchButton;
    private javax.swing.JButton bmvMdIntraButton;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
