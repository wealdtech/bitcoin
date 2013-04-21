package com.wealdtech.bitcoin.transaction;

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

  public SimpleSpendTransactionBuilder()
  {
    this.builder = new Transaction.Builder();
  }

  public Transaction build()
  {
    return this.builder.build();
  }
}
