package task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к файлу:");

        String filename = scanner.nextLine();
        ArrayList<Integer> nums = new ArrayList<>();

        // Чтение чисел из файла
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] numbers = line.split("\\s+");
                for (String numStr : numbers) {
                    try {
                        nums.add(Integer.parseInt(numStr));
                    } catch (NumberFormatException e) {
                        System.err.println("Некорректный формат числа: " + numStr);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return;
        }

        if (nums.isEmpty()) {
            System.out.println("Файл не содержит чисел.");
            return;
        }

        // Сортировка и определение медианы
        Collections.sort(nums);
        int median = nums.get(nums.size() / 2);

        // Подсчёт минимального количества ходов
        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }

        //   src/main/java/task4/input.txt   -  на вход подается путь к файлу

        System.out.println("Минимальное количество ходов: " + moves);
    }
}