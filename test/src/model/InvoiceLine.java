package model;

public class InvoiceLine
{
    private String invoiceNumber;
    private String itemname;
    private String price;
    private String count;
    private String total;


    public InvoiceLine(String invoiceNumber, String itemname, String price,String count, String total) {
        this.invoiceNumber = invoiceNumber;
        this.itemname = itemname;
        this.price = price;
        this.count = count;
        this.total = total;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getItemName() {
        return itemname;
    }

    public void setItemName(String invoiceDate) {
        this.itemname = itemname;
    }

    public String getPrice() {return price;}

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {return count;}

    public void setCount(String total) {
        this.total = count;
    }

    public String gettotal() {return total;}

    public void settotal(String total) {
        this.total = total;
    }

}
