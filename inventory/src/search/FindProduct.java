package search;

import main.Inventory;
import main.Product;

/**
 * Find certain product given by its upc code.
 * Check whether this upc correspond to the certain product exist.
 */
public class FindProduct {

  /**
   * Get the product.
   */
  private Product result;

  /**
   * Search in the inventory to get this product.
   *
   * @param inv the inventory stored.
   * @param upc the upc code of this product.
   * @return this product.
   */
  public Product search(final Inventory inv, final String upc) {
    for (int i = 0; i < inv.getProducts().size(); i++) {
      if (inv.getProducts().get(i).getUpc().equals(upc)) {
        result = inv.getProducts().get(i);
      }

    }
    return result;
  }

  /**
   * Check whether this product exist given upc code.
   *
   * @param inv the inventory stored.
   * @param upc the upc code of this product.
   * @return true if this product exist in our inventory, false otherwise.
   */
  public boolean whetherExist(final Inventory inv, final String upc) {
    for (int i = 0; i < inv.getProducts().size(); i++) {
      if (inv.getProducts().get(i).getUpc().equals(upc)) {
        return true;
      }
    }
    return false;
  }

}
