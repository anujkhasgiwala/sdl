package assessment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem1 {
	Map<String, Integer> wordIntegerMapping = new HashMap<String, Integer>(100);
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
		Problem1 p = new Problem1();
		String fileContents = fileContent.toString().trim();
		p.createMap(fileContents);
		p.printFileContent(fileContents);
	}
	
	private void createMap(String fileContent) {
		if (fileContent.length() > 0) {
			int counter = 1;
			for(String word : fileContent.split(" ")) {
				if(!wordIntegerMapping.containsKey(word)) {
					wordIntegerMapping.put(word, counter);
					counter++;
				}
			}
		}
	}
	
	private void printFileContent(String fileConetents) {
		String words[] = fileConetents.split(" ");
		for(int i = 0; i < words.length; i++) {
			System.out.print(wordIntegerMapping.get(words[i]) + " ");
		}
	}
}