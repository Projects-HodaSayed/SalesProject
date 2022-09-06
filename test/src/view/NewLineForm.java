package view;

import model.InvoiceLine;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class NewLineForm extends JDialog implements ActionListener {
    private JSplitPane splitPane1;
    private JPanel panel1;
    private JSplitPane splitPane2;
    private JPanel panel3;
    private JScrollPane scrollPane3;
    private JPanel panel4;
    private JPanel panel2;
    private JSplitPane splitPane3;

    private JLabel itemNameLabel;

    private JTextField itemNametxt;

    private JLabel itemCountLabel;
    private JTextField itemCounttxt;
    private JLabel itemPriceLabel;
    private JTextField itemPricetxt;

    private JButton okbtn;
    private JButton cancelbtn;

    private String InvoiceNumber;
    InvoiceLine NewInvoiceLine;
    SalesInvoiceForm salesInvoiceForm = null;

    public NewLineForm(SalesInvoiceForm salesInvoiceForm , String InvoiceNumber)
    {
        this.InvoiceNumber = InvoiceNumber;
        this.NewInvoiceLine = null;
        this.salesInvoiceForm = salesInvoiceForm;
        initComponents();
        this.setSize(500, 300);
        this.setVisible(true);
        //this.setAlwaysOnTop(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {

        splitPane1 = new JSplitPane();
        panel1 = new JPanel();
        splitPane2 = new JSplitPane();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel2 = new JPanel();

        itemNameLabel = new JLabel();
        itemNametxt = new JTextField();
        itemCountLabel = new JLabel();
        itemCounttxt = new JTextField();
        itemPriceLabel = new JLabel();
        itemPricetxt = new JTextField();

        okbtn = new JButton();
        cancelbtn = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout());

        //======== splitPane1 ========
        {
            splitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
            splitPane1.setDividerLocation(200);

            //======== panel1 ========
            {
                panel1.setLayout(new GridLayout());
                panel1.setLayout(new GridLayout(3, 2, 0, 1));

                //---- itemNameLabel ----
                itemNameLabel.setText("Item name");
                panel1.add(itemNameLabel);
                panel1.add(itemNametxt);

                //---- itemCountLabel ----
                itemCountLabel.setText("Item Count");
                panel1.add(itemCountLabel);
                panel1.add(itemCounttxt);

                //---- itemPriceLabel ----
                itemPriceLabel.setText("Item Price");
                panel1.add(itemPriceLabel);
                panel1.add(itemPricetxt);
            }
            splitPane1.setTopComponent(panel1);

                //======== panel2 ========
                {
                    panel2.setLayout(new GridLayout(1, 2));

                    //---- creatInvoicebtn ----
                    okbtn.setText("Ok");
                    okbtn.setActionCommand("ok");
                    okbtn.addActionListener(this);
                    panel2.add(okbtn);

                    //---- deleteInvoicebtn ----
                    cancelbtn.setText("Cancel");
                    cancelbtn.setActionCommand("cancel");
                    cancelbtn.addActionListener(this);
                    panel2.add(cancelbtn);
                }
                splitPane1.setBottomComponent(panel2);

        }
        contentPane.add(splitPane1);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand()) {
            case "ok":
                this.NewInvoiceLine = createNewLine();
                if(NewInvoiceLine != null)
                {
                    salesInvoiceForm.updateInvoiceLineTable(NewInvoiceLine);
                }
                this.dispose();
                break;
            case "cancel":
                this.dispose();
                break;
        }
    }

    private InvoiceLine createNewLine()
    {
        InvoiceLine newInvoiceLine = null;
        if(itemNametxt.getText() != "" && itemPricetxt.getText() != "" &&
            itemCounttxt.getText() != "")
        {
            int totalPrice = Integer.parseInt(itemPricetxt.getText()) * Integer.parseInt(itemCounttxt.getText());
            newInvoiceLine = new InvoiceLine(this.InvoiceNumber, itemNametxt.getText(), itemPricetxt.getText(),
                    itemCounttxt.getText(), String.valueOf(totalPrice));
        }
        return newInvoiceLine;
    }
}
