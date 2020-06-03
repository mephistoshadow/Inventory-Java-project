package main;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yangsiq1 on 14/07/17.
 * main.Location class.
 * Define the location of a product.
 * main.Location contains section name and shelf number.
 * All products should have location.
 */
public class Location {

  /**
   * remember the two index of shelf.
   */
  private int index1;
  /**
   * remember the two index of shelf.
   */
  private int index2;

  /**
   * the location of all products.
   */
  private HashMap<String, ArrayList<Shelf>> section;

  /**
   * how to create the location.
   */
  public Location() {
    this.section = new HashMap<>();

  }

  /**
   * get the section hash map.
   *
   * @return the hashmap of the sections.
   */
  public final HashMap<String, ArrayList<Shelf>> getSection() {
    return section;
  }

  /**
   * creat the location.
   *
   * @param name name of the section.
   */
  final void creatSection(final String name) {
    /*
    the arraylist of shelfs.
   */
    ArrayList<Shelf> shelfs = new ArrayList<>();
    shelfs.add(new Shelf("a"));
    shelfs.add(new Shelf("b"));
    shelfs.add(new Shelf("c"));
    this.section.put(name, shelfs);
  }

  /**
   * change the location with the section name and shelf num 1 and num2.
   *
   * @param sec the section name
   * @param name1 the shelf1 index
   * @param name2 the shelf2 index
   */
  final void changeloc(final String sec,
      final String name1, final String name2) {
    final int thre = 3;
    for (int i = 0; i < thre; i++) {
      if (getSection().get(sec).get(i).getName().equals(name1)) {
        index1 = i;
      } else if (getSection().get(sec).get(i).getName().equals(name2)) {
        index2 = i;
      }
    }
    Shelf c = this.getSection().get(sec).get(index1);
    c.setName(name2);
    Shelf d = this.getSection().get(sec).get(index2);
    d.setName(name1);
    this.getSection().get(sec).set(index1, d);
    this.getSection().get(sec).set(index2, c);
  }


}
