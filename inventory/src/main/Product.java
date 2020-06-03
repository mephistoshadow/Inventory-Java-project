package main;

import gui.Cashierform;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Product class.
 * a product is something being sold by the store.
 * product has unique upc code,price,quantity,shelf number,
 * price history and section.
 */
public class Product {

  /**
   * if this product is on sale or not.
   */
  private boolean sale;
  /**
   * Name of the product.
   */
  private String name;
  /**
   * Upc code of the product.
   */
  private String upc;
  /**
   * Cost for the product.
   */
  private String cost;
  /**
   * Price of the product.
   */
  private String price;
  /**
   * Quantity of the product in inventory.
   */
  private String quantity;
  /**
   * The shelf number.
   */
  private int shelfnum;

  /**
   * The sale date.
   */
  private String saleDate;

  /**
   * the threshold of this product.
   */
  private String threshold;

  /**
   * The section of this product.
   */
  private String section;

  /**
   * Check the price history of this product.
   */
  private ArrayList<String> pricehistory = new ArrayList<>();
  /**
   * Avoid magic number 0.8.
   */
  private final double zeroE = 0.8;


  /**
   * create a product in store for sale.
   *
   * @param n name of the product.
   * @param up upc code of the product.
   * @param co cost of the product.
   * @param pri price of the product.
   * @param quan quantity of the product in stock.
   * @param sec section of the product in stock.
   */
  public Product(final String n, final String up,
      final String co, final String pri, final String quan, final String sec) {
    this.name = n;
    this.upc = up;
    this.cost = co;
    this.price = pri;
    this.quantity = quan;
    this.threshold = String.valueOf(Integer.valueOf(quantity) / 2);
    this.section = sec;
    this.pricehistory.add(price);
  }

  /**
   * Get the shelf number.
   *
   * @return the shelf number.
   */
  public final int getShelfnum() {
    return shelfnum;
  }

  /**
   * Set the shelf number.
   *
   * @param shelfn the shelf number to be set.
   */
  public final void setShelfnum(final int shelfn) {
    this.shelfnum = shelfn;
  }

  /**
   * Get the name of this main.Product.
   *
   * @return return the name of this product.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the UPC of this product.
   *
   * @return the UPC.
   */
  public String getUpc() {
    return upc;
  }

  /**
   * Get the cost of this product.
   *
   * @return the cost.
   */
  final String getCost() {
    return cost;
  }

  /**
   * Get the section of this product.
   *
   * @return the section.
   */
  final String getSection() {
    return section;
  }

  /**
   * Get ht price of this product.
   *
   * @return the price.
   */
  public String getPrice() {
    return price;
  }

  /**
   * Set the price of this product.
   *
   * @param p the price to be set.
   */
  private void setPrice(final String p) {
    this.price = p;
  }

  /**
   * Get the quantity of this product.
   *
   * @return the quantity.
   */
  public String getQuantity() {
    return quantity;
  }

  /**
   * Set the quantity of the product.
   *
   * @param quant Quantity want to set for this product.
   */
  public void setQuantity(final String quant) {
    this.quantity = quant;
  }

  /**
   * Get the price history of this product.
   *
   * @return the price history.
   */
  final ArrayList<String> getPricehistory() {
    return this.pricehistory;
  }

  /**
   * Set this item to sale and give it a sale date.
   *
   * @param date the sale date.
   */
  public final void setSale(final String date) {
    this.saleDate = date;
    this.price = String.valueOf(Double.valueOf(this.price) * zeroE);
    pricehistory.add(price);

  }

  /**
   * Cancel product sale status. The price will
   * be 80% of this original price.
   */
  public void setUnsale() {
    Double p = Double.valueOf(this.getPrice());
    Double s = p / zeroE;
    BigDecimal bd = new BigDecimal(s);
    bd = bd.setScale(2, RoundingMode.HALF_UP);
    this.setPrice(String.valueOf(bd.doubleValue()));
    Cashierform.getSalemap().remove(this);
  }

  /**
   * Get the threshold.
   *
   * @return the threshold.
   */
  public final String getThreshold() {
    return threshold;
  }

  /**
   * Get the sale date.
   *
   * @return the sale date.
   */
   public String getSaleDate() {
    return saleDate;
  }

  /**
   * Get whether a product is on sale.
   * @return true if sale , false otherwise.
   */
  public boolean isSale() {
    return sale;
  }

  /**
   * The initial shelf when the product comes from inventory.
   *
   * @param a the inventory.
   * @param num the number of shelf to be put.
   */
  final void initialtoshelf(final Inventory a, final int num) {
    a.getLoc().getSection().get(section)
        .get(num).getShelf().add(name);
  }


}
