package com.wealdtech.bitcoin.generator.raw;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.wealdtech.bitcoin.generator.Generator;
import com.wealdtech.bitcoin.transaction.Transaction;
import com.wealdtech.bitcoin.transaction.TransactionInput;
import com.wealdtech.bitcoin.transaction.TransactionOutput;

public class TransactionGeneratorRawImpl extends BaseGeneratorRawImpl<Transaction> implements Generator<Transaction>
{
  private static final Logger LOGGER = LoggerFactory.getLogger(TransactionGeneratorRawImpl.class);

  // Maximum size of a transaction in bytes
  // TODO
  private static final int MAX_SIZE = 99999;

  @Inject
  public TransactionGeneratorRawImpl()
  {
  }

  @Override
  public void startGen()
  {
    super.startGen(null, MAX_SIZE);
  }

  @Override
  public void startGen(final ByteBuffer inBuf)
  {
    super.startGen(inBuf, MAX_SIZE);
  }

  @Override
  public void generate(final Transaction transaction)
  {
    this.buf.putInt(transaction.getVersion());
    this.buf.put(Utils.longToVarintHexChars(transaction.getInputs().size()));
    for (TransactionInput input : transaction.getInputs())
    {
      this.buf.put(input.getTxHash().getHash());
      this.buf.putInt(input.getTxIndex());
      // TODO script length and script

    }
    this.buf.put(Utils.longToVarintHexChars(transaction.getOutputs().size()));
    for (TransactionOutput output : transaction.getOutputs())
    {
      this.buf.putLong(output.getValue().getSatoshis());
      // TODO
//      byte[] scriptBytes = output.getScript();
//      this.buf.put(Utils.longToVarintHexChars(output.getScript()));
    }
  }
}
