package com.cscourse.week13.dsidelnik.assignment13;

import java.nio.ByteBuffer;

public class Archiver {


    /**
     * Returns byte array of long value
     */
    private byte[] longToByteArray(long value) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Long.BYTES);
        byteBuffer.putLong(value);
        return byteBuffer.array();
    }

    /**
     * Returns byte array of int values
     */
    private byte[] intToByteArray(int value) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Long.BYTES);
        byteBuffer.putInt(value);
        return byteBuffer.array();
    }

}
