package com.wealdtech.bitcoin.generator.raw;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.google.common.primitives.UnsignedInteger;
import com.google.common.primitives.UnsignedLongs;

public class Utils
{
  /**
   * Turn a uint32-size value in to a little-endian byte array
   * @param val the value
   * @return the byte array representing the value
   */
  public static byte[] longToUint32LE(final long val)
  {
    byte[] res = new byte[4];
    res[0] = (byte)(0xFF & (val >> 0));
    res[1] = (byte)(0xFF & (val >> 8));
    res[2] = (byte)(0xFF & (val >> 16));
    res[3] = (byte)(0xFF & (val >> 24));
    return res;
  }

  /**
   * Turn a uint32-size value in to a big-endian byte array
   * @param val the value
   * @return the byte array representing the value
   */
  public static byte[] longToUint32BE(final long val)
  {
    byte[] res = new byte[4];
    res[0] = (byte)(0xFF & (val >> 24));
    res[1] = (byte)(0xFF & (val >> 16));
    res[2] = (byte)(0xFF & (val >> 8));
    res[3] = (byte)(0xFF & (val >> 0));
    return res;
  }

  /**
   * Turn a uint64-size value in to a little-endian byte array
   * @param val the value
   * @return the byte array representing the value
   */
  public static byte[] longToUint64LE(final long val)
  {
    byte[] res = new byte[8];
    res[0] = (byte)(0xFF & (val >> 0));
    res[1] = (byte)(0xFF & (val >> 8));
    res[2] = (byte)(0xFF & (val >> 16));
    res[3] = (byte)(0xFF & (val >> 24));
    res[4] = (byte)(0xFF & (val >> 32));
    res[5] = (byte)(0xFF & (val >> 40));
    res[6] = (byte)(0xFF & (val >> 48));
    res[7] = (byte)(0xFF & (val >> 56));
    return res;
  }

  /**
   * Convert a long to a Bitcoin VarInt hex string
   *
   * @param value
   *          a long
   * @return a byte array containing the characters for the hex string
   */
  public static byte[] longToVarintLE(long value)
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


  public static void uint32ToByteArrayLE(final long val, final byte[] out, final int offset)
  {
    out[offset + 0] = (byte)(0xFF & (val >> 0));
    out[offset + 1] = (byte)(0xFF & (val >> 8));
    out[offset + 2] = (byte)(0xFF & (val >> 16));
    out[offset + 3] = (byte)(0xFF & (val >> 24));
  }

  public static void uint32ToByteArrayBE(final long val, final byte[] out, final int offset)
  {
    out[offset + 0] = (byte) (0xFF & (val >> 24));
    out[offset + 1] = (byte) (0xFF & (val >> 16));
    out[offset + 2] = (byte) (0xFF & (val >> 8));
    out[offset + 3] = (byte) (0xFF & (val >> 0));
  }

  public static long readUint32BE(final byte[] bytes, final int offset)
  {
    return ((bytes[offset + 0] & 0xFFL) << 24) |
           ((bytes[offset + 1] & 0xFFL) << 16) |
           ((bytes[offset + 2] & 0xFFL) << 8) |
           ((bytes[offset + 3] & 0xFFL) << 0);
  }

  /**
   * Work around lack of unsigned types in Java.
   */
  public static boolean isLessThanUnsigned(final long n1, final long n2)
  {
    return UnsignedLongs.compare(n1, n2) < 0;
  }

  public static byte[] hexStringToBytes(final String input)
  {
    int len = input.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
        data[i / 2] = (byte) ((Character.digit(input.charAt(i), 16) << 4)
                             + Character.digit(input.charAt(i+1), 16));
    }
    return data;
  }

  /**
   * Convert a single byte to a hex string.
   *
   * @param input
   *          a byte
   * @return hex representation of the byte
   */
  public static String byteToHexString(final byte input)
  {
    return Integer.toHexString((input & 0xf0) >> 4) + Integer.toHexString((input & 0x0f) >> 0);
  }

  private static final MessageDigest digest;
  static
  {
    try
    {
      digest = MessageDigest.getInstance("SHA-256");
    }
    catch (NoSuchAlgorithmException e)
    {
      throw new RuntimeException(e); // Can't happen.
    }
  }

  /**
   * See {@link Utils#doubleDigest(byte[], int, int)}.
   */
  public static byte[] doubleDigest(byte[] input)
  {
    return doubleDigest(input, 0, input.length);
  }

  /**
   * Calculates the SHA-256 hash of the given byte range, and then hashes the
   * resulting hash again. This is standard procedure in Bitcoin. The resulting
   * hash is in big endian form.
   */
  public static byte[] doubleDigest(byte[] input, int offset, int length)
  {
    synchronized (digest)
    {
      digest.reset();
      digest.update(input, offset, length);
      byte[] first = digest.digest();
      return digest.digest(first);
    }
  }

  /**
   * Returns the given byte array hex encoded.
   */
  public static String bytesToHexString(byte[] bytes)
  {
    StringBuffer sb = new StringBuffer(bytes.length * 2);
    for (byte b : bytes)
    {
      String s = Integer.toString(0xFF & b, 16);
      if (s.length() == 1)
      {
        sb.append('0');
      }
      sb.append(s);
    }
    return sb.toString();
  }

  /**
   * Returns a copy of the given byte array in reverse order.
   */
  public static byte[] reverseBytes(byte[] bytes)
  {
    // We could use the XOR trick here but it's easier to understand if we
    // don't. If we find this is really a
    // performance issue the matter can be revisited.
    byte[] buf = new byte[bytes.length];
    for (int i = 0; i < bytes.length; i++)
      buf[i] = bytes[bytes.length - 1 - i];
    return buf;
  }

  /**
   * MPI encoded numbers are produced by the OpenSSL BN_bn2mpi function. They consist of
   * a 4 byte big endian length field, followed by the stated number of bytes representing
   * the number in big endian format (with a sign bit).
   * @param hasLength can be set to false if the given array is missing the 4 byte length field
   */
  public static BigInteger decodeMPI(byte[] mpi, boolean hasLength)
  {
    byte[] buf;
    if (hasLength)
    {
      int length = (int)readUint32BE(mpi, 0);
      buf = new byte[length];
      System.arraycopy(mpi, 4, buf, 0, length);
    }
    else
      buf = mpi;
    if (buf.length == 0)
      return BigInteger.ZERO;
    boolean isNegative = (buf[0] & 0x80) == 0x80;
    if (isNegative)
      buf[0] &= 0x7f;
    BigInteger result = new BigInteger(buf);
    return isNegative ? result.negate() : result;
  }

  /**
   * MPI encoded numbers are produced by the OpenSSL BN_bn2mpi function. They
   * consist of a 4 byte big endian length field, followed by the stated number
   * of bytes representing the number in big endian format (with a sign bit).
   *
   * @param includeLength
   *          indicates whether the 4 byte length field should be included
   */
  public static byte[] encodeMPI(BigInteger value, boolean includeLength)
  {
    if (value.equals(BigInteger.ZERO))
    {
      if (!includeLength)
        return new byte[] {};
      else
        return new byte[] {0x00,
                           0x00,
                           0x00,
                           0x00};
    }
    boolean isNegative = value.compareTo(BigInteger.ZERO) < 0;
    if (isNegative)
      value = value.negate();
    byte[] array = value.toByteArray();
    int length = array.length;
    if ((array[0] & 0x80) == 0x80)
      length++;
    if (includeLength)
    {
      byte[] result = new byte[length + 4];
      System.arraycopy(array, 0, result, length - array.length + 3, array.length);
      uint32ToByteArrayBE(length, result, 0);
      if (isNegative)
        result[4] |= 0x80;
      return result;
    }
    else
    {
      byte[] result;
      if (length != array.length)
      {
        result = new byte[length];
        System.arraycopy(array, 0, result, 1, array.length);
      }
      else
        result = array;
      if (isNegative)
        result[0] |= 0x80;
      return result;
    }
  }


  /**
   * Write out an array of bytes, prepending the length using a VarInt
   * @param baos the stream to which to write the bytes
   * @param data the bytes
   */
  public static void writeBytesWithLength(final ByteArrayOutputStream baos, final byte[] data) throws IOException
  {
    baos.write(Utils.longToVarintLE(data.length));
    baos.write(data);
  }
}
