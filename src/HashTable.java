public class HashTable {
    private int ts;
    private int currentSize;
    private HashEntry[] table;
    private int primeSize;

    /* Constructor */
    public HashTable(int ts) {
        currentSize = 0;
        this.ts = ts;
        table = new HashEntry[this.ts];
        for (int i = 0; i < this.ts; i++)
            table[i] = null;
        primeSize = getPrime();
    }

    /* Function to get prime number less than table size for myhash2 function */
    public int getPrime() {
        for (int i = ts - 1; i >= 1; i--)
        {
            int fact = 0;
            for (int j = 2; j <= (int) Math.sqrt(i); j++)
                if (i % j == 0)
                    fact++;
            if (fact == 0)
                return i;
        }
        /* Return a prime number */
        return 3;
    }
    //empty hash table contents by setting every value to null
    public void empty() {
        currentSize = 0;
        for (int i = 0; i < ts; i++) {
            table[i] = null;
        }
    }

    //gets value of corresponding key
    public int get(String key) {
        int hash1 = firsthash(key);
        int hash2 = doublehash(key);

        while (table[hash1] != null && !table[hash1].key.equals(key)) {
            hash1 += hash2;
            hash1 %= ts;
        }
        return table[hash1].value;
    }
    // insert a value and corresponding key into table
    public void insert(String key, int value) {
        //if table is full
        if (currentSize == ts) {
            System.out.println("Table full");
            return;
        }
        //else hash to available slot
        int hash1 = firsthash(key);
        int hash2 = doublehash(key);
        while (table[hash1] != null) {
            hash1 += hash2;
            hash1 %= ts;
        }
        //store key and value in table and increment size of table
        table[hash1] = new HashEntry(key, value);
        currentSize++;
    }

    // to remove a a value and corresponding key
    public void remove(String key) {
        int hash1 = firsthash(key);
        int hash2 = doublehash(key);

        //keeps going until the key is found
        while (table[hash1] != null && !table[hash1].key.equals(key)) {
            hash1 += hash2;
            hash1 %= ts;
        }
        //sets specific location to null and decrements current size of table
        table[hash1] = null;
        currentSize--;
    }

    //checks if the table is empty or not
    public boolean isEmpty() {
        if(currentSize != 0) {
            return false;
        }
        return true;
    }

    // myhash1 which gives a hash value for a string
    private int firsthash(String x ){
        int hashVal = x.hashCode();
        hashVal %= ts;
        if (hashVal < 0) {
            hashVal += ts;
        }
        return hashVal;
    }
    //doublehash method for double hashing
    private int doublehash(String s) {
        int hashValue = s.hashCode();
        hashValue %= ts;
        if (hashValue < 0)
            hashValue += ts;
        return primeSize - hashValue % primeSize;
    }

    //gets current size of table, which does not include null values
    public int getSize() {
        return currentSize;
    }

    // prints contents of hash table
    public void printHashTable() {
        for (int i = 0; i < ts; i++) {
            if (table[i] != null) {
                System.out.println(table[i].key + "\t" + table[i].value);
            }
        }
    }
}
