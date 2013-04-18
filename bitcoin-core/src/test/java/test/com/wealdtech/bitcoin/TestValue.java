package test.com.wealdtech.bitcoin;

import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;

import com.wealdtech.bitcoin.Value;

public class TestValue
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

}
