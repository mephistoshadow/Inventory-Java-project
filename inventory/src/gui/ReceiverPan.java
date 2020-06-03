package gui;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import main.Receiver;
import main.Inventory;
import search.FindProduct;
import main.Product;
import search.FileOperation;

/**
 * Created by Lippter on 2017-08-02.
 * Receiver GUI panel.
 * Receiver can do Scan, View, Work and Exit on this panel.
 * Receiver will type in upc code in TextField.
 * reply message will appear on TextArea.
 */
public final class ReceiverPan {

  /**
   * Information of product.
   */
  private JTextArea info;
  /**
   * UPC of the product.
   */
  private JTextField upc;
  /**
   * Click to Scan the item.
   */
  private JButton scanButton;
  /**
   * Click to view product information.
   */
  private JButton viewButton;
  /**
   * Click to see work done.
   */
  private JButton workButton;
  /**
   * Name of this main.Receiver.
   */
  private JLabel name;
  /**
   * main.Receiver main panel.
   */
  private JPanel receiverP;
  /**
   * Button to view order.
   * click to view all the order the store have
   */
  private JButton viewOrderButton;
  /**
   * Accept order button.
   * Click to accept the order
   * type in "product upc, product name, distributor, quantity"
   */
  private JButton acceptOrderButton;

  /**
   * Initialize a ReceiverPan.
   *
   * @param nam Name of this main.Receiver.
   * @throws IOException Throw IOException.
   */
  private ReceiverPan(final String nam) throws IOException {
    Receiver rece = Mainpane.getWorkerfinder().getReceiverMap().get(nam);
    name.setText(nam);
    Inventory inv = Mainpane.getInventory();
    info.setEnabled(false);
    FindProduct productFinder = new FindProduct();
    FileOperation fileOp = new FileOperation();
    scanButton.addActionListener(e -> {
      String code = upc.getText();
      final int cc = 3;
      final int dd = 4;
      final int ee = 5;
      try {
        if (inv.getName().containsKey(code)) {
          Product pr = productFinder.search(inv, code);
          rece.scan(inv, pr, 0);
          info.setText(String.format("Successfully scaned "
              + pr.getName()
              + "%n" + "Quantity in store: "
              + inv.getQuantity().get(code)));
          info.setEnabled(false);
        } else {
          String proInfo = (String) JOptionPane
              .showInputDialog(receiverP, "Enter item info",
                  "Message", JOptionPane.PLAIN_MESSAGE,
                  null, null, "");
          String[] brokenLine = proInfo.split(",");
          Product pro = new Product(brokenLine[0],
              code, brokenLine[1], brokenLine[2],
              brokenLine[cc], brokenLine[dd]);
          pro.setShelfnum(Integer.valueOf(brokenLine[ee]));
          rece.scan(inv, pro, pro.getShelfnum());
          info.setText("Successfully scaned "
              + pro.getName()
              + ", Quantity in store:"
              + pro.getQuantity());
          info.setEnabled(false);
        }
      } catch (Exception b) {
        info.setText("You should enter"
            + " 'product name, price, cost, quantity, section, shelf'");
        info.setEnabled(false);
      }
    });
    viewButton.addActionListener(e -> {
      try {
        info.setText(rece.view(inv, upc.getText()));
        info.setEnabled(false);
      } catch (Exception b) {
        System.err.println(b.getMessage());
      }
    });
    workButton.addActionListener(e -> {
      info.setText("");
      for (int i = 0; i < rece.selfwork().size(); i++) {
        info.setText(info.getText() + rece.selfwork().get(i));
      }
      info.setText(info.getText() + "\n" + "END");
    });
    viewOrderButton.addActionListener(e ->
        Managerform.viewOrderFrame(fileOp.getPoFilePath(),
            "Pending Order"));
    acceptOrderButton.addActionListener(e -> {
      try {
        final int cc = 3;
        String orderInfo = (String) JOptionPane
            .showInputDialog(receiverP, "Enter order info",
                "Order", JOptionPane.PLAIN_MESSAGE,
                null, null, "");
        String[] brokenLine = orderInfo.split(", ");
        Product pro = productFinder.search(inv, brokenLine[0]);
        rece.acceptOrder(inv, orderInfo, pro, brokenLine[cc]);
        if (!Receiver.isMessage()) {
          JOptionPane.showMessageDialog(receiverP,
              "We do not have this Order",
              "Error Message",
              JOptionPane.ERROR_MESSAGE);
        } else {
          info.setText(nam
              + " accept order of "
              + pro.getName() + ", quantity added: " + brokenLine[cc]
              + " quantity in inventory: " + pro.getQuantity());
        }
      } catch (Exception b) {
        JOptionPane.showMessageDialog(receiverP,
            "Wrong Order format, type in 'Product upc"
                + ", Product name, Distributor, quantity'",
            "Error Message",
            JOptionPane.ERROR_MESSAGE);
      }
    });
  }


  /**
   * Return the main.Receiver Panel. size 500 x 500.
   *
   * @param nam Name of this main.Receiver.
   * @throws IOException Throw IOException.
   */
  static void show(final String nam) throws IOException {
    final int fh = 500;
    JFrame frame = new JFrame("ReceiverPan");
    frame.setContentPane(new ReceiverPan(nam).receiverP);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setSize(new Dimension(fh, fh));
    frame.setVisible(true);
  }
}
