package task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Task2 {
    public static void main(String[] args) {
        String circleFile = "src/main/java/task2/file1.txt";
        String pointsFile = "src/main/java/task2/file2.txt";

        try {
            // читает данные о круге из файла
            BufferedReader circleReader = new BufferedReader(new FileReader(circleFile));
            String[] circleCenter = circleReader.readLine().split(" ");
            double centerX = Double.parseDouble(circleCenter[0]);
            double centerY = Double.parseDouble(circleCenter[1]);
            double radius = Double.parseDouble(circleReader.readLine());
            circleReader.close();

            // Считывание данных о точках из файла
            BufferedReader pointsReader = new BufferedReader(new FileReader(pointsFile));
            String line;
            ArrayList<String> results = new ArrayList<>();

            while ((line = pointsReader.readLine()) != null) {
                String[] point = line.split(" ");
                double pointX = Double.parseDouble(point[0]);
                double pointY = Double.parseDouble(point[1]);
                double distanceSquared = Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2);
                double radiusSquared = Math.pow(radius, 2);

                if (distanceSquared < radiusSquared) {
                    results.add("1");
                } else if (distanceSquared == radiusSquared) {
                    results.add("0");
                } else {
                    results.add("2");
                }
            }
            pointsReader.close();

            // Вывод результатов
            for (String result : results) {
                System.out.println(result);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}