package main;

import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.Writer;
import java.io.FileWriter;


/**
 * this how to genral the main.Inventory for other name.
 */
public interface General {

  /**
   * get the products.
   *
   * @return the arraylist
   */
  ArrayList<Product> getProducts();

  /**
   * get the location.
   *
   * @return the location
   */
  Location getLoc();


  /**
   * get the price map of product.
   *
   * @return the Hashmap.
   */
  HashMap<String, String> getPrice();

  /**
   * get the quantity map of product.
   *
   * @return HashMap
   */
  HashMap<String, String> getQuantity();

  /**
   * get the upc map.
   *
   * @return HashMap
   */
  HashMap<String, String> getName();

  /**
   * get the return map.
   *
   * @return the hashmap of main.Inventory.
   */
  HashMap<String, ArrayList<String>> getInventorymap();

  /**
   * import the file for us to initialize the inventory.
   *
   * @param filepath is the file path for us to read the inventory
   * @return the arraylist of product when we have read a file.
   * @throws FileNotFoundException if we dont have that file throws the exception.
   */
  static ArrayList<Product> import1(final String filepath) throws
      FileNotFoundException {
    ArrayList<Product> lo = new ArrayList<>();
    final int cc = 3;
    final int dd = 4;
    final int ee = 5;
    Scanner scanner = new Scanner(new FileInputStream(filepath));
    String[] record;
    Product product;
    while (scanner.hasNextLine()) {
      record = scanner.nextLine().split(",");
      product = new Product(record[1],
          record[0], record[2], record[cc], record[dd], record[ee]);
      lo.add(product);
    }
    scanner.close();
    return lo;
  }

  /**
   * this is the method for us to write the file.
   *
   * @param map is the thing we want to write into the file.
   * @param path the inventory path of us.
   * @throws IOException throws the exception for us to use.
   */
  static void writetofile(HashMap<String, ArrayList<String>> map, String path)
      throws IOException {
    String eol = System.getProperty("line.separator");
    try (Writer writer = new FileWriter(path)) {
      final int cc = 3;
      final int dd = 4;
      for (Map.Entry<String, ArrayList<String>> entry
          : map.entrySet()) {
        writer.append(entry.getKey())
            .append(',')
            .append(entry.getValue().get(0))
            .append(",")
            .append(entry.getValue().get(1))
            .append(",")
            .append(entry.getValue().get(2))
            .append(",")
            .append(entry.getValue().get(cc))
            .append(",")
            .append(entry.getValue().get(dd))
            .append(eol);
      }
    } catch (IOException ex) {
      ex.printStackTrace(System.err);
    }
  }

  /**
   * initialize the shelf when we initialize the inventory.
   *
   * @param num is the number of shelf that we want to initialize.
   */
  void initialshelf(int num);

  /**
   * this is how we setup the inventory and we need to
   * implements the method for us to do the things.
   *
   * @throws IOException when we dont have the file we throws the exception.
   */
  void setup() throws IOException;

}
