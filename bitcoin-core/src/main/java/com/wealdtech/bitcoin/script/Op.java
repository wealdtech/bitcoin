package com.wealdtech.bitcoin.script;

/**
 * An Op is a single operation.
 * It is either an opcode or a set of data.
 */
public class Op
{
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
    // TODO copy the data
    this.data = data;
  }

  public Opcode getOpcode()
  {
    return this.opcode;
  }

  public byte[] getData()
  {
    return this.data;
  }

  public boolean isOpcode()
  {
    return this.opcode != null;
  }
}
