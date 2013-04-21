package com.wealdtech.bitcoin.crypto;

import static com.wealdtech.Preconditions.checkNotNull;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;

import javax.annotation.Nullable;

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
   * Create an ECKey given the public and private keys
   * @param pubKey the public key
   * @param privKey the private key
   */
  public ECKey(@Nullable final BigInteger pubKey, final BigInteger privKey)
  {
    checkNotNull(privKey, "Private key is required");
    this.privKey = privKey;

    if (pubKey == null)
    {
      // Calculate it from the private key
      final ECPoint point = ecParams.getG().multiply(privKey);
      this.pubKey = new BigInteger(point.getEncoded());
    }
    else
    {
      this.pubKey = pubKey;
    }
  }

  public static ECKey fromString(@Nullable final String pubKeyStr, final String privKeyStr)
  {
    checkNotNull(privKeyStr, "Private key is required");

    BigInteger pubKey;
    if (pubKeyStr == null)
    {
      pubKey = null;
    }
    else
    {
      pubKey = new BigInteger(Base58.decodeChecked(pubKeyStr));
    }

    BigInteger privKey = new BigInteger(Base58.decodeChecked(privKeyStr));

    return new ECKey(pubKey, privKey);
  }
}
