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

import static com.wealdtech.Preconditions.checkState;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.wealdtech.bitcoin.Script;

public class TransactionInput implements Comparable<TransactionInput>
{
  private static final Logger LOGGER = LoggerFactory.getLogger(TransactionInput.class);

  /**
   * Maximum sequence number.  Currently the only allowable
   * sequence number, as transaction replacement is not yet
   * available
   */
  public static final long SEQUENCE_MAX = 0xFFFFFFFFL;

  private final String txHash;
  private final int txIndex;
  private final long sequence;
  private final Optional<Script> script;

  /**
   * Create a transaction input.  Note that this is a private constructor;
   * all transaction inputs should be created using the supplied builder.
   */
  private TransactionInput(final String txHash, final int txIndex, final Long sequence, final Script script)
  {
    this.txHash = txHash;
    this.txIndex = txIndex;
    if (sequence == null)
    {
      this.sequence = SEQUENCE_MAX;
    }
    else
    {
      this.sequence = sequence;
    }
    this.script = Optional.fromNullable(script);
    validate();
  }

  private void validate()
  {
    // TODO txHash and txIndex
    checkState(this.sequence == SEQUENCE_MAX, "Invalid sequence number");
    if (this.script.isPresent())
    {
      // TODO validate the script
    }
  }

  public String getTxHash()
  {
    return this.txHash;
  }

  public int getTxIndex()
  {
    return this.txIndex;
  }

  public Script getScript()
  {
    return this.script.orNull();
  }

  public long getSequence()
  {
    return this.sequence;
  }

  // Standard object methods follow
  @Override
  public String toString()
  {
    return Objects.toStringHelper(this)
                  .add("sequence", this.sequence)
                  .add("script", this.script)
                  .toString();
  }

  @Override
  public boolean equals(final Object that)
  {
    return (that instanceof TransactionInput) && (this.compareTo((TransactionInput)that) == 0);
  }

  @Override
  public int hashCode()
  {
    return Objects.hashCode(this.sequence, this.script);
  }

  @Override
  public int compareTo(final TransactionInput that)
  {
    return ComparisonChain.start()
                          .compare(this.sequence, that.sequence)
                          .compare(this.script.orNull(), that.script.orNull(), Ordering.natural().nullsFirst())
                          .result();
  }

  /**
   * Builder for transaction inputs.  This is the only way
   * in which a transaction input should be created
   */
  public static class Builder
  {
    private String txHash;
    private int txIndex;
    private Long sequence;
    private Script script;

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
    public Builder(final TransactionInput prior)
    {
      this.txHash = prior.txHash;
      this.txIndex = prior.txIndex;
      this.sequence = prior.sequence;
      this.script = prior.script.orNull();
    }

    public Builder txHash(final String txHash)
    {
      this.txHash = txHash;
      return this;
    }

    public Builder txIndex(final int txIndex)
    {
      this.txIndex = txIndex;
      return this;
    }

    /**
     * Set the sequence
     * @param sequence the sequence
     * @return
     */
    public Builder sequence(final Long sequence)
    {
      this.sequence = sequence;
      return this;
    }

    /**
     * Set the script
     * @param script the script
     * @return
     */
    public Builder script(final Script script)
    {
      this.script = script;
      return this;
    }


    /**
     * Build the transaction input
     * @return a new transaction input
     */
    public TransactionInput build()
    {
      return new TransactionInput(this.txHash, this.txIndex, this.sequence, this.script);
    }
  }
}
