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
package com.wealdtech.bitcoin;

import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wealdtech.DataError;

/**
 * Network defines the Bitcoin network on which we are operating.
 * Currently the two options are "Production" and "Test"
 */
public enum Network
{
  /**
   * Production network
   */
  PRODUCTION("Production",
             "org.bitcoin.production",
      		   8333,
             new int[] {0, 5}),

  /**
   * Test network
   */
  TEST("Test",
      "org.bitcoin.test",
       18333,
       new int[] {111, 196});

  private final String name;
  private final String id;
  private final int tcpPort;
  private final int[] validAddressVersions;

  Network(final String name, final String id, final int tcpPort, final int[] validAddressVersions)
  {
    this.name = name;
    this.id = id;
    this.tcpPort = tcpPort;
    this.validAddressVersions = validAddressVersions;
  }

  /**
   * Obtain the name of this Bitcoin network
   * @return the name of this Bitcoin network
   */
  public String getName()
  {
    return this.name;
  }

  /**
   * Obtain the ID of this Bitcoin network
   * @return the ID of this Bitcoin network
   */
  public String getId()
  {
    return this.id;
  }
  /**
   * Obtain the default TCP port for daemons on this Bitcoin network
   * @return the default TCP port for daemons on this Bitcoin network
   */
  public int getTcpPort()
  {
    return this.tcpPort;
  }

  /**
   * Obtain the first byte of addresses on this Bitcoin network
   * @return the first byte of addresses on this Bitcoin network
   */
  public int getAddressVersion()
  {
    return this.validAddressVersions[0];
  }

  /**
   * Obtain the first byte of all addresses on this Bitcoin network
   * @return the first byte of all addresses on this Bitcoin network
   */
  public int[] getValidAddressVersions()
  {
    return this.validAddressVersions;
  }

  // FIXME
  public static Network fromVersion(final int version)
  {
    for (Network network: Network.values())
    {
      for (int i : network.getValidAddressVersions())
      {
        if (version == i)
        {
          return network;
        }
      }
    }
    throw new DataError.Bad("Unknown address version " + version);
  }

  /**
   * Present the enum in a suitable output format
   */
  @Override
  @JsonValue
  public String toString()
  {
      return super.toString().toUpperCase(Locale.ENGLISH);
  }

  /**
   * Parse a string in to a suitable enum
   * @param network the string
   * @return a network
   */
  @JsonCreator
  public static Network parse(final String network)
  {
    try
    {
      return valueOf(network.toUpperCase(Locale.ENGLISH));
    }
    catch (IllegalArgumentException iae)
    {
      // N.B. we don't pass the iae as the cause of this exception because
      // this happens during invocation, and in that case the enum handler
      // will report the root cause exception rather than the one we throw.
      throw new DataError.Bad("Network type \"" + network + "\" is invalid");
    }
  }
}
