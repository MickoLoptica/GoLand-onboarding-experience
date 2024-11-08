package solution;

import java.io.*;
import java.util.*;

public class IndexingService {

	private Map<String, List<String>> index;
	
	public IndexingService() {
		index = new HashMap<String, List<String>>();
	}
	
	public void indexFiles(List<File> files) {
		for (File file : files) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = br.readLine()) != null) {
					String[] words = line.split("[,\\.\\s:\"?!-]");
					for (String word : words) {
						if (!word.isEmpty()) {
							word = word.toLowerCase();
							if (!index.containsKey(word)) {
								index.put(word, new ArrayList<String>());
							}
							if (!index.get(word).contains(file.getName())) {
								index.get(word).add(file.getName());
							}
						}
					}
				}
			}
			catch (IOException e) {
				System.out.println("Error reading file " + file.getName());
			}
		}
	}
	
	public List<String> searchWord(String word) {
		return index.getOrDefault(word.toLowerCase(), Collections.emptyList());
	}
	
	public void saveIndexToFile(String outputPath) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath))) {
			for (Map.Entry<String, List<String>> entry: index.entrySet()) {
				writer.println(entry.getKey() + " - " + String.join(", ", entry.getValue()));
			}
		}
		catch (IOException e) {
			System.out.println("Error while writing index to file");
		}
	}
	
}
