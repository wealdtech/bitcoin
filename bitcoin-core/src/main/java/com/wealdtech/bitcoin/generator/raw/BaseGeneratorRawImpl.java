package com.wealdtech.bitcoin.generator.raw;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class BaseGeneratorRawImpl
{
  protected ByteBuffer createByteBuffer(final int size)
  {
    final ByteBuffer buf = ByteBuffer.allocate(size);
    buf.order(ByteOrder.LITTLE_ENDIAN);
    return buf;
  }

  public String finishGen(final ByteBuffer buf)
  {
    buf.limit(buf.position());
    return Utils.byteBufferToHexString(buf);
  }
}
