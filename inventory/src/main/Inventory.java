package main;

import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import search.FileOperation;


/**
 * this is the main.Inventory for store all the products.
 */
public class Inventory implements General {

  /**
   * the price map of all the products.
   */
  private HashMap<String, String> price;
  /**
   * the quantity of product.
   */
  private HashMap<String, String> quantity;
  /**
   * the products arraylist.
   */
  private ArrayList<Product> products;
  /**
   * the inventory hashmap.
   */
  private HashMap<String, ArrayList<String>> inventorymap;
  /**
   * the location.
   */
  private Location loc;
  /**
   * the name map of the product.
   */
  private HashMap<String, String> name;

  /**
   * get the inventory file path for user to use.
   *
   * @return the file path of inventory use.
   */
  final String getInventoryCSVfilePath() {
    return this.inventoryCSVfilePath;
  }

  /**
   * The inventory.csv path.
   */
  private String inventoryCSVfilePath = FileOperation.findAbsolutePath(
      "inventory.csv");

  /**
   * how to create the main.Inventory.
   *
   * @param a the location
   * @throws IOException throw the exception
   */
  public Inventory(final Location a) throws IOException {
    this.price = new HashMap<>();
    this.name = new HashMap<>();
    this.quantity = new HashMap<>();
    this.inventorymap = new HashMap<>();
    this.products = new ArrayList<>();
    this.loc = a;
    setup();
  }

  /**
   * get the products.
   *
   * @return the arraylist of the product.
   */
  public ArrayList<Product> getProducts() {
    return products;
  }

  /**
   * get the location of the store layout.
   *
   * @return the location of the store.
   */
  public Location getLoc() {
    return loc;
  }


  /**
   * get the price map of product.
   *
   * @return the Hashmap of the price of the product.
   */
  public HashMap<String, String> getPrice() {
    return price;
  }

  /**
   * get the quantity map of product.
   *
   * @return HashMap the quantity of the products.
   */
  public HashMap<String, String> getQuantity() {
    return quantity;
  }


  /**
   * get the upc map.
   *
   * @return HashMap of the name of the product.
   */
  public HashMap<String, String> getName() {
    return name;
  }

  /**
   * get the return map.
   *
   * @return the hashmap of main.Inventory.
   */
  public HashMap<String, ArrayList<String>> getInventorymap() {
    return inventorymap;
  }

  /**
   * this is to setuop the inventory information for user to use.
   *
   * @throws IOException when we dont have that file we throw the Exception.
   */
  public void setup() throws IOException {
    ArrayList<Product> product = General.import1(inventoryCSVfilePath);

    for (Product aProduct : product) {
      ArrayList<String> lo = new ArrayList<>();
      lo.add(aProduct.getName());
      lo.add(aProduct.getCost());
      lo.add(aProduct.getPrice());
      lo.add(aProduct.getQuantity());
      lo.add(aProduct.getSection());
      this.products.add(aProduct);
      this.loc.creatSection(aProduct.getSection());
      inventorymap.put(aProduct.getUpc(), lo);
      price.put(aProduct.getUpc(), aProduct.getPrice());
      quantity.put(aProduct.getUpc(), aProduct.getQuantity());
      name.put(aProduct.getUpc(), aProduct.getName());
    }

  }

  /**
   * initial the shalf status.
   *
   * @param num the shelf number of we want to setup.
   */
  public void initialshelf(final int num) {
    for (int i = 0; i < this.products.size(); i++) {
      this.getProducts().get(i).initialtoshelf(this, num);
    }
  }


}





