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
package test.com.wealdtech.bitcoin.generator.raw;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.wealdtech.bitcoin.Address;
import com.wealdtech.bitcoin.generator.Generator;
import com.wealdtech.bitcoin.generator.raw.ScriptGeneratorRawImpl;
import com.wealdtech.bitcoin.generator.raw.Utils;
import com.wealdtech.bitcoin.script.OutputScript;
import com.wealdtech.bitcoin.script.Script;

public class ScriptGeneratorRawImplTest
{
  @Test
  public void testOutputScript1() throws Exception
  {
    Script script = OutputScript.create(new Address("mnR2ntXMDv2PaHCgCbby8iQg6TAv8Ecp5D"));

    Generator<Script> gen = new ScriptGeneratorRawImpl();
    gen.startGen();
    gen.generate(script, false);
    final String raw = Utils.bytesToHexString(gen.finishGen());
    assertEquals(raw, "1");
  }

}
