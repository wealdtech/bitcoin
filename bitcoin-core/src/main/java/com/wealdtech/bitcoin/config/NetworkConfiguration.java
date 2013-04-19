package com.wealdtech.bitcoin.config;

import static com.wealdtech.Preconditions.checkNotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.wealdtech.bitcoin.Network;
import com.wealdtech.configuration.Configuration;

/**
 * Configuration providing Bitcoin network information
 *
 */
public class NetworkConfiguration implements Configuration, Comparable<NetworkConfiguration>
{
  private Network network = Network.TEST;

  /**
   * Create a network with default values;
   */
  public NetworkConfiguration()
  {
    // 0-configuration defaults
  }

  /**
   * Create a configuration with specified values for all options.
   * Note that this should not be called directly, and the Builder should be
   * used for instantiation.
   *
   * @param network
   *          the name of the Bitcoin network to use
   */
  @JsonCreator
  private NetworkConfiguration(@JsonProperty("network") final Network network)
  {
    if (network != null)
    {
      this.network = network;
    }
    validate();
  }

  private void validate()
  {
    checkNotNull(this.network, "network setting is required");
  }

  public Network getNetwork()
  {
    return this.network;
  }

  // Standard object methods follow
  @Override
  public String toString()
  {
    return Objects.toStringHelper(this)
                  .add("network", this.getNetwork())
                  .toString();
  }

  @Override
  public boolean equals(final Object that)
  {
    return (that instanceof NetworkConfiguration) && (this.compareTo((NetworkConfiguration)that) == 0);
  }

  @Override
  public int hashCode()
  {
    return Objects.hashCode(this.getNetwork());
  }

  @Override
  public int compareTo(final NetworkConfiguration that)
  {
    return ComparisonChain.start()
                          .compare(this.getNetwork(), that.getNetwork())
                          .result();
  }
}
