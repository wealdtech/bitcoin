package test.com.wealdtech.bitcoin;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import com.wealdtech.DataError;
import com.wealdtech.bitcoin.config.NetworkConfiguration;
import com.wealdtech.configuration.ConfigurationSource;

public class NetworkConfigurationTest
{
  @Test
  public void testValidConfiguration() throws Exception
  {
    // Test obtaining the configuration from a valid configuration source
    final NetworkConfiguration configuration = new ConfigurationSource<NetworkConfiguration>().getConfiguration("networkconfiguration1-config.json", NetworkConfiguration.class);
    assertNotNull(configuration);
    configuration.toString();
    configuration.hashCode();
    assertEquals(configuration, configuration);
    assertNotEquals(null, configuration);
    assertNotEquals(configuration, null);
    assertNotNull(configuration.getNetwork());
    assertEquals(configuration.getNetwork().getName(), "Production");
  }

  @Test
  public void testEmptyConfiguration() throws Exception
  {
    // Test obtaining the configuration from an empty configuration source
    final NetworkConfiguration configuration = new ConfigurationSource<NetworkConfiguration>().getConfiguration("networkconfiguration2-config.json", NetworkConfiguration.class);
    assertNotNull(configuration);
    configuration.toString();
    configuration.hashCode();
    assertEquals(configuration, configuration);
    assertNotEquals(null, configuration);
    assertNotEquals(configuration, null);
    assertNotNull(configuration.getNetwork());
    assertEquals(configuration.getNetwork().getName(), "Test");
  }

  @Test
  public void testInvalidConfiguration() throws Exception
  {
    // Test obtaining the configuration from an invalid configuration source
    try
    {
      new ConfigurationSource<NetworkConfiguration>().getConfiguration("networkconfiguration3-config.json", NetworkConfiguration.class);
      // Should not reach here
      fail("Accepted invalid configuration file");
    }
    catch (DataError.Bad de)
    {
      // Good
    }
  }

}
