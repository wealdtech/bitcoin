package com.wealdtech.bitcoin.generator.raw;

import java.nio.ByteBuffer;

import com.google.common.primitives.UnsignedInteger;
import com.google.common.primitives.UnsignedLongs;

public class Utils
{
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

  /**
   * convert a bytebuffer to a hex string.
   * @param input the bytebuffer
   * @return the hex string
   */
  public static String byteBufferToHexString(final ByteBuffer input)
  {
    final int size = input.limit();
    final StringBuffer sb = new StringBuffer(size);
    for (int cur = 0; cur < size; cur++)
    {
      sb.append(byteToHexString(input.get(cur)));
    }
    return sb.toString();
  }

  /**
   * Convert a single byte to a hex string.
   * @param input a byte
   * @return hex representation of the byte
   */
  public static String byteToHexString(final byte input)
  {
    return Integer.toHexString((input&0xf0)>>4) + Integer.toHexString((input&0x0f)>>0);
  }

  /**
   * Convert a long to a Bitcoin VarInt hex string
   * @param value a long
   * @return a byte array containing the characters for the hex string
   */
  public static byte[] longToVarintHexChars(long value)
  {
    if (isLessThanUnsigned(value, 253))
    {
      return new byte[] {(byte)value};
    }
    else if (isLessThanUnsigned(value, 65536))
    {
      return new byte[] {(byte)253,
                         (byte)(value),
                         (byte)(value >> 8)};
    }
    else if (isLessThanUnsigned(value, UnsignedInteger.MAX_VALUE.longValue()))
    {
      byte[] bytes = new byte[5];
      bytes[0] = (byte)254;
      uint32ToByteArrayLE(value, bytes, 1);
      return bytes;
    }
    else
    {
      byte[] bytes = new byte[9];
      bytes[0] = (byte)255;
      uint32ToByteArrayLE(value, bytes, 1);
      uint32ToByteArrayLE(value >>> 32, bytes, 5);
      return bytes;
    }
  }
}
