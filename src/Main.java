
import Trie.Trie;

public class Main {

    public static void main(String args[]) 
	{
    	    	
		Trie obj1 = new Trie();
		
		String[] words = {"apple", "tea", "test", "tester", "ten", "testing", "tennant", 
						  "tenacity", "tentacle", "tend", "tenders", "tend", "tending", 
						  "tender", "tend", "test"};
		
		for(int i = 0; i < words.length; i++)
		{
			obj1.insert(words[i]);
		}
		
		obj1.predict("te", 3);
	} 

}
