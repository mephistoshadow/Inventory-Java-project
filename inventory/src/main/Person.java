package main;

import java.util.ArrayList;


/**
 * this is how we create the person interface and initialize all the things.
 *
 * @param <T> the type to define the person typr
 */
public interface Person<T> {
  /**
   * this is the  selfwork to do.
   *
   * @return the array list of all work;
   */
  ArrayList<T> selfwork();

  /**
   * get the name of a person.
   *
   * @return the name of a person to use in other class.
   */
  String getName();

}
