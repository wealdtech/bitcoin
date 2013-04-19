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
             8333,
             new byte[] {0}),

  /**
   * Test network
   */
  TEST("Test",
       18333,
       new byte[] {111});

  private final String name;
  private final int tcpPort;
  private final byte[] validAddressHeaders;

  Network(final String id, final int tcpPort, final byte [] validAddressHeaders)
  {
    this.name = id;
    this.tcpPort = tcpPort;
    this.validAddressHeaders = validAddressHeaders;
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
   * Obtain the default TCP port for daemons on this Bitcoin network
   * @return the default TCP port for daemons on this Bitcoin network
   */
  public int getTcpPort()
  {
    return this.tcpPort;
  }

  /**
   * Obtain the first byte of base-58 encoded addresses on this Bitcoin network
   * @return the first byte of base-58 encoded addresses on this Bitcoin network
   */
  public byte getAddressHeader()
  {
    return this.validAddressHeaders[0];
  }

  /**
   * Obtain the first byte of all valid base-58 encoded addresses on this Bitcoin network
   * @return the first byte of all valid base-58 encoded addresses on this Bitcoin network
   */
  public byte[] getValidAddressHeaders()
  {
    return this.validAddressHeaders;
  }

  @Override
  @JsonValue
  public String toString()
  {
      return super.toString().toLowerCase(Locale.ENGLISH);
  }

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
