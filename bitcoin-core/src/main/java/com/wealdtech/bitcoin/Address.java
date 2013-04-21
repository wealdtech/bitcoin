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

import javax.annotation.Nullable;

import com.wealdtech.bitcoin.crypto.Hash;
import com.wealdtech.bitcoin.crypto.Ripemd160Hash;
import com.wealdtech.bitcoin.utils.Base58;

/**
 * <p>
 * A Bitcoin address looks like 1MsScoe2fTJoq4ZPdQgqyhgWeoNamYPevy and is
 * derived from an elliptic curve public key plus a set of network parameters.
 * Not to be confused with a {@link PeerAddress} or {@link AddressMessage} which
 * are about network (TCP) addresses.
 * </p>
 *
 * <p>
 * A standard address is built by taking the RIPE-MD160 hash of the public key
 * bytes, with a version prefix and a checksum suffix, then encoding it
 * textually as base58. The version prefix is used to both denote the network
 * for which the address is valid (see {@link NetworkParameters}, and also to
 * indicate how the bytes inside the address should be interpreted. Whilst
 * almost all addresses today are hashes of public keys, another (currently
 * unsupported type) can contain a hash of a script instead.
 * </p>
 */
public class Address extends BitcoinKey
{
  /**
   * Instantiate an address given a standard Bitcoin-format string
   * @param input the address string representation
   * @return an address
   */
  // FIXME sort this method
  public static Address fromAddressString(final String input)
  {
    final byte[] tmp = Base58.decodeChecked(input);
    final Network network = Network.fromVersion((int)tmp[0]);
    final byte[] fred = new byte[tmp.length - 1];
    System.arraycopy(tmp, 1, fred, 0, tmp.length - 1);
    final Hash hash = new Ripemd160Hash(fred);
    return new Address(network, hash);
  }

  /**
   * Instantiate an address given the hash of a public key
   * <p>
   *
   * <pre>
   * new Address(NetworkParameters.prodNet(), Hex.decode(&quot;4a22c3c4cbb31e4d03b15550636762bda0baf85a&quot;));
   * </pre>
   */
  public static Address fromHash(@Nullable final Network network, final Ripemd160Hash hash)
  {
    return new Address(network, hash);
  }

  /** The (big endian) 20 byte hash that is the core of a Bitcoin address. */
  public byte[] getHash160()
  {
    return this.bytes;
  }
}
