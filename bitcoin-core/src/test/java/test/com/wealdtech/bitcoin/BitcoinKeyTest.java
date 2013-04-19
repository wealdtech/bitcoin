package test.com.wealdtech.bitcoin;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wealdtech.bitcoin.Network;
import com.wealdtech.bitcoin.config.NetworkConfiguration;
import com.wealdtech.configuration.ConfigurationSource;

public class BitcoinKeyTest
{
  private Network network;

  @BeforeClass
  public void setup() throws Exception
  {
    this.network = new ConfigurationSource<NetworkConfiguration>().getConfiguration("bitconkey-config.json", NetworkConfiguration.class).getNetwork();
  }

  @Test
  public void testValid1() throws Exception
  {
//    final BitcoinKey key = new BitcoinKey("fred");
  }

  // NULL

  // Invalid address

  // Invalid checksum

}
