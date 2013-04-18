package com.wealdtech.bitcoin;

import static com.wealdtech.Preconditions.checkArgument;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.ImmutableMap;

/**
 * Handle Bitcoin values.
 */
public class Value
{
  private final long amount;

  private static final Pattern PATTERN = Pattern.compile("([\\d]+)(\\.[\\d]+)*[\\s]*(" +
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
  
  public Value(final long amount, final BTCUnit unit)
  {
    this.amount = BTCUnit.toSatoshis(amount, unit);
  }

  public long getSatoshis()
  {
    return this.amount;
  }

  /**
   * Calculate a value from a string representation
   * @param str string representation
   * @return the value
   */
  public static Value fromString(final String str)
  {
    final Matcher matcher = PATTERN.matcher(str);
    checkArgument(matcher.matches(), "Unable to parse BTC value \"%s\"", str);

    final Long amount = Long.parseLong(matcher.group(1));
    String subamount = null;
    if (matcher.group(2) != null)
    {
      subamount = matcher.group(2).substring(1, matcher.group(2).length());
      System.err.println("Subamount is " + subamount);
    }
    final BTCUnit units = SUFFIXES.get(matcher.group(3));
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
  public String toPrettyString()
  {
    return toPrettyString(true);
  }

  /**
   * Provide a string representation with suitable formatting given the amount
   * @param common If <code>true</code> then restrict format to commonly-used units
   */
  public String toPrettyString(final boolean common)
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


  // TODO standard object values
}
