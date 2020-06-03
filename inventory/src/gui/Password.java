package gui;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 * this is for us to generate the password panel and jump to other panel.
 */
public final class Password {

  /**
   * this is for us to enter the password.
   */
  private JPasswordField password;
  /**
   * this is for us to generate the passwordpan to show the gui.
   */
  private JPanel passwordpan;
  /**
   * this is for us to submit the password to check if it is good.
   */
  private JButton submit;
  /**
   * this frame is for us to generate the fram.
   */
  private static JFrame fram;
  /**
   * this is for system to check if the password is correct.
   */
  private static boolean check = false;

  /**
   * this is for us to generate the password panel.
   */
  private Password() {
    submit.addActionListener(e -> {
      check = check();
      createshow();
    });

  }
  /**
   * this is for us to show the password panel
   * and to see which sub-panel we should jump.
   */
  private void createshow() {
    if (check) {
      if (Mainpane.getClasscall().equals("manager")) {
        try {
          Managerform.show();
          fram.dispose();
        } catch (IOException ex) {
          System.err.println(ex.getMessage());
        }

      } else if (Mainpane.getClasscall().equals("reshelver")) {
        try {
          if (Mainpane.getWorkerfinder().existWorker(
              "Reshelver", Mainpane.getPass())) {
            ReshelverPanel.show(Mainpane.getPass());
            fram.dispose();
          } else {
            notExist("reshelver");
          }
        } catch (IOException ex) {
          System.err.println(ex.getMessage());
        }
      } else if (Mainpane.getClasscall().equals("receiver")) {
        try {
          if (Mainpane.getWorkerfinder().existWorker(
              "Receiver", Mainpane.getPass())) {
            ReceiverPan.show(Mainpane.getPass());
            fram.dispose();
          } else {
            notExist("receiver");
          }
        } catch (IOException ex) {
          System.err.println(ex.getMessage());
        }
      } else if (Mainpane.getClasscall().equals("cashier")) {
        try {
          if (Mainpane.getWorkerfinder().existWorker(
              "Cashier", Mainpane.getPass())) {
            Cashierform.show(Mainpane.getPass());
            fram.dispose();
          } else {
            notExist("cashier");
          }
        } catch (IOException ex) {
          System.err.println(ex.getMessage());
        }
      }
    } else {
      JOptionPane.showMessageDialog(passwordpan,
          "you have type the wrong password",
          "Error Message",
          JOptionPane.ERROR_MESSAGE);
      fram.dispose();
    }
  }

  /**
   * this method is for us to chekc the password to see if it is correct.
   * @return a boolean.
   */
   private boolean check() {
    char[] pass = Mainpane.getPassword();
    return Arrays.equals(pass, password.getPassword());
  }

  /**
   * this is for us to show the gui of the password panel.
   */
  static void show() {
   final int six = 60;
   final int twofiv = 250;
   JFrame frame = new JFrame("password");
   fram = frame;
   frame.setContentPane(new Password().passwordpan);
   frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   frame.setSize(twofiv, six);
   frame.setVisible(true);
 }

  /**
   * this is for us to check is this worker is exit or not.
   * @param nam is the username for us to use.
   */
 private void notExist(final String nam) {
   JOptionPane.showMessageDialog(passwordpan,
       "We don't have this " + nam + ", Try again.",
       "Error Message",
       JOptionPane.ERROR_MESSAGE);
      fram.dispose();
 }

}
