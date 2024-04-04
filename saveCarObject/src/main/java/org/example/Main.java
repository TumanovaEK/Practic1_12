package org.example;

import org.example.service.AutomobilService;

import java.io.*;
import java.util.Scanner;
import org.json.JSONObject;

public class Main {
    public static <Gson> void main(String[] args) {
        System.out.println("Туманова Екатерина Александровна, группа РИБО-02-22, вариант 1, практика1_12");
        Scanner scanner = new Scanner(System.in);

        AutomobilService.Automobil automobil = new AutomobilService.Automobil();
        System.out.println("Введите марку автомобиля:");
        automobil.brand = scanner.nextLine();
        System.out.println("Введите модель автомобиля:");
        automobil.model = scanner.nextLine();

        boolean isYearValid = false;
        while (!isYearValid) {
            try {
                System.out.println("Введите год выпуска:");
                automobil.year = Integer.parseInt(scanner.nextLine());
                isYearValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите корректный год.");
            }
        }

        boolean isPriceValid = false;
        while (!isPriceValid) {
            try {
                System.out.println("Введите цену:");
                automobil.price = Double.parseDouble(scanner.nextLine());
                isPriceValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите корректную цену.");
            }
        }

        boolean isNewValid = false;
        while (!isNewValid) {
            System.out.println("Это новый автомобиль? (true/false):");
            String isNewInput = scanner.nextLine();
            if (isNewInput.equalsIgnoreCase("true")) {
                automobil.isNew = true;
                isNewValid = true;
            } else if (isNewInput.equalsIgnoreCase("false")) {
                automobil.isNew = false;
                isNewValid = true;
            } else {
                System.out.println("Ошибка: Введите 'true' или 'false'.");
            }
        }

        boolean isPathValid = false;
        String filePath = "";
        while (!isPathValid) {
            System.out.println("Введите путь к файлу для сохранения:");
            filePath = scanner.nextLine();
            File file = new File(filePath);
            if (file.isAbsolute()) {
                isPathValid = true;
            } else {
                System.out.println("Ошибка: Введите абсолютный путь к файлу.");
            }
        }

        try {
            String finalFilePath = filePath;
            Thread serializeThread = new Thread(() -> {
                try (FileWriter fileWriter = new FileWriter(finalFilePath)) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("Brand", automobil.getBrand());
                    jsonObject.put("Model", automobil.getModel());
                    jsonObject.put("Price", automobil.getPrice());
                    jsonObject.put("Year", automobil.getYear());
                    jsonObject.put("isNew", automobil.isNew());

                    String json = jsonObject.toString();

                    fileWriter.write(json);
                    System.out.println("Данные успешно сохранены в файл: " + finalFilePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            serializeThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}