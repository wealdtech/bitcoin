package test.com.wealdtech.bitcoin.generator.raw;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.wealdtech.bitcoin.Address;
import com.wealdtech.bitcoin.generator.Generator;
import com.wealdtech.bitcoin.generator.raw.ScriptGeneratorRawImpl;
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
    gen.generate(script);
    final String raw = gen.finishGen();
    assertEquals(raw, "1");
  }

  @Test
  public void testInputScript1() throws Exception
  {
    Script script = OutputScript.create(new Address("mnR2ntXMDv2PaHCgCbby8iQg6TAv8Ecp5D"));

    Generator<Script> gen = new ScriptGeneratorRawImpl();
    gen.startGen();
    gen.generate(script);
    final String raw = gen.finishGen();
    assertEquals(raw, "1");
  }
}
