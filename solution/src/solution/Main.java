package solution;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		IndexingService indexingService = new IndexingService();
		List<File> files = new ArrayList<File>();
		while (true) {
			System.out.println("Select operation:");
			System.out.println("1 - Create index");
			System.out.println("2 - Find files containing a given word");
			System.out.println("0 - Exit");
			String op = scanner.nextLine();
			if (op.equalsIgnoreCase("0")) {
				break;
			}
			else if (op.equalsIgnoreCase("1")) {
				System.out.println("Please type paths to files for indexing (type '!' for end of the files list): " );
				while (true) {
					String input = scanner.nextLine();
					if (input.equalsIgnoreCase("!")) {
						break;
					}
					files.add(new File(input));
				}
				indexingService.indexFiles(files);
				System.out.println("Where do you want to save the index?");
				String outputPath = scanner.nextLine();
				indexingService.saveIndexToFile(outputPath);
				System.out.println("Index was successfully saved!");
			}
			else {
				while (true) {
					System.out.println("Please type the word you want to find (type '!' to end the search): ");
					String searchWord = scanner.nextLine();
					if (searchWord.equalsIgnoreCase("!")) {
						break;
					}
					List<String> results = indexingService.searchWord(searchWord);
					if (results.isEmpty()) {
						System.out.println("Word was not found in the index");
					}
					else {
						System.out.println("Word was found in these files: " + String.join(", ", results));
					}
				}
			}
		}
		scanner.close();
	}

}
