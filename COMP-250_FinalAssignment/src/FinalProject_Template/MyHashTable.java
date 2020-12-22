package FinalProject_Template;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class MyHashTable<K,V> implements Iterable<HashPair<K,V>>{
    // num of entries to the table
    private int numEntries;
    // num of buckets 
    private int numBuckets;
    // load factor needed to check for rehashing 
    private static final double MAX_LOAD_FACTOR = 0.75;
    // ArrayList of buckets. Each bucket is a LinkedList of HashPair
    private ArrayList<LinkedList<HashPair<K,V>>> buckets; 
    
    // constructor
    public MyHashTable(int initialCapacity) {
        // ADD YOUR CODE BELOW THIS
        this.numBuckets = initialCapacity;
        this.numEntries = 0;
        buckets = new ArrayList<LinkedList<HashPair<K,V>>>();
        for (int i = 0; i < initialCapacity; i++) {
        	buckets.add(i, new LinkedList<HashPair<K,V>>());
        }
        //ADD YOUR CODE ABOVE THIS
    }
    
    public int size() {
        return this.numEntries;
    }
    
    public boolean isEmpty() {
        return this.numEntries == 0;
    }
    
    public int numBuckets() {
        return this.numBuckets;
    }
    
    /**
     * Returns the buckets variable. Useful for testing  purposes.
     */
    public ArrayList<LinkedList< HashPair<K,V> > > getBuckets(){
        return this.buckets;
    }
    
    /**
     * Given a key, return the bucket position for the key. 
     */
    public int hashFunction(K key) {
        int hashValue = Math.abs(key.hashCode())%this.numBuckets;
        return hashValue;
    }
    
    /**
     * Takes a key and a value as input and adds the corresponding HashPair
     * to this HashTable. Expected average run time  O(1)
     */
    public V put(K key, V value) {
        //  ADD YOUR CODE BELOW HERE
    	
    	int bucketId = hashFunction(key);
    	V returnValue = null;
    	if (this.getBuckets().get(bucketId).size() == 0) {
    		HashPair<K,V> newHashPair = new HashPair<K,V>(key, value);
    		this.getBuckets().get(bucketId).add(newHashPair);
    		returnValue = value;
    		this.numEntries += 1;
    	}
    	else {
    		for (int i = 0; i < this.getBuckets().get(bucketId).size(); i++) {
    			if (this.getBuckets().get(bucketId).get(i).getKey().equals(key)) {
    				V oldValue = this.getBuckets().get(bucketId).get(i).getValue();
    				this.getBuckets().get(bucketId).get(i).setValue(value);
    				returnValue = oldValue;
    				break;
    			}	
    		}
    		
    		if (returnValue == null) {
    			HashPair<K,V> newHashPair = new HashPair<K,V>(key, value);
	    		this.getBuckets().get(bucketId).add(newHashPair);
	    		this.numEntries += 1;
	    		returnValue = value;
	    		
    		}
    	}
    	if (((double)numEntries/(double)numBuckets) > MAX_LOAD_FACTOR) {
    		this.rehash();
    	}
    	return returnValue;
        
        //  ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Get the value corresponding to key. Expected average runtime O(1)
     */
    
    public V get(K key) {
        //ADD YOUR CODE BELOW HERE
       int bucketId = hashFunction(key);
       V returnValue = null;
       for (int i = 0; i < this.getBuckets().get(bucketId).size(); i++) {
    		   if (this.getBuckets().get(bucketId).get(i).getKey().equals(key)) {
    			   returnValue = this.getBuckets().get(bucketId).get(i).getValue();
    		   }
    	   }
       
       return returnValue;
    	
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Remove the HashPair corresponding to key . Expected average runtime O(1) 
     */
    public V remove(K key) {
        //ADD YOUR CODE BELOW HERE
    	int bucketId = hashFunction(key);
        V returnValue = null;
        
        if (this.getBuckets().get(bucketId).equals(null)) {
        	returnValue = null;
        }
        
        for (int i = 0; i < this.getBuckets().get(bucketId).size(); i++) {
        	if (this.getBuckets().get(bucketId).get(i).getKey().equals(key)) {
        		V oldValue = this.getBuckets().get(bucketId).get(i).getValue();
        		this.getBuckets().get(bucketId).remove(i);
        		returnValue = oldValue;
        		this.numEntries -= 1;
        		}
      
        }
        return returnValue;
        //ADD YOUR CODE ABOVE HERE
    }
 
    
    /** 
     * Method to double the size of the hashtable if load factor increases
     * beyond MAX_LOAD_FACTOR.
     * Made public for ease of testing.
     * Expected average runtime is O(m), where m is the number of buckets
     */
    public void rehash() {
        //ADD YOUR CODE BELOW HERE
    	
    		ArrayList<LinkedList<HashPair<K,V>>> tempBuckets = buckets;
        	buckets = new ArrayList<LinkedList<HashPair<K,V>>>();
        	for (int i = 0; i < 2 * numBuckets; i++) {
        		buckets.add(i, new LinkedList<HashPair<K,V>>());
        	}
        	this.numEntries = 0;
        	this.numBuckets *= 2;
        	
        	for (int i = 0; i < tempBuckets.size(); i++) {
        		for (int j = 0; j < tempBuckets.get(i).size(); j++) {
        			K key = tempBuckets.get(i).get(j).getKey();
        			V value = tempBuckets.get(i).get(j).getValue();
        			this.put(key, value);
        			//this.numEntries +=1;
        		}
        	}
        		
    	
    	
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Return a list of all the keys present in this hashtable.
     * Expected average runtime is O(m), where m is the number of buckets
     */
    
    public ArrayList<K> keys() {
        //ADD YOUR CODE BELOW HERE
    	ArrayList <K> keysArrayList = new ArrayList <K>();
    	for (int i = 0; i < this.buckets.size(); i++) {
    		for (int j = 0; j < this.buckets.get(i).size(); j++) {
    			keysArrayList.add(this.buckets.get(i).get(j).getKey());
    		}
    	}
    		
        
    	return keysArrayList;
    	
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Returns an ArrayList of unique values present in this hashtable.
     * Expected average runtime is O(m) where m is the number of buckets
     */
    public ArrayList<V> values() {
        //ADD CODE BELOW HERE
    	MyHashTable<V, Integer> valuesHashTable = new MyHashTable<V, Integer>(10);
    	
    	ArrayList <V> valuesArrayList = new ArrayList<V>();
    	for (int i = 0; i < buckets.size(); i++) {
    		for (int j = 0; j < buckets.get(i).size(); j++) {
    			Boolean hasDuplicate = false;
    			int bucketId = valuesHashTable.hashFunction(buckets.get(i).get(j).getValue());
    			for (int k = 0; k < valuesHashTable.getBuckets().get(bucketId).size(); k++) {
    				if (valuesHashTable.getBuckets().get(bucketId).get(k).getKey().equals(buckets.get(i).get(j).getValue())) {
    					hasDuplicate = true;
    					break;
    				}
    			}
    			if (!hasDuplicate) {
    				valuesArrayList.add(buckets.get(i).get(j).getValue());
    				valuesHashTable.put(buckets.get(i).get(j).getValue(), 1);
    			}
    		}
    	}
    	return valuesArrayList;
    	
        //ADD CODE ABOVE HERE
    }
    
    
	/**
	 * This method takes as input an object of type MyHashTable with values that 
	 * are Comparable. It returns an ArrayList containing all the keys from the map, 
	 * ordered in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n^2), where n is the number 
	 * of pairs in the map. 
	 */
    public static <K, V extends Comparable<V>> ArrayList<K> slowSort (MyHashTable<K, V> results) {
        ArrayList<K> sortedResults = new ArrayList<>();
        for (HashPair<K, V> entry : results) {
			V element = entry.getValue();
			K toAdd = entry.getKey();
			int i = sortedResults.size() - 1;
			V toCompare = null;
        	while (i >= 0) {
        		toCompare = results.get(sortedResults.get(i));
        		if (element.compareTo(toCompare) <= 0 )
        			break;
        		i--;
        	}
        	sortedResults.add(i+1, toAdd);
        }
        return sortedResults;
    }
    
    
	/**
	 * This method takes as input an object of type MyHashTable with values that 
	 * are Comparable. It returns an ArrayList containing all the keys from the map, 
	 * ordered in descending order based on the values they mapped to.
	 * 
	 * The time complexity for this method is O(n*log(n)), where n is the number 
	 * of pairs in the map. 
	 */
    
    public static <K, V extends Comparable<V>> ArrayList<K> fastSort(MyHashTable<K, V> results) {
        //ADD CODE BELOW HERE
    	ArrayList <K> sortedResults = new ArrayList<>();
    	ArrayList <HashPair<K,V>> list = new ArrayList<HashPair<K,V>>();
    	for (int i = 0; i < results.getBuckets().size(); i++) {
    		for (int j = 0; j < results.getBuckets().get(i).size(); j++) {
    			list.add(results.getBuckets().get(i).get(j));
    		}
    	}
    	sort(list, 0, list.size() - 1);
    	for (int i = 0; i < list.size(); i++) {
    		sortedResults.add(list.get(i).getKey());
    	}
    	//sortedResults.add(null);
    	return sortedResults;
		
        //ADD CODE ABOVE HERE
    }
    
    private static <K, V extends Comparable<V>> void merge(ArrayList<HashPair<K,V>> list, int left, int middle, int right) {
    	// Find size of two subarrays to be merged
    	int n1 = middle - left + 1;
    	int n2 = right - middle;
    	
    	//Create temp arrays
    	ArrayList <HashPair<K,V>> leftArray = new ArrayList<HashPair<K,V>>();
    	ArrayList <HashPair<K,V>> rightArray = new ArrayList<HashPair<K,V>>();
    	
    	//Copy data to temp arrays
    	for (int i = 0; i < n1; i++) {
    		leftArray.add(i, list.get(left +i));
    	}
    	for (int i = 0; i < n2; i++) {
    		rightArray.add(i, list.get(middle + 1 + i));
    	}
    	
    	/* Merge the temp Arrays*/
    	// Initial indexes of first and second subarrays
    	int leftIndex = 0; 
    	int rightIndex = 0;
    	
    	for (int i = left; i < right + 1; i++) {
    		if (leftIndex < leftArray.size() && rightIndex < rightArray.size()) {
    			if (leftArray.get(leftIndex).getValue().compareTo(rightArray.get(rightIndex).getValue()) >= 0) {
    				list.set(i, leftArray.get(leftIndex));
    				leftIndex ++;
    			}else{
    				list.set(i, rightArray.get(rightIndex));
    				rightIndex++;
    			}
    		}else if(leftIndex < leftArray.size()){
    			list.set(i, leftArray.get(leftIndex));
				leftIndex ++;
    		}else if(rightIndex < rightArray.size()) {
    			list.set(i, rightArray.get(rightIndex));
				rightIndex++;
    		}
    	}
    	
    
    	
    	
    	
    }
    
    private static <K, V extends Comparable<V>> void sort(ArrayList<HashPair<K,V>> list, int left, int right) {
    	if (left < right) {
    		int middle = (left + right)/2;
    		sort(list, left, middle);
    		sort(list, middle + 1, right);
    		
    		merge(list, left, middle, right);
    	}
    }

    
    
    
    @Override
    public MyHashIterator iterator() {
        return new MyHashIterator();
    }   
    
    private class MyHashIterator implements Iterator<HashPair<K,V>> {
        //ADD YOUR CODE BELOW HERE
    	ArrayList <HashPair <K,V>> list = new ArrayList <HashPair <K,V>>();
    	private int currentSize;
        //ADD YOUR CODE ABOVE HERE
    	
    	/**
    	 * Expected average runtime is O(m) where m is the number of buckets
    	 */
        private MyHashIterator() {
            //ADD YOUR CODE BELOW HERE
        	for (int i = 0; i < buckets.size(); i++) {
        		for (int j = 0; j < buckets.get(i).size(); j++) {
        			list.add(buckets.get(i).get(j));
        		}
        	}
        	this.currentSize = 0;
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        /**
         * Expected average runtime is O(1)
         */
        public boolean hasNext() {
            //ADD YOUR CODE BELOW HERE
        	
        	return (currentSize < list.size());
        	
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        /**
         * Expected average runtime is O(1)
         */
        public HashPair<K,V> next() {
            //ADD YOUR CODE BELOW HERE
        	
        	return list.get(currentSize ++);
        	
            //ADD YOUR CODE ABOVE HERE
        }
        
    }
}
