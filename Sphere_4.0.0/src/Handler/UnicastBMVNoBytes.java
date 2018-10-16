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
import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

public class UnicastBMVNoBytes {

    public static JFrame frame = new JFrame();
    private final String Username;
    private final String Password;
    private final String Group;
    private static long nextExpectedSequenceNumber = 0;
    private static ByteBuffer buffer;
    private static Socket clientSocket;
    private static volatile boolean connected = false;
    final private Decoder.Potocol protocol;
    private static String service;

    private static final int BMV_GROUP_LENGTH = 1;
    private static final int BMV_USER_LENGTH = 6;
    private static final int BMV_PASSWORD_LENGTH = 10;
    private static final int BMV_LOGIN_REQUEST_LENGTH = 19;
    private static final int BMV_LOGIN_LENGTH = BMV_USER_LENGTH + BMV_PASSWORD_LENGTH + BMV_GROUP_LENGTH;

    private static final int BMV_SEQUENCE_LENGTH = 4;
    private static final int BMV_QUANTITY_LENGTH = 2;
    private static final int BMV_RESEND_REQUEST_LENGTH = 9;
    private static final int BMV_RESEND_LENGTH = BMV_SEQUENCE_LENGTH + BMV_QUANTITY_LENGTH + BMV_GROUP_LENGTH;

    private static final int BMV_INSTRUMENT_LENGTH = 4;
    private static final int BMV_SNAPSHOT_TYPE_LENGTH = 1;
    private static final int BMV_SNAPSHOT_REQUEST_LENGTH = 8;
    private static final int BMV_SNAPSHOT_LENGTH = BMV_INSTRUMENT_LENGTH + BMV_SNAPSHOT_TYPE_LENGTH + BMV_GROUP_LENGTH;

    private static final int BMV_SAMPLE_LENGTH = 1;
    private static final int BMV_SNAPSHOT_HOUR_LENGTH = 8;
    private static final int BMV_INDEX_SNAPSHOT_REQUEST_LENGTH = 12;
    private static final int BMV_INDEX_SNAPSHOT_LENGTH = BMV_SAMPLE_LENGTH + BMV_SNAPSHOT_HOUR_LENGTH + BMV_GROUP_LENGTH;

    private static final int BMV_LOGOUT_LENGTH = 2;

    private static final int BMV_LOGIN_RESPONSE_LENGTH = 2;

    public static String retransmissionQuantity = "";

    private static byte session[];

    /**
     *
     * @author Juan Jesús Martínez Serrano / juanjmtzs@gmail.com
     *
     *
     */
    public UnicastBMVNoBytes() {
        Username = null;
        Password = null;
        Group = null;
        protocol = null;
    }

    public UnicastBMVNoBytes(String username, String password, String group, String host, int port, Decoder.Potocol protocol, String receiverIP) throws IOException {
        Username = username;
        Password = password;
        Group = group;
        if (!receiverIP.equals("") && !receiverIP.equals(" ") && !receiverIP.equals("0.0.0.0") && !receiverIP.equals("  ") && !receiverIP.equals("   ") && !receiverIP.equals("    ")) {
            clientSocket.bind(new InetSocketAddress(InetAddress.getByName(receiverIP), 0));
        }
        clientSocket.connect(new InetSocketAddress(host, port), 25200000);
        System.out.println("" + clientSocket.isConnected());
        System.out.println("" + clientSocket.isClosed());
        System.out.println("" + clientSocket.getInetAddress());
        System.out.println("" + clientSocket.getLocalAddress());
        System.out.println("" + clientSocket.getLocalSocketAddress());
        System.out.println("" + clientSocket.getPort());
        System.out.println("" + clientSocket.getLocalPort());
        connected = true;
        buffer.order(ByteOrder.BIG_ENDIAN);
        this.protocol = protocol;
    }

    private static class PacketType {

        public static final byte BMV_login_response_type = '&';
        public static final byte BMV_resend_request_response_type = '*';
        public static final byte BMV_snapshot_request_response_type = '+';
        public static final byte BMV_snapshot_completed_type = '?';
        public static final byte BMV_login_request_type = '!';
        public static final byte BMV_resend_request_type = '#';
        public static final byte BMV_snapshot_request_type = '$';
        public static final byte BMV_index_snapshot_request_type = '-';
        public static final byte BMV_logout_request_type = '%';

    }

    private static class Packet {

        private final Charset DEFAULT_CHARSET = Charset.forName("US-ASCII");
        private static final byte PADDING = ' ';
        private byte type;
        private int length = 1;

        Packet(byte type, int length) {
            this.type = type;
            this.length += length;
        }

        Packet(byte type) {
            this.type = type;
        }

        void padRight(ByteBuffer buffer, String s, long length) {
            buffer.put(s.getBytes(DEFAULT_CHARSET));
            for (int i = s.length(); i < length; ++i) {
                buffer.put(PADDING);
            }
        }

        void padLeft(ByteBuffer buffer, String s, long length) {
            for (int i = s.length(); i < length; ++i) {
                buffer.put(PADDING);
            }
            buffer.put(s.getBytes(DEFAULT_CHARSET));
        }

        String stripLeft(byte[] buffer, int offset, int length) {
            int i = offset;
            for (; i < length + offset; i++) {
                if (buffer[i] != PADDING) {
                    break;
                }
            }
            return new String(buffer, i, length - (i - offset), DEFAULT_CHARSET);
        }

        String stripRight(byte[] buffer, int offset, int length) {
            int i = offset + length - 1;
            for (; i >= offset; i--) {
                if (buffer[i] != PADDING) {
                    break;
                }
            }
            return new String(buffer, offset, i - offset + 1, DEFAULT_CHARSET);
        }

        void encode(ByteBuffer buffer) {
            buffer.put((byte) length);
            buffer.put(type);
        }

        void encode(ByteBuffer buffer, int length) {
            buffer.put((byte) length);
            buffer.put(type);
        }

        void decode(byte[] buffer) {
        }
    }

    public static void close() {
        if (clientSocket != null) {
            try {
                clientSocket.close();
            } catch (IOException ignore) {

                String messageError = "Socket closed" + "\n" + ignore;

                JOptionPane.showMessageDialog(frame, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

    private static void send() throws IOException {
        send(buffer);
    }

    private static void send(ByteBuffer b) throws IOException {
        clientSocket.getOutputStream().write(b.array(), 0, b.position());
        clientSocket.getOutputStream().flush();
        b.clear();
    }

    private byte readData(DataInputStream in) throws IOException {
        for (;;) {
            try {
                return in.readByte();
            } catch (SocketTimeoutException e) {

            }
        }
    }

    private void loop() throws IOException {
        final DataInputStream in = new DataInputStream(clientSocket.getInputStream());
        while (connected == true && clientSocket.isConnected() == true) {
            byte[] header = new byte[17];
            byte len[];

            in.readFully(header, 0, 17);
            len = Arrays.copyOfRange(header, 0, 2);
            byte[] messages = new byte[new BigInteger(len).intValue() - 17];
            in.readFully(messages);
            byte type = messages[2];

            byte grupo[];
            byte sequence[];
            byte messagesInPackage[];
            byte timestamp[];

            len = Arrays.copyOfRange(header, 0, 2);
            messagesInPackage = Arrays.copyOfRange(header, 2, 3);
            grupo = Arrays.copyOfRange(header, 3, 4);
            session = Arrays.copyOfRange(header, 4, 5);
            sequence = Arrays.copyOfRange(header, 5, 9);
            timestamp = Arrays.copyOfRange(header, 9, 17);

            BigInteger seqNum = new BigInteger(sequence);
            //seqNum = seqNum.subtract(BigInteger.valueOf(1));

            ByteBuffer bb = ByteBuffer.allocate(new BigInteger(len).intValue() + 2);
            bb.put(header);
            bb.put(messages);

            MDBMV.Update.setText(""
                    + "Session:  " + new BigInteger(session) + "\n"
                    + "Total Messages:  " + new Integer((Decoder.BMVNoBytes.count_depth
                            + Decoder.BMVNoBytes.count_probable_allocation_price
                            + Decoder.BMVNoBytes.count_continuos_auction_beginning
                            + Decoder.BMVNoBytes.count_status_changes
                            + Decoder.BMVNoBytes.count_to_middle_price_existing_quotes
                            + Decoder.BMVNoBytes.count_local_and_global_stock_markets
                            + Decoder.BMVNoBytes.count_debt_metals_and_money_market
                            + Decoder.BMVNoBytes.count_capital_market_warrants
                            + Decoder.BMVNoBytes.count_capital_market_TRACS
                            + Decoder.BMVNoBytes.count_mutual_funds
                            + Decoder.BMVNoBytes.count_underlying_value_on_warrants
                            + Decoder.BMVNoBytes.count_tradability
                            + Decoder.BMVNoBytes.count_trade_cancellation
                            + Decoder.BMVNoBytes.count_weighted_average_price_settlement_prices
                            + Decoder.BMVNoBytes.count_best_offer
                            + Decoder.BMVNoBytes.count_capital_trades
                            + Decoder.BMVNoBytes.count_event_systems
                            + Decoder.BMVNoBytes.count_virtual_trades
                            + Decoder.BMVNoBytes.count_mutual_fund_trades
                            + Decoder.BMVNoBytes.count_registry_operations
                            + Decoder.BMVNoBytes.count_derivatives_market_trades
                            + Decoder.BMVNoBytes.count_order_addition
                            + Decoder.BMVNoBytes.count_order_change
                            + Decoder.BMVNoBytes.count_execution_of_orders
                            + Decoder.BMVNoBytes.count_volume_update
                            + Decoder.BMVNoBytes.count_orders_cancellation
                            + Decoder.BMVNoBytes.count_instruments_statistics
                            + Decoder.BMVNoBytes.count_inavs
                            + Decoder.BMVNoBytes.count_general_indexes
                            + Decoder.BMVNoBytes.count_indexes_samples
                            + Decoder.BMVNoBytes.count_open_interest
                            + Decoder.BMVNoBytes.count_public_offerings
                            + Decoder.BMVNoBytes.count_futures_operations_and_swaps_derivatives_market
                            + Decoder.BMVNoBytes.count_derivatives_market_strategies
                            + Decoder.BMVNoBytes.count_dollar_buy_sell
                            + Decoder.BMVNoBytes.count_short_sales_balances_per_instrument
                            + Decoder.BMVNoBytes.count_capital_markets_multiples
                            + Decoder.BMVNoBytes.count_benchmarks
                            + Decoder.BMVNoBytes.count_capitalization_rules
                            + Decoder.BMVNoBytes.count_origin_closing_price
                            + Decoder.BMVNoBytes.count_consolidated_relation_between_bmvbiva_instruments
                            + Decoder.BMVNoBytes.count_consolidated_system_events
                            + Decoder.BMVNoBytes.count_consolidated_capitals_market_trades
                            + Decoder.BMVNoBytes.count_consolidated_trades_cancellation
                            + Decoder.BMVNoBytes.count_consolidated_best_bid
                            + Decoder.BMVNoBytes.count_consolidated_registration_of_order
                            + Decoder.BMVNoBytes.count_consolidated_execution_of_orders
                            + Decoder.BMVNoBytes.count_consolidated_cancellation_of_orders
                            + Decoder.BMVNoBytes.count_consolidated_amendment_of_orders
                            + Decoder.BMVNoBytes.count_consolidated_updating_of_volume
                            + Decoder.BMVNoBytes.count_consolidated_changes_in_status
                            + Decoder.BMVNoBytes.count_consolidated_reference_price
                            + Decoder.BMVNoBytes.count_consolidated_probable_price_of_allotment
                            + Decoder.BMVNoBytes.count_consolidated_startup_of_ongoing_bidding_sessions
                            + Decoder.BMVNoBytes.count_consolidated_existence_of_bids_at_mean_price
                            + Decoder.BMVNoBytes.count_consolidated_weighted_average_price_ppp
                            + Decoder.BMVNoBytes.count_consolidated_investment_companies_trades
                            + Decoder.BMVNoBytes.count_consolidated_inavs
                            + Decoder.BMVNoBytes.count_consolidated_probable_price_of_allotment_volume_indicator
                            + Decoder.BMVNoBytes.count_consolidated_spread_quality
                            + Decoder.BMVNoBytes.count_consolidated_message_ratio
                            + Decoder.BMVNoBytes.count_consolidated_depth_at_best_prices
                            + Decoder.BMVNoBytes.count_consolidated_effective_spread
                            + Decoder.BMVNoBytes.count_consolidated_trading_sniper
                            + Decoder.BMVNoBytes.count_consolidated_quotes_quality
                            + Decoder.BMVNoBytes.count_consolidated_price_leaderboard
                            + Decoder.BMVNoBytes.count_consolidated_spread
                            + Decoder.BMVNoBytes.count_consolidated_benchmark_trade
                            + Decoder.BMVNoBytes.count_consolidated_hour_tracker
                            + Decoder.BMVNoBytes.count_consolidated_big_picture
                            + Decoder.BMVNoBytes.count_consolidated_local_and_global_stock_market
                            + Decoder.BMVNoBytes.count_consolidated_capital_market_warrants
                            + Decoder.BMVNoBytes.count_consolidated_capital_market_tracs
                            + Decoder.BMVNoBytes.count_consolidated_mutual_funds
                            + Decoder.BMVNoBytes.count_consolidated_debt_and_metals_market
                            + Decoder.BMVNoBytes.count_global_and_local_catalog
                            + Decoder.BMVNoBytes.count_turn_over_ratio_per_security
                            + Decoder.BMVNoBytes.count_securities_suspension
                            + Decoder.BMVNoBytes.count_securities_unsuspension
                            + Decoder.BMVNoBytes.count_negotiation_state_change_SUBRM
                            + Decoder.BMVNoBytes.count_official_closing_price)) + "\n"
                    + "     Depth (1):  " + Decoder.BMVNoBytes.count_depth + "\n"
                    + "     Probable Allocation Price (2):  " + Decoder.BMVNoBytes.count_probable_allocation_price + "\n"
                    + "     Continuos Auction Beginning (3):  " + Decoder.BMVNoBytes.count_continuos_auction_beginning + "\n"
                    + "     Status Changes (4):  " + Decoder.BMVNoBytes.count_status_changes + "\n"
                    + "     To Middle Price Existing Quotes (5):  " + Decoder.BMVNoBytes.count_to_middle_price_existing_quotes + "\n"
                    + "     Local and Global Stock Markets (a):  " + Decoder.BMVNoBytes.count_local_and_global_stock_markets + "\n"
                    + "     Debt Metals and Money Market(b):  " + Decoder.BMVNoBytes.count_debt_metals_and_money_market + "\n"
                    + "     Capital Market Warrants(c):  " + Decoder.BMVNoBytes.count_capital_market_warrants + "\n"
                    + "     Capital Market TRACS (e):  " + Decoder.BMVNoBytes.count_capital_market_TRACS + "\n"
                    + "     Mutual Funds (f):  " + Decoder.BMVNoBytes.count_mutual_funds + "\n"
                    + "     Underlying Value on Warrants (y):  " + Decoder.BMVNoBytes.count_underlying_value_on_warrants + "\n"
                    + "     Tradability (E):  " + Decoder.BMVNoBytes.count_tradability + "\n"
                    + "     Trade Cancellation (H):  " + Decoder.BMVNoBytes.count_trade_cancellation + "\n"
                    + "     Weighted Average Price Settlement Prices (M):  " + Decoder.BMVNoBytes.count_weighted_average_price_settlement_prices + "\n"
                    + "     Best Offer (O):  " + Decoder.BMVNoBytes.count_best_offer + "\n"
                    + "     Capital Trades (P):  " + Decoder.BMVNoBytes.count_capital_trades + "\n"
                    + "     System Events (S):  " + Decoder.BMVNoBytes.count_event_systems + "\n"
                    + "     Virtual Trades (V):  " + Decoder.BMVNoBytes.count_virtual_trades + "\n"
                    + "     Mutual Fund Trades (Y):  " + Decoder.BMVNoBytes.count_mutual_fund_trades + "\n"
                    + "     Registry Operations (Z):  " + Decoder.BMVNoBytes.count_registry_operations + "\n"
                    + "     Derivatives Market Trades (Q):  " + Decoder.BMVNoBytes.count_derivatives_market_trades + "\n"
                    + "     Order Addition (A):  " + Decoder.BMVNoBytes.count_order_addition + "\n"
                    + "     Order Change (F):  " + Decoder.BMVNoBytes.count_order_change + "\n"
                    + "     Execution of Orders (C):  " + Decoder.BMVNoBytes.count_execution_of_orders + "\n"
                    + "     Volume Update (X):  " + Decoder.BMVNoBytes.count_volume_update + "\n"
                    + "     Orders Cancellation (D):  " + Decoder.BMVNoBytes.count_orders_cancellation + "\n"
                    + "     Instruments Statistics (R):  " + Decoder.BMVNoBytes.count_instruments_statistics + "\n"
                    + "     INAV's (G):  " + Decoder.BMVNoBytes.count_inavs + "\n"
                    + "     General Indexes (U):  " + Decoder.BMVNoBytes.count_general_indexes + "\n"
                    + "     Indexes Samples (W):  " + Decoder.BMVNoBytes.count_indexes_samples + "\n"
                    + "     Open Interest (I):  " + Decoder.BMVNoBytes.count_open_interest + "\n"
                    + "     Public Offerings (B):  " + Decoder.BMVNoBytes.count_public_offerings + "\n"
                    + "     Futures Operations and Swaps Derivatives Market (d):  " + Decoder.BMVNoBytes.count_futures_operations_and_swaps_derivatives_market + "\n"
                    + "     Derivatives Market Strategies (g):  " + Decoder.BMVNoBytes.count_derivatives_market_strategies + "\n"
                    + "     Dollar Buy Sell (r):  " + Decoder.BMVNoBytes.count_dollar_buy_sell + "\n"
                    + "     Short Sales Balances per Instrument (s):  " + Decoder.BMVNoBytes.count_short_sales_balances_per_instrument + "\n"
                    + "     Capital Markets Multiples (t):  " + Decoder.BMVNoBytes.count_capital_markets_multiples + "\n"
                    + "     Benchmarks (x):  " + Decoder.BMVNoBytes.count_benchmarks + "\n"
                    + "     Capitalization Rules (z):  " + Decoder.BMVNoBytes.count_capitalization_rules + "\n"
                    + "     Origin Closing Price (~):  " + Decoder.BMVNoBytes.count_origin_closing_price + "\n"
                    + "     Relation between BMV/BIVA Instruments(j):  " + Decoder.BMVNoBytes.count_consolidated_relation_between_bmvbiva_instruments + "\n"
                    + "     System Events(7):  " + Decoder.BMVNoBytes.count_consolidated_system_events + "\n"
                    + "     Capitals Market Trades (p):  " + Decoder.BMVNoBytes.count_consolidated_capitals_market_trades + "\n"
                    + "     Trades Cancellation (q):  " + Decoder.BMVNoBytes.count_consolidated_trades_cancellation + "\n"
                    + "     Best Bid(m):  " + Decoder.BMVNoBytes.count_consolidated_best_bid + "\n"
                    + "     Registration of Order(n):  " + Decoder.BMVNoBytes.count_consolidated_registration_of_order + "\n"
                    + "     Execution of Orders(k):  " + Decoder.BMVNoBytes.count_consolidated_execution_of_orders + "\n"
                    + "     Cancellation of Orders(u):  " + Decoder.BMVNoBytes.count_consolidated_cancellation_of_orders + "\n"
                    + "     Amendment of Orders(v):  " + Decoder.BMVNoBytes.count_consolidated_amendment_of_orders + "\n"
                    + "     Updating of Volume(w):  " + Decoder.BMVNoBytes.count_consolidated_updating_of_volume + "\n"
                    + "     Changes in Status(9):  " + Decoder.BMVNoBytes.count_consolidated_changes_in_status + "\n"
                    + "     Reference Price(8):  " + Decoder.BMVNoBytes.count_consolidated_reference_price + "\n"
                    + "     Probable Price of Allotment(i):  " + Decoder.BMVNoBytes.count_consolidated_probable_price_of_allotment + "\n"
                    + "     Startup of Ongoing Bidding Sessions()):  " + Decoder.BMVNoBytes.count_consolidated_startup_of_ongoing_bidding_sessions + "\n"
                    + "     Existence of Bids At Mean Price(,):  " + Decoder.BMVNoBytes.count_consolidated_existence_of_bids_at_mean_price + "\n"
                    + "     Weighted Average Price (PPP)(6):  " + Decoder.BMVNoBytes.count_consolidated_weighted_average_price_ppp + "\n"
                    + "     Investment Companies Trades(():  " + Decoder.BMVNoBytes.count_consolidated_investment_companies_trades + "\n"
                    + "     INAV's(]):  " + Decoder.BMVNoBytes.count_consolidated_inavs + "\n"
                    + "     Probable Price of Allotment/Volume Indicator(\\):  " + Decoder.BMVNoBytes.count_consolidated_probable_price_of_allotment_volume_indicator + "\n"
                    + "     Spread Quality({):  " + Decoder.BMVNoBytes.count_consolidated_spread_quality + "\n"
                    + "     Message Ratio(}):  " + Decoder.BMVNoBytes.count_consolidated_message_ratio + "\n"
                    + "     Depth at Best Prices(^):  " + Decoder.BMVNoBytes.count_consolidated_depth_at_best_prices + "\n"
                    + "     Effective Spread(=):  " + Decoder.BMVNoBytes.count_consolidated_effective_spread + "\n"
                    + "     Trading Sniper(<):  " + Decoder.BMVNoBytes.count_consolidated_trading_sniper + "\n"
                    + "     Quotes Quality(|):  " + Decoder.BMVNoBytes.count_consolidated_quotes_quality + "\n"
                    + "     Price Leaderboard(@):  " + Decoder.BMVNoBytes.count_consolidated_price_leaderboard + "\n"
                    + "     Spread(;):  " + Decoder.BMVNoBytes.count_consolidated_spread + "\n"
                    + "     Benchmark Trade(:):  " + Decoder.BMVNoBytes.count_consolidated_benchmark_trade + "\n"
                    + "     Hour Tracker(/):  " + Decoder.BMVNoBytes.count_consolidated_hour_tracker + "\n"
                    + "     Big Picture('):  " + Decoder.BMVNoBytes.count_consolidated_big_picture + "\n"
                    + "     Local and Global Stock Market(h):  " + Decoder.BMVNoBytes.count_consolidated_local_and_global_stock_market + "\n"
                    + "     Capital Market Warrants(T):  " + Decoder.BMVNoBytes.count_consolidated_capital_market_warrants + "\n"
                    + "     Capital Market TRACS([):  " + Decoder.BMVNoBytes.count_consolidated_capital_market_tracs + "\n"
                    + "     Mutual Funds(0):  " + Decoder.BMVNoBytes.count_consolidated_mutual_funds + "\n"
                    + "     Debt and Metals Market(.):  " + Decoder.BMVNoBytes.count_consolidated_debt_and_metals_market + "\n"
                    + "     Global and Local Catalog(J):  " + Decoder.BMVNoBytes.count_global_and_local_catalog + "\n"
                    + "     Turn Over Ratio per Security (o):  " + Decoder.BMVNoBytes.count_turn_over_ratio_per_security + "\n"
                    + "     Securities Suspension (K):  " + Decoder.BMVNoBytes.count_securities_suspension + "\n"
                    + "     Securities Unsuspension (l):  " + Decoder.BMVNoBytes.count_securities_unsuspension + "\n"
                    + "     Negotiation State Change SUB-RM (L):  " + Decoder.BMVNoBytes.count_negotiation_state_change_SUBRM + "\n"
                    + "     Official Closing Price (N):  " + Decoder.BMVNoBytes.count_official_closing_price
                    + "\n");

            switch (type) {

                case PacketType.BMV_login_response_type:
                    System.out.print(""
                            + "[BMV-IN]{"
                            + "\"Message\":{"
                            + "\"Name\":" + "\"" + "Login Response" + "\"" + ","
                            + "\"Type\":" + "\"" + "&" + "\"" + ","
                            + "\"Status\":" + "\"" + (char) messages[3] + "\""
                            + "}}"
                            + "\n"
                    );
                    if (MDBMV.viewMessages.isSelected()) {

                        MDBMV.logsArea.append(""
                                + "[BMV-IN]{"
                                + "\"Message\":{"
                                + "\"Name\":" + "\"" + "Login Response" + "\"" + ","
                                + "\"Type\":" + "\"" + "&" + "\"" + ","
                                + "\"Status\":" + "\"" + (char) messages[3] + "\""
                                + "}}"
                                + "\n");
                        MDBMV.logsArea.setCaretPosition(MDBMV.logsArea.getText().length());
                    }
                    break;
                case PacketType.BMV_resend_request_response_type:
                    retransmissionQuantity = "" + new BigInteger(Arrays.copyOfRange(messages, 8, 10));
                    System.out.print(""
                            + "[BMV-IN]{"
                            + "\"Message\":{"
                            + "\"Name\":" + "\"" + "Resend Request Response" + "\"" + ","
                            + "\"Type\":" + "\"" + "*" + "\"" + ","
                            + "\"Group\":" + "\"" + new BigInteger(Arrays.copyOfRange(messages, 3, 4)) + "\"" + ","
                            + "\"First Message\":" + "\"" + new BigInteger(Arrays.copyOfRange(messages, 4, 8)) + "\"" + ","
                            + "\"Quantity\":" + "\"" + new BigInteger(Arrays.copyOfRange(messages, 8, 10)) + "\"" + ","
                            + "\"Status\":" + "\"" + (char) messages[10] + "\""
                            + "}}"
                            + "\n"
                    );

                    if (MDBMV.viewMessages.isSelected()) {
                        MDBMV.logsArea.append(""
                                + "[BMV-IN]{"
                                + "\"Message\":{"
                                + "\"Name\":" + "\"" + "Resend Request Response" + "\"" + ","
                                + "\"Type\":" + "\"" + "*" + "\"" + ","
                                + "\"Group\":" + "\"" + new BigInteger(Arrays.copyOfRange(messages, 3, 4)) + "\"" + ","
                                + "\"First Message\":" + "\"" + new BigInteger(Arrays.copyOfRange(messages, 4, 8)) + "\"" + ","
                                + "\"Quantity\":" + "\"" + new BigInteger(Arrays.copyOfRange(messages, 8, 10)) + "\"" + ","
                                + "\"Status\":" + "\"" + (char) messages[10] + "\""
                                + "}}"
                                + "\n");
                        MDBMV.logsArea.setCaretPosition(MDBMV.logsArea.getText().length());
                    }
                    break;
                case PacketType.BMV_snapshot_request_response_type:
                    System.out.print(""
                            + "[BMV-IN]{"
                            + "\"Message\":{"
                            + "\"Name\":" + "\"" + "Snapshot Request Response" + "\"" + ","
                            + "\"Type\":" + "\"" + "+" + "\"" + ","
                            + "\"Quantity\":" + "\"" + new BigInteger(Arrays.copyOfRange(messages, 3, 7)) + "\"" + ","
                            + "\"Status\":" + "\"" + (char) messages[7] + "\"" + ","
                            + "\"Type\":" + "\"" + new BigInteger(Arrays.copyOfRange(messages, 8, 9)) + "\""
                            + "}}"
                            + "\n"
                    );
                    if (MDBMV.viewMessages.isSelected()) {

                        MDBMV.logsArea.append(""
                                + "[BMV-IN]{"
                                + "\"Message\":{"
                                + "\"Name\":" + "\"" + "Snapshot Request Response" + "\"" + ","
                                + "\"Type\":" + "\"" + "+" + "\"" + ","
                                + "\"Quantity\":" + "\"" + new BigInteger(Arrays.copyOfRange(messages, 3, 7)) + "\"" + ","
                                + "\"Status\":" + "\"" + (char) messages[7] + "\"" + ","
                                + "\"Type\":" + "\"" + new BigInteger(Arrays.copyOfRange(messages, 8, 9)) + "\""
                                + "}}"
                                + "\n"
                        );
                        MDBMV.logsArea.setCaretPosition(MDBMV.logsArea.getText().length());
                    }
                    break;

                case PacketType.BMV_snapshot_completed_type:
                    System.out.print(""
                            + "[BMV-IN]{"
                            + "\"Message\":{"
                            + "\"Name\":" + "\"" + "Snapshot Completed" + "\"" + ","
                            + "\"Type\":" + "\"" + "?" + "\"" + ","
                            + "\"Sequence Number\":" + "\"" + new BigInteger(Arrays.copyOfRange(messages, 3, 7)) + "\"" + ","
                            + "\"Market Data Group\":" + "\"" + new BigInteger(Arrays.copyOfRange(messages, 7, 8)) + "\"" + ","
                            + "\"Type\":" + "\"" + new BigInteger(Arrays.copyOfRange(messages, 8, 9)) + "\""
                            + "}}"
                            + "\n"
                    );

                    if (MDBMV.viewMessages.isSelected()) {
                        MDBMV.logsArea.append(""
                                + "[BMV-IN]{"
                                + "\"Message\":{"
                                + "\"Name\":" + "\"" + "Snapshot Completed" + "\"" + ","
                                + "\"Type\":" + "\"" + "?" + "\"" + ","
                                + "\"Sequence Number\":" + "\"" + new BigInteger(Arrays.copyOfRange(messages, 3, 7)) + "\"" + ","
                                + "\"Market Data Group\":" + "\"" + new BigInteger(Arrays.copyOfRange(messages, 7, 8)) + "\"" + ","
                                + "\"Type\":" + "\"" + new BigInteger(Arrays.copyOfRange(messages, 8, 9)) + "\""
                                + "}}"
                                + "\n"
                        );
                        MDBMV.logsArea.setCaretPosition(MDBMV.logsArea.getText().length());
                    }
                    break;
                default:

                    int mip = new BigInteger(messagesInPackage).intValue();
                    byte messagehelper[] = messages;
                    for (int x = 1; x <= mip; x++) {
                        int size = new BigInteger(Arrays.copyOfRange(messagehelper, 0, 2)).intValue();
                        byte messageToDecode[] = Arrays.copyOfRange(messagehelper, 2, size + 2);
                        ByteBuffer message = ByteBuffer.wrap(messageToDecode);

                        Decoder.Potocol protocol = new Decoder.BMVNoBytes();
                        String JSONmessage = protocol.parse(message, Long.parseLong(seqNum + ""));
                        System.out.println("[BMV-R]" + JSONmessage);
                        if (MDBMV.viewMessages.isSelected()) {
                            MDBMV.logsArea.append("[BMV-R]" + JSONmessage + "\n");
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
                    break;
            }
        }
    }

    public static void LoginRequest(String group, String user, String password) throws IOException {
        if (clientSocket != null) {

            if (clientSocket.isConnected() == true) {

                try {
                    final Packet login = new Packet(PacketType.BMV_login_request_type);
                    final ByteBuffer ByteBufferAux;
                    ByteBufferAux = ByteBuffer.allocate(BMV_LOGIN_REQUEST_LENGTH);
                    login.encode(ByteBufferAux, BMV_LOGIN_REQUEST_LENGTH);

                    ByteBufferAux.put(Byte.valueOf(group));
                    login.padRight(ByteBufferAux, user, BMV_USER_LENGTH);
                    login.padRight(ByteBufferAux, password, BMV_PASSWORD_LENGTH);

                    System.out.println("[BMVTCP-OUT] Login Request Packet: " + Arrays.toString(ByteBufferAux.array()));

                    System.out.print("[BMV-OUT]{");
                    System.out.print("\"Message\":{");
                    System.out.print("\"Name\":" + "\"" + "Login Request" + "\"" + ",");
                    System.out.print("\"Type\":" + "\"" + "!" + "\"" + ",");
                    System.out.print("\"Group\":" + group + ",");
                    System.out.print("\"User\":" + "\"" + user + "\"");
                    System.out.println("}}");

                    if (MDBMV.viewMessages.isSelected()) {
                        MDBMV.logsArea.append(""
                                + "[BMV-OUT]{"
                                + "\"Message\":{"
                                + "\"Name\":" + "\"" + "Login Request" + "\"" + ","
                                + "\"Type\":" + "\"" + "!" + "\"" + ","
                                + "\"Group\":" + group + ","
                                + "\"User\":" + "\"" + user + "\""
                                + "}}"
                                + "\n");
                        MDBMV.logsArea.setCaretPosition(MDBMV.logsArea.getText().length());
                    }
                    send(ByteBufferAux);

                } catch (Exception e) {

                    String messageError = "Socket closed" + "\n" + e;

                    JOptionPane.showMessageDialog(frame, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);

                }
            } else {
                String messageError = "Socket closed" + "\n";

                JOptionPane.showMessageDialog(frame, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);
            }

        } else {

            try {
                String messageError = "Socket closed" + "\n";

                JOptionPane.showMessageDialog(frame, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                System.out.println("" + e);
            }

        }

    }

    public static void ResendRequest(String group, String firstMessage, String quantity) throws IOException {
        if (clientSocket != null) {

            if (clientSocket.isConnected() == true) {

                try {
                    final Packet resend = new Packet(PacketType.BMV_resend_request_type);
                    final ByteBuffer ByteBufferAux;
                    ByteBufferAux = ByteBuffer.allocate(BMV_RESEND_REQUEST_LENGTH);
                    resend.encode(ByteBufferAux, BMV_RESEND_REQUEST_LENGTH);

                    ByteBufferAux.put(Byte.valueOf(group));
                    ByteBufferAux.putInt(Integer.parseInt(firstMessage));
                    ByteBufferAux.putShort(Short.parseShort(quantity));

                    System.out.print(""
                            + "[BMV-OUT]{"
                            + "\"Message\":{"
                            + "\"Name\":" + "\"" + "Resend Request" + "\"" + ","
                            + "\"Type\":" + "\"" + "!" + "\"" + ","
                            + "\"Group\":" + group + ","
                            + "\"First Message\":" + "\"" + firstMessage + "\"" + ","
                            + "\"Quantity\":" + "\"" + quantity + "\""
                            + "}}"
                            + "\n");

                    if (MDBMV.viewMessages.isSelected()) {
                        MDBMV.logsArea.append(""
                                + "[BMV-OUT]{"
                                + "\"Message\":{"
                                + "\"Name\":" + "\"" + "Resend Request" + "\"" + ","
                                + "\"Type\":" + "\"" + "!" + "\"" + ","
                                + "\"Group\":" + group + ","
                                + "\"First Message\":" + "\"" + firstMessage + "\"" + ","
                                + "\"Quantity\":" + "\"" + quantity + "\""
                                + "}}"
                                + "\n");
                        MDBMV.logsArea.setCaretPosition(MDBMV.logsArea.getText().length());
                    }
                    send(ByteBufferAux);

                } catch (Exception e) {

                    String messageError = "Socket closed" + "\n" + e;

                    JOptionPane.showMessageDialog(frame, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);

                }
            } else {
                String messageError = "Socket closed" + "\n";

                JOptionPane.showMessageDialog(frame, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);
            }

        } else {

            try {
                String messageError = "Socket closed" + "\n";

                JOptionPane.showMessageDialog(frame, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                System.out.println("" + e);
            }

        }

    }

    public static void SnapshotRequest(String group, String instrument, String type) throws IOException {
        if (clientSocket != null) {

            if (clientSocket.isConnected() == true) {

                try {
                    final Packet resend = new Packet(PacketType.BMV_snapshot_request_type);
                    final ByteBuffer ByteBufferAux;
                    ByteBufferAux = ByteBuffer.allocate(BMV_SNAPSHOT_REQUEST_LENGTH);
                    resend.encode(ByteBufferAux, BMV_SNAPSHOT_REQUEST_LENGTH);

                    ByteBufferAux.put(Byte.valueOf(group));
                    ByteBufferAux.putInt(Integer.parseInt(instrument));
                    ByteBufferAux.put(Byte.valueOf(type));

                    System.out.print(""
                            + "[BMV-OUT]{"
                            + "\"Message\":{"
                            + "\"Name\":" + "\"" + "Snapshot Request" + "\"" + ","
                            + "\"Type\":" + "\"" + "$" + "\"" + ","
                            + "\"Group\":" + group + ","
                            + "\"Instrument ID\":" + "\"" + instrument + "\"" + ","
                            + "\"Snapshot Type\":" + "\"" + type + "\""
                            + "}}"
                            + "\n");

                    if (MDBMV.viewMessages.isSelected()) {
                        MDBMV.logsArea.append(""
                                + "[BMV-OUT]{"
                                + "\"Message\":{"
                                + "\"Name\":" + "\"" + "Snapshot Request" + "\"" + ","
                                + "\"Type\":" + "\"" + "$" + "\"" + ","
                                + "\"Group\":" + group + ","
                                + "\"Instrument ID\":" + "\"" + instrument + "\"" + ","
                                + "\"Snapshot Type\":" + "\"" + type + "\""
                                + "}}"
                                + "\n");
                        MDBMV.logsArea.setCaretPosition(MDBMV.logsArea.getText().length());
                    }
                    send(ByteBufferAux);

                } catch (Exception e) {

                    String messageError = "Socket closed" + "\n" + e;

                    JOptionPane.showMessageDialog(frame, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);

                }
            } else {
                String messageError = "Socket closed" + "\n";

                JOptionPane.showMessageDialog(frame, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);
            }

        } else {

            try {
                String messageError = "Socket closed" + "\n";

                JOptionPane.showMessageDialog(frame, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                System.out.println("" + e);
            }

        }

    }

    public static void IndexSnapshotRequest(String group, String sample, String hour) throws IOException {
        if (clientSocket != null) {

            if (clientSocket.isConnected() == true) {

                try {
                    final Packet resend = new Packet(PacketType.BMV_index_snapshot_request_type);
                    final ByteBuffer ByteBufferAux;
                    ByteBufferAux = ByteBuffer.allocate(BMV_INDEX_SNAPSHOT_REQUEST_LENGTH);
                    resend.encode(ByteBufferAux, BMV_INDEX_SNAPSHOT_REQUEST_LENGTH);

                    ByteBufferAux.put(Byte.valueOf(group));
                    resend.padRight(ByteBufferAux, sample, 1);
                    ByteBufferAux.putLong(Long.parseLong(hour));

                    System.out.print(""
                            + "[BMV-OUT]{"
                            + "\"Message\":{"
                            + "\"Name\":" + "\"" + "Snapshot Request" + "\"" + ","
                            + "\"Type\":" + "\"" + "$" + "\"" + ","
                            + "\"Group\":" + group + ","
                            + "\"Sample\":" + "\"" + sample + "\"" + ","
                            + "\"Recovery Hour\":" + "\"" + hour + "\""
                            + "}}"
                            + "\n");

                    if (MDBMV.viewMessages.isSelected()) {
                        MDBMV.logsArea.append(""
                                + "[BMV-OUT]{"
                                + "\"Message\":{"
                                + "\"Name\":" + "\"" + "Snapshot Request" + "\"" + ","
                                + "\"Type\":" + "\"" + "$" + "\"" + ","
                                + "\"Group\":" + group + ","
                                + "\"Sample\":" + "\"" + sample + "\"" + ","
                                + "\"Recovery Hour\":" + "\"" + hour + "\""
                                + "}}"
                                + "\n");
                        MDBMV.logsArea.setCaretPosition(MDBMV.logsArea.getText().length());
                    }
                    send(ByteBufferAux);

                } catch (Exception e) {

                    String messageError = "Socket closed" + "\n" + e;

                    JOptionPane.showMessageDialog(frame, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);

                }
            } else {
                String messageError = "Socket closed" + "\n";

                JOptionPane.showMessageDialog(frame, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);
            }

        } else {

            try {
                String messageError = "Socket closed" + "\n";

                JOptionPane.showMessageDialog(frame, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                System.out.println("" + e);
            }

        }

    }

    private static void logoff() {
        try {
            final Packet logoff = new Packet(PacketType.BMV_logout_request_type);
            final ByteBuffer ByteBufferAux;
            ByteBufferAux = ByteBuffer.allocate(BMV_LOGOUT_LENGTH);
            logoff.encode(ByteBufferAux);
            System.out.println("[BMV-OUT] Logoff: " + Arrays.toString(ByteBufferAux.array()));
            send(ByteBufferAux);

        } catch (IOException e) {

            String messageError = "Socket closed" + "\n" + e;

            JOptionPane.showMessageDialog(frame, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void start() throws IOException {
        LoginRequest(Group, Username, Password);
        loop();

    }

    public static void stop() {
        connected = false;
        logoff();
        if (nextExpectedSequenceNumber > 0) {

            JOptionPane.showMessageDialog(frame, "Disconnected session with last sequence number: " + nextExpectedSequenceNumber, "Disconnected!", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    public static void kill() {
        stop();
        close();

        Decoder.BMVNoBytes.count_depth = 0;
        Decoder.BMVNoBytes.count_probable_allocation_price = 0;
        Decoder.BMVNoBytes.count_continuos_auction_beginning = 0;
        Decoder.BMVNoBytes.count_status_changes = 0;
        Decoder.BMVNoBytes.count_to_middle_price_existing_quotes = 0;
        Decoder.BMVNoBytes.count_local_and_global_stock_markets = 0;
        Decoder.BMVNoBytes.count_debt_metals_and_money_market = 0;
        Decoder.BMVNoBytes.count_capital_market_warrants = 0;
        Decoder.BMVNoBytes.count_capital_market_TRACS = 0;
        Decoder.BMVNoBytes.count_mutual_funds = 0;
        Decoder.BMVNoBytes.count_underlying_value_on_warrants = 0;
        Decoder.BMVNoBytes.count_tradability = 0;
        Decoder.BMVNoBytes.count_trade_cancellation = 0;
        Decoder.BMVNoBytes.count_weighted_average_price_settlement_prices = 0;
        Decoder.BMVNoBytes.count_best_offer = 0;
        Decoder.BMVNoBytes.count_capital_trades = 0;
        Decoder.BMVNoBytes.count_event_systems = 0;
        Decoder.BMVNoBytes.count_virtual_trades = 0;
        Decoder.BMVNoBytes.count_mutual_fund_trades = 0;
        Decoder.BMVNoBytes.count_registry_operations = 0;

        Decoder.BMVNoBytes.count_derivatives_market_trades = 0;
        Decoder.BMVNoBytes.count_order_addition = 0;
        Decoder.BMVNoBytes.count_order_change = 0;
        Decoder.BMVNoBytes.count_execution_of_orders = 0;
        Decoder.BMVNoBytes.count_volume_update = 0;
        Decoder.BMVNoBytes.count_orders_cancellation = 0;
        Decoder.BMVNoBytes.count_instruments_statistics = 0;
        Decoder.BMVNoBytes.count_inavs = 0;
        Decoder.BMVNoBytes.count_general_indexes = 0;
        Decoder.BMVNoBytes.count_indexes_samples = 0;
        Decoder.BMVNoBytes.count_open_interest = 0;
        Decoder.BMVNoBytes.count_public_offerings = 0;
        Decoder.BMVNoBytes.count_futures_operations_and_swaps_derivatives_market = 0;
        Decoder.BMVNoBytes.count_derivatives_market_strategies = 0;
        Decoder.BMVNoBytes.count_dollar_buy_sell = 0;
        Decoder.BMVNoBytes.count_short_sales_balances_per_instrument = 0;
        Decoder.BMVNoBytes.count_capital_markets_multiples = 0;
        Decoder.BMVNoBytes.count_benchmarks = 0;
        Decoder.BMVNoBytes.count_capitalization_rules = 0;

        Decoder.BMVNoBytes.count_global_and_local_catalog = 0;
        Decoder.BMVNoBytes.count_turn_over_ratio_per_security = 0;
        Decoder.BMVNoBytes.count_securities_suspension = 0;
        Decoder.BMVNoBytes.count_securities_unsuspension = 0;
        Decoder.BMVNoBytes.count_negotiation_state_change_SUBRM = 0;

    }

    /**
     *
     * @author Juan Jesús Martínez Serrano / juanjmtzs@gmail.com
     * @param args
     * @throws java.io.IOException
     *
     *
     */
    public static void StartHandling(String[] args) throws IOException, UnsupportedLookAndFeelException {
        clientSocket = new Socket();
        buffer = ByteBuffer.allocate(4 * 1024);

        Decoder.BMVNoBytes.count_depth = 0;
        Decoder.BMVNoBytes.count_probable_allocation_price = 0;
        Decoder.BMVNoBytes.count_continuos_auction_beginning = 0;
        Decoder.BMVNoBytes.count_status_changes = 0;
        Decoder.BMVNoBytes.count_to_middle_price_existing_quotes = 0;
        Decoder.BMVNoBytes.count_local_and_global_stock_markets = 0;
        Decoder.BMVNoBytes.count_debt_metals_and_money_market = 0;
        Decoder.BMVNoBytes.count_capital_market_warrants = 0;
        Decoder.BMVNoBytes.count_capital_market_TRACS = 0;
        Decoder.BMVNoBytes.count_mutual_funds = 0;
        Decoder.BMVNoBytes.count_underlying_value_on_warrants = 0;
        Decoder.BMVNoBytes.count_tradability = 0;
        Decoder.BMVNoBytes.count_trade_cancellation = 0;
        Decoder.BMVNoBytes.count_weighted_average_price_settlement_prices = 0;
        Decoder.BMVNoBytes.count_best_offer = 0;
        Decoder.BMVNoBytes.count_capital_trades = 0;
        Decoder.BMVNoBytes.count_event_systems = 0;
        Decoder.BMVNoBytes.count_virtual_trades = 0;
        Decoder.BMVNoBytes.count_mutual_fund_trades = 0;
        Decoder.BMVNoBytes.count_registry_operations = 0;

        Decoder.BMVNoBytes.count_derivatives_market_trades = 0;
        Decoder.BMVNoBytes.count_order_addition = 0;
        Decoder.BMVNoBytes.count_order_change = 0;
        Decoder.BMVNoBytes.count_execution_of_orders = 0;
        Decoder.BMVNoBytes.count_volume_update = 0;
        Decoder.BMVNoBytes.count_orders_cancellation = 0;
        Decoder.BMVNoBytes.count_instruments_statistics = 0;
        Decoder.BMVNoBytes.count_inavs = 0;
        Decoder.BMVNoBytes.count_general_indexes = 0;
        Decoder.BMVNoBytes.count_indexes_samples = 0;
        Decoder.BMVNoBytes.count_open_interest = 0;
        Decoder.BMVNoBytes.count_public_offerings = 0;
        Decoder.BMVNoBytes.count_futures_operations_and_swaps_derivatives_market = 0;
        Decoder.BMVNoBytes.count_derivatives_market_strategies = 0;
        Decoder.BMVNoBytes.count_dollar_buy_sell = 0;
        Decoder.BMVNoBytes.count_short_sales_balances_per_instrument = 0;
        Decoder.BMVNoBytes.count_capital_markets_multiples = 0;
        Decoder.BMVNoBytes.count_benchmarks = 0;
        Decoder.BMVNoBytes.count_capitalization_rules = 0;

        Decoder.BMVNoBytes.count_global_and_local_catalog = 0;
        Decoder.BMVNoBytes.count_turn_over_ratio_per_security = 0;
        Decoder.BMVNoBytes.count_securities_suspension = 0;
        Decoder.BMVNoBytes.count_securities_unsuspension = 0;
        Decoder.BMVNoBytes.count_negotiation_state_change_SUBRM = 0;

        try {

            service = args[2];
            if (MDBMV.toFile.isSelected() == true) {

                String userHomeFolder = MDBMV.userHomeFolder;
                Output output = new Output();
                output.changeOutput(service, userHomeFolder);
            } else {
                System.setOut(System.out);
            }

            final UnicastBMVNoBytes s = new UnicastBMVNoBytes(args[3], args[4], args[5], args[0], Integer.parseInt(args[1]), new Decoder.BMVNoBytes(), args[6]);
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    s.stop();
                }
            });
            s.start();
        } catch (Exception e) {

            MDBMV.progress.setIndeterminate(false);
            MDBMV.connect.setEnabled(true);
            MDBMV.serviceSelector.setEnabled(true);
            MDBMV.DissconnectDo.setEnabled(false);
            MDBMV.printBytes.setEnabled(true);
            MDBMV.toFile.setEnabled(true);
            MDBMV.viewMessages.setEnabled(true);

            MDBMV.resendButton.setEnabled(false);
            MDBMV.snapshotButton.setEnabled(false);
            MDBMV.indSnapshotButton.setEnabled(false);
            MDBMV.clear.setEnabled(true);
            MDBMV.save.setEnabled(true);

            String messageError = "While Running: " + "\n\n" + e.getMessage();

            if (e.getMessage() == "null") {

                JFrame error = new JFrame();

                JOptionPane.showMessageDialog(error, messageError, "Warning!", JOptionPane.WARNING_MESSAGE);
            } else if ((e + "").equals("java.net.ConnectException: Connection refused: connect")) {
                messageError = "Something Went Wrong: " + "\n\n" + "Connection Refused, Please Verify.";
                JFrame error = new JFrame();

                JOptionPane.showMessageDialog(error, messageError, "Error!", JOptionPane.ERROR_MESSAGE);
            } else {
                JFrame error = new JFrame();
                try {
                    MDBMV.Update.setText(""
                    + "Session:  " + new BigInteger(session) + "\n"
                    + "Total Messages:  " + new Integer((Decoder.BMVNoBytes.count_depth
                            + Decoder.BMVNoBytes.count_probable_allocation_price
                            + Decoder.BMVNoBytes.count_continuos_auction_beginning
                            + Decoder.BMVNoBytes.count_status_changes
                            + Decoder.BMVNoBytes.count_to_middle_price_existing_quotes
                            + Decoder.BMVNoBytes.count_local_and_global_stock_markets
                            + Decoder.BMVNoBytes.count_debt_metals_and_money_market
                            + Decoder.BMVNoBytes.count_capital_market_warrants
                            + Decoder.BMVNoBytes.count_capital_market_TRACS
                            + Decoder.BMVNoBytes.count_mutual_funds
                            + Decoder.BMVNoBytes.count_underlying_value_on_warrants
                            + Decoder.BMVNoBytes.count_tradability
                            + Decoder.BMVNoBytes.count_trade_cancellation
                            + Decoder.BMVNoBytes.count_weighted_average_price_settlement_prices
                            + Decoder.BMVNoBytes.count_best_offer
                            + Decoder.BMVNoBytes.count_capital_trades
                            + Decoder.BMVNoBytes.count_event_systems
                            + Decoder.BMVNoBytes.count_virtual_trades
                            + Decoder.BMVNoBytes.count_mutual_fund_trades
                            + Decoder.BMVNoBytes.count_registry_operations
                            + Decoder.BMVNoBytes.count_derivatives_market_trades
                            + Decoder.BMVNoBytes.count_order_addition
                            + Decoder.BMVNoBytes.count_order_change
                            + Decoder.BMVNoBytes.count_execution_of_orders
                            + Decoder.BMVNoBytes.count_volume_update
                            + Decoder.BMVNoBytes.count_orders_cancellation
                            + Decoder.BMVNoBytes.count_instruments_statistics
                            + Decoder.BMVNoBytes.count_inavs
                            + Decoder.BMVNoBytes.count_general_indexes
                            + Decoder.BMVNoBytes.count_indexes_samples
                            + Decoder.BMVNoBytes.count_open_interest
                            + Decoder.BMVNoBytes.count_public_offerings
                            + Decoder.BMVNoBytes.count_futures_operations_and_swaps_derivatives_market
                            + Decoder.BMVNoBytes.count_derivatives_market_strategies
                            + Decoder.BMVNoBytes.count_dollar_buy_sell
                            + Decoder.BMVNoBytes.count_short_sales_balances_per_instrument
                            + Decoder.BMVNoBytes.count_capital_markets_multiples
                            + Decoder.BMVNoBytes.count_benchmarks
                            + Decoder.BMVNoBytes.count_capitalization_rules
                            + Decoder.BMVNoBytes.count_origin_closing_price
                            + Decoder.BMVNoBytes.count_consolidated_relation_between_bmvbiva_instruments
                            + Decoder.BMVNoBytes.count_consolidated_system_events
                            + Decoder.BMVNoBytes.count_consolidated_capitals_market_trades
                            + Decoder.BMVNoBytes.count_consolidated_trades_cancellation
                            + Decoder.BMVNoBytes.count_consolidated_best_bid
                            + Decoder.BMVNoBytes.count_consolidated_registration_of_order
                            + Decoder.BMVNoBytes.count_consolidated_execution_of_orders
                            + Decoder.BMVNoBytes.count_consolidated_cancellation_of_orders
                            + Decoder.BMVNoBytes.count_consolidated_amendment_of_orders
                            + Decoder.BMVNoBytes.count_consolidated_updating_of_volume
                            + Decoder.BMVNoBytes.count_consolidated_changes_in_status
                            + Decoder.BMVNoBytes.count_consolidated_reference_price
                            + Decoder.BMVNoBytes.count_consolidated_probable_price_of_allotment
                            + Decoder.BMVNoBytes.count_consolidated_startup_of_ongoing_bidding_sessions
                            + Decoder.BMVNoBytes.count_consolidated_existence_of_bids_at_mean_price
                            + Decoder.BMVNoBytes.count_consolidated_weighted_average_price_ppp
                            + Decoder.BMVNoBytes.count_consolidated_investment_companies_trades
                            + Decoder.BMVNoBytes.count_consolidated_inavs
                            + Decoder.BMVNoBytes.count_consolidated_probable_price_of_allotment_volume_indicator
                            + Decoder.BMVNoBytes.count_consolidated_spread_quality
                            + Decoder.BMVNoBytes.count_consolidated_message_ratio
                            + Decoder.BMVNoBytes.count_consolidated_depth_at_best_prices
                            + Decoder.BMVNoBytes.count_consolidated_effective_spread
                            + Decoder.BMVNoBytes.count_consolidated_trading_sniper
                            + Decoder.BMVNoBytes.count_consolidated_quotes_quality
                            + Decoder.BMVNoBytes.count_consolidated_price_leaderboard
                            + Decoder.BMVNoBytes.count_consolidated_spread
                            + Decoder.BMVNoBytes.count_consolidated_benchmark_trade
                            + Decoder.BMVNoBytes.count_consolidated_hour_tracker
                            + Decoder.BMVNoBytes.count_consolidated_big_picture
                            + Decoder.BMVNoBytes.count_consolidated_local_and_global_stock_market
                            + Decoder.BMVNoBytes.count_consolidated_capital_market_warrants
                            + Decoder.BMVNoBytes.count_consolidated_capital_market_tracs
                            + Decoder.BMVNoBytes.count_consolidated_mutual_funds
                            + Decoder.BMVNoBytes.count_consolidated_debt_and_metals_market
                            + Decoder.BMVNoBytes.count_global_and_local_catalog
                            + Decoder.BMVNoBytes.count_turn_over_ratio_per_security
                            + Decoder.BMVNoBytes.count_securities_suspension
                            + Decoder.BMVNoBytes.count_securities_unsuspension
                            + Decoder.BMVNoBytes.count_negotiation_state_change_SUBRM
                            + Decoder.BMVNoBytes.count_official_closing_price)) + "\n"
                    + "     Depth (1):  " + Decoder.BMVNoBytes.count_depth + "\n"
                    + "     Probable Allocation Price (2):  " + Decoder.BMVNoBytes.count_probable_allocation_price + "\n"
                    + "     Continuos Auction Beginning (3):  " + Decoder.BMVNoBytes.count_continuos_auction_beginning + "\n"
                    + "     Status Changes (4):  " + Decoder.BMVNoBytes.count_status_changes + "\n"
                    + "     To Middle Price Existing Quotes (5):  " + Decoder.BMVNoBytes.count_to_middle_price_existing_quotes + "\n"
                    + "     Local and Global Stock Markets (a):  " + Decoder.BMVNoBytes.count_local_and_global_stock_markets + "\n"
                    + "     Debt Metals and Money Market(b):  " + Decoder.BMVNoBytes.count_debt_metals_and_money_market + "\n"
                    + "     Capital Market Warrants(c):  " + Decoder.BMVNoBytes.count_capital_market_warrants + "\n"
                    + "     Capital Market TRACS (e):  " + Decoder.BMVNoBytes.count_capital_market_TRACS + "\n"
                    + "     Mutual Funds (f):  " + Decoder.BMVNoBytes.count_mutual_funds + "\n"
                    + "     Underlying Value on Warrants (y):  " + Decoder.BMVNoBytes.count_underlying_value_on_warrants + "\n"
                    + "     Tradability (E):  " + Decoder.BMVNoBytes.count_tradability + "\n"
                    + "     Trade Cancellation (H):  " + Decoder.BMVNoBytes.count_trade_cancellation + "\n"
                    + "     Weighted Average Price Settlement Prices (M):  " + Decoder.BMVNoBytes.count_weighted_average_price_settlement_prices + "\n"
                    + "     Best Offer (O):  " + Decoder.BMVNoBytes.count_best_offer + "\n"
                    + "     Capital Trades (P):  " + Decoder.BMVNoBytes.count_capital_trades + "\n"
                    + "     System Events (S):  " + Decoder.BMVNoBytes.count_event_systems + "\n"
                    + "     Virtual Trades (V):  " + Decoder.BMVNoBytes.count_virtual_trades + "\n"
                    + "     Mutual Fund Trades (Y):  " + Decoder.BMVNoBytes.count_mutual_fund_trades + "\n"
                    + "     Registry Operations (Z):  " + Decoder.BMVNoBytes.count_registry_operations + "\n"
                    + "     Derivatives Market Trades (Q):  " + Decoder.BMVNoBytes.count_derivatives_market_trades + "\n"
                    + "     Order Addition (A):  " + Decoder.BMVNoBytes.count_order_addition + "\n"
                    + "     Order Change (F):  " + Decoder.BMVNoBytes.count_order_change + "\n"
                    + "     Execution of Orders (C):  " + Decoder.BMVNoBytes.count_execution_of_orders + "\n"
                    + "     Volume Update (X):  " + Decoder.BMVNoBytes.count_volume_update + "\n"
                    + "     Orders Cancellation (D):  " + Decoder.BMVNoBytes.count_orders_cancellation + "\n"
                    + "     Instruments Statistics (R):  " + Decoder.BMVNoBytes.count_instruments_statistics + "\n"
                    + "     INAV's (G):  " + Decoder.BMVNoBytes.count_inavs + "\n"
                    + "     General Indexes (U):  " + Decoder.BMVNoBytes.count_general_indexes + "\n"
                    + "     Indexes Samples (W):  " + Decoder.BMVNoBytes.count_indexes_samples + "\n"
                    + "     Open Interest (I):  " + Decoder.BMVNoBytes.count_open_interest + "\n"
                    + "     Public Offerings (B):  " + Decoder.BMVNoBytes.count_public_offerings + "\n"
                    + "     Futures Operations and Swaps Derivatives Market (d):  " + Decoder.BMVNoBytes.count_futures_operations_and_swaps_derivatives_market + "\n"
                    + "     Derivatives Market Strategies (g):  " + Decoder.BMVNoBytes.count_derivatives_market_strategies + "\n"
                    + "     Dollar Buy Sell (r):  " + Decoder.BMVNoBytes.count_dollar_buy_sell + "\n"
                    + "     Short Sales Balances per Instrument (s):  " + Decoder.BMVNoBytes.count_short_sales_balances_per_instrument + "\n"
                    + "     Capital Markets Multiples (t):  " + Decoder.BMVNoBytes.count_capital_markets_multiples + "\n"
                    + "     Benchmarks (x):  " + Decoder.BMVNoBytes.count_benchmarks + "\n"
                    + "     Capitalization Rules (z):  " + Decoder.BMVNoBytes.count_capitalization_rules + "\n"
                    + "     Origin Closing Price (~):  " + Decoder.BMVNoBytes.count_origin_closing_price + "\n"
                    + "     Relation between BMV/BIVA Instruments(j):  " + Decoder.BMVNoBytes.count_consolidated_relation_between_bmvbiva_instruments + "\n"
                    + "     System Events(7):  " + Decoder.BMVNoBytes.count_consolidated_system_events + "\n"
                    + "     Capitals Market Trades (p):  " + Decoder.BMVNoBytes.count_consolidated_capitals_market_trades + "\n"
                    + "     Trades Cancellation (q):  " + Decoder.BMVNoBytes.count_consolidated_trades_cancellation + "\n"
                    + "     Best Bid(m):  " + Decoder.BMVNoBytes.count_consolidated_best_bid + "\n"
                    + "     Registration of Order(n):  " + Decoder.BMVNoBytes.count_consolidated_registration_of_order + "\n"
                    + "     Execution of Orders(k):  " + Decoder.BMVNoBytes.count_consolidated_execution_of_orders + "\n"
                    + "     Cancellation of Orders(u):  " + Decoder.BMVNoBytes.count_consolidated_cancellation_of_orders + "\n"
                    + "     Amendment of Orders(v):  " + Decoder.BMVNoBytes.count_consolidated_amendment_of_orders + "\n"
                    + "     Updating of Volume(w):  " + Decoder.BMVNoBytes.count_consolidated_updating_of_volume + "\n"
                    + "     Changes in Status(9):  " + Decoder.BMVNoBytes.count_consolidated_changes_in_status + "\n"
                    + "     Reference Price(8):  " + Decoder.BMVNoBytes.count_consolidated_reference_price + "\n"
                    + "     Probable Price of Allotment(i):  " + Decoder.BMVNoBytes.count_consolidated_probable_price_of_allotment + "\n"
                    + "     Startup of Ongoing Bidding Sessions()):  " + Decoder.BMVNoBytes.count_consolidated_startup_of_ongoing_bidding_sessions + "\n"
                    + "     Existence of Bids At Mean Price(,):  " + Decoder.BMVNoBytes.count_consolidated_existence_of_bids_at_mean_price + "\n"
                    + "     Weighted Average Price (PPP)(6):  " + Decoder.BMVNoBytes.count_consolidated_weighted_average_price_ppp + "\n"
                    + "     Investment Companies Trades(():  " + Decoder.BMVNoBytes.count_consolidated_investment_companies_trades + "\n"
                    + "     INAV's(]):  " + Decoder.BMVNoBytes.count_consolidated_inavs + "\n"
                    + "     Probable Price of Allotment/Volume Indicator(\\):  " + Decoder.BMVNoBytes.count_consolidated_probable_price_of_allotment_volume_indicator + "\n"
                    + "     Spread Quality({):  " + Decoder.BMVNoBytes.count_consolidated_spread_quality + "\n"
                    + "     Message Ratio(}):  " + Decoder.BMVNoBytes.count_consolidated_message_ratio + "\n"
                    + "     Depth at Best Prices(^):  " + Decoder.BMVNoBytes.count_consolidated_depth_at_best_prices + "\n"
                    + "     Effective Spread(=):  " + Decoder.BMVNoBytes.count_consolidated_effective_spread + "\n"
                    + "     Trading Sniper(<):  " + Decoder.BMVNoBytes.count_consolidated_trading_sniper + "\n"
                    + "     Quotes Quality(|):  " + Decoder.BMVNoBytes.count_consolidated_quotes_quality + "\n"
                    + "     Price Leaderboard(@):  " + Decoder.BMVNoBytes.count_consolidated_price_leaderboard + "\n"
                    + "     Spread(;):  " + Decoder.BMVNoBytes.count_consolidated_spread + "\n"
                    + "     Benchmark Trade(:):  " + Decoder.BMVNoBytes.count_consolidated_benchmark_trade + "\n"
                    + "     Hour Tracker(/):  " + Decoder.BMVNoBytes.count_consolidated_hour_tracker + "\n"
                    + "     Big Picture('):  " + Decoder.BMVNoBytes.count_consolidated_big_picture + "\n"
                    + "     Local and Global Stock Market(h):  " + Decoder.BMVNoBytes.count_consolidated_local_and_global_stock_market + "\n"
                    + "     Capital Market Warrants(T):  " + Decoder.BMVNoBytes.count_consolidated_capital_market_warrants + "\n"
                    + "     Capital Market TRACS([):  " + Decoder.BMVNoBytes.count_consolidated_capital_market_tracs + "\n"
                    + "     Mutual Funds(0):  " + Decoder.BMVNoBytes.count_consolidated_mutual_funds + "\n"
                    + "     Debt and Metals Market(.):  " + Decoder.BMVNoBytes.count_consolidated_debt_and_metals_market + "\n"
                    + "     Global and Local Catalog(J):  " + Decoder.BMVNoBytes.count_global_and_local_catalog + "\n"
                    + "     Turn Over Ratio per Security (o):  " + Decoder.BMVNoBytes.count_turn_over_ratio_per_security + "\n"
                    + "     Securities Suspension (K):  " + Decoder.BMVNoBytes.count_securities_suspension + "\n"
                    + "     Securities Unsuspension (l):  " + Decoder.BMVNoBytes.count_securities_unsuspension + "\n"
                    + "     Negotiation State Change SUB-RM (L):  " + Decoder.BMVNoBytes.count_negotiation_state_change_SUBRM + "\n"
                    + "     Official Closing Price (N):  " + Decoder.BMVNoBytes.count_official_closing_price
                    + "\n");
                } catch (Exception ex) {
                }
                JOptionPane.showMessageDialog(error, "Connection was closed.\n" + e, "Dissconnected!", JOptionPane.INFORMATION_MESSAGE);

            }
            close();
        }
    }

}
