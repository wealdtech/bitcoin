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
