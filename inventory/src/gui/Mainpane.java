package gui;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import main.Inventory;
import main.Location;
import search.WorkFinder;
import main.Revenue;

/**
 * this is the main panel for us to show the gui,
 * it always contains the information for us to use.
 */
public final class Mainpane extends JPanel {

  /**
   * this is the inventory for us to do the modify.
   */
  private static Inventory inventory;
  /**
   * the workerfinder is for us to hire and fire
   * people and set the individual password.
   */
  private static WorkFinder workerfinder;
  /**
   * this button is for cashier login.
   */
  private JButton cashButton;
  /**
   * this is for us to set the mainpanel.
   */
  private JPanel mainPanel;
  /**
   * this button is for us to login manager login.
   */
  private JButton managerButton;
  /**
   * this is for reshelver login.
   */
  private JButton resheButton;
  /**
   * this is for receiver login button.
   */
  private JButton receButton;
  /**
   * this is for us to exit the program.
   */
  private JButton exitButton;
  /**
   * this textfield is for us to enter the login cashier name.
   */
  private JTextField cashiername;
  /**
   * this textfield is for us to enter the login reshelver name.
   */
  private JTextField reshname;
  /**
   * this is for us to enter the receiver name.
   */
  private JTextField receivername;
  /**
   * this is for us to set the day and for cashier setsale and unsale use.
   */
  private JButton day;
  /**
   * this label is for us to show the day.
   */
  private JLabel rday;
  /**
   * is count is for us to do the day operation.
   */
  private int count;
  /**
   * this string is for us to identify which panel
   * we should generate when we press the button.
   */
  private static String classcall = "";
  /**
   * this string is for us to set the password when the username is different.
   */
  private static String pass = "";


  /**
   * Password varies for different people, this is default.
   */
  private static char[] password;

  /**
   * this is for us to reset the count to zero.
   */
  private void setCount() {
    this.count = 0;
  }

  /**
   * this is for us to output the inventory to other panel to modify.
   *
   * @return the  inventory we use.
   */
  public static Inventory getInventory() {
    return inventory;
  }

  /**
   * this is for us to output the workfinder to hire and fire worker.
   *
   * @return the workfinder we use.
   */
  static WorkFinder getWorkerfinder() {
    return workerfinder;
  }

  /**
   * this is for us to output the class
   * call to use to generate the correct panel.
   *
   * @return the String classcall we use.
   */
  static String getClasscall() {
    return classcall;
  }

  /**
   * this is for us to output the password for us to setup the password.
   *
   * @return the string name.
   */
  static String getPass() {
    return pass;
  }

  /**
   * get the password.
   *
   * @return the password we use.
   */
  static char[] getPassword() {
    return password;
  }

  /**
   * Main panel.
   */
  private Mainpane() {
    rday.setText("0");
    try {
            /*
      this is the location for us to change the layout of our store.
     */
      Location location = new Location();
      inventory = new Inventory(location);
      inventory.initialshelf(0);
      workerfinder = new WorkFinder();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    cashButton.addActionListener(e -> {
      classcall = "cashier";
      pass = cashiername.getText();
      password = pass.toCharArray();
      Password.show();

    });

    managerButton.addActionListener(e -> {
      classcall = "manager";
      password = new char[]{'m', 'a', 'n', 'a'};
//                PasswordPanel.createAndShowGUI("none");
      Password.show();
    });

    resheButton.addActionListener(e -> {
      classcall = "reshelver";
      pass = reshname.getText();
      password = pass.toCharArray();
      Password.show();
    });

    receButton.addActionListener(e -> {
      classcall = "receiver";
      pass = receivername.getText();
      password = pass.toCharArray();
      Password.show();
    });
    exitButton.addActionListener(e -> System.exit(0));
    day.addActionListener(e -> {
      final int thirOne = 31;
      count++;
      if (count == thirOne) {
        setCount();
      }
      setDay();
      rday.setText(String.valueOf(count));
      rday.setEnabled(false);
    });

  }

  /**
   * Day button some operations, every day finish the
   * profit and revenue will be clear. After the sale
   * date pass, the product price will change back.
   */
  private void setDay() {
    if (!(Cashierform.getSalemap().size() == 0)) {
      Cashierform.getSalemap().replaceAll((k, v) -> v - 1);
      for (int i = 0; i < Cashierform.getProlist().size(); i++) {
        if (!(Cashierform.getSalemap().size() == 0)) {
          if (Cashierform.getSalemap().get(
              Cashierform.getProlist().get(i)) == 0) {
            Cashierform.getProlist().get(i).setUnsale();
          }
        }
      }
    }
    Revenue.clearProfit();
    Revenue.clearRevenue();
  }

  /**
   * The main function to init this program.
   *
   * @param args The args.
   */
  public static void main(final String[] args) {
    final int fivhun = 500;
    JFrame frame = new JFrame("Main");
    frame.setSize(new Dimension(fivhun, fivhun));
    frame.setContentPane(new Mainpane().mainPanel);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }


}
