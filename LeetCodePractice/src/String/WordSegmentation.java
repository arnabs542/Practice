package String;

import java.util.ArrayList;

/*Given a long string S, only include normal English words, words are separated by a single space, and give you a positive integer. Please divide the string into several lines and the number of lines is minimum. Requirement 1: You can only wrap between words. The same word cannot be separated; Requirement 2: Each line cannot be more than K unique characters after the division.

Example
Give s="aaaa bbb cccc ddd ee ff ggggg", k=8,return ["aaaa bbb","cccc ddd","ee ff","ggggg"]

Notice
String length does not exceed 100000
Data guarantee legal*/
public class WordSegmentation {
	/**
     * @param s: the string
     * @param k: the k
     * @return: the answer
     */
    public String[] wordSegmentation(String s, int k) {
        // Write your code here
    	ArrayList<String> res = new ArrayList<>();
    	String[] words =s.split("\\s+");
    	String temp = "";
    	for(String word : words){
    		if(temp.isEmpty()){
    			temp = temp + word;
    		} else {
    			if(temp.length() + word.length() + 1 <= k){
    				temp = temp + " " + word;
    			} else {
    				res.add(temp);
    				temp = word;
    			}
    		}
    	}
    	res.add(temp);
    	return res.toArray(new String[0]);
    }
    
    public String[] wordSegmentationWithSB(String s, int k) {
        // Write your code here
    	ArrayList<String> res = new ArrayList<>();
    	String[] words =s.split("\\s+");
    	StringBuilder sb = new StringBuilder();
    	for(String word : words){
    		if(sb.length() == 0){
    			sb.append(word);
    		} else {
    			if(word.length() + sb.length() + 1 <= k){
    				sb.append(" ");
        			sb.append(word);
        		} else {
        			res.add(sb.toString());
        			sb.setLength(0);
        			sb.append(word);
        		}
    		}
    	}
    	res.add(sb.toString());
    	return res.toArray(new String[0]);
    }
}
