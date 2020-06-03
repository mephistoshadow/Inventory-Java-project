package search;

import java.io.IOException;
import main.Person;

/**
 * how ot create the finder for us to use.
 */
public interface Finder {

  /**
   * put in to the map when we want to add a person.
   *
   * @param job is the job of the person for us to use.
   * @param name name of the person.
   * @throws IOException when we dont have that map.
   */
  void putIntoMap(String job, String name) throws IOException;

  /**
   * get the person for us to use the thing.
   *
   * @param name the name of person.
   * @return a person can to things.
   */
  Person getFromMap(String name);
}
