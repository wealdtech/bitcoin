package test.com.wealdtech.bitcoin.generator.raw;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.wealdtech.bitcoin.Transaction;
import com.wealdtech.bitcoin.Value;
import com.wealdtech.bitcoin.generator.TransactionGenerator;
import com.wealdtech.bitcoin.generator.raw.TransactionGeneratorRawImpl;
import com.wealdtech.bitcoin.transaction.TransactionInput;
import com.wealdtech.bitcoin.transaction.TransactionOutput;

public class GeneratorRawImplTest
{
  @Test
  public void testjgm() throws Exception
  {
    List<TransactionInput> inputs = new ArrayList<>();
    inputs.add(new TransactionInput.Builder().script(null).build());

    List<TransactionOutput> outputs = new ArrayList<>();
    outputs.add(new TransactionOutput.Builder().value(Value.fromLong(5L)).script(null).build());

    Transaction trans = new Transaction.Builder()
                                       .version(1)
                                       .inputs(inputs)
                                       .outputs(outputs)
                                       .lockTime(200000)
                                       .build();

    TransactionGenerator gen = new TransactionGeneratorRawImpl();
    gen.startGen(trans);
    gen.generate();
    final String raw = gen.finishGen();
    assertEquals(raw, "01000000");
  }
}
