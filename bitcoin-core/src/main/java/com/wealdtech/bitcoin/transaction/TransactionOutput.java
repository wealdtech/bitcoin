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
package com.wealdtech.bitcoin.transaction;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.wealdtech.bitcoin.Script;

public class TransactionOutput implements Comparable<TransactionOutput>
{
  private static final Logger LOGGER = LoggerFactory.getLogger(TransactionOutput.class);

  private BigInteger value;
  private Script scriptPubKey;

  /**
   * Create a transaction output.  Note that this is a private constructor; 
   * all transaction outputs should be created using the supplied builder.
   */
  private TransactionOutput(final BigInteger value, final Script scriptPubKey)
  {
    this.value = value;
    this.scriptPubKey = scriptPubKey;
  }

  // Standard object methods follow
  @Override
  public String toString()
  {
    return Objects.toStringHelper(this)
                  .add("value", this.value)
                  .add("scriptPubKey", this.scriptPubKey)
                  .toString();
  }

  @Override
  public boolean equals(final Object that)
  {
    return (that instanceof TransactionOutput) && (this.compareTo((TransactionOutput)that) == 0);
  }
  
  @Override
  public int hashCode()
  {
    return Objects.hashCode(this.value, this.scriptPubKey);
  }

  @Override
  public int compareTo(final TransactionOutput that)
  {
    return ComparisonChain.start()
                          .compare(this.value, that.value)
                          .compare(this.scriptPubKey, that.scriptPubKey)
                          .result();
  }

  /**
   * Builder for transaction outputs.  This is the only way
   * in which a transaction output should be created
   */
  public static class Builder
  {
    private BigInteger value;
    private Script scriptPubKey;

    /**
     * Generate a new builder.
     */
    public Builder()
    {
    }

    /**
     * Generate a builder based on a prior object.
     * @param prior the prior object
     */
    public Builder(final TransactionOutput prior)
    {
      this.value = prior.value;
      this.scriptPubKey = prior.scriptPubKey;
    }

    /**
     * Set the value
     * @param sequence the value
     * @return
     */
    public Builder value(final BigInteger value)
    {
      this.value = value;
      return this;
    }

    /**
     * Set the script public key
     * @param scriptPubKey the script public key
     * @return
     */
    public Builder scriptPubKey(final Script scriptPubKey)
    {
      this.scriptPubKey = scriptPubKey;
      return this;
    }


    /**
     * Build the transaction output
     * @return a new transaction output
     */
    public TransactionOutput build()
    {
      return new TransactionOutput(this.value, this.scriptPubKey);
    }
  }
}
