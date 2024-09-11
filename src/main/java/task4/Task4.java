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
        String filename = scanner.nextLine();  //   src/main/java/task4/input.txt   -  на вход подается путь к файлу
        ArrayList<Integer> nums = new ArrayList<>(); // + список для хранения чисел из файла

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) { // чтение чисел из файла
            String line;
            while ((line = br.readLine()) != null) {
                String[] numbers = line.split("\\s+"); // строка делится на отдельные числа
                for (String numStr : numbers) {
                    try {
                        nums.add(Integer.parseInt(numStr)); // строка -> целое число, добавляем
                    } catch (NumberFormatException e) {
                        System.err.println("Некорректный формат числа: " + numStr);
                    }
                }
            }
            // ошибка при чтении файла + ошибка при пустом списке
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return;
        }
        if (nums.isEmpty()) {
            System.out.println("Файл не содержит чисел.");
            return;
        }

        Collections.sort(nums); // сортировка
        int median = nums.get(nums.size() / 2); // определение медианы

        // подсчёт мин. количества ходов
        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        System.out.println("Минимальное количество ходов: " + moves);
    }
}



