package gui;

import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * this is the worker panel for us to hire and fire a worker.
 * panel to hire or fire worker.
 */
public final class Worker {

  /**
   * this is for us to enter type and name to hire.
   */
  private JTextField hireField;
  /**
   * this is for us to enter the type and name to fire.
   */
  private JTextField firefield;
  /**
   * this is for us to do the event when we want to hire.
   */
  private JButton hire;
  /**
   * this is for us to do the event when we wan to
   * fire a worker to add to workerfinder.
   */
  private JButton fire;
  /**
   * this is the panel for us to set something for workerpane.
   */
  private JPanel workerPanel;
  /**
   * this is for us to set up the frame for this panel.
   */
  private static JFrame fram;

  /**
   * this is for us to gener ate the worker panel
   * and assign work to each button.
   */
  private Worker() {
    hire.addActionListener(e -> {
      String[] info = hireField.getText().split(",");
      try {
        try {
          Mainpane.getWorkerfinder().putIntoMap(info[0], info[1]);
        } catch (ArrayIndexOutOfBoundsException ex) {
          JOptionPane.showMessageDialog(null, "type error");
        }
        try {
          if (!(Mainpane.getWorkerfinder().getFromMap(info[1]) == null)) {
            JOptionPane.showMessageDialog(null, "Hire success!");
            fram.dispose();
          } else {
            JOptionPane.showMessageDialog(null, "Hire failed!");
            fram.dispose();
          }
        } catch (NullPointerException ex) {
          JOptionPane.showMessageDialog(null, "Hire failed!");
        }

      } catch (IOException ex) {
        System.err.println("dont have this file");
      }
    });

    fire.addActionListener(e -> {
      String[] info = firefield.getText().split(",");
      try {
        try {
          Mainpane.getWorkerfinder().fireStaff(info[0], info[1]);
        } catch (ArrayIndexOutOfBoundsException ex) {
          JOptionPane.showMessageDialog(null, "type error");
        }
        try {
          if (Mainpane.getWorkerfinder().getFromMap(info[1]) == null) {
            JOptionPane.showMessageDialog(null, "Fire success!");
            fram.dispose();
          } else {
            JOptionPane.showMessageDialog(null, "Fire failed!");
            fram.dispose();
          }
        } catch (NullPointerException ex) {
          JOptionPane.showMessageDialog(null, "Fire failed!");
        }
      } catch (IOException ex) {
        System.err.println("dont have this file");
      }
    });
  }

  /**
   * this is for us to generate the gui panel and set
   * some thing we want; like size.
   */
  static void show() {
    JFrame frame = new JFrame("Worker");
    fram = frame;
    frame.setContentPane(new Worker().workerPanel);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

}
