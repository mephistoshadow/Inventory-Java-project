package main;

import java.io.IOException;
import search.FindProduct;
import java.util.ArrayList;

/**
 * Cashier in this store.
 * Cashier can scan the product to sell it or check the product prize.
 * Cashier also can set product sale or cancel this product sale status.
 */
public class Cashier implements Person {

  /**
   * What a cashier does.
   */
  private ArrayList<String> selfwork;
  /**
   * Cashier name.
   */
  private String name;
  /**
   * the amount of  product has been sold.
   */
  private int productSold;

  /**
   * The cashier constructor.
   * Cashier has a name and a self work.
   *
   * @param nam the cashier name.
   * @throws IOException the io exception.
   */
  public Cashier(final String nam) throws IOException {
    this.name = nam;
    selfwork = new ArrayList<>();

  }

  /**
   * Get the cashier name.
   *
   * @return the cashier name.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Get what a cashier does.
   *
   * @return the detail work of a cashier.
   */
  @Override
  public ArrayList selfwork() {
    selfwork.clear();
    selfwork.add(String.valueOf(Revenue.getRevenue()));
    selfwork.add(String.valueOf(productSold));
    return selfwork;
  }


  /**
   * Scan the item upc, if the customer wants to buy this item, reduce
   * quantity 1 in the inventory.If the customer wants to see the sale
   * date of sale item, print out the information.
   *
   * @param a the inventory.
   * @param upc the item upc.
   * @throws IOException IOException.
   */
  public void scan(final Inventory a, final String upc) throws IOException {
    FindProduct product = new FindProduct();
    final int three = 3;
    //  current product.
    Product curpro = product.search(a, upc);
    String currentQuan = a.getQuantity().get(upc);
    Integer c = Integer.valueOf(currentQuan);
    c -= 1;
    String value = c.toString();
    if (!(Integer.valueOf(value) < 0)) {
      a.getQuantity().replace(upc, value);
      a.getInventorymap().get(upc).set(three,
          String.valueOf(Integer.valueOf(value)));
      curpro.setQuantity(value);
      General.writetofile(a.getInventorymap(), a.getInventoryCSVfilePath());
      // calculate the profit and revenue of each scan.
      double profit = Revenue.getProfit();
      double revenue = Revenue.getRevenue();
      profit += Double.valueOf(curpro.getPrice())
          - Double.valueOf(curpro.getCost());
      revenue += Double.valueOf(curpro.getPrice());
      Revenue.setProfit(profit);
      Revenue.setRevenue(revenue);

      productSold += 1;
    }
    General.writetofile(a.getInventorymap(), a.getInventoryCSVfilePath());
  }

}

