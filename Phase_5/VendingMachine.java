import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

class VendingMachine implements Searchable, Persistable{
   public static int MAX_SODA_TYPES = 10;
   private static LinkedList<VendingMachine> machineList = new LinkedList<VendingMachine>();
   private LinkedList<Soda> sodaList = new LinkedList<Soda>();   
   private String name;
   double balance;
   
   public VendingMachine(String _name) throws IllegalArgumentException{
      setName(_name);
   } 
    
   
   public VendingMachine(){
      this.name = "NOT-SET";
   }
   
   public String getName(){
      return this.name;
   }
   
   public void setName(String name) throws IllegalArgumentException{
      this.name = name;
      if(machineList.query(name) == null){
         machineList.add(new Node<VendingMachine>(this));
      }else{
         throw new IllegalArgumentException(String.format("Can not create this machine, %s already exists!", this.name));
      }
   }
   
   public void addSoda(){
      addSoda("NOT NAMED", 0);
   }
   
   public boolean addSoda(String sodaName){
      return addSoda(sodaName, 0);
   }
   
   public boolean addSoda(String sodaName, double price){
      if(sodaName.length() <= 0){ return false;}
      if(price < 0){ return false;}
      Soda aSoda = new Soda(sodaName, price, 0);
      return sodaList.add(new Node<Soda>(aSoda));
   }
   
   public boolean addSoda(String sodaName, double price, int quantity){
      if(sodaName.length() <= 0){return false;}
      if(price < 0){return false;}
      if(quantity < 0){return false;}
      Soda aSoda = new Soda(sodaName, price, quantity);
      return sodaList.add(new Node<Soda>(aSoda));
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
   
   public String getSodaName(int index){
      Soda aSoda = selectSoda(index);
      return (aSoda == null)? "" :aSoda.getName();
   }
   
   /**
   gets the current balance
   @return balance of all cash in soda machine
   */
   public double getBal(){
      return this.balance;
   }
   
   public boolean setBal(double balance){
      if(balance >= 0){
         this.balance = balance;
         return true;
      }else{
         return false;
      }
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
   public boolean reStockSoda(String sodaName){
      Soda aSoda = selectSoda(sodaName);
      return (aSoda == null)? false:aSoda.reStock();  
   }
   
   public boolean reStockSoda(String sodaName, int amountToStock){
      Soda aSoda = selectSoda(sodaName);
      return (aSoda == null)? false: aSoda.reStock(amountToStock);
   }
   
   public boolean reStockSoda(int sodaIndex, int amountToStock){
      Soda aSoda = selectSoda(sodaIndex);
      return (aSoda == null)? false : aSoda.reStock(amountToStock);
   }
   
   public boolean reStockSoda(int sodaIndex){
      Soda aSoda = selectSoda(sodaIndex);
      return (aSoda == null)? false: aSoda.reStock();
   }
   
   public void reStock(){
      for(int x = 1; x <= sodaList.getLength(); x++){
         reStockSoda(x);
      }
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
   public boolean setSodaPrice(String sodaName, double price){
      Soda aSoda = selectSoda(sodaName);
      return (aSoda == null)? false: aSoda.setPrice(price);
   }
   
   /**
   set the price of a soda in the machine
   @param soda index
   @param price
   */
   public boolean setSodaPrice(int sodaIndex, double price){
      Soda aSoda = selectSoda(sodaIndex);
      return (aSoda == null)? false: aSoda.setPrice(price);
   }
   
   public int getNumSodas(){
      return sodaList.getLength();
   }
   
   public String getSodaSaveData(int index){
      Soda aSoda = selectSoda(index);
      return (aSoda == null)? "": aSoda.getSaveData();
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
   
   public static boolean saveData(String fileName){
      try{
         PrintWriter out = new PrintWriter(new FileOutputStream(new File(fileName)));
         Node<VendingMachine> curMachine = machineList.getCursor();
         
         while(curMachine != null){
            out.printf(curMachine.getData().getSaveData());
            curMachine = machineList.next();
         }
         out.close();
         return true;
      }catch(FileNotFoundException e){
         e.printStackTrace();
         return false;
      }
   }
   
   public String getSaveData(){
      String sodaInfo = "";
      for(int x = 1; x <= getNumSodas(); x++){
         sodaInfo += getSodaSaveData(x);
      }
      String output = String.format("<Machine>%n   <Name>%s</Name>%n      <bal> %s </bal>%s%n</Machine>%n", getName(), getBal(), sodaInfo);
      return output;
   }
   
   public static boolean loadData(String fileName){
      try{
         Scanner in = new Scanner(new FileInputStream(new File(fileName)));
         int loadCount = 0;
         while(in.hasNext()){
            String VendData = "";
            String inText = in.nextLine();
            if(inText.equals("<Machine>")){
               loadCount++;
               VendingMachine aVendMachine = new VendingMachine();
               
               while(in.hasNext()){
                  String nextLine = in.nextLine();
                  if(nextLine.equals("</Machine>")){ break;}
                  VendData += nextLine;
               }
               aVendMachine.setLoadData(VendData);
            }
         }
         in.close();
      }catch(FileNotFoundException e){
         e.printStackTrace();
      }
      return false;
   }
   
   public void setLoadData(String data){
      final String NAME_TAG = "<Name>";
      final String END_NAME_TAG = "</Name>";
      
      final String BAL_TAG = "<bal>";
      final String END_BAL_TAG = "</bal>";
      
      final String SODA_TAG = "<Soda>";
      final String END_SODA_TAG = "</Soda>";
      
      final String SODA_NAME_TAG = "<SodaName>";
      final String END_SODA_NAME_TAG = "</SodaName>";
      
      final String SODA_PRICE = "<SodaPrice>";
      final String END_SODA_PRICE = "</SodaPrice>";
      
      final String SODA_QUANTITY = "<SodaQuantity>";
      final String END_SODA_QUANTITY = "</SodaQuantity>"; 
      
      //load in name
      int nameStart = data.indexOf(NAME_TAG);
      int nameEnd = data.indexOf(END_NAME_TAG);
      if(nameStart >= 0 && nameEnd >= 0){
         nameStart += NAME_TAG.length();
         String machName = data.substring(nameStart, nameEnd);
         if(machName.length() > 0){
            try{
            setName(machName);
            }catch(IllegalArgumentException e){
               e.printStackTrace();
            }
         }
      }
      
      //load in bal
      int balStart = data.indexOf(BAL_TAG);
      int balEnd = data.indexOf(END_BAL_TAG);
      balStart += BAL_TAG.length();
      String strBal = data.substring(balStart, balEnd).trim();
      double bal = 0;
      
      try{
         bal = Double.parseDouble(strBal);
         setBal(bal);
      }catch(NumberFormatException e){
         e.printStackTrace();
      }
      
      //load sodas
      int sodaDataStart = data.indexOf(SODA_TAG);
      if(sodaDataStart >= 0){
         String sodaData = data.substring(sodaDataStart);
         Scanner sodaScan = new Scanner(sodaData);
         sodaScan.useDelimiter(SODA_TAG);
         while(sodaScan.hasNext()){
            //String soda = sodaScan.next();
            String sodaInfo = sodaScan.next();
            //sodaInfo = sodaInfo.substring(0, sodaInfo.indexOf(END_SODA_TAG));
            
            String sodaName = parseData(SODA_NAME_TAG, END_SODA_NAME_TAG, sodaInfo).trim();
            String strSodaPrice = parseData(SODA_PRICE, END_SODA_PRICE, sodaInfo).trim();
            String strSodaQuantity = parseData(SODA_QUANTITY, END_SODA_QUANTITY, sodaInfo).trim();
            double sodaPrice = -99;
            int sodaQuantity = -99;
            
            try{
               sodaPrice = Double.parseDouble(strSodaPrice);
               sodaQuantity = Integer.parseInt(strSodaQuantity);
            }catch(NumberFormatException e){
               e.printStackTrace();
            }
            
            addSoda(sodaName, sodaPrice, sodaQuantity);
            
         }
         sodaScan.close();
      }
   }
   
   public static String parseData(String fTag, String lTag, String data){

      int startPos = data.indexOf(fTag) + fTag.length();
      int endPos = data.indexOf(lTag);
      String output = data.substring(startPos, endPos);
      
      return output;
   }
   
   /**
   to string allows a quick overview of the soda machine
   */
   public String toString(){
      return String.format("Report%nName: %s%nBalance: %.2f%n%s", name, getBal(), sodaList);
   }
   
   
}