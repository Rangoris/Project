/*
   Sean Geserick
   SGeseric@masonlive.gmu.edu
*/

import javax.swing.JOptionPane;

class Implementation{
   public static void main(String args[]){
      
    String prompt = "Main Menu\n" +
                    "1 Create a new vending machine\n" + 
                    "2 Remove a vending machine\n" +
                    "3 Check a vending machines stock\n" +
                    "4 Restock a vending machine\n" +
                    "5 Withdraw money from a vending machine\n" +
                    "6 View a report\n" +
                    "7 Buy a soda\n" +
                    "8 exit\n";
      
      boolean quit = false;
      
      do{       
         String input = JOptionPane.showInputDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.QUESTION_MESSAGE);
      try{
         switch (input) {
            case "1":
              createVendingMachine();
              break;
            case "2":
              removeVendingMachine();
              break;
            case "3":
               checkVendingMachineStock();
               break;
            case "4":
               restockVendingMachine();
               break;
            case "5":
               withdrawVendingMachine();
               break;
            case "6":
               report();
               break;
            case "7":
               buySoda();
               break;
            case "8":
               print("Thanks!");
               quit = true;
               break;
            default:
               JOptionPane.showMessageDialog(null, "Your input must be 1, 2, 3, 4, 5, 6, 7, or 8"
               ,"IT 306 Project Phase 2",JOptionPane.ERROR_MESSAGE);
         }
      }catch(NullPointerException e) {
         print("Thanks!");
         break;
      }
      }while(!quit);
   
   }//end of main
   
   /*
      Asks the user to enter the name of a vending machine, the name of a soda, and the price of that soda
      
   */
   public static void createVendingMachine(){
      String prompt = "Enter the name of your vending machine";
      String machineName = getInputString(prompt);
      
      VendingMachine aVM = new VendingMachine(machineName);
      
      int count = 0;
      
      try{
      while( count < VendingMachine.MAX_SODA_TYPES ){
         prompt = "Enter the name of a soda";
         String sodaName = getInputString(prompt);
         
         prompt = "Enter the price of " + sodaName;
         double sodaPrice = getInputDouble(prompt);
         
         if( aVM.addSoda( sodaName, sodaPrice, 40 )){
            count++;
         }
      }
      }catch(NullPointerException e){
      
      }
      
   }
    
   /*
   
   */
   public static void removeVendingMachine(){
      String prompt = "Enter the name of the soda machine you would like to remove";
      String machineToRemove = getInputString(prompt);
      
      VendingMachine.removeMachine(machineToRemove);
   }
   
   /*
   
   */
   public static void checkVendingMachineStock(){
      String prompt = "Enter the name of the soda machine you would like to check the stock of";
      String machineName = getInputString(prompt);
      
      VendingMachine temp = VendingMachine.getVendingMachine(machineName);
      
      print( temp.toString() );
   }
   
   /*
   
   */
   public static void restockVendingMachine(){
      String prompt = "Enter the name of the soda machine you would like to restock";
      String machineName = getInputString(prompt);
      
      VendingMachine temp = VendingMachine.getVendingMachine(machineName);
      
      temp.reStock(0);
      temp.reStock(1);
      temp.reStock(2);
      temp.reStock(3);
      temp.reStock(4);
      temp.reStock(5);
      temp.reStock(6);
      temp.reStock(7);
      temp.reStock(8);
      temp.reStock(9);
      temp.reStock(10);
   }
    
   /*
   
   */
   public static void withdrawVendingMachine(){
      String prompt = "Enter the name of the soda machine you would like to withdraw money from";
      String machineName = getInputString(prompt);
      
      VendingMachine temp = VendingMachine.getVendingMachine(machineName);
      
      double balance = temp.getBal();
      prompt = "You can withfraw an amount between 0 and " + balance;
      double withdraw = getInputDouble(prompt);
      
      temp.withDraw( withdraw );
      
      prompt = "You withdrew " + balance + " from " + machineName;
      print( prompt );
   }
   
   /*
   calls toString() on 
   */
   public static void report(){
      LinkedList machineList = VendingMachine.getMachines();
      print( machineList.toString() );
   }
    
   /*
   Asks the user for a VendingMachine name, finds that machine, asks the user which of that machines sodas they want to purchase
   if they try to purchase an invalid soda number an error is displayed to them
   if they enter a valid soda number the soda is sold and a message is shown to the user so they know it was sold
   */
   public static void buySoda(){
      String prompt = "Enter the name of the soda machine you would like to buy a soda from";
      String machineName = getInputString(prompt);
      
      VendingMachine temp = VendingMachine.getVendingMachine(machineName);
      
      prompt =  "Name: " + machineName + "\n" + "Enter the number of the soda you wish to purchase\n" +
             "Name              Price\n" + 
             "1 " + temp.getSodaName(1) + "                  " + temp.getSodaPrice(1) + "\n" +
             "2 " + temp.getSodaName(2) + "                  " + temp.getSodaPrice(2) + "\n" +
             "3 " + temp.getSodaName(3) + "                  " + temp.getSodaPrice(3) + "\n" +
             "4 " + temp.getSodaName(4) + "                  " + temp.getSodaPrice(4) + "\n" +
             "5 " + temp.getSodaName(5) + "                  " + temp.getSodaPrice(5) + "\n" +
             "6 " + temp.getSodaName(6) + "                  " + temp.getSodaPrice(6) + "\n" +
             "7 " + temp.getSodaName(7) + "                  " + temp.getSodaPrice(7) + "\n" +
             "8 " + temp.getSodaName(8) + "                  " + temp.getSodaPrice(8) + "\n" +
             "9 " + temp.getSodaName(9) + "                  " + temp.getSodaPrice(9) + "\n" +
             "10 " + temp.getSodaName(10) + "                " + temp.getSodaPrice(10) + "\n";
             
      int sodaChoice = getInputInteger(prompt);
      
      if( sodaChoice < 0 || sodaChoice > 9){
         JOptionPane.showMessageDialog(null, "Your input must be 1, 2, 3, 4, 5, 6, 7, 8, 9, or 10","IT 306 Project Phase 2",JOptionPane.ERROR_MESSAGE);
      }
      else {
         temp.buySoda(sodaChoice);
         prompt = "Enjoy your " + temp.getSodaName(sodaChoice);
         print(prompt);
      }
      
   }
   
   /*********************************************************/
   //methods for receiving input
   //each of them take a string to ask the user 
   
   public static int getInputInteger( String prompt )
   {
      boolean valid = false;
      int input = 0;
      while( valid == false )
      {
         // Present dialog to user
         String stringInput = JOptionPane.showInputDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.QUESTION_MESSAGE);
         try
         {
            // Try to convert String into an integer
            input = Integer.parseInt(stringInput);
            valid = true;
         }
         catch(NumberFormatException e)
         {
            // If problem occured, let user know and try again
            valid = false;
            print("Input error:\nPlease enter a valid string (can be converted to integer).");
         }
      }
      return input;
   }
   
   public static double getInputDouble( String prompt )
   {
      boolean valid = false;
      double input = 0.0;
      while( valid == false )
      {
         // Present dialog to user
         String stringInput = JOptionPane.showInputDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.QUESTION_MESSAGE);
         try
         {
            // Try to convert String into a double
            input = Double.parseDouble(stringInput);
            valid = true;
         }
         catch(NumberFormatException e)
         {
            // If problem occured, let user know and try again
            valid = false;
            print("Input error:\nPlease enter a valid string (can be converted to double).");
         }
      }
      return input;
   }
   
   public static String getInputString( String prompt )
   {
      boolean valid = false;
      String input = null;
      while( valid == false )
      {
         // Present dialog to user
         input = JOptionPane.showInputDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.QUESTION_MESSAGE);
         if( input != null && input.length() != 0 )
         {
            valid = true;
         }
         else
         {  
            // If problem occured, let user know and try again
            print("Input error:\nPlease enter a valid (non-null) string.");
         }
      }
      return input;
   }
   
   
   
   public static void print( String message )
   {
      JOptionPane.showMessageDialog(null, message,"IT 306 Project Phase 2",JOptionPane.INFORMATION_MESSAGE);
   }
   
}//end of implementation
