package com.wealdtech.bitcoin.crypto;

import static com.wealdtech.Preconditions.checkArgument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import com.google.common.base.Objects;
import com.google.common.io.BaseEncoding;
import com.google.common.io.ByteStreams;
import com.wealdtech.bitcoin.generator.raw.Utils;

public class Sha256Hash implements Serializable, Comparable<Sha256Hash>
{
  public static final Sha256Hash ZERO_HASH = new Sha256Hash(new byte[32]);

  private static final BaseEncoding HEX = BaseEncoding.base16().lowerCase();

  private final byte[] hash;

  /**
   * Creates a Sha256Hash by wrapping the given byte array. It must be 32 bytes
   * long.
   */
  public Sha256Hash(final byte[] rawHashBytes)
  {
    checkArgument(rawHashBytes.length == 32, "Input must be 32 bytes long");
    this.hash = rawHashBytes;
  }

  /**
   * Creates a Sha256Hash by decoding the given hex string. It must be 64
   * characters long.
   */
  public Sha256Hash(final String hexString)
  {
    checkArgument(hexString.length() == 64, "Input must be 64 characters long");
    this.hash = HEX.decode(hexString);
  }

  /**
   * Calculates the (one-time) hash of contents and returns it as a new wrapped
   * hash.
   */
  public static Sha256Hash create(byte[] contents)
  {
    try
    {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      return new Sha256Hash(digest.digest(contents));
    }
    catch (NoSuchAlgorithmException e)
    {
      throw new RuntimeException(e); // Cannot happen.
    }
  }

  /**
   * Calculates the hash of the hash of the contents. This is a standard
   * operation in Bitcoin.
   */
  public static Sha256Hash createDouble(byte[] contents)
  {
    return new Sha256Hash(Utils.doubleDigest(contents));
  }

  /**
   * Returns a hash of the given files contents. Reads the file fully into
   * memory before hashing so only use with small files.
   *
   * @throws IOException
   */
  public static Sha256Hash hashFileContents(File f) throws IOException
  {
    FileInputStream in = new FileInputStream(f);
    try
    {
      return create(ByteStreams.toByteArray(in));
    }
    finally
    {
      in.close();
    }
  }

  /**
   * Returns the bytes interpreted as a positive integer.
   */
  public BigInteger toBigInteger()
  {
    return new BigInteger(1, this.hash);
  }

  public byte[] getHash()
  {
    return this.hash;
  }

  public Sha256Hash duplicate()
  {
    return new Sha256Hash(this.hash);
  }

  // Standard object methods follow
  @Override
  public String toString()
  {
    return Objects.toStringHelper(this)
                  .add("hash", Utils.bytesToHexString(this.hash))
                  .toString();
  }

  @Override
  public boolean equals(final Object that)
  {
    return (that instanceof Sha256Hash) && (this.compareTo((Sha256Hash)that) == 0);
  }

  /**
   * Hash code of the byte array as calculated by {@link Arrays#hashCode()}.
   * Note the difference between a SHA256 secure bytes and the type of
   * quick/dirty bytes used by the Java hashCode method which is designed for
   * use in bytes tables.
   */
  @Override
  public int hashCode()
  {
    // Use the last 4 bytes, not the first 4 which are often zeros in Bitcoin.
    return (this.hash[31] & 0xFF) |((this.hash[30] & 0xFF) << 8) | ((this.hash[29] & 0xFF) << 16) | ((this.hash[28] & 0xFF) << 24);
  }

  @Override
  public int compareTo(final Sha256Hash that)
  {
    for (int i = 0; i < 32; i++)
    {
      if (this.hash[i] < that.hash[i])
      {
        return -1;
      }
      if (this.hash[i] > that.hash[i])
      {
        return 1;
      }
    }
    return 0;
  }
}
