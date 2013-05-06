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
package test.com.wealdtech.bitcoin.script;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Bytes;
import com.wealdtech.bitcoin.generator.raw.Utils;
import com.wealdtech.bitcoin.script.Op;

public class OpTest
{
  @Test
  public void testCompareTo1() throws Exception
  {
    final Op op1 = new Op(Utils.hexStringToBytes("010203"));
    final Op op2 = new Op(Utils.hexStringToBytes("010203"));
    assertTrue(op1.compareTo(op2) == 0);
  }

  @Test
  public void testCompareTo2() throws Exception
  {
    final Op op1 = new Op(Utils.hexStringToBytes("010203"));
    final Op op2 = new Op(Utils.hexStringToBytes("010202"));
    assertTrue(op1.compareTo(op2) == 1);
  }

  @Test
  public void testCompareTo3() throws Exception
  {
    final Op op1 = new Op(ImmutableList.copyOf(Bytes.asList()));
    final Op op2 = new Op(ImmutableList.copyOf(Bytes.asList()));
    assertTrue(op1.compareTo(op2) == 0);
  }
}
