package com.wealdtech.bitcoin.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bouncycastle.crypto.digests.RIPEMD160Digest;

import com.wealdtech.ServerError;

/**
 * Helper functions to work with cryptography systems
 */
public class Crypto
{
  /**
   * Create
   * @param input
   * @return
   */
  public static byte[] ripeOfShaOfBytes(final byte[] input)
  {
    try
    {
      byte[] sha256 = MessageDigest.getInstance("SHA-256").digest(input);
      RIPEMD160Digest digest = new RIPEMD160Digest();
      digest.update(sha256, 0, sha256.length);
      byte[] out = new byte[20];
      digest.doFinal(out, 0);
      return out;
    }
    catch (NoSuchAlgorithmException nsae)
    {
      throw new ServerError("Failed to access digest", nsae);
    }
  }

  public static Sha256Hash shaOfShaOfBytes(final byte[] input)
  {
    try
    {
      byte[] sha256 = MessageDigest.getInstance("SHA-256").digest(input);
      RIPEMD160Digest digest = new RIPEMD160Digest();
      digest.update(sha256, 0, sha256.length);
      byte[] out = new byte[20];
      digest.doFinal(out, 0);
      return out;
    }
    catch (NoSuchAlgorithmException nsae)
    {
      throw new ServerError("Failed to access digest", nsae);
    }
  }
}
