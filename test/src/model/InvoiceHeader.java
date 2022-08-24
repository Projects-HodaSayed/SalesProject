package model;//import java.util.Date;

public class InvoiceHeader {

    private String invoiceNumber;
    private String invoiceDate;
    private String customerName;

    private String total;


    public InvoiceHeader(String invoiceNumber, String invoiceDate, String customerName,String total) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
        this.total = total;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String gettotal() {return total;}

    public void settotal(String total) {
        this.total = total;
    }
}
