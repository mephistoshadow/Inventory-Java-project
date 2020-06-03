package gui;

import main.Shelf;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Created by yangsiq1 on 02/08/17.
 * inventory panel for the store.
 * It will show the inventory of the store when main.Reshelver.
 * want to check or make change of the product location.
 */
public class Inventorypanel extends JFrame {

  /**
   * Table to show location.
   */
  private JTable table;
  /**
   * Button to view The location.
   */
  private JButton view = null;
  /**
   * Type in section to see layouts.
   */
  private JTextField section = null;
  /**
   * magic number ten = 10.
   */
  private final int ten = 10;
  /**
   * Data pf the layout.
   */
  private String[][] datas = new String[ten
      ][ten];

  /**
   * main.Inventory main panel. Shows the layout of the store.
   */
  Inventorypanel() {
    super("inventory");
    view = new JButton("view");
    section = new JTextField();
    view.addActionListener(e -> {
      try {
        setsection(section.getText());
        section.setEnabled(false);
        table.setEnabled(false);
        datas = new String[ten][ten];
        view.setEnabled(false);

      } catch (NullPointerException ex) {
        System.err.println(ex.getMessage());
      }


    });
    showimage();
  }

  /**
   * Show the section layout.
   */
  private void showimage() {
    String[] titles = {"a", "b", "c"};
    final int fh = 500;
    add(section, BorderLayout.SOUTH);
    add(view, BorderLayout.WEST);
    table = new JTable(datas, titles);
    add(new JScrollPane(table));
    setSize(fh, fh);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setVisible(true);
  }

  /**
   * Set the section of the store. Changes location.
   *
   * @param sectionname Section name of the product.
   */
  private void setsection(final String sectionname) {

    ArrayList<Shelf> shelf =
        Mainpane.getInventory().getLoc().getSection().get(sectionname);
    final int cc = 3;
    for (int i = 0; i < cc; i++) {
      for (int j = 0; j < shelf.get(i).getShelf().size(); j++) {
        datas[j][i] = shelf.get(i).getShelf().get(j);
      }
    }
  }

  /**
   * Main method to show the panel.
   *
   * @param args List of Strings.
   */
  public static void main(final String[] args) {
    new Inventorypanel();
  }
}

