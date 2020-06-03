package gui;

import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import main.Reshelver;
import search.FileOperation;

/**
 * The reshelver GUI panel
 * this is how to create the reshelverpanel.
 */
final class ReshelverPanel {

  /**
   * press this to change the location.
   */
  private JButton changeLocationButton;
  /**
   * press the button to view the orderhistorybutton.
   */
  private JButton viewOrderhistoryButton;
  /**
   * press the button the view the quantity button to view quantity.
   */
  private JButton viewquantityButton;
  /**
   * press the section button to view the section.
   */
  private JButton viewsection;
  /**
   * this is JtextField to enter the quantity.
   */
  private JTextField quantity;
  /**
   * this is the text field the location ot enter the location.
   */
  private JTextField location;
  /**
   * this is the reshalverpan for us to create.
   */
  private JPanel reshaelverPan;
  /**
   * this is the txt field for us to show the real quantity.
   */
  private JTextField realquantity;
  /**
   * this is the label for us to see the name .
   */
  private JLabel name;
  /**
   * this is for us to see the work the reshelver has done.
   */
  private JButton work;
  /**
   * this is for us to show the reshelver's work.
   */
  private JTextArea workdone;
  /**
   * this is the fileop we want ot use to readfrom a file.
   */
  private FileOperation fileOp;

  /**
   * this is the reshelverpanel for us to create the .
   * reshelverpanel and use the button the work.
   *
   * @param nam this is for us to show the nam on the panel.
   * @throws IOException throw the not file exception to tell us.
   */
  private ReshelverPanel(final String nam) throws IOException {
    Reshelver re = Mainpane.getWorkerfinder().getReshelverMap().get(nam);
    name.setText(nam);
    fileOp = new FileOperation();
    changeLocationButton.addActionListener(e -> {

      String[] info = location.getText().split(",");
      try {
        re.changeloc(Mainpane.getInventory().getLoc(),
            info[0], info[1], info[2]);
      } catch (Exception b) {
        System.err.println("dont have this file");
      }
    });

    viewsection.addActionListener(e -> {
      new Inventorypanel();
      re.selfwork().add(" i have view the section");

    });

    viewquantityButton.addActionListener(e -> {
      try {
        String upc = quantity.getText();
        getquantity(upc);
        realquantity.setText(String.valueOf(getquantity(upc)));
        realquantity.setEnabled(false);
      } catch (NumberFormatException ex) {
        System.err.println("do not do this");
      }

    });
    viewOrderhistoryButton.addActionListener(e ->
        Managerform.viewOrderFrame(fileOp
                .getOhFilePath(),
            "orderhistory"));
    work.addActionListener(e -> {
      for (int i = 0; i < re.selfwork().size(); i++) {
        workdone.setText(workdone.getText() + "\n" + re.selfwork().get(i));
      }
      work.setEnabled(false);
      workdone.setEnabled(false);
    });
  }

  /**
   * this is for us to show the gui panel.
   *
   * @param name this is for us to pass the name of
   * this reshelver to do the self work.
   * @throws IOException throw the exception when we dont have the file.
   */
  static void show(final String name) throws IOException {
    final int seven = 700;
    final int thousand = 1000;
    JFrame frame = new JFrame("ReshelverPanel");
    frame.setContentPane(new ReshelverPanel(name).reshaelverPan);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setSize(seven, thousand);
    frame.setVisible(true);
  }

  /**
   * this how to get the quantity.
   *
   * @param upc this is the product upc for us to use.
   * @return the quantity of the produce
   */
  private int getquantity(final String upc) {
    return Integer.valueOf(Mainpane.getInventory().getQuantity().get(upc));
  }
}
