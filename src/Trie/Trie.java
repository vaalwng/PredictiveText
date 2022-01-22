package Trie;
// Author: Val Evander M. Wong
// Class: 3345 - Prof. Stallbohm
// Project Title: Trie Data Structure
// Date: April 5, 2020

import java.util.*;

public class Trie {
	
	class TrieNode {
		
	    char value;																		// alphabet value associated to this node (a-z)
	    boolean isEnd;																	// boolean value to determine whether this is the end of a word
	    int count;																		// number of times this node has been visited through
	    HashMap<Character, TrieNode> children;											// an array of Nodes that are the children of that current Node	

	    public TrieNode() { }
	 
	    public TrieNode(char c){
	        this.value = c;
	        this.count = 0;
	        this.isEnd = false;
	        this.children = new HashMap<Character, TrieNode>();
	    }
	    
	    public char getChar()
	    {
	    	return this.value;
	    }
	    	    	    	    
	}
	
    private TrieNode root;
 
    public Trie() {
        root = new TrieNode(' ');
    }
 
    public void insert(String word) 
    {
    	word = word.toLowerCase();
    	
        HashMap<Character, TrieNode> children = root.children;
        
        TrieNode current;      
        char c;
        
        for(int i = 0; i<word.length(); i++)
        {
            c = word.charAt(i);
     
            if(children.containsKey(c))
            {
                    current = children.get(c);
            }
            else
            {
                current = new TrieNode(c);
                children.put(c, current);
            }
 
            if(i == word.length() - 1)
            {
                current.isEnd = true;
                current.count++;
            }         
            children = current.children;
        }
    }
 
    public boolean find(String word) 
    {
    	word = word.toLowerCase();
    	
        TrieNode current = findNode(word);
 
        if(current != null && current.isEnd)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
  
    public TrieNode findNode(String word)
    {
    	
        HashMap<Character, TrieNode> children = root.children;     
        TrieNode current = null;
        char c;
        
        for(int i = 0; i < word.length(); i++){
        	
        	c = word.charAt(i);
            
            if(children.containsKey(c))
            {
                current = children.get(c);
                children = current.children;
            }
            else
            {
                return null;
            }
        }
 
        return current;
    }
                
    public HashMap<String, Integer> sortByValueReverse(HashMap<String, Integer> hashMap)
    {    	
    	List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hashMap.entrySet());
    	
    	Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
    	{
    		public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
    		{
    			return (o2.getValue().compareTo(o1.getValue()));
    		}
    	});
    	
    	HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
    	
    	for(Map.Entry<String, Integer> aa : list)
    	{
    		temp.put(aa.getKey(), aa.getValue());
    	}
    	
    	return temp;
    }   
  
    public void printRootChildren() 
    {
    	System.out.println(root.children);
    }
        
    public void preOrderTraversalHelper(List<String> listOfWords, TrieNode current, String prefix)
    {
    	for(Map.Entry<Character, TrieNode> each : current.children.entrySet())
    	{
    		char temp = each.getKey();
    		preOrderTraversalHelper(listOfWords, each.getValue(), prefix + temp);
    		current = each.getValue();
    		if(current.count > 0)
    		{
    			listOfWords.add(prefix + temp);
    		}
    	}    	
    }
    
    public List<String> preOrderTraversal(String prefix)
    {
    	List<String> listOfWords = new ArrayList<String>();
    	
    	TrieNode lastCharNode = findNode(prefix);
    	
    	preOrderTraversalHelper(listOfWords, lastCharNode, prefix);
    	
    	return listOfWords;
    }
    
    public void predict(String prefix, int n)
    {
    	int x = n;
    	
    	prefix = prefix.toLowerCase();
    	
    	List<String> listOfWords = preOrderTraversal(prefix);
    	
    	HashMap<String, Integer> counts = new HashMap<String, Integer>();
    	
    	for(String temp : listOfWords)
    	{
    		counts.put(temp, findNode(temp).count);
    	}
    	
    	counts = sortByValueReverse(counts);
    	    	    	
    	List<String> topNWords = new ArrayList<String>();
    	
    	for(Map.Entry<String, Integer> entry : counts.entrySet())
    	{
    		if(n == 0)
    		{
    			break;
    		}
    		else
    		{
    			topNWords.add(entry.getKey());
    			n--;
    		}
    	}
    	   
    	System.out.print("Top " + x + " word(s) with prefix '" + prefix + "' : " + topNWords);
    }
}