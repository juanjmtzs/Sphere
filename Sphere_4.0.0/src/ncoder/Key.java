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

package ncoder;

public class Key {

    private String password;

    private char theKey[] = new char[10];

    public Key(String currentPassword) {
        password = currentPassword;

    }

    public char[] generateKey() {

        char array[] = password.toCharArray();

        int value;

        for (int i = 0; i < 10; i++) {

            value = (int) array[i];

            String s = Integer.toBinaryString(value);

            theKey[i] = s.charAt(5);

        }
        String keyString;

        String firstChar = String.valueOf(theKey[0]);
        String secondChar = String.valueOf(theKey[1]);
        String thirdChar = String.valueOf(theKey[2]);
        String fourthChar = String.valueOf(theKey[3]);
        String fifthChar = String.valueOf(theKey[4]);
        String sixthChar = String.valueOf(theKey[5]);
        String seventhChar = String.valueOf(theKey[6]);
        String eightChar = String.valueOf(theKey[7]);
        String nineChar = String.valueOf(theKey[8]);
        String tenthChar = String.valueOf(theKey[9]);

        keyString = firstChar + secondChar + thirdChar + fourthChar + fifthChar
                + sixthChar + seventhChar + eightChar + nineChar + tenthChar;

        return theKey;

    }

}
