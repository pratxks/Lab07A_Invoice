
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;

class LimitedRowTextArea extends JTextArea 
{
   private int maxRows = 0;

   public LimitedRowTextArea()
   {
       
   }
   
   public void setMaxRows(int maxRows) {
      this.maxRows = maxRows;
   }

   public int getMaxRows() {
      return maxRows; 
   }
   
   public void replaceSelection(String content) 
   {
       if ((getMaxRows() > 0) && (getLineCount() > getMaxRows()))  
       {
           this.getToolkit().beep(); // or whatever warning
           return;
       }
       
       super.replaceSelection(content);
   }   
}

public class InvoiceItemInputFrame extends JFrame
{
    private JPanel mainPanel;
    private JPanel customerAddressPanel;
    private JPanel invoiceInputPanel;
    private JPanel controlPanel;
    
    private LimitedRowTextArea customerAddress;
//    private JTextArea customerAddress;
    
    private JTextField productName;
    private JTextField productPrice;
    private JTextField productQuantity;
    private JTextField invoiceItemCount;
        
    private JButton addLineItemButton;
    private JButton printInvoiceButton;
    private JButton quitButton;
   
    private int itemCount;
    private Invoice invoiceInstance;
    
    public InvoiceItemInputFrame()
    {
        setTitle("Invoice Item Input Frame");
    }
    
    public void SetInvoiceItemInputFrameDisplay()
    {
        mainPanel = new JPanel();

        createCustomerAddressPanel();
        createInvoiceInputPanel();
        createControlPanel();

        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(customerAddressPanel, BorderLayout.NORTH);
        mainPanel.add(invoiceInputPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel);
        
        itemCount = 0;
        invoiceInstance = new Invoice();
    }
    
    private void createCustomerAddressPanel() 
    {
        customerAddressPanel = new JPanel();
        
        Font customerAddLabelFont  = new Font(Font.DIALOG_INPUT, Font.BOLD, 24);
        Font customerAddAreaFont  = new Font(Font.DIALOG_INPUT, Font.BOLD, 14);

        Border blueLineBorder = BorderFactory.createLineBorder(Color.BLUE, 1);
        //Border blueTitledBorder = BorderFactory.createTitledBorder(blueLineBorder, "Invoice Item Input Panel", TitledBorder.LEADING, TitledBorder.TOP, productInputPanelFont);
        
        customerAddressPanel.setBorder(blueLineBorder);
                   
        customerAddressPanel.setFont(customerAddLabelFont);
        
        GridBagLayout customerAddGridBagLayout = new GridBagLayout();

        customerAddressPanel.setLayout(customerAddGridBagLayout);
                
        GridBagConstraints bagConstraints1 = new GridBagConstraints();
        
        bagConstraints1.insets = new Insets(10, 30, 10, 10);
        bagConstraints1.anchor = GridBagConstraints.WEST;
        
        JLabel customerAddLabel = new JLabel("Customer Address");
        customerAddLabel.setFont(customerAddLabelFont);
                
        bagConstraints1.fill = GridBagConstraints.HORIZONTAL;

        bagConstraints1.gridx = 0;
        bagConstraints1.gridy = 1;
            
        customerAddressPanel.add(customerAddLabel, bagConstraints1);
        
        customerAddress = new LimitedRowTextArea();
        customerAddress.setRows(3);
        customerAddress.setColumns(40);
        customerAddress.setMaxRows(3);
        customerAddress.setFont(customerAddAreaFont);
                
        JScrollPane customerAddScrollPane = new JScrollPane(customerAddress);
        customerAddScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        customerAddScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        bagConstraints1.fill = GridBagConstraints.NONE;

        bagConstraints1.gridx = 1;
        bagConstraints1.gridy = 1;
        
        customerAddressPanel.add(customerAddScrollPane, bagConstraints1);        
    }
    
    private void createInvoiceInputPanel() 
    {
        invoiceInputPanel = new JPanel();
        
        Font productInputPanelFont  = new Font(Font.DIALOG_INPUT, Font.BOLD, 18);
                
        Border blueLineBorder = BorderFactory.createLineBorder(Color.BLUE, 1);
        //Border blueTitledBorder = BorderFactory.createTitledBorder(blueLineBorder, "Invoice Item Input Panel", TitledBorder.LEADING, TitledBorder.TOP, productInputPanelFont);
        
        invoiceInputPanel.setBorder(blueLineBorder);
                   
        invoiceInputPanel.setFont(productInputPanelFont);
        
        GridBagLayout productInputGridBagLayout = new GridBagLayout();

        invoiceInputPanel.setLayout(productInputGridBagLayout);
                
        GridBagConstraints bagConstraints1 = new GridBagConstraints();
        
        bagConstraints1.insets = new Insets(10, 30, 10, 10);
        bagConstraints1.anchor = GridBagConstraints.WEST;
        
        JLabel productNameLabel = new JLabel("Product Name");
        productNameLabel.setFont(productInputPanelFont);
                
        bagConstraints1.fill = GridBagConstraints.HORIZONTAL;

        bagConstraints1.gridx = 0;
        bagConstraints1.gridy = 1;
            
        invoiceInputPanel.add(productNameLabel, bagConstraints1);
        
        productName = new JTextField(35);
        productName.setFont(productInputPanelFont);
                
        bagConstraints1.fill = GridBagConstraints.NONE;

        bagConstraints1.gridx = 1;
        bagConstraints1.gridy = 1;
        
        invoiceInputPanel.add(productName, bagConstraints1);
        
        JLabel productDescLabel = new JLabel("Product Price");
        productDescLabel.setFont(productInputPanelFont);
        
        bagConstraints1.fill = GridBagConstraints.HORIZONTAL;

        bagConstraints1.gridx = 0;
        bagConstraints1.gridy = 2;
    
        invoiceInputPanel.add(productDescLabel, bagConstraints1);
        
        productPrice = new JTextField(10);
        productPrice.setFont(productInputPanelFont);
//        productPrice.setHorizontalAlignment(JTextField.RIGHT);
                
        bagConstraints1.fill = GridBagConstraints.NONE;

        bagConstraints1.gridx = 1;
        bagConstraints1.gridy = 2;
        
        invoiceInputPanel.add(productPrice, bagConstraints1);
        
        JLabel productIDLabel = new JLabel("Product Quantity");
        productIDLabel.setFont(productInputPanelFont);
        
        bagConstraints1.fill = GridBagConstraints.HORIZONTAL;

        bagConstraints1.gridx = 0;
        bagConstraints1.gridy = 3;
    
        invoiceInputPanel.add(productIDLabel, bagConstraints1);
        
        productQuantity = new JTextField(6);
        productQuantity.setFont(productInputPanelFont);
        
        bagConstraints1.fill = GridBagConstraints.NONE;

        bagConstraints1.gridx = 1;
        bagConstraints1.gridy = 3;
        
        invoiceInputPanel.add(productQuantity, bagConstraints1);

        invoiceItemCount = new JTextField(10);
        invoiceItemCount.setFont(productInputPanelFont);
        invoiceItemCount.setEditable(false);
        invoiceItemCount.setHorizontalAlignment(JTextField.RIGHT);
                
        bagConstraints1.fill = GridBagConstraints.NONE;
        bagConstraints1.anchor = GridBagConstraints.EAST;
        bagConstraints1.insets = new Insets(10, 30, 10, 0);
        
        bagConstraints1.gridx = 0;
        bagConstraints1.gridy = 5;
        
        invoiceInputPanel.add(invoiceItemCount, bagConstraints1);
        
        JLabel invoiceItemCountLabel = new JLabel("Items Added");
        invoiceItemCountLabel.setFont(productInputPanelFont);
        
        bagConstraints1.fill = GridBagConstraints.NONE;
        bagConstraints1.anchor = GridBagConstraints.WEST;
        bagConstraints1.insets = new Insets(10, 10, 10, 10);
        
        bagConstraints1.gridx = 1;
        bagConstraints1.gridy = 5;
        
        invoiceInputPanel.add(invoiceItemCountLabel, bagConstraints1);        
    }

    private void createControlPanel() 
    {
        controlPanel = new JPanel();
        
        Border blueLineBorder = BorderFactory.createLineBorder(Color.BLUE, 1);
        controlPanel.setBorder(blueLineBorder);
        
        Font controlPanelFont  = new Font(Font.SANS_SERIF, Font.BOLD, 24);
        
        GridBagLayout controlGridBagLayout = new GridBagLayout();

        controlPanel.setLayout(controlGridBagLayout);
                
        GridBagConstraints bagConstraints1 = new GridBagConstraints();
        
        bagConstraints1.anchor = GridBagConstraints.WEST;
        
        addLineItemButton = new JButton("Add Invoice Item");       
        addLineItemButton.setFont(controlPanelFont);
        
        bagConstraints1.fill = GridBagConstraints.NONE;
        bagConstraints1.insets = new Insets(17, 30, 17, 50);

        bagConstraints1.gridx = 0;
        bagConstraints1.gridy = 1;
            
        addLineItemButton.addActionListener(ae -> addLineItemButtonClicked());
        
        controlPanel.add(addLineItemButton, bagConstraints1);

        printInvoiceButton = new JButton("Print Invoice");       
        printInvoiceButton.setFont(controlPanelFont);
        
        bagConstraints1.fill = GridBagConstraints.NONE;
        bagConstraints1.insets = new Insets(17, 30, 17, 50);

        bagConstraints1.gridx = 1;
        bagConstraints1.gridy = 1;
            
        printInvoiceButton.addActionListener(ae -> printInvoiceButtonClicked());
        
        controlPanel.add(printInvoiceButton, bagConstraints1);
        
        quitButton = new JButton("Quit");
        quitButton.setFont(controlPanelFont);
                
        bagConstraints1.fill = GridBagConstraints.NONE;

        bagConstraints1.gridx = 2;
        bagConstraints1.gridy = 1;
        
        quitButton.addActionListener(ae -> quitButtonClicked());
        
        controlPanel.add(quitButton, bagConstraints1);        
    }

    private void clearLineItemInputs()
    {
        productName.setText("");
        productPrice.setText("");
        productQuantity.setText("");
    }
    
    private void addLineItemButtonClicked() 
    {
        if(productName.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please input Product Name");
        }
        else if(productPrice.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please input Product Price");
        }
        else if(productQuantity.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please input Product Quantity");
        }
        else
        {
            try 
            {
                Product product_item = new Product(productName.getText(), Double.valueOf(productPrice.getText()));

                LineItem line_item = new LineItem(product_item, Integer.valueOf(productQuantity.getText()));
                
                invoiceInstance.addLineItem(line_item);
                
                itemCount++;
                invoiceItemCount.setText("" + itemCount);
                
                clearLineItemInputs();
            } 
            catch (NumberFormatException e) 
            {
                JOptionPane.showMessageDialog(null, "Please input valid Price and Quantity for item");
            } 
            catch (Exception e) 
            {
            }
        }
    }

    private void printInvoiceButtonClicked() 
    {
        String custAdd = "";

        for (int line = 0; line < customerAddress.getLineCount(); line++) 
        {
            try 
            {
                int start = customerAddress.getLineStartOffset(line);
                int end = customerAddress.getLineEndOffset(line);
                
                String lineText = "";
                if((start >= 0) && (end > 0))
                    lineText = customerAddress.getText(start, end - start);
                
                if (lineText.endsWith("\n"))
                {
                    lineText = lineText.substring(0, lineText.length() - 1);
                }
                
                custAdd += lineText + "\n";
            } 
            catch (BadLocationException ex) 
            {
                //unprocessed exception
            }
        }
    
        invoiceInstance.setCustomer_address(custAdd);
        
        InvoicePrintFrame MyInvoicePrintFrame = new InvoicePrintFrame(invoiceInstance);

        MyInvoicePrintFrame.SetInvoicePrintFrameDisplay();

        MyInvoicePrintFrame.setSize(430, 360);

        MyInvoicePrintFrame.setResizable(false);

        MyInvoicePrintFrame.setLocationRelativeTo(null);
        
        MyInvoicePrintFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyInvoicePrintFrame.setVisible(true);    
    }
    
    private void quitButtonClicked() 
    {
        int option = JOptionPane.showConfirmDialog(null, "Do you want to Quit", "Quit Confirm", JOptionPane.YES_NO_OPTION);
        
        if(option == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }

}
