package main;

/**
 * Access daily revenue and profit.
 * Reading pending order list from pendingorder.csv.
 *
 * Pending order needs to sending an order to a distributor.
 * This will be the last step.
 */
public final class Manager {

  /**
   * Getter of profit.
   *
   * @return the profit
   */
  public double getProfit() {
    Revenue reve = new Revenue();
    return reve.round(Revenue.getProfit());
  }

  /**
   * Getter of revenue.
   *
   * @return the revenue.
   */
  public double getRevenue() {
    Revenue reve = new Revenue();
    return reve.round(Revenue.getRevenue());
  }
}
