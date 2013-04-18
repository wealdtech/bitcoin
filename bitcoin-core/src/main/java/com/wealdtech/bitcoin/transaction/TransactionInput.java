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

  private final long sequence;
  private final Optional<Script> scriptSig;
  
  /**
   * Create a transaction input.  Note that this is a private constructor; 
   * all transaction inputs should be created using the supplied builder.
   */
  private TransactionInput(final Long sequence, final Script scriptSig)
  {
    if (sequence == null)
    {
      this.sequence = SEQUENCE_MAX;
    }
    else
    {
      this.sequence = sequence;
    }
    this.scriptSig = Optional.fromNullable(scriptSig);
  }

  private void validate()
  {
    checkState(this.sequence == SEQUENCE_MAX, "Invalid sequence number");
    if (this.scriptSig.isPresent())
    {
      // TODO validate the script
    }
  }

  // Standard object methods follow
  @Override
  public String toString()
  {
    return Objects.toStringHelper(this)
                  .add("sequence", this.sequence)
                  .add("scriptSig", this.scriptSig)
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
    return Objects.hashCode(this.sequence, this.scriptSig);
  }

  @Override
  public int compareTo(final TransactionInput that)
  {
    return ComparisonChain.start()
                          .compare(this.sequence, that.sequence)
                          .compare(this.scriptSig.orNull(), that.scriptSig.orNull(), Ordering.natural().nullsFirst())
                          .result();
  }

  /**
   * Builder for transaction inputs.  This is the only way
   * in which a transaction input should be created
   */
  public static class Builder
  {
    private Long sequence;
    private Script scriptSig;

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
      this.sequence = prior.sequence;
      this.scriptSig = prior.scriptSig.orNull();
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
     * Set the script signature
     * @param scriptSig the script signature
     * @return
     */
    public Builder scriptSig(final Script scriptSig)
    {
      this.scriptSig = scriptSig;
      return this;
    }


    /**
     * Build the transaction input
     * @return a new transaction input
     */
    public TransactionInput build()
    {
      return new TransactionInput(this.sequence, this.scriptSig);
    }
  }
}
