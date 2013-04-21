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

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.wealdtech.bitcoin.BTCUnit;

public class BTCUnitTest
{
  @Test
  public void testGetBestMinSatoshis() throws Exception
  {
    assertEquals(BTCUnit.getBest(1L), BTCUnit.SATOSHIS);
  }

  @Test
  public void testGetBestMaxSatoshis() throws Exception
  {
    assertEquals(BTCUnit.getBest(99L), BTCUnit.SATOSHIS);
  }

  @Test
  public void testGetBestMinuBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(100L), BTCUnit.MICROBTCS);
  }

  @Test
  public void testGetBestMaxuBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999L), BTCUnit.MICROBTCS);
  }

  @Test
  public void testGetBestMinmBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000L), BTCUnit.MILLIBTCS);
  }

  @Test
  public void testGetBestMaxmBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999999L), BTCUnit.MILLIBTCS);
  }

  @Test
  public void testGetBestMinBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000000L), BTCUnit.BTCS);
  }

  @Test
  public void testGetBestMaxBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999999999L), BTCUnit.BTCS);
  }

  @Test
  public void testGetBestMinkBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000000000L), BTCUnit.KILOBTCS);
  }

  @Test
  public void testGetBestMaxkBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999999999999L), BTCUnit.KILOBTCS);
  }

  @Test
  public void testGetBestMinMBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000000000000L), BTCUnit.MEGABTCS);
  }

  @Test
  public void testGetBestMaxMBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999999999999999L), BTCUnit.MEGABTCS);
  }

  @Test
  public void testGetBest0BTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(0L), BTCUnit.BTCS);
  }

  @Test
  public void testGetBestMinSatoshisUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(1L, false), BTCUnit.SATOSHIS);
  }

  @Test
  public void testGetBestMaxSatoshisUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(99L, false), BTCUnit.SATOSHIS);
  }

  @Test
  public void testGetBestMinuBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(100L, false), BTCUnit.MICROBTCS);
  }

  @Test
  public void testGetBestMaxuBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999L, false), BTCUnit.MICROBTCS);
  }

  @Test
  public void testGetBestMinmBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000L, false), BTCUnit.MILLIBTCS);
  }

  @Test
  public void testGetBestMaxmBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(999999L, false), BTCUnit.MILLIBTCS);
  }

  @Test
  public void testGetBestMincBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(1000000L, false), BTCUnit.CENTIBTCS);
  }

  @Test
  public void testGetBestMaxcBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(9999999L, false), BTCUnit.CENTIBTCS);
  }

  @Test
  public void testGetBestMindBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(10000000L, false), BTCUnit.DECIBTCS);
  }

  @Test
  public void testGetBestMaxdBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999999L, false), BTCUnit.DECIBTCS);
  }

  @Test
  public void testGetBestMinBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000000L, false), BTCUnit.BTCS);
  }

  @Test
  public void testGetBestMaxBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(999999999L, false), BTCUnit.BTCS);
  }

  @Test
  public void testGetBestMindaBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(1000000000L, false), BTCUnit.DECABTCS);
  }

  @Test
  public void testGetBestMaxdaBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(9999999999L, false), BTCUnit.DECABTCS);
  }

  @Test
  public void testGetBestMinhBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(10000000000L, false), BTCUnit.HECTOBTCS);
  }

  @Test
  public void testGetBestMaxhBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999999999L, false), BTCUnit.HECTOBTCS);
  }

  @Test
  public void testGetBestMinkBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000000000L, false), BTCUnit.KILOBTCS);
  }

  @Test
  public void testGetBestMaxkBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999999999999L, false), BTCUnit.KILOBTCS);
  }

  @Test
  public void testGetBestMinMBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000000000000L, false), BTCUnit.MEGABTCS);
  }

  @Test
  public void testPrettyStringSatoshis1() throws Exception
  {
    assertEquals(BTCUnit.SATOSHIS.toPrettyString(1L), "1");
  }

  @Test
  public void testPrettyStringSatoshis2() throws Exception
  {
    assertEquals(BTCUnit.SATOSHIS.toPrettyString(2000000000000001L), "2000000000000001");
  }

  @Test
  public void testPrettyStringuBTC1() throws Exception
  {
    assertEquals(BTCUnit.MICROBTCS.toPrettyString(1L), "0.01µBTC");
  }

  @Test
  public void testPrettyStringuBTC2() throws Exception
  {
    assertEquals(BTCUnit.MICROBTCS.toPrettyString(2000000000000001L), "20000000000000.01µBTC");
  }

  @Test
  public void testPrettyStringmBTC1() throws Exception
  {
    assertEquals(BTCUnit.MILLIBTCS.toPrettyString(1L), "0.00001mBTC");
  }

  @Test
  public void testPrettyStringmBTC2() throws Exception
  {
    assertEquals(BTCUnit.MILLIBTCS.toPrettyString(2000000000000001L), "20000000000.00001mBTC");
  }

  @Test
  public void testPrettyStringcBTC1() throws Exception
  {
    assertEquals(BTCUnit.CENTIBTCS.toPrettyString(1L), "0.000001cBTC");
  }

  @Test
  public void testPrettyStringcBTC2() throws Exception
  {
    assertEquals(BTCUnit.CENTIBTCS.toPrettyString(2000000000000001L), "2000000000.000001cBTC");
  }

  @Test
  public void testPrettyStringdBTC1() throws Exception
  {
    assertEquals(BTCUnit.DECIBTCS.toPrettyString(1L), "0.0000001dBTC");
  }

  @Test
  public void testPrettyStringdBTC2() throws Exception
  {
    assertEquals(BTCUnit.DECIBTCS.toPrettyString(2000000000000001L), "200000000.0000001dBTC");
  }

  @Test
  public void testPrettyStringdTC1() throws Exception
  {
    assertEquals(BTCUnit.BTCS.toPrettyString(1L), "0.00000001BTC");
  }

  @Test
  public void testPrettyStringBTC2() throws Exception
  {
    assertEquals(BTCUnit.BTCS.toPrettyString(2000000000000001L), "20000000.00000001BTC");
  }

  @Test
  public void testPrettyStringBTC3() throws Exception
  {
    assertEquals(BTCUnit.BTCS.toPrettyString(1500000000L), "15BTC");
  }

  @Test
  public void testPrettyStringBTC4() throws Exception
  {
    assertEquals(BTCUnit.BTCS.toPrettyString(1523400000L), "15.234BTC");
  }

  @Test
  public void testPrettyStringdaBTC1() throws Exception
  {
    assertEquals(BTCUnit.DECABTCS.toPrettyString(1L), "0.000000001daBTC");
  }

  @Test
  public void testPrettyStringdaBTC2() throws Exception
  {
    assertEquals(BTCUnit.DECABTCS.toPrettyString(2000000000000001L), "2000000.000000001daBTC");
  }

  @Test
  public void testPrettyStringhBTC1() throws Exception
  {
    assertEquals(BTCUnit.HECTOBTCS.toPrettyString(1L), "0.0000000001hBTC");
  }

  @Test
  public void testPrettyStringhBTC2() throws Exception
  {
    assertEquals(BTCUnit.HECTOBTCS.toPrettyString(2000000000000001L), "200000.0000000001hBTC");
  }

  @Test
  public void testPrettyStringkBTC1() throws Exception
  {
    assertEquals(BTCUnit.KILOBTCS.toPrettyString(1L), "0.00000000001kBTC");
  }

  @Test
  public void testPrettyStringkBTC2() throws Exception
  {
    assertEquals(BTCUnit.KILOBTCS.toPrettyString(2000000000000001L), "20000.00000000001kBTC");
  }

  @Test
  public void testPrettyStringMBTC1() throws Exception
  {
    assertEquals(BTCUnit.MEGABTCS.toPrettyString(1L), "0.00000000000001MBTC");
  }

  @Test
  public void testPrettyStringMBTC2() throws Exception
  {
    assertEquals(BTCUnit.MEGABTCS.toPrettyString(2000000000000001L), "20.00000000000001MBTC");
  }
}
