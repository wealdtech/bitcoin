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
package com.wealdtech.bitcoin;

import static com.wealdtech.Preconditions.checkNotNull;

import java.util.Arrays;

import com.wealdtech.bitcoin.crypto.Hash;
import com.wealdtech.bitcoin.generator.raw.Utils;
import com.wealdtech.bitcoin.utils.Base58;

/**
 * <p>
 * In Bitcoin the following format is often used to represent some type of key:
 * </p>
 * <p/>
 *
 * <pre>
 * [one version byte] [hash] [4 checksum bytes]
 * </pre>
 * <p/>
 * <p>
 * and the result is then Base58 encoded. This format is used for addresses, and
 * private keys exported using the dumpprivkey command.
 * </p>
 */
public class BitcoinKey
{
  Hash hash;
  byte header;
  protected byte[] bytes;

  protected BitcoinKey(final Hash hash)
  {
    this.hash = hash;
  }

  protected BitcoinKey(final String encoded)
  {
    checkNotNull(encoded, "Address is required");
    byte[] tmp = Base58.decodeChecked(encoded);
    this.header = tmp[0];
    this.bytes = new byte[tmp.length - 1];
    System.arraycopy(tmp, 1, this.bytes, 0, tmp.length - 1);
  }

  protected BitcoinKey(final byte[] bytes)
  {
    this.bytes = bytes;
  }

  /**
   * Check to see if this address is for a specific network
   * @param network the network
   * @return <code>true</code> if this address is valid on the given network, otherwise <code>false</code>
   */
  public boolean isForNetwork(final Network network)
  {
    boolean found = false;
    for (byte header : network.getValidAddressHeaders())
    {
      if (this.header == header)
      {
        found = true;
        break;
      }
    }
    return found;
  }

  @Override
  public String toString()
  {
    byte[] addressBytes = new byte[1 + this.bytes.length + 4];
    addressBytes[0] = this.header;
    System.arraycopy(this.bytes, 0, addressBytes, 1, this.bytes.length);
    byte[] check = Utils.doubleDigest(addressBytes, 0, this.bytes.length + 1);
    System.arraycopy(check, 0, addressBytes, this.bytes.length + 1, 4);
    return Base58.encode(addressBytes);
  }

  @Override
  public int hashCode()
  {
    return Arrays.hashCode(this.bytes);
  }

  @Override
  public boolean equals(Object o)
  {
    if (!(o instanceof BitcoinKey))
      return false;
    BitcoinKey bk = (BitcoinKey)o;
    return Arrays.equals(bk.bytes, this.bytes);
  }
}
