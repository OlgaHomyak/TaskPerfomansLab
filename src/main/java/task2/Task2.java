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
            BufferedReader circleReader = new BufferedReader(new FileReader(circleFile)); // читаем данные об окружности из файла
            // строка разбивается на части пробелом
            String[] circleCenter = circleReader.readLine().split(" ");
            double centerX = Double.parseDouble(circleCenter[0]);
            double centerY = Double.parseDouble(circleCenter[1]);
            double radius = Double.parseDouble(circleReader.readLine());
            circleReader.close();

            BufferedReader pointsReader = new BufferedReader(new FileReader(pointsFile)); // читаем данные  из файла о точках
            String line;
            ArrayList<String> results = new ArrayList<>();

            while ((line = pointsReader.readLine()) != null) {
                String[] point = line.split(" ");
                double pointX = Double.parseDouble(point[0]);
                double pointY = Double.parseDouble(point[1]);

                // квадрат расстояния от точки до центра окружности
                double distanceSquared = Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2);
                //  квадрат радиуса окружности
                double radiusSquared = Math.pow(radius, 2);

                // квадрат расстояния меньше квадрата радиуса, точка внутри окружности
                if (distanceSquared < radiusSquared) {
                    results.add("1");
                // равны, точка внутри окружности
                } else if (distanceSquared == radiusSquared) {
                    results.add("0");
                // больше, точка снаружи окружности
                } else {
                    results.add("2");
                }
            }
            pointsReader.close();

            //  результаты:
            for (String result : results) {
                System.out.println(result);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}