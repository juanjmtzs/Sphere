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

import BMV.MDBMV;
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

public class MulticastBMVYesBytes {

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

        Decoder.BMVYesBytes.count_depth = 0;
        Decoder.BMVYesBytes.count_probable_allocation_price = 0;
        Decoder.BMVYesBytes.count_continuos_auction_beginning = 0;
        Decoder.BMVYesBytes.count_status_changes = 0;
        Decoder.BMVYesBytes.count_to_middle_price_existing_quotes = 0;
        Decoder.BMVYesBytes.count_local_and_global_stock_markets = 0;
        Decoder.BMVYesBytes.count_debt_metals_and_money_market = 0;
        Decoder.BMVYesBytes.count_capital_market_warrants = 0;
        Decoder.BMVYesBytes.count_capital_market_TRACS = 0;
        Decoder.BMVYesBytes.count_mutual_funds = 0;
        Decoder.BMVYesBytes.count_underlying_value_on_warrants = 0;
        Decoder.BMVYesBytes.count_tradability = 0;
        Decoder.BMVYesBytes.count_trade_cancellation = 0;
        Decoder.BMVYesBytes.count_weighted_average_price_settlement_prices = 0;
        Decoder.BMVYesBytes.count_best_offer = 0;
        Decoder.BMVYesBytes.count_capital_trades = 0;
        Decoder.BMVYesBytes.count_event_systems = 0;
        Decoder.BMVYesBytes.count_virtual_trades = 0;
        Decoder.BMVYesBytes.count_mutual_fund_trades = 0;
        Decoder.BMVYesBytes.count_registry_operations = 0;

        Decoder.BMVYesBytes.count_derivatives_market_trades = 0;
        Decoder.BMVYesBytes.count_order_addition = 0;
        Decoder.BMVYesBytes.count_order_change = 0;
        Decoder.BMVYesBytes.count_execution_of_orders = 0;
        Decoder.BMVYesBytes.count_volume_update = 0;
        Decoder.BMVYesBytes.count_orders_cancellation = 0;
        Decoder.BMVYesBytes.count_instruments_statistics = 0;
        Decoder.BMVYesBytes.count_inavs = 0;
        Decoder.BMVYesBytes.count_general_indexes = 0;
        Decoder.BMVYesBytes.count_indexes_samples = 0;
        Decoder.BMVYesBytes.count_open_interest = 0;
        Decoder.BMVYesBytes.count_public_offerings = 0;
        Decoder.BMVYesBytes.count_futures_operations_and_swaps_derivatives_market = 0;
        Decoder.BMVYesBytes.count_derivatives_market_strategies = 0;
        Decoder.BMVYesBytes.count_dollar_buy_sell = 0;
        Decoder.BMVYesBytes.count_short_sales_balances_per_instrument = 0;
        Decoder.BMVYesBytes.count_capital_markets_multiples = 0;
        Decoder.BMVYesBytes.count_benchmarks = 0;
        Decoder.BMVYesBytes.count_capitalization_rules = 0;

        Decoder.BMVYesBytes.count_global_and_local_catalog = 0;
        Decoder.BMVYesBytes.count_turn_over_ratio_per_security = 0;
        Decoder.BMVYesBytes.count_securities_suspension = 0;
        Decoder.BMVYesBytes.count_securities_unsuspension = 0;
        Decoder.BMVYesBytes.count_negotiation_state_change_SUBRM = 0;

    }

    public static void StartHandling(String[] args) throws UnknownHostException, IOException {
        try {

            Decoder.BMVYesBytes.count_depth = 0;
            Decoder.BMVYesBytes.count_probable_allocation_price = 0;
            Decoder.BMVYesBytes.count_continuos_auction_beginning = 0;
            Decoder.BMVYesBytes.count_status_changes = 0;
            Decoder.BMVYesBytes.count_to_middle_price_existing_quotes = 0;
            Decoder.BMVYesBytes.count_local_and_global_stock_markets = 0;
            Decoder.BMVYesBytes.count_debt_metals_and_money_market = 0;
            Decoder.BMVYesBytes.count_capital_market_warrants = 0;
            Decoder.BMVYesBytes.count_capital_market_TRACS = 0;
            Decoder.BMVYesBytes.count_mutual_funds = 0;
            Decoder.BMVYesBytes.count_underlying_value_on_warrants = 0;
            Decoder.BMVYesBytes.count_tradability = 0;
            Decoder.BMVYesBytes.count_trade_cancellation = 0;
            Decoder.BMVYesBytes.count_weighted_average_price_settlement_prices = 0;
            Decoder.BMVYesBytes.count_best_offer = 0;
            Decoder.BMVYesBytes.count_capital_trades = 0;
            Decoder.BMVYesBytes.count_event_systems = 0;
            Decoder.BMVYesBytes.count_virtual_trades = 0;
            Decoder.BMVYesBytes.count_mutual_fund_trades = 0;
            Decoder.BMVYesBytes.count_registry_operations = 0;

            Decoder.BMVYesBytes.count_derivatives_market_trades = 0;
            Decoder.BMVYesBytes.count_order_addition = 0;
            Decoder.BMVYesBytes.count_order_change = 0;
            Decoder.BMVYesBytes.count_execution_of_orders = 0;
            Decoder.BMVYesBytes.count_volume_update = 0;
            Decoder.BMVYesBytes.count_orders_cancellation = 0;
            Decoder.BMVYesBytes.count_instruments_statistics = 0;
            Decoder.BMVYesBytes.count_inavs = 0;
            Decoder.BMVYesBytes.count_general_indexes = 0;
            Decoder.BMVYesBytes.count_indexes_samples = 0;
            Decoder.BMVYesBytes.count_open_interest = 0;
            Decoder.BMVYesBytes.count_public_offerings = 0;
            Decoder.BMVYesBytes.count_futures_operations_and_swaps_derivatives_market = 0;
            Decoder.BMVYesBytes.count_derivatives_market_strategies = 0;
            Decoder.BMVYesBytes.count_dollar_buy_sell = 0;
            Decoder.BMVYesBytes.count_short_sales_balances_per_instrument = 0;
            Decoder.BMVYesBytes.count_capital_markets_multiples = 0;
            Decoder.BMVYesBytes.count_benchmarks = 0;
            Decoder.BMVYesBytes.count_capitalization_rules = 0;

            Decoder.BMVYesBytes.count_global_and_local_catalog = 0;
            Decoder.BMVYesBytes.count_turn_over_ratio_per_security = 0;
            Decoder.BMVYesBytes.count_securities_suspension = 0;
            Decoder.BMVYesBytes.count_securities_unsuspension = 0;
            Decoder.BMVYesBytes.count_negotiation_state_change_SUBRM = 0;

            group = InetAddress.getByName(args[0]);
            final int port = Integer.parseInt(args[1]);

            String service = args[2];
            receiverIP = args[3];

            if (MDBMV.toFile.isSelected() == true) {
                String userHomeFolder = MDBMV.userHomeFolder;

                Output output = new Output();
                output.changeOutput(service, userHomeFolder);
            } else {

                System.setOut(System.out);
            }

            socket = new MulticastSocket(port);
            if (receiverIP == "") {
                socket.setInterface(InetAddress.getLocalHost());
            } else {
                socket.setInterface(InetAddress.getByName(receiverIP));
            }
            socket.joinGroup(group);
            socket.setSoTimeout(5000);
            if (socket.isClosed() == true) {

                MDBMV.progress.setIndeterminate(false);
                MDBMV.connect.setEnabled(true);
                MDBMV.DissconnectDo.setEnabled(false);
                MDBMV.serviceSelector.setEnabled(true);

                MDBMV.toFile.setEnabled(true);

                MDBMV.viewMessages.setEnabled(true);
                MDBMV.printBytes.setEnabled(true);

                JFrame error = new JFrame();

                JOptionPane.showMessageDialog(error, "Disconnected", "Error!", JOptionPane.ERROR_MESSAGE);

            } else {

                try {
                    continua = true;

                    DatagramPacket packet = new DatagramPacket(new byte[64000], 64000);
                    System.out.println("[BMV-M] {\"Service Name\":\"BMV Multicast\"}");
                    while (continua != false) {
                        socket.receive(packet);
                        System.out.println("[UDP-IN] Packet "
                                + Arrays.toString(Arrays.copyOfRange(packet.getData(), 0, packet.getLength())));
                        int packetSize = packet.getLength();
                        byte len[];
                        byte grupo[];
                        byte session[];
                        byte sequence[];
                        byte messagesInPackage[];
                        byte timestamp[];
                        byte messages[];

                        len = Arrays.copyOfRange(packet.getData(), 0, 2);
                        messagesInPackage = Arrays.copyOfRange(packet.getData(), 2, 3);
                        grupo = Arrays.copyOfRange(packet.getData(), 3, 4);
                        session = Arrays.copyOfRange(packet.getData(), 4, 5);
                        sequence = Arrays.copyOfRange(packet.getData(), 5, 9);
                        timestamp = Arrays.copyOfRange(packet.getData(), 9, 17);
                        messages = Arrays.copyOfRange(packet.getData(), 17, packetSize);

                        BigInteger seqNum = new BigInteger(sequence);
                        //seqNum = seqNum.subtract(BigInteger.valueOf(1));

                        MDBMV.Update.setText(""
                                + "Session:  " + new BigInteger(session) + "\n"
                                + "Total Messages:  " + seqNum + "\n"
                                + "     Depth (1):  " + Decoder.BMVYesBytes.count_depth + "\n"
                                + "     Probable Allocation Price (2):  " + Decoder.BMVYesBytes.count_probable_allocation_price + "\n"
                                + "     Continuos Auction Beginning (3):  " + Decoder.BMVYesBytes.count_continuos_auction_beginning + "\n"
                                + "     Status Changes (4):  " + Decoder.BMVYesBytes.count_status_changes + "\n"
                                + "     To Middle Price Existing Quotes (5):  " + Decoder.BMVYesBytes.count_to_middle_price_existing_quotes + "\n"
                                + "     Local and Global Stock Markets (a):  " + Decoder.BMVYesBytes.count_local_and_global_stock_markets + "\n"
                                + "     Debt Metals and Money Market(b):  " + Decoder.BMVYesBytes.count_debt_metals_and_money_market + "\n"
                                + "     Capital Market Warrants(c):  " + Decoder.BMVYesBytes.count_capital_market_warrants + "\n"
                                + "     Capital Market TRACS (e):  " + Decoder.BMVYesBytes.count_capital_market_TRACS + "\n"
                                + "     Mutual Funds (f):  " + Decoder.BMVYesBytes.count_mutual_funds + "\n"
                                + "     Underlying Value on Warrants (y):  " + Decoder.BMVYesBytes.count_underlying_value_on_warrants + "\n"
                                + "     Tradability (E):  " + Decoder.BMVYesBytes.count_tradability + "\n"
                                + "     Trade Cancellation (H):  " + Decoder.BMVYesBytes.count_trade_cancellation + "\n"
                                + "     Weighted Average Price Settlement Prices (M):  " + Decoder.BMVYesBytes.count_weighted_average_price_settlement_prices + "\n"
                                + "     Best Offer (O):  " + Decoder.BMVYesBytes.count_best_offer + "\n"
                                + "     Capital Trades (P):  " + Decoder.BMVYesBytes.count_capital_trades + "\n"
                                + "     System Events (S):  " + Decoder.BMVYesBytes.count_event_systems + "\n"
                                + "     Virtual Trades (V):  " + Decoder.BMVYesBytes.count_virtual_trades + "\n"
                                + "     Mutual Fund Trades (Y):  " + Decoder.BMVYesBytes.count_mutual_fund_trades + "\n"
                                + "     Registry Operations (Z):  " + Decoder.BMVYesBytes.count_registry_operations + "\n"
                                + "     Derivatives Market Trades (Q):  " + Decoder.BMVYesBytes.count_derivatives_market_trades + "\n"
                                + "     Order Addition (A):  " + Decoder.BMVYesBytes.count_order_addition + "\n"
                                + "     Order Change (F):  " + Decoder.BMVYesBytes.count_order_change + "\n"
                                + "     Execution of Orders (C):  " + Decoder.BMVYesBytes.count_execution_of_orders + "\n"
                                + "     Volume Update (X):  " + Decoder.BMVYesBytes.count_volume_update + "\n"
                                + "     Orders Cancellation (D):  " + Decoder.BMVYesBytes.count_orders_cancellation + "\n"
                                + "     Instruments Statistics (R):  " + Decoder.BMVYesBytes.count_instruments_statistics + "\n"
                                + "     INAV's (G):  " + Decoder.BMVYesBytes.count_inavs + "\n"
                                + "     General Indexes (U):  " + Decoder.BMVYesBytes.count_general_indexes + "\n"
                                + "     Indexes Samples (W):  " + Decoder.BMVYesBytes.count_indexes_samples + "\n"
                                + "     Open Interest (I):  " + Decoder.BMVYesBytes.count_open_interest + "\n"
                                + "     Public Offerings (B):  " + Decoder.BMVYesBytes.count_public_offerings + "\n"
                                + "     Futures Operations and Swaps Derivatives Market (d):  " + Decoder.BMVYesBytes.count_futures_operations_and_swaps_derivatives_market + "\n"
                                + "     Derivatives Market Strategies (g):  " + Decoder.BMVYesBytes.count_derivatives_market_strategies + "\n"
                                + "     Dollar Buy Sell (r):  " + Decoder.BMVYesBytes.count_dollar_buy_sell + "\n"
                                + "     Short Sales Balances per Instrument (s):  " + Decoder.BMVYesBytes.count_short_sales_balances_per_instrument + "\n"
                                + "     Capital Markets Multiples (t):  " + Decoder.BMVYesBytes.count_capital_markets_multiples + "\n"
                                + "     Benchmarks (x):  " + Decoder.BMVYesBytes.count_benchmarks + "\n"
                                + "     Capitalization Rules (z):  " + Decoder.BMVYesBytes.count_capitalization_rules + "\n"
                                + "     Global and Local Catalog(J):  " + Decoder.BMVYesBytes.count_global_and_local_catalog + "\n"
                                + "     Turn Over Ratio per Security (o):  " + Decoder.BMVYesBytes.count_turn_over_ratio_per_security + "\n"
                                + "     Securities Suspension (K):  " + Decoder.BMVYesBytes.count_securities_suspension + "\n"
                                + "     Securities Unsuspension (l):  " + Decoder.BMVYesBytes.count_securities_unsuspension + "\n"
                                + "     Negotiation State Change SUB-RM (L):  " + Decoder.BMVYesBytes.count_negotiation_state_change_SUBRM
                                + "\n");

                        System.out.println("[UDP-IN] Lenght:" + Arrays.toString(len) + " Decoded:" + new BigInteger(len));
                        System.out.println("[UDP-IN] Messages In Package:" + Arrays.toString(messagesInPackage) + " Decoded:" + new BigInteger(messagesInPackage));
                        System.out.println("[UDP-IN] Group:" + Arrays.toString(grupo) + " Decoded:" + new BigInteger(grupo));
                        System.out.println("[UDP-IN] Session:" + Arrays.toString(session) + " Decoded:" + new BigInteger(session));
                        System.out.println("[UDP-IN] Sequence:" + Arrays.toString(sequence) + " Decoded:" + new BigInteger(sequence));
                        System.out.println("[UDP-IN] Timestamp:" + Arrays.toString(timestamp) + " Decoded:" + new BigInteger(timestamp));
                        System.out.println("[UDP-IN] Messages:" + Arrays.toString(messages));
                        int mip = new BigInteger(messagesInPackage).intValue();
                        byte messagehelper[] = messages;
                        for (int x = 1; x <= mip; x++) {
                            int size = new BigInteger(Arrays.copyOfRange(messagehelper, 0, 2)).intValue();
                            byte messageToDecode[] = Arrays.copyOfRange(messagehelper, 2, size + 2);
                            ByteBuffer message = ByteBuffer.wrap(messageToDecode);

                            
                            Decoder.Potocol protocol = new Decoder.BMVYesBytes();
                            String JSON = protocol.parse(message, Long.parseLong(seqNum + ""));
                            System.out.println("[BMV-M]" + JSON);
                            if (MDBMV.viewMessages.isSelected()) {
                                MDBMV.logsArea.append("[BMV-M]" + JSON + "\n");
                                MDBMV.logsArea.setCaretPosition(MDBMV.logsArea.getText().length());
                            }
                            if (mip > 1) {

                                messages = Arrays.copyOfRange(messagehelper, size + 2, messagehelper.length);
                                messagehelper = messages;
                            } else {
                                break;
                            }
                            seqNum = seqNum.add(BigInteger.valueOf(1));
                        }

                        packet = new DatagramPacket(new byte[64000], 64000);
                    }

                } catch (IOException | NumberFormatException e) {
                    MDBMV.progress.setIndeterminate(false);
                    MDBMV.connect.setEnabled(true);
                    MDBMV.DissconnectDo.setEnabled(false);
                    MDBMV.serviceSelector.setEnabled(true);

                    MDBMV.toFile.setEnabled(true);

                    MDBMV.viewMessages.setEnabled(true);
                    MDBMV.printBytes.setEnabled(true);

                    MDBMV.clear.setEnabled(true);
                    MDBMV.save.setEnabled(true);

                    if (e.getMessage().equals("Receive timed out")) {

                        JFrame error = new JFrame();

                        JOptionPane.showMessageDialog(error, "Couldn't Join to the Group", "Error!", JOptionPane.ERROR_MESSAGE);
                    } else {

                        JFrame error = new JFrame();

                        JOptionPane.showMessageDialog(error, "While Connected: " + e, "Warning!", JOptionPane.WARNING_MESSAGE);
                    }
                }

            }

        } catch (Exception e) {
            MDBMV.progress.setIndeterminate(false);
            MDBMV.connect.setEnabled(true);
            MDBMV.DissconnectDo.setEnabled(false);
            MDBMV.serviceSelector.setEnabled(true);

            MDBMV.toFile.setEnabled(true);

            MDBMV.viewMessages.setEnabled(true);
            MDBMV.printBytes.setEnabled(true);

            MDBMV.clear.setEnabled(true);
            MDBMV.save.setEnabled(true);

            JFrame error = new JFrame();

            JOptionPane.showMessageDialog(error, "Couldn't Join to the Group: " + e, "Error!", JOptionPane.ERROR_MESSAGE);

        }
    }
}
