package com.wealdtech.bitcoin.transaction;

import java.util.ArrayList;
import java.util.List;

import com.wealdtech.bitcoin.Address;
import com.wealdtech.bitcoin.Value;
import com.wealdtech.bitcoin.script.OutputScript;

/**
 * A {@link Transaction} builder to spend coins from one or more previous
 * transactions. The transaction requires the following items:
 * <ul>
 * <li>The hash of one or more existing transactions, which contain the coin to spend
 * <li>One or more sets of outputs, containing the amount of coin to spend and the address to which to send it
 * <li>A map of {@link ECKey}
 * </ul>
 * There are a number of important notes
 */
public class SimpleSpendTransactionBuilder
{
  private final Transaction.Builder builder;

  private final List<TransactionInput> inputs;
  private final List<TransactionOutput> outputs;

  public SimpleSpendTransactionBuilder()
  {
    this.builder = new Transaction.Builder();
    this.inputs = new ArrayList<TransactionInput>();
    this.outputs = new ArrayList<TransactionOutput>();
  }

  /**
   * Send an amount of bitcoins to a recipient as part of this transaction
   * @param value the value of the bitcoins to spend
   * @param recipient the recipient of the bitcoins
   */
  public void sendToRecipient(final Value value, final Address recipient)
  {
    TransactionOutput output = new TransactionOutput.Builder()
                                                    .value(value)
                                                    .script(OutputScript.create(recipient))
                                                    .build();
    this.outputs.add(output);
  }

  public Transaction build()
  {
    this.builder.inputs(this.inputs);
    this.builder.outputs(this.outputs);
    return this.builder.build();
  }
}
