import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LogFileParser {

	public static void main(String[] args) {

		BufferedReader fileReader = null;
		BufferedReader bufferRead;
		String URL;
		int hourOfDay = 0;
		int profilerTimePerHour = 0;

		try {

			System.out.print("Enter the URI here : ");

			bufferRead = new BufferedReader(new InputStreamReader(System.in));
			URL = "URI=[" + bufferRead.readLine() + "]";
			String sCurrentLine;

			String homeDirectory = System.getProperty("user.home");
			String fileName = "therap.log.ms-2.2013-10-21";

			fileReader = new BufferedReader(new FileReader(homeDirectory + "/"
					+ fileName));

			while ((sCurrentLine = fileReader.readLine()) != null) {

				if (sCurrentLine.contains(URL)) {

					if (Integer.parseInt(sCurrentLine.split(" ")[1].substring(
							0, 2)) == hourOfDay) {
						profilerTimePerHour += Integer.parseInt(sCurrentLine
								.split(" ")[16].split("=")[1].replaceAll("ms",
								""));
					} else {
						System.out.println("Total time in  hour " + hourOfDay
								+ " is : " + profilerTimePerHour + " ms");
						profilerTimePerHour = 0;
						profilerTimePerHour += Integer.parseInt(sCurrentLine
								.split(" ")[16].split("=")[1].replaceAll("ms",
								""));
						hourOfDay++;
					}

				}

			}
			System.out.println("Total time in  hour " + hourOfDay + " is : "
					+ profilerTimePerHour + " ms");

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}