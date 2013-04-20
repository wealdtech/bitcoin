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

import static com.wealdtech.Preconditions.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableMap;

/**
 * Manage Bitcoin values. Values can be instantiated from string, longs or
 * BigDecimals. Values are held internally as an integer number of satoshis. Any
 * attempt to create a value less than 1 satoshi will return 0.
 */
public class Value implements Serializable, Comparable<Value>
{
  private static final long serialVersionUID = 7627675232479834704L;

  private final long amount;

  private static final Pattern PATTERN = Pattern.compile("([\\d]*\\.?[\\d]*)[\\s]*(" +
                                                         "[Ss]atoshis?|" +
                                                         "([uµmcdhkKM]|da)?BTCs?|" +
                                                         "[Bb]itcoins?)");

  private static final ImmutableMap<String, BTCUnit> SUFFIXES;
  static
  {
    final ImmutableMap.Builder<String, BTCUnit> suffixes = ImmutableMap.builder();

    suffixes.put("satoshi", BTCUnit.SATOSHIS);
    suffixes.put("Satoshi", BTCUnit.SATOSHIS);
    suffixes.put("satoshis", BTCUnit.SATOSHIS);
    suffixes.put("Satoshis", BTCUnit.SATOSHIS);

    suffixes.put("uBTC", BTCUnit.MICROBTCS);
    suffixes.put("µBTC", BTCUnit.MICROBTCS);

    suffixes.put("mBTC", BTCUnit.MILLIBTCS);

    suffixes.put("cBTC", BTCUnit.CENTIBTCS);

    suffixes.put("dBTC", BTCUnit.DECIBTCS);

    suffixes.put("BTC", BTCUnit.BTCS);
    suffixes.put("bitcoin", BTCUnit.BTCS);
    suffixes.put("Bitcoin", BTCUnit.BTCS);
    suffixes.put("bitcoins", BTCUnit.BTCS);
    suffixes.put("Bitcoins", BTCUnit.BTCS);

    suffixes.put("daBTC", BTCUnit.DECABTCS);

    suffixes.put("hBTC", BTCUnit.HECTOBTCS);

    suffixes.put("kBTC", BTCUnit.KILOBTCS);
    suffixes.put("KBTC", BTCUnit.KILOBTCS);

    suffixes.put("MBTC", BTCUnit.MEGABTCS);

    SUFFIXES = suffixes.build();
  }

  public Value(final BigDecimal amount, final BTCUnit unit)
  {
    this.amount = BTCUnit.toSatoshis(amount, unit);
  }

  public Value(final long amount, final BTCUnit unit)
  {
    this.amount = BTCUnit.toSatoshis(new BigDecimal(amount), unit);
  }

  public long getSatoshis()
  {
    return this.amount;
  }


  public static Value fromLong(final Long val)
  {
    return new Value(val, BTCUnit.SATOSHIS);
  }

  /**
   * Calculate a value from a string representation
   * @param str string representation
   * @return the value
   */
  public static Value fromString(final String str)
  {
    checkNotNull(str, "Missing value");

    final Matcher matcher = PATTERN.matcher(str);
    checkArgument(matcher.matches(), "Unable to parse BTC value \"%s\"", str);

    final BigDecimal amount = new BigDecimal(matcher.group(1));
    final BTCUnit units = SUFFIXES.get(matcher.group(2));
    return new Value(amount, units);
  }

  /**
   * Provide a string representation in BTC
   */
  public String toBTCString()
  {
    return toPrettyString(BTCUnit.BTCS);
  }

  /**
   * Provide a string representation in Satoshis
   */
  public String toSatoshiString()
  {
    return toPrettyString(BTCUnit.SATOSHIS);
  }

  /**
   * Provide a string representation with suitable formatting given the amount
   */
  public String toBestString()
  {
    return toBestString(true);
  }

  /**
   * Provide a string representation with suitable formatting given the amount
   *
   * @param common
   *          If <code>true</code> then restrict format to commonly-used units,
   *          if <code>false</code> then use less common units such as daBTC
   */
  public String toBestString(final boolean common)
  {
    return toPrettyString(BTCUnit.getBest(this.amount, common));
  }

  /**
   * Provide a string representation in a given unit
   */
  public String toPrettyString(final BTCUnit unit)
  {
    return unit.toPrettyString(this.amount);
  }

  // Standard object methods follow
  @Override
  public String toString()
  {
    return Objects.toStringHelper(this)
                  .add("amount", this.amount)
                  .toString();
  }

  @Override
  public boolean equals(final Object that)
  {
    return (that instanceof Value) && (this.compareTo((Value)that) == 0);
  }

  @Override
  public int hashCode()
  {
    return Objects.hashCode(this.amount);
  }

  @Override
  public int compareTo(final Value that)
  {
    return ComparisonChain.start()
                          .compare(this.amount, that.amount)
                          .result();
  }
}
