package com.wealdtech.bitcoin.generator;

import com.wealdtech.bitcoin.Transaction;

public interface TransactionGenerator
{
  void startGen(final Transaction transaction);

  void generate();

  String finishGen();
}
