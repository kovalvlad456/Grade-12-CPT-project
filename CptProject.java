/**
 * Cpt project
 * Cpt to prompt the user with highest price
 * Vlad Koval
 * ICS 4U1
 * June 18, 2021
 */

//importing classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Arrays;
public class CptProject {
  double total; //variable for total
  
  /*
   * Constructor
   * pre: n/a
   * post: intializes total to 0
   */
  public CptProject(){
    double total = 0; // intializes total to 0
  }
  
  /*
   * Gets the highest price from price array
   * pre: price array
   * post: sends the highest price from the array to the caller
   */
  public double highestPrice (double[] priceArray){
    double a = priceArray[0]; //intializes a as the element at 0
    for (int x = 1;x < priceArray.length;x++){ //for loop that stops at priceArray length
      if(priceArray[x] > a){ //if price array at x is greater than a
        a = priceArray[x]; // a equals price array at x
      }
    }
    return a; //returns a
  }
          
       
  }
  
  

