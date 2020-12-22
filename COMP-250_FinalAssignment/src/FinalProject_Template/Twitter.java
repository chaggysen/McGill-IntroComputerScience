package FinalProject_Template;

import java.util.ArrayList;

public class Twitter {
	
	//ADD YOUR CODE BELOW HERE
	private MyHashTable <String, Tweet> tweetsByAuthor;
	private MyHashTable <String, ArrayList<Tweet>> tweetsByDate;
	private ArrayList <Tweet> tweets;
	private ArrayList <String> stopWords;
	//ADD CODE ABOVE HERE 
	
	// O(n+m) where n is the number of tweets, and m the number of stopWords
	public Twitter(ArrayList<Tweet> tweets, ArrayList<String> stopWords) {
		//ADD YOUR CODE BELOW HERE
		tweetsByAuthor = new MyHashTable<String, Tweet>(tweets.size());
		tweetsByDate = new MyHashTable<String, ArrayList<Tweet>>(tweets.size());
		this.tweets = new ArrayList<Tweet>();
		for (int i = 0; i < tweets.size(); i++) {
			this.tweetsByAuthor.put(tweets.get(i).getAuthor(), tweets.get(i));
			if (this.tweetsByDate.keys().contains(tweets.get(i).getDateAndTime().substring(0, 10))) {
				this.tweetsByDate.get(tweets.get(i).getDateAndTime().substring(0, 10)).add(tweets.get(i));
			}
			else {
				this.tweetsByDate.put(tweets.get(i).getDateAndTime().substring(0, 10), new ArrayList<Tweet>());
				this.tweetsByDate.get(tweets.get(i).getDateAndTime().substring(0, 10)).add(tweets.get(i));
			}
			this.tweets.add(tweets.get(i));
		}
		this.stopWords = stopWords;
		//ADD CODE ABOVE HERE 
	}
	
	
    /**
     * Add Tweet t to this Twitter
     * O(1)
     */
	public void addTweet(Tweet t) {
		//ADD CODE BELOW HERE
		this.tweetsByAuthor.put(t.getAuthor(), t);
		this.tweetsByDate.get(t.getDateAndTime().substring(0, 10)).add(t);
		//ADD CODE ABOVE HERE 
	}
	

    /**
     * Search this Twitter for the latest Tweet of a given author.
     * If there are no tweets from the given author, then the 
     * method returns null. 
     * O(1)  
     */
    public Tweet latestTweetByAuthor(String author) {
        //ADD CODE BELOW HERE
    	Tweet currentLatestTweet = null;
    	int bucketId = tweetsByAuthor.hashFunction(author);
    	for(int i = 0; i < tweetsByAuthor.getBuckets().get(bucketId).size(); i++) {
    		if (tweetsByAuthor.getBuckets().get(bucketId).get(i).getKey().equals(author)){
    			currentLatestTweet = tweetsByAuthor.getBuckets().get(bucketId).get(i).getValue();
    		}
    	}
    	
    	return currentLatestTweet;
    	

        //ADD CODE ABOVE HERE 
    }


    /**
     * Search this Twitter for Tweets by `date' and return an 
     * ArrayList of all such Tweets. If there are no tweets on 
     * the given date, then the method returns null.
     * O(1)
     */
    public ArrayList<Tweet> tweetsByDate(String date) {
        //ADD CODE BELOW HERE
    	ArrayList <Tweet> tweetsThatDate = new ArrayList <Tweet>();
    	int bucketId = tweetsByDate.hashFunction(date);
    	for (int i = 0; i < tweetsByDate.getBuckets().get(bucketId).size(); i++) {
    		if (tweetsByDate.getBuckets().get(bucketId).get(i).getKey().substring(0,10).equals(date)) {
    			//while(tweetsByDate.getBuckets().get(bucketId).iterator().hasNext()) {
    				tweetsThatDate = tweetsByDate.getBuckets().get(bucketId).get(i).getValue();
    			//}
    			return tweetsThatDate;
    		}
    		else {
    			return null;
    		}
    	}
    	return tweetsThatDate;
        //ADD CODE ABOVE HERE
    }
    
	/**
	 * Returns an ArrayList of words (that are not stop words!) that
	 * appear in the tweets. The words should be ordered from most 
	 * frequent to least frequent by counting in how many tweet messages
	 * the words appear. Note that if a word appears more than once
	 * in the same tweet, it should be counted only once. 
	 */
    public ArrayList<String> trendingTopics() {
        //ADD CODE BELOW HERE
    	ArrayList <String> orderedWords = new ArrayList <String>();
    	MyHashTable <String, Integer> words = new MyHashTable <String, Integer>(20);
    	for (int i = 0; i < this.tweets.size(); i++) {
    		for (int j = 0; j < getWords(this.tweets.get(i).getMessage()).size(); j++) {
    			String word = getWords(this.tweets.get(i).getMessage()).get(j).toLowerCase();
    			Boolean added = false;
    			for (int x = 0; x< j; x++) {
    				if (getWords(this.tweets.get(i).getMessage()).get(x).toLowerCase().equals(word.toLowerCase())) {
    					added = true;
    				}
    			}
    			if (!words.keys().contains(word) && !stopWords.contains(word) && added == false) {
    				words.put(word, 1);
    			}else if (words.keys().contains(word) && !stopWords.contains(word) && added == false){
    				int bucketId = words.hashFunction(word);
    				for (int k = 0; k < words.getBuckets().get(bucketId).size(); k++) {
    					if (words.getBuckets().get(bucketId).get(k).getKey().equals(word)){
    						int oldValue = words.getBuckets().get(bucketId).get(k).getValue();
        					words.getBuckets().get(bucketId).get(k).setValue(oldValue + 1);
    					}
    				}
    			}
    		}
    	}
    	words.fastSort(words);
    	orderedWords = words.fastSort(words);
    	return orderedWords;
        //ADD CODE ABOVE HERE    	
    }
    
    
    
    /**
     * An helper method you can use to obtain an ArrayList of words from a 
     * String, separating them based on apostrophes and space characters. 
     * All character that are not letters from the English alphabet are ignored. 
     */
    private static ArrayList<String> getWords(String msg) {
    	msg = msg.replace('\'', ' ');
    	String[] words = msg.split(" ");
    	ArrayList<String> wordsList = new ArrayList<String>(words.length);
    	for (int i=0; i<words.length; i++) {
    		String w = "";
    		for (int j=0; j< words[i].length(); j++) {
    			char c = words[i].charAt(j);
    			if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
    				w += c;
    			
    		}
    		wordsList.add(w);
    	}
    	return wordsList;
    }

    

}
