class LinkedList<D>{
   private Node<D> head;
   private Node<D> tail;
   
   public LinkedList(Node<D> _firstNode){
      setHead(_firstNode);
      setTail(_firstNode);
   }
   
   public LinkedList(){
      this.head = null;
      this.tail = null;
   }
   
   public Node<D> getHead(){
      return this.head;
   }
   
   public Node<D> getTail(){
      return this.tail;
   }
   
   private boolean setHead(Node<D> head){
      if(head == null){return false;}
      if(this.tail == null){ this.tail = head;}
      this.head = head;
      return true;
   }
   
   private boolean setTail(Node<D> tail){
      if(tail == null){return false;}
      if(this.head == null){this.head = tail;}
      this.tail = tail;
      return true;
   }
   
   public boolean add(Node<D> aNode){
      if(aNode == null){ return false;}
      if(head == null){ 
         setHead(aNode);
      }else{
         head.setNextNode(aNode);
         this.head = aNode;
         head.setNextNode(null);
      }
      return true;
   }
   
   public boolean remove(String name){
      Node<D> prevNode = null;
      Node<D> curNode = this.tail;
      
      do{
         Searchable anItem = (Searchable)curNode.getData();
         if(anItem.getName().equals(name)){
            //remove item
            if(prevNode != null){
               prevNode.setNextNode(curNode.getNextNode());
            }else{ this.tail = curNode.getNextNode();}
            curNode.setNextNode(null);
            curNode = null;
            return true;
         }
         prevNode = curNode;
         curNode = curNode.getNextNode();
      }while(curNode != null);
      return false;
   }
   
   public Node<D> query(String name){
      Node<D> curNode = this.tail;
      
      while(curNode != null){
         Searchable anItem = (Searchable)curNode.getData();
         if(anItem.getName().equals(name)){ return curNode;}
         curNode = curNode.getNextNode();
      }
      return null;
   }
   
   public Node<D> queryByIndex(int index){
      int count = 0;
      Node<D> curNode = this.tail;
      while(curNode != null && count != index){
         count++;
         if(count == index){return curNode;}
         curNode = curNode.getNextNode();
      }
      return null;
   }

   public String toString(){
      String output = "";
      Node<D> curNode = this.tail;
      while(curNode != null){
         output += String.format("=>%s%n", curNode);
         curNode = curNode.getNextNode();
      }
      return output;
   }
}