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

package Handler;

import ITCH.ITCHMain;
import Sphere.Output;
import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MulticastYesBytes {

    private static MulticastSocket socket;
    private static InetAddress group;
    private static boolean continua = true;
    public static String receiverIP = "";

    public static void kill() {
        continua = false;
        if (socket.isConnected()) {
            try {
                socket.leaveGroup(group);
            } catch (IOException ex) {
                Logger.getLogger(MulticastNoBytes.class.getName()).log(Level.SEVERE, null, ex);
            }
            socket.disconnect();
            socket.close();
        }

        Decoder.ItchYesBytes.count_add_order = 0;
        Decoder.ItchYesBytes.count_broken_trade = 0;
        Decoder.ItchYesBytes.count_order_executed_with_price = 0;
        Decoder.ItchYesBytes.count_order_delete = 0;
        Decoder.ItchYesBytes.count_order_executed = 0;
        Decoder.ItchYesBytes.count_order_replace = 0;
        Decoder.ItchYesBytes.count_trading_action = 0;
        Decoder.ItchYesBytes.count_indicative_price_quantity = 0;
        Decoder.ItchYesBytes.count_price_tick = 0;
        Decoder.ItchYesBytes.count_quantity_tick = 0;
        Decoder.ItchYesBytes.count_stock_directory = 0;
        Decoder.ItchYesBytes.count_warrants_directory = 0;
        Decoder.ItchYesBytes.count_system_event = 0;
        Decoder.ItchYesBytes.count_time_stamp = 0;
        Decoder.ItchYesBytes.count_participant_directory = 0;
        Decoder.ItchYesBytes.count_trade_message = 0;
        Decoder.ItchYesBytes.count_glimpse_snapshot = 0;
        Decoder.ItchYesBytes.count_reference_price = 0;
        Decoder.ItchYesBytes.count_best_bid_offer = 0;
        Decoder.ItchYesBytes.count_news_message = 0;

    }

    public static void StartHandling(String[] args) throws UnknownHostException, IOException {
        try {

            Decoder.ItchYesBytes.count_add_order = 0;
            Decoder.ItchYesBytes.count_broken_trade = 0;
            Decoder.ItchYesBytes.count_order_executed_with_price = 0;
            Decoder.ItchYesBytes.count_order_delete = 0;
            Decoder.ItchYesBytes.count_order_executed = 0;
            Decoder.ItchYesBytes.count_order_replace = 0;
            Decoder.ItchYesBytes.count_trading_action = 0;
            Decoder.ItchYesBytes.count_indicative_price_quantity = 0;
            Decoder.ItchYesBytes.count_price_tick = 0;
            Decoder.ItchYesBytes.count_quantity_tick = 0;
            Decoder.ItchYesBytes.count_stock_directory = 0;
            Decoder.ItchYesBytes.count_warrants_directory = 0;
            Decoder.ItchYesBytes.count_system_event = 0;
            Decoder.ItchYesBytes.count_time_stamp = 0;
            Decoder.ItchYesBytes.count_participant_directory = 0;
            Decoder.ItchYesBytes.count_trade_message = 0;
            Decoder.ItchYesBytes.count_glimpse_snapshot = 0;
            Decoder.ItchYesBytes.count_reference_price = 0;
            Decoder.ItchYesBytes.count_best_bid_offer = 0;
            Decoder.ItchYesBytes.count_news_message = 0;

            group = InetAddress.getByName(args[0]);
            final int port = Integer.parseInt(args[1]);
            receiverIP = args[3];

            String service = args[2];

            if (ITCHMain.toFile.isSelected() == true) {
                String userHomeFolder = ITCHMain.userHomeFolder;

                Output output = new Output();
                output.changeOutput(service, userHomeFolder);
            } else {

                System.setOut(System.out);
            }

            socket = new MulticastSocket(port);
            if (!receiverIP.equals("") && !receiverIP.equals(" ") && !receiverIP.equals("0.0.0.0") && !receiverIP.equals("  ") && !receiverIP.equals("   ") && !receiverIP.equals("    ")) {

                socket.setInterface(InetAddress.getLocalHost());
            } else {
                socket.setInterface(InetAddress.getByName(receiverIP));
            }
            socket.joinGroup(group);
            socket.setSoTimeout(2000);
            if (socket.isClosed() == true) {

                ITCHMain.progress.setIndeterminate(false);
                ITCHMain.connect.setEnabled(true);
                ITCHMain.DissconnectDo.setEnabled(false);
                ITCHMain.serviceSelector.setEnabled(true);
                ITCHMain.printBytes.setEnabled(true);
                ITCHMain.toFile.setEnabled(true);
                ITCHMain.viewMessages.setEnabled(true);

                ITCHMain.clear.setEnabled(true);
                ITCHMain.save.setEnabled(true);

                JFrame error = new JFrame();

                JOptionPane.showMessageDialog(error, "Disconected", "Error!", JOptionPane.ERROR_MESSAGE);

            } else {

                try {
                    continua = true;

                    DatagramPacket packet = new DatagramPacket(new byte[64000], 64000);
                    System.out.println("[ITCH-M] {\"Service Name\":\"TV Multicast\"}");
                    while (continua != false) {
                        socket.receive(packet);
                        System.out.println("[MoldUDP-IN] Packet "
                                + Arrays.toString(Arrays.copyOfRange(packet.getData(), 0, packet.getLength())));
                        int packetSize = packet.getLength();
                        byte session[] = new byte[10];
                        byte sequence[] = new byte[8];
                        byte messagesInPackage[] = new byte[2];
                        byte messages[] = new byte[packet.getLength() - 20];

                        session = Arrays.copyOfRange(packet.getData(), 0, 10);
                        sequence = Arrays.copyOfRange(packet.getData(), 10, 18);
                        messagesInPackage = Arrays.copyOfRange(packet.getData(), 18, 20);
                        messages = Arrays.copyOfRange(packet.getData(), 20, packetSize);

                        BigInteger seqNum = new BigInteger(sequence);
                        seqNum = seqNum.subtract(BigInteger.valueOf(1));

                        ITCHMain.Update.setText(""
                                + "Session:  " + new String(session) + "\n"
                                + "Total Messages:  " + seqNum + "\n"
                                + "     Add Orders(A):  " + Decoder.ItchYesBytes.count_add_order + "\n"
                                + "     Busted Trades(B):  " + Decoder.ItchYesBytes.count_broken_trade + "\n"
                                + "     Orders Executed With Price (C):  " + Decoder.ItchYesBytes.count_order_executed_with_price + "\n"
                                + "     Orders Deleted (D):  " + Decoder.ItchYesBytes.count_order_delete + "\n"
                                + "     Orders Executed (E):  " + Decoder.ItchYesBytes.count_order_executed + "\n"
                                + "     Orders Updated (U):  " + Decoder.ItchYesBytes.count_order_replace + "\n"
                                + "     Trading Actions(H):  " + Decoder.ItchYesBytes.count_trading_action + "\n"
                                + "     Indicative Prices/Quiantities (I):  " + Decoder.ItchYesBytes.count_indicative_price_quantity + "\n"
                                + "     Price Ticks (L):  " + Decoder.ItchYesBytes.count_price_tick + "\n"
                                + "     Quantity Ticks (M):  " + Decoder.ItchYesBytes.count_quantity_tick + "\n"
                                + "     Orderbook Directories (R):  " + Decoder.ItchYesBytes.count_stock_directory + "\n"
                                + "     System Events (S):  " + Decoder.ItchYesBytes.count_system_event + "\n"
                                + "     Timestamps (T):  " + Decoder.ItchYesBytes.count_time_stamp + "\n"
                                + "     Participants Directories (F):  " + Decoder.ItchYesBytes.count_participant_directory + "\n"
                                + "     Trades (P):  " + Decoder.ItchYesBytes.count_trade_message + "\n"
                                + "     Glimpse Snapshot (G):  " + Decoder.ItchYesBytes.count_glimpse_snapshot + "\n"
                                + "     Reference Prices (X):  " + Decoder.ItchYesBytes.count_reference_price + "\n"
                                + "     Best Bid Offers (Q):  " + Decoder.ItchYesBytes.count_best_bid_offer + "\n"
                                + "     News (N):  " + Decoder.ItchYesBytes.count_news_message
                                + "\n");

                        System.out.println("[MoldUDP-IN] Session:" + Arrays.toString(session) + " Decoded:" + new String(session));
                        System.out.println("[MoldUDP-IN] Sequence:" + Arrays.toString(sequence) + " Decoded:" + new BigInteger(sequence));
                        System.out.println("[MoldUDP-IN] Messages In Package:" + Arrays.toString(messagesInPackage) + " Decoded:" + new BigInteger(messagesInPackage));
                        System.out.println("[MoldUDP-IN] Messages:" + Arrays.toString(messages));
                        int mip = new BigInteger(messagesInPackage).intValue();
                        byte messagehelper[] = messages;;
                        for (int x = 1; x <= mip; x++) {

                            new BigInteger(Arrays.copyOfRange(messagehelper, 0, 2)).intValue();
                            int size = new BigInteger(Arrays.copyOfRange(messagehelper, 0, 2)).intValue();
                            byte messageToDecode[] = Arrays.copyOfRange(messagehelper, 2, size + 2);
                            ByteBuffer message = ByteBuffer.wrap(messageToDecode);

                            seqNum = seqNum.add(BigInteger.valueOf(1));
                            Decoder.Potocol protocol = new Decoder.ItchYesBytes();
                            String JSON = protocol.parse(message, Long.parseLong(seqNum + ""));
                            System.out.println("[ITCH-M]" + JSON);
                            if (ITCHMain.viewMessages.isSelected()) {
                                ITCHMain.logsArea.append("[ITCH-M]" + JSON + "\n");
                                ITCHMain.logsArea.setCaretPosition(ITCHMain.logsArea.getText().length());
                            }
                            if (mip > 1) {
                                messages = new byte[messagehelper.length - size - 2];
                                messages = Arrays.copyOfRange(messagehelper, size + 2, messagehelper.length);
                                messagehelper = messages;
                            } else {
                                break;
                            }
                        }

                        packet = new DatagramPacket(new byte[64000], 64000);
                    }

                } catch (IOException | NumberFormatException e) {
                    ITCHMain.progress.setIndeterminate(false);
                    ITCHMain.connect.setEnabled(true);
                    ITCHMain.DissconnectDo.setEnabled(false);
                    ITCHMain.serviceSelector.setEnabled(true);
                    ITCHMain.printBytes.setEnabled(true);
                    ITCHMain.toFile.setEnabled(true);
                    ITCHMain.viewMessages.setEnabled(true);

                    ITCHMain.clear.setEnabled(true);
                    ITCHMain.save.setEnabled(true);

                    if (e.getMessage().equals("Receive timed out")) {

                        JFrame error = new JFrame();

                        JOptionPane.showMessageDialog(error, "Couldn't Join to the Group", "Error!", JOptionPane.ERROR_MESSAGE);
                    } else {

                        JFrame error = new JFrame();

                        JOptionPane.showMessageDialog(error, "While Connected: " + e.getMessage(), "Warning!", JOptionPane.WARNING_MESSAGE);
                    }
                }

            }

        } catch (Exception e) {
            ITCHMain.progress.setIndeterminate(false);
            ITCHMain.connect.setEnabled(true);
            ITCHMain.DissconnectDo.setEnabled(false);
            ITCHMain.serviceSelector.setEnabled(true);
            ITCHMain.printBytes.setEnabled(true);
            ITCHMain.toFile.setEnabled(true);
            ITCHMain.viewMessages.setEnabled(true);

            ITCHMain.clear.setEnabled(true);
            ITCHMain.save.setEnabled(true);

            JFrame error = new JFrame();

            JOptionPane.showMessageDialog(error, "Couldn't Join to the Group: " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);

        }
    }
}
