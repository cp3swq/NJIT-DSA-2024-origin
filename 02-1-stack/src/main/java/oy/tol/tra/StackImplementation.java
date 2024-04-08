package oy.tol.tra;

public class StackImplementation<E> implements StackInterface<E> {

   private Object [] itemArray;
   private int capacity;
   private int currentIndex = -1;
   private static final int DEFAULT_STACK_SIZE = 10;

   public StackImplementation() throws StackAllocationException {
         capacity = DEFAULT_STACK_SIZE;
         itemArray = new Object[DEFAULT_STACK_SIZE];
      
   }
   public StackImplementation(int capacity) throws StackAllocationException {
      if(capacity < 2){ 
         throw new StackAllocationException("Less than 2!");
      }
      this.capacity = capacity;
      itemArray=new Object[capacity];
   }

   @Override
   public int capacity() {
      return capacity;
   }

   public void push(E element) throws NullPointerException { 
      if (element == null) {   
         throw new NullPointerException("Cannot push null element into the stack!");  
     }    
      if (size() == capacity()) {  
          @SuppressWarnings("unchecked")  
          E[] newArray = (E[]) new Object[this.capacity * 2 + 1];  
          System.arraycopy(itemArray, 0, newArray, 0, itemArray.length);  
          itemArray = newArray;  
          capacity = capacity * 2 + 1;  
      }  
      itemArray[++currentIndex] = element;  
  }     
   

  @SuppressWarnings("unchecked")
  @Override
  public E pop() throws StackIsEmptyException {
     if (isEmpty()){
        throw new StackIsEmptyException("Stack is empty!");
     }
     Object popElement = itemArray[currentIndex];
     itemArray[currentIndex] = null;
     currentIndex--;
     return (E) popElement;
  }


   @SuppressWarnings("unchecked")
   @Override
   public E peek() throws StackIsEmptyException {
      if(isEmpty()){
         throw new StackIsEmptyException("Stack is empty!");
      }
      return (E)itemArray[currentIndex];
   }
   

   @Override
   public int size() {
      return currentIndex + 1;
   }

   @Override
   public void clear() {
      currentIndex = -1;
   }

   @Override
   public boolean isEmpty() {
      return currentIndex == -1; 
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder("[");
      for (var index = 0; index <= currentIndex; index++) {
         builder.append(itemArray[index].toString());
         if (index < currentIndex) {
            builder.append(", ");
         }
      }
      builder.append("]");
      return builder.toString();
   }
}
