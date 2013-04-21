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
