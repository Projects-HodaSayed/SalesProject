
import model.InvoiceHeader;
import model.InvoiceLine;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.Collections;

public class SalesInvoiceForm extends JFrame implements ActionListener {
    private JTable invoiceHeaderJTable;
    private JTable invoiceLinesJTable;
    private JMenuBar fileMenuBar;
    private JMenu fileMenu;
    private JMenuItem loadItem;
    private JMenuItem saveItem;

    private JSplitPane splitPane1;
    private JPanel panel1;
    private JSplitPane splitPane2;
    private JPanel panel3;
    private JScrollPane scrollPane3;
    private JPanel panel4;
    private JButton creatInvoicebtn;
    private JButton deleteInvoicebtn;
    private JPanel panel2;
    private JSplitPane splitPane3;
    private JPanel panel7;
    private JSplitPane splitPane4;
    private JPanel panel8;
    private JLabel invoiceLabel;
    private JTextField invoiceNotxt;
    private JLabel invoicedatelabel;
    private JTextField invoicedatetxt;
    private JLabel customernamelabel;
    private JTextField customernametxt;
    private JLabel invoicetotallabel;
    private JTextField invoicetotaltxt;
    private JPanel panel9;
    private JLabel invoiceitemeslabel;
    private JScrollPane scrollPane4;
    private JPanel panel6;
    private JButton savebtn;
    private JButton cancelbtn;
    private ArrayList<InvoiceHeader> invoiceHeaderList ;
    private ArrayList<InvoiceLine> invoiceHeaderLineList ;
    private boolean newInvoice;


    public SalesInvoiceForm()
    {
        initComponents();
        String pathheader = "src/InvoiceHeader.csv";

        //Create tabel from file.CSV
        createInvoiceTables(pathheader);

        this.setSize(1400, 1000);
        this.setVisible(true);
        //this.setAlwaysOnTop(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initComponents() {

        splitPane1 = new JSplitPane();
        panel1 = new JPanel();
        splitPane2 = new JSplitPane();
        panel3 = new JPanel();
        scrollPane3 = new JScrollPane();
        invoiceHeaderJTable = new JTable();
        panel4 = new JPanel();
        creatInvoicebtn = new JButton();
        deleteInvoicebtn = new JButton();
        panel2 = new JPanel();
        splitPane3 = new JSplitPane();
        panel7 = new JPanel();
        splitPane4 = new JSplitPane();
        panel8 = new JPanel();
        invoiceLabel = new JLabel();
        invoiceNotxt = new JTextField();
        invoicedatelabel = new JLabel();
        invoicedatetxt = new JTextField();
        customernamelabel = new JLabel();
        customernametxt = new JTextField();
        invoicetotallabel = new JLabel();
        invoicetotaltxt = new JTextField();
        panel9 = new JPanel();
        invoiceitemeslabel = new JLabel();
        scrollPane4 = new JScrollPane();
        invoiceLinesJTable = new JTable();
        panel6 = new JPanel();
        savebtn = new JButton();
        cancelbtn = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout());
        //======= Menu Bar
        fileMenuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        loadItem = new JMenuItem("Load File", 'L');
        loadItem.setAccelerator(KeyStroke.getKeyStroke('L', KeyEvent.CTRL_DOWN_MASK));
        loadItem.addActionListener(this);
        loadItem.setActionCommand("L");

        saveItem = new JMenuItem("SaveFile", 'S');
        saveItem.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_DOWN_MASK));
        saveItem.addActionListener(this);
        saveItem.setActionCommand("S");

        setJMenuBar(fileMenuBar);
        fileMenuBar.add(fileMenu);
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        //

        //======== splitPane1 ========
        {
            splitPane1.setDividerLocation(700);

            //======== panel1 ========

            panel1.setLayout(new GridLayout(1, 1));

                //======== splitPane2 ========
            {
                    splitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
                    splitPane2.setDividerLocation(850);

                    //======== panel3 ========
                    {
                        panel3.setLayout(new GridLayout());

                        //======== scrollPane3 ========
                        {

                            //---- invoiceHeaderJTable ----
                            invoiceHeaderJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                            invoiceHeaderJTable.setRowSelectionAllowed(true);;

                            ListSelectionModel select= invoiceHeaderJTable.getSelectionModel();
                            select.addListSelectionListener(new ListSelectionListener() {

                                        public void valueChanged(ListSelectionEvent event) {
                                            String Data = null;
                                            int[] row = invoiceHeaderJTable.getSelectedRows();
                                            // int[] columns = invoiceHeaderJTable.getSelectedColumns();

                                            for (int i = 0; i < row.length; i++) {
                                                for (int j = 0; j < 4; j++) {
                                                    invoiceNotxt.setText((String) invoiceHeaderJTable.getValueAt(row[i], 0));
                                                    invoicedatetxt.setText((String) invoiceHeaderJTable.getValueAt(row[i], 1));
                                                    customernametxt.setText((String) invoiceHeaderJTable.getValueAt(row[i], 2));
                                                    invoicetotaltxt.setText((String) invoiceHeaderJTable.getValueAt(row[i], 3));
                                                }
                                            }

                                            String[][] invoicelinesData = new String[invoiceHeaderLineList.size()][5];
                                            int mm = Integer.parseInt(invoiceNotxt.getText());
                                            int z = 0;
                                            for (int y = 0; y < invoiceHeaderLineList.size(); y++) {

                                                InvoiceLine invoiceLineobj = new InvoiceLine("", "", "", "", "");
                                                invoiceLineobj = invoiceHeaderLineList.get(y);

                                                int cc = Integer.parseInt(invoiceLineobj.getInvoiceNumber());
                                                if (mm == cc)
                                                {
                                                    int totalPrice = Integer.parseInt(invoiceLineobj.getPrice()) * Integer.parseInt(invoiceLineobj.getCount());
                                                    invoiceLineobj.settotal(String.valueOf(totalPrice));
                                                    invoicelinesData[z][0] = String.valueOf(invoiceLineobj.getInvoiceNumber());
                                                    invoicelinesData[z][1] = String.valueOf(invoiceLineobj.getItemName());
                                                    invoicelinesData[z][2] = String.valueOf(invoiceLineobj.getPrice());
                                                    invoicelinesData[z][3] = String.valueOf(invoiceLineobj.getCount());
                                                    invoicelinesData[z][4] = String.valueOf(invoiceLineobj.gettotal());
                                                    z++;
                                                }
                                            }

                                            invoiceLinesJTable.setModel(
                                                    new DefaultTableModel(invoicelinesData,
                                                            new String[]{
                                                                    "No.", "Item Name", "Item Price", "Count", "Item Total"
                                                            }
                                                    ));
                                        }
                                    }
                            );

                            invoiceHeaderJTable.setModel(new DefaultTableModel(
                                    new Object[][] {
                                            {null, null, null, null},
                                            {null, null, null, null},
                                    },
                                    new String[] {
                                            "No.", "Date", "Customer", "Total"
                                    }
                            ) {
                                Class<?>[] columnTypes = new Class<?>[] {
                                        String.class, String.class, String.class, String.class
                                };
                                @Override
                                public Class<?> getColumnClass(int columnIndex) {
                                    return columnTypes[columnIndex];
                                }
                            });
                            scrollPane3.setViewportView(invoiceHeaderJTable);
                        }
                        panel3.add(scrollPane3);
                    }
                    splitPane2.setTopComponent(panel3);

                    //======== panel4 ========
                    {
                        panel4.setLayout(new GridLayout(1, 2));

                        //---- creatInvoicebtn ----
                        creatInvoicebtn.setText("Create New Invoice");
                        creatInvoicebtn.setActionCommand("Create");
                        creatInvoicebtn.addActionListener(this);
                        panel4.add(creatInvoicebtn);

                        //---- deleteInvoicebtn ----
                        deleteInvoicebtn.setText("Delete Invoice");
                        deleteInvoicebtn.setActionCommand("Delete");
                        deleteInvoicebtn.addActionListener(this);
                        panel4.add(deleteInvoicebtn);
                    }
                    splitPane2.setBottomComponent(panel4);
                }
                panel1.add(splitPane2);

            splitPane1.setLeftComponent(panel1);

            //======== panel2 ========
            {
                panel2.setLayout(new GridLayout());

                //======== splitPane3 ========
                {
                    splitPane3.setOrientation(JSplitPane.VERTICAL_SPLIT);
                    splitPane3.setDividerLocation(850);

                    //======== panel7 ========
                    {
                        panel7.setLayout(new GridLayout());

                        //======== splitPane4 ========
                        {
                            splitPane4.setOrientation(JSplitPane.VERTICAL_SPLIT);
                            splitPane4.setDividerLocation(120);

                            //======== panel8 ========
                            {
                                panel8.setLayout(new GridLayout(4, 2, 0, 1));

                                //---- invoiceLabel ----
                                invoiceLabel.setText("Invoice No.");
                                invoiceLabel.setLabelFor(invoiceNotxt);
                                panel8.add(invoiceLabel);

                                //---- invoiceNotxt ----
                                invoiceNotxt.setEditable(false);
                                panel8.add(invoiceNotxt);

                                //---- invoicedatelabel ----
                                invoicedatelabel.setText("Invoice Date");
                                panel8.add(invoicedatelabel);
                                panel8.add(invoicedatetxt);

                                //---- customernamelabel ----
                                customernamelabel.setText("Customer Name");
                                panel8.add(customernamelabel);
                                panel8.add(customernametxt);

                                //---- invoicetotallabel ----
                                invoicetotallabel.setText("Invoice Total");
                                invoicetotallabel.setIcon(null);
                                invoicetotaltxt.setEditable(false);
                                panel8.add(invoicetotallabel);
                                panel8.add(invoicetotaltxt);
                            }
                            splitPane4.setTopComponent(panel8);

                            //======== panel9 ========
                            {
                                panel9.setLayout(new GridLayout(2, 1));

                                //---- invoiceitemeslabel ----
                                //invoiceitemeslabel.setText("Invoice Items");
                                //invoiceitemeslabel.setHorizontalTextPosition(SwingConstants.LEFT);
                                //invoiceitemeslabel.setPreferredSize(new Dimension(30, 10));
                                //panel9.add(invoiceitemeslabel);

                                //======== scrollPane4 ========
                                {

                                    //---- invoiceLinesJTable ----
                                    invoiceLinesJTable.setModel(new DefaultTableModel(
                                            new Object[][] {
                                                    {null, null, null, null, null},
                                                    {null, null, null, null, null},
                                            },
                                            new String[] {
                                                    "No.", "Item Name", "Item Price", "Count", "Item Total"
                                            }
                                    ) {
                                        Class<?>[] columnTypes = new Class<?>[] {
                                                String.class, String.class, String.class, String.class, String.class
                                        };
                                        @Override
                                        public Class<?> getColumnClass(int columnIndex) {
                                            return columnTypes[columnIndex];
                                        }
                                    });
                                   /* invoiceLinesJTable.setBorder(new TitledBorder(new CompoundBorder(
                                            new BevelBorder(BevelBorder.RAISED),
                                            null), "Invoice Items", TitledBorder.ABOVE_TOP, TitledBorder.ABOVE_TOP));*/
                                    scrollPane4.setViewportView(invoiceLinesJTable);
                                }
                                panel9.add(scrollPane4);
                            }
                            splitPane4.setBottomComponent(panel9);
                        }
                        panel7.add(splitPane4);
                    }
                    splitPane3.setTopComponent(panel7);

                    //======== panel6 ========
                    {
                        panel6.setLayout(new GridLayout(1, 2));

                        //---- savebtn ----
                        savebtn.setText("Save");
                        savebtn.setActionCommand("Save");
                        savebtn.addActionListener(this);
                        savebtn.setPreferredSize(new Dimension(78, 10));
                        panel6.add(savebtn);

                        //---- cancelbtn ----
                        cancelbtn.setText("Cancel");
                        cancelbtn.setActionCommand("Cancel");
                        cancelbtn.addActionListener(this);
                        panel6.add(cancelbtn);
                    }
                    splitPane3.setBottomComponent(panel6);
                }
                panel2.add(splitPane3);
            }
            splitPane1.setRightComponent(panel2);
        }
        contentPane.add(splitPane1);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void createInvoiceTables(String pathheader)
    {
        BufferedReader bufReader = null;
        invoiceHeaderList = new ArrayList<InvoiceHeader>();
        invoiceHeaderLineList = new ArrayList<InvoiceLine>();

        //Invoice Header File
        try {
            //String pathheader = "/Users/hodasayed/Downloads/Sales Invoice Generator/InvoiceHeader.csv";

            File file = new File(pathheader);
            FileReader interpleader = new FileReader(file);
            bufReader = new BufferedReader(interpleader);

            String readiness;

            while ((readiness = bufReader.readLine()) != null)
            {
                String[] splitData = readiness.split(",");

                InvoiceHeader invoiceHeader = new InvoiceHeader(splitData[0], splitData[1], splitData[2],"");
                invoiceHeaderList.add(invoiceHeader);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufReader.close();
            } catch (IOException e) {
            }
        }
        //Invoice Lines File
        try {
            //String pathline2 = "/Users/hodasayed/Downloads/Sales Invoice Generator/InvoiceLine.csv";
            String pathline2 = pathheader.replaceFirst("InvoiceHeader","InvoiceLine") ;

            File file = new File(pathline2);
            FileReader interpleader = new FileReader(file);
            bufReader = new BufferedReader(interpleader);

            String readiness2;

            while ((readiness2 = bufReader.readLine()) != null)
            {
                String[] splitData = readiness2.split(",");

                InvoiceLine invoiceLine = new InvoiceLine(splitData[0],splitData[1], splitData[2],splitData[3],"");
                invoiceHeaderLineList.add(invoiceLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufReader.close();
            } catch (IOException e) {
            }
        }

        String[][] headerData = new String[invoiceHeaderList.size()][4];

        for(int i = 0 ; i < invoiceHeaderList.size(); i++)
        {
            InvoiceHeader invoiceHeaderobj = new InvoiceHeader("","","","");
            invoiceHeaderobj = invoiceHeaderList.get(i);
            int totalPrice = 0;
            for(int y = 0 ; y < invoiceHeaderLineList.size();y++)
            {
                InvoiceLine invoiceLineobj = new InvoiceLine("","","","","");
                invoiceLineobj = invoiceHeaderLineList.get(y);
                int mm = Integer.parseInt(invoiceHeaderobj.getInvoiceNumber());
                int cc = Integer.parseInt(invoiceLineobj.getInvoiceNumber());
                if(mm == cc)
                {
                totalPrice += Integer.parseInt(invoiceLineobj.getPrice()) * Integer.parseInt(invoiceLineobj.getCount());
                }
            }
            invoiceHeaderobj.settotal(String.valueOf(totalPrice));
            headerData [i][0] = String.valueOf(invoiceHeaderobj.getInvoiceNumber());
            headerData [i][1] = String.valueOf(invoiceHeaderobj.getInvoiceDate());
            headerData [i][2] = String.valueOf(invoiceHeaderobj.getCustomerName());
            headerData [i][3] = String.valueOf(invoiceHeaderobj.gettotal());
        }
        invoiceHeaderJTable.setModel(
                new DefaultTableModel(headerData,
                new String[] {
                        "No.", "Date", "Customer", "Total"
                }
        ));
    }

    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand()) {
            case "L":
                loadFile();
                break;
            case "S":
                saveFile();
                break;
            case "Create":
                clearRightPanel();
                newInvoice = true;
                createnewInvoicenumber();
                break;
            case "Delete":
                DeleteInvoice();
                break;
            case "Save":
                saveFile();
                break;
            case "Cancel":
                this.dispose();
                break;
        }
    }

    private void createnewInvoicenumber()
    {
        int max = 0;
        int invoicenumber = 0;
        InvoiceHeader invoiceHeaderobj = new InvoiceHeader("", "", "", "");
        for(int i = 0 ; i < invoiceHeaderList.size(); i++)
        {
            invoiceHeaderobj = invoiceHeaderList.get((i));
            invoicenumber= Integer.valueOf(invoiceHeaderobj.getInvoiceNumber());
            if(max > invoicenumber)
            {
            max = max;
            }
            else {
            max = invoicenumber;
            }
        }
        invoiceNotxt.setText(String.valueOf(max+1) );
    }

    private void DeleteInvoice()
    {
        int[] row = invoiceHeaderJTable.getSelectedRows();
        if(row != null )
        {
            String selectedinvoiceNumber = "";
            selectedinvoiceNumber = (String) invoiceHeaderJTable.getValueAt(row[0], 0);

            String[][] headerData = new String[invoiceHeaderList.size()][4];

            for(int i = 0 ; i < invoiceHeaderList.size(); i++) {
                InvoiceHeader invoiceHeaderobj = new InvoiceHeader("", "", "", "");
                invoiceHeaderobj = invoiceHeaderList.get(i);
                int totalPrice = 0;
                int mm = Integer.parseInt(invoiceHeaderobj.getInvoiceNumber());
                int cc = Integer.parseInt(selectedinvoiceNumber);

                if (mm == cc) {
                    invoiceHeaderList.remove(i);
                    break;
                }
            }
            for(int i = 0 ; i < invoiceHeaderList.size(); i++)
            {
                InvoiceHeader invoiceHeaderobj = new InvoiceHeader("", "", "", "");
                invoiceHeaderobj = invoiceHeaderList.get(i);

                headerData[i][0] = String.valueOf(invoiceHeaderobj.getInvoiceNumber());
                headerData[i][1] = String.valueOf(invoiceHeaderobj.getInvoiceDate());
                headerData[i][2] = String.valueOf(invoiceHeaderobj.getCustomerName());
                headerData[i][3] = String.valueOf(invoiceHeaderobj.gettotal());
            }
                invoiceHeaderJTable.setModel(
                    new DefaultTableModel(headerData,
                            new String[] {
                                    "No.", "Date", "Customer", "Total"
                            }
                    ));

            String[][] invoicelinesData = new String[invoiceHeaderLineList.size()][5];
            int mm = Integer.parseInt(selectedinvoiceNumber);

            for (int y = 0; y < invoiceHeaderLineList.size(); y++)
            {
                InvoiceLine invoiceLineobj = new InvoiceLine("", "", "", "", "");
                invoiceLineobj = invoiceHeaderLineList.get(y);
                int cc = Integer.parseInt(invoiceLineobj.getInvoiceNumber());
                if (mm == cc) {
                    invoiceHeaderLineList.remove(y);
                    break;
                }
            }
            for (int y = 0; y < invoiceHeaderLineList.size(); y++)
            {
                InvoiceLine invoiceLineobj = new InvoiceLine("", "", "", "", "");
                invoiceLineobj = invoiceHeaderLineList.get(y);

                invoicelinesData[y][0] = String.valueOf(invoiceLineobj.getInvoiceNumber());
                invoicelinesData[y][1] = String.valueOf(invoiceLineobj.getItemName());
                invoicelinesData[y][2] = String.valueOf(invoiceLineobj.getPrice());
                invoicelinesData[y][3] = String.valueOf(invoiceLineobj.getCount());
                invoicelinesData[y][4] = String.valueOf(invoiceLineobj.gettotal());
            }

            /*invoiceLinesJTable.setModel(
                    new DefaultTableModel(invoicelinesData,
                            new String[]{
                                    "No.", "Item Name", "Item Price", "Count", "Item Total"
                            }
                    ));
                    */
        }
        clearRightPanel();
    }

    private  void loadFile()
    {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(this);
        BufferedReader bufReader = null;

        if (result == JFileChooser.APPROVE_OPTION)
        {
                String path = fc.getSelectedFile().getPath();
                createInvoiceTables(path);
                clearRightPanel();
        }

    }
    private  void saveFile()
    {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(this);
        BufferedWriter bufferwriter = null;
        BufferedWriter bufferwriterlines = null;

        if (result == JFileChooser.APPROVE_OPTION)
        {
            try {

                String path = fc.getSelectedFile().getPath();
                String pathLines = path.replace("InvoiceHeader","InvoiceLine");

                FileWriter writer = new FileWriter(path);
                FileWriter writer2 = new FileWriter(pathLines);

                bufferwriter = new BufferedWriter(writer);
                bufferwriterlines = new BufferedWriter(writer2);

                String datatoreadHeader = "";
                int invoiceNumeber = 0;

                //Add Invoice Header
                if(newInvoice)
                {
                    for(int i = 0 ; i < invoiceHeaderList.size(); i++)
                    {
                        InvoiceHeader invoiceHeaderobj = new InvoiceHeader("", "", "", "");
                        invoiceHeaderobj = invoiceHeaderList.get(i);
                        datatoreadHeader += String.valueOf(invoiceHeaderobj.getInvoiceNumber()) + "," +
                                    String.valueOf(invoiceHeaderobj.getInvoiceDate()) + "," +
                                    String.valueOf(invoiceHeaderobj.getCustomerName()) +
                                    "\r\n";
                    }
                    InvoiceHeader newinvoiceHeaderobj = new InvoiceHeader(invoiceNotxt.getText(), invoicedatetxt.getText() , customernametxt.getText(),"");
                    invoiceHeaderList.add(newinvoiceHeaderobj);
                    datatoreadHeader += invoiceNotxt.getText() + "," + invoicedatetxt.getText() + "," + customernametxt.getText()+"\r\n";
                }
                else {
                    for (int i = 0; i < invoiceHeaderList.size(); i++) {
                        InvoiceHeader invoiceHeaderobj = new InvoiceHeader("", "", "", "");
                        invoiceHeaderobj = invoiceHeaderList.get(i);
                        if (invoiceNotxt.getText() != "" && invoiceNumeber == Integer.parseInt(invoiceHeaderobj.getInvoiceNumber())) {
                            datatoreadHeader += invoiceNotxt.getText() + "," + invoicedatetxt.getText() + "," + customernametxt.getText() + "\r\n";
                        } else {
                            datatoreadHeader += String.valueOf(invoiceHeaderobj.getInvoiceNumber()) + "," +
                                    String.valueOf(invoiceHeaderobj.getInvoiceDate()) + "," +
                                    String.valueOf(invoiceHeaderobj.getCustomerName()) +
                                    "\r\n";
                        }
                    }
                }

                bufferwriter.write(datatoreadHeader);
                bufferwriter.close();

                //--------Add Invoice Lines

                int invoiceNumeberLine = 0;
                String datatoWriteLines = "";
                for(int i = 0;i<invoiceLinesJTable.getRowCount();i++)
                {
                    if ((String) invoiceLinesJTable.getModel().getValueAt(i, 0) != null)
                    {
                        invoiceNumeberLine = Integer.parseInt((String) invoiceLinesJTable.getModel().getValueAt(i, 0));

                        datatoWriteLines +=
                                (String) invoiceLinesJTable.getModel().getValueAt(i, 0) + "," +
                                        (String) invoiceLinesJTable.getModel().getValueAt(i, 1) + "," +
                                        (String) invoiceLinesJTable.getModel().getValueAt(i, 2) + "," +
                                        (String) invoiceLinesJTable.getModel().getValueAt(i, 3) +
                                        "\r\n";
                    }
                }
                        for (int y = 0; y < invoiceHeaderLineList.size(); y++)
                        {
                            InvoiceLine invoiceLineobj = new InvoiceLine("", "", "", "", "");
                            invoiceLineobj = invoiceHeaderLineList.get(y);

                            int cc = Integer.parseInt(invoiceLineobj.getInvoiceNumber());
                            if (Integer.parseInt(invoiceNotxt.getText()) != cc) {
                                datatoWriteLines += String.valueOf(invoiceLineobj.getInvoiceNumber())+ "," +
                                String.valueOf(invoiceLineobj.getItemName())+ "," +
                                String.valueOf(invoiceLineobj.getPrice())+ "," +
                                String.valueOf(invoiceLineobj.getCount())+
                                "\r\n";
                            }
                        }

                bufferwriterlines.write(datatoWriteLines);
                bufferwriterlines.close();
            }
            catch (FileNotFoundException e)
            {
            e.printStackTrace();
            }
            catch (IOException e)
            {
            e.printStackTrace();
            }
            finally {
                try {
                    bufferwriter.close();
                    bufferwriterlines.close();
                } catch (IOException e) {
                }
            }

        }

    }
    private void clearRightPanel()
    {
        invoicetotaltxt.setText("");
        invoiceNotxt.setText("");
        customernametxt.setText("");
        invoicedatetxt.setText("");
        invoiceLinesJTable.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                },
                new String[] {
                        "No.", "Item Name", "Item Price", "Count", "Item Total"
                }
        ) );

    }
}
