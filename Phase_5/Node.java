class Node<D>{
   private Node<D> nextNode;
   private D data;
   
   public Node(D datum){
      setData(datum);
   } 
   
   public D getData(){
      return data;
   }
   
   public Node<D> getNextNode(){
      return nextNode;
   }
   
   public void setNextNode(Node<D> next){
      this.nextNode = next;
   }
   public boolean setData(D aDatum){
      if(aDatum == null){
         return false;
      }
      this.data = aDatum;
      return true;
   }
   
   public String toString(){
      return this.getData().toString();
   }

   
}