package test.com.wealdtech.bitcoin.crypto;

import static org.testng.Assert.fail;

import org.testng.annotations.Test;

import com.wealdtech.bitcoin.crypto.ECKey;

public class ECKeyTest
{
  @Test
  public void testECKey1() throws Exception
  {
    ECKey key = ECKey.fromString("cNasU8BJHPcApCVfe4jU6bRSLazB1gnA46JTdYg4CuNUZnajKWVx", "n1H4uDa8J3NooFU5sNqb4zLUcvE611SqgZ");
    fail("here");
  }
}
