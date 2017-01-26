
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
		showDifferentInformationAccordingToTheChosenOption(scanner, choice);
	}

	public static void showTheMenu() {
		System.out.println("----------------------------");
		System.out.println("1. ����� �������");
		System.out.println("2. ��� ������ �������");
		System.out.println("3. ��� ������� �� ��������");
		System.out.println("4. ������� ������ �� �������");
		System.out.println("5. �����");
		System.out.println("----------------------------");
	}

	public static int chooseAnOptionFromTheMenu(Scanner scanner) {
		System.out.println("�������� �����: ");
		int choice = scanner.nextInt();
		choice = checkIfTheChosenOptionIsCorrect(scanner, choice);
		return choice;
	}

	public static int checkIfTheChosenOptionIsCorrect(Scanner scanner, int choice) {
		while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5) {
			System.out.println("���������� �� ��� ����� �� ����� � ����������! ����, �������� �� ������.");
			choice = scanner.nextInt();
		}
		return choice;
	}

	public static void showDifferentInformationAccordingToTheChosenOption(Scanner scanner, int choice)
			throws FileNotFoundException, IOException {
		switch (choice) {
		case 1:
			inputUserName(scanner);
			inputUserEmail(scanner);
			inputUserPIN(scanner);
			inputInitialDateOfTheFurlough(scanner);
			inputFinalDateOfTheFurlough(scanner);
			inputTypeOfTheFurlough(scanner);
			System.out.println();
			showDifferentInformationAccordingToTheChosenOption(scanner, chooseAnOptionFromTheMenu(scanner));
			break;
		case 2:
			int numberOfTheLinesInTheFile = countsTheLinesInTheFile();
			String[] readTheDataFromTheFile = readArray(numberOfTheLinesInTheFile);
			displayTheDataInATableFormat(numberOfTheLinesInTheFile, readTheDataFromTheFile);
			System.out.println();
			showDifferentInformationAccordingToTheChosenOption(scanner, chooseAnOptionFromTheMenu(scanner));
			break;
		case 3:
			inputTheNameOfTheWantedPersonAndShowsTheInfoOfTheirApplication(scanner);
			System.out.println();
			showDifferentInformationAccordingToTheChosenOption(scanner, chooseAnOptionFromTheMenu(scanner));
			break;
		case 4:
			System.out.println("���������� �� ������������, �� �������� � ��� ��� ����������.\n");
			showDifferentInformationAccordingToTheChosenOption(scanner, chooseAnOptionFromTheMenu(scanner));
			break;
		case 5:
			System.out.println("���������� �� �� ���������� ������ �� �������.");
			break;
		}
	}

	public static void inputUserName(Scanner scanner) throws FileNotFoundException {
		System.out.println("�������� ����� ��: ");
		String name = scanner.nextLine();
		name = scanner.nextLine();
		name = checkIfTheNameIsCorrect(scanner, name);
		writeDownTheInputtedInformationInAFile(name);
	}

	public static String checkIfTheNameIsCorrect(Scanner scanner, String name) {
		while (!Pattern.matches(".*[�-��-�]+.*[�-��-�]", name)) {
			System.out.println("���������� �� ��� ��� � ����������. ����, ������� ���� ���.");
			name = scanner.nextLine();
		}
		return name;
	}

	public static void inputUserEmail(Scanner scanner) throws FileNotFoundException {
		System.out.println("�������� ������ ��: ");
		String email = scanner.nextLine();
		email = checkIfTheEmailIsCorrect(scanner, email);
		writeDownTheInputtedInformationInAFile(email);
	}

	public static String checkIfTheEmailIsCorrect(Scanner scanner, String email) {
		while ((email.indexOf("@gmail.com") == -1) && (email.indexOf("@abv.bg") == -1)
				&& (email.indexOf("@yahoo.com") == -1) && (email.indexOf("@mail.bg") == -1)) {
			System.out.println("���������� �� ��� ����� � ����������. ����, �������� �� ������!");
			email = scanner.nextLine();
		}
		return email;
	}

	public static void inputUserPIN(Scanner scanner) throws FileNotFoundException {
		System.out.println("�������� ������� �� ���������� �����: ");
		String pin = scanner.nextLine();
		pin = checkIfThePINIsCorrect(scanner, pin);
		writeDownTheInputtedInformationInAFile(pin);
	}

	public static String checkIfThePINIsCorrect(Scanner scanner, String pin) {
		while (!Pattern.matches("[0-9]{10}", pin)) {
			System.out.println(
					"���������� �� ��� ������ ���������� ����� � ����������. ����, ������� ��� ������ ���������� �����.");
			pin = scanner.nextLine();
		}
		return pin;
	}

	public static void inputInitialDateOfTheFurlough(Scanner scanner) throws FileNotFoundException {
		System.out.println("�������� ������� ���� �� ������ ������� (������: ��/��/����): ");
		String initialDate = scanner.nextLine();
		initialDate = checkIfTheInitialDateIsCorrect(scanner, initialDate);
		writeDownTheInputtedInformationInAFile(initialDate);
	}

	public static String checkIfTheInitialDateIsCorrect(Scanner scanner, String initialDate) {
		while (!Pattern.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})", initialDate)) {
			System.out.println(
					"���������� �� ��� ������� ���� �� ������ ������� � ����������. ����, ������� ���� ������� ���� �� ������ �������.");
			initialDate = scanner.nextLine();
		}
		return initialDate;
	}

	public static void inputFinalDateOfTheFurlough(Scanner scanner) {
		System.out.println("�������� ������ ���� �� ������ ������� (������: ��/��/����): ");
		String finalDate = scanner.nextLine();
		finalDate = checkIfTheFinalDateIsCorrect(scanner, finalDate);
		writeDownTheInputtedInformationInAFile(finalDate);
	}

	public static String checkIfTheFinalDateIsCorrect(Scanner scanner, String finalDate) {
		while (!Pattern.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})", finalDate)) {
			System.out.println(
					"���������� �� ��� ������ ���� �� ������ ������� � ����������. ����, ������� ���� ������ ���� �� ������ �������.");
			finalDate = scanner.nextLine();
		}
		return finalDate;
	}

	public static void inputTypeOfTheFurlough(Scanner scanner) throws FileNotFoundException {
		System.out.println("������� ��� �� �������� �� ��� ������� (������� / ���������)");
		String typeOfTheFurlough = scanner.nextLine();
		typeOfTheFurlough = checkIfTheTypeOfTheFurloughIsCorrect(scanner, typeOfTheFurlough);
		writeDownTheInputtedInformationInAFile(typeOfTheFurlough);
	}

	public static String checkIfTheTypeOfTheFurloughIsCorrect(Scanner scanner, String typeOfTheFurlough) {
		while ((typeOfTheFurlough.indexOf("�������") == -1) && (typeOfTheFurlough.indexOf("���������") == -1)) {
			System.out.println("���������� �� ��� ��� �� ��������� � ����������. ����, ������� ��� ���.");
			typeOfTheFurlough = scanner.nextLine();
		}
		return typeOfTheFurlough;
	}

	public static void writeDownTheInputtedInformationInAFile(String scanner) {
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

	public static int countsTheLinesInTheFile() throws IOException {
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

	public static String[] readArray(int numberOfTheLinesInTheFile) throws FileNotFoundException {
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

	public static void displayTheDataInATableFormat(int numberOfTheLinesInTheFile, String[] dataOfTheFile) {
		System.out.printf("%5s%25s%28s%37s%35s%25s\n", "���", "�����", "���", "������� ���� �� ���������",
				"������ ���� �� ���������", "��� �� ���������");
		for (int i = 0; i < numberOfTheLinesInTheFile; i += 6) {
			System.out.format("%1s%30s%25s%25s%37s%29s\n", dataOfTheFile[i], dataOfTheFile[i + 1], dataOfTheFile[i + 2],
					dataOfTheFile[i + 3], dataOfTheFile[i + 4], dataOfTheFile[i + 5]);
		}
	}

	public static void inputTheNameOfTheWantedPersonAndShowsTheInfoOfTheirApplication(Scanner scanner)
			throws IOException, FileNotFoundException {
		System.out.println("�������� ��� �� ��������");
		String inputtedName = scanner.nextLine();
		inputtedName = scanner.nextLine();
		inputtedName = checkIfTheNameIsCorrect(scanner, inputtedName);
		int numberOfTheLinesInTheFile = countsTheLinesInTheFile();
		String[] readTheDataFromTheFile = readArray(numberOfTheLinesInTheFile);
		displayTheInfoForTheWantedPersonInATableFormat(inputtedName, numberOfTheLinesInTheFile, readTheDataFromTheFile);
	}

	public static void displayTheInfoForTheWantedPersonInATableFormat(String inputtedName,
			int numberOfTheLinesInTheFile, String[] readTheDataFromTheFile) {
		boolean flag = false;
		for (int i = 0; i < numberOfTheLinesInTheFile; i += 6) {
			if (inputtedName.equals(readTheDataFromTheFile[i])) {
				flag = true;
				System.out.printf("%5s%25s%28s%37s%35s%25s\n", "���", "�����", "���", "������� ���� �� ���������",
						"������ ���� �� ���������", "��� �� ���������");
				System.out.format("%1s%30s%25s%25s%37s%29s\n", readTheDataFromTheFile[i], readTheDataFromTheFile[i + 1],
						readTheDataFromTheFile[i + 2], readTheDataFromTheFile[i + 3], readTheDataFromTheFile[i + 4],
						readTheDataFromTheFile[i + 5]);
			}
		}
		if (flag == false) {
			System.out.println("�������� � ������ ��� �� � ������� ������ �� �������!");
		}
	}
}