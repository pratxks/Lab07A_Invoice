
public class LineItem 
{
    private Product m_item;
    private int m_quantity;
    private double m_itemtotal;
    
    public LineItem(Product item, int quantity)
    {
        m_item = item;
        m_quantity = quantity;
    }

    public Product getM_item() {
        return m_item;
    }

    public void setM_item(Product m_item) {
        this.m_item = m_item;
    }

    public int getM_quantity() {
        return m_quantity;
    }

    public void setM_quantity(int m_quantity) {
        this.m_quantity = m_quantity;
    }

    public double getM_itemtotal() {
        return (m_item.getM_price() * m_quantity);
    }
   
}
