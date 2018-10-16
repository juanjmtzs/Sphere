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

package Decoder;

import OUCH.OUCHMain;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;


public class OuchYesBytes implements Potocol {

    //OUCH Outbound
    static final byte system_event = 'S';
    static final byte accepted_message = 'A';
    static final byte replaced_order = 'U';
    static final byte canceled_order = 'C';
    static final byte executed_order = 'E';
    static final byte broken_trade = 'B';
    static final byte rejected_message = 'J';

    public static int count_system_event = 0;
    public static int count_accepted_message = 0;
    public static int count_replaced_order = 0;
    public static int count_cancelled_order = 0;
    public static int count_executed_order = 0;
    public static int count_broken_trade = 0;
    public static int count_rejected_message = 0;

    public static int token = 0;

    private Vector<Object> data;
    private final DefaultTableModel model = (DefaultTableModel) OUCHMain.ordersTable.getModel();
    private final DefaultTableModel modelTrades = (DefaultTableModel) OUCHMain.tradesTable.getModel();

    @Override
    public String parse(ByteBuffer message, long seqNum) {
        byte type = message.get(0);
        byte[] bytes;
        BigInteger bigInt;
        String jsonSonstructor = "";
        jsonSonstructor = jsonSonstructor + "{";
        jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
        jsonSonstructor = jsonSonstructor + "\"Message\":{";

        switch (type) {
            case system_event:
                count_system_event++;
                try {
                    ///////////////////////////////////////////////////////////////////Bytes
                    jsonSonstructor = jsonSonstructor + "\"Bytes\":\"";
                    for (int i = 0; i < 10; i++) {
                        jsonSonstructor = jsonSonstructor + message.get(i);
                        jsonSonstructor = jsonSonstructor + " ";
                    }
                    jsonSonstructor = jsonSonstructor + "\",";
                    ////////////////////////////////////////////////////////////////////////
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"System Event\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 9);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Timestamp\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 9, 10);
                    jsonSonstructor = jsonSonstructor + "\"Evebt Code:\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                } catch (UnsupportedEncodingException ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }
                break;
            case accepted_message:
                count_accepted_message++;

                data = new Vector<Object>();

                try {
                    ///////////////////////////////////////////////////////////////////Bytes
                    jsonSonstructor = jsonSonstructor + "\"Bytes\":\"";
                    for (int i = 0; i < 60; i++) {
                        jsonSonstructor = jsonSonstructor + message.get(i);
                        jsonSonstructor = jsonSonstructor + " ";
                    }
                    jsonSonstructor = jsonSonstructor + "\",";
                    ////////////////////////////////////////////////////////////////////////
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Accepted Message\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 9);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Timestamp\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 9, 13);
                    bigInt = new BigInteger(bytes);
                    token = bigInt.intValue();
                    jsonSonstructor = jsonSonstructor + "\"Order Token\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";
                    data.add(bigInt);

                    bytes = Arrays.copyOfRange(message.array(), 13, 14);
                    jsonSonstructor = jsonSonstructor + "\"Account Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";
                    data.add(new String(bytes, "UTF-8"));

                    bytes = Arrays.copyOfRange(message.array(), 14, 18);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Account Id\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 18, 19);
                    jsonSonstructor = jsonSonstructor + "\"Order Verb\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";
                    data.add(new String(bytes, "UTF-8"));

                    bytes = Arrays.copyOfRange(message.array(), 19, 27);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Quantity\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";
                    data.add(bigInt);

                    bytes = Arrays.copyOfRange(message.array(), 27, 31);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Orderbook\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";
                    data.add(bigInt);

                    bytes = Arrays.copyOfRange(message.array(), 31, 35);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Price\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";
                    data.add(bigInt);

                    bytes = Arrays.copyOfRange(message.array(), 35, 39);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Time in Force\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";
                    data.add(bigInt);

                    bytes = Arrays.copyOfRange(message.array(), 39, 43);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"ClientId\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 43, 51);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Order Reference Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";
                    data.add(bigInt);

                    bytes = Arrays.copyOfRange(message.array(), 51, 59);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Minimum Quantity\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";
                    data.add(bigInt);

                    bytes = Arrays.copyOfRange(message.array(), 59, 60);
                    jsonSonstructor = jsonSonstructor + "\"Order State\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                    data.add(new String(bytes, "UTF-8"));

                    model.addRow(data);

                } catch (UnsupportedEncodingException ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }
                break;

            case replaced_order:
                count_replaced_order++;

                try {
                    ///////////////////////////////////////////////////////////////////Bytes
                    jsonSonstructor = jsonSonstructor + "\"Bytes\":\"";
                    for (int i = 0; i < 43; i++) {
                        jsonSonstructor = jsonSonstructor + message.get(i);
                        jsonSonstructor = jsonSonstructor + " ";
                    }
                    jsonSonstructor = jsonSonstructor + "\",";
                    ////////////////////////////////////////////////////////////////////////
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Replaced Order\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 9);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Timestamp\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 9, 13);
                    bigInt = new BigInteger(bytes);
                    token = bigInt.intValue();
                    jsonSonstructor = jsonSonstructor + "\"Replacement Order Token\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";
                    Object newToken = bigInt;

                    bytes = Arrays.copyOfRange(message.array(), 13, 14);
                    jsonSonstructor = jsonSonstructor + "\"Order Verb\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 14, 22);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Quantity\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";
                    Object newQuantity = bigInt;

                    bytes = Arrays.copyOfRange(message.array(), 22, 26);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Orderbook\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 26, 30);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Price\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";
                    Object newPrice = bigInt;

                    bytes = Arrays.copyOfRange(message.array(), 30, 38);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Order Reference Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 38, 39);
                    jsonSonstructor = jsonSonstructor + "\"Order State\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 39, 43);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Previous Order Token\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + "}}";
                    Object id = bigInt;

                    for (int x = 0; x < OUCHMain.ordersTable.getRowCount(); x++) {

                        if (OUCHMain.ordersTable.getValueAt(x, 0).equals(id) == true) {
                            OUCHMain.ordersTable.setValueAt(newToken, x, 0);
                            OUCHMain.ordersTable.setValueAt(newQuantity, x, 3);
                            OUCHMain.ordersTable.setValueAt(newPrice, x, 5);
                        }

                    }

                } catch (UnsupportedEncodingException ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }
                break;
            case canceled_order:
                count_cancelled_order++;

                try {
                    ///////////////////////////////////////////////////////////////////Bytes
                    jsonSonstructor = jsonSonstructor + "\"Bytes\":\"";
                    for (int i = 0; i < 22; i++) {
                        jsonSonstructor = jsonSonstructor + message.get(i);
                        jsonSonstructor = jsonSonstructor + " ";
                    }
                    jsonSonstructor = jsonSonstructor + "\",";
                    ////////////////////////////////////////////////////////////////////////
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Canceled Order\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 9);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Timestamp\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 9, 13);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Order Token\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";
                    Object id = bigInt;

                    for (int x = 0; x < OUCHMain.ordersTable.getRowCount(); x++) {

                        if (OUCHMain.ordersTable.getValueAt(x, 0).equals(id) == true) {
                            OUCHMain.ordersTable.setValueAt("Cancelled", x, 9);
                        }

                    }

                    bytes = Arrays.copyOfRange(message.array(), 13, 21);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Quantity\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 21, 22);
                    jsonSonstructor = jsonSonstructor + "\"Reason\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (UnsupportedEncodingException ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }
                break;

            case executed_order:
                count_executed_order++;
                data = new Vector<Object>();

                try {
                    ///////////////////////////////////////////////////////////////////Bytes
                    jsonSonstructor = jsonSonstructor + "\"Bytes\":\"";
                    for (int i = 0; i < 38; i++) {
                        jsonSonstructor = jsonSonstructor + message.get(i);
                        jsonSonstructor = jsonSonstructor + " ";
                    }
                    jsonSonstructor = jsonSonstructor + "\",";
                    ////////////////////////////////////////////////////////////////////////
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Executed Order\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 9);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Timestamp\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 9, 13);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Order Token\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";
                    Object id = bigInt;
                    data.add(bigInt);
                    for (int x = 0; x < OUCHMain.ordersTable.getRowCount(); x++) {
                        if (OUCHMain.ordersTable.getValueAt(x, 0).equals(id) == true) {
                            OUCHMain.ordersTable.setValueAt("Executed", x, 9);
                        }
                    }

                    bytes = Arrays.copyOfRange(message.array(), 13, 21);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Executed Quantity\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";
                    data.add(bigInt);

                    bytes = Arrays.copyOfRange(message.array(), 21, 25);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Executed Price\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";
                    data.add(bigInt);

                    bytes = Arrays.copyOfRange(message.array(), 25, 26);
                    jsonSonstructor = jsonSonstructor + "\"Liquidity Flag\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";
                    data.add(new String(bytes, "UTF-8"));

                    bytes = Arrays.copyOfRange(message.array(), 26, 34);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Match Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";
                    data.add(bigInt);

                    bytes = Arrays.copyOfRange(message.array(), 34, 38);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Counter Party Id\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + "}}";

                    data.add("Trade");

                    modelTrades.addRow(data);

                } catch (UnsupportedEncodingException ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }
                break;
            case broken_trade:
                count_broken_trade++;
                try {
                    ///////////////////////////////////////////////////////////////////Bytes
                    jsonSonstructor = jsonSonstructor + "\"Bytes\":\"";
                    for (int i = 0; i < 22; i++) {
                        jsonSonstructor = jsonSonstructor + message.get(i);
                        jsonSonstructor = jsonSonstructor + " ";
                    }
                    jsonSonstructor = jsonSonstructor + "\",";
                    ////////////////////////////////////////////////////////////////////////
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Busted Trade\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 9);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Timestamp\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 9, 13);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Order Token\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 13, 21);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Match Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";
                    Object id = bigInt;
                    for (int x = 0; x < OUCHMain.tradesTable.getRowCount(); x++) {
                        if (OUCHMain.tradesTable.getValueAt(x, 4).equals(id) == true) {
                            OUCHMain.tradesTable.setValueAt("Broken", x, 5);
                        }
                    }

                    bytes = Arrays.copyOfRange(message.array(), 21, 22);
                    jsonSonstructor = jsonSonstructor + "\"Reason\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (UnsupportedEncodingException ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;

            case rejected_message:
                count_rejected_message++;

                try {
                    ///////////////////////////////////////////////////////////////////Bytes
                    jsonSonstructor = jsonSonstructor + "\"Bytes\":\"";
                    for (int i = 0; i < 14; i++) {
                        jsonSonstructor = jsonSonstructor + message.get(i);
                        jsonSonstructor = jsonSonstructor + " ";
                    }
                    jsonSonstructor = jsonSonstructor + "\",";
                    ////////////////////////////////////////////////////////////////////////
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Rejected Message\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 9);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Timestamp\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 9, 13);
                    bigInt = new BigInteger(bytes);
                    token = bigInt.intValue();
                    jsonSonstructor = jsonSonstructor + "\"Order Token\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 13, 14);
                    jsonSonstructor = jsonSonstructor + "\"Reason\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (UnsupportedEncodingException ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }
                break;

            default:
                jsonSonstructor = jsonSonstructor + "\"Name\":\"Unsupported Message Type - " + (char) type + "\"}}";
                break;
        }
        return jsonSonstructor;
    }
}
