package assessment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Problem2 {
	public static List<String> ngrams(int n, String str) {
        List<String> ngrams = new ArrayList<String>();
        String[] words = str.split(" ");
        for (int i = 0; i < words.length - n + 1; i++)
            ngrams.add(concat(words, i, i+n));
        return ngrams;
    }

    public static String concat(String[] words, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++)
            sb.append((i > start ? " " : "") + words[i]);
        return sb.toString();
    }

    public static void main(String[] args) {
    	Scanner in = new Scanner(System.in); 
        String s = in.nextLine();
		File file = new File(s);
		StringBuffer fileContent = new StringBuffer();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null)
				fileContent.append(st);
				fileContent.append(" ");
		} catch (FileNotFoundException fnf) {
			fnf.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		String fileContents = fileContent.toString().trim();
    	
        for (int n = 3; n >= 1; n--) {
        	Map<String, Integer> frequency = new HashMap<String, Integer>();
        	System.out.println("==== " + n + "-grams ====");
            for (String ngram : ngrams(n, fileContents)) {
            	if(frequency.containsKey(ngram)) {
            		frequency.put(ngram, frequency.get(ngram) + 1);
            	} else {
            		frequency.put(ngram, 1);
            	}
            }
            List list = new ArrayList(frequency.entrySet());
            Collections.sort(list, new SortFrequencyDesc());
            for(int i = 0; i < list.size(); i++) {
            	String key = ((Map.Entry<String, Integer>)list.get(i)).getKey();
            	int value = ((Map.Entry<String, Integer>)list.get(i)).getValue();
            	System.out.println(value + " " + key);
            }
            System.out.println();
        }
    }
}

class SortFrequencyDesc implements Comparator<Map.Entry<Integer, Integer>>{
	@Override
    public int compare(Map.Entry<Integer,Integer> e1, Map.Entry<Integer,Integer> e2) {
        return e2.getValue().compareTo(e1.getValue());
    }
}
