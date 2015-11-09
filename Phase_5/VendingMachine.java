

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
      if(sodaList.query(sodaName) == null){ return false;}
      if(!(sodaList.query(sodaName).getData() instanceof Soda)){return false;}
      Soda aSoda = sodaList.query(sodaName).getData();
      aSoda.reStock();
      return true;
   }
   
   public boolean reStock(String sodaName, int amountToStock){
      if(sodaList.query(sodaName) == null){ return false;}
      if(!(sodaList.query(sodaName).getData() instanceof Soda)){return false;}
      Soda aSoda = sodaList.query(sodaName).getData();
      return aSoda.reStock(amountToStock);
   }
   
   public boolean reStock(int sodaIndex, int amountToStock){
      Node<Soda> aNode = sodaList.queryByIndex(sodaIndex);
      if(aNode == null){return false;}
      if(!(aNode.getData() instanceof Soda)){return false;}
      Soda aSoda = aNode.getData();
      return aSoda.reStock(amountToStock);
   }
   
   public boolean reStock(int sodaIndex){
      Node<Soda> aNode = sodaList.queryByIndex(sodaIndex);
      if(aNode == null){return false;}
      if(!(aNode.getData() instanceof Soda)){return false;}
      Soda aSoda = aNode.getData();
      return aSoda.reStock();
   }
      
   /** 
   set the price of a soda in the machine
   @param soda name
   @param price
   */
   public boolean setPrice(String sodaName, double price){
      Node<Soda> aNode = sodaList.query(sodaName);
      if(aNode == null){return false;}
      if(!(aNode.getData() instanceof Soda)){return false;}
      Soda aSoda = aNode.getData();
      return aSoda.setPrice(price);
   }
   
   /**
   set the rpice of a soda in the machine
   @param soda index
   @param price
   */
   public boolean setPrice(int sodaIndex, double price){
      Node<Soda> aNode = sodaList.queryByIndex(sodaIndex);
      if(aNode == null){return false;}
      if(!(aNode.getData() instanceof Soda)){return false;}
      Soda aSoda = aNode.getData();
      return aSoda.setPrice(price);
   }
   
   /*public Soda getSoda(String name){
      if(sodaList.query(name) == null){ return null;}
      if(!(sodaList.query(name).getData() instanceof Soda)){return null;}
      Soda aSoda = (Soda)(sodaList.query(name).getData());
      return aSoda;
   }*/
   
   //____________________________________
   
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
   
   /**
   to string allows a quick overview of the soda machine
   */
   public String toString(){
      return String.format("Report%nName: %s%nBalance: %.2f%n%s", name, getBal(), sodaList);
   }
   
}