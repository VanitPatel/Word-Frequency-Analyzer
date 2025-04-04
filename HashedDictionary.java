import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The implementation of the DictionaryInterface using a hash dictionary
 */
public class HashedDictionary<K, V> implements DictionaryInterface<K, V> {
    private int numberOfEntries;
    private int numberOfCollision;
    private static final int MAX_CAPACITY = 3001;
    private boolean integrityOK = false;
    private Entry<K, V>[] hashTable;

    /**
     * constructor to make the hash table
     *  sets integrityOk to true and numberOfEntries to 0
     * @param initialCapacity gives the size of the hash table
     */
    public HashedDictionary(int initialCapacity) {
        if(initialCapacity <= MAX_CAPACITY) {
        @SuppressWarnings("unchecked")
        Entry<K,V>[] temp = (Entry<K, V>[]) new Entry[initialCapacity];
        hashTable = temp;
        integrityOK = true;
        numberOfEntries = 0;
        } else {
            throw new IllegalStateException("size exceeds maximum capacity");
        }
    }

    /**
     * adds the words into the hash dictionary if it does not exist in the dictionary
     * if the word already exist then the value will be changed
     * If the words added to the hash dictionary does not exist and the hashIndex is different then the number of collision increases
     */
    @Override
    public V add(K key, V value) {

        checkIntegrity();

        if ((key == null) || (value == null)) {

            throw new IllegalArgumentException("Cannot add null to a dictionary");

        } else {

            V oldValue;
            int index = getHashIndex(key);
            int newIndex = probe(index, key);

            if(hashTable[newIndex] == null) {

                hashTable[newIndex] = new Entry<>(key, value);
                numberOfEntries++;
                oldValue = null;

                if(index != newIndex) {

                    numberOfCollision++;

                }

            } else {
            
                oldValue = hashTable[newIndex].value;
                hashTable[newIndex].value = value;
        
            }
            return oldValue;
        }
    }

    //gets the value of the word
    @Override
    public V getValue (K key) {

        checkIntegrity();
        V result = null;
        int index = getHashIndex(key);
        index = probe(index, key);

        if (hashTable[index] != null) {

            result = hashTable[index].value;

        }
        return result;
    } 

    @SuppressWarnings("unchecked")
    @Override
    public Iterator<K> getKeyIterator() {

        return new KeyIterator();
    }

    /**
     * 
     * @return the number of collision
     */
    public int getCollisionCount() {

        return numberOfCollision;
    }

    /**
     * linear probing 
     * if there is a collision then the hash index will move to the next one
     * @param index the index of the word
     * @param key the word in the hash dictionary
     * @return the hash index of the word
     */
    private int probe (int index, K key) {

        boolean found = false;

        while (!found && hashTable[index] != null) {

            if(key.equals(hashTable[index].key)) {

                found = true;

            } else {

                index = (index + 1) % hashTable.length;
            
            }
        }
        return index;
    }

    //gets the hashIndex of the word
    private int getHashIndex(K key) {

        int hashIndex = key.hashCode() % hashTable.length;
        if(hashIndex < 0) {

            hashIndex = hashIndex + hashTable.length;

        }
        return hashIndex;
    }

    //encapsulates the key and value associated with each entry in the hash dictionary
    @SuppressWarnings("hiding")
    private class Entry<K, V> {

        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;

        }
    }

    /**
     * creates an iterator tha traverses all seach key in the hash dictionary
     */
    @SuppressWarnings("rawtypes")
    private class KeyIterator implements Iterator{
        private int currentIndex;
        private int NumberLeft;

        private KeyIterator() {

            currentIndex = 0;
            NumberLeft = numberOfEntries;

        }
        
        public boolean hasNext() {
            return NumberLeft > 0;
        }

        public K next() {

            K result = null;
            
            if(hasNext()) {

                while (hashTable[currentIndex] == null) {

                    currentIndex++;

                }

                result = hashTable[currentIndex].key;
                NumberLeft--;
                currentIndex++;

            } else {
                throw new NoSuchElementException();
            }
            return result;
        }
        
    }

    //throws an SecurityException if the integrityOk fails
    private void checkIntegrity() {

        if(!integrityOK) {
            throw new SecurityException("Object is currupt");
        }
    }


    //unused methods
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public boolean contains(K key) {
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public Iterator<V> getValueIterator() {
        throw new UnsupportedOperationException("Unimplemented method 'getValueIterator'");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }

    @Override
    public int getSize() {
        throw new UnsupportedOperationException("Unimplemented method 'getSize'");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    } 
}