/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package normal;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import normal.*;
/**
 *
 * @author Ty
 */
public class MainWindow extends javax.swing.JFrame {
    //FROM INPUTS
    //Full path
    private Input input;
    private final InputList inputList = new InputList();
//    private final String filenameEmployee = "C:\\Users\\Ty\\Downloads\\GuiTeaShop_1\\EmployeesList.csv";
//    private final String filenameBusiness = "C:\\Users\\Ty\\Downloads\\GuiTeaShop_1\\Business.csv";
//    private final String transactionlist = "C:\\Users\\Ty\\Downloads\\GuiTeaShop_1\\transactionlist.csv";
//    private final String filenameInput = "C:\\Users\\Ty\\Downloads\\GuiTeaShop_1\\inputsdummy.csv";
//    private final String filenameProduct = "C:\\Users\\Ty\\Downloads\\GuiTeaShop_1\\productlist.csv";
//    private final String delimiter = ",";
    
    private final String filenameEmployee = "EmployeesList.csv";
    private final String filenameBusiness = "Business.csv";
    private final String transactionlist = "transactionlist.csv";
    private final String filenameInput = "inputsdummy.csv";
    private final String filenameProduct = "productlist.csv";
    private final String delimiter = ",";
    //END INPUT VARIABLES
    
    
    
    private Register store1;
    private Employees employees;
    private Employees schedule;
    
    /**
     * Creates new form MainWindow
     */
    public MainWindow(Register store1, Employees employees, Employees schedule) {
        this.store1 = store1;
        this.employees = employees;
        this.schedule = schedule;
        initComponents();
        
        readCSVTransactions();
        readCSVInput();
        readCSVProduct();
        readCSVEmployee();
        updateCSVBusinessHours();
        
        //Set the following labels to invisible on start up
        incorrectPasswordLabel.setVisible(false);
        incorrectUserLabel.setVisible(false);
        notManagerLabel.setVisible(false);
        
        
    }
    
    private void updateCSVProduct() {
        try
        {
            
            // Make a copy of the original file
            
//            File oldFile = new File(filename);
//            File new_file = new File(filename + ".csv");
//            
//            Files.copy(oldFile.toPath(), new_file.toPath());
            
            File file = new File(filenameProduct);
            BufferedWriter bf = new BufferedWriter(new FileWriter(file));
            

            Map<Integer, Product> products = store1.getProductList();
            for (Map.Entry<Integer, Product> temp : products.entrySet())
            {
                //String tempID = (store1.getProductList().get(temp).getId()) + "";
                String tempID = temp.getValue().getId() + "";
                String tempName = temp.getValue().getName();
                String tempPrice = temp.getValue().getPrice() + "";
                String tempQuantity = temp.getValue().getQuantity() + "";
                String tempTax = temp.getValue().getTaxRate() + "";
                String tempDiscount = temp.getValue().getDiscount() + "";
                String tempProductType = temp.getValue().getProductType() + "";
                
                
                
                String row = "";


                row = tempID + "," + tempName + "," + 
                        tempPrice + "," + tempQuantity + "," + tempTax
                        + "," + tempDiscount + "," + 
                        tempProductType +
                        "\n";

                System.out.println("New row: " + row);

                // Add the row to the file
                bf.write(row);
            }
            bf.close();
            
            
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(this, "Error writing the file");
        }
        redrawProductTable("manager");
        redrawProductManagementTable();
        
    }
    
    private void readCSVProduct() {
        try
        {
            File file = new File(filenameProduct);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            String line = "";
            String[] tempArr;
            Map<Integer, Product> products = store1.getProductList();
            
            //Count number of lines
            long lines = 0;
             try (BufferedReader reader = new BufferedReader(new FileReader(filenameProduct))) {
                    while (reader.readLine() != null) lines++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            
            
            
            System.out.println("Line count is " + lines);
            
            while ((line = br.readLine()) != null)
            {
                tempArr = line.split(delimiter);
                
                int tempID = Integer.valueOf(tempArr[0].trim());
                String tempName = tempArr[1].trim();
                double tempPrice = Double.valueOf(tempArr[2].trim());
                int tempQuantity = Integer.valueOf(tempArr[3].trim());
                double tempTax = Double.valueOf(tempArr[4].trim());
                double tempDiscount = Double.valueOf(tempArr[5].trim());
                char tempProductType = tempArr[6].trim().charAt(0);
                
                //Product tempProduct = new Product(store1, tempName, tempPrice, tempQuantity);
                
//                System.out.println("Product ID is " + tempProduct.getId());
//                System.out.println("temp ID is " + tempID);
                
                //store1.getProductList().put(tempID-1, tempProduct); 
                
                
                switch(tempProductType){
                    case 'T':
                    case 't':
                        Tea tempProductTea = new Tea(store1, tempName, tempPrice, tempQuantity);
                        //Put product in productList map 
                        store1.getProductList().put(tempID, tempProductTea);
                        break;
                    
                    case 'F':
                    case 'f':
                        Food tempProductFood = new Food(store1, tempName, tempPrice, tempQuantity);
                        //Put product in productList map 
                        store1.getProductList().put(tempID, tempProductFood);
                        break;
                    
                    case 'A':
                    case 'a':
                        Apparel tempProductApparel = new Apparel(store1, tempName, tempPrice, tempQuantity);
                        //Put product in productList map 
                        store1.getProductList().put(tempID, tempProductApparel);
                        break;
                    
                    case 'X':
                    case 'x':
                        Product tempProduct = new Product(store1, tempName, tempPrice, tempQuantity);
                        //Put product in productList map 
                        store1.getProductList().put(tempID, tempProduct);
                        break;
                        
                    default:
                        Product tempProduct2 = new Product(store1, tempName, tempPrice, tempQuantity);
                        //Put product in productList map 
                        store1.getProductList().put(tempID, tempProduct2);
                        break;
                }
                
                 
            }
            br.close();
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(this, "Error reading the file");
        }
        redrawProductTable("employee");
        redrawProductTable("manager");
        redrawProductManagementTable();
        
    }
    
    private void readCSVInput() {
        try
        {
            DefaultTableModel inputReport = (DefaultTableModel) tblInputReport.getModel();
            DefaultTableModel inputReportRecords = (DefaultTableModel) tblInputReportRecords.getModel();
            File file = new File(filenameInput);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            String line = "";
            String[] tempArr;
            HashMap<Integer, Input> inputs = inputList.getInputs();
            while ((line = br.readLine()) != null)
            {
                tempArr = line.split(delimiter);
                for (Object field : tempArr)
                {
                    System.out.println(field + " ");
                }
                
                int lineItemID = Integer.valueOf(tempArr[0].trim());
                String vendor = tempArr[1].trim();
                String category = tempArr[2].trim();
                String inputx = tempArr[3].trim();
                double unitCost = Double.valueOf(tempArr[4].trim());
                int quantity = Integer.valueOf(tempArr[5].trim());
                double discount = Double.valueOf(tempArr[6].trim());
                double tax = Double.valueOf(tempArr[7].trim());
                double gc = Double.valueOf(tempArr[8].trim());
                double da = Double.valueOf(tempArr[9].trim());
                double ta = Double.valueOf(tempArr[10].trim());
                double nc = Double.valueOf(tempArr[11].trim());
                
                inputReport.addRow(new Object[]{lineItemID, vendor, category, 
                    inputx, unitCost, quantity, discount, 
                    tax, gc, da, ta, nc});
                
                inputReportRecords.addRow(new Object[]{lineItemID, vendor, category, 
                    inputx, unitCost, quantity, discount, 
                    tax, gc, da, ta, nc});
                
                txtTotalGrossCost.setText(String.format("%.2f",getTotalGrossCost(tblInputReport.getSelectedRow())));
                txtTotalDiscountAmount.setText(String.format("%.2f",getTotalDiscountAmount(tblInputReport.getSelectedRow())));
                txtTotalTaxAmount.setText(String.format("%.2f",getTotalTaxAmount(tblInputReport.getSelectedRow())));
                txtTotalNetCost.setText(String.format("%.2f",getTotalNetCost(tblInputReport.getSelectedRow())));
                
                addInput(lineItemID, vendor, category, inputx, unitCost, quantity, discount, tax);
                
                //System.out.println();
            }
            br.close();
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(this, "Error reading the file");
        }
    }
    
    private void updateCSVInput() {
        try
        {
            
            // Make a copy of the original file
            
//            File oldFile = new File(filename);
//            File new_file = new File(filename + ".csv");
//            
//            Files.copy(oldFile.toPath(), new_file.toPath());
            
            File file = new File(filenameInput);
            BufferedWriter bf = new BufferedWriter(new FileWriter(file));

            HashMap<Integer, Input> inputs = inputList.getInputs();
            for (Map.Entry inp : inputs.entrySet())
            {
                String row = "";
                int lineItemID = (Integer) inp.getKey();
                Input i = inputList.getInput(lineItemID);

                String vendor = i.getVendorName();
                String category = i.getVendorCategory();
                String inputx = i.getInput();
                double unitCost = i.getCost();
                int quantity = i.getQuantity();
                double discount = i.getDiscount();
                double tax = i.getTaxRate();
                double gc = i.getGrossCost(unitCost, quantity);
                double da = i.getDiscountAmount(unitCost, quantity, discount);
                double ta = i.getTaxAmount(tax, unitCost, quantity);
                double nc = i.getNetCost(tax, unitCost, quantity, discount);

                row = String.valueOf(lineItemID) + "," + vendor + "," + 
                        category + "," + inputx + "," + String.format("%.2f",unitCost)
                        + "," + String.valueOf(quantity) + "," + 
                        String.format("%.2f",discount) + "," + String.format("%.2f",tax)
                        + "," + String.format("%.2f",gc) + "," + String.format("%.2f",da)
                        + "," + String.format("%.2f",ta) + "," + String.format("%.2f",nc) 
                        + "\n";

                System.out.println("New row: " + row);

                // Add the row to the file
                bf.write(row);
            }
            bf.close();
            
            
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(this, "Error writing the file");
        }
        
    }
    
    private void readCSVTransactions(){
        try
        {
           File file = new File(transactionlist);
           FileReader fr = new FileReader(file);
           BufferedReader br = new BufferedReader(fr);
           
           String line = "";
           String[] tempArr;
           ArrayList<Object> tempTransaction = new ArrayList<Object>();
           br.readLine();
           
            
           while((line = br.readLine()) != null){
               tempArr = line.split(delimiter);
               
               
               
                //System.out.println("");
                //System.out.print("first cell is " + tempArr[0]);
                
               int transactionID = Integer.valueOf(tempArr[0].trim());
               double transactionTotal = Double.valueOf(tempArr[1].trim());
               double transactionSubTotal = Double.valueOf(tempArr[2].trim());
               double transactionTax = Double.valueOf(tempArr[3].trim());
               int transactionQuantity = Integer.valueOf(tempArr[4].trim());
               
               tempTransaction.add(transactionID);
               tempTransaction.add(transactionTotal);
               tempTransaction.add(transactionSubTotal);
               tempTransaction.add(transactionTax);
               tempTransaction.add(transactionQuantity);
               
               store1.getAllTransactions().add(new ArrayList(tempTransaction));
               
               tempTransaction.clear();
           }
           br.close();
        }
        catch(IOException e)
        {
             JOptionPane.showMessageDialog(this, "Error reading the file");       
        }
        
        redrawAllTransactionsTable();
    }
    
    private void updateCSVTransactions(){
        try
        {
            File file = new File(transactionlist);
            BufferedWriter bf = new BufferedWriter(new FileWriter(file));
            
            String row = "";
            int transactionID;
            double transactionTotal;
            double transactionSubTotal;
            double transactionTax;
            int transactionQuantity;
            
            int size = store1.getAllTransactions().size();
            String[] tempArr;
            ArrayList<Object> tempTransaction = new ArrayList<Object>();
            
            for(int i=0; i<size; i++){
                row = "";
                
                transactionID = (Integer)store1.getAllTransactions().get(i).get(0);
                transactionTotal = (Double)store1.getAllTransactions().get(i).get(1);
                transactionSubTotal = (Double)store1.getAllTransactions().get(i).get(2);
                transactionTax = (Double)store1.getAllTransactions().get(i).get(3);
                transactionQuantity = (Integer)store1.getAllTransactions().get(i).get(4);
                
                row = String.valueOf(transactionID) + "," +
                        String.format("$%.2f",transactionTotal) + "," + 
                        String.format("$.2f", transactionSubTotal) + "," + 
                        String.format("$.2f", transactionTax) + "," + 
                        String.valueOf(transactionQuantity) + 
                        "\n";
                
                bf.write(row);
                
            }
            bf.close();
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(this, "Error writing the file");    
        }
    }
    
    //Product List to Table
    public Object[][] getProductListLite(Register register){
        int size = register.getProductList().size();
        Object[][] productDetailsLite = new Object[size][4];
        int i = 0; //counter variable
        
        Iterator<Integer> itr = register.getProductList().keySet().iterator();
        
        while(itr.hasNext()){
            int x = itr.next();
            productDetailsLite[i][0] = register.getProductList().get(x).getId();
            productDetailsLite[i][1] = register.getProductList().get(x).getName();
            productDetailsLite[i][2] = register.getProductList().get(x).getPrice();
            productDetailsLite[i][3] = register.getProductList().get(x).getQuantity();
            
            i++;
            
        }
        return productDetailsLite;
    }
    
    public Object[][] getProductList(Register register){
        int size = register.getProductList().size();
        Object[][] productDetails = new Object[size][7];
        int i = 0; //counter variable
        
        Iterator<Integer> itr = register.getProductList().keySet().iterator();
        
        while(itr.hasNext()){
            int x = itr.next();
            
            productDetails[i][0] = register.getProductList().get(x).getId();
            productDetails[i][1] = register.getProductList().get(x).getName();
            productDetails[i][2] = register.getProductList().get(x).getPrice();
            productDetails[i][3] = register.getProductList().get(x).getQuantity();
            productDetails[i][4] = register.getProductList().get(x).getTaxRate();
            productDetails[i][5] = register.getProductList().get(x).getDiscount();
            productDetails[i][6] = register.getProductList().get(x).getProductType();
            
            
            i++;
            //System.out.println(x + " is itr and " + i + " is i");
            
        }
        return productDetails;
    }
    
    public Object[][] getCartList(Register register){
        int size = store1.getCart().size();
        Object[][] cartList = new Object[size][3];
        for(int i=0; i<size; i++){
            //refer to legend in class Register for index values for addToCart()
            cartList[i][0] = register.getCart().get(i).get(0);
            cartList[i][1] = register.getCart().get(i).get(2);
            cartList[i][2] = register.getCart().get(i).get(1);
        }
        
        return cartList;
    }
    
    public Object[][] getAllTransactionsList(Register register){
        int size = store1.getAllTransactions().size();
        Object[][] transactionList = new Object[size][5];
        String tempID = "";
        String tempTotal = "";
        String tempSubTotal = "";
        String tempTax = "";
        String tempQuantity = "";
        
        for(int i=0; i<size; i++){
            //refer to legend in class Register for index values for addToCart()
            tempID = register.getAllTransactions().get(i).get(0).toString();
            tempTotal = String.format("$%.2f", register.getAllTransactions().get(i).get(1));
            tempSubTotal = String.format("$%.2f", register.getAllTransactions().get(i).get(2));
            tempTax = String.format("$%.2f", register.getAllTransactions().get(i).get(3));
            tempQuantity = register.getAllTransactions().get(i).get(4).toString();
            
            transactionList[i][0] = tempID;
            transactionList[i][1] = tempTotal;
            transactionList[i][2] = tempSubTotal;
            transactionList[i][3] = tempTax;
            transactionList[i][4] = tempQuantity;
        }
        
        return transactionList;
    }
    
    public double getTotalRevenue(){
        int size = store1.getAllTransactions().size();
        double tempTotal = 0;
        
        for(int i = 0; i<size; i++){
            tempTotal = Double.parseDouble(store1.getAllTransactions().get(i).get(1).toString())
                    +tempTotal;
            
        }
        return tempTotal;
    }
    
    public double getSubTotalRevenue(){
        int size = store1.getAllTransactions().size();
        double tempSubTotal = 0;
        
        for(int i = 0; i<size; i++){
            tempSubTotal = Double.parseDouble(store1.getAllTransactions().get(i).get(2).toString())
                    +tempSubTotal;
            
        }
        return tempSubTotal;
    }
    
    public double getTotalTaxCollected(){
        int size = store1.getAllTransactions().size();
        double tempTax = 0;
        
        for(int i = 0; i<size; i++){
            tempTax = Double.parseDouble(store1.getAllTransactions().get(i).get(3).toString())
                    +tempTax;
            
        }
        return tempTax;
    }
    
    public int getTotalUnitsSold(){
        int size = store1.getAllTransactions().size();
        int tempQuantity = 0;
        
        for(int i = 0; i<size; i++){
            tempQuantity = Integer.parseInt(store1.getAllTransactions().get(i).get(4).toString())
                    +tempQuantity;
            
        }
        return tempQuantity;
        
    }
    
    public void redrawCartTable(String tableType){
        if (tableType.contains("employee")){
            cartTable.setModel(new javax.swing.table.DefaultTableModel(
            getCartList(store1),
            new String [] {
                "Name", "Price", "Quantity"
                }
            ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        } 
        else if (tableType.contains("manager"))
        {
            cartTableForManager.setModel(new javax.swing.table.DefaultTableModel(
            getCartList(store1),
            new String [] {
                "Name", "Price", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        }
    }
    
    public void redrawProductTable(String tableType){
        if (tableType.contains("employee")){
            productTable.setModel(new javax.swing.table.DefaultTableModel(
                     getProductListLite(store1),
            new String [] {
                "ID", "Name", "Price", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        } 
        else if (tableType.contains("manager"))
        {
            productTableForManager.setModel(new javax.swing.table.DefaultTableModel(
                     getProductListLite(store1),
            new String [] {
                "ID", "Name", "Price", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        }
        
        //Set first column (IDs) to be smaller than the rest
        productTableForManager.getColumnModel().getColumn(0).setPreferredWidth(32);
        productTableForManager.getColumnModel().getColumn(0).setMaxWidth(32);
        productTableForManager.getColumnModel().getColumn(0).setMinWidth(32);
        //Set third column (price) to be smaller than the rest
        productTableForManager.getColumnModel().getColumn(2).setPreferredWidth(45);
        productTableForManager.getColumnModel().getColumn(2).setMaxWidth(45);
        productTableForManager.getColumnModel().getColumn(2).setMinWidth(45);
        //Set fourth column (quantity) to be smaller than the rest
        productTableForManager.getColumnModel().getColumn(3).setPreferredWidth(32);
        productTableForManager.getColumnModel().getColumn(3).setMaxWidth(32);
        productTableForManager.getColumnModel().getColumn(3).setMinWidth(32);
    }
    
    public void redrawProductManagementTable(){
        Map<Integer,Product> tempList = new HashMap<Integer,Product>();
        productManagementTable.setModel(new javax.swing.table.DefaultTableModel(
                     getProductList(store1),
            new String [] {
                "ID", "Name", "Price", "Quantity", "Tax Rate", "Discount", "Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
       
        if (productManagementTable.getColumnModel().getColumnCount() > 0) {
            productManagementTable.getColumnModel().getColumn(0).setMinWidth(35);
            productManagementTable.getColumnModel().getColumn(0).setPreferredWidth(35);
            productManagementTable.getColumnModel().getColumn(0).setMaxWidth(35);
            productManagementTable.getColumnModel().getColumn(2).setMinWidth(70);
            productManagementTable.getColumnModel().getColumn(2).setPreferredWidth(70);
            productManagementTable.getColumnModel().getColumn(2).setMaxWidth(70);
            productManagementTable.getColumnModel().getColumn(3).setMinWidth(65);
            productManagementTable.getColumnModel().getColumn(3).setPreferredWidth(65);
            productManagementTable.getColumnModel().getColumn(3).setMaxWidth(65);
            productManagementTable.getColumnModel().getColumn(4).setMinWidth(55);
            productManagementTable.getColumnModel().getColumn(4).setPreferredWidth(55);
            productManagementTable.getColumnModel().getColumn(4).setMaxWidth(55);
            productManagementTable.getColumnModel().getColumn(5).setMinWidth(40);
            productManagementTable.getColumnModel().getColumn(5).setPreferredWidth(40);
            productManagementTable.getColumnModel().getColumn(5).setMaxWidth(40);
            productManagementTable.getColumnModel().getColumn(6).setMinWidth(35);
            productManagementTable.getColumnModel().getColumn(6).setPreferredWidth(35);
            productManagementTable.getColumnModel().getColumn(6).setMaxWidth(35); 
    
        }
        
        productRecordsTable.setModel(new javax.swing.table.DefaultTableModel(
                     getProductList(store1),
            new String [] {
                "ID", "Name", "Price", "Quantity", "Tax Rate", "Discount", "Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
       
        if (productRecordsTable.getColumnModel().getColumnCount() > 0) {
            productRecordsTable.getColumnModel().getColumn(0).setMinWidth(35);
            productRecordsTable.getColumnModel().getColumn(0).setPreferredWidth(35);
            productRecordsTable.getColumnModel().getColumn(0).setMaxWidth(35);
            productRecordsTable.getColumnModel().getColumn(2).setMinWidth(70);
            productRecordsTable.getColumnModel().getColumn(2).setPreferredWidth(70);
            productRecordsTable.getColumnModel().getColumn(2).setMaxWidth(70);
            productRecordsTable.getColumnModel().getColumn(3).setMinWidth(65);
            productRecordsTable.getColumnModel().getColumn(3).setPreferredWidth(65);
            productRecordsTable.getColumnModel().getColumn(3).setMaxWidth(65);
            productRecordsTable.getColumnModel().getColumn(4).setMinWidth(55);
            productRecordsTable.getColumnModel().getColumn(4).setPreferredWidth(55);
            productRecordsTable.getColumnModel().getColumn(4).setMaxWidth(55);
            productRecordsTable.getColumnModel().getColumn(5).setMinWidth(40);
            productRecordsTable.getColumnModel().getColumn(5).setPreferredWidth(40);
            productRecordsTable.getColumnModel().getColumn(5).setMaxWidth(40);
            productRecordsTable.getColumnModel().getColumn(6).setMinWidth(35);
            productRecordsTable.getColumnModel().getColumn(6).setPreferredWidth(35);
            productRecordsTable.getColumnModel().getColumn(6).setMaxWidth(35); 
    
        }
        
        totalProductsField.setText(getTotalProducts());
        totalUnitsField.setText(getTotalUnits());
        inventorySaleValueField.setText(getInventorySaleValue());
    }
    
    public void redrawAllTransactionsTable(){
        transactionsRecordsTable.setModel(new javax.swing.table.DefaultTableModel(
            getAllTransactionsList(store1),
            new String [] {
                "ID", "Total", "Sub Total", "Tax", "Quantity of Items"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        
        jScrollPane6.setViewportView(transactionsRecordsTable);
        
        if (transactionsRecordsTable.getColumnModel().getColumnCount() > 0) {
            transactionsRecordsTable.getColumnModel().getColumn(0).setMinWidth(40);
            transactionsRecordsTable.getColumnModel().getColumn(0).setPreferredWidth(40);
            transactionsRecordsTable.getColumnModel().getColumn(0).setMaxWidth(40);
            transactionsRecordsTable.getColumnModel().getColumn(4).setMinWidth(50);
            transactionsRecordsTable.getColumnModel().getColumn(4).setPreferredWidth(50);
            transactionsRecordsTable.getColumnModel().getColumn(4).setMaxWidth(50);
        }
        
        String tempTotal = String.format("$%.2f", getTotalRevenue());
        totalRevenue.setText(tempTotal);
        
        String tempSubTotal = String.format("$%.2f", getSubTotalRevenue());
        totalSubTotalRevenue.setText(tempSubTotal);
        
        String tempTax = String.format("$%.2f", getTotalTaxCollected());
        totalTaxCollected.setText(tempTax);
        
        int tempQuantity = getTotalUnitsSold();
        totalUnitsSold.setText(tempQuantity + "");
        
        int lastID = store1.getTransactions()-1;
        lastTransaction.setText(lastID + "");
    }
    
    private void filterProductTable(String query) {
       
        DefaultTableModel products = (DefaultTableModel) productManagementTable.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(products);
        productManagementTable.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
       
    }
    
    private void filterInputTable(String query) {
        
        DefaultTableModel inputReport = (DefaultTableModel) tblInputReport.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(inputReport);
        tblInputReport.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
        
    }
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        Register store1 = new Register();
	SignIn signIn = new SignIn();
	Employees employees = new Employees();
	Employees schedule = new Employees();
		
	employees.addDefaultEmployee();
	
	signIn.signIn(store1, employees, schedule);
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow(store1, employees, schedule).setVisible(true);
            }
        });
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuInputReport = new javax.swing.JMenu();
        menuItemAdd = new javax.swing.JMenuItem();
        menuItemUpdate = new javax.swing.JMenuItem();
        menuItemDelete = new javax.swing.JMenuItem();
        menuItemImport = new javax.swing.JMenuItem();
        menuItemExport = new javax.swing.JMenuItem();
        menuItemSave = new javax.swing.JMenuItem();
        menuItemReturntoMM = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        bgEmployeeManagement = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        signInWindow = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        empSignIn = new javax.swing.JButton();
        manSignIn = new javax.swing.JButton();
        quitApplication = new javax.swing.JButton();
        incorrectPasswordLabel = new javax.swing.JLabel();
        incorrectUserLabel = new javax.swing.JLabel();
        notManagerLabel = new javax.swing.JLabel();
        bgPicture = new javax.swing.JLabel();
        employeeSaleMenu = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cartTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        selectedID = new javax.swing.JTextField();
        selectedQuantity = new javax.swing.JSpinner();
        btnAddToCart = new javax.swing.JButton();
        btnClearCart = new javax.swing.JButton();
        subTotalAmount = new javax.swing.JTextField();
        taxAmount = new javax.swing.JTextField();
        totalAmount = new javax.swing.JTextField();
        btnSale = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnLogOut = new javax.swing.JButton();
        bgEmployeeSaleMenu = new javax.swing.JLabel();
        managerMenuWindow = new javax.swing.JTabbedPane();
        saleMenuTab = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        menuTitleLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        cartTableForManager = new javax.swing.JTable();
        cartLabel = new javax.swing.JLabel();
        productLogLabel = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        productTableForManager = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        selectedIDForManager = new javax.swing.JTextField();
        selectedQuantityForManager = new javax.swing.JSpinner();
        btnAddToCart1 = new javax.swing.JButton();
        btnClearCart1 = new javax.swing.JButton();
        subTotalAmountForManager = new javax.swing.JTextField();
        taxAmountForManager = new javax.swing.JTextField();
        totalAmountForManager = new javax.swing.JTextField();
        btnSale1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnLogOut1 = new javax.swing.JButton();
        bgManagerSaleMenu = new javax.swing.JLabel();
        productManagementTab = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        productManagementTable = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        selectedIDProdManagement = new javax.swing.JTextField();
        selectedNameProdManagement = new javax.swing.JTextField();
        selectedPriceProdManagement = new javax.swing.JTextField();
        selectedQuantityProdManagement = new javax.swing.JTextField();
        selectedTaxRateProdManagement = new javax.swing.JTextField();
        selectedDiscountProdManagement = new javax.swing.JTextField();
        selectedTypeProdManagement = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        btnAddProduct = new javax.swing.JButton();
        btnModifyProduct = new javax.swing.JButton();
        btnDeleteProduct = new javax.swing.JButton();
        btnClearProdManagement = new javax.swing.JButton();
        searchFieldProducts = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        btnLogOut2 = new javax.swing.JButton();
        bgProductManagement = new javax.swing.JLabel();
        employeesManagementTab = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        employeeInfoTab = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        javax.swing.JLabel jLabel29 = new javax.swing.JLabel();
        javax.swing.JButton jButton1 = new javax.swing.JButton();
        javax.swing.JButton jButton2 = new javax.swing.JButton();
        javax.swing.JButton jButton3 = new javax.swing.JButton();
        javax.swing.JLabel jLabel30 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel31 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel32 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel33 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel34 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel35 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel36 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        javax.swing.JLabel jLabel37 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jButton7 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        bgEmployeeInfoTab = new javax.swing.JLabel();
        businessDetailsTab = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox7 = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        lblDay = new javax.swing.JLabel();
        bgEmployeeManagement1 = new javax.swing.JLabel();
        scheduleTab = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel49 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        lblScheduledHours = new javax.swing.JLabel();
        lblHoursLimit = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        lblOvertime = new javax.swing.JLabel();
        bgEmployeeManagement2 = new javax.swing.JLabel();
        inputManagementTab = new javax.swing.JPanel();
        pnlInput = new javax.swing.JPanel();
        lblHeader = new javax.swing.JLabel();
        lblLineItemID = new javax.swing.JLabel();
        lblVendorName = new javax.swing.JLabel();
        lblVendorCategory = new javax.swing.JLabel();
        lblInputName = new javax.swing.JLabel();
        lblUnitCost = new javax.swing.JLabel();
        lblQuantity = new javax.swing.JLabel();
        lblDiscount = new javax.swing.JLabel();
        lblTaxRate = new javax.swing.JLabel();
        txtLineItemID = new javax.swing.JTextField();
        txtVendorName = new javax.swing.JTextField();
        txtVendorCategory = new javax.swing.JTextField();
        txtInputName = new javax.swing.JTextField();
        txtUnitCost = new javax.swing.JTextField();
        txtQuantity = new javax.swing.JTextField();
        txtDiscount = new javax.swing.JTextField();
        txtTaxRate = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        scrlpneInputReport = new javax.swing.JScrollPane();
        tblInputReport = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        lblSearch = new javax.swing.JLabel();
        bgInputManagement = new javax.swing.JLabel();
        recordsTab = new javax.swing.JPanel();
        recordsTabs = new javax.swing.JTabbedPane();
        transactionsTab = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        transactionsRecordsTable = new javax.swing.JTable();
        lastTransaction = new javax.swing.JTextField();
        totalRevenue = new javax.swing.JTextField();
        totalSubTotalRevenue = new javax.swing.JTextField();
        totalTaxCollected = new javax.swing.JTextField();
        totalUnitsSold = new javax.swing.JTextField();
        lastTransactionLabel = new javax.swing.JLabel();
        totalRevenueLabel = new javax.swing.JLabel();
        totalSubTotalRevenueLabel = new javax.swing.JLabel();
        totalTaxCollectedLabel = new javax.swing.JLabel();
        totalUnitsSoldLabel = new javax.swing.JLabel();
        bgTransactionRecords = new javax.swing.JLabel();
        employeeRecordsTab = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        employeesRecordsTable = new javax.swing.JTable();
        totalEmployees = new javax.swing.JTextField();
        totalManagers = new javax.swing.JTextField();
        totalHoursWorked = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        bgEmployeeRecords = new javax.swing.JLabel();
        inputRecordsTab = new javax.swing.JPanel();
        scrlpneInputReport1 = new javax.swing.JScrollPane();
        tblInputReportRecords = new javax.swing.JTable();
        lblTotalNetCost1 = new javax.swing.JLabel();
        txtTotalGrossCost = new javax.swing.JTextField();
        lblTotalGrossCost1 = new javax.swing.JLabel();
        txtTotalNetCost = new javax.swing.JTextField();
        txtTotalDiscountAmount = new javax.swing.JTextField();
        lblTotalDiscountAmount1 = new javax.swing.JLabel();
        txtTotalTaxAmount = new javax.swing.JTextField();
        lblTotalTaxAmount1 = new javax.swing.JLabel();
        bgInputRecords = new javax.swing.JLabel();
        productRecordsTab = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        productRecordsTable = new javax.swing.JTable();
        totalProductsField = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        totalUnitsField = new javax.swing.JTextField();
        inventorySaleValueField = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        bgProductRecords = new javax.swing.JLabel();

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuInputReport.setText("File");

        menuItemAdd.setText("Add");
        menuInputReport.add(menuItemAdd);

        menuItemUpdate.setText("Update");
        menuInputReport.add(menuItemUpdate);

        menuItemDelete.setText("Delete");
        menuInputReport.add(menuItemDelete);

        menuItemImport.setText("Import");
        menuInputReport.add(menuItemImport);

        menuItemExport.setText("Export");
        menuInputReport.add(menuItemExport);

        menuItemSave.setText("Save");
        menuInputReport.add(menuItemSave);

        menuItemReturntoMM.setText("Return to Manager Menu");
        menuInputReport.add(menuItemReturntoMM);

        jMenuBar1.add(menuInputReport);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jFrame1.setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        );

        bgEmployeeManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tea Shop Version 0.98");
        setMinimumSize(new java.awt.Dimension(600, 450));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setMinimumSize(new java.awt.Dimension(600, 420));
        jPanel2.setPreferredSize(new java.awt.Dimension(600, 420));
        jPanel2.setLayout(new java.awt.CardLayout());

        signInWindow.setMinimumSize(new java.awt.Dimension(600, 400));
        signInWindow.setPreferredSize(new java.awt.Dimension(600, 400));
        signInWindow.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tea Shop Sign In");
        signInWindow.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 600, -1));
        signInWindow.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 70, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Username:");
        signInWindow.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, -1, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Password:");
        signInWindow.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, -1, -1));

        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        signInWindow.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 70, -1));

        empSignIn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        empSignIn.setText("Employee Sign In");
        empSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empSignInActionPerformed(evt);
            }
        });
        signInWindow.add(empSignIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, -1, -1));

        manSignIn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manSignIn.setText("Manager Sign In");
        manSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manSignInActionPerformed(evt);
            }
        });
        signInWindow.add(manSignIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, -1, -1));

        quitApplication.setText("Quit Application");
        quitApplication.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitApplicationActionPerformed(evt);
            }
        });
        signInWindow.add(quitApplication, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, -1, -1));

        incorrectPasswordLabel.setForeground(new java.awt.Color(255, 255, 255));
        incorrectPasswordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        incorrectPasswordLabel.setText("Incorrect password. Try again.");
        incorrectPasswordLabel.setToolTipText("");
        signInWindow.add(incorrectPasswordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 600, -1));

        incorrectUserLabel.setForeground(new java.awt.Color(255, 255, 255));
        incorrectUserLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        incorrectUserLabel.setText("User not in system. Try again.");
        incorrectUserLabel.setToolTipText("");
        signInWindow.add(incorrectUserLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 600, -1));

        notManagerLabel.setForeground(new java.awt.Color(255, 255, 255));
        notManagerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        notManagerLabel.setText("User is not a manager. Try again.");
        notManagerLabel.setToolTipText("");
        signInWindow.add(notManagerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 600, -1));

        bgPicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureDarkened.jpg"))); // NOI18N
        bgPicture.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bgPicture.setMaximumSize(new java.awt.Dimension(600, 450));
        bgPicture.setMinimumSize(new java.awt.Dimension(600, 450));
        bgPicture.setPreferredSize(new java.awt.Dimension(600, 450));
        signInWindow.add(bgPicture, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 596, 416));

        jPanel2.add(signInWindow, "card7");

        employeeSaleMenu.setMinimumSize(new java.awt.Dimension(600, 420));
        employeeSaleMenu.setPreferredSize(new java.awt.Dimension(600, 420));
        employeeSaleMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Employee Sale Menu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 49, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        employeeSaleMenu.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 30));

        cartTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cartTable.setModel(new javax.swing.table.DefaultTableModel(
            getCartList(store1),
            new String [] {
                "Name", "Price", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        cartTable.setAutoCreateRowSorter(true);
        cartTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cartTablePropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(cartTable);

        employeeSaleMenu.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 190, 240));

        jLabel5.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Cart");
        employeeSaleMenu.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 190, -1));

        jLabel6.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Product Log");
        employeeSaleMenu.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 270, -1));

        productTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        productTable.setModel(new javax.swing.table.DefaultTableModel(
            getProductListLite(store1),
            new String [] {
                "ID", "Name", "Price", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(productTable);
        productTable.setAutoCreateRowSorter(true);
        //Set first column (IDs) to be smaller than the rest
        productTable.getColumnModel().getColumn(0).setPreferredWidth(32);
        //Set third column (price) to be smaller than the rest
        productTable.getColumnModel().getColumn(2).setPreferredWidth(45);
        //Set fourth column (quantity) to be smaller than the rest
        productTable.getColumnModel().getColumn(3).setPreferredWidth(32);

        employeeSaleMenu.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 270, 350));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Quantity:");
        employeeSaleMenu.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, -1, -1));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("ID:");
        employeeSaleMenu.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 50, -1));

        selectedID.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        selectedID.setPreferredSize(new java.awt.Dimension(90, 22));
        selectedID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedIDActionPerformed(evt);
            }
        });
        employeeSaleMenu.add(selectedID, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 110, -1));

        selectedQuantity.setName(""); // NOI18N
        selectedQuantity.setPreferredSize(new java.awt.Dimension(90, 22));
        employeeSaleMenu.add(selectedQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, 110, -1));

        btnAddToCart.setText("Add to Cart");
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });
        employeeSaleMenu.add(btnAddToCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 100, -1));

        btnClearCart.setText("Clear Cart");
        btnClearCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearCartActionPerformed(evt);
            }
        });
        employeeSaleMenu.add(btnClearCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 90, -1));

        subTotalAmount.setEditable(false);
        subTotalAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        subTotalAmount.setText("$0.00");
        subTotalAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subTotalAmountActionPerformed(evt);
            }
        });
        employeeSaleMenu.add(subTotalAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 70, -1));

        taxAmount.setEditable(false);
        taxAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        taxAmount.setText("$0.00");
        taxAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taxAmountActionPerformed(evt);
            }
        });
        employeeSaleMenu.add(taxAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 70, -1));

        totalAmount.setEditable(false);
        totalAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalAmount.setText("$0.00");
        employeeSaleMenu.add(totalAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, 70, -1));

        btnSale.setText("Complete Sale");
        btnSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaleActionPerformed(evt);
            }
        });
        employeeSaleMenu.add(btnSale, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 110, 30));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Tax:");
        employeeSaleMenu.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 40, -1));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Total:");
        employeeSaleMenu.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 40, -1));

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Sub:");
        employeeSaleMenu.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, 40, -1));

        btnLogOut.setText("Log out");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });
        employeeSaleMenu.add(btnLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 310, -1, -1));

        bgEmployeeSaleMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N
        employeeSaleMenu.add(bgEmployeeSaleMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 420));

        jPanel2.add(employeeSaleMenu, "card3");

        managerMenuWindow.setForeground(new java.awt.Color(255, 255, 255));
        managerMenuWindow.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                managerMenuWindowFocusGained(evt);
            }
        });

        saleMenuTab.setMinimumSize(new java.awt.Dimension(600, 400));
        saleMenuTab.setPreferredSize(new java.awt.Dimension(600, 400));
        saleMenuTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titlePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        menuTitleLabel.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        menuTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuTitleLabel.setText("Manager Sale Menu");

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addComponent(menuTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addComponent(menuTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        saleMenuTab.add(titlePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 30));

        cartTableForManager.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cartTableForManager.setModel(new javax.swing.table.DefaultTableModel(
            getCartList(store1),
            new String [] {
                "Name", "Price", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        cartTableForManager.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cartTableForManagerPropertyChange(evt);
            }
        });
        jScrollPane3.setViewportView(cartTableForManager);

        saleMenuTab.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 190, 210));

        cartLabel.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        cartLabel.setForeground(new java.awt.Color(255, 255, 255));
        cartLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cartLabel.setText("Cart");
        saleMenuTab.add(cartLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 190, -1));

        productLogLabel.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        productLogLabel.setForeground(new java.awt.Color(255, 255, 255));
        productLogLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productLogLabel.setText("Product Log");
        saleMenuTab.add(productLogLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 270, -1));

        productTableForManager.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        productTableForManager.setModel(new javax.swing.table.DefaultTableModel(
            getProductListLite(store1),
            new String [] {
                "ID", "Name", "Price", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productTableForManager.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productTableForManagerMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(productTableForManager);
        //Set first column (IDs) to be smaller than the rest
        productTableForManager.getColumnModel().getColumn(0).setPreferredWidth(32);
        productTableForManager.getColumnModel().getColumn(0).setMaxWidth(32);
        productTableForManager.getColumnModel().getColumn(0).setMinWidth(32);
        //Set third column (price) to be smaller than the rest
        productTableForManager.getColumnModel().getColumn(2).setPreferredWidth(45);
        productTableForManager.getColumnModel().getColumn(2).setMaxWidth(45);
        productTableForManager.getColumnModel().getColumn(2).setMinWidth(45);
        //Set fourth column (quantity) to be smaller than the rest
        productTableForManager.getColumnModel().getColumn(3).setPreferredWidth(32);
        productTableForManager.getColumnModel().getColumn(3).setMaxWidth(32);
        productTableForManager.getColumnModel().getColumn(3).setMinWidth(32);

        saleMenuTab.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 270, 320));

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Quantity:");
        saleMenuTab.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, -1, -1));

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("ID:");
        saleMenuTab.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 50, -1));

        selectedIDForManager.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        selectedIDForManager.setPreferredSize(new java.awt.Dimension(90, 22));
        selectedIDForManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedIDForManagerActionPerformed(evt);
            }
        });
        saleMenuTab.add(selectedIDForManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 110, -1));

        selectedQuantityForManager.setName(""); // NOI18N
        selectedQuantityForManager.setPreferredSize(new java.awt.Dimension(90, 22));
        saleMenuTab.add(selectedQuantityForManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, 110, -1));

        btnAddToCart1.setText("Add to Cart");
        btnAddToCart1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCart1ActionPerformed(evt);
            }
        });
        saleMenuTab.add(btnAddToCart1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 100, -1));

        btnClearCart1.setText("Clear Cart");
        btnClearCart1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearCart1ActionPerformed(evt);
            }
        });
        saleMenuTab.add(btnClearCart1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 90, -1));

        subTotalAmountForManager.setEditable(false);
        subTotalAmountForManager.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        subTotalAmountForManager.setText("$0.00");
        subTotalAmountForManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subTotalAmountForManagerActionPerformed(evt);
            }
        });
        saleMenuTab.add(subTotalAmountForManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 70, -1));

        taxAmountForManager.setEditable(false);
        taxAmountForManager.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        taxAmountForManager.setText("$0.00");
        taxAmountForManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taxAmountForManagerActionPerformed(evt);
            }
        });
        saleMenuTab.add(taxAmountForManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 70, -1));

        totalAmountForManager.setEditable(false);
        totalAmountForManager.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalAmountForManager.setText("$0.00");
        saleMenuTab.add(totalAmountForManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, 70, -1));

        btnSale1.setText("Complete Sale");
        btnSale1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSale1ActionPerformed(evt);
            }
        });
        saleMenuTab.add(btnSale1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 110, 30));

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Tax:");
        saleMenuTab.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 40, -1));

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Total:");
        saleMenuTab.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 40, -1));

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Sub:");
        saleMenuTab.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, 40, -1));

        btnLogOut1.setText("Log out");
        btnLogOut1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOut1ActionPerformed(evt);
            }
        });
        saleMenuTab.add(btnLogOut1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 340, 80, 20));

        bgManagerSaleMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N
        saleMenuTab.add(bgManagerSaleMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -34, 600, 420));

        managerMenuWindow.addTab("Sale Menu", saleMenuTab);
        saleMenuTab.getAccessibleContext().setAccessibleName("Sale Menu");

        productManagementTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        productManagementTable.setModel(new javax.swing.table.DefaultTableModel(
            getProductList(store1),
            new String [] {
                "ID", "Name", "Price", "Quantity", "Tax Rate", "Discount", "Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productManagementTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productManagementTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(productManagementTable);
        jScrollPane5.setViewportView(productManagementTable);
        productManagementTable.setAutoCreateRowSorter(true);
        if (productManagementTable.getColumnModel().getColumnCount() > 0) {
            productManagementTable.getColumnModel().getColumn(0).setMinWidth(35);
            productManagementTable.getColumnModel().getColumn(0).setPreferredWidth(35);
            productManagementTable.getColumnModel().getColumn(0).setMaxWidth(35);
            productManagementTable.getColumnModel().getColumn(2).setMinWidth(70);
            productManagementTable.getColumnModel().getColumn(2).setPreferredWidth(70);
            productManagementTable.getColumnModel().getColumn(2).setMaxWidth(70);
            productManagementTable.getColumnModel().getColumn(3).setMinWidth(65);
            productManagementTable.getColumnModel().getColumn(3).setPreferredWidth(65);
            productManagementTable.getColumnModel().getColumn(3).setMaxWidth(65);
            productManagementTable.getColumnModel().getColumn(4).setMinWidth(55);
            productManagementTable.getColumnModel().getColumn(4).setPreferredWidth(55);
            productManagementTable.getColumnModel().getColumn(4).setMaxWidth(55);
            productManagementTable.getColumnModel().getColumn(5).setMinWidth(40);
            productManagementTable.getColumnModel().getColumn(5).setPreferredWidth(40);
            productManagementTable.getColumnModel().getColumn(5).setMaxWidth(40);
            productManagementTable.getColumnModel().getColumn(6).setMinWidth(35);
            productManagementTable.getColumnModel().getColumn(6).setPreferredWidth(35);
            productManagementTable.getColumnModel().getColumn(6).setMaxWidth(35);
        }

        productManagementTab.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, 320));

        jLabel12.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Product Management");
        productManagementTab.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 140, -1));

        selectedIDProdManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedIDProdManagementActionPerformed(evt);
            }
        });
        productManagementTab.add(selectedIDProdManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 70, -1));
        productManagementTab.add(selectedNameProdManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 70, -1));
        productManagementTab.add(selectedPriceProdManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 70, -1));
        productManagementTab.add(selectedQuantityProdManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 70, -1));
        productManagementTab.add(selectedTaxRateProdManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 70, -1));
        productManagementTab.add(selectedDiscountProdManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 70, -1));

        selectedTypeProdManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedTypeProdManagementActionPerformed(evt);
            }
        });
        productManagementTab.add(selectedTypeProdManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 70, -1));

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Type:");
        productManagementTab.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 50, -1));

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("ID:");
        productManagementTab.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 50, -1));

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Name:");
        productManagementTab.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 50, -1));

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Price:");
        productManagementTab.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 50, -1));

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Quantity:");
        productManagementTab.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 50, -1));

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Tax Rate:");
        productManagementTab.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 50, -1));

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Discount:");
        productManagementTab.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 50, -1));

        btnAddProduct.setText("Add Product");
        btnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductActionPerformed(evt);
            }
        });
        productManagementTab.add(btnAddProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 120, -1));

        btnModifyProduct.setText("Modify by ID");
        btnModifyProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyProductActionPerformed(evt);
            }
        });
        productManagementTab.add(btnModifyProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 120, -1));

        btnDeleteProduct.setText("Delete by ID");
        btnDeleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProductActionPerformed(evt);
            }
        });
        productManagementTab.add(btnDeleteProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 120, -1));

        btnClearProdManagement.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        btnClearProdManagement.setText("Clear All");
        btnClearProdManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearProdManagementActionPerformed(evt);
            }
        });
        productManagementTab.add(btnClearProdManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 80, 20));

        searchFieldProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldProductsActionPerformed(evt);
            }
        });
        searchFieldProducts.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldProductsKeyReleased(evt);
            }
        });
        productManagementTab.add(searchFieldProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, 160, -1));

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Search:");
        productManagementTab.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 340, 70, -1));

        btnLogOut2.setText("Log out");
        btnLogOut2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOut2ActionPerformed(evt);
            }
        });
        productManagementTab.add(btnLogOut2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 340, 80, 20));

        bgProductManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N
        productManagementTab.add(bgProductManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -34, 600, 420));

        managerMenuWindow.addTab("Product Manangement", productManagementTab);

        employeesManagementTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setOpaque(true);
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(600, 400));

        employeeInfoTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "ID", "Position", "Wage", "Hours", "Password", "Manager?"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTable1);

        employeeInfoTab.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 39, 347, 223));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("List of Employees");
        employeeInfoTab.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 11, -1, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        employeeInfoTab.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(359, 274, -1, -1));

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        employeeInfoTab.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 274, 56, -1));

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        employeeInfoTab.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 274, -1, -1));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Name");
        employeeInfoTab.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 39, -1, -1));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Id Number");
        employeeInfoTab.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 71, -1, -1));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Position");
        employeeInfoTab.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 105, -1, -1));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Wage");
        employeeInfoTab.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 139, 37, -1));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Hours");
        employeeInfoTab.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 173, -1, -1));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Password");
        employeeInfoTab.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 207, -1, -1));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Manager Access?");
        employeeInfoTab.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 241, -1, -1));
        employeeInfoTab.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 39, 178, -1));
        employeeInfoTab.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 71, 148, -1));
        employeeInfoTab.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(414, 139, 177, -1));
        employeeInfoTab.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(414, 173, 177, -1));
        employeeInfoTab.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 207, 156, -1));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Information");
        employeeInfoTab.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 103, -1));

        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Yes");
        employeeInfoTab.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(488, 242, -1, -1));

        jCheckBox2.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox2.setText("No");
        employeeInfoTab.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(541, 242, -1, -1));

        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton7.setText("Clear");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        employeeInfoTab.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 274, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Admin", "Barista", "Manager", "Shift Lead" }));
        employeeInfoTab.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 105, 160, -1));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("SEARCH:");
        employeeInfoTab.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 277, -1, -1));

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });
        employeeInfoTab.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 277, 192, -1));

        bgEmployeeInfoTab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N
        employeeInfoTab.add(bgEmployeeInfoTab, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -65, 600, 420));

        jTabbedPane1.addTab("Employee Information", employeeInfoTab);

        businessDetailsTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Sunday", "9:00", "14:00"},
                {"Monday", "6:00", "17:00"},
                {"Tuesday", "6:00", "17:00"},
                {"Wednesday", "6:00", "17:00"},
                {"Thursday", "6:00", "17:00"},
                {"Friday", "6:00", "20:00"},
                {"Saturday", "6:00", "20:00"}
            },
            new String [] {
                "Day", "Open", "Close"
            }
        ));
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jTable5);

        businessDetailsTab.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 61, 310, 179));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Business Hours");
        businessDetailsTab.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 300, -1));

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Update Hours");
        businessDetailsTab.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 20, 150, -1));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Day");
        businessDetailsTab.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 30, -1));

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Open Time");
        businessDetailsTab.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, -1, -1));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Close Time");
        businessDetailsTab.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, -1, -1));

        jComboBox6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "6:00", "6:30", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00" }));
        businessDetailsTab.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, -1, -1));

        jComboBox7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "6:00", "6:30", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00" }));
        businessDetailsTab.add(jComboBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, -1, -1));

        jButton9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton9.setText("Update");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        businessDetailsTab.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 200, -1, -1));

        lblDay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDay.setForeground(new java.awt.Color(255, 255, 255));
        lblDay.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        businessDetailsTab.add(lblDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 90, 20));

        bgEmployeeManagement1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N
        businessDetailsTab.add(bgEmployeeManagement1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -35, 600, 420));

        jTabbedPane1.addTab("Business Details", businessDetailsTab);

        scheduleTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Employees");
        scheduleTab.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 15, -1, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jTable2);

        scheduleTab.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 41, 129, 273));

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Schedule");
        scheduleTab.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 15, -1, -1));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Day", "Start Time", "End Time", "Daily Hour", "Role"
            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(jTable3);

        scheduleTab.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 41, 247, 273));

        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Day");
        scheduleTab.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 44, -1, -1));

        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("End Time");
        scheduleTab.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 124, -1, -1));

        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("Start Time");
        scheduleTab.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 84, -1, -1));

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setText("Add");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        scheduleTab.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 155, -1, -1));

        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton5.setText("Update");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        scheduleTab.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(502, 155, -1, -1));

        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton6.setText("Delete");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        scheduleTab.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(502, 194, -1, -1));

        jButton8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton8.setText("Clear");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        scheduleTab.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 194, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "6:00", "6:30", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00" }));
        scheduleTab.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 81, -1, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "6:00", "6:30", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00" }));
        scheduleTab.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 121, -1, -1));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" }));
        scheduleTab.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 41, 99, -1));

        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Scheduled Hours:");
        scheduleTab.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 233, -1, -1));

        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Hours Limit:");
        scheduleTab.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 255, -1, -1));
        scheduleTab.add(lblScheduledHours, new org.netbeans.lib.awtextra.AbsoluteConstraints(517, 233, 43, -1));
        scheduleTab.add(lblHoursLimit, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 255, 43, -1));

        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("Overtime:");
        scheduleTab.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 277, -1, -1));
        scheduleTab.add(lblOvertime, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 277, 43, -1));

        bgEmployeeManagement2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N
        scheduleTab.add(bgEmployeeManagement2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -35, 600, 390));

        jTabbedPane1.addTab("Schedule", scheduleTab);

        employeesManagementTab.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 390));

        managerMenuWindow.addTab("Employee Management", employeesManagementTab);

        pnlInput.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHeader.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        lblHeader.setForeground(new java.awt.Color(255, 255, 255));
        lblHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeader.setText("Input Management");
        pnlInput.add(lblHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 163, 22));

        lblLineItemID.setForeground(new java.awt.Color(255, 255, 255));
        lblLineItemID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLineItemID.setText("ID:");
        pnlInput.add(lblLineItemID, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 76, -1));

        lblVendorName.setForeground(new java.awt.Color(255, 255, 255));
        lblVendorName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVendorName.setText("Vendor Name:");
        pnlInput.add(lblVendorName, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, -1));

        lblVendorCategory.setForeground(new java.awt.Color(255, 255, 255));
        lblVendorCategory.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVendorCategory.setText("Category:");
        pnlInput.add(lblVendorCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 76, -1));

        lblInputName.setForeground(new java.awt.Color(255, 255, 255));
        lblInputName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblInputName.setText("Input Name:");
        pnlInput.add(lblInputName, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 76, -1));

        lblUnitCost.setForeground(new java.awt.Color(255, 255, 255));
        lblUnitCost.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUnitCost.setText("Unit Cost $");
        pnlInput.add(lblUnitCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 76, -1));

        lblQuantity.setForeground(new java.awt.Color(255, 255, 255));
        lblQuantity.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblQuantity.setText("Quantity:");
        pnlInput.add(lblQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 76, -1));

        lblDiscount.setForeground(new java.awt.Color(255, 255, 255));
        lblDiscount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDiscount.setText("Discount %");
        pnlInput.add(lblDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 76, -1));

        lblTaxRate.setForeground(new java.awt.Color(255, 255, 255));
        lblTaxRate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTaxRate.setText("Tax Rate %");
        pnlInput.add(lblTaxRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 76, -1));

        txtLineItemID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLineItemIDActionPerformed(evt);
            }
        });
        txtLineItemID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLineItemIDKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLineItemIDKeyReleased(evt);
            }
        });
        pnlInput.add(txtLineItemID, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 75, -1));

        txtVendorName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtVendorNameKeyPressed(evt);
            }
        });
        pnlInput.add(txtVendorName, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 75, -1));

        txtVendorCategory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtVendorCategoryKeyPressed(evt);
            }
        });
        pnlInput.add(txtVendorCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 75, -1));

        txtInputName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtInputNameKeyPressed(evt);
            }
        });
        pnlInput.add(txtInputName, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 75, -1));

        txtUnitCost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUnitCostKeyPressed(evt);
            }
        });
        pnlInput.add(txtUnitCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 75, -1));

        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuantityKeyPressed(evt);
            }
        });
        pnlInput.add(txtQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 75, -1));

        txtDiscount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDiscountKeyPressed(evt);
            }
        });
        pnlInput.add(txtDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 75, -1));

        txtTaxRate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTaxRateKeyPressed(evt);
            }
        });
        pnlInput.add(txtTaxRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 75, -1));

        btnAdd.setFont(new java.awt.Font("Mshtakan", 1, 14)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        pnlInput.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 59, -1));

        btnUpdate.setFont(new java.awt.Font("Mshtakan", 1, 14)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                btnUpdateStateChanged(evt);
            }
        });
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        pnlInput.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, -1, -1));

        btnDelete.setFont(new java.awt.Font("Mshtakan", 1, 14)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        pnlInput.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 350, -1, -1));

        btnReset.setFont(new java.awt.Font("Mshtakan", 1, 14)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        pnlInput.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, -1, -1));

        tblInputReport.setFont(new java.awt.Font("Mshtakan", 0, 10)); // NOI18N
        tblInputReport.setModel(new javax.swing.table.DefaultTableModel(

            new Object [][] {

            },
            new String [] {
                "ID", "Vendor", "Cat.", "Input", "U.C. ($)", "Quan.", "Disc. (%)", "Tax (%)", "G.C. ($)", "D.A. ($)", "T.A. ($)", "N.C. ($)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblInputReport.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblInputReport.getTableHeader().setReorderingAllowed(false);
        tblInputReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInputReportMouseClicked(evt);
            }
        });
        tblInputReport.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblInputReport.getTableHeader().setReorderingAllowed(false);
        tblInputReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInputReportMouseClicked(evt);
            }
        });
        scrlpneInputReport.setViewportView(tblInputReport);
        scrlpneInputReport.setViewportView(tblInputReport);
        if (tblInputReport.getColumnModel().getColumnCount() > 0) {
            tblInputReport.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblInputReport.getColumnModel().getColumn(1).setPreferredWidth(70);
            tblInputReport.getColumnModel().getColumn(2).setPreferredWidth(70);
            tblInputReport.getColumnModel().getColumn(3).setPreferredWidth(70);
            tblInputReport.getColumnModel().getColumn(4).setPreferredWidth(40);
            tblInputReport.getColumnModel().getColumn(5).setPreferredWidth(40);
            tblInputReport.getColumnModel().getColumn(6).setPreferredWidth(40);
            tblInputReport.getColumnModel().getColumn(7).setPreferredWidth(40);
            tblInputReport.getColumnModel().getColumn(8).setPreferredWidth(50);
            tblInputReport.getColumnModel().getColumn(9).setPreferredWidth(50);
            tblInputReport.getColumnModel().getColumn(10).setPreferredWidth(50);
            tblInputReport.getColumnModel().getColumn(11).setPreferredWidth(50);
        }

        pnlInput.add(scrlpneInputReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 6, 430, 330));

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        pnlInput.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, 205, -1));

        lblSearch.setFont(new java.awt.Font("Mshtakan", 0, 14)); // NOI18N
        lblSearch.setForeground(new java.awt.Color(255, 255, 255));
        lblSearch.setText("Search:");
        pnlInput.add(lblSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 350, 51, -1));

        bgInputManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N
        pnlInput.add(bgInputManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -34, 600, 420));

        javax.swing.GroupLayout inputManagementTabLayout = new javax.swing.GroupLayout(inputManagementTab);
        inputManagementTab.setLayout(inputManagementTabLayout);
        inputManagementTabLayout.setHorizontalGroup(
            inputManagementTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        inputManagementTabLayout.setVerticalGroup(
            inputManagementTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        managerMenuWindow.addTab("Input Management", inputManagementTab);

        recordsTabs.setForeground(new java.awt.Color(255, 255, 255));

        transactionsTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        transactionsRecordsTable.setModel(new javax.swing.table.DefaultTableModel(
            getAllTransactionsList(store1),
            new String [] {
                "ID", "Total", "Sub Total", "Tax", "Quantity of Items"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        String tempTotal = String.format("$%.2f", getTotalRevenue());
        totalRevenue.setText(tempTotal);

        String tempSubTotal = String.format("$%.2f", getSubTotalRevenue());
        totalSubTotalRevenue.setText(tempSubTotal);

        String tempTax = String.format("$%.2f", getTotalTaxCollected());
        totalTaxCollected.setText(tempTax);

        int tempQuantity = getTotalUnitsSold();
        totalUnitsSold.setText(tempQuantity + "");

        int lastID = store1.getTransactions()-1;
        lastTransaction.setText(lastID + "");
        jScrollPane6.setViewportView(transactionsRecordsTable);
        if (transactionsRecordsTable.getColumnModel().getColumnCount() > 0) {
            transactionsRecordsTable.getColumnModel().getColumn(0).setMinWidth(40);
            transactionsRecordsTable.getColumnModel().getColumn(0).setPreferredWidth(40);
            transactionsRecordsTable.getColumnModel().getColumn(0).setMaxWidth(40);
            transactionsRecordsTable.getColumnModel().getColumn(4).setMinWidth(50);
            transactionsRecordsTable.getColumnModel().getColumn(4).setPreferredWidth(50);
            transactionsRecordsTable.getColumnModel().getColumn(4).setMaxWidth(50);
        }

        transactionsTab.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 290));

        lastTransaction.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lastTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastTransactionActionPerformed(evt);
            }
        });
        transactionsTab.add(lastTransaction, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 316, 90, -1));

        totalRevenue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalRevenue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalRevenueActionPerformed(evt);
            }
        });
        transactionsTab.add(totalRevenue, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 316, 90, -1));

        totalSubTotalRevenue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        transactionsTab.add(totalSubTotalRevenue, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 316, 90, -1));

        totalTaxCollected.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        transactionsTab.add(totalTaxCollected, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 316, 100, -1));

        totalUnitsSold.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        transactionsTab.add(totalUnitsSold, new org.netbeans.lib.awtextra.AbsoluteConstraints(363, 316, 90, -1));

        lastTransactionLabel.setForeground(new java.awt.Color(255, 255, 255));
        lastTransactionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastTransactionLabel.setText("Last Transaction");
        transactionsTab.add(lastTransactionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 294, 110, -1));

        totalRevenueLabel.setForeground(new java.awt.Color(255, 255, 255));
        totalRevenueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalRevenueLabel.setText("Total Revenue");
        transactionsTab.add(totalRevenueLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 294, 90, -1));

        totalSubTotalRevenueLabel.setForeground(new java.awt.Color(255, 255, 255));
        totalSubTotalRevenueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalSubTotalRevenueLabel.setText("Sub Total Revenue");
        transactionsTab.add(totalSubTotalRevenueLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 294, 110, -1));

        totalTaxCollectedLabel.setForeground(new java.awt.Color(255, 255, 255));
        totalTaxCollectedLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalTaxCollectedLabel.setText("Total Tax Collected");
        transactionsTab.add(totalTaxCollectedLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 294, -1, -1));

        totalUnitsSoldLabel.setForeground(new java.awt.Color(255, 255, 255));
        totalUnitsSoldLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalUnitsSoldLabel.setText("Total Units Sold");
        transactionsTab.add(totalUnitsSoldLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(363, 294, 90, -1));

        bgTransactionRecords.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N
        transactionsTab.add(bgTransactionRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -70, 600, 430));

        recordsTabs.addTab("Transaction Report", transactionsTab);

        employeeRecordsTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        employeesRecordsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "ID", "Position", "Wage", "Hours", "Password", "Manager?"
            }
        ));
        employeesRecordsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employeesRecordsTableMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(employeesRecordsTable);

        employeeRecordsTab.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 290));

        totalEmployees.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        employeeRecordsTab.add(totalEmployees, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 70, -1));

        totalManagers.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        employeeRecordsTab.add(totalManagers, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 70, -1));

        totalHoursWorked.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        employeeRecordsTab.add(totalHoursWorked, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, 80, -1));

        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Total Employees");
        employeeRecordsTab.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 110, -1));

        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Total Managers");
        employeeRecordsTab.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, 90, -1));

        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Total Hours Worked");
        employeeRecordsTab.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, 120, -1));

        bgEmployeeRecords.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N
        employeeRecordsTab.add(bgEmployeeRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -60, 600, 410));

        recordsTabs.addTab("Employee Records", employeeRecordsTab);

        inputRecordsTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblInputReportRecords.setFont(new java.awt.Font("Mshtakan", 0, 10)); // NOI18N
        tblInputReportRecords.setModel(new javax.swing.table.DefaultTableModel(

            new Object [][] {

            },
            new String [] {
                "ID", "Vendor", "Cat.", "Input", "U.C. ($)", "Quan.", "Disc. (%)", "Tax (%)", "G.C. ($)", "D.A. ($)", "T.A. ($)", "N.C. ($)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblInputReport.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblInputReport.getTableHeader().setReorderingAllowed(false);
        tblInputReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInputReportMouseClicked(evt);
            }
        });
        tblInputReportRecords.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblInputReportRecords.getTableHeader().setReorderingAllowed(false);
        tblInputReportRecords.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInputReportRecordsMouseClicked(evt);
            }
        });
        scrlpneInputReport1.setViewportView(tblInputReportRecords);

        inputRecordsTab.add(scrlpneInputReport1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 290));

        lblTotalNetCost1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotalNetCost1.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalNetCost1.setText("Total Net $");
        inputRecordsTab.add(lblTotalNetCost1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 290, -1, -1));

        txtTotalGrossCost.setEditable(false);
        txtTotalGrossCost.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalGrossCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalGrossCostActionPerformed(evt);
            }
        });
        inputRecordsTab.add(txtTotalGrossCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 90, -1));

        lblTotalGrossCost1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotalGrossCost1.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalGrossCost1.setText("Total Gross $");
        inputRecordsTab.add(lblTotalGrossCost1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, -1, -1));

        txtTotalNetCost.setEditable(false);
        txtTotalNetCost.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inputRecordsTab.add(txtTotalNetCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 310, 80, -1));

        txtTotalDiscountAmount.setEditable(false);
        txtTotalDiscountAmount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inputRecordsTab.add(txtTotalDiscountAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 310, 90, -1));

        lblTotalDiscountAmount1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotalDiscountAmount1.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalDiscountAmount1.setText("Total Discount $");
        inputRecordsTab.add(lblTotalDiscountAmount1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 290, -1, -1));

        txtTotalTaxAmount.setEditable(false);
        txtTotalTaxAmount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inputRecordsTab.add(txtTotalTaxAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, 80, -1));

        lblTotalTaxAmount1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotalTaxAmount1.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalTaxAmount1.setText("Total Tax $");
        inputRecordsTab.add(lblTotalTaxAmount1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, -1, -1));

        bgInputRecords.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N
        inputRecordsTab.add(bgInputRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -70, 600, 430));

        recordsTabs.addTab("Input Report", inputRecordsTab);

        productRecordsTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        productRecordsTable.setModel(new javax.swing.table.DefaultTableModel(
            getProductList(store1),
            new String [] {
                "ID", "Name", "Price", "Quantity", "Tax Rate", "Discount", "Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        totalProductsField.setText(getTotalProducts());
        totalUnitsField.setText(getTotalUnits());
        inventorySaleValueField.setText(getInventorySaleValue());
        productRecordsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productRecordsTableMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(productRecordsTable);

        productRecordsTab.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 290));

        totalProductsField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        productRecordsTab.add(totalProductsField, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, 80, 30));

        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Total Products");
        productRecordsTab.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 294, 84, -1));

        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Total Units");
        productRecordsTab.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 294, 84, -1));

        totalUnitsField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        productRecordsTab.add(totalUnitsField, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 310, 84, 30));

        inventorySaleValueField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        productRecordsTab.add(inventorySaleValueField, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 310, 106, 30));

        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Inventory Sale Value");
        productRecordsTab.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 294, -1, -1));

        bgProductRecords.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N
        productRecordsTab.add(bgProductRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -70, 600, 430));

        recordsTabs.addTab("Product Report", productRecordsTab);

        javax.swing.GroupLayout recordsTabLayout = new javax.swing.GroupLayout(recordsTab);
        recordsTab.setLayout(recordsTabLayout);
        recordsTabLayout.setHorizontalGroup(
            recordsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recordsTabLayout.createSequentialGroup()
                .addComponent(recordsTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        recordsTabLayout.setVerticalGroup(
            recordsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(recordsTabs)
        );

        managerMenuWindow.addTab("Reports", recordsTab);

        jPanel2.add(managerMenuWindow, "card4");
        managerMenuWindow.getAccessibleContext().setAccessibleName("Sale Menu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBounds(0, 0, 616, 416);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogOut1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOut1ActionPerformed
        signInWindow.setVisible(true);
        managerMenuWindow.setVisible(false);
    }//GEN-LAST:event_btnLogOut1ActionPerformed

    private void btnSale1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSale1ActionPerformed
        if(store1.getCart().isEmpty())
        {
            //TODO make label visibile that cart is empty
        }
        else
        {
            //get transaction number minus 1 for ArrayList access
            int tempTransaction = store1.getTransactions()-1;

            store1.sale(); //Execute Sale

            int tempQuantity = (int)store1.getAllTransactions().get(tempTransaction).get(4);
            double tempSubTotal = (double)store1.getAllTransactions().get(tempTransaction).get(2);
            double tempTaxAmount = (double)store1.getAllTransactions().get(tempTransaction).get(3);
            double tempTotal = (double)store1.getAllTransactions().get(tempTransaction).get(1);

            //Convert dollar values to formatted Strings for easy display
            String sSubTotal = String.format("%.2f", tempSubTotal);
            String sTaxAmount = String.format("%.2f", tempTaxAmount);
            String sTotal = String.format("%.2f", tempTotal);
            
            Icon icon1 = new javax.swing.ImageIcon(getClass().getResource("/normal/resources/teacup.png"));

            
            
            JOptionPane.showMessageDialog(this,
                "Transaction Completed.\nTotal items sold: " +
                tempQuantity + "\nSub Total: $" + sSubTotal +
                "\nTax: $" + sTaxAmount + "\nTotal: $" + sTotal,
                "Receipt for Transaction #" + (tempTransaction+1),
                JOptionPane.DEFAULT_OPTION, icon1);

            //REDRAW ALL TABLES PROCEDURE
            redrawCartTable("manager");
            redrawProductManagementTable();
            redrawAllTransactionsTable();

            //Update subtotal, tax amount and total boxes
            subTotalAmountForManager.setText(store1.getCurrentSubTotal(store1));
            taxAmountForManager.setText(store1.getCurrentTax(store1));
            totalAmountForManager.setText(store1.getCurrentTotal(store1));
        }
    }//GEN-LAST:event_btnSale1ActionPerformed

    private void taxAmountForManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taxAmountForManagerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_taxAmountForManagerActionPerformed

    private void subTotalAmountForManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subTotalAmountForManagerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subTotalAmountForManagerActionPerformed

    private void btnClearCart1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearCart1ActionPerformed
        if(!store1.getCart().isEmpty()){
            int value = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to clear the cart?");
            //Prompt user for yes, no, cancel
            //(0==yes, 1==no, 2==cancel) only clear for "0"

            if(value==0)
            {
                store1.clearCart();

                //REDRAW TABLE PROCEDURE
                redrawProductTable("manager");

                //REDRAW CART TABLE PROCEDURE
                redrawCartTable("manager");

                //Update subtotal, tax amount and total boxes
                subTotalAmountForManager.setText(store1.getCurrentSubTotal(store1));
                taxAmountForManager.setText(store1.getCurrentTax(store1));
                totalAmountForManager.setText(store1.getCurrentTotal(store1));

            }
        }
    }//GEN-LAST:event_btnClearCart1ActionPerformed

    private void btnAddToCart1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCart1ActionPerformed
        
        if((Integer)selectedQuantityForManager.getValue()>0){
            store1.addToCart(store1.getProductList().get(Integer.parseInt(selectedIDForManager.getText())), (Integer)selectedQuantityForManager.getValue());

            //Update subtotal, tax amount and total boxes
            subTotalAmountForManager.setText(store1.getCurrentSubTotal(store1));
            taxAmountForManager.setText(store1.getCurrentTax(store1));
            totalAmountForManager.setText(store1.getCurrentTotal(store1));

            //REDRAW PRODUCT TABLE PROCEDURE
            redrawProductTable("manager");

            //REDRAW CART TABLE PROCEDURE
            redrawCartTable("manager");

        } else {
            //TODO: add negative/zero catch
        }
    }//GEN-LAST:event_btnAddToCart1ActionPerformed

    private void selectedIDForManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedIDForManagerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectedIDForManagerActionPerformed

    private void productTableForManagerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productTableForManagerMouseClicked
        int index = productTableForManager.getSelectedRow();
        String tempId = productTableForManager.getValueAt(index,0).toString();
        selectedIDForManager.setText(tempId);
    }//GEN-LAST:event_productTableForManagerMouseClicked

    private void cartTableForManagerPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cartTableForManagerPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cartTableForManagerPropertyChange

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        signInWindow.setVisible(true);
        employeeSaleMenu.setVisible(false);
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaleActionPerformed
        if(store1.getCart().isEmpty())
        {
            //TODO make label visibile that cart is empty
        }
        else
        {
            //get transaction number minus 1 for ArrayList access
            int tempTransaction = store1.getTransactions()-1;

            store1.sale(); //Execute Sale

            int tempQuantity = (int)store1.getAllTransactions().get(tempTransaction).get(4);
            double tempSubTotal = (double)store1.getAllTransactions().get(tempTransaction).get(2);
            double tempTaxAmount = (double)store1.getAllTransactions().get(tempTransaction).get(3);
            double tempTotal = (double)store1.getAllTransactions().get(tempTransaction).get(1);

            //Convert dollar values to formatted Strings for easy display
            String sSubTotal = String.format("%.2f", tempSubTotal);
            String sTaxAmount = String.format("%.2f", tempTaxAmount);
            String sTotal = String.format("%.2f", tempTotal);

            JOptionPane.showConfirmDialog(this,
                "Transaction Completed.\nTotal items sold: " +
                tempQuantity + "\nSub Total: $" + sSubTotal +
                "\nTax: $" + sTaxAmount + "\nTotal: $" + sTotal,
                "Receipt for Transaction #" + (tempTransaction+1),
                JOptionPane.DEFAULT_OPTION);

            //REDRAW CART TABLE PROCEDURE
            redrawCartTable("employee");
            redrawAllTransactionsTable();

            //Update subtotal, tax amount and total boxes
            subTotalAmount.setText(store1.getCurrentSubTotal(store1));
            taxAmount.setText(store1.getCurrentTax(store1));
            totalAmount.setText(store1.getCurrentTotal(store1));
        }
    }//GEN-LAST:event_btnSaleActionPerformed

    private void taxAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taxAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_taxAmountActionPerformed

    private void subTotalAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subTotalAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subTotalAmountActionPerformed

    private void btnClearCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearCartActionPerformed
        if(!store1.getCart().isEmpty()){
            int value = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to clear the cart?");
            //Prompt user for yes, no, cancel
            //(0==yes, 1==no, 2==cancel) only clear for "0"

            if(value==0)
            {
                store1.clearCart();

                //REDRAW TABLE PROCEDURE
                redrawProductTable("employee");

                //REDRAW CART TABLE PROCEDURE
                redrawCartTable("employee");

                //Update subtotal, tax amount and total boxes
                subTotalAmount.setText(store1.getCurrentSubTotal(store1));
                taxAmount.setText(store1.getCurrentTax(store1));
                totalAmount.setText(store1.getCurrentTotal(store1));

            }
        }
    }//GEN-LAST:event_btnClearCartActionPerformed

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed

        if((Integer)selectedQuantity.getValue()>0){
            store1.addToCart(store1.getProductList().get(Integer.parseInt(selectedID.getText())), (Integer)selectedQuantity.getValue());

            //Update subtotal, tax amount and total boxes
            subTotalAmount.setText(store1.getCurrentSubTotal(store1));
            taxAmount.setText(store1.getCurrentTax(store1));
            totalAmount.setText(store1.getCurrentTotal(store1));

            //REDRAW PRODUCT TABLE PROCEDURE
            redrawProductTable("employee");

            //REDRAW CART TABLE PROCEDURE
            redrawCartTable("employee");

        } else {
            //TODO: add negative/zero catch
        }
    }//GEN-LAST:event_btnAddToCartActionPerformed

    private void selectedIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectedIDActionPerformed

    private void productTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productTableMouseClicked
        int index = productTable.getSelectedRow();
        String tempId = productTable.getValueAt(index,0).toString();
        selectedID.setText(tempId);
    }//GEN-LAST:event_productTableMouseClicked

    private void cartTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cartTablePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cartTablePropertyChange

    private void quitApplicationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitApplicationActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitApplicationActionPerformed

    private void manSignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manSignInActionPerformed
        normal.ManagerMainMenu managerSignIn = new ManagerMainMenu();
        String a = username.getText();
        String b = password.getText();
        String temp = managerSignIn.managerSignIn(store1, employees, schedule, a, b);

        //SIGN IN
        //Check for credentials
        if(temp.equals("success"))
        {
            //User sign in successful
            signInWindow.setVisible(false);
            managerMenuWindow.setVisible(true);

            //Set the following labels to invisibile for return to menu
            incorrectPasswordLabel.setVisible(false);
            notManagerLabel.setVisible(false);
            incorrectUserLabel.setVisible(false);

            //Set the username and password fields to blank for return
            username.setText("");
            password.setText("");
        }
        else if(temp.equals("failed"))
        {
            //Password incorrect
            incorrectUserLabel.setVisible(false);
            notManagerLabel.setVisible(false);
            incorrectPasswordLabel.setVisible(true);
        }
        else if(temp.equals("not_manager"))
        {
            //User is not a manager
            incorrectUserLabel.setVisible(false);
            incorrectPasswordLabel.setVisible(false);
            notManagerLabel.setVisible(true);
        }
        else
        {
            //Username not in system
            incorrectPasswordLabel.setVisible(false);
            notManagerLabel.setVisible(false);
            incorrectUserLabel.setVisible(true);
        }
    }//GEN-LAST:event_manSignInActionPerformed

    private void empSignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empSignInActionPerformed
        normal.EmployeeMainMenu empSignIn = new EmployeeMainMenu();
        String a = username.getText();
        String b = password.getText();
        String temp = empSignIn.employeeSignin(store1, employees, schedule, a, b);

        //SIGN IN
        //Check for credentials
        if(temp.equals("success"))
        {
            //User sign in successful
            signInWindow.setVisible(false);
            employeeSaleMenu.setVisible(true);

            //Set the following labels to invisibile for return to menu
            incorrectPasswordLabel.setVisible(false);
            incorrectUserLabel.setVisible(false);

            //Set the username and password fields to blank for return
            username.setText("");
            password.setText("");
        }
        else if(temp.equals("failed"))
        {
            //Password incorrect
            incorrectUserLabel.setVisible(false);
            incorrectPasswordLabel.setVisible(true);
        }
        else
        {
            //Username not in system
            incorrectPasswordLabel.setVisible(false);
            incorrectUserLabel.setVisible(true);
        }
    }//GEN-LAST:event_empSignInActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

    private void selectedIDProdManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedIDProdManagementActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectedIDProdManagementActionPerformed

    private void btnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductActionPerformed
        int tempID = -1; //temporary value
        boolean addCheck = true; //only add if true, true by default
        int value=3; //temp value for JOptionPane.showConfirmDialog
        
        //Make sure that required fields are filled out
        if(selectedTypeProdManagement.getText().isEmpty()){
            selectedTypeProdManagement.setBackground(Color.red);
            addCheck=false;
        }
        
        if(selectedQuantityProdManagement.getText().isEmpty()){
            selectedQuantityProdManagement.setBackground(Color.red);
            addCheck=false;
        }
        
        if(selectedPriceProdManagement.getText().isEmpty()){
            selectedPriceProdManagement.setBackground(Color.red);
            addCheck=false;
        }
        
        if(selectedNameProdManagement.getText().isEmpty()){
            selectedNameProdManagement.setBackground(Color.red);
            addCheck=false;
        }
        
        
        
        if(addCheck){
            //Show a confirm box to let the user double check
                value = JOptionPane.showConfirmDialog(this,
                "Product IDs are assigned automatically.\n\nAre the following options correct?\n" +
                "ID: " + store1.getProductIndex() + "\n" +
                "Name: " + selectedNameProdManagement.getText() + "\n" +
                "Price: $" + selectedPriceProdManagement.getText() + "\n" +
                "Quantity: " + selectedQuantityProdManagement.getText() + "\n" +
                "Tax Rate: " + selectedTaxRateProdManagement.getText() + "\n" +
                "Discount: " + selectedDiscountProdManagement.getText() + "\n" +
                "Type: " + selectedTypeProdManagement.getText() + "\n");
        } 
        else 
        {
            JOptionPane.showConfirmDialog(this,
                    "Please ensure that required fields are filled in.",
                    "Product Creation Error",
                    JOptionPane.DEFAULT_OPTION);
        }
        
        if(value==0){
            switch(selectedTypeProdManagement.getText().charAt(0)){
                case 'T':
                case 't':
                    Tea tempT = new Tea(store1,
                    selectedNameProdManagement.getText(),
                    Double.parseDouble(selectedPriceProdManagement.getText()),
                    Integer.parseInt(selectedQuantityProdManagement.getText()));
                    
                    if(!selectedTaxRateProdManagement.getText().isEmpty()){
                        tempT.setTaxRate(Double.parseDouble(selectedTaxRateProdManagement.getText()));
                    }
                    
                    if(!selectedDiscountProdManagement.getText().isEmpty()){
                        tempT.setDiscount(Double.parseDouble(selectedDiscountProdManagement.getText()));
                    }
                    
                    break;
                    
                case 'F':
                case 'f':
                    Food tempF = new Food(store1,
                    selectedNameProdManagement.getText(),
                    Double.parseDouble(selectedPriceProdManagement.getText()),
                    Integer.parseInt(selectedQuantityProdManagement.getText()));
                    
                    if(!selectedTaxRateProdManagement.getText().isEmpty()){
                        tempF.setTaxRate(Double.parseDouble(selectedTaxRateProdManagement.getText()));
                    }
                    
                    if(!selectedDiscountProdManagement.getText().isEmpty()){
                        tempF.setDiscount(Double.parseDouble(selectedDiscountProdManagement.getText()));
                    }
                    
                    break;
                
                case 'A':
                case 'a':
                    Apparel tempA = new Apparel(store1,
                    selectedNameProdManagement.getText(),
                    Double.parseDouble(selectedPriceProdManagement.getText()),
                    Integer.parseInt(selectedQuantityProdManagement.getText()));
                    
                    if(!selectedTaxRateProdManagement.getText().isEmpty()){
                        tempA.setTaxRate(Double.parseDouble(selectedTaxRateProdManagement.getText()));
                    }
                    
                    if(!selectedDiscountProdManagement.getText().isEmpty()){
                        tempA.setDiscount(Double.parseDouble(selectedDiscountProdManagement.getText()));
                    }
                    
                    break;
                
                case 'X':
                case 'x':
                    Product tempP = new Product(store1,
                    selectedNameProdManagement.getText(),
                    Double.parseDouble(selectedPriceProdManagement.getText()),
                    Integer.parseInt(selectedQuantityProdManagement.getText()));
                    
                    if(!selectedTaxRateProdManagement.getText().isEmpty()){
                        tempP.setTaxRate(Double.parseDouble(selectedTaxRateProdManagement.getText()));
                    }
                    
                    if(!selectedDiscountProdManagement.getText().isEmpty()){
                        tempP.setDiscount(Double.parseDouble(selectedDiscountProdManagement.getText()));
                    }
                    
                    break;
                    
                default:
                    
                    JOptionPane.showConfirmDialog(this,
                    "Please ensure entered Product Type is appropriate.\n" +
                    "\nAccepted values:\n't' -> Tea Products\n" +
                    "'f' -> Food Products\n'a' -> Apparel Products\n" +
                    "'x' -> Miscellaneous Products",
                    "Product Creation Error",
                    JOptionPane.DEFAULT_OPTION);
                  
                    
            }
            
            //Set box colors back to white after succesfully creating a product
            selectedNameProdManagement.setBackground(Color.white);
            selectedPriceProdManagement.setBackground(Color.white);
            selectedQuantityProdManagement.setBackground(Color.white);
            selectedTypeProdManagement.setBackground(Color.white);
            
        }
        //updateCSVProduct();
        redrawProductManagementTable();
        redrawProductTable("manager");
        updateCSVProduct();
    }//GEN-LAST:event_btnAddProductActionPerformed

    private void productManagementTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productManagementTableMouseClicked
        int index = productManagementTable.getSelectedRow();
        String temp = productManagementTable.getValueAt(index,0).toString();
        selectedIDProdManagement.setText(temp);
        temp = productManagementTable.getValueAt(index,1).toString();
        selectedNameProdManagement.setText(temp);
        temp = productManagementTable.getValueAt(index,2).toString();
        selectedPriceProdManagement.setText(temp);
        temp = productManagementTable.getValueAt(index,3).toString();
        selectedQuantityProdManagement.setText(temp);
        temp = productManagementTable.getValueAt(index,4).toString();
        selectedTaxRateProdManagement.setText(temp);
        temp = productManagementTable.getValueAt(index,5).toString();
        selectedDiscountProdManagement.setText(temp);
        temp = productManagementTable.getValueAt(index,6).toString();
        selectedTypeProdManagement.setText(temp);
    }//GEN-LAST:event_productManagementTableMouseClicked

    private void btnModifyProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyProductActionPerformed
        int tempSize = store1.getProductList().size();
        boolean modifyCheck = false;
        int value=3; //temp value for JOptionPane.showConfirmDialog
        int tempID;
        
        if(selectedIDProdManagement.getText().isEmpty() ||
                selectedNameProdManagement.getText().isEmpty() ||
                selectedPriceProdManagement.getText().isEmpty() ||
                selectedQuantityProdManagement.getText().isEmpty() ||
                selectedTypeProdManagement.getText().isEmpty() ){
            
            
            if(selectedTypeProdManagement.getText().isEmpty()){
            selectedTypeProdManagement.setBackground(Color.red);
            }
        
            if(selectedQuantityProdManagement.getText().isEmpty()){
            selectedQuantityProdManagement.setBackground(Color.red);
            }
        
            if(selectedPriceProdManagement.getText().isEmpty()){
            selectedPriceProdManagement.setBackground(Color.red);
            }
        
            if(selectedNameProdManagement.getText().isEmpty()){
            selectedNameProdManagement.setBackground(Color.red);
            }
            
            if(selectedIDProdManagement.getText().isEmpty()){
            selectedIDProdManagement.setBackground(Color.red);
            }

            //If no ID matches, show user that product is not in system
            JOptionPane.showConfirmDialog(this,
                    "Please fill in the required information.",
                    "Product Modification Error",
                    JOptionPane.DEFAULT_OPTION);
            value=4; //to avoid "no id in system" below
        } 
        else
        {
            //back to white in case red
            selectedIDProdManagement.setBackground(Color.white);
            selectedNameProdManagement.setBackground(Color.white);
            selectedPriceProdManagement.setBackground(Color.white);
            selectedQuantityProdManagement.setBackground(Color.white);
            selectedTypeProdManagement.setBackground(Color.white);
            
            tempID = Integer.parseInt(selectedIDProdManagement.getText());
            
            //Iterate throgh product List to ensure product is there
            Iterator<Integer> itr = store1.getProductList().keySet().iterator();
        
            while(itr.hasNext()){
                int i = itr.next();
                if(store1.getProductList().get(i).getId() == tempID){
                
                    //Show a confirm box to let the user double check
                    value = JOptionPane.showConfirmDialog(this,
                    "Are the following options correct?\n" +
                    "ID: " + tempID + "\n" +
                    "Name: " + selectedNameProdManagement.getText() + "\n" +
                    "Price: $" + selectedPriceProdManagement.getText() + "\n" +
                    "Quantity: " + selectedQuantityProdManagement.getText() + "\n" +
                    "Tax Rate: " + selectedTaxRateProdManagement.getText() + "\n" +
                    "Discount: " + selectedDiscountProdManagement.getText() + "\n" +
                    "Type: " + selectedTypeProdManagement.getText() + "\n");
                
                    //0=yes
                    if(value == 0){
                        modifyCheck = true;
                        //System.out.println("mody check " + modifyCheck);
                    }
                    break;
                }
            }
        }
        //Modify the product based on ID
        if(modifyCheck){
            tempID = Integer.parseInt(selectedIDProdManagement.getText());
            store1.getProductList().get(tempID).setName(selectedNameProdManagement.getText().toString());
            store1.getProductList().get(tempID).setPrice(Double.parseDouble(selectedPriceProdManagement.getText().toString()));
            store1.getProductList().get(tempID).setQuantity(Integer.parseInt(selectedQuantityProdManagement.getText().toString()));
            store1.getProductList().get(tempID).setTaxRate(Double.parseDouble(selectedTaxRateProdManagement.getText().toString()));
            store1.getProductList().get(tempID).setDiscount(Double.parseDouble(selectedDiscountProdManagement.getText().toString()));
            store1.getProductList().get(tempID).setProductType(selectedTypeProdManagement.getText().charAt(0));
                
        } 
        else if(value==3)
        {
            //If no ID matches, show user that product is not in system
            JOptionPane.showConfirmDialog(this,
                    "No product in system with that ID.",
                    "Product Modification Error",
                    JOptionPane.DEFAULT_OPTION);
        } 
        else 
        {
            
        }
        //updateCSVProduct();
        redrawProductManagementTable();
        redrawProductTable("manager");
        updateCSVProduct();
    }//GEN-LAST:event_btnModifyProductActionPerformed

    private void managerMenuWindowFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_managerMenuWindowFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_managerMenuWindowFocusGained

    private void btnDeleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProductActionPerformed
        if(selectedIDProdManagement.getText().isEmpty()){
            selectedIDProdManagement.setBackground(Color.red);
             JOptionPane.showConfirmDialog(this,
                    "Must enter ID.",
                    "Product Deletion Error",
                    JOptionPane.DEFAULT_OPTION);
        } 
        else 
        {
            int tempID = Integer.parseInt(selectedIDProdManagement.getText());
            
            if(store1.getProductList().containsKey(tempID)){
                int value = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete the product " +
                            store1.getProductList().get(tempID).getName() + 
                            "?");
                
                if(value==0){
                    store1.getProductList().remove(tempID);
                }
                else
                {
                    JOptionPane.showConfirmDialog(this,
                    "Deletion terminated",
                    "Product Deletion Terminated",
                    JOptionPane.DEFAULT_OPTION);
                }
            }
            else
            {
                JOptionPane.showConfirmDialog(this,
                    "ID is not in system.",
                    "Product Deletion Error",
                    JOptionPane.DEFAULT_OPTION);
            }
        }
        //updateCSVProduct();
        redrawProductManagementTable();
        redrawProductTable("manager");
        updateCSVProduct();
    }//GEN-LAST:event_btnDeleteProductActionPerformed

    private void btnClearProdManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearProdManagementActionPerformed
        //Clear all fields
        selectedIDProdManagement.setText("");
        selectedNameProdManagement.setText("");
        selectedPriceProdManagement.setText("");
        selectedQuantityProdManagement.setText("");
        selectedDiscountProdManagement.setText("");
        selectedTaxRateProdManagement.setText("");
        selectedTypeProdManagement.setText("");
        
        //Make all fields white in case there are red boxes left over
        selectedIDProdManagement.setBackground(Color.white);
        selectedNameProdManagement.setBackground(Color.white);
        selectedPriceProdManagement.setBackground(Color.white);
        selectedQuantityProdManagement.setBackground(Color.white);
        selectedDiscountProdManagement.setBackground(Color.white);
        selectedTaxRateProdManagement.setBackground(Color.white);
        selectedTypeProdManagement.setBackground(Color.white);
        
        
        redrawProductManagementTable();
        
    }//GEN-LAST:event_btnClearProdManagementActionPerformed

    private void selectedTypeProdManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedTypeProdManagementActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectedTypeProdManagementActionPerformed

    private void btnLogOut2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOut2ActionPerformed
        signInWindow.setVisible(true);
        managerMenuWindow.setVisible(false);
    }//GEN-LAST:event_btnLogOut2ActionPerformed

    private void searchFieldProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldProductsActionPerformed
        
    }//GEN-LAST:event_searchFieldProductsActionPerformed

    private void searchFieldProductsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldProductsKeyReleased
        String query = searchFieldProducts.getText();
        filterProductTable(query);
    }//GEN-LAST:event_searchFieldProductsKeyReleased

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        String query = txtSearch.getText();
        filterInputTable(query);
    }//GEN-LAST:event_txtSearchKeyReleased

    private void tblInputReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInputReportMouseClicked
        DefaultTableModel inputReport = (DefaultTableModel) tblInputReport.getModel();
        int index = tblInputReport.getSelectedRow();
        if (tblInputReport.getSelectionModel().isSelectedIndex(index))
        {
            txtLineItemID.setEditable(false);
            txtLineItemID.setBackground(Color.YELLOW);
            btnAdd.setEnabled(false);
            txtLineItemID.setText(inputReport.getValueAt(index, 0).toString());
            txtVendorName.setText(inputReport.getValueAt(index, 1).toString());
            txtVendorCategory.setText(inputReport.getValueAt(index, 2).toString());
            txtInputName.setText(inputReport.getValueAt(index, 3).toString());
            txtUnitCost.setText(inputReport.getValueAt(index, 4).toString());
            txtQuantity.setText(inputReport.getValueAt(index, 5).toString());
            txtDiscount.setText(inputReport.getValueAt(index, 6).toString());
            txtTaxRate.setText(inputReport.getValueAt(index, 7).toString());
        }
        else
        {
            tblInputReport.getSelectionModel().clearSelection();
            txtLineItemID.setEditable(true);
            txtLineItemID.setBackground(Color.GREEN);
            btnAdd.setEnabled(true);
            txtLineItemID.setText("");
            txtVendorName.setText("");
            txtVendorCategory.setText("");
            txtInputName.setText("");
            txtUnitCost.setText("");
            txtQuantity.setText("");
            txtDiscount.setText("");
            txtTaxRate.setText("");
        }
    }//GEN-LAST:event_tblInputReportMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtLineItemID.setText("");
        txtVendorName.setText("");
        txtVendorCategory.setText("");
        txtInputName.setText("");
        txtUnitCost.setText("");
        txtQuantity.setText("");
        txtDiscount.setText("");
        txtTaxRate.setText("");
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        input = new Input();

        int yesOrNo = JOptionPane.showConfirmDialog(null,
            "Are you sure you want to delete this line item?", "Update Line Item",
            JOptionPane.YES_NO_OPTION);
        if (yesOrNo == 0)
        {
            DefaultTableModel inputReport = (DefaultTableModel) tblInputReport.getModel();
            DefaultTableModel inputReportRecords = (DefaultTableModel) tblInputReportRecords.getModel();
            int index = tblInputReport.getSelectedRow();
            int lineItemID = Integer.valueOf(txtLineItemID.getText());
            inputReport.removeRow(index);
            inputList.removeInput(lineItemID);

            JOptionPane.showMessageDialog(this, "Successfully Deleted Line Item");

            input.setLineItemID(Integer.valueOf(txtLineItemID.getText()));
            input.setVendorName(txtVendorName.getText());
            input.setVendorCategory(txtVendorCategory.getText());
            input.setInput(txtInputName.getText());
            input.setCost(Double.valueOf(txtUnitCost.getText()));
            input.setQuantity(Integer.valueOf(txtQuantity.getText()));
            input.setDiscount(Double.valueOf(txtDiscount.getText()));
            input.setTaxRate(Double.valueOf(txtTaxRate.getText()));
            double grossCost = input.getGrossCost(Double.valueOf(txtUnitCost.getText()),
                Integer.valueOf(txtQuantity.getText()));
            double discAmt = input.getDiscountAmount(
                Double.valueOf(txtUnitCost.getText()),
                Integer.valueOf(txtQuantity.getText()),
                Double.valueOf(txtDiscount.getText()));
            double taxAmt = input.getTaxAmount(Double.valueOf(txtTaxRate.getText()),
                Double.valueOf(txtUnitCost.getText()),
                Integer.valueOf(txtQuantity.getText()));
            double netCost = input.getNetCost(Double.valueOf(txtTaxRate.getText()),
                Double.valueOf(txtUnitCost.getText()),
                Integer.valueOf(txtQuantity.getText()),
                Double.valueOf(txtDiscount.getText()));

            txtTotalGrossCost.setText(String.format("%.2f", getTotalGrossCost(grossCost)));
            txtTotalDiscountAmount.setText(String.format("%.2f", getTotalDiscountAmount(discAmt)));
            txtTotalTaxAmount.setText(String.format("%.2f", getTotalTaxAmount(taxAmt)));
            txtTotalNetCost.setText(String.format("%.2f", getTotalNetCost(netCost)));
            txtTotalGrossCost.setText(String.format("%.2f", getTotalGrossCost(0.0)));

            txtLineItemID.setText("");
            txtVendorName.setText("");
            txtVendorCategory.setText("");
            txtInputName.setText("");
            txtUnitCost.setText("");
            txtQuantity.setText("");
            txtDiscount.setText("");
            txtTaxRate.setText("");
            updateCSVInput();
            tblInputReport.getSelectionModel().clearSelection();
            tblInputReportRecords.getSelectionModel().clearSelection();
            
            btnAdd.setEnabled(true);
            txtLineItemID.setEditable(true);
            txtLineItemID.setBackground(Color.GREEN);
        }
        else
        {
            return;
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            input = new Input();
            int yesOrNo = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to update this line item?", "Update Line Item",
                JOptionPane.YES_NO_OPTION);
            if (yesOrNo == 0)
            {
                DefaultTableModel inputReport = (DefaultTableModel) tblInputReport.getModel();
                int index = tblInputReport.getSelectedRow();
                input.setLineItemID(Integer.valueOf(txtLineItemID.getText()));
                input.setVendorName(txtVendorName.getText());
                input.setVendorCategory(txtVendorCategory.getText());
                input.setInput(txtInputName.getText());
                input.setCost(Double.valueOf(txtUnitCost.getText()));
                input.setQuantity(Integer.valueOf(txtQuantity.getText()));
                input.setDiscount(Double.valueOf(txtDiscount.getText()));
                input.setTaxRate(Double.valueOf(txtTaxRate.getText()));
                double grossCost = input.getGrossCost(Double.valueOf(txtUnitCost.getText()),
                    Integer.valueOf(txtQuantity.getText()));
                double discAmt = input.getDiscountAmount(
                    Double.valueOf(txtUnitCost.getText()),
                    Integer.valueOf(txtQuantity.getText()),
                    Double.valueOf(txtDiscount.getText()));
                double taxAmt = input.getTaxAmount(Double.valueOf(txtTaxRate.getText()),
                    Double.valueOf(txtUnitCost.getText()),
                    Integer.valueOf(txtQuantity.getText()));
                double netCost = input.getNetCost(Double.valueOf(txtTaxRate.getText()),
                    Double.valueOf(txtUnitCost.getText()),
                    Integer.valueOf(txtQuantity.getText()),
                    Double.valueOf(txtDiscount.getText()));

                tblInputReport.setValueAt(Integer.valueOf(txtLineItemID.getText()), index, 0);
                tblInputReport.setValueAt(txtVendorName.getText(), index, 1);
                tblInputReport.setValueAt(txtVendorCategory.getText(), index, 2);
                tblInputReport.setValueAt(txtInputName.getText(), index, 3);
                tblInputReport.setValueAt(Double.valueOf(txtUnitCost.getText()), index, 4);
                tblInputReport.setValueAt(Integer.valueOf(txtQuantity.getText()), index, 5);
                tblInputReport.setValueAt(Double.valueOf(txtDiscount.getText()), index, 6);
                tblInputReport.setValueAt(Double.valueOf(txtTaxRate.getText()), index, 7);
                tblInputReport.setValueAt((grossCost), index, 8);
                tblInputReport.setValueAt((discAmt), index, 9);
                tblInputReport.setValueAt((taxAmt), index, 10);
                tblInputReport.setValueAt((netCost), index, 11);
                
                
                        
                tblInputReportRecords.setValueAt(Integer.valueOf(txtLineItemID.getText()), index, 0);
                tblInputReportRecords.setValueAt(txtVendorName.getText(), index, 1);
                tblInputReportRecords.setValueAt(txtVendorCategory.getText(), index, 2);
                tblInputReportRecords.setValueAt(txtInputName.getText(), index, 3);
                tblInputReportRecords.setValueAt(Double.valueOf(txtUnitCost.getText()), index, 4);
                tblInputReportRecords.setValueAt(Integer.valueOf(txtQuantity.getText()), index, 5);
                tblInputReportRecords.setValueAt(Double.valueOf(txtDiscount.getText()), index, 6);
                tblInputReportRecords.setValueAt(Double.valueOf(txtTaxRate.getText()), index, 7);
                tblInputReportRecords.setValueAt((grossCost), index, 8);
                tblInputReportRecords.setValueAt((discAmt), index, 9);
                tblInputReportRecords.setValueAt((taxAmt), index, 10);
                tblInputReportRecords.setValueAt((netCost), index, 11);

                txtTotalGrossCost.setText(String.format("%.2f", getTotalGrossCost(tblInputReport.getSelectedRow())));
                txtTotalDiscountAmount.setText(String.format("%.2f", getTotalDiscountAmount(tblInputReport.getSelectedRow())));
                txtTotalTaxAmount.setText(String.format("%.2f", getTotalTaxAmount(tblInputReport.getSelectedRow())));
                txtTotalNetCost.setText(String.format("%.2f", getTotalNetCost(tblInputReport.getSelectedRow())));

                int lineItemID = Integer.valueOf(txtLineItemID.getText());
                String vendor = txtVendorName.getText();
                String category = txtVendorCategory.getText();
                String inputx = txtInputName.getText();
                double unitCost = Double.valueOf(txtUnitCost.getText());
                int quantity = Integer.valueOf(txtQuantity.getText());
                double discount = Double.valueOf(txtDiscount.getText());
                double tax = Double.valueOf(txtTaxRate.getText());
                double gc = Double.valueOf(txtTotalGrossCost.getText());
                double da = Double.valueOf(txtTotalDiscountAmount.getText());
                double ta = Double.valueOf(txtTotalTaxAmount.getText());
                double nc = Double.valueOf(txtTotalNetCost.getText());

                addInput(lineItemID, vendor, category, inputx,
                    unitCost, quantity, discount, tax);

                updateCSVInput();

                JOptionPane.showMessageDialog(this, "Successfully Updated Line Item");
            }
            else
            {
                return;
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "At least one of your entries has an error");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnUpdateStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_btnUpdateStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateStateChanged

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            input = new Input();
            input.setLineItemID(Integer.valueOf(txtLineItemID.getText()));
            input.setVendorName(txtVendorName.getText());
            input.setVendorCategory(txtVendorCategory.getText());
            input.setInput(txtInputName.getText());
            input.setCost(Double.valueOf(txtUnitCost.getText()));
            input.setQuantity(Integer.valueOf(txtQuantity.getText()));
            input.setDiscount(Double.valueOf(txtDiscount.getText()));
            input.setTaxRate(Double.valueOf(txtTaxRate.getText()));
            double grossCost = input.getGrossCost(Double.valueOf(txtUnitCost.getText()),
                Integer.valueOf(txtQuantity.getText()));
            double discAmt = input.getDiscountAmount(
                Double.valueOf(txtUnitCost.getText()),
                Integer.valueOf(txtQuantity.getText()),
                Double.valueOf(txtDiscount.getText()));
            double taxAmt = input.getTaxAmount(Double.valueOf(txtTaxRate.getText()),
                Double.valueOf(txtUnitCost.getText()),
                Integer.valueOf(txtQuantity.getText()));
            double netCost = input.getNetCost(Double.valueOf(txtTaxRate.getText()),
                Double.valueOf(txtUnitCost.getText()),
                Integer.valueOf(txtQuantity.getText()),
                Double.valueOf(txtDiscount.getText()));

            DefaultTableModel inputReport = (DefaultTableModel) tblInputReport.getModel();
            int index = tblInputReport.getSelectedRow();
            tblInputReport.getSelectedColumns();

            Object[] toAdd = {Integer.valueOf(txtLineItemID.getText()),
                String.valueOf(txtVendorName.getText()),
                String.valueOf(txtVendorCategory.getText()),
                String.valueOf(txtInputName.getText()),
                Double.valueOf(txtUnitCost.getText()),
                Integer.valueOf(txtQuantity.getText()),
                Double.valueOf(txtDiscount.getText()),
                Double.valueOf(txtTaxRate.getText()),
                grossCost, discAmt, taxAmt, netCost};

            txtTotalGrossCost.setText(String.format("%.2f",getTotalGrossCost(grossCost)));
            txtTotalDiscountAmount.setText(String.format("%.2f",getTotalDiscountAmount(discAmt)));
            txtTotalTaxAmount.setText(String.format("%.2f",getTotalTaxAmount(taxAmt)));
            txtTotalNetCost.setText(String.format("%.2f",getTotalNetCost(netCost)));

            inputReport.addRow(toAdd);

            int lineItemID = Integer.valueOf(txtLineItemID.getText());
            String vendor = txtVendorName.getText();
            String category = txtVendorCategory.getText();
            String inputx = txtInputName.getText();
            double unitCost = Double.valueOf(txtUnitCost.getText());
            int quantity = Integer.valueOf(txtQuantity.getText());
            double discount = Double.valueOf(txtDiscount.getText());
            double tax = Double.valueOf(txtTaxRate.getText());
            double gc = Double.valueOf(txtTotalGrossCost.getText());
            double da = Double.valueOf(txtTotalDiscountAmount.getText());
            double ta = Double.valueOf(txtTotalTaxAmount.getText());
            double nc = Double.valueOf(txtTotalNetCost.getText());
            
            addInput(lineItemID, vendor, category, inputx,
                unitCost, quantity, discount, tax);

            txtLineItemID.setText("");
            txtVendorName.setText("");
            txtVendorCategory.setText("");
            txtInputName.setText("");
            txtUnitCost.setText("");
            txtQuantity.setText("");
            txtDiscount.setText("");
            txtTaxRate.setText("");
            updateCSVInput();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "At least one of your entries has an error");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtTaxRateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTaxRateKeyPressed
        int key = evt.getKeyCode();
        if ((key >= evt.VK_0 && key <= evt.VK_9) ||
            (key >= evt.VK_NUMPAD0 && key <= evt.VK_NUMPAD9)
            || (key == KeyEvent.VK_BACK_SPACE)
            || (key == KeyEvent.VK_PERIOD))
        {
            txtTaxRate.setEditable(true);
            txtTaxRate.setBackground(Color.GREEN);
        }
        else
        {
            txtTaxRate.setEditable(false);
            txtTaxRate.setBackground(Color.RED);
        }
    }//GEN-LAST:event_txtTaxRateKeyPressed

    private void txtDiscountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiscountKeyPressed
        int key = evt.getKeyCode();
        if ((key >= evt.VK_0 && key <= evt.VK_9) ||
            (key >= evt.VK_NUMPAD0 && key <= evt.VK_NUMPAD9) ||
            (key == KeyEvent.VK_BACK_SPACE) ||
            (key == KeyEvent.VK_PERIOD))
        {
            txtDiscount.setEditable(true);
            txtDiscount.setBackground(Color.GREEN);
        }
        else
        {
            txtDiscount.setEditable(false);
            txtDiscount.setBackground(Color.RED);
        }
    }//GEN-LAST:event_txtDiscountKeyPressed

    private void txtQuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyPressed
        int key = evt.getKeyCode();
        if ((key >= evt.VK_0 && key <= evt.VK_9) ||
            (key >= evt.VK_NUMPAD0 && key <= evt.VK_NUMPAD9)
            || (key == KeyEvent.VK_BACK_SPACE))
        {
            txtQuantity.setEditable(true);
            txtQuantity.setBackground(Color.GREEN);
        }
        else
        {
            txtQuantity.setEditable(false);
            txtQuantity.setBackground(Color.RED);
        }
    }//GEN-LAST:event_txtQuantityKeyPressed

    private void txtUnitCostKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnitCostKeyPressed
        int key = evt.getKeyCode();
        if ((key >= evt.VK_0 && key <= evt.VK_9) ||
            (key >= evt.VK_NUMPAD0 && key <= evt.VK_NUMPAD9)
            || (key == KeyEvent.VK_BACK_SPACE)
            || (key == KeyEvent.VK_PERIOD))
        {
            txtUnitCost.setEditable(true);
            txtUnitCost.setBackground(Color.GREEN);
        }
        else
        {
            txtUnitCost.setEditable(false);
            txtUnitCost.setBackground(Color.RED);
        }
    }//GEN-LAST:event_txtUnitCostKeyPressed

    private void txtInputNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInputNameKeyPressed
        txtInputName.setEditable(true);
        txtInputName.setBackground(Color.GREEN);
    }//GEN-LAST:event_txtInputNameKeyPressed

    private void txtVendorCategoryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVendorCategoryKeyPressed
        txtVendorCategory.setEditable(true);
        txtVendorCategory.setBackground(Color.GREEN);
    }//GEN-LAST:event_txtVendorCategoryKeyPressed

    private void txtVendorNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVendorNameKeyPressed
        txtVendorName.setEditable(true);
        txtVendorName.setBackground(Color.GREEN);
    }//GEN-LAST:event_txtVendorNameKeyPressed

    private void txtLineItemIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLineItemIDKeyReleased
        try {
            DefaultTableModel inputReport = (DefaultTableModel) tblInputReport.getModel();
            int index = tblInputReport.getSelectedRow();
            if (tblInputReport.isRowSelected(index)) {
                txtLineItemID.setEditable(false);
                txtLineItemID.setBackground(Color.YELLOW);
            } else {
                txtLineItemID.setEditable(true);
                txtLineItemID.setBackground(Color.GREEN);
            }
            if (inputList.getInputs().containsKey(Integer.valueOf(txtLineItemID.getText()))) {
                txtLineItemID.setEditable(false);
                txtLineItemID.setBackground(Color.YELLOW);
                btnAdd.setEnabled(false);
            } else {
                txtLineItemID.setEditable(true);
                txtLineItemID.setBackground(Color.GREEN);
                btnAdd.setEnabled(true);
            }
        }
        catch (Exception e) {
            //JOptionPane.showMessageDialog(this, "At least one of your entries has an error");
            //System.out.println();
        }
    }//GEN-LAST:event_txtLineItemIDKeyReleased

    private void txtLineItemIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLineItemIDKeyPressed
        int key = evt.getKeyCode();;
        DefaultTableModel inputReport = (DefaultTableModel) tblInputReport.getModel();
        int index = tblInputReport.getSelectedRow();
        int colindex = tblInputReport.getSelectedColumn();
        if ((key >= evt.VK_0 && key <= evt.VK_9)
            || (key >= evt.VK_NUMPAD0 && key <= evt.VK_NUMPAD9)
            || (key == KeyEvent.VK_BACK_SPACE))
        {
            txtLineItemID.setEditable(true);
            txtLineItemID.setBackground(Color.GREEN);
        }
        else
        {
            txtLineItemID.setEditable(false);
            txtLineItemID.setBackground(Color.RED);
        }

        if (tblInputReport.isRowSelected(index))
        {
            txtLineItemID.setEditable(false);
            txtLineItemID.setBackground(Color.YELLOW);
        }
        else
        {
            txtLineItemID.setEditable(true);
            txtLineItemID.setBackground(Color.GREEN);
        }
//        if (inputList.getInputs().containsKey(Integer.valueOf(txtLineItemID.getText())))
//        {
//            txtLineItemID.setText("");
//            txtLineItemID.setEditable(false);
//            txtLineItemID.setBackground(Color.YELLOW);
//            JOptionPane.showMessageDialog(this, "ID already exists");
//        } 
//        else if ((key == KeyEvent.VK_BACK_SPACE))
//        {
//            txtLineItemID.setEditable(true);
//            txtLineItemID.setBackground(Color.GREEN);
//        }
//        else
//        {
//            txtLineItemID.setEditable(true);
//            txtLineItemID.setBackground(Color.GREEN);
//        }   
    }//GEN-LAST:event_txtLineItemIDKeyPressed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void productRecordsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productRecordsTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_productRecordsTableMouseClicked

    private void txtTotalGrossCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalGrossCostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalGrossCostActionPerformed

    private void tblInputReportRecordsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInputReportRecordsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblInputReportRecordsMouseClicked

    private void totalRevenueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalRevenueActionPerformed

    }//GEN-LAST:event_totalRevenueActionPerformed

    private void lastTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastTransactionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastTransactionActionPerformed

    private void txtLineItemIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLineItemIDActionPerformed
            
    }//GEN-LAST:event_txtLineItemIDActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        String oName = model.getValueAt(jTable1.getSelectedRow(), 0).toString();
        String oId = model.getValueAt(jTable1.getSelectedRow(), 1).toString();
        String oPosition = model.getValueAt(jTable1.getSelectedRow(), 2).toString();
        String oWage = model.getValueAt(jTable1.getSelectedRow(), 3).toString();
        String oHours = model.getValueAt(jTable1.getSelectedRow(), 4).toString();
        String oPass = model.getValueAt(jTable1.getSelectedRow(), 5).toString();
        String oType = model.getValueAt(jTable1.getSelectedRow(), 6).toString();

        boolean type1 = false;
        boolean type2 = false;
        switch (oType) {
            case "Yes" -> {
                type1 = true;
                type2 = false;
            }
            case "No" -> {
                type1 = false;
                type2 = true;
            }
            default -> JOptionPane.showMessageDialog(this,
                "Error reading manager access",
                "Manager Access Error",
                JOptionPane.ERROR_MESSAGE);
        }

        jTextField1.setText(oName);
        jTextField2.setText(oId);
        jComboBox1.setSelectedItem(oPosition);
        jTextField4.setText(oWage);
        jTextField5.setText(oHours);
        jPasswordField1.setText(oPass);
        jCheckBox1.setSelected(type1);
        jCheckBox2.setSelected(type2);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // updates row
        int row = jTable1.getSelectedRow();
        boolean end = false;

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();

        if (row < 0)
        {
            JOptionPane.showMessageDialog(this,
                "No row is selected! Please select one row",
                "Select row",
                JOptionPane.ERROR_MESSAGE);
        } else
        {
            String name = jTextField1.getText();
            String id = jTextField2.getText();
            String position = jComboBox1.getSelectedItem().toString();
            String wage = jTextField4.getText();
            String hours = jTextField5.getText();
            String pass = jPasswordField1.getText();
            String type = "";

            for(int i=0; i<jTable1.getRowCount(); i++){
                if(name.equals(jTable1.getValueAt(i, 0))){
                    JOptionPane.showMessageDialog(this,
                        "Employee with same name is in the system.",
                        "Try Again.",
                        JOptionPane.ERROR_MESSAGE);
                    end = true;
                    return;
                } else if (id.equals(jTable1.getValueAt(i, 1))){
                    JOptionPane.showMessageDialog(this,
                        "Employee with same ID is in the system.",
                        "Try Again.",
                        JOptionPane.ERROR_MESSAGE);
                    end = true;
                    return;
                } else {
                    end = false;
                }
            }

            if(jCheckBox1.isSelected())
            {
                type = "Yes";
            } else if(jCheckBox2.isSelected())
            {
                type = "No";
            } else {
                JOptionPane.showMessageDialog(this,
                    "Please enter all fields.",
                    "Try Again.",
                    JOptionPane.ERROR_MESSAGE);
            }

            if (name.isEmpty() || id.isEmpty() || position.isEmpty() || wage.isEmpty() || hours.isEmpty())
            {
                JOptionPane.showMessageDialog(this,
                    "Please enter all fields.",
                    "Try Again.",
                    JOptionPane.ERROR_MESSAGE);
            } else if (position.equals("N/A")){
                JOptionPane.showMessageDialog(this,
                    "Position is not set.",
                    "Empty Position.",
                    JOptionPane.ERROR_MESSAGE);
            } else if (end == true){
            } else {
                model.setValueAt(name, jTable1.getSelectedRow(), 0);
                model.setValueAt(id, jTable1.getSelectedRow(), 1);
                model.setValueAt(position, jTable1.getSelectedRow(), 2);
                model.setValueAt(wage, jTable1.getSelectedRow(), 3);
                model.setValueAt(hours, jTable1.getSelectedRow(), 4);
                model.setValueAt(pass, jTable1.getSelectedRow(), 5);
                model.setValueAt(type, jTable1.getSelectedRow(), 6);

                model2.setValueAt(name, jTable1.getSelectedRow(), 0);
            }

            updateCSVEmployee();

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // add employee
        String name = jTextField1.getText();
        String id = jTextField2.getText();
        String position = jComboBox1.getSelectedItem().toString();
        String wage = jTextField4.getText();
        String hours = jTextField5.getText();
        String pass = jPasswordField1.getText();
        String type = "";
        boolean end = false;

        if(jCheckBox1.isSelected())
        {
            type = "Yes";
        } else if(jCheckBox2.isSelected())
        {
            type = "No";
        } else {
            JOptionPane.showMessageDialog(this,
                "Please enter all fields.",
                "Try Again.",
                JOptionPane.ERROR_MESSAGE);
        }

        for(int i=0; i<jTable1.getRowCount(); i++){
            if(name.equals(jTable1.getValueAt(i, 0))){
                JOptionPane.showMessageDialog(this,
                    "Employee with same name is in the system.",
                    "Try Again.",
                    JOptionPane.ERROR_MESSAGE);
                end = true;
                return;
            } else if (id.equals(jTable1.getValueAt(i, 1))){
                JOptionPane.showMessageDialog(this,
                    "Employee with same ID is in the system.",
                    "Try Again.",
                    JOptionPane.ERROR_MESSAGE);
                end = true;
                return;
            } else {
                end = false;
            }
        }

        if (name.isEmpty() || id.isEmpty() || position.isEmpty() || wage.isEmpty() || hours.isEmpty() || pass.isEmpty())
        {
            JOptionPane.showMessageDialog(this,
                "Please enter all fields.",
                "Try Again.",
                JOptionPane.ERROR_MESSAGE);
        } else if (position.equals("N/A")){
            JOptionPane.showMessageDialog(this,
                "Position is not set.",
                "Empty Position.",
                JOptionPane.ERROR_MESSAGE);
        } else if (end == true){
            return;
        } else {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
            model.addRow(new Object[]{name, id, position, wage, hours, pass, type});
            model2.addRow(new Object[]{name});
        }

        clear();

        createCSVSchedule(name);
        updateCSVEmployee();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // delete row
        int row = jTable1.getSelectedRow();

        if (row < 0)
        {
            JOptionPane.showMessageDialog(this,
                "No row is selected! Please selct one row",
                "Select row",
                JOptionPane.ERROR_MESSAGE);
        } else
        {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
            model.removeRow(row);
            jTable2.setRowSelectionInterval(row, row);
            model2.removeRow(row);

            updateCSVEmployee();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        clear();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model1);
        jTable1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(jTextField3.getText().trim()));
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        DefaultTableModel model5 = (DefaultTableModel) jTable5.getModel();

        String oDay = model5.getValueAt(jTable5.getSelectedRow(), 0).toString();
        String oOpen = model5.getValueAt(jTable5.getSelectedRow(), 1).toString();
        String oClose = model5.getValueAt(jTable5.getSelectedRow(), 2).toString();

        lblDay.setText(oDay);
        jComboBox6.setSelectedItem(oOpen);
        jComboBox7.setSelectedItem(oClose);
    }//GEN-LAST:event_jTable5MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        int row = jTable5.getSelectedRow();

        DefaultTableModel model = (DefaultTableModel) jTable5.getModel();

        if (row < 0)
        {
            JOptionPane.showMessageDialog(this,
                "No row is selected! Please select one row",
                "Select row",
                JOptionPane.ERROR_MESSAGE);
        } else
        {
            String day = lblDay.getText();
            String open = jComboBox6.getSelectedItem().toString();
            String close = jComboBox7.getSelectedItem().toString();

            String[] hmOpen = open.split(":");
            double startH = Integer.parseInt(hmOpen[0]);
            double startM = Integer.parseInt(hmOpen[1]);
            startM = startM/60;
            double startT = startH + startM;

            String[] hmClose = close.split(":");
            double endH = Integer.parseInt(hmClose[0]);
            double endM = Integer.parseInt(hmClose[1]);
            endM = endM/60;
            double endT = endH + endM;

            if (endT<=startT){
                JOptionPane.showMessageDialog(this,
                    "Close time can not be before open time.",
                    "Logical Error",
                    JOptionPane.ERROR_MESSAGE);
            } else {
                model.setValueAt(day, jTable5.getSelectedRow(), 0);
                model.setValueAt(open, jTable5.getSelectedRow(), 1);
                model.setValueAt(close, jTable5.getSelectedRow(), 2);

                updateCSVBusinessHours();
            }
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // Select Employee name in scheduler
        DefaultTableModel model3 = (DefaultTableModel) jTable3.getModel();
        DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();

        model3.setRowCount(0);
        jLabel18.setText("");
        String name = model2.getValueAt(jTable2.getSelectedRow(), 0).toString();
        readCSVEmpHours(name);
        populateOvertime();
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // Updates values in scheduler
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
        String name = model2.getValueAt(jTable2.getSelectedRow(), 0).toString();

        String oDay = model.getValueAt(jTable3.getSelectedRow(), 0).toString();
        String oStart = model.getValueAt(jTable3.getSelectedRow(), 1).toString();
        String oEnd = model.getValueAt(jTable3.getSelectedRow(), 2).toString();

        jComboBox2.setSelectedItem(oStart);
        jComboBox3.setSelectedItem(oEnd);
        jComboBox4.setSelectedItem(oDay);

        updateCSVEmpHours(name);
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // adds shift
        DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
        DefaultTableModel model3 = (DefaultTableModel) jTable3.getModel();
        String name = model2.getValueAt(jTable2.getSelectedRow(), 0).toString();
        String day = jComboBox4.getSelectedItem().toString();
        String start = jComboBox2.getSelectedItem().toString();
        String end = jComboBox3.getSelectedItem().toString();

        String[] hourMinStart = start.split(":");
        double startH = Integer.parseInt(hourMinStart[0]);
        double startM = Integer.parseInt(hourMinStart[1]);
        startM = startM/60;
        double startT = startH + startM;

        String[] hourMinEnd = end.split(":");
        double endH = Integer.parseInt(hourMinEnd[0]);
        double endM = Integer.parseInt(hourMinEnd[1]);
        endM = endM/60;
        double endT = endH + endM;

        for(int i=0; i<jTable3.getRowCount(); i++){
            if(day.equals(jTable3.getValueAt(i, 0))){
                String startValue = jTable3.getValueAt(i, 1).toString();
                String endValue = jTable3.getValueAt(i, 2).toString();

                String[] hourMinSt = startValue.split(":");
                double stH = Integer.parseInt(hourMinSt[0]);
                double stM = Integer.parseInt(hourMinSt[1]);
                stM = stM/60;
                double startVal = stH + stM;

                String[] hourMinE = endValue.split(":");
                double eH = Integer.parseInt(hourMinE[0]);
                double eM = Integer.parseInt(hourMinE[1]);
                eM = eM/60;
                double endVal = eH + eM;

                if(startT>=startVal&&startT<=endVal){
                    JOptionPane.showMessageDialog(this,
                        "New shift is overlapping with another shift.",
                        "Shift Overlap",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (endT>=startVal&&endT<=endVal){
                    JOptionPane.showMessageDialog(this,
                        "New shift is overlapping with another shift.",
                        "Shift Overlap",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (startT<=startVal&&endT>=endVal){
                    JOptionPane.showMessageDialog(this,
                        "New shift is overlapping with another shift.",
                        "Shift Overlap",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                } else{
                }
            } else {
            }
        }

        if (day.equals("N/A") || start.equals("N/A") || end.equals("N/A"))
        {
            JOptionPane.showMessageDialog(this,
                "Please enter all fields.",
                "Try Again.",
                JOptionPane.ERROR_MESSAGE);
        } else{
            double dHours = endT - startT;
            DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
            int row = jTable2.getSelectedRow();
            String role = model1.getValueAt(row, 2).toString();

            if (endT <= startT){
                JOptionPane.showMessageDialog(this,
                    "Start time must be before end time",
                    "Invalid shift times.",
                    JOptionPane.ERROR_MESSAGE);
            } else {
                model3.addRow(new Object[]{day, start, end, dHours, role});
            }
            populateOvertime();
            updateCSVEmpHours(name);
        }

        clearSch();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // edit shift
        int row = jTable3.getSelectedRow();

        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();

        if (row < 0)
        {
            JOptionPane.showMessageDialog(this,
                "No row is selected! Please select one row",
                "Select row",
                JOptionPane.ERROR_MESSAGE);
        } else
        {
            String day = jComboBox4.getSelectedItem().toString();
            String start = jComboBox2.getSelectedItem().toString();
            String end = jComboBox3.getSelectedItem().toString();

            String[] hourMinStart = start.split(":");
            double startH = Integer.parseInt(hourMinStart[0]);
            double startM = Integer.parseInt(hourMinStart[1]);
            startM = startM/60;
            double startT = startH + startM;

            String[] hourMinEnd = end.split(":");
            double endH = Integer.parseInt(hourMinEnd[0]);
            double endM = Integer.parseInt(hourMinEnd[1]);
            endM = endM/60;
            double endT = endH + endM;                    
            if (day.equals("N/A") || start.equals("N/A") || end.equals("N/A"))
            {
                JOptionPane.showMessageDialog(this,
                    "Please enter all fields.",
                    "Try Again.",
                    JOptionPane.ERROR_MESSAGE);
            } else{
                double dHours = endT - startT;
                DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
                String name = model2.getValueAt(jTable2.getSelectedRow(), 0).toString();
                String role = model.getValueAt(row, 4).toString();

                if (endT <= startT){
                    JOptionPane.showMessageDialog(this,
                        "Start time must be before end time",
                        "Invalid shift times.",
                        JOptionPane.ERROR_MESSAGE);
                } else {
                    model.setValueAt(day, jTable3.getSelectedRow(), 0);
                    model.setValueAt(start, jTable3.getSelectedRow(), 1);
                    model.setValueAt(end, jTable3.getSelectedRow(), 2);
                    model.setValueAt(dHours, jTable3.getSelectedRow(), 3);
                    model.setValueAt(role, jTable3.getSelectedRow(), 4);
                    populateOvertime();
                    updateCSVEmpHours(name);
                }
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // delete shift
        DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
        String name = model2.getValueAt(jTable2.getSelectedRow(), 0).toString();
        int row = jTable3.getSelectedRow();

        if (row < 0)
        {
            JOptionPane.showMessageDialog(this,
                "No row is selected! Please selct one row",
                "Select row",
                JOptionPane.ERROR_MESSAGE);
        } else
        {
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.removeRow(row);
            clearSch();
        }
        populateOvertime();
        updateCSVEmpHours(name);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        clearSch();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void employeesRecordsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeesRecordsTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_employeesRecordsTableMouseClicked

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bgEmployeeInfoTab;
    private javax.swing.JLabel bgEmployeeManagement;
    private javax.swing.JLabel bgEmployeeManagement1;
    private javax.swing.JLabel bgEmployeeManagement2;
    private javax.swing.JLabel bgEmployeeRecords;
    private javax.swing.JLabel bgEmployeeSaleMenu;
    private javax.swing.JLabel bgInputManagement;
    private javax.swing.JLabel bgInputRecords;
    private javax.swing.JLabel bgManagerSaleMenu;
    private javax.swing.JLabel bgPicture;
    private javax.swing.JLabel bgProductManagement;
    private javax.swing.JLabel bgProductRecords;
    private javax.swing.JLabel bgTransactionRecords;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnAddToCart1;
    private javax.swing.JButton btnClearCart;
    private javax.swing.JButton btnClearCart1;
    private javax.swing.JButton btnClearProdManagement;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteProduct;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnLogOut1;
    private javax.swing.JButton btnLogOut2;
    private javax.swing.JButton btnModifyProduct;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSale;
    private javax.swing.JButton btnSale1;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel businessDetailsTab;
    private javax.swing.JLabel cartLabel;
    private javax.swing.JTable cartTable;
    private javax.swing.JTable cartTableForManager;
    private javax.swing.JButton empSignIn;
    private javax.swing.JPanel employeeInfoTab;
    private javax.swing.JPanel employeeRecordsTab;
    private javax.swing.JPanel employeeSaleMenu;
    private javax.swing.JPanel employeesManagementTab;
    private javax.swing.JTable employeesRecordsTable;
    private javax.swing.JLabel incorrectPasswordLabel;
    private javax.swing.JLabel incorrectUserLabel;
    private javax.swing.JPanel inputManagementTab;
    private javax.swing.JPanel inputRecordsTab;
    private javax.swing.JTextField inventorySaleValueField;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField lastTransaction;
    private javax.swing.JLabel lastTransactionLabel;
    private javax.swing.JLabel lblDay;
    private javax.swing.JLabel lblDiscount;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblHoursLimit;
    private javax.swing.JLabel lblInputName;
    private javax.swing.JLabel lblLineItemID;
    private javax.swing.JLabel lblOvertime;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblScheduledHours;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblTaxRate;
    private javax.swing.JLabel lblTotalDiscountAmount1;
    private javax.swing.JLabel lblTotalGrossCost1;
    private javax.swing.JLabel lblTotalNetCost1;
    private javax.swing.JLabel lblTotalTaxAmount1;
    private javax.swing.JLabel lblUnitCost;
    private javax.swing.JLabel lblVendorCategory;
    private javax.swing.JLabel lblVendorName;
    private javax.swing.JButton manSignIn;
    private javax.swing.JTabbedPane managerMenuWindow;
    private javax.swing.JMenu menuInputReport;
    private javax.swing.JMenuItem menuItemAdd;
    private javax.swing.JMenuItem menuItemDelete;
    private javax.swing.JMenuItem menuItemExport;
    private javax.swing.JMenuItem menuItemImport;
    private javax.swing.JMenuItem menuItemReturntoMM;
    private javax.swing.JMenuItem menuItemSave;
    private javax.swing.JMenuItem menuItemUpdate;
    private javax.swing.JLabel menuTitleLabel;
    private javax.swing.JLabel notManagerLabel;
    private javax.swing.JPasswordField password;
    private javax.swing.JPanel pnlInput;
    private javax.swing.JLabel productLogLabel;
    private javax.swing.JPanel productManagementTab;
    private javax.swing.JTable productManagementTable;
    private javax.swing.JPanel productRecordsTab;
    private javax.swing.JTable productRecordsTable;
    private javax.swing.JTable productTable;
    private javax.swing.JTable productTableForManager;
    private javax.swing.JButton quitApplication;
    private javax.swing.JPanel recordsTab;
    private javax.swing.JTabbedPane recordsTabs;
    private javax.swing.JPanel saleMenuTab;
    private javax.swing.JPanel scheduleTab;
    private javax.swing.JScrollPane scrlpneInputReport;
    private javax.swing.JScrollPane scrlpneInputReport1;
    private javax.swing.JTextField searchFieldProducts;
    private javax.swing.JTextField selectedDiscountProdManagement;
    private javax.swing.JTextField selectedID;
    private javax.swing.JTextField selectedIDForManager;
    private javax.swing.JTextField selectedIDProdManagement;
    private javax.swing.JTextField selectedNameProdManagement;
    private javax.swing.JTextField selectedPriceProdManagement;
    private javax.swing.JSpinner selectedQuantity;
    private javax.swing.JSpinner selectedQuantityForManager;
    private javax.swing.JTextField selectedQuantityProdManagement;
    private javax.swing.JTextField selectedTaxRateProdManagement;
    private javax.swing.JTextField selectedTypeProdManagement;
    private javax.swing.JPanel signInWindow;
    private javax.swing.JTextField subTotalAmount;
    private javax.swing.JTextField subTotalAmountForManager;
    private javax.swing.JTextField taxAmount;
    private javax.swing.JTextField taxAmountForManager;
    private javax.swing.JTable tblInputReport;
    private javax.swing.JTable tblInputReportRecords;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JTextField totalAmount;
    private javax.swing.JTextField totalAmountForManager;
    private javax.swing.JTextField totalEmployees;
    private javax.swing.JTextField totalHoursWorked;
    private javax.swing.JTextField totalManagers;
    private javax.swing.JTextField totalProductsField;
    private javax.swing.JTextField totalRevenue;
    private javax.swing.JLabel totalRevenueLabel;
    private javax.swing.JTextField totalSubTotalRevenue;
    private javax.swing.JLabel totalSubTotalRevenueLabel;
    private javax.swing.JTextField totalTaxCollected;
    private javax.swing.JLabel totalTaxCollectedLabel;
    private javax.swing.JTextField totalUnitsField;
    private javax.swing.JTextField totalUnitsSold;
    private javax.swing.JLabel totalUnitsSoldLabel;
    private javax.swing.JTable transactionsRecordsTable;
    private javax.swing.JPanel transactionsTab;
    private javax.swing.JTextField txtDiscount;
    private javax.swing.JTextField txtInputName;
    private javax.swing.JTextField txtLineItemID;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTaxRate;
    private javax.swing.JTextField txtTotalDiscountAmount;
    private javax.swing.JTextField txtTotalGrossCost;
    private javax.swing.JTextField txtTotalNetCost;
    private javax.swing.JTextField txtTotalTaxAmount;
    private javax.swing.JTextField txtUnitCost;
    private javax.swing.JTextField txtVendorCategory;
    private javax.swing.JTextField txtVendorName;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables

    private String getTotalProducts() {
        String temp;
        int size;
        size = store1.getProductList().size();
        temp = size + "";
        return temp;
    }

    private String getTotalUnits() {
        String temp;
        int num = 0;
        
        
        Iterator<Integer> itr = store1.getProductList().keySet().iterator();
        
        while(itr.hasNext()){
            num = num + 
                store1.getProductList().get(itr.next()).getQuantity();
        }
        
        temp = num + "";
        return temp;
    }

    private String getInventorySaleValue() {
        String temp;
        int quantity;
        double price = 0, totalPrice = 0;
        
        
        Iterator<Integer> itr = store1.getProductList().keySet().iterator();
        
        while(itr.hasNext()){
            int x = itr.next();
            quantity = store1.getProductList().get(x).getQuantity();
            price = store1.getProductList().get(x).getPrice();
            totalPrice = totalPrice +
                    (price*quantity);
            
        } 
        
        temp = String.format("$%.2f", totalPrice);
        return temp;
    }
    
    public double getTotalGrossCost(double grossCost) {
        
        double sum = grossCost;
        for (int i = 0; i < tblInputReport.getRowCount(); i++) 
        {
            sum += (double) tblInputReport.getValueAt(i, 8);
        }
        
        return sum;
        
    }
    
    public double getTotalDiscountAmount(double discAmt) {
        
        double sum = discAmt;
        for (int i = 0; i < tblInputReport.getRowCount(); i++) 
        {
            sum += (double) tblInputReport.getValueAt(i, 9);
        }
        
        return sum;
        
    }
    
    public double getTotalTaxAmount(double taxAmt) {
        
        double sum = taxAmt;
        for (int i = 0; i < tblInputReport.getRowCount(); i++) 
        {
            sum += (double) tblInputReport.getValueAt(i, 10);
        }
        
        return sum;
        
    }
    
    public double getTotalNetCost(double netCost) {
        
        double sum = netCost;
        for (int i = 0; i < tblInputReport.getRowCount(); i++) 
        {
            sum += (double) tblInputReport.getValueAt(i, 11);
        }
        
        return sum;
        
    }
    
    private void addInput(int lineItemID, String vendorName, String vendorCategory, String input,
            double cost, int quantity, double discount, double taxRate) {
        
        Input newInput = new Input(lineItemID, vendorName, vendorCategory, input, 
                cost, quantity, discount, taxRate);
        inputList.addInput(newInput);
        
        //System.out.println(inputList.getInput(lineItemID));
        
    }
    
    //Employee Management Methods
    public void updateCSVEmployee(){
        try {

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        File file = new File(filenameEmployee);
            try (FileWriter csv = new FileWriter(file)) {
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        csv.write(model.getValueAt(i, j).toString() + ",");
                    }
                    csv.write("\n");
                }   }
        JOptionPane.showMessageDialog(this,
                    "CSV File Updated",
                    "CSV File",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
        }
    }
    
    private void readCSVEmployee()
    {
        try
        {
            DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
            DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
            DefaultTableModel modelRecords = (DefaultTableModel) employeesRecordsTable.getModel();
            
            File file = new File(filenameEmployee);
            FileReader fr = new FileReader(file);
            
            double totalHours = 0;
            int managerCount = 0;
            int employeeCount = 0;
            
            
            try (BufferedReader br = new BufferedReader(fr)) {
                String line = "";
                String[] tempArr;
                
               
                
                while ((line = br.readLine()) != null)
                {
                    tempArr = line.split(delimiter);
                    
                    String name = String.valueOf(tempArr[0].trim());
                    String id = String.valueOf(tempArr[1].trim());
                    String position = String.valueOf(tempArr[2].trim());
                    String wage = String.valueOf(tempArr[3].trim());
                    String hours = String.valueOf(tempArr[4].trim());
                    String pass = String.valueOf(tempArr[5].trim());
                    String type = String.valueOf(tempArr[6].trim());
                    
                    totalHours = totalHours + Double.parseDouble(hours);
                    
                    
                    if(type.contains("Yes")){
                        managerCount++;
                    } else {
                        employeeCount++;
                    }
                    
                    model1.addRow(new Object[]{name, id, position, wage, hours, pass, type});
                    model2.addRow(new Object[]{name});
                    modelRecords.addRow(new Object[]{name, id, position, wage, hours, pass, type});
                }
                
                totalHoursWorked.setText(totalHours + "");
                totalManagers.setText(managerCount + "");
                totalEmployees.setText(employeeCount + "");
                
            }
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(this, "Error reading the file");
        }
    }
    
    public void clear(){
        jTextField1.setText("");
        jTextField2.setText("");
        jComboBox1.setSelectedItem("N/A");
        jTextField4.setText("");
        jTextField5.setText("");
        jPasswordField1.setText("");
        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(true);
    }
    
    public void clearSch(){
        jComboBox2.setSelectedItem("N/A");
        jComboBox3.setSelectedItem("N/A");
        jComboBox4.setSelectedItem("N/A");
    }
    
    public void updateCSVEmpHours(String name){
        String csvName = name + ".csv";
        try {

        DefaultTableModel model3 = (DefaultTableModel) jTable3.getModel();
        File file = new File(csvName);
            try (FileWriter csv = new FileWriter(file)) {
                for (int i = 0; i < model3.getRowCount(); i++) {
                    for (int j = 0; j < model3.getColumnCount(); j++) {
                        csv.write(model3.getValueAt(i, j).toString() + ",");
                    }
                    csv.write("\n");
                }   }
        } catch (IOException e) {
        }
    }
    
    public void readCSVEmpHours(String name){
        String fileName = name + ".csv";
        try
        {
            DefaultTableModel model3 = (DefaultTableModel) jTable3.getModel();
            
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br;
            br = new BufferedReader(fr);
            
            String line = "";
            String[] tempArr;
            while ((line = br.readLine()) != null)
            {
                tempArr = line.split(delimiter);
                
                String day = String.valueOf(tempArr[0]);
                String start = String.valueOf(tempArr[1]);
                String end = String.valueOf(tempArr[2]);
                String dHours = String.valueOf(tempArr[3]);
                String role = String.valueOf(tempArr[4]);
                
                model3.addRow(new Object[]{day, start, end, dHours, role});
            }
            br.close();
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(this, "Error reading the file");
        }
    }
    
    public void createCSVSchedule(String name){
        String csvName = name + ".csv";
        try {
            File file = new File(csvName);
            FileWriter csv = new FileWriter(file);
       
            csv.close();
        } catch (IOException e) {
        }
    }    
    
    public void populateOvertime(){
        DefaultTableModel model3 = (DefaultTableModel) jTable3.getModel();
        DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
        
        double total = 0;
        for (int i = 0; i < model3.getRowCount(); i++) {
            String add = model3.getValueAt(i, 3).toString();
            double adder = Double.parseDouble(add);
            total = total + adder;
        }
        String tots = Double.toString(total);
        lblScheduledHours.setText(tots);
        int row = jTable2.getSelectedRow();
        String schHour = model1.getValueAt(row, 3).toString();
        lblHoursLimit.setText(schHour);
        double schHours = Double.parseDouble(schHour);
        double ovtD = total - schHours;
        String ovt = Double.toString(ovtD);
        if(ovtD>0){
            lblOvertime.setText(ovt);
            JOptionPane.showMessageDialog(this,
                    "Warning: Employee is overtime!",
                    "Overtime Warning.",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            lblOvertime.setText("N/A");
        }
    }
    
    public void readCSVBusinessHours(){
        try
        {
            DefaultTableModel model5 = (DefaultTableModel) jTable5.getModel();
            
            File file = new File(filenameBusiness);
            FileReader fr = new FileReader(file);
            try (BufferedReader br = new BufferedReader(fr)) {
                String line = "";
                String[] tempArr;
                while ((line = br.readLine()) != null)
                {
                    tempArr = line.split(delimiter);
                    
                    String day = String.valueOf(tempArr[0]);
                    String open = String.valueOf(tempArr[1]);
                    String close = String.valueOf(tempArr[2]);
                    
                    model5.addRow(new Object[]{day, open, close});
                }
            }
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(this, "Error reading the file");
        }
    }
    
    public void updateCSVBusinessHours(){
       
        try {

            DefaultTableModel model5 = (DefaultTableModel) jTable5.getModel();
            File file = new File(filenameBusiness);
            try (FileWriter csv = new FileWriter(file)) {
                for (int i = 0; i < model5.getRowCount(); i++) {
                    for (int j = 0; j < model5.getColumnCount(); j++) {
                        csv.write(model5.getValueAt(i, j).toString() + ",");
                    }
                    csv.write("\n");
                }
            }
            } catch (IOException e) {
        }
    }   
}
