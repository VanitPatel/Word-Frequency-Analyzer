import java.util.Iterator;

/**
 * The method the user can use in the Hashed Dictionary
 */
public interface DictionaryInterface<K, V> {

    /**
     * Adds a new entry to the hash dictionary. If the given search key already exists in the hash dictionary
     * then it will replace the corresponding value
     * @param key An object search key of the new entry.
     * @param value An object associated with the search
     * @return return null or the value of the entry what was added
     */
    public V add(K key, V value);

    //not implemented
    public V remove(K key);

    /**
     * gets the value of the search key
     * @param key the key to find the value
     * @return returns the value of the key or null if the value does not exist
     */
    public V getValue(K key);

    //not implemented
    public boolean contains(K key);

    /**
     * tranverses all the search keys in the hash dictinary
     * @return the access of the search
     */
    public Iterator<K> getKeyIterator();

     //not implemented
    public Iterator<V> getValueIterator();

     //not implemented
    public boolean isEmpty();

     //not implemented
    public int getSize();

     //not implemented
    public void clear(); 
 
    
}