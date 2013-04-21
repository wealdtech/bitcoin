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
