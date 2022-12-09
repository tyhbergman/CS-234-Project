package normal;


import java.util.HashMap;
import java.io.*;
import javax.swing.JOptionPane;
import normal.Input;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nickanderson
 */
public class InputList {
    
    private HashMap<Integer, Input> inputs;
    private static final String filename = "inputsdummy.csv";
    private static final String delimiter = ",";

    public InputList() {
        
        inputs = new HashMap<>();
        
    }
    
    public void addInput(Input anInput) {
        
        int id = anInput.getLineItemID();
        inputs.put(id, anInput);
        
    }
   
    
    public void removeInput(int id) {
        
        inputs.remove(id);
        
    }
    
    
    public Input getInput(int id) {
        
        return inputs.get(id);
        
    }
    
    public HashMap<Integer, Input> getInputs() {
        
        return inputs;
        
    }
    
}
