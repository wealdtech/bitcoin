package com.wealdtech.bitcoin.generator;

import java.nio.ByteBuffer;

public interface Generator<T>
{
  ByteBuffer generate(final T input, final ByteBuffer buf);

  String finishGen(final ByteBuffer buf);
}
