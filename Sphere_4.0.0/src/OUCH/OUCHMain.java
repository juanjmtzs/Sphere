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

package OUCH;

import Handler.OUCHSoupBinTCPNoBytes;
import Handler.OUCHSoupBinTCPYesBytes;
import Properties.AppProperties;
import Sphere.Main;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableCellRenderer;


public class OUCHMain extends javax.swing.JFrame {

    public static JFrame windows;
    private boolean outputToFiles = false;
    public static String userHomeFolder;

    public static void setUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Creates new form Main
     */
    /**
     *
     * @author Juan Jesús Martínez Serrano / juanjmtzs@gmail.com
     *
     *
     */
    public OUCHMain() {

        initComponents();

        windows = this;
        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon img = new ImageIcon(classLoader.getResource("imgs/icon2.png"));

        windows.setIconImage(img.getImage());
        logs.setVisible(false);

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

        }

        final JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem buyItem = new JMenuItem("BUY");
        buyItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                NewOrder newOrder = new NewOrder();
                newOrder.orderVerbField.setText("B");
                newOrder.orderbookField.setText("" + orderbookTable.getValueAt(orderbookTable.getSelectedRow(), 0));
                newOrder.quantityField.setText("" + orderbookTable.getValueAt(orderbookTable.getSelectedRow(), 3));
                newOrder.priceField.setText("" + orderbookTable.getValueAt(orderbookTable.getSelectedRow(), 4));
                NewOrder.mDPrice = newOrder.priceField.getText();
                newOrder.setVisible(true);
            }
        });
        popupMenu.add(buyItem);

        JMenuItem sellItem = new JMenuItem("SELL");
        sellItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                NewOrder newOrder = new NewOrder();
                newOrder.orderVerbField.setText("S");
                newOrder.orderbookField.setText("" + orderbookTable.getValueAt(orderbookTable.getSelectedRow(), 0));
                newOrder.quantityField.setText("" + orderbookTable.getValueAt(orderbookTable.getSelectedRow(), 3));
                newOrder.priceField.setText("" + orderbookTable.getValueAt(orderbookTable.getSelectedRow(), 4));
                NewOrder.mDPrice = newOrder.priceField.getText();
                newOrder.setVisible(true);
            }
        });
        popupMenu.add(sellItem);
        orderbookTable.setComponentPopupMenu(popupMenu);

        final JPopupMenu popupMenuOrders = new JPopupMenu();
        JMenuItem modifyItem = new JMenuItem("Modify");
        modifyItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ModifyOrder modifyOrder = new ModifyOrder();
                modifyOrder.orderTokenField.setText("" + ordersTable.getValueAt(ordersTable.getSelectedRow(), 0));
                modifyOrder.quantityField.setText("" + ordersTable.getValueAt(ordersTable.getSelectedRow(), 3));
                modifyOrder.priceField.setText("" + ordersTable.getValueAt(ordersTable.getSelectedRow(), 5));
                modifyOrder.setVisible(true);
            }
        });
        popupMenuOrders.add(modifyItem);

        JMenuItem cancelItem = new JMenuItem("Cancel");
        cancelItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CancelOrder cancelOrder = new CancelOrder();
                cancelOrder.orderTokenField.setText("" + ordersTable.getValueAt(ordersTable.getSelectedRow(), 0));
                cancelOrder.setVisible(true);
            }
        });
        popupMenuOrders.add(cancelItem);
        ordersTable.setComponentPopupMenu(popupMenuOrders);

        containerFields.setVisible(false);

        class GreenRedRenderer extends DefaultTableCellRenderer {

            GreenRedRenderer() {
                setHorizontalAlignment(CENTER);
            }

            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value,
                    boolean isSelected, boolean hasFocus,
                    int row, int column
            ) {
                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column
                );

                if (table.getValueAt(row, 9).equals("L") || table.getValueAt(row, 9).equals("Executed")) {

                    c.setBackground(new Color(23, 171, 33));
                    c.setForeground(Color.WHITE);
                } else {
                    c.setBackground(new Color(182, 31, 31));
                    c.setForeground(new Color(223, 223, 223));
                }
                if (isSelected) {
                    c.setBackground(new Color(77, 77, 77));
                    c.setForeground(Color.WHITE);
                }

                return c;
            }
        }
        class GreenRedRendererTrades extends DefaultTableCellRenderer {

            GreenRedRendererTrades() {
                setHorizontalAlignment(CENTER);
            }

            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value,
                    boolean isSelected, boolean hasFocus,
                    int row, int column
            ) {
                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column
                );

                if (table.getValueAt(row, 5).equals("Trade")) {

                    c.setBackground(new Color(23, 171, 33));
                    c.setForeground(Color.WHITE);
                } else {
                    c.setBackground(new Color(182, 31, 31));
                    c.setForeground(new Color(223, 223, 223));
                }
                if (isSelected) {
                    c.setBackground(new Color(77, 77, 77));
                    c.setForeground(Color.WHITE);
                }

                return c;
            }
        }
        ordersTable.setDefaultRenderer(Object.class, new GreenRedRenderer());
        tradesTable.setDefaultRenderer(Object.class, new GreenRedRendererTrades());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Update = new javax.swing.JTextArea();
        DissconnectDo = new javax.swing.JButton();
        connect = new javax.swing.JButton();
        serviceSelector = new javax.swing.JComboBox<>();
        progress = new javax.swing.JProgressBar();
        containerFields = new javax.swing.JPanel();
        toFile = new javax.swing.JCheckBox();
        logs = new javax.swing.JLabel();
        serviceLabel = new javax.swing.JLabel();
        servicename = new javax.swing.JTextField();
        seqnum = new javax.swing.JTextField();
        seqLabel = new javax.swing.JLabel();
        portLabel = new javax.swing.JLabel();
        port = new javax.swing.JTextField();
        hostname = new javax.swing.JTextField();
        ipLabel = new javax.swing.JLabel();
        sessionLabel = new javax.swing.JLabel();
        session = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        passwordLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        printBytesOuch = new javax.swing.JCheckBox();
        save = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        viewMessages = new javax.swing.JCheckBox();
        jScrollPane5 = new javax.swing.JScrollPane();
        logsArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        orderbookTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ordersTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tradesTable = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sphere OUCH");
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.darkGray);
        setMinimumSize(new java.awt.Dimension(1100, 800));

        jPanel2.setBackground(new java.awt.Color(238, 238, 238));

        Update.setEditable(false);
        Update.setBackground(new java.awt.Color(242, 242, 242));
        Update.setColumns(20);
        Update.setForeground(new java.awt.Color(38, 38, 38));
        Update.setRows(5);
        Update.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(Update);

        DissconnectDo.setForeground(new java.awt.Color(38, 38, 38));
        DissconnectDo.setText("Dissconnect");
        DissconnectDo.setEnabled(false);
        DissconnectDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DissconnectDoActionPerformed(evt);
            }
        });

        connect.setForeground(new java.awt.Color(38, 38, 38));
        connect.setText("Connect");
        connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectActionPerformed(evt);
            }
        });

        serviceSelector.setForeground(new java.awt.Color(38, 38, 38));
        serviceSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "OUCH" }));
        serviceSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviceSelectorActionPerformed(evt);
            }
        });

        progress.setBackground(new java.awt.Color(255, 255, 255));
        progress.setOpaque(true);

        containerFields.setBackground(new java.awt.Color(238, 238, 238));

        toFile.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        toFile.setText("Logs in Files");
        toFile.setToolTipText("");
        toFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toFileActionPerformed(evt);
            }
        });

        logs.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        logs.setForeground(new java.awt.Color(51, 51, 51));
        logs.setText("Logs:");

        serviceLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        serviceLabel.setForeground(new java.awt.Color(51, 51, 51));
        serviceLabel.setText("Service Name:");

        servicename.setBackground(new java.awt.Color(242, 242, 242));
        servicename.setForeground(new java.awt.Color(38, 38, 38));
        servicename.setEnabled(false);
        servicename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                servicenameActionPerformed(evt);
            }
        });

        seqnum.setEditable(false);
        seqnum.setBackground(new java.awt.Color(242, 242, 242));
        seqnum.setForeground(new java.awt.Color(38, 38, 38));
        seqnum.setText("1");
        seqnum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                seqnumKeyTyped(evt);
            }
        });

        seqLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        seqLabel.setForeground(new java.awt.Color(51, 51, 51));
        seqLabel.setText("Sequence Number:");

        portLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        portLabel.setForeground(new java.awt.Color(51, 51, 51));
        portLabel.setText("Port:");

        port.setBackground(new java.awt.Color(242, 242, 242));
        port.setForeground(new java.awt.Color(38, 38, 38));
        port.setToolTipText("33333");
        port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portActionPerformed(evt);
            }
        });
        port.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                portKeyTyped(evt);
            }
        });

        hostname.setBackground(new java.awt.Color(242, 242, 242));
        hostname.setForeground(new java.awt.Color(38, 38, 38));
        hostname.setToolTipText("200.200.200.200");
        hostname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                hostnameKeyTyped(evt);
            }
        });

        ipLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ipLabel.setForeground(new java.awt.Color(51, 51, 51));
        ipLabel.setText("IP:");

        sessionLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        sessionLabel.setForeground(new java.awt.Color(51, 51, 51));
        sessionLabel.setText("Session:");

        session.setBackground(new java.awt.Color(242, 242, 242));
        session.setForeground(new java.awt.Color(38, 38, 38));
        session.setText(" ");
        session.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sessionKeyTyped(evt);
            }
        });

        password.setBackground(new java.awt.Color(242, 242, 242));
        password.setForeground(new java.awt.Color(38, 38, 38));
        password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passwordKeyTyped(evt);
            }
        });

        passwordLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(51, 51, 51));
        passwordLabel.setText("Password:");

        usernameLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(51, 51, 51));
        usernameLabel.setText("Username:");

        username.setBackground(new java.awt.Color(242, 242, 242));
        username.setForeground(new java.awt.Color(38, 38, 38));
        username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usernameKeyTyped(evt);
            }
        });

        printBytesOuch.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        printBytesOuch.setText("Print Bytes");
        printBytesOuch.setToolTipText("");
        printBytesOuch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBytesOuchActionPerformed(evt);
            }
        });

        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/save.png"))); // NOI18N
        save.setToolTipText("Save Service Configuration");
        save.setPreferredSize(new java.awt.Dimension(31, 31));
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/clear.png"))); // NOI18N
        clear.setPreferredSize(new java.awt.Dimension(31, 31));
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        viewMessages.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        viewMessages.setSelected(true);
        viewMessages.setText("View Messages");
        viewMessages.setToolTipText("");
        viewMessages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewMessagesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout containerFieldsLayout = new javax.swing.GroupLayout(containerFields);
        containerFields.setLayout(containerFieldsLayout);
        containerFieldsLayout.setHorizontalGroup(
            containerFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerFieldsLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(containerFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(containerFieldsLayout.createSequentialGroup()
                        .addGroup(containerFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameLabel)
                            .addComponent(passwordLabel)
                            .addComponent(sessionLabel)
                            .addComponent(ipLabel)
                            .addComponent(portLabel)
                            .addComponent(seqLabel)
                            .addComponent(serviceLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(containerFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seqnum)
                            .addComponent(port)
                            .addComponent(hostname)
                            .addComponent(session, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                            .addComponent(servicename)
                            .addComponent(username)
                            .addComponent(password)))
                    .addGroup(containerFieldsLayout.createSequentialGroup()
                        .addGroup(containerFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(printBytesOuch)
                            .addGroup(containerFieldsLayout.createSequentialGroup()
                                .addComponent(toFile)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(viewMessages, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 285, Short.MAX_VALUE)
                        .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        containerFieldsLayout.setVerticalGroup(
            containerFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerFieldsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(containerFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(containerFieldsLayout.createSequentialGroup()
                        .addGroup(containerFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usernameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(containerFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordLabel)
                            .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(containerFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sessionLabel)
                            .addComponent(session, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(containerFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ipLabel)
                            .addComponent(hostname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(containerFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(portLabel)
                            .addComponent(port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(containerFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(seqLabel)
                            .addComponent(seqnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(containerFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(serviceLabel)
                            .addComponent(servicename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logs)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(containerFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(containerFieldsLayout.createSequentialGroup()
                                .addGroup(containerFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(toFile)
                                    .addComponent(viewMessages))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(printBytesOuch))
                            .addComponent(clear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        logsArea.setBackground(new java.awt.Color(242, 242, 242));
        logsArea.setColumns(20);
        logsArea.setForeground(new java.awt.Color(38, 38, 38));
        logsArea.setRows(5);
        jScrollPane5.setViewportView(logsArea);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(progress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(serviceSelector, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(containerFields, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(connect, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DissconnectDo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(serviceSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(containerFields, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DissconnectDo)
                    .addComponent(connect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        jTabbedPane1.addTab("OUCH LOGON", jPanel2);

        orderbookTable.setAutoCreateRowSorter(true);
        orderbookTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        orderbookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "SecCode", "Board", "MinQty", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        orderbookTable.setFocusTraversalPolicyProvider(true);
        orderbookTable.setFocusable(false);
        orderbookTable.setRowMargin(3);
        jScrollPane2.setViewportView(orderbookTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1095, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Orderbook", jPanel1);

        ordersTable.setAutoCreateRowSorter(true);
        ordersTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ordersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Token", "AccType", "Verb", "Qty", "Orderbook", "Price", "TIF", "RefNum", "MinQty", "State"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ordersTable.setFocusTraversalPolicyProvider(true);
        ordersTable.setFocusable(false);
        ordersTable.setRowMargin(3);
        jScrollPane3.setViewportView(ordersTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1095, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Orders", jPanel3);

        tradesTable.setAutoCreateRowSorter(true);
        tradesTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tradesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Token", "ExecQty", "ExecPrice", "LiquidityFlag", "MatchNum", "State"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tradesTable.setFocusTraversalPolicyProvider(true);
        tradesTable.setFocusable(false);
        tradesTable.setRowMargin(3);
        jScrollPane4.setViewportView(tradesTable);

        jTabbedPane1.addTab("Trades", jScrollPane4);

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
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void DissconnectDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DissconnectDoActionPerformed
        // TODO add your handling code here:

        switch (serviceSelector.getSelectedIndex()) {

            case 6:
                break;
            case 7:
                break;
            default:
                progress.setIndeterminate(false);
                connect.setEnabled(true);
                serviceSelector.setEnabled(true);
                DissconnectDo.setEnabled(false);
                printBytesOuch.setEnabled(true);
                toFile.setEnabled(true);
                viewMessages.setEnabled(true);

                clear.setEnabled(true);
                save.setEnabled(true);

                if (printBytesOuch.isSelected() == true) {
                    try {
                        OUCHSoupBinTCPYesBytes.kill();
                    } catch (Exception e) {
                    }
                } else {
                    try {
                        OUCHSoupBinTCPNoBytes.kill();
                    } catch (Exception e) {
                    }
                }

                Runtime garbage1 = Runtime.getRuntime();
                garbage1.gc();

                break;
        }

    }//GEN-LAST:event_DissconnectDoActionPerformed

    private void portActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_portActionPerformed

    private void connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectActionPerformed
        // TODO add your handling code here:
        logsArea.setText("");

        switch (serviceSelector.getSelectedIndex()) {

            case 6:

                break;

            case 7:

                break;

            default:
                if (!username.getText().isEmpty() && !password.getText().isEmpty() && !session.getText().isEmpty() && !hostname.getText().isEmpty() && !port.getText().isEmpty() && !seqnum.getText().isEmpty()) {

                    progress.setIndeterminate(true);
                    String args[] = new String[7];
                    args[0] = username.getText();
                    args[1] = password.getText();
                    args[2] = session.getText();
                    args[3] = hostname.getText();
                    args[4] = port.getText();
                    args[5] = seqnum.getText();
                    args[6] = servicename.getText();

                    connect.setEnabled(false);
                    serviceSelector.setEnabled(false);
                    DissconnectDo.setEnabled(true);
                    printBytesOuch.setEnabled(false);
                    toFile.setEnabled(false);
                    viewMessages.setEnabled(false);

                    clear.setEnabled(false);
                    save.setEnabled(false);
                    //MainContainer.paint(MainContainer.getGraphics());
                    if (printBytesOuch.isSelected() == true) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {

                                    OUCHSoupBinTCPYesBytes.StartHandling(args);
                                } catch (IOException ex) {
                                    Logger.getLogger(OUCHMain.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (UnsupportedLookAndFeelException ex) {
                                    Logger.getLogger(OUCHMain.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }).start();
                    } else {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {

                                    OUCHSoupBinTCPNoBytes.StartHandling(args);
                                } catch (IOException ex) {
                                    Logger.getLogger(OUCHMain.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (UnsupportedLookAndFeelException ex) {
                                    Logger.getLogger(OUCHMain.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }).start();
                    }

                } else {
                    JOptionPane.showMessageDialog(this,
                            "All fields are requiered!",
                            "Warning",
                            JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
        /*PrintStream con;
        con = new PrintStream(new TextAreaOutputStream(outputTextArea));
        System.setOut(con);
        System.setErr(con);
         */

    }//GEN-LAST:event_connectActionPerformed

    private void servicenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_servicenameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_servicenameActionPerformed

    private void serviceSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceSelectorActionPerformed
        // TODO add your handling code here:

        AppProperties appProperties = new AppProperties();
        Properties properties;
        switch (serviceSelector.getSelectedIndex()) {
            case 1:
                containerFields.setVisible(true);
                try {
                    appProperties.loadPropertiesOUCH();
                } catch (Exception e) {
                }
                properties = appProperties.getProperties();
                containerFields.setVisible(true);
                username.setVisible(true);
                password.setVisible(true);
                session.setVisible(true);
                seqnum.setVisible(true);
                usernameLabel.setVisible(true);
                passwordLabel.setVisible(true);
                sessionLabel.setVisible(true);
                seqLabel.setVisible(true);
                username.setText(properties.getProperty("username"));
                password.setText(properties.getProperty("password"));
                session.setText(properties.getProperty("session"));
                hostname.setText(properties.getProperty("ip"));
                port.setText(properties.getProperty("port"));
                seqnum.setText(properties.getProperty("sequence"));
                servicename.setText("OUCH");
                connect.setText("Connect");
                DissconnectDo.setText("Dissconnect");

                break;

            case 0:
                containerFields.setVisible(false);
                break;
            default:
                break;
        }
    }//GEN-LAST:event_serviceSelectorActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:

        // create a jframe
        Main.copyRight.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
        Update.setText("");
        logsArea.setText("");
        serviceSelector.setSelectedIndex(0);
    }//GEN-LAST:event_clearActionPerformed

    private void toFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toFileActionPerformed
        // TODO add your handling code here:
        if (toFile.isSelected()) {
            userHomeFolder = System.getProperty("user.home") + "\\Desktop";
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Select Folder");
            chooser.setApproveButtonText("Ok");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                userHomeFolder = chooser.getSelectedFile() + "";
            }
            logs.setText("Logs: " + userHomeFolder);
            logs.setVisible(true);
            outputToFiles = true;

        } else {
            logs.setVisible(false);
            outputToFiles = false;
        }
    }//GEN-LAST:event_toFileActionPerformed

    private void usernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyTyped
        // TODO add your handling code here:
        char teclaPulsada = evt.getKeyChar();
        if (!Character.isLetterOrDigit(teclaPulsada)) {
            evt.consume();
        }
        if (username.getText().length() == 6) {
            evt.consume();
        }
    }//GEN-LAST:event_usernameKeyTyped

    private void passwordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyTyped
        // TODO add your handling code here:
        char teclaPulsada = evt.getKeyChar();
        if (!Character.isLetterOrDigit(teclaPulsada)) {
            evt.consume();
        }
        if (password.getText().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_passwordKeyTyped

    private void sessionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sessionKeyTyped
        // TODO add your handling code here:
        char teclaPulsada = evt.getKeyChar();
        if (!Character.isDigit(teclaPulsada) && teclaPulsada != ' ') {
            evt.consume();
        }
        if (session.getText().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_sessionKeyTyped

    private void hostnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hostnameKeyTyped
        // TODO add your handling code here:
        char teclaPulsada = evt.getKeyChar();
        if (!Character.isDigit(teclaPulsada) && teclaPulsada != '.') {
            evt.consume();
        }
        if (hostname.getText().length() == 15) {
            evt.consume();
        }
    }//GEN-LAST:event_hostnameKeyTyped

    private void portKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_portKeyTyped
        // TODO add your handling code here:
        char teclaPulsada = evt.getKeyChar();
        if (!Character.isDigit(teclaPulsada)) {
            evt.consume();
        }
        if (port.getText().length() == 5) {
            evt.consume();
        }
    }//GEN-LAST:event_portKeyTyped

    private void seqnumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_seqnumKeyTyped
        // TODO add your handling code here:
        char teclaPulsada = evt.getKeyChar();
        if (!Character.isDigit(teclaPulsada)) {
            evt.consume();
        }
        if (seqnum.getText().length() == 9) {
            evt.consume();
        }
    }//GEN-LAST:event_seqnumKeyTyped

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        AppProperties appProperties = new AppProperties(username.getText(), password.getText(), session.getText(), hostname.getText(), port.getText(), seqnum.getText());
        switch (serviceSelector.getSelectedItem().toString()) {

            case "OUCH":
                try {
                    appProperties.savePropertiesOUCH();
                    JOptionPane.showMessageDialog(this,
                            "OUCH Service Settings Saved",
                            "Saved!",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this,
                            "Something went wrong: " + e,
                            "Error!",
                            JOptionPane.ERROR_MESSAGE);
                }

                break;
        }
    }//GEN-LAST:event_saveActionPerformed

    private void printBytesOuchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBytesOuchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_printBytesOuchActionPerformed

    private void viewMessagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewMessagesActionPerformed
        // TODO add your handling code here:
        if (viewMessages.isSelected()) {
            logsArea.setEnabled(true);
        } else {
            logsArea.setEnabled(false);
        }
    }//GEN-LAST:event_viewMessagesActionPerformed

    /**
     * @param args the command line arguments
     */
    /**
     *
     * @author Juan Jesús Martínez Serrano / juanjmtzs@gmail.com
     *
     *
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton DissconnectDo;
    public static javax.swing.JTextArea Update;
    public static javax.swing.JButton clear;
    public static javax.swing.JButton connect;
    private javax.swing.JPanel containerFields;
    private javax.swing.JTextField hostname;
    private javax.swing.JLabel ipLabel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel logs;
    public static javax.swing.JTextArea logsArea;
    public static javax.swing.JTable orderbookTable;
    public static javax.swing.JTable ordersTable;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField port;
    private javax.swing.JLabel portLabel;
    public static javax.swing.JCheckBox printBytesOuch;
    public static javax.swing.JProgressBar progress;
    public static javax.swing.JButton save;
    private javax.swing.JLabel seqLabel;
    private javax.swing.JTextField seqnum;
    private javax.swing.JLabel serviceLabel;
    public static javax.swing.JComboBox<String> serviceSelector;
    private javax.swing.JTextField servicename;
    private javax.swing.JTextField session;
    private javax.swing.JLabel sessionLabel;
    public static javax.swing.JCheckBox toFile;
    public static javax.swing.JTable tradesTable;
    private javax.swing.JTextField username;
    private javax.swing.JLabel usernameLabel;
    public static javax.swing.JCheckBox viewMessages;
    // End of variables declaration//GEN-END:variables
}
