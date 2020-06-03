package main;

import java.io.IOException;
import java.util.ArrayList;
import search.FileOperation;

/**
 * Class main.Receiver.
 * They will scan in new items into inventory
 * and also new units of a pre-existing item;
 * view location, cost, price history,
 * and current price of a product.
 */
public class Receiver implements Person {

  /**
   * get the boolean to determine if the order exist or not.
   *
   * @return true if order exist.
   */
  public static boolean isMessage() {
    return message;
  }

  /**
   * the boolean to determine if the order exist or not.
   */
  private static boolean message = true;
  /**
   * FileOperation fileOp to read file.
   */
  private FileOperation fileOp;

  /**
   * work of this main.Receiver have done.
   */
  private ArrayList<String> work;

  /**
   * name of this main.Receiver.
   */
  private String name;

  /**
   * get the Name of this main.Receiver.
   *
   * @return name of the receiver
   */
  public String getName() {
    return this.name;
  }

  /**
   * return the work of this main.Receiver.
   *
   * @return work of this Reciever.
   */
  public ArrayList<String> selfwork() {
    return this.work;
  }

  /**
   * initialize a main.Receiver.
   *
   * @param nam name of this main.Receiver.
   */
  public Receiver(final String nam) {
    this.name = nam;
    work = new ArrayList<>();
    fileOp = new FileOperation();
  }

  /**
   * Scan an item, if it already in the stock, increase quantity by 1,
   * if it is not in the inventory, append that item in inventory.
   *
   * @param a main.Inventory of the store.
   * @param pr product added.
   * @param shelfnum shelf number of the product.
   * @throws IOException change the quantity in inventory.
   */
  public void scan(final Inventory a, final Product pr, final int shelfnum)
      throws IOException {
    String prc = pr.getUpc();
    ArrayList<String> info = new ArrayList<>();
    if (a.getQuantity().keySet().contains(prc)) {
      final int t = 3;
      Integer quan = Integer.valueOf(a.getQuantity().get(prc)) + 1;
      String quant = quan.toString();
      a.getQuantity().replace(prc, quant);
      a.getInventorymap().get(prc).set(t, quant);
      pr.setQuantity(quant);
      selfwork().add(name
          + " Scanned in an unit of "
          + pr.getName() + ", quantity: "
          + pr.getQuantity() + "\n");
    } else {
      a.getProducts().add(pr);
      a.getName().put(prc, pr.getName());
      a.getPrice().put(prc, pr.getPrice());
      a.getQuantity().put(prc, pr.getQuantity());
      info.add(pr.getName());
      info.add(pr.getCost());
      info.add(pr.getPrice());
      info.add(pr.getQuantity());
      info.add(pr.getSection());
      pr.setShelfnum(shelfnum);
      a.getInventorymap().put(prc, info);
      selfwork().add(name + " Scanned in new item "
          + pr.getName() + ", quantity: " + pr.getQuantity() + "\n");
      if (!a.getLoc().getSection().containsKey(pr.getSection())) {
        a.getLoc().creatSection(pr.getSection());
        a.getLoc().getSection().get(pr.getSection())
            .get(shelfnum).getShelf().add(pr.getName());
      } else {
        a.getLoc().getSection().get(pr.getSection())
            .get(shelfnum).getShelf().add(pr.getName());
      }
    }
    General.writetofile(a.getInventorymap(), a.getInventoryCSVfilePath());
  }

  /**
   * View the information of the product.
   *
   * @param a inventory a.
   * @param code upc code of the product.
   * @return information of this product not confirming situation describe.
   * @throws IOException Throw exception if
   */
  public String view(final Inventory a, final String code) throws IOException {
    for (int i = 0; i < a.getProducts().size(); i++) {
      if (a.getProducts().get(i).getUpc().equals(code)) {
        return "name: "
            + String.format(a.getProducts().get(i).getName() + ", price: $"
            + a.getProducts().get(i).getPrice() + "%n" + "cost: $"
            + a.getProducts().get(i).getCost() + ", location: "
            + a.getProducts().get(i).getSection()
            + "%n" + "Price History:"
            + a.getProducts().get(i).getPricehistory());
      }
    }
    return "Cannot find this product";
  }

  /**
   * Accept the order.
   *
   * @param inv inventory of the store.
   * @param line order information.
   * @param pr product of the order.
   * @param quantity quantity of the order.
   * @throws IOException throws IOException
   */
  public void acceptOrder(final Inventory inv,
      final String line, final Product pr,
      final String quantity) throws IOException {
    boolean orderExist = fileOp.receiverdo(line);
    String prc = pr.getUpc();
    if (inv.getQuantity().keySet().contains(prc) && orderExist) {
      message = true;
      final int t = 3;
      Integer quan = Integer.valueOf(inv.getQuantity().get(prc))
          + Integer.valueOf(quantity);
      String quant = quan.toString();
      inv.getQuantity().replace(prc, quant);
      inv.getInventorymap().get(prc).set(t, quant);
      pr.setQuantity(quant);
      selfwork().add(name
          + " accept order of "
          + pr.getName() + ", quantity added: " + quantity
          + " quantity in inventory: " + pr.getQuantity() + "\n");

    } else {
      message = false;
    }
    General.writetofile(inv.getInventorymap(), inv.getInventoryCSVfilePath());
  }
}
