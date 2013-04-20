package test.com.wealdtech.bitcoin.generator.raw;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.wealdtech.bitcoin.generator.raw.Utils;

public class UtilsTest
{
  @Test
  public void testVarInt1() throws Exception
  {
    byte[] res = Utils.longToVarintLE(50);
    assertEquals(res.length, 1);
    assertEquals(res[0], 50);
  }

  @Test
  public void testVarInt2() throws Exception
  {
    byte[] res = Utils.longToVarintLE(300);
    assertEquals(res.length, 3);
    assertEquals(res[0], -3);
    assertEquals(res[1], 44);
    assertEquals(res[2], 1);
  }

  @Test
  public void testVarInt3() throws Exception
  {
    byte[] res = Utils.longToVarintLE(65560);
    assertEquals(res.length, 5);
    assertEquals(res[0], -2);
    assertEquals(res[1], 24);
    assertEquals(res[2], 0);
    assertEquals(res[3], 1);
    assertEquals(res[4], 0);
  }

  @Test
  public void testVarInt4() throws Exception
  {
    byte[] res = Utils.longToVarintLE(4294967300L);
    assertEquals(res.length, 9);
    assertEquals(res[0], -1);
    assertEquals(res[1], 4);
    assertEquals(res[2], 0);
    assertEquals(res[3], 0);
    assertEquals(res[4], 0);
    assertEquals(res[5], 1);
    assertEquals(res[6], 0);
    assertEquals(res[7], 0);
    assertEquals(res[8], 0);
  }

  @Test
  public void testHexStringToBytes() throws Exception
  {
    final String str = "c9a8171a4b753f7263c0bcb69319229539650fb6a43427a644e8ca33ca38a749";
    assertEquals(Utils.bytesToHexString(Utils.hexStringToBytes(str)), str);
  }
}
