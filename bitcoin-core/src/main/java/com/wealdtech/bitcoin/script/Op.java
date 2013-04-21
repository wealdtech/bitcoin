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

import java.io.Serializable;

/**
 * An Op is a single operation.
 * It is either an opcode or a set of data.
 */
public class Op implements Serializable
{
  private static final long serialVersionUID = 8035542146562071509L;

  private final Opcode opcode;
  private final byte[] data;

  public Op(final Opcode opcode)
  {
    this.opcode = opcode;
    this.data = null;
  }

  public Op(final byte[] data)
  {
    this.opcode = null;
    this.data = new byte[data.length];
    System.arraycopy(data, 0, this.data, 0, data.length);
  }

  public Opcode getOpcode()
  {
    return this.opcode;
  }

  public byte[] getData()
  {
    byte[] retdata = new byte[this.data.length];
    System.arraycopy(this.data, 0, retdata, 0, this.data.length);
    return retdata;
  }

  public boolean isOpcode()
  {
    return this.opcode != null;
  }
}
