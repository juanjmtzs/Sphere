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

package Requester;

import ITCH.ITCHMain;
import Sphere.Output;
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RequesterYesBytes {

    private static InetAddress group;

    private static DatagramSocket clientSocket;

    public static void kill() throws IOException {
        clientSocket.close();

    }

    public static void StartHandling(String args[]) throws Exception {
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

        String service = args[5];
        if (ITCHMain.toFile.isSelected() == true) {
            String userHomeFolder = ITCHMain.userHomeFolder;
            Output output = new Output();
            output.changeOutput(service, userHomeFolder);
        } else {
            System.setOut(System.out);
        }

        clientSocket = new DatagramSocket();
        clientSocket.setSoTimeout(2000);
        InetAddress IPAddress = InetAddress.getByName(args[0]);
        byte[] sendData = new byte[20];
        byte[] receiveData = new byte[64000];

        byte[] sessBytes = new byte[10];

        String session = args[2];
        int sequence = Integer.parseInt(args[3]);
        int messages = Integer.parseInt(args[4]);
        sessBytes = session.getBytes();

        int seqHelper = (int) sequence;
        int messagHelper = (int) messages - (int) sequence;

        try {
            while (seqHelper <= (int) sequence + messagHelper) {

                sendData = buildRequest(sessBytes, (long) seqHelper, (short) 60);

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Integer.parseInt(args[1]));

                clientSocket.send(sendPacket);
                System.out.println("[MoldUDP-OUT] Petition: " + Arrays.toString(sendData));
                DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(packet);
                System.out.println("[MoldUDP-IN] Packet "
                        + Arrays.toString(Arrays.copyOfRange(packet.getData(), 0, packet.getLength())));

                int packetSize = packet.getLength();
                byte sessionRec[] = new byte[10];
                byte sequenceRec[] = new byte[8];
                byte messagesInPackage[] = new byte[2];
                byte messagesRec[] = new byte[packet.getLength() - 20];

                sessionRec = Arrays.copyOfRange(packet.getData(), 0, 10);
                sequenceRec = Arrays.copyOfRange(packet.getData(), 10, 18);
                messagesInPackage = Arrays.copyOfRange(packet.getData(), 18, 20);
                messagesRec = Arrays.copyOfRange(packet.getData(), 20, packetSize);

                BigInteger seqNum = new BigInteger(sequenceRec);
                seqNum = seqNum.subtract(BigInteger.valueOf(1));

                System.out.println("[MoldUDP-IN] Session:" + Arrays.toString(sessionRec) + " Decoded:" + new String(sessionRec));
                System.out.println("[MoldUDP-IN] Sequence:" + Arrays.toString(sequenceRec) + " Decoded:" + new BigInteger(sequenceRec));
                System.out.println("[MoldUDP-IN] Messages In Package:" + Arrays.toString(messagesInPackage) + " Decoded:" + new BigInteger(messagesInPackage));
                System.out.println("[MoldUDP-IN] Messages:" + Arrays.toString(messagesRec));

                int mip = new BigInteger(messagesInPackage).intValue();
                byte messagehelper[] = messagesRec;
                for (int y = 1; y <= mip; y++) {

                    new BigInteger(Arrays.copyOfRange(messagehelper, 0, 2)).intValue();
                    int size = new BigInteger(Arrays.copyOfRange(messagehelper, 0, 2)).intValue();
                    byte messageToDecode[] = Arrays.copyOfRange(messagehelper, 2, size + 2);
                    ByteBuffer message = ByteBuffer.wrap(messageToDecode);

                    seqNum = seqNum.add(BigInteger.valueOf(1));
                    if (seqNum.intValue() == (int) messages + 1) {
                        break;
                    }

                    Decoder.Potocol protocol = (Decoder.Potocol) new Decoder.ItchYesBytes();
                    String JSON = protocol.parse(message, Long.parseLong(seqNum + ""));
                    System.out.println("[ITCH-R]" + JSON);
                    if (ITCHMain.viewMessages.isSelected()) {
                        ITCHMain.logsArea.append("[ITCH-R]" + JSON + "\n");
                        ITCHMain.logsArea.setCaretPosition(ITCHMain.logsArea.getText().length());
                    }
                    if (mip > 1) {
                        messagesRec = new byte[messagehelper.length - size - 2];
                        messagesRec = Arrays.copyOfRange(messagehelper, size + 2, messagehelper.length);
                        messagehelper = messagesRec;
                    } else {
                        break;
                    }
                }

                seqHelper = seqNum.intValue() + 1;
                long counts = Decoder.ItchYesBytes.count_add_order + Decoder.ItchYesBytes.count_broken_trade + Decoder.ItchYesBytes.count_order_executed_with_price + Decoder.ItchYesBytes.count_order_delete + Decoder.ItchYesBytes.count_order_executed + Decoder.ItchYesBytes.count_order_replace + Decoder.ItchYesBytes.count_trading_action + Decoder.ItchYesBytes.count_indicative_price_quantity
                        + Decoder.ItchYesBytes.count_price_tick + Decoder.ItchYesBytes.count_quantity_tick + Decoder.ItchYesBytes.count_stock_directory + Decoder.ItchYesBytes.count_system_event + Decoder.ItchYesBytes.count_time_stamp
                        + Decoder.ItchYesBytes.count_participant_directory + Decoder.ItchYesBytes.count_trade_message
                        + Decoder.ItchYesBytes.count_glimpse_snapshot
                        + Decoder.ItchYesBytes.count_reference_price
                        + Decoder.ItchYesBytes.count_best_bid_offer
                        + Decoder.ItchYesBytes.count_news_message;

                ITCHMain.Update.setText(""
                        + "Total Messages:  " + counts + "\n"
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

            }

            clientSocket.close();
            CompletedRequest();
        } catch (Exception e) {
            ITCHMain.progress.setIndeterminate(false);
            ITCHMain.connect.setEnabled(true);
            ITCHMain.serviceSelector.setEnabled(true);
            ITCHMain.DissconnectDo.setEnabled(false);
            ITCHMain.printBytes.setEnabled(true);
            ITCHMain.toFile.setEnabled(true);
            ITCHMain.viewMessages.setEnabled(true);

            ITCHMain.clear.setEnabled(true);
            ITCHMain.save.setEnabled(true);

            JFrame error = new JFrame();
            JOptionPane.showMessageDialog(error, "Something Went Wrong: " + "\n\n" + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);

        }
    }

    public static void CompletedRequest() {
        ITCHMain.progress.setIndeterminate(false);
        ITCHMain.connect.setEnabled(true);
        ITCHMain.serviceSelector.setEnabled(true);
        ITCHMain.DissconnectDo.setEnabled(false);
        ITCHMain.printBytes.setEnabled(true);
        ITCHMain.toFile.setEnabled(true);
        ITCHMain.viewMessages.setEnabled(true);

        ITCHMain.clear.setEnabled(true);
        ITCHMain.save.setEnabled(true);

        JFrame error = new JFrame();
        JOptionPane.showMessageDialog(error, "Retransmission Completed.", "Done!", JOptionPane.INFORMATION_MESSAGE);
    }

    static byte[] buildRequest(byte[] session, long sequenceNumber, short messageCount) {
        ByteBuffer buf = ByteBuffer.allocate(20);

        for (int i = 0; i < Math.max(0, 10 - session.length); ++i) {
            buf.put((byte) ' ');
        }
        for (int i = 0; i < Math.min(10, session.length); ++i) {
            buf.put(session[i]);
        }

        buf.putLong(sequenceNumber);

        buf.putShort(messageCount);

        return buf.array();
    }

}
