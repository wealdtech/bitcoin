package com.wealdtech.bitcoin.script;

import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * A script is a sequence of operations.
 */
public class Script implements Comparable<Script>
{
  private final ImmutableList<Op> ops;


  /**
   * Create a new script, containing a number
   * of operations.
   * @param ops a list of operations
   */
  public Script(final List<Op> ops)
  {
    if (this.ops == null)
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