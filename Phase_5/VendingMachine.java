import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

class VendingMachine implements Searchable{
   public static int MAX_SODA_TYPES = 10;
   private static LinkedList<VendingMachine> machineList = new LinkedList<VendingMachine>();
   private LinkedList<Soda> sodaList = new LinkedList<Soda>();   
   private String name;
   double balance;
   
   public VendingMachine(String _name) throws IllegalArgumentException{
      this.name = _name;
      //make sure it is not already listed
      if(machineList.query(_name) == null){
         machineList.add(new Node<VendingMachine>(this));
      }else{
         throw new IllegalArgumentException(String.format("Can not create this machine, %s already exists!", this.name));
      }
   }  
   
   public VendingMachine(){
      this("");
   }
   
   public String getName(){
      return this.name;
   }
   
   public boolean addSoda(String sodaName, double price){
      if(sodaName.length() <= 0){ return false;}
      if(price < 0){ return false;}
      Soda aSoda = new Soda(sodaName, price, 0);
      return sodaList.add(new Node<Soda>(aSoda));
   }
   
   public boolean addSoda(String sodaName){
      return addSoda(sodaName, 0);
   }
   
   public boolean removeSoda(String soda){
      return sodaList.remove(soda);
   }
   
   /**
      buy a soda based on the index (One based index)
      for example with a list of items 1) coke, 2) pepsi
      a selection of 1 would be coke.
      @param index
      @return true if transaction succeeded false otherwise
   */
   public boolean buySoda(int index){
      Node aNode = sodaList.queryByIndex(index);
      if(aNode == null){return false;}
      if(!(aNode.getData() instanceof Soda)){ return false;}
      Soda aSoda = (Soda)aNode.getData();
      boolean result = aSoda.buySoda();
      if(result){balance += aSoda.getPrice();}
      return result;
   }
   
   /**
   gets the current balance
   @return balance of all cash in soda machine
   */
   public double getBal(){
      return this.balance;
   }
   
   /**
   with draw allows an amount to be removed from the soda machine
   @param amount
   @param validation of withdraw
   */
   public boolean withDraw(double amount){
      if(amount <= balance){
         balance -= amount; 
         return true;           
      }else{
         return false;
      }
   }
   
   /**
   restock a given soda type
   @param soda name
   @return validates if reStock was successfull;
   */
   public boolean reStock(String sodaName){
      Soda aSoda = selectSoda(sodaName);
      return (aSoda == null)? false:aSoda.reStock();  
   }
   
   public boolean reStock(String sodaName, int amountToStock){
      Soda aSoda = selectSoda(sodaName);
      return (aSoda == null)? false: aSoda.reStock(amountToStock);
   }
   
   public boolean reStock(int sodaIndex, int amountToStock){
      Soda aSoda = selectSoda(sodaIndex);
      return (aSoda == null)? false : aSoda.reStock(amountToStock);
   }
   
   public boolean reStock(int sodaIndex){
      Soda aSoda = selectSoda(sodaIndex);
      return (aSoda == null)? false: aSoda.reStock();
   }
   
   /**
   get the price of the soda given the index
   @param index
   @return price or -1 if invalid name given;
   */
   public double getSodaPrice(int sodaIndex){
      Soda aSoda = selectSoda(sodaIndex);
      return (aSoda == null)? -1: aSoda.getPrice();
   }
   
   /**
   get the price of the soda given the index
   @param soda name
   @return price or -1 if invalid name given;
   */
   public double getSodaPrice(String sodaName){
      Soda aSoda = selectSoda(sodaName);
      return (aSoda == null)? -1: aSoda.getPrice();
   }
   
   /**
   helper method for performing selection of sodas
   @param index
   */
   private Soda selectSoda(int index){
      Node<Soda> aNode = sodaList.queryByIndex(index);
      if(aNode == null){return null;}
      if(!(aNode.getData() instanceof Soda)){return null;}
      Soda aSoda = aNode.getData();
      return aSoda;
   }
   
    /**
   helper method for performing selection of sodas
   @param String soda name
   */
   private Soda selectSoda(String sodaName){
      Node<Soda> aNode = sodaList.query(sodaName);
      if(aNode == null){return null;}
      if(!(aNode.getData() instanceof Soda)){return null;}
      Soda aSoda = aNode.getData();
      return aSoda;
   }
      
   /** 
   set the price of a soda in the machine
   @param soda name
   @param price
   */
   public boolean setPrice(String sodaName, double price){
      Soda aSoda = selectSoda(sodaName);
      return (aSoda == null)? false: aSoda.setPrice(price);
   }
   
   /**
   set the price of a soda in the machine
   @param soda index
   @param price
   */
   public boolean setPrice(int sodaIndex, double price){
      Soda aSoda = selectSoda(sodaIndex);
      return (aSoda == null)? false: aSoda.setPrice(price);
   }
   
   public int getNumSodas(){
      return sodaList.getLength();
   }
   
   //____________________________________
   
   /**
   gets the linked list of all vending machines
   */
   public static LinkedList getMachines(){
      return machineList;
   }
   
   /**
   remove a given machine
   @param machine
   @return validation
   */
   public static boolean removeMachine(String name){
      return machineList.remove(name);
   }
   
   //To Do
   public static boolean loadData(String fileName){
      try{
         Scanner in = new Scanner(new FileInputStream(new File(fileName)));
      }catch(FileNotFoundException e){
         e.printStackTrace();
      }
      return false;
   }
   
   public static boolean saveData(String fileName){
      try{
         PrintWriter out = new PrintWriter(new FileOutputStream(new File(fileName)));
         Node<VendingMachine> curMachine = machineList.getCursor();
         
         while(curMachine != null){
            out.printf("<Machine>%n   <Name>%s</Name>%n   <bal>%s</bal>%n</Machine>%n", curMachine.getData().getName(), curMachine.getData().getBal());
            curMachine = machineList.next();
         }
         
         
         out.close();
         return true;
      }catch(FileNotFoundException e){
         e.printStackTrace();
         return false;
      }
   }
   
   /**
   to string allows a quick overview of the soda machine
   */
   public String toString(){
      return String.format("Report%nName: %s%nBalance: %.2f%n%s", name, getBal(), sodaList);
   }
   
   
}