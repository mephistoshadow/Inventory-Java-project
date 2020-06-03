package main;

import java.io.IOException;
import search.FileOperation;

/**
 * Order class,if certain product is lower than threshold.
 * Order class will be invoked to order from distributor.
 * Also will write the order information to OrderHistory.csv
 * and Pendingorder.csv file.
 */
public class Order {

  /**
   * File operation, write information to OrderHistory.csv
   * and Pendingorder.csv file.
   */
  private FileOperation fileOp;

  /**
   * If products' quantity is below its threshold.
   * Create an order of that quantity.
   *
   * @param nam The name of product.
   * @param upid The upc of product.
   * @param distri The distributor of product.
   * @param quant The quantity needs to order.
   * @throws IOException Throw ioexception.
   */
  public Order(final String nam,
      final String upid,
      final String distri,
      final String quant) throws IOException {
    /*
    The name, id, distributor and quantity of this order.
   */
    fileOp = new FileOperation();
    setRecord(nam, upid, distri, quant);
  }

  /**
   * Set the list of record of every order.
   * Write into OrderHistory.csv
   * and Pendingorder.csv file.
   *
   * @param nam The name of product.
   * @param upc The upc of product.
   * @param distri The distributor of product.
   * @param quant The quantity needs to order.
   * @throws IOException Throw IOException.
   */
  private void setRecord(final String nam,
      final String upc,
      final String distri,
      final String quant) throws IOException {
    try {
      new Manager();
      String[] record = {upc, nam, distri, quant};
      fileOp.writePofile(record);
      fileOp.writeOhfile(record);
    } catch (IOException ex) {
      ex.printStackTrace(System.err);
    }
  }

}
