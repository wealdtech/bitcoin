package com.wealdtech.bitcoin.generator.raw;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.wealdtech.bitcoin.generator.Generator;
import com.wealdtech.bitcoin.script.Op;
import com.wealdtech.bitcoin.script.Opcode;
import com.wealdtech.bitcoin.script.Script;

public class ScriptGeneratorRawImpl extends BaseGeneratorRawImpl<Script> implements Generator<Script>
{
  private static final Logger LOGGER = LoggerFactory.getLogger(ScriptGeneratorRawImpl.class);

  // Maximum size of a script in bytes
  // TODO
  private static final int MAX_SIZE = 99999;

  @Inject
  public ScriptGeneratorRawImpl()
  {
  }

  @Override
  public void startGen()
  {
    super.startGen(null, MAX_SIZE);
  }

  @Override
  public void startGen(final ByteBuffer inBuf)
  {
    super.startGen(inBuf, MAX_SIZE);
  }

  @Override
  public void generate(final Script script)
  {
    for (Op op : script.getOps())
    {
      if (op.isOpcode())
      {
        // Simple opcode
        this.buf.put(op.getOpcode().getCode());
      }
      else
      {
        // Data; exact output depends on length of the data
        byte[] data = op.getData();
        if (data.length < Opcode.OP_PUSHDATA1.getCode())
        {
          this.buf.put((byte)data.length);
          this.buf.put(data);
        }
        else if (data.length < 256)
        {
          this.buf.put(Opcode.OP_PUSHDATA1.getCode());
          this.buf.put((byte)data.length);
          this.buf.put(data);
        }
        else if (data.length < 65536)
        {
          this.buf.put(Opcode.OP_PUSHDATA2.getCode());
          this.buf.put((byte)(0xFF & (data.length)));
          this.buf.put((byte)(0xFF & (data.length >> 8)));
          this.buf.put(data);
        }
        else
        {
          throw new RuntimeException("Unimplemented");
        }
      }
    }
  }
}
