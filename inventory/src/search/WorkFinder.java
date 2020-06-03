package search;

import java.io.IOException;
import java.util.HashMap;
import main.Cashier;
import main.Person;
import main.Receiver;
import main.Reshelver;

/**
 * this is for us to create the worker finder to hire and fire use.
 */
public class WorkFinder implements Finder {

  /**
   * this is the receivermap for us to use to put into receiver.
   */
  private HashMap<String, Receiver> receivermap;
  /**
   * this is the map for us to putin to reshelver.
   */
  private HashMap<String, Reshelver> reshelvermap;
  /**
   * this is the map for us to put in to cashier.
   */
  private HashMap<String, Cashier> cashiermap;

  /**
   * this is for us to get the receiver map.
   *
   * @return the hashmap of receiver.
   */
  public HashMap<String, Receiver> getReceiverMap() {
    return receivermap;
  }

  /**
   * this is for us to get from reshelver.
   *
   * @return the hashmap of a reshelver.
   */
  public HashMap<String, Reshelver> getReshelverMap() {
    return reshelvermap;
  }

  /**
   * this is for us to get the cashier map.
   *
   * @return the hash map of the cashier.
   */
  public HashMap<String, Cashier> getCashierMap() {
    return cashiermap;
  }

  /**
   * this is how to gonna generate the workerfinder.
   */
  public WorkFinder() {
    reshelvermap = new HashMap<>();
    receivermap = new HashMap<>();
    cashiermap = new HashMap<>();
  }

  /**
   * this is for us like hire the worker.
   *
   * @param job is the job of the person for us to use.
   * @param name name of the person.
   * @throws IOException throw the IOexception when we dont have this file.
   */
  public void putIntoMap(final String job, final String name)
      throws IOException {
    if (job.equals("reshelver")) {
      reshelvermap.put(name, new Reshelver(name));
    } else if (job.equals("receiver")) {
      receivermap.put(name, new Receiver(name));
    } else if (job.equals("cashier")) {
      cashiermap.put(name, new Cashier(name));
    }
  }

  /**
   * this is for us to get the worker from the map we want.
   *
   * @param name the name of person.
   * @return the person that we want.
   */
  public Person getFromMap(final String name) {
    if (receivermap.containsKey(name)) {
      return receivermap.get(name);
    } else if (reshelvermap.containsKey(name)) {
      return reshelvermap.get(name);
    } else if (cashiermap.containsKey(name)) {
      return cashiermap.get(name);
    } else {
      return null;
    }
  }

  /**
   * fire worker.
   *
   * @param name1 name of this worker.
   * @param typ job of this worker.
   * @throws IOException throw IOException.
   */
  public void fireStaff(final String typ, final String name1)
      throws IOException {
    if (typ.equalsIgnoreCase("cashier")) {
      this.getCashierMap().remove(name1);
    } else if (typ.equalsIgnoreCase("receiver")) {
      this.getReceiverMap().remove(name1);
    } else if (typ.equalsIgnoreCase("reshelver")) {
      this.getReshelverMap().remove(name1);
    }
  }

  /**
   * if the worker exist in his job map, return true.
   * else, return false.
   *
   * @param type type of job.
   * @param name name of this worker.
   * @return boolean if worker exist or not.
   */
  public boolean existWorker(final String type, final String name) {
    if (type.equals("Cashier") && cashiermap.containsKey(name)) {
      return true;
    } else if (type.equals("Reshelver") && reshelvermap.containsKey(name)) {
      return true;
    } else if (type.equals("Receiver") && receivermap.containsKey(name)) {
      return true;
    }
    return false;
  }
}
