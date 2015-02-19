import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

	public static void main(String[] args) {

		BufferedReader reader = null;
		BufferedReader bufferRead;
		String URL;
		int[] myArray = new int[24];

		try {

			System.out.print("Enter the URI here : ");

			bufferRead = new BufferedReader(new InputStreamReader(System.in));
			URL = "URI=[" + bufferRead.readLine() + "]";
			String sCurrentLine;

			String homeDirectory = System.getProperty("user.home");
			String fileName = "therap.log.ms-2.2013-10-21";

			reader = new BufferedReader(new FileReader(homeDirectory + "/"
					+ fileName));

			while ((sCurrentLine = reader.readLine()) != null) {

				if (sCurrentLine.contains(URL)) {

					myArray[Integer.parseInt(sCurrentLine.split(" ")[1]
							.substring(0, 2))] += Integer.parseInt(sCurrentLine
							.split(" ")[16].split("=")[1].replaceAll("ms", ""));

				}

			}
			for (int i = 0; i < 24; i++) {
				System.out.println("Total time in  hour " + i + " is : "
						+ myArray[i] + " ms");
			}

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}