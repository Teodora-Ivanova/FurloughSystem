
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
		int choice = chooseAnOptionFromTheMenu(scanner);
		switch (choice) {
		case 1:
			inputUserName(scanner);
			inputUserEmail(scanner);
			inputUserEGN(scanner);
			inputInitialDateOfTheFurlough(scanner);
			inputFinalDateOfTheFurlough(scanner);
			inputTypeOfTheFurlough(scanner);
			break;
		case 2:
			int numberOfTheLinesInTheFile = countsTheLinesInTheFile();
			String[] readTheDataFromTheFile = readArray(numberOfTheLinesInTheFile);
			displayTheDataInATableFormat(numberOfTheLinesInTheFile, readTheDataFromTheFile);
			break;
		case 3:
			inputTheNameOfTheWantedPerson(scanner);
			break;
		case 4:
			break;
		case 5:
			break;
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

	private static int chooseAnOptionFromTheMenu(Scanner scanner) {
		System.out.println("Въведи избор: ");
		int choice = scanner.nextInt();
		choice = checkIfTheChosenOptionIsCorrect(scanner, choice);
		return choice;
	}

	private static int checkIfTheChosenOptionIsCorrect(Scanner scanner, int choice) {
		while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5) {
			System.out.println("Въведеният от Вас номер на опция е некоректен! Моля, въведете го отново.");
			choice = scanner.nextInt();
		}
		return choice;
	}

	private static void inputUserName(Scanner scanner) throws FileNotFoundException {
		System.out.println("Въведете името си: ");
		String name = scanner.nextLine();
		name = scanner.nextLine();
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

		egn = checkIfTheEGNIsCorrect(scanner, egn);
		writeDownTheInputtedInformationInAFile(egn);
	}

	private static String checkIfTheEGNIsCorrect(Scanner scanner, String egn) {
		while (!Pattern.matches("[0-9]{10}", egn)) {
			System.out.println(
					"Въведеният от Вас единен граждански номер е некоректен. Моля, впишете нов единен граждански номер.");
			egn = scanner.nextLine();
		}
		return egn;
	}

	private static void inputInitialDateOfTheFurlough(Scanner scanner) throws FileNotFoundException {
		System.out.println("Въведете начална дата за Вашата отпуска (формат: дд/мм/гггг): ");
		String initialDate = scanner.nextLine();
		initialDate = checkIfTheInitialDateIsCorrect(scanner, initialDate);
		writeDownTheInputtedInformationInAFile(initialDate);

	}

	private static String checkIfTheInitialDateIsCorrect(Scanner scanner, String initialDate) {
		while (!Pattern.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})", initialDate)) {
			System.out.println(
					"Въведената от Вас начална дата на Вашата отпуска е некоректна. Моля, впишете нова начална дата на Вашата отпуска.");
			initialDate = scanner.nextLine();
		}
		return initialDate;
	}

	private static void inputFinalDateOfTheFurlough(Scanner scanner) {
		System.out.println("Въведете крайна дата за Вашата отпуска (формат: дд/мм/гггг): ");
		String finalDate = scanner.nextLine();
		finalDate = checkIfTheFinalDateIsCorrect(scanner, finalDate);
		writeDownTheInputtedInformationInAFile(finalDate);
	}

	private static String checkIfTheFinalDateIsCorrect(Scanner scanner, String finalDate) {
		while (!Pattern.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})", finalDate)) {
			System.out.println(
					"Въведената от Вас крайна дата на Вашата отпуска е некоректна. Моля, впишете нова крайна дата на Вашата отпуска.");
			finalDate = scanner.nextLine();
		}
		return finalDate;
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
		inputtedName = scanner.nextLine();
		inputtedName = checkIfTheNameIsCorrect(scanner, inputtedName);
		int numberOfTheLinesInTheFile = countsTheLinesInTheFile();
		String[] readTheDataFromTheFile = readArray(numberOfTheLinesInTheFile);
		for (int i = 0; i < numberOfTheLinesInTheFile; i += 6) {
			if (inputtedName.equals(readTheDataFromTheFile[i])) {
				String name = readTheDataFromTheFile[i];
				String email = readTheDataFromTheFile[i + 1];
				String egn = readTheDataFromTheFile[i + 2];
				String initialDatеOfTheFurlough = readTheDataFromTheFile[i + 3];
				String finalDatеOfTheFurlough = readTheDataFromTheFile[i + 4];
				String typeOfTheFurlough = readTheDataFromTheFile[i + 5];
				System.out.format("%1s%35s%27s%37s%47s%43s", name, email, egn, initialDatеOfTheFurlough,
						finalDatеOfTheFurlough, typeOfTheFurlough);
				System.out.println();
			}
		}
	}
}
