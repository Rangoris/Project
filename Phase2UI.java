/*
   Sean Geserick
   G00641250
   SGeseric@masonlive.gmu.edu
*/

import javax.swing.JOptionPane;

public class Phase2UI {
   public static void main(String[] args) {
   
   String prompt = "Main Menu\n" +
                    "1 Create a new vending machine\n" + 
                    "2 Remove a vending machine\n" +
                    "3 Check a vending machines stock\n" +
                    "4 Restock a vending machine\n" +
                    "5 Reduce money in a vending machine\n" +
                    "6 View a report\n" +
                    "7 Buy a soda\n" +
                    "8 exit\n";
   
   String input = JOptionPane.showInputDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.QUESTION_MESSAGE);
   
   //option 1
   prompt = "Enter the name of your vending machine";
   input = JOptionPane.showInputDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.QUESTION_MESSAGE);
   
   prompt = "Enter the name of a soda";
   input = JOptionPane.showInputDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.QUESTION_MESSAGE);
   
   String sodaName = "SODA_NAME";
   prompt = "Enter the price of " + sodaName;
   input = JOptionPane.showInputDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.QUESTION_MESSAGE);
   
   //option 2
   prompt = "Enter the name of the soda machine you would like to remove";
   input = JOptionPane.showInputDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.QUESTION_MESSAGE);
   
   String machineName = "MACHINE_NAME";
   prompt = "There are no vending machines on record with the name: " + machineName;
   JOptionPane.showMessageDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.ERROR_MESSAGE);
   
   prompt = machineName + " has been removed";
   JOptionPane.showMessageDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.INFORMATION_MESSAGE);
   
   //option 3
   prompt = "Enter the name of the soda machine you would like to check the stock of";
   input = JOptionPane.showInputDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.QUESTION_MESSAGE);
   
   prompt = "The stock of " + machineName + "\n" +
             "SODA1: 0\n" +
             "SODA2: 0\n" +
             "SODA3: 0\n" +
             "SODA4: 0\n" +
             "SODA5: 0\n" +
             "SODA6: 0\n" +
             "SODA7: 0\n" +
             "SODA8: 0\n" +
             "SODA9: 0\n" +
             "SODA10: 0\n";
   JOptionPane.showMessageDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.INFORMATION_MESSAGE);
   
   //option 4
   prompt = "Enter the name of the soda machine you would like to restock";
   input = JOptionPane.showInputDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.QUESTION_MESSAGE);
   
   prompt = machineName + " has been restocked";
   JOptionPane.showMessageDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.INFORMATION_MESSAGE);

   //option 5
   prompt = "Enter the name of the soda machine you would like to withdraw money from";
   input = JOptionPane.showInputDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.QUESTION_MESSAGE);
   
   String balance = "CURRENT_BALANCE";
   prompt = "Your input must be between 0 and " + balance;
   JOptionPane.showMessageDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.ERROR_MESSAGE);
   
   prompt = "You withdrew " + balance + " from " + machineName;
   JOptionPane.showMessageDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.INFORMATION_MESSAGE);
   
   //option 6
   prompt =  "Report\n" +
             "Name: " + machineName + "\n" +
             "Balance: " + balance + "\n" +
             "Name     Stock     Price\n" + 
             "SODA1:     0     $0.00\n" +
             "SODA2:     0     $0.00\n" +
             "SODA3:     0     $0.00\n" +
             "SODA4:     0     $0.00\n" +
             "SODA5:     0     $0.00\n" +
             "SODA6:     0     $0.00\n" +
             "SODA7:     0     $0.00\n" +
             "SODA8:     0     $0.00\n" +
             "SODA9:     0     $0.00\n" +
             "SODA10:    0     $0.00\n";
   input = JOptionPane.showInputDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.QUESTION_MESSAGE);
   
   //option 7
   prompt =  "Name: " + machineName + "\n" + "Enter the number of the soda you wish to purchase\n" +
             "Name              Price\n" + 
             "1 SODA1:          $0.00\n" +
             "2 SODA2:          $0.00\n" +
             "3 SODA3:          $0.00\n" +
             "4 SODA4:          $0.00\n" +
             "5 SODA5:          $0.00\n" +
             "6 SODA6:          $0.00\n" +
             "7 SODA7:          $0.00\n" +
             "8 SODA8:          $0.00\n" +
             "9 SODA9:          $0.00\n" +
             "10 SODA10:        $0.00\n";
   JOptionPane.showInputDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.QUESTION_MESSAGE);
   
   prompt =  "Name: " + machineName + "\n" + "Enter your money\n" +
             "Name              Price\n" + 
             "1 SODA1:          $0.00\n" +
             "Money inserted:   $0.00";
   JOptionPane.showInputDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.QUESTION_MESSAGE);
   
   prompt =  "Name: " + machineName + "\n" + "Enter your money\n" +
             "Name              Price\n" + 
             "1 SODA1:          $0.00\n" +
             "Money inserted:   $1.00";
   JOptionPane.showInputDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.QUESTION_MESSAGE);
   
   //option 8
   prompt = "Thanks!";
   JOptionPane.showMessageDialog(null, prompt,"IT 306 Project Phase 2",JOptionPane.INFORMATION_MESSAGE);
   
   }//end of main
   
}//end of Phase2UI