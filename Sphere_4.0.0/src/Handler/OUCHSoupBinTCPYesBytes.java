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

import OUCH.OUCHMain;
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
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

public class OUCHSoupBinTCPYesBytes {

    public static JFrame frame = new JFrame();
    private final int USERNAME_LENGTH = 6;
    private final int PASSWORD_LENGTH = 10;
    private final int SESSION_LENGTH = 10;

    private final int LOGIN_REQUEST_LENGTH = 49;
    private final int SEQUENCE_NUMBER_LENGTH = 20;
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

    static final String enter_order = "O";
    static final String replace_order = "U";
    static final String cancel_order = "X";

    public static int count_enter_order = 0;
    public static int count_replace_order = 0;
    public static int count_cancel_order = 0;

    static final int ORDERTOKEN_LENGTH = 4;
    static final int ACCOUNTTYPE_LENGTH = 1;
    static final int ACCOUNTID_LENGTH = 4;
    static final int ORDERVERB_LENGTH = 1;
    static final int QUANTITY_LENGTH = 8;
    static final int ORDERBOOK_LENGTH = 4;
    static final int PRICE_LENGTH = 4;
    static final int TIMEINFORCE_LENGTH = 4;
    static final int CLIENTID_LENGTH = 4;

    static final int MESSAGETYPE_LENGTH = 1;
    static final int MINQTY_LENGTH = 8;
    static final int ENTERORDER_LENGTH = MESSAGETYPE_LENGTH + ORDERTOKEN_LENGTH + ACCOUNTTYPE_LENGTH + ACCOUNTID_LENGTH + ORDERVERB_LENGTH + QUANTITY_LENGTH + ORDERBOOK_LENGTH + PRICE_LENGTH + TIMEINFORCE_LENGTH + CLIENTID_LENGTH + MINQTY_LENGTH;
    static final int REPLACEORDER_LENGTH = MESSAGETYPE_LENGTH + ORDERTOKEN_LENGTH + ORDERTOKEN_LENGTH + QUANTITY_LENGTH + PRICE_LENGTH;
    static final int CANCELORDER_LENGTH = MESSAGETYPE_LENGTH + ORDERTOKEN_LENGTH;

    /**
     *
     * @author Juan Jesús Martínez Serrano / juanjmtzs@gmail.com
     *
     *
     */
    public OUCHSoupBinTCPYesBytes() {
        soupUsername = null;
        soupPassword = null;
        soupSession = null;
        soupSequenceNumber = null;
        protocol = null;
    }

    public OUCHSoupBinTCPYesBytes(String username, String password, String session, String host, int port, long sequenceNumber, Decoder.Potocol protocol) throws IOException {
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

        void encode(ByteBuffer buffer, int length) {
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

                JOptionPane.showMessageDialog(frame, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);
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

    private short readData(DataInputStream in) throws IOException {
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
        while (connected == true && clientSocket.isConnected() == true) {
            buffer.clear();
            clientSocket.setSoTimeout(1000);
            short len = readData(in);
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
                    System.out.println("[OUCH-U]" + JSON);
                    if (OUCHMain.viewMessages.isSelected()) {
                        OUCHMain.logsArea.append("[OUCH-U]" + JSON + "\n");
                        OUCHMain.logsArea.setCaretPosition(OUCHMain.logsArea.getText().length());
                    }
                    heart = 0;

                    OUCHMain.Update.setText(""
                            + "Session:  " + session + "\n"
                            + "Last SeqNum:  " + nextExpectedSequenceNumber + "\n"
                            + ".::SENT MESSAGES::." + "\n"
                            + "     Enter Orders(O):  " + count_enter_order + "\n"
                            + "     Replace Orders(U):  " + count_replace_order + "\n"
                            + "     Cancel Orders (X):  " + count_cancel_order + "\n"
                            + ".::RECEIVED MESSAGES::." + "\n"
                            + "     System Events(S):  " + Decoder.OuchYesBytes.count_system_event + "\n"
                            + "     Accepted Message (A):  " + Decoder.OuchYesBytes.count_accepted_message + "\n"
                            + "     Replaced Orders (U):  " + Decoder.OuchYesBytes.count_replaced_order + "\n"
                            + "     Cancelled Orders(C):  " + Decoder.OuchYesBytes.count_cancelled_order + "\n"
                            + "     Executed Order (E):  " + Decoder.OuchYesBytes.count_executed_order + "\n"
                            + "     Broken Trades (B):  " + Decoder.OuchYesBytes.count_broken_trade + "\n"
                            + "     Rejected Messages (J):  " + Decoder.OuchYesBytes.count_rejected_message
                            + "\n");
                    ++nextExpectedSequenceNumber;

                    break;
                case PacketType.login_accepted_type:

                    loginAccepted();
                    break;
                case PacketType.login_rejected_type:
                    System.out.print("[OUCH-U]Login Rejected: ");
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
                    OUCHMain.progress.setIndeterminate(false);
                    OUCHMain.connect.setEnabled(true);
                    OUCHMain.serviceSelector.setEnabled(true);
                    OUCHMain.DissconnectDo.setEnabled(false);
                    OUCHMain.printBytesOuch.setEnabled(true);
                    OUCHMain.toFile.setEnabled(true);
                    OUCHMain.viewMessages.setEnabled(true);

                    OUCHMain.clear.setEnabled(true);
                    OUCHMain.save.setEnabled(true);
                    Runtime garbage1 = Runtime.getRuntime();
                    garbage1.gc();
                    return;
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
                System.out.print("[OUCH-U] {\"Service Name\":\"" + service + "\",");
                System.out.print("\"Session\":" + session + ",");
                System.out.println("\"Next SeqNum\":" + sequenceNumber + "}");
                OUCHMain.Update.setText(""
                        + "Session:  " + session + "\n"
                        + "Last SeqNum:  " + nextExpectedSequenceNumber + "\n"
                        + ".::SENT MESSAGES::." + "\n"
                        + "     Enter Orders(O):  " + count_enter_order + "\n"
                        + "     Replace Orders(U):  " + count_replace_order + "\n"
                        + "     Cancel Orders (X):  " + count_cancel_order + "\n"
                        + ".::RECEIVED MESSAGES::." + "\n"
                        + "     System Events(S):  " + Decoder.OuchYesBytes.count_system_event + "\n"
                        + "     Accepted Message (A):  " + Decoder.OuchYesBytes.count_accepted_message + "\n"
                        + "     Replaced Orders (U):  " + Decoder.OuchYesBytes.count_replaced_order + "\n"
                        + "     Cancelled Orders(C):  " + Decoder.OuchYesBytes.count_cancelled_order + "\n"
                        + "     Executed Order (E):  " + Decoder.OuchYesBytes.count_executed_order + "\n"
                        + "     Broken Trades (B):  " + Decoder.OuchYesBytes.count_broken_trade + "\n"
                        + "     Rejected Messages (J):  " + Decoder.OuchYesBytes.count_rejected_message
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
        OUCHMain.progress.setIndeterminate(false);
        OUCHMain.connect.setEnabled(true);
        OUCHMain.serviceSelector.setEnabled(true);
        OUCHMain.DissconnectDo.setEnabled(false);
        OUCHMain.printBytesOuch.setEnabled(true);
        OUCHMain.toFile.setEnabled(true);
        OUCHMain.viewMessages.setEnabled(true);

        OUCHMain.clear.setEnabled(true);
        OUCHMain.save.setEnabled(true);

        JOptionPane.showMessageDialog(frame, rejection, "Login Rejected!", JOptionPane.ERROR_MESSAGE);
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

        System.out.println("[SoupBinTCP-OUT] Logon: " + Arrays.toString(ByteBufferAux.array()));
        send(ByteBufferAux);
        lastHeartbeatMillis = System.currentTimeMillis();
    }

    public static void EnterOrder(String token, String accountType, String accountId, String orderverb, String quantity, String orderBook, String price, String timeInForce, String clientId, String minQty) throws IOException {
        if (clientSocket != null) {

            if (clientSocket.isConnected() == true) {

                try {
                    final Packet order = new Packet(PacketType.unsequenced_data_type);
                    final ByteBuffer ByteBufferAux;
                    ByteBufferAux = ByteBuffer.allocate(3 + ENTERORDER_LENGTH);
                    order.encode(ByteBufferAux, 1 + ENTERORDER_LENGTH);

                    order.padRight(ByteBufferAux, enter_order, MESSAGETYPE_LENGTH);
                    ByteBufferAux.putInt(Integer.parseInt(token));
                    order.padRight(ByteBufferAux, accountType, ACCOUNTTYPE_LENGTH);
                    ByteBufferAux.putInt(Integer.parseInt(accountId));
                    order.padRight(ByteBufferAux, orderverb, ORDERVERB_LENGTH);
                    ByteBufferAux.putLong(Long.parseLong(quantity));
                    ByteBufferAux.putInt(Integer.parseInt(orderBook));
                    ByteBufferAux.putInt(Integer.parseInt(price));
                    ByteBufferAux.putInt(Integer.parseInt(timeInForce));
                    ByteBufferAux.putInt(Integer.parseInt(clientId));
                    ByteBufferAux.putLong(Long.parseLong(minQty));

                    System.out.println("[SoupBinTCP-OUT] Unsequenced Packet: " + Arrays.toString(ByteBufferAux.array()));

                    System.out.print("[OUCH-OUT]{\"Token\":" + token + ",");
                    System.out.print("\"Message\":{");
                    System.out.print("\"Bytes\":" + "\"" + Arrays.toString(Arrays.copyOfRange(ByteBufferAux.array(), 3, 46)).replace("[", "").replace(",", "").replace("]", "") + "\"" + ",");
                    System.out.print("\"Name\":" + "\"" + "Enter Order" + "\"" + ",");
                    System.out.print("\"Type\":" + "\"" + "O" + "\"" + ",");
                    System.out.print("\"AcType\":" + "\"" + accountType + "\"" + ",");
                    System.out.print("\"AcId\":" + accountId + ",");
                    System.out.print("\"OrdVerb\":" + "\"" + orderverb + "\"" + ",");
                    System.out.print("\"Quanty\":" + quantity + ",");
                    System.out.print("\"OrdBook\":" + orderBook + ",");
                    System.out.print("\"Price\":" + price + ",");
                    System.out.print("\"TimeInForce\":" + timeInForce + ",");
                    System.out.print("\"ClientId\":" + clientId + ",");
                    System.out.print("\"MinQty\":" + minQty);
                    System.out.println("}}");

                    if (OUCHMain.viewMessages.isSelected()) {
                        OUCHMain.logsArea.append(""
                                + "[OUCH-OUT]{\"Token\":" + token + ","
                                + "\"Message\":{"
                                + "\"Bytes\":" + "\"" + Arrays.toString(Arrays.copyOfRange(ByteBufferAux.array(), 3, 46)).replace("[", "").replace(",", "").replace("]", "") + "\"" + ","
                                + "\"Name\":" + "\"" + "Enter Order" + "\"" + ","
                                + "\"Type\":" + "\"" + "O" + "\"" + ","
                                + "\"AcType\":" + "\"" + accountType + "\"" + ","
                                + "\"AcId\":" + accountId + ","
                                + "\"OrdVerb\":" + "\"" + orderverb + "\"" + ","
                                + "\"Quanty\":" + quantity + ","
                                + "\"OrdBook\":" + orderBook + ","
                                + "\"Price\":" + price + ","
                                + "\"TimeInForce\":" + timeInForce + ","
                                + "\"ClientId\":" + clientId + ","
                                + "\"MinQty\":" + minQty
                                + "}}"
                                + "\n");
                        OUCHMain.logsArea.setCaretPosition(OUCHMain.logsArea.getText().length());
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

    public static void ModifyOrder(String token, String tokenReplace, String quantity, String price) throws IOException {

        if (clientSocket != null) {
            if (clientSocket.isConnected() == true) {
                try {
                    final Packet order = new Packet(PacketType.unsequenced_data_type);
                    final ByteBuffer ByteBufferAux;
                    ByteBufferAux = ByteBuffer.allocate(3 + REPLACEORDER_LENGTH);
                    order.encode(ByteBufferAux, 1 + REPLACEORDER_LENGTH);

                    order.padRight(ByteBufferAux, replace_order, MESSAGETYPE_LENGTH);
                    ByteBufferAux.putInt(Integer.parseInt(token));
                    ByteBufferAux.putInt(Integer.parseInt(tokenReplace));
                    ByteBufferAux.putLong(Long.parseLong(quantity));
                    ByteBufferAux.putInt(Integer.parseInt(price));

                    System.out.println("[SoupBinTCP-OUT] Unsequenced Packet: " + Arrays.toString(ByteBufferAux.array()));

                    System.out.print("[OUCH-OUT]{\"New Token\":" + tokenReplace + ",");
                    System.out.print("\"Message\":{");
                    System.out.print("\"Bytes\":" + "\"" + Arrays.toString(Arrays.copyOfRange(ByteBufferAux.array(), 3, 24)).replace("[", "").replace(",", "").replace("]", "") + "\"" + ",");
                    System.out.print("\"Name\":" + "\"" + "Modify Order" + "\"" + ",");
                    System.out.print("\"Type\":" + "\"" + "U" + "\"" + ",");
                    System.out.print("\"Old Token\":" + "\"" + token + "\"" + ",");
                    System.out.print("\"Quanty\":" + quantity + ",");
                    System.out.print("\"Price\":" + price);
                    System.out.println("}}");

                    if (OUCHMain.viewMessages.isSelected()) {
                        OUCHMain.logsArea.append(""
                                + "[OUCH-OUT]{\"New Token\":" + tokenReplace + ","
                                + "\"Message\":{"
                                + "\"Bytes\":" + "\"" + Arrays.toString(Arrays.copyOfRange(ByteBufferAux.array(), 3, 24)).replace("[", "").replace(",", "").replace("]", "") + "\"" + ","
                                + "\"Name\":" + "\"" + "Modify Order" + "\"" + ","
                                + "\"Type\":" + "\"" + "U" + "\"" + ","
                                + "\"Old Token\":" + "\"" + token + "\"" + ","
                                + "\"Quanty\":" + quantity + ","
                                + "\"Price\":" + price
                                + "}}"
                                + "\n");
                        OUCHMain.logsArea.setCaretPosition(OUCHMain.logsArea.getText().length());
                    }
                    send(ByteBufferAux);

                } catch (IOException e) {

                    String messageError = "Socket closed" + "\n" + e;

                    JOptionPane.showMessageDialog(frame, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);

                }
            } else {
                String messageError = "Socket closed" + "\n";

                JOptionPane.showMessageDialog(frame, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            String messageError = "Socket closed" + "\n";

            JOptionPane.showMessageDialog(frame, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public static void CancelOrder(String token) throws IOException {

        if (clientSocket != null) {
            if (clientSocket.isConnected() == true) {
                try {
                    final Packet order = new Packet(PacketType.unsequenced_data_type);
                    final ByteBuffer ByteBufferAux;
                    ByteBufferAux = ByteBuffer.allocate(3 + CANCELORDER_LENGTH);
                    order.encode(ByteBufferAux, 1 + CANCELORDER_LENGTH);

                    order.padRight(ByteBufferAux, cancel_order, MESSAGETYPE_LENGTH);
                    ByteBufferAux.putInt(Integer.parseInt(token));

                    System.out.println("[SoupBinTCP-OUT] Uncequenced Packet: " + Arrays.toString(ByteBufferAux.array()));

                    System.out.print("[OUCH-OUT]{\"Token\":" + token + ",");
                    System.out.print("\"Message\":{");
                    System.out.print("\"Bytes\":" + "\"" + Arrays.toString(Arrays.copyOfRange(ByteBufferAux.array(), 3, 8)).replace("[", "").replace(",", "").replace("]", "") + "\"" + ",");
                    System.out.print("\"Name\":" + "\"" + "Cancel Order" + "\"" + ",");
                    System.out.print("\"Type\":" + "\"" + "X" + "\"" + ",");
                    System.out.print("\"Token\":" + "\"" + token);
                    System.out.println("}}");
                    if (OUCHMain.viewMessages.isSelected()) {

                        OUCHMain.logsArea.append(""
                                + "[OUCH-OUT]{\"Token\":" + token + ","
                                + "\"Message\":{"
                                + "\"Bytes\":" + "\"" + Arrays.toString(Arrays.copyOfRange(ByteBufferAux.array(), 3, 8)).replace("[", "").replace(",", "").replace("]", "") + "\"" + ","
                                + "\"Name\":" + "\"" + "Cancel Order" + "\"" + ","
                                + "\"Type\":" + "\"" + "X" + "\"" + ","
                                + "\"Token\":" + "\"" + token
                                + "}}"
                                + "\n");
                        OUCHMain.logsArea.setCaretPosition(OUCHMain.logsArea.getText().length());
                    }

                    send(ByteBufferAux);

                } catch (IOException e) {

                    String messageError = "Socket closed" + "\n" + e;

                    JOptionPane.showMessageDialog(frame, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);

                }
            } else {
                String messageError = "Socket closed" + "\n";

                JOptionPane.showMessageDialog(frame, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            String messageError = "Socket closed" + "\n";

            JOptionPane.showMessageDialog(frame, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private static void logoff() {
        try {
            final Packet logoff = new Packet(PacketType.logout_request_type);
            final ByteBuffer ByteBufferAux;
            ByteBufferAux = ByteBuffer.allocate(LOGOUT_LENGTH);
            logoff.encode(ByteBufferAux);
            System.out.println("[SoupBinTCP-OUT] Logoff: " + Arrays.toString(ByteBufferAux.array()));
            send(ByteBufferAux);

        } catch (IOException e) {

            String messageError = "Socket closed" + "\n" + e;

            JOptionPane.showMessageDialog(frame, messageError, "Information", JOptionPane.INFORMATION_MESSAGE);

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

            JOptionPane.showMessageDialog(frame, "Disconnected session with last sequence number: " + nextExpectedSequenceNumber, "Disconnected!", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    public static void kill() {
        stop();
        close();

    }

    public static void StartHandling(String[] args) throws IOException, UnsupportedLookAndFeelException {
        clientSocket = new Socket();
        buffer = ByteBuffer.allocate(4 * 1024);
        Decoder.OuchYesBytes.count_accepted_message = 0;
        Decoder.OuchYesBytes.count_broken_trade = 0;
        count_cancel_order = 0;
        Decoder.OuchYesBytes.count_cancelled_order = 0;
        count_enter_order = 0;
        Decoder.OuchYesBytes.count_executed_order = 0;
        Decoder.OuchYesBytes.count_rejected_message = 0;
        count_replace_order = 0;
        Decoder.OuchYesBytes.count_replaced_order = 0;
        Decoder.OuchYesBytes.count_system_event = 0;
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
            if (OUCHMain.toFile.isSelected() == true) {

                String userHomeFolder = OUCHMain.userHomeFolder;
                Output output = new Output();
                output.changeOutput(service, userHomeFolder);
            } else {

                System.setOut(System.out);
            }

            final OUCHSoupBinTCPYesBytes s = new OUCHSoupBinTCPYesBytes(args[0], args[1], args[2], args[3], Integer.parseInt(args[4]), Long.parseLong(args[5]), new Decoder.OuchYesBytes());
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    s.stop();
                }
            });
            s.start();
        } catch (Exception e) {

            OUCHMain.progress.setIndeterminate(false);
            OUCHMain.connect.setEnabled(true);
            OUCHMain.serviceSelector.setEnabled(true);
            OUCHMain.DissconnectDo.setEnabled(false);
            OUCHMain.printBytesOuch.setEnabled(true);
            OUCHMain.toFile.setEnabled(true);
            OUCHMain.viewMessages.setEnabled(true);

            OUCHMain.clear.setEnabled(true);
            OUCHMain.save.setEnabled(true);

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
        OUCHMain.progress.setIndeterminate(false);
        OUCHMain.connect.setEnabled(true);
        OUCHMain.serviceSelector.setEnabled(true);
        OUCHMain.DissconnectDo.setEnabled(false);
        OUCHMain.printBytesOuch.setEnabled(true);
        OUCHMain.toFile.setEnabled(true);
        OUCHMain.viewMessages.setEnabled(true);

        OUCHMain.clear.setEnabled(true);
        OUCHMain.save.setEnabled(true);

        stopGapFilled();
        close();

    }

    public static void stopGapFilled() {
        connected = false;
        logoff();
        if (nextExpectedSequenceNumber > 0) {

            JOptionPane.showMessageDialog(frame, "Snapshot Completed.", "Done!", JOptionPane.INFORMATION_MESSAGE);

        }

    }

}
