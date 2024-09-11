package task1;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("значение n: "); //количество элементов в массиве
        int n = scanner.nextInt();
        System.out.print("значение m: "); //интервал, с которым будем двигаться
        int m = scanner.nextInt();

        if (n <= 0 || m <= 0) { // исключаем введение некорректных данных
            System.out.println("вводим только положительные числа");
            scanner.close();
            return;
        }

        StringBuilder result = new StringBuilder();
        int current = 1; // первый элемент пути всегда 1
        result.append(current);

        for (int i = 1; i < n; i++) { //цикл продолжается, пока i < n
            int index = (m - 1) * i; // текущий индекс в зависимости от m
            int element = (index + 1) % n; // сл элемент, берем остаток от n
            element = (element == 0) ? n : element; // если результат 0, то возвращаемся к n
            if (element == 1) break; // если вернулись к 1 элементу, финал
            result.append(element);
        }
        System.out.print(result);
    }
}

//n = 4, m = 3   решение: 1234  интервалы: 123, 341  путь: 13.
//n = 5, m = 4   решение: 12345 интервалы: 1234, 4512, 2345, 5123, 3451 путь: 14253.