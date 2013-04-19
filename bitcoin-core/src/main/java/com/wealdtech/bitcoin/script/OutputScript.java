package com.wealdtech.bitcoin.script;

/**
 * A simple script to output to a single recipient
 */
public class OutputScript
{
  public static Script create()
  {
    Script script = new Script();

    return script;
  }
}
