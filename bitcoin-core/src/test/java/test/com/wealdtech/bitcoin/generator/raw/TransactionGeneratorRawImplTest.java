/*
 *    Copyright 2013 Weald Technology Trading Limited
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package test.com.wealdtech.bitcoin.generator.raw;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;
import com.wealdtech.bitcoin.Address;
import com.wealdtech.bitcoin.Value;
import com.wealdtech.bitcoin.crypto.ECKey;
import com.wealdtech.bitcoin.crypto.Sha256Hash;
import com.wealdtech.bitcoin.generator.Generator;
import com.wealdtech.bitcoin.generator.raw.TransactionGeneratorRawImpl;
import com.wealdtech.bitcoin.generator.raw.Utils;
import com.wealdtech.bitcoin.script.OutputScript;
import com.wealdtech.bitcoin.script.Script;
import com.wealdtech.bitcoin.transaction.HashType;
import com.wealdtech.bitcoin.transaction.Transaction;
import com.wealdtech.bitcoin.transaction.TransactionInput;
import com.wealdtech.bitcoin.transaction.TransactionOutput;

public class TransactionGeneratorRawImplTest
{
//  @Test
//  public void testjgm() throws Exception
//  {
//    // This is based on transaction 3998e8b0ea86332636f43cb7dbfba19bb11ce78a7f6980ba9e24bd47c53d321a from testnet3
//    List<TransactionInput> inputs = new ArrayList<>();
//    inputs.add(new TransactionInput.Builder()
//                                   .txHash(Sha256Hash.fromHexString("c9a8171a4b753f7263c0bcb69319229539650fb6a43427a644e8ca33ca38a749"))
//                                   .txIndex(0)
//                                   .script(InputScript.create(Utils.hexStringToBytes("304402203993400b0f5c51cc6ceb7f9e23cab5fcf2fbfa04b763ea646419c45e1322d014022075f7bfe4f918e26b79ac8e3218ff1dda5a2b2913f0c1c931500c05d61d9c226801"),
//                                                              Utils.hexStringToBytes("0283bae814dad5bc2a723b6ba1c59abd107615a310a29fe410e41a630f12b10e74")))
//                                   .build());
//
//    List<TransactionOutput> outputs = new ArrayList<>();
//    outputs.add(new TransactionOutput.Builder()
//                                     .value(Value.fromLong(500000000L))
//                                     .script(OutputScript.create(Address.fromAddressString("mxLrkWEwx5j8Qchydc8ghGFdCTHkEJoUsF")))
//                                     .build());
//    outputs.add(new TransactionOutput.Builder()
//                                     .value(Value.fromLong(650000000L))
//                                     .script(OutputScript.create(Address.fromAddressString("mmeH4w8dT57mPVwiwNTquQKybneCsb4q4Y")))
//                                     .build());
//
//    Transaction trans = new Transaction.Builder()
//                                       .version(1)
//                                       .inputs(inputs)
//                                       .outputs(outputs)
//                                       .lockTime(0)
//                                       .build();
//
//    Generator<Transaction> gen = new TransactionGeneratorRawImpl();
//    gen.startGen();
//    gen.generate(trans);
//    final String raw = Utils.bytesToHexString(gen.finishGen());
//    assertEquals(raw, "010000000149a738ca33cae844a62734a4b60f653995221993b6bcc063723f754b1a17a8c9000000006a47304402203993400b0f5c51cc6ceb7f9e23cab5fcf2fbfa04b763ea646419c45e1322d014022075f7bfe4f918e26b79ac8e3218ff1dda5a2b2913f0c1c931500c05d61d9c226801210283bae814dad5bc2a723b6ba1c59abd107615a310a29fe410e41a630f12b10e74ffffffff020065cd1d000000001976a914b89159d15707db99eb2e655e72f6020f4157c95e88ac8036be26000000001976a91443335687d0875711af3fade9b9a72b3d3a5d52ff88ac00000000");
//  }

//  @Test
//  public void test81cc() throws Exception
//  {
//    // This is based on transaction 81cc05ed20336988620a0a716c37636e7818374c840948adf85880ada200a7ea from testnet3
//
//    final Map<Sha256Hash, ECKey> signingKeys = new HashMap<>();
//
//    List<TransactionInput> inputs = new ArrayList<>();
//    Sha256Hash txHash = Sha256Hash.fromHexString("7b04cde9ac7ed7291a9497e60fa5a9bbb781c1842ddd15e9094080a42200b6bd");
//    inputs.add(new TransactionInput.Builder()
//                                   .txHash(txHash)
//                                   .txIndex(0)
//                                   .build());
//    // Keys to sign the above input
//    signingKeys.put(txHash, ECKey.fromString("92fSgBLfJCpJ4ABNC7Q8h7Frj5v61vpHBx2o8HYKpqfZt4eaNF5"));
//
//    List<TransactionOutput> outputs = new ArrayList<>();
//    outputs.add(new TransactionOutput.Builder()
//                                     .value(Value.fromLong(966664000L))
//                                     .script(OutputScript.create(Address.fromAddressString("msnk1YwD2dqr3sg8bGxFVLLiPWPbFB75e3")))
//                                     .build());
//    outputs.add(new TransactionOutput.Builder()
//                                     .value(Value.fromLong(33336000L))
//                                     .script(OutputScript.create(Address.fromAddressString("mhesw8quju8LW4WNiwK3RGV8M2VMpNAjWH")))
//                                     .build());
//
//    Transaction trans = new Transaction.Builder()
//                                       .version(1)
//                                       .inputs(inputs)
//                                       .outputs(outputs)
//                                       .lockTime(0)
//                                       .build();
//
//    // Now that we have a complete transaction we need to sign the inputs
////    trans = trans.signInputs(ImmutableMap.copyOf(signingKeys), HashType.ALL);
//
//    Generator<Transaction> gen = new TransactionGeneratorRawImpl();
//    gen.startGen();
//    gen.generate(trans);
//    final String raw = Utils.bytesToHexString(gen.finishGen());
//    assertEquals(raw, "");
//
//  }

  @Test
  public void testNew() throws Exception
  {
    final Map<Sha256Hash, ECKey> signingKeys = new HashMap<>();
    final Map<Sha256Hash, Script> signingScripts = new HashMap<>();

    List<TransactionInput> inputs = new ArrayList<>();
    Sha256Hash txHash = Sha256Hash.fromHexString("81cc05ed20336988620a0a716c37636e7818374c840948adf85880ada200a7ea");
    inputs.add(new TransactionInput.Builder()
                                   .txHash(txHash)
                                   .txIndex(0)
                                   .build());
    // We need the private key for the address to which the above transaction was sent, and the
    // script that was part of the output of the input transaction
    signingKeys.put(txHash, ECKey.fromString("92uW3Z2Qi9jSUwzf5akWi1hLudTopB7fpDXDmxMMg9845ULUmqg"));
    // FIXME This isn't good enough, we need to use txHash:txIndex as an identifier
    signingScripts.put(txHash, OutputScript.create(Address.fromAddressString("msnk1YwD2dqr3sg8bGxFVLLiPWPbFB75e3")));
    List<TransactionOutput> outputs = new ArrayList<>();
    outputs.add(new TransactionOutput.Builder()
                                     .value(Value.fromLong(966664000L))
                                     .script(OutputScript.create(Address.fromAddressString("msrc25tv5yEr2ugqGCipdDVGrhrPoNNqWj")))
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
    assertEquals(raw, "0100000001eaa700a2ad8058f8ad4809844c3718786e63376c710a0a6288693320ed05cc810000000000ffffffff01401f9e39000000001976a9148758e755c9c2b9d8125ca024fb0b48d1517695b788ac00000000");

    // Now that we have a complete transaction we need to sign the inputs
    trans = trans.signInputs(ImmutableMap.copyOf(signingKeys), ImmutableMap.copyOf(signingScripts), HashType.ALL);

    gen.startGen();
    gen.generate(trans);
    final String rawSigned = Utils.bytesToHexString(gen.finishGen());
    assertEquals(rawSigned, "");
  }
}
