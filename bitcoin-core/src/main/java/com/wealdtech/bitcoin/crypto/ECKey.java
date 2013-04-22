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

import static com.wealdtech.Preconditions.checkNotNull;

import java.io.Serializable;
import java.math.BigInteger;

import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.math.ec.ECPoint;

import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Bytes;
import com.wealdtech.bitcoin.Address;
import com.wealdtech.bitcoin.Network;
import com.wealdtech.bitcoin.generator.raw.Utils;
import com.wealdtech.bitcoin.utils.Base58;

public class ECKey implements Serializable
{
  private static final long serialVersionUID = 9161326405924378230L;

  private static final ECDomainParameters ecParams;

  private final BigInteger pubKey;
  private final BigInteger privKey;

  static
  {
    // Set up EC parameters
    final X9ECParameters params = SECNamedCurves.getByName("secp256k1");
    ecParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());
  }

  /**
   * Create a key pair given the private key
   * @param privKey the private key
   */
  public ECKey(final BigInteger privKey)
  {
    checkNotNull(privKey, "Private key is required");
    this.privKey = privKey;

    // Calculate public key from the private key
    final ECPoint point = ecParams.getG().multiply(privKey);
    System.err.println("Public key is " + Utils.bytesToHexString(point.getEncoded()));
    // Compress it
    ECPoint compressedPoint = new ECPoint.Fp(ecParams.getCurve(), point.getX(), point.getY(), true);
    System.err.println("Compressed public key is " + Utils.bytesToHexString(compressedPoint.getEncoded()));
    // FIXME to compress or not to compress?
//    this.pubKey = new BigInteger(1, compressedPoint.getEncoded());
    this.pubKey = new BigInteger(1, point.getEncoded());
  }

  /**
   * Create a key pair given the private key
   * @param privKey the private key
   */
  public static ECKey fromString(final String privKey)
  {
    checkNotNull(privKey, "Private key is required");
    // FIXME remove debug statements
    System.err.println("Decoded is " + Utils.bytesToHexString(Base58.decodeChecked(privKey)));
    System.err.println("Integer is " + new BigInteger(1, Base58.decodeChecked(privKey)));
    return new ECKey(new BigInteger(1, Base58.decodeChecked(privKey)));
  }

  public BigInteger getPubKey()
  {
    return this.pubKey;
  }

  public BigInteger getPrivKey()
  {
    return this.privKey;
  }

  public static void debugKey(final String privKey)
  {
    System.err.println("================");
    System.err.println("Decoded is " + Utils.bytesToHexString(Base58.decodeChecked(privKey)));
    final ECKey key = new ECKey(new BigInteger(1, Base58.decodeChecked(privKey)));
    final Sha256Hash hash1 = Sha256Hash.create(ImmutableList.copyOf(Bytes.asList(key.getPubKey().toByteArray())));
    System.err.println("Hash1 is " + Utils.bytesToHexString(hash1.getHash()));
    final Ripemd160Hash hash2 = Ripemd160Hash.create(hash1.getHash());
    System.err.println("Hash2 is " + Utils.bytesToHexString(hash2.getHash()));
    final Address addr = new Address(Network.TEST, hash2);
    System.err.println("Address is " + addr);
//    System.err.println("Encoded address is " + Base58.encode(addr));
    //    final Sha256Hash hash2 = Sha256Hash.create(ImmutableList.copyOf(Bytes.asList(key.getPubKey().toByteArray())));
    System.err.println("================");
  }
}
