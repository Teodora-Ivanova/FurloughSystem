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

	private static void showTheMenu() {
		System.out.println("----------------------------");
		System.out.println("1. Заяви отпуска");
		System.out.println("2. Виж всички отпуски");
		System.out.println("3. Виж отпуска на служител");
		System.out.println("4. Промени статус на отпуска");
		System.out.println("5. Изход");
		System.out.println("----------------------------");
	}

	private static String chooseAnOptionFromTheMenu(Scanner scanner) {
		System.out.println("Въведи избор: ");
		String choice = scanner.nextLine();
		while ((!Pattern.matches("1", choice)) && (!Pattern.matches("2", choice)) && (!Pattern.matches("3", choice))
				&& (!Pattern.matches("4", choice)) && (!Pattern.matches("5", choice))) {
			System.out.println("Въведеният от Вас номер на опция е некоректен! Моля, въведете го отново.");
			choice = scanner.nextLine();
		}
		return choice;
	}

	private static void inputUserName(Scanner scanner) throws FileNotFoundException {
		System.out.println("Въведете името си: ");
		String name = scanner.nextLine();
		name = checkIfTheNameIsCorrect(scanner, name);
		writeDownTheInputtedInformationInAFile(name);
	}

	private static String checkIfTheNameIsCorrect(Scanner scanner, String name) {
		while (!Pattern.matches(".*[А-Яа-я]+.*[А-Яа-я]", name)) {
			System.out.println("Въведеното от Вас име е некоректно. Моля, впишете ново име.");
			name = scanner.nextLine();
		}
		return name;
	}

	private static void inputUserEmail(Scanner scanner) throws FileNotFoundException {
		System.out.println("Въведете имейла си: ");
		String email = scanner.nextLine();
		email = checkIfTheEmailIsCorrect(scanner, email);
		writeDownTheInputtedInformationInAFile(email);
	}

	private static String checkIfTheEmailIsCorrect(Scanner scanner, String email) {
		while ((email.indexOf("@gmail.com") == -1) && (email.indexOf("@abv.bg") == -1)
				&& (email.indexOf("@yahoo.com") == -1) && (email.indexOf("@mail.bg") == -1)) {
			System.out.println("Въведеният от вас имейл е некоректен. Моля, въведете го отново!");
			email = scanner.nextLine();
		}
		return email;
	}

	private static void inputUserEGN(Scanner scanner) throws FileNotFoundException {
		System.out.println("Въведете единния си граждански номер: ");
		String egn = scanner.nextLine();
		int length = egn.length();
		egn = checkIfTheEGNIsCorrect(scanner, egn, length);
		writeDownTheInputtedInformationInAFile(egn);
	}

	private static String checkIfTheEGNIsCorrect(Scanner scanner, String egn, int length) {
		while (!Pattern.matches(".*[0-9]+.*[0-9]", egn) && (length != 10)) {
			System.out.println(
					"Въведеният от Вас единен граждански номер е некоректен. Моля, впишете нов единен граждански номер.");
			egn = scanner.nextLine();
		}
		return egn;
	}

	private static void inputInitialDataOfTheFurlough(Scanner scanner) throws FileNotFoundException {
		System.out.println("Въведете начална дата за Вашата отпуска: ");
		String initialData = scanner.nextLine();
		initialData = checkIfTheInitialDataIsCorrect(scanner, initialData);
		writeDownTheInputtedInformationInAFile(initialData);

	}

	private static String checkIfTheInitialDataIsCorrect(Scanner scanner, String initialData) {
		while (!Pattern.matches(".*[0-9]+.*[0-9]", initialData)) {
			System.out.println(
					"Въведената от Вас начална дата на Вашата отпуска е некоректна. Моля, впишете нова начална дата на Вашата отпуска.");
			initialData = scanner.nextLine();
		}
		return initialData;
	}

	private static void inputFinalDataOfTheFurlough(Scanner scanner) {
		System.out.println("Въведете крайна дата за Вашата отпуска: ");
		String finalData = scanner.nextLine();
		finalData = checkIfTheFinalDataIsCorrect(scanner, finalData);
		writeDownTheInputtedInformationInAFile(finalData);
	}

	private static String checkIfTheFinalDataIsCorrect(Scanner scanner, String finalData) {
		while (!Pattern.matches(".*[0-9]+.*[0-9]", finalData)) {
			System.out.println(
					"Въведената от Вас крайна дата на Вашата отпуска е некоректна. Моля, впишете нова крайна дата на Вашата отпуска.");
			finalData = scanner.nextLine();
		}
		return finalData;
	}

	private static void inputTypeOfTheFurlough(Scanner scanner) throws FileNotFoundException {
		System.out.println("Задайте тип на исканата от Вас отпуска (платена / неплатена)");
		String typeOfTheFurlough = scanner.nextLine();
		typeOfTheFurlough = checkIfTheTypeOfTheFurloughIsCorrect(scanner, typeOfTheFurlough);
		writeDownTheInputtedInformationInAFile(typeOfTheFurlough);
	}

	private static String checkIfTheTypeOfTheFurloughIsCorrect(Scanner scanner, String typeOfTheFurlough) {
		while ((!Pattern.matches("платена", typeOfTheFurlough)) && (!Pattern.matches("неплатена", typeOfTheFurlough))) {
			System.out.println("Въведеният от Вас тип на отпуската е некоректен. Моля, впишете нов тип.");
			typeOfTheFurlough = scanner.nextLine();
		}
		return typeOfTheFurlough;
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
		System.out.printf("%5s%30s%30s%50s%45s%40s", "Име", "Имейл", "ЕГН", "Начална дата на оптуската",
				"Крайна дата на отпуската", "Тип на отпуската");
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

	private static void inputTheNameOfTheWantedPerson(Scanner scanner) throws IOException, FileNotFoundException {
		System.out.println("Въведете име на служител");
		String inputtedName = scanner.nextLine();
		int numberOfTheLinesInTheFile = countsTheLinesInTheFile();
		// System.out.println(counter);
		String[] readTheDataFromTheFile = readArray(numberOfTheLinesInTheFile);
		// System.out.println(Arrays.toString(reading));
		for (int i = 0; i < numberOfTheLinesInTheFile; i += 6) {
			if (inputtedName.equals(readTheDataFromTheFile[i])) {
				String name2 = readTheDataFromTheFile[i];
				String email2 = readTheDataFromTheFile[i + 1];
				String egn2 = readTheDataFromTheFile[i + 2];
				String initialDataOfTheFurlough2 = readTheDataFromTheFile[i + 3];
				String finalDataOfTheFurlough2 = readTheDataFromTheFile[i + 4];
				String typeOfTheFurlough2 = readTheDataFromTheFile[i + 5];
				System.out.format("%1s%35s%27s%37s%47s%43s", name2, email2, egn2, initialDataOfTheFurlough2,
						finalDataOfTheFurlough2, typeOfTheFurlough2);
				System.out.println();
			}
		}
	}
}
