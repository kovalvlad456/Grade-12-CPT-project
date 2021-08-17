/**
 * Inventory Business GUI
 * Inventory control system for a small business
 * Vlad Koval
 * ICS 4U1
 * June 18, 2021
 */
// importing different java classes into the program
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.math.RoundingMode;
import java.text.DecimalFormat;
public class InventoryBusinessGui implements ActionListener {
  JFrame frame, frameTwo;// variable for the two frames using JFrame
  JPanel contentPane, contentPaneTwo; //varaible for two contentpanes using JPanel
  static JButton closeButton; //creating a variable for the close button
  static JButton totalValueButton; //creating a variable for the total value button
  static JButton lowInventoryButton; //creating a variable for the low inventory button
  static JButton midInventoryButton;//creating a variable for the mid inventory button
  static JButton highInventoryButton; //creating a variable for the high inventory button
  static JButton updatePriceButton;//creating a variable for the update price button
  static JButton highestPButton; //creating a variable for the highest price button
  static JButton saveButton; //creating a variable for the save button
  static JButton[] addButton; //creating a variable array for the add button 
  static JButton[] deleteButton; //creating a variable array for the delete button
  static JButton[] valueButton;//creating a variable array for the value button
  static JLabel[] lblItem; //creating a variable array for the label item
  static JLabel[] lblItemQ; //creating a variable array for the label quantity
  static JLabel[] lblItemP; //creating a variable array for the label price
  static JLabel[] lblHeadings; //creating a variable array for the label headings
  static JTextField[] txtQChange; //making a textfield for text quantity change
  static int[] itemQ = new int[6]; // creating an array for all the quantities
  static double[] itemP = new double[6];// creating an array for all the prices
  String [] itemName = new String[6];// creating an array for all the item names
  String [] fields; // creating an array for all the fields
  static String name = " "; // string variable
  static int quantity = 0; //int variable
  static double price = 0; // double variable
  BufferedReader in = null; //creating the duffered reader variable
  String line = "Item, 10, 38.5"; // string variable
  File f = new File("Inventory2.txt");//setting up the data file
  
  /*
   * Constructor
   * pre: n/a
   * post: sets up all the GUI and try catch errors
   */
  public InventoryBusinessGui(){
    try{ //seeting up the try
      in = new BufferedReader(new FileReader(f)); // intializing in by calling the file reader
      System.out.println("File opening"); //output statement
    } catch (FileNotFoundException e) { //catch the file not found exception e
      System.out.println("Problem opening file"); //output statement
    }
    
    do {//do while loop
      try{ //setting up the try
        line = in.readLine();// intialzing in
      } catch (IOException e) { // catch the exception
        System.out.println("Problem reading data from file");//output statement
      }
     
      // read data from records - data all on 1 line, 6 records with 3 fields per record
      if (line!=null){
        for (int i=0;i<6;i++){  //for loop
          fields=line.split(",");
          if (i==0){ //if i the counter equals 0
            itemName[i]=fields[i]; //item name at i and item fields at i both equal each other
            itemQ[i]=Integer.parseInt(fields[i+1]); //intializing itemQ at i
            itemP[i]=Double.parseDouble(fields[i+2]); //intialzing itemp at i
          }else{ // the else 
            itemName[i]=fields[i+(i*2)]; //changing the the fields array to re-intialize the item name array
            itemQ[i]=Integer.parseInt(fields[(i+(i*2))+1]);
            itemP[i]=Double.parseDouble(fields[(i+(i*2))+2]);          
          }
        }
      }
    }while (line!=null);//while loop
    
    try { //try catch statement
      in.close();
      System.out.println("Closing File"); //output statement
    } catch (IOException e) {
      System.out.println("Problem Closing " + e); // output statement
    }
   
    String[] headings = {"Item", "Quantity", "Price", " ", " ", " ", " "}; //creating headings for the gui and storing them in a string array
    
    frame = new JFrame("Business Supply Inventory"); // creating the output frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // setting the default to exit on close
    
    contentPane = new JPanel(); // creating the content pane
    contentPane.setLayout(new GridLayout(itemName.length+2,6,10,5)); //setting up the layout based on the item name
    contentPane.setBorder(BorderFactory.createEmptyBorder(12,12,12,12)); // setting border with the 12 dimension
    
    addButton = new JButton[itemName.length]; //making an array of GUI's for add button
    deleteButton = new JButton[itemName.length]; //making an array of GUI's for delete button
    valueButton = new JButton[itemName.length];//making an array of GUI's for value button
    txtQChange = new JTextField[itemName.length]; //making an array of GUI's for quantity text change
    lblItem = new JLabel[itemName.length]; //making an array of GUI's for label items
    lblItemQ = new JLabel[itemName.length];//making an array of GUI's for label quantities
    lblItemP = new JLabel[itemName.length]; //making an array of GUI's for label prices
    lblHeadings = new JLabel[headings.length]; //making an array of GUI's for label headings
    
    //Setting the title for each button
    totalValueButton = new JButton("Total value"); 
    lowInventoryButton = new JButton("Low Inventory");
    midInventoryButton = new JButton("Mid Inventory");
    highInventoryButton = new JButton("High Inventory");
    saveButton = new JButton("Save");
    updatePriceButton = new JButton ("Update Price");
    highestPButton = new JButton("Highest Price");
    
    for (int i = 0; i < headings.length; i++){ //for loop that stops at headings length
      lblHeadings[i] = new JLabel(headings[i]); // label headings to be same size as headings at i
      contentPane.add(lblHeadings[i]); // adding label headings to content pane
    }
    for (int i = 0; i < itemName.length; i++){ //for loop that stops at items name length
      lblItem[i] = new JLabel(itemName[i]);  // label item to equal item name
      lblItemQ[i] = new JLabel(String.valueOf(itemQ[i])); // label quantity to equal item quantity
      lblItemP[i] = new JLabel(String.valueOf(itemP[i])); // label price to equal item price
      txtQChange[i] = new JTextField(); //sets up the textfeild at i
      addButton[i] = new JButton("Add"); //sets up all the button with the title add
      deleteButton[i] = new JButton("Delete"); //sets up all the button with the title delete
      valueButton[i] = new JButton("Value"); //sets up all the button with the title value
      addButton[i].addActionListener(this); //adding action listener to all the add buttons
      deleteButton[i].addActionListener(this); //adding action listener to all the delete buttons
      valueButton[i].addActionListener(this);//adding action listener to all the value buttons
      contentPane.add(lblItem[i]); // adding all the arrays at i to the main content pane
      contentPane.add(lblItemQ[i]);
      contentPane.add(lblItemP[i]);
      contentPane.add(txtQChange[i]);
      contentPane.add(valueButton[i]);
      contentPane.add(addButton[i]);
      contentPane.add(deleteButton[i]);
    }
    saveButton.addActionListener(this); //adding action listener to the save button
    updatePriceButton.addActionListener(this); //adding action listener to the update price button
    midInventoryButton.addActionListener(this); //adding action listener to the mid inventory button
    lowInventoryButton.addActionListener(this); //adding action listener to the low inventory button
    highInventoryButton.addActionListener(this); //adding action listener to the high inventory
    totalValueButton.addActionListener(this); //adding action listener to the total value button
    highestPButton.addActionListener(this); //adding action listener to the highest price button
    contentPane.add(lowInventoryButton); // adding all the buttons to the main content pane
    contentPane.add(midInventoryButton); // adding all the buttons to the main content pane, and seeting up the order their listed
    contentPane.add(highInventoryButton);
    contentPane.add(totalValueButton);
    contentPane.add(highestPButton);
    contentPane.add(saveButton);
    contentPane.add(updatePriceButton);
    
    frame.setContentPane(contentPane); // setting the frame to show the content pane
    frame.setSize(900,450); //setting the frame size
    frame.setVisible(true); // set the frame visible
    
    
  }
  
  
  /*
   * All the action performed by the user
   * pre: ActionEvent e
   * post: the user's actions
   */
  public void actionPerformed(ActionEvent e) {
    for (int i = 0; i < itemQ.length; i++){ // for loop that stops at itemQ length
        if (e.getSource()== addButton[i]){ // if users press's on of the add buttons
          addInventory(i); //calls the add inventory method
        }else if (e.getSource()== deleteButton[i]){ // else if users press's on of the delete buttons
          deleteInventory(i); //calls the delete inventory method
        }else if(e.getSource()==valueButton[i]){ // else if users press's on of the value buttons
          valueInventory(i); // calls the value inventory method
        }
    }
    if (e.getSource()==totalValueButton){ // if the user press on the total value button
      totalValueInventory(); // calls the total value inventory method
    }
    if(e.getSource()==lowInventoryButton){ // if the user press on the low inventory button
      lowerInventory(); // calls the lower inventory method
    }
    if(e.getSource()==highInventoryButton){ // if the user press on the high inventory button
      higherInventory(); // calls the hgiher inventory method
    }
    if (e.getSource()==updatePriceButton){ // if the user press on the update price button
      updatePriceInventory(); // calls the update inventory method
    }
      
    if (e.getSource()==saveButton){ // if the user press on the save button
      int a = JOptionPane.showConfirmDialog(null, "Confirm inventory overwrite?"); //gui output
      if(a==JOptionPane.YES_OPTION){ // user choose the yes option
        updateInventory(); // calls the update inventory method
      }
    }
    if(e.getSource()==highestPButton){ // if the user press on the highest price button
      highestPrice(); // calls the highest Price method
    } 
    if (e.getSource()==midInventoryButton){ // if the user press on the mid inventory button
      middleInventory(); // calls the middle inventory method
    }
    if (e.getSource()==saveButton){ // if the user press on the save button
      JOptionPane.showMessageDialog(null, "We have saved your changes"); // outut gui statement
    }
      
  }
  /*
   * add the users amount to the quantity
   * pre: int k
   * post: adds to the quantity
   * 
   */
  public void addInventory(int k){
    quantity = Integer.parseInt(txtQChange[k].getText()); //intializing quantity with the users input
    itemQ[k] = itemQ[k]+quantity; //adding quantity to the item quantity at k
    lblItemQ[k].setText(String.valueOf(itemQ[k])); // setting the text to show the new quantity
  }
  /*
   * Deletes the users input from the quantity
   * pre: int k
   * post: deletes from the quantity
   */
  public void deleteInventory(int k){
    if (itemQ[k] <= 0){ //if the quantity at k is 0 or less
      JOptionPane.showMessageDialog(frame, "Sorry but you cannot delete any inventory"); // output gui cannot be deleted
      return; // stops the method from running
    }
    quantity = Integer.parseInt(txtQChange[k].getText());//intializing quantity with the users input
    itemQ[k] = itemQ[k]-quantity; //deleting quantity to the item quantity at k
    lblItemQ[k].setText(String.valueOf(itemQ[k])); // setting the text to show the new quantity
  }
  /*
   * Gets the value of the item
   * pre: int j
   * post: gets value by multiplying quantity and price
   */
  public void valueInventory(int j){
    double value = 0; //variable for value
    double rounded = 0; // variable for rounded
    quantity = itemQ[j]; //quantity equals itemQ at j
    value = (quantity * itemP[j]); // value equals price multiplied by the price
    rounded = Math.round(value*100)/100; // rounding the value
    JOptionPane.showMessageDialog(frame, "The value is: " + value + "\n The rounded down value is: " + rounded); //outputting the final rounded value
  }
  /*
   * Gets the total value of all prices and quantities
   * pre: n/a
   * post: displays the total values
   */
  public void totalValueInventory(){
    double totalValue = 0; // variable for total value
    double rounded = 0; // variable for rounded
    for (int i = 0; i < itemQ.length; i++){ //for loop that stops at itemQ length
      totalValue += (itemQ[i] * itemP[i]); //multiplies all the quantities and prices
      rounded = Math.round(totalValue*100)/100; //rounds the total value
    }
    JOptionPane.showMessageDialog(frame, "The total value is: " + totalValue + "\n The rounded down total value is: " + rounded); //outputs all the total values with a gui
    
  }
  /*
   * Gets the low inventory
   * pre: n/a
   * post: displays all quantities under 10
   */
  public void lowerInventory(){ 
    for (int i = 0; i < itemQ.length; i++){ //for loop that stops at itemQ length
      if(itemQ[i] <= 10){ // if the quantity is under 10
        JOptionPane.showMessageDialog(frame, "The low inventory quantities are: " + itemName[i] + ": " + itemQ[i]); //outputs the item anme and item quantity
      }
    }
  }
  /*
   * Gets the mid inventory 
   * pre: n/a
   * post: displays all quantities over 10 and under 100
   */
  public void middleInventory(){
    for (int i = 0; i < itemQ.length; i++){ //for loop that stops at itemQ length
      if(itemQ[i] > 10 && itemQ[i] < 100){ // if the quantity is over 10 and under 100
        JOptionPane.showMessageDialog(frame, "The items with quantities between 10 and 100 are:  " + itemName[i] + ": " + itemQ[i]); //outputs the item anme and item quantity
      }
      }
    }
  /*
   * Gets the high inventory
   * pre: n/a
   * post: displays all quantities over 100
   */
  public void higherInventory(){
    for (int i = 0; i < itemQ.length; i++){ //for loop that stops at itemQ length
      if(itemQ[i] >= 100){ // if the quantity is over 10 and under 100
        JOptionPane.showMessageDialog(null, "The high inventory quantities are: " + itemName[i] + ": " +itemQ[i]); //outputs the item anme and item quantity
      }
      }
    }
  /*
   * gets the highest price
   * pre: none
   * post: displays the highest price
   */
  public void highestPrice(){
    CptProject c = new CptProject();// intialzing c for the cpt project class
    JOptionPane.showMessageDialog(null, c.highestPrice(itemP)); // calls the cpt project class to get the highest price
  }
  /*
   * updates the inventory based on what the user changes
   * pre: n/a
   * post: saves the changes to the data file
   */
  public void updateInventory() {
    PrintWriter out = null; //intializing out
    String line = ""; //string variable
    File source = new File("Inventory2.txt"); //intialzing source with a file
    try{ //try catch statement
      out = new PrintWriter(new BufferedWriter(new FileWriter(source, false)), true); //setting out to a new printer writer
      System.out.println("File Opening"); //output statement
    } catch (IOException e){ // catch exception e
      System.out.println("Problem opening file"); //output statement
    }
    for (int i=0; i<itemQ.length; i++){ //for loop that stops at itemQ length
      name = itemName[i]; //name equals name array at i
      quantity = itemQ[i]; //quantity equals quantity array at i
      price = itemP[i]; //price equals price array at i
      line = name + "," + quantity + "," + price + ","; // intializing line
      out.print(line);//prints line to out
    }
    out.close(); //closes out
  }
  /*
   * updates the price per unit
   * pre: n/a
   * post: updates the price that the user selects
   */
  public void updatePriceInventory(){
    if(txtQChange[1].getText().isEmpty()){
      JOptionPane.showMessageDialog(null, "Type in the new price you want for in the item in the input box"); //JOption output message gui
    }
    for(int k = 0;k<txtQChange.length;k++){ //for loop that stops at text quantity change
      if(!txtQChange[k].getText().isEmpty()){ //if text field is not empty
        price = Double.parseDouble(txtQChange[k].getText()); //price equals the users price update input
        itemP[k] = price;//item price at k equals price
        lblItemP[k].setText(String.valueOf(itemP[k])); //sets the label item price to the updated price
      }
    }
    updateInventory(); //calls the update inventory method to save the changes
  
          
  }
  public static void main(String[] args) { 
    new InventoryBusinessGui(); // runs the actual class
    
    
    
  }
  
  
}
