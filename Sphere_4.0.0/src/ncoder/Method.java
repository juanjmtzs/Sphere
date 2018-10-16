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

public class Method {

    public char[] IP(char original[]) {
        char permuted[] = new char[8];

        permuted[0] = original[1];
        permuted[1] = original[5];
        permuted[2] = original[2];
        permuted[3] = original[0];
        permuted[4] = original[3];
        permuted[5] = original[7];
        permuted[6] = original[4];
        permuted[7] = original[6];

        return permuted;
    }

    public char[] SW(char input[]) {

        char switched[] = new char[8];

        char left[] = new char[4];
        char right[] = new char[4];

        for (int i = 0; i < input.length; i++) {
            if (i < 4) {
                left[i] = input[i];
            } else {
                right[i - 4] = input[i];
            }
        }

        for (int i = 0; i < switched.length; i++) {
            if (i < 4) {
                switched[i] = right[i];
            } else {
                switched[i] = left[i - 4];
            }
        }

        return switched;
    }

    public char[] FK(char array[], char key[]) {

        char result[] = new char[8];
        char left[] = new char[4];
        char right[] = new char[4];

        for (int i = 0; i < array.length; i++) {
            if (i < 4) {
                left[i] = array[i];
            } else if (i >= 4) {
                right[i - 4] = array[i];
            }
        }

        char rightAfterE_P[] = new char[8];
        rightAfterE_P = E_P(right);

        char firstXorResult[] = new char[8];
        firstXorResult = xor(rightAfterE_P, key);

        char leftXorResult[] = new char[4];
        char rightXorResult[] = new char[4];

        for (int i = 0; i < firstXorResult.length; i++) {
            if (i < 4) {
                leftXorResult[i] = firstXorResult[i];
            } else if (i >= 4) {
                rightXorResult[i - 4] = firstXorResult[i];
            }
        }

        char afterSBox0[] = new char[2];
        afterSBox0 = S0(leftXorResult);

        char afterSBox1[] = new char[2];
        afterSBox1 = S1(rightXorResult);

        char afterSBoxes[] = new char[4];

        for (int i = 0; i < afterSBoxes.length; i++) {
            if (i < 2) {
                afterSBoxes[i] = afterSBox0[i];
            } else {
                afterSBoxes[i] = afterSBox1[i - 2];
            }
        }

        char afterP4[] = new char[4];
        afterP4 = P4(afterSBoxes);

        char secondXorResult[] = new char[4];
        secondXorResult = xor(left, afterP4);

        char leftOutput[] = new char[4];
        char rightOutput[] = new char[4];

        leftOutput = secondXorResult;
        rightOutput = right;

        for (int i = 0; i < result.length; i++) {
            if (i < 4) {
                result[i] = leftOutput[i];
            } else {
                result[i] = rightOutput[i - 4];
            }
        }

        return result;

    }

    public char[] IP_inverse(char original[]) {
        char permuted[] = new char[8];

        permuted[0] = original[3];
        permuted[1] = original[0];
        permuted[2] = original[2];
        permuted[3] = original[4];
        permuted[4] = original[6];
        permuted[5] = original[1];
        permuted[6] = original[7];
        permuted[7] = original[5];

        return permuted;
    }

    private char[] P4(char original[]) {
        char permuted[] = new char[4];

        permuted[0] = original[1];
        permuted[1] = original[3];
        permuted[2] = original[2];
        permuted[3] = original[0];

        return permuted;
    }

    private char[] E_P(char original[]) {
        char permuted[] = new char[8];

        permuted[0] = original[3];
        permuted[1] = original[0];
        permuted[2] = original[1];
        permuted[3] = original[2];
        permuted[4] = original[1];
        permuted[5] = original[2];
        permuted[6] = original[3];
        permuted[7] = original[0];

        return permuted;
    }

    private char[] S0(char input[]) {

        int SBox0[][] = {
            {1, 0, 3, 2},
            {3, 2, 1, 0},
            {0, 2, 1, 3},
            {3, 1, 3, 2}};
        char rowChars[] = new char[2];
        char columnChars[] = new char[2];

        rowChars[0] = input[0];
        rowChars[1] = input[3];

        columnChars[0] = input[1];
        columnChars[1] = input[2];

        String rowString = new String(rowChars);
        String columnString = new String(columnChars);

        int result = SBox0[Integer.parseInt(rowString, 2)][Integer.parseInt(columnString, 2)];

        String resultString = Integer.toBinaryString(result);

        if (resultString.equals("1")) {
            resultString = "01";
        }

        if (resultString.equals("0")) {
            resultString = "00";
        }

        char output[] = new char[2];
        output[0] = resultString.charAt(0);
        output[1] = resultString.charAt(1);

        return output;

    }

    private char[] S1(char input[]) {

        int SBox1[][] = {
            {0, 1, 2, 3},
            {2, 0, 1, 3},
            {3, 0, 1, 0},
            {2, 1, 0, 3}};

        char rowChars[] = new char[2];
        char columnChars[] = new char[2];

        rowChars[0] = input[0];
        rowChars[1] = input[3];

        columnChars[0] = input[1];
        columnChars[1] = input[2];

        String rowString = new String(rowChars);
        String columnString = new String(columnChars);

        int result = SBox1[Integer.parseInt(rowString, 2)][Integer.parseInt(columnString, 2)];

        String resultString = Integer.toBinaryString(result);

        if (resultString.equals("1")) {
            resultString = "01";
        }

        if (resultString.equals("0")) {
            resultString = "00";
        }

        char output[] = new char[2];
        output[0] = resultString.charAt(0);
        output[1] = resultString.charAt(1);

        return output;

    }

    public char[] xor(char array1[], char array2[]) {

        if (array1.length == 8) {

            char result[] = new char[8];
            for (int i = 0; i < 8; i++) {
                if (array1[i] == array2[i]) {
                    result[i] = '0';
                } else {
                    result[i] = '1';
                }
            }

            return result;
        } else {

            char result[] = new char[4];

            for (int i = 0; i < 4; i++) {

                if (array1[i] == array2[i]) {
                    result[i] = '0';
                } else {
                    result[i] = '1';
                }

            }

            return result;

        }

    }
}
