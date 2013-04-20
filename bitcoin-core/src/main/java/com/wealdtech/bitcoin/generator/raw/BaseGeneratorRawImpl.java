package com.wealdtech.bitcoin.generator.raw;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.wealdtech.bitcoin.generator.Generator;


public abstract class BaseGeneratorRawImpl<T> implements Generator<T>
{
  // TODO
  private static final int DEFAULT_SIZE = 99999;

  protected ByteBuffer buf;

  private ByteBuffer createByteBuffer(final int size)
  {
    final ByteBuffer buf = ByteBuffer.allocate(size);
    buf.order(ByteOrder.LITTLE_ENDIAN);
    return buf;
  }

  @Override
  public void startGen()
  {
    startGen(null, DEFAULT_SIZE);
  }

  @Override
  public void startGen(final ByteBuffer inBuf)
  {
    startGen(inBuf, DEFAULT_SIZE);
  }

  protected void startGen(final ByteBuffer inBuf, final int size)
  {
    if (inBuf == null)
    {
      this.buf = createByteBuffer(size);
    }
    else
    {
      this.buf = inBuf;
    }
  }

  @Override
  public String finishGen()
  {
    this.buf.limit(this.buf.position());
    return Utils.byteBufferToHexString(this.buf);
  }
}
