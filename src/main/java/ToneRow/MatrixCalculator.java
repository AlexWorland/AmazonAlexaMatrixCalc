package ToneRow;/*
 * Author: Alex Worland
 * Date:
 * Description:
 */

import java.util.Scanner;

public class MatrixCalculator {

    public static String[][] getMatrix(int[] rowNums) {
        // TODO code application logic here


        Scanner keyboard = new Scanner(System.in);
//      String[] primeRow = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
        String[] invertedRow = new String[12];
        String[][] matrixAnswer = new String[12][12];
        int[] invertedNums = new int[12];
        int[] primeIntervals = new int[11];

//        for (int i = 0; i < 12; i++) {
//            System.out.println("Please Enter Note #" + (i + 1));
//            primeRow[i] = keyboard.nextLine();
//        }

//        primeNums = getRowNums(primeRow);
        primeIntervals = getPrimeIntervals(rowNums);
//        invertedNums = getInvertedNums(primeNums);
        invertedNums = getInvertedRow(rowNums, primeIntervals);
        matrixAnswer = calculateRowMatrix(invertedNums, primeIntervals);
//        displayAnswer(matrixAnswer);
        ToneRowUtils.getSpeechletResponse("Matrix Calculated. What row do you want?", "What row do you want?", true);
        return matrixAnswer;
    }

    public static int[] getRowNums(String[] incomingRow) {
        int[] rowNums = new int[12];
        for (int i = 0; i < 12; i++) {
            if (incomingRow[i].equals("C")) {
                rowNums[i] = 0;
            }
            if (incomingRow[i].equals("C#") || incomingRow[i].equals("Db")) {
                rowNums[i] = 1;
            }
            if (incomingRow[i].equals("D")) {
                rowNums[i] = 2;
            }
            if (incomingRow[i].equals("D#") || incomingRow[i].equals("Eb")) {
                rowNums[i] = 3;
            }
            if (incomingRow[i].equals("E")) {
                rowNums[i] = 4;
            }
            if (incomingRow[i].equals("F")) {
                rowNums[i] = 5;
            }
            if (incomingRow[i].equals("F#") || incomingRow[i].equals("Gb")) {
                rowNums[i] = 6;
            }
            if (incomingRow[i].equals("G")) {
                rowNums[i] = 7;
            }
            if (incomingRow[i].equals("Ab") || incomingRow[i].equals("G#")) {
                rowNums[i] = 8;
            }
            if (incomingRow[i].equals("A")) {
                rowNums[i] = 9;
            }
            if (incomingRow[i].equals("A#") || incomingRow[i].equals("Bb")) {
                rowNums[i] = 10;
            }
            if (incomingRow[i].equals("B")) {
                rowNums[i] = 11;
            }
        }
        return rowNums;
    }

//    public  String[] getRowNames(int[] incomingNums) {
//        String[] rowNames = new String[12];
//        for (int i = 0; i < 12; i++) {
//            if (incomingNums[i] == 0) {
//                rowNames[i] = "C";
//            }
//            if (incomingNums[i] == 1) {
//                rowNames[i] = "C#";
//            }
//            if (incomingNums[i] == 2) {
//                rowNames[i] = "D";
//            }
//            if (incomingNums[i] == 3) {
//                rowNames[i] = "D#";
//            }
//            if (incomingNums[i] == 4) {
//                rowNames[i] = "E";
//            }
//            if (incomingNums[i] == 5) {
//                rowNames[i] = "F";
//            }
//            if (incomingNums[i] == 6) {
//                rowNames[i] = "F#";
//            }
//            if (incomingNums[i] == 7) {
//                rowNames[i] = "G";
//            }
//            if (incomingNums[i] == 8) {
//                rowNames[i] = "G#";
//            }
//            if (incomingNums[i] == 9) {
//                rowNames[i] = "A";
//            }
//            if (incomingNums[i] == 10) {
//                rowNames[i] = "A#";
//            }
//            if (incomingNums[i] == 11) {
//                rowNames[i] = "B";
//            }
//
//        }
//        return rowNames;
//    }

    public static String getNoteName(int incomingNoteNum) {
        String noteName = "";

        incomingNoteNum = Math.abs(incomingNoteNum);

        if (incomingNoteNum >= 12) {
            incomingNoteNum = incomingNoteNum % 12;
        }

        if (incomingNoteNum == 0) {
            noteName = "C";
        }
        if (incomingNoteNum == 1) {
            noteName = "C#";
        }
        if (incomingNoteNum == 2) {
            noteName = "D";
        }
        if (incomingNoteNum == 3) {
            noteName = "D#";
        }
        if (incomingNoteNum == 4) {
            noteName = "E";
        }
        if (incomingNoteNum == 5) {
            noteName = "F";
        }
        if (incomingNoteNum == 6) {
            noteName = "F#";
        }
        if (incomingNoteNum == 7) {
            noteName = "G";
        }
        if (incomingNoteNum == 8) {
            noteName = "G#";
        }
        if (incomingNoteNum == 9) {
            noteName = "A";
        }
        if (incomingNoteNum == 10) {
            noteName = "A#";
        }
        if (incomingNoteNum == 11) {
            noteName = "B";
        }

        return noteName;
    }

    public static int[] getPrimeIntervals(int[] incomingPrimeNums) {
        int[] primeIntervals = new int[11];
        for (int i = 0, p = 1; i < 11; i++, p++) {
            primeIntervals[i] = incomingPrimeNums[p] - incomingPrimeNums[i];
        }
        return primeIntervals;
    }

//    public  int[] getInvertedNums(int[] primeNums){
//        int[] invertedNums = new int[12];
//
//        for (int i = 0; i < 12; i++) {
//            invertedNums[i] = (12 - primeNums[i]);
//        }
//        return invertedNums;
//    }

    public static int[] getInvertedRow(int[] primeNums, int[] primeIntervals){
        String[] invertedRow = new String[12];
        int[] invertedRowInt = new int[12];
        invertedRowInt[0] = primeNums[0];


        for (int i = 0; i < primeIntervals.length; i++) {
            invertedRowInt[i + 1] = invertedRowInt[i] - primeIntervals[i];
//            invertedRow[i] = getNoteName(invertedRowInt[i]);
        }

        return invertedRowInt;
    }

    public static String[][] calculateRowMatrix(int[] invertedRowNums, int[] primeIntervals){
        int[][] numMatrix = new int[12][12];
        String[][] stringMatrix = new String[12][12];
        int[] subArray = new int[12];

        for (int i = 0; i < 12; i++) {
            numMatrix[i][0] = invertedRowNums[i];
        }

        for (int p = 0; p < 12; p++){
            for (int i = 0; i < 11; i++){
                numMatrix[p][i + 1] = (numMatrix[p][i] + primeIntervals[i]);
            }
        }

        for (int p = 0; p < 12; p++){
            for (int i = 0; i < 12; i++){
                subArray[i] = numMatrix[p][i];
                stringMatrix[p][i] = getNoteName(subArray[i]);
            }
        }

        return stringMatrix;
    }

//    public static void displayAnswer(String[][] result) {
//        System.out.println("The Prime Row Is...");
//
//        for (int p = 0; p < 12; p++){
//            for (int i = 0; i < 12; i++){
//                System.out.print(result[p][i] + " ");
//            }
//            System.out.println();
//        }
//    }
}
