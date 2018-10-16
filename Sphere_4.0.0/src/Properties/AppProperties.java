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

package Properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;


public class AppProperties {

    private Properties configProps;
    private File configFile;
    private String username, password, session, ip, port, sequence, finalSequence, receiver, group, license;

    public AppProperties() {
    }

    public AppProperties(String username, String password, String ip, String port, String group, boolean x, String receiver) {
        this.username = username;
        this.password = password;
        this.ip = ip;
        this.port = port;
        this.group = group;
        this.receiver = receiver;

    }

    public AppProperties(String username, String password, String session, String ip, String port, String sequence) {
        this.username = username;
        this.password = password;
        this.session = session;
        this.ip = ip;
        this.port = port;
        this.sequence = sequence;
    }

    public AppProperties(String ip, String port, String receiver) {
        this.ip = ip;
        this.port = port;
        this.receiver = receiver;
    }

    public AppProperties(String session, String ip, String port, String sequence, String finalSequence) {
        this.session = session;
        this.ip = ip;
        this.port = port;
        this.sequence = sequence;
        this.finalSequence = finalSequence;
    }

    public AppProperties(String license) {
        this.license = license;
    }

    public void loadPropertiesLicense() throws IOException {
        configFile = new File("configLicense.properties");
        Properties defaultProps = new Properties();

        defaultProps.setProperty("license", "");

        configProps = new Properties(defaultProps);

        InputStream inputStream = new FileInputStream(configFile);
        configProps.load(inputStream);
        inputStream.close();
    }

    public void savePropertiesLicense() throws IOException {
        configFile = new File("configLicense.properties");

        Properties defaultProps = new Properties();

        defaultProps.setProperty("license", "");

        configProps = new Properties(defaultProps);

        configProps.setProperty("license", license);

        OutputStream outputStream = new FileOutputStream(configFile);
        configProps.store(outputStream, "Sphere License Settings");
        outputStream.close();
    }

    public void loadPropertiesTV() throws IOException {
        configFile = new File("configTV.properties");
        Properties defaultProps = new Properties();

        defaultProps.setProperty("username", "username");
        defaultProps.setProperty("password", "password");
        defaultProps.setProperty("session", "0000");
        defaultProps.setProperty("ip", "0.0.0.0");
        defaultProps.setProperty("port", "0000");
        defaultProps.setProperty("sequence", "0");

        configProps = new Properties(defaultProps);

        InputStream inputStream = new FileInputStream(configFile);
        configProps.load(inputStream);
        inputStream.close();
    }

    public void savePropertiesTV() throws IOException {
        configFile = new File("configTV.properties");

        Properties defaultProps = new Properties();

        defaultProps.setProperty("username", "username");
        defaultProps.setProperty("password", "password");
        defaultProps.setProperty("session", "0000");
        defaultProps.setProperty("ip", "0.0.0.0");
        defaultProps.setProperty("port", "0000");
        defaultProps.setProperty("sequence", "0");

        configProps = new Properties(defaultProps);

        configProps.setProperty("username", username);
        configProps.setProperty("password", password);
        configProps.setProperty("session", session);
        configProps.setProperty("ip", ip);
        configProps.setProperty("port", port);
        configProps.setProperty("sequence", sequence);
        OutputStream outputStream = new FileOutputStream(configFile);
        configProps.store(outputStream, "Sphere TV Service Settings");
        outputStream.close();
    }

    public void loadPropertiesBBO() throws IOException {
        configFile = new File("configBBO.properties");
        Properties defaultProps = new Properties();

        defaultProps.setProperty("username", "username");
        defaultProps.setProperty("password", "password");
        defaultProps.setProperty("session", "0000");
        defaultProps.setProperty("ip", "0.0.0.0");
        defaultProps.setProperty("port", "0000");
        defaultProps.setProperty("sequence", "0");

        configProps = new Properties(defaultProps);

        InputStream inputStream = new FileInputStream(configFile);
        configProps.load(inputStream);
        inputStream.close();
    }

    public void savePropertiesBBO() throws IOException {
        configFile = new File("configBBO.properties");

        Properties defaultProps = new Properties();

        defaultProps.setProperty("username", "username");
        defaultProps.setProperty("password", "password");
        defaultProps.setProperty("session", "0000");
        defaultProps.setProperty("ip", "0.0.0.0");
        defaultProps.setProperty("port", "0000");
        defaultProps.setProperty("sequence", "0");

        configProps = new Properties(defaultProps);

        configProps.setProperty("username", username);
        configProps.setProperty("password", password);
        configProps.setProperty("session", session);
        configProps.setProperty("ip", ip);
        configProps.setProperty("port", port);
        configProps.setProperty("sequence", sequence);
        OutputStream outputStream = new FileOutputStream(configFile);
        configProps.store(outputStream, "Sphere BBO Service Settings");
        outputStream.close();
    }

    public void loadPropertiesLTP() throws IOException {
        configFile = new File("configLTP.properties");
        Properties defaultProps = new Properties();

        defaultProps.setProperty("username", "username");
        defaultProps.setProperty("password", "password");
        defaultProps.setProperty("session", "0000");
        defaultProps.setProperty("ip", "0.0.0.0");
        defaultProps.setProperty("port", "0000");
        defaultProps.setProperty("sequence", "0");

        configProps = new Properties(defaultProps);

        InputStream inputStream = new FileInputStream(configFile);
        configProps.load(inputStream);
        inputStream.close();
    }

    public void savePropertiesLTP() throws IOException {
        configFile = new File("configLTP.properties");

        Properties defaultProps = new Properties();

        defaultProps.setProperty("username", "username");
        defaultProps.setProperty("password", "password");
        defaultProps.setProperty("session", "0000");
        defaultProps.setProperty("ip", "0.0.0.0");
        defaultProps.setProperty("port", "0000");
        defaultProps.setProperty("sequence", "0");

        configProps = new Properties(defaultProps);

        configProps.setProperty("username", username);
        configProps.setProperty("password", password);
        configProps.setProperty("session", session);
        configProps.setProperty("ip", ip);
        configProps.setProperty("port", port);
        configProps.setProperty("sequence", sequence);
        OutputStream outputStream = new FileOutputStream(configFile);
        configProps.store(outputStream, "Sphere LTP Service Settings");
        outputStream.close();
    }

    public void loadPropertiesNews() throws IOException {
        configFile = new File("configNEWS.properties");
        Properties defaultProps = new Properties();

        defaultProps.setProperty("username", "username");
        defaultProps.setProperty("password", "password");
        defaultProps.setProperty("session", "0000");
        defaultProps.setProperty("ip", "0.0.0.0");
        defaultProps.setProperty("port", "0000");
        defaultProps.setProperty("sequence", "0");

        configProps = new Properties(defaultProps);

        InputStream inputStream = new FileInputStream(configFile);
        configProps.load(inputStream);
        inputStream.close();
    }

    public void savePropertiesNews() throws IOException {
        configFile = new File("configNEWS.properties");

        Properties defaultProps = new Properties();

        defaultProps.setProperty("username", "username");
        defaultProps.setProperty("password", "password");
        defaultProps.setProperty("session", "0000");
        defaultProps.setProperty("ip", "0.0.0.0");
        defaultProps.setProperty("port", "0000");
        defaultProps.setProperty("sequence", "0");

        configProps = new Properties(defaultProps);

        configProps.setProperty("username", username);
        configProps.setProperty("password", password);
        configProps.setProperty("session", session);
        configProps.setProperty("ip", ip);
        configProps.setProperty("port", port);
        configProps.setProperty("sequence", sequence);
        OutputStream outputStream = new FileOutputStream(configFile);
        configProps.store(outputStream, "Sphere NEWS Service Settings");
        outputStream.close();
    }

    public void loadPropertiesGLIMPSE() throws IOException {
        configFile = new File("configGLIMPSE.properties");
        Properties defaultProps = new Properties();

        defaultProps.setProperty("username", "username");
        defaultProps.setProperty("password", "password");
        defaultProps.setProperty("session", "0000");
        defaultProps.setProperty("ip", "0.0.0.0");
        defaultProps.setProperty("port", "0000");
        defaultProps.setProperty("sequence", "0");

        configProps = new Properties(defaultProps);

        InputStream inputStream = new FileInputStream(configFile);
        configProps.load(inputStream);
        inputStream.close();
    }

    public void savePropertiesGLIMPSE() throws IOException {
        configFile = new File("configGLIMPSE.properties");

        Properties defaultProps = new Properties();

        defaultProps.setProperty("username", "username");
        defaultProps.setProperty("password", "password");
        defaultProps.setProperty("session", "0000");
        defaultProps.setProperty("ip", "0.0.0.0");
        defaultProps.setProperty("port", "0000");
        defaultProps.setProperty("sequence", "0");

        configProps = new Properties(defaultProps);

        configProps.setProperty("username", username);
        configProps.setProperty("password", password);
        configProps.setProperty("session", session);
        configProps.setProperty("ip", ip);
        configProps.setProperty("port", port);
        configProps.setProperty("sequence", sequence);
        OutputStream outputStream = new FileOutputStream(configFile);
        configProps.store(outputStream, "Sphere GLIMPSE Service Settings");
        outputStream.close();
    }

    public void loadPropertiesTVMulticast() throws IOException {
        configFile = new File("configTVMulticast.properties");
        Properties defaultProps = new Properties();

        defaultProps.setProperty("ip", "0.0.0.0");
        defaultProps.setProperty("port", "0000");
        defaultProps.setProperty("receiver", "");

        configProps = new Properties(defaultProps);

        InputStream inputStream = new FileInputStream(configFile);
        configProps.load(inputStream);
        inputStream.close();
    }

    public void savePropertiesTVMulticast() throws IOException {
        configFile = new File("configTVMulticast.properties");

        Properties defaultProps = new Properties();

        defaultProps.setProperty("ip", "0.0.0.0");
        defaultProps.setProperty("port", "0000");
        defaultProps.setProperty("receiver", "");

        configProps = new Properties(defaultProps);
        configProps.setProperty("ip", ip);
        configProps.setProperty("port", port);
        configProps.setProperty("receiver", receiver);
        OutputStream outputStream = new FileOutputStream(configFile);
        configProps.store(outputStream, "Sphere TVMulticast Service Settings");
        outputStream.close();
    }

    public void loadPropertiesBMVMulticast() throws IOException {
        configFile = new File("configBMVMulticast.properties");
        Properties defaultProps = new Properties();

        defaultProps.setProperty("ip", "0.0.0.0");
        defaultProps.setProperty("port", "0000");
        defaultProps.setProperty("receiver", "");

        configProps = new Properties(defaultProps);

        InputStream inputStream = new FileInputStream(configFile);
        configProps.load(inputStream);
        inputStream.close();
    }

    public void savePropertiesBMVMulticast() throws IOException {
        configFile = new File("configBMVMulticast.properties");

        Properties defaultProps = new Properties();

        defaultProps.setProperty("ip", "0.0.0.0");
        defaultProps.setProperty("port", "0000");
        defaultProps.setProperty("receiver", "");

        configProps = new Properties(defaultProps);
        configProps.setProperty("ip", ip);
        configProps.setProperty("port", port);
        configProps.setProperty("receiver", receiver);
        OutputStream outputStream = new FileOutputStream(configFile);
        configProps.store(outputStream, "Sphere BMV Multicast Service Settings");
        outputStream.close();
    }

    public void loadPropertiesRetransmission() throws IOException {
        configFile = new File("configRetransmission.properties");
        Properties defaultProps = new Properties();

        defaultProps.setProperty("session", "0000");
        defaultProps.setProperty("ip", "0.0.0.0");
        defaultProps.setProperty("port", "0000");
        defaultProps.setProperty("sequence", "0");
        defaultProps.setProperty("finalSequence", "0");

        configProps = new Properties(defaultProps);

        InputStream inputStream = new FileInputStream(configFile);
        configProps.load(inputStream);
        inputStream.close();
    }

    public void savePropertiesRetransmission() throws IOException {
        configFile = new File("configRetransmission.properties");

        Properties defaultProps = new Properties();

        defaultProps.setProperty("session", "0000");
        defaultProps.setProperty("ip", "0.0.0.0");
        defaultProps.setProperty("port", "0000");
        defaultProps.setProperty("sequence", "0");
        defaultProps.setProperty("finalSequence", "0");

        configProps = new Properties(defaultProps);

        configProps.setProperty("session", session);
        configProps.setProperty("ip", ip);
        configProps.setProperty("port", port);
        configProps.setProperty("sequence", sequence);
        configProps.setProperty("finalSequence", sequence);
        OutputStream outputStream = new FileOutputStream(configFile);
        configProps.store(outputStream, "Sphere Retransmission Service Settings");
        outputStream.close();
    }

    public void loadPropertiesOUCH() throws IOException {
        configFile = new File("configOUCH.properties");
        Properties defaultProps = new Properties();

        defaultProps.setProperty("username", "username");
        defaultProps.setProperty("password", "password");
        defaultProps.setProperty("session", "0000");
        defaultProps.setProperty("ip", "0.0.0.0");
        defaultProps.setProperty("port", "0000");
        defaultProps.setProperty("sequence", "1");

        configProps = new Properties(defaultProps);

        InputStream inputStream = new FileInputStream(configFile);
        configProps.load(inputStream);
        inputStream.close();
    }

    public void savePropertiesOUCH() throws IOException {
        configFile = new File("configOUCH.properties");

        Properties defaultProps = new Properties();

        defaultProps.setProperty("username", "username");
        defaultProps.setProperty("password", "password");
        defaultProps.setProperty("session", "0000");
        defaultProps.setProperty("ip", "0.0.0.0");
        defaultProps.setProperty("port", "0000");
        defaultProps.setProperty("sequence", "1");

        configProps = new Properties(defaultProps);

        configProps.setProperty("username", username);
        configProps.setProperty("password", password);
        configProps.setProperty("session", session);
        configProps.setProperty("ip", ip);
        configProps.setProperty("port", port);
        configProps.setProperty("sequence", sequence);
        OutputStream outputStream = new FileOutputStream(configFile);
        configProps.store(outputStream, "Sphere OUCH Service Settings");
        outputStream.close();
    }

    public void loadPropertiesBMVUnicast() throws IOException {
        configFile = new File("configBMVUnicast.properties");
        Properties defaultProps = new Properties();

        defaultProps.setProperty("username", "username");
        defaultProps.setProperty("password", "password");
        defaultProps.setProperty("group", "0");
        defaultProps.setProperty("ip", "0.0.0.0");
        defaultProps.setProperty("port", "0000");
        defaultProps.setProperty("receiver", "0.0.0.0");

        configProps = new Properties(defaultProps);

        InputStream inputStream = new FileInputStream(configFile);
        configProps.load(inputStream);
        inputStream.close();
    }

    public void savePropertiesBMVUnicast() throws IOException {
        configFile = new File("configBMVUnicast.properties");

        Properties defaultProps = new Properties();

        defaultProps.setProperty("username", "username");
        defaultProps.setProperty("password", "password");
        defaultProps.setProperty("group", "0");
        defaultProps.setProperty("ip", "0.0.0.0");
        defaultProps.setProperty("port", "0000");
        defaultProps.setProperty("receiver", "0.0.0.0");

        configProps = new Properties(defaultProps);

        configProps.setProperty("username", username);
        configProps.setProperty("password", password);
        configProps.setProperty("group", group);
        configProps.setProperty("ip", ip);
        configProps.setProperty("port", port);
        configProps.setProperty("receiver", receiver);
        OutputStream outputStream = new FileOutputStream(configFile);
        configProps.store(outputStream, "Sphere BMV Unicast Service Settings");
        outputStream.close();
    }

    public Properties getProperties() {
        return configProps;
    }

}
