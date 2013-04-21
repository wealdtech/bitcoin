package com.wealdtech.bitcoin.script;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * A script is a sequence of operations.
 */
public class Script implements Serializable, Comparable<Script>
{
  private static final long serialVersionUID = -4548052402380963962L;

  private final ImmutableList<Op> ops;

  /**
   * Create a new script, containing a number
   * of operations.
   * @param ops a list of operations
   */
  public Script(final List<Op> ops)
  {
    if (ops == null)
    {
      this.ops = ImmutableList.of();
    }
    else
    {
      this.ops = ImmutableList.copyOf(ops);
    }
  }

  public ImmutableList<Op> getOps()
  {
    return this.ops;
  }

  @Override
  public int compareTo(Script o)
  {
    // FIXME Auto-generated method stub
    return 0;
  }
}