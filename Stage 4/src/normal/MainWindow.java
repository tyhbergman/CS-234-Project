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
    private final String filenameInput = "inputlist.csv";
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
            empProductLog.setModel(new javax.swing.table.DefaultTableModel(
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
        
        
        scrollPaneTranactionRecords.setViewportView(transactionsRecordsTable);
        
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
        frameTeaShop = new javax.swing.JPanel();
        signInWindow = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        lblUsername = new javax.swing.JLabel();
        lblPW = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        empSignIn = new javax.swing.JButton();
        manSignIn = new javax.swing.JButton();
        quitApplication = new javax.swing.JButton();
        incorrectPasswordLabel = new javax.swing.JLabel();
        incorrectUserLabel = new javax.swing.JLabel();
        notManagerLabel = new javax.swing.JLabel();
        bgPicture = new javax.swing.JLabel();
        employeeSaleMenu = new javax.swing.JPanel();
        panelEmployee = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        empCartScrollTable = new javax.swing.JScrollPane();
        cartTable = new javax.swing.JTable();
        lblEmpCartTable = new javax.swing.JLabel();
        lblEmpProductLogTitle = new javax.swing.JLabel();
        empScrollPaneProductLog = new javax.swing.JScrollPane();
        empProductLog = new javax.swing.JTable();
        lblEmpQuantity = new javax.swing.JLabel();
        lblEmpID = new javax.swing.JLabel();
        selectedID = new javax.swing.JTextField();
        selectedQuantity = new javax.swing.JSpinner();
        btnAddToCart = new javax.swing.JButton();
        btnClearCart = new javax.swing.JButton();
        subTotalAmount = new javax.swing.JTextField();
        taxAmount = new javax.swing.JTextField();
        totalAmount = new javax.swing.JTextField();
        btnSale = new javax.swing.JButton();
        lblEmpTax = new javax.swing.JLabel();
        lblEmpTotal = new javax.swing.JLabel();
        lblEmpSubTotal = new javax.swing.JLabel();
        btnLogOut = new javax.swing.JButton();
        bgEmployeeSaleMenu = new javax.swing.JLabel();
        managerMenuWindow = new javax.swing.JTabbedPane();
        saleMenuTab = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        menuTitleLabel = new javax.swing.JLabel();
        manScrollPanelCartTable = new javax.swing.JScrollPane();
        cartTableForManager = new javax.swing.JTable();
        cartLabel = new javax.swing.JLabel();
        productLogLabel = new javax.swing.JLabel();
        manScrollPaneProductLog = new javax.swing.JScrollPane();
        productTableForManager = new javax.swing.JTable();
        lblManQuantity = new javax.swing.JLabel();
        lblManID = new javax.swing.JLabel();
        selectedIDForManager = new javax.swing.JTextField();
        selectedQuantityForManager = new javax.swing.JSpinner();
        btnAddToCartManager = new javax.swing.JButton();
        btnClearCartManager = new javax.swing.JButton();
        subTotalAmountForManager = new javax.swing.JTextField();
        taxAmountForManager = new javax.swing.JTextField();
        totalAmountForManager = new javax.swing.JTextField();
        btnSaleManager = new javax.swing.JButton();
        lblManTax = new javax.swing.JLabel();
        lblManTotal = new javax.swing.JLabel();
        lblManSub = new javax.swing.JLabel();
        btnLogOutManager = new javax.swing.JButton();
        bgManagerSaleMenu = new javax.swing.JLabel();
        productManagementTab = new javax.swing.JPanel();
        scrollPaneProductManagement = new javax.swing.JScrollPane();
        productManagementTable = new javax.swing.JTable();
        lblProductManagementTitle = new javax.swing.JLabel();
        selectedIDProdManagement = new javax.swing.JTextField();
        selectedNameProdManagement = new javax.swing.JTextField();
        selectedPriceProdManagement = new javax.swing.JTextField();
        selectedQuantityProdManagement = new javax.swing.JTextField();
        selectedTaxRateProdManagement = new javax.swing.JTextField();
        selectedDiscountProdManagement = new javax.swing.JTextField();
        selectedTypeProdManagement = new javax.swing.JTextField();
        lblProductType = new javax.swing.JLabel();
        lblProductId = new javax.swing.JLabel();
        lblProductName = new javax.swing.JLabel();
        lblProductPrice = new javax.swing.JLabel();
        lblProductQuantity = new javax.swing.JLabel();
        lblProductTaxRate = new javax.swing.JLabel();
        lblProductDiscount = new javax.swing.JLabel();
        btnAddProduct = new javax.swing.JButton();
        btnModifyProduct = new javax.swing.JButton();
        btnDeleteProduct = new javax.swing.JButton();
        btnClearProdManagement = new javax.swing.JButton();
        searchFieldProducts = new javax.swing.JTextField();
        lblProductSearch = new javax.swing.JLabel();
        btnLogOutProductManagement = new javax.swing.JButton();
        bgProductManagement = new javax.swing.JLabel();
        employeesManagementTab = new javax.swing.JPanel();
        employeeManagementTabs = new javax.swing.JTabbedPane();
        employeeInfoTab = new javax.swing.JPanel();
        scrollPaneEmployeeManagement = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        javax.swing.JLabel lblEmployeeTableTitle = new javax.swing.JLabel();
        javax.swing.JButton btnEmployeeManagementUpdate = new javax.swing.JButton();
        javax.swing.JButton btnEmployeeManagementAdd = new javax.swing.JButton();
        javax.swing.JButton btnEmployeeManagementDelete = new javax.swing.JButton();
        javax.swing.JLabel lblEmpManagementName = new javax.swing.JLabel();
        javax.swing.JLabel lblEmpManagementId = new javax.swing.JLabel();
        javax.swing.JLabel lblEmpManagementPosition = new javax.swing.JLabel();
        javax.swing.JLabel lblEmpManagementWage = new javax.swing.JLabel();
        javax.swing.JLabel lblEmpManagementHours = new javax.swing.JLabel();
        javax.swing.JLabel lblEmpManagementPW = new javax.swing.JLabel();
        javax.swing.JLabel lblEmpManagementManagerAccess = new javax.swing.JLabel();
        empManagementName = new javax.swing.JTextField();
        empManagementId = new javax.swing.JTextField();
        empManagementWage = new javax.swing.JTextField();
        empManagementHours = new javax.swing.JTextField();
        empManagementPWField = new javax.swing.JPasswordField();
        javax.swing.JLabel lblEmpManagementInfoTitle = new javax.swing.JLabel();
        empManagementYesRadio = new javax.swing.JCheckBox();
        empManagementNoRadio = new javax.swing.JCheckBox();
        btnEmpManagementClear = new javax.swing.JButton();
        empManagementPostionBox = new javax.swing.JComboBox<>();
        lblEmpManagementSearch = new javax.swing.JLabel();
        empManagementSearchField = new javax.swing.JTextField();
        bgEmployeeInfoTab = new javax.swing.JLabel();
        businessDetailsTab = new javax.swing.JPanel();
        scrollPaneBusinessHours = new javax.swing.JScrollPane();
        busniessHoursTable = new javax.swing.JTable();
        lblBusinessDetailsTitle = new javax.swing.JLabel();
        lblBusinessDetailsHoursTitle = new javax.swing.JLabel();
        lblBusinessDetailsDay = new javax.swing.JLabel();
        lblBusinessDetailsOpenTime = new javax.swing.JLabel();
        lblBusinessDetailsCloseTime = new javax.swing.JLabel();
        businessDetailsOpenBox = new javax.swing.JComboBox<>();
        businessDetailsCloseBox = new javax.swing.JComboBox<>();
        btnBusinessHoursUpdate = new javax.swing.JButton();
        lblBusinessDetailsShowDay = new javax.swing.JLabel();
        bgTransactionRecords3 = new javax.swing.JLabel();
        scheduleTab = new javax.swing.JPanel();
        lblScheduleTitleEmp = new javax.swing.JLabel();
        scrollPaneScheduleEmployees = new javax.swing.JScrollPane();
        scheduleEmployeeNamesTable = new javax.swing.JTable();
        lblScheduleTitleSch = new javax.swing.JLabel();
        scrollPaneSchedule = new javax.swing.JScrollPane();
        scheduleEmployeesTable = new javax.swing.JTable();
        lblScheduleDay = new javax.swing.JLabel();
        lblScheduleEndTime = new javax.swing.JLabel();
        lblScheduleStartTime = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        lblScheduleScheduledHours = new javax.swing.JLabel();
        lblScheduleHoursLimit = new javax.swing.JLabel();
        lblShowScheduledHours = new javax.swing.JLabel();
        lblShowHoursLimit = new javax.swing.JLabel();
        lblScheduleOvertime = new javax.swing.JLabel();
        lblOvertime = new javax.swing.JLabel();
        bgTransactionRecords2 = new javax.swing.JLabel();
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
        scrollPaneTranactionRecords = new javax.swing.JScrollPane();
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
        scrollPaneEmployeeRecords = new javax.swing.JScrollPane();
        employeesRecordsTable = new javax.swing.JTable();
        totalEmployees = new javax.swing.JTextField();
        totalManagers = new javax.swing.JTextField();
        totalHoursWorked = new javax.swing.JTextField();
        lblTotalEmployees = new javax.swing.JLabel();
        lblTotalManagers = new javax.swing.JLabel();
        lblTotalHoursWorked = new javax.swing.JLabel();
        bgTransactionRecords1 = new javax.swing.JLabel();
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
        scrollPaneProductReport = new javax.swing.JScrollPane();
        productRecordsTable = new javax.swing.JTable();
        totalProductsField = new javax.swing.JTextField();
        lblTotalProducts = new javax.swing.JLabel();
        lblTotalUnits = new javax.swing.JLabel();
        totalUnitsField = new javax.swing.JTextField();
        inventorySaleValueField = new javax.swing.JTextField();
        lblInventorySaleValue = new javax.swing.JLabel();
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
        setTitle("Tea Shop Version 1.02");
        setMinimumSize(new java.awt.Dimension(1000, 620));
        setPreferredSize(new java.awt.Dimension(1000, 620));

        frameTeaShop.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        frameTeaShop.setMinimumSize(new java.awt.Dimension(1000, 620));
        frameTeaShop.setPreferredSize(new java.awt.Dimension(1000, 620));
        frameTeaShop.setLayout(new java.awt.CardLayout());

        signInWindow.setMinimumSize(new java.awt.Dimension(1000, 620));
        signInWindow.setPreferredSize(new java.awt.Dimension(1000, 620));
        signInWindow.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle.setFont(new java.awt.Font("Microsoft JhengHei", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Tea Shop Sign In");
        signInWindow.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1010, -1));
        signInWindow.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 70, -1));

        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUsername.setText("Username:");
        signInWindow.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, -1, -1));

        lblPW.setForeground(new java.awt.Color(255, 255, 255));
        lblPW.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPW.setText("Password:");
        signInWindow.add(lblPW, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, -1, -1));

        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        signInWindow.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, 70, -1));

        empSignIn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        empSignIn.setText("Employee Sign In");
        empSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empSignInActionPerformed(evt);
            }
        });
        signInWindow.add(empSignIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, -1, -1));

        manSignIn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manSignIn.setText("Manager Sign In");
        manSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manSignInActionPerformed(evt);
            }
        });
        signInWindow.add(manSignIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 220, -1, -1));

        quitApplication.setText("Quit Application");
        quitApplication.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitApplicationActionPerformed(evt);
            }
        });
        signInWindow.add(quitApplication, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 270, -1, -1));

        incorrectPasswordLabel.setForeground(new java.awt.Color(255, 255, 255));
        incorrectPasswordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        incorrectPasswordLabel.setText("Incorrect password. Try again.");
        incorrectPasswordLabel.setToolTipText("");
        signInWindow.add(incorrectPasswordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 1000, -1));

        incorrectUserLabel.setForeground(new java.awt.Color(255, 255, 255));
        incorrectUserLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        incorrectUserLabel.setText("User not in system. Try again.");
        incorrectUserLabel.setToolTipText("");
        signInWindow.add(incorrectUserLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 1000, -1));

        notManagerLabel.setForeground(new java.awt.Color(255, 255, 255));
        notManagerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        notManagerLabel.setText("User is not a manager. Try again.");
        notManagerLabel.setToolTipText("");
        signInWindow.add(notManagerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 1000, 20));

        bgPicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureDarkened.jpg"))); // NOI18N
        bgPicture.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bgPicture.setMaximumSize(new java.awt.Dimension(600, 450));
        bgPicture.setMinimumSize(new java.awt.Dimension(600, 450));
        bgPicture.setPreferredSize(new java.awt.Dimension(600, 450));
        signInWindow.add(bgPicture, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 620));

        frameTeaShop.add(signInWindow, "card7");

        employeeSaleMenu.setMinimumSize(new java.awt.Dimension(1000, 620));
        employeeSaleMenu.setPreferredSize(new java.awt.Dimension(1000, 620));
        employeeSaleMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelEmployee.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Employee Sale Menu");

        javax.swing.GroupLayout panelEmployeeLayout = new javax.swing.GroupLayout(panelEmployee);
        panelEmployee.setLayout(panelEmployeeLayout);
        panelEmployeeLayout.setHorizontalGroup(
            panelEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmployeeLayout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelEmployeeLayout.setVerticalGroup(
            panelEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmployeeLayout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        employeeSaleMenu.add(panelEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 30));

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
        empCartScrollTable.setViewportView(cartTable);

        employeeSaleMenu.add(empCartScrollTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, 320, 450));

        lblEmpCartTable.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        lblEmpCartTable.setForeground(new java.awt.Color(255, 255, 255));
        lblEmpCartTable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmpCartTable.setText("Cart");
        employeeSaleMenu.add(lblEmpCartTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 320, -1));

        lblEmpProductLogTitle.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        lblEmpProductLogTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblEmpProductLogTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmpProductLogTitle.setText("Product Log");
        employeeSaleMenu.add(lblEmpProductLogTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 500, -1));

        empProductLog.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        empProductLog.setModel(new javax.swing.table.DefaultTableModel(
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
        empProductLog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empProductLogMouseClicked(evt);
            }
        });
        empScrollPaneProductLog.setViewportView(empProductLog);
        empProductLog.setAutoCreateRowSorter(true);
        //Set first column (IDs) to be smaller than the rest
        empProductLog.getColumnModel().getColumn(0).setPreferredWidth(32);
        //Set third column (price) to be smaller than the rest
        empProductLog.getColumnModel().getColumn(2).setPreferredWidth(45);
        //Set fourth column (quantity) to be smaller than the rest
        empProductLog.getColumnModel().getColumn(3).setPreferredWidth(32);

        employeeSaleMenu.add(empScrollPaneProductLog, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 490, 560));

        lblEmpQuantity.setForeground(new java.awt.Color(255, 255, 255));
        lblEmpQuantity.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEmpQuantity.setText("Quantity:");
        employeeSaleMenu.add(lblEmpQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, -1, -1));

        lblEmpID.setForeground(new java.awt.Color(255, 255, 255));
        lblEmpID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEmpID.setText("ID:");
        employeeSaleMenu.add(lblEmpID, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, 50, -1));

        selectedID.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        selectedID.setPreferredSize(new java.awt.Dimension(90, 22));
        selectedID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedIDActionPerformed(evt);
            }
        });
        employeeSaleMenu.add(selectedID, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 50, 120, -1));

        selectedQuantity.setName(""); // NOI18N
        selectedQuantity.setPreferredSize(new java.awt.Dimension(90, 22));
        employeeSaleMenu.add(selectedQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, 120, -1));

        btnAddToCart.setText("Add to Cart");
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });
        employeeSaleMenu.add(btnAddToCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 110, 100, -1));

        btnClearCart.setText("Clear Cart");
        btnClearCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearCartActionPerformed(evt);
            }
        });
        employeeSaleMenu.add(btnClearCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, 90, -1));

        subTotalAmount.setEditable(false);
        subTotalAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        subTotalAmount.setText("$0.00");
        subTotalAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subTotalAmountActionPerformed(evt);
            }
        });
        employeeSaleMenu.add(subTotalAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 50, 70, -1));

        taxAmount.setEditable(false);
        taxAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        taxAmount.setText("$0.00");
        taxAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taxAmountActionPerformed(evt);
            }
        });
        employeeSaleMenu.add(taxAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 80, 70, -1));

        totalAmount.setEditable(false);
        totalAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalAmount.setText("$0.00");
        employeeSaleMenu.add(totalAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 110, 70, -1));

        btnSale.setText("Complete Sale");
        btnSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaleActionPerformed(evt);
            }
        });
        employeeSaleMenu.add(btnSale, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 160, 110, 30));

        lblEmpTax.setForeground(new java.awt.Color(255, 255, 255));
        lblEmpTax.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEmpTax.setText("Tax:");
        employeeSaleMenu.add(lblEmpTax, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 80, 40, -1));

        lblEmpTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblEmpTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEmpTotal.setText("Total:");
        employeeSaleMenu.add(lblEmpTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 110, 40, -1));

        lblEmpSubTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblEmpSubTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEmpSubTotal.setText("Sub:");
        employeeSaleMenu.add(lblEmpSubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 50, 40, -1));

        btnLogOut.setText("Log out");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });
        employeeSaleMenu.add(btnLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 540, -1, -1));

        bgEmployeeSaleMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N
        employeeSaleMenu.add(bgEmployeeSaleMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 620));

        frameTeaShop.add(employeeSaleMenu, "card3");

        managerMenuWindow.setForeground(new java.awt.Color(255, 255, 255));
        managerMenuWindow.setMinimumSize(new java.awt.Dimension(1000, 620));
        managerMenuWindow.setPreferredSize(new java.awt.Dimension(1000, 620));
        managerMenuWindow.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                managerMenuWindowFocusGained(evt);
            }
        });

        saleMenuTab.setMinimumSize(new java.awt.Dimension(1000, 620));
        saleMenuTab.setPreferredSize(new java.awt.Dimension(1000, 620));
        saleMenuTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titlePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        menuTitleLabel.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        menuTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuTitleLabel.setText("Manager Sale Menu");

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(menuTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
        manScrollPanelCartTable.setViewportView(cartTableForManager);

        saleMenuTab.add(manScrollPanelCartTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 160, 310, 390));

        cartLabel.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        cartLabel.setForeground(new java.awt.Color(255, 255, 255));
        cartLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cartLabel.setText("Cart");
        saleMenuTab.add(cartLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 310, -1));

        productLogLabel.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        productLogLabel.setForeground(new java.awt.Color(255, 255, 255));
        productLogLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productLogLabel.setText("Product Log");
        saleMenuTab.add(productLogLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 490, -1));

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
        manScrollPaneProductLog.setViewportView(productTableForManager);
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

        saleMenuTab.add(manScrollPaneProductLog, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 490, 500));

        lblManQuantity.setForeground(new java.awt.Color(255, 255, 255));
        lblManQuantity.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblManQuantity.setText("Quantity:");
        saleMenuTab.add(lblManQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 80, -1, -1));

        lblManID.setForeground(new java.awt.Color(255, 255, 255));
        lblManID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblManID.setText("ID:");
        saleMenuTab.add(lblManID, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, 50, -1));

        selectedIDForManager.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        selectedIDForManager.setPreferredSize(new java.awt.Dimension(90, 22));
        selectedIDForManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedIDForManagerActionPerformed(evt);
            }
        });
        saleMenuTab.add(selectedIDForManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 50, 120, -1));

        selectedQuantityForManager.setName(""); // NOI18N
        selectedQuantityForManager.setPreferredSize(new java.awt.Dimension(90, 22));
        saleMenuTab.add(selectedQuantityForManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, 120, -1));

        btnAddToCartManager.setText("Add to Cart");
        btnAddToCartManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartManagerActionPerformed(evt);
            }
        });
        saleMenuTab.add(btnAddToCartManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 110, 100, -1));

        btnClearCartManager.setText("Clear Cart");
        btnClearCartManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearCartManagerActionPerformed(evt);
            }
        });
        saleMenuTab.add(btnClearCartManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 110, 90, -1));

        subTotalAmountForManager.setEditable(false);
        subTotalAmountForManager.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        subTotalAmountForManager.setText("$0.00");
        subTotalAmountForManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subTotalAmountForManagerActionPerformed(evt);
            }
        });
        saleMenuTab.add(subTotalAmountForManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 50, 70, -1));

        taxAmountForManager.setEditable(false);
        taxAmountForManager.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        taxAmountForManager.setText("$0.00");
        taxAmountForManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taxAmountForManagerActionPerformed(evt);
            }
        });
        saleMenuTab.add(taxAmountForManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 80, 70, -1));

        totalAmountForManager.setEditable(false);
        totalAmountForManager.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalAmountForManager.setText("$0.00");
        saleMenuTab.add(totalAmountForManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 110, 70, -1));

        btnSaleManager.setText("Complete Sale");
        btnSaleManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaleManagerActionPerformed(evt);
            }
        });
        saleMenuTab.add(btnSaleManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 160, 110, 30));

        lblManTax.setForeground(new java.awt.Color(255, 255, 255));
        lblManTax.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblManTax.setText("Tax:");
        saleMenuTab.add(lblManTax, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 80, 40, -1));

        lblManTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblManTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblManTotal.setText("Total:");
        saleMenuTab.add(lblManTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 110, 40, -1));

        lblManSub.setForeground(new java.awt.Color(255, 255, 255));
        lblManSub.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblManSub.setText("Sub:");
        saleMenuTab.add(lblManSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 50, 40, -1));

        btnLogOutManager.setText("Log out");
        btnLogOutManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutManagerActionPerformed(evt);
            }
        });
        saleMenuTab.add(btnLogOutManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 520, 80, 20));

        bgManagerSaleMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N
        saleMenuTab.add(bgManagerSaleMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -34, 1000, 620));

        managerMenuWindow.addTab("Sale Menu", saleMenuTab);
        saleMenuTab.getAccessibleContext().setAccessibleName("Sale Menu");

        productManagementTab.setMinimumSize(new java.awt.Dimension(1000, 620));
        productManagementTab.setPreferredSize(new java.awt.Dimension(1000, 620));
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
        scrollPaneProductManagement.setViewportView(productManagementTable);
        scrollPaneProductManagement.setViewportView(productManagementTable);
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

        productManagementTab.add(scrollPaneProductManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 810, 500));

        lblProductManagementTitle.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        lblProductManagementTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblProductManagementTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProductManagementTitle.setText("Product Management");
        productManagementTab.add(lblProductManagementTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 170, -1));

        selectedIDProdManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedIDProdManagementActionPerformed(evt);
            }
        });
        productManagementTab.add(selectedIDProdManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 90, -1));
        productManagementTab.add(selectedNameProdManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 90, -1));
        productManagementTab.add(selectedPriceProdManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 90, -1));
        productManagementTab.add(selectedQuantityProdManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 90, -1));
        productManagementTab.add(selectedTaxRateProdManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 90, -1));
        productManagementTab.add(selectedDiscountProdManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 90, -1));

        selectedTypeProdManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedTypeProdManagementActionPerformed(evt);
            }
        });
        productManagementTab.add(selectedTypeProdManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 90, -1));

        lblProductType.setForeground(new java.awt.Color(255, 255, 255));
        lblProductType.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProductType.setText("Type:");
        productManagementTab.add(lblProductType, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 50, -1));

        lblProductId.setForeground(new java.awt.Color(255, 255, 255));
        lblProductId.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProductId.setText("ID:");
        productManagementTab.add(lblProductId, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 50, -1));

        lblProductName.setForeground(new java.awt.Color(255, 255, 255));
        lblProductName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProductName.setText("Name:");
        productManagementTab.add(lblProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 50, -1));

        lblProductPrice.setForeground(new java.awt.Color(255, 255, 255));
        lblProductPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProductPrice.setText("Price:");
        productManagementTab.add(lblProductPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 50, -1));

        lblProductQuantity.setForeground(new java.awt.Color(255, 255, 255));
        lblProductQuantity.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProductQuantity.setText("Quantity:");
        productManagementTab.add(lblProductQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 50, -1));

        lblProductTaxRate.setForeground(new java.awt.Color(255, 255, 255));
        lblProductTaxRate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProductTaxRate.setText("Tax Rate:");
        productManagementTab.add(lblProductTaxRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 50, -1));

        lblProductDiscount.setForeground(new java.awt.Color(255, 255, 255));
        lblProductDiscount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProductDiscount.setText("Discount:");
        productManagementTab.add(lblProductDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 50, -1));

        btnAddProduct.setText("Add Product");
        btnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductActionPerformed(evt);
            }
        });
        productManagementTab.add(btnAddProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 140, -1));

        btnModifyProduct.setText("Modify by ID");
        btnModifyProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyProductActionPerformed(evt);
            }
        });
        productManagementTab.add(btnModifyProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 140, -1));

        btnDeleteProduct.setText("Delete by ID");
        btnDeleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProductActionPerformed(evt);
            }
        });
        productManagementTab.add(btnDeleteProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 140, -1));

        btnClearProdManagement.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        btnClearProdManagement.setText("Clear All");
        btnClearProdManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearProdManagementActionPerformed(evt);
            }
        });
        productManagementTab.add(btnClearProdManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 100, 20));

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
        productManagementTab.add(searchFieldProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 520, 170, -1));

        lblProductSearch.setForeground(new java.awt.Color(255, 255, 255));
        lblProductSearch.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblProductSearch.setText("Search:");
        productManagementTab.add(lblProductSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 520, 50, -1));

        btnLogOutProductManagement.setText("Log out");
        btnLogOutProductManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutProductManagementActionPerformed(evt);
            }
        });
        productManagementTab.add(btnLogOutProductManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 520, 80, 20));

        bgProductManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N
        productManagementTab.add(bgProductManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -34, 1000, 620));

        managerMenuWindow.addTab("Product Manangement", productManagementTab);

        employeesManagementTab.setMinimumSize(new java.awt.Dimension(1000, 620));
        employeesManagementTab.setPreferredSize(new java.awt.Dimension(1000, 620));
        employeesManagementTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        employeeManagementTabs.setForeground(new java.awt.Color(255, 255, 255));
        employeeManagementTabs.setMinimumSize(new java.awt.Dimension(1000, 620));
        employeeManagementTabs.setOpaque(true);
        employeeManagementTabs.setPreferredSize(new java.awt.Dimension(1000, 620));

        employeeInfoTab.setMinimumSize(new java.awt.Dimension(1000, 620));
        employeeInfoTab.setPreferredSize(new java.awt.Dimension(1000, 620));
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
        scrollPaneEmployeeManagement.setViewportView(jTable1);

        employeeInfoTab.add(scrollPaneEmployeeManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 39, 730, 420));

        lblEmployeeTableTitle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEmployeeTableTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblEmployeeTableTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmployeeTableTitle.setText("List of Employees");
        employeeInfoTab.add(lblEmployeeTableTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, 730, -1));

        btnEmployeeManagementUpdate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEmployeeManagementUpdate.setText("Update");
        btnEmployeeManagementUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmployeeManagementUpdateActionPerformed(evt);
            }
        });
        employeeInfoTab.add(btnEmployeeManagementUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 320, 90, -1));

        btnEmployeeManagementAdd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEmployeeManagementAdd.setText("Add");
        btnEmployeeManagementAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmployeeManagementAddActionPerformed(evt);
            }
        });
        employeeInfoTab.add(btnEmployeeManagementAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 320, 90, -1));

        btnEmployeeManagementDelete.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEmployeeManagementDelete.setText("Delete");
        btnEmployeeManagementDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmployeeManagementDeleteActionPerformed(evt);
            }
        });
        employeeInfoTab.add(btnEmployeeManagementDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 360, 90, -1));

        lblEmpManagementName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEmpManagementName.setForeground(new java.awt.Color(255, 255, 255));
        lblEmpManagementName.setText("Name");
        employeeInfoTab.add(lblEmpManagementName, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 80, -1, -1));

        lblEmpManagementId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEmpManagementId.setForeground(new java.awt.Color(255, 255, 255));
        lblEmpManagementId.setText("Id Number");
        employeeInfoTab.add(lblEmpManagementId, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 110, -1, -1));

        lblEmpManagementPosition.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEmpManagementPosition.setForeground(new java.awt.Color(255, 255, 255));
        lblEmpManagementPosition.setText("Position");
        employeeInfoTab.add(lblEmpManagementPosition, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 150, -1, -1));

        lblEmpManagementWage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEmpManagementWage.setForeground(new java.awt.Color(255, 255, 255));
        lblEmpManagementWage.setText("Wage");
        employeeInfoTab.add(lblEmpManagementWage, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 180, 37, -1));

        lblEmpManagementHours.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEmpManagementHours.setForeground(new java.awt.Color(255, 255, 255));
        lblEmpManagementHours.setText("Hours");
        employeeInfoTab.add(lblEmpManagementHours, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 220, -1, -1));

        lblEmpManagementPW.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEmpManagementPW.setForeground(new java.awt.Color(255, 255, 255));
        lblEmpManagementPW.setText("Password");
        employeeInfoTab.add(lblEmpManagementPW, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 250, -1, -1));

        lblEmpManagementManagerAccess.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEmpManagementManagerAccess.setForeground(new java.awt.Color(255, 255, 255));
        lblEmpManagementManagerAccess.setText("Manager Access?");
        employeeInfoTab.add(lblEmpManagementManagerAccess, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 280, -1, -1));
        employeeInfoTab.add(empManagementName, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, 178, -1));
        employeeInfoTab.add(empManagementId, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 110, 148, -1));
        employeeInfoTab.add(empManagementWage, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 180, 177, -1));
        employeeInfoTab.add(empManagementHours, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 220, 177, -1));
        employeeInfoTab.add(empManagementPWField, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 250, 156, -1));

        lblEmpManagementInfoTitle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEmpManagementInfoTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblEmpManagementInfoTitle.setText("Information");
        employeeInfoTab.add(lblEmpManagementInfoTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 50, 103, -1));

        empManagementYesRadio.setForeground(new java.awt.Color(255, 255, 255));
        empManagementYesRadio.setText("Yes");
        employeeInfoTab.add(empManagementYesRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 280, -1, -1));

        empManagementNoRadio.setForeground(new java.awt.Color(255, 255, 255));
        empManagementNoRadio.setText("No");
        employeeInfoTab.add(empManagementNoRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 280, -1, -1));

        btnEmpManagementClear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEmpManagementClear.setText("Clear");
        btnEmpManagementClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpManagementClearActionPerformed(evt);
            }
        });
        employeeInfoTab.add(btnEmpManagementClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 360, 90, -1));

        empManagementPostionBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Admin", "Barista", "Manager", "Shift Lead" }));
        employeeInfoTab.add(empManagementPostionBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 150, 160, -1));

        lblEmpManagementSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEmpManagementSearch.setForeground(new java.awt.Color(255, 255, 255));
        lblEmpManagementSearch.setText("SEARCH:");
        employeeInfoTab.add(lblEmpManagementSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 470, -1, -1));

        empManagementSearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empManagementSearchFieldActionPerformed(evt);
            }
        });
        empManagementSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                empManagementSearchFieldKeyPressed(evt);
            }
        });
        employeeInfoTab.add(empManagementSearchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 470, 200, -1));

        bgEmployeeInfoTab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N
        employeeInfoTab.add(bgEmployeeInfoTab, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -65, 1000, 620));

        employeeManagementTabs.addTab("Employee Information", employeeInfoTab);

        businessDetailsTab.setMinimumSize(new java.awt.Dimension(1000, 620));
        businessDetailsTab.setPreferredSize(new java.awt.Dimension(1000, 620));
        businessDetailsTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        busniessHoursTable.setModel(new javax.swing.table.DefaultTableModel(
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
        busniessHoursTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                busniessHoursTableMouseClicked(evt);
            }
        });
        scrollPaneBusinessHours.setViewportView(busniessHoursTable);

        businessDetailsTab.add(scrollPaneBusinessHours, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 670, 170));

        lblBusinessDetailsTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblBusinessDetailsTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblBusinessDetailsTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBusinessDetailsTitle.setText("Business Hours");
        businessDetailsTab.add(lblBusinessDetailsTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 300, -1));

        lblBusinessDetailsHoursTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblBusinessDetailsHoursTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblBusinessDetailsHoursTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBusinessDetailsHoursTitle.setText("Update Hours");
        businessDetailsTab.add(lblBusinessDetailsHoursTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 130, 150, -1));

        lblBusinessDetailsDay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBusinessDetailsDay.setForeground(new java.awt.Color(255, 255, 255));
        lblBusinessDetailsDay.setText("Day");
        businessDetailsTab.add(lblBusinessDetailsDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 180, 30, -1));

        lblBusinessDetailsOpenTime.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBusinessDetailsOpenTime.setForeground(new java.awt.Color(255, 255, 255));
        lblBusinessDetailsOpenTime.setText("Open Time");
        businessDetailsTab.add(lblBusinessDetailsOpenTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 220, -1, -1));

        lblBusinessDetailsCloseTime.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBusinessDetailsCloseTime.setForeground(new java.awt.Color(255, 255, 255));
        lblBusinessDetailsCloseTime.setText("Close Time");
        businessDetailsTab.add(lblBusinessDetailsCloseTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 260, -1, -1));

        businessDetailsOpenBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        businessDetailsOpenBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "6:00", "6:30", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00" }));
        businessDetailsTab.add(businessDetailsOpenBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 220, -1, -1));

        businessDetailsCloseBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        businessDetailsCloseBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "6:00", "6:30", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00" }));
        businessDetailsTab.add(businessDetailsCloseBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 260, -1, -1));

        btnBusinessHoursUpdate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBusinessHoursUpdate.setText("Update");
        btnBusinessHoursUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusinessHoursUpdateActionPerformed(evt);
            }
        });
        businessDetailsTab.add(btnBusinessHoursUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 310, -1, -1));

        lblBusinessDetailsShowDay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBusinessDetailsShowDay.setForeground(new java.awt.Color(255, 255, 255));
        lblBusinessDetailsShowDay.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        businessDetailsTab.add(lblBusinessDetailsShowDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 180, 90, 20));

        bgTransactionRecords3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N
        businessDetailsTab.add(bgTransactionRecords3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -70, 1000, 630));

        employeeManagementTabs.addTab("Business Details", businessDetailsTab);

        scheduleTab.setMinimumSize(new java.awt.Dimension(1000, 620));
        scheduleTab.setPreferredSize(new java.awt.Dimension(1000, 620));
        scheduleTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblScheduleTitleEmp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblScheduleTitleEmp.setForeground(new java.awt.Color(255, 255, 255));
        lblScheduleTitleEmp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScheduleTitleEmp.setText("Employees");
        scheduleTab.add(lblScheduleTitleEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 210, -1));

        scheduleEmployeeNamesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name"
            }
        ));
        scheduleEmployeeNamesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scheduleEmployeeNamesTableMouseClicked(evt);
            }
        });
        scrollPaneScheduleEmployees.setViewportView(scheduleEmployeeNamesTable);

        scheduleTab.add(scrollPaneScheduleEmployees, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 51, 220, 460));

        lblScheduleTitleSch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblScheduleTitleSch.setForeground(new java.awt.Color(255, 255, 255));
        lblScheduleTitleSch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScheduleTitleSch.setText("Schedule");
        scheduleTab.add(lblScheduleTitleSch, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 20, 530, -1));

        scheduleEmployeesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Day", "Start Time", "End Time", "Daily Hour", "Role"
            }
        ));
        scheduleEmployeesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scheduleEmployeesTableMouseClicked(evt);
            }
        });
        scrollPaneSchedule.setViewportView(scheduleEmployeesTable);

        scheduleTab.add(scrollPaneSchedule, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 540, 460));

        lblScheduleDay.setForeground(new java.awt.Color(255, 255, 255));
        lblScheduleDay.setText("Day");
        scheduleTab.add(lblScheduleDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 60, -1, -1));

        lblScheduleEndTime.setForeground(new java.awt.Color(255, 255, 255));
        lblScheduleEndTime.setText("End Time");
        scheduleTab.add(lblScheduleEndTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 140, -1, -1));

        lblScheduleStartTime.setForeground(new java.awt.Color(255, 255, 255));
        lblScheduleStartTime.setText("Start Time");
        scheduleTab.add(lblScheduleStartTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 100, -1, -1));

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setText("Add");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        scheduleTab.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 170, -1, -1));

        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton5.setText("Update");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        scheduleTab.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 170, -1, -1));

        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton6.setText("Delete");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        scheduleTab.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 210, -1, -1));

        jButton8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton8.setText("Clear");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        scheduleTab.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 210, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "6:00", "6:30", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00" }));
        scheduleTab.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 100, -1, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "6:00", "6:30", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00" }));
        scheduleTab.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 140, -1, -1));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" }));
        scheduleTab.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 60, 99, -1));

        lblScheduleScheduledHours.setForeground(new java.awt.Color(255, 255, 255));
        lblScheduleScheduledHours.setText("Scheduled Hours:");
        scheduleTab.add(lblScheduleScheduledHours, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 250, -1, -1));

        lblScheduleHoursLimit.setForeground(new java.awt.Color(255, 255, 255));
        lblScheduleHoursLimit.setText("Hours Limit:");
        scheduleTab.add(lblScheduleHoursLimit, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 270, -1, -1));
        scheduleTab.add(lblShowScheduledHours, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 250, 43, -1));
        scheduleTab.add(lblShowHoursLimit, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 270, 43, -1));

        lblScheduleOvertime.setForeground(new java.awt.Color(255, 255, 255));
        lblScheduleOvertime.setText("Overtime:");
        scheduleTab.add(lblScheduleOvertime, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 290, -1, -1));
        scheduleTab.add(lblOvertime, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 290, 43, -1));

        bgTransactionRecords2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N
        scheduleTab.add(bgTransactionRecords2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -70, 1000, 630));

        employeeManagementTabs.addTab("Schedule", scheduleTab);

        employeesManagementTab.add(employeeManagementTabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 590));

        managerMenuWindow.addTab("Employee Management", employeesManagementTab);

        inputManagementTab.setMinimumSize(new java.awt.Dimension(1000, 620));
        inputManagementTab.setPreferredSize(new java.awt.Dimension(1000, 620));

        pnlInput.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHeader.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        lblHeader.setForeground(new java.awt.Color(255, 255, 255));
        lblHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeader.setText("Input Management");
        pnlInput.add(lblHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 180, 22));

        lblLineItemID.setForeground(new java.awt.Color(255, 255, 255));
        lblLineItemID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLineItemID.setText("ID:");
        pnlInput.add(lblLineItemID, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 76, -1));

        lblVendorName.setForeground(new java.awt.Color(255, 255, 255));
        lblVendorName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVendorName.setText("Vendor Name:");
        pnlInput.add(lblVendorName, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, -1));

        lblVendorCategory.setForeground(new java.awt.Color(255, 255, 255));
        lblVendorCategory.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVendorCategory.setText("Category:");
        pnlInput.add(lblVendorCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 76, -1));

        lblInputName.setForeground(new java.awt.Color(255, 255, 255));
        lblInputName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblInputName.setText("Input Name:");
        pnlInput.add(lblInputName, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 76, -1));

        lblUnitCost.setForeground(new java.awt.Color(255, 255, 255));
        lblUnitCost.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUnitCost.setText("Unit Cost $");
        pnlInput.add(lblUnitCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 76, -1));

        lblQuantity.setForeground(new java.awt.Color(255, 255, 255));
        lblQuantity.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblQuantity.setText("Quantity:");
        pnlInput.add(lblQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 76, -1));

        lblDiscount.setForeground(new java.awt.Color(255, 255, 255));
        lblDiscount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDiscount.setText("Discount %");
        pnlInput.add(lblDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 76, -1));

        lblTaxRate.setForeground(new java.awt.Color(255, 255, 255));
        lblTaxRate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTaxRate.setText("Tax Rate %");
        pnlInput.add(lblTaxRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 76, -1));

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
        pnlInput.add(txtLineItemID, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 90, -1));

        txtVendorName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtVendorNameKeyPressed(evt);
            }
        });
        pnlInput.add(txtVendorName, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 90, -1));

        txtVendorCategory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtVendorCategoryKeyPressed(evt);
            }
        });
        pnlInput.add(txtVendorCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 90, -1));

        txtInputName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtInputNameKeyPressed(evt);
            }
        });
        pnlInput.add(txtInputName, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 90, -1));

        txtUnitCost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUnitCostKeyPressed(evt);
            }
        });
        pnlInput.add(txtUnitCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 90, -1));

        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuantityKeyPressed(evt);
            }
        });
        pnlInput.add(txtQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 90, -1));

        txtDiscount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDiscountKeyPressed(evt);
            }
        });
        pnlInput.add(txtDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 90, -1));

        txtTaxRate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTaxRateKeyPressed(evt);
            }
        });
        pnlInput.add(txtTaxRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, 90, -1));

        btnAdd.setFont(new java.awt.Font("Mshtakan", 1, 14)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        pnlInput.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 70, -1));

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
        pnlInput.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, -1, -1));

        btnDelete.setFont(new java.awt.Font("Mshtakan", 1, 14)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        pnlInput.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 500, -1, -1));

        btnReset.setFont(new java.awt.Font("Mshtakan", 1, 14)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        pnlInput.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 500, 80, -1));

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

        pnlInput.add(scrlpneInputReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 800, 480));

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
        pnlInput.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 500, 205, -1));

        lblSearch.setFont(new java.awt.Font("Mshtakan", 0, 14)); // NOI18N
        lblSearch.setForeground(new java.awt.Color(255, 255, 255));
        lblSearch.setText("Search:");
        pnlInput.add(lblSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 500, 51, -1));

        bgInputManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N
        pnlInput.add(bgInputManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -34, 1000, 620));

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

        recordsTab.setMinimumSize(new java.awt.Dimension(1000, 620));
        recordsTab.setPreferredSize(new java.awt.Dimension(1000, 620));

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
        scrollPaneTranactionRecords.setViewportView(transactionsRecordsTable);
        if (transactionsRecordsTable.getColumnModel().getColumnCount() > 0) {
            transactionsRecordsTable.getColumnModel().getColumn(0).setMinWidth(40);
            transactionsRecordsTable.getColumnModel().getColumn(0).setPreferredWidth(40);
            transactionsRecordsTable.getColumnModel().getColumn(0).setMaxWidth(40);
            transactionsRecordsTable.getColumnModel().getColumn(4).setMinWidth(50);
            transactionsRecordsTable.getColumnModel().getColumn(4).setPreferredWidth(50);
            transactionsRecordsTable.getColumnModel().getColumn(4).setMaxWidth(50);
        }

        transactionsTab.add(scrollPaneTranactionRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 440));

        lastTransaction.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lastTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastTransactionActionPerformed(evt);
            }
        });
        transactionsTab.add(lastTransaction, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 470, 90, -1));

        totalRevenue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalRevenue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalRevenueActionPerformed(evt);
            }
        });
        transactionsTab.add(totalRevenue, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 470, 90, -1));

        totalSubTotalRevenue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        transactionsTab.add(totalSubTotalRevenue, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 470, 90, -1));

        totalTaxCollected.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        transactionsTab.add(totalTaxCollected, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 470, 100, -1));

        totalUnitsSold.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        transactionsTab.add(totalUnitsSold, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 470, 90, -1));

        lastTransactionLabel.setForeground(new java.awt.Color(255, 255, 255));
        lastTransactionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastTransactionLabel.setText("Last Transaction");
        transactionsTab.add(lastTransactionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 450, 110, -1));

        totalRevenueLabel.setForeground(new java.awt.Color(255, 255, 255));
        totalRevenueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalRevenueLabel.setText("Total Revenue");
        transactionsTab.add(totalRevenueLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 450, 90, -1));

        totalSubTotalRevenueLabel.setForeground(new java.awt.Color(255, 255, 255));
        totalSubTotalRevenueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalSubTotalRevenueLabel.setText("Sub Total Revenue");
        transactionsTab.add(totalSubTotalRevenueLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 450, 110, -1));

        totalTaxCollectedLabel.setForeground(new java.awt.Color(255, 255, 255));
        totalTaxCollectedLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalTaxCollectedLabel.setText("Total Tax Collected");
        transactionsTab.add(totalTaxCollectedLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 450, -1, -1));

        totalUnitsSoldLabel.setForeground(new java.awt.Color(255, 255, 255));
        totalUnitsSoldLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalUnitsSoldLabel.setText("Total Units Sold");
        transactionsTab.add(totalUnitsSoldLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 90, -1));

        bgTransactionRecords.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N
        transactionsTab.add(bgTransactionRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -70, 1000, 630));

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
        scrollPaneEmployeeRecords.setViewportView(employeesRecordsTable);

        employeeRecordsTab.add(scrollPaneEmployeeRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 440));

        totalEmployees.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        employeeRecordsTab.add(totalEmployees, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 470, 70, -1));

        totalManagers.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        employeeRecordsTab.add(totalManagers, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 470, 70, -1));

        totalHoursWorked.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        employeeRecordsTab.add(totalHoursWorked, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 470, 80, -1));

        lblTotalEmployees.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalEmployees.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalEmployees.setText("Total Employees");
        employeeRecordsTab.add(lblTotalEmployees, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 450, 110, -1));

        lblTotalManagers.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalManagers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalManagers.setText("Total Managers");
        employeeRecordsTab.add(lblTotalManagers, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 450, 90, -1));

        lblTotalHoursWorked.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalHoursWorked.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalHoursWorked.setText("Total Hours Worked");
        employeeRecordsTab.add(lblTotalHoursWorked, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 120, -1));

        bgTransactionRecords1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N
        employeeRecordsTab.add(bgTransactionRecords1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -70, 1000, 630));

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

        inputRecordsTab.add(scrlpneInputReport1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 440));

        lblTotalNetCost1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotalNetCost1.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalNetCost1.setText("Total Net $");
        inputRecordsTab.add(lblTotalNetCost1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 450, -1, -1));

        txtTotalGrossCost.setEditable(false);
        txtTotalGrossCost.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalGrossCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalGrossCostActionPerformed(evt);
            }
        });
        inputRecordsTab.add(txtTotalGrossCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 470, 90, -1));

        lblTotalGrossCost1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotalGrossCost1.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalGrossCost1.setText("Total Gross $");
        inputRecordsTab.add(lblTotalGrossCost1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 450, -1, -1));

        txtTotalNetCost.setEditable(false);
        txtTotalNetCost.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inputRecordsTab.add(txtTotalNetCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 470, 80, -1));

        txtTotalDiscountAmount.setEditable(false);
        txtTotalDiscountAmount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inputRecordsTab.add(txtTotalDiscountAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 470, 90, -1));

        lblTotalDiscountAmount1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotalDiscountAmount1.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalDiscountAmount1.setText("Total Discount $");
        inputRecordsTab.add(lblTotalDiscountAmount1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 450, -1, -1));

        txtTotalTaxAmount.setEditable(false);
        txtTotalTaxAmount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inputRecordsTab.add(txtTotalTaxAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 470, 80, -1));

        lblTotalTaxAmount1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotalTaxAmount1.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalTaxAmount1.setText("Total Tax $");
        inputRecordsTab.add(lblTotalTaxAmount1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 450, -1, -1));

        bgInputRecords.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N
        inputRecordsTab.add(bgInputRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -70, -1, 630));

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
        scrollPaneProductReport.setViewportView(productRecordsTable);

        productRecordsTab.add(scrollPaneProductReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 440));

        totalProductsField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        productRecordsTab.add(totalProductsField, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 470, 80, 30));

        lblTotalProducts.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalProducts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalProducts.setText("Total Products");
        productRecordsTab.add(lblTotalProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 450, 84, -1));

        lblTotalUnits.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalUnits.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalUnits.setText("Total Units");
        productRecordsTab.add(lblTotalUnits, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 450, 84, -1));

        totalUnitsField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        productRecordsTab.add(totalUnitsField, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 470, 84, 30));

        inventorySaleValueField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        productRecordsTab.add(inventorySaleValueField, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 470, 106, 30));

        lblInventorySaleValue.setForeground(new java.awt.Color(255, 255, 255));
        lblInventorySaleValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInventorySaleValue.setText("Inventory Sale Value");
        productRecordsTab.add(lblInventorySaleValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, -1, -1));

        bgProductRecords.setIcon(new javax.swing.ImageIcon(getClass().getResource("/normal/resources/bgPictureBlurred.jpg"))); // NOI18N
        productRecordsTab.add(bgProductRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -70, 1000, 630));

        recordsTabs.addTab("Product Report", productRecordsTab);

        javax.swing.GroupLayout recordsTabLayout = new javax.swing.GroupLayout(recordsTab);
        recordsTab.setLayout(recordsTabLayout);
        recordsTabLayout.setHorizontalGroup(
            recordsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(recordsTabs)
        );
        recordsTabLayout.setVerticalGroup(
            recordsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(recordsTabs)
        );

        managerMenuWindow.addTab("Reports", recordsTab);

        frameTeaShop.add(managerMenuWindow, "card4");
        managerMenuWindow.getAccessibleContext().setAccessibleName("Sale Menu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frameTeaShop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frameTeaShop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBounds(0, 0, 1016, 628);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogOutManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutManagerActionPerformed
        signInWindow.setVisible(true);
        managerMenuWindow.setVisible(false);
    }//GEN-LAST:event_btnLogOutManagerActionPerformed

    private void btnSaleManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaleManagerActionPerformed
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
    }//GEN-LAST:event_btnSaleManagerActionPerformed

    private void taxAmountForManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taxAmountForManagerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_taxAmountForManagerActionPerformed

    private void subTotalAmountForManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subTotalAmountForManagerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subTotalAmountForManagerActionPerformed

    private void btnClearCartManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearCartManagerActionPerformed
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
    }//GEN-LAST:event_btnClearCartManagerActionPerformed

    private void btnAddToCartManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartManagerActionPerformed
        
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
    }//GEN-LAST:event_btnAddToCartManagerActionPerformed

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

    private void empProductLogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empProductLogMouseClicked
        int index = empProductLog.getSelectedRow();
        String tempId = empProductLog.getValueAt(index,0).toString();
        selectedID.setText(tempId);
    }//GEN-LAST:event_empProductLogMouseClicked

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

    private void btnLogOutProductManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutProductManagementActionPerformed
        signInWindow.setVisible(true);
        managerMenuWindow.setVisible(false);
    }//GEN-LAST:event_btnLogOutProductManagementActionPerformed

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

        empManagementName.setText(oName);
        empManagementId.setText(oId);
        empManagementPostionBox.setSelectedItem(oPosition);
        empManagementWage.setText(oWage);
        empManagementHours.setText(oHours);
        empManagementPWField.setText(oPass);
        empManagementYesRadio.setSelected(type1);
        empManagementNoRadio.setSelected(type2);
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnEmployeeManagementUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmployeeManagementUpdateActionPerformed
        // updates row
        int row = jTable1.getSelectedRow();
        boolean end = false;

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        DefaultTableModel model2 = (DefaultTableModel) scheduleEmployeeNamesTable.getModel();

        if (row < 0)
        {
            JOptionPane.showMessageDialog(this,
                "No row is selected! Please select one row",
                "Select row",
                JOptionPane.ERROR_MESSAGE);
        } else
        {
            String name = empManagementName.getText();
            String id = empManagementId.getText();
            String position = empManagementPostionBox.getSelectedItem().toString();
            String wage = empManagementWage.getText();
            String hours = empManagementHours.getText();
            String pass = empManagementPWField.getText();
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

            if(empManagementYesRadio.isSelected())
            {
                type = "Yes";
            } else if(empManagementNoRadio.isSelected())
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
    }//GEN-LAST:event_btnEmployeeManagementUpdateActionPerformed

    private void btnEmployeeManagementAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmployeeManagementAddActionPerformed
        // add employee
        String name = empManagementName.getText();
        String id = empManagementId.getText();
        String position = empManagementPostionBox.getSelectedItem().toString();
        String wage = empManagementWage.getText();
        String hours = empManagementHours.getText();
        String pass = empManagementPWField.getText();
        String type = "";
        boolean end = false;

        if(empManagementYesRadio.isSelected())
        {
            type = "Yes";
        } else if(empManagementNoRadio.isSelected())
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
            DefaultTableModel model2 = (DefaultTableModel) scheduleEmployeeNamesTable.getModel();
            model.addRow(new Object[]{name, id, position, wage, hours, pass, type});
            model2.addRow(new Object[]{name});
        }

        clear();

        createCSVSchedule(name);
        updateCSVEmployee();
    }//GEN-LAST:event_btnEmployeeManagementAddActionPerformed

    private void btnEmployeeManagementDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmployeeManagementDeleteActionPerformed
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
            DefaultTableModel model2 = (DefaultTableModel) scheduleEmployeeNamesTable.getModel();
            model.removeRow(row);
            scheduleEmployeeNamesTable.setRowSelectionInterval(row, row);
            model2.removeRow(row);

            updateCSVEmployee();
        }
    }//GEN-LAST:event_btnEmployeeManagementDeleteActionPerformed

    private void btnEmpManagementClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpManagementClearActionPerformed
        clear();
    }//GEN-LAST:event_btnEmpManagementClearActionPerformed

    private void empManagementSearchFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_empManagementSearchFieldKeyPressed
        DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model1);
        jTable1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(empManagementSearchField.getText().trim()));
    }//GEN-LAST:event_empManagementSearchFieldKeyPressed

    private void busniessHoursTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_busniessHoursTableMouseClicked
        DefaultTableModel model5 = (DefaultTableModel) busniessHoursTable.getModel();

        String oDay = model5.getValueAt(busniessHoursTable.getSelectedRow(), 0).toString();
        String oOpen = model5.getValueAt(busniessHoursTable.getSelectedRow(), 1).toString();
        String oClose = model5.getValueAt(busniessHoursTable.getSelectedRow(), 2).toString();

        lblBusinessDetailsShowDay.setText(oDay);
        businessDetailsOpenBox.setSelectedItem(oOpen);
        businessDetailsCloseBox.setSelectedItem(oClose);
    }//GEN-LAST:event_busniessHoursTableMouseClicked

    private void btnBusinessHoursUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusinessHoursUpdateActionPerformed
        int row = busniessHoursTable.getSelectedRow();

        DefaultTableModel model = (DefaultTableModel) busniessHoursTable.getModel();

        if (row < 0)
        {
            JOptionPane.showMessageDialog(this,
                "No row is selected! Please select one row",
                "Select row",
                JOptionPane.ERROR_MESSAGE);
        } else
        {
            String day = lblBusinessDetailsShowDay.getText();
            String open = businessDetailsOpenBox.getSelectedItem().toString();
            String close = businessDetailsCloseBox.getSelectedItem().toString();

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
                model.setValueAt(day, busniessHoursTable.getSelectedRow(), 0);
                model.setValueAt(open, busniessHoursTable.getSelectedRow(), 1);
                model.setValueAt(close, busniessHoursTable.getSelectedRow(), 2);

                updateCSVBusinessHours();
            }
        }
    }//GEN-LAST:event_btnBusinessHoursUpdateActionPerformed

    private void scheduleEmployeeNamesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scheduleEmployeeNamesTableMouseClicked
        // Select Employee name in scheduler
        DefaultTableModel model3 = (DefaultTableModel) scheduleEmployeesTable.getModel();
        DefaultTableModel model2 = (DefaultTableModel) scheduleEmployeeNamesTable.getModel();

        model3.setRowCount(0);
        lblManTotal.setText("");
        String name = model2.getValueAt(scheduleEmployeeNamesTable.getSelectedRow(), 0).toString();
        readCSVEmpHours(name);
        populateOvertime();
    }//GEN-LAST:event_scheduleEmployeeNamesTableMouseClicked

    private void scheduleEmployeesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scheduleEmployeesTableMouseClicked
        // Updates values in scheduler
        DefaultTableModel model = (DefaultTableModel) scheduleEmployeesTable.getModel();
        DefaultTableModel model2 = (DefaultTableModel) scheduleEmployeeNamesTable.getModel();
        String name = model2.getValueAt(scheduleEmployeeNamesTable.getSelectedRow(), 0).toString();

        String oDay = model.getValueAt(scheduleEmployeesTable.getSelectedRow(), 0).toString();
        String oStart = model.getValueAt(scheduleEmployeesTable.getSelectedRow(), 1).toString();
        String oEnd = model.getValueAt(scheduleEmployeesTable.getSelectedRow(), 2).toString();

        jComboBox2.setSelectedItem(oStart);
        jComboBox3.setSelectedItem(oEnd);
        jComboBox4.setSelectedItem(oDay);

        updateCSVEmpHours(name);
    }//GEN-LAST:event_scheduleEmployeesTableMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // adds shift
        DefaultTableModel model2 = (DefaultTableModel) scheduleEmployeeNamesTable.getModel();
        DefaultTableModel model3 = (DefaultTableModel) scheduleEmployeesTable.getModel();
        String name = model2.getValueAt(scheduleEmployeeNamesTable.getSelectedRow(), 0).toString();
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

        for(int i=0; i<scheduleEmployeesTable.getRowCount(); i++){
            if(day.equals(scheduleEmployeesTable.getValueAt(i, 0))){
                String startValue = scheduleEmployeesTable.getValueAt(i, 1).toString();
                String endValue = scheduleEmployeesTable.getValueAt(i, 2).toString();

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
            int row = scheduleEmployeeNamesTable.getSelectedRow();
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
        int row = scheduleEmployeesTable.getSelectedRow();

        DefaultTableModel model = (DefaultTableModel) scheduleEmployeesTable.getModel();

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
                DefaultTableModel model2 = (DefaultTableModel) scheduleEmployeeNamesTable.getModel();
                String name = model2.getValueAt(scheduleEmployeeNamesTable.getSelectedRow(), 0).toString();
                String role = model.getValueAt(row, 4).toString();

                if (endT <= startT){
                    JOptionPane.showMessageDialog(this,
                        "Start time must be before end time",
                        "Invalid shift times.",
                        JOptionPane.ERROR_MESSAGE);
                } else {
                    model.setValueAt(day, scheduleEmployeesTable.getSelectedRow(), 0);
                    model.setValueAt(start, scheduleEmployeesTable.getSelectedRow(), 1);
                    model.setValueAt(end, scheduleEmployeesTable.getSelectedRow(), 2);
                    model.setValueAt(dHours, scheduleEmployeesTable.getSelectedRow(), 3);
                    model.setValueAt(role, scheduleEmployeesTable.getSelectedRow(), 4);
                    populateOvertime();
                    updateCSVEmpHours(name);
                }
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // delete shift
        DefaultTableModel model2 = (DefaultTableModel) scheduleEmployeeNamesTable.getModel();
        String name = model2.getValueAt(scheduleEmployeeNamesTable.getSelectedRow(), 0).toString();
        int row = scheduleEmployeesTable.getSelectedRow();

        if (row < 0)
        {
            JOptionPane.showMessageDialog(this,
                "No row is selected! Please selct one row",
                "Select row",
                JOptionPane.ERROR_MESSAGE);
        } else
        {
            DefaultTableModel model = (DefaultTableModel) scheduleEmployeesTable.getModel();
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

    private void empManagementSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empManagementSearchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empManagementSearchFieldActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bgEmployeeInfoTab;
    private javax.swing.JLabel bgEmployeeManagement;
    private javax.swing.JLabel bgEmployeeSaleMenu;
    private javax.swing.JLabel bgInputManagement;
    private javax.swing.JLabel bgInputRecords;
    private javax.swing.JLabel bgManagerSaleMenu;
    private javax.swing.JLabel bgPicture;
    private javax.swing.JLabel bgProductManagement;
    private javax.swing.JLabel bgProductRecords;
    private javax.swing.JLabel bgTransactionRecords;
    private javax.swing.JLabel bgTransactionRecords1;
    private javax.swing.JLabel bgTransactionRecords2;
    private javax.swing.JLabel bgTransactionRecords3;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnAddToCartManager;
    private javax.swing.JButton btnBusinessHoursUpdate;
    private javax.swing.JButton btnClearCart;
    private javax.swing.JButton btnClearCartManager;
    private javax.swing.JButton btnClearProdManagement;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteProduct;
    private javax.swing.JButton btnEmpManagementClear;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnLogOutManager;
    private javax.swing.JButton btnLogOutProductManagement;
    private javax.swing.JButton btnModifyProduct;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSale;
    private javax.swing.JButton btnSaleManager;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> businessDetailsCloseBox;
    private javax.swing.JComboBox<String> businessDetailsOpenBox;
    private javax.swing.JPanel businessDetailsTab;
    private javax.swing.JTable busniessHoursTable;
    private javax.swing.JLabel cartLabel;
    private javax.swing.JTable cartTable;
    private javax.swing.JTable cartTableForManager;
    private javax.swing.JScrollPane empCartScrollTable;
    private javax.swing.JTextField empManagementHours;
    private javax.swing.JTextField empManagementId;
    private javax.swing.JTextField empManagementName;
    private javax.swing.JCheckBox empManagementNoRadio;
    private javax.swing.JPasswordField empManagementPWField;
    private javax.swing.JComboBox<String> empManagementPostionBox;
    private javax.swing.JTextField empManagementSearchField;
    private javax.swing.JTextField empManagementWage;
    private javax.swing.JCheckBox empManagementYesRadio;
    private javax.swing.JTable empProductLog;
    private javax.swing.JScrollPane empScrollPaneProductLog;
    private javax.swing.JButton empSignIn;
    private javax.swing.JPanel employeeInfoTab;
    private javax.swing.JTabbedPane employeeManagementTabs;
    private javax.swing.JPanel employeeRecordsTab;
    private javax.swing.JPanel employeeSaleMenu;
    private javax.swing.JPanel employeesManagementTab;
    private javax.swing.JTable employeesRecordsTable;
    private javax.swing.JPanel frameTeaShop;
    private javax.swing.JLabel incorrectPasswordLabel;
    private javax.swing.JLabel incorrectUserLabel;
    private javax.swing.JPanel inputManagementTab;
    private javax.swing.JPanel inputRecordsTab;
    private javax.swing.JTextField inventorySaleValueField;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField lastTransaction;
    private javax.swing.JLabel lastTransactionLabel;
    private javax.swing.JLabel lblBusinessDetailsCloseTime;
    private javax.swing.JLabel lblBusinessDetailsDay;
    private javax.swing.JLabel lblBusinessDetailsHoursTitle;
    private javax.swing.JLabel lblBusinessDetailsOpenTime;
    private javax.swing.JLabel lblBusinessDetailsShowDay;
    private javax.swing.JLabel lblBusinessDetailsTitle;
    private javax.swing.JLabel lblDiscount;
    private javax.swing.JLabel lblEmpCartTable;
    private javax.swing.JLabel lblEmpID;
    private javax.swing.JLabel lblEmpManagementSearch;
    private javax.swing.JLabel lblEmpProductLogTitle;
    private javax.swing.JLabel lblEmpQuantity;
    private javax.swing.JLabel lblEmpSubTotal;
    private javax.swing.JLabel lblEmpTax;
    private javax.swing.JLabel lblEmpTotal;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblInputName;
    private javax.swing.JLabel lblInventorySaleValue;
    private javax.swing.JLabel lblLineItemID;
    private javax.swing.JLabel lblManID;
    private javax.swing.JLabel lblManQuantity;
    private javax.swing.JLabel lblManSub;
    private javax.swing.JLabel lblManTax;
    private javax.swing.JLabel lblManTotal;
    private javax.swing.JLabel lblOvertime;
    private javax.swing.JLabel lblPW;
    private javax.swing.JLabel lblProductDiscount;
    private javax.swing.JLabel lblProductId;
    private javax.swing.JLabel lblProductManagementTitle;
    private javax.swing.JLabel lblProductName;
    private javax.swing.JLabel lblProductPrice;
    private javax.swing.JLabel lblProductQuantity;
    private javax.swing.JLabel lblProductSearch;
    private javax.swing.JLabel lblProductTaxRate;
    private javax.swing.JLabel lblProductType;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblScheduleDay;
    private javax.swing.JLabel lblScheduleEndTime;
    private javax.swing.JLabel lblScheduleHoursLimit;
    private javax.swing.JLabel lblScheduleOvertime;
    private javax.swing.JLabel lblScheduleScheduledHours;
    private javax.swing.JLabel lblScheduleStartTime;
    private javax.swing.JLabel lblScheduleTitleEmp;
    private javax.swing.JLabel lblScheduleTitleSch;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblShowHoursLimit;
    private javax.swing.JLabel lblShowScheduledHours;
    private javax.swing.JLabel lblTaxRate;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotalDiscountAmount1;
    private javax.swing.JLabel lblTotalEmployees;
    private javax.swing.JLabel lblTotalGrossCost1;
    private javax.swing.JLabel lblTotalHoursWorked;
    private javax.swing.JLabel lblTotalManagers;
    private javax.swing.JLabel lblTotalNetCost1;
    private javax.swing.JLabel lblTotalProducts;
    private javax.swing.JLabel lblTotalTaxAmount1;
    private javax.swing.JLabel lblTotalUnits;
    private javax.swing.JLabel lblUnitCost;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblVendorCategory;
    private javax.swing.JLabel lblVendorName;
    private javax.swing.JScrollPane manScrollPaneProductLog;
    private javax.swing.JScrollPane manScrollPanelCartTable;
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
    private javax.swing.JPanel panelEmployee;
    private javax.swing.JPasswordField password;
    private javax.swing.JPanel pnlInput;
    private javax.swing.JLabel productLogLabel;
    private javax.swing.JPanel productManagementTab;
    private javax.swing.JTable productManagementTable;
    private javax.swing.JPanel productRecordsTab;
    private javax.swing.JTable productRecordsTable;
    private javax.swing.JTable productTableForManager;
    private javax.swing.JButton quitApplication;
    private javax.swing.JPanel recordsTab;
    private javax.swing.JTabbedPane recordsTabs;
    private javax.swing.JPanel saleMenuTab;
    private javax.swing.JTable scheduleEmployeeNamesTable;
    private javax.swing.JTable scheduleEmployeesTable;
    private javax.swing.JPanel scheduleTab;
    private javax.swing.JScrollPane scrlpneInputReport;
    private javax.swing.JScrollPane scrlpneInputReport1;
    private javax.swing.JScrollPane scrollPaneBusinessHours;
    private javax.swing.JScrollPane scrollPaneEmployeeManagement;
    private javax.swing.JScrollPane scrollPaneEmployeeRecords;
    private javax.swing.JScrollPane scrollPaneProductManagement;
    private javax.swing.JScrollPane scrollPaneProductReport;
    private javax.swing.JScrollPane scrollPaneSchedule;
    private javax.swing.JScrollPane scrollPaneScheduleEmployees;
    private javax.swing.JScrollPane scrollPaneTranactionRecords;
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
            DefaultTableModel model2 = (DefaultTableModel) scheduleEmployeeNamesTable.getModel();
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
        empManagementName.setText("");
        empManagementId.setText("");
        empManagementPostionBox.setSelectedItem("N/A");
        empManagementWage.setText("");
        empManagementHours.setText("");
        empManagementPWField.setText("");
        empManagementYesRadio.setSelected(false);
        empManagementNoRadio.setSelected(true);
    }
    
    public void clearSch(){
        jComboBox2.setSelectedItem("N/A");
        jComboBox3.setSelectedItem("N/A");
        jComboBox4.setSelectedItem("N/A");
    }
    
    public void updateCSVEmpHours(String name){
        String csvName = name + ".csv";
        try {

        DefaultTableModel model3 = (DefaultTableModel) scheduleEmployeesTable.getModel();
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
            DefaultTableModel model3 = (DefaultTableModel) scheduleEmployeesTable.getModel();
            
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
        DefaultTableModel model3 = (DefaultTableModel) scheduleEmployeesTable.getModel();
        DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
        
        double total = 0;
        for (int i = 0; i < model3.getRowCount(); i++) {
            String add = model3.getValueAt(i, 3).toString();
            double adder = Double.parseDouble(add);
            total = total + adder;
        }
        String tots = Double.toString(total);
        lblShowScheduledHours.setText(tots);
        int row = scheduleEmployeeNamesTable.getSelectedRow();
        String schHour = model1.getValueAt(row, 3).toString();
        lblShowHoursLimit.setText(schHour);
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
            DefaultTableModel model5 = (DefaultTableModel) busniessHoursTable.getModel();
            
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

            DefaultTableModel model5 = (DefaultTableModel) busniessHoursTable.getModel();
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
