package com.wealdtech.bitcoin;

/*
 * Units used with bitcoins
 */
public enum BTCUnit
{
  /**
   * Satoshis
   */
  SATOSHIS(1L),
  
  /**
   * Microbitcoins
   */
  MICROBTCS(100L),

  /**
   * Millibitcoins
   */
  MILLIBTCS(100000L),

  /**
   * Centibitcoins
   */
  CENTIBTCS(1000000L),

  /**
   * Decibitcoins
   */
  DECIBTCS(10000000L),

  /**
   * Bitcoins
   */
  BTCS(100000000L),
  
  /**
   * Decabitcoins
   */
  DECABTCS(1000000000L),
  
  /**
   * Hectobitcoins
   */
  HECTOBTCS(10000000000L),
  
  /**
   * Kilobitcoins
   */
  KILOBTCS(100000000000L),
  
  /**
   * Megabitcoins
   */
  MEGABTCS(100000000000000L);
  
  private final long satoshis;
  
  BTCUnit(long satoshis)
  {
    this.satoshis = satoshis;
  }

  private long convert(final long amount, final BTCUnit unit)
  {
    return (amount * unit.satoshis) / satoshis;
  }

  public static long toSatoshis(final long amount, final BTCUnit unit)
  {
    return SATOSHIS.convert(amount, unit);
  }
}
