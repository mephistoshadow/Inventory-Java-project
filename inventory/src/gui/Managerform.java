package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import search.FileOperation;
import main.Manager;

/**
 * Manager Panel,manger has view order history button,
 * view daily revenue button, view daily profit revenue,
 * hire or fire button,view pending order button.
 */
public final class Managerform {

  /**
   * Manager main panel.
   */
  private JPanel managerPan;
  /**
   * View Order history button.
   */
  private JButton viewOhButton;
  /**
   * View Daily revenue button.
   */
  private JButton viewDrButton;
  /**
   * View Daily profit button.
   */
  private JButton viewDpButton;
  /**
   * Hire or fire button.
   */
  private JButton hireFireButton;
  /**
   * View Pending order button.
   */
  private JButton viewPoButton;

  /**
   * The manger panel.It has all buttons.
   *
   * @throws IOException throws exception.
   */
  private Managerform() throws IOException {
    JFrame frame = new JFrame("Manager");
    FileOperation fileOp = new FileOperation();
    Manager manager = new Manager();
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    viewOhButton.addActionListener(e -> viewOrderFrame(fileOp.getOhFilePath(),
        "Order History"));
    viewDrButton.addActionListener(e -> JOptionPane.showMessageDialog(frame,
        "The daily revenue is " + manager.getRevenue()));
    viewDpButton.addActionListener(e -> JOptionPane.showMessageDialog(frame,
        "The daily profit is " + manager.getProfit()));
    viewPoButton.addActionListener(e -> viewOrderFrame(fileOp.getPoFilePath(),
        "Pending order"));
    hireFireButton.addActionListener(e -> Worker.show());
  }

  /**
   * Create a order frame can view order history and pending
   * order.
   *
   * @param filepath file path of certain profile.
   * @param title file title of certain profile.
   */
  static void viewOrderFrame(final String filepath, final String title) {
    JFrame frame = new JFrame(title);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    Vector<String> columnnames = new Vector<>();
    columnnames.addElement("UPC");
    columnnames.addElement("Product");
    columnnames.addElement("Distributor");
    columnnames.addElement("Quantity");
    Vector<Vector> rowdata = new Vector<>();
    try {
      Scanner reader = new Scanner(new FileInputStream(filepath));
      while (reader.hasNextLine()) {
        String str = reader.nextLine();
        List<String> list = Arrays.asList(str.split(","));
        Vector<String> vect = new Vector<>();
        vect.addAll(list);
        rowdata.addElement(vect);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    final int fh = 500;
    JTable pendingtable = new JTable(rowdata, columnnames);
    JScrollPane scrollPane = new JScrollPane(pendingtable);
    frame.add(scrollPane, BorderLayout.CENTER);
    frame.setSize(fh, fh);
    frame.setVisible(true);
  }

  /**
   * Create this panel.
   *
   * @throws IOException throws Exception.
   */
  static void show() throws IOException {
    final int fiveH = 500;
    JFrame frame = new JFrame("Managerform");
    frame.setContentPane(new Managerform().managerPan);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
    frame.setSize(new Dimension(fiveH, fiveH));
  }


}
