class Soda implements Searchable{
   public static int MAX_STOCK = 40;
   private String name;
   private double price;
   private int quantity;
   
   //name, price, quantity constructor 
   public Soda(String _name, double _price, int _quantity){
      this.name = _name;
      this.price = _price;
      this.quantity = _quantity;
   }
   
   //name constructor
   public Soda(String _name){
      this(_name, 0,0);
   }
   
   //empty constructor
   public Soda(){
      this("", 0, 0);
   }
   
   public String getName(){
      return this.name;
   }
   
   public double getPrice(){
      return this.price;
   }
   
   public boolean setPrice(double price){
      if(price < 0){ return false;}
      this.price = price;
      return true;
   }
   
   public boolean setQuantity(int quantity){
      if(quantity >= 0){
         this.quantity = quantity;
         return true;
      }else{ return false;}
   }
   
   public void setName(String name){
      this.name = name;
   }
   
   public boolean reStock(){
      return reStock(MAX_STOCK - quantity);
   }
   
   public boolean reStock(int amountToStock){
      if(amountToStock + quantity > MAX_STOCK){ return false;}
      this.quantity += amountToStock;
      return true;
   }
   
   public boolean buySoda(){
      if(quantity > 0){
         quantity--;
         return true;
      }else{
         return false;
      }
   }
   
   public String toString(){
      return String.format("%s:\t%d\t%.2f", name, quantity, price);
   }
}