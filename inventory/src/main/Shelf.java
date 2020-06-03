package main;

import java.util.ArrayList;

/**
 * this is the shelf class for use to create the shlef.
 */
public class Shelf {

  /**
   * the shelf name for us to use.
   */
  private String name;
  /**
   * the shelf to store the product.
   */
  private ArrayList<String> shelf;

  /**
   * get the name.
   *
   * @return the string name of the shelf.
   */
  public String getName() {
    return name;
  }

  /**
   * set the the shelf name.
   *
   * @param n the name of shelf.
   */
  public void setName(final String n) {
    this.name = n;
  }

  /**
   * how to create a shelf.
   *
   * @param nme the name of shelf.
   */
  Shelf(final String nme) {
    this.name = nme;
    this.shelf = new ArrayList<>();
  }

  /**
   * get the arryalist of shelf.
   *
   * @return the arraylist shelf to do shelf.
   */
  public ArrayList<String> getShelf() {
    return shelf;
  }
}
