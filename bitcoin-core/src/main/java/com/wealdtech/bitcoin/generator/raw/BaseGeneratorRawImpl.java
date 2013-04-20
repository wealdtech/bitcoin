package com.wealdtech.bitcoin.generator.raw;

import java.io.ByteArrayOutputStream;

import com.wealdtech.bitcoin.generator.Generator;


public abstract class BaseGeneratorRawImpl<T> implements Generator<T>
{
  protected ByteArrayOutputStream baos;

  @Override
  public void startGen()
  {
    startGen(null);
  }

  @Override
  public void startGen(final ByteArrayOutputStream baos)
  {
    if (baos == null)
    {
      this.baos = new ByteArrayOutputStream();
    }
    else
    {
      this.baos = baos;
    }
  }

  @Override
  public void generate(final T input)
  {
    generate(input, false);
  }

  @Override
  public byte[] finishGen()
  {
    return this.baos.toByteArray();
  }
}
