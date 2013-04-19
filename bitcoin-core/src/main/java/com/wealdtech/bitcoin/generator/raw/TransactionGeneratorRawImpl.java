package com.wealdtech.bitcoin.generator.raw;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.wealdtech.bitcoin.Transaction;
import com.wealdtech.bitcoin.generator.Generator;
import com.wealdtech.bitcoin.transaction.TransactionInput;
import com.wealdtech.bitcoin.transaction.TransactionOutput;

public class TransactionGeneratorRawImpl extends BaseGeneratorRawImpl implements Generator<Transaction>
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
  public ByteBuffer generate(final Transaction transaction, final ByteBuffer inBuf)
  {
    ByteBuffer buf;
    if (inBuf == null)
    {
      // Need to create our own
      buf = createByteBuffer(MAX_SIZE);
    }
    else
    {
      buf = inBuf;
    }

    buf.putInt(transaction.getVersion());
    buf.put(Utils.longToVarintHexChars(transaction.getInputs().size()));
    for (TransactionInput input : transaction.getInputs())
    {
      buf.put(input.getTxHash().getHash());
      buf.putInt(input.getTxIndex());
      // TODO script length and script

    }
    buf.put(Utils.longToVarintHexChars(transaction.getOutputs().size()));
    for (TransactionOutput output : transaction.getOutputs())
    {
      buf.putLong(output.getValue().getSatoshis());
      // TODO
//      byte[] scriptBytes = output.getScript();
//      this.buf.put(Utils.longToVarintHexChars(output.getScript()));
    }
    return buf;
  }
}
