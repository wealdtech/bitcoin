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

import static com.wealdtech.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import com.wealdtech.bitcoin.transaction.TransactionInput;
import com.wealdtech.bitcoin.transaction.TransactionOutput;

/**
 * A transaction contains inputs and outputs.
 *
 */
public class Transaction implements Serializable, Comparable<Transaction>
{
  private static final Logger LOGGER = LoggerFactory.getLogger(Transaction.class);

  private final long version;
  private final ImmutableList<TransactionInput> inputs;
  private final ImmutableList<TransactionOutput> outputs;
  private final long lockTime;

  /**
   * Create a transaction.  Note that this is a private constructor; all 
   * transactions should be created using the supplied builder.
   */
  private Transaction(final long version, final List<TransactionInput> inputs, final List<TransactionOutput> outputs, final long lockTime)
  {
    this.version = version;
    this.inputs = ImmutableList.copyOf(inputs);
    this.outputs = ImmutableList.copyOf(outputs);
    this.lockTime = lockTime;
    validate();
  }

  private void validate()
  {
    checkNotNull(inputs, "Inputs cannot be null");
    checkNotNull(outputs, "Outputs cannot be null");
  }

  // Standard object methods follow
  @Override
  public String toString()
  {
    return Objects.toStringHelper(this)
                  .add("version", this.version)
                  .add("inputs", this.inputs)
                  .add("outputs", this.outputs)
                  .add("lockTime", this.lockTime)
                  .toString();
  }

  @Override
  public boolean equals(final Object that)
  {
    return (that instanceof Transaction) && (this.compareTo((Transaction)that) == 0);
  }
  
  @Override
  public int hashCode()
  {
    return Objects.hashCode(this.version, this.inputs, this.outputs, this.lockTime);
  }

  @Override
  public int compareTo(final Transaction that)
  {
    return ComparisonChain.start()
                          .compare(this.version, that.version)
                          .compare(this.inputs, that.inputs, Ordering.<TransactionInput>natural().lexicographical())
                          .compare(this.outputs, that.outputs, Ordering.<TransactionOutput>natural().lexicographical())
                          .compare(this.lockTime, that.lockTime)
                          .result();
  }

  /**
   * Builder for transactions.  This is the only way
   * in which a transaction should be created
   */
  public static class Builder
  {
    private long version;
    private List<TransactionInput> inputs;
    private List<TransactionOutput> outputs;
    private long lockTime;

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
    public Builder(final Transaction prior)
    {
      this.version = prior.version;
      this.inputs = prior.inputs;
      this.outputs = prior.outputs;
      this.lockTime = prior.lockTime;
    }

    /**
     * Set the version
     * @param version the version
     * @return
     */
    public Builder version(final long version)
    {
      this.version = version;
      return this;
    }

    /**
     * Set the inputs
     * @param inputs the inputs
     * @return
     */
    public Builder inputs(final List<TransactionInput> inputs)
    {
      this.inputs = inputs;
      return this;
    }

    /**
     * Set the outputs
     * @param outputs the outputs
     * @return
     */
    public Builder oututs(final List<TransactionOutput> outputs)
    {
      this.outputs = outputs;
      return this;
    }

    /**
     * Set the lock time
     * @param lockTime the lock time
     * @return
     */
    public Builder lockTime(final long lockTime)
    {
      this.lockTime = lockTime;
      return this;
    }

    /**
     * Build the transaction
     * @return a new transaction
     */
    public Transaction build()
    {
      return new Transaction(this.version, this.inputs, this.outputs, this.lockTime);
    }
  }
}
