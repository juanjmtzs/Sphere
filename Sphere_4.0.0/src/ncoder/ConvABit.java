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

import java.util.*;

public class ConvABit extends BitSet {

    public ConvABit(int size) {
        super(size);
    }

    public char[] bitSetToCharArray(int numberOfBitsFromLeft) {

        char theBitSet[] = new char[numberOfBitsFromLeft];

        for (int i = 0; i < numberOfBitsFromLeft; i++) {
            if (super.get(i)) {
                theBitSet[i] = '1';
            } else {
                theBitSet[i] = '0';
            }
        }

        return theBitSet;
    }

    public void initializeAccordingToCharArray(char a[]) {

        for (int i = 0; i < a.length; i++) {
            if (a[i] == '1') {
                this.set(i);
            } else if (a[i] == '0') {
                this.clear(i);
            }
        }
    }

    public ConvABit LS_1(int numberOfBitsFromLeft) {

        char oldBitSet[] = this.bitSetToCharArray(numberOfBitsFromLeft);
        char newBitSet[] = new char[oldBitSet.length];

        newBitSet[newBitSet.length - 1] = oldBitSet[0];

        for (int i = 0; i < newBitSet.length - 1; i++) {
            newBitSet[i] = oldBitSet[i + 1];
        }

        ConvABit shiftedSet = new ConvABit(newBitSet.length);
        shiftedSet.initializeAccordingToCharArray(newBitSet);

        return shiftedSet;
    }

    public ConvABit LS_2(int numberOfBitsFromLeft) {

        ConvABit firstShift = new ConvABit(numberOfBitsFromLeft);
        ConvABit secondShift = new ConvABit(numberOfBitsFromLeft);

        firstShift = this.LS_1(numberOfBitsFromLeft);
        secondShift = firstShift.LS_1(numberOfBitsFromLeft);

        return secondShift;
    }

    public ConvABit LS_3(int numberOfBitsFromLeft) {

        ConvABit secondShift = new ConvABit(numberOfBitsFromLeft);
        ConvABit thirdShift = new ConvABit(numberOfBitsFromLeft);

        secondShift = this.LS_2(numberOfBitsFromLeft);
        thirdShift = secondShift.LS_2(numberOfBitsFromLeft);

        return thirdShift;
    }

    public ConvABit LS_4(int numberOfBitsFromLeft) {

        ConvABit thirdShift = new ConvABit(numberOfBitsFromLeft);
        ConvABit fourthShift = new ConvABit(numberOfBitsFromLeft);

        thirdShift = this.LS_3(numberOfBitsFromLeft);
        fourthShift = thirdShift.LS_3(numberOfBitsFromLeft);

        return fourthShift;
    }

    public ConvABit LS_5(int numberOfBitsFromLeft) {

        ConvABit fourthShift = new ConvABit(numberOfBitsFromLeft);
        ConvABit fifthShift = new ConvABit(numberOfBitsFromLeft);

        fourthShift = this.LS_4(numberOfBitsFromLeft);
        fifthShift = fourthShift.LS_4(numberOfBitsFromLeft);

        return fifthShift;
    }

    public ConvABit LS_6(int numberOfBitsFromLeft) {

        ConvABit fifthShift = new ConvABit(numberOfBitsFromLeft);
        ConvABit sixthShift = new ConvABit(numberOfBitsFromLeft);

        fifthShift = this.LS_5(numberOfBitsFromLeft);
        sixthShift = fifthShift.LS_5(numberOfBitsFromLeft);

        return sixthShift;
    }

    public ConvABit LS_7(int numberOfBitsFromLeft) {

        ConvABit sixthShift = new ConvABit(numberOfBitsFromLeft);
        ConvABit seventhShift = new ConvABit(numberOfBitsFromLeft);

        sixthShift = this.LS_6(numberOfBitsFromLeft);
        seventhShift = sixthShift.LS_6(numberOfBitsFromLeft);

        return seventhShift;
    }

    public ConvABit LS_8(int numberOfBitsFromLeft) {

        ConvABit seventhShift = new ConvABit(numberOfBitsFromLeft);
        ConvABit eighthShift = new ConvABit(numberOfBitsFromLeft);

        seventhShift = this.LS_7(numberOfBitsFromLeft);
        eighthShift = seventhShift.LS_7(numberOfBitsFromLeft);

        return eighthShift;
    }

    public ConvABit LS_9(int numberOfBitsFromLeft) {

        ConvABit eighthShift = new ConvABit(numberOfBitsFromLeft);
        ConvABit ninthShift = new ConvABit(numberOfBitsFromLeft);

        eighthShift = this.LS_8(numberOfBitsFromLeft);
        ninthShift = eighthShift.LS_8(numberOfBitsFromLeft);

        return ninthShift;
    }

    public ConvABit LS_10(int numberOfBitsFromLeft) {

        ConvABit ninthShift = new ConvABit(numberOfBitsFromLeft);
        ConvABit tenthShift = new ConvABit(numberOfBitsFromLeft);

        ninthShift = this.LS_9(numberOfBitsFromLeft);
        tenthShift = ninthShift.LS_9(numberOfBitsFromLeft);

        return tenthShift;
    }

    public ConvABit LS_11(int numberOfBitsFromLeft) {

        ConvABit tenthShift = new ConvABit(numberOfBitsFromLeft);
        ConvABit eleventhShift = new ConvABit(numberOfBitsFromLeft);

        tenthShift = this.LS_10(numberOfBitsFromLeft);
        eleventhShift = tenthShift.LS_10(numberOfBitsFromLeft);

        return eleventhShift;
    }

    public ConvABit LS_12(int numberOfBitsFromLeft) {

        ConvABit eleventhShift = new ConvABit(numberOfBitsFromLeft);
        ConvABit twelfthShift = new ConvABit(numberOfBitsFromLeft);

        eleventhShift = this.LS_11(numberOfBitsFromLeft);
        twelfthShift = eleventhShift.LS_11(numberOfBitsFromLeft);

        return twelfthShift;
    }

    public ConvABit LS_13(int numberOfBitsFromLeft) {

        ConvABit twelfthShift = new ConvABit(numberOfBitsFromLeft);
        ConvABit thirteenthShift = new ConvABit(numberOfBitsFromLeft);

        twelfthShift = this.LS_12(numberOfBitsFromLeft);
        thirteenthShift = twelfthShift.LS_12(numberOfBitsFromLeft);

        return thirteenthShift;
    }

    public ConvABit LS_14(int numberOfBitsFromLeft) {

        ConvABit thirteenthShift = new ConvABit(numberOfBitsFromLeft);
        ConvABit fourteenthShift = new ConvABit(numberOfBitsFromLeft);

        thirteenthShift = this.LS_13(numberOfBitsFromLeft);
        fourteenthShift = thirteenthShift.LS_13(numberOfBitsFromLeft);

        return fourteenthShift;
    }

    public ConvABit LS_15(int numberOfBitsFromLeft) {

        ConvABit fourteenthShift = new ConvABit(numberOfBitsFromLeft);
        ConvABit fifteenthShift = new ConvABit(numberOfBitsFromLeft);

        fourteenthShift = this.LS_14(numberOfBitsFromLeft);
        fifteenthShift = fourteenthShift.LS_14(numberOfBitsFromLeft);

        return fifteenthShift;
    }

    public ConvABit LS_16(int numberOfBitsFromLeft) {

        ConvABit fifteenthShift = new ConvABit(numberOfBitsFromLeft);
        ConvABit sixteenthShift = new ConvABit(numberOfBitsFromLeft);

        fifteenthShift = this.LS_15(numberOfBitsFromLeft);
        sixteenthShift = fifteenthShift.LS_15(numberOfBitsFromLeft);

        return sixteenthShift;
    }

}
