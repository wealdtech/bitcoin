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
package test.com.wealdtech.bitcoin;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import com.wealdtech.DataError;
import com.wealdtech.bitcoin.Value;

public class ValueTest
{

  @Test
  public void testParseSatoshi1() throws Exception
  {
    final Value val = Value.fromString("5 satoshis");
    assertEquals(val.getSatoshis(), 5L);
  }

  @Test
  public void testParseSatoshi2() throws Exception
  {
    final Value val = Value.fromString("5Satoshis");
    assertEquals(val.getSatoshis(), 5L);
  }

  @Test
  public void testParseSatoshi3() throws Exception
  {
    final Value val = Value.fromString("5 satoshi");
    assertEquals(val.getSatoshis(), 5L);
  }

  @Test
  public void testParseSatoshi4() throws Exception
  {
    final Value val = Value.fromString("5 satoshis");
    assertEquals(val.getSatoshis(), 5L);
  }

  @Test
  public void testParseuBTC1() throws Exception
  {
    final Value val = Value.fromString("5 uBTC");
    assertEquals(val.getSatoshis(), 500L);
  }

  @Test
  public void testParseuBTC2() throws Exception
  {
    final Value val = Value.fromString("5uBTC");
    assertEquals(val.getSatoshis(), 500L);
  }

  @Test
  public void testParseuBTC3() throws Exception
  {
    final Value val = Value.fromString("5 µBTC");
    assertEquals(val.getSatoshis(), 500L);
  }

  @Test
  public void testParsemBTC1() throws Exception
  {
    final Value val = Value.fromString("5 mBTC");
    assertEquals(val.getSatoshis(), 500000L);
  }

  @Test
  public void testParsemBTC2() throws Exception
  {
    final Value val = Value.fromString("5mBTC");
    assertEquals(val.getSatoshis(), 500000L);
  }

  @Test
  public void testParsecBTC1() throws Exception
  {
    final Value val = Value.fromString("5 cBTC");
    assertEquals(val.getSatoshis(), 5000000L);
  }

  @Test
  public void testParsecBTC2() throws Exception
  {
    final Value val = Value.fromString("5cBTC");
    assertEquals(val.getSatoshis(), 5000000L);
  }

  @Test
  public void testParsedBTC1() throws Exception
  {
    final Value val = Value.fromString("5 dBTC");
    assertEquals(val.getSatoshis(), 50000000L);
  }

  @Test
  public void testParsedBTC2() throws Exception
  {
    final Value val = Value.fromString("5dBTC");
    assertEquals(val.getSatoshis(), 50000000L);
  }

  @Test
  public void testParseBTC1() throws Exception
  {
    final Value val = Value.fromString("5 BTC");
    assertEquals(val.getSatoshis(), 500000000L);
  }

  @Test
  public void testParseBTC2() throws Exception
  {
    final Value val = Value.fromString("5BTC");
    assertEquals(val.getSatoshis(), 500000000L);
  }

  @Test
  public void testParseBitcoins1() throws Exception
  {
    final Value val = Value.fromString("5 bitcoins");
    assertEquals(val.getSatoshis(), 500000000L);
  }

  @Test
  public void testParseBitcoins2() throws Exception
  {
    final Value val = Value.fromString("5bitcoins");
    assertEquals(val.getSatoshis(), 500000000L);
  }

  @Test
  public void testParseBitcoins3() throws Exception
  {
    final Value val = Value.fromString("5 bitcoin");
    assertEquals(val.getSatoshis(), 500000000L);
  }

  @Test
  public void testParseBitcoins4() throws Exception
  {
    final Value val = Value.fromString("5 Bitcoin");
    assertEquals(val.getSatoshis(), 500000000L);
  }

  @Test
  public void testParsedaBTC1() throws Exception
  {
    final Value val = Value.fromString("5 daBTC");
    assertEquals(val.getSatoshis(), 5000000000L);
  }

  @Test
  public void testParsedaBTC2() throws Exception
  {
    final Value val = Value.fromString("5daBTC");
    assertEquals(val.getSatoshis(), 5000000000L);
  }

  @Test
  public void testParsehBTC1() throws Exception
  {
    final Value val = Value.fromString("5 hBTC");
    assertEquals(val.getSatoshis(), 50000000000L);
  }

  @Test
  public void testParsehBTC2() throws Exception
  {
    final Value val = Value.fromString("5hBTC");
    assertEquals(val.getSatoshis(), 50000000000L);
  }

  @Test
  public void testParsekBTC1() throws Exception
  {
    final Value val = Value.fromString("5 kBTC");
    assertEquals(val.getSatoshis(), 500000000000L);
  }

  @Test
  public void testParsekBTC2() throws Exception
  {
    final Value val = Value.fromString("5kBTC");
    assertEquals(val.getSatoshis(), 500000000000L);
  }

  @Test
  public void testParsekBTC3() throws Exception
  {
    final Value val = Value.fromString("5KBTC");
    assertEquals(val.getSatoshis(), 500000000000L);
  }

  @Test
  public void testParseMBTC1() throws Exception
  {
    final Value val = Value.fromString("5 MBTC");
    assertEquals(val.getSatoshis(), 500000000000000L);
  }

  @Test
  public void testParseMBTC2() throws Exception
  {
    final Value val = Value.fromString("5MBTC");
    assertEquals(val.getSatoshis(), 500000000000000L);
  }

  @Test
  public void testParseDecimal1() throws Exception
  {
    final Value val = Value.fromString("5.2MBTC");
    assertEquals(val.getSatoshis(), 520000000000000L);
  }

  @Test
  public void testParseDecimal2() throws Exception
  {
    final Value val = Value.fromString("0.1BTC");
    assertEquals(val.getSatoshis(), 10000000L);
  }

  @Test
  public void testParseDecimal3() throws Exception
  {
    final Value val = Value.fromString(".1BTC");
    assertEquals(val.getSatoshis(), 10000000L);
  }

  @Test
  public void testParseDecimal4() throws Exception
  {
    final Value val = Value.fromString(".001BTC");
    assertEquals(val.getSatoshis(), 100000L);
  }

  @Test
  public void testParseSubSatoshi() throws Exception
  {
    final Value val = Value.fromString(".1 satoshis");
    assertEquals(val.getSatoshis(), 0L);
  }

  @Test
  public void testParseNull1() throws Exception
  {
    try
    {
      Value.fromString(null);
      // Should not reach here
      fail("Parsed null value");
    }
    catch (DataError.Missing de)
    {
      // Good
    }
  }

  @Test
  public void testParseInvalid1() throws Exception
  {
    try
    {
      Value.fromString("Bad value");
      // Should not reach here
      fail("Parsed bad value");
    }
    catch (DataError.Bad de)
    {
      // Good
    }
  }
}
