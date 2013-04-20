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
   * An address is a RIPEMD160 hash of a public key, therefore is always 160
   * bits or 20 bytes.
   */
  public static final int LENGTH = 20;

  /**
   * Construct an address from parameters and the hash160 form. Example:
   * <p>
   *
   * <pre>
   * new Address(NetworkParameters.prodNet(), Hex.decode(&quot;4a22c3c4cbb31e4d03b15550636762bda0baf85a&quot;));
   * </pre>
   */
  public Address(final byte[] hash160)
  {
    super(hash160);
    if (hash160.length != 20) // 160 = 8 * 20
    {
      throw new RuntimeException("Addresses are 160-bit hashes, so you must provide 20 bytes");
    }
  }

  /**
   * Construct an address from parameters and the standard "human readable"
   * form. Example:
   * <p>
   *
   * <pre>
   * new Address(NetworkParameters.prodNet(), &quot;17kzeh4N8g49GFvdDzSf8PjaPfyoD1MndL&quot;);
   * </pre>
   * <p>
   * @param address
   *          The textual form of the address, such as
   *          "17kzeh4N8g49GFvdDzSf8PjaPfyoD1MndL"
   *
   */
  public Address(final String address)
  {
    super(address);
  }


  /** The (big endian) 20 byte hash that is the core of a Bitcoin address. */
  public byte[] getHash160()
  {
    return this.bytes;
  }
}
