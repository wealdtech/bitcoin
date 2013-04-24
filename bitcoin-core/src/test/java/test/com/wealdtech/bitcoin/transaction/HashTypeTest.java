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
package test.com.wealdtech.bitcoin.transaction;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import com.wealdtech.bitcoin.transaction.HashType;

public class HashTypeTest
{
  @Test
  public void testAnyoneCanPay1() throws Exception
  {
    HashType hashType = HashType.ALL_ANYONECANPAY;
    assertTrue(hashType.isAnyoneCanPay());
  }

  @Test
  public void testAnyoneCanPay2() throws Exception
  {
    HashType hashType = HashType.ALL;
    assertFalse(hashType.isAnyoneCanPay());
  }

  @Test
  public void testIsKindOf1() throws Exception
  {
    HashType hashType = HashType.ALL_ANYONECANPAY;
    assertTrue(hashType.isKindOf(HashType.ALL));
  }

  @Test
  public void testIsKindOf2() throws Exception
  {
    HashType hashType = HashType.ALL_ANYONECANPAY;
    assertFalse(hashType.isKindOf(HashType.SINGLE));
  }

  @Test
  public void testIsKindOf3() throws Exception
  {
    HashType hashType = HashType.ALL;
    assertTrue(hashType.isKindOf(HashType.ALL));
  }

  @Test
  public void testIsKindOf4() throws Exception
  {
    HashType hashType = HashType.ALL;
    assertFalse(hashType.isKindOf(HashType.ANYONECANPAY));
  }
}
