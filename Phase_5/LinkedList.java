class LinkedList<D>{
   private Node<D> head;
   private Node<D> tail;
   private Node<D> cursor;
   private int length;
   
   /** constructor accepting firstNode*/
   public LinkedList(Node<D> _firstNode){
      this.length = 0;
      setHead(_firstNode);
      setTail(_firstNode);
      cursor = this.tail;
   }
   
   /** constructor accepting no arguments*/
   public LinkedList(){
      this.length = 0;
      this.head = null;
      this.tail = null;
      cursor = this.tail;
   }
   
   public Node<D> getHead(){
      return this.head;
   }
   
   public Node<D> getTail(){
      return this.tail;
   }
   
   private boolean setHead(Node<D> head){
      if(head == null){return false;}
      if(this.tail == null){ setTail(head);}
      this.head = head;
      return true;
   }
   
   private boolean setTail(Node<D> tail){
      if(tail == null){return false;}
      if(this.head == null){this.head = tail;}
      this.tail = tail;
      cursor = this.tail;
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
      this.length++;
      return true;
   }
   
   public boolean remove(String name){
      Node<D> prevNode = null;
      Node<D> curNode = this.tail;
      
      for(int x = 0; x < length; x++){
         Searchable anItem = (Searchable)curNode.getData();
         if(anItem.getName().equals(name)){
            //remove item
            if(prevNode != null){
               prevNode.setNextNode(curNode.getNextNode());
            }else{ setTail(curNode.getNextNode());}
            curNode.setNextNode(null);
            curNode = null;
            this.length--;
            return true;
         }
         prevNode = curNode;
         curNode = curNode.getNextNode();
      }
      return false;
   }
   
   public Node<D> next(){
      Node<D> out = this.cursor.getNextNode();
      cursor = (cursor == null)? this.tail: cursor.getNextNode();
      return out;
   }
   
   public boolean hasNext(){
      if(cursor == null){return false;}      
      return (cursor.getNextNode() == null)? false: true;
   }
   
   public Node<D> getCursor(){
      return this.cursor;
   }
   
   /**
   query the linked list for a value with the given name
   returns the node 
   */
   public Node<D> query(String name){
      Node<D> curNode = this.tail;
      
      for(int x = 0; x < length; x++){
         Searchable anItem = (Searchable)curNode.getData();
         if(anItem.getName().equals(name)){ return curNode;}
         curNode = curNode.getNextNode();
      }
      return null;
   }
   
   public int getLength(){ return this.length;}
   
   public Node<D> queryByIndex(int index){
      //int count = 0;
      Node<D> curNode = this.tail;
      
      for(int x = 1; x <= length; x++){
         if(x == index){return curNode;}
         curNode = curNode.getNextNode();
      }
      return null;
   }

   public String toString(){
      String output = "";
      Node<D> curNode = this.tail;
      
      for(int x =0; x < length; x++){
         output += String.format("=>%s%n", curNode);
         curNode = curNode.getNextNode();
      }
      
      return output;
   }
}