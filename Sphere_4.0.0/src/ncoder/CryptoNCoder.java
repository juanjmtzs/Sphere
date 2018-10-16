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

public class CryptoNCoder {

    private char key[] = new char[16];
    private char subkey1[] = new char[8];
    private char subkey2[] = new char[8];
    private char subkey3[] = new char[8];
    private char subkey4[] = new char[8];
    private char subkey5[] = new char[8];
    private char subkey6[] = new char[8];
    private char subkey7[] = new char[8];
    private char subkey8[] = new char[8];
    private char subkey9[] = new char[8];
    private char subkey10[] = new char[8];
    private char subkey11[] = new char[8];
    private char subkey12[] = new char[8];
    private char subkey13[] = new char[8];
    private char subkey14[] = new char[8];
    private char subkey15[] = new char[8];
    private char subkey16[] = new char[8];

    public String currentPassword;

    public String encrypt(String key, String text) {
        currentPassword = key;

        generateKeys();

        String textToEncrypt = text;
        String EncryptedText = "";

        char charsToManipulate[] = new char[8];

        for (int i = 0; i < textToEncrypt.length(); i++) {

            char theChar = textToEncrypt.charAt(i);
            int value = (int) theChar;
            String valueString = Integer.toBinaryString(value);

            while (valueString.length() < 8) {
                valueString = "0" + valueString;
            }

            for (int j = 0; j < 8; j++) {
                charsToManipulate[j] = valueString.charAt(j);
            }

            // chars to manipulate are now ready
            Method s = new Method();

            char array1[] = new char[8];
            array1 = s.IP(charsToManipulate);
            char array2[] = new char[8];
            array2 = s.FK(array1, subkey1);
            char array3[] = new char[8];
            array3 = s.SW(array2);
            char array4[] = new char[8];
            array4 = s.FK(array3, subkey2);
            char array5[] = new char[8];
            array5 = s.SW(array4);
            char array6[] = new char[8];
            array6 = s.FK(array5, subkey3);
            char array7[] = new char[8];
            array7 = s.SW(array6);
            char array8[] = new char[8];
            array8 = s.FK(array7, subkey4);
            char array9[] = new char[8];
            array9 = s.SW(array8);
            char array10[] = new char[8];
            array10 = s.FK(array9, subkey5);
            char array11[] = new char[8];
            array11 = s.SW(array10);
            char array12[] = new char[8];
            array12 = s.FK(array11, subkey6);
            char array13[] = new char[8];
            array13 = s.SW(array12);
            char array14[] = new char[8];
            array14 = s.FK(array13, subkey7);
            char array15[] = new char[8];
            array15 = s.SW(array14);
            char array16[] = new char[8];
            array16 = s.FK(array15, subkey8);
            char array17[] = new char[8];
            array17 = s.SW(array16);
            char array18[] = new char[8];
            array18 = s.FK(array17, subkey9);
            char array19[] = new char[8];
            array19 = s.SW(array18);
            char array20[] = new char[8];
            array20 = s.FK(array19, subkey10);
            char array21[] = new char[8];
            array21 = s.SW(array20);
            char array22[] = new char[8];
            array22 = s.FK(array21, subkey11);
            char array23[] = new char[8];
            array23 = s.SW(array22);
            char array24[] = new char[8];
            array24 = s.FK(array23, subkey12);
            char array25[] = new char[8];
            array25 = s.SW(array24);
            char array26[] = new char[8];
            array26 = s.FK(array25, subkey13);
            char array27[] = new char[8];
            array27 = s.SW(array26);
            char array28[] = new char[8];
            array28 = s.FK(array27, subkey14);
            char array29[] = new char[8];
            array29 = s.SW(array28);
            char array30[] = new char[8];
            array30 = s.FK(array29, subkey15);
            char array31[] = new char[8];
            array31 = s.SW(array30);
            char array32[] = new char[8];
            array32 = s.FK(array31, subkey16);

            char arrayFinal[] = new char[8];
            arrayFinal = s.IP_inverse(array32);

            String tmp = new String("");
            for (int k = 0; k < arrayFinal.length; k++) {
                tmp += String.valueOf(arrayFinal[k]);
            }

            EncryptedText += (char) (Integer.parseInt(tmp, 2));

        }

        return EncryptedText;

    }

    // S-DES decryption algorithm
    public String decrypt(String key, String text) {
        currentPassword = key;

        generateKeys();

        String textToDecrypt = text;
        String DecryptedText = new String("");

        char charsToManipulate[] = new char[8];

        for (int i = 0; i < textToDecrypt.length(); i++) {

            char theChar = textToDecrypt.charAt(i);
            int value = (int) theChar;
            String valueString = Integer.toBinaryString(value);

            while (valueString.length() < 8) {
                valueString = "0" + valueString;
            }

            for (int j = 0; j < 8; j++) {
                charsToManipulate[j] = valueString.charAt(j);
            }
            // chars to manipulate are now ready

            Method s = new Method();

            char array1[] = new char[8];
            array1 = s.IP(charsToManipulate);
            char array2[] = new char[8];
            array2 = s.FK(array1, subkey16);
            char array3[] = new char[8];
            array3 = s.SW(array2);
            char array4[] = new char[8];
            array4 = s.FK(array3, subkey15);
            char array5[] = new char[8];
            array5 = s.SW(array4);
            char array6[] = new char[8];
            array6 = s.FK(array5, subkey14);
            char array7[] = new char[8];
            array7 = s.SW(array6);
            char array8[] = new char[8];
            array8 = s.FK(array7, subkey13);
            char array9[] = new char[8];
            array9 = s.SW(array8);
            char array10[] = new char[8];
            array10 = s.FK(array9, subkey12);
            char array11[] = new char[8];
            array11 = s.SW(array10);
            char array12[] = new char[8];
            array12 = s.FK(array11, subkey11);
            char array13[] = new char[8];
            array13 = s.SW(array12);
            char array14[] = new char[8];
            array14 = s.FK(array13, subkey10);
            char array15[] = new char[8];
            array15 = s.SW(array14);
            char array16[] = new char[8];
            array16 = s.FK(array15, subkey9);
            char array17[] = new char[8];
            array17 = s.SW(array16);
            char array18[] = new char[8];
            array18 = s.FK(array17, subkey8);
            char array19[] = new char[8];
            array19 = s.SW(array18);
            char array20[] = new char[8];
            array20 = s.FK(array19, subkey7);
            char array21[] = new char[8];
            array21 = s.SW(array20);
            char array22[] = new char[8];
            array22 = s.FK(array21, subkey6);
            char array23[] = new char[8];
            array23 = s.SW(array22);
            char array24[] = new char[8];
            array24 = s.FK(array23, subkey5);
            char array25[] = new char[8];
            array25 = s.SW(array24);
            char array26[] = new char[8];
            array26 = s.FK(array25, subkey4);
            char array27[] = new char[8];
            array27 = s.SW(array26);
            char array28[] = new char[8];
            array28 = s.FK(array27, subkey3);
            char array29[] = new char[8];
            array29 = s.SW(array28);
            char array30[] = new char[8];
            array30 = s.FK(array29, subkey2);
            char array31[] = new char[8];
            array31 = s.SW(array30);
            char array32[] = new char[8];
            array32 = s.FK(array31, subkey1);

            char arrayFinal[] = new char[8];
            arrayFinal = s.IP_inverse(array32);

            String tmp = new String("");
            for (int k = 0; k < arrayFinal.length; k++) {
                tmp += String.valueOf(arrayFinal[k]);
            }

            DecryptedText += (char) (Integer.parseInt(tmp, 2));

        }

        return DecryptedText;
    }

    private void generateKeys() {

        key = (new Key(currentPassword)).generateKey();

        // P10
        char p10[] = new char[10];
        p10[0] = key[1];
        p10[1] = key[5];
        p10[2] = key[0];
        p10[3] = key[8];
        p10[4] = key[2];
        p10[5] = key[3];
        p10[6] = key[9];
        p10[7] = key[7];
        p10[8] = key[6];
        p10[9] = key[4];

        // Split the 10 bits into 5's
        ConvABit LeftFiveBits = new ConvABit(5);
        ConvABit RightFiveBits = new ConvABit(5);

        for (int i = 0; i < p10.length; i++) {

            if (i < 5) {
                if (p10[i] == '1') {
                    LeftFiveBits.set(i);
                } else if (p10[i] == '0') {
                    LeftFiveBits.clear(i);
                }
            } else {
                if (p10[i] == '1') {
                    RightFiveBits.set(i);
                } else if (p10[i] == '0') {
                    RightFiveBits.clear(i);
                }
            }
        }

        // Apply LS_1 on each one
        ConvABit LS_1LeftBits = LeftFiveBits.LS_1(5);
        ConvABit LS_1RightBits = RightFiveBits.LS_1(5);

        // Apply P8 to produce the first subkey
        char theKeyAfterLS_1[] = new char[10];

        char left1[] = LS_1LeftBits.bitSetToCharArray(5);
        char right1[] = LS_1RightBits.bitSetToCharArray(5);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                theKeyAfterLS_1[i] = left1[i];
            } else {
                theKeyAfterLS_1[i] = right1[i - 5];
            }
        }

        subkey1[0] = theKeyAfterLS_1[2];
        subkey1[1] = theKeyAfterLS_1[7];
        subkey1[2] = theKeyAfterLS_1[3];
        subkey1[3] = theKeyAfterLS_1[5];
        subkey1[4] = theKeyAfterLS_1[6];
        subkey1[5] = theKeyAfterLS_1[8];
        subkey1[6] = theKeyAfterLS_1[9];
        subkey1[7] = theKeyAfterLS_1[4];

        // Apply LS_2
        ConvABit LS_2LeftBits = LS_1LeftBits.LS_2(5);
        ConvABit LS_2RightBits = LS_1RightBits.LS_2(5);

        // Apply P8
        char theKeyAfterLS_2[] = new char[10];

        char left2[] = LS_2LeftBits.bitSetToCharArray(5);
        char right2[] = LS_2RightBits.bitSetToCharArray(5);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                theKeyAfterLS_2[i] = left2[i];
            } else {
                theKeyAfterLS_2[i] = right2[i - 5];
            }
        }

        subkey2[0] = theKeyAfterLS_2[2];
        subkey2[1] = theKeyAfterLS_2[7];
        subkey2[2] = theKeyAfterLS_2[3];
        subkey2[3] = theKeyAfterLS_2[5];
        subkey2[4] = theKeyAfterLS_2[6];
        subkey2[5] = theKeyAfterLS_2[8];
        subkey2[6] = theKeyAfterLS_2[9];
        subkey2[7] = theKeyAfterLS_2[4];

        // Apply LS_3
        ConvABit LS_3LeftBits = LS_2LeftBits.LS_3(5);
        ConvABit LS_3RightBits = LS_2RightBits.LS_3(5);

        // Apply P8
        char theKeyAfterLS_3[] = new char[10];

        char left3[] = LS_3LeftBits.bitSetToCharArray(5);
        char right3[] = LS_3RightBits.bitSetToCharArray(5);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                theKeyAfterLS_3[i] = left3[i];
            } else {
                theKeyAfterLS_3[i] = right3[i - 5];
            }
        }

        subkey3[0] = theKeyAfterLS_3[2];
        subkey3[1] = theKeyAfterLS_3[7];
        subkey3[2] = theKeyAfterLS_3[3];
        subkey3[3] = theKeyAfterLS_3[5];
        subkey3[4] = theKeyAfterLS_3[6];
        subkey3[5] = theKeyAfterLS_3[8];
        subkey3[6] = theKeyAfterLS_3[9];
        subkey3[7] = theKeyAfterLS_3[4];

        // Apply LS_4
        ConvABit LS_4LeftBits = LS_3LeftBits.LS_4(5);
        ConvABit LS_4RightBits = LS_3RightBits.LS_4(5);

        // Apply P8
        char theKeyAfterLS_4[] = new char[10];

        char left4[] = LS_4LeftBits.bitSetToCharArray(5);
        char right4[] = LS_4RightBits.bitSetToCharArray(5);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                theKeyAfterLS_4[i] = left4[i];
            } else {
                theKeyAfterLS_4[i] = right4[i - 5];
            }
        }

        subkey4[0] = theKeyAfterLS_4[2];
        subkey4[1] = theKeyAfterLS_4[7];
        subkey4[2] = theKeyAfterLS_4[3];
        subkey4[3] = theKeyAfterLS_4[5];
        subkey4[4] = theKeyAfterLS_4[6];
        subkey4[5] = theKeyAfterLS_4[8];
        subkey4[6] = theKeyAfterLS_4[9];
        subkey4[7] = theKeyAfterLS_4[4];

        // Apply LS_5
        ConvABit LS_5LeftBits = LS_4LeftBits.LS_5(5);
        ConvABit LS_5RightBits = LS_4RightBits.LS_5(5);

        // Apply P8
        char theKeyAfterLS_5[] = new char[10];

        char left5[] = LS_5LeftBits.bitSetToCharArray(5);
        char right5[] = LS_5RightBits.bitSetToCharArray(5);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                theKeyAfterLS_5[i] = left5[i];
            } else {
                theKeyAfterLS_5[i] = right5[i - 5];
            }
        }

        subkey4[0] = theKeyAfterLS_4[2];
        subkey4[1] = theKeyAfterLS_4[7];
        subkey4[2] = theKeyAfterLS_4[3];
        subkey4[3] = theKeyAfterLS_4[5];
        subkey4[4] = theKeyAfterLS_4[6];
        subkey4[5] = theKeyAfterLS_4[8];
        subkey4[6] = theKeyAfterLS_4[9];
        subkey4[7] = theKeyAfterLS_4[4];

        // Apply LS_6
        ConvABit LS_6LeftBits = LS_5LeftBits.LS_6(5);
        ConvABit LS_6RightBits = LS_5RightBits.LS_6(5);

        // Apply P8
        char theKeyAfterLS_6[] = new char[10];

        char left6[] = LS_6LeftBits.bitSetToCharArray(5);
        char right6[] = LS_6RightBits.bitSetToCharArray(5);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                theKeyAfterLS_6[i] = left6[i];
            } else {
                theKeyAfterLS_6[i] = right6[i - 5];
            }
        }

        subkey6[0] = theKeyAfterLS_6[2];
        subkey6[1] = theKeyAfterLS_6[7];
        subkey6[2] = theKeyAfterLS_6[3];
        subkey6[3] = theKeyAfterLS_6[5];
        subkey6[4] = theKeyAfterLS_6[6];
        subkey6[5] = theKeyAfterLS_6[8];
        subkey6[6] = theKeyAfterLS_6[9];
        subkey6[7] = theKeyAfterLS_6[4];

        // Apply LS_7
        ConvABit LS_7LeftBits = LS_6LeftBits.LS_7(5);
        ConvABit LS_7RightBits = LS_6RightBits.LS_7(5);

        // Apply P8
        char theKeyAfterLS_7[] = new char[10];

        char left7[] = LS_7LeftBits.bitSetToCharArray(5);
        char right7[] = LS_7RightBits.bitSetToCharArray(5);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                theKeyAfterLS_7[i] = left7[i];
            } else {
                theKeyAfterLS_7[i] = right7[i - 5];
            }
        }

        subkey7[0] = theKeyAfterLS_7[2];
        subkey7[1] = theKeyAfterLS_7[7];
        subkey7[2] = theKeyAfterLS_7[3];
        subkey7[3] = theKeyAfterLS_7[5];
        subkey7[4] = theKeyAfterLS_7[6];
        subkey7[5] = theKeyAfterLS_7[8];
        subkey7[6] = theKeyAfterLS_7[9];
        subkey7[7] = theKeyAfterLS_7[4];

        // Apply LS_8
        ConvABit LS_8LeftBits = LS_7LeftBits.LS_8(5);
        ConvABit LS_8RightBits = LS_7RightBits.LS_8(5);

        // Apply P8
        char theKeyAfterLS_8[] = new char[10];

        char left8[] = LS_8LeftBits.bitSetToCharArray(5);
        char right8[] = LS_8RightBits.bitSetToCharArray(5);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                theKeyAfterLS_8[i] = left8[i];
            } else {
                theKeyAfterLS_8[i] = right8[i - 5];
            }
        }

        subkey8[0] = theKeyAfterLS_8[2];
        subkey8[1] = theKeyAfterLS_8[7];
        subkey8[2] = theKeyAfterLS_8[3];
        subkey8[3] = theKeyAfterLS_8[5];
        subkey8[4] = theKeyAfterLS_8[6];
        subkey8[5] = theKeyAfterLS_8[8];
        subkey8[6] = theKeyAfterLS_8[9];
        subkey8[7] = theKeyAfterLS_8[4];

        // Apply LS_9
        ConvABit LS_9LeftBits = LS_8LeftBits.LS_9(5);
        ConvABit LS_9RightBits = LS_8RightBits.LS_9(5);

        // Apply P8
        char theKeyAfterLS_9[] = new char[10];

        char left9[] = LS_9LeftBits.bitSetToCharArray(5);
        char right9[] = LS_9RightBits.bitSetToCharArray(5);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                theKeyAfterLS_9[i] = left9[i];
            } else {
                theKeyAfterLS_9[i] = right9[i - 5];
            }
        }

        subkey9[0] = theKeyAfterLS_9[2];
        subkey9[1] = theKeyAfterLS_9[7];
        subkey9[2] = theKeyAfterLS_9[3];
        subkey9[3] = theKeyAfterLS_9[5];
        subkey9[4] = theKeyAfterLS_9[6];
        subkey9[5] = theKeyAfterLS_9[8];
        subkey9[6] = theKeyAfterLS_9[9];
        subkey9[7] = theKeyAfterLS_9[4];

        // Apply LS_10
        ConvABit LS_10LeftBits = LS_9LeftBits.LS_10(5);
        ConvABit LS_10RightBits = LS_9RightBits.LS_10(5);

        // Apply P8
        char theKeyAfterLS_10[] = new char[10];

        char left10[] = LS_10LeftBits.bitSetToCharArray(5);
        char right10[] = LS_10RightBits.bitSetToCharArray(5);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                theKeyAfterLS_10[i] = left10[i];
            } else {
                theKeyAfterLS_10[i] = right10[i - 5];
            }
        }

        subkey10[0] = theKeyAfterLS_10[2];
        subkey10[1] = theKeyAfterLS_10[7];
        subkey10[2] = theKeyAfterLS_10[3];
        subkey10[3] = theKeyAfterLS_10[5];
        subkey10[4] = theKeyAfterLS_10[6];
        subkey10[5] = theKeyAfterLS_10[8];
        subkey10[6] = theKeyAfterLS_10[9];
        subkey10[7] = theKeyAfterLS_10[4];

        // Apply LS_11
        ConvABit LS_11LeftBits = LS_10LeftBits.LS_11(5);
        ConvABit LS_11RightBits = LS_10RightBits.LS_11(5);

        // Apply P8
        char theKeyAfterLS_11[] = new char[10];

        char left11[] = LS_11LeftBits.bitSetToCharArray(5);
        char right11[] = LS_11RightBits.bitSetToCharArray(5);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                theKeyAfterLS_11[i] = left11[i];
            } else {
                theKeyAfterLS_11[i] = right11[i - 5];
            }
        }

        subkey11[0] = theKeyAfterLS_11[2];
        subkey11[1] = theKeyAfterLS_11[7];
        subkey11[2] = theKeyAfterLS_11[3];
        subkey11[3] = theKeyAfterLS_11[5];
        subkey11[4] = theKeyAfterLS_11[6];
        subkey11[5] = theKeyAfterLS_11[8];
        subkey11[6] = theKeyAfterLS_11[9];
        subkey11[7] = theKeyAfterLS_11[4];

        // Apply LS_12
        ConvABit LS_12LeftBits = LS_11LeftBits.LS_12(5);
        ConvABit LS_12RightBits = LS_11RightBits.LS_12(5);

        // Apply P8
        char theKeyAfterLS_12[] = new char[10];

        char left12[] = LS_12LeftBits.bitSetToCharArray(5);
        char right12[] = LS_12RightBits.bitSetToCharArray(5);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                theKeyAfterLS_12[i] = left12[i];
            } else {
                theKeyAfterLS_12[i] = right12[i - 5];
            }
        }

        subkey12[0] = theKeyAfterLS_12[2];
        subkey12[1] = theKeyAfterLS_12[7];
        subkey12[2] = theKeyAfterLS_12[3];
        subkey12[3] = theKeyAfterLS_12[5];
        subkey12[4] = theKeyAfterLS_12[6];
        subkey12[5] = theKeyAfterLS_12[8];
        subkey12[6] = theKeyAfterLS_12[9];
        subkey12[7] = theKeyAfterLS_12[4];

        // Apply LS_13
        ConvABit LS_13LeftBits = LS_12LeftBits.LS_13(5);
        ConvABit LS_13RightBits = LS_12RightBits.LS_13(5);

        // Apply P8
        char theKeyAfterLS_13[] = new char[10];

        char left13[] = LS_13LeftBits.bitSetToCharArray(5);
        char right13[] = LS_13RightBits.bitSetToCharArray(5);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                theKeyAfterLS_13[i] = left13[i];
            } else {
                theKeyAfterLS_13[i] = right13[i - 5];
            }
        }

        subkey13[0] = theKeyAfterLS_13[2];
        subkey13[1] = theKeyAfterLS_13[7];
        subkey13[2] = theKeyAfterLS_13[3];
        subkey13[3] = theKeyAfterLS_13[5];
        subkey13[4] = theKeyAfterLS_13[6];
        subkey13[5] = theKeyAfterLS_13[8];
        subkey13[6] = theKeyAfterLS_13[9];
        subkey13[7] = theKeyAfterLS_13[4];

        // Apply LS_14
        ConvABit LS_14LeftBits = LS_13LeftBits.LS_14(5);
        ConvABit LS_14RightBits = LS_13RightBits.LS_14(5);

        // Apply P8
        char theKeyAfterLS_14[] = new char[10];

        char left14[] = LS_14LeftBits.bitSetToCharArray(5);
        char right14[] = LS_14RightBits.bitSetToCharArray(5);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                theKeyAfterLS_14[i] = left14[i];
            } else {
                theKeyAfterLS_14[i] = right14[i - 5];
            }
        }

        subkey14[0] = theKeyAfterLS_14[2];
        subkey14[1] = theKeyAfterLS_14[7];
        subkey14[2] = theKeyAfterLS_14[3];
        subkey14[3] = theKeyAfterLS_14[5];
        subkey14[4] = theKeyAfterLS_14[6];
        subkey14[5] = theKeyAfterLS_14[8];
        subkey14[6] = theKeyAfterLS_14[9];
        subkey14[7] = theKeyAfterLS_14[4];

        // Apply LS_15
        ConvABit LS_15LeftBits = LS_14LeftBits.LS_15(5);
        ConvABit LS_15RightBits = LS_14RightBits.LS_15(5);

        // Apply P8
        char theKeyAfterLS_15[] = new char[10];

        char left15[] = LS_15LeftBits.bitSetToCharArray(5);
        char right15[] = LS_15RightBits.bitSetToCharArray(5);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                theKeyAfterLS_15[i] = left15[i];
            } else {
                theKeyAfterLS_15[i] = right15[i - 5];
            }
        }

        subkey15[0] = theKeyAfterLS_15[2];
        subkey15[1] = theKeyAfterLS_15[7];
        subkey15[2] = theKeyAfterLS_15[3];
        subkey15[3] = theKeyAfterLS_15[5];
        subkey15[4] = theKeyAfterLS_15[6];
        subkey15[5] = theKeyAfterLS_15[8];
        subkey15[6] = theKeyAfterLS_15[9];
        subkey15[7] = theKeyAfterLS_15[4];

        // Apply LS_16
        ConvABit LS_16LeftBits = LS_15LeftBits.LS_16(5);
        ConvABit LS_16RightBits = LS_15RightBits.LS_16(5);

        // Apply P8
        char theKeyAfterLS_16[] = new char[10];

        char left16[] = LS_16LeftBits.bitSetToCharArray(5);
        char right16[] = LS_16RightBits.bitSetToCharArray(5);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                theKeyAfterLS_16[i] = left16[i];
            } else {
                theKeyAfterLS_16[i] = right16[i - 5];
            }
        }

        subkey16[0] = theKeyAfterLS_16[2];
        subkey16[1] = theKeyAfterLS_16[7];
        subkey16[2] = theKeyAfterLS_16[3];
        subkey16[3] = theKeyAfterLS_16[5];
        subkey16[4] = theKeyAfterLS_16[6];
        subkey16[5] = theKeyAfterLS_16[8];
        subkey16[6] = theKeyAfterLS_16[9];
        subkey16[7] = theKeyAfterLS_16[4];

    }

}
