package com.wealdtech.bitcoin.generator.raw;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.wealdtech.bitcoin.Transaction;
import com.wealdtech.bitcoin.generator.TransactionGenerator;
import com.wealdtech.bitcoin.transaction.TransactionInput;
import com.wealdtech.bitcoin.transaction.TransactionOutput;

public class TransactionGeneratorRawImpl implements TransactionGenerator
{
  private static final Logger LOGGER = LoggerFactory.getLogger(TransactionGeneratorRawImpl.class);

  // Maximum size of a transaction in bytes
  // TODO
  private static final int MAX_SIZE = 99999;

  private final ByteBuffer buf;
  private final int offset;

  private Transaction transaction;

  @Inject
  public TransactionGeneratorRawImpl()
  {
    this.buf = ByteBuffer.allocate(MAX_SIZE);
    this.buf.order(ByteOrder.LITTLE_ENDIAN);
    this.offset = 0;
  }

  @Override
  public void startGen(final Transaction transaction)
  {
    this.transaction = transaction;
  }

  @Override
  public void generate()
  {
    this.buf.putInt(this.transaction.getVersion());
    this.buf.put(Utils.longToVarintHexChars(this.transaction.getInputs().size()));
    for (TransactionInput input : this.transaction.getInputs())
    {
      this.buf.put(input.getTxHash().geHash());
      this.buf.putInt(input.getTxIndex());
      // TODO script length and script

    }
    this.buf.put(Utils.longToVarintHexChars(this.transaction.getOutputs().size()));
    for (TransactionOutput output : this.transaction.getOutputs())
    {
      this.buf.putLong(output.getValue().getSatoshis());
      // TODO
//      byte[] scriptBytes = output.getScript();
//      this.buf.put(Utils.longToVarintHexChars(output.getScript()));
    }
    this.buf.limit(this.buf.position());
  }

  @Override
  public String finishGen()
  {
    return Utils.byteBufferToHexString(this.buf);
  }
}
