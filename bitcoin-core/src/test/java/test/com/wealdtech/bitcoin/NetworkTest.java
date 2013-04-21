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

import com.wealdtech.bitcoin.Network;

public class NetworkTest
{
  @Test
  public void testProdPubkeyValid() throws Exception
  {
    assertEquals(Network.PRODUCTION, Network.fromVersion(0));
  }

  @Test
  public void testProdScriptValid() throws Exception
  {
    assertEquals(Network.PRODUCTION, Network.fromVersion(5));
  }

  @Test
  public void testTestPubkeyValid() throws Exception
  {
    assertEquals(Network.TEST, Network.fromVersion(111));
  }

  @Test
  public void testTestScriptValid() throws Exception
  {
    assertEquals(Network.TEST, Network.fromVersion(196));
  }
}
