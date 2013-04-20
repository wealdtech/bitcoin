package test.com.wealdtech.bitcoin.generator.raw;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.wealdtech.bitcoin.Address;
import com.wealdtech.bitcoin.Value;
import com.wealdtech.bitcoin.crypto.Sha256Hash;
import com.wealdtech.bitcoin.generator.Generator;
import com.wealdtech.bitcoin.generator.raw.TransactionGeneratorRawImpl;
import com.wealdtech.bitcoin.generator.raw.Utils;
import com.wealdtech.bitcoin.script.InputScript;
import com.wealdtech.bitcoin.script.OutputScript;
import com.wealdtech.bitcoin.transaction.Transaction;
import com.wealdtech.bitcoin.transaction.TransactionInput;
import com.wealdtech.bitcoin.transaction.TransactionOutput;

public class TransactionGeneratorRawImplTest
{
  @Test
  public void testjgm() throws Exception
  {
    List<TransactionInput> inputs = new ArrayList<>();
    inputs.add(new TransactionInput.Builder()
                                   .txHash(new Sha256Hash("c9a8171a4b753f7263c0bcb69319229539650fb6a43427a644e8ca33ca38a749"))
                                   .txIndex(0)
                                   .script(InputScript.create(new byte[] {(byte)0x30, (byte)0x44, (byte)0x02, (byte)0x20, (byte)0x39, (byte)0x93, (byte)0x40, (byte)0x0b, (byte)0x0f, (byte)0x5c, (byte)0x51, (byte)0xcc, (byte)0x6c, (byte)0xeb, (byte)0x7f, (byte)0x9e, (byte)0x23, (byte)0xca, (byte)0xb5, (byte)0xfc, (byte)0xf2, (byte)0xfb, (byte)0xfa, (byte)0x04, (byte)0xb7, (byte)0x63, (byte)0xea, (byte)0x64, (byte)0x64, (byte)0x19, (byte)0xc4, (byte)0x5e, (byte)0x13, (byte)0x22, (byte)0xd0, (byte)0x14, (byte)0x02, (byte)0x20, (byte)0x75, (byte)0xf7, (byte)0xbf, (byte)0xe4, (byte)0xf9, (byte)0x18, (byte)0xe2, (byte)0x6b, (byte)0x79, (byte)0xac, (byte)0x8e, (byte)0x32, (byte)0x18, (byte)0xff, (byte)0x1d, (byte)0xda, (byte)0x5a, (byte)0x2b, (byte)0x29, (byte)0x13, (byte)0xf0, (byte)0xc1, (byte)0xc9, (byte)0x31, (byte)0x50, (byte)0x0c, (byte)0x05, (byte)0xd6, (byte)0x1d, (byte)0x9c, (byte)0x22, (byte)0x68, (byte)0x01}, new byte[] {(byte)0x02, (byte)0x83, (byte)0xba, (byte)0xe8, (byte)0x14, (byte)0xda, (byte)0xd5, (byte)0xbc, (byte)0x2a, (byte)0x72, (byte)0x3b, (byte)0x6b, (byte)0xa1, (byte)0xc5, (byte)0x9a, (byte)0xbd, (byte)0x10, (byte)0x76, (byte)0x15, (byte)0xa3, (byte)0x10, (byte)0xa2, (byte)0x9f, (byte)0xe4, (byte)0x10, (byte)0xe4, (byte)0x1a, (byte)0x63, (byte)0x0f, (byte)0x12, (byte)0xb1, (byte)0x0e, (byte)0x74}))
                                   .build());

    List<TransactionOutput> outputs = new ArrayList<>();
    outputs.add(new TransactionOutput.Builder()
                                     .value(Value.fromLong(500000000L))
                                     .script(OutputScript.create(new Address("mxLrkWEwx5j8Qchydc8ghGFdCTHkEJoUsF")))
                                     .build());
    outputs.add(new TransactionOutput.Builder()
                                     .value(Value.fromLong(650000000L))
                                     .script(OutputScript.create(new Address("mmeH4w8dT57mPVwiwNTquQKybneCsb4q4Y")))
                                     .build());

    Transaction trans = new Transaction.Builder()
                                       .version(1)
                                       .inputs(inputs)
                                       .outputs(outputs)
                                       .lockTime(0)
                                       .build();

    Generator<Transaction> gen = new TransactionGeneratorRawImpl();
    gen.startGen();
    gen.generate(trans);
    final String raw = Utils.bytesToHexString(gen.finishGen());
    assertEquals(raw, "01000000");
  }
}
