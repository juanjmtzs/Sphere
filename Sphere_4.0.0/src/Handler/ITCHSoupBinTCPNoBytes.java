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
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

public class ITCHSoupBinTCPNoBytes {

    public static JFrame frame = null;
    public static JFrame outPut = null;
    private final int USERNAME_LENGTH = 6;
    private final int PASSWORD_LENGTH = 10;
    private final int SESSION_LENGTH = 10;
    private final int SEQUENCE_NUMBER_LENGTH = 20;

    private final int LOGIN_REQUEST_LENGTH = 49;
    private final int LOGIN_LENGTH = USERNAME_LENGTH + PASSWORD_LENGTH + SESSION_LENGTH + SEQUENCE_NUMBER_LENGTH;
    private final int LOGIN_ACCEPT_LENGTH = SESSION_LENGTH + SEQUENCE_NUMBER_LENGTH;
    private static final int LOGOUT_LENGTH = 3;
    private static final int CLIENT_HEART_LENGTH = 3;
    private final static int CLIENT_HEARTBEAT_INTERVAL = 1000;
    private final String soupUsername;
    private final String soupPassword;
    private final String soupSession;
    private BigInteger soupSequenceNumber;
    private static long nextExpectedSequenceNumber = 0;
    private static ByteBuffer buffer;
    private long lastHeartbeatMillis = 0;
    private static Socket clientSocket;
    private static volatile boolean connected = false;
    private static int heart = 0;
    final private Decoder.Potocol protocol;
    private static String session;
    private static String service;

    /**
     *
     * @author Juan Jesús Martínez Serrano / juanjmtzs@gmail.com
     *
     *
     */
    public ITCHSoupBinTCPNoBytes() {
        soupUsername = null;
        soupPassword = null;
        soupSession = null;
        soupSequenceNumber = null;
        protocol = null;
    }

    public ITCHSoupBinTCPNoBytes(String username, String password, String session, String host, int port, long sequenceNumber, Decoder.Potocol protocol) throws IOException {
        soupUsername = username;
        soupPassword = password;
        soupSession = session;
        clientSocket.connect(new InetSocketAddress(host, port), 25200000);
        connected = true;
        soupSequenceNumber = BigInteger.valueOf(sequenceNumber);
        buffer.order(ByteOrder.BIG_ENDIAN);
        this.protocol = protocol;
    }

    private static class PacketType {

        public static final byte login_accepted_type = 'A';
        public static final byte server_heartbeat_type = 'H';
        public static final byte login_rejected_type = 'J';
        public static final byte login_request_type = 'L';
        public static final byte logout_request_type = 'O';
        public static final byte client_heartbeat_type = 'R';
        public static final byte sequenced_data_type = 'S';
        public static final byte unsequenced_data_type = 'U';
        public static final byte end_of_session_type = 'Z';
    }

    private class LoginReject {

        public static final byte not_authorised = 'A';
        public static final byte session_unavailable = 'S';
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
            buffer.putShort((short) length);
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

                JFrame error = new JFrame();

                JOptionPane.showMessageDialog(error, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

    private static void sendBuffer() throws IOException {

        send(buffer);
    }

    private static void send(ByteBuffer b) throws IOException {
        clientSocket.getOutputStream().write(b.array(), 0, b.position());
        clientSocket.getOutputStream().flush();
        b.clear();
    }

    private short readBytes(DataInputStream in) throws IOException {
        for (;;) {
            try {
                if (System.currentTimeMillis() - lastHeartbeatMillis > CLIENT_HEARTBEAT_INTERVAL) {
                    final Packet clientHeartBeat = new Packet(PacketType.client_heartbeat_type);
                    final ByteBuffer ByteBufferAux;
                    ByteBufferAux = ByteBuffer.allocate(CLIENT_HEART_LENGTH);
                    clientHeartBeat.encode(ByteBufferAux);

                    send(ByteBufferAux);
                    lastHeartbeatMillis = System.currentTimeMillis();
                }

                return in.readShort();
            } catch (SocketTimeoutException e) {

            }
        }
    }

    private void loopFactory() throws IOException {
        final DataInputStream in = new DataInputStream(clientSocket.getInputStream());
        while (connected == true) {

            buffer.clear();
            clientSocket.setSoTimeout(1000);
            short len = readBytes(in);
            clientSocket.setSoTimeout(0);
            byte type = in.readByte();
            for (short numBytes = 0; numBytes < len - 1;) {
                numBytes += in.read(buffer.array(), numBytes, len - numBytes - 1);
            }
            buffer.position(0);
            buffer.limit(len - 1);
            switch (type) {
                case PacketType.sequenced_data_type:

                    String JSON = protocol.parse(buffer, nextExpectedSequenceNumber);
                    System.out.println("[ITCH-U]" + JSON);
                    if (ITCHMain.viewMessages.isSelected()) {
                        ITCHMain.logsArea.append("[ITCH-U]" + JSON + "\n");
                        ITCHMain.logsArea.setCaretPosition(ITCHMain.logsArea.getText().length());
                    }
                    heart = 0;

                    ITCHMain.Update.setText(""
                            + "Session:  " + session + "\n"
                            + "Last SeqNum:  " + nextExpectedSequenceNumber + "\n"
                            + "     Add Orders(A):  " + Decoder.ItchNoBytes.count_add_order + "\n"
                            + "     Busted Trades(B):  " + Decoder.ItchNoBytes.count_broken_trade + "\n"
                            + "     Orders Executed With Price (C):  " + Decoder.ItchNoBytes.count_order_executed_with_price + "\n"
                            + "     Orders Deleted (D):  " + Decoder.ItchNoBytes.count_order_delete + "\n"
                            + "     Orders Executed (E):  " + Decoder.ItchNoBytes.count_order_executed + "\n"
                            + "     Orders Updated (U):  " + Decoder.ItchNoBytes.count_order_replace + "\n"
                            + "     Trading Actions(H):  " + Decoder.ItchNoBytes.count_trading_action + "\n"
                            + "     Indicative Prices/Quiantities (I):  " + Decoder.ItchNoBytes.count_indicative_price_quantity + "\n"
                            + "     Price Ticks (L):  " + Decoder.ItchNoBytes.count_price_tick + "\n"
                            + "     Quantity Ticks (M):  " + Decoder.ItchNoBytes.count_quantity_tick + "\n"
                            + "     Orderbook Directories (R):  " + Decoder.ItchNoBytes.count_stock_directory + "\n"
                            + "     System Events (S):  " + Decoder.ItchNoBytes.count_system_event + "\n"
                            + "     Timestamps (T):  " + Decoder.ItchNoBytes.count_time_stamp + "\n"
                            + "     Participants Directories (F):  " + Decoder.ItchNoBytes.count_participant_directory + "\n"
                            + "     Trades (P):  " + Decoder.ItchNoBytes.count_trade_message + "\n"
                            + "     Glimpse Snapshot (G):  " + Decoder.ItchNoBytes.count_glimpse_snapshot + "\n"
                            + "     Reference Prices (X):  " + Decoder.ItchNoBytes.count_reference_price + "\n"
                            + "     Best Bid Offers (Q):  " + Decoder.ItchNoBytes.count_best_bid_offer + "\n"
                            + "     News (N):  " + Decoder.ItchNoBytes.count_news_message
                            + "\n");
                    ++nextExpectedSequenceNumber;

                    break;
                case PacketType.login_accepted_type:
                    loginAccepted();
                    break;
                case PacketType.login_rejected_type:
                    System.out.print("[ITCH-U]Login Rejected: ");
                    loginRejected();
                    return;
                case PacketType.server_heartbeat_type:
                    if (heart == 0) {

                    }
                    heart = 1;

                    break;
                case PacketType.end_of_session_type:
                    System.out.println("[SoupBinTCP-IN]End of Session");
                    logoff();
                    ITCHMain.progress.setIndeterminate(false);
                    ITCHMain.connect.setEnabled(true);
                    ITCHMain.serviceSelector.setEnabled(true);
                    ITCHMain.DissconnectDo.setEnabled(false);
                    ITCHMain.printBytes.setEnabled(true);
                    ITCHMain.toFile.setEnabled(true);
                    ITCHMain.viewMessages.setEnabled(true);

                    ITCHMain.clear.setEnabled(true);
                    ITCHMain.save.setEnabled(true);
                    Runtime garbage1 = Runtime.getRuntime();
                    garbage1.gc();
                    break;
                case PacketType.unsequenced_data_type:
                    System.out.println("[SoupBinTCP-IN]Unsequenced Packet");
                    heart = 0;
                    break;
                default:
                    break;
            }
        }
    }

    @SuppressWarnings("empty-statement")
    private void loginAccepted() {
        class LoginAccepted extends Packet {

            public LoginAccepted() {
                super(PacketType.login_accepted_type, LOGIN_ACCEPT_LENGTH);
            }

            @Override
            void decode(byte[] buffer) {
                session = stripRight(buffer, 0, SESSION_LENGTH);
                String sequenceNumber = stripLeft(buffer, SESSION_LENGTH, SEQUENCE_NUMBER_LENGTH);
                System.out.print("[ITCH-U] {\"Service Name\":\"" + service + "\",");
                System.out.print("\"Session\":" + session + ",");
                System.out.println("\"Next SeqNum\":" + sequenceNumber + "}");
                ITCHMain.Update.setText(""
                        + "Session:  " + session + "\n"
                        + "Next SeqNum:  " + sequenceNumber + "\n"
                        + "     Add Orders(A):  " + Decoder.ItchNoBytes.count_add_order + "\n"
                        + "     Busted Trades(B):  " + Decoder.ItchNoBytes.count_broken_trade + "\n"
                        + "     Orders Executed With Price (C):  " + Decoder.ItchNoBytes.count_order_executed_with_price + "\n"
                        + "     Orders Deleted (D):  " + Decoder.ItchNoBytes.count_order_delete + "\n"
                        + "     Orders Executed (E):  " + Decoder.ItchNoBytes.count_order_executed + "\n"
                        + "     Orders Updated (U):  " + Decoder.ItchNoBytes.count_order_replace + "\n"
                        + "     Trading Actions(H):  " + Decoder.ItchNoBytes.count_trading_action + "\n"
                        + "     Indicative Prices/Quiantities (I):  " + Decoder.ItchNoBytes.count_indicative_price_quantity + "\n"
                        + "     Price Ticks (L):  " + Decoder.ItchNoBytes.count_price_tick + "\n"
                        + "     Quantity Ticks (M):  " + Decoder.ItchNoBytes.count_quantity_tick + "\n"
                        + "     Orderbook Directories (R):  " + Decoder.ItchNoBytes.count_stock_directory + "\n"
                        + "     System Events (S):  " + Decoder.ItchNoBytes.count_system_event + "\n"
                        + "     Timestamps (T):  " + Decoder.ItchNoBytes.count_time_stamp + "\n"
                        + "     Participants Directories (F):  " + Decoder.ItchNoBytes.count_participant_directory + "\n"
                        + "     Trades (P):  " + Decoder.ItchNoBytes.count_trade_message + "\n"
                        + "     Glimpse Snapshot (G):  " + Decoder.ItchNoBytes.count_glimpse_snapshot + "\n"
                        + "     Reference Prices (X):  " + Decoder.ItchNoBytes.count_reference_price + "\n"
                        + "     Best Bid Offers (Q):  " + Decoder.ItchNoBytes.count_best_bid_offer + "\n"
                        + "     News (N):  " + Decoder.ItchNoBytes.count_news_message
                        + "\n");
                nextExpectedSequenceNumber = Long.parseLong(sequenceNumber);
            }
        };
        new LoginAccepted().decode(buffer.array());

    }

    private void loginRejected() {
        byte reason = buffer.get();
        String rejection = "";
        switch (reason) {
            case LoginReject.not_authorised:
                rejection = "Login Information is not recognized: Please verify User, Password, IP and Port values";
                break;
            case LoginReject.session_unavailable:
                rejection = "Session " + soupSession + " is not available";
                break;
            default:
                rejection = "Unsupported Rejection";
                break;
        }
        System.out.println(rejection);
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

        JOptionPane.showMessageDialog(error, rejection, "Login Rejected!", JOptionPane.ERROR_MESSAGE);
    }

    private void login() throws IOException {
        final Packet login = new Packet(PacketType.login_request_type, LOGIN_LENGTH);

        final ByteBuffer ByteBufferAux;
        ByteBufferAux = ByteBuffer.allocate(LOGIN_REQUEST_LENGTH);

        login.encode(ByteBufferAux);
        login.padRight(ByteBufferAux, soupUsername, USERNAME_LENGTH);
        login.padRight(ByteBufferAux, soupPassword, PASSWORD_LENGTH);
        if (soupSession != null) {
            login.padRight(ByteBufferAux, soupSession, SESSION_LENGTH);
        } else {
            login.padRight(ByteBufferAux, "", SESSION_LENGTH);
        }
        login.padLeft(ByteBufferAux, soupSequenceNumber.toString(), SEQUENCE_NUMBER_LENGTH);

        System.out.println("[SoupBinTCP-OUT] Logon");
        send(ByteBufferAux);
        lastHeartbeatMillis = System.currentTimeMillis();
    }

    private static void logoff() {
        try {
            final Packet logoff = new Packet(PacketType.logout_request_type);
            final ByteBuffer ByteBufferAux;
            ByteBufferAux = ByteBuffer.allocate(LOGOUT_LENGTH);
            logoff.encode(ByteBufferAux);
            System.out.println("[SoupBinTCP-OUT] Logoff");
            send(ByteBufferAux);

        } catch (IOException e) {

            String messageError = "Socket closed" + "\n" + e;

            JFrame error = new JFrame();

            JOptionPane.showMessageDialog(error, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void start() throws IOException {
        login();
        loopFactory();
    }

    public static void stop() {
        connected = false;
        logoff();

        if (nextExpectedSequenceNumber > 0) {

            JFrame info = new JFrame();
            JOptionPane.showMessageDialog(info, "Disconnected session with last sequence number: " + nextExpectedSequenceNumber, "Disconnected!", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    public static void kill() {

        stop();
        close();

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

        Decoder.ItchNoBytes.count_add_order = 0;
        Decoder.ItchNoBytes.count_broken_trade = 0;
        Decoder.ItchNoBytes.count_order_executed_with_price = 0;
        Decoder.ItchNoBytes.count_order_delete = 0;
        Decoder.ItchNoBytes.count_order_executed = 0;
        Decoder.ItchNoBytes.count_order_replace = 0;
        Decoder.ItchNoBytes.count_trading_action = 0;
        Decoder.ItchNoBytes.count_indicative_price_quantity = 0;
        Decoder.ItchNoBytes.count_price_tick = 0;
        Decoder.ItchNoBytes.count_quantity_tick = 0;
        Decoder.ItchNoBytes.count_stock_directory = 0;
        Decoder.ItchNoBytes.count_warrants_directory = 0;
        Decoder.ItchNoBytes.count_system_event = 0;
        Decoder.ItchNoBytes.count_time_stamp = 0;
        Decoder.ItchNoBytes.count_participant_directory = 0;
        Decoder.ItchNoBytes.count_trade_message = 0;
        Decoder.ItchNoBytes.count_glimpse_snapshot = 0;
        Decoder.ItchNoBytes.count_reference_price = 0;
        Decoder.ItchNoBytes.count_best_bid_offer = 0;
        Decoder.ItchNoBytes.count_news_message = 0;
        if (args.length != 7) {
            args = new String[6];
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("   Enter username:");
            args[0] = br.readLine();
            System.out.println("   Enter password:");
            args[1] = br.readLine();
            System.out.println("   Enter session:");
            args[2] = br.readLine();
            System.out.println("   Enter hostname:");
            args[3] = br.readLine();
            System.out.println("   Enter port:");
            args[4] = br.readLine();
            System.out.println("   Enter seqnum:");
            args[5] = br.readLine();
            System.out.println("   Enter service name:");
            args[6] = br.readLine();

        }
        try {

            service = args[6];
            if (ITCHMain.toFile.isSelected() == true) {
                String userHomeFolder = ITCHMain.userHomeFolder;
                Output output = new Output();
                output.changeOutput(service, userHomeFolder);
            } else {

                System.setOut(System.out);
            }

            final ITCHSoupBinTCPNoBytes s = new ITCHSoupBinTCPNoBytes(args[0], args[1], args[2], args[3], Integer.parseInt(args[4]), Long.parseLong(args[5]), new Decoder.ItchNoBytes());

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    s.stop();
                }
            });
            s.start();
        } catch (IOException | NumberFormatException e) {

            ITCHMain.progress.setIndeterminate(false);
            ITCHMain.connect.setEnabled(true);
            ITCHMain.serviceSelector.setEnabled(true);
            ITCHMain.DissconnectDo.setEnabled(false);
            ITCHMain.printBytes.setEnabled(true);
            ITCHMain.toFile.setEnabled(true);
            ITCHMain.viewMessages.setEnabled(true);

            ITCHMain.clear.setEnabled(true);
            ITCHMain.save.setEnabled(true);

            String messageError = "While Running: " + "\n\n" + e.getMessage();

            if (e.getMessage() == "null") {

                JFrame error = new JFrame();

                JOptionPane.showMessageDialog(error, messageError, "Warning!", JOptionPane.WARNING_MESSAGE);
            } else if ((e + "").equals("java.net.ConnectException: Connection refused: connect")) {
                messageError = "Something Went Wrong: " + "\n\n" + "Connection Refused, Please Verify.";
                JFrame error = new JFrame();

                JOptionPane.showMessageDialog(error, messageError, "Error!", JOptionPane.ERROR_MESSAGE);
            }
            close();
        }
    }

    public static void gapFilled() {
        ITCHMain.progress.setIndeterminate(false);
        ITCHMain.connect.setEnabled(true);
        ITCHMain.serviceSelector.setEnabled(true);
        ITCHMain.DissconnectDo.setEnabled(false);
        ITCHMain.printBytes.setEnabled(true);
        ITCHMain.toFile.setEnabled(true);
        ITCHMain.viewMessages.setEnabled(true);

        ITCHMain.clear.setEnabled(true);
        ITCHMain.save.setEnabled(true);

        stopGapFilled();
        close();

    }

    public static void stopGapFilled() {
        connected = false;
        logoff();
        if (nextExpectedSequenceNumber > 0) {

            JFrame error = new JFrame();
            JOptionPane.showMessageDialog(error, "Snapshot Completed.", "Done!", JOptionPane.INFORMATION_MESSAGE);

        }

    }

}
