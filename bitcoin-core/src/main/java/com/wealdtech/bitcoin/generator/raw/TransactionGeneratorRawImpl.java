package com.wealdtech.bitcoin.generator.raw;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.wealdtech.ServerError;
import com.wealdtech.bitcoin.generator.Generator;
import com.wealdtech.bitcoin.script.Script;
import com.wealdtech.bitcoin.transaction.Transaction;
import com.wealdtech.bitcoin.transaction.TransactionInput;
import com.wealdtech.bitcoin.transaction.TransactionOutput;

public class TransactionGeneratorRawImpl extends BaseGeneratorRawImpl<Transaction> implements Generator<Transaction>
{
  private static final Logger LOGGER = LoggerFactory.getLogger(TransactionGeneratorRawImpl.class);

  @Inject
  public TransactionGeneratorRawImpl()
  {
  }

  @Override
  public void startGen(final ByteArrayOutputStream baos)
  {
    super.startGen(baos);
  }

  @Override
  public void generate(final Transaction transaction, final boolean includeLength)
  {
    ByteArrayOutputStream transBaos;
    if (includeLength)
    {
      // We need to prepend the length so write this to a temporary baos
      // to allow us to do this
      transBaos = new ByteArrayOutputStream();
    }
    else
    {
      transBaos = this.baos;
    }

    Generator<Script> scriptGen = new ScriptGeneratorRawImpl();

    try
    {
      transBaos.write(Utils.longToUint32LE(transaction.getVersion()));
      transBaos.write(Utils.longToVarintLE(transaction.getInputs().size()));
      for (TransactionInput input : transaction.getInputs())
      {
        transBaos.write(Utils.reverseBytes(input.getTxHash().getHash()));
        transBaos.write(Utils.longToUint32LE(input.getTxIndex()));
        scriptGen.startGen(transBaos);
        scriptGen.generate(input.getScript(), true);
        transBaos.write(Utils.longToUint32LE(input.getSequence()));
      }
      transBaos.write(Utils.longToVarintLE(transaction.getOutputs().size()));
      for (TransactionOutput output : transaction.getOutputs())
      {
        transBaos.write(Utils.longToUint64LE(output.getValue().getSatoshis()));
        scriptGen.startGen(transBaos);
        scriptGen.generate(output.getScript(), true);
      }
      transBaos.write(Utils.longToUint32LE(transaction.getLockTime()));

      if (includeLength)
      {
        Utils.writeBytesWithLength(this.baos, transBaos.toByteArray());
      }
    }
    catch (IOException ioe)
    {
      LOGGER.warn("I/O error whilst generating transaction: " + ioe);
      throw new ServerError("Failed to generate transaction", ioe);
    }
  }
}
