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
import java.security.SecureRandom;

import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.math.ec.ECPoint;

import com.wealdtech.bitcoin.generator.raw.Utils;
import com.wealdtech.bitcoin.utils.Base58;

public class ECKey implements Serializable
{
  private static final ECDomainParameters ecParams;
  private static final SecureRandom secureRandom;

  private final BigInteger pubKey;
  private final BigInteger privKey;

  static
  {
    // Set up EC parameters
    final X9ECParameters params = SECNamedCurves.getByName("secp256k1");
    ecParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());
    secureRandom = new SecureRandom();
  }

  /**
   * Create a key pair given the private key
   * @param privKey the private key
   */
  public ECKey(final BigInteger privKey)
  {
    checkNotNull(privKey, "Private key is required");
    this.privKey = privKey;

    // Calculate it from the private key
    final ECPoint point = ecParams.getG().multiply(privKey);
    // Compress it
    ECPoint compressedPoint = compressPoint(point);
    this.pubKey = new BigInteger(1, compressedPoint.getEncoded());
  }

  /**
   * Compress a point, resulting in a format suitable for Bitcoin usage
   * @param uncompressed
   * @return
   */
  private ECPoint compressPoint(final ECPoint uncompressed)
  {
    return new ECPoint.Fp(ecParams.getCurve(), uncompressed.getX(), uncompressed.getY(), true);
  }

  /**
   * Create a key pair given the private key
   * @param privKey the private key
   */
  public static ECKey fromString(final String privKey)
  {
    checkNotNull(privKey, "Private key is required");
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
}
