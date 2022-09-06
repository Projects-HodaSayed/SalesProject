
package view;

import model.InvoiceHeader;
import model.InvoiceLine;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class NewHeaderForm extends JDialog implements ActionListener {
    private JSplitPane splitPane1;
    private JPanel panel1;
    private JSplitPane splitPane2;
    private JPanel panel3;

    private JScrollPane scrollPane3;
    private JPanel panel4;
    private JPanel panel2;
    private JSplitPane splitPane3;

    private JLabel invoiceDateLabel;

    private JTextField invoiceDatetxt;

    private JLabel customerNameLabel;
    private JTextField customerNametxt;

    private JButton okbtn;
    private JButton cancelbtn;

    private String InvoiceNumber;
    InvoiceHeader NewInvoiceHeader;
    SalesInvoiceForm salesInvoiceForm = null;

    public NewHeaderForm(SalesInvoiceForm salesInvoiceForm , String InvoiceNumber)
    {
        this.InvoiceNumber = InvoiceNumber;
        this.NewInvoiceHeader = null;
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

        invoiceDateLabel = new JLabel();
        invoiceDatetxt = new JTextField();
        customerNameLabel = new JLabel();
        customerNametxt = new JTextField();


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

                //---- invoiceDateLabel ----
                invoiceDateLabel.setText("Invoice Date");
                panel1.add(invoiceDateLabel);
                panel1.add(invoiceDatetxt);

                //---- customerNameLabel ----
                customerNameLabel.setText("Customer Name");
                panel1.add(customerNameLabel);
                panel1.add(customerNametxt);

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
                this.NewInvoiceHeader = createNewHeader();
                if(NewInvoiceHeader != null)
                {
                    salesInvoiceForm.updateInvoiceHeaderTable(NewInvoiceHeader);
                }
                this.dispose();
                break;
            case "cancel":
                this.dispose();
                break;
        }
    }

    private InvoiceHeader createNewHeader()
    {
        InvoiceHeader newInvoiceHeader = null;
        if(invoiceDatetxt.getText() != "" && customerNametxt.getText() != "" )
        {
            //int totalPrice = Integer.parseInt(itemPricetxt.getText()) * Integer.parseInt(itemCounttxt.getText());
            newInvoiceHeader = new InvoiceHeader(this.InvoiceNumber, invoiceDatetxt.getText(), customerNametxt.getText(), "");
        }
        return newInvoiceHeader;
    }
}
