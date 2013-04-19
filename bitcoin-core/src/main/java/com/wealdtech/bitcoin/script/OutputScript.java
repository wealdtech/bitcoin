package com.wealdtech.bitcoin.script;

import static com.wealdtech.bitcoin.script.Opcode.*;

import java.util.ArrayList;
import java.util.List;

import com.wealdtech.bitcoin.Address;

/**
 * Helper class to generate outputs
 */
public class OutputScript
{
  public static Script create(final Address recipient)
  {
    List<Op> ops = new ArrayList<>();
    ops.add(new Op(OP_DUP));
    ops.add(new Op(OP_HASH160));
    ops.add(new Op(recipient.getHash160()));
    ops.add(new Op(OP_EQUALVERIFY));
    ops.add(new Op(OP_CHECKSIG));

    return new Script(ops);
  }
}
