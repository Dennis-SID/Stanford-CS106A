package com.cscourse.week13.dsidelnik.assignment13;

import java.nio.ByteBuffer;

public class SandBox {

    public static void main(String[] args) {
        long i = 1997555665;

        byte[] one = stackOverflovOption(i);
        byte[] two = longToByteArray(i);

        for (Byte b : one) {
            System.out.print(" " + Long.toBinaryString(b));
        }

        System.out.println("");

        for (Byte b : two) {
            System.out.print(" " + Long.toBinaryString(b));
        }
        System.out.println();

        System.out.println(54321 + 12345);
    }


    public static byte[] stackOverflovOption(long i) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(i);
        return buffer.array();
    }

    public static byte[] longToByteArray(long i) {
        return new byte[]{
                (byte) (i >>> 56),
                (byte) (i >>> 48),
                (byte) (i >>> 40),
                (byte) (i >>> 32),
                (byte) (i >>> 24),
                (byte) (i >>> 16),
                (byte) (i >>> 8),
                (byte) (i)
        };
    }

    public static byte[] intToByteArray(int i) {
        return new byte[]{
                (byte) (i >>> 24),
                (byte) (i >>> 16),
                (byte) (i >>> 8),
                (byte) (i)
        };
    }
}
