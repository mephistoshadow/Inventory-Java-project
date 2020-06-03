package search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Some file operations. Find the path of certain file.
 * Write to OrderHistory.csv.
 * Write to Pendingorder.csv.
 */
public class FileOperation {

  /**
   * The file path for Pendingorder.csv.
   */
  private String poFilePath =
      FileOperation.findAbsolutePath("Pendingorder.csv");
  /**
   * The file path for OrderHistory.csv.
   */
  private String ohFilePath =
      FileOperation.findAbsolutePath("OrderHistory.csv");

  /**
   * Get the OrderHistory.csv file path.
   *
   * @return OrderHistory.csv file path.
   */
  public String getOhFilePath() {
    return ohFilePath;
  }

  /**
   * Get the pending order file.
   */
  private File pofile = new File(
      poFilePath);

  /**
   * Get the pending order file.
   *
   * @return the pending order file path.
   */
  private File getPofile() {
    return pofile;
  }

  /**
   * Get the pending order file path.
   *
   * @return the pending order file path.
   */
  public String getPoFilePath() {
    return poFilePath;
  }


  /**
   * Find the path of certain file.
   *
   * @param fileName the file name need to be searched.
   * @return the file path.
   */
  public static String findAbsolutePath(final String fileName) {
    String outputFilePath = "";
    File filepa = new File("inventory.csv");
    String file = filepa.getAbsolutePath();
    String absolutFilePath = file.replace("/inventory.csv", "/");
    // check which system user are using.
    String systemName = System.getProperty("os.name");
    if (systemName.equals("Mac OS X")) {
      // get the path.
      if (!file.contains("ProjectPhase1/")) {
        outputFilePath = absolutFilePath
            + "ProjectPhase1/" + fileName;
        return outputFilePath;
      } else {
        outputFilePath = absolutFilePath
            + fileName;
        return outputFilePath;
      }
    } else if (systemName.equals("Linux")) {
      // get the path.
      if (!file.contains("ProjectPhase1/")) {
        outputFilePath = absolutFilePath + "ProjectPhase1/" + fileName;
      } else {
        outputFilePath = absolutFilePath + fileName;
      }
      return outputFilePath;
    }
    return outputFilePath;
  }

  /**
   * Write to pending order csv file.
   *
   * @param record the String need to be wrote into file.
   * @throws IOException throw Exception.
   */
  public void writePofile(final String[] record)
      throws IOException {
    final int three = 3;
    String eol = System.getProperty("line.separator");
    // get the file path.
    try (Writer writer = new FileWriter(poFilePath, true)) {
      for (int i = 0; i < record.length; i++) {
        // write the record to order history file.
        String str = record[i];
        writer.write(str);
        if (i != three) {
          writer.append(", ");
        }
      }
      writer.append(eol);

    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Write to order history csv file.
   *
   * @param record the String need to be wrote into file.
   * @throws IOException throw Exception.
   */
  public void writeOhfile(final String[] record)
      throws IOException {
    String eol = System.getProperty("line.separator");
    final int three = 3;
    // get the file path.
    try (Writer writer = new FileWriter(ohFilePath, true)) {
      for (int i = 0; i < record.length; i++) {
        // write the record to order history file.
        String str = record[i];
        writer.write(str);
        if (i != three) {
          writer.append(", ");
        }
      }
      writer.append(eol);
    } catch (IOException ex) {
      ex.printStackTrace(System.err);
    }
  }

  /**
   * Check if pending order file contain order line.
   * ifso, delete that order.
   * else do nothing.
   * return boolean if order exist or not.
   *
   * @param line order information
   * @return true if that order exist.
   * @throws IOException if the file do not exist.
   */
  public boolean receiverdo(final String line) throws IOException {
    File inputFile = this.getPofile();
    File tempFile = new File("PendingOrder.csv");

    boolean orderExist = false;

    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

    String currentLine;

    while ((currentLine = reader.readLine()) != null) {
      String trimmedLine = currentLine.trim();
      if (trimmedLine.equals(line)) {
        orderExist = true;
        continue;
      }
      writer.write(currentLine + System.getProperty("line.separator"));
    }
    tempFile.renameTo(inputFile);
    writer.close();
    reader.close();
    return orderExist;
  }
}
