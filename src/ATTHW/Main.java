package ATTHW;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class UserInfoApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Введите данные в произвольном порядке (Фамилия Имя Отчество датарождения номертелефона пол):");
            String userData = scanner.nextLine();

            String[] dataParts = userData.split("\\s+");

            if (dataParts.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных. Введите все 6 параметров.");
            }

            String lastName = dataParts[0];
            String firstName = dataParts[1];
            String middleName = dataParts[2];
            String birthDate = dataParts[3];
            long phoneNumber = Long.parseLong(dataParts[4]);
            char gender = dataParts[5].charAt(0);

            // Проверка формата даты рождения
            if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
                throw new IllegalArgumentException("Неверный формат даты рождения. Используйте dd.mm.yyyy.");
            }

            // Проверка формата пола
            if (gender != 'f' && gender != 'm') {
                throw new IllegalArgumentException("Неверный формат пола. Используйте 'f' или 'm'.");
            }

            // Создание файла
            String fileName = lastName + ".txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                String userInfo = lastName + firstName + middleName + birthDate + " " + phoneNumber + gender;
                writer.write(userInfo);
                writer.newLine();
                System.out.println("Данные успешно записаны в файл: " + fileName);
            } catch (IOException e) {
                throw new IOException("Проблема с чтением-записью в файл.", e);
            }

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}