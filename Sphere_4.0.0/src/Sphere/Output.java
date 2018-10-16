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

import java.io.File;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Output {

    public void changeOutput(String service, String userHomeFolder) {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        Date date = new Date();
        switch (service) {
            case "NEWS":

                try {
                    System.setOut(new PrintStream(new File(userHomeFolder, "NEWS_" + dateFormat.format(date) + ".log")));
                } catch (Exception e) {
                    String messageError = e + "";

                    JFrame error = new JFrame();

                    JOptionPane.showMessageDialog(error, messageError, "Error", JOptionPane.ERROR_MESSAGE);

                }
                break;
            case "LTP":
                try {
                    System.setOut(new PrintStream(new File(userHomeFolder, "LTP_" + dateFormat.format(date) + ".log")));
                } catch (Exception e) {
                    String messageError = e + "";

                    JFrame error = new JFrame();

                    JOptionPane.showMessageDialog(error, messageError, "Error", JOptionPane.ERROR_MESSAGE);

                }
                break;
            case "BBO":
                try {
                    System.setOut(new PrintStream(new File(userHomeFolder, "BBO_" + dateFormat.format(date) + ".log")));
                } catch (Exception e) {
                    String messageError = e + "";

                    JFrame error = new JFrame();

                    JOptionPane.showMessageDialog(error, messageError, "Error", JOptionPane.ERROR_MESSAGE);

                }
                break;
            case "GLIMPSE":
                try {
                    System.setOut(new PrintStream(new File(userHomeFolder, "GLIMPSE_" + dateFormat.format(date) + ".log")));
                } catch (Exception e) {
                    String messageError = e + "";

                    JFrame error = new JFrame();

                    JOptionPane.showMessageDialog(error, messageError, "Error", JOptionPane.ERROR_MESSAGE);

                }
                break;
            case "TV":
                try {
                    System.setOut(new PrintStream(new File(userHomeFolder, "TV_" + dateFormat.format(date) + ".log")));
                } catch (Exception e) {
                    String messageError = e + "";

                    JFrame error = new JFrame();

                    JOptionPane.showMessageDialog(error, messageError, "Error", JOptionPane.ERROR_MESSAGE);

                }
                break;

            case "TV Multicast":
                try {
                    System.setOut(new PrintStream(new File(userHomeFolder, "TVMulticast_" + dateFormat.format(date) + ".log")));
                } catch (Exception e) {
                    String messageError = e + "";

                    JFrame error = new JFrame();

                    JOptionPane.showMessageDialog(error, messageError, "Error", JOptionPane.ERROR_MESSAGE);

                }
                break;
            case "Retransmissions":
                try {
                    System.setOut(new PrintStream(new File(userHomeFolder, "Retransmission_" + dateFormat.format(date) + ".log")));
                } catch (Exception e) {
                    String messageError = e + "";

                    JFrame error = new JFrame();

                    JOptionPane.showMessageDialog(error, messageError, "Error", JOptionPane.ERROR_MESSAGE);

                }
                break;
            case "OUCH":
                try {
                    System.setOut(new PrintStream(new File(userHomeFolder, "OUCH_" + dateFormat.format(date) + ".log")));
                } catch (Exception e) {
                    String messageError = e + "";

                    JFrame error = new JFrame();

                    JOptionPane.showMessageDialog(error, messageError, "Error", JOptionPane.ERROR_MESSAGE);

                }
                break;
            case "BMV Multicast":
                try {
                    System.setOut(new PrintStream(new File(userHomeFolder, "BMVMulticast_" + dateFormat.format(date) + ".log")));
                } catch (Exception e) {
                    String messageError = e + "";

                    JFrame error = new JFrame();

                    JOptionPane.showMessageDialog(error, messageError, "Error", JOptionPane.ERROR_MESSAGE);

                }
                break;
            case "BMV Unicast":
                try {
                    System.setOut(new PrintStream(new File(userHomeFolder, "BMVUnicast_" + dateFormat.format(date) + ".log")));
                } catch (Exception e) {
                    String messageError = e + "";

                    JFrame error = new JFrame();

                    JOptionPane.showMessageDialog(error, messageError, "Error", JOptionPane.ERROR_MESSAGE);

                }
                break;
        }
    }

}
