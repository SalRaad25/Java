public class GenericArrayStack<E> implements Stack<E> {
   
   // ADD YOUR INSTANCE VARIABLES HERE
    private E[] elems;
    private int top;

   // Constructor
    public GenericArrayStack( int capacity ) {
        
    // ADD YOU CODE HERE
        elems = (E[]) new Object[ capacity ];
        top = 0;

    }

    // Returns true if this ArrayStack is empty
    public boolean isEmpty() {
        
    // ADD YOU CODE HERE
        return ( top == 0 );

    }

    public void push( E elem ) {
        
    // ADD YOU CODE HERE
        elems[ top++ ] = elem;


    }
    public E pop() {
        
    // ADD YOU C ODE HERE
        E saved = elems[ --top ];

        elems[ top ] = null; // scrub the memory!

        return saved;

    }

    public E peek() {
        
    // ADD YOU CODE HERE
        return elems[ top-1 ];

    }
}
