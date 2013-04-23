/*
 *    Copyright 2013 Weald Technology Trading Limited
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.wealdtech.bitcoin.script;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Bytes;
import com.wealdtech.bitcoin.crypto.ECKey;
import com.wealdtech.bitcoin.crypto.ECSignature;

/**
 * A simple script to provide signatures for an existing transaction
 */
public class InputScript
{
  public static Script create(final ECSignature signature, final ECKey key)
  {
    List<Op> ops = new ArrayList<>();
    ops.add(new Op(signature.getBytes()));
    ops.add(new Op(ImmutableList.copyOf(Bytes.asList(key.getPubKey().toByteArray()))));

    return new Script(ops);
  }
}
