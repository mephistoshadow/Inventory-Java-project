package gui;

import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import main.Product;
import main.Order;
import main.Revenue;
import main.Cashier;
import search.FindProduct;


/**
 * Cashier GUI panel.
 * Cashier can scan, set product sale,cancel product sale status and checkout.
 * Type the upc code and display certain product.
 */
public final class Cashierform {

  /**
   * this is cashier panel.
   */
  private JPanel cashierpan;
  /**
   * Output area, print the information in the screen.
   */
  private JTextArea output;
  /**
   * The upc code for certain product.
   */
  private JTextField upc;
  /**
   * Scan button, press this button you can sell this product.
   */
  private JButton scanButton;
  /**
   * Search button, press this button your can search the price of
   * this button.
   */
  private JButton searchButton;
  /**
   * Set sale button, press this button you can set this product on sale.
   */
  private JButton setsaleButton;
  /**
   * Cancel sale status, press this button you can cancel the sale
   * status for product.
   */
  private JButton unsaleButton;
  /**
   * Cashier name.
   */
  private JLabel name;
  /**
   * If one product is set to be sale,this product will be
   * put into salemap.Each product correspond to one date.
   */
  private static HashMap<Product, Integer> salemap = new HashMap<>();
  /**
   * Check button. press this button you can checkout.
   */
  private JButton checkout;
  /**
   * What does this cashier do. press this button you can see.
   */
  private JButton workButton;
  /**
   * Certain upc correspond to certain Product.
   */
  private Product pro;
  /**
   * Total cost of your shopping.
   */
  private Double totalCost = 0.0;
  /**
   * The price of this product.
   */
  private String price;
  /**
   * Two payment method you can choose when you pay.
   */
  private static final String[] METHOD = {"Payment:Cash", "Payment:Credit"};
  /**
   * Sale product list, all sale products will contains in this list.
   */
  private static ArrayList<Product> prolist = new ArrayList<>();

  /**
   * Clear the cost.
   */
  private void setTotalCost() {
    totalCost = 0.0;
  }

  /**
   * Getter method for salemap.
   *
   * @return salemap.
   */
  public static HashMap<Product, Integer> getSalemap() {
    return salemap;
  }

  /**
   * Getter method for product list.
   *
   * @return prolist.
   */
  static ArrayList<Product> getProlist() {
    return prolist;
  }

  /**
   * Create an order panel when this product is lower than the threshold.
   *
   * @param prod Certain product correspond to the product.
   */
  private void orderPanel(final Product prod) {
    final int three = 3;
    if (prod.getQuantity().equals(pro.getThreshold())) {
      String order = (String) JOptionPane.showInputDialog(cashierpan,
          "This product "
              + "is almost sold out, " + "order from ", "Order",
          JOptionPane.PLAIN_MESSAGE,
          null, null, null);
      String check = (String) JOptionPane.showInputDialog(cashierpan,
          "Do you want set your own amount of quantity? Type yes or no!",
          "Check", JOptionPane.PLAIN_MESSAGE,
          null, null, null);
      try {
        if (check.equals("yes")) {
          String quantity = (String) JOptionPane.showInputDialog(cashierpan,
              "The amount you want to order! ", "Order",
              JOptionPane.PLAIN_MESSAGE,
              null, null, null);
          new Order(prod.getName(), prod.getUpc(), order,
              String.valueOf(quantity));
        } else {
          Integer orderNum = three * Integer.valueOf(prod.getThreshold());
          new Order(prod.getName(), prod.getUpc(), order,
              String.valueOf(orderNum));
        }
      } catch (Exception b) {
        System.err.println(b.getMessage());
      }
      JOptionPane.showMessageDialog(null, "Send this order to " + order);
    }
  }

  /**
   * Cashier panel, has scan button, set sale button,
   * set unsale button, search button and
   * checkout button.
   *
   * @param nam the name of this cashier.
   * @throws IOException throws IOException.
   */
  private Cashierform(final String nam) throws IOException {
    output.setEnabled(false);
    Cashier cashier = Mainpane.getWorkerfinder().getCashierMap().get(nam);
    name.setText(nam);
    FindProduct product = new FindProduct();
    Revenue revenue = new Revenue();

    scanButton.addActionListener(e -> {
      String code = upc.getText();
      if (product.whetherExist(Mainpane.getInventory(), code)) {
        pro = product.search(Mainpane.getInventory(), code);
        orderPanel(pro);
        if (pro.getQuantity().equals("0")) {
          output.setText(output.getText()
              + "This product sold out,don't scan" + "\n");
        } else {
          price = pro.getPrice();
          double priced = Double.parseDouble(price);
          totalCost += priced;
          try {
            cashier.scan(Mainpane.getInventory(), code);
            output.setText(output.getText() + "Product: " + pro.getName()
                + "," + "   Quantity: 1,"
                + " Price: " + "$" + pro.getPrice() + "\n");
          } catch (Exception b) {
            System.err.println(b.getMessage());
          }
        }
      } else {
        output.setText(output.getText() + "Sorry, we don't "
            + "have this product." + "\n");
      }
    });

    checkout.addActionListener(e -> {
      output.setText(output.getText() + "\n" + "Your total cost is "
          + revenue.round(totalCost) + "." + "\n");

      JOptionPane.showInputDialog(cashierpan, "Your total cost is "
              + revenue.round(totalCost), "Message",
          JOptionPane.PLAIN_MESSAGE,
          null, METHOD, METHOD[0]);
      output.setText("");
      setTotalCost();
    });

    searchButton.addActionListener(e -> {
      String code = upc.getText();
      if (product.whetherExist(Mainpane.getInventory(), code)) {
        pro = product.search(Mainpane.getInventory(), code);
        price = pro.getPrice();
        if (pro.isSale()) {
          output.setText("The price of " + pro.getName() + " is " + price
              + " and its sale date is " + pro.getSaleDate()
              + " days. Only " + salemap.get(pro)
              + " days left!! " + "\n");
        } else {
          output.setText("The price of " + pro.getName() + " is " + price
              + "." + "\n");
        }
      } else {
        output.setText("Sorry, we don't have this product."
            + "\n");
      }

    });
    setsaleButton.addActionListener(e -> {
      String code = upc.getText();

      String date = (String) JOptionPane.showInputDialog(
          cashierpan, "How many days for sale? ", "Message",
          JOptionPane.PLAIN_MESSAGE,
          null, null, null);
      pro = product.search(Mainpane.getInventory(), code);
      pro.setSale(date);

      prolist.add(pro);
      Integer date1 = Integer.valueOf(date);
      salemap.put(pro, date1);


    });
    unsaleButton.addActionListener(e -> {
      String code = upc.getText();
      pro = product.search(Mainpane.getInventory(), code);
      if (salemap.containsKey(pro)) {
        pro.setUnsale();
        salemap.remove(pro);
      }
    });
    workButton.addActionListener(
        e -> output.setText(cashier.getName()
            + " total revenue is " + cashier.selfwork().get(0)
            + ". Total product sold is " + cashier.selfwork().get(1) + ".\n"));
  }




  /**
   * Show this cashier panel.
   *
   * @param nam the name of this cashier.
   * @throws IOException throws IOException.
   */
  static void show(final String nam) throws IOException {
    final int fiveH = 500;
    JFrame frame = new JFrame("Cashierform");
    frame.setContentPane(new Cashierform(nam).cashierpan);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
    frame.setSize(new Dimension(fiveH, fiveH));
  }


}
