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
    this.network = new ConfigurationSource<NetworkConfiguration>().getConfiguration("bitcoinkey-config.json", NetworkConfiguration.class).getNetwork();
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
