package com.wealdtech.bitcoin.generator;

import java.nio.ByteBuffer;

public interface Generator<T>
{
  void startGen();

  void startGen(final ByteBuffer inBuf);

  void generate(final T input);

  String finishGen();
}
