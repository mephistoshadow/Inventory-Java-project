package main;

import java.io.IOException;
import java.util.ArrayList;


/**
 * this is the reshelver class for us to genrate.
 */
public class Reshelver implements Person {

  /**
   * this is for us to setup the self work.
   */
  private ArrayList<String> work;

  /**
   * this is for us to create a new reshelver with a name.
   *
   * @param n the name of a reshelver.
   */
  public Reshelver(final String n) {
    this.name = n;
    work = new ArrayList<>();
  }

  /**
   * name of this main.Reshelver.
   */
  private String name;

  /**
   * change the location.
   *
   * @param loc the location.
   * @param secname the sec name
   * @param firstname the shelf name
   * @param secshelfname the other shelf name
   * @throws IOException throws the exception
   */
  public void changeloc(final Location loc, final
  String secname, final String firstname, final String secshelfname)
      throws IOException {
    loc.changeloc(secname, firstname, secshelfname);
    work.add("reshelver has reshelf the" + " " + secname
        + " " + "the shelf" + " " + firstname + " "
        + "and" + " " + secshelfname + " "
        + "and now is " + "\n"
        + loc.getSection().get(secname).get(0).getShelf().toString() + "\n"
        + loc.getSection().get(secname).get(1).getShelf().toString()
        + "\n" + loc.getSection().get(secname).get(2).getShelf().toString());
  }

  /**
   * this is for us to getthe name for a reshelver
   * when we want to us in gui.
   *
   * @return a string name of a reshelver.
   */
  public String getName() {
    return this.name;
  }

  /**
   * this is us to output he work and to see the work done.
   *
   * @return the arraylist of the re shelver has done.
   */
  public ArrayList<String> selfwork() {
    return this.work;
  }

}
