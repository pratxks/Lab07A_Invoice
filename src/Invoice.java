
import java.util.ArrayList;


public class Invoice 
{
    private String customer_address;
    private double total_amount_due;
    private ArrayList<LineItem> item_list;

    public Invoice()
    {
        customer_address = "";
        total_amount_due = 0.0f;
        item_list = new ArrayList<>();
    }
    
    public Invoice(Invoice copyInvoice)
    {
        customer_address = copyInvoice.customer_address;
        total_amount_due = copyInvoice.total_amount_due;
        item_list = new ArrayList<>();
        
        for(LineItem item : copyInvoice.item_list)
        {
            item_list.add(item);
        }
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public double getTotal_amount_due() {
        return total_amount_due;
    }

    public void setTotal_amount_due(double total_amount_due) {
        this.total_amount_due = total_amount_due;
    }

    public LineItem getLineItem(int itemindex)
    {
        if(itemindex < item_list.size())
        {
            return item_list.get(itemindex);
        }
        
        return null;
    }
    
    public void addLineItem(LineItem item)
    {
        item_list.add(item);
    }
    
    public ArrayList<LineItem> getItem_list() 
    {
        return item_list;
    }
}
