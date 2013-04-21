package com.wealdtech.bitcoin.script;

import java.io.Serializable;

/**
 * An Op is a single operation.
 * It is either an opcode or a set of data.
 */
public class Op implements Serializable
{
  private static final long serialVersionUID = 8035542146562071509L;

  private final Opcode opcode;
  private final byte[] data;

  public Op(final Opcode opcode)
  {
    this.opcode = opcode;
    this.data = null;
  }

  public Op(final byte[] data)
  {
    this.opcode = null;
    this.data = new byte[data.length];
    System.arraycopy(data, 0, this.data, 0, data.length);
  }

  public Opcode getOpcode()
  {
    return this.opcode;
  }

  public byte[] getData()
  {
    byte[] retdata = new byte[this.data.length];
    System.arraycopy(this.data, 0, retdata, 0, this.data.length);
    return retdata;
  }

  public boolean isOpcode()
  {
    return this.opcode != null;
  }
}
