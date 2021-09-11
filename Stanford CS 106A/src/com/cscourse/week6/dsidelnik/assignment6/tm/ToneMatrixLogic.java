package com.cscourse.week6.dsidelnik.assignment6.tm;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] resultArray = new double[ToneMatrixConstants.sampleSize()];
        int netSize = ToneMatrixConstants.size();

        for (int row = 0; row < netSize; row++) {
            if (toneMatrix[row][column]) {
                for (int col = 0; col < resultArray.length; col++) {
                    resultArray[col] += samples[row][col];
                }
            }
        }


        return normalizeSound(resultArray);
    }

    /**
     * Normilizes wava of the sound
     * @param array process array
     * @return normalized array
     */
    private static double[] normalizeSound(double [] array) {

        for (int i = 0; i < array.length; i++) {
            if ((array[i] > 1) || array[i] < -1) {
                for (int j = 0; j < array.length; j++) {
                    array[j] = array[j] / array[i];
                }
            }
        }
        return array;
    }
}
