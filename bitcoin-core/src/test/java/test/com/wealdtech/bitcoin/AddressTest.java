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
import com.wealdtech.bitcoin.Address;
import com.wealdtech.bitcoin.Network;

public class AddressTest
{
  @Test
  public void testValid1() throws Exception
  {
    final Address address = new Address("mubDRUjZ5HMKeJeyNa9H7RqwUZwjo2p21b");
    assertNotNull(address);
    assertTrue(address.isForNetwork(Network.TEST));
  }

  @Test
  public void testValid2() throws Exception
  {
    final Address address = new Address("1M2Fu8hQemwycg235RtndtSdUsFJ4yp5bg");
    assertNotNull(address);
    assertTrue(address.isForNetwork(Network.PRODUCTION));
  }

  @Test
  public void testNullAddress() throws Exception
  {
    try
    {
      new Address((String)null);
      fail("Created NULL address");
    }
    catch (DataError.Missing de)
    {
      // Good
    }
  }

  @Test
  public void testEmptyAddress() throws Exception
  {
    try
    {
      new Address("");
      fail("Created empty address");
    }
    catch (DataError.Bad de)
    {
      // Good
    }
  }

  @Test
  public void testInvalidAddress() throws Exception
  {
    try
    {
      new Address("bad");
      fail("Created invalid address");
    }
    catch (DataError.Bad de)
    {
      // Good
    }
  }

  @Test
  public void testInvalidChecksum() throws Exception
  {
    try
    {
      new Address("mubDRUjZ5HMKeJeyNa9H7RqwUZwjo2p21c");
      fail("Created address with bad checksum");
    }
    catch (DataError.Bad de)
    {
      // Good
    }
  }
}
