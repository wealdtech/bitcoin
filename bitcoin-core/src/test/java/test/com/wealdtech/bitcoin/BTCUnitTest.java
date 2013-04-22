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
import com.wealdtech.bitcoin.BTCUnit;

public class BTCUnitTest
{
  @Test
  public void testGetBestMinSatoshis() throws Exception
  {
    assertEquals(BTCUnit.getBest(1L), BTCUnit.SATOSHI);
  }

  @Test
  public void testGetBestMaxSatoshis() throws Exception
  {
    assertEquals(BTCUnit.getBest(99L), BTCUnit.SATOSHI);
  }

  @Test
  public void testGetBestMinuBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(100L), BTCUnit.MICROBTC);
  }

  @Test
  public void testGetBestMaxuBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999L), BTCUnit.MICROBTC);
  }

  @Test
  public void testGetBestMinmBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000L), BTCUnit.MILLIBTC);
  }

  @Test
  public void testGetBestMaxmBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999999L), BTCUnit.MILLIBTC);
  }

  @Test
  public void testGetBestMinBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000000L), BTCUnit.BTC);
  }

  @Test
  public void testGetBestMaxBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999999999L), BTCUnit.BTC);
  }

  @Test
  public void testGetBestMinkBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000000000L), BTCUnit.KILOBTC);
  }

  @Test
  public void testGetBestMaxkBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999999999999L), BTCUnit.KILOBTC);
  }

  @Test
  public void testGetBestMinMBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000000000000L), BTCUnit.MEGABTC);
  }

  @Test
  public void testGetBestMaxMBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999999999999999L), BTCUnit.MEGABTC);
  }

  @Test
  public void testGetBest0BTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(0L), BTCUnit.BTC);
  }

  @Test
  public void testGetBestMinSatoshisUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(1L, false), BTCUnit.SATOSHI);
  }

  @Test
  public void testGetBestMaxSatoshisUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(99L, false), BTCUnit.SATOSHI);
  }

  @Test
  public void testGetBestMinuBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(100L, false), BTCUnit.MICROBTC);
  }

  @Test
  public void testGetBestMaxuBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999L, false), BTCUnit.MICROBTC);
  }

  @Test
  public void testGetBestMinmBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000L, false), BTCUnit.MILLIBTC);
  }

  @Test
  public void testGetBestMaxmBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(999999L, false), BTCUnit.MILLIBTC);
  }

  @Test
  public void testGetBestMincBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(1000000L, false), BTCUnit.CENTIBTC);
  }

  @Test
  public void testGetBestMaxcBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(9999999L, false), BTCUnit.CENTIBTC);
  }

  @Test
  public void testGetBestMindBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(10000000L, false), BTCUnit.DECIBTC);
  }

  @Test
  public void testGetBestMaxdBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999999L, false), BTCUnit.DECIBTC);
  }

  @Test
  public void testGetBestMinBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000000L, false), BTCUnit.BTC);
  }

  @Test
  public void testGetBestMaxBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(999999999L, false), BTCUnit.BTC);
  }

  @Test
  public void testGetBestMindaBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(1000000000L, false), BTCUnit.DECABTC);
  }

  @Test
  public void testGetBestMaxdaBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(9999999999L, false), BTCUnit.DECABTC);
  }

  @Test
  public void testGetBestMinhBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(10000000000L, false), BTCUnit.HECTOBTC);
  }

  @Test
  public void testGetBestMaxhBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999999999L, false), BTCUnit.HECTOBTC);
  }

  @Test
  public void testGetBestMinkBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000000000L, false), BTCUnit.KILOBTC);
  }

  @Test
  public void testGetBestMaxkBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999999999999L, false), BTCUnit.KILOBTC);
  }

  @Test
  public void testGetBestMinMBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000000000000L, false), BTCUnit.MEGABTC);
  }

  @Test
  public void testPrettyStringSatoshis1() throws Exception
  {
    assertEquals(BTCUnit.SATOSHI.toPrettyString(1L), "1");
  }

  @Test
  public void testPrettyStringSatoshis2() throws Exception
  {
    assertEquals(BTCUnit.SATOSHI.toPrettyString(2000000000000001L), "2000000000000001");
  }

  @Test
  public void testPrettyStringuBTC1() throws Exception
  {
    assertEquals(BTCUnit.MICROBTC.toPrettyString(1L), "0.01 µBTC");
  }

  @Test
  public void testPrettyStringuBTC2() throws Exception
  {
    assertEquals(BTCUnit.MICROBTC.toPrettyString(2000000000000001L), "20000000000000.01 µBTC");
  }

  @Test
  public void testPrettyStringmBTC1() throws Exception
  {
    assertEquals(BTCUnit.MILLIBTC.toPrettyString(1L), "0.00001 mBTC");
  }

  @Test
  public void testPrettyStringmBTC2() throws Exception
  {
    assertEquals(BTCUnit.MILLIBTC.toPrettyString(2000000000000001L), "20000000000.00001 mBTC");
  }

  @Test
  public void testPrettyStringcBTC1() throws Exception
  {
    assertEquals(BTCUnit.CENTIBTC.toPrettyString(1L), "0.000001 cBTC");
  }

  @Test
  public void testPrettyStringcBTC2() throws Exception
  {
    assertEquals(BTCUnit.CENTIBTC.toPrettyString(2000000000000001L), "2000000000.000001 cBTC");
  }

  @Test
  public void testPrettyStringdBTC1() throws Exception
  {
    assertEquals(BTCUnit.DECIBTC.toPrettyString(1L), "0.0000001 dBTC");
  }

  @Test
  public void testPrettyStringdBTC2() throws Exception
  {
    assertEquals(BTCUnit.DECIBTC.toPrettyString(2000000000000001L), "200000000.0000001 dBTC");
  }

  @Test
  public void testPrettyStringdTC1() throws Exception
  {
    assertEquals(BTCUnit.BTC.toPrettyString(1L), "0.00000001 BTC");
  }

  @Test
  public void testPrettyStringBTC2() throws Exception
  {
    assertEquals(BTCUnit.BTC.toPrettyString(2000000000000001L), "20000000.00000001 BTC");
  }

  @Test
  public void testPrettyStringBTC3() throws Exception
  {
    assertEquals(BTCUnit.BTC.toPrettyString(1500000000L), "15 BTC");
  }

  @Test
  public void testPrettyStringBTC4() throws Exception
  {
    assertEquals(BTCUnit.BTC.toPrettyString(1523400000L), "15.234 BTC");
  }

  @Test
  public void testPrettyStringdaBTC1() throws Exception
  {
    assertEquals(BTCUnit.DECABTC.toPrettyString(1L), "0.000000001 daBTC");
  }

  @Test
  public void testPrettyStringdaBTC2() throws Exception
  {
    assertEquals(BTCUnit.DECABTC.toPrettyString(2000000000000001L), "2000000.000000001 daBTC");
  }

  @Test
  public void testPrettyStringhBTC1() throws Exception
  {
    assertEquals(BTCUnit.HECTOBTC.toPrettyString(1L), "0.0000000001 hBTC");
  }

  @Test
  public void testPrettyStringhBTC2() throws Exception
  {
    assertEquals(BTCUnit.HECTOBTC.toPrettyString(2000000000000001L), "200000.0000000001 hBTC");
  }

  @Test
  public void testPrettyStringkBTC1() throws Exception
  {
    assertEquals(BTCUnit.KILOBTC.toPrettyString(1L), "0.00000000001 kBTC");
  }

  @Test
  public void testPrettyStringkBTC2() throws Exception
  {
    assertEquals(BTCUnit.KILOBTC.toPrettyString(2000000000000001L), "20000.00000000001 kBTC");
  }

  @Test
  public void testPrettyStringMBTC1() throws Exception
  {
    assertEquals(BTCUnit.MEGABTC.toPrettyString(1L), "0.00000000000001 MBTC");
  }

  @Test
  public void testPrettyStringMBTC2() throws Exception
  {
    assertEquals(BTCUnit.MEGABTC.toPrettyString(2000000000000001L), "20.00000000000001 MBTC");
  }

  @Test
  public void testParseValid() throws Exception
  {
    BTCUnit unit = BTCUnit.parse("MILLIBTC");
    assertEquals(unit, BTCUnit.MILLIBTC);
  }

  @Test
  public void testParseNull() throws Exception
  {
    try
    {
      BTCUnit.parse(null);
      fail("Parsed null BTC unit");
    }
    catch (DataError.Missing de)
    {
      // Good
    }
  }

  @Test
  public void testParseInvalid() throws Exception
  {
    try
    {
      BTCUnit.parse("KBITCOIN");
      fail("Parsed invalid BTC unit");
    }
    catch (DataError.Bad de)
    {
      // Good
    }
  }

  @Test
  public void testToSatoshis() throws Exception
  {
    long satoshis = BTCUnit.toSatoshis(12345L, BTCUnit.BTC);
    assertEquals(satoshis, 1234500000000L);
  }
}
