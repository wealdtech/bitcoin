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
package test.com.wealdtech.bitcoin.crypto;

import static org.testng.Assert.assertTrue;

import java.math.BigInteger;

import org.testng.annotations.Test;

import com.wealdtech.bitcoin.crypto.ECKey;
import com.wealdtech.bitcoin.utils.Base58;

public class ECKeyTest
{
  @Test
  public void testECKey1() throws Exception
  {
    final ECKey key = ECKey.fromString("cNasU8BJHPcApCVfe4jU6bRSLazB1gnA46JTdYg4CuNUZnajKWVx");
    System.err.println("Pubkey is " + key.getPubKey());
    BigInteger pubkey = key.getPubKey();

    assertTrue(key.getPubKey().equals(new BigInteger(1, Base58.decodeChecked("n1H4uDa8J3NooFU5sNqb4zLUcvE611SqgZ"))));
  }
}
