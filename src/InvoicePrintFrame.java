
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class InvoicePrintFrame extends JFrame
{
    private JPanel mainPanel;
    private JPanel titleandAddressPanel;
    private JPanel invoiceDisplayPanel;
    
    private JLabel invoiceTitleLabel;
    private JTextArea customerAddress;
    
    private JTextArea invoiceDisplayArea;
    
    private Invoice invoiceInstance;
    
    public InvoicePrintFrame(Invoice invInstance)
    {
        setTitle("Invoice");
        
        invoiceInstance = new Invoice(invInstance);
    }
    
    public void SetInvoicePrintFrameDisplay()
    {
        mainPanel = new JPanel();
        
        mainPanel.setLayout(new BorderLayout());
                
        createTitleandAddressPanel();
        createInvoiceDisplayPanel();
        
        mainPanel.add(titleandAddressPanel, BorderLayout.NORTH);
        mainPanel.add(invoiceDisplayPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        createInvoicePrint();
    }

    private void createTitleandAddressPanel() 
    {
        titleandAddressPanel = new JPanel();
        
        Font titleFont  = new Font(Font.SANS_SERIF, Font.BOLD, 28);
        Font addressFont  = new Font(Font.MONOSPACED, Font.PLAIN, 14);
        
        GridBagLayout titleGridBagLayout = new GridBagLayout();
        
        GridBagConstraints bagConstraints1 = new GridBagConstraints();
        
        titleandAddressPanel.setLayout(titleGridBagLayout);
        
        invoiceTitleLabel = new JLabel("INVOICE");
        
        invoiceTitleLabel.setFont(titleFont);
        
        bagConstraints1.fill = GridBagConstraints.NONE;
        bagConstraints1.insets = new Insets(0, 0, 0, 0);
        bagConstraints1.anchor = GridBagConstraints.CENTER;
        
        bagConstraints1.gridx = 2;
        bagConstraints1.gridy = 0;
        bagConstraints1.gridwidth = 3;
        
        titleandAddressPanel.add(invoiceTitleLabel, bagConstraints1);
        
        JPanel addressPanel = new JPanel();
        
//        addressPanel.setLayout(new BorderLayout());
        
        customerAddress = new JTextArea("");
        customerAddress.setRows(3);
        customerAddress.setColumns(30);
        customerAddress.setEditable(false);
        customerAddress.setFont(addressFont);
        
//        addressPanel.add(customerAddress, BorderLayout.LINE_START);
        
        //top, left, bottom, right
        bagConstraints1.fill = GridBagConstraints.NONE;
        bagConstraints1.insets = new Insets(10, 0, 0, 0);
        bagConstraints1.anchor = GridBagConstraints.WEST;
        
        bagConstraints1.gridx = 0;
        bagConstraints1.gridy = 1;
        
        titleandAddressPanel.add(customerAddress, bagConstraints1);
    }

    private void createInvoiceDisplayPanel() 
    {
        invoiceDisplayPanel = new JPanel();
        
        Font invoiceDisplayFont  = new Font(Font.MONOSPACED, Font.BOLD, 14);
        
        invoiceDisplayArea = new JTextArea(10, 50);
        invoiceDisplayArea.setFont(invoiceDisplayFont);
        invoiceDisplayArea.setEditable(false);
        
        JScrollPane invoiceDisScrollPane = new JScrollPane(invoiceDisplayArea);
        invoiceDisScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        invoiceDisScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        invoiceDisplayPanel.add(invoiceDisScrollPane);
    }

    private void createInvoicePrint()
    {
        customerAddress.setText(invoiceInstance.getCustomer_address());
        
        String headerAndFooter = new String(new char[50]).replace("\0", "=");
        invoiceDisplayArea.append(headerAndFooter + "\n");
        
        String tableHeader = String.format("%-22s", "Item");
        tableHeader += String.format("%5s", "Qty");
        tableHeader += String.format("%11s", "Price");
        tableHeader += String.format("%12s", "Total");
        invoiceDisplayArea.append(tableHeader + "\n\n");
        
        double total_amount_due = 0.0f;
        
        for(LineItem item : invoiceInstance.getItem_list())
        {
            String item_str = String.format("%-22s", item.getM_item().getM_name());
            item_str += String.format("%5s", item.getM_quantity());
            String strPrice = String.format("%.2f", item.getM_item().getM_price());
            item_str += String.format("%11s", "$" + strPrice);
            String strItemTotal = String.format("%.2f", item.getM_itemtotal());
            item_str += String.format("%12s", "$" + strItemTotal);
            
            total_amount_due += item.getM_itemtotal();
            
            invoiceDisplayArea.append(item_str + "\n");
        }
        
        invoiceDisplayArea.append(headerAndFooter + "\n");
        
        if(total_amount_due > 0)
        {
            String strAmountDueLabel = String.format("%-13s", "AMOUNT DUE:");
            String strTotalAmountDue = String.format("%.2f", total_amount_due);
            invoiceDisplayArea.append("\n" + strAmountDueLabel + "$" + strTotalAmountDue + "\n");
        }        
    }
}
