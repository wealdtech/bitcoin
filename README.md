Bitcoin
========

A simple Java implementation of the core Bitcoin classes to handle generation of Bitcoin transactions.

Rationale
=========

The existing [Bitcoinj project](https://code.google.com/p/bitcoinj/) is a complete implementation of the Bitcoin protocol.  This makes it overkill for the most common Bitcoin operation: generation of transactions.

This project is focused on allowing developers to generate Bitcoin transactions with a minimal learning curve.

Sample
======

The most common use case is a standard Bitcoin transaction, where ownership of some amount of bitcoins is transferred from one owner to another:

    final SimpleSpendTransactionBuilder builder = new SimpleSpendTransactionBuilder();
    final Transaction trans = builder.build();

Once the transaction is built there are a number of options as to what to do with it, but the most common is to obtain a dump of it which can then be broadcast by any compliant bitcoin implementation:

    final Generator<Transaction> gen = new TransactionGeneratorRawImpl();
    gen.startGen();
    gen.generate(trans);
    final String raw = Utils.bytesToHexString(gen.finishGen());

License
=======

Copyright 2012 Weald Technology Trading Limited.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
