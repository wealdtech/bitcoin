package com.wealdtech.bitcoin.crypto;

import static com.wealdtech.Preconditions.checkNotNull;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;

import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.math.ec.ECPoint;

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
    this.pubKey = new BigInteger(point.getEncoded());
  }

  /**
   * Create a key pair given the private key
   * @param privKey the private key
   */
  public static ECKey fromString(final String privKey)
  {
    checkNotNull(privKey, "Private key is required");
    return new ECKey(new BigInteger(Base58.decodeChecked(privKey)));
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
