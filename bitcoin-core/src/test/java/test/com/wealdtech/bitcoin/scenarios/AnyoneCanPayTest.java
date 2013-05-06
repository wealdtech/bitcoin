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
package test.com.wealdtech.bitcoin.scenarios;

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
import com.wealdtech.bitcoin.script.Script;
import com.wealdtech.bitcoin.script.StandardOutputScript;
import com.wealdtech.bitcoin.transaction.HashType;
import com.wealdtech.bitcoin.transaction.Transaction;
import com.wealdtech.bitcoin.transaction.TransactionInput;
import com.wealdtech.bitcoin.transaction.TransactionOutput;

public class AnyoneCanPayTest
{

  @Test
  public void testAnyoneCanPay1() throws Exception
  {
    // Anyone can pay source 1:
    //   Bitcoin address: mne11ovE3uUkJdymdR8ACqdDQcZALeW7UM
    //   Private key:     92LCUMEYsdvX9zsjPKSo4Fv5eGXCZ711zhz4T7qQHCGin2WfHVF
    //   Source txHash:   f1a86aa7d2b9bff4460cf3141e385eabc2d0ea9a867f38c6ec1a0dba43cb5602
    // Anyone can pay source 2:
    //   Bitcoin address: mne12jHSA5veyY6oCuXQKj4EbJfB6ncBYS
    //   Private key:     91ef891kmP1zQBxH4BcfxoCEB1oTmCPNdRAUdw8S9JDArw4h1YH
    //   Source txHash:   6b0f7253af28e39de86e64721e991416fa9063be20b09211b62befce0303d79d

    // Anyone can pay sink:
    //   Bitcoin address: mne13JWeXkD1RLaiiyLGFu4YpbFP2k6UAN
    //   Private key:     92uaaww6NWwW6gwXSeGj9SJcNNkMnoZL6QFxhhgCqnRdgu16ASf
    //   Source txHash:   148313791fe862ffb41eaa9804a88a7bc90c9f8d8069e7b49a5904abb67d7862

    final Map<Sha256Hash, ECKey> signingKeys = new HashMap<>();
    final Map<Sha256Hash, Script> signingScripts = new HashMap<>();

    //  Create initial transaction.  This consists of the following:
    //    - 1 input (for transaction fee) coming from the sink
    //    - 1 output (for funds) going to the sink


    List<TransactionInput> inputs = new ArrayList<>();
    Sha256Hash txHash = Sha256Hash.fromHexString("81cc05ed20336988620a0a716c37636e7818374c840948adf85880ada200a7ea");
    inputs.add(new TransactionInput.Builder()
                                   .txHash(txHash)
                                   .txIndex(0)
                                   .build());
    // We need the private key for the address to which the above transaction was sent, and the
    // script that was part of the output of the input transaction
    signingKeys.put(txHash, ECKey.fromBase58String("92uW3Z2Qi9jSUwzf5akWi1hLudTopB7fpDXDmxMMg9845ULUmqg", "msnk1YwD2dqr3sg8bGxFVLLiPWPbFB75e3"));
    signingScripts.put(txHash, StandardOutputScript.create(Address.fromAddressString("msnk1YwD2dqr3sg8bGxFVLLiPWPbFB75e3")));
    List<TransactionOutput> outputs = new ArrayList<>();
    outputs.add(new TransactionOutput.Builder()
                                     .value(Value.fromLong(966664000L))
                                     .script(StandardOutputScript.create(Address.fromAddressString("msrc25tv5yEr2ugqGCipdDVGrhrPoNNqWj")))
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
    // TODO verify the signature?
  }
}
