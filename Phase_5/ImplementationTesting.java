class ImplementationTesting{
   public static void main(String args[]){
      
      try{
         VendingMachine aVM = new VendingMachine("Dining Hall");
         aVM.addSoda("Red Bull", 2.5);
         aVM.addSoda("Sprite", 1.5);
         aVM.addSoda("Coca-cola", 1.25);
         
         //below two lines are equivelant (one selects soda by one based index, one selects by name)
         aVM.reStock(1);
         aVM.reStock("Red Bull");
         
         //below two lines are equivelant (one selects soda by name, one selects by onebased index)
         aVM.setSodaPrice("Red Bull", 1.25);
         aVM.setSodaPrice(2, 3.5);
         
         //buy redBull
         aVM.buySoda(1);
         //buy sprite
         aVM.buySoda(2);
         //buy coca-cola
         aVM.buySoda(3);
         
         aVM.withDraw(6);
      }catch(IllegalArgumentException e){
         System.out.println("error!");
      }
      
      try{
         VendingMachine aVM2 = new VendingMachine("Dining Hall 2");
         aVM2.addSoda("Dr. Pepper", 1.50);
         aVM2.addSoda("Dr. Thunder", 1.25);
         aVM2.addSoda("Mr. Pibb", 1.25);
      }catch(IllegalArgumentException e){
         System.out.println("error!");
      }
      
      try{
         VendingMachine aVM2 = new VendingMachine("Dining Hall 3");
         aVM2.addSoda("Dr. Pepper", 1.50);
         aVM2.addSoda("Monster", 2.25);
         aVM2.addSoda("liter-o-cola", 50.25);
      }catch(IllegalArgumentException e){
         System.out.println("error!");
      }
      
      //System.out.println(VendingMachine.getMachines());
      VendingMachine.saveData("Test.txt");
      
      VendingMachine.removeMachine("Dining Hall");
      VendingMachine.removeMachine("Dining Hall 2");
      VendingMachine.removeMachine("Dining Hall 3");
      
      VendingMachine.loadData("Test.txt");
      System.out.println(VendingMachine.getMachines());
   }
}