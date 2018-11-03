import java.io.File;
import java.util.Scanner;

public class ReadFile {
	ReadFile() {
		//unused.
	}
	public static void main(String[] args) throws Exception {
		File file = new File("WebContext.txt");
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine()) {
			System.out.println(scan.nextLine());
		}
	}
}