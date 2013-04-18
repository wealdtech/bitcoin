package com.wealdtech.bitcoin;

import com.google.common.primitives.UnsignedInteger;

/**
 * Handle Bitcoin variable-length integers.
 * {@link https://en.bitcoin.it/wiki/Protocol_specification#Variable_length_integer}
 * This class contains helper functions to allow translation in and out of
 */
public class VarInt
{
  public final long value;
  private final int originallyEncodedSize;

  public VarInt(final long value)
  {
    this.value = value;
    this.originallyEncodedSize = getSizeInBytes();
  }

  public VarInt(byte[] buf, int offset)
  {
    int first = 0xFF & buf[offset];
    long val;
    if (first < 253)
    {
      // 8 bits.
      val = first;
      this.originallyEncodedSize = 1;
    }
    else if (first == 253)
    {
      // 16 bits.
      val = (0xFF & buf[offset + 1]) | ((0xFF & buf[offset + 2]) << 8);
      this.originallyEncodedSize = 3;
    }
    else if (first == 254)
    {
      // 32 bits.
      val = Utils.readUint32(buf, offset + 1);
      this.originallyEncodedSize = 5;
    }
    else
    {
      // 64 bits.
      val = Utils.readUint32(buf, offset + 1) | (Utils.readUint32(buf, offset + 5) << 32);
      this.originallyEncodedSize = 9;
    }
    this.value = val;
  }

  /**
   * Gets the number of bytes used to encode this originally if deserialized
   * from a byte array. Otherwise returns the minimum encoded size
   */
  public int getOriginalSizeInBytes()
  {
    return this.originallyEncodedSize;
  }

  /**
   * Gets the minimum encoded size of the value stored in this VarInt
   */
  public int getSizeInBytes()
  {
    return sizeOf(this.value);
  }

  /**
   * Gets the minimum encoded size of the given value.
   */
  public static int sizeOf(final int value)
  {
    if (value < 253)
    {
      return 1;
    }
    else if (value < 65536)
    {
      return 3; // 1 marker + 2 data bytes
    }
    return 5; // 1 marker + 4 data bytes
  }

  /**
   * Gets the minimum encoded size of the given value.
   */
  public static int sizeOf(final long value)
  {
    if (Utils.isLessThanUnsigned(value, 253))
    {
      return 1;
    }
    else if (Utils.isLessThanUnsigned(value, 65536))
    {
      return 3; // 1 marker + 2 data bytes
    }
    else if (Utils.isLessThanUnsigned(value, UnsignedInteger.MAX_VALUE.longValue()))
    {
      return 5; // 1 marker + 4 data bytes
    }
    else
    {
      return 9; // 1 marker + 8 data bytes
    }
  }

  public byte[] encode()
  {
    return encodeBE();
  }

  public byte[] encodeBE()
  {
    if (Utils.isLessThanUnsigned(value, 253))
    {
      return new byte[] {(byte)value};
    }
    else if (Utils.isLessThanUnsigned(value, 65536))
    {
      return new byte[] {(byte)253,
                         (byte)(value),
                         (byte)(value >> 8)};
    }
    else if (Utils.isLessThanUnsigned(value, UnsignedInteger.MAX_VALUE.longValue()))
    {
      byte[] bytes = new byte[5];
      bytes[0] = (byte)254;
      Utils.uint32ToByteArrayLE(value, bytes, 1);
      return bytes;
    }
    else
    {
      byte[] bytes = new byte[9];
      bytes[0] = (byte)255;
      Utils.uint32ToByteArrayLE(value, bytes, 1);
      Utils.uint32ToByteArrayLE(value >>> 32, bytes, 5);
      return bytes;
    }
  }
}
