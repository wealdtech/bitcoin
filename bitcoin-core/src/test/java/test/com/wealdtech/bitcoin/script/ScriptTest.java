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

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;
import com.wealdtech.DataError;
import com.wealdtech.bitcoin.script.Op;
import com.wealdtech.bitcoin.script.Opcode;
import com.wealdtech.bitcoin.script.Script;

public class ScriptTest
{
  @Test
  public void testParseValid1() throws Exception
  {
    Script script = new Script(ImmutableList.<Op>of(new Op(Opcode.OP_NOP)));
    assertNotNull(script);
    script.toString();
    script.hashCode();
    assertEquals(script, script);
    assertNotEquals(null, script);
    assertNotEquals(script, null);
    assertNotNull(script.getOps());
    assertEquals(script.getOps().get(0).getOpcode(), Opcode.OP_NOP);
  }

  @Test
  public void testParseInvalid1() throws Exception
  {
    try
    {
      new Script(ImmutableList.<Op>of());
      fail("Managed to create empty script");
    }
    catch (DataError.Bad de)
    {
      // Good
    }
  }

}
