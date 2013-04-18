package test.com.wealdtech.bitcoin;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

import com.wealdtech.bitcoin.BTCUnit;

public class TestBTCUnit
{
  @Test
  public void testMinSatoshis() throws Exception
  {
    assertEquals(BTCUnit.getBest(1L), BTCUnit.SATOSHIS);
  }

  @Test
  public void testMaxSatoshis() throws Exception
  {
    assertEquals(BTCUnit.getBest(99L), BTCUnit.SATOSHIS);
  }

  @Test
  public void testMinuBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(100L), BTCUnit.MICROBTCS);
  }

  @Test
  public void testMaxuBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999L), BTCUnit.MICROBTCS);
  }

  @Test
  public void testMinmBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000L), BTCUnit.MILLIBTCS);
  }

  @Test
  public void testMaxmBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999999L), BTCUnit.MILLIBTCS);
  }

  @Test
  public void testMinBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000000L), BTCUnit.BTCS);
  }

  @Test
  public void testMaxBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999999999L), BTCUnit.BTCS);
  }

  @Test
  public void testMinkBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000000000L), BTCUnit.KILOBTCS);
  }

  @Test
  public void testMaxkBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999999999999L), BTCUnit.KILOBTCS);
  }

  @Test
  public void testMinMBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000000000000L), BTCUnit.MEGABTCS);
  }

  @Test
  public void testMaxMBTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999999999999999L), BTCUnit.MEGABTCS);
  }

  @Test
  public void test0BTC() throws Exception
  {
    assertEquals(BTCUnit.getBest(0L), BTCUnit.BTCS);
  }

  @Test
  public void testMinSatoshisUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(1L, false), BTCUnit.SATOSHIS);
  }

  @Test
  public void testMaxSatoshisUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(99L, false), BTCUnit.SATOSHIS);
  }

  @Test
  public void testMinuBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(100L, false), BTCUnit.MICROBTCS);
  }

  @Test
  public void testMaxuBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999L, false), BTCUnit.MICROBTCS);
  }

  @Test
  public void testMinmBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000L, false), BTCUnit.MILLIBTCS);
  }

  @Test
  public void testMaxmBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(999999L, false), BTCUnit.MILLIBTCS);
  }

  @Test
  public void testMincBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(1000000L, false), BTCUnit.CENTIBTCS);
  }

  @Test
  public void testMaxcBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(9999999L, false), BTCUnit.CENTIBTCS);
  }

  @Test
  public void testMindBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(10000000L, false), BTCUnit.DECIBTCS);
  }

  @Test
  public void testMaxdBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999999L, false), BTCUnit.DECIBTCS);
  }

  @Test
  public void testMinBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000000L, false), BTCUnit.BTCS);
  }

  @Test
  public void testMaxBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(999999999L, false), BTCUnit.BTCS);
  }

  @Test
  public void testMindaBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(1000000000L, false), BTCUnit.DECABTCS);
  }

  @Test
  public void testMaxdaBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(9999999999L, false), BTCUnit.DECABTCS);
  }

  @Test
  public void testMinhBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(10000000000L, false), BTCUnit.HECTOBTCS);
  }

  @Test
  public void testMaxhBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999999999L, false), BTCUnit.HECTOBTCS);
  }

  @Test
  public void testMinkBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(100000000000L, false), BTCUnit.KILOBTCS);
  }

  @Test
  public void testMaxkBTCUncommon() throws Exception
  {
    assertEquals(BTCUnit.getBest(99999999999999L, false), BTCUnit.KILOBTCS);
  }

  @Test
  public void testMinMBTCUncommon() throws Exception
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
