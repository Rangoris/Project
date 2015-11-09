

class VendingMachine implements Searchable{
   public static int MAX_SODA_TYPES = 10;
   private static LinkedList<VendingMachine> machineList = new LinkedList<VendingMachine>();
   private LinkedList<Soda> sodaList = new LinkedList<Soda>();   
   private String name;
   
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
   
   public boolean removeSoda(String soda){
      return sodaList.remove(soda);
   }
   
   public boolean buySoda(int index){
      Node aNode = sodaList.queryByIndex(index);
      if(aNode == null){return false;}
      if(!(aNode.getData() instanceof Soda)){ return false;}
      Soda aSoda = (Soda)aNode.getData();
      return aSoda.buySoda();
   }
   
   public double getBal(){
      return -99;
   }
   
   public double withDraw(){
      return -99;
   }
   
   public Soda getSoda(String name){
      if(sodaList.query(name) == null){ return null;}
      if(!(sodaList.query(name).getData() instanceof Soda)){return null;}
      Soda aSoda = (Soda)(sodaList.query(name).getData());
      return aSoda;
   }
   
   //____________________________________
   
   public static LinkedList getMachines(){
      return machineList;
   }
   
   public static boolean removeMachine(String name){
      return machineList.remove(name);
   }
   
   public String toString(){
      return String.format("Report%nName: %s%nBalance: %.2f%n%s", name, getBal(), sodaList);
   }
   
}