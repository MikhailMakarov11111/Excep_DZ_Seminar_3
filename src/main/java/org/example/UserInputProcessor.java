package org.example;

import java.io.*;

public class UserInputProcessor {
    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine();
            String[] inputData = input.split(" ");

            if (inputData.length != 6) {
                System.out.println("Ошибка: количество введенных данных не соответствует требуемому");
                return;
            }

            String surname = inputData[0];
            String firstName = inputData[1];
            String lastName = inputData[2];
            String dateOfBirth = inputData[3];
            long phoneNumber = Long.parseLong(inputData[4]);
            char gender = inputData[5].charAt(0);

            if (!isValidDateOfBirth(dateOfBirth) || (gender != 'f' && gender != 'm')) {
                throw new IllegalArgumentException();
            }

            String fileName = surname + ".txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                writer.write(String.format("%s %s %s %s %d %c%n", surname, firstName, lastName, dateOfBirth, phoneNumber, gender));
            } catch (IOException e) {
                System.err.println("Ошибка записи данных в файл: " + e.getMessage());
            }

        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Ошибка обработки введенных данных: " + e.getMessage());
        }
    }

    private static boolean isValidDateOfBirth(String date) {
        try {
            String[] dateParts = date.split("\\.");
            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);
            return (day >= 1 && day <= 31) && (month >= 1 && month <= 12) && (year >= 1900 && year <= 9999);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e) {
            return false;
        }
    }
}