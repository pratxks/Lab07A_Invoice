
public class Product 
{
    private String m_name;
    private double m_price;
    
    public Product()
    {
        m_name = "";
        m_price = 0.0f;
    }
    
    public Product(String name, double price)
    {
        m_name = name;
        m_price = price;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public double getM_price() {
        return m_price;
    }

    public void setM_price(double m_price) {
        this.m_price = m_price;
    }
}
