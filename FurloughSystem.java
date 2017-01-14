
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FurloughSystem {
	//test commit

	public static void main(String[] args) throws IOException {
		showTheMenu();
		Scanner scanner = new Scanner(System.in);
		String choice = chooseAnOptionFromTheMenu(scanner);
		switch (choice) {
		case "1":
			// writeDownTheInputtedInformationInAFile(choice);
			inputUserName(scanner);
			inputUserEmail(scanner);
			inputUserEGN(scanner);
			inputInitialDataOfTheFurlough(scanner);
			inputFinalDataOfTheFurlough(scanner);
			inputTypeOfTheFurlough(scanner);
			break;
		case "2":
			int numberOfTheLinesInTheFile = countsTheLinesInTheFile();
			// System.out.println(numberOfTheLinesInTheFile);
			String[] readTheDataFromTheFile = readArray(numberOfTheLinesInTheFile);
			// System.out.println(Arrays.toString(readTheDataFromTheFile));
			displayTheDataInATableFormat(numberOfTheLinesInTheFile, readTheDataFromTheFile);
			break;
		case "3":
			inputTheNameOfTheWantedPerson(scanner);
		}
	}

	private static void inputTheNameOfTheWantedPerson(Scanner scanner) throws IOException, FileNotFoundException {
		System.out.println("Âúâåäåòå èìå íà ñëóæèòåë");
		String inputtedName = scanner.nextLine();
		int counter = countsTheLinesInTheFile();
		// System.out.println(counter);
		String[] reading = readArray(counter);
		// System.out.println(Arrays.toString(reading));
		for (int i = 0; i < counter; i += 6) {
			if (inputtedName.equals(reading[i])) {
				String name2 = reading[i];
				String email2 = reading[i + 1];
				String egn2 = reading[i + 2];
				String initialDataOfTheFurlough2 = reading[i + 3];
				String finalDataOfTheFurlough2 = reading[i + 4];
				String typeOfTheFurlough2 = reading[i + 5];
				System.out.format("%1s%35s%27s%37s%47s%43s", name2, email2, egn2, initialDataOfTheFurlough2,
						finalDataOfTheFurlough2, typeOfTheFurlough2);
				System.out.println();
			}
		}
	}

	private static void showTheMenu() {
		System.out.println("----------------------------");
		System.out.println("1. Çàÿâè îòïóñêà");
		System.out.println("2. Âèæ âñè÷êè îòïóñêè");
		System.out.println("3. Âèæ îòïóñêà íà ñëóæèòåë");
		System.out.println("4. Ïðîìåíè ñòàòóñ íà îòïóñêà");
		System.out.println("5. Èçõîä");
		System.out.println("----------------------------");
	}

	private static String chooseAnOptionFromTheMenu(Scanner scanner) {
		System.out.println("Âúâåäè èçáîð: ");
		String choice = scanner.nextLine();
		while ((!Pattern.matches("1", choice)) && (!Pattern.matches("2", choice)) && (!Pattern.matches("3", choice))
				&& (!Pattern.matches("4", choice)) && (!Pattern.matches("5", choice))) {
			System.out.println("Âúâåäåíèÿò îò Âàñ íîìåð íà îïöèÿ å íåêîðåêòåí! Ìîëÿ, âúâåäåòå ãî îòíîâî.");
			choice = scanner.nextLine();
		}
		return choice;
	}

	private static void inputUserName(Scanner scanner) throws FileNotFoundException {
		System.out.println("Âúâåäåòå èìåòî ñè: ");
		String name = scanner.nextLine();
		while (!Pattern.matches(".*[À-ßà-ÿ]+.*[À-ßà-ÿ]", name)) {
			System.out.println("Âúâåäåíîòî îò Âàñ èìå å íåêîðåêòíî. Ìîëÿ, âïèøåòå íîâî èìå.");
			name = scanner.nextLine();
		}
		writeDownTheInputtedInformationInAFile(name);
	}

	private static void inputUserEmail(Scanner scanner) throws FileNotFoundException {
		System.out.println("Âúâåäåòå èìåéëà ñè: ");
		String email = scanner.nextLine();
		while ((email.indexOf("@gmail.com") == -1) && (email.indexOf("@abv.bg") == -1)
				&& (email.indexOf("@yahoo.com") == -1) && (email.indexOf("@mail.bg") == -1)) {
			System.out.println("Âúâåäåíèÿò îò âàñ èìåéë å íåêîðåêòåí. Ìîëÿ, âúâåäåòå ãî îòíîâî!");
			email = scanner.nextLine();
		}
		writeDownTheInputtedInformationInAFile(email);
	}

	private static void inputUserEGN(Scanner scanner) throws FileNotFoundException {
		System.out.println("Âúâåäåòå åäèííèÿ ñè ãðàæäàíñêè íîìåð: ");
		String egn = scanner.nextLine();
		int length = egn.length();
		while (!Pattern.matches(".*[0-9]+.*[0-9]", egn) && (length != 10)) {
			System.out.println(
					"Âúâåäåíèÿò îò Âàñ åäèíåí ãðàæäàíñêè íîìåð å íåêîðåêòåí. Ìîëÿ, âïèøåòå íîâ åäèíåí ãðàæäàíñêè íîìåð.");
			egn = scanner.nextLine();
		}
		writeDownTheInputtedInformationInAFile(egn);
	}

	private static void inputInitialDataOfTheFurlough(Scanner scanner) throws FileNotFoundException {
		System.out.println("Âúâåäåòå íà÷àëíà äàòà çà Âàøàòà îòïóñêà: ");
		String initialData = scanner.nextLine();
		while (!Pattern.matches(".*[0-9]+.*[0-9]", initialData)) {
			System.out.println(
					"Âúâåäåíàòà îò Âàñ íà÷àëíà äàòà íà Âàøàòà îòïóñêà å íåêîðåêòíà. Ìîëÿ, âïèøåòå íîâà íà÷àëíà äàòà íà Âàøàòà îòïóñêà.");
			initialData = scanner.nextLine();
		}
		writeDownTheInputtedInformationInAFile(initialData);

	}

	private static void inputFinalDataOfTheFurlough(Scanner scanner) {
		System.out.println("Âúâåäåòå êðàéíà äàòà çà Âàøàòà îòïóñêà: ");
		String finalData = scanner.nextLine();
		while (!Pattern.matches(".*[0-9]+.*[0-9]", finalData)) {
			System.out.println(
					"Âúâåäåíàòà îò Âàñ êðàéíà äàòà íà Âàøàòà îòïóñêà å íåêîðåêòíà. Ìîëÿ, âïèøåòå íîâà êðàéíà äàòà íà Âàøàòà îòïóñêà.");
			finalData = scanner.nextLine();
		}
		writeDownTheInputtedInformationInAFile(finalData);
	}

	private static void inputTypeOfTheFurlough(Scanner scanner) throws FileNotFoundException {
		System.out.println("Çàäàéòå òèï íà èñêàíàòà îò Âàñ îòïóñêà (ïëàòåíà / íåïëàòåíà)");
		String typeOfTheFurlough = scanner.nextLine();
		while ((!Pattern.matches("ïëàòåíà", typeOfTheFurlough)) && (!Pattern.matches("íåïëàòåíà", typeOfTheFurlough))) {
			System.out.println("Âúâåäåíèÿò îò Âàñ òèï íà îòïóñêàòà å íåêîðåêòåí. Ìîëÿ, âïèøåòå íîâ òèï.");
			typeOfTheFurlough = scanner.nextLine();
		}
		writeDownTheInputtedInformationInAFile(typeOfTheFurlough);
	}

	private static void writeDownTheInputtedInformationInAFile(String scanner) {
		FileWriter writeDownInAFile;
		try {
			writeDownInAFile = new FileWriter("UserInformation.txt", true);
			writeDownInAFile.write(scanner);
			writeDownInAFile.write("\r\n");
			writeDownInAFile.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private static int countsTheLinesInTheFile() throws IOException {
		BufferedReader line = new BufferedReader(new FileReader("UserInformation.txt"));
		String singleLineOfTheFile;
		int numberOfTheLinesInTheFile = 0;
		try {
			while ((singleLineOfTheFile = line.readLine()) != null) {
				numberOfTheLinesInTheFile++;
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		line.close();
		return numberOfTheLinesInTheFile;
	}

	private static String[] readArray(int numberOfTheLinesInTheFile) throws FileNotFoundException {
		BufferedReader line = new BufferedReader(new FileReader("UserInformation.txt"));
		String[] readTheDataFromTheFile = new String[numberOfTheLinesInTheFile];
		try {
			for (int i = 0; i < numberOfTheLinesInTheFile; i++) {
				readTheDataFromTheFile[i] = line.readLine();
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

		return readTheDataFromTheFile;
	}

	private static void displayTheDataInATableFormat(int numberOfTheLinesInTheFile, String[] dataOfTheFile) {
		System.out.format("%5s%30s%30s%50s%45s%40s", "Èìå", "Èìåéë", "ÅÃÍ", "Íà÷àëíà äàòà íà îïòóñêàòà",
				"Êðàéíà äàòà íà îòïóñêàòà", "Òèï íà îòïóñêàòà");
		System.out.println();
		for (int i = 0; i < numberOfTheLinesInTheFile; i += 6) {
			String name = dataOfTheFile[i];
			String email = dataOfTheFile[i + 1];
			String egn = dataOfTheFile[i + 2];
			String initialDataOfTheFurlough = dataOfTheFile[i + 3];
			String finalDataOfTheFurlough = dataOfTheFile[i + 4];
			String typeOfTheFurlough = dataOfTheFile[i + 5];
			System.out.format("%1s%35s%27s%37s%47s%43s", name, email, egn, initialDataOfTheFurlough,
					finalDataOfTheFurlough, typeOfTheFurlough);
			System.out.println();
		}
	}
}
