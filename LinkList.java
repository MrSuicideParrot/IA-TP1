public class Node{
	int val;
	Node<Integer> next;

	Node(int v, Node<Integer> n){
		val = v;
		next = n;
	}

	Node(int v){
		val = v;
		next = NULL;
	}
}

public class LinkList{
	Node<Integer> = first;
	int size;

	LinkList(){
		first = NULL;
		size = 0;
	}

	LinkList(LinkList<Integer> l){
		first = l.first;
		size = l.size;
	}

	public int first(){
		return first;
	}

	public int size(){
		return size;
	}

	public boolean isEmpty(){
		return (size == 0)
	}

	public indexOf(int val){
		int index = 0;
		Node<Integer>  = first;

		while(p != NULL){
			int curr = p.val;
			
			if(curr == val)
				return index;
			else{
				p = p.next;
				index++;
			}
		}

		if(p==NULL)
			return -1;
	}

	public void addFirst(int value) {
    	first = new Node<Integer>(value, first);
    	size++;
  	}

	public void add(int val, int index){
		if ( index < 0 || index > size - 1 )
      		throw new IndexOutOfBoundsException();
    	if ( index == 0 ) 
      	
      	first = new Node<T>(value, first);
    	else {
     		Node<Integer> p = first;
      	
      	for( int i = 0 ; i < index - 1 ; i++)
        	p = p.next;
      	p.next = new Node<Integer>(value, p.next);
    	}
   
   		 size++;
  	}

  	public void addLast(T value) {
    	if(size == 0) 
     		first = new Node<Integer>(value, first);
    	
    	else {
      		Node<Integer> p = first;
     		
     		for( int i = 0 ; i < size - 1 ; i++)
        		p = p.next;
      		
      		p.next = new Node<Integer>(value);
    	
    	}
    
    	size++;
  	}

  	public void remFirst() {
   		if (size == 0) 
      		throw new IndexOutOfBoundsException();
    	addFirst = first.next;
    	
    	size--;
  }

  	public void rem(int index) { // rem = remove
    	if ( index < 0 || index > size - 1 )
      		throw new IndexOutOfBoundsException();
    	if (size == 0) 
      		throw new IndexOutOfBoundsException();
    	if ( index == 0 ) 
      		first = first.next;
    
    	else {
      		Node<Integer> p = first;
      	
      		for ( int i = 0; i < index - 1; i++ ) 
        		p = p.next;
      
      		p.next = p.next.next;
    	}
    	size--;
  	}

  	public T get(int index) {
    	if (size == 0) 
      		throw new IndexOutOfBoundsException();
    	if ( index < 0 || index > size - 1 )
      		throw new IndexOutOfBoundsException();
    	
    	Node<Integer> p = first;
    	
    	for ( int i = 0; i < index ; i++ ) 
      		p = p.next;
    	
    	return p.value;
  	}

}

