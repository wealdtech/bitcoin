package test.com.wealdtech.bitcoin.generator.raw;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.wealdtech.bitcoin.Transaction;
import com.wealdtech.bitcoin.generator.TransactionGenerator;
import com.wealdtech.bitcoin.generator.raw.TransactionGeneratorRawImpl;
import com.wealdtech.bitcoin.transaction.TransactionInput;

public class TestGeneratorRawImpl
{
  @Test
  public void testjgm() throws Exception
  {
    List<TransactionInput> inputs = new ArrayList<>();
    for (int i = 0; i < 4294967296L; i++)
    {
      inputs.add(new TransactionInput.Builder().scriptSig(null).build());
    }
    Transaction trans = new Transaction.Builder()
                                       .version(1)
                                       .inputs(inputs)
                                       .outputs(null)
                                       .lockTime(200000)
                                       .build();

    TransactionGenerator gen = new TransactionGeneratorRawImpl();
    gen.startGen(trans);
    gen.generate();
    final String raw = gen.finishGen();
    assertEquals(raw, "01000000");
  }
}
