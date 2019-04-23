public interface List<E> {

    void add( int index, E elem );
    void add( E elem );

    E remove( int index );
    boolean remove( E o );

    E get( int index );

    int size();
    boolean isEmpty();
}
