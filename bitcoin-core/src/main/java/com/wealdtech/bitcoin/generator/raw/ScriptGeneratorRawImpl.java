package com.wealdtech.bitcoin.generator.raw;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.wealdtech.bitcoin.generator.Generator;
import com.wealdtech.bitcoin.script.Op;
import com.wealdtech.bitcoin.script.Opcode;
import com.wealdtech.bitcoin.script.Script;

public class ScriptGeneratorRawImpl extends BaseGeneratorRawImpl implements Generator<Script>
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
  public ByteBuffer generate(final Script script, final ByteBuffer buf)
  {
    for (Op op : script.getOps())
    {
      if (op.isOpcode())
      {
        // Simple opcode
        buf.put(op.getOpcode().getCode());
      }
      else
      {
        // Data; exact output depends on length of the data
        byte[] data = op.getData();
        if (data.length < Opcode.OP_PUSHDATA1.getCode())
        {
          buf.put((byte)data.length);
          buf.put(data);
        }
        else if (data.length < 256)
        {
          buf.put(Opcode.OP_PUSHDATA1.getCode());
          buf.put((byte)data.length);
          buf.put(data);
        }
        else if (data.length < 65536)
        {
          buf.put(Opcode.OP_PUSHDATA2.getCode());
          buf.put((byte)(0xFF & (data.length)));
          buf.put((byte)(0xFF & (data.length >> 8)));
          buf.put(data);
        }
        else
        {
          throw new RuntimeException("Unimplemented");
        }
      }
    }
    return buf;
  }
}
