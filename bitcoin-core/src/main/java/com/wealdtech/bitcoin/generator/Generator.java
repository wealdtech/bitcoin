package com.wealdtech.bitcoin.generator;

import java.io.ByteArrayOutputStream;

public interface Generator<T>
{
  void startGen();

  void startGen(final ByteArrayOutputStream inBuf);

  void generate(final T input);

  void generate(final T input, final boolean includeLength);

  byte[] finishGen();
}
