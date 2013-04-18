package com.wealdtech.bitcoin;

import java.text.DecimalFormat;

/*
 * Handle the units used with bitcoins.
 */
public enum BTCUnit
{
  /**
   * Satoshis
   */
  SATOSHIS(1L, new DecimalFormat("################"), true),
  
  /**
   * Microbitcoins
   */
  MICROBTCS(100L, new DecimalFormat("#.##µBTC"), true),

  /**
   * Millibitcoins
   */
  MILLIBTCS(100000L, new DecimalFormat("#.#####mBTC"), true),

  /**
   * Centibitcoins
   */
  CENTIBTCS(1000000L, new DecimalFormat("#.######cBTC"), false),

  /**
   * Decibitcoins
   */
  DECIBTCS(10000000L, new DecimalFormat("#.#######dBTC"), false),

  /**
   * Bitcoins
   */
  BTCS(100000000L, new DecimalFormat("#.########BTC"), true),
  
  /**
   * Decabitcoins
   */
  DECABTCS(1000000000L, new DecimalFormat("#.#########daBTC"), false),
  
  /**
   * Hectobitcoins
   */
  HECTOBTCS(10000000000L, new DecimalFormat("#.##########hBTC"), false),
  
  /**
   * Kilobitcoins
   */
  KILOBTCS(100000000000L, new DecimalFormat("#.###########kBTC"), true),
  
  /**
   * Megabitcoins
   */
  MEGABTCS(100000000000000L, new DecimalFormat("#.##############MBTC"), true);
  
  private final long satoshis; // Number of satoshis in this unit
  private final DecimalFormat format; // Canonical string representation of amounts of this unit
  private final boolean common; // If this is a unit in common use

  BTCUnit(final long satoshis, final DecimalFormat format, final boolean common)
  {
    this.satoshis = satoshis;
    this.format = format;
    this.common = common;
  }

  private long convert(final long amount, final BTCUnit unit)
  {
    return (amount * unit.satoshis) / satoshis;
  }

  public static long toSatoshis(final long amount, final BTCUnit unit)
  {
    return SATOSHIS.convert(amount, unit);
  }

  /**
   * Return the best for a given amount of satoshis and only using common units.
   * Best is, of course, subjective.  We will attempt to find the unit which
   * fits gives us up to (but no more than) 3 digits before the decimal point.
   * @param amount the amount
   * @return the most suitable unit
   */
  public static BTCUnit getBest(final long amount)
  {
    return getBest(amount, true);
  }

  /**
   * Return the best for a given amount of satoshis.
   * Best is, of course, subjective.  We choose the unit which gives the
   * least number of digits before the decimal place, given that there should
   * be at least 1.
   * @param amount the amount
   * @param common if <code>true</code> then only use the most common units
   * @return the most suitable unit
   */
  public static BTCUnit getBest(final long amount, final boolean common)
  {
    BTCUnit best = null;

    // Special case: 0 BTC
    if (amount == 0)
    {
      return BTCUnit.BTCS;
    }

    // Work through the units to find the closest
    for (BTCUnit candidate : BTCUnit.values())
    {
      if (candidate.common || common ==false)
      {
        long digits = (int)(Math.log10(amount/candidate.satoshis) + 1);
        if (digits <= 0)
        {
          // The last one was as good as we were going to get
          return best;
        }
        else
        {
          best = candidate;
        }
      }
    }
    return best;
  }

  public String toPrettyString(final long amount)
  {
    return format.format(((double)amount) / this.satoshis);
  }
}

//  public String toPrettyString(final long amount, boolean common)
//  {
//    final DecimalFormat df = (DecimalFormat)NumberFormat.getInstance();
//
//    for (BTCUnit unit: BTCUnit.values())
//    {
//      if (unit.common == true || common == false) // Restrict to common if required
//      {
//        if (amount <= amount)
//        {
//          df.applyPattern(prefixes.get(num));
//          return stripZeros(df, df.format((amount >= min) ? amount / (float) num : amount));
//        }
//      }
//    }
////    final StringBuilder sb = new StringBuilder(32);
//    
//  }
// 
//  private static String stripZeros(final DecimalFormat df, final String fmtd)
//  {
//    char decsep = df.getDecimalFormatSymbols().getDecimalSeparator();
//    return fmtd.replaceAll("\\" + decsep + "00", EMPTY);
//  }
//
//  /**
//   * Convert a string value
//   * @param amount
//   * @param unit
//   * @return
//   */
//  public static long toSatoshis(final String amount, final BTCUnit unit)
//  {
//    return SATOSHIS.convert(Long.parseLong(amount), unit);
//  }
//}
