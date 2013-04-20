package test.com.wealdtech.bitcoin.generator.raw;

import static org.testng.Assert.assertEquals;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.wealdtech.bitcoin.Address;
import com.wealdtech.bitcoin.Value;
import com.wealdtech.bitcoin.crypto.Sha256Hash;
import com.wealdtech.bitcoin.generator.Generator;
import com.wealdtech.bitcoin.generator.raw.TransactionGeneratorRawImpl;
import com.wealdtech.bitcoin.generator.raw.Utils;
import com.wealdtech.bitcoin.script.OutputScript;
import com.wealdtech.bitcoin.transaction.Transaction;
import com.wealdtech.bitcoin.transaction.TransactionInput;
import com.wealdtech.bitcoin.transaction.TransactionOutput;

public class GeneratorRawImplTest
{
  @Test
  public void testjgm() throws Exception
  {
    List<TransactionInput> inputs = new ArrayList<>();
    inputs.add(new TransactionInput.Builder()
                                   .txHash(new Sha256Hash(Utils.hexStringToBytes("cf036824e654d8ef6dc3bf176e5c5202aed85a74e3e897800070145bd9a43220")))
                                   .txIndex(1)
                                   .script(null)
                                   .build());

    List<TransactionOutput> outputs = new ArrayList<>();
    outputs.add(new TransactionOutput.Builder()
                                     .value(Value.fromLong(5L))
                                     .script(OutputScript.create(new Address("mnR2ntXMDv2PaHCgCbby8iQg6TAv8Ecp5D")))
                                     .build());

    Transaction trans = new Transaction.Builder()
                                       .version(1)
                                       .inputs(inputs)
                                       .outputs(outputs)
                                       .lockTime(0)
                                       .build();

    Generator<Transaction> gen = new TransactionGeneratorRawImpl();
    ByteBuffer buf = gen.generate(trans, null);
    final String raw = gen.finishGen(buf);
    assertEquals(raw, "01000000");
  }
}
