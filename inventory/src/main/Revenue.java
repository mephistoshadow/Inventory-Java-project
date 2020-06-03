package main;

/**
 * The revenue class. This class deal with all profit and revenue.
 * Clear the revenue and profit after each day.
 */
public class Revenue {

  /**
   * The profit of the all products sold.
   */
  private static double profit;
  /**
   * The revenue of the all products sold.
   */
  private static double revenue;

  /**
   * Get the profit of this store.
   *
   * @return the current profit of this store.
   */
  static double getProfit() {
    return profit;
  }

  /**
   * Get the revenue of this store.
   *
   * @return the current revenue of this store.
   */
  static double getRevenue() {
    return revenue;
  }

  /**
   * Clear the profit after each day end.
   */
  public static void clearProfit() {
    Revenue.profit = 0.0;
  }

  /**
   * Clear the revenue after each day end.
   */
  public static void clearRevenue() {
    Revenue.revenue = 0.0;
  }

  /**
   * Set the profit of this store.
   *
   * @param pro the profit to be set.
   */
  static void setProfit(final double pro) {
    Revenue.profit = pro;
  }

  /**
   * Set the revenue of this store.
   *
   * @param rev the revenue to be set.
   */
  static void setRevenue(final double rev) {
    Revenue.revenue = rev;
  }

  /**
   * Round current profit or revenue to two decimal palces.
   *
   * @param value the number enter.
   * @return this number round into two decimal places.
   */
  public double round(final double value) {
    final int ten = 10;
    long roundTwo = (long) Math.pow(ten, 2);
    long result = Math.round(value * roundTwo);
    return (double) result / roundTwo;
  }

}
