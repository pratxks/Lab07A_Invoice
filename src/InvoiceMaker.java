
import javax.swing.JFrame;


public class InvoiceMaker 
{
    public static void main(String[] args) 
    {
        InvoiceItemInputFrame MyInvoiceItemInputFrame = new InvoiceItemInputFrame();

        MyInvoiceItemInputFrame.SetInvoiceItemInputFrameDisplay();

        MyInvoiceItemInputFrame.setSize(900, 400);

        MyInvoiceItemInputFrame.setResizable(false);

        MyInvoiceItemInputFrame.setLocationRelativeTo(null);
        
        MyInvoiceItemInputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyInvoiceItemInputFrame.setVisible(true);        
    }
}
