package com.wealdtech.bitcoin;

import com.google.common.primitives.UnsignedLongs;

public class Utils
{
  public static long readUint32(final byte[] bytes, final int offset)
  {
    return ((bytes[offset] & 0xFFL) << 0) |
           ((bytes[offset + 1] & 0xFFL) << 8) |
           ((bytes[offset + 2] & 0xFFL) << 16) |
           ((bytes[offset + 3] & 0xFFL) << 24);
  }

  public static long readInt64(final byte[] bytes, final int offset)
  {
    return ((bytes[offset] & 0xFFL) << 0) |
           ((bytes[offset + 1] & 0xFFL) << 8) |
           ((bytes[offset + 2] & 0xFFL) << 16) |
           ((bytes[offset + 3] & 0xFFL) << 24) |
           ((bytes[offset + 4] & 0xFFL) << 32) |
           ((bytes[offset + 5] & 0xFFL) << 40) |
           ((bytes[offset + 6] & 0xFFL) << 48) |
           ((bytes[offset + 7] & 0xFFL) << 56);
  }

  public static void uint32ToByteArrayLE(final long val, final byte[] out, int offset)
  {
    out[offset + 0] = (byte)(0xFF & (val >> 0));
    out[offset + 1] = (byte)(0xFF & (val >> 8));
    out[offset + 2] = (byte)(0xFF & (val >> 16));
    out[offset + 3] = (byte)(0xFF & (val >> 24));
  }

  /**
   * Work around lack of unsigned types in Java.
   */
  public static boolean isLessThanUnsigned(long n1, long n2)
  {
    return UnsignedLongs.compare(n1, n2) < 0;
  }
}
