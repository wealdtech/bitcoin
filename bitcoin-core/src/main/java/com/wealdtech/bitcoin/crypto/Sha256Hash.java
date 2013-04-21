/*
 *    Copyright 2013 Weald Technology Trading Limited
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.wealdtech.bitcoin.crypto;

import static com.wealdtech.Preconditions.checkArgument;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import com.google.common.base.Objects;
import com.wealdtech.bitcoin.generator.raw.Utils;

public class Sha256Hash implements Hash
{
  private static final long serialVersionUID = -5296267658252547109L;

  private final byte[] hash;

  /**
   * Instantiates a pre-computed SHA-256 hash.
   * @param rawHashBytes the pre-computed hash
   */
  public Sha256Hash(final byte[] rawHashBytes)
  {
    checkArgument(rawHashBytes.length == 32, "Input must be 32 bytes long");
    this.hash = rawHashBytes;
  }

  /**
   * Instantiates a pre-computed SHA-256 hash.
   * @param hexString the hash in the form of a hex string
   */
  public static Sha256Hash fromHexString(final String hexString)
  {
    checkArgument(hexString.length() == 64, "Input must be 64 characters long");
    return new Sha256Hash(Utils.hexStringToBytes(hexString));
  }

  /**
   * Instantiate a new SHA-256 hash for a given set of data
   * @param contents the data for which to compute the hash
   */
  public static Sha256Hash create(final byte[] data)
  {
    try
    {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      return new Sha256Hash(digest.digest(data));
    }
    catch (NoSuchAlgorithmException e)
    {
      throw new RuntimeException(e); // Cannot happen.
    }
  }

  @Override
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
  public int compareTo(final Hash that)
  {
    if (!(that instanceof Sha256Hash))
    {
      return -1;
    }
    for (int i = 0; i < 32; i++)
    {
      if (this.hash[i] < that.getHash()[i])
      {
        return -1;
      }
      if (this.hash[i] > that.getHash()[i])
      {
        return 1;
      }
    }
    return 0;
  }
}
