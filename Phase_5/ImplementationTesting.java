class ImplementationTesting{
   public static void main(String args[]){
      
      try{
         VendingMachine aVM = new VendingMachine("Dining Hall");
         aVM.addSoda("Red Bull", 2.5);
         aVM.addSoda("Sprite", 1.5);
         aVM.addSoda("Coca-cola", 1.25);
         aVM.reStock("Red Bull");
         aVM.reStock("Sprite");
         aVM.reStock("Coca-cola");
         
         
         
         aVM.buySoda(1);
         aVM.buySoda(2);
         aVM.buySoda(3);
         
         aVM.withDraw(6);
      }catch(IllegalArgumentException e){
         System.out.println("error!");
      }
      
      try{
         VendingMachine aVM2 = new VendingMachine("Dining Hall2");
         aVM2.addSoda("Dr. Pepper", 1.50);
         aVM2.addSoda("Dr. Thunder", 1.25);
         aVM2.addSoda("Mr. Pibb", 1.25);
         VendingMachine.removeMachine("South Wing");
      }catch(IllegalArgumentException e){
         System.out.println("error!");
      }
      
      VendingMachine.removeMachine("Dining Hall2");
      
      LinkedList machineList = VendingMachine.getMachines();
      System.out.println(machineList);
   }
}