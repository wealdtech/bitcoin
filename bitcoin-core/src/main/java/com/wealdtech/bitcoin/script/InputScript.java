package com.wealdtech.bitcoin.script;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple script to provide signatures for an existing transaction
 */
public class InputScript
{
  public static Script create(final byte[] signature, final byte[] pubkey)
  {
    List<Op> ops = new ArrayList<>();
    ops.add(new Op(signature));
    ops.add(new Op(pubkey));

    return new Script(ops);
  }
}
