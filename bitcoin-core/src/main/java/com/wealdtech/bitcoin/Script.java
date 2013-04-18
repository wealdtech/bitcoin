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
package com.wealdtech.bitcoin;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

public class Script implements Comparable<Script>
{

  // Standard object methods follow
  @Override
  public String toString()
  {
    return Objects.toStringHelper(this)
                  .toString();
  }

  @Override
  public boolean equals(final Object that)
  {
    return (that instanceof Script) && (this.compareTo((Script)that) == 0);
  }
  
  @Override
  public int hashCode()
  {
    return Objects.hashCode(1);
  }

  @Override
  public int compareTo(final Script that)
  {
    return ComparisonChain.start()
                          .result();
  }
}
