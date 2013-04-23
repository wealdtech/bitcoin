import java.math.BigInteger;

import org.testng.annotations.Test;

import com.google.bitcoin.core.Address;
import com.google.bitcoin.core.DumpedPrivateKey;
import com.google.bitcoin.core.ECKey;
import com.google.bitcoin.core.NetworkParameters;
import com.google.bitcoin.core.Script;
import com.google.bitcoin.core.Transaction;
import com.google.bitcoin.core.Transaction.SigHash;
import com.google.bitcoin.core.TransactionOutput;
import com.google.bitcoin.core.Utils;
import com.google.bitcoin.core.Wallet;
import com.google.bitcoin.core.WalletTransaction;
import com.google.bitcoin.core.WalletTransaction.Pool;

public class TestBitcoinJ
{
  @Test
  public void testSigning() throws Exception
  {
    NetworkParameters network = NetworkParameters.testNet3();
    Wallet wallet = new Wallet(network);
    DumpedPrivateKey privKey = new DumpedPrivateKey(network, "92uW3Z2Qi9jSUwzf5akWi1hLudTopB7fpDXDmxMMg9845ULUmqg");
    ECKey key = privKey.getKey();
    wallet.addKey(key);

    // The transaction from which are spending funds
    Transaction prevTx = new Transaction(network, Utils.parseAsHexOrBase58("0100000001bdb60022a4804009e915dd2d84c181b7bba9a50fe697941a29d77eace9cd047b000000008b48304502207ae034494a74af1e96f52a57a3990b16728661f488a8e0a2a8bbd1914cadedf302210089378cf2f552ab0b66f929ed5fc794f7ba31a9bf56ec096cc1e6afab036a3a43014104cc1f08de2d9b5af3056658da4045a08b6bd78c753a534c62fcb8c6f5147e8a39a11be776628dc553cc1f384093fc351e2cf029b91a0e4aa88172a8064fb00ef7ffffffff02401f9e39000000001976a914869de901fdd86ded48eef944ce954415145f67da88acc0aafc01000000001976a914176ff06d7befb7a6fae3ebc0910576e00ecbe82988ac00000000"));
    wallet.addWalletTransaction(new WalletTransaction(Pool.UNSPENT, prevTx));

    // The transaction to spend funds
    Transaction curTx = new Transaction(network);
    curTx.addInput(prevTx.getOutput(0));
    TransactionOutput curTo = new TransactionOutput(network, curTx, new BigInteger("966664000", 10), Script.createOutputScript(new Address(network, "msrc25tv5yEr2ugqGCipdDVGrhrPoNNqWj")));
    curTx.addOutput(curTo);
    wallet.addWalletTransaction(new WalletTransaction(Pool.UNSPENT, curTx));

    System.err.println("Pre-signing: " + Utils.bytesToHexString(curTx.bitcoinSerialize()));
    curTx.signInputs(SigHash.ALL, wallet);
    System.err.println("Post-signing: " + Utils.bytesToHexString(curTx.bitcoinSerialize()));
  }
}
